package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "정부지자체 시정조치 이행점검 계획")
public class GovImplChkPlan {
    @ApiModelProperty(value = "정부지자체 시정조치 이행점검번호")
    private int implChkPlanNo;

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "사업장")
    private String plantNm;

    @ApiModelProperty(value = "대상년도")
    private String year;

    @ApiModelProperty(value = "대상부서Cnt")
    private String targetDeptCnt;

    @ApiModelProperty(value = "구분(정기/수시) RSA_REG_REGDEM")
    private String regRegdem;

    @ApiModelProperty(value = "구분(정기/수시)")
    private String regRegdemNm;

    @ApiModelProperty(value = "점검(계획)시작일")
    private String chkStartDt;

    @ApiModelProperty(value = "점검(계획)종료일")
    private String chkEndDt;

    @ApiModelProperty(value = "점검 기간")
    private String chkPeriod;

    @ApiModelProperty(value = "주관부서")
    private String mainDeptCd;

    @ApiModelProperty(value = "주관부서")
    private String mainDeptNm;

    @ApiModelProperty(value = "대상부서")
    private String targetDeptCd;

    @ApiModelProperty(value = "대상부서")
    private String targetDeptNm;

    @ApiModelProperty(value = "점검명")
    private String chkNm;

    @ApiModelProperty(value = "상세내용")
    private String chkDesc;

    @ApiModelProperty(value = "단계 COM_STEP")
    private String procStepCd;

    @ApiModelProperty(value = "단계")
    private String procStepNm;

    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateCd;

    @ApiModelProperty(value = "상태")
    private String stateNm;

    @ApiModelProperty(value = "결재상태 COM_BIZ_APPR_STEP")
    private String stepCd;

    @ApiModelProperty(value = "단계(상태)")
    private String stepNm;

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "관련 정부지자체")
    private String refGovNm;

    @ApiModelProperty(value = "평가리더ID")
    private String deptReaderId;

    @ApiModelProperty(value = "평가리더")
    private String deptReaderNm;

    @ApiModelProperty(value = "평가리더 부서코드")
    private String readerDeptCd;

    @ApiModelProperty(value = "평가리더 부서")
    private String readerDeptNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "등록자부서명")
    private String createDeptNm;

    @ApiModelProperty(value = "등록자부서코드")
    private String createDeptCd;

    @ApiModelProperty(value = "수정자부서명")
    private String updateDeptNm;

    @ApiModelProperty(value = "수정자부서코드")
    private String updateDeptCd;

    @ApiModelProperty(value = "등록자직위명")
    private String createPositionNm;

    @ApiModelProperty(value = "등록자직위코드")
    private String createPositionCd;

    @ApiModelProperty(value = "수정자직위명")
    private String updatePositionNm;

    @ApiModelProperty(value = "수정자직위코드")
    private String updatePositionCd;

    @ApiModelProperty(value = "작성자")
    private String writerNm;

    @ApiModelProperty(value = "작성일")
    private String writeDt;

    @ApiModelProperty(value = "개선요청")
    private String requestCnt;

    @ApiModelProperty(value = "조치 미완료")
    private String incompletCnt;

    @ApiModelProperty(value = "조치기한 초과")
    private String overdueCnt;

    @ApiModelProperty(value = "조치 완료")
    private String completCnt;

    @ApiModelProperty(value = "내부점검자 목록")
    private List<GovImplChkInRater> GovImplChkInRaters;

    @ApiModelProperty(value = "내부점검자 목록")
    private List<GovImplChkExRater> GovImplChkExRaters;

    @ApiModelProperty(value = "점검결과")
    private GovImplChkRslt govImplChkRslt;
}
