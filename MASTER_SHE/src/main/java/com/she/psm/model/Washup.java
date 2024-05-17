package com.she.psm.model;

import lombok.Data;

@Data
public class Washup {

    private int washupNo;
    private String plantCd;
    private String plantNm;
    private String usage;
    private String location;
    private String warmMethod;
    private String remark;
    private String revNum;
    private String revContents;
    private String createrId;
    private String createDt;
    private String updaterId;
    private String updateDt;
    private int safChngNo;
    private String chngNum;
    private String writerUserNm;
    private String writerDt;
}
