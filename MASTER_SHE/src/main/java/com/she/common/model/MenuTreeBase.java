package com.she.common.model;

import lombok.Data;

/**
 * menu tree를 구성할 base 모델 클래스 ※ 직접 메뉴 트리를 구성하지 않고 frontEnd에서 조합해서 만듦
 */
@Data
public class MenuTreeBase {

    private String menuId;

    private String menuNm;
    
    private String menuNmEn;
    
    private String menuNmJa;
    
    private String menuNmZhTw;
    
    private String menuNmZhCn;
    
    private String id;

    private String label;

    private String menuLvl;

    private String upMenuId;

    private String frontEndUrl;

    private String taskGrpCd;

    private String sortOrder;

    private String accessYn;

    private String writeYn;

    private String treeSortOrder;

    private String menuFullPath;

    private String useYn;

    private String useYnNm;

    private String color;

    private String icon;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    private int bbsNo;

}
