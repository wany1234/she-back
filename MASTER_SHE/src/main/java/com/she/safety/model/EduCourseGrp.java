package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduCourseGrp {

    private int safEduCourseNo;

    private Integer safElectTitlNo;

    private Integer eduGrpNo;

    private Integer preEduCd;

    private String eduComYn;

    private String eduComStd;

    private String eduComPer;

    private String eduComTime;

    private String docKeepPer;

    private String lawDesk;

    private String useYn;

    private String eduRepeatYn;
}
