package com.she.mgt.model;

import lombok.Data;

@Data
public class ElectTitlLcn {

    private int safElectTitlNo;

    private String lcnTypeCd;

    private String lcnTypeNm;

    private String plantCd;

    private String plantNm;

    private String deptCd;

    private String deptNm;

    private String userId;

    private String userNm;

    private String licenseGubun;

    private String licenseGubunNm;

    private String licenseNm;

    private String licenseNo;

    private String officeCd;

    private String lcnGetDt; // 자격취득일
}
