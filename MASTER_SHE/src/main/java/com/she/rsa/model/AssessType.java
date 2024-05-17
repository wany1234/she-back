
package com.she.rsa.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AssessType {

    private int assessTypeNo;

    private String assessNm;

    private String assessDesc;

    private String createUserId;

    private String createDt;

    private String updateUserId;

    private String updateDt;

    private String assessTypeCd;

    private String assessTypeNm;

    private String assessGroupCd;

    private String assessGroupNm;

    private int assessGroupNo;

    private double revNo;

    private String revContents;

    private List<RiskMatrix> frequencyList;

    private List<RiskMatrix> strongList;

    private List<RiskMatrix> riskList;

    private String plantCd;

    private String plantNm;

    private String writerUserNm;

    private String writerDt;
}
