package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TestItemResult {
    private int ewtrMonPosNo;

    private String ewtrMonPosNm;

    private String plantCd;

    private String ewtrTestItemCd;

    private String ewtrTestItemNm;

    private String measureYmd;

    private Float numResult;

    private String charResult;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String chkTime;

    private String itemCd;

    private String inputTypeCd;

    private String ewtrCleanFacNo;

    private String sortOrder;

    @ApiModelProperty(value = "작성부서 코드")
    private String deptCd;

}
