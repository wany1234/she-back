package com.she.safety.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduProblemMgt {
    private int eduCourseMatNo;

    private int eduMatNo;

    private int matGrpNo;

    private String plantCd;

    private String plantNm;

    private String eduTypeCd;

    private String eduTypeNm;

    private int safEduCourseNo;

    private String title;

    private String revNum;

    private String revYmd;

    private String enfYmd;

    private String contents;

    private String revContents;

    private String useYn;

    private String delYn;

    private String eduAttCd;

    private String eduAttNm;

    private String eduCourseNm;

    private String[] checkData;

    private String createUserId;

    private String createDt;

    private String createUserNm;

    private String updateUserId;

    private String updateDt;

    private String updateUserNm;

    private String writerUserNm;

    private String writerDt;
}
