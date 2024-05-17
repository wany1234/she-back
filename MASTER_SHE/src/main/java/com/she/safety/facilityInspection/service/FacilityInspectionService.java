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
package com.she.safety.facilityInspection.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.impr.service.ImprService;
import com.she.safety.facilityInspection.mapper.FacilityInspectionMapper;
import com.she.safety.model.FacilityInspectionInspector;
import com.she.safety.model.FacilityInspectionItemResult;
import com.she.safety.model.FacilityInspectionMaster;
import com.she.safety.model.FacilityInspectionSchedule;
import com.she.utils.ConstVal;

/**
 * 시설 점검 기능정의
 */
@Service
public class FacilityInspectionService {
    @Autowired
    private FacilityInspectionMapper facilityInspectionMapper;

    @Autowired
    private ImprService imprService;

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

    public List<FacilityInspectionMaster> getFacilityInspectionSchedules(String startYmd, String endYmd, String plantCd, String deptCd, String tgtDeptCd, String pfmDeptCd, String deptSubYn, String tgtDeptSubYn, String pfmDeptSubYn, String comFacilityCheckCd, String comFacilityTypeCd, String facilityNm, String stepStatus) throws Exception {
        return facilityInspectionMapper.getFacilityInspectionSchedules(startYmd, endYmd, plantCd, deptCd, tgtDeptCd, pfmDeptCd, deptSubYn, tgtDeptSubYn, pfmDeptSubYn, comFacilityCheckCd, comFacilityTypeCd, facilityNm, stepStatus);
    }

    /**
     * 시설 유형 셀렉박스 리스트 조회
     * 
     * @return 시설 유형 리스트
     * @throws Exception
     */
    public List<Map<String, String>> getComFacilityTypes(String facilityCd, String facilityNm, String useYn) throws Exception {
        return facilityInspectionMapper.getComFacilityTypes(facilityCd, facilityNm, useYn);
    }

    /**
     * 시설점검마스터 및 일정 상세조회
     * 
     * @param safFacilityCheckNo
     *            설비점검일련번호
     * @return 설비점검마스터 및 일정
     * @throws Exception
     */
    public FacilityInspectionMaster getFacilityInspectionSchedule(int comFacilityCheckNo) throws Exception {
        // 시설점검 - 마스터
        FacilityInspectionMaster facilityCheckMaster = facilityInspectionMapper.getFacilityInspectionMaster(comFacilityCheckNo);
        // 시설점검 - 일정
        facilityCheckMaster.setFacilityInspectionSchedules(facilityInspectionMapper.getFacilityInspectionSchedule(comFacilityCheckNo, facilityCheckMaster.getPlantCd()));
        return facilityCheckMaster;
    }

    /**
     * 시설점검일정 신규등록
     * 
     * @param facilityInspectionMaster
     *            시설점검일정
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createFacilityInspectionSchedule(FacilityInspectionMaster facilityInspectionMaster) throws Exception {
        int resultNo = 0;

        // 시설점검 마스터 신규등록
        resultNo = facilityInspectionMapper.createFacilityInspectionMaster(facilityInspectionMaster);
        // 시설점검일정 신규등록
        if (facilityInspectionMaster.getFacilityInspectionSchedules() != null && facilityInspectionMaster.getFacilityInspectionSchedules().size() > 0) {
            for (FacilityInspectionSchedule facilityInspectionSchedule : facilityInspectionMaster.getFacilityInspectionSchedules()) {
                facilityInspectionSchedule.setComFacilityCheckNo(facilityInspectionMaster.getComFacilityCheckNo());
                facilityInspectionSchedule.setCreateUserId(facilityInspectionMaster.getCreateUserId());
                facilityInspectionSchedule.setCheckStepCd(ConstVal.SAF_CHECK_STEP_SCHEDULE_CD);
                resultNo += facilityInspectionMapper.createFacilityCheckSchedule(facilityInspectionSchedule);
            }
        }
        return facilityInspectionMaster.getComFacilityCheckNo();
    }

    /**
     * 시설점검일정 수정
     * 
     * @param facilityInspectionMaster
     *            시설점검일정
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateFacilityInspectionSchedule(FacilityInspectionMaster facilityInspectionMaster) throws Exception {
        int resultNo = 0;
        // 시설점검 마스터 수정
        resultNo = facilityInspectionMapper.updateFacilityInspectionMaster(facilityInspectionMaster);
        // 시설점검 일정
        if (facilityInspectionMaster.getFacilityInspectionSchedules() != null && facilityInspectionMaster.getFacilityInspectionSchedules().size() > 0) {
            for (FacilityInspectionSchedule facilityInspectionSchedule : facilityInspectionMaster.getFacilityInspectionSchedules()) {

                if (facilityInspectionSchedule.getComFacilityCheckScheduleNo() > 0) {
                    // 시설점검일정 수정
                    facilityInspectionSchedule.setUpdateUserId(facilityInspectionMaster.getUpdateUserId());
                    resultNo += facilityInspectionMapper.updateFacilityCheckSchedule(facilityInspectionSchedule);
                } else { // 시설점검일정 신규등록
                    facilityInspectionSchedule.setComFacilityCheckNo(facilityInspectionMaster.getComFacilityCheckNo());
                    facilityInspectionSchedule.setCreateUserId(facilityInspectionMaster.getCreateUserId());
                    facilityInspectionSchedule.setCheckStepCd(ConstVal.SAF_CHECK_STEP_SCHEDULE_CD);
                    resultNo += facilityInspectionMapper.createFacilityCheckSchedule(facilityInspectionSchedule);
                }

                // 제외 했던 데이터(시설 또는 점검주기)를 삭제 처리
                if (facilityInspectionMaster.getDeleteData() != null && facilityInspectionMaster.getDeleteData().length > 0) {
                    for (int comFacilityCheckScheduleNo : facilityInspectionMaster.getDeleteData()) {
                        resultNo += facilityInspectionMapper.deleteFacilityCheckSchedule(comFacilityCheckScheduleNo);
                    }
                }
            }
        }
        return facilityInspectionMaster.getComFacilityCheckNo();
    }

    /**
     * 시설점검마스터 삭제
     * 
     * @param comFacilityCheckNo
     *            시설점검마스터번호
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteFacilityCheckSchedule(int comFacilityCheckNo) throws Exception {
        int resultNo = 0;

        // 설비점검_일정 삭제
        resultNo += facilityInspectionMapper.deleteFacilityCheckSchedules(comFacilityCheckNo);
        // 설비점검_마스터 삭제
        resultNo += facilityInspectionMapper.deleteFacilityInspectionMaster(comFacilityCheckNo);

        return resultNo;
    }

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

    public List<FacilityInspectionSchedule> getFacilityInspectionPlans(String plantCd, String startYmd, String endYmd, String comFacilityCheckCd, String comFacilityTypeCd, String facilityNm, String checkStepCd, String deptCd, String tgtDeptCd, String pfmDeptCd, String deptSubYn, String tgtDeptSubYn, String pfmDeptSubYn, int comFacilityCheckNo)
            throws Exception {
        return facilityInspectionMapper.getFacilityInspectionPlans(plantCd, startYmd, endYmd, comFacilityCheckCd, comFacilityTypeCd, facilityNm, checkStepCd, deptCd, tgtDeptCd, pfmDeptCd, deptSubYn, tgtDeptSubYn, pfmDeptSubYn, comFacilityCheckNo);

    }

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
     * @param comFacilityCheckNo
     *            시설점검마스터번호
     * @return 시설점검일정 목록
     * @throws Exception
     */

    public List<FacilityInspectionSchedule> getFacilityInspectionResults(String plantCd, String startYmd, String endYmd, String comFacilityCheckCd, String comFacilityTypeCd, String facilityNm, String checkResultCd, String checkStepCd, String deptCd, String tgtDeptCd, String pfmDeptCd, String deptSubYn, String tgtDeptSubYn, String pfmDeptSubYn,
            int comFacilityCheckNo) throws Exception {
        return facilityInspectionMapper.getFacilityInspectionResults(plantCd, startYmd, endYmd, comFacilityCheckCd, comFacilityTypeCd, facilityNm, checkResultCd, checkStepCd, deptCd, tgtDeptCd, pfmDeptCd, deptSubYn, tgtDeptSubYn, pfmDeptSubYn, comFacilityCheckNo);

    }

    /**
     * 시설점검계획 상세조회
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 시설점검계획
     * @throws Exception
     */

    public FacilityInspectionSchedule getFacilityInspectionPlan(int comFacilityCheckScheduleNo) throws Exception {
        FacilityInspectionSchedule facilityInspectionSchedule = facilityInspectionMapper.getFacilityInspectionPlan(comFacilityCheckScheduleNo);
        if (facilityInspectionSchedule != null) {
            facilityInspectionSchedule.setFacilityInsInspectors(facilityInspectionMapper.getFacilityInsInspectors(comFacilityCheckScheduleNo, ""));
        }
        return facilityInspectionSchedule;
    }

    /**
     * 시설점검계획 수정
     * 
     * @param facilityInspectionSchedule
     *            시설점검일정
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateFacilityInspectionPlan(FacilityInspectionSchedule facilityInspectionSchedule) throws Exception {
        int resultNo = 0;
        // update
        if (facilityInspectionSchedule.getComFacilityCheckScheduleNo() > 0) {
            if (ConstVal.SAF_CHECK_STEP_IMPROVED_CD.equals(facilityInspectionSchedule.getCheckStepCd())) {
                int requestImprovementCount = imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_FACILITY_INSPECTION, facilityInspectionSchedule.getComFacilityCheckScheduleNo(), facilityInspectionSchedule.getUpdateUserId());

                if (requestImprovementCount > 0) {
                    facilityInspectionSchedule.setCheckStepCd(ConstVal.SAF_CHECK_STEP_CHECK_CD);
                }
            }
            // 시설점검일정 수정
            resultNo += facilityInspectionMapper.updateFacilityInspectionPlan(facilityInspectionSchedule);

            if (facilityInspectionSchedule.getFacilityInsInspectors() != null) {
                // 시설점검_대상자 삭제
                facilityInspectionMapper.deleteFacilityInsInspector(facilityInspectionSchedule.getComFacilityCheckScheduleNo());

                if (facilityInspectionSchedule.getFacilityInsInspectors().size() > 0) {
                    // 시설점검_대상자 등록
                    for (FacilityInspectionInspector facilityInspectionInspector : facilityInspectionSchedule.getFacilityInsInspectors()) {
                        facilityInspectionInspector.setComFacilityCheckNo(facilityInspectionSchedule.getComFacilityCheckNo());
                        facilityInspectionInspector.setComFacilityCheckScheduleNo(facilityInspectionSchedule.getComFacilityCheckScheduleNo());
                        resultNo += facilityInspectionMapper.createFacilityInsInspector(facilityInspectionInspector);
                    }
                }
            }
            if (facilityInspectionSchedule.getFacilityInsItemResults() != null) {
                if (facilityInspectionSchedule.getFacilityInsItemResults().size() > 0) {
                    // 설비점검_항목별점검결과 등록/수정
                    for (FacilityInspectionItemResult facilityInspectionItemResult : facilityInspectionSchedule.getFacilityInsItemResults()) {
                        if (facilityInspectionItemResult.getComFacilityCheckScheduleNo() > 0) {
                            // 수정
                            facilityInspectionItemResult.setUpdateUserId(facilityInspectionSchedule.getUpdateUserId());
                            resultNo += facilityInspectionMapper.updateFacilityInsItemResult(facilityInspectionItemResult);
                        } else {
                            // 등록
                            facilityInspectionItemResult.setCreateUserId(facilityInspectionSchedule.getCreateUserId());
                            facilityInspectionItemResult.setComFacilityCheckNo(facilityInspectionSchedule.getComFacilityCheckNo());
                            facilityInspectionItemResult.setComFacilityCheckScheduleNo(facilityInspectionSchedule.getComFacilityCheckScheduleNo());
                            resultNo += facilityInspectionMapper.createFacilityInsItemResult(facilityInspectionItemResult);
                        }
                    }
                }
            }
        }
        return facilityInspectionSchedule.getComFacilityCheckScheduleNo();
    }

    /**
     * 시설점검계획 무점검계획등록
     * 
     * @param facilityInspectionSchedule
     *            시설점검일정(계획)
     * @return 변경 행 수
     * @throws Exception
     */
    public int insertFacilityInspectionPlan(FacilityInspectionSchedule facilityInspectionSchedule) throws Exception {
        int resultNo = 0;
        FacilityInspectionMaster facilityInspectionMaster = new FacilityInspectionMaster();
        facilityInspectionMaster.setPlantCd(facilityInspectionSchedule.getPlantCd());
        facilityInspectionMaster.setDeptCd(facilityInspectionSchedule.getDeptCd());
        facilityInspectionMaster.setTgtDeptCd(facilityInspectionSchedule.getTgtDeptCd());
        facilityInspectionMaster.setPfmDeptCd(facilityInspectionSchedule.getPfmDeptCd());
        facilityInspectionMaster.setComFacilityTypeCd(facilityInspectionSchedule.getComFacilityTypeCd()); // 시설유형코드
        facilityInspectionMaster.setFacilityCd(facilityInspectionSchedule.getFacilityCd()); // 시설코드
        facilityInspectionMaster.setComFacilityCheckCd(facilityInspectionSchedule.getComFacilityCheckCd()); // 시설종류코드
        facilityInspectionMaster.setComFacilityCheckTitle(facilityInspectionSchedule.getFacilityCheckNm()); // 시설점검명
        facilityInspectionMaster.setComFacilityCheckPlanSymd(facilityInspectionSchedule.getFacilityCheckYmd() != null ? facilityInspectionSchedule.getFacilityCheckYmd() : facilityInspectionSchedule.getFacilityCheckSchYmd()); // 시설점검계획시작일
        facilityInspectionMaster.setComFacilityCheckPlanEymd(facilityInspectionSchedule.getFacilityCheckYmd() != null ? facilityInspectionSchedule.getFacilityCheckYmd() : facilityInspectionSchedule.getFacilityCheckSchYmd()); // 시설점검계획종료일
        facilityInspectionMaster.setComFacilityCheckPerd("FCC1M"); // 시설점검주기
        facilityInspectionMaster.setCreateUserId(facilityInspectionSchedule.getCreateUserId());
        facilityInspectionMaster.setCreateDt(facilityInspectionSchedule.getCreateDt());
        facilityInspectionMaster.setNoSchYn(facilityInspectionSchedule.getNoSchYn());
        resultNo += facilityInspectionMapper.createFacilityInspectionMaster(facilityInspectionMaster);

        facilityInspectionSchedule.setComFacilityCheckNo(facilityInspectionMaster.getComFacilityCheckNo());
        facilityInspectionSchedule.setFacilityCheckSchYmd(facilityInspectionSchedule.getFacilityCheckSchYmd() != null ? facilityInspectionSchedule.getFacilityCheckSchYmd() : facilityInspectionSchedule.getFacilityCheckYmd());
        resultNo += facilityInspectionMapper.createFacilityCheckSchedule(facilityInspectionSchedule);
        // update
        if (facilityInspectionSchedule.getComFacilityCheckScheduleNo() > 0) {
            if (ConstVal.SAF_CHECK_STEP_IMPROVED_CD.equals(facilityInspectionSchedule.getCheckStepCd())) {
                int requestImprovementCount = imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_FACILITY_INSPECTION, facilityInspectionSchedule.getComFacilityCheckScheduleNo(), facilityInspectionSchedule.getUpdateUserId());

                if (requestImprovementCount > 0) {
                    facilityInspectionSchedule.setCheckStepCd(ConstVal.SAF_CHECK_STEP_CHECK_CD);
                }
            }
            // 시설점검일정 등록

            if (facilityInspectionSchedule.getFacilityInsInspectors() != null) {
                // 시설점검_대상자 삭제
                facilityInspectionMapper.deleteFacilityInsInspector(facilityInspectionSchedule.getComFacilityCheckScheduleNo());

                if (facilityInspectionSchedule.getFacilityInsInspectors().size() > 0) {
                    // 시설점검_대상자 등록
                    for (FacilityInspectionInspector facilityInspectionInspector : facilityInspectionSchedule.getFacilityInsInspectors()) {
                        facilityInspectionInspector.setComFacilityCheckNo(facilityInspectionSchedule.getComFacilityCheckNo());
                        facilityInspectionInspector.setComFacilityCheckScheduleNo(facilityInspectionSchedule.getComFacilityCheckScheduleNo());
                        resultNo += facilityInspectionMapper.createFacilityInsInspector(facilityInspectionInspector);
                    }
                }
            }
            if (facilityInspectionSchedule.getFacilityInsItemResults() != null) {
                if (facilityInspectionSchedule.getFacilityInsItemResults().size() > 0) {
                    // 설비점검_항목별점검결과 등록/수정
                    for (FacilityInspectionItemResult facilityInspectionItemResult : facilityInspectionSchedule.getFacilityInsItemResults()) {
                        if (facilityInspectionItemResult.getComFacilityCheckScheduleNo() > 0) {
                            // 수정
                            facilityInspectionItemResult.setUpdateUserId(facilityInspectionSchedule.getUpdateUserId());
                            resultNo += facilityInspectionMapper.updateFacilityInsItemResult(facilityInspectionItemResult);
                        } else {
                            // 등록
                            facilityInspectionItemResult.setCreateUserId(facilityInspectionSchedule.getCreateUserId());
                            facilityInspectionItemResult.setComFacilityCheckNo(facilityInspectionSchedule.getComFacilityCheckNo());
                            facilityInspectionItemResult.setComFacilityCheckScheduleNo(facilityInspectionSchedule.getComFacilityCheckScheduleNo());
                            resultNo += facilityInspectionMapper.createFacilityInsItemResult(facilityInspectionItemResult);
                        }
                    }
                }

                // 무계획 결과 등록 시 시설점검항목 첨부파일 임시키 변환
                String taskKey = String.valueOf(facilityInspectionSchedule.getComFacilityCheckScheduleNo()) + "," + String.valueOf(facilityInspectionMaster.getComFacilityCheckNo());
                this.facilityInspectionMapper.updateFacilityInspectionItemRsltFileKey(facilityInspectionSchedule.getCheckItemTaskTempKey(), taskKey);
            }
        }
        return facilityInspectionSchedule.getComFacilityCheckScheduleNo();
    }

    /**
     * 시설점검일정(계획) 삭제
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일정일련번호
     * @return 변경 행 수
     * @throws Exception
     */

    @Transactional
    public int deleteFacilityInspectionPlan(int comFacilityCheckScheduleNo) throws Exception {
        int resultNo = 0;
        // 시설점검_항목별점검결과 삭제
        resultNo += facilityInspectionMapper.deleteFacilityInsItemResult(comFacilityCheckScheduleNo);
        // 시설점검_점검자 삭제
        resultNo += facilityInspectionMapper.deleteFacilityInsInspector(comFacilityCheckScheduleNo);
        // 시설점검_일정 삭제
        resultNo += facilityInspectionMapper.deleteFacilityCheckSchedule(comFacilityCheckScheduleNo);

        return resultNo;
    }

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
    public List<FacilityInspectionInspector> getFacilityInsInspectors(int comFacilityCheckScheduleNo, String inspectorClassCd) throws Exception {
        return facilityInspectionMapper.getFacilityInsInspectors(comFacilityCheckScheduleNo, inspectorClassCd);
    }

    /**
     * 시설점검 항목별점검결과 조회
     * 
     * @param comFacilityCheckScheduleNo
     *            시설점검일련번호
     * @param comFacilityCheckCd
     *            점검종류코드
     * @param comFacilityTypeCd
     *            시설유형코드
     * @param plantCd
     *            사업장코드
     * @return 시설점검 항목별점검결과 목록
     * @throws Exception
     */
    public List<FacilityInspectionItemResult> getFacilityInsItemResults(int comFacilityCheckScheduleNo, String comFacilityCheckCd, String comFacilityTypeCd, String plantCd) throws Exception {
        List<FacilityInspectionItemResult> facilityInspectionItemResults = facilityInspectionMapper.getFacilityInsItemResults(comFacilityCheckScheduleNo, comFacilityTypeCd, plantCd);
        // 저장된 데이터 값이 있는 경우
        if (facilityInspectionItemResults != null && facilityInspectionItemResults.size() > 0) {
            // 저장된 데이터값에 설비유형의 값이 현재 설비의 설비유형값과 동일한지 체크
            for (FacilityInspectionItemResult facilityInspectionItemResult : facilityInspectionItemResults) {
                // 동일한 값이 들어 있다면 저장된 데이터 값을 그대로 가져가 사용
                if (comFacilityTypeCd.equals(facilityInspectionItemResult.getComFacilityTypeCd())) {
                    return facilityInspectionItemResults;
                }
            }
            // 동일한 값이 없어 반환 되지 않은 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 저장된 값에 붙인다.
            facilityInspectionItemResults.addAll(facilityInspectionMapper.getNewFacilityInsItemResults(comFacilityTypeCd, comFacilityCheckCd, plantCd));
        } else {
            // 저장된 값이 없는 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 가지고 온다. (사용하는 것들만)
            facilityInspectionItemResults = facilityInspectionMapper.getNewFacilityInsItemResults(comFacilityTypeCd, comFacilityCheckCd, plantCd);
        }
        return facilityInspectionItemResults;
    }

    /**
     * 시설점검계획 진행단계 변경
     *
     * @param comFacilityCheckScheduleNo
     *            시설점검일정 ID
     * @param bizApprStepCd
     *            결재진행단계
     * @param apprRqstNo
     *            결재요청번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessFacilityInspection(int comFacilityCheckScheduleNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String checkStpeCd = "";
        // 결재완료
        FacilityInspectionSchedule facilityInspectionSchedule = facilityInspectionMapper.getFacilityInspectionPlan(comFacilityCheckScheduleNo);
        checkStpeCd = facilityInspectionSchedule.getCheckStepCd();
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료의 경우
            if (checkStpeCd.equals(ConstVal.SAF_CHK_STEP_PLAN_CD)) {
                // 계획상태의 경우
                facilityInspectionSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_RESULT_CD);
            } else if (checkStpeCd.equals(ConstVal.SAF_CHK_STEP_RESULT_CD)) {
                // 결과상태의 경우
                facilityInspectionSchedule.setCheckStepCd(ConstVal.SAF_CHK_STEP_IMPROVED_CD);

                // 미접수 상태로 변경
                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_FACILITY_INSPECTION, facilityInspectionSchedule.getComFacilityCheckScheduleNo(), "");
            }
        }

        // 결재요청번호 세팅
        if (checkStpeCd.equals(ConstVal.SAF_CHK_STEP_PLAN_CD)) {
            // 계획상태의 경우
            facilityInspectionSchedule.setPApprRqstNo(apprRqstNo);
        } else if (checkStpeCd.equals(ConstVal.SAF_CHK_STEP_IMPROVED_CD)) {
            // 결과상태의 경우
            facilityInspectionSchedule.setRApprRqstNo(apprRqstNo);
        }

        // 시설점검 결재 진행단계 수정
        resultNo += facilityInspectionMapper.updateFacilityCheckSchedule(facilityInspectionSchedule);
        return resultNo;
    }

    /**
     * 시설점검일정 진행단계 변경
     *
     * @param comFacilityCheckNo
     *            시설점검마스터 ID
     * @param bizApprStepCd
     *            결재진행단계
     * @param apprRqstNo
     *            결재요청번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessFacilityInspectionSchedule(int comFacilityCheckNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        // 결재완료
        FacilityInspectionMaster facilityInspectionMaster = facilityInspectionMapper.getFacilityInspectionMaster(comFacilityCheckNo);

        List<FacilityInspectionSchedule> facilityInspectionSchedules = this.facilityInspectionMapper.getFacilityInspectionResults(facilityInspectionMaster.getPlantCd(), facilityInspectionMaster.getComFacilityCheckPlanSymd(), facilityInspectionMaster.getComFacilityCheckPlanEymd(), facilityInspectionMaster.getComFacilityCheckCd(), "", "", "",
                "CHS00", "", "", "", "", "", "", facilityInspectionMaster.getComFacilityCheckNo());
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료의 경우
            for (int i = 0; i < facilityInspectionSchedules.size(); i++) {
                facilityInspectionSchedules.get(i).setCheckStepCd(ConstVal.SAF_CHK_STEP_PLAN_CD);
            }
            facilityInspectionMaster.setFacilityInspectionSchedules(facilityInspectionSchedules);
        }

        // 결재요청번호 세팅
        facilityInspectionMaster.setApprRqstNo(apprRqstNo);

        // 시설점검 결재 진행단계 수정
        resultNo += facilityInspectionMapper.updateFacilityInspectionMaster(facilityInspectionMaster);
        for (int i = 0; i < facilityInspectionSchedules.size(); i++) {
            facilityInspectionMapper.updateFacilityCheckSchedule(facilityInspectionSchedules.get(i));
        }
        return resultNo;
    }

    public List<HashMap<String, Object>> getFacilityInspectionResultStatus(String plantCd, String year) throws Exception {
        return facilityInspectionMapper.getFacilityInspectionResultStatus(plantCd, year);
    }

    public List<HashMap<String, Object>> getFacilityInspectionResultStatusSub(String plantCd, String year, String comFacilityCheckCd, String comFacilityTypeCd, String deptCd, String deptSubYn) throws Exception {
        return facilityInspectionMapper.getFacilityInspectionResultStatusSub(plantCd, year, comFacilityCheckCd, comFacilityTypeCd, deptCd, deptSubYn);
    }

}
