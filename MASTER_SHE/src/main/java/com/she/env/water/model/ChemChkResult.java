package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChemChkResult {

    private int ewtrChemNo;

    private String ewtrChemNm;

    private String measureYmd;

    private String ewtrDischGrpNm;

    private String ewtrDischGrpCd;

    private Float buyAmt;

    private Float consumAmt;
    private String consumAmtt;

    private Float amountCurr;
    private String amountCurrt;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String ewtrCleanFacNo;

    private String prevAmountCurr;

    private String prevAmountCurrt;

    private Float inAmt;

    private String inAmtt;

    private String remarks;

    @ApiModelProperty(value = "작성부서 코드")
    private String deptCd;

}
