package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(description = "HRD사외전문교육")
@Data
public class EduCourse {

    private int safEduCourseNo;

    private String eduAttCd;

    private String eduAttNm;

    private String isTypeCd;

    private String isTypeNm;

    private String eduTypeCd;

    private String eduTypeNm;

    private String eduObject;

    private String eduCourseNm;

    private String plantCd;

    private String plantNm;

    private String lawYn;

    private Float eduCost;

    private String studyTgt;

    private String studyContents;

    private String lawYnNm;

    private String lawPerdCd;

    private String lawPerdNm;

    private int dftEduTm;

    private int lawEduTm;

    private String eduDayNum;

    private String useYn;

    private String useYnNm;

    private String sortOrder;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private int planCnt;

    private String writerUserNm;

    private String writerDt;
}
