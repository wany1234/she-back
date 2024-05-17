package com.she.safety.patrol.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.Patrol;
import com.she.safety.model.PatrolInspector;
import com.she.safety.model.PatrolItemResult;

@Mapper
@Repository("com.she.safety.patrol.mapper.PatrolMapper")
public interface PatrolMapper {

    /**
     * 순회 조회
     * 
     * @param plantCd
     *            사업장
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            순회주관부서
     * @param safCheckKindNo
     *            순회종류
     * @param checkStepCd
     *            진행단계
     * @param tgtClsCd
     *            순회대상
     * @param tgtDeptCd
     *            순회대상부서
     * @param tgtVendorCd
     *            순회대상업체
     * @param bizApprStepCd
     *            결재진행상태
     * @return 순회 목록
     * @throws Exception
     */
	public List<Patrol> getPatrols(@Param("tgtVendorNm") String tgtVendorNm, @Param("checkResultCd") String checkResultCd, @Param("plantCd") String plantCd, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("startDateRes") String startDateRes, @Param("endDateRes") String endDateRes, @Param("deptCd") String deptCd,
            @Param("deptSubYn") String deptSubYn, @Param("safCheckKindNo") int safCheckKindNo, @Param("checkStepCd") String checkStepCd, @Param("tgtClsCd") String tgtClsCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pgpDeptCd") String pgpDeptCd, @Param("pgpDeptSubYn") String pgpDeptSubYn,@Param("tgtVendorCd") String tgtVendorCd, @Param("bizApprStepCd") String bizApprStepCd,
            @Param("userNm") String userNm, @Param("checkTitle") String checkTitle) throws Exception;

    /**
     * 순회 상세조회
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 순회
     * @throws Exception
     */
    public Patrol getPatrol(@Param("safCheckRsltNo") int safCheckRsltNo) throws Exception;

    /**
     * 순회 등록
     * 
     * @param patrol
     *            순회
     * @return 등록 행 수
     * @throws Exception
     */
    public int createPatrol(Patrol patrol) throws Exception;

    /**
     * 순회 수정
     * 
     * @param patrol
     *            순회
     * @return 수정 행 수
     * @throws Exception
     */
    public int updatePatrol(Patrol patrol) throws Exception;

    /**
     * 순회 삭제
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deletePatrol(@Param("safCheckRsltNo") int safCheckRsltNo) throws Exception;

    /**
     * 순회 삭제
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteMasterPatrol(@Param("patrolMstNo") int patrolMstNo) throws Exception;

    /**
     * 순회 점검자 등록/수정
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int createPlanPsn(PatrolInspector inspector) throws Exception;

    /**
     * 순회 결과 조회1
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 순회 결과 목록
     * @throws Exception
     */
    public List<PatrolItemResult> getPatrolResultItems(@Param("safCheckRsltNo") int safCheckRsltNo) throws Exception;

    /**
     * 순회 결과 조회2
     * 
     * @param safCheckKindNo
     *            순회종류
     * @return 순회 결과 목록
     * @throws Exception
     */
    public List<PatrolItemResult> getNewPatrolResultItems(@Param("safCheckKindNo") int safCheckKindNo) throws Exception;

    /**
     * 순회 결과 등록
     * 
     * @param patrolItemResult
     *            순회결과
     * @return 등록 행 수
     * @throws Exception
     */
    public int createPatrolResultItem(PatrolItemResult patrolItemResult) throws Exception;

    /**
     * 안전점검 점검자 목록 조회
     * 
     * @param safCheckScheduleNo
     * @param inspectorClassCd
     * @return 안전점검 점검자 목록
     * @throws Exception
     */
    public List<PatrolInspector> getCheckInspectors(@Param("safCheckRsltNo") int safCheckRsltNo, @Param("inspectorClassCd") String inspectorClassCd) throws Exception;

    public int deletePlanPsn(@Param("safCheckRsltNo") int safCheckRsltNo) throws Exception;

    /**
     * 순회 결과 삭제
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deletePatrolResultItem(@Param("safCheckRsltNo") int safCheckRsltNo) throws Exception;

    public List<HashMap<String, Object>> getPatrolStatus(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception;

    public int countPatrolList(@Param("patrolMstNo") int patrolMstNo, @Param("checkStepCd") String checkStepCd) throws Exception;

    public int completePatrolResultPlan(@Param("safCheckRsltNo") int safCheckRsltNo) throws Exception;

    public List<HashMap<String, Object>> getPatrolStatusSub(@Param("plantCd") String plantCd, @Param("year") String year, @Param("deptCd") String deptCd, @Param("tgtClsCd") String tgtClsCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtVendorCd") String tgtVendorCd, @Param("checkResultCd") String checkResultCd) throws Exception;

}
