package com.she.mgt.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "SHE목표달성현황")
@Getter
@Setter
public class MgtTgtStatus {
    @ApiModelProperty(value = "대상연도")
    private String year;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "대상부서코드")
    private String deptCd;
    @ApiModelProperty(value = "대상부서명")
    private String deptNm;
    @ApiModelProperty(value = "SHE분야명")
    private String bizFieldNm;
    @ApiModelProperty(value = "SHE분야별 항목명")
    private String bizFieldItemNm;
    @ApiModelProperty(value = "1월목표값")
    private String m1TargetVal;
    @ApiModelProperty(value = "1월실적값")
    private String m1RsltVal;
    @ApiModelProperty(value = "1월달성율")
    private String m1AchiRate;
    @ApiModelProperty(value = "1월평가값")
    private String m1EvalVal;
    @ApiModelProperty(value = "2월목표값")
    private String m2TargetVal;
    @ApiModelProperty(value = "2월실적값")
    private String m2RsltVal;
    @ApiModelProperty(value = "2월달성율")
    private String m2AchiRate;
    @ApiModelProperty(value = "2월평가값")
    private String m2EvalVal;
    @ApiModelProperty(value = "3월목표값")
    private String m3TargetVal;
    @ApiModelProperty(value = "3월실적값")
    private String m3RsltVal;
    @ApiModelProperty(value = "3월달성율")
    private String m3AchiRate;
    @ApiModelProperty(value = "3월평가값")
    private String m3EvalVal;
    @ApiModelProperty(value = "4월목표값")
    private String m4TargetVal;
    @ApiModelProperty(value = "4월실적값")
    private String m4RsltVal;
    @ApiModelProperty(value = "4월달성율")
    private String m4AchiRate;
    @ApiModelProperty(value = "4월평가값")
    private String m4EvalVal;
    @ApiModelProperty(value = "5월목표값")
    private String m5TargetVal;
    @ApiModelProperty(value = "5월실적값")
    private String m5RsltVal;
    @ApiModelProperty(value = "5월달성율")
    private String m5AchiRate;
    @ApiModelProperty(value = "5월평가값")
    private String m5EvalVal;
    @ApiModelProperty(value = "6월목표값")
    private String m6TargetVal;
    @ApiModelProperty(value = "6월실적값")
    private String m6RsltVal;
    @ApiModelProperty(value = "6월달성율")
    private String m6AchiRate;
    @ApiModelProperty(value = "6월평가값")
    private String m6EvalVal;
    @ApiModelProperty(value = "7월목표값")
    private String m7TargetVal;
    @ApiModelProperty(value = "7월실적값")
    private String m7RsltVal;
    @ApiModelProperty(value = "7월달성율")
    private String m7AchiRate;
    @ApiModelProperty(value = "7월평가값")
    private String m7EvalVal;
    @ApiModelProperty(value = "8월목표값")
    private String m8TargetVal;
    @ApiModelProperty(value = "8월실적값")
    private String m8RsltVal;
    @ApiModelProperty(value = "8월달성율")
    private String m8AchiRate;
    @ApiModelProperty(value = "8월평가값")
    private String m8EvalVal;
    @ApiModelProperty(value = "9월목표값")
    private String m9TargetVal;
    @ApiModelProperty(value = "9월실적값")
    private String m9RsltVal;
    @ApiModelProperty(value = "9월달성율")
    private String m9AchiRate;
    @ApiModelProperty(value = "9월평가값")
    private String m9EvalVal;
    @ApiModelProperty(value = "10월목표값")
    private String m10TargetVal;
    @ApiModelProperty(value = "10월실적값")
    private String m10RsltVal;
    @ApiModelProperty(value = "10월달성율")
    private String m10AchiRate;
    @ApiModelProperty(value = "10월평가값")
    private String m10EvalVal;
    @ApiModelProperty(value = "11월목표값")
    private String m11TargetVal;
    @ApiModelProperty(value = "11월실적값")
    private String m11RsltVal;
    @ApiModelProperty(value = "11월달성율")
    private String m11AchiRate;
    @ApiModelProperty(value = "11월평가값")
    private String m11EvalVal;
    @ApiModelProperty(value = "12월목표값")
    private String m12TargetVal;
    @ApiModelProperty(value = "12월실적값")
    private String m12RsltVal;
    @ApiModelProperty(value = "12월달성율")
    private String m12AchiRate;
    @ApiModelProperty(value = "12월평가값")
    private String m12EvalVal;
}
