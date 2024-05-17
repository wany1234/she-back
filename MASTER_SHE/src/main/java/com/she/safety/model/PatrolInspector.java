package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "순회점검 점검자")
public class PatrolInspector {
    @ApiModelProperty(value = "점검자번호")
    private int safCheckInspectPsnNo;

    @ApiModelProperty(value = "점검자번호")
    private int safCheckRsltNo;

    @ApiModelProperty(value = "점검자구분코드")
    private String inspectorClassCd;

    @ApiModelProperty(value = "점검자구분코드명")
    private String inspectorClassNm;

    @ApiModelProperty(value = "사용자ID")
    private String userId;

    @ApiModelProperty(value = "성명")
    private String userNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "소속")
    private String deptNm;

    @ApiModelProperty(value = "직책")
    private String dutyNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    public int getSafCheckInspectPsnNo() {
        return safCheckInspectPsnNo;
    }

    public void setSafCheckInspectPsnNo(int safCheckInspectPsnNo) {
        this.safCheckInspectPsnNo = safCheckInspectPsnNo;
    }

    public int getSafCheckRsltNo() {
        return safCheckRsltNo;
    }

    public void setSafCheckRsltNo(int safCheckRsltNo) {
        this.safCheckRsltNo = safCheckRsltNo;
    }

    public String getInspectorClassCd() {
        return inspectorClassCd;
    }

    public void setInspectorClassCd(String inspectorClassCd) {
        this.inspectorClassCd = inspectorClassCd;
    }

    public String getInspectorClassNm() {
        return inspectorClassNm;
    }

    public void setInspectorClassNm(String inspectorClassNm) {
        this.inspectorClassNm = inspectorClassNm;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
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

    public String getDutyNm() {
        return dutyNm;
    }

    public void setDutyNm(String dutyNm) {
        this.dutyNm = dutyNm;
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

}
