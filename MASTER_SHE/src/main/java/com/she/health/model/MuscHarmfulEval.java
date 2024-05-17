package com.she.health.model;

import java.util.List;

import com.she.common.model.AttachFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

// SK E&S
@ApiModel(description = "유해요인조사 평가 정보")
@Data
public class MuscHarmfulEval {

    @ApiModelProperty(value = "유해요인평가번호")
    private int muscHarmfulEvalNo;

    @ApiModelProperty(value = "부담작업번호")
    private int muscResearchChklistNo;

    @ApiModelProperty(value = "유해요인조사번호")
    private int muscHarmfulNo;

    @ApiModelProperty(value = "단위작업번호")
    private int muscResearchUnitNo;

    @ApiModelProperty(value = "작업부하(A)")
    private int workload;

    @ApiModelProperty(value = "작업빈도(B)")
    private int workCnt;

    @ApiModelProperty(value = "총점수(A*B)")
    private int totalScore;

    @ApiModelProperty(value = "유해요인")
    private String chklistDesc;

    @ApiModelProperty(value = "작업명")
    private String workNm;

    @ApiModelProperty(value = "단위작업명")
    private String unitWorkNm;

    @ApiModelProperty(value = "발생원인")
    private String causes;

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

    @ApiModelProperty(value = "최종평가(O,X)")
    private String finalValue;

    @ApiModelProperty(value = "[첨부파일]임시id")
    private String tempId;

    @ApiModelProperty(value = "[첨부파일]파일정보들")
    private List<AttachFile> files;

    @ApiModelProperty(value = "유해요인코드")
    private String harmfulCode;

    // @ApiModelProperty(value = "단위작업 목록")
    // private List<MuscResearchUnit> muscUnitList;
    //
    // @ApiModelProperty(value = "조사구분 목록")
    // private List<MuscHarmfulEval> surveryTypeItems;

    // @ApiModelProperty(value = "대상공정 목록")
    // private List<MuscSurveyProcess> processItems;
    //
    // @ApiModelProperty(value = "작업분류표 목록")
    // private List<MuscSurveyChklist> chklistItems;
    //
    // @ApiModelProperty(value = "단위작업 목록")
    // private List<MuscSurveyChklist> unitItems;

}
