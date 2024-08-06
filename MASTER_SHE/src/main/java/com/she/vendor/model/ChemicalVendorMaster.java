package com.she.vendor.model;

import java.util.List;

import lombok.Data;

@Data
public class ChemicalVendorMaster {
    private String vendorCd;

    private List<String> plantCds;

    private String plantNms;

    private String originCd;

    private String originNmKr;

    private String originNmEn;

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

    private String vendorTypeNm;

    private String vendorAttCd;

    private String vendorAttNm;

    private String useYn;

    private String useYnNm;

    private String supplyYn;

    private String supplyYnNm;

    private String sellerYn;

    private String sellerYnNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String portalId;

    private String portalUseYn;

    private String portalPwd;

    private int sortOrder;

    private String regDt;

    private List<VendorWorker> workers;

    private int totalCnt;

    private String writerUserNm;

    private String writerDt;

    private String authYn;

    private String addYn;

    private String authNumber;
}
