package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PwrcChkResult {

    private int ewtrPwrMeterNo;

    private String ewtrPwrMeterNm;

    private String measureYmd;

    private Float runTm;

    private Float pwrConsumAmt;

    private String pwrConsumAmtt;

    private String chkTime;

    private Float pwrMeterCntT;

    private String pwrMeterCntTt;

    private Float pwrMeterCntY;

    private String pwrMeterCntYt;

    private String remark;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private Float pwrcPerDay;

    private String ewtrCleanFacNo;

    private String pwrMeterMagn;

    @ApiModelProperty(value = "작성부서코드")
    private String deptCd;

}
