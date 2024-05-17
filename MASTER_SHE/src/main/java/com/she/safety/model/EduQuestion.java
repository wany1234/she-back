package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduQuestion {

    private int eduQuestionNo;

    private String plantCd;

    private String plantNm;

    private String eduAttCd;

    private String eduAttNm;

    private String eduTypeCd;

    private String eduTypeNm;

    private String eduCourseNm;

    private String safEduCourseNo;

    private String questionYn;

    private String useYn;

    private String useYnNm;

    private String sortOrder;

    private String createUserId;

    private String createDt;

    private String createUserNm;

    private String updateUserId;

    private String updateDt;

    private String updateUserNm;

    private int questionCount;

    private List<EduQuestionList> questions;

    private int[] delQuestion;

    private int[] delAnswer;

    private String writerUserNm;

    private String writerDt;

}
