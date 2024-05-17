package com.she.health.workMeasure.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.health.model.WorkMeasureResult;
import com.she.manage.model.Dept;

@Mapper
@Repository("com.she.health.workMeasure.mapper.WorkMeasureResultMapper")
public interface WorkMeasureResultMapper {

    /**
     * 작업환경측정결과 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 작업환경측정결과 목록
     * @throws Exception
     */
    public List<WorkMeasureResult> getWorkMeasureResults(@Param("workMeasPlanNo") int workMeasPlanNo) throws Exception;

    /**
     * 작업환경측정결과 저장
     *
     * @param workMeasureResult
     *            작업환경측정결과
     * @return 저장 행 수
     * @throws Exception
     */
    public int createWorkMeasureResult(WorkMeasureResult workMeasureResult) throws Exception;

    /**
     * 작업환경측정결과 상세 조회
     *
     * @param workMeasRsltNo
     *            작업환경측정결과번호
     * @return 작업환경측정결과
     * @throws Exception
     */
    public WorkMeasureResult getWorkMeasureResult(@Param("workMeasRsltNo") int workMeasRsltNo) throws Exception;

    /**
     * 작업환경측정결과 수정
     *
     * @param workMeasureResult
     *            작업환경측정결과
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateWorkMeasureResult(WorkMeasureResult workMeasureResult) throws Exception;

    /**
     * 작업환경측정결과 삭제(단일)
     *
     * @param workMeasRsltNo
     *            작업환경측정결과번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteWorkMeasureResult(@Param("workMeasRsltNo") int workMeasRsltNo) throws Exception;

    /**
     * 작업환경측정결과 통계
     *
     * @return 작업환경측정결과 통계 목록
     * @throws Exception
     *             예외
     */
    public List<WorkMeasureResult> getWorkMeasureResultStatus(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("hazardNm") String hazardNm, @Param("fromYear") String fromYear, @Param("toYear") String toYear, @Param("yearPeriod") String[] yearPeriod,
            @Param("yearPeriodStr") String yearPeriodStr, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 작업환경측정결과 통계 차트
     *
     * @return 작업환경측정결과 통계 목록
     * @throws Exception
     *             예외
     */
    public List<Map<String, Object>> getWorkMeasureResultChart(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("hazardNm") String hazardNm, @Param("exposureExcessRate") String exposureExcessRate, @Param("fromYear") String fromYear, @Param("toYear") String toYear,
            @Param("monthPeriod") String[] monthPeriod, @Param("monthPeriodStr") String monthPeriodStr) throws Exception;

    public List<Dept> getDeptWorkTempleteInfos() throws Exception;

    /**
     * 작업환경측정결과 조회(계획접수 단계일 경우)
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 작업환경측정결과 목록
     * @throws Exception
     */
    public List<WorkMeasureResult> getInitWorkMeasureResults(@Param("workMeasPlanNo") int workMeasPlanNo) throws Exception;
}
