package com.she.psm.model;

import lombok.Data;

@Data
public class LocalVentilation {

    private int localVentilationNo;
    private String plantCd;
    private String plantNm;
    private String source;
    private String processNm;
    private String inoutFlag;
    private String harmfulType;
    private String hoodForm;
    private String hoodVelocity;
    private String reactionVelocity;
    // private String airVol;
    private String exairVol;
    private String prairVol;
    private String motorVol;
    private String motorEpForm;
    private String airExhaustOrder;
    private String remark;
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
