package com.she.health.model;

import com.she.common.model.AttachFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

// SK E&S
@ApiModel(description = "근골격계 질환조사 정보")
@Data
public class MuscResearchRslt {

    @ApiModelProperty(value = "조사결과번호")
    private int muscResearchRsltNo;

    @ApiModelProperty(value = "부서별조사번호")
    private int muscResearchDeptNo;

    @ApiModelProperty(value = "단위작업번호")
    private int muscResearchUnitNo;

    @ApiModelProperty(value = "단위작업명")
    private String unitWorkNm;

    @ApiModelProperty(value = "작업명")
    private String workNm;

    @ApiModelProperty(value = "부담작업번호")
    private int muscResearchChklistNo;

    @ApiModelProperty(value = "작업시간")
    private float workTime;

    @ApiModelProperty(value = "작업횟수")
    private int workCnt;

    @ApiModelProperty(value = "총노출시간")
    private float totExposureTime;

    @ApiModelProperty(value = "총노출시간(체크리스트)")
    private float total;

    @ApiModelProperty(value = "최종평가(O,X)")
    private String finalValue;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "조사부서코드")
    private String deptCd;

    @ApiModelProperty(value = "조사부서명")
    private String deptNm;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "단위작업 목록")
    private List<MuscHarmfulEval> muscHarmfulSurvList;

    @ApiModelProperty(value = "[첨부파일]임시id")
    private String tempId;

    @ApiModelProperty(value = "[첨부파일]파일정보들")
    private List<AttachFile> files;

    @ApiModelProperty(value = "조사담당자")
    private String researchUserId;
}
