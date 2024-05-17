package com.she.manage.model;

/**
 * 권한 그룹별 메뉴 모델
 */
public class AuthGrpMenu {
    private int authGrpNo;
    private String menuId;
    private String menuNm;
    private String id;
    private String label;
    private int menuLvl;
    private String upMenuId;
    private String accessYn;
    // private String allPlantAccessCd;
    private String writeYn;
    private String createUserId;
    private String createDt;

    // public String getAllPlantAccessCd() {
    // return allPlantAccessCd;
    // }
    //
    // public void setAllPlantAccessCd(String allPlantAccessCd) {
    // this.allPlantAccessCd = allPlantAccessCd;
    // }

    public AuthGrpMenu() {
    }

    public int getAuthGrpNo() {
        return authGrpNo;
    }

    public void setAuthGrpNo(int authGrpNo) {
        this.authGrpNo = authGrpNo;
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

    public int getMenuLvl() {
        return menuLvl;
    }

    public void setMenuLvl(int menuLvl) {
        this.menuLvl = menuLvl;
    }

    public String getUpMenuId() {
        return upMenuId;
    }

    public void setUpMenuId(String upMenuId) {
        this.upMenuId = upMenuId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
