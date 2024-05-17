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
package com.she.safety.facilityInspection.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.FacilityInspectionInspector;
import com.she.safety.model.FacilityInspectionItemResult;
import com.she.safety.model.FacilityInspectionMaster;
import com.she.safety.model.FacilityInspectionSchedule;

@Mapper
@Repository("com.she.safety.facilityInspection.mapper.FacilityInspectionMapper")
public interface FacilityInspectionMapper {
    /**
     * 시설점검일정 조회
     * 
     * @param startYmd
     *            from
     * @param endYmd
     *            to
     * @param plantCd
     *            사업장
     * @param deptCd
     *            점검부서
     * @param comFacilityCheckCd
     *            점검종류
     * @param comFacilityTypeCd
     *            시설유형
     * @param facilityNm
     *            시설명
     * @param stepStatus
     *            진행단계
     * @return 시설점검일정 목록
     * @throws Exception
     */

    public List<FacilityInspectionMaster> getFacilityInspectionSchedules(@Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("pfmDeptCd") String pfmDeptCd, @Param("deptSubYn") String deptSubYn,
            @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pfmDeptSubYn") String pfmDeptSubYn, @Param("comFacilityCheckCd") String comFacilityCheckCd, @Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("facilityNm") String facilityNm, @Param("stepStatus") String stepStatus) throws Exception;

    /**
     * 시설 유형 셀렉박스 리스트 조회
     * 
     * @return 시설 유형 리스트
     * @throws Exception
     */
    public List<Map<String, String>> getComFacilityTypes(@Param("facilityCd") String facilityCd, @Param("facilityNm") String facilityNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 시설점검마스터 상세조회
     * 
     * @param safAccidentNo
     *            시설점검일련번호
     * @return 시설점검마스터
     * @throws Exception
     */
    public FacilityInspectionMaster getFacilityInspectionMaster(@Param("comFacilityCheckNo") int comFacilityCheckNo) throws Exception;

    /**
     * 시설점검일정목록 상세조회
     * 
     * @param safAccidentNo
     *            시설점검일련번호
     * @param plantCd
     *            사업장코드
     * @return 시설점검일정목록
     * @throws Exception
     */
    public List<FacilityInspectionSchedule> getFacilityInspectionSchedule(@Param("comFacilityCheckNo") int comFacilityCheckNo, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 시설점검마스터 신규등록
     * 
     * @param facilityInspectionMaster
     *            시설점검마스터
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilityInspectionMaster(FacilityInspectionMaster facilityInspectionMaster) throws Exception;

    /**
     * 시설점검마스터 수정
     * 
     * @param facilityInspectionMaster
     *            시설점검마스터
     * @return 수정 행 수
     * @throws Exception
     */

    public int updateFacilityInspectionMaster(FacilityInspectionMaster facilityInspectionMaster) throws Exception;

    /**
     * 시설점검일정 신규등록
     * 
     * @param facilityInspectionSchedule
     *            시설점검일정
     * @return 등록 행 수
     * @throws Exception
     */

    public int createFacilityCheckSchedule(FacilityInspectionSchedule facilityInspectionSchedule) throws Exception;

    /**
     * 시설점검일정 수정
     * 
     * @param facilityInspectionSchedule
     *            시설점검일정
     * @return 수정 행 수
     * @throws Exception
     */

    public int updateFacilityCheckSchedule(FacilityInspectionSchedule facilityInspectionSchedule) throws Exception;

    /**
     * 시설점검일정 삭제
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilityCheckSchedule(@Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception;

    /**
     * 시설점검일정 삭제 (시설점검마스터에 따른)
     * 
     * @param comFacilityCheckNo
     *            시설점검일련번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilityCheckSchedules(@Param("comFacilityCheckNo") int comFacilityCheckNo) throws Exception;

    /**
     * 시설점검마스터 삭제
     * 
     * @param comFacilityCheckNo
     *            시설점검일련번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilityInspectionMaster(@Param("comFacilityCheckNo") int comFacilityCheckNo) throws Exception;

    /**
     * 시설점검계획 조회
     * 
     * @param plantCd
     *            사업장
     * @param startYmd
     *            from
     * @param endYmd
     *            to
     * @param comFacilityCheckCd
     *            점검종류
     * @param comFacilityTypeCd
     *            시설유형
     * @param facilityNm
     *            시설명
     * @param checkStepCd
     *            진행단계
     * @param deptCd
     *            점검부서
     * @param comFacilityCheckNo
     *            시설점검마스터번호
     * @return 시설점검일정 목록
     * @throws Exception
     */
    public List<FacilityInspectionSchedule> getFacilityInspectionPlans(@Param("plantCd") String plantCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("comFacilityCheckCd") String comFacilityCheckCd, @Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("facilityNm") String facilityNm,

            @Param("checkStepCd") String checkStepCd, @Param("deptCd") String deptCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("pfmDeptCd") String pfmDeptCd, @Param("deptSubYn") String deptSubYn, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pfmDeptSubYn") String pfmDeptSubYn, @Param("comFacilityCheckNo") int comFacilityCheckNo)
            throws Exception;

    /**
     * 시설점검결과 조회
     * 
     * @param plantCd
     *            사업장
     * @param startYmd
     *            from
     * @param endYmd
     *            to
     * @param comFacilityCheckCd
     *            점검종류
     * @param comFacilityTypeCd
     *            시설유형
     * @param facilityNm
     *            시설명
     * @param checkStepCd
     *            진행단계
     * @param deptCd
     *            점검부서
     * @return 시설점검일정 목록
     * @throws Exception
     */
    public List<FacilityInspectionSchedule> getFacilityInspectionResults(@Param("plantCd") String plantCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("comFacilityCheckCd") String comFacilityCheckCd, @Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("facilityNm") String facilityNm,
            @Param("checkResultCd") String checkResultCd, @Param("checkStepCd") String checkStepCd, @Param("deptCd") String deptCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("pfmDeptCd") String pfmDeptCd, @Param("deptSubYn") String deptSubYn, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pfmDeptSubYn") String pfmDeptSubYn,
            @Param("comFacilityCheckNo") int comFacilityCheckNo) throws Exception;

    /**
     * 시설점검계획 상세조회
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 시설점검계획
     * @throws Exception
     */
    public FacilityInspectionSchedule getFacilityInspectionPlan(@Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception;

    /**
     * 시설점검계획 수정
     * 
     * @param facilityInspectionSchedule
     *            시설점검계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityInspectionPlan(FacilityInspectionSchedule facilityInspectionSchedule) throws Exception;

    /**
     * 시설점검 대상자 조회
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @param inspectorClassCd
     *            점검자구분코드
     * @return 시설점검 대상자 목록
     * @throws Exception
     */
    public List<FacilityInspectionInspector> getFacilityInsInspectors(@Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo, @Param("inspectorClassCd") String inspectorClassCd) throws Exception;

    /**
     * 시설점검_대상자 등록
     * 
     * @param FacilityInspectionInspector
     *            시설점검_대상자
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityInsInspector(FacilityInspectionInspector facilityInspectionInspector) throws Exception;

    /**
     * 시설점검_대상자 삭제
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteFacilityInsInspector(@Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception;

    /**
     * 시설점검 항목별점검결과 조회
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일련번호
     * @param comFacilityCheckCd
     *            점검종류코드
     * @param comFacilityTypeCd
     *            시설유형코드
     * @return 시설점검 항목별점검결과 목록
     * @throws Exception
     */
    public List<FacilityInspectionItemResult> getFacilityInsItemResults(@Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo, @Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 시설점검 항목별점검항목 조회
     * 
     * @param comFacilityTypeCd
     *            시설유형
     * @param comFacilityCheckCd
     *            점검종류코드
     * @param plantCd
     *            사업장
     * @return 시설점검 항목별점검항목 목록
     * @throws Exception
     */
    public List<FacilityInspectionItemResult> getNewFacilityInsItemResults(@Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("comFacilityCheckCd") String comFacilityCheckCd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 시설점검_항목별점검결과 등록
     * 
     * @param facilityInspectionItemResult
     *            시설점검_항목별점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityInsItemResult(FacilityInspectionItemResult facilityInspectionItemResult) throws Exception;

    /**
     * 시설점검_항목별점검결과 수정
     * 
     * @param facilityInspectionItemResult
     *            시설점검_항목별점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityInsItemResult(FacilityInspectionItemResult facilityInspectionItemResult) throws Exception;

    /**
     * 시설점검_항목별점검결과 삭제
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteFacilityInsItemResult(@Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception;

    /**
     * 시설점검 진행상태 수정
     * 
     * @param checkStepCd
     *            시설점검진행상태
     * @param updateUserId
     *            수정자ID
     * @param comFacilityCheckResultNo
     *            시설점검일정번호
     * @return 변경 행 수
     * @throws Exception
     */

    public int updateFacilityInspectionStepCd(@Param("checkStpeCd") String checkStpeCd, @Param("updateUserId") String updateUserId, @Param("comFacilityCheckScheduleNo") int comFacilityCheckScheduleNo) throws Exception;

    public List<HashMap<String, Object>> getFacilityInspectionResultStatus(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception;

    public List<HashMap<String, Object>> getFacilityInspectionResultStatusSub(@Param("plantCd") String plantCd, @Param("year") String year, @Param("comFacilityCheckCd") String comFacilityCheckCd, @Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 무계획 점검결과 등록 시 시설점검항목 첨부파일 임시키 변환
     * 
     * @param checkItemTaskTempKey
     * @param taskKey
     * @return
     * @throws Exception
     */
    public int updateFacilityInspectionItemRsltFileKey(@Param("checkItemTaskTempKey") String checkItemTaskTempKey, @Param("taskKey") String taskKey) throws Exception;

}
