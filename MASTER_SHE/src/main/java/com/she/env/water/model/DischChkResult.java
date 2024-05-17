package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DischChkResult {
    private int ewtrDischNo;

    private String ewtrClassNm;

    private String ewtrDischClassNm;

    private String ewtrDischNm;

    private String measureYmd;

    private Float meterCntY;

    private Float meterCntT;

    private Float consumAmt;

    private String meterCntYt;

    private String meterCntTt;

    private String consumAmtt;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String ewtrCleanFacNo;

    @ApiModelProperty(value = "작성부서 코드")
    private String deptCd;

}
