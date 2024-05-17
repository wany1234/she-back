package com.she.manage.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "결재문서결재선")
@Data
public class ApprBizLineDtl {

    @ApiModelProperty(value = "결재문서마스터번호")
    private int apprBizNo;
    
    @ApiModelProperty(value = "결재문서결재선번호")
    private int apprBizLineNo;
    
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    
    @ApiModelProperty(value = "결재자 순번")
    private int lineSeqNo;
    
    @ApiModelProperty(value = "결재자 소속 구분")
    private String apprDeptTypeCd;
    
    @ApiModelProperty(value = "결재자 사업장 코드")
    private String apprUserPlantCd;
    
    @ApiModelProperty(value = "결재자 사업장 코드명")
    private String apprUserPlantNm;
    
    @ApiModelProperty(value = "결재자 부서 코드")
    private String apprUserDeptCd;
    
    @ApiModelProperty(value = "결재자 부서 코드명")
    private String apprUserDeptNm;
    
    @ApiModelProperty(value = "결재자 타입(DUTY:직책, NAME:이름)")
    private String apprUserTypeCd;
    
    @ApiModelProperty(value = "결재자(직책/ID)")
    private String apprUser;
    
    @ApiModelProperty(value = "결재자(직책/ID)명")
    private String apprUserNm;

    @ApiModelProperty(value = "사업장확인부서코드")
    private String plantConfirmDeptCd;
    
    @ApiModelProperty(value = "사업장확인부서명")
    private String plantConfirmDeptNm;
    
    @ApiModelProperty(value = "사업장확인부서실무자ID")
    private String plantConfWorkerId;
    
    @ApiModelProperty(value = "사업장확인부서실무자명")
    private String plantConfWorkerNm;
    
    @ApiModelProperty(value = "사업장확인부서담당자ID")
    private String plantConfChargerId;
    
    @ApiModelProperty(value = "사업장확인부서담당자명")
    private String plantConfChargerNm;
    
    @ApiModelProperty(value = "본사확인부서코드")
    private String hqConfirmDeptCd;
    
    @ApiModelProperty(value = "본사확인부서명")
    private String hqConfirmDeptNm;
    
    @ApiModelProperty(value = "본사확인부서실무자ID")
    private String hqConfWorkerId;
    
    @ApiModelProperty(value = "본사확인부서실무자명")
    private String hqConfWorkerNm;
    
    @ApiModelProperty(value = "본사확인부서담당자ID")
    private String hqConfChargerId;
    
    @ApiModelProperty(value = "본사확인부서담당자명")
    private String hqConfChargerNm;
}
