package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class IngrChkResult {
    private int ewtrIngrNo;

    private String ewtrIngrNm;

    private String measureYmd;

    private Float consumAmt;

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
