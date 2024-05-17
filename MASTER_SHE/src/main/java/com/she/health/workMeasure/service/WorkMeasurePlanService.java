package com.she.health.workMeasure.service;

import com.she.health.model.WorkMeasurePlan;
import com.she.health.model.WorkMeasurePlanHazard;
import com.she.health.model.WorkMeasurePlanTarget;
import com.she.health.model.WorkMeasureResult;
import com.she.health.workMeasure.mapper.WorkMeasurePlanMapper;
import com.she.utils.ConstVal;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class WorkMeasurePlanService {

    @Autowired
    private WorkMeasurePlanMapper workMeasurePlanMapper;

    @Autowired
    private WorkMeasureResultService workMeasureResultService;

    /**
     * 작업환경측정계획 신규등록
     *
     * @param workMeasurePlan 작업환경측정계획
     * @return 작업환경측정계획번호
     * @throws Exception
     */
    @Transactional
    public int createWorkMeasurePlan(WorkMeasurePlan workMeasurePlan) throws Exception {
        this.workMeasurePlanMapper.createWorkMeasurePlan(workMeasurePlan);
        if (CollectionUtils.isNotEmpty(workMeasurePlan.getWorkMeasurePlanTargets())) {
            for (WorkMeasurePlanTarget workMeasurePlanTarget : workMeasurePlan.getWorkMeasurePlanTargets()) {
                workMeasurePlanTarget.setWorkMeasPlanNo(workMeasurePlan.getWorkMeasPlanNo());
                workMeasurePlanTarget.setCreateUserId(workMeasurePlan.getCreateUserId());
                workMeasurePlanMapper.createWorkMeasurePlanTarget(workMeasurePlanTarget);

                if (CollectionUtils.isNotEmpty(workMeasurePlanTarget.getWorkMeasurePlanHazards())) {
                    for (WorkMeasurePlanHazard workMeasurePlanHazard : workMeasurePlanTarget
                            .getWorkMeasurePlanHazards()) {
                        workMeasurePlanHazard.setWorkMeasPlanTargetNo(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
                        workMeasurePlanMapper.createWorkMeasurePlanHazard(workMeasurePlanHazard);
                    }
                }
            }
        }
        return workMeasurePlan.getWorkMeasPlanNo();
    }

    /**
     * 작업환경측정계획 조회
     *
     * @param plantCd         사업장
     * @param year            측정년도
     * @param measPlanNm      측정계획명
     * @param workMeasStateCd 진행상태
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    public List<WorkMeasurePlan> getWorkMeasurePlans(String plantCd, String year, String measPlanNm,
            String workMeasStateCd) throws Exception {
        return workMeasurePlanMapper.getWorkMeasurePlans(plantCd, year, measPlanNm, workMeasStateCd);
    }

    /**
     * 작업환경측정계획 상세 조회
     *
     * @param workMeasPlanNo 작업환경측정계획번호
     * @return 작업환경측정계획
     * @throws Exception
     */
    public WorkMeasurePlan getWorkMeasurePlan(int workMeasPlanNo) throws Exception {
        WorkMeasurePlan workMeasurePlan = this.workMeasurePlanMapper.getWorkMeasurePlan(workMeasPlanNo);
        if (workMeasurePlan != null) {
            List<WorkMeasurePlanTarget> workMeasurePlanTargets = this.workMeasurePlanMapper
                    .getWorkMeasurePlanTargets(workMeasPlanNo);
            if (CollectionUtils.isNotEmpty(workMeasurePlanTargets)) {
                for (WorkMeasurePlanTarget workMeasurePlanTarget : workMeasurePlanTargets) {
                    workMeasurePlanTarget.setWorkMeasurePlanHazards(this.workMeasurePlanMapper
                            .getWorkMeasurePlanHazards(workMeasurePlanTarget.getWorkMeasPlanTargetNo()));
                }
            }
            workMeasurePlan.setWorkMeasurePlanTargets(workMeasurePlanTargets);
        }
        return workMeasurePlan;
    }

    /**
     * 작업환경측정계획 수정
     *
     * @param workMeasurePlan 작업환경측정계획
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateWorkMeasurePlan(WorkMeasurePlan workMeasurePlan) throws Exception {

        int result = 0;
        this.workMeasurePlanMapper.updateWorkMeasurePlan(workMeasurePlan);

        // 유해인자 등록 및 수정
        if (CollectionUtils.isNotEmpty(workMeasurePlan.getWorkMeasurePlanTargets())) {
            for (WorkMeasurePlanTarget workMeasurePlanTarget : workMeasurePlan.getWorkMeasurePlanTargets()) {
                workMeasurePlanTarget.setWorkMeasPlanNo(workMeasurePlan.getWorkMeasPlanNo());
                if (workMeasurePlanTarget.getWorkMeasPlanTargetNo() > 0) {
                    // 수정인 경우
                    workMeasurePlanTarget.setUpdateUserId(workMeasurePlan.getUpdateUserId());
                    workMeasurePlanMapper.updateWorkMeasurePlanTarget(workMeasurePlanTarget);
                } else {
                    // 등록인 경우
                    workMeasurePlanTarget.setCreateUserId(workMeasurePlan.getUpdateUserId());
                    workMeasurePlanMapper.createWorkMeasurePlanTarget(workMeasurePlanTarget);
                }

                if (workMeasurePlanTarget.getWorkMeasurePlanHazards() != null) {
                    workMeasurePlanMapper.deleteWorkMeasurePlanHazard(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
                    if (workMeasurePlanTarget.getWorkMeasurePlanHazards().size() > 0) {
                        for (WorkMeasurePlanHazard workMeasurePlanHazard : workMeasurePlanTarget
                                .getWorkMeasurePlanHazards()) {
                            workMeasurePlanHazard
                                    .setWorkMeasPlanTargetNo(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
                            workMeasurePlanMapper.createWorkMeasurePlanHazard(workMeasurePlanHazard);
                        }
                    }
                }
            }
        }

        // 대상 범위 및 유해인자 삭제
        if (CollectionUtils.isNotEmpty(workMeasurePlan.getDeleteWorkMeasurePlanTargets())) {
            for (WorkMeasurePlanTarget workMeasurePlanTarget : workMeasurePlan.getDeleteWorkMeasurePlanTargets()) {
                workMeasurePlanMapper.deleteWorkMeasurePlanHazard(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
                workMeasurePlanMapper.deleteWorkMeasurePlanTarget(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
            }
        }
        return result;
    }

    /**
     * 작업환경측정계획 체크
     *
     * @param plantCd        사업장코드
     * @param year           측정년도
     * @param halfYearCd     측정분기
     * @param measAgency     측정기관
     * @param workMeasPlanNo 작업환경측정계획번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckWorkMeasurePlan(String plantCd, String year, String halfYearCd,
            int workMeasPlanNo, String measDt) throws Exception {
        return workMeasurePlanMapper.getCheckWorkMeasurePlan(plantCd, year, halfYearCd, workMeasPlanNo, measDt);
    }

    /**
     * 작업환경측정계획 삭제
     *
     * @param workMeasurePlan 작업환경측정계획s
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteWorkMeasurePlan(WorkMeasurePlan workMeasurePlan) throws Exception {
        int returnVal = 0;
        // if (workMeasurePlans != null && workMeasurePlans.size() > 0) {
        // for (WorkMeasurePlan workMeasurePlan : workMeasurePlans) {
        if (workMeasurePlan != null) {
            List<WorkMeasurePlanTarget> workMeasurePlanTargets = this.workMeasurePlanMapper
                    .getWorkMeasurePlanTargets(workMeasurePlan.getWorkMeasPlanNo());

            if (CollectionUtils.isNotEmpty(workMeasurePlanTargets)) {
                for (WorkMeasurePlanTarget workMeasurePlanTarget : workMeasurePlanTargets) {
                    workMeasurePlanMapper.deleteWorkMeasurePlanHazard(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
                    workMeasurePlanMapper.deleteWorkMeasurePlanTarget(workMeasurePlanTarget.getWorkMeasPlanTargetNo());
                }
            }
        }

        if( workMeasurePlan != null) {
            returnVal += this.workMeasurePlanMapper.deleteWorkMeasurePlan(workMeasurePlan.getWorkMeasPlanNo());
        }

        // }
        // }
        return returnVal;
    }

    /**
     * 작업환경측정계획 결재
     *
     * @param workMeasPlanNo 작업환경측정계획 번호
     * @param bizApprStepCd  결재단계
     * @param apprRqstNo     결재번호
     * @return 수정 행 수
     * @throws Exception
     */
    public int apprProcessWorkMeasurePlan(int workMeasPlanNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String workMeasStateCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            List<WorkMeasureResult> workMeasureResults = workMeasureResultService
                    .getInitWorkMeasureResults(workMeasPlanNo);

            for (WorkMeasureResult workMeasureResult : workMeasureResults) {
                workMeasureResultService.createWorkMeasureResult(workMeasureResult);
            }

            // 결재완료
            workMeasStateCd = ConstVal.WORK_MEASURE_PLAN_COMP;
        }

        return workMeasurePlanMapper.apprProcessWorkMeasurePlan(workMeasPlanNo, apprRqstNo, workMeasStateCd);
    }

    /**
     * 작업환경측정결과 결재
     *
     * @param workMeasPlanNo 작업환경측정계획 번호
     * @param bizApprStepCd  결재단계
     * @param apprRqstNo     결재번호
     * @return 수정 행 수
     * @throws Exception
     */
    public int apprProcessWorkMeasureResult(int workMeasPlanNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String workMeasStateCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            workMeasStateCd = ConstVal.WORK_MEASURE_RESULT_COMP;
        }

        return workMeasurePlanMapper.apprProcessWorkMeasureResult(workMeasPlanNo, apprRqstNo, workMeasStateCd);
    }

    /**
     * 작업환경측정결과 화면 조회
     *
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    public List<WorkMeasurePlan> getWorkMeasurePlanResults(String plantCd, String year, String measPlanNm,
            String workMeasStateCd) throws Exception {
        return workMeasurePlanMapper.getWorkMeasurePlanResults(plantCd, year, measPlanNm, workMeasStateCd);
    }

    /**
     * 작업환경측정결과 실적 조회
     *
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    public List<WorkMeasureResult> workmeasurestatss(String plantCd, String year, String deptCd, String processCd, String halfYearCd, String workAreaGradeCd) throws Exception {
        return workMeasurePlanMapper.workmeasurestatss(plantCd, year, deptCd, processCd, halfYearCd, workAreaGradeCd);
    }

    /**
     * 작업환경측정계획 결과용 상세 조회
     *
     * @param workMeasPlanNo 작업환경측정계획번호
     * @return 작업환경측정계획
     * @throws Exception
     */
    public WorkMeasurePlan getWorkMeasurePlanResult(int workMeasPlanNo) throws Exception {
        WorkMeasurePlan workMeasurePlan = this.workMeasurePlanMapper.getWorkMeasurePlanResult(workMeasPlanNo);
        if (workMeasurePlan != null) {
            List<WorkMeasurePlanTarget> workMeasurePlanTargets = this.workMeasurePlanMapper
                    .getWorkMeasurePlanTargets(workMeasPlanNo);
            if (CollectionUtils.isNotEmpty(workMeasurePlanTargets)) {
                for (WorkMeasurePlanTarget workMeasurePlanTarget : workMeasurePlanTargets) {
                    workMeasurePlanTarget.setWorkMeasurePlanHazards(this.workMeasurePlanMapper
                            .getWorkMeasurePlanHazards(workMeasurePlanTarget.getWorkMeasPlanTargetNo()));
                }
            }
            workMeasurePlan.setWorkMeasurePlanTargets(workMeasurePlanTargets);
            workMeasurePlan.setWorkMeasureResults(workMeasureResultService.getWorkMeasureResults(workMeasPlanNo, null));
        }
        return workMeasurePlan;
    }
}
