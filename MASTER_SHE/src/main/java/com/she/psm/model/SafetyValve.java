package com.she.psm.model;

import java.util.List;

import com.she.chm.model.Chemprod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "법인")
@Getter
@Setter
public class SafetyValve {

    private String safFacilityCd;

    private String count;

    private String facilityNm;

    private String plantCd;

    private String plantNm;

    private int processNo;

    private String processCd;

    private String processNm;

    private String revNum;

    private List<Chemprod> chemprods;

    private String inMat;

    private String condition;

    private String outVol;

    private String regulVol;

    private String nozSizeIn;

    private String nozSizeOut;

    private String tempPlan;

    private String tempOpr;

    private String protectSerial;

    private String protectOpr;

    private String protectPlan;

    private String valveSetting;

    private String valueMat;

    private String valveTrim;

    private String precision;

    private String outConnect;

    private String outReason;

    private String form;

    private String createUserId;

    private String updateUserId;

    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "MOC 번호")
    private String chngNum;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
