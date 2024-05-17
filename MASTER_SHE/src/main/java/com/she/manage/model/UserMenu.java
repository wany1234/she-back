package com.she.manage.model;

/**
 * 사용자별 메뉴 모델
 */
public class UserMenu {
    private String userId;
    private String userNm;
    private String deptNm;
    private String menuId;
    private String menuNm;
    private String menuLvl;
    private String upMenuId;
    private String frontEndUrl;
    private String sortOrder;
    private String accessYn;
    private String writeYn;
    private String authGrpMenuYn;
    private String allPlantAccessCd;
    private String dlgtFromDt;
    private String dlgtToDt;
    private String menuFullPath;
    private String createUserId;
    private String color;
    private String icon;

    public String getAllPlantAccessCd() {
        return allPlantAccessCd;
    }

    public void setAllPlantAccessCd(String allPlantAccessCd) {
        this.allPlantAccessCd = allPlantAccessCd;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public UserMenu() {
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

    public String getDeptNm() {
        return deptNm;
    }

    public void setDeptNm(String deptNm) {
        this.deptNm = deptNm;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public String getMenuLvl() {
        return menuLvl;
    }

    public void setMenuLvl(String menuLvl) {
        this.menuLvl = menuLvl;
    }

    public String getUpMenuId() {
        return upMenuId;
    }

    public void setUpMenuId(String upMenuId) {
        this.upMenuId = upMenuId;
    }

    public String getFrontEndUrl() {
        return frontEndUrl;
    }

    public void setFrontEndUrl(String frontEndUrl) {
        this.frontEndUrl = frontEndUrl;
    }

    public String getAccessYn() {
        return accessYn;
    }

    public void setAccessYn(String accessYn) {
        this.accessYn = accessYn;
    }

    public String getWriteYn() {
        return writeYn;
    }

    public void setWriteYn(String writeYn) {
        this.writeYn = writeYn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getMenuFullPath() {
        return menuFullPath;
    }

    public void setMenuFullPath(String menuFullPath) {
        this.menuFullPath = menuFullPath;
    }

    public String getAuthGrpMenuYn() {
        return authGrpMenuYn;
    }

    public void setAuthGrpMenuYn(String authGrpMenuYn) {
        this.authGrpMenuYn = authGrpMenuYn;
    }

    public String getDlgtFromDt() {
        return dlgtFromDt;
    }

    public void setDlgtFromDt(String dlgtFromDt) {
        this.dlgtFromDt = dlgtFromDt;
    }

    public String getDlgtToDt() {
        return dlgtToDt;
    }

    public void setDlgtToDt(String dlgtToDt) {
        this.dlgtToDt = dlgtToDt;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
}
