package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "인허가현황") // 추가
@Data
public class LicensingStatus {

    private int lcnBoardNo;

    private int lcnBoardGrpNo;

    private String kindCd;

    private String title;

    private String refOffice;

    private String roomTp;

    private String refLaw;

    private String refDesc;

    private String revNum;

    private String revYmd;

    private String contents;

    private String plantCd;

    private String plantNm;

    private String deptCd;

    private String deptNm;

    private String revContents;

    private String useYn;

    private String delYn;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String writerUserNm;

    private String writerDt;
}
