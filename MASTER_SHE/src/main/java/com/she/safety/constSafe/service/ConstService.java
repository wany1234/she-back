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
package com.she.safety.constSafe.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.safety.constSafe.mapper.ConstMapper;
import com.she.safety.model.Const;
import com.she.safety.model.ConstKind;
import com.she.safety.model.ConstKindSubconn;
import com.she.safety.model.ConstKindSubconnWorker;
import com.she.safety.model.LOTO;
import com.she.safety.wkod.service.WkodMasterService;
import com.she.utils.ConstVal;

@Service
public class ConstService {

    @Autowired
    private ConstMapper constMapper;
    @Autowired
    private WkodMasterService wkodMasterService;

    /**
     * 공사현황 조회
     * 
     * @param plantCd
     *            사업장
     * @param constStepCd
     *            공사진행단계
     * @param bizApprStepCd
     *            결재진행단계
     * @param constStartYmd
     *            작업시작일
     * @param constEndYmd
     *            작업종료일
     * @param constTitle
     *            공사명
     * @return 공사현황 목록
     * @throws Exception
     */
    public List<Const> getConsts(String plantCd, String constStepCd, String bizApprStepCd, String constStartYmd, String constEndYmd, String constTitle, String vendorCd, String showVendorYn, DefaultParam defaultParam) throws Exception {
        return constMapper.getConsts(plantCd, constStepCd, bizApprStepCd, constStartYmd, constEndYmd, constTitle, vendorCd, showVendorYn, defaultParam);
    }

    /**
     * 공사현황 상세 조회
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황
     * @throws Exception
     */
    public Const getConst(String constNo, DefaultParam defaultParam) throws Exception {
        // 공사현황 상세 정보
        Const constInfo = constMapper.getConst(constNo, defaultParam);
        // 공사현황 작업구분
        constInfo.setWkodKindCds(constMapper.getConstKinds(constNo, defaultParam));
        // 공사현황에 따른 작업구분별 업체정보
        List<ConstKindSubconn> constKindSubconns = constMapper.getConstKindSubconns(constNo, "", "", "", null, "", defaultParam);

        if (constKindSubconns != null && constKindSubconns.size() > 0) {
            // 공사현황에 따른 작업구분별 업체 작업자 정보
            for (ConstKindSubconn constKindSubconn : constKindSubconns) {
                constKindSubconn.setConstKindSubconnWorkers(constMapper.getConstKindSubconnWorkers(constKindSubconn.getConstKindSubconnNo()));
            }
        }
        constInfo.setConstKindSubconns(constKindSubconns);

        // LOTO 정보
        constInfo.setConstLotos(constMapper.getLotos(constNo));

        return constInfo;
    }

    /**
     * 공사현황 상세 조회
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황
     * @throws Exception
     */
    public Const getConstVendor(String constNo, String vendorCd, DefaultParam defaultParam) throws Exception {
        // 공사현황 상세 정보
        Const constInfo = constMapper.getConst(constNo, defaultParam);
        // 공사현황 작업구분
        constInfo.setWkodKindCds(constMapper.getConstKinds(constNo, defaultParam));
        // 공사현황에 따른 작업구분별 업체정보
        List<ConstKindSubconn> constKindSubconns = constMapper.getConstKindSubconns(constNo, "", vendorCd, "", null, "", defaultParam);

        if (constKindSubconns != null && constKindSubconns.size() > 0) {
            // 공사현황에 따른 작업구분별 업체 작업자 정보
            for (ConstKindSubconn constKindSubconn : constKindSubconns) {
                constKindSubconn.setConstKindSubconnWorkers(constMapper.getConstKindSubconnWorkers(constKindSubconn.getConstKindSubconnNo()));
            }
        }
        constInfo.setConstKindSubconns(constKindSubconns);

        // LOTO 정보
        constInfo.setConstLotos(constMapper.getLotos(constNo));

        return constInfo;
    }

    public HashMap<String, Object> getConstCheck(String constNo, String constNum) throws Exception {
        return constMapper.getConstCheck(constNo, constNum);
    }

    /**
     * 공사현황 신규등록
     * 
     * @param constData
     *            공사현황
     * @return 공사현황번호
     * @throws Exception
     */
    @Transactional
    public String createConst(Const constData) throws Exception {

        int resultNo = 0;
        // 신규등록 전 seq값 가져와서 셋팅
        constData.setSeqValue(this.constMapper.getConstSeq());
        // 공사현황 신규등록
        resultNo += this.constMapper.createConst(constData);
        // 공사현황 작업구분 리스트 신규등록
        if (constData.getWkodKindCds() != null) {
            for (ConstKind constKind : constData.getWkodKindCds()) {
                constKind.setConstNo(constData.getConstNo());
                resultNo += this.constMapper.createConstKind(constKind);
            }
        }
        // 공사현황 작업구분별 업체 리스트 신규등록
        if (constData.getConstKindSubconns() != null) {
            for (ConstKindSubconn constKindSubconn : constData.getConstKindSubconns()) {
                constKindSubconn.setConstNo(constData.getConstNo());
                constKindSubconn.setWorkPasserYn(constKindSubconn.getConstKindSubconnWorkers() != null && constKindSubconn.getConstKindSubconnWorkers().size() > 0 ? "Y" : "N");
                resultNo += this.constMapper.createConstKindSubconn(constKindSubconn);

                // 공사현황 작업구분별 업체 작업자 리스트 신규등록
                if (constKindSubconn.getConstKindSubconnWorkers() != null) {
                    for (ConstKindSubconnWorker constKindSubconnWorker : constKindSubconn.getConstKindSubconnWorkers()) {
                        constKindSubconnWorker.setConstKindSubconnNo(constKindSubconn.getConstKindSubconnNo());
                        resultNo += this.constMapper.createConstKindSubconnWorker(constKindSubconnWorker);
                    }
                }
            }
        }
        // LOTO 신규등록
        if (constData.getConstLotos() != null) {
            for (LOTO loto : constData.getConstLotos()) {
                loto.setConstNo(constData.getConstNo());
                resultNo += this.constMapper.createLoto(loto);
            }
        }
        // 법적인허가 대상 항목 저장
        wkodMasterService.createWkodLegalLcn(constData);

        return resultNo > 0 ? constData.getConstNo() : "";
    }

    /**
     * 공사현황 수정
     * 
     * @param constData
     *            공사현황
     * @return 공사현황번호
     * @throws Exception
     */
    @Transactional
    public int updateConst(Const constData) throws Exception {
        int resultNo = 0;
        // 공사현황 수정
        resultNo += this.constMapper.updateConst(constData);

        // 공사현황별 작업구분, 작업구분에 따른 업체, 작업자들, LOTO 일괄 삭제
        this.deleteConstKinds(constData.getConstNo());

        // 공사현황 작업구분 리스트 신규등록
        if (constData.getWkodKindCds() != null) {
            for (ConstKind constKind : constData.getWkodKindCds()) {
                constKind.setConstNo(constData.getConstNo());
                resultNo += this.constMapper.createConstKind(constKind);
            }
        }
        // 공사현황 작업구분별 업체 리스트 신규등록
        if (constData.getConstKindSubconns() != null) {
            for (ConstKindSubconn constKindSubconn : constData.getConstKindSubconns()) {
                constKindSubconn.setConstNo(constData.getConstNo());
                constKindSubconn.setWorkPasserYn(constKindSubconn.getConstKindSubconnWorkers() != null && constKindSubconn.getConstKindSubconnWorkers().size() > 0 ? "Y" : "N");
                resultNo += this.constMapper.createConstKindSubconn(constKindSubconn);

                // 공사현황 작업구분별 업체 작업자 리스트 신규등록
                if (constKindSubconn.getConstKindSubconnWorkers() != null) {
                    for (ConstKindSubconnWorker constKindSubconnWorker : constKindSubconn.getConstKindSubconnWorkers()) {
                        constKindSubconnWorker.setConstKindSubconnNo(constKindSubconn.getConstKindSubconnNo());
                        resultNo += this.constMapper.createConstKindSubconnWorker(constKindSubconnWorker);
                    }
                }
            }
        }
        // LOTO 신규등록
        if (constData.getConstLotos() != null) {
            for (LOTO loto : constData.getConstLotos()) {
                loto.setConstNo(constData.getConstNo());
                resultNo += this.constMapper.createLoto(loto);
            }
        }
        // 법적인허가 대상 항목 저장
        wkodMasterService.createWkodLegalLcn(constData);

        return resultNo;
    }

    /**
     * 공사현황 삭제
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황번호
     * @throws Exception
     */
    @Transactional
    public int deleteConst(String constNo) throws Exception {
        // delete_yn 처리 하는 것임으로 실제 삭제 X
        return this.constMapper.deleteConst(constNo);
    }

    /**
     * 공사현황별 작업구분, 작업구분에 따른 업체, 작업자들, LOTO 일괄 삭제
     * 
     * @param constData
     *            공사현황
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteConstKinds(String constNo) throws Exception {
        int resultNo = 0;
        // 공사현황에 따른 작업구분별 업체 작업자들 삭제
        resultNo += this.constMapper.deleteConstKindSubconnWorker(constNo);
        // 공사현황에 따른 작업구분별 업체 삭제
        resultNo += this.constMapper.deleteConstKindSubconn(constNo);
        // 공사현황에 따른 작업구분 삭제
        resultNo += this.constMapper.deleteConstKind(constNo);
        // LOTO 삭제
        resultNo += this.constMapper.deleteLoto(constNo);
        return resultNo;
    }

    /**
     * LOTO 조회
     * 
     * @param constNo
     *            공사번호
     * @return 공사현황 목록
     * @throws Exception
     */
    public List<LOTO> getLotos(String constNo) throws Exception {
        return constMapper.getLotos(constNo);
    }

    /**
     * 공사현황에 따른 작업구분별 업체정보 조회
     * 
     * @param constNo
     *            공사번호
     * @param isDateCondition
     *            공사기간조건사용
     * @param vendorCd
     *            업체코드
     * @return 공사현황에 따른 작업구분별 업체정보
     * @throws Exception
     */
    public List<ConstKindSubconn> getConstKindSubconns(String constNo, String isDateCondition, String vendorCd, String plantCd, String[] wkodKindCds, String constTitle, DefaultParam defaultParam) throws Exception {
        List<ConstKindSubconn> constKindSubconns = constMapper.getConstKindSubconns(constNo, isDateCondition, vendorCd, plantCd, wkodKindCds, constTitle, defaultParam);

        if (constKindSubconns != null && constKindSubconns.size() > 0) {
            // 공사현황에 따른 작업구분별 업체 작업자 정보
            for (ConstKindSubconn constKindSubconn : constKindSubconns) {
                constKindSubconn.setConstKindSubconnWorkers(constMapper.getConstKindSubconnWorkers(constKindSubconn.getConstKindSubconnNo()));
            }
        }
        return constKindSubconns;
    }

    /**
     * 공사현황 작업구분별 업체번호 별 작업자들 조회
     * 
     * @param constKindSubconnNos
     *            공사작업구분별_번호 리스트
     * @return 공사현황 작업구분별 업체번호 별 작업자들
     * @throws Exception
     */
    public List<ConstKindSubconnWorker> getConstKindSubconnsWorkers(int[] constKindSubconnNos) throws Exception {
        return constMapper.getConstKindSubconnsWorkers(constKindSubconnNos);
    }

    /**
     * 공사현황 작업구분별 업체번호 별 작업자들 조회
     * 
     * @param constKindSubconnNos
     *            공사작업구분별_번호 리스트
     * @return 공사현황 작업구분별 업체번호 별 작업자들
     * @throws Exception
     */
    public int updateConstSubconnStep(ConstKindSubconn constKindSubconn) throws Exception {
        int resultNo = 0;
        // 작업기간 & 산재번호 & 완료 처리 저장
        resultNo += constMapper.updateConstSubconnStep(constKindSubconn);
        if (constKindSubconn.getConstKindSubconnWorkers() != null) {
            // 공사현황에 따른 작업구분별 업체 작업자들 삭제
            resultNo += this.constMapper.deleteConstKindSubconnWorkerKey(constKindSubconn.getConstKindSubconnNo());
            if (constKindSubconn.getConstKindSubconnWorkers().size() > 0) {
                for (ConstKindSubconnWorker constKindSubconnWorker : constKindSubconn.getConstKindSubconnWorkers()) {
                    constKindSubconnWorker.setConstKindSubconnNo(constKindSubconn.getConstKindSubconnNo());
                    resultNo += this.constMapper.createConstKindSubconnWorker(constKindSubconnWorker);
                }

            }
        }
        return resultNo;
    }

    /**
     * 공사 진행단계 변경
     *
     * @param constNo
     *            공사번호
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeConstSafe(String constNo, int apprRqstNo) throws Exception {
        return constMapper.completeConstSafe(constNo, apprRqstNo, "");
    }

    /**
     * 공사 진행단계 완료
     *
     * @param constNo
     *            공사번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeConstSafeStep(String constNo, String constStepCd) throws Exception {
        return constMapper.completeConstSafeStep(constNo, constStepCd);
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
    public int apprProcessConst(String constNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String constStepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            Const constInfo = constMapper.getConst(constNo, new DefaultParam("kr"));
            // 작성중의 경우 공사진행중으로 변경
            if (constInfo.getConstStepCd().equals(ConstVal.SAF_CONST_STEP_WRITING)) {
                constStepCd = ConstVal.SAF_CONST_STEP_WORKING;
            }
        }
        // 작업허가서 결재 진행단계 수정
        resultNo += constMapper.completeConstSafe(constNo, apprRqstNo, constStepCd);
        return resultNo;
    }

}
