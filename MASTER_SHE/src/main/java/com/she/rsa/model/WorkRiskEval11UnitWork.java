package com.she.rsa.model;

import lombok.Data;

@Data
public class WorkRiskEval11UnitWork {

    private String plantCd; // 사업장코드
    private String plantNm; // 사업장명
    private String unitWorkCd; // 단위작업코드
    private String unitWorkNm; // 단위작업명
    private String remark; // 비고
    private int sortOrder; // 정렬순서
    private String useYn; // 사용여부
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;

}
