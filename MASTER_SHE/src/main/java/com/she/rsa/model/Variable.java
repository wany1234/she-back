package com.she.rsa.model;

import java.util.List;

import lombok.Data;

@Data
public class Variable {

    private int varId;

    private int assessPlanNo;

    private int processNo;

    private int nodeId;

    private String nodeCd;

    private String varNm;

    private String varNmKr;

    private String varNmEn;

    private String useYn;

    private String useYnNm;

    private String designIntent;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private List<VariableUseGuideword> variableUseGuideword;

}
