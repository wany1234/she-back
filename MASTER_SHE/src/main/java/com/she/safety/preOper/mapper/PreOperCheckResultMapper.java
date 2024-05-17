package com.she.safety.preOper.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.FacilityMst;
import com.she.common.model.DefaultParam;
import com.she.safety.model.FacilChkItemResult;
import com.she.safety.model.FacilChkMaster;
import com.she.safety.model.FacilChkResult;
import com.she.safety.model.ForEachType;
import com.she.safety.model.SafPreOperChkPsn;

@Mapper
@Repository("com.she.safety.preOper.mapper.PreOperCheckResultMapper")
public interface PreOperCheckResultMapper {

    public List<HashMap<String, Object>> getFacilityCheckResultStatus(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception;

    public List<HashMap<String, Object>> getFacilityCheckResultStatusSub(@Param("plantCd") String plantCd, @Param("year") String year, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    // ============================================================================================================

    /**
     * 설비점검계획 목록 조회
     *
     * @param plantCd
     *            사업장코드
     * @param safCheckTypeCd
     *            설비점검종류코드
     * @param checkStepCd
     *            점검상태
     * @param startYmd
     *            기간(시작)
     * @param endYmd
     *            기간(종료)
     * @return 설비점검계획 목록
     * @throws Exception
     */
    public List<FacilChkMaster> getFacilChkPlans(@Param("plantCd") String plantCd, @Param("checkStepCd") String checkStepCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("tDeptCd") String tDeptCd, @Param("tDeptSubYn") String tDeptSubYn, @Param("pDeptCd") String pDeptcd, @Param("pDeptSubYn") String pDeptSubYn,
            @Param("chkEndYn") String chkEndYn, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("year") String year, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("keyword") String keyword, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 설비점검계획 상세 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검계획 상세
     * @throws Exception
     */
    public FacilChkMaster getFacilChkPlan(@Param("safFacilChkNo") int safFacilChkNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilChkPlan(FacilChkMaster facilChkMaster) throws Exception;

    /**
     * 설비점검 계획 수정
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateFacilChkPlan(FacilChkMaster facilChkMaster) throws Exception;

    /**
     * 설비점검계획 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkPlan(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

    /**
     * 설비점검 결과 목록 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkResult> getFacilChkResults(@Param("safFacilChkNo") int safFacilChkNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비별 점검자 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public String[] getFacilChkUser(@Param("safFacilChkNo") int safFacilChkNo, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 설비점검 결과 등록
     *
     * @param facilChkResult
     *            설비점검 결과
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilChkResult(FacilChkResult facilChkResult) throws Exception;

    /**
     * 점검자 등록
     *
     * @param safFacilityCd
     *            설비점검 결과
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilChkUser(@Param("safFacilChkNo") int safFacilChkNo, @Param("userId") String userId, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 설비점검계획별 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deletePlanFacilChkResults(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

    /**
     * 설비점검계획별 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkUser(@Param("safFacilChkNo") int safFacilChkNo, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 설비점검일정별 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkResult(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

    /**
     * 설비별 점검항목 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkItem(@Param("safFacilChkNo") int safFacilChkNo, @Param("safFacilityCd") String safFacilityCd, @Param("safCheckTypeCd") String safCheckTypeCd) throws Exception;

    /**
     * 설비별 점검항목 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkItemImpr(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

    /**
     * 설비별 점검유형 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteForEachItem(@Param("safFacilChkNo") int safFacilChkNo, @Param("safCheckTypeCd") String safCheckTypeCd) throws Exception;

    public int deleteFacility(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

    public int deleteForEachFacility(@Param("safFacilChkNo") int safFacilChkNo, @Param("safCheckTypeCd") String safCheckTypeCd) throws Exception;

    /**
     * 전년도 설비점검결과 조회
     *
     * @param safFacilChkNo
     *            검색조건
     * @return 설비점검 계획 상세
     */
    public List<FacilChkResult> getFacilChkBeforeYear(@Param("safFacilChkNo") int safFacilChkNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비별 점검항목
     *
     * @param safFacilChkNo
     *            key
     * @return 안전설비
     * @throws Exception
     */
    public List<ForEachType> getForEachTypes(@Param("safFacilChkNo") int safFacilChkNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검계획 설비별 점검항목 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검별 항목 목록
     * @throws Exception
     */
    public List<FacilChkItemResult> getFacilChkPlanItems(@Param("safFacilChkNo") int safFacilChkNo, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<FacilityMst> getForEachFacilitys(@Param("safFacilChkNo") int safFacilChkNo, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검별 항목 목록 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검별 항목 목록
     * @throws Exception
     */
    public List<FacilChkItemResult> getFacilChkItems(@Param("safFacilChkNo") int safFacilChkNo, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("parentYn") String parentYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검별 항목 목록 조회
     *
     * @param safFacilityTypeCd
     *            설비유형코드
     * @return 설비점검별 항목 목록
     * @throws Exception
     */
    public List<FacilChkItemResult> getNewFacilChkItems(@Param("plantCd") String plantCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("noPlanYn") String noPlanYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 점검항목별 설비 insert
     *
     * @param forEachType
     *            점검항목별 결과
     * @return 등록 및 수정 행 수
     * @throws Exception
     */
    public int createForEachFacility(ForEachType forEachType) throws Exception;

    /**
     * 점검항목별 결과 저장 및 수정
     *
     * @param facilChkItemResult
     *            점검항목별 결과
     * @return 등록 및 수정 행 수
     * @throws Exception
     */
    public int updateFacilChkItem(FacilChkItemResult facilChkItemResult) throws Exception;

    /**
     * 점검항목별 결과 저장 및 수정
     *
     * @param facilChkItemResult
     *            점검항목별 결과
     * @return 등록 및 수정 행 수
     * @throws Exception
     */
    public int updateFacilChkItemSec(FacilChkItemResult facilChkItemResult) throws Exception;

    /**
     * 점검항목별 결과 저장 및 수정
     *
     * @param facilChkItemResult
     *            점검항목별 결과
     * @return 등록 및 수정 행 수
     * @throws Exception
     */
    public int updateForEachItem(FacilChkItemResult facilChkItemResult) throws Exception;

    /**
     * 설비별 점검결과 전체완료
     *
     * @param facilChkMaster
     *            설비점검계획
     * @return 수정 행 수
     */
    public int updateFacilChkResults(FacilChkMaster facilChkMaster) throws Exception;

    /**
     * 설비점검계획 결재 후 진행단계 변경
     *
     * 설비점검번호
     *
     * @param apprRqstNo
     * @param chkStepCd
     * @return 수정 행 수
     */
    public int completeFacilChkMaster(@Param("safFacilityCheckNo") int safFacilityCheckNo, @Param("apprRqstNo") int apprRqstNo, @Param("chkStepCd") String chkStepCd);

    /**
     * 설비점검계획 결재 후 진행단계 변경
     *
     * 설비점검번호
     *
     * @param safFacilChkNo
     * @param chkStepCd
     * @return 수정 행 수
     */
    public int completeFacilChkPlan(@Param("safFacilChkNo") int safFacilChkNo, @Param("apprRqstNo") int apprRqstNo, @Param("chkStepCd") String chkStepCd);

    public int completeResultFacilChkPlan(@Param("safFacilChkNo") int safFacilChkNo, @Param("rApprRqstNo") int rApprRqstNo, @Param("chkStepCd") String chkStepCd);

    /**
     * 설비점검 사업장별 실적집계
     *
     * @param plantCd
     *            사업장코드
     * @param year
     *            년도(점검일)
     * @return 설비점검 사업장별 실적집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getFacilChkResultStatus(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception;

    /**
     * 설비점검 사업장별 실적 세부집계
     *
     * @param plantCd
     *            사업장코드
     * @param year
     *            년도(점검일)
     * @param safCheckTypeCd
     *
     * @param safFacilityTypeCd
     *
     * @param deptCd
     *            점검주관부서
     * @return 설비점검 사업장별 실적 세부집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getFacilChkResultStatusSub(@Param("plantCd") String plantCd, @Param("year") String year, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검 결과 목록 조회
     *
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkResult> getFacilChkResultTable(@Param("plantCd") String plantCd, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("chkEndYn") String chkEndYn, @Param("deptCd") String deptCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("safFacilityTypeCd") String safFacilityTypeCd,
            @Param("completeYn") String completeYn, @Param("dtlYn") String dtlYn) throws Exception;

    /**
     * 가동전점검 점검자 조회
     *
     * @param safFacilChkNo
     * @return
     * @throws Exception
     */
    public List<SafPreOperChkPsn> getPreOperChkPsns(@Param("safFacilChkNo") int safFacilChkNo, @Param("inspectorClassCd") String inspectorClassCd) throws Exception;

    /**
     * 가동전점검 점검자 등록
     *
     * @param safPreOperChkPsn
     * @return
     * @throws Exception
     */
    public int createPreOperChkPsn(SafPreOperChkPsn safPreOperChkPsn) throws Exception;

    /**
     * 가동전점검 점검자 삭제
     *
     * @param safFacilChkNo
     * @return
     * @throws Exception
     */
    public int deletePreOperChkPsn(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

}
