package com.she.safety.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduQuestionAnswer {

    private int eduQuestionAnsSeq;

    private int eduQuestionAnsNo;

    private int eduQuestionLstNo;

    private String answerContents;

    private String correctYn;

    private String sortOrder;

    private String useYn;
}
