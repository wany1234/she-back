
/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.safety.wkod.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.config.GlobalSettings;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.rsa.model.RiskHazard;
import com.she.safety.constSafe.mapper.ConstMapper;
import com.she.safety.model.Const;
import com.she.safety.model.ConstKind;
import com.she.safety.model.ConstKindSubconn;
import com.she.safety.model.ConstKindSubconnWorker;
import com.she.safety.model.ConstLegalLcn;
import com.she.safety.model.ConstLegalLcnMaster;
import com.she.safety.model.FacilityUsed;
import com.she.safety.model.LOTO;
import com.she.safety.model.Signature;
import com.she.safety.model.WkodChkItem;
import com.she.safety.model.WkodChkResult;
import com.she.safety.model.WkodGasMeas;
import com.she.safety.model.WkodKind;
import com.she.safety.model.WkodMaster;
import com.she.safety.model.WkodSubconnWorker;
import com.she.safety.wkod.mapper.WkodMasterMapper;
import com.she.utils.ConstVal;
import com.she.utils.FileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 건강검진기관 기능정의
 *
 */
@Service
public class WkodMasterService {
    @Autowired
    private WkodMasterMapper wkodMasterMapper;

    @Autowired
    private ConstMapper constMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private GlobalSettings globalSettings;

    /**
     * 작업허가서 조회
     *
     * @param plantCd
     *            사업장
     * @param constTitle
     *            공사명
     * @param wkodClsCd
     *            허가서구분
     * @param vendorNm
     *            공사업체
     * @param startYmd
     *            작업기간 시작일자
     * @param endYmd
     *            작업기간 종료일자
     * @param reqDeptCd
     *            신청부서
     * @param wkodKindCds
     *            작업종류
     * @param pubDeptCd
     *            발행부서
     * @param wkodStepCd
     *            진행단계
     * @param wkodNum
     *            작업NO
     * @param workTitle
     *            작업명
     * @param searchFlag
     *            조회구분자
     * @param constNo
     *            공사번호
     * @return 작업허가서 목록
     * @throws Exception
     */
    public List<WkodMaster> getWkodMasters(String plantCd, String wkodClsCd, String vendorCd, String startYmd, String endYmd, String reqDeptCd, String reqDeptSubYn, String[] wkodKindCds, String pubDeptCd, String pubDeptSubYn, String wkodStepCd, String wkodNum, String keyword, String searchFlag, String constNo, String psmYn, String subconnNm,
            DefaultParam defaultParam) throws Exception {
        return wkodMasterMapper.getWkodMasters(plantCd, wkodClsCd, vendorCd, startYmd, endYmd, reqDeptCd, reqDeptSubYn, wkodKindCds, pubDeptCd, pubDeptSubYn, wkodStepCd, wkodNum, keyword, searchFlag, constNo, psmYn, subconnNm, defaultParam);
    }

    // /**
    // * 작업종류 조회 (공통으로 작업된 기능과 다르게 배열로 값을 받아 처리해야 하기에 따로 개발)
    // *
    // * @param code_group_cd
    // * @param attr1[]
    // * @return 작업종류 목록
    // * @throws Exception
    // */
    // public List<CodeMaster> getWkodKinds(String codeGroupCd, String attr1)
    // throws Exception {
    // return wkodMasterMapper.getWkodKinds(codeGroupCd, attr1);
    // }

    /**
     * 작업허가서 상세 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업허가서 목록
     * @throws Exception
     */
    public WkodMaster getWkodMaster(int wkodNo, DefaultParam defaultParam) throws Exception {

        WkodMaster wkodMaster = wkodMasterMapper.getWkodMaster(wkodNo, defaultParam);

        if (wkodMaster != null) {
            // 작업구분
            wkodMaster.setWkodKinds(wkodMasterMapper.getWkodKind(wkodNo, defaultParam));
            // 가스농도측정
            wkodMaster.setWkodGasMeass(wkodMasterMapper.getWkodGasMeas(wkodNo));
            // 안전조치사항
            wkodMaster.setWkodCheckResult(wkodMasterMapper.getSafWkodChkResult(wkodNo));
            // 보호구
            wkodMaster.setWkodSpeCds(wkodMasterMapper.getSafWkodUseSpe(wkodNo));
            // 유해위험 요인
            wkodMaster.setSelectHandleChemContentRow(wkodMasterMapper.getWkodHazardSelectList(wkodNo));
            // 작업자 정보
            wkodMaster.setWkodSubconnWorkers(wkodMasterMapper.getWkodSubconnWorkers(wkodNo));
            // 설비&LOTO
            wkodMaster.setWkodLotos(wkodMasterMapper.getWkodLotos(wkodNo));
        }

        return wkodMaster;
    }

    /**
     * 작업허가서 현황 조회
     *
     * @param plantCd
     *            사업장
     * @param reqDeptCd
     *            신청부서
     * @param subconnNm
     *            공사업체
     * @param startYmd
     *            작업시작일
     * @param wkodNo
     *            작업종료일
     * @return 작업허가서 현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getWkodMasterStatus(String plantCd, String reqDeptCd, String subconnNm, String startYmd, String endYmd, DefaultParam defaultParam) throws Exception {
        // 허가서구분별 작업구분 조회
        List<String> wkodKinds = wkodMasterMapper.getWkodClsKinds();
        return wkodMasterMapper.getWkodMasterStatus(plantCd, reqDeptCd, subconnNm, startYmd, endYmd, wkodKinds.get(0), wkodKinds.get(1), defaultParam);
    }

    /**
     * 작업허가서 현황 조회 (사업장에 따른 신청부서 별 주관부서별 공사업체)
     *
     * @param plantCd
     *            사업장
     * @param reqDeptCd
     *            신청부서
     * @param vendorCd
     *            공사업체
     * @param startYmd
     *            작업시작일
     * @param wkodNo
     *            작업종료일
     * @return 작업허가서 현황 (사업장에 따른 신청부서 별 주관부서별 공사업체)
     * @throws Exception
     */
    public List<HashMap<String, Object>> getWkodMasterStatusSub(String plantCd, String reqDeptCd, String pubDeptCd, String subconnNm, String startYmd, String endYmd, DefaultParam defaultParam) throws Exception {
        // 허가서구분별 작업구분 조회
        List<String> wkodKinds = wkodMasterMapper.getWkodClsKinds();
        return wkodMasterMapper.getWkodMasterStatusSub(plantCd, reqDeptCd, pubDeptCd, subconnNm, startYmd, endYmd, wkodKinds.get(0), wkodKinds.get(1), defaultParam);
    }

    /**
     * 작업허가서 안전보호구 조회 c
     * 
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업허가서 안전보호구 목록
     * @throws Exception
     */
    public List<String> getSafWkodUseSpe(int wkodNo) throws Exception {
        return wkodMasterMapper.getSafWkodUseSpe(wkodNo);
    }

    /**
     * 작업허가서 취급물질 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업허가서 취급물질 목록
     * @throws Exception
     */
    public List<RiskHazard> getWkodHazardSelectList(int wkodNo) throws Exception {
        return wkodMasterMapper.getWkodHazardSelectList(wkodNo);
    }

    // /**
    // * 안전점검확인 결과 조회
    // * @param wkodNo 작업허가서 ID
    // * @param searhFlag 조회구분
    // * @return 작업허가서 목록
    // * @throws Exception
    // */
    // public List<WkodChkItem> getWkodDepts(int wkodNo, String searchFlag)
    // throws Exception
    // {
    // return wkodMasterMapper.getWkodDepts(wkodNo, searchFlag);
    // }
    //
    // /**
    // * 안전점검확인 결과(저장된 데이터) 조회
    // * @param wkodNo 작업허가서 ID
    // * @param searhFlag 조회구분
    // * @return 작업허가서 목록
    // * @throws Exception
    // */
    // public List<WkodChkItem> getSelectedWkodDepts(int wkodNo, String
    // searchFlag) throws Exception
    // {
    // return wkodMasterMapper.getSelectedWkodDepts(wkodNo, searchFlag);
    // }

    /**
     * 작업허가서 항목 생성
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 작업허가서 번호
     * @throws Exception
     */

    @Transactional
    public int createWkodMaster(WkodMaster wkodMaster) throws Exception {

        // 공사번호는 없는데 공사명이 있는 경우 공사정보를 직접입력했다고 가정하여 공사정보를 신규등록한다.
        if ((wkodMaster.getConstNo() == null || "".equals(wkodMaster.getConstNo())) && !"".equals(wkodMaster.getConstTitle())) {
            Const constData = new Const();
            constData.setConstTitle(wkodMaster.getConstTitle()); // 공사명
            constData.setConstStartYmd(wkodMaster.getConstStartYmd()); // 작업시작일
            constData.setConstEndYmd(wkodMaster.getConstEndYmd()); // 작업종료일
            constData.setConstContent(wkodMaster.getConstContent()); // 공사내용
            constData.setPlantCd(wkodMaster.getPlantCd()); // 사업장
            constData.setConstStepCd(ConstVal.SAF_CONST_STEP_WORKING);
            constData.setCreateDeptCd(wkodMaster.getReqDeptCd()); // 등록자 소속부서
            constData.setShowVendorYn("Y"); // 회사조회여부?
            constData.setCreateUserId(wkodMaster.getCreateUserId()); // 등록자
            // 공사현황 신규등록
            constData.setSeqValue(constMapper.getConstSeq());
            constMapper.createConst(constData);

            // 공사현황 작업구분 리스트 신규등록
            for (WkodKind wkodKind : wkodMaster.getWkodKinds()) {
                ConstKind constKind = new ConstKind();
                constKind.setConstNo(constData.getConstNo());
                constKind.setWkodKindCd(wkodKind.getWkodKindCd());
                this.constMapper.createConstKind(constKind);

                ConstKindSubconn constKindSubconn = new ConstKindSubconn();
                constKindSubconn.setConstNo(constData.getConstNo());
                constKindSubconn.setWorkPasserYn(wkodMaster.getWkodSubconnWorkers() != null && wkodMaster.getWkodSubconnWorkers().size() > 0 ? "Y" : "N");
                constKindSubconn.setSelfYn("Y");
                constKindSubconn.setWkodKindCd(wkodKind.getWkodKindCd());
                constKindSubconn.setConstStartYmd(constData.getConstStartYmd());
                constKindSubconn.setConstEndYmd(constData.getConstEndYmd());
                constKindSubconn.setSubconnStepCd("STEP2");
                constKindSubconn.setVendorCd("");
                this.constMapper.createConstKindSubconn(constKindSubconn);

                // 공사현황 작업구분별 업체 작업자 리스트 신규등록
                if (wkodMaster.getWkodSubconnWorkers() != null) {
                    for (WkodSubconnWorker wkodSubconnWorker : wkodMaster.getWkodSubconnWorkers()) {
                        ConstKindSubconnWorker constKindSubconnWorker = new ConstKindSubconnWorker();
                        constKindSubconnWorker.setConstKindSubconnNo(constKindSubconn.getConstKindSubconnNo());
                        constKindSubconnWorker.setSelfYn("Y");
                        constKindSubconnWorker.setUserId(wkodSubconnWorker.getUserId());
                        constKindSubconnWorker.setWorkerNm(wkodSubconnWorker.getWorkerNm());
                        constKindSubconnWorker.setDeptCd(wkodSubconnWorker.getDeptCd());
                        constKindSubconnWorker.setDeptNm(wkodSubconnWorker.getDeptNm());
                        constKindSubconnWorker.setBirthYmd(wkodSubconnWorker.getBirthYmd());
                        constKindSubconnWorker.setGenderCd(wkodSubconnWorker.getGenderCd());
                        constKindSubconnWorker.setWorkerJob(wkodSubconnWorker.getWorkerJob());
                        constKindSubconnWorker.setSpeCommts(wkodSubconnWorker.getSpeCommts());
                        constKindSubconnWorker.setCarNum(wkodSubconnWorker.getCarNum());
                        constKindSubconnWorker.setInEquip(wkodSubconnWorker.getInEquip());
                        this.constMapper.createConstKindSubconnWorker(constKindSubconnWorker);
                    }
                }
            }
            wkodMaster.setConstNo(constData.getConstNo());
        }

        if ("" != wkodMaster.getConfirmFlag() && null != wkodMaster.getConfirmFlag()) {
            wkodMaster.setWkodStepCd(wkodMaster.getConfirmFlag());
        }

        if ("true".equals(wkodMaster.getIsApprRequest())) {
            wkodMaster.setReqApprStepCd(ConstVal.COM_BIZ_APPR_APPR_ING);
        } else {
            wkodMaster.setReqApprStepCd(ConstVal.COM_BIZ_APPR_WRITE);
        }

        // 작업허가서 신규등록
        wkodMasterMapper.createWkodMaster(wkodMaster);

        // 작업구분 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodKinds())) {
            for (WkodKind wkodKind : wkodMaster.getWkodKinds()) {
                wkodKind.setWkodNo(wkodMaster.getWkodNo());
                wkodMasterMapper.createWkodKind(wkodKind);
            }
        }

        // 가스농도측정 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodGasMeass())) {
            for (WkodGasMeas wkodGasMeas : wkodMaster.getWkodGasMeass()) {
                wkodGasMeas.setWkodNo(wkodMaster.getWkodNo());
                wkodMasterMapper.createWkodGasMeas(wkodGasMeas);
            }
        }

        // 작업허가서 별 유해위험 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getSelectHandleChemContentRow())) {
            for (RiskHazard riskHazard : wkodMaster.getSelectHandleChemContentRow()) {
                wkodMasterMapper.createWkodHazard(wkodMaster.getWkodNo(), riskHazard.getRiskHazardNo());
            }
        }

        // 작업허가서 별 사용 보호구 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodSpeCds())) {
            for (String wkodSpeCd : wkodMaster.getWkodSpeCds()) {
                wkodMasterMapper.createWkodUseSpe(wkodMaster.getWkodNo(), wkodSpeCd);
            }
        }

        // 안전조치사항 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodCheckResult())) {
            for (WkodChkResult wkodChkResult : wkodMaster.getWkodCheckResult()) {
                wkodChkResult.setWkodNo(wkodMaster.getWkodNo());
                wkodMasterMapper.createWkodChkResult(wkodChkResult);
            }
        }

        // 법적인허가대상 항목 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodLegalLcns())) {
            for (ConstLegalLcn constLegalLcn : wkodMaster.getWkodLegalLcns()) {
                constLegalLcn.setWkodNo(wkodMaster.getWkodNo());
                wkodMasterMapper.createWkodNoLegalLcn(constLegalLcn);
            }
        }

        // 작업자정보 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodSubconnWorkers())) {
            for (WkodSubconnWorker wkodSubconnWorker : wkodMaster.getWkodSubconnWorkers()) {
                wkodSubconnWorker.setWkodNo(wkodMaster.getWkodNo());
                wkodMasterMapper.createWkodSubconnWorker(wkodSubconnWorker);
            }
        }

        // LOTO 신규등록
        if (CollectionUtils.isNotEmpty(wkodMaster.getWkodLotos())) {
            for (LOTO loto : wkodMaster.getWkodLotos()) {
                loto.setWkodNo(wkodMaster.getWkodNo());
                this.wkodMasterMapper.createWkodLoto(loto);
            }
        }

        return wkodMaster.getWkodNo();
    }

    /**
     * 작업허가서 항목 수정
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 작업허가서 번호
     * @throws Exception
     */
    @Transactional
    public int updateWkodMaster(WkodMaster wkodMaster) throws Exception {
        // 공사번호는 없는데 공사명이 있는 경우 공사정보를 직접입력했다고 가정하여 공사정보를 신규등록한다.
        if ((wkodMaster.getConstNo() == null || "".equals(wkodMaster.getConstNo())) && !"".equals(wkodMaster.getConstTitle()) && wkodMaster.getConstTitle() != null) {
            Const constData = new Const();
            constData.setSeqValue(constMapper.getConstSeq());
            constData.setConstTitle(wkodMaster.getConstTitle()); // 공사명
            constData.setConstStartYmd(wkodMaster.getConstStartYmd()); // 작업시작일
            constData.setConstEndYmd(wkodMaster.getConstEndYmd()); // 작업종료일
            constData.setConstContent(wkodMaster.getConstContent()); // 공사내용
            constData.setPlantCd(wkodMaster.getPlantCd()); // 사업장
            constData.setConstStepCd(ConstVal.SAF_CONST_STEP_WORKING);
            constData.setCreateDeptCd(wkodMaster.getReqDeptCd()); // 등록자 소속부서
            constData.setShowVendorYn("Y"); // 회사조회여부?
            constData.setCreateUserId(wkodMaster.getCreateUserId()); // 등록자
            // 공사현황 신규등록
            constMapper.createConst(constData);

            // 공사현황 작업구분 리스트 신규등록
            for (WkodKind wkodKind : wkodMaster.getWkodKinds()) {
                ConstKind constKind = new ConstKind();
                constKind.setConstNo(constData.getConstNo());
                constKind.setWkodKindCd(wkodKind.getWkodKindCd());
                this.constMapper.createConstKind(constKind);

                ConstKindSubconn constKindSubconn = new ConstKindSubconn();
                constKindSubconn.setConstNo(constData.getConstNo());
                constKindSubconn.setWorkPasserYn(wkodMaster.getWkodSubconnWorkers() != null && wkodMaster.getWkodSubconnWorkers().size() > 0 ? "Y" : "N");
                constKindSubconn.setSelfYn("Y");
                constKindSubconn.setWkodKindCd(wkodKind.getWkodKindCd());
                constKindSubconn.setConstStartYmd(constData.getConstStartYmd());
                constKindSubconn.setConstEndYmd(constData.getConstEndYmd());
                constKindSubconn.setSubconnStepCd("STEP2");
                constKindSubconn.setVendorCd("");
                this.constMapper.createConstKindSubconn(constKindSubconn);

                // 공사현황 작업구분별 업체 작업자 리스트 신규등록
                if (wkodMaster.getWkodSubconnWorkers() != null) {
                    for (WkodSubconnWorker wkodSubconnWorker : wkodMaster.getWkodSubconnWorkers()) {
                        ConstKindSubconnWorker constKindSubconnWorker = new ConstKindSubconnWorker();
                        constKindSubconnWorker.setConstKindSubconnNo(constKindSubconn.getConstKindSubconnNo());
                        constKindSubconnWorker.setSelfYn("Y");
                        constKindSubconnWorker.setUserId(wkodSubconnWorker.getUserId());
                        constKindSubconnWorker.setWorkerNm(wkodSubconnWorker.getWorkerNm());
                        constKindSubconnWorker.setDeptCd(wkodSubconnWorker.getDeptCd());
                        constKindSubconnWorker.setDeptNm(wkodSubconnWorker.getDeptNm());
                        constKindSubconnWorker.setBirthYmd(wkodSubconnWorker.getBirthYmd());
                        constKindSubconnWorker.setGenderCd(wkodSubconnWorker.getGenderCd());
                        constKindSubconnWorker.setWorkerJob(wkodSubconnWorker.getWorkerJob());
                        constKindSubconnWorker.setSpeCommts(wkodSubconnWorker.getSpeCommts());
                        constKindSubconnWorker.setCarNum(wkodSubconnWorker.getCarNum());
                        constKindSubconnWorker.setInEquip(wkodSubconnWorker.getInEquip());
                        this.constMapper.createConstKindSubconnWorker(constKindSubconnWorker);
                    }
                }
            }
            wkodMaster.setConstNo(constData.getConstNo());
        }

        if ("" != wkodMaster.getConfirmFlag() && null != wkodMaster.getConfirmFlag()) {
            wkodMaster.setWkodStepCd(wkodMaster.getConfirmFlag());
        }

        // 작업허가서 수정
        wkodMasterMapper.updateWkodMaster(wkodMaster);

        // 작업구분 수정
        if (wkodMaster.getWkodKinds() != null) {
            // 작업구분 삭제
            wkodMasterMapper.deleteWkodKind(wkodMaster.getWkodNo());
            if (wkodMaster.getWkodKinds().size() > 0) {
                // 작업구분 등록
                for (WkodKind wkodKind : wkodMaster.getWkodKinds()) {
                    wkodKind.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodKind(wkodKind);
                }

            }
        }

        // 가스농도측정 수정
        if (wkodMaster.getWkodGasMeass() != null) {
            // 가스농도측정 삭제
            wkodMasterMapper.deleteWkodGasMeas(wkodMaster.getWkodNo());
            if (wkodMaster.getWkodGasMeass().size() > 0) {
                // 가스농도측정 등록
                for (WkodGasMeas wkodGasMeas : wkodMaster.getWkodGasMeass()) {
                    wkodGasMeas.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodGasMeas(wkodGasMeas);
                }

            }
        }

        // 작업허가서 별 유해위험 수정
        if (wkodMaster.getSelectHandleChemContentRow() != null) {
            wkodMasterMapper.deleteWkodHazard(wkodMaster.getWkodNo());
            if (wkodMaster.getSelectHandleChemContentRow().size() > 0) {
                for (RiskHazard riskHazard : wkodMaster.getSelectHandleChemContentRow()) {
                    wkodMasterMapper.createWkodHazard(wkodMaster.getWkodNo(), riskHazard.getRiskHazardNo());
                }
            }
        }

        // 작업허가서 별 사용 보호구 신규등록
        if (wkodMaster.getWkodSpeCds() != null) {
            wkodMasterMapper.deleteWkodUseSpe(wkodMaster.getWkodNo());
            for (String wkodSpeCd : wkodMaster.getWkodSpeCds()) {
                wkodMasterMapper.createWkodUseSpe(wkodMaster.getWkodNo(), wkodSpeCd);
            }
        }

        // 안전조치사항 수정
        if (wkodMaster.getWkodCheckResult() != null) {
            // 안전점검 점검확인항목 삭제
            wkodMasterMapper.deleteWkodChkResult(wkodMaster.getWkodNo());
            // 안전점검 점검확인항목 신규등록
            if (wkodMaster.getWkodCheckResult().size() > 0) {
                for (WkodChkResult wkodChkResult : wkodMaster.getWkodCheckResult()) {
                    wkodChkResult.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodChkResult(wkodChkResult);
                }
            }
        }

        // wkodMasterService.createWkodUseFacility(wkodMaster);

        // 법적인허가 대상 수정
        if (wkodMaster.getWkodLegalLcns() != null) {
            // 작업허가별 법적인허가대상 항목 삭제
            wkodMasterMapper.deleteWkodNoLegalLcn(wkodMaster.getWkodNo());

            if (wkodMaster.getWkodLegalLcns().size() > 0) {
                // 작업허가별 법적인허가대상 항목 신규등록
                for (ConstLegalLcn constLegalLcn : wkodMaster.getWkodLegalLcns()) {
                    constLegalLcn.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodNoLegalLcn(constLegalLcn);
                }
            }
        }

        // 작업자정보 수정
        if (wkodMaster.getWkodSubconnWorkers() != null) {
            // 작업자정보 삭제
            wkodMasterMapper.deleteWkodSubconnWorker(wkodMaster.getWkodNo());
            if (wkodMaster.getWkodSubconnWorkers().size() > 0) {
                // 작업자정보 등록
                for (WkodSubconnWorker wkodSubconnWorker : wkodMaster.getWkodSubconnWorkers()) {
                    wkodSubconnWorker.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodSubconnWorker(wkodSubconnWorker);
                }
            }
        }

        // LOTO 수정
        if (wkodMaster.getWkodLotos() != null) {
            this.wkodMasterMapper.deleteWkodLoto(wkodMaster.getWkodNo());
            for (LOTO loto : wkodMaster.getWkodLotos()) {
                loto.setWkodNo(wkodMaster.getWkodNo());
                this.wkodMasterMapper.createWkodLoto(loto);
            }
        }

        return wkodMaster.getWkodNo();
    }

    /**
     * 작업허가서 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 작업허가서 번호
     * @throws Exception
     */
    public int deleteWkodMaster(WkodMaster wkodMaster) throws Exception {
        int result = 0;

        // 작업구분 삭제
        result += wkodMasterMapper.deleteWkodKind(wkodMaster.getWkodNo());

        // 유해 위험 요인 삭제
        result += wkodMasterMapper.deleteWkodHazard(wkodMaster.getWkodNo());

        // 가스농도측정 삭제
        result += wkodMasterMapper.deleteWkodGasMeas(wkodMaster.getWkodNo());

        // 작업허가서별 사용 보호구 삭제
        result += wkodMasterMapper.deleteWkodUseSpe(wkodMaster.getWkodNo());

        // 안전조치사항 삭제
        result += wkodMasterMapper.deleteWkodLoto(wkodMaster.getWkodNo());

        // 법적 인허가 대상 삭제
        result += wkodMasterMapper.deleteWkodNoLegalLcn(wkodMaster.getWkodNo());

        // 업체 작업자 삭제
        result += wkodMasterMapper.deleteWkodSubconnWorker(wkodMaster.getWkodNo());

        // LOTO 삭제
        result += wkodMasterMapper.deleteWkodChkResult(wkodMaster.getWkodNo());

        // 작업허가서 삭제
        result += wkodMasterMapper.deleteWkodMaster(wkodMaster);

        return result;
    }

    /**
     * 작업허가서별 설비 조회
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 작업허가서별 설비 목록
     * @throws Exception
     */
    public List<FacilityUsed> getWkodMasterFacility(int wkodNo, DefaultParam defaultParam) throws Exception {
        return wkodMasterMapper.getWkodMasterFacility(wkodNo, defaultParam);
    }

    /**
     * 작업허가서별 설비 생성
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createWkodUseFacility(WkodMaster wkodMaster) throws Exception {
        // 작업허가별 설비 삭제
        if (wkodMaster.getSafFacilityCds() != null) {
            wkodMasterMapper.deleteWkodMasterFacility(wkodMaster.getWkodNo(), "");

            // 작업허가별 설비 생성
            if (wkodMaster.getSafFacilityCds().size() > 0) {
                for (FacilityUsed facilityUsed : wkodMaster.getSafFacilityCds()) {
                    facilityUsed.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodUseFacility(facilityUsed);
                }
            }
        }

        return wkodMaster.getWkodNo();
    }

    /**
     * 작업허가서별 설비 삭제
     *
     * @param facilityUsed
     *            작업허가서별 설비
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteWkodMasterFacility(List<FacilityUsed> facilityUseds) throws Exception {
        int resultNo = 0;
        if (facilityUseds != null && facilityUseds.size() > 0) {
            for (FacilityUsed facilityUsed : facilityUseds) {
                resultNo += wkodMasterMapper.deleteWkodMasterFacility(facilityUsed.getWkodNo(), facilityUsed.getSafFacilityCd());
            }
        }
        return resultNo;
    }

    /**
     * 법적인허가대상 조회
     *
     * @param wkodNo
     *            작업허가서번호
     * @return 법적인허가대상
     * @throws Exception
     */
    public List<ConstLegalLcnMaster> getWkodLegalLcns(String constNo) throws Exception {
        // 법적인허가 분류 조회
        List<ConstLegalLcnMaster> wkodLegalLcnMasters = wkodMasterMapper.getWkodLegalLcnGrps();
        if (wkodLegalLcnMasters != null) {
            for (ConstLegalLcnMaster wkodLegalLcnMaster : wkodLegalLcnMasters) {
                // 법적인허가 분류에 따른 항목 조회
                List<CodeMaster> codeMasters = codeMasterMapper.getSelect(wkodLegalLcnMaster.getGrpCd(), "Y", new DefaultParam("kr"));

                List<ConstLegalLcn> comboItems = new ArrayList<ConstLegalLcn>();
                if (codeMasters != null) {
                    for (CodeMaster codeMaster : codeMasters) {
                        ConstLegalLcn comboItem = new ConstLegalLcn();
                        comboItem.setGrpCd(wkodLegalLcnMaster.getGrpCd());
                        comboItem.setGrpNm(wkodLegalLcnMaster.getGrpNm());
                        comboItem.setItmCd(codeMaster.getCode());
                        comboItem.setItmNm(codeMaster.getCodeNm());

                        comboItems.add(comboItem);
                    }
                }
                wkodLegalLcnMaster.setComboItems(comboItems);

                // 작업허가서번호에 따른 법적인허가 대상 항목 조회
                wkodLegalLcnMaster.setConstLegalLcns(wkodMasterMapper.getWkodLegalLcns(constNo, wkodLegalLcnMaster.getGrpCd()));
            }
        }
        return wkodLegalLcnMasters;
    }

    /**
     * 법적인허가대상 조회
     *
     * @param wkodNo
     *            작업허가서번호
     * @return 법적인허가대상
     * @throws Exception
     */
    public List<ConstLegalLcnMaster> getWkodNoLegalLcns(int wkodNo) throws Exception {
        // 법적인허가 분류 조회
        List<ConstLegalLcnMaster> wkodLegalLcnMasters = wkodMasterMapper.getWkodLegalLcnGrps();
        if (wkodLegalLcnMasters != null) {
            for (ConstLegalLcnMaster wkodLegalLcnMaster : wkodLegalLcnMasters) {
                // 법적인허가 분류에 따른 항목 조회
                List<CodeMaster> codeMasters = codeMasterMapper.getSelect(wkodLegalLcnMaster.getGrpCd(), "Y", new DefaultParam("kr"));

                List<ConstLegalLcn> comboItems = new ArrayList<ConstLegalLcn>();
                if (codeMasters != null) {
                    for (CodeMaster codeMaster : codeMasters) {
                        ConstLegalLcn comboItem = new ConstLegalLcn();
                        comboItem.setGrpCd(wkodLegalLcnMaster.getGrpCd());
                        comboItem.setGrpNm(wkodLegalLcnMaster.getGrpNm());
                        comboItem.setItmCd(codeMaster.getCode());
                        comboItem.setItmNm(codeMaster.getCodeNm());

                        comboItems.add(comboItem);
                    }
                }
                wkodLegalLcnMaster.setComboItems(comboItems);

                // 작업허가서번호에 따른 법적인허가 대상 항목 조회
                wkodLegalLcnMaster.setConstLegalLcns(wkodMasterMapper.getWkodNoLegalLcns(wkodNo, wkodLegalLcnMaster.getGrpCd()));
            }
        }
        return wkodLegalLcnMasters;
    }

    public List<LOTO> getWkodLotos(int wkodNo) throws Exception {
        return wkodMasterMapper.getWkodLotos(wkodNo);
    }

    /**
     * 법적인허가 대상 항목 신규등록
     *
     * @param wkodMaster
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public String createWkodLegalLcn(Const constSafe) throws Exception {
        if (constSafe.getConstLegalLcns() != null) {
            // 작업허가별 법적인허가대상 항목 삭제
            wkodMasterMapper.deleteWkodLegalLcn(constSafe.getConstNo());

            if (constSafe.getConstLegalLcns().size() > 0) {
                // 작업허가별 법적인허가대상 항목 신규등록
                for (ConstLegalLcn constLegalLcn : constSafe.getConstLegalLcns()) {
                    constLegalLcn.setConstNo(constSafe.getConstNo());
                    wkodMasterMapper.createWkodLegalLcn(constLegalLcn);
                }
            }
        }

        return constSafe.getConstNo();
    }

    /**
     * 법적인허가 대상 항목 신규등록 작업허가서
     *
     * @param wkodMaster
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createWkodNoLegalLcn(WkodMaster wkodMaster) throws Exception {
        if (wkodMaster.getWkodLegalLcns() != null) {
            // 작업허가별 법적인허가대상 항목 삭제
            wkodMasterMapper.deleteWkodNoLegalLcn(wkodMaster.getWkodNo());

            if (wkodMaster.getWkodLegalLcns().size() > 0) {
                // 작업허가별 법적인허가대상 항목 신규등록
                for (ConstLegalLcn constLegalLcn : wkodMaster.getWkodLegalLcns()) {
                    constLegalLcn.setWkodNo(wkodMaster.getWkodNo());
                    wkodMasterMapper.createWkodNoLegalLcn(constLegalLcn);
                }
            }
        }

        return wkodMaster.getWkodNo();
    }

    /**
     * 작업구분 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업구분 목록
     * @throws Exception
     */
    public List<WkodKind> getWkodKind(int wkodNo, DefaultParam defaultParam) throws Exception {
        return wkodMasterMapper.getWkodKind(wkodNo, defaultParam);
    }

    /**
     * 가스농도측정 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 가스농도측정 목록
     * @throws Exception
     */
    public List<WkodGasMeas> getWkodGasMeas(int wkodNo) throws Exception {
        return wkodMasterMapper.getWkodGasMeas(wkodNo);
    }

    /**
     * 작업허가서별 작업자 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업허가서별 작업자 목록
     * @throws Exception
     */
    public List<WkodSubconnWorker> getWkodSubconnWorkers(int wkodNo) throws Exception {
        return wkodMasterMapper.getWkodSubconnWorkers(wkodNo);
    }

    /**
     * 작업허가서 진행단계 변경
     *
     * @param wkodNo
     *            작업허가서 ID
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeWkodMaster(int wkodNo, int apprRqstNo) throws Exception {
        return wkodMasterMapper.completeWkodMaster(wkodNo, apprRqstNo, "");
    }

    /**
     * 작업허가서 진행단계 변경
     *
     * @param wkodNo
     *            작업허가서 ID
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessWkodMaster(int wkodNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String wkodStpeCd = "";
        // 결재완료
        WkodMaster wkodMaster = wkodMasterMapper.getWkodMaster(wkodNo, new DefaultParam("kr"));
        wkodStpeCd = wkodMaster.getWkodStepCd();
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 신청의 경우
            if (wkodStpeCd.equals(ConstVal.SAF_WKOD_STEP_REQUEST)) {
                wkodStpeCd = ConstVal.SAF_WKOD_STEP_PRINT;
            }
        }
        // 작업허가서 결재 진행단계 수정
        resultNo += wkodMasterMapper.completeWkodMaster(wkodNo, apprRqstNo, wkodStpeCd);
        return resultNo;
    }

    /**
     * 작업허가서 출력
     * 
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업허가서 목록
     * @throws Exception
     */
    @SuppressWarnings("null")
    public File getWkodMasterPrint(int wkodNo, DefaultParam defaultParam) throws Exception {
        // 작업허가서 && 공사
        WkodMaster wkodMaster = wkodMasterMapper.getWkodMaster(wkodNo, defaultParam);

        // 작업구분
        List<WkodKind> wkodKinds = wkodMasterMapper.getWkodKindPrint(wkodNo, defaultParam);
        JRDataSource wkodKindList = new JRBeanCollectionDataSource(wkodKinds);

        // 보호구
        List<HashMap<String, Object>> wkodSpeCds = wkodMasterMapper.getSafWkodUseSpeAllInfo(wkodNo, defaultParam);
        JRDataSource wkodSpeCdList = new JRBeanCollectionDataSource(wkodSpeCds);
        // 유햐위험요인
        List<RiskHazard> selectHandleChemContentRow = wkodMasterMapper.getWkodHazardSelectList(wkodNo);
        if (selectHandleChemContentRow == null || selectHandleChemContentRow.size() == 0) {
            RiskHazard blankRiskHazard = new RiskHazard();
            blankRiskHazard.setRiskHazardNo(0);
            blankRiskHazard.setRiskHazardNm("");
            if (null == selectHandleChemContentRow && selectHandleChemContentRow.size() == 0) {
                selectHandleChemContentRow.add(blankRiskHazard);

            }
        }
        JRDataSource selectHandleChemContentList = new JRBeanCollectionDataSource(selectHandleChemContentRow);
        // LOTO
        List<LOTO> wkodLotos = wkodMasterMapper.getWkodLotos(wkodMaster.getWkodNo());
        if (wkodLotos == null || wkodLotos.size() == 0) {
            LOTO blankLOTO = new LOTO();
            blankLOTO.setConstNo("");
            blankLOTO.setSafFacilityCd("");
            blankLOTO.setFacilityNm("");
            blankLOTO.setLockLocate("");
            blankLOTO.setSafFacilityTypeCd("");
            blankLOTO.setSafFacilityTypeNm("");
            if (wkodLotos == null && wkodLotos.size() == 0) {
                wkodLotos.add(blankLOTO);
            }

        }

        JRDataSource lotoList = new JRBeanCollectionDataSource(wkodLotos);
        // 안전조치항목
        List<WkodChkItem> wkodCheckResult = wkodMasterMapper.getWkodChkItemPrint(wkodNo, defaultParam);
        if (CollectionUtils.isEmpty(wkodCheckResult)) {
            wkodCheckResult.add(new WkodChkItem());
            wkodCheckResult.get(0).setChkItemNm("N/A");
        }
        JRDataSource confirmList = new JRBeanCollectionDataSource(wkodCheckResult);

        // 파일경로 setting
        String reportPath = globalSettings.getReportWkod();
        String subReportDir = globalSettings.getReportWkodDir();

        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = (file != null ? file.getAbsolutePath() : null);
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        List<WkodMaster> data = new ArrayList<WkodMaster>();
        data.add(wkodMaster);
        JRDataSource datasource = new JRBeanCollectionDataSource(data);

        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("subreport_dir", subReportDir);
        parameters.put("wkodKindNm", wkodMaster.getWkodClsNm());
        parameters.put("plantNm", wkodMaster.getPlantNm());
        parameters.put("constNo", wkodMaster.getConstNum());
        parameters.put("period", (wkodMaster.getConstStartYmd() != null && wkodMaster.getConstEndYmd() != null) ? wkodMaster.getConstStartYmd() + "~" + wkodMaster.getConstEndYmd() : null);
        parameters.put("constTitle", wkodMaster.getConstTitle());
        parameters.put("constContent", wkodMaster.getConstContent());
        parameters.put("reqDeptNm", wkodMaster.getReqDeptNm());
        parameters.put("reqUserNm", wkodMaster.getReqUserNm());
        parameters.put("reqYmd", wkodMaster.getReqYmd());
        parameters.put("wkodNum", wkodMaster.getWkodNum());
        parameters.put("wkodClsNm", wkodMaster.getWkodClsNm());
        parameters.put("WkodKindList", wkodKindList);
        // parameters.put("WkodSpeKindList", wkodSpeKindList);
        parameters.put("workYmd", wkodMaster.getWorkYmd());
        parameters.put("workTime", wkodMaster.getWorkYmdStartTime() + "~" + wkodMaster.getWorkYmdEndTime());
        parameters.put("processNm", wkodMaster.getProcessNm());
        parameters.put("workArea", wkodMaster.getWorkArea());
        parameters.put("workTitle", wkodMaster.getWorkTitle());
        parameters.put("workContent", wkodMaster.getWorkContent());
        parameters.put("pubDeptNm", wkodMaster.getPubDeptNm());
        parameters.put("pubMgrNm", wkodMaster.getPubMgrNm());
        parameters.put("pubMgrTel", wkodMaster.getPubMgrTel());
        parameters.put("subconnNm", wkodMaster.getSubconnNm());
        parameters.put("subconnUserNm", wkodMaster.getSubconnUserNm());
        parameters.put("subconnUserTel", wkodMaster.getSubconnUserTel());
        parameters.put("workerNumb", wkodMaster.getWorkerNumb());
        parameters.put("coopDeptNm", wkodMaster.getCoopDeptNm());
        parameters.put("chngTakePsn", wkodMaster.getChngTakePsn());
        parameters.put("chngAccPsn", wkodMaster.getChngAccPsn());
        parameters.put("WkodSpeCdList", wkodSpeCdList);
        parameters.put("SelectHandleChemContentList", selectHandleChemContentList);
        parameters.put("LOTOList", lotoList);
        parameters.put("ConfirmList", confirmList);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

        return new File(outputFileName);
    }

    public int updateWkodPrt(int wkodNo) throws Exception {
        return wkodMasterMapper.updateWkodPrt(wkodNo);
    }

    /**
     * 작업허가서 서명 확인 등록(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    public int createConfirmSign(Signature signature) throws Exception {
        return wkodMasterMapper.createConfirmSign(signature);
    }

    /**
     * 작업허가서 서명 확인 수정(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    public int updateConfirmSign(Signature signature) throws Exception {
        return wkodMasterMapper.updateConfirmSign(signature);
    }

    /**
     * 작업허가서 서명 확인 삭제(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    public int deleteConfirmSign(int signNo) throws Exception {
        return wkodMasterMapper.deleteConfirmSign(signNo);
    }

    /**
     * 작업허가서 서명 확인 조회 (모바일)
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 서명 확인 목록
     * @throws Exception
     */

    public List<Signature> getConfirmSigns(int wkodNo) throws Exception {
        return wkodMasterMapper.getConfirmSigns(wkodNo);
    }

    /**
     * 작업허가서 서명 확인 조회 (모바일)
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 서명 확인 목록
     * @throws Exception
     */

    /**
     * 작업허가서 안전조치항목 불러오기
     *
     * @param wkodMaster
     *            작업허가서
     * @return 변경행수
     * @throws Exception
     */

    public List<WkodChkResult> getSafWkodChkResultBase(int wkodNo, String plantCd, DefaultParam defaultParam) throws Exception {
        List<WkodChkResult> temp = wkodMasterMapper.getSafWkodChkResultBase(wkodNo, plantCd, defaultParam);
        return temp;
    }

    /**
     * 작업허가서 작업완료
     *
     * @param wkodMaster
     *            작업허가서
     * @return 변경행수
     * @throws Exception
     */

    public int constCompleteWkodMaster(WkodMaster wkodMaster) throws Exception {
        wkodMaster.setWkodStepCd(ConstVal.SAF_WKOD_STEP_COMPLETION);
        return wkodMasterMapper.constCompleteWkodMaster(wkodMaster);
    }

    /**
     * 작업허가서 작업연장
     *
     * @param wkodMaster
     *            작업허가서
     * @return 변경행수
     * @throws Exception
     */

    public int overWkodMaster(WkodMaster wkodMaster) throws Exception {
        wkodMaster.setWkodStepCd(ConstVal.SAF_WKOD_STEP_OVER);
        return wkodMasterMapper.overWkodMaster(wkodMaster);
    }

    /**
     * 작업허가서 작업취소
     *
     * @param wkodMaster
     *            작업허가서
     * @return 변경행수
     * @throws Exception
     */

    public int cancelWkodMaster(WkodMaster wkodMaster) throws Exception {
        wkodMaster.setWkodStepCd(ConstVal.SAF_WKOD_STEP_CANCEL);
        return wkodMasterMapper.cancelWkodMaster(wkodMaster);
    }

    /**
     * 작업허가서 작업위치 (1개)
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 서명 확인 목록
     * @throws Exception
     */

    public com.she.safety.model.Map getSafWkodMap(int mapNo, String plantCd, DefaultParam defaultParam) throws Exception {
        return wkodMasterMapper.getSafWkodMap(mapNo, plantCd, defaultParam);
    }
}
