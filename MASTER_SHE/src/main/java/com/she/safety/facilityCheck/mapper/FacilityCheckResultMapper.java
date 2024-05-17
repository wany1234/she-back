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
package com.she.safety.facilityCheck.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.FacilChkItemResult;
import com.she.safety.model.FacilChkMaster;
import com.she.safety.model.FacilChkResult;
import com.she.safety.model.FacilityCheckInspector;
import com.she.safety.model.FacilityCheckItemResult;
import com.she.safety.model.FacilityCheckMaster;
import com.she.safety.model.FacilityCheckSchedule;

@Mapper
@Repository("com.she.safety.facilityCheck.mapper.FacilityCheckResultMapper")
public interface FacilityCheckResultMapper {

    /**
     * 설비점검마스터 신규등록
     *
     * @param facilityCheckMaster
     *            설비점검마스터
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilityCheckMaster(FacilityCheckMaster facilityCheckMaster) throws Exception;

    /**
     * 설비점검마스터 수정
     *
     * @param facilityCheckMaster
     *            설비점검마스터
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateFacilityCheckMaster(FacilityCheckMaster facilityCheckMaster) throws Exception;

    /**
     * 설비점검일정 삭제 (설비점검마스터에 따른)
     *
     * @param safFacilityCheckNo
     *            설비점검일련번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilityCheckSchedules(@Param("safFacilityCheckNo") int safFacilityCheckNo) throws Exception;

    /**
     * 설비점검마스터 삭제
     *
     * @param safFacilityCheckNo
     *            설비점검일련번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilityCheckMaster(@Param("safFacilityCheckNo") int safFacilityCheckNo) throws Exception;

    /**
     * 설비점검계획 조회
     *
     * @param startYmd
     *            from
     * @param endYmd
     *            to
     * @param plantCd
     *            사업장
     * @param safCheckTypeCd
     *            점검종류
     * @param safFacilityTypeCd
     *            설비유형
     * @param facilityNm
     *            설비명
     * @param checkStepCd
     *            진행단계
     * @param safFacilityCheckNo
     *            설비점검마스터번호
     * @return 설비점검계획 목록
     * @throws Exception
     */
    public List<FacilityCheckSchedule> getFacilityCheckResults(@Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("facilityNm") String facilityNm,
            @Param("checkStepCd") String checkStepCd, @Param("safFacilityCheckNo") int safFacilityCheckNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검계획 상세조회
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @return 설비점검계획
     * @throws Exception
     */
    public FacilityCheckSchedule getFacilityCheckResult(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검결과 조회
     *
     * @param startYmd
     *            from
     * @param endYmd
     *            to
     * @param plantCd
     *            사업장
     * @param safCheckTypeCd
     *            점검종류
     * @param safFacilityTypeCd
     *            설비유형
     * @param facilityNm
     *            설비명
     * @param checkStepCd
     *            진행단계
     * @param safFacilityCheckNo
     *            설비점검마스터번호
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilityCheckSchedule> getrFacilityCheckResults(@Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("facilityNm") String facilityNm,
            @Param("checkStepCd") String checkStepCd, @Param("safFacilityCheckNo") int safFacilityCheckNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검결과 상세조회
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @return 설비점검결과
     * @throws Exception
     */
    public FacilityCheckSchedule getrFacilityCheckResult(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검계획 수정
     *
     * @param facilityCheckSchedule
     *            설비점검계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityCheckResult(FacilityCheckSchedule facilityCheckSchedule) throws Exception;

    /**
     * 설비점검_대상자 삭제
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteFacilityCheckInspector(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo) throws Exception;

    /**
     * 설비점검_항목별점검결과 삭제
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteFacilityCheckItemResult(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo) throws Exception;

    /**
     * 설비점검_대상자 등록
     *
     * @param facilityCheckInspector
     *            설비점검_대상자
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityCheckInspector(FacilityCheckInspector facilityCheckInspector) throws Exception;

    /**
     * 설비점검 대상자 조회
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @param inspectorClassCd
     *            점검자구분코드
     * @return 설비점검 대상자 목록
     * @throws Exception
     */
    public List<FacilityCheckInspector> getFacilityCheckInspectors(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo, @Param("inspectorClassCd") String inspectorClassCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검 항목별점검결과 조회
     *
     * @param safFacilityTypeCd
     *            설비유형
     * @param plantCd
     *            사업장
     * @return 설비점검 항목별점검결과 목록
     * @throws Exception
     */
    public List<FacilityCheckItemResult> getNewFacilityCheckItemResults(@Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 설비점검_항목별점검결과 등록
     *
     * @param FacilityCheckItemResult
     *            설비점검_항목별점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityCheckItemResult(FacilityCheckItemResult facilityCheckItemResult) throws Exception;

    /**
     * 설비점검_항목별점검결과 수정
     *
     * @param FacilityCheckItemResult
     *            설비점검_항목별점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityCheckItemResult(FacilityCheckItemResult facilityCheckItemResult) throws Exception;

    /**
     * 설비점검 진행상태 수정
     *
     * @param checkStepCd
     *            설비점검진행상태
     * @param updateUserId
     *            수정자ID
     * @param safFacilityCheckResultNo
     *            설비점검결과번호
     * @return 설비점검 대상자 목록
     * @throws Exception
     */
    public int updateFacilityCheckResultStepCd(@Param("checkStepCd") String checkStepCd, @Param("updateUserId") String updateUserId, @Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo) throws Exception;

    /**
     * 설비점검 진행상태 수정
     *
     * @param safFacilityCheckNo
     *            설비점검마스터 번호
     * @param apprRqstNo
     *            결재진행번호
     * @return 설비점검 대상자 목록
     * @throws Exception
     */
    public int completeFacilityCheckMaster(@Param("safFacilityCheckNo") int safFacilityCheckNo, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    /**
     * 설비점검 진행상태 수정
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정 번호
     * @param checkStepCd
     *            설비점검일정 진행상태코드
     * @return 설비점검 대상자 목록
     * @throws Exception
     */
    public int completeFacilityCheckMasterSchedule(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo, @Param("checkStepCd") String checkStepCd) throws Exception;

    /**
     * 설비점검 진행상태 수정
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정 번호
     * @param apprRqstNo
     *            결재진행번호
     * @param checkStepCd
     *            설비점검일정 진행상태코드
     * @return 설비점검 대상자 목록
     * @throws Exception
     */
    public int completeFacilityCheckSchedule(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo, @Param("apprRqstNo") int apprRqstNo, @Param("checkStepCd") String checkStepCd) throws Exception;

    /**
     * 설비점검 진행상태 수정
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정 번호
     * @param apprRqstNo
     *            결재진행번호
     * @param checkStepCd
     *            설비점검일정 진행상태코드
     * @return 설비점검 대상자 목록
     * @throws Exception
     */
    public int completeFacilityCheckScheduleResult(@Param("safFacilityCheckScheduleNo") int safFacilityCheckScheduleNo, @Param("apprRqstNo") int apprRqstNo, @Param("checkStepCd") String checkStepCd) throws Exception;

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
    public List<FacilChkMaster> getFacilChkPlans(@Param("plantCd") String plantCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("checkStepCd") String checkStepCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("keyword") String keyword,
            @Param("tDeptCd") String tDeptCd, @Param("tDeptSubYn") String tDeptSubYn, @Param("pDeptCd") String pDeptcd, @Param("pDeptSubYn") String pDeptSubYn, @Param("chkEndYn") String chkEndYn, @Param("facilityNm") String facilityNm, @Param("defaultParam") DefaultParam defaultParam);

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
     * 설비점검결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkResult(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

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
    public List<FacilChkResult> getFacilChkResults(@Param("safFacilChkNo") int safFacilChkNo, @Param("tDeptCd") String tDeptCd, @Param("pDeptCd") String pDeptCd, @Param("safFacilityCd") String safFacilityCd) throws Exception;

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
     * 설비점검 결과 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkResults(@Param("safFacilChkNo") int safFacilChkNo, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 설비점검결과 목록
     *
     * @param plantCd
     *            사업장코드
     * @param safCheckTypeCd
     *            설비점검종류코드
     * @param checkStepCd
     *            점검상태코드
     * @param startYmd
     *            기간(시작)
     * @param endYmd
     *            기간(종료)
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkMaster> getRFacilChkPlans(@Param("plantCd") String plantCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("checkStepCd") String checkStepCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("keyword") String keyword,
            @Param("tDeptCd") String tDeptCd, @Param("tDeptSubYn") String tDeptSubYn, @Param("pDeptCd") String pDeptcd, @Param("pDeptSubYn") String pDeptSubYn, @Param("chkEndYn") String chkEndYn, @Param("facilityNm") String facilityNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비점검결과 상세 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param safFacilityCd
     *            설비코드
     * @return 설비점검결과 상세 정보
     * @throws Exception
     */
    public FacilChkResult getFacilChkResult(@Param("safFacilChkNo") int safFacilChkNo, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 설비점검별 항목 목록 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param safFacilityCd
     *            설비코드
     * @return 설비점검별 항목 목록
     * @throws Exception
     */
    public List<FacilChkItemResult> getFacilChkItems(@Param("safFacilChkNo") int safFacilChkNo, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 설비점검별 항목 목록 조회
     *
     * @param safFacilityTypeCd
     *            설비유형코드
     * @return 설비점검별 항목 목록
     * @throws Exception
     */
    public List<FacilChkItemResult> getNewFacilChkItems(@Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("plantCd") String plantCd, @Param("safCheckTypeCd") String safCheckTypeCd) throws Exception;

    /**
     * 설비점검결과 수정
     *
     * @param facilChkResult
     *            설비점검결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateFacilChkResult(FacilChkResult facilChkResult) throws Exception;

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
     * @param safFacilChkNo
     *            설비점검번호
     * @param apprRqstNo
     * @param chkStepCd
     * @return 수정 행 수
     */
    public int completeFacilChkPlan(@Param("safFacilChkNo") int safFacilChkNo, @Param("apprRqstNo") int apprRqstNo, @Param("chkStepCd") String chkStepCd);

    /**
     * 설비점검결과 확정 유무 판별
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검 결과수
     * @throws Exception
     */
    public int getFacilChkComStatus(@Param("safFacilChkNo") int safFacilChkNo) throws Exception;

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
            @Param("chkStepCd") String chkStepCd) throws Exception;

}
