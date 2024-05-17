package com.she.mgt.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MgtLawMakingCheck extends MgtLawMaking {
    // @ApiModelProperty(value = "입법예고 번호")
    // private String lmSeqNo;

    @ApiModelProperty(value = "입법예고 검토번호")
    private int lmcSeqNo;

    @ApiModelProperty(value = "입법예고 검토단계코드")
    private String checkStepCd;

    @ApiModelProperty(value = "입법예고 검토단계명")
    private String checkStepNm;

    @ApiModelProperty(value = "등록일자")
    private String rqstDt;

    @ApiModelProperty(value = "검토요청 제목")
    private String checkRqstTitle;

    @ApiModelProperty(value = "검토요청 내용")
    private String checkRqstDesc;

    @ApiModelProperty(value = "법규내용요약")
    private String summary;

    @ApiModelProperty(value = "결재요청번호(법규등록)")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재진행단계코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행단계")
    private String bizApprStepNm;

    @ApiModelProperty(value = "작성자")
    private String createUserId;

    @ApiModelProperty(value = "작성자명")
    private String createUserNm;

    @ApiModelProperty(value = "작성일")
    private String createDt;

    @ApiModelProperty(value = "작성자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "검토팀s")
    private String checkDeptNms;

    @ApiModelProperty(value = "검토부서 목록")
    private List<MgtLawMakingCheckDept> lawMakingCheckDepts;

    // @ApiModelProperty(value = "검토자 목록")
    // private MgtLawMakingChecker[] lawMakingCheckers;
}
