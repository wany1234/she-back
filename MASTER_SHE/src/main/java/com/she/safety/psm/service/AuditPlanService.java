package com.she.safety.psm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.model.SafPsmAuditRsltDept;
import com.she.safety.model.SafPsmAuditRsltStd;
import com.she.safety.model.SafPsmAuditRsltUser;
import com.she.safety.psm.mapper.AuditPlanMapper;
import com.she.utils.ConstVal;

/*
* PSM 계획 기능정의
*
*/
@Service
public class AuditPlanService {

    @Autowired
    private AuditPlanMapper auditPlanMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private AttachFileService attachFileService;

    /**
     * PSM 계획 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 계획 목록
     * @throws Exception
     */
    public List<SafPsmAuditRslt> getAuditPlans(HashMap<String, Object> parameter, DefaultParam defaultParam) throws Exception {
        return auditPlanMapper.getAuditPlans(parameter, defaultParam);
    }

    /**
     * PSM 계획 등록
     * 
     * @param safPsmAuditRslt
     *            PSM 계획
     * @return PSM 계획 키
     */
    @Transactional
    public int createAuditPlan(SafPsmAuditRslt safPsmAuditRslt, String taskClassNm, List<String> tempIds, MultipartFile[] files) throws Exception {
        // 계획 등록
        safPsmAuditRslt.setPsmProgState(ConstVal.CODE_MASTER_PSM_PROG_STATE_WRITE);
        auditPlanMapper.createSafPsmAuditRslt(safPsmAuditRslt);

        if (safPsmAuditRslt.getAuditRsltNo() > 0) {
            // 대상부서
            if (safPsmAuditRslt.getSafPsmAuditRsltDepts() != null && safPsmAuditRslt.getSafPsmAuditRsltDepts().size() > 0) {
                for (SafPsmAuditRsltDept safPsmAuditRsltDept : safPsmAuditRslt.getSafPsmAuditRsltDepts()) {
                    safPsmAuditRsltDept.setAuditRsltNo(safPsmAuditRslt.getAuditRsltNo());
                    auditPlanMapper.createSafPsmAuditRsltDept(safPsmAuditRsltDept);

                    if (safPsmAuditRslt.getSafPsmAuditRsltStds() != null && safPsmAuditRslt.getSafPsmAuditRsltStds().size() > 0) {

                        for (SafPsmAuditRsltStd safPsmAuditRsltStd : safPsmAuditRslt.getSafPsmAuditRsltStds()) {
                            safPsmAuditRsltStd.setAuditRsltNo(safPsmAuditRslt.getAuditRsltNo());
                            safPsmAuditRsltStd.setCreateUserId(safPsmAuditRslt.getCreateUserId());
                            safPsmAuditRsltStd.setTargetDeptCd(safPsmAuditRsltDept.getTargetDeptCd());
                            // 감사기준
                            auditPlanMapper.createSafPsmAuditRsltStd(safPsmAuditRsltStd);

                            // 감사항목
                            auditPlanMapper.createSafPsmAuditRsltItem(safPsmAuditRsltStd);

                            // attachFileService.uploadTableFiles(String.valueOf(safPsmAuditRsltStd.getAuditRsltDeptStdNo()),
                            // safPsmAuditRsltStd.getTempId(),
                            // safPsmAuditRslt.getCreateUserId(), taskClassNm,
                            // tempIds, files);
                        }
                    }
                }
            }
            // 감사원
            if (safPsmAuditRslt.getSafPsmAuditRsltUsers() != null && safPsmAuditRslt.getSafPsmAuditRsltUsers().size() > 0) {
                for (SafPsmAuditRsltUser safPsmAuditRsltUser : safPsmAuditRslt.getSafPsmAuditRsltUsers()) {
                    safPsmAuditRsltUser.setAuditRsltNo(safPsmAuditRslt.getAuditRsltNo());
                    safPsmAuditRsltUser.setCreateUserId(safPsmAuditRslt.getCreateUserId());
                    auditPlanMapper.createSafPsmAuditRsltUser(safPsmAuditRsltUser);
                }
            }
        }
        return safPsmAuditRslt.getAuditRsltNo();
    }

    /**
     * PSM 계획 상세 조회
     * 
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return PSM 계획
     * @throws Exception
     */
    public SafPsmAuditRslt getAuditPlan(int auditRsltNo, DefaultParam defaultParam) throws Exception {

        // 계획 조회
        SafPsmAuditRslt safPsmAuditRslt = auditPlanMapper.getSafPsmAuditRslt(auditRsltNo, defaultParam);
        // 대상부서 조회
        safPsmAuditRslt.setSafPsmAuditRsltDepts(auditPlanMapper.getSafPsmAuditRsltDepts(auditRsltNo, defaultParam));
        // 감사원
        safPsmAuditRslt.setSafPsmAuditRsltUsers(auditPlanMapper.getSafPsmAuditRsltUsers(auditRsltNo, defaultParam));
        // 감사기준
        safPsmAuditRslt.setSafPsmAuditRsltStds(auditPlanMapper.getSafPsmAuditRsltStds(auditRsltNo, defaultParam));
        // 감사항목
        if (safPsmAuditRslt.getSafPsmAuditRsltStds() != null && safPsmAuditRslt.getSafPsmAuditRsltStds().size() > 0) {
            for (SafPsmAuditRsltStd safPsmAuditRsltStd : safPsmAuditRslt.getSafPsmAuditRsltStds()) {
                safPsmAuditRsltStd.setFiles(attachFileMapper.getUploadFiles("AUDIT_STD", Integer.toString(safPsmAuditRsltStd.getAuditStdNo())));
                safPsmAuditRsltStd.setSafPsmAuditRsltItems(auditPlanMapper.getSafPsmAuditRsltItems(safPsmAuditRsltStd.getAuditStdNo(), defaultParam));
            }
        }

        return safPsmAuditRslt;
    }

    /**
     * PSM 계획 수정
     * 
     * @param safPsmAuditRslt
     *            PSM 계획
     * @return PSM 계획 키
     */
    @Transactional
    public int updateAuditPlan(SafPsmAuditRslt safPsmAuditRslt, String taskClassNm, List<String> tempIds, MultipartFile[] files) throws Exception {
        // 계획 수정
        safPsmAuditRslt.setPsmProgState(ConstVal.CODE_MASTER_PSM_PROG_STATE_WRITE);
        auditPlanMapper.updateSafPsmAuditRslt(safPsmAuditRslt);

        auditPlanMapper.deleteSafPsmAuditRsltStdAll(safPsmAuditRslt.getAuditRsltNo());
        auditPlanMapper.deleteSafPsmAuditRsltItemAll(safPsmAuditRslt.getAuditRsltNo());

        auditPlanMapper.deleteSafPsmAuditRsltDept(safPsmAuditRslt.getAuditRsltNo());

        if (safPsmAuditRslt.getSafPsmAuditRsltDepts() != null && safPsmAuditRslt.getSafPsmAuditRsltDepts().size() > 0) {
            for (SafPsmAuditRsltDept safPsmAuditRsltDept : safPsmAuditRslt.getSafPsmAuditRsltDepts()) {
                safPsmAuditRsltDept.setAuditRsltNo(safPsmAuditRslt.getAuditRsltNo());
                auditPlanMapper.createSafPsmAuditRsltDept(safPsmAuditRsltDept);

                if (safPsmAuditRslt.getSafPsmAuditRsltStds() != null && safPsmAuditRslt.getSafPsmAuditRsltStds().size() > 0) {
                    // 감사기준
                    for (SafPsmAuditRsltStd safPsmAuditRsltStd : safPsmAuditRslt.getSafPsmAuditRsltStds()) {
                        // if(safPsmAuditRsltStd.getAuditRsltNo() > 0) {
                        // safPsmAuditRsltStd.setUpdateUserId(safPsmAuditRslt.getUpdateUserId());
                        // safPsmAuditRsltStd.setTargetDeptCd(safPsmAuditRsltDept.getTargetDeptCd());
                        // auditPlanMapper.updateSafPsmAuditRsltStd(safPsmAuditRsltStd);
                        // } else {
                        safPsmAuditRsltStd.setAuditRsltNo(safPsmAuditRslt.getAuditRsltNo());
                        safPsmAuditRsltStd.setCreateUserId(safPsmAuditRslt.getUpdateUserId());
                        safPsmAuditRsltStd.setTargetDeptCd(safPsmAuditRsltDept.getTargetDeptCd());
                        auditPlanMapper.createSafPsmAuditRsltStd(safPsmAuditRsltStd);

                        // 감사항목
                        auditPlanMapper.createSafPsmAuditRsltItem(safPsmAuditRsltStd);
                        // }
                        // attachFileService.uploadTableFiles(String.valueOf(safPsmAuditRsltStd.getAuditRsltDeptStdNo()),
                        // safPsmAuditRsltStd.getTempId(),
                        // safPsmAuditRslt.getUpdateUserId(), taskClassNm,
                        // tempIds, files);
                    }
                }
            }
        }
        // 감사원
        if (safPsmAuditRslt.getSafPsmAuditRsltUsers() != null && safPsmAuditRslt.getSafPsmAuditRsltUsers().size() > 0) {
            for (SafPsmAuditRsltUser safPsmAuditRsltUser : safPsmAuditRslt.getSafPsmAuditRsltUsers()) {
                if (safPsmAuditRsltUser.getAuditRsltNo() > 0) {
                    safPsmAuditRsltUser.setUpdateUserId(safPsmAuditRslt.getUpdateUserId());
                    auditPlanMapper.updateSafPsmAuditRsltUser(safPsmAuditRsltUser);
                } else {
                    safPsmAuditRsltUser.setAuditRsltNo(safPsmAuditRslt.getAuditRsltNo());
                    safPsmAuditRsltUser.setCreateUserId(safPsmAuditRslt.getUpdateUserId());
                    auditPlanMapper.createSafPsmAuditRsltUser(safPsmAuditRsltUser);
                }
            }
        }

        // 감사원 삭제
        if (safPsmAuditRslt.getDeleteSafPsmAuditRsltUsers() != null && safPsmAuditRslt.getDeleteSafPsmAuditRsltUsers().size() > 0) {
            for (SafPsmAuditRsltUser safPsmAuditRsltUser : safPsmAuditRslt.getDeleteSafPsmAuditRsltUsers()) {
                if (safPsmAuditRsltUser.getAuditRsltNo() > 0) {
                    auditPlanMapper.deleteSafPsmAuditRsltUser(safPsmAuditRsltUser);
                }
            }
        }

        return safPsmAuditRslt.getAuditRsltNo();
    }

    /**
     * PSM 계획 삭제
     * 
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return 키
     * @throws Exception
     */
    @Transactional
    public int deleteAuditPlan(List<SafPsmAuditRslt> safPsmAuditRslts) throws Exception {
        int resultNo = 0;
        if (safPsmAuditRslts != null && safPsmAuditRslts.size() > 0) {
            for (SafPsmAuditRslt safPsmAuditRslt : safPsmAuditRslts) {
                // 항목 삭제
                auditPlanMapper.deleteSafPsmAuditRsltItemAll(safPsmAuditRslt.getAuditRsltNo());
                // 기준 삭제
                auditPlanMapper.deleteSafPsmAuditRsltStdAll(safPsmAuditRslt.getAuditRsltNo());
                // 감사원 삭제
                auditPlanMapper.deleteSafPsmAuditRsltUserAll(safPsmAuditRslt.getAuditRsltNo());
                // 대상부서 삭제
                auditPlanMapper.deleteSafPsmAuditRsltDept(safPsmAuditRslt.getAuditRsltNo());
                // 계획 삭제
                resultNo += auditPlanMapper.deleteSafPsmAuditRslt(safPsmAuditRslt.getAuditRsltNo());
            }
        }

        return resultNo;
    }

    /**
     * PSM 진행 상태 변경
     * 
     * @param safChngNo
     *            변경관리 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int apprProcessAuditPlan(int auditRsltNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String imprStepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
            imprStepCd = ConstVal.CODE_MASTER_PSM_PROG_STATE_WRITE;

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 계획결재완료
            imprStepCd = ConstVal.CODE_MASTER_PSM_PROG_STATE_APPR_COMPLETE;

        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(bizApprStepCd)) {
            // 계획중
            imprStepCd = ConstVal.CODE_MASTER_PSM_PROG_STATE_APPR_REQUEST;

        }
        return auditPlanMapper.completeImpr(auditRsltNo, apprRqstNo, imprStepCd);
    }

    /**
     * PSM 계획 중복 조회
     * 
     * @param plantCd
     *            사업장코드
     * @param auditYear
     *            감사년도
     * @return 카운트
     */
    public int auditplanCheck(String plantCd, String auditYear, DefaultParam defaultParam) throws Exception {
        return auditPlanMapper.auditplanCheck(plantCd, auditYear, defaultParam);
    }

}
