package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "공사현황 작업구분별 업체")
@Getter
@Setter
public class ConstKindSubconn {
    @ApiModelProperty(value = "공사작업구분별_번호")
    private int constKindSubconnNo;
    @ApiModelProperty(value = "공사번호")
    private String constNo;
    @ApiModelProperty(value = "작업허가서구분")
    private String attr1;
    @ApiModelProperty(value = "작업구분번호")
    private String wkodKindCd;
    @ApiModelProperty(value = "작업구분명")
    private String wkodKindNm;
    @ApiModelProperty(value = "자체작업여부")
    private String selfYn;
    @ApiModelProperty(value = "업체코드")
    private String vendorCd;
    @ApiModelProperty(value = "업체명")
    private String vendorNm;
    @ApiModelProperty(value = "작업시작일")
    private String constStartYmd;
    @ApiModelProperty(value = "작업종료일")
    private String constEndYmd;
    @ApiModelProperty(value = "안전계획서등록여부")
    private String safPlanYn;
    @ApiModelProperty(value = "작업출입자등록여부")
    private String workPasserYn;
    @ApiModelProperty(value = "공사업체별진행단계")
    private String subconnStepCd;
    @ApiModelProperty(value = "산재번호")
    private String indAccNum;
    @ApiModelProperty(value = "2차도급업체")
    private String subconn2rdNm;
    @ApiModelProperty(value = "3차도급업체")
    private String subconn3rdNm;
    @ApiModelProperty(value = "공사현황 작업구분별 업체 출입자 리스트")
    private List<ConstKindSubconnWorker> constKindSubconnWorkers;
    @ApiModelProperty(value = "PO번호")
    private String constNum;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "공사명")
    private String constTitle;
    @ApiModelProperty(value = "공사내용")
    private String constContent;
    @ApiModelProperty(value = "공사인원수")
    private int workerNumb;
    @ApiModelProperty(value = "평가계획no")
    private int assessPlanNo;
    @ApiModelProperty(value = "평가명")
    private String assessNm;
}
