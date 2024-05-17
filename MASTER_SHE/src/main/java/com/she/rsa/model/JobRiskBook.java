package com.she.rsa.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JobRiskBook {
    private int riskBookNo;

    private String assessTypeNm;

    private int assessPlanNo;

    private String deptCd;

    private String deptNm;

    private int jobId;

    private String jobCd;

    private String jobNm;

    private int jobRevno;

    private int jobStepId;

    private int jobStepNo;

    private String jobStepNm;

    private String processCd;

    private String processNm;

    private int chemProdNo;

    private String chemProdNmKr;

    private String chemProdNmEn;

    private String property;

    private String propertyNm;

    private String fugacity;

    private String boilpoint;

    private int chemNo;

    private String casNo;

    private String chemNmKr;

    private String chemNmEn;

    private String limitRepval;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String imprStepCd;

    private String imprStepNm;

    private String assessNm;

    private int riskHazardNo;

    private String subRiskHazardNm;

    private String upRiskHazardNm;

    private String suspectYn;

    private String suspectYnNm;

    private String improveNo;

    private String improve;

    private String userId;

    private String userNm;

    private String assessDate;

    private int frequencySize;

    private int strongSize;

    private int riskSize;

    private int safImprActNo;

    private String exposureCriteriaOver;

    private int scenarioNo;

    private int nodeId;

    private String nodeNo;

    private String nodeCd;

    private String deviationNm;

    private int pidNo;

    private String pid;

    private String pidNm;

    private String deviationCause;

    private String deviationResult;

    private String existingSafeguards;

    private String currentRiskSize;

    private String currentFrequencySize;

    private String currentStrongSize;

    private String afterRiskSize;

    private String afterFrequencySize;

    private String afterStrongSize;

    private String plantCd;

    private String plantNm;

    private String evalDesc;

    private String writerUserNm;

    private String writerDt;
}
