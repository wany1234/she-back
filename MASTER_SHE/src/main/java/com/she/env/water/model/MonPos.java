package com.she.env.water.model;

import java.util.List;

import lombok.Data;

@Data
public class MonPos {
    private int ewtrMonPosNo;

    private String ewtrMonPosNm;

    private String plantCd;

    private String plantNm;

    private String deptCd;

    private String deptNm;

    private String ewtrMonCount;

    private String wtrPerdCd;

    private String measureCnt;

    private String remark;

    private int sortOrder;

    private String useYn;

    private String useYnNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String[] selectedTestItemCds;

    private List<MonPosTestItem> monPosTestItem;

    private String writerUserNm;

    private String writerDt;
}
