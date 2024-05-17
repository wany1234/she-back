package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "안전작업_가스농도측정")
@Getter
@Setter
public class WkodGasMeas {
    @ApiModelProperty(value = "가스농도측정번호")
    private int gasMeasNo;
    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;
    @ApiModelProperty(value = "측정시간")
    private String measTime;
    @ApiModelProperty(value = "측정시")
    private String measHour;
    @ApiModelProperty(value = "측정분")
    private String measMin;
    @ApiModelProperty(value = "측정지점")
    private String measPnt;
    @ApiModelProperty(value = "가스종류")
    private String gasKindNm;
    @ApiModelProperty(value = "측정결과")
    private String measRslt;
    @ApiModelProperty(value = "측정자")
    private String measMan;
}
