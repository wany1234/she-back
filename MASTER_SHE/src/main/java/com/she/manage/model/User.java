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

package com.she.manage.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.swagger.annotations.ApiModelProperty;

public class User {
    public String userId;

    public String userPwd;

    public String userPwdSHA;

    public String userPwdRepeat;

    public String userNm;

    public String deptCd;

    public String deptNm;

    public String plantCd;

    public String plantNm;

    public String dutyCd;

    public String dutyNm;

    public String positionCd;

    public String positionNm;
    
    public String jobCd;

    public String jobNm;

    public String comSexTypeCd;

    public String comSexTypeNm;

    public String officeTel;

    public String phoneNo;

    public String email;

    public String birthYmd;

    public String entryYmd;

    public String useYn;

    public String useYnNm;

    public String createUserId;

    public String createUserNm;

    public String createDt;

    public String updateUserId;

    public String updateUserNm;

    public String updateDt;

    public String signature;
    
    public String imageUrl;
    
	public String age;

    @ApiModelProperty(value = "패스워드SALT")
    public String salt;

    public Set<UserAuthGrp> userAuthGroups = new HashSet<UserAuthGrp>(0);

    public List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
    public String loginId;

    @ApiModelProperty(value = "인사부서코드")
    private String deptCdHr;

    @ApiModelProperty(value = "인사부서")
    private String deptNmHr;

    @ApiModelProperty(value = "관리감독자여부")
    private String mgrSvYn;

    @ApiModelProperty(value = "임시직여부")
    private String tempUserYn;

    @ApiModelProperty(value = "외부직원여부")
    private String exUserYn;

    @ApiModelProperty(value = "승인권한여부")
    private String apprAuthYn;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "휴직시작일")
    private String leaveSdt;

    @ApiModelProperty(value = "휴직종료일")
    private String leaveEdt;

    @ApiModelProperty(value = "부서배치일")
    private String instYmd;

    @ApiModelProperty(value = "퇴사일")
    private String retireYmd;

    @ApiModelProperty(value = "(C)CLASS코드")
    private String userClsCd;

    @ApiModelProperty(value = "CLASS")
    private String userClsNm;

    @ApiModelProperty(value = "비밀번호 초기화여부")
    private String userPwdInitYn;

    @ApiModelProperty(value = "사용자 이름(영문)")
    private String userNmEn;

    public User() {

    }

    public User(String userId, String userPwdSHA) {
        super();
        this.userId = userId;
        this.userPwdSHA = userPwdSHA;
    }

    public String getDutyCd() {
        return dutyCd;
    }

    public void setDutyCd(String dutyCd) {
        this.dutyCd = dutyCd;
    }

    public String getDutyNm() {
        return dutyNm;
    }

    public void setDutyNm(String dutyNm) {
        this.dutyNm = dutyNm;
    }

    public String getPositionCd() {
        return positionCd;
    }

    public void setPositionCd(String positionCd) {
        this.positionCd = positionCd;
    }

    public String getPositionNm() {
        return positionNm;
    }

    public void setPositionNm(String positionNm) {
        this.positionNm = positionNm;
    }

    public String getJobCd() {
        return jobCd;
    }

    public void setJobCd(String jobCd) {
        this.jobCd = jobCd;
    }

    public String getJobNm() {
        return jobNm;
    }

    public void setJobNm(String jobNm) {
        this.jobNm = jobNm;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwdSHA() {
        return userPwdSHA;
    }

    public void setUserPwdSHA(String userPwdSHA) {
        this.userPwdSHA = userPwdSHA;
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

    public String getComSexTypeCd() {
        return comSexTypeCd;
    }

    public void setComSexTypeCd(String comSexTypeCd) {
        this.comSexTypeCd = comSexTypeCd;
    }

    public String getComSexTypeNm() {
        return comSexTypeNm;
    }

    public void setComSexTypeNm(String comSexTypeNm) {
        this.comSexTypeNm = comSexTypeNm;
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthYmd() {
        return birthYmd;
    }

    public void setBirthYmd(String birthYmd) {
        this.birthYmd = birthYmd;
    }

    public String getEntryYmd() {
        return entryYmd;
    }

    public void setEntryYmd(String entryYmd) {
        this.entryYmd = entryYmd;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Set<UserAuthGrp> getUserAuthGroups() {
        return userAuthGroups;
    }

    public void setUserAuthGroups(Set<UserAuthGrp> userAuthGroups) {
        this.userAuthGroups = userAuthGroups;
    }

    public List<GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return roles;
        }
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPwdRepeat() {
        return userPwdRepeat;
    }

    public void setUserPwdRepeat(String userPwdRepeat) {
        this.userPwdRepeat = userPwdRepeat;
    }

    public String getDeptCdHr() {
        return deptCdHr;
    }

    public void setDeptCdHr(String deptCdHr) {
        this.deptCdHr = deptCdHr;
    }

    public String getMgrSvYn() {
        return mgrSvYn;
    }

    public void setMgrSvYn(String mgrSvYn) {
        this.mgrSvYn = mgrSvYn;
    }

    public String getTempUserYn() {
        return tempUserYn;
    }

    public void setTempUserYn(String tempUserYn) {
        this.tempUserYn = tempUserYn;
    }

    public String getExUserYn() {
        return exUserYn;
    }

    public void setExUserYn(String exUserYn) {
        this.exUserYn = exUserYn;
    }

    public String getApprAuthYn() {
        return apprAuthYn;
    }

    public void setApprAuthYn(String apprAuthYn) {
        this.apprAuthYn = apprAuthYn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLeaveSdt() {
        return leaveSdt;
    }

    public void setLeaveSdt(String leaveSdt) {
        this.leaveSdt = leaveSdt;
    }

    public String getLeaveEdt() {
        return leaveEdt;
    }

    public void setLeaveEdt(String leaveEdt) {
        this.leaveEdt = leaveEdt;
    }

    public String getInstYmd() {
        return instYmd;
    }

    public void setInstYmd(String instYmd) {
        this.instYmd = instYmd;
    }

    public String getRetireYmd() {
        return retireYmd;
    }

    public void setRetireYmd(String retireYmd) {
        this.retireYmd = retireYmd;
    }

    public String getUserClsCd() {
        return userClsCd;
    }

    public void setUserClsCd(String userClsCd) {
        this.userClsCd = userClsCd;
    }

    public String getUserClsNm() {
        return userClsNm;
    }

    public void setUserClsNm(String userClsNm) {
        this.userClsNm = userClsNm;
    }

    public String getUserPwdInitYn() {
        return userPwdInitYn;
    }

    public void setUserPwdInitYn(String userPwdInitYn) {
        this.userPwdInitYn = userPwdInitYn;
    }

    public String getUserNmEn() {
        return userNmEn;
    }

    public void setUserNmEn(String userNmEn) {
        this.userNmEn = userNmEn;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDeptNmHr() {
        return deptNmHr;
    }

    public void setDeptNmHr(String deptNmHr) {
        this.deptNmHr = deptNmHr;
    }
    
    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
