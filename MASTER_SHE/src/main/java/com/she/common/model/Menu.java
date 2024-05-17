package com.she.common.model;

import lombok.Data;

/**
 * 메뉴 Model Class
 */
@Data
public class Menu {
    private String menuId;

    private String taskGrpCd;

    private String menuNm;
    
    private String menuNmEn;
    
    private String menuNmJa;
    
    private String menuNmZhTw;
    
    private String menuNmZhCn;
    
    private String upMenuId;

    private int menuLvl;

    private String frontEndUrl;

    private String icon;

    private String color;

    private int sortOrder;

    private String useYn;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    private boolean changeUpMenu;

    private int bbsNo;

}
