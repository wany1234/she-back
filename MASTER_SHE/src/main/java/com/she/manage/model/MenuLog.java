package com.she.manage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuLog {
    private int logNo;
    private String menuId;
    private String menuNm;
    private String menuPath;
    private String url;
    private String createUserId;
    private String createUserNm;
    private String logDt;
}
