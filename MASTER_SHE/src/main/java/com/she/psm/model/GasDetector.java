package com.she.psm.model;

import lombok.Data;

@Data
public class GasDetector {

    private int gasDetectorNo;
    private String plantCd;
    private String plantNm;
    private String mgrNum;
    private String sensingObj;
    private String location;
    private String oprTm;
    private String detectMethod;
    // private String alarmVal;
    private String alarmDisplay;
    private String alarmRange;
    private String alarmLocation;
    private String precision;
    private String management;
    private String remark;
    private String measureinfo;
    private String useYn;
    private String useYnNm;
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
