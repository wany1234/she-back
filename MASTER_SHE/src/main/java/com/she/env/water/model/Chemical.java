package com.she.env.water.model;

import lombok.Data;

@Data
public class Chemical {
    private int ewtrChemNo;

    private String ewtrChemNm;

    private Float amountCurr;

    private String ewtrChemCd;

    private String envUnitCd;

    private String envUnitNm;

    private String useYn;

    private String useYnNm;

    private String remark;

    private int sortOrder;

    private String plantCd;

    private String plantNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private int checkCount;

    private String writerUserNm;

    private String writerDt;

}
