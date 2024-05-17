package com.she.safety.model;

import java.util.List;

public class PatrolMaster {

    /**
     * 안전점검결과번호
     */
    public int patrolMstNo;

    /**
     * 사업장 코드
     */
    public String plantCd;

    /**
     * 사업장 명
     */
    public String plantNm;

    /**
     * 주관부서코드
     */
    public String deptCd;
    /**
     * 주관부서명
     */
    public String deptNm;
    
    // 수행부서
    public String pgpDeptCd;
	public String pgpDeptNm;
	
	public String bizApprStepNm;


	/**
     * 년도
     */
    public String year;

    /**
     * 점검계획시작일
     */
    public String checkPlanSYmd;

    /**
     * 점검계획종료일
     */
    public String checkPlanEYmd;

    /**
     * 점검명
     */
    public String title;

    /**
     * 점검진행상태
     */
    public String stepCd;

    /**
     * 점검진행상태명
     */
    public String stepNm;

    /**
     * 대상업체/부서 명
     */
    public String tgtNm;
    
    // 수행부서
    public String pgpNm;

	/**
     * 안전점검결과번호
     */
    public int safCheckKindNo;

    /**
     * 안전점검결과번호
     */
    public String safCheckKindNm;

    /**
     * 점검주기
     */
    public String checkPerd;

    /**
     * 점검결과결재진행상태
     */
    public String apprStepCd;

    /**
     * 결재진행상태번호
     */
    public int apprRqstNo;

    /**
     * 등록자
     */
    public String createUserId;

    /**
     * 등록자명
     */
    public String createUserNm;

    /**
     * 등록일
     */
    public String createDt;

    /**
     * 수정자
     */
    public String updateUserId;

    /**
     * 수정자명
     */
    public String updateUserNm;

    /**
     * 수정일
     */
    public String updateDt;

    /**
     * 작성자
     */
    public String writerUserNm;
    /**
     * 작성일
     */
    public String writerDt;
    public List<Patrol> patrolList;

    public PatrolMaster() {
        super();
    }

    public PatrolMaster(int patrolMstNo, String plantCd, String plantNm, String deptCd, String deptNm, String pgpDeptCd, String pgpDeptNm, String year, String checkPlanSYmd, String checkPlanEYmd, String title, String stepCd, String stepNm, String tgtNm, String pgpNm, int safCheckKindNo, String safCheckKindNm, String checkPerd, String apprStepCd, int apprRqstNo, String createUserId,
            String createUserNm, String createDt, String updateUserId, String updateUserNm, String updateDt, String writerUserNm, String writerDt, String bizApprStepNm, List<Patrol> patrolList) {
        super();
        this.patrolMstNo = patrolMstNo;
        this.plantCd = plantCd;
        this.plantNm = plantNm;
        this.deptCd = deptCd;
        this.deptNm = deptNm;
        this.pgpDeptCd = pgpDeptCd;
        this.pgpDeptNm = pgpDeptNm;
        this.year = year;
        this.checkPlanSYmd = checkPlanSYmd;
        this.checkPlanEYmd = checkPlanEYmd;
        this.title = title;
        this.stepCd = stepCd;
        this.stepNm = stepNm;
        this.tgtNm = tgtNm;
        this.pgpNm = pgpNm;
        this.safCheckKindNo = safCheckKindNo;
        this.safCheckKindNm = safCheckKindNm;
        this.checkPerd = checkPerd;
        this.apprStepCd = apprStepCd;
        this.apprRqstNo = apprRqstNo;
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateUserNm = updateUserNm;
        this.updateDt = updateDt;
        this.patrolList = patrolList;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
        this.bizApprStepNm = bizApprStepNm;
    }

    public int getPatrolMstNo() {
        return patrolMstNo;
    }

    public void setPatrolMstNo(int patrolMstNo) {
        this.patrolMstNo = patrolMstNo;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCheckPlanSYmd() {
        return checkPlanSYmd;
    }

    public void setCheckPlanSYmd(String checkPlanSYmd) {
        this.checkPlanSYmd = checkPlanSYmd;
    }

    public String getCheckPlanEYmd() {
        return checkPlanEYmd;
    }

    public void setCheckPlanEYmd(String checkPlanEYmd) {
        this.checkPlanEYmd = checkPlanEYmd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStepCd() {
        return stepCd;
    }

    public void setStepCd(String stepCd) {
        this.stepCd = stepCd;
    }

    public String getStepNm() {
        return stepNm;
    }

    public void setStepNm(String stepNm) {
        this.stepNm = stepNm;
    }

    public String getTgtNm() {
        return tgtNm;
    }

    public void setTgtNm(String tgtNm) {
        this.tgtNm = tgtNm;
    }
    
    public String getPgpNm() {
		return pgpNm;
	}

	public void setPgpNm(String pgpNm) {
		this.pgpNm = pgpNm;
	}

    public int getSafCheckKindNo() {
        return safCheckKindNo;
    }

    public void setSafCheckKindNo(int safCheckKindNo) {
        this.safCheckKindNo = safCheckKindNo;
    }

    public String getSafCheckKindNm() {
        return safCheckKindNm;
    }

    public void setSafCheckKindNm(String safCheckKindNm) {
        this.safCheckKindNm = safCheckKindNm;
    }

    public String getCheckPerd() {
        return checkPerd;
    }

    public void setCheckPerd(String checkPerd) {
        this.checkPerd = checkPerd;
    }

    public String getApprStepCd() {
        return apprStepCd;
    }

    public void setApprStepCd(String apprStepCd) {
        this.apprStepCd = apprStepCd;
    }

    public int getApprRqstNo() {
        return apprRqstNo;
    }

    public void setApprRqstNo(int apprRqstNo) {
        this.apprRqstNo = apprRqstNo;
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

    public List<Patrol> getPatrolList() {
        return patrolList;
    }

    public void setPatrolList(List<Patrol> patrolList) {
        this.patrolList = patrolList;
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
	
	public String getBizApprStepNm() {
		return bizApprStepNm;
	}

	public void setBizApprStepNm(String bizApprStepNm) {
		this.bizApprStepNm = bizApprStepNm;
	}

}
