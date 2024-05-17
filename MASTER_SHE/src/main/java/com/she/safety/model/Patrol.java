package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "순회점검결과")
public class Patrol {
    @ApiModelProperty(value = "안전점검결과번호")
    public int safCheckRsltNo;
    @ApiModelProperty(value = "안전점검종류번호")
    public int safCheckKindNo;
    @ApiModelProperty(value = "연간순회점검번호")
    public int patrolMstNo;
    @ApiModelProperty(value = "안전점검종류명")
    public String safCheckKindNm;
    @ApiModelProperty(value = "순회대상구분코드")
    public String tgtClsCd;
    @ApiModelProperty(value = "순회대상구분명")
    public String tgtClsNm;
    @ApiModelProperty(value = "대상부서코드")
    public String tgtDeptCd;
    @ApiModelProperty(value = "대상부서명")
    public String tgtDeptNm;
    @ApiModelProperty(value = "수행부서코드")
    public String pgpDeptCd;
	@ApiModelProperty(value = "수행부서명")
    public String pgpDeptNm;
    @ApiModelProperty(value = "사업장코드")
    public String plantCd;
    @ApiModelProperty(value = "사업장명")
    public String plantNm;
    @ApiModelProperty(value = "주관부서코드")
    public String deptCd;
    @ApiModelProperty(value = "주관부서명")
    public String deptNm;
    @ApiModelProperty(value = "점검일")
    public String checkYmd;
    @ApiModelProperty(value = "점검명")
    public String checkTitle;
    @ApiModelProperty(value = "결과요약")
    public String checkResult;
    @ApiModelProperty(value = "점검진행상태")
    public String checkStepCd;
    @ApiModelProperty(value = "점검계획진행상태")
    public String checkPlanStepCd;
    @ApiModelProperty(value = "점검계획진행상태")
    public String checkPlanStepNm;
    @ApiModelProperty(value = "점검진행상태명")
    public String checkStepNm;
    @ApiModelProperty(value = "점검예정일")
    public String checkSchYmd;
    @ApiModelProperty(value = "결재요청번호")
    public int apprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    public String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    public String bizApprStepNm;
    @ApiModelProperty(value = "점검결과코드")
    public String checkResultCd;
    @ApiModelProperty(value = "점검결과명")
    public String checkResultNm;
    @ApiModelProperty(value = "대상업체코드")
    public String tgtVendorCd;
    @ApiModelProperty(value = "대상업체명")
    public String tgtVendorNm;
    @ApiModelProperty(value = "일정명")
    public String title;
    @ApiModelProperty(value = "순회대상")
    public String tgt;
    @ApiModelProperty(value = "등록자")
    public String createUserId;
    @ApiModelProperty(value = "등록자명")
    public String createUserNm;
    @ApiModelProperty(value = "등록일")
    public String createDt;
    @ApiModelProperty(value = "수정자")
    public String updateUserId;
    @ApiModelProperty(value = "수정자명")
    public String updateUserNm;
    @ApiModelProperty(value = "수정일")
    public String updateDt;
    @ApiModelProperty(value = "부서/협력업체코드(공통) alias")
    public String tgtCd;
    @ApiModelProperty(value = "부서/협력업체명(공통) alias")
    public String tgtNm;
	@ApiModelProperty(value = "부서/협력업체코드(공통) alias")
    public String pgpCd;
    @ApiModelProperty(value = "부서/협력업체명(공통) alias")
    public String pgpNm;
    @ApiModelProperty(value = "부서/협력업체인지 판단 여부 alias")
    public String isDept;
    @ApiModelProperty(value = "순회 점검자")
    public List<PatrolInspector> patrolInspectors;
    @ApiModelProperty(value = "순회 점검결과")
    public List<PatrolItemResult> patrolItems;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public Patrol() {
        super();
    }

    public Patrol(int safCheckRsltNo, int safCheckKindNo, int patrolMstNo, String safCheckKindNm, String tgtClsCd, String tgtClsNm, String tgtDeptCd, String tgtDeptNm, String pgpDeptCd, String pgpDeptNm, String plantCd, String plantNm, String deptCd, String deptNm, String checkYmd, String checkTitle, String checkResult, String checkStepCd, String checkPlanStepCd,
            String checkPlanStepNm, String checkStepNm, String checkSchYmd, int apprRqstNo, String bizApprStepCd, String bizApprStepNm, String checkResultCd, String checkResultNm, String tgtVendorCd, String tgtVendorNm, String title, String tgt, String createUserId, String createUserNm, String createDt, String updateUserId, String updateUserNm,
            String updateDt, String tgtCd, String tgtNm, String pgpCd, String pgpNm, String isDept, List<PatrolInspector> patrolInspectors, List<PatrolItemResult> patrolItems, String writerUserNm, String writerDt) {
        super();
        this.safCheckRsltNo = safCheckRsltNo;
        this.safCheckKindNo = safCheckKindNo;
        this.patrolMstNo = patrolMstNo;
        this.safCheckKindNm = safCheckKindNm;
        this.tgtClsCd = tgtClsCd;
        this.tgtClsNm = tgtClsNm;
        this.tgtDeptCd = tgtDeptCd;
        this.tgtDeptNm = tgtDeptNm;
        this.pgpDeptCd = pgpDeptCd;
        this.pgpDeptNm = pgpDeptNm;
        this.plantCd = plantCd;
        this.plantNm = plantNm;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.checkYmd = checkYmd;
        this.checkTitle = checkTitle;
        this.checkResult = checkResult;
        this.checkStepCd = checkStepCd;
        this.checkPlanStepCd = checkPlanStepCd;
        this.checkPlanStepNm = checkPlanStepNm;
        this.checkStepNm = checkStepNm;
        this.checkSchYmd = checkSchYmd;
        this.apprRqstNo = apprRqstNo;
        this.bizApprStepCd = bizApprStepCd;
        this.bizApprStepNm = bizApprStepNm;
        this.checkResultCd = checkResultCd;
        this.checkResultNm = checkResultNm;
        this.tgtVendorCd = tgtVendorCd;
        this.tgtVendorNm = tgtVendorNm;
        this.title = title;
        this.tgt = tgt;
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateUserNm = updateUserNm;
        this.updateDt = updateDt;
        this.tgtCd = tgtCd;
        this.tgtNm = tgtNm;
        this.pgpCd = pgpCd;
        this.pgpNm = pgpNm;
        this.isDept = isDept;
        this.patrolInspectors = patrolInspectors;
        this.patrolItems = patrolItems;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
    }

    public int getSafCheckRsltNo() {
        return safCheckRsltNo;
    }

    public void setSafCheckRsltNo(int safCheckRsltNo) {
        this.safCheckRsltNo = safCheckRsltNo;
    }

    public int getSafCheckKindNo() {
        return safCheckKindNo;
    }

    public void setSafCheckKindNo(int safCheckKindNo) {
        this.safCheckKindNo = safCheckKindNo;
    }

    public int getPatrolMstNo() {
        return patrolMstNo;
    }

    public void setPatrolMstNo(int patrolMstNo) {
        this.patrolMstNo = patrolMstNo;
    }

    public String getSafCheckKindNm() {
        return safCheckKindNm;
    }

    public void setSafCheckKindNm(String safCheckKindNm) {
        this.safCheckKindNm = safCheckKindNm;
    }

    public String getTgtClsCd() {
        return tgtClsCd;
    }

    public void setTgtClsCd(String tgtClsCd) {
        this.tgtClsCd = tgtClsCd;
    }

    public String getTgtClsNm() {
        return tgtClsNm;
    }

    public void setTgtClsNm(String tgtClsNm) {
        this.tgtClsNm = tgtClsNm;
    }

    public String getTgtDeptCd() {
        return tgtDeptCd;
    }

    public void setTgtDeptCd(String tgtDeptCd) {
        this.tgtDeptCd = tgtDeptCd;
    }

    public String getTgtDeptNm() {
        return tgtDeptNm;
    }

    public void setTgtDeptNm(String tgtDeptNm) {
        this.tgtDeptNm = tgtDeptNm;
    }
    
    public String getPgpDeptCd() {
		return pgpDeptCd;
	}

	public void setPgpDeptCd(String pgpDeptCd) {
		this.pgpDeptCd = pgpDeptCd;
	}

	public String getPgpDeptNm() {
		return pgpDeptNm;
	}

	public void setPgpDeptNm(String pgpDeptNm) {
		this.pgpDeptNm = pgpDeptNm;
	}


    public String getPlantCd() {
        return plantCd;
    }

    public void setPlantCd(String plantCd) {
        this.plantCd = plantCd;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getCheckYmd() {
        return checkYmd;
    }

    public void setCheckYmd(String checkYmd) {
        this.checkYmd = checkYmd;
    }

    public String getCheckTitle() {
        return checkTitle;
    }

    public void setCheckTitle(String checkTitle) {
        this.checkTitle = checkTitle;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getCheckStepCd() {
        return checkStepCd;
    }

    public void setCheckStepCd(String checkStepCd) {
        this.checkStepCd = checkStepCd;
    }

    public String getCheckPlanStepCd() {
        return checkPlanStepCd;
    }

    public void setCheckPlanStepCd(String checkPlanStepCd) {
        this.checkPlanStepCd = checkPlanStepCd;
    }

    public String getCheckPlanStepNm() {
        return checkPlanStepNm;
    }

    public void setCheckPlanStepNm(String checkPlanStepNm) {
        this.checkPlanStepNm = checkPlanStepNm;
    }

    public String getCheckStepNm() {
        return checkStepNm;
    }

    public void setCheckStepNm(String checkStepNm) {
        this.checkStepNm = checkStepNm;
    }

    public String getCheckSchYmd() {
        return checkSchYmd;
    }

    public void setCheckSchYmd(String checkSchYmd) {
        this.checkSchYmd = checkSchYmd;
    }

    public int getApprRqstNo() {
        return apprRqstNo;
    }

    public void setApprRqstNo(int apprRqstNo) {
        this.apprRqstNo = apprRqstNo;
    }

    public String getBizApprStepCd() {
        return bizApprStepCd;
    }

    public void setBizApprStepCd(String bizApprStepCd) {
        this.bizApprStepCd = bizApprStepCd;
    }

    public String getBizApprStepNm() {
        return bizApprStepNm;
    }

    public void setBizApprStepNm(String bizApprStepNm) {
        this.bizApprStepNm = bizApprStepNm;
    }

    public String getCheckResultCd() {
        return checkResultCd;
    }

    public void setCheckResultCd(String checkResultCd) {
        this.checkResultCd = checkResultCd;
    }

    public String getCheckResultNm() {
        return checkResultNm;
    }

    public void setCheckResultNm(String checkResultNm) {
        this.checkResultNm = checkResultNm;
    }

    public String getTgtVendorCd() {
        return tgtVendorCd;
    }

    public void setTgtVendorCd(String tgtVendorCd) {
        this.tgtVendorCd = tgtVendorCd;
    }

    public String getTgtVendorNm() {
        return tgtVendorNm;
    }

    public void setTgtVendorNm(String tgtVendorNm) {
        this.tgtVendorNm = tgtVendorNm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTgt() {
        return tgt;
    }

    public void setTgt(String tgt) {
        this.tgt = tgt;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserNm() {
        return updateUserNm;
    }

    public void setUpdateUserNm(String updateUserNm) {
        this.updateUserNm = updateUserNm;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public String getTgtCd() {
        return tgtCd;
    }

    public void setTgtCd(String tgtCd) {
        this.tgtCd = tgtCd;
    }

    public String getTgtNm() {
        return tgtNm;
    }

    public void setTgtNm(String tgtNm) {
        this.tgtNm = tgtNm;
    }
    public String getPgpCd() {
		return pgpCd;
	}

	public void setPgpCd(String pgpCd) {
		this.pgpCd = pgpCd;
	}

	public String getPgpNm() {
		return pgpNm;
	}

	public void setPgpNm(String pgpNm) {
		this.pgpNm = pgpNm;
	}

    public String getIsDept() {
        return isDept;
    }

    public void setIsDept(String isDept) {
        this.isDept = isDept;
    }

    public List<PatrolInspector> getPatrolInspectors() {
        return patrolInspectors;
    }

    public void setPatrolInspectors(List<PatrolInspector> patrolInspectors) {
        this.patrolInspectors = patrolInspectors;
    }

    public List<PatrolItemResult> getPatrolItems() {
        return patrolItems;
    }

    public void setPatrolItems(List<PatrolItemResult> patrolItems) {
        this.patrolItems = patrolItems;
    }

    public String getWriterUserNm() {
        return writerUserNm;
    }

    public void setWriterUserNm(String writerUserNm) {
        this.writerUserNm = writerUserNm;
    }

    public String getWriterDt() {
        return writerDt;
    }

    public void setWriterDt(String writerDt) {
        this.writerDt = writerDt;
    }

}
