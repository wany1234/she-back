package com.she.mgt.model;

import java.util.List;

import lombok.Data;

@Data
public class ElectTitle {

    private int safElectTitlNo;

    private String plantCd;

    private String plantNm;

    private String electClsCd;

    private String electClsNm;

    private String electAttCd;

    private String electAttNm;

    private String refLawCd;

    private String electTypeCd;

    private String electTypeNm;

    private String refLawNm;

    private String electTitlNm;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private List<String> lcnTypeCds;

    private String electStndDesc;

    private String qualifiRequire;

    private String writerUserNm;

    private String writerDt;

    private String evalTgtYn;

    private String evalTgtYnNm;

    private String useYnCt;

    private String code;

    private String codeNm;

    private List<ElectTitlItem> electTitleItems;

}
