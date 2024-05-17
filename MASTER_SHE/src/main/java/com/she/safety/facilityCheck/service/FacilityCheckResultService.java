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
package com.she.safety.facilityCheck.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.baseInfo.facility.mapper.FacilityMapper;
import com.she.baseInfo.model.FacilityMst;
import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.safety.change.service.ChangeService;
import com.she.safety.facilityCheck.mapper.FacilityCheckResultMapper;
import com.she.safety.model.ChangeRefWork;
import com.she.safety.model.FacilChkItemResult;
import com.she.safety.model.FacilChkMaster;
import com.she.safety.model.FacilChkResult;
import com.she.safety.model.FacilityCheckInspector;
import com.she.safety.model.FacilityCheckItemResult;
import com.she.safety.model.FacilityCheckSchedule;
import com.she.utils.ConstVal;

/**
 * 설비점검결과 기능정의
 */
@Service
public class FacilityCheckResultService {
    @Autowired
    private FacilityCheckResultMapper facilityCheckResultMapper;

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private ImprService imprService;
    @Autowired
    private ChangeService changeService;

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
    public List<FacilityCheckSchedule> getFacilityCheckResults(String startYmd, String endYmd, String plantCd, String deptCd, String safCheckTypeCd, String safFacilityTypeCd, String facilityNm, String checkStepCd, int safFacilityCheckNo, DefaultParam defaultParam) throws Exception {
        return facilityCheckResultMapper.getFacilityCheckResults(startYmd, endYmd, plantCd, deptCd, safCheckTypeCd, safFacilityTypeCd, facilityNm, checkStepCd, safFacilityCheckNo, defaultParam);
    }

    /**
     * 설비점검계획 상세조회
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @return 설비점검계획
     * @throws Exception
     */
    public FacilityCheckSchedule getFacilityCheckResult(int safFacilityCheckScheduleNo, DefaultParam defaultParam) throws Exception {
        FacilityCheckSchedule facilityCheckSchedule = facilityCheckResultMapper.getFacilityCheckResult(safFacilityCheckScheduleNo, defaultParam);
        facilityCheckSchedule.setFacilityCheckInspectors(facilityCheckResultMapper.getFacilityCheckInspectors(safFacilityCheckScheduleNo, "", defaultParam));
        return facilityCheckSchedule;
    }

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
    public List<FacilityCheckSchedule> getrFacilityCheckResults(String startYmd, String endYmd, String plantCd, String deptCd, String safCheckTypeCd, String safFacilityTypeCd, String facilityNm, String checkStepCd, int safFacilityCheckNo, DefaultParam defaultParam) throws Exception {
        return facilityCheckResultMapper.getrFacilityCheckResults(startYmd, endYmd, plantCd, deptCd, safCheckTypeCd, safFacilityTypeCd, facilityNm, checkStepCd, safFacilityCheckNo, defaultParam);
    }

    /**
     * 설비점검결과 상세조회
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정일련번호
     * @return 설비점검결과
     * @throws Exception
     */
    public FacilityCheckSchedule getrFacilityCheckResult(int safFacilityCheckScheduleNo, DefaultParam defaultParam) throws Exception {
        FacilityCheckSchedule facilityCheckSchedule = facilityCheckResultMapper.getrFacilityCheckResult(safFacilityCheckScheduleNo, defaultParam);
        facilityCheckSchedule.setFacilityCheckInspectors(facilityCheckResultMapper.getFacilityCheckInspectors(safFacilityCheckScheduleNo, "", defaultParam));
        return facilityCheckSchedule;
    }

    /**
     * 설비점검계획 수정
     *
     * @param facilityCheckSchedule
     *            설비점검계획
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateFacilityCheckResult(FacilityCheckSchedule facilityCheckSchedule, DefaultParam defaultParam) throws Exception {
        int resultNo = 0;

        // update
        if (facilityCheckSchedule.getSafFacilityCheckScheduleNo() > 0) {
            // 개선사항이 있더라도 개선은 개선대로 점검은 점검대로 흘러가게 한다.
            // if
            // (ConstVal.SAF_CHECK_STEP_IMPROVED_CD.equals(facilityCheckSchedule.getCheckStepCd()))
            // {
            // int requestImprovementCount =
            // imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_FACILITY,
            // facilityCheckSchedule.getSafFacilityCheckScheduleNo(),
            // facilityCheckSchedule.getUpdateUserId());
            //
            // if (requestImprovementCount > 0) {
            // facilityCheckSchedule.setCheckStepCd(ConstVal.SAF_CHECK_STEP_CHECK_CD);
            // }
            // }

            // 설비점검결과 수정
            resultNo += facilityCheckResultMapper.updateFacilityCheckResult(facilityCheckSchedule);

            // 설비점검결과를 등록하면서 점검일이 들어가게 될 시에 설비마스터에 최근 설비점검 법정/자체 점검일에 등록된 점검일을
            // 업데이트 한다.
            if (facilityCheckSchedule.getSafFacilityCheckYmd() != null && !facilityCheckSchedule.getSafFacilityCheckYmd().equals("")) {
                FacilityMst facilityMst = this.facilityMapper.getFacilityMst(facilityCheckSchedule.getSafFacilityCd(), defaultParam);
                if (facilityCheckSchedule.getSafCheckTypeCd().equals("LAWCK")) {
                    // 법정점검일 경우에는 최근법정점검일에 입력된 점검일을 넣는다.
                    facilityMst.setLawChkYmd(facilityCheckSchedule.getSafFacilityCheckYmd());
                } else if (facilityCheckSchedule.getSafCheckTypeCd().equals("SELFC")) {
                    // 자체점검일 경우에는 최근자체점검일에 입력된 점검일을 넣는다.
                    facilityMst.setSelfChkYmd(facilityCheckSchedule.getSafFacilityCheckYmd());
                } else if (facilityCheckSchedule.getSafCheckTypeCd().equals("INSBO")) {
                    // 자체점검일 경우에는 최근자체점검일에 입력된 점검일을 넣는다.
                    facilityMst.setInspChkYmd(facilityCheckSchedule.getSafFacilityCheckYmd());
                }
                // 설비마스터 수정
                this.facilityMapper.updateFacilityMst(facilityMst);
            }

            if (facilityCheckSchedule.getFacilityCheckInspectors() != null) {
                // 설비점검_대상자 삭제
                facilityCheckResultMapper.deleteFacilityCheckInspector(facilityCheckSchedule.getSafFacilityCheckScheduleNo());

                if (facilityCheckSchedule.getFacilityCheckInspectors().size() > 0) {
                    // 설비점검_대상자 등록
                    for (FacilityCheckInspector facilityCheckInspector : facilityCheckSchedule.getFacilityCheckInspectors()) {
                        facilityCheckInspector.setSafFacilityCheckNo(facilityCheckSchedule.getSafFacilityCheckNo());
                        facilityCheckInspector.setSafFacilityCheckScheduleNo(facilityCheckSchedule.getSafFacilityCheckScheduleNo());
                        resultNo += facilityCheckResultMapper.createFacilityCheckInspector(facilityCheckInspector);
                    }
                }
            }

            if (facilityCheckSchedule.getFacilityCheckItemResults() != null) {
                if (facilityCheckSchedule.getFacilityCheckItemResults().size() > 0) {
                    // 설비점검_항목별점검결과 등록/수정
                    for (FacilityCheckItemResult facilityCheckItemResult : facilityCheckSchedule.getFacilityCheckItemResults()) {
                        if (facilityCheckItemResult.getSafFacilityCheckScheduleNo() > 0) {
                            // 수정
                            facilityCheckItemResult.setUpdateUserId(facilityCheckSchedule.getUpdateUserId());
                            resultNo += facilityCheckResultMapper.updateFacilityCheckItemResult(facilityCheckItemResult);
                        } else {
                            // 등록
                            facilityCheckItemResult.setCreateUserId(facilityCheckSchedule.getCreateUserId());
                            facilityCheckItemResult.setSafFacilityCheckNo(facilityCheckSchedule.getSafFacilityCheckNo());
                            facilityCheckItemResult.setSafFacilityCheckScheduleNo(facilityCheckSchedule.getSafFacilityCheckScheduleNo());
                            resultNo += facilityCheckResultMapper.createFacilityCheckItemResult(facilityCheckItemResult);
                        }
                    }
                }
            }
        }

        return resultNo;
    }

    /**
     * 설비점검마스터에 따른 일정 진행단계 변경
     *
     * @param safFacilityCheckNo
     *            설비점검마스터번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessFacilityCheckMaster(int safFacilityCheckNo, String bizApprStepCd, int apprRqstNo, DefaultParam defaultParam) throws Exception {
        int resultNo = 0;
        String checkStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            List<FacilityCheckSchedule> facilityCheckSchedules = facilityCheckResultMapper.getFacilityCheckResults("", "", "", "", "", "", "", "", safFacilityCheckNo, defaultParam);
            if (facilityCheckSchedules != null && facilityCheckSchedules.size() > 0) {
                for (FacilityCheckSchedule facilityCheckSchedule : facilityCheckSchedules) {
                    if (facilityCheckSchedule.getCheckStepCd().equals(ConstVal.SAF_CHK_STEP_SCHEDULE_CD)) {
                        // 일정상태의 경우
                        checkStepCd = ConstVal.SAF_CHK_STEP_PLAN_CD;
                    } else if (facilityCheckSchedule.getCheckStepCd().equals(ConstVal.SAF_CHK_STEP_PLAN_CD)) {
                        // 계획상태의 경우
                        checkStepCd = ConstVal.SAF_CHK_STEP_RESULT_CD;
                    } else if (facilityCheckSchedule.getCheckStepCd().equals(ConstVal.SAF_CHK_STEP_RESULT_CD)) {
                        // 계획 완료의 경우
                        checkStepCd = ConstVal.SAF_CHK_STEP_IMPROVED_CD;
                    }

                    resultNo += facilityCheckResultMapper.completeFacilityCheckMasterSchedule(facilityCheckSchedule.getSafFacilityCheckScheduleNo(), checkStepCd);
                }
            }
        }
        // 설비점검마스터에 따른 일정 진행단계 변경
        resultNo += facilityCheckResultMapper.completeFacilityCheckMaster(safFacilityCheckNo, apprRqstNo);
        return resultNo;
    }

    /**
     * 설비점검일정 진행단계 변경
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessFacilityCheckSchedule(int safFacilityCheckScheduleNo, String bizApprStepCd, int apprRqstNo, DefaultParam defaultParam) throws Exception {
        int resultNo = 0;
        String checkStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            FacilityCheckSchedule facilityCheckSchedule = facilityCheckResultMapper.getFacilityCheckResult(safFacilityCheckScheduleNo, defaultParam);
            if (facilityCheckSchedule != null) {
                if (facilityCheckSchedule.getCheckStepCd().equals(ConstVal.SAF_CHK_STEP_PLAN_CD)) {
                    // 계획상태의 경우
                    checkStepCd = ConstVal.SAF_CHK_STEP_RESULT_CD;
                }
            }
        }

        // 설비점검마스터에 따른 일정 진행단계 변경
        resultNo += facilityCheckResultMapper.completeFacilityCheckSchedule(safFacilityCheckScheduleNo, apprRqstNo, checkStepCd);
        return resultNo;
    }

    /**
     * 설비점검일정결과 진행단계 변경
     *
     * @param safFacilityCheckScheduleNo
     *            설비점검일정 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessFacilityCheckScheduleResult(int safFacilityCheckScheduleNo, String bizApprStepCd, int apprRqstNo, DefaultParam defaultParam) throws Exception {
        int resultNo = 0;
        String checkStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            FacilityCheckSchedule facilityCheckSchedule = facilityCheckResultMapper.getFacilityCheckResult(safFacilityCheckScheduleNo, defaultParam);
            if (facilityCheckSchedule != null) {
                if (facilityCheckSchedule.getCheckStepCd().equals(ConstVal.SAF_CHK_STEP_RESULT_CD)) {
                    // 계획완료상태의 경우
                    checkStepCd = ConstVal.SAF_CHK_STEP_IMPROVED_CD;
                    // 상세의 진행단계가 완료로 바뀔 시에 해당 상세 엮여 있는 개선조치사항건에 대해서 접수 상태로 변경
                    imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_FACILITY, safFacilityCheckScheduleNo, "");
                }
            }
        }

        // 설비점검마스터에 따른 일정결과 진행단계 변경
        resultNo += facilityCheckResultMapper.completeFacilityCheckScheduleResult(safFacilityCheckScheduleNo, apprRqstNo, checkStepCd);
        return resultNo;
    }

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
    public List<FacilChkMaster> getFacilChkPlans(String plantCd, String safCheckTypeCd, String checkStepCd, String deptCd, String deptSubYn, String startYmd, String endYmd, String keyword, String tDeptCd, String tDeptSubYn, String pDeptCd, String pDeptSubYn, String chkEndYn, String facilityNm, DefaultParam defaultParam) throws Exception {
        return facilityCheckResultMapper.getFacilChkPlans(plantCd, safCheckTypeCd, checkStepCd, deptCd, deptSubYn, startYmd, endYmd, keyword, tDeptCd, tDeptSubYn, pDeptCd, pDeptSubYn, chkEndYn, facilityNm, defaultParam);
    }

    /**
     * 설비점검계획 상세 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검 계획 상세
     */
    public FacilChkMaster getFacilChkPlan(int safFacilChkNo, String tDeptCd, String pDeptCd, DefaultParam defaultParam) throws Exception {
        FacilChkMaster result = facilityCheckResultMapper.getFacilChkPlan(safFacilChkNo, defaultParam);
        String safFacilityCd = null; // 점검설비코드 유무와 상관이 없기 때문에 ''
        result.setFacilChkResults(facilityCheckResultMapper.getFacilChkResults(safFacilChkNo, tDeptCd, pDeptCd, safFacilityCd));

        return result;
    }

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 등록 행 수
     */
    @Transactional
    public int createFacilChkPlan(FacilChkMaster facilChkMaster) throws Exception {
        // 무계획 점검결과 등록이 아닌경우
        if (facilChkMaster.getNoPlanYn() == null || !facilChkMaster.getNoPlanYn().equals("Y")) {
            facilChkMaster.setChkStepCd(ConstVal.SAF_CHK_STEP_PLAN_CD);
        }

        // 설비점검 계획 등록
        int result = facilityCheckResultMapper.createFacilChkPlan(facilChkMaster);

        // 설비점검 결과 등록
        List<FacilChkResult> facilChkResults = facilChkMaster.getFacilChkResults();

        for (int i = 0; i < facilChkResults.size(); i++) {
            facilChkResults.get(i).setSafFacilChkNo(facilChkMaster.getSafFacilChkNo());
            facilChkResults.get(i).setChkComYn(null);
            facilChkResults.get(i).setChkEndYn("N");
            facilityCheckResultMapper.createFacilChkResult(facilChkResults.get(i));
            result++;
        }

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(facilChkMaster.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(facilChkMaster.getSafFacilChkNo()));
        changeRefWork.setRefTableNm("saf_facil_chk_plan");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_5);
        changeRefWork.setCreateUserId(facilChkMaster.getCreateUserId());
        changeService.taskChange(changeRefWork);

        return facilChkMaster.getSafFacilChkNo();
    }

    /**
     * 설비점검 계획 수정
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 수정 행 수
     */
    @Transactional
    public int updateFacilChkPlan(FacilChkMaster facilChkMaster) throws Exception {
        // 설비점검 계획 수정
        int result = facilityCheckResultMapper.updateFacilChkPlan(facilChkMaster);

        // 설비점검 결과 수정
        List<FacilChkResult> facilChkResults = facilChkMaster.getFacilChkResults();

        if (facilChkResults != null) {
            if (facilChkResults.size() > 0) {
                for (int i = 0; i < facilChkResults.size(); i++) {
                    // 설비점검 결과 삭제
                    facilityCheckResultMapper.deleteFacilChkResults(facilChkMaster.getSafFacilChkNo(), facilChkResults.get(i).getSafFacilityCd());
                    facilChkResults.get(i).setSafFacilChkNo(facilChkMaster.getSafFacilChkNo());
                    //
                    if (facilChkResults.get(i).getChkEndYn() == null) {
                        facilChkResults.get(i).setChkEndYn("N");
                    }
                    // 점검확정여부가 null이거나 미확정일 경우
                    if (facilChkResults.get(i).getChkComYn() == null || facilChkResults.get(i).getChkComYn().equals("미확정")) {
                        facilChkResults.get(i).setChkComYn("N");
                    } else {
                        facilChkResults.get(i).setChkComYn("Y");
                    }
                    facilityCheckResultMapper.createFacilChkResult(facilChkResults.get(i));
                    result++;
                }
            }
        }

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(facilChkMaster.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(facilChkMaster.getSafFacilChkNo()));
        changeRefWork.setRefTableNm("saf_facil_chk_plan");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_5);
        changeRefWork.setCreateUserId(facilChkMaster.getCreateUserId());
        changeService.taskChange(changeRefWork);

        return facilChkMaster.getSafFacilChkNo();
    }

    /**
     * 설비점검계획 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkPlan(int safFacilChkNo) throws Exception {
        int resultNo = 0;
        resultNo += facilityCheckResultMapper.deleteFacilChkResult(safFacilChkNo);
        resultNo += facilityCheckResultMapper.deleteFacilChkPlan(safFacilChkNo);
        return resultNo;
    }

    /**
     * 설비점검결과 목록 조회
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
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkMaster> getRFacilChkPlans(String plantCd, String safCheckTypeCd, String checkStepCd, String deptCd, String deptSubYn, String startYmd, String endYmd, String keyword, String tDeptCd, String tDeptSubYn, String pDeptCd, String pDeptSubYn, String chkEndYn, String facilityNm, DefaultParam defaultParam) throws Exception {
        return facilityCheckResultMapper.getRFacilChkPlans(plantCd, safCheckTypeCd, checkStepCd, deptCd, deptSubYn, startYmd, endYmd, keyword, tDeptCd, tDeptSubYn, pDeptCd, pDeptSubYn, chkEndYn, facilityNm, defaultParam);
    }

    /**
     * 설비점검결과 목록 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkResult> getFacilChkResults(int safFacilChkNo, String tDeptCd, String pDeptCd, String safFacilityCd) throws Exception {
        return facilityCheckResultMapper.getFacilChkResults(safFacilChkNo, tDeptCd, pDeptCd, safFacilityCd);
    }

    /**
     * 설비점검결과 상세 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param safFacilityCd
     *            설비코드
     * @return 설비점검결과 상세 정보
     */
    public FacilChkResult getFacilChkResult(int safFacilChkNo, String safFacilityCd, String plantCd, String safCheckTypeCd, DefaultParam defaultParam) throws Exception {
        FacilChkResult result = facilityCheckResultMapper.getFacilChkResult(safFacilChkNo, safFacilityCd);

        result.setFacilChkItems(this.setFacilChkItems(safFacilChkNo, safFacilityCd, plantCd, safCheckTypeCd, defaultParam));
        return result;
    }

    public List<FacilChkItemResult> setFacilChkItems(int safFacilChkNo, String safFacilityCd, String plantCd, String safCheckTypeCd, DefaultParam defaultParam) throws Exception {
        FacilityMst facilityMst = facilityMapper.getFacilityMst(safFacilityCd, defaultParam);
        List<FacilChkItemResult> saveData = facilityCheckResultMapper.getFacilChkItems(safFacilChkNo, safFacilityCd);
        // 저장된 데이터 값이 있는 경우
        if (saveData != null && saveData.size() > 0) {
            // 저장된 데이터값에 설비유형의 값이 현재 설비의 설비유형값과 동일한지 체크
            for (FacilChkItemResult facilChkItemResult : saveData) {
                // 동일한 값이 들어 있다면 저장된 데이터 값을 그대로 가져가 사용
                if (facilityMst.getSafFacilityTypeCd().equals(facilChkItemResult.getSafFacilityTypeCd())) {
                    return saveData;
                }
            }
            // 동일한 값이 없어 반환 되지 않은 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 저장된 값에 붙인다.
            saveData.addAll(facilityCheckResultMapper.getNewFacilChkItems(facilityMst.getSafFacilityTypeCd(), plantCd, safCheckTypeCd));
        } else {
            // 저장된 값이 없는 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 가지고 온다. (사용하는 것들만)
            saveData = facilityCheckResultMapper.getNewFacilChkItems(facilityMst.getSafFacilityTypeCd(), plantCd, safCheckTypeCd);
        }
        return saveData;
    }

    /**
     * 설비별 점검결과 수정
     *
     * @param facilChkResult
     *            설비점검결과
     * @return 수정 행 수
     */
    @Transactional
    public int updateFacilChkResult(FacilChkResult facilChkResult) throws Exception {
        // 설비점검결과 수정
        int result = facilityCheckResultMapper.updateFacilChkResult(facilChkResult);

        // 점검항목별 결과
        List<FacilChkItemResult> facilChkItems = facilChkResult.getFacilChkItems();

        if (facilChkItems != null) {
            if (facilChkItems.size() > 0) {
                for (int i = 0; i < facilChkItems.size(); i++) {
                    facilChkItems.get(i).setSafFacilChkNo(facilChkResult.getSafFacilChkNo());
                    facilChkItems.get(i).setSafFacilityCd(facilChkResult.getSafFacilityCd());
                    result += facilityCheckResultMapper.updateFacilChkItem(facilChkItems.get(i));
                }
            }
        }

        return result;
    }

    /**
     * 설비별 점검결과 전체완료
     *
     * @param facilChkMaster
     *            설비점검계획
     * @return 수정 행 수
     */
    public int updateFacilChkResults(FacilChkMaster facilChkMaster) throws Exception {

        int result = 0;

        // 설비점검 결과 수정
        List<FacilChkResult> facilChkResults = facilChkMaster.getFacilChkResults();

        if (facilChkResults != null) {
            if (facilChkResults.size() > 0) {

                for (int i = 0; i < facilChkResults.size(); i++) {
                    // 설비점검 결과 삭제
                    facilityCheckResultMapper.deleteFacilChkResults(facilChkMaster.getSafFacilChkNo(), facilChkResults.get(i).getSafFacilityCd());
                    facilChkResults.get(i).setChkComYn("Y");
                    facilChkResults.get(i).setSafFacilChkNo(facilChkMaster.getSafFacilChkNo());
                    facilityCheckResultMapper.createFacilChkResult(facilChkResults.get(i));
                    result = 1;
                }
            }
        }

        // 설비점검 결과 중 점검 확정되지 않은 설비점검 수
        int comCount = facilityCheckResultMapper.getFacilChkComStatus(facilChkMaster.getSafFacilChkNo());

        if (comCount == 0) {

            // 상세의 진행단계가 완료로 바뀔 시에 해당 상세 엮여 있는 개선조치사항건에 대해서 접수 상태로 변경
            imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_FACILITY, facilChkMaster.getSafFacilChkNo(), "");

            // 계획을 점검완료로 변경
            facilityCheckResultMapper.updateFacilChkResults(facilChkMaster);
            result = 1;
        }

        return result;
    }

    /**
     * 설비점검계획 진행단계 변경
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessFacilChkPlan(int safFacilChkNo, String bizApprStepCd, int apprRqstNo, DefaultParam defaultParam) throws Exception {
        int resultNo = 0;
        FacilChkMaster facilChkPlan = facilityCheckResultMapper.getFacilChkPlan(safFacilChkNo, defaultParam);
        String chkStepCd = facilChkPlan.getChkStepCd();

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            if (facilChkPlan != null) {
                if (facilChkPlan.getChkStepCd().equals(ConstVal.SAF_CHK_STEP_PLAN_CD)) {
                    // 계획상태의 경우
                    chkStepCd = ConstVal.SAF_CHK_STEP_RESULT_CD;
                }
            }
        }

        // 설비점검마스터에 따른 일정 진행단계 변경
        resultNo += facilityCheckResultMapper.completeFacilChkPlan(safFacilChkNo, apprRqstNo, chkStepCd);
        return resultNo;
    }

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
    public List<HashMap<String, Object>> getFacilChkResultStatus(String plantCd, String year) throws Exception {
        return facilityCheckResultMapper.getFacilChkResultStatus(plantCd, year);
    }

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
    public List<HashMap<String, Object>> getFacilChkResultStatusSub(String plantCd, String year, String safCheckTypeCd, String safFacilityTypeCd, String deptCd, String deptSubYn, DefaultParam defaultParam) throws Exception {
        return facilityCheckResultMapper.getFacilChkResultStatusSub(plantCd, year, safCheckTypeCd, safFacilityTypeCd, deptCd, deptSubYn, defaultParam);
    }

    /**
     * 설비점검결과 목록 조회
     *
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkResult> getFacilChkResultTable(String plantCd, String startDate, String endDate, String chkEndYn, String deptCd, String safCheckTypeCd, String safFacilityTypeCd, String chkStepCd) throws Exception {
        return facilityCheckResultMapper.getFacilChkResultTable(plantCd, startDate, endDate, chkEndYn, deptCd, safCheckTypeCd, safFacilityTypeCd, chkStepCd);
    }
}
