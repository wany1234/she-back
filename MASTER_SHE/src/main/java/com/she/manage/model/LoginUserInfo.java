package com.she.manage.model;

import java.util.ArrayList;
import java.util.List;

public class LoginUserInfo {
    public User user;
    public String token;
    public List<String> auths;
    public VendorUser vendor;
    public DeptAuthGrp deptAuthGrp;
    public List<UserAuthGrp> userAuthGrps;

    public List<UserAuthGrp> getUserAuthGrps() {
        return userAuthGrps;
    }

    public void setUserAuthGrps(List<UserAuthGrp> userAuthGrps) {
        this.userAuthGrps = userAuthGrps;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginUserInfo() {
        this.auths = new ArrayList<>();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getAuths() {
        return auths;
    }

    public void setAuths(List<String> auths) {
        this.auths = auths;
    }

    public VendorUser getVendor() {
        return vendor;
    }

    public void setVendor(VendorUser vendor) {
        this.vendor = vendor;
    }

    public DeptAuthGrp getDeptAuthGrp() {
        return deptAuthGrp;
    }

    public void setDeptAuthGrp(DeptAuthGrp deptAuthGrp) {
        this.deptAuthGrp = deptAuthGrp;
    }

}
