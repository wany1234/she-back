package com.she.health.model;

public class SuspectHist {
    private int heaCheckupPlanNo;

    private String heaCheckupPlanNm;

    private String heaCheckupClassCd;

    private String heaCheckupClassNm;

    private String userId;

    private String userNm;

    private String changedDt;

    private String suspectYn;

    private String deptCd;

    private String deptNm;

    private String remark;

    private String createUserId;

    private String createDt;

    private String heaDiagnoseNms;

    private String heaDiseaseNms;

    public String getHeaCheckupPlanNm() {
        return heaCheckupPlanNm;
    }

    public void setHeaCheckupPlanNm(String heaCheckupPlanNm) {
        this.heaCheckupPlanNm = heaCheckupPlanNm;
    }

    public String getHeaCheckupClassCd() {
        return heaCheckupClassCd;
    }

    public void setHeaCheckupClassCd(String heaCheckupClassCd) {
        this.heaCheckupClassCd = heaCheckupClassCd;
    }

    public String getHeaCheckupClassNm() {
        return heaCheckupClassNm;
    }

    public void setHeaCheckupClassNm(String heaCheckupClassNm) {
        this.heaCheckupClassNm = heaCheckupClassNm;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getHeaDiagnoseNms() {
        return heaDiagnoseNms;
    }

    public void setHeaDiagnoseNms(String heaDiagnoseNms) {
        this.heaDiagnoseNms = heaDiagnoseNms;
    }

    public String getHeaDiseaseNms() {
        return heaDiseaseNms;
    }

    public void setHeaDiseaseNms(String heaDiseaseNms) {
        this.heaDiseaseNms = heaDiseaseNms;
    }

    public int getHeaCheckupPlanNo() {
        return heaCheckupPlanNo;
    }

    public String getChangedDt() {
        return changedDt;
    }

    public void setChangedDt(String changedDt) {
        this.changedDt = changedDt;
    }

    public void setHeaCheckupPlanNo(int heaCheckupPlanNo) {
        this.heaCheckupPlanNo = heaCheckupPlanNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSuspectYn() {
        return suspectYn;
    }

    public void setSuspectYn(String suspectYn) {
        this.suspectYn = suspectYn;
    }

    public String getDeptCd() {
        return deptCd;
    }

    public void setDeptCd(String deptCd) {
        this.deptCd = deptCd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
