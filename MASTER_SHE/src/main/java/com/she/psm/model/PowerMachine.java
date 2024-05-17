package com.she.psm.model;

import java.util.List;

import com.she.chm.model.Chemprod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "동력기계")
@Getter
@Setter
public class PowerMachine {

    private String safFacilityCd;

    private String count;

    private String specInfo;

    private String matQuality;

    private String motorVol;

    private String protectType;

    private String invertorNm;

    private String facilityNm;

    private String plantCd;

    private String plantNm;

    private int processNo;

    private String processCd;

    private String processNm;

    private String revNum;

    private String createUserId;

    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "MOC 번호")
    private String chngNum;

    private List<Chemprod> chemprods;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
