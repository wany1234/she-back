package com.she.manage.model;

import lombok.Data;

/**
 * 권한 그룹 도메인 모델
 */
@Data
public class AuthGrp {
    private int authGrpNo;
    private String authGrpNm;
    private String remarks;
    private String useYn;
    private String useYnNm;
    private String settingYn;
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String id;
    private String label;
}
