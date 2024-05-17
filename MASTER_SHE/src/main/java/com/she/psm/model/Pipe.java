package com.she.psm.model;

import java.util.List;

import com.she.chm.model.Chemprod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "배관")
@Getter
@Setter
public class Pipe {
    private int pipeNo;

    private String pipeCd;

    private String pipeNm;

    private String planPress;

    private String planTemp;

    private String pipeMat;

    private String gasketMat;

    private String nondestructMethod;

    private String nondestructPer;

    private String aftHeatTreatYn;

    private String aftHeatTreatYnNm;

    private String plantCd;

    private String plantNm;

    private int processNo;

    private String processCd;

    private String processNm;

    private List<Chemprod> chemprods;

    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "MOC 번호")
    private String chngNum;

    private String createUserId;

    private String createDt;

    private String createDeptCd;

    private String createDeptNm;

    private String createPositionCd;

    private String createPositionNm;

    private String updateUserId;

    private String updateDt;

    private String updateDeptCd;

    private String updateDeptNm;

    private String updatePositionCd;

    private String updatePositionNm;

    private String writerUserNm;

    private String writerDt;
}
