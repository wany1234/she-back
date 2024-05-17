package com.she.manage.model;

import lombok.Data;

@Data
public class VendorUser {

    private String vendorCd;

    private String originCd;

    private String vendorNm;

    private String vendorAddr;

    private String factoryAddr;

    private String bizNum;

    private String chargerDept;

    private String vendorTel;

    private String vendorFax;

    private String email;

    private String chargerNm;

    private String chargerTel;

    private String chiefNm;

    private String purchaseOrgCd;

    private String purchaseOrgNm;

    private String agencyNm;

    private String agencyTel;

    private String agencyCharger;

    private String vendorTypeCd;

    private String vendorAttCd;

    private String portalUseYn;

    private String portalId;

    private String portalPwd;

    private String useYn;

    private int sortOrder;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;
}
