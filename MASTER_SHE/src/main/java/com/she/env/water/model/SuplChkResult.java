package com.she.env.water.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SuplChkResult {
    private int ewtrSuplNo;

    private String ewtrSuplNm;

    private String ewtrSuplClassCd;

    private String ewtrSuplClassNm;

    private String measureYmd;

    private String meterCntYt;

    private String meterCntTt;

    private String consumAmtt;

    private Float meterCntY;

    private Float meterCntT;

    private Float consumAmt;

    private String chkTime;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String ewtrCleanFacNo;

    @ApiModelProperty(value = "작성부서")
    private String deptCd;

    private List<SuplChkResult> suplChkResultList;

}
