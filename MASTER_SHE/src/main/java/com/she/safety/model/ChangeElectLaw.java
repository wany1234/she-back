package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "변경관리_선해임관련법규")
@Getter
@Setter
public class ChangeElectLaw {

    private int safChngElectLawResultNo;

    private int safChngNo;

    private String electAttCd;

    private String electAttNm;

    private String refLawCd;

    private String refLawNm;

    private String chngRsltYn;

    private String mgrId;

    private String mgrNm;

    private String remark;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    private List<ChangeElectResult> changeElectResults;

    private List<Integer> checkChangeElectResults;
}
