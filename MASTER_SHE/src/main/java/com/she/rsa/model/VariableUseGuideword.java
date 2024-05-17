package com.she.rsa.model;

import java.util.List;

import lombok.Data;

@Data
public class VariableUseGuideword {

    private int varId;

    private String guidewordCd;

    private String guidewordNm;

    private String useYn;

    private List<VariableUseGuidewordPosibleCause> posibleCauses;

}
