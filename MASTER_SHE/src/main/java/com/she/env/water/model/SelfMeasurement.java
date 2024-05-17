package com.she.env.water.model;

import java.util.List;

import lombok.Data;

@Data
public class SelfMeasurement {

    private int ewtrMeasNo;
    private int ewtrMonPosNo;

    private String ewtrMonPosNm;

    private String measureYmd;

    private String plantCd;

    private String plantNm;

    private String deptCd;

    private String deptNm;

    private String envEngrNm;

    private String weatherCd;

    private String weatherNm;

    private String temp;

    private String envEngrOpn;

    private String measMthodCd;

    private String measMthodNm;

    private String apprStepCd;

    private String apprStepNm;

    private String remarks;

    private int apprRqstNo;

    private String bizApprStepCd;

    private String bizApprStepNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private List<SelfMeasurementResult> selfMeasureResult;

}
