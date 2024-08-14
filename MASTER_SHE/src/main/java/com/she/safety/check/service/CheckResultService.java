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
package com.she.safety.check.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.safety.check.mapper.CheckResultMapper;
import com.she.safety.model.CheckInspector;
import com.she.safety.model.CheckItemResult;
import com.she.safety.model.CheckMaster;
import com.she.safety.model.CheckSchedule;
import com.she.safety.model.CheckVendor;
import com.she.utils.ConstVal;

/**
 * 안전점검결과 기능정의
 *
 */
@Service
public class CheckResultService {

    @Autowired
    private CheckResultMapper checkResultMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 안전점검일정 조회
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
    public List<CheckMaster> getCheckScheduleList(String startDate, String endDate, String tgtDeptCd, String tgtDeptSubYn, String deptCd, String deptSubYn, String pfmDeptCd, String pfmDeptSubYn, int safCheckKindNo, String plantCd, String stepStatus, String keyword, DefaultParam defaultParam) throws Exception {
        return checkResultMapper.getCheckScheduleList(startDate, endDate, tgtDeptCd, tgtDeptSubYn, deptCd, deptSubYn, pfmDeptCd, pfmDeptSubYn,safCheckKindNo, plantCd, stepStatus, keyword, defaultParam);
    }

    /**
     * 안전점검일정 등록
     *
     * @param checkResult
     * @return
     * @throws Exception
     */
    @Transactional
    public int createCheckSchedule(CheckMaster checkMaster) throws Exception {
        int result = 0;
        if (checkMaster == null) {
            return 0;
        } else {
            // 안전점검 마스터 등록
            result = checkResultMapper.createCheckMaster(checkMaster);

            if (result <= 0) {
                return 0;
            } else {
                // 안전점검 일정 등록
                if (CollectionUtils.isNotEmpty(checkMaster.getCheckScheduleList())) {
                    for (CheckSchedule checkSchedule : checkMaster.getCheckScheduleList()) {
                        checkSchedule.setSafCheckNo(checkMaster.getSafCheckNo()); // 안전점검일련번호
                        checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_SCHEDULE_CD); // 점검진행상태(일정)
                        checkSchedule.setCreateUserId(checkMaster.getCreateUserId()); // 등록자
                        checkResultMapper.createCheckSchedule(checkSchedule);
                    }
                }
                
                // 순회점검일때 협력업체 등록
                if (ConstVal.CHNG_KIND_CIRCUIT.equals(checkMaster.getChngKind()) && CollectionUtils.isNotEmpty(checkMaster.getVendorList())) {
                	for (CheckVendor checkVendor : checkMaster.getVendorList()) {
                		checkVendor.setSafCheckNo(checkMaster.getSafCheckNo()); // 안전점검일련번호
                		checkResultMapper.insertCheckVendor(checkVendor);
                	}
                }
                return checkMaster.getSafCheckNo();
            }
        }
    }

    /**
     * 안전점검 일정 상세조회
     *
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    public CheckMaster getCheckScheduleDetail(int safCheckNo, DefaultParam defaultParam) throws Exception {
        if (safCheckNo > 0) {
            // 안전점검 마스터 조회
            CheckMaster checkMaster = checkResultMapper.getCheckMaster(safCheckNo, defaultParam);

            if (checkMaster == null) {
                return null;
            } else {
                // 안전점검 일정 목록 조회
                checkMaster.setCheckScheduleList(checkResultMapper.getCheckSchedule(safCheckNo, defaultParam));
                // 아전점검 협력업체 조회
                checkMaster.setVendorList(checkResultMapper.getCheckVendor(safCheckNo));
                return checkMaster;
            }
        } else {
            return null;
        }
    }

    /**
     * 안전점검일정 수정
     *
     * @param checkMaster
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateCheckSchedule(CheckMaster checkMaster) throws Exception {
        int result = 0;
        if (checkMaster == null) {
            return 0;
        } else {
            // 안전점검 마스터 수정
            result = checkResultMapper.updateCheckMaster(checkMaster);

            if (result <= 0) {
                return 0;
            } else {
                // 기등록된 안전점검 일정 삭제
                checkResultMapper.deleteCheckSchedule(checkMaster.getSafCheckNo(), 0);
                // 안전점검 일정 등록
                if (CollectionUtils.isNotEmpty(checkMaster.getCheckScheduleList())) {
                    for (CheckSchedule checkSchedule : checkMaster.getCheckScheduleList()) {
                        checkSchedule.setSafCheckNo(checkMaster.getSafCheckNo()); // 안전점검일련번호
                        checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_SCHEDULE_CD); // 점검진행상태(일정)
                        checkSchedule.setCreateUserId(checkMaster.getCreateUserId()); // 등록자
                        checkResultMapper.createCheckSchedule(checkSchedule);
                    }
                }
                
                // 기등록된 협력업체 삭제
                checkResultMapper.deleteCheckVendor(checkMaster.getSafCheckNo());
                // 순회점검일때 협력업체 등록
                if (ConstVal.CHNG_KIND_CIRCUIT.equals(checkMaster.getChngKind()) && CollectionUtils.isNotEmpty(checkMaster.getVendorList())) {
                	for (CheckVendor checkVendor : checkMaster.getVendorList()) {
                		checkVendor.setSafCheckNo(checkMaster.getSafCheckNo()); // 안전점검일련번호
                		checkResultMapper.insertCheckVendor(checkVendor);
                	}
                }
                
                return checkMaster.getSafCheckNo();
            }
        }
    }

    /**
     * 안전점검일정 결재처리
     *
     * @param safCheckNo
     * @param apprRqstNo
     * @param bizApprStepCd
     * @return
     */
    @Transactional
    public int apprCheckSchedule(int safCheckNo, int apprRqstNo, String bizApprStepCd) throws Exception {
        int result = 0;
        if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_ING)) {
            // 결재중
            result = checkResultMapper.updateCheckMasterForAppr(apprRqstNo, safCheckNo);
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            // 결재완료
            result = checkResultMapper.updateCheckScheduleForAppr(ConstVal.SAF_CHK_STEP_PLAN_CD, safCheckNo);
            result = checkResultMapper.updateCheckMasterForAppr(apprRqstNo, safCheckNo);
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
            // 반려는 처리할게 없다..
            result = 1;
        } else {
            result = checkResultMapper.updateCheckMasterForAppr(apprRqstNo, safCheckNo);
        }
        return result;
    }

    /**
     * 안전점검일정 완료
     *
     * @param checkMaster
     * @return
     * @throws Exception
     */
    @Transactional
    public int completeCheckSchedule(CheckMaster checkMaster) throws Exception {
        int result = 0;
        String[] arrayTgtDeptCd = null;
        String[] arrayCheckSchYmd = null;
        String[] arrayCheckTitle = null;

        if (checkMaster == null) {
            return 0;
        } else {
            // 안전점검 마스터 수정
            result = checkResultMapper.updateCheckMaster(checkMaster);

            if (result <= 0) {
                return 0;
            } else {
                // 기등록된 안전점검 일정 삭제
                checkResultMapper.deleteCheckSchedule(checkMaster.getSafCheckNo(), 0);

                // 대상부서 목록
                if (ArrayUtils.isNotEmpty(checkMaster.getArrayTgtDeptCd())) {
                    arrayTgtDeptCd = checkMaster.getArrayTgtDeptCd();
                }
                // 점검예정일 목록
                if (ArrayUtils.isNotEmpty(checkMaster.getArrayCheckSchYmd())) {
                    arrayCheckSchYmd = checkMaster.getArrayCheckSchYmd();
                }
                // 점검명 목록
                if (ArrayUtils.isNotEmpty(checkMaster.getArrayCheckTitle())) {
                    arrayCheckTitle = checkMaster.getArrayCheckTitle();
                }
                // 안전점검 일정 등록
                if (ArrayUtils.isNotEmpty(arrayTgtDeptCd)) {
                    for (int i = 0; i < arrayTgtDeptCd.length; i++) {
                        if (ArrayUtils.isNotEmpty(arrayCheckSchYmd) && ArrayUtils.isNotEmpty(arrayCheckTitle)) {
                            for (int j = 0; j < arrayCheckSchYmd.length; j++) {
                                CheckSchedule checkSchedule = new CheckSchedule();
                                checkSchedule.setSafCheckNo(checkMaster.getSafCheckNo()); // 안전점검일련번호
                                checkSchedule.setCheckSchYmd(arrayCheckSchYmd[j]); // 점검예정일
                                checkSchedule.setCheckTitle(arrayCheckTitle[j]); // 점검명
                                checkSchedule.setTgtDeptCd(arrayTgtDeptCd[i]); // 대상부서코드
                                checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_PLAN_CD); // 점검진행상태(계획)
                                checkSchedule.setCreateUserId(checkMaster.getCreateUserId()); // 등록자
                                checkResultMapper.createCheckSchedule(checkSchedule);
                            }
                        }
                    }
                }
                return checkMaster.getSafCheckNo();
            }
        }
    }

    /**
     * 안전점검 일정 삭제
     *
     * @param safCheckNo
     * @return
     * @throws Exception
     */
    public int deleteCheckSchedule(int safCheckNo) throws Exception {
        int result = 0;
        if (safCheckNo > 0) {
            // 안전점검 일정 목록 삭제
            result = checkResultMapper.deleteCheckSchedule(safCheckNo, 0);
            //안전점검 협력업체 삭제
            result += checkResultMapper.deleteCheckVendor(safCheckNo);
            // 안전점검 마스터 삭제
            result += checkResultMapper.deleteCheckMaster(safCheckNo);
        }
        return result;
    }

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
     * @return 안전점검결과 목록
     * @throws Exception
     */
    public List<CheckSchedule> getCheckPlanList(String startDate, String endDate, String tgtDeptCd, String tgtDeptSubYn, String pfmDeptCd, String pfmDeptSubYn, String deptCd, String deptSubYn, int safCheckKindNo, String plantCd, String checkStepCd, String keyword, DefaultParam defaultParam) throws Exception {
        return checkResultMapper.getCheckPlanList(startDate, endDate, tgtDeptCd, tgtDeptSubYn, pfmDeptCd, pfmDeptSubYn, deptCd, deptSubYn, safCheckKindNo, plantCd, checkStepCd, keyword, defaultParam);
    }

    /**
     * 안전점검 계획, 결과 상세 조회
     *
     * @param safCheckScheduleNo
     * @return
     * @throws Exception
     */
    public CheckSchedule getCheckScheduleInfo(int safCheckScheduleNo, DefaultParam defaultParam) throws Exception {
        if (safCheckScheduleNo <= 0) {
            return null;
        } else {
            return checkResultMapper.getCheckScheduleInfo(safCheckScheduleNo, defaultParam);
        }
    }

    /**
     * 안전점검 점검자 조회
     *
     * @param safCheckScheduleNo
     * @param inspectorClassCd
     * @return 안전점검 점검자 목록
     * @throws Exception
     */
    public List<CheckInspector> getCheckInspectors(int safCheckScheduleNo, String inspectorClassCd, DefaultParam defaultParam) throws Exception {
        return checkResultMapper.getCheckInspectors(safCheckScheduleNo, inspectorClassCd, defaultParam);
    }

    /**
     * 안전점검계획 등록
     *
     * @param checkSchedule
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createCheckPlan(CheckSchedule checkSchedule) throws Exception {
        int resultNo = 0;
        /**
         * 2020-02-08 KDH 무일정등록 추가로 인해 일정 없이 계획이 들어 갈 수 있도록 수정 이 시점에서 이 API는 타지
         * 않는 것으로 확인 따라서 바꾸어도 다른 곳에 영향 가지 않음으로 수정
         */
        CheckMaster checkMaster = new CheckMaster();
        checkMaster.setPlantCd(checkSchedule.getPlantCd());
        checkMaster.setDeptCd(checkSchedule.getDeptCd());
        checkMaster.setSafCheckKindNo(checkSchedule.getSafCheckKindNo());
        checkMaster.setCreateUserId(checkSchedule.getCreateUserId());
        checkMaster.setCheckMasterTitle(checkSchedule.getCheckTitle());
        checkMaster.setNoSchYn("Y"); // 무일정여부에 Y를 넣으므로 목록과 현황에서 조회 및 카운팅 되지 않게
        // 처리
        checkResultMapper.createCheckMaster(checkMaster);

        checkSchedule.setSafCheckNo(checkMaster.getSafCheckNo());
        checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_PLAN_CD);
        checkResultMapper.createCheckSchedule(checkSchedule);

        if (CollectionUtils.isNotEmpty(checkSchedule.getCheckInspectors())) {
            // 안전점검_점검자 등록
            for (CheckInspector checkInspector : checkSchedule.getCheckInspectors()) {
                checkInspector.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                checkInspector.setSafCheckNo(checkSchedule.getSafCheckNo());

                // 점검결과 탭이 사라짐으로 인해 예정시작시간과 예정종료시간에 따른 결과물이 없어짐
                // 따라서 시작시간과 종료시간을 예정시간에 맞추어 똑같이 기입
                // 이유는 안전점검결과의 상태가 계획완료 상태로 바뀌게 되면 예정일이 아닌 점검시간을 보여줄건데 이때 기입
                // 되어졌었던 예정일을 default로 보여주기 위함
                // 결과 상태에서 update 및 insert 하는 경우에는 예정일의 값을 가져가서 넣어주는 것이 아닌 진짜
                // 점검시간 컬럼에 값이 바인딩 되어 등록 되도록 해야함
                checkInspector.setCheckHour(checkInspector.getCheckSchHour());
                checkInspector.setCheckMinute(checkInspector.getCheckSchMinute());
                checkInspector.setCheckEhour(checkInspector.getCheckSchEhour());
                checkInspector.setCheckEminute(checkInspector.getCheckSchEminute());
                resultNo += checkResultMapper.createCheckInspector(checkInspector);
            }
        }
        return resultNo > 0 ? checkSchedule.getSafCheckScheduleNo() : 0;
    }

    /**
     * 안전점검계획 수정.
     *
     * @param checkSchedule
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateCheckPlan(CheckSchedule checkSchedule) throws Exception {
        int resultNo = 0;
        /**
         * 2020-02-08 KDH 해당 API는 현 시점에서 안전점검계획 메뉴에 상세 팝업에서 수정 버튼 누르는 경우만 탐 따라서
         * 계획상세 팝업에서만 타는 것으로 점검자 테이블을 수정하는 경우 점검예정시간을 점검시간에 그대로 넣는다. 일정과 계획은
         * update 치고 있지 않음으로 로직 추가
         */
        if (checkSchedule.getSafCheckScheduleNo() > 0) {
            CheckMaster checkMaster = new CheckMaster();
            checkMaster.setPlantCd(checkSchedule.getPlantCd());
            checkMaster.setDeptCd(checkSchedule.getDeptCd());
            checkMaster.setSafCheckKindNo(checkSchedule.getSafCheckKindNo());
            checkMaster.setCheckMasterTitle(checkSchedule.getCheckMasterTitle());
            checkMaster.setUpdateUserId(checkSchedule.getUpdateUserId());
            checkMaster.setSafCheckNo(checkSchedule.getSafCheckNo());
            checkResultMapper.updateCheckMaster(checkMaster);

            checkResultMapper.updateCheckSchedule(checkSchedule);
            if (CollectionUtils.isNotEmpty(checkSchedule.getCheckInspectors())) {
                // 안전점검_점검자 삭제
                checkResultMapper.deleteCheckInspector(checkSchedule.getSafCheckScheduleNo());
                // 안전점검_점검자 등록
                for (CheckInspector checkInspector : checkSchedule.getCheckInspectors()) {
                    checkInspector.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                    checkInspector.setSafCheckNo(checkSchedule.getSafCheckNo());

                    // 점검결과 탭이 사라짐으로 인해 예정시작시간과 예정종료시간에 따른 결과물이 없어짐
                    // 따라서 시작시간과 종료시간을 예정시간에 맞추어 똑같이 기입
                    // 이유는 안전점검결과의 상태가 계획완료 상태로 바뀌게 되면 예정일이 아닌 점검시간을 보여줄건데 이때 기입
                    // 되어졌었던 예정일을 default로 보여주기 위함
                    // 결과 상태에서 update 및 insert 하는 경우에는 예정일의 값을 가져가서 넣어주는 것이 아닌
                    // 진짜
                    // 점검시간 컬럼에 값이 바인딩 되어 등록 되도록 해야함
                    checkInspector.setCheckHour(checkInspector.getCheckSchHour());
                    checkInspector.setCheckMinute(checkInspector.getCheckSchMinute());
                    checkInspector.setCheckEhour(checkInspector.getCheckSchEhour());
                    checkInspector.setCheckEminute(checkInspector.getCheckSchEminute());
                    resultNo += checkResultMapper.createCheckInspector(checkInspector);
                }
            }
        }
        return resultNo;
    }

    /**
     * 안전점검 계획 결재
     *
     * @param safCheckScheduleNo
     * @param apprRqstNo
     * @param bizApprStepCd
     * @return
     * @throws Exception
     */
    @Transactional
    public int apprCheckPlan(int safCheckScheduleNo, int apprRqstNo, String bizApprStepCd) throws Exception {
        int result = 0;
        if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_ING)) {
            // 결재중
            result = checkResultMapper.updateCheckPlanForAppr(safCheckScheduleNo, apprRqstNo, "");
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            // 결재완료
            result = checkResultMapper.updateCheckPlanForAppr(safCheckScheduleNo, apprRqstNo, ConstVal.SAF_CHK_STEP_RESULT_CD); // 안전점검
            // 결과
            // 단계로
            // 변경
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
            // 반려는 처리할게 없다..
            result = 1;
        }
        return result;
    }

    /**
     * 안전점검계획 완료.
     *
     * @param checkSchedule
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int completeCheckPlan(CheckSchedule checkSchedule) throws Exception {
        int resultNo = 0;

        if (checkSchedule.getSafCheckScheduleNo() > 0) {
            // 안전점검일정 계획단계 완료 처리
            checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_RESULT_CD); // 점검진행상태(결과)
            resultNo = checkResultMapper.updateCheckSchedule(checkSchedule);

            if (resultNo > 0) {
                if (CollectionUtils.isNotEmpty(checkSchedule.getCheckInspectors())) {
                    // 안전점검_점검자 삭제
                    checkResultMapper.deleteCheckInspector(checkSchedule.getSafCheckScheduleNo());
                    // 안전점검_점검자 등록
                    for (CheckInspector checkInspector : checkSchedule.getCheckInspectors()) {
                        checkInspector.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                        checkInspector.setSafCheckNo(checkSchedule.getSafCheckNo());

                        // 점검결과 탭이 사라짐으로 인해 예정시작시간과 예정종료시간에 따른 결과물이 없어짐
                        // 따라서 시작시간과 종료시간을 예정시간에 맞추어 똑같이 기입
                        // 이유는 안전점검결과의 상태가 계획완료 상태로 바뀌게 되면 예정일이 아닌 점검시간을 보여줄건데
                        // 이때 기입
                        // 되어졌었던 예정일을 default로 보여주기 위함
                        // 결과 상태에서 update 및 insert 하는 경우에는 예정일의 값을 가져가서 넣어주는 것이
                        // 아닌
                        // 진짜
                        // 점검시간 컬럼에 값이 바인딩 되어 등록 되도록 해야함
                        checkInspector.setCheckHour(checkInspector.getCheckSchHour());
                        checkInspector.setCheckMinute(checkInspector.getCheckSchMinute());
                        checkInspector.setCheckEhour(checkInspector.getCheckSchEhour());
                        checkInspector.setCheckEminute(checkInspector.getCheckSchEminute());
                        resultNo += checkResultMapper.createCheckInspector(checkInspector);
                    }
                }
            }
        }
        return resultNo;
    }

    /**
     * 안전점검계획 삭제
     *
     * @param safCheckNo
     * @param safCheckScheduleNo
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteCheckPlan(int safCheckNo, int safCheckScheduleNo) throws Exception {
        int resultNo = 0;
        if (safCheckNo > 0) {
            // 안전점검 점검자 삭제
            resultNo += checkResultMapper.deleteCheckInspector(safCheckScheduleNo);
            // 안전점검계획 삭제
            resultNo = checkResultMapper.deleteCheckSchedule(safCheckNo, safCheckScheduleNo);
            // 안전점검일정은 삭제 X
        }
        return resultNo;
    }

    /**
     * 안전점검결과 목록 조회
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
     * @param checkStepCd
     *            점검진행상태
     * @return 안전점검결과 목록
     * @throws Exception
     */
    public List<CheckSchedule> getCheckResultList(String startDate, String endDate, String checkResultCd, String tgtDeptCd, String tgtDeptSubYn, String pfmDeptCd, String pfmDeptSubYn, String deptCd, String deptSubYn, int safCheckKindNo, String plantCd, String checkStepCd, String keyword, DefaultParam defaultParam) throws Exception {
        return checkResultMapper.getCheckResultList(startDate, endDate, checkResultCd, tgtDeptCd, tgtDeptSubYn, pfmDeptCd, pfmDeptSubYn, deptCd, deptSubYn, safCheckKindNo, plantCd, checkStepCd, keyword, defaultParam);
    }

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
    public List<CheckItemResult> getCheckItemResults(int safCheckScheduleNo, int safCheckNo, int safCheckKindNo) throws Exception {
        return checkResultMapper.getCheckItemResults(safCheckScheduleNo, safCheckNo, safCheckKindNo);
    }

    /**
     * 안전점검결과 수정.
     *
     * @param checkResult
     *            안전점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public CheckSchedule updateCheckResult(CheckSchedule checkSchedule) throws Exception {
        int resultNo = 0;

        if (checkSchedule.getSafCheckScheduleNo() > 0) {
            // 안전점검일정 결과 업데이트
            CheckSchedule param = new CheckSchedule();
            param.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
            param.setCheckYmd(checkSchedule.getCheckYmd());
            param.setCheckTitle(checkSchedule.getCheckTitle());
            param.setCheckResult(checkSchedule.getCheckResult());
            param.setCheckResultCd(checkSchedule.getCheckResultCd());
            param.setUpdateUserId(checkSchedule.getUpdateUserId());
            param.setCheckStepCd(checkSchedule.getCheckStepCd());
            param.setCheckSchYmd(checkSchedule.getCheckSchYmd());

            resultNo = checkResultMapper.updateCheckSchedule(param);

            if (resultNo <= 0) {
                return checkSchedule;
            } else {
                if (CollectionUtils.isNotEmpty(checkSchedule.getCheckInspectors())) {
                    // 안전점검_점검자 삭제
                    checkResultMapper.deleteCheckInspector(checkSchedule.getSafCheckScheduleNo());
                    // 안전점검_점검자 등록
                    for (CheckInspector checkInspector : checkSchedule.getCheckInspectors()) {
                        checkInspector.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                        checkInspector.setSafCheckNo(checkSchedule.getSafCheckNo());
                        resultNo += checkResultMapper.createCheckInspector(checkInspector);
                    }
                }
                if (CollectionUtils.isNotEmpty(checkSchedule.getCheckItemResults())) {
                    // 안전점검 항목별점검결과 삭제
                   checkResultMapper.deleteCheckItemResult(checkSchedule.getSafCheckScheduleNo(), 0);
//                    checkResultMapper.deleteCheckItemResult(checkSchedule.getSafCheckScheduleNo(), checkSchedule.getSafCheckKindNo());
                    // 안전점검 항목별점검결과 등록
                    for (CheckItemResult checkItemResult : checkSchedule.getCheckItemResults()) {
                        checkItemResult.setSafCheckNo(checkSchedule.getSafCheckNo());
                        checkItemResult.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                        checkItemResult.setCreateUserId(checkSchedule.getUpdateUserId());
                        resultNo += checkResultMapper.createCheckItemResult(checkItemResult);
                    }
                }
            }
        } else {
            return checkSchedule;
        }
        return checkSchedule;
    }

    /**
     * 안전점검 결과 결재
     *
     * @param safCheckScheduleNo
     * @param apprRqstNo
     * @param bizApprStepCd
     * @return
     * @throws Exception
     */
    @Transactional
    public int apprCheckResult(int safCheckScheduleNo, int apprRqstNo, String bizApprStepCd) throws Exception {
        int result = 0;
        if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_ING)) {
            // 결재중
            result = checkResultMapper.updateCheckResultForAppr(safCheckScheduleNo, apprRqstNo, "");
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            // 결재완료
            imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_CHECK, safCheckScheduleNo, "");
            result = checkResultMapper.updateCheckResultForAppr(safCheckScheduleNo, apprRqstNo, ConstVal.SAF_CHK_STEP_IMPROVED_CD); // 안전점검
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
            // 반려는 처리할게 없다..
            result = 1;
        } else {
            checkResultMapper.updateCheckResultForAppr(safCheckScheduleNo, apprRqstNo, "");
        }
        return result;
    }

    /**
     * 안전점검결과 완료.
     *
     * @param checkSchedule
     * @return
     * @throws Exception
     */
    @Transactional
    public int completeSafetyCheckResult(CheckSchedule checkSchedule) throws Exception {
        int resultNo = 0;

        // update
        if (checkSchedule.getSafCheckScheduleNo() > 0) {
            int requestImprovementCount = imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_CHECK, checkSchedule.getSafCheckScheduleNo(), checkSchedule.getUpdateUserId());
            if (requestImprovementCount > 0) {
                checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_CHECK_CD);
            } else {
                checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_IMPROVED_CD);
            }

            if (checkSchedule.getSafCheckScheduleNo() > 0) {
                // 안전점검일정 결과 업데이트
                CheckSchedule param = new CheckSchedule();
                param.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                param.setCheckYmd(checkSchedule.getCheckYmd());
                param.setCheckResult(checkSchedule.getCheckResult());
                param.setCheckResultCd(checkSchedule.getCheckResultCd());
                param.setCheckStepCd(checkSchedule.getCheckStepCd());
                param.setCheckTitle(checkSchedule.getCheckTitle());
                param.setUpdateUserId(checkSchedule.getUpdateUserId());

                resultNo = checkResultMapper.updateCheckSchedule(param);

                if (resultNo <= 0) {
                    return 0;
                } else {
                    if (CollectionUtils.isNotEmpty(checkSchedule.getCheckInspectors())) {
                        // 안전점검_점검자 삭제
                        checkResultMapper.deleteCheckInspector(checkSchedule.getSafCheckScheduleNo());
                        // 안전점검_점검자 등록
                        for (CheckInspector checkInspector : checkSchedule.getCheckInspectors()) {
                            checkInspector.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                            checkInspector.setSafCheckNo(checkSchedule.getSafCheckNo());
                            resultNo += checkResultMapper.createCheckInspector(checkInspector);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(checkSchedule.getCheckItemResults())) {
                        // 안전점검 항목별점검결과 삭제
                        checkResultMapper.deleteCheckItemResult(checkSchedule.getSafCheckScheduleNo(), 0);
                        // 안전점검 항목별점검결과 등록
                        for (CheckItemResult checkItemResult : checkSchedule.getCheckItemResults()) {
                            checkItemResult.setSafCheckNo(checkSchedule.getSafCheckNo());
                            checkItemResult.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo());
                            checkItemResult.setCreateUserId(checkSchedule.getUpdateUserId());
                            resultNo += checkResultMapper.createCheckItemResult(checkItemResult);
                        }
                    }
                }
            } else {
                return 0;
            }
        }
        return resultNo;
    }

    /**
     * 안전점검 무계획결과 등록
     *
     * @param checkResult
     *            안전점검결과
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public CheckSchedule createCheckResult(CheckSchedule checkSchedule) throws Exception {
        int resultNo = 0;

        if (checkSchedule != null) {
            // 안전점검 마스터 등록
            CheckMaster checkMaster = new CheckMaster();
            checkMaster.setPlantCd(checkSchedule.getPlantCd()); // 사업장코드
            checkMaster.setSafCheckKindNo(checkSchedule.getSafCheckKindNo()); // 안전점검종류번호
            checkMaster.setDeptCd(checkSchedule.getDeptCd()); // 주관부서
            checkMaster.setNoSchYn("Y"); // 무일정여부
            checkMaster.setCreateUserId(checkSchedule.getCreateUserId()); // 등록자
            checkMaster.setCheckMasterTitle(checkSchedule.getCheckTitle());
            int result = checkResultMapper.createCheckMaster(checkMaster);

            if (result > 0) {
                // 안전점검일정 등록
                checkSchedule.setSafCheckNo(checkMaster.getSafCheckNo()); // 안전점검일련번호
                checkSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_RESULT_CD); // 점검진행상태(결과)
                checkSchedule.setNoPlanYn("Y"); // 무계획여부
                result = checkResultMapper.createCheckSchedule(checkSchedule);
                resultNo = checkSchedule.getSafCheckScheduleNo();

                if (result > 0) {
                    if (CollectionUtils.isNotEmpty(checkSchedule.getCheckInspectors())) {
                        // 안전점검_점검자 등록
                        for (CheckInspector checkInspector : checkSchedule.getCheckInspectors()) {
                            checkInspector.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo()); // 안전점검일련번호
                            checkInspector.setSafCheckNo(checkSchedule.getSafCheckNo()); // 안전점검일정일련번호
                            checkResultMapper.createCheckInspector(checkInspector);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(checkSchedule.getCheckItemResults())) {
                        // 안전점검 항목별점검결과 등록
                        for (CheckItemResult checkItemResult : checkSchedule.getCheckItemResults()) {
                            checkItemResult.setSafCheckNo(checkSchedule.getSafCheckNo()); // 안전점검일정일련번호
                            checkItemResult.setSafCheckScheduleNo(checkSchedule.getSafCheckScheduleNo()); // 안전점검일련번호
                            checkItemResult.setCreateUserId(checkSchedule.getUpdateUserId());
                            checkResultMapper.createCheckItemResult(checkItemResult);
                        }
                    }
                }
            }
        }
        return checkSchedule;
    }

    /**
     * 안전점검결과 삭제
     *
     * @param safCheckNo
     *            안전점검 마스터 일련번호
     * @param safCheckScheduleNo
     *            안전점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteCheckResult(int safCheckNo, int safCheckScheduleNo) throws Exception {
        int resultNo = 0;
        if (safCheckNo > 0) {
            // 개선 건 삭제
            resultNo += checkResultMapper.deleteImprAct(safCheckScheduleNo);
            // 안전점검 항목별점검결과 삭제
            resultNo += checkResultMapper.deleteCheckItemResult(safCheckScheduleNo, 0);
            // 안전점검 점검자 삭제
            resultNo += checkResultMapper.deleteCheckInspector(safCheckScheduleNo);
            // 안전점검일정 삭제
            resultNo += checkResultMapper.deleteCheckSchedule(safCheckNo, safCheckScheduleNo);
        }
        return resultNo;
    }

    public List<HashMap<String, Object>> getCheckResultStatus(String plantCd, String year) throws Exception {
        return checkResultMapper.getCheckResultStatus(plantCd, year);
    }

    public List<HashMap<String, Object>> getCheckResultStatusSub(String plantCd, String year, int safCheckKindNo, String deptCd, String deptSubYn, String tgtDeptCd, String tgtDeptSubYn) throws Exception {
        return checkResultMapper.getCheckResultStatusSub(plantCd, year, safCheckKindNo, deptCd, deptSubYn, tgtDeptCd, tgtDeptSubYn);
    }

}
