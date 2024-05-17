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
package com.she.rsa.assess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.assess.mapper.AssessPlanMapper;
import com.she.rsa.model.AssessPlan;
import com.she.utils.ConstVal;

/**
 * 평가계획 기능정의
 */
@Service
public class AssessPlanService {
    @Autowired
    private AssessPlanMapper assessPlanMapper;

    /**
     * 평가계획 조회
     *
     * @param chkKRASYn
     *            KRAS yn
     * @param chkJSAYn
     *            JSA yn
     * @param chkCHARMYn
     *            CHARM yn
     * @param assessNm
     *            평가명
     * @param regRegdem
     *            정시/수기
     * @param assessDesc
     *            상세내용
     * @return 평가계획 목록
     * @throws Exception
     */
    public List<AssessPlan> getAssessPlans(String plantCd, String deptCd, String deptSubYn, String userId, String assessStatus, String startDate, String endDate, String assessGroupCd, String assessTypeCd, String userNm, String assessNm, String regRegdem, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return assessPlanMapper.getAssessPlans(plantCd, deptCd, deptSubYn, userId, assessStatus, startDate, endDate, assessGroupCd, assessTypeCd, userNm, assessNm, regRegdem, startYear, endYear, defaultParam);
    }

    /**
     * 평가계획 조회
     *
     * @param chkKRASYn
     *            KRAS yn
     * @param chkJSAYn
     *            JSA yn
     * @param chkCHARMYn
     *            CHARM yn
     * @param assessNm
     *            평가명
     * @param regRegdem
     *            정시/수기
     * @param assessDesc
     *            상세내용
     * @return 평가계획 목록
     * @throws Exception
     */
    public List<AssessPlan> getAssessPlanResults(String plantCd, String assessStatus, String userId, String deptCd, String deptSubYn, String startDate, String endDate, String assessGroupCd, String assessTypeCd, String userNm, String assessNm, String regRegdem, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return assessPlanMapper.getAssessPlanResults(plantCd, assessStatus, userId, deptCd, deptSubYn, startDate, endDate, assessGroupCd, assessTypeCd, userNm, assessNm, regRegdem, startYear, endYear, defaultParam);
    }

    /**
     * 평가계획 상세 조회
     *
     * @param assessPlanNo
     *            평가계획 번호
     * @return 평가계획
     * @throws Exception
     */
    public AssessPlan getAssessPlan(int assessPlanNo, DefaultParam defaultParam) throws Exception {
        return assessPlanMapper.getAssessPlan(assessPlanNo, defaultParam);
    }

    /**
     * 평가계획 신규등록
     *
     * @param AssessPlan
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAssessPlan(AssessPlan assessPlan) throws Exception {
        return assessPlanMapper.createAssessPlan(assessPlan);
    }

    /**
     * 평가계획 수정
     *
     * @param AssessPlan
     *            평가계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateAssessPlan(AssessPlan assessPlan) throws Exception {
        return assessPlanMapper.updateAssessPlan(assessPlan);
    }

    /**
     * 시설점검계획 진행단계 변경
     *
     * @param comFacilityCheckScheduleNo
     *            시설점검일정 ID
     * @param bizApprStepCd
     *            결재진행단계
     * @param apprRqstNo
     *            결재요청번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprAssessPlan(int assessPlanNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        /*
         * RSA_ASSESS_PLAN_STEP_WRITE-----------P 작성중
         * RSA_ASSESS_PLAN_STEP_CMPLT_PLAN_CODE-A 계획완료
         * RSA_ASSESS_PLAN_STEP_ING_CODE--------E 진행중
         * RSA_ASSESS_PLAN_STEP_EVAL_CMPLT_CODE-C 평가완료
         */
        int resultNo = 0;
        String assessStpeCd = "";
        // 결재완료
        AssessPlan assessPlan = assessPlanMapper.getAssessPlan(assessPlanNo, new DefaultParam("kr"));
        assessStpeCd = assessPlan.getAssessStepCd();
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            if (ConstVal.RSA_ASSESS_PLAN_STEP_WRITE.equals(assessStpeCd)) { // 작성중
                assessPlan.setAssessStepCd(ConstVal.RSA_ASSESS_PLAN_STEP_CMPLT_PLAN_CODE); // 계획완료
            } else if (ConstVal.RSA_ASSESS_PLAN_STEP_ING_CODE.equals(assessStpeCd)) { // 진행중
                assessPlan.setAssessStepCd(ConstVal.RSA_ASSESS_PLAN_STEP_EVAL_CMPLT_CODE); // 평가완료
            }
        }
        // 결재요청번호 세팅
        if (assessStpeCd.equals(ConstVal.RSA_ASSESS_PLAN_STEP_WRITE)) {
            // 계획상태의 경우
            assessPlan.setPapprRqstNo(apprRqstNo);
        } else if (assessStpeCd.equals(ConstVal.RSA_ASSESS_PLAN_STEP_ING_CODE)) {
            // 결과상태의 경우
            assessPlan.setRapprRqstNo(apprRqstNo);
        }
        //
        // 결재 진행단계 수정
        resultNo += assessPlanMapper.updateAssessPlanApprStep(assessPlan);
        return resultNo;
    }

}
