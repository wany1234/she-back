package com.she.health.model;

import com.she.common.model.AttachFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

// SK E&S
@ApiModel(description = "근골격계 질환조사 정보")
@Data
public class MuscHarmful {

    @ApiModelProperty(value = "유해요인조사번호")
    private int muscHarmfulNo;

    @ApiModelProperty(value = "유해요인조사번호")
    private int muscResearchNo;

    @ApiModelProperty(value = "단위작업번호")
    private int muscResearchUnitNo;

    @ApiModelProperty(value = "단위작업명")
    private String unitWorkNm;

    @ApiModelProperty(value = "조사구분(R:정기,I:수시)")
    private String surveryType;

    @ApiModelProperty(value = "조사구분명(R:정기,I:수시)")
    private String surveryTypeNm;

    @ApiModelProperty(value = "조사일시")
    private String surveryDt;

    @ApiModelProperty(value = "조사자")
    private String surveryUserId;

    @ApiModelProperty(value = "조사자명")
    private String surveryUserNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "공정")
    private String processNm;

    @ApiModelProperty(value = "작업내용")
    private String processDesc;

    @ApiModelProperty(value = "변화유무(작업설비)")
    private String changeYnEquip;

    @ApiModelProperty(value = "변화유무(작업량)")
    private String changeYnWorkload;

    @ApiModelProperty(value = "변화유무(작업속도)")
    private String changeYnSpeed;

    @ApiModelProperty(value = "변화유무(업무형태)")
    private String changeYnBusiness;

    @ApiModelProperty(value = "변화유무(기타)")
    private String changeYnEtc;

    @ApiModelProperty(value = "내용요약(작업설비)")
    private String summaryEquip;

    @ApiModelProperty(value = "내용요약(작업량)")
    private String summaryWorkload;

    @ApiModelProperty(value = "내용요약(작업속도)")
    private String summarySpeed;

    @ApiModelProperty(value = "내용요약(업무형태)")
    private String summaryBusiness;

    @ApiModelProperty(value = "내용요약(기타)")
    private String summaryEtc;

    @ApiModelProperty(value = "종합요약")
    private String summaryOverall;

    @ApiModelProperty(value = "개선요청")
    private String improvement;

    @ApiModelProperty(value = "즉시조치")
    private String request;

    @ApiModelProperty(value = "등록일")
    private String createUserId;

    @ApiModelProperty(value = "등록자")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "단위작업 목록")
    private List<MuscResearchUnit> muscUnitList;

    @ApiModelProperty(value = "탭 정보")
    private List<MuscHarmfulEval> muscHarmfulEvals;

    @ApiModelProperty(value = "삭제 탭 정보")
    private List<MuscHarmfulEval> deleteMuscHarmfulEvals;

    @ApiModelProperty(value = "[첨부파일]임시id")
    private String tempId;

    @ApiModelProperty(value = "[첨부파일]파일정보들")
    private List<AttachFile> files;

    @ApiModelProperty(value = "유해요인 원인 분석 건수")
    private String harmCnt;

    // @ApiModelProperty(value = "대상공정 목록")
    // private List<MuscSurveyProcess> processItems;
    //
    // @ApiModelProperty(value = "작업분류표 목록")
    // private List<MuscSurveyChklist> chklistItems;
    //
    // @ApiModelProperty(value = "단위작업 목록")
    // private List<MuscSurveyChklist> unitItems;

}
