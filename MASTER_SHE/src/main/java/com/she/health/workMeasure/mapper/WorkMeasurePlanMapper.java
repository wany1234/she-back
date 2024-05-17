package com.she.health.workMeasure.mapper;

import com.she.health.model.WorkMeasurePlan;
import com.she.health.model.WorkMeasurePlanHazard;
import com.she.health.model.WorkMeasurePlanTarget;
import com.she.health.model.WorkMeasureResult;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository("com.she.health.workMeasure.mapper.WorkMeasurePlanMapper")
public interface WorkMeasurePlanMapper {

    /**
     * 작업환경측정계획 신규등록
     *
     * @param workMeasurePlan
     *            작업환경측정계획
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWorkMeasurePlan(WorkMeasurePlan workMeasurePlan) throws Exception;

    /**
     * 작업환경측정계획 범위 및 대상 신규등록
     *
     * @param workMeasurePlanTarget
     *            범위 및 대상
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWorkMeasurePlanTarget(WorkMeasurePlanTarget workMeasurePlanTarget) throws Exception;

    /**
     * 작업환경측정계획 범위 및 대상 유해위험요인 신규등록
     *
     * @param workMeasurePlanHazard
     *            범위 및 대상 유해위험요인
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWorkMeasurePlanHazard(WorkMeasurePlanHazard workMeasurePlanHazard) throws Exception;

    /**
     * 작업환경측정계획 조회
     *
     * @param plantCd
     *            사업장
     * @param year
     *            측정년도
     * @param measPlanNm
     *            측정계획명
     * @param workMeasStateCd
     *            진행상태
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    public List<WorkMeasurePlan> getWorkMeasurePlans(@Param("plantCd") String plantCd,
                                                     @Param("year") String year,
                                                     @Param("measPlanNm") String measPlanNm,
                                                     @Param("workMeasStateCd") String workMeasStateCd) throws Exception;

    /**
     * 작업환경측정계획 상세 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 작업환경측정계획
     * @throws Exception
     */
    public WorkMeasurePlan getWorkMeasurePlan(@Param("workMeasPlanNo") int workMeasPlanNo) throws Exception;

    /**
     * 작업환경측정계획 상세 범위 및 대상 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 범위 및 대상
     * @throws Exception
     */
    public List<WorkMeasurePlanTarget> getWorkMeasurePlanTargets(@Param("workMeasPlanNo") int workMeasPlanNo) throws Exception;

    /**
     * 작업환경측정계획 상세 범위 및 대상 유해위험요인 조회
     *
     * @param workMeasPlanTargetNo
     *            작업환경측정계획 상세 범위 및 대상 번호
     * @return 유해위험요인
     * @throws Exception
     */
    public List<WorkMeasurePlanHazard> getWorkMeasurePlanHazards(@Param("workMeasPlanTargetNo") int workMeasPlanTargetNo) throws Exception;

    /**
     * 작업환경측정계획 수정
     *
     * @param workMeasurePlan
     *            작업환경측정계획
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateWorkMeasurePlan(WorkMeasurePlan workMeasurePlan) throws Exception;

    /**
     * 작업환경측정계획 범위 및 대상 수정
     *
     * @param workMeasurePlanTarget
     *            범위 및 대상
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateWorkMeasurePlanTarget(WorkMeasurePlanTarget workMeasurePlanTarget) throws Exception;

    /**
     * 작업환경측정계획 범위 및 대상 유해위험요인 삭제
     *
     * @param workMeasPlanTargetNo
     *            범위 및 대상 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWorkMeasurePlanHazard(@Param("workMeasPlanTargetNo") int workMeasPlanTargetNo) throws Exception;

    /**
     * 작업환경측정계획 범위 및 대상 삭제
     *
     * @param workMeasPlanTargetNo
     *            범위 및 대상 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWorkMeasurePlanTarget(@Param("workMeasPlanTargetNo") int workMeasPlanTargetNo) throws Exception;

    /**
     * 작업환경측정계획 체크
     *
     * @param plantCd
     *            사업장코드
     * @param year
     *            측정년도
     * @param halfYearCd
     *            측정분기
     * @param measAgency
     *            측정기관
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckWorkMeasurePlan(@Param("plantCd") String plantCd,
                                                                 @Param("year") String year,
                                                                 @Param("halfYearCd") String halfYearCd,
                                                                 @Param("workMeasPlanNo") int workMeasPlanNo,
                                                                 @Param("measDt") String measDt) throws Exception;

    /**
     * 작업환경측정계획 삭제
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWorkMeasurePlan(@Param("workMeasPlanNo") int workMeasPlanNo) throws Exception;

    /**
     * 작업환경측정계획 단계 변경
     *
     * @param workMeasurePlan
     *            작업환경측정계획
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateWorkMeasurePlanStep(WorkMeasurePlan workMeasurePlan) throws Exception;

    /**
     * 작업환경측정계획 결재
     *
     * @param workMeasPlanNo
     *            작업환경측정계획 번호
     * @param apprRqstNo
     *            결재번호
     * @param workMeasStateCd
     *            작업환경측정계획 단계
     * @return 수정 행 수
     * @throws Exception
     */
    public int apprProcessWorkMeasurePlan(@Param("workMeasPlanNo") int workMeasPlanNo,
                                          @Param("apprRqstNo") int apprRqstNo,
                                          @Param("workMeasStateCd") String workMeasStateCd) throws Exception;

    /**
     * 작업환경측정결과 결재
     *
     * @param workMeasPlanNo
     *            작업환경측정계획 번호
     * @param apprRqstNo
     *            결재번호
     * @param workMeasStateCd
     *            작업환경측정계획 단계
     * @return 수정 행 수
     * @throws Exception
     */
    public int apprProcessWorkMeasureResult(@Param("workMeasPlanNo") int workMeasPlanNo,
                                            @Param("apprRqstNo") int apprRqstNo,
                                            @Param("workMeasStateCd") String workMeasStateCd) throws Exception;

    /**
     * 작업환경측정결과 화면 조회
     *
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    public List<WorkMeasurePlan> getWorkMeasurePlanResults(@Param("plantCd") String plantCd,
                                                           @Param("year") String year,
                                                           @Param("measPlanNm") String measPlanNm,
                                                           @Param("workMeasStateCd") String workMeasStateCd) throws Exception;

    /**
     * 작업환경측정결과 실적 조회
     *
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    public List<WorkMeasureResult> workmeasurestatss(@Param("plantCd") String plantCd,
            @Param("year") String year,
            @Param("deptCd") String deptCd,
            @Param("processCd") String processCd,
            @Param("halfYearCd") String halfYearCd,
            @Param("workAreaGradeCd") String workAreaGradeCd) throws Exception;

    /**
     * 작업환경측정계획 결과용 상세 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 작업환경측정계획
     * @throws Exception
     */
    public WorkMeasurePlan getWorkMeasurePlanResult(@Param("workMeasPlanNo") int workMeasPlanNo) throws Exception;
}
