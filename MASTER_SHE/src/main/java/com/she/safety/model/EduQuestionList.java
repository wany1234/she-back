package com.she.safety.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduQuestionList {

    private int safEduMstNo;

    private int eduQuestionNo;

    private int eduQuestionLstSeq;

    private int eduQuestionLstNo;

    private String questionContents;

    private String status;

    private String useYn;

    private String sortOrder;

    private int answerCount; // 답안건수

    private String userId;

    private String createUserId;

    private String createDt;

    private String createUserNm;

    private String updateUserId;

    private String updateDt;

    private String updateUserNm;

    private String[] checkData;

    private int selectData;

    private List<EduQuestionAnswer> eduQuestionAnswers;

}
