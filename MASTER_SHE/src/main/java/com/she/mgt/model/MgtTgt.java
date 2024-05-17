package com.she.mgt.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "SHE목표")
@Getter
@Setter
public class MgtTgt {
    @ApiModelProperty(value = "SHE목표 번호")
    private int mgtTargetNo;
    @ApiModelProperty(value = "SHE목표 월번호")
    private int mgtTargetMonthNo;
    @ApiModelProperty(value = "SHE목표 그룹 번호")
    private int mgtTargetGrpNo;
    @ApiModelProperty(value = "대상연도")
    private String year;
    @ApiModelProperty(value = "대상월")
    private String month;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "대상부서")
    private String deptCd;
    @ApiModelProperty(value = "대상부서명")
    private String deptNm;
    @ApiModelProperty(value = "목표단계(상태)")
    private String tstepCd;
    @ApiModelProperty(value = "목표단계(상태)명")
    private String tstepNm;
    @ApiModelProperty(value = "실적단계(상태)")
    private String rstepCd;
    @ApiModelProperty(value = "실적단계(상태)명")
    private String rstepNm;
    @ApiModelProperty(value = "평가단계(상태)")
    private String estepCd;
    @ApiModelProperty(value = "평가단계(상태)명")
    private String estepNm;
    @ApiModelProperty(value = "목표")
    private String target;
    @ApiModelProperty(value = "방침")
    private String policy;
    @ApiModelProperty(value = "목표결재요청번호")
    private String tapprRqstNo;
    @ApiModelProperty(value = "실적결재요청번호")
    private String rapprRqstNo;
    @ApiModelProperty(value = "평가결재요청번호")
    private String eapprRqstNo;
    @ApiModelProperty(value = "실적진행상태")
    private String rstateCd;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "등록자")
    private String updateUserId;
    @ApiModelProperty(value = "등록일")
    private String updateDt;
    @ApiModelProperty(value = "기타 파라미터")
    public String parameter;
    @ApiModelProperty(value = "활동데이터보고결과 리스트")
    public List<MgtTgtItemPlanRslt> planDataList;
    @ApiModelProperty(value = "활동데이터보고결과 리스트")
    public List<MgtTgtItemEvalRslt> evalDataList;

    public MgtTgt() {
        super();
    }

    public MgtTgt(int mgtTargetNo, int mgtTargetMonthNo, int mgtTargetGrpNo, String year, String month, String plantCd, String deptCd, String target, String policy, String tstepCd, String tstepNm, String rstepCd, String rstepNm, String estepCd, String estepNm, String tapprRqstNo, String rapprRqstNo, String eapprRqstNo, String createUserId,
            String createDt, String updateUserId, String updateDt, String parameter, List<MgtTgtItemPlanRslt> planDataList, List<MgtTgtItemEvalRslt> evalDataList) {
        super();
        this.mgtTargetNo = mgtTargetNo;
        this.mgtTargetMonthNo = mgtTargetMonthNo;
        this.mgtTargetGrpNo = mgtTargetGrpNo;
        this.year = year;
        this.month = month;
        this.plantCd = plantCd;
        this.deptCd = deptCd;
        this.target = target;
        this.policy = policy;
        this.tstepCd = tstepCd;
        this.tstepNm = tstepNm;
        this.rstepCd = rstepCd;
        this.rstepNm = rstepNm;
        this.estepCd = estepCd;
        this.estepNm = estepNm;
        this.tapprRqstNo = tapprRqstNo;
        this.rapprRqstNo = rapprRqstNo;
        this.eapprRqstNo = eapprRqstNo;
        this.createUserId = createUserId;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateDt = updateDt;
        this.parameter = parameter;
        this.planDataList = planDataList;
        this.evalDataList = evalDataList;
    }
}
