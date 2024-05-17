package com.she.env.air.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PreventFacMaintResults {

    private String eairPreventFacMaintHistNo;
    private String eairPreventFacNo;
    private String eairOutletNum;
    private String eairOutletNm;
    private String eairPreventFacNm;
    private String maintYmd;
    private String worker;
    private String remark;

}
