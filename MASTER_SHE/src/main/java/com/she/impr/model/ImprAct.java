package com.she.impr.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "개선조치")
@Getter
@Setter
public class ImprAct {
    @ApiModelProperty(value = "개선조치번호")
    private int safImprActNo;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장번호")
    private String plantNm;
    @ApiModelProperty(value = "개선분류코드")
    private String imprClassCd;
    @ApiModelProperty(value = "개선분류명")
    private String imprClassNm;
    @ApiModelProperty(value = "조치처리구분코드")
    private String actClassCd;
    @ApiModelProperty(value = "조치처리구분명")
    private String actClassNm;
    @ApiModelProperty(value = "요청일")
    private String imprRqstYmd;
    @ApiModelProperty(value = "제목")
    private String imprTitle;
    @ApiModelProperty(value = "삭제여부")
    private String delYn;
    @ApiModelProperty(value = "완료여부")
    private String completYn;
    @ApiModelProperty(value = "개선요청업체코드")
    private String imprRqstVendorCd;
    @ApiModelProperty(value = "개선요청업체명")
    private String imprRqstVendorNm;

    @ApiModelProperty(value = "개선요청부서코드")
    private String imprRqstDeptCd;
    @ApiModelProperty(value = "개선요청부서명")
    private String imprRqstDeptNm;
    @ApiModelProperty(value = "개선요청자사용자ID")
    private String imprRqstUserId;
    @ApiModelProperty(value = "개선요청자성명")
    private String imprRqstUserNm;
    @ApiModelProperty(value = "개선요청내용")
    private String imprRqstContents;
    @ApiModelProperty(value = "개선진행단계코드")
    private String imprStepCd;
    @ApiModelProperty(value = "개선진행단계명")
    private String imprStepNm;
    @ApiModelProperty(value = "관련테이블PKID")
    private int refTableId;
    @ApiModelProperty(value = "조치예정일")
    private String actSchYmd;
    @ApiModelProperty(value = "조치완료일")
    private String actConfirmYmd;
    @ApiModelProperty(value = "조치기한")
    private String actLimitYmd;
    @ApiModelProperty(value = "공정코드")
    private String processCd;
    @ApiModelProperty(value = "공정명")
    private String processNm;
    @ApiModelProperty(value = "세부위치")
    private String dtlLocat;

    @ApiModelProperty(value = "조치업체조치완료여부")
    private String actVendorConfirmYn;
    @ApiModelProperty(value = "기존조치업체")
    private String preActVendorCd;
    @ApiModelProperty(value = "기존조치업체명")
    private String preActVendorNm;
    @ApiModelProperty(value = "조치업체변경사유")
    private String actVendorChngContents;
    @ApiModelProperty(value = "조치업체코드")
    private String actVendorCd;
    @ApiModelProperty(value = "조치업체명")
    private String actVendorNm;

    @ApiModelProperty(value = "기존조치부서")
    private String preActDeptCd;
    @ApiModelProperty(value = "기존조치부서명")
    private String preActDeptNm;
    @ApiModelProperty(value = "조치부서변경사유")
    private String actDeptChngContents;
    @ApiModelProperty(value = "조치부서코드")
    private String actDeptCd;
    @ApiModelProperty(value = "조치부서명")
    private String actDeptNm;
    @ApiModelProperty(value = "조치자ID")
    private String actUserId;
    @ApiModelProperty(value = "조치자성명")
    private String actUserNm;
    @ApiModelProperty(value = "조치결과내용")
    private String actResultContents;
    @ApiModelProperty(value = "조치결과검토")
    private String actResultReview;
    @ApiModelProperty(value = "개선전빈도")
    private String befFreq;
    @ApiModelProperty(value = "개선전강도")
    private String befInten;
    @ApiModelProperty(value = "개선전위험도")
    private String befDegRisk;
    @ApiModelProperty(value = "개선후빈도")
    private String aftFreq;
    @ApiModelProperty(value = "개선후강도")
    private String aftInten;
    @ApiModelProperty(value = "개선후위험도")
    private String aftDegRisk;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "최종수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "최종수정일")
    private String updateDt;
    @ApiModelProperty(value = "위험등록부번호")
    private String riskBookNo;
    @ApiModelProperty(value = "계획미수립수")
    private int notPlanCnt;
    @ApiModelProperty(value = "계획수립수")
    private int planCnt;
    @ApiModelProperty(value = "조치진행중수")
    private int actCnt;
    @ApiModelProperty(value = "조치완료수")
    private int completeCnt;
    @ApiModelProperty(value = "계획미수립수")
    private int notPlanCnt2;
    @ApiModelProperty(value = "계획수립수")
    private int planCnt2;
    @ApiModelProperty(value = "조치진행중수")
    private int actCnt2;
    @ApiModelProperty(value = "조치완료수")
    private int completeCnt2;
    @ApiModelProperty(value = "이행률(%)")
    private int transitionRate1;
    @ApiModelProperty(value = "이행률(%)")
    private int transitionRate2;
    @ApiModelProperty(value = "이행률(%)")
    private int transitionRate3;
    @ApiModelProperty(value = "결재진행번호")
    private String apprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;
    @ApiModelProperty(value = "첫번째 개선전사진")
    private String befImg;
    @ApiModelProperty(value = "첫번째 개선후사진")
    private String aftImg;
    @ApiModelProperty(value = "발견사항")
    private String discoverMatter;
    @ApiModelProperty(value = "조치부서 변경여부")
    private String actChangeYn;

    private String errorMessage;

    @ApiModelProperty(value = "개선요청 일괄등록 uuid")
    private String uuid;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "작성자부서")
    private String createDeptCd;
    @ApiModelProperty(value = "작성자부서명")
    private String createDeptNm;
    @ApiModelProperty(value = "작성자직위")
    private String createPositionCd;
    @ApiModelProperty(value = "작성자직위명")
    private String createPositionNm;
    @ApiModelProperty(value = "상태")
    private String stateCd;
    @ApiModelProperty(value = "상태")
    private String stateNm;
    @ApiModelProperty(value = "단계")
    private String imprGubun;
    @ApiModelProperty(value = "결재/상태")
    private String apprStateNm;
}
