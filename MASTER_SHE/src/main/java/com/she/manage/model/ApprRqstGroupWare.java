package com.she.manage.model;

import lombok.Data;

@Data
public class ApprRqstGroupWare {

    // 결재문서번호
    private Integer apprBizNo;

    // 결재요청번호
    private Integer apprRqstNo;

    // 결재요청일
    private String rqstDt;

    // 업무명
    private String bizNm;

    // 결재요청명
    private String apprRqstNm;

    // 결재진행단계코드
    private String bizApprStepCd;

    // 결재진행단계
    private String bizApprStepNm;

    // 결재자ID
    private String apprUserId;

    // 결재자
    private String apprUserNm;

    // 결재자부서명
    private String apprUserDeptNm;

    // 결재자처리상태
    private String apprStepNm;

    // 결재일시
    private String apprDt;
}
