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

    @ApiModelProperty(value = "패스워드SALT")
    public String salt;

    public Set<UserAuthGrp> userAuthGroups = new HashSet<UserAuthGrp>(0);

    public List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

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

}
