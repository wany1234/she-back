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
package com.she.manage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.health.infirmary.service.InfirmaryService;
import com.she.health.workMeasure.service.WorkMeasurePlanService;
import com.she.impr.service.ImprService;
import com.she.manage.mapper.ApprMapper;
import com.she.manage.model.Appr;
import com.she.manage.model.ApprBiz;
import com.she.manage.model.ApprBizLine;
import com.she.manage.model.ApprBizLineDtl;
import com.she.manage.model.ApprDelegate;
import com.she.manage.model.ApprGroupWare;
import com.she.manage.model.ApprRqst;
import com.she.manage.model.ApprRqstGroupWare;
import com.she.manage.model.ApprRqstLine;
import com.she.manage.model.User;
import com.she.mgt.budgetManagement.service.MgtBudgetExecutionService;
import com.she.mgt.budgetManagement.service.MgtBudgetingService;
import com.she.mgt.elect.service.ElectEvalPlanService;
import com.she.mgt.industrialSafetyHealthCommittee.service.IndustrialSafetyHealthCommitteeService;
import com.she.mgt.mgtLaw.service.SheLawMakingCheckService;
import com.she.mgt.mgtTarget.service.MgtTargetService;
import com.she.psm.PsmDocu.service.PsmDocuService;
import com.she.rsa.assess.service.AssessPlanService;
import com.she.rsa.planmgmt.service.PlanmgmtService;
import com.she.rsa.workRiskEval.service.WorkRiskEval04Service;
import com.she.rsa.workRiskEval.service.WorkRiskEval05Service;
import com.she.rsa.workRiskEval.service.WorkRiskEvalPlanService;
import com.she.safety.accident.service.AccidentService;
import com.she.safety.accident.service.NearmissService;
import com.she.safety.change.service.ChangeService;
import com.she.safety.check.service.CheckResultService;
import com.she.safety.consol.service.ConsolidationService;
import com.she.safety.constSafe.service.ConstService;
import com.she.safety.facilityCheck.service.FacilityCheckResultService;
import com.she.safety.facilityInspection.service.FacilityInspectionService;
import com.she.safety.govImplChk.service.GovImplChkService;
import com.she.safety.majDisaInsp.Service.MajDisaInspService;
import com.she.safety.preOper.service.PreOperCheckResultService;
import com.she.safety.psm.service.AuditPlanService;
import com.she.safety.psm.service.AuditResultService;
import com.she.safety.safetyhealth.service.InspectionSHService;
import com.she.safety.wkod.service.WkodMasterService;
import com.she.utils.ConstVal;
import com.she.vendor.assmnSfhlc.service.AssmnSfhlcService;
import com.she.vendor.subconEval.service.SubconEvalResultService;

@Service
public class ApprService {
    @Autowired
    private ApprMapper apprMapper;

    @Autowired
    private WkodMasterService wkodMasterService;

    @Autowired
    private ConstService constService;

    @Autowired
    private SubconEvalResultService subconEvalResultService;

    @Autowired
    private FacilityInspectionService facilityInspectionService;

    @Autowired
    private CheckResultService checkResultService;

    @Autowired
    private AssessPlanService assessPlanService;

    @Autowired
    private FacilityCheckResultService facilityCheckResultService;

    @Autowired
    private NearmissService nearmissService;

    @Autowired
    private AccidentService accidentService;

    @Autowired
    private ChangeService changeService;

    @Autowired
    private ImprService imprService;

    @Autowired
    private InfirmaryService infirmaryService;

    @Autowired
    private AuditPlanService auditPlanService;

    @Autowired
    private AuditResultService auditResultService;

    @Autowired
    private WorkMeasurePlanService workMeasurePlanService;

    @Autowired
    private SheLawMakingCheckService sheLawMakingCheckService;

    @Autowired
    private ConsolidationService consolidationService;

    @Autowired
    private IndustrialSafetyHealthCommitteeService industrialSafetyHealthCommitteeService;

    @Autowired
    private PreOperCheckResultService preOperCheckResultService;

    @Autowired
    private MgtTargetService mgtTargetService;

    @Autowired
    private PlanmgmtService planmgmtService;

    @Autowired
    private MgtBudgetingService mgtBudgetingService;

    @Autowired
    private MgtBudgetExecutionService mgtBudgetExecutionService;

    @Autowired
    private AssmnSfhlcService assmnSfhlcService;

    @Autowired
    private ElectEvalPlanService electEvalPlanService;

    @Autowired
    private MajDisaInspService majDisaInspService;

    @Autowired
    private InspectionSHService inspectionSHService;

    @Autowired
    private GovImplChkService govImplChkService;

    @Autowired
    private WorkRiskEvalPlanService workRiskEvalPlanService;

    @Autowired
    private WorkRiskEval04Service workRiskEval04Service;

    @Autowired
    private WorkRiskEval05Service workRiskEval05Service;

    @Autowired
    private PsmDocuService psmDocuService;

    /**
     * 결재문서 마스터 목록 조회
     *
     * @param apprBizCd
     * @param bizNm
     * @param apprBizTypeCd
     * @return
     * @throws Exception
     */
    public List<ApprBiz> getApprBizList(String apprBizCd, String bizNm, String apprBizTypeCd, DefaultParam defaultParam) throws Exception {
        return apprMapper.getApprBizList(apprBizCd, bizNm, apprBizTypeCd, defaultParam);
    }

    /**
     * 결재선 등록
     *
     * @param apprBiz
     * @return
     * @throws Exception
     */
    @Transactional
    public int createApprLine(ApprBiz apprBiz) throws Exception {
        int result = 0;

        if (apprBiz == null) {
            return 0;
        } else {
            // 결재문서 마스터 등록
            result = apprMapper.createApprBiz(apprBiz);

            if (result <= 0) {
                return 0;
            } else {
                int apprBizNo = apprBiz.getApprBizNo();
                // 결재문서 결재선 등록
                if (CollectionUtils.isNotEmpty(apprBiz.getApprBizLineList())) {
                    if (StringUtils.equals(apprBiz.getApprBizTypeCd(), ConstVal.COM_PLANT_SAME_COMMON)) {
                        // 공통일때
                        ApprBizLine apprBizLine = apprBiz.getApprBizLineList().get(0);
                        apprBizLine.setApprBizNo(apprBizNo);
                        // 결재문서 결재선 등록
                        apprMapper.createApprBizLine(apprBizLine);

                        // 결재문서 결재선 세부정보 등록
                        if (CollectionUtils.isNotEmpty(apprBiz.getApprBizLineDtlList())) {
                            for (ApprBizLineDtl apprBizLineDtl : apprBiz.getApprBizLineDtlList()) {
                                apprBizLineDtl.setApprBizNo(apprBizNo);
                                apprBizLineDtl.setApprBizLineNo(apprBizLine.getApprBizLineNo());
                                apprMapper.createApprBizLineDtl(apprBizLineDtl);
                            }
                        }
                    } else {
                        for (ApprBizLine apprBizLine : apprBiz.getApprBizLineList()) {
                            apprBizLine.setApprBizNo(apprBizNo);
                            // 결재문서 결재선 등록
                            apprMapper.createApprBizLine(apprBizLine);

                            // 결재문서 결재선 세부정보 등록
                            if (CollectionUtils.isNotEmpty(apprBiz.getApprBizLineDtlList())) {
                                for (ApprBizLineDtl apprBizLineDtl : apprBiz.getApprBizLineDtlList()) {
                                    if (StringUtils.equals(apprBizLine.getPlantCd(), apprBizLineDtl.getPlantCd())) {
                                        apprBizLineDtl.setApprBizNo(apprBizNo);
                                        apprBizLineDtl.setApprBizLineNo(apprBizLine.getApprBizLineNo());
                                        apprMapper.createApprBizLineDtl(apprBizLineDtl);
                                    }

                                }
                            }
                        }
                    }
                }
                return apprBiz.getApprBizNo();
            }
        }
    }

    /**
     * 결재선 수정
     *
     * @param apprBiz
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateApprLine(ApprBiz apprBiz) throws Exception {
        int result = 0;

        if (apprBiz == null) {
            return 0;
        } else {
            // 결재문서 마스터 수정
            result = apprMapper.updateApprBiz(apprBiz);

            if (result <= 0) {
                return 0;
            } else {
                int apprBizNo = apprBiz.getApprBizNo();
                // 결재문서 결재선 등록
                if (CollectionUtils.isNotEmpty(apprBiz.getApprBizLineList())) {
                    // 기등록된 결재문서 결재선 삭제
                    apprMapper.deleteApprBizLine(apprBizNo);
                    // 기등록된 결재문서 세부정보 삭제
                    apprMapper.deleteApprBizLineDtl(apprBizNo);

                    if (StringUtils.equals(apprBiz.getApprBizTypeCd(), ConstVal.COM_PLANT_SAME_COMMON)) {
                        // 공통일때
                        ApprBizLine apprBizLine = apprBiz.getApprBizLineList().get(0);
                        apprBizLine.setApprBizNo(apprBizNo);
                        // 결재문서 결재선 등록
                        apprMapper.createApprBizLine(apprBizLine);

                        // 결재문서 결재선 세부정보 등록
                        if (CollectionUtils.isNotEmpty(apprBiz.getApprBizLineDtlList())) {
                            for (ApprBizLineDtl apprBizLineDtl : apprBiz.getApprBizLineDtlList()) {
                                apprBizLineDtl.setApprBizNo(apprBizNo);
                                apprBizLineDtl.setApprBizLineNo(apprBizLine.getApprBizLineNo());
                                apprMapper.createApprBizLineDtl(apprBizLineDtl);
                            }
                        }
                    } else {
                        for (ApprBizLine apprBizLine : apprBiz.getApprBizLineList()) {
                            apprBizLine.setApprBizNo(apprBizNo);
                            // 결재문서 결재선 등록
                            apprMapper.createApprBizLine(apprBizLine);

                            // 결재문서 결재선 세부정보 등록
                            if (CollectionUtils.isNotEmpty(apprBiz.getApprBizLineDtlList())) {
                                for (ApprBizLineDtl apprBizLineDtl : apprBiz.getApprBizLineDtlList()) {
                                    if (StringUtils.equals(apprBizLine.getPlantCd(), apprBizLineDtl.getPlantCd())) {
                                        apprBizLineDtl.setApprBizNo(apprBizNo);
                                        apprBizLineDtl.setApprBizLineNo(apprBizLine.getApprBizLineNo());
                                        apprMapper.createApprBizLineDtl(apprBizLineDtl);
                                    }

                                }
                            }
                        }
                    }
                }
                return apprBiz.getApprBizNo();
            }
        }
    }

    /**
     * 결재선 상세 조회
     *
     * @param apprBizNo
     * @return
     * @throws Exception
     */
    public ApprBiz getApprBizDetail(int apprBizNo, DefaultParam defaultParam) throws Exception {
        if (apprBizNo > 0) {
            ApprBiz apprBiz = apprMapper.getApprBizDetail(apprBizNo, "", defaultParam);
            if (apprBiz == null) {
                return null;
            } else {
                apprBiz.setApprBizLineList(apprMapper.getApprBizLineList(apprBizNo, "", defaultParam));
                apprBiz.setApprBizLineDtlList(apprMapper.getApprBizLineDtlList(apprBizNo, "", defaultParam));
                return apprBiz;
            }
        } else {
            return null;
        }
    }

    /**
     * 결재문서 유형코드 조회
     *
     * @param apprBizCd
     * @return
     * @throws Exception
     */
    public int getApprBizCodeCount(String apprBizCd) throws Exception {
        if (StringUtils.isNotBlank(apprBizCd)) {
            return apprMapper.getApprBizCodeCount(apprBizCd);
        } else {
            return -1;
        }
    }

    /**
     * 결재 정보 조회
     *
     * @param apprBizCd
     * @param plantCd
     * @param userId
     * @return
     * @throws Exception
     */
    public ApprBiz getApprRequestInfo(String apprBizCd, String plantCd, String userId, DefaultParam defaultParam) throws Exception {
        if (StringUtils.isNotBlank(apprBizCd) && StringUtils.isNotBlank(plantCd)) {
            // 결재문서 마스터 조회
            ApprBiz apprBiz = apprMapper.getApprBizDetail(0, apprBizCd, defaultParam);
            if (apprBiz == null) {
                return null;
            } else {
                // 결재문서 결재선 조회
                List<ApprBizLine> apprBizLineList = apprMapper.getApprBizLineList(apprBiz.getApprBizNo(), plantCd, defaultParam);
                // 결재문서 결제선 상세 조회
                List<ApprBizLineDtl> apprBizLineDtlList = apprMapper.getApprBizLineDtlList(apprBiz.getApprBizNo(), plantCd, defaultParam);

                if (CollectionUtils.isEmpty(apprBizLineList)) {
                    return null;
                } else {
                    apprBiz.setApprBizLineList(apprBizLineList);
                    apprBiz.setApprBizLineDtlList(apprBizLineDtlList);

                    ApprBizLine apprBizLine = apprBizLineList.get(0);
                    ApprBizLineDtl apprBizLineDtl = null;
                    if (CollectionUtils.isNotEmpty(apprBizLineDtlList)) {
                        apprBizLineDtl = apprBizLineDtlList.get(0);
                    }

                    String apprLineTypeCd = apprBizLine.getApprLineTypeCd();

                    List<Map<String, String>> apprLines = new ArrayList<>();
                    HashMap<String, Object> apprDelegator = new HashMap<String, Object>();
                    int j = 0;
                    for (int i = 0; i < apprLineTypeCd.length(); i++) {
                        Map<String, String> userMap = null;

                        if (StringUtils.equals(CharUtils.toString(apprLineTypeCd.charAt(i)), "Y")) {
                            if (i == 0) {
                                // 업무부서 담당
                                userMap = getApprUserInfo(userId, "31"); // 담당조회
                                if (userMap == null) {
                                    userMap = getApprUserInfo(userId, "21"); // 팀장조회
                                    if (userMap == null) {
                                        userMap = getApprUserInfo(userId, "11"); // 실장조회
                                    }
                                }
                                if (userMap != null) {
                                    userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_APPROVAL);
                                }
                            } else if (i == 1) {
                                // 업무부서 팀장
                                if (apprLines.size() > 0) {
                                    Map<String, String> checkMap = apprLines.get(0);
                                    boolean flag = true;
                                    String checkDutyCd = checkMap.get("dutyCd");
                                    if (StringUtils.equals(checkDutyCd, "21") || StringUtils.equals(checkDutyCd, "11")) {
                                        flag = false;
                                    }
                                    if (flag) {
                                        userMap = getApprUserInfo(userId, "21"); // 팀장조회
                                        if (userMap != null) {
                                            userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_APPROVAL);
                                        }
                                    }
                                } else {
                                    userMap = getApprUserInfo(userId, "21"); // 팀장조회
                                    if (userMap != null) {
                                        userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_APPROVAL);
                                    }
                                }
                            } else if (i == 2) {
                                // 업무부서 실장
                                if (apprLines.size() > 0) {
                                    boolean flag = true;
                                    for (Map<String, String> map : apprLines) {
                                        if (map.get("dutyCd") == "11") {
                                            flag = false;
                                        }
                                    }
                                    if (flag) {
                                        userMap = getApprUserInfo(userId, "11"); // 실장조회
                                        if (userMap != null) {
                                            userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_APPROVAL);
                                        }
                                    }
                                } else {
                                    userMap = getApprUserInfo(userId, "11"); // 실장조회
                                    if (userMap != null) {
                                        userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_APPROVAL);
                                    }
                                }
                            } else if (i == 3) {
                                // 사업장 확인부서 실무자
                                if (apprBizLineDtl != null) {
                                    userMap = new HashMap<>();
                                    userMap.put("userId", apprBizLineDtl.getPlantConfWorkerId());
                                    userMap.put("userNm", apprBizLineDtl.getPlantConfWorkerNm());
                                    userMap.put("deptCd", apprBizLineDtl.getPlantConfirmDeptCd());
                                    userMap.put("deptNm", apprBizLineDtl.getPlantConfirmDeptNm());
                                    userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_PLANT_CONFIRM);
                                }
                            } else if (i == 4) {
                                // 사업장 확인부서 담당
                                if (apprBizLineDtl != null) {
                                    if (StringUtils.isNotBlank(apprBizLineDtl.getPlantConfChargerId())) {
                                        userMap = new HashMap<>();
                                        userMap.put("userId", apprBizLineDtl.getPlantConfChargerId());
                                        userMap.put("userNm", apprBizLineDtl.getPlantConfChargerNm());
                                        userMap.put("deptCd", apprBizLineDtl.getPlantConfirmDeptCd());
                                        userMap.put("deptNm", apprBizLineDtl.getPlantConfirmDeptNm());
                                        userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_PLANT_CONFIRM);
                                    } else {
                                        if (StringUtils.isNotBlank(apprBizLineDtl.getPlantConfWorkerId())) {
                                            userMap = getApprUserInfo(apprBizLineDtl.getPlantConfWorkerId(), "31"); // 담당조회
                                            if (userMap != null) {
                                                userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_PLANT_CONFIRM);
                                            }
                                        }
                                    }
                                }
                            } else if (i == 5) {
                                // 사업장 확인부서 팀장
                                if (apprBizLineDtl != null) {
                                    if (StringUtils.isNotBlank(apprBizLineDtl.getPlantConfWorkerId())) {
                                        // 실무자의 팀장 조회
                                        userMap = getApprUserInfo(apprBizLineDtl.getPlantConfWorkerId(), "21");
                                        if (userMap != null) {
                                            userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_PLANT_CONFIRM);
                                        }
                                    } else {
                                        if (StringUtils.isNotBlank(apprBizLineDtl.getPlantConfChargerId())) {
                                            // 실무자가 없을때 담당의 팀장 조회
                                            userMap = getApprUserInfo(apprBizLineDtl.getPlantConfChargerId(), "21");
                                            if (userMap != null) {
                                                userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_PLANT_CONFIRM);
                                            }
                                        } else {
                                            if (StringUtils.isNotBlank(apprBizLineDtl.getPlantConfirmDeptCd())) {
                                                // 부서별 팀장 조회
                                                userMap = getApprUserInfoByDept(apprBizLineDtl.getPlantConfirmDeptCd(), "21");
                                                if (userMap != null) {
                                                    userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_PLANT_CONFIRM);
                                                }
                                            }
                                        }
                                    }
                                }
                            } else if (i == 6) {
                                // 본사 확인부서 실무자
                                if (apprBizLineDtl != null) {
                                    userMap = new HashMap<>();
                                    userMap.put("userId", apprBizLineDtl.getHqConfWorkerId());
                                    userMap.put("userNm", apprBizLineDtl.getHqConfWorkerNm());
                                    userMap.put("deptCd", apprBizLineDtl.getHqConfirmDeptCd());
                                    userMap.put("deptNm", apprBizLineDtl.getHqConfirmDeptNm());
                                    userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_HQ_CONFIRM);
                                }
                            } else if (i == 7) {
                                // 본사 확인부서 담당
                                if (apprBizLineDtl != null) {
                                    if (StringUtils.isNotBlank(apprBizLineDtl.getHqConfChargerId())) {
                                        userMap = new HashMap<>();
                                        userMap.put("userId", apprBizLineDtl.getHqConfChargerId());
                                        userMap.put("userNm", apprBizLineDtl.getHqConfChargerNm());
                                        userMap.put("deptCd", apprBizLineDtl.getHqConfirmDeptCd());
                                        userMap.put("deptNm", apprBizLineDtl.getHqConfirmDeptNm());
                                        userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_HQ_CONFIRM);
                                    } else {
                                        if (StringUtils.isNotBlank(apprBizLineDtl.getHqConfWorkerId())) {
                                            userMap = getApprUserInfo(apprBizLineDtl.getHqConfWorkerId(), "31"); // 담당조회
                                            if (userMap != null) {
                                                userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_HQ_CONFIRM);
                                            }
                                        }
                                    }
                                }
                            } else if (i == 8) {
                                // 본사 확인부서 팀장
                                if (apprBizLineDtl != null) {
                                    if (StringUtils.isNotBlank(apprBizLineDtl.getHqConfWorkerId())) {
                                        // 실무자의 팀장 조회
                                        userMap = getApprUserInfo(apprBizLineDtl.getHqConfWorkerId(), "21");
                                        if (userMap != null) {
                                            userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_HQ_CONFIRM);
                                        }
                                    } else {
                                        if (StringUtils.isNotBlank(apprBizLineDtl.getHqConfChargerId())) {
                                            // 실무자가 없을때 담당의 팀장 조회
                                            userMap = getApprUserInfo(apprBizLineDtl.getHqConfChargerId(), "21");
                                            if (userMap != null) {
                                                userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_HQ_CONFIRM);
                                            }
                                        } else {
                                            if (StringUtils.isNotBlank(apprBizLineDtl.getHqConfirmDeptCd())) {
                                                // 부서별 팀장 조회
                                                userMap = getApprUserInfoByDept(apprBizLineDtl.getHqConfirmDeptCd(), "21");
                                                if (userMap != null) {
                                                    userMap.put("apprTypeCd", ConstVal.COM_APPR_TYPE_HQ_CONFIRM);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (userMap != null && userMap.containsKey("userId")) {
                                apprDelegator = this.apprMapper.getApprDelegator(userMap.get("userId"));
                            }

                            if (apprDelegator != null && apprDelegator.containsKey("delegator_id")) {
                                userMap.put("delegatorId", apprDelegator.get("delegator_id").toString());
                                userMap.put("delegatorNm", apprDelegator.get("delegator_nm").toString());
                            }
                            if (userMap != null) {
                                userMap.put("sortOrder", Integer.toString(j));
                                apprLines.add(userMap);
                            }
                            apprDelegator = null;
                            j++;
                        }
                    }
                    if (CollectionUtils.isNotEmpty(apprLines)) {
                        apprBiz.setApprLines(apprLines);
                    }
                }
                return apprBiz;
            }
        } else {
            return null;
        }
    }

    /**
     * 결재선 결재자 조회
     *
     * @param userId
     * @param dutyCd
     * @return
     * @throws Exception
     */
    public Map<String, String> getApprUserInfo(String userId, String dutyCd) throws Exception {
        User user = apprMapper.getApprUserInfo(userId, dutyCd);
        if (user != null) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userId", user.getUserId());
            userMap.put("userNm", user.getUserNm());
            userMap.put("deptCd", user.getDeptCd());
            userMap.put("deptNm", user.getDeptNm());
            userMap.put("dutyCd", user.getDutyCd());
            userMap.put("dutyNm", user.getDutyNm());
            return userMap;
        } else {
            return null;
        }
    }

    /**
     * 결재선 부서별 팀장 조회
     *
     * @param deptCd
     * @return
     * @throws Exception
     */
    public Map<String, String> getApprUserInfoByDept(String deptCd, String dutyCd) throws Exception {
        User user = apprMapper.getApprUserInfoByDept(deptCd, dutyCd);
        if (user != null) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("userId", user.getUserId());
            userMap.put("userNm", user.getUserNm());
            userMap.put("deptCd", user.getDeptCd());
            userMap.put("deptNm", user.getDeptNm());
            userMap.put("dutyCd", user.getDutyCd());
            userMap.put("dutyNm", user.getDutyNm());
            return userMap;
        } else {
            return null;
        }
    }

    /**
     * 결재요청 등록
     *
     * @param apprRqst
     * @return
     * @throws Exception
     */
    @Transactional
    public int createApprRequest(ApprRqst apprRqst) throws Exception {
        if (apprRqst != null) {
            // 결재요청 등록
            apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_ING); // 업무결재진행단계코드
            int result = apprMapper.createApprRqst(apprRqst);

            if (result > 0) {
                Boolean isApproval = false;
                if (CollectionUtils.isNotEmpty(apprRqst.getApprLines())) {
                    // 기안자 등록
                    ApprRqstLine apprRqstLine = new ApprRqstLine();
                    apprRqstLine.setApprRqstNo(apprRqst.getApprRqstNo()); // 결재요청번호
                    apprRqstLine.setLineSeqNo(0); // 결재선순번
                    apprRqstLine.setApprTypeCd(ConstVal.COM_APPR_TYPE_DRAFT); // 결재자유형코드(기안)
                    apprRqstLine.setApprUserId(apprRqst.getReqUserId()); // 상신자ID
                    apprRqstLine.setApprStepCd(ConstVal.COM_APPR_STEP_APPROVAL); // 결재자처리상태코드
                    apprMapper.createApprRqstLine(apprRqstLine);

                    // 결재요청 결재선 목록 등록
                    int i = 1;
                    for (Map<String, String> apprLine : apprRqst.getApprLines()) {
                        ApprRqstLine param = new ApprRqstLine();
                        param.setApprRqstNo(apprRqst.getApprRqstNo()); // 결재요청번호
                        param.setLineSeqNo(i); // 결재선순번
                        param.setApprTypeCd(apprLine.get("apprTypeCd")); // 결재자유형코드(기안)
                        param.setApprUserId(apprLine.get("userId")); // 결재자 ID
                        param.setDelegatorId(apprLine.get("delegatorId")); // 대결자ID
                        if (apprRqst.getReqUserId().equals(apprLine.get("userId")) && i == 1) {
                            param.setApprStepCd(ConstVal.COM_APPR_STEP_APPROVAL);
                            isApproval = true;
                        }
                        apprMapper.createApprRqstLine(param);
                        i++;
                    }

                    // 첫번째 결재자에게 메일 발송 처리 필요....

                }
                int cnt = 0;
                if (apprRqst.getApprLines().size() == 1 && isApproval) {
                    apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_COMPLETE); // 결재완료
                    apprMapper.updateBizApprStep(apprRqst);
                    cnt = updateProcessStatus(apprRqst.getApprRqstNo(), ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
                } else {
                    cnt = updateProcessStatus(apprRqst.getApprRqstNo(), ConstVal.COM_BIZ_APPR_STEP_ING);
                }
                return cnt == 0 ? 0 : apprRqst.getApprRqstNo();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 결재요청 수정(재결재요청)
     *
     * @param apprRqst
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateApprRequest(ApprRqst apprRqst) throws Exception {
        if (apprRqst == null) {
            return 0;
        } else {
            // 반려된 결재요청건 재기안상태로 변경 처리
            apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_REAPPROVAL); // 재기안상태
            int result = apprMapper.updateBizApprStep(apprRqst);

            if (result <= 0) {
                return 0;
            } else {
                // 결재요청 등록
                apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_ING); // 결재중상태
                apprRqst.setPApprRqstNo(apprRqst.getApprRqstNo()); // 상위결재요청번호
                result = apprMapper.createApprRqst(apprRqst);

                if (result <= 0) {
                    return 0;
                } else {
                    if (CollectionUtils.isNotEmpty(apprRqst.getApprLines())) {
                        // 기안자 등록
                        ApprRqstLine apprRqstLine = new ApprRqstLine();
                        apprRqstLine.setApprRqstNo(apprRqst.getApprRqstNo()); // 결재요청번호
                        apprRqstLine.setLineSeqNo(0); // 결재선순번
                        apprRqstLine.setApprTypeCd(ConstVal.COM_APPR_TYPE_DRAFT); // 결재자유형코드(기안)
                        apprRqstLine.setApprUserId(apprRqst.getReqUserId()); // 상신자ID
                        apprRqstLine.setApprStepCd(ConstVal.COM_APPR_STEP_APPROVAL); // 결재자처리상태코드
                        apprMapper.createApprRqstLine(apprRqstLine);

                        // 결재요청 결재선 목록 등록
                        int i = 1;
                        for (Map<String, String> apprLine : apprRqst.getApprLines()) {
                            ApprRqstLine param = new ApprRqstLine();
                            param.setApprRqstNo(apprRqst.getApprRqstNo()); // 결재요청번호
                            param.setLineSeqNo(i); // 결재선순번
                            param.setApprTypeCd(apprLine.get("apprTypeCd")); // 결재자유형코드(기안)
                            param.setApprUserId(apprLine.get("userId")); // 결재자ID
                            apprMapper.createApprRqstLine(param);
                            i++;
                        }

                        // 첫번째 결재자에게 메일 발송 처리 필요....

                    }
                    return updateProcessStatus(apprRqst.getApprRqstNo(), ConstVal.COM_BIZ_APPR_STEP_ING);
                }
            }
        }
    }

    /**
     * 결재 할 문서 조회
     *
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param apprYn
     *            처리여부
     * @param apprUserId
     *            사용자ID
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<Appr> getWhichApprs(String startDate, String endDate, String apprYn, String returnYn, String apprUserId, String apprRqstNm, DefaultParam defaultParam) throws Exception {
        return apprMapper.getWhichApprs(startDate, endDate, apprYn, returnYn, apprUserId, apprRqstNm, defaultParam);
    }

    /**
     * 결재 할 문서 결재선 목록 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<ApprRqstLine> getWhichApprLine(int apprRqstNo, DefaultParam defaultParam) throws Exception {
        if (apprRqstNo <= 0) {
            return null;
        } else {
            return apprMapper.getWhichApprLine(apprRqstNo, defaultParam);
        }
    }

    /**
     * 결재 처리 상태 수정
     *
     * @param apprRqstLine
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateApprStep(ApprRqstLine apprRqstLine) throws Exception {
        if (apprRqstLine == null) {
            return 0;
        } else {

            ApprRqstLine apprLine = this.apprMapper.getApprLine(apprRqstLine.getApprRqstNo(), apprRqstLine.getLineSeqNo());
            apprLine.setApprStepCd(apprRqstLine.getApprStepCd());
            // 결재 정보 유효성 검사
            int cnt = apprMapper.getCountApprRqstLine(apprLine);
            if (cnt <= 0) {
                return 0;
            } else {
                // 결재 처리 상태 수정
                apprLine.setApprRemark(apprRqstLine.getApprRemark());
                int result = apprMapper.updateApprStep(apprLine);

                if (result > 0) {
                    if (StringUtils.equals(apprLine.getApprStepCd(), ConstVal.COM_APPR_STEP_APPROVAL)) {
                        // 승인일때
                        // 총 결재선 개수 조회
                        ApprRqstLine param = new ApprRqstLine();
                        param.setApprRqstNo(apprLine.getApprRqstNo());
                        int totAppr = apprMapper.getCountApprRqstLine(param);

                        if (totAppr <= (apprLine.getLineSeqNo() + 1)) {
                            // 최종 승인일때 결재요청 테이블 업무결재진행단계 업데이트
                            ApprRqst apprRqst = new ApprRqst();
                            apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_COMPLETE); // 결재완료
                            apprRqst.setApprRqstNo(apprLine.getApprRqstNo());
                            result = apprMapper.updateBizApprStep(apprRqst);

                            // 기안자에게 메일 발송 처리 필요..

                            // 각 업무별 상태 변경 api 호출
                            return updateProcessStatus(apprLine.getApprRqstNo(), ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
                        } else {
                            // 다음 결재자에게 메일 발송 처리 필요..

                            return 1;
                        }
                    } else {
                        // 반려일때
                        // 결재요청 테이블 업무결재진행단계 업데이트
                        ApprRqst apprRqst = new ApprRqst();
                        apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_REJECT); // 반려
                        apprRqst.setApprRqstNo(apprLine.getApprRqstNo());
                        result = apprMapper.updateBizApprStep(apprRqst);

                        // 기안자에게 메일 발송 처리 필요..

                        // 각 업무별 상태 변경 api 호출
                        return updateProcessStatus(apprLine.getApprRqstNo(), ConstVal.COM_BIZ_APPR_STEP_REJECT);
                    }
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * 각 업무별 결재 상태에 따른 api호출
     *
     * @param apprRqstNo
     * @param bizApprStepCd
     * @return
     */
    @Transactional
    public int updateProcessStatus(int apprRqstNo, String bizApprStepCd) throws Exception {

        // 결재요청 상세 정보 조회
        Appr appr = apprMapper.getApprRqstDetail(apprRqstNo);

        if (appr == null) {
            return 0;
        } else {
            // 결재요청 파라미터 추출
            Map<String, String> requestParams = new HashMap<String, String>(); // api전달파라미터
            String reqParam = appr.getApprReqParameter();
            String[] params = reqParam.split("\\|");

            for (String param : params) {
                String[] splitParam = param.split("\\:");
                requestParams.put(splitParam[0], splitParam[1]);
            }

            this.apprCallback(appr, requestParams, apprRqstNo, bizApprStepCd);
            return 1;
        }
    }

    public void apprCallback(Appr appr, Map<String, String> requestParams, int apprRqstNo, String bizApprStepCd) throws Exception {
        // 문서 유형 별 또는 url 별 각 service api 호출 처리 필요...
        if (StringUtils.equals(appr.getApprBizCd(), "CS-WO-01")) {
            // 작업허가서 추가 로직 처리 필요....
            wkodMasterService.apprProcessWkodMaster(Integer.parseInt(requestParams.get("wkodNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "CS-WO-02")) {
            // 작업허가서 모바일
            wkodMasterService.apprProcessWkodMaster(Integer.parseInt(requestParams.get("wkodNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "CS-WO-03")) {
            // 공사현황 추가 로직
            constService.apprProcessConst(requestParams.get("constNo"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "CN-EV-01")) {
            // 평가항목결과 추가 로직
            subconEvalResultService.apprProcessSubconEvalResult(Integer.parseInt(requestParams.get("safSubconEvalResultNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_FA-02") || StringUtils.equals(appr.getApprBizCd(), "SA_FA-03")) {
            // 시설점검계획,결과 추가 로직
            facilityInspectionService.apprProcessFacilityInspection(Integer.parseInt(requestParams.get("comFacilityCheckScheduleNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_FA-01")) {
            // 시설점검일정 추가 로직
            facilityInspectionService.apprProcessFacilityInspectionSchedule(Integer.parseInt(requestParams.get("comFacilityCheckNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "RS-EV-01") || StringUtils.equals(appr.getApprBizCd(), "RS-EV-02") || StringUtils.equals(appr.getApprBizCd(), "RS-HZ-01") || StringUtils.equals(appr.getApprBizCd(), "RS-HZ-02") || StringUtils.equals(appr.getApprBizCd(), "RS-EV-03") || StringUtils.equals(appr.getApprBizCd(), "RS-EV-04")) {
            // 위험성 평가 수립 / 결과
            assessPlanService.apprAssessPlan(Integer.parseInt(requestParams.get("assessPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CE-01")) {
            // 안전점검일정
            checkResultService.apprCheckSchedule(Integer.parseInt(requestParams.get("safCheckNo")), apprRqstNo, bizApprStepCd);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CE-02")) {
            // 안전점검계획
            checkResultService.apprCheckPlan(Integer.parseInt(requestParams.get("safCheckScheduleNo")), apprRqstNo, bizApprStepCd);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CE-03")) {
            // 안전점검결과
            checkResultService.apprCheckResult(Integer.parseInt(requestParams.get("safCheckScheduleNo")), apprRqstNo, bizApprStepCd);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-NR-01")) {
            // 아차사고등록
            nearmissService.apprNearmiss(Integer.parseInt(requestParams.get("safNearmissNo")), apprRqstNo, bizApprStepCd);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_FC-04")) {
            // 설비점검 마스터 추가 로직
            // 설비점검 마스터에 할당되어진 설비점검 일정의 정보들을 결재진행하는 것으로 하위의 일정들의 값들을 결재완료일
            // 시에 상태값을 바꾸고 결재진행번호 값을 update한다.
            facilityCheckResultService.apprProcessFacilityCheckMaster(Integer.parseInt(requestParams.get("safFacilityCheckNo")), bizApprStepCd, apprRqstNo, new DefaultParam("kr"));
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_IB-01")) {
            // 가동전점검 마스터별 일정 추가 로직(설비점검과 상동)
            // facilityCheckResultService.apprProcessFacilityCheckSchedule(
            // Integer.parseInt(requestParams.get("safFacilityCheckScheduleNo")),
            // bizApprStepCd, apprRqstNo);
            facilityCheckResultService.apprProcessFacilChkPlan(Integer.parseInt(requestParams.get("safFacilChkNo")), bizApprStepCd, apprRqstNo, new DefaultParam("kr"));
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_FC-01")) {
            // 설비점검 마스터별 일정 추가 로직
            // facilityCheckResultService.apprProcessFacilityCheckSchedule(
            // Integer.parseInt(requestParams.get("safFacilityCheckScheduleNo")),
            // bizApprStepCd, apprRqstNo);
            facilityCheckResultService.apprProcessFacilChkPlan(Integer.parseInt(requestParams.get("safFacilChkNo")), bizApprStepCd, apprRqstNo, new DefaultParam("kr"));
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_FC-02")) {
            // 설비점검 마스터별 일정 결과 법정점검 추가 로직
            facilityCheckResultService.apprProcessFacilityCheckScheduleResult(Integer.parseInt(requestParams.get("safFacilityCheckScheduleNo")), bizApprStepCd, apprRqstNo, new DefaultParam("kr"));
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_FC-03")) {
            // 설비점검 마스터별 일정 결과 자체점검 추가 로직
            facilityCheckResultService.apprProcessFacilityCheckScheduleResult(Integer.parseInt(requestParams.get("safFacilityCheckScheduleNo")), bizApprStepCd, apprRqstNo, new DefaultParam("kr"));
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-AC-01")) {
            // 사고 발생
            accidentService.apprProcessAccidentOccur(Integer.parseInt(requestParams.get("safAccidentNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-AC-02")) {
            // 사고 접수
            accidentService.apprProcessAccidentReception(Integer.parseInt(requestParams.get("safAccidentNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-AC-03")) {
            // 사고 조사결과
            accidentService.apprProcessAccidentResult(Integer.parseInt(requestParams.get("safAccidentNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CH-01")) {
            // 변경관리 요청
            changeService.apprProcessChange(Integer.parseInt(requestParams.get("safChngNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CH-0A")) {
            // 변경관리 요청(위원회 미개최)
            changeService.apprProcessChange(Integer.parseInt(requestParams.get("safChngNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CH-02")) {
            // 변경관리 심의 및 승인
            changeService.apprProcessChange(Integer.parseInt(requestParams.get("safChngNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-CH-03")) {
            // 변경관리 변경실시/사후관리
            changeService.apprProcessChange(Integer.parseInt(requestParams.get("safChngNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "CR_IM-02")) {
            // 개선
            imprService.apprProcessImpr(Integer.parseInt(requestParams.get("safImprActNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "HE-PS-01")) {
            // 유소견자 지정/해제
            infirmaryService.apprSuspectRequest(Integer.parseInt(requestParams.get("suspectReqNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-AU-01")) {
            // PSM 계획 결재
            auditPlanService.apprProcessAuditPlan(Integer.parseInt(requestParams.get("auditRsltNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-AU-02")) {
            // PSM 결과 결재
            auditResultService.apprProcessAuditResult(Integer.parseInt(requestParams.get("auditRsltNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "HE-WM-01")) {
            // 작업환경측정 계획
            workMeasurePlanService.apprProcessWorkMeasurePlan(Integer.parseInt(requestParams.get("workMeasPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "HE-WM-02")) {
            // 작업환경측정 결과
            workMeasurePlanService.apprProcessWorkMeasureResult(Integer.parseInt(requestParams.get("workMeasPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-LM-01")) {
            // 작업환경측정 결과
            sheLawMakingCheckService.apprProcessLawMaking(Integer.parseInt(requestParams.get("lmcSeqNo")), Integer.parseInt(requestParams.get("seqNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_CO_0")) {
            // 연간합동점검계획
            consolidationService.apprConsolPlanByYear(Integer.parseInt(requestParams.get("safCongChkPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA_CO_1")) {
            // 연간합동점검결과
            consolidationService.apprConsolResultByYear(Integer.parseInt(requestParams.get("safCongChkRsltNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-CM-01")) {
            // 경영 > Communication (산업안전보건위원회_회의록)
            industrialSafetyHealthCommitteeService.apprProcessCommittee(Integer.parseInt(requestParams.get("committeeConfNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-OP-01")) {
            // 가동전 점검 계획
            preOperCheckResultService.apprProcessPreOperChkPlan(Integer.parseInt(requestParams.get("safFacilChkNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-OP-02")) {
            // 가동전 점검 결과
            preOperCheckResultService.apprProcessPreOperChkResult(Integer.parseInt(requestParams.get("safFacilChkNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-MI-06")) {
            // SHE 목표 결재
            mgtTargetService.apprProcessMgtTarget(requestParams.get("mgtTargetGrpNo"), appr.getApprBizCd(), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-MI-07") || StringUtils.equals(appr.getApprBizCd(), "MG-MI-08")) {
            // SHE 실적/평가 결재
            mgtTargetService.apprProcessMgtTarget(requestParams.get("mgtTargetMonthNo"), appr.getApprBizCd(), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "RS-PL-01") || StringUtils.equals(appr.getApprBizCd(), "RS-PL-02")) {
            // 평가계획 관리
            planmgmtService.updateAppr(Integer.parseInt(requestParams.get("assessPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "RS-RE-01") || StringUtils.equals(appr.getApprBizCd(), "RS-RE-02")) {
            // 평가결과 관리
            String imprClassCd = (requestParams.get("riskType").equals("work") ? "ICL41" : "ICL42");
            planmgmtService.resultUpdateAppr(Integer.parseInt(requestParams.get("assessDeptNo")), imprClassCd, bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-BD-01")) {
            // 예산편성 관리
            mgtBudgetingService.updateAppr(requestParams.get("budgetActDeptNo"), Integer.parseInt(requestParams.get("budgetActNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-BD-02")) {
            // 예산집행 관리
            mgtBudgetExecutionService.updateAppr(requestParams.get("budgetExecNo"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "CN-EV-02")) {
            // 평가및안전보건비용 계획
            assmnSfhlcService.updateAppr(Integer.parseInt(requestParams.get("vendorEvalPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "CN-EV-03")) {
            // 평가및안전보건비용 결과
            assmnSfhlcService.updateResultAppr(Integer.parseInt(requestParams.get("evalPlanVendorNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-EV-01")) {
            // 법정선임자 평가 계획
            electEvalPlanService.updateAppr(requestParams.get("evalPlanNo"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "MG-CM-02")) {
            // 종사자의견청취 결과 관리
            industrialSafetyHealthCommitteeService.apprProcessCommittee(Integer.parseInt(requestParams.get("committeeConfNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-MA-01")) {
            // 중대시민재해점검 관리
            majDisaInspService.updateAppr(requestParams.get("majDisaInspNo"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "SA-GV-01")) {
            govImplChkService.updateAppr(Integer.parseInt(requestParams.get("implChkPlanNo")), bizApprStepCd, apprRqstNo, appr.getApprUserId());
        } else if (StringUtils.equals(appr.getApprBizCd(), "IS-PL-01")) {
            // 점검계획 관리
            inspectionSHService.updateAppr(Integer.parseInt(requestParams.get("implChkPlanNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "IS-PL-02")) {
            // 점검결과 관리
            inspectionSHService.resultUpdateAppr(Integer.parseInt(requestParams.get("implChkDeptNo")), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "WK-EV-01")) {
            // 작업위험성평가 계획
            workRiskEvalPlanService.updateAppr(requestParams.get("plantCd"), requestParams.get("evalYear"), requestParams.get("evalNo"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "WK-EV-02")) {
            // 작업 위험성평가 결과 결재요청
            workRiskEval04Service.updateAppr(requestParams.get("plantCd"), requestParams.get("evalYear"), requestParams.get("evalNo"), requestParams.get("deptCd"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "WK-EV-03")) {
            // 작업 위험성평가 결과 검토
            workRiskEval05Service.updateAppr(requestParams.get("plantCd"), requestParams.get("evalYear"), requestParams.get("evalNo"), requestParams.get("deptCd"), bizApprStepCd, apprRqstNo);
        } else if (StringUtils.equals(appr.getApprBizCd(), "PS-PD-01")) {
            // 문서 관리
            psmDocuService.apprDeptPlanResult(Integer.parseInt(requestParams.get("psmDocuNo")), bizApprStepCd, apprRqstNo);
        }

    }

    /**
     * 일괄결제 처리
     *
     * @param appr
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateApprStepAll(Appr appr) throws Exception {
        int result = 0;

        if (CollectionUtils.isEmpty(appr.getApprRqstLineList())) {
            return 0;
        } else {
            List<ApprRqstLine> apprRqstLineList = appr.getApprRqstLineList();

            for (ApprRqstLine apprRqstLine : apprRqstLineList) {
                ApprRqstLine apprLine = this.apprMapper.getApprLine(apprRqstLine.getApprRqstNo(), apprRqstLine.getLineSeqNo());
                apprLine.setApprStepCd(apprRqstLine.getApprStepCd());
                // 결재 정보 유효성 검사
                // apprRqstLine.setApprUserId(appr.getApprUserId());
                int cnt = apprMapper.getCountApprRqstLine(apprLine);
                if (cnt <= 0) {
                    return 0;
                } else {
                    // 결재 처리 상태 수정
                    apprLine.setApprStepCd(ConstVal.COM_APPR_STEP_APPROVAL);
                    result = apprMapper.updateApprStep(apprLine);

                    if (result > 0) {
                        // 총 결재선 개수 조회
                        ApprRqstLine param = new ApprRqstLine();
                        param.setApprRqstNo(apprLine.getApprRqstNo());
                        int totAppr = apprMapper.getCountApprRqstLine(param);

                        if (totAppr <= (apprLine.getLineSeqNo() + 1)) {
                            // 최종 승인일때 결재요청 테이블 업무결재진행단계 업데이트
                            ApprRqst apprRqst = new ApprRqst();
                            apprRqst.setBizApprStepCd(ConstVal.COM_BIZ_APPR_STEP_COMPLETE); // 결재완료
                            apprRqst.setApprRqstNo(apprLine.getApprRqstNo());
                            result = apprMapper.updateBizApprStep(apprRqst);

                            if (result > 0) {
                                // 기안자에게 메일 발송 처리 필요..

                                // 각 업무별 상태 변경 api 호출
                                updateProcessStatus(apprLine.getApprRqstNo(), ConstVal.COM_BIZ_APPR_STEP_COMPLETE);
                            } else {
                                return 0;
                            }
                        } else {
                            // 다음 결재자에게 메일 발송 처리 필요..

                        }
                    } else {
                        return 0;
                    }
                }
            }
            return result;
        }
    }

    /**
     * 결재 요청 문서 조회
     *
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param bizApprStepCd
     *            결재진행단계코드
     * @param apprUserId
     *            사용자ID
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<Appr> getApprRequestList(String startDate, String endDate, String bizApprStepCd, String apprUserId, String apprRqstNm, DefaultParam defaultParam) throws Exception {
        return apprMapper.getApprRequestList(startDate, endDate, bizApprStepCd, apprUserId, apprRqstNm, defaultParam);
    }

    /**
     * 결재요청 상세 정보 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public Appr getApprRqstDetail(int apprRqstNo) throws Exception {
        if (apprRqstNo > 0) {
            return apprMapper.getApprRqstDetail(apprRqstNo); // 결재요청 상세 정보 조회
        } else {
            return null;
        }
    }

    /**
     * 반려 결재 상세 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public Appr getRejectApprDetail(int apprRqstNo, DefaultParam defaultParam) throws Exception {
        if (apprRqstNo > 0) {
            // 결재요청 상세 조회
            Appr appr = apprMapper.getApprRqstDetail(apprRqstNo);
            if (appr != null) {
                // 반려 결재선 목록 조회
                appr.setApprRqstLineList(apprMapper.getRejectApprLine(apprRqstNo, defaultParam));
                // 반려 결재 이력 조회
                appr.setApprRqstLineHistoryList(apprMapper.getApprLineHistory(apprRqstNo, defaultParam));
            }
            return appr;
        } else {
            return null;
        }
    }

    /**
     * 그룹웨어 결재 할 문서 조회
     *
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param apprYn
     *            처리여부
     * @param userId
     *            사용자ID
     * @param apprStepCd
     *            결재자처리상태코드
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<ApprGroupWare> getWhichApprsForGroupWare(String startDate, String endDate, String apprYn, String userId, String apprStepCd, DefaultParam defaultParam) throws Exception {
        return apprMapper.getWhichApprsForGroupWare(startDate, endDate, apprYn, userId, apprStepCd, defaultParam);
    }

    /**
     * 그룹웨어 결재 요청 문서 조회
     *
     * @param startDate
     *            결재요청시작일
     * @param endDate
     *            결재요청종료일
     * @param bizApprStepCd
     *            결재진행단계코드
     * @param userId
     *            사용자ID
     * @return 결재 할 문서 목록
     * @throws Exception
     */
    public List<ApprRqstGroupWare> getApprRequestListForGroupWare(String startDate, String endDate, String bizApprStepCd, String userId, DefaultParam defaultParam) throws Exception {
        return apprMapper.getApprRequestListForGroupWare(startDate, endDate, bizApprStepCd, userId, defaultParam);
    }

    /**
     * 상위결재자 변경
     *
     * @param apprLines
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    @Transactional
    public Integer updateApprLine(List<ApprRqstLine> apprLines, int apprRqstNo) throws Exception {
        int count = 0;
        // 상위 결재선 삭제
        count += this.apprMapper.deleteApprRqstLine(apprRqstNo);
        // 시퀀스 개수 구하기
        ApprRqstLine tmpApprRqstLine = new ApprRqstLine();
        tmpApprRqstLine.setApprRqstNo(apprRqstNo);
        int i = this.apprMapper.getCountApprRqstLine(tmpApprRqstLine);

        for (ApprRqstLine apprRqstLine : apprLines) {
            ApprRqstLine param = new ApprRqstLine();
            param.setApprRqstNo(apprRqstNo); // 결재요청번호
            param.setLineSeqNo(i); // 결재선순번
            param.setApprTypeCd(ConstVal.COM_APPR_TYPE_APPROVAL); // 결재자유형코드(결재)
            param.setApprUserId(apprRqstLine.getUserId() == null ? apprRqstLine.getApprUserId() : apprRqstLine.getUserId()); // 결재자
                                                                                                                             // ID
            count += this.apprMapper.createApprRqstLine(param);
            i++;
        }

        return count;
    }

    /**
     * 결재 중 문서 수정 권한 조회
     *
     * @param apprRqstNo
     * @param userId
     * @return
     * @throws Exception
     */
    public Boolean getAuthCheck(int apprRqstNo, String userId) throws Exception {
        boolean disabled = true;
        Appr appr = this.apprMapper.getApprRqstDetail(apprRqstNo);

        appr.setApprRqstLineList(this.apprMapper.getWhichApprLine(apprRqstNo, new DefaultParam("kr")));

        if (StringUtils.equals(appr.getBizApprStepCd(), ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            disabled = true;
        } else if (StringUtils.equals(appr.getBizApprStepCd(), ConstVal.COM_BIZ_APPR_STEP_ING)) {
            for (ApprRqstLine apprRqstLine : appr.getApprRqstLineList()) {
                if (apprRqstLine.getApprStepCd() == null) {
                    if (apprRqstLine.getDelegatorId() != null && apprRqstLine.getDelegatorId().equals(userId)) {
                        disabled = false;
                        break;
                    }
                    if (apprRqstLine.getApprUserId().equals(userId)) {
                        disabled = false;
                    }
                    break;
                }
            }
        } else {
            disabled = false;
        }

        return disabled;
    }

    /**
     * 결재 회수 가능한지 여부 조회
     *
     * @param apprRqstNo
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean getCollectCheck(int apprRqstNo, String userId) {
        boolean flag = false;

        List<ApprRqstLine> apprRqstLines = this.apprMapper.getCollectCheck(apprRqstNo);

        if (apprRqstLines.size() == 1) {
            flag = true;
        }

        for (ApprRqstLine apprRqstLine : apprRqstLines) {
            if (apprRqstLine.getApprTypeCd().equals(ConstVal.COM_APPR_TYPE_REQUESTER)) {
                if (!userId.equals(apprRqstLine.getApprUserId())) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    /**
     * 결재 회수
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int deleteAppr(int apprRqstNo) {
        // TODO Auto-generated method stub
        return this.apprMapper.deleteAppr(apprRqstNo);
    }

    /**
     * 결재 파라미터 수정
     *
     * @param apprRqstNo
     * @param apprParam
     * @return
     * @throws Exception
     */
    public int updateApprParam(int apprRqstNo, String apprParam) {
        return this.apprMapper.updateApprParam(apprRqstNo, apprParam);
    }

    /**
     * 결재 회수
     *
     * @param apprRqstNo
     *            결재번호
     *
     * @return
     * @throws Exception
     */
    public int collectAppr(int apprRqstNo) throws Exception {

        // 결재요청 상세 정보 조회
        Appr appr = apprMapper.getApprRqstDetail(apprRqstNo);

        if (appr == null) {
            return 0;
        } else {
            // 결재요청 파라미터 추출
            Map<String, String> requestParams = new HashMap<String, String>(); // api전달파라미터
            String reqParam = appr.getApprReqParameter();
            String[] params = reqParam.split("\\|");

            for (String param : params) {
                String[] splitParam = param.split("\\:");
                requestParams.put(splitParam[0], splitParam[1]);
            }

            this.apprCallback(appr, requestParams, 0, "");
            this.deleteAppr(apprRqstNo);

            return 1;
        }
    }

    /**
     * 대리 결재자 등록
     *
     * @param apprDelegates
     * @return
     * @throws Exception
     */
    @Transactional
    public HashMap<String, Object> createApprDelegate(List<ApprDelegate> apprDelegates, String userId) {
        this.apprMapper.deleteApprDelegates(userId, null, null, null);
        HashMap<String, Object> map;
        for (ApprDelegate apprDelegate : apprDelegates) {
            this.apprMapper.createApprDelegate(apprDelegate);
            map = this.apprMapper.checkApprDelegate(apprDelegate.getDelegatorId(), apprDelegate.getStartYmd(), apprDelegate.getEndYmd());

            if (map != null) {
                this.apprMapper.deleteApprDelegates(apprDelegate.getUserId(), apprDelegate.getDelegatorId(), apprDelegate.getStartYmd(), apprDelegate.getEndYmd());
                return map;
            }
        }
        return new HashMap<String, Object>();
    }

    /**
     * 대리 결재자 조회
     *
     * @param apprRqstLines
     * @return
     * @throws Exception
     */
    public List<ApprRqstLine> getApprdelegators(List<ApprRqstLine> apprRqstLines) {
        HashMap<String, Object> map;
        for (ApprRqstLine apprRqstLine : apprRqstLines) {
            map = this.apprMapper.getApprDelegator(apprRqstLine.getUserId());
            if (map != null && map.containsKey("delegator_id")) {
                apprRqstLine.setDelegatorId(map.get("delegator_id").toString());
                apprRqstLine.setDelegatorNm(map.get("delegator_nm").toString());
            }
        }
        return apprRqstLines;
    }

    /**
     * 결재 완료일 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public String getApprEndDt(int apprRqstNo) {
        return apprMapper.getApprEndDt(apprRqstNo);
    }

    /**
     * 결재진행상태 조회
     *
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getApprProgress(int apprRqstNo) {
        return apprMapper.getApprProgress(apprRqstNo);
    }

}
