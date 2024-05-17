/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.safety.model;

import java.util.List;

import com.she.rsa.model.RiskHazard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "작업허가서 탭 데이터")
@Getter
@Setter
public class WkodMasterTabData {

    @ApiModelProperty(value = "보호구목록")
    private List<String> wkodSpeCds;

    @ApiModelProperty(value = "특별작업구분목록")
    private List<WkodSpeKind> wkodSpeKinds;

    @ApiModelProperty(value = "작업구분목록")
    private List<WkodKind> wkodKinds;

    @ApiModelProperty(value = "가스농도측정목록")
    private List<WkodGasMeas> wkodGasMeass;

    @ApiModelProperty(value = "업체작업자목록")
    private List<WkodSubconnWorker> wkodSubconnWorkers;

    @ApiModelProperty(value = "안전작업점검확인결과목록")
    private List<WkodChkResult> wkodCheckResult;

    @ApiModelProperty(value = "유해위험물질목록")
    private List<RiskHazard> selectHandleChemContentRow;

    @ApiModelProperty(value = "설비목록")
    private List<FacilityUsed> safFacilityCds;

    @ApiModelProperty(value = "법적인허가 대상항목")
    private List<ConstLegalLcn> wkodLegalLcns;

    @ApiModelProperty(value = "LOTO")
    private List<LOTO> wkodLotos;


    @ApiModelProperty(value = "LOTO 리스트")
    private List<LOTO> constLotos;

}