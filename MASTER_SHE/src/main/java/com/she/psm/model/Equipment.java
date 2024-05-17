package com.she.psm.model;

import java.util.List;

import com.she.chm.model.Chemprod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "장치 및 설비")
@Getter
@Setter
public class Equipment {
    private String safFacilityCd;

    private List<Chemprod> chemprods;

    private int equipmentNo;

    private String count;

    private String facilityNm;

    private String plantCd;

    private String plantNm;

    private int processNo;

    private String processCd;

    private String processNm;

    private String revNum;

    private String inMat;

    private String vol;

    private String pressOpr;

    private String pressPlan;

    private String tempOpr;

    private String tempPlan;

    private String matMain;

    private String matSub;

    private String matGasket;

    private String weldEff;

    private String calcThick;

    private String corrosBuf;

    private String useThick;

    private String aftHeatTreatYn;

    private String aftHeatTreatYnNm;

    private String nondestructMethod;

    private String nondestructPer;

    private String actLaw;

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
