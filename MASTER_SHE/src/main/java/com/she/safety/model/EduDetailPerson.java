package com.she.safety.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EduDetailPerson {

    private int safEduMstNo;

    private String userId;

    private String userNm;

    private String deptCd;

    private String deptNm;

    private String plantCd;

    private String plantNm;

    private String completYn;

    private String completYmd;

    private String remark;

    private String reEduExcYn;

    private String eduEvalPnt;

    private String eduEvalContents;

    private String reEduNotYn;

    private String eduEvalPntSec;

    private String eduEvalTime;

    private String reCompletYn;

    private String reRemark;

    private String reEduTime;

    private int videoWatchTime;

}
