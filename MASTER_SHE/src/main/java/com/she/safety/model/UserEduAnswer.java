package com.she.safety.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserEduAnswer {

    private int seqNo;

    private int safEduMstNo;

    private int eduQuestionAnsNo;

    private int eduQuestionLstNo;

    private String userId;

    private BigDecimal eduQuestionPnt;

    private String createUserId;

    private String updateUserId;

}
