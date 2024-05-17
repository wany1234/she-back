package com.she.psm.model;

import lombok.Data;

@Data
public class FireDetection {

    private int fireDetectionNo;
    private String plantCd;
    private String plantNm;
    private String location;
    private String singleAlarm;
    private String emergencyAlarm;
    private String startAlarm;
    private String autoDetection;
    private String emergencyBroadcast;
    private String autoBreakingnews;
    private String integrationSystem;
    private String shortAlarm;
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
