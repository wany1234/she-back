package com.she.rsa.model;

import java.util.List;

import lombok.Data;

@Data
public class DeptAssessJob {
    private String deptCd;

    private String deptNm;

    private int assessPlanNo;

    private String assessNm;

    private int jobId;

    private String jobCd;

    private String jobNm;

    private int processNo;

    private String processCd;

    private String processNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String assessDate;

    private int afterRiskNo;

    private String riskSizeAvg;

    private String targetYnNm;

    private List<Job> Jobs;

}
