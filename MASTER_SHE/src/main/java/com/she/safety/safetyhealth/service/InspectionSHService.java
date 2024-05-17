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
package com.she.safety.safetyhealth.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.rsa.model.ResultEstimator;
import com.she.safety.model.InspectionSH;
import com.she.safety.model.InspectionSHDept;
import com.she.safety.safetyhealth.mapper.InspectionSHMapper;
import com.she.utils.ConstVal;

/**
 * 점검계획 기능정의
 *
 */
@Service
public class InspectionSHService {
    private static Logger logger = LoggerFactory.getLogger(InspectionSHService.class);

    @Autowired
    private InspectionSHMapper inspectionSHMapper;
    @Autowired
    private AttachFileMapper attachFileMapper;
    @Autowired
    private ImprService imprService;

    /**
     * 점검계획 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 목록
     * @throws Exception
     */
    public List<InspectionSH> getPlanLists(String plantCd, String year, String regRegdemCd, String chkNm, String procStepCd, String stateCd, String mainDeptCd, String deptSubYn, String yearChk, DefaultParam defaultParam) throws Exception {
        return inspectionSHMapper.getPlanLists(plantCd, year, regRegdemCd, chkNm, procStepCd, stateCd, mainDeptCd, deptSubYn, yearChk, defaultParam);
    }

    /**
     * 점검계획 관리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 조회
     * @throws Exception
     */
    public InspectionSH getPlanInfo(int implChkPlanNo, DefaultParam defaultParam) throws Exception {
        InspectionSH inspectionSH = inspectionSHMapper.getPlanInfo(implChkPlanNo, defaultParam);
        return inspectionSH;
    }

    /**
     * 점검계획 관리 대상부서조회
     * 
     * @param parameter
     *            검색조건
     * @return 점검계획 관리 대상부서조회
     * @throws Exception
     */
    public List<InspectionSHDept> getPlanDeptList(int implChkPlanNo, String apprFlag) throws Exception {
        List<InspectionSHDept> planmgmtDeptList = inspectionSHMapper.getPlanDeptList(implChkPlanNo, apprFlag);
        return planmgmtDeptList;
    }

    /**
     * 점검계획 관리 등록
     * 
     * @param InspectionSH
     *            점검계획 관리 List
     * @return 점검계획 관리 Key값
     * @throws Exception
     */
    @Transactional
    public int createInspection(InspectionSH inspectionSH) throws Exception {
        inspectionSH.setStepCd("BAPSB"); // COM_BIZ_APPR_STEP
        inspectionSH.setStateCd("STATE2"); // COM_STATE
        inspectionSHMapper.createInspection(inspectionSH);
        // inspectionSHMapper.deletePlanmgmtDept(planmgmt.getAssessPlanNo());
        if (inspectionSH.getInspectionSHDetp() != null && inspectionSH.getInspectionSHDetp().size() > 0) {
            for (int i = 0; i < inspectionSH.getInspectionSHDetp().size(); i++) {
                inspectionSH.getInspectionSHDetp().get(i).setStateCd("STATE1");
                inspectionSH.getInspectionSHDetp().get(i).setImplChkPlanNo(inspectionSH.getImplChkPlanNo());
                inspectionSHMapper.createPlanDept(inspectionSH.getInspectionSHDetp().get(i));
            }
        }

        return inspectionSH.getImplChkPlanNo();
    }

    /**
     * 점검계획 관리 수정
     * 
     * @param InspectionSH
     *            점검계획 관리 List
     * @return 점검계획 관리 Key값
     * @throws Exception
     */
    @Transactional
    public int updateInspection(InspectionSH inspectionSH) throws Exception {
        inspectionSHMapper.updateInspection(inspectionSH);
        // inspectionSHMapper.deletePlanmgmtDept(planmgmt.getAssessPlanNo());
        if (inspectionSH.getInspectionSHDetp() != null && inspectionSH.getInspectionSHDetp().size() > 0) {
            for (int i = 0; i < inspectionSH.getInspectionSHDetp().size(); i++) {
                inspectionSH.getInspectionSHDetp().get(i).setImplChkPlanNo(inspectionSH.getImplChkPlanNo());
                if (inspectionSH.getInspectionSHDetp().get(i).getImplChkDeptNo() != 0) {
                    inspectionSH.getInspectionSHDetp().get(i).setStateCd("STATE1");
                    inspectionSHMapper.updatePlanDept(inspectionSH.getInspectionSHDetp().get(i));
                } else {
                    inspectionSH.getInspectionSHDetp().get(i).setImplChkPlanNo(inspectionSH.getImplChkPlanNo());
                    inspectionSHMapper.createPlanDept(inspectionSH.getInspectionSHDetp().get(i));
                }

            }
        }
        return inspectionSH.getImplChkPlanNo();
    }

    /**
     * 점검계획/결과 관리 삭제
     * 
     * @param InspectionSH
     * 
     * @return 점검계획/결과 관리 삭제
     * @throws Exception
     */
    public int deleteInspection(int implChkPlanNo) throws Exception {
        return inspectionSHMapper.deleteInspection(implChkPlanNo);
    }

    /**
     * 점검계획 관리 대상부서삭제
     * 
     * @param InspectionSH
     *            점검계획 관리 List
     * @return 점검계획 관리 Key값
     * @throws Exception
     */
    @Transactional
    public int deleteInspectionDept(List<InspectionSHDept> inspectionSHDept) throws Exception {
        int cnt = 0;
        if (inspectionSHDept != null && inspectionSHDept.size() > 0) {
            for (int i = 0; i < inspectionSHDept.size(); i++) {
                cnt += inspectionSHMapper.deleteInspectionDept(inspectionSHDept.get(i).getImplChkPlanNo(), inspectionSHDept.get(i).getImplChkDeptNo());
            }
        }
        return cnt;
    }

    @Transactional
    public int updateAppr(int implChkPlanNo, String apprStepCd, int apprRqstNo) {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += inspectionSHMapper.updateAppr(implChkPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += inspectionSHMapper.updateAppr(implChkPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료 - 결재 요청 PSEND(완료) 으로 세팅
            resultNo += inspectionSHMapper.updateAppr(implChkPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
        }

        return resultNo;
    }

    /**
     * 점검결과 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 관리 목록
     * @throws Exception
     */
    public List<InspectionSHDept> getResultLists(String plantCd, String year, String chkNm, String regRegdemCd, String yearChk, String imprChk, String deptSubYn, String deptCd, int monFlag, String gubun, String mainDeptCd, String mainDeptSubYn, String stateCd, DefaultParam defaultParam) throws Exception {
        return inspectionSHMapper.getResultLists(plantCd, year, chkNm, regRegdemCd, yearChk, imprChk, deptSubYn, deptCd, monFlag, gubun, mainDeptCd, mainDeptSubYn, stateCd, defaultParam);
    }

    /**
     * 점검결과 관리 조회
     * 
     * @param
     * @return 점검결과 관리 조회
     * @throws Exception
     */
    public InspectionSHDept getResultInfo(int implChkDeptNo, DefaultParam defaultParam) throws Exception {
        InspectionSHDept inspectionSHDept = inspectionSHMapper.getResultInfo(implChkDeptNo, defaultParam);
        List<ResultEstimator> estimatorInList = inspectionSHMapper.getEstimatorIn(implChkDeptNo, defaultParam);
        List<ResultEstimator> estimatorOutList = inspectionSHMapper.getEstimatorOut(implChkDeptNo, defaultParam);
        inspectionSHDept.setEstimatorInList(estimatorInList);
        inspectionSHDept.setEstimatorOutList(estimatorOutList);
        return inspectionSHDept;
    }

    /**
     * 점검결과 관리 평가자(내부, 외부)등록 / 무계획 등록 포함
     * 
     * @param ResultEstimator
     * 
     * @return 점검결과 관리 평가자(내부, 외부) / 무계획 등록 포함
     * @throws Exception
     */
    @Transactional
    public int createResult(InspectionSHDept inspectionSHDept) throws Exception {
        InspectionSH inspectionSH = new InspectionSH();
        if (inspectionSHDept.getImplChkPlanNo() == 0) {

            inspectionSH.setPlantCd(inspectionSHDept.getPlantCd());
            inspectionSH.setYear(inspectionSHDept.getYear());
            inspectionSH.setRegRegdemCd(inspectionSHDept.getRegRegdemCd());
            inspectionSH.setRegRegdemCd(inspectionSHDept.getRegRegdemCd());
            inspectionSH.setChkStartDt(inspectionSHDept.getChkStartDt());
            inspectionSH.setChkEndDt(inspectionSHDept.getChkEndDt());
            inspectionSH.setStepCd("BAPSG");
            inspectionSH.setChkNm(inspectionSHDept.getChkNm());
            inspectionSH.setChkDesc(inspectionSHDept.getChkDesc());
            inspectionSH.setMainDeptCd(inspectionSHDept.getMainDeptCd());
            inspectionSH.setCreateUserId(inspectionSHDept.getCreateUserId());
            inspectionSH.setCreateDeptCd(inspectionSHDept.getCreateDeptCd());
            inspectionSH.setCreateDeptNm(inspectionSHDept.getCreateDeptNm());
            inspectionSH.setCreatePositionCd(inspectionSHDept.getCreatePositionCd());
            inspectionSH.setCreatePositionNm(inspectionSHDept.getCreatePositionNm());
            inspectionSH.setStateCd("STATE4");
            inspectionSH.setStepCd("BAPSG");
            inspectionSHMapper.createInspection(inspectionSH);
            inspectionSHDept.setStateCd("STATE1");
            inspectionSHDept.setImplChkPlanNo(inspectionSH.getImplChkPlanNo());
            inspectionSHMapper.createPlanDept(inspectionSHDept);
        } else {
            inspectionSHDept.setStateCd("STATE2");
            inspectionSHMapper.updatePlanDept(inspectionSHDept);
        }

        // 평가자 내부/외부
        inspectionSHMapper.deleteEstimator(inspectionSHDept.getImplChkDeptNo());
        if (inspectionSHDept.getEstimatorInList() != null && inspectionSHDept.getEstimatorInList().size() > 0) {
            for (int i = 0; i < inspectionSHDept.getEstimatorInList().size(); i++) {
                inspectionSHDept.getEstimatorInList().get(i).setImplChkDeptNo(inspectionSHDept.getImplChkDeptNo());
                inspectionSHMapper.createInEstimator(inspectionSHDept.getEstimatorInList().get(i));
            }
        }
        if (inspectionSHDept.getEstimatorOutList() != null && inspectionSHDept.getEstimatorOutList().size() > 0) {
            for (int i = 0; i < inspectionSHDept.getEstimatorOutList().size(); i++) {
                inspectionSHDept.getEstimatorOutList().get(i).setImplChkDeptNo(inspectionSHDept.getImplChkDeptNo());
                inspectionSHMapper.createOutEstimator(inspectionSHDept.getEstimatorOutList().get(i));
            }
        }

        // 점검 결과 테이블
        if (inspectionSHDept.getImplChkRsltNo() != 0) {
            inspectionSHMapper.updateResultRslt(inspectionSHDept);
        } else {
            inspectionSHMapper.createResultRslt(inspectionSHDept);
        }
        return inspectionSHDept.getImplChkDeptNo();
    }

    @Transactional
    public int resultUpdateAppr(int implChkDeptNo, String apprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += inspectionSHMapper.resultUpdateAppr(implChkDeptNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += inspectionSHMapper.resultUpdateAppr(implChkDeptNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료 - 결재 요청 PSEND(완료) 으로 세팅
            resultNo += inspectionSHMapper.resultUpdateAppr(implChkDeptNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
            imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_SAFETYHEALTH, implChkDeptNo, "");
        }

        return resultNo;
    }

    /**
     * 점검결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getResultstatusList(String plantCd, String year, String regRegdemCd, String totalFlag, DefaultParam defaultParam) throws Exception {
        List<HashMap<String, Object>> resultStatusLists = inspectionSHMapper.getResultstatusList(plantCd, year, regRegdemCd, totalFlag, defaultParam);
        return resultStatusLists;
    }

    /**
     * 점검결과 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 점검결과 삭제 가능 확인
     * @throws Exception
     */
    public int getResultImprStatus(int implChkDeptNo) throws Exception {
        int imprStatus = inspectionSHMapper.getResultImprStatus(implChkDeptNo);
        return imprStatus;
    }

    /**
     * 점검결과 삭제
     * 
     * @param InspectionSH
     * 
     * @return 점검결과 관리 삭제
     * @throws Exception
     */
    public int deleteResultDept(int implChkDeptNo) throws Exception {
        // return inspectionSHMapper.deletePlanmgmtDept(assessDeptNo);
        return inspectionSHMapper.deleteUpdateResultDept(implChkDeptNo);
    }
}
