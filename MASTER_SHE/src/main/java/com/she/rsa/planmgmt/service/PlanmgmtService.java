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
package com.she.rsa.planmgmt.service;

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
import com.she.rsa.model.Planmgmt;
import com.she.rsa.model.PlanmgmtDeptList;
import com.she.rsa.model.ResultEstimator;
import com.she.rsa.planmgmt.mapper.PlanmgmtMapper;
import com.she.utils.ConstVal;

/**
 * 평가계획 기능정의
 *
 */
@Service
public class PlanmgmtService {
    private static Logger logger = LoggerFactory.getLogger(PlanmgmtService.class);

    @Autowired
    private PlanmgmtMapper planmgmtMapper;
    @Autowired
    private AttachFileMapper attachFileMapper;
    @Autowired
    private ImprService imprService;

    /**
     * 평가계획 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 관리 목록
     * @throws Exception
     */
    public List<Planmgmt> getPlanmgmtLists(String plantCd, String assessYear, String assessTypeCd, String regRegdemCd, String yearChk, String assessNm, String riskType, String stateCd, String mainDeptCd, String deptSubYn, DefaultParam defaultParam) throws Exception {
        return planmgmtMapper.getPlanmgmtLists(plantCd, assessYear, assessTypeCd, regRegdemCd, yearChk, assessNm, riskType, stateCd, mainDeptCd, deptSubYn, defaultParam);
    }

    /**
     * 평가계획 관리 조회
     * 
     * @param assessPlanNo
     *            평가계획번호
     * @return 평가계획 관리 조회
     * @throws Exception
     */
    public Planmgmt getPlanmgmtInfo(int assessPlanNo, DefaultParam defaultParam) throws Exception {
        Planmgmt planmgmt = planmgmtMapper.getPlanmgmtInfo(assessPlanNo, defaultParam);

        return planmgmt;
    }

    /**
     * 평가계획 관리 조회
     * 
     * @param assessPlanNo
     *            평가계획번호
     * @return 평가계획 관리 조회
     * @throws Exception
     */
    public List<PlanmgmtDeptList> getPlanmgmtDeptList(int assessPlanNo, String apprFlag) throws Exception {
        List<PlanmgmtDeptList> planmgmtDeptList = planmgmtMapper.getPlanmgmtDeptList(assessPlanNo, apprFlag);
        return planmgmtDeptList;
    }

    /**
     * 평가계획 관리 등록
     * 
     * @param Planmgmt
     *            평가계획 관리 List
     * @return 평가계획 관리 Key값
     * @throws Exception
     */
    @Transactional
    public int createPlanmgmt(Planmgmt planmgmt) throws Exception {
        planmgmt.setStepCd("BAPSB");
        planmgmtMapper.createPlanmgmt(planmgmt);
        // planmgmtMapper.deletePlanmgmtDept(planmgmt.getAssessPlanNo());
        if (planmgmt.getPlanmgmtDeptList() != null && planmgmt.getPlanmgmtDeptList().size() > 0) {
            for (int i = 0; i < planmgmt.getPlanmgmtDeptList().size(); i++) {
                planmgmt.getPlanmgmtDeptList().get(i).setAssessPlanNo(planmgmt.getAssessPlanNo());
                planmgmtMapper.createPlanmgmtDept(planmgmt.getPlanmgmtDeptList().get(i));
            }
        }

        return planmgmt.getAssessPlanNo();
    }

    /**
     * 평가계획 관리 수정
     * 
     * @param Planmgmt
     *            평가계획 관리 List
     * @return 평가계획 관리 Key값
     * @throws Exception
     */
    @Transactional
    public int updatePlanmgmt(Planmgmt planmgmt) throws Exception {
        planmgmtMapper.updatePlanmgmt(planmgmt);
        // planmgmtMapper.deletePlanmgmtDept(planmgmt.getAssessPlanNo());
        if (planmgmt.getPlanmgmtDeptList() != null && planmgmt.getPlanmgmtDeptList().size() > 0) {
            for (int i = 0; i < planmgmt.getPlanmgmtDeptList().size(); i++) {
                planmgmt.getPlanmgmtDeptList().get(i).setAssessPlanNo(planmgmt.getAssessPlanNo());
                if (planmgmt.getPlanmgmtDeptList().get(i).getAssessDeptNo() != 0) {
                    planmgmt.getPlanmgmtDeptList().get(i).setStateCd("STATE1");
                    planmgmtMapper.updatePlanmgmtDept(planmgmt.getPlanmgmtDeptList().get(i));
                } else {
                    planmgmt.getPlanmgmtDeptList().get(i).setAssessPlanNo(planmgmt.getAssessPlanNo());
                    planmgmtMapper.createPlanmgmtDept(planmgmt.getPlanmgmtDeptList().get(i));
                }

            }
        }
        return planmgmt.getAssessPlanNo();
    }

    /**
     * 평가계획/결과 관리 삭제
     * 
     * @param Planmgmt
     * 
     * @return 평가계획/결과 관리 삭제
     * @throws Exception
     */
    public int deletePlanmgmt(int assessPlanNo) throws Exception {
        return planmgmtMapper.deletePlanmgmt(assessPlanNo);
    }

    /**
     * 평가계획 관리 대상부서삭제
     * 
     * @param Planmgmt
     *            평가계획 관리 List
     * @return 평가계획 관리 Key값
     * @throws Exception
     */
    @Transactional
    public int deletePlanmgmtDeptList(List<PlanmgmtDeptList> planmgmtDeptList) throws Exception {
        int cnt = 0;
        if (planmgmtDeptList != null && planmgmtDeptList.size() > 0) {
            for (int i = 0; i < planmgmtDeptList.size(); i++) {
                cnt += planmgmtMapper.deletePlanmgmtDept(planmgmtDeptList.get(i).getAssessPlanNo(), planmgmtDeptList.get(i).getAssessDeptNo());
            }
        }
        return cnt;
    }

    @Transactional
    public int updateAppr(int assessPlanNo, String apprStepCd, int apprRqstNo) {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += planmgmtMapper.updateAppr(assessPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += planmgmtMapper.updateAppr(assessPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료 - 결재 요청 PSEND(완료) 으로 세팅
            resultNo += planmgmtMapper.updateAppr(assessPlanNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
        }

        return resultNo;
    }

    /**
     * 평가결과 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 관리 목록
     * @throws Exception
     */
    public List<PlanmgmtDeptList> getResultmgmtLists(String plantCd, String assessYear, String assessTypeCd, String regRegdemCd, String yearChk, String assessNm, String imprChk, String deptSubYn, String deptCd, String riskType, int monFlag, String gubun, String mainDeptCd, String mainDeptSubYn, String stateCd, DefaultParam defaultParam)
            throws Exception {
        return planmgmtMapper.getResultmgmtLists(plantCd, assessYear, assessTypeCd, regRegdemCd, yearChk, assessNm, imprChk, deptSubYn, deptCd, riskType, monFlag, gubun, mainDeptCd, mainDeptSubYn, stateCd, defaultParam);
    }

    /**
     * 평가결과 관리 조회
     * 
     * @param assessPlanNo,
     *            assessDeptNo 평가계획번호, 부서
     * @return 평가결과 관리 조회
     * @throws Exception
     */
    public PlanmgmtDeptList getResultmgmtInfo(int assessDeptNo, DefaultParam defaultParam) throws Exception {
        PlanmgmtDeptList resultDetpList = planmgmtMapper.getResultmgmtInfo(assessDeptNo, defaultParam);
        List<ResultEstimator> estimatorInList = planmgmtMapper.getEstimatorIn(assessDeptNo, defaultParam);
        List<ResultEstimator> estimatorOutList = planmgmtMapper.getEstimatorOut(assessDeptNo, defaultParam);
        resultDetpList.setEstimatorInList(estimatorInList);
        resultDetpList.setEstimatorOutList(estimatorOutList);
        return resultDetpList;
    }

    /**
     * 평가결과 관리 평가자(내부, 외부)등록 / 무계획 등록 포함
     * 
     * @param ResultEstimator
     * 
     * @return 평가결과 관리 평가자(내부, 외부) / 무계획 등록 포함
     * @throws Exception
     */
    @Transactional
    public int createResultmgmt(PlanmgmtDeptList planmgmtDeptInfo) throws Exception {
        Planmgmt planmgmt = new Planmgmt();
        planmgmt.setDeptCd(planmgmtDeptInfo.getDeptCd());
        if (planmgmtDeptInfo.getAssessPlanNo() == 0) {

            planmgmt.setDeptCd(planmgmtDeptInfo.getDeptCd());
            planmgmt.setPlantCd(planmgmtDeptInfo.getPlantCd());
            planmgmt.setRiskType(planmgmtDeptInfo.getRiskType());
            planmgmt.setAssessYear(planmgmtDeptInfo.getAssessYear());
            planmgmt.setAssessTypeCd(planmgmtDeptInfo.getAssessTypeCd());
            planmgmt.setRegRegdemCd(planmgmtDeptInfo.getRegRegdemCd());
            planmgmt.setRegRegdemCd(planmgmtDeptInfo.getRegRegdemCd());
            planmgmt.setAssessStartDt(planmgmtDeptInfo.getAssessStartDt());
            planmgmt.setAssessEndDt(planmgmtDeptInfo.getAssessEndDt());
            planmgmt.setStepCd("BAPSG");
            planmgmt.setAssessNm(planmgmtDeptInfo.getAssessNm());
            planmgmt.setAssessDesc(planmgmtDeptInfo.getAssessDesc());
            planmgmt.setMainDeptCd(planmgmtDeptInfo.getMainDeptCd());
            planmgmt.setCreateUserId(planmgmtDeptInfo.getCreateUserId());
            planmgmt.setCreateDeptCd(planmgmtDeptInfo.getCreateDeptCd());
            planmgmt.setCreateDeptNm(planmgmtDeptInfo.getCreateDeptNm());
            planmgmt.setCreatePositionCd(planmgmtDeptInfo.getCreatePositionCd());
            planmgmt.setCreatePositionNm(planmgmtDeptInfo.getCreatePositionNm());
            planmgmt.setStateNm("STATE4");
            planmgmt.setStepCd("BAPSG");
            planmgmtMapper.createPlanmgmt(planmgmt);
            planmgmtDeptInfo.setAssessPlanNo(planmgmt.getAssessPlanNo());
            planmgmtMapper.createPlanmgmtDept(planmgmtDeptInfo);
        } else {
            planmgmtDeptInfo.setStateCd("STATE2");
            planmgmtMapper.updatePlanmgmtDept(planmgmtDeptInfo);
        }

        // 평가자 내부/외부
        planmgmtMapper.deleteEstimator(planmgmtDeptInfo.getAssessDeptNo());
        if (planmgmtDeptInfo.getEstimatorInList() != null && planmgmtDeptInfo.getEstimatorInList().size() > 0) {
            for (int i = 0; i < planmgmtDeptInfo.getEstimatorInList().size(); i++) {
                planmgmtDeptInfo.getEstimatorInList().get(i).setAssessDeptNo(planmgmtDeptInfo.getAssessDeptNo());
                planmgmtMapper.createInEstimator(planmgmtDeptInfo.getEstimatorInList().get(i));
            }
        }
        if (planmgmtDeptInfo.getEstimatorOutList() != null && planmgmtDeptInfo.getEstimatorOutList().size() > 0) {
            for (int i = 0; i < planmgmtDeptInfo.getEstimatorOutList().size(); i++) {
                planmgmtDeptInfo.getEstimatorOutList().get(i).setAssessDeptNo(planmgmtDeptInfo.getAssessDeptNo());
                planmgmtMapper.createOutEstimator(planmgmtDeptInfo.getEstimatorOutList().get(i));
            }
        }

        // 위험성평가 결과 테이블
        if (planmgmtDeptInfo.getAssessRsltNo() != 0) {
            planmgmtMapper.updateAssessRslt(planmgmtDeptInfo);
        } else {
            planmgmtMapper.createAssessRslt(planmgmtDeptInfo);
        }
        return planmgmtDeptInfo.getAssessDeptNo();
    }

    /**
     * 평가결과 관리 부서삭제
     * 
     * @param PlanmgmtDeptList
     *            평가결과 관리 부서삭제
     * @return int
     * @throws Exception
     */
    @Transactional
    public int deleteResultDept(int assessDeptNo) throws Exception {
        int cnt = 0;
        cnt = planmgmtMapper.deleteResultDept(assessDeptNo);
        return cnt;
    }

    @Transactional
    public int resultUpdateAppr(int assessDeptNo, String imprClassCd, String apprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += planmgmtMapper.resultUpdateAppr(assessDeptNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_REJECT);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(apprStepCd)) {
            // 결재 중 - 결재 요청 PSREG(작성중) 으로 세팅
            resultNo += planmgmtMapper.resultUpdateAppr(assessDeptNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_ING);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재 완료 - 결재 요청 PSEND(완료) 으로 세팅
            resultNo += planmgmtMapper.resultUpdateAppr(assessDeptNo, apprRqstNo, ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
            imprService.updateImprStepCd(imprClassCd, assessDeptNo, "");
        }

        return resultNo;
    }

    /**
     * 평가결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getResultstatusList(String plantCd, String assessYear, String regRegdemCd, String riskType, String totalFlag, DefaultParam defaultParam) throws Exception {
        List<HashMap<String, Object>> resultStatusLists = planmgmtMapper.getResultstatusList(plantCd, assessYear, regRegdemCd, riskType, totalFlag, defaultParam);
        return resultStatusLists;
    }

    /**
     * 평가계획 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가계획 삭제 가능 확인
     * @throws Exception
     */
    public int getPlanmgmtStatus(int assessPlanNo) throws Exception {
        int planmgmtStatus = planmgmtMapper.getPlanmgmtStatus(assessPlanNo);
        return planmgmtStatus;
    }

    /**
     * 평가결과 삭제 가능 확인
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 삭제 가능 확인
     * @throws Exception
     */
    public int getPlanmgmtImprStatus(int assessDeptNo) throws Exception {
        int planmgmtImprStatus = planmgmtMapper.getPlanmgmtImprStatus(assessDeptNo);
        return planmgmtImprStatus;
    }

    /**
     * 평가결과 삭제
     * 
     * @param Planmgmt
     * 
     * @return 평가결과 관리 삭제
     * @throws Exception
     */
    public int deletePlanmgmtDept(int assessDeptNo) throws Exception {
        // return planmgmtMapper.deletePlanmgmtDept(assessDeptNo);
        return planmgmtMapper.deleteUpdatePlanmgmtDept(assessDeptNo);
    }
}
