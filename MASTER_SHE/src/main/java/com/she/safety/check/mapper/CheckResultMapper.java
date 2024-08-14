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
package com.she.safety.check.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.CheckInspector;
import com.she.safety.model.CheckItemResult;
import com.she.safety.model.CheckMaster;
import com.she.safety.model.CheckSchedule;
import com.she.safety.model.CheckVendor;

@Mapper
@Repository("com.she.safety.check.mapper.CheckResultMapper")
public interface CheckResultMapper {

    /**
     * 안전점검일정 목록 조회
     *
     * @param startDate
     *            From
     * @param endDate
     *            To
     * @param tgtDeptCd
     *            대상부서코드
     * @param deptCd
     *            주관부서코드
     * @param safCheckKindNo
     *            점검종류번호
     * @param plantCd
     *            사업장 코드
     * @param stepStatus
     *            점검상태
     * @return 안전점검결과 목록
     * @throws Exception
     */
    public List<CheckMaster> getCheckScheduleList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("pfmDeptCd") String pfmDeptCd, @Param("pfmDeptSubYn") String pfmDeptSubYn, @Param("safCheckKindNo") int safCheckKindNo,
            @Param("plantCd") String plantCd, @Param("stepStatus") String stepStatus, @Param("keyword") String keyword, @Param("defaultParam") DefaultParam defaultParam, @Param("chngKind") String chngKind, @Param("vendorCd") String vendorCd) throws Exception;

    /**
     * 안전점검 마스터 등록
     *
     * @param checkMaster
     * @return
     * @throws Exception
     */
    public int createCheckMaster(CheckMaster checkMaster) throws Exception;

    /**
     * 안전점검 일정 등록
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    public int createCheckSchedule(CheckSchedule checkSchedule) throws Exception;

    /**
     * 안전점검 마스터 조회
     *
     * @param safCheckNo
     * @return 안전점검 마스터 정보
     * @throws Exception
     */
    public CheckMaster getCheckMaster(@Param("safCheckNo") int safCheckNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검 일정 목록 조회
     *
     * @param safCheckNo
     * @return 안전점검 일정 목록
     * @throws Exception
     */
    public List<CheckSchedule> getCheckSchedule(@Param("safCheckNo") int safCheckNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검 마스터 수정
     *
     * @param checkMaster
     * @return
     * @throws Exception
     */
    public int updateCheckMaster(CheckMaster checkMaster) throws Exception;

    /**
     * 안전점검 마스터 일정결재요청번호 수정
     *
     * @param apprRqstNo
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    public int updateCheckMasterForAppr(@Param("apprRqstNo") int apprRqstNo, @Param("safCheckNo") int safCheckNo) throws Exception;

    /**
     * 안전점검 일정 점검진행상태 일괄 수정
     *
     * @param checkStepCd
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    public int updateCheckScheduleForAppr(@Param("checkStepCd") String checkStepCd, @Param("safCheckNo") int safCheckNo) throws Exception;

    /**
     * 안전점검 마스터 삭제
     *
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    public int deleteCheckMaster(@Param("safCheckNo") int safCheckNo) throws Exception;

    /**
     * 안전점검 일정 삭제
     *
     * @param safCheckNo
     * @param safCheckScheduleNo
     * @return
     * @throws Exception
     */
    public int deleteCheckSchedule(@Param("safCheckNo") int safCheckNo, @Param("safCheckScheduleNo") int safCheckScheduleNo) throws Exception;

    /**
     * 안전점검계획 목록 조회
     *
     * @param startDate
     * @param endDate
     * @param tgtDeptCd
     * @param deptCd
     * @param safCheckKindNo
     * @param plantCd
     * @param checkStepCd
     * @return
     * @throws Exception
     */
    public List<CheckSchedule> getCheckPlanList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pfmDeptCd") String pfmDeptCd, @Param("pfmDeptSubYn") String pfmDeptSubYn, @Param("deptCd") String deptCd,
            @Param("deptSubYn") String deptSubYn, @Param("safCheckKindNo") int safCheckKindNo, @Param("plantCd") String plantCd, @Param("checkStepCd") String checkStepCd, @Param("keyword") String keyword, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검 계획, 결과 상세 조회
     *
     * @param safCheckScheduleNo
     * @return
     * @throws Exception
     */
    public CheckSchedule getCheckScheduleInfo(@Param("safCheckScheduleNo") int safCheckScheduleNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검 점검자 목록 조회
     *
     * @param safCheckScheduleNo
     * @param inspectorClassCd
     * @return 안전점검 점검자 목록
     * @throws Exception
     */
    public List<CheckInspector> getCheckInspectors(@Param("safCheckScheduleNo") int safCheckScheduleNo, @Param("inspectorClassCd") String inspectorClassCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검_점검자 등록
     *
     * @param checkInspector
     *            안전점검_점검자
     * @return 변경 행 수
     * @throws Exception
     */
    public int createCheckInspector(CheckInspector checkInspector) throws Exception;

    /**
     * 안전점검_점검자 삭제
     *
     * @param safCheckScheduleNo
     *            안전점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteCheckInspector(@Param("safCheckScheduleNo") int safCheckScheduleNo) throws Exception;

    /**
     * 안전점검_개선 삭제
     *
     * @param safCheckScheduleNo
     *            안전점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteImprAct(@Param("safCheckScheduleNo") int safCheckScheduleNo) throws Exception;

    /**
     * 안전점검일정 수정
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    public int updateCheckSchedule(CheckSchedule checkSchedule) throws Exception;

    /**
     * 안전점검 계획 결재요청번호, 결재진행상태 수정
     *
     * @param safCheckScheduleNo
     * @param apprRqstNo
     * @param checkStepCd
     * @return
     * @throws Exception
     */
    public int updateCheckPlanForAppr(@Param("safCheckScheduleNo") int safCheckScheduleNo, @Param("apprRqstNo") int apprRqstNo, @Param("checkStepCd") String checkStepCd) throws Exception;

    /**
     * 안전점검 결과 결재요청번호, 결재진행상태 수정
     *
     * @param safCheckScheduleNo
     * @param apprRqstNo
     * @param checkStepCd
     * @return
     * @throws Exception
     */
    public int updateCheckResultForAppr(@Param("safCheckScheduleNo") int safCheckScheduleNo, @Param("apprRqstNo") int apprRqstNo, @Param("checkStepCd") String checkStepCd) throws Exception;

    /**
     * 안전점검계획 목록 조회
     *
     * @param startDate
     * @param endDate
     * @param tgtDeptCd
     * @param deptCd
     * @param safCheckKindNo
     * @param plantCd
     * @param checkStepCd
     * @return
     * @throws Exception
     */
    public List<CheckSchedule> getCheckResultList(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("checkResultCd") String checkResultCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtDeptSubYn") String tgtDeptSubYn, @Param("pfmDeptCd") String pfmDeptCd, @Param("pfmDeptSubYn") String pfmDeptSubYn,
            @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("safCheckKindNo") int safCheckKindNo, @Param("plantCd") String plantCd, @Param("checkStepCd") String checkStepCd, @Param("keyword") String keyword, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전점검_항목별점검결과 등록
     *
     * @param checkItemResult
     *            안전점검_항목별점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    public int createCheckItemResult(CheckItemResult checkItemResult) throws Exception;

    /**
     * 안전점검_항목별점검결과 삭제
     *
     * @param safCheckScheduleNo
     *            안전점검일정 일련번호
     * @param safCheckItemNo
     *            안전점검항목번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteCheckItemResult(@Param("safCheckScheduleNo") int safCheckScheduleNo, @Param("safCheckItemNo") int safCheckItemNo) throws Exception;

    /**
     * 안전점검 항목별점검결과 조회
     *
     * @param safCheckScheduleNo
     *            안전점검일정 일련번호
     * @param safCheckNo
     *            안전점검 마스터 일련번호
     * @param safCheckKindNo
     *            안전설비유형코드
     * @return 안전점검 항목별점검결과 목록
     * @throws Exception
     */
    public List<CheckItemResult> getCheckItemResults(@Param("safCheckScheduleNo") int safCheckScheduleNo, @Param("safCheckNo") int safCheckNo, @Param("safCheckKindNo") int safCheckKindNo) throws Exception;

    /**
     * 안전점검 진행상태값 수정
     *
     * @param checkStepCd
     *            안전점검진행상태코드
     * @param updateUserId
     *            수정자ID
     * @param safCheckScheduleNo
     *            안전점검일정일련번호
     * @return 안전점검 대상자 목록
     * @throws Exception
     */
    public int updateCheckStepCd(@Param("checkStepCd") String checkStepCd, @Param("updateUserId") String updateUserId, @Param("safCheckScheduleNo") int safCheckScheduleNo) throws Exception;

    public List<HashMap<String, Object>> getCheckResultStatus(@Param("plantCd") String plantCd, @Param("year") String year) throws Exception;

    public List<HashMap<String, Object>> getCheckResultStatusSub(@Param("plantCd") String plantCd, @Param("year") String year, @Param("safCheckKindNo") int safCheckKindNo, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtDeptSubYn") String tgtDeptSubYn) throws Exception;
    
    /**
     * 안전점검일정 협력업체 등록
     * @return
     * @throws Exception
     */
    public int insertCheckVendor(CheckVendor checkVendor) throws Exception;
    
    /**
     * 안전점검일정 협력업체 삭제
     * @return
     * @throws Exception
     */
    public int deleteCheckVendor(@Param("safCheckNo") int safCheckNo) throws Exception;
    
    /**
     * 안전점검일정 협력업체 조회
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    public List<CheckVendor> getCheckVendor(@Param("safCheckNo") int safCheckNo) throws Exception;

}
