package com.she.safety.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MajDisaInsp {

    @ApiModelProperty(value = "중대시민재해점검번호")
    private int majDisaInspNo;
    @ApiModelProperty(value = "점검일시")
    private String inspDt;
    @ApiModelProperty(value = "년도")
    private String year;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "사업장")
    private String plantNm;
    @ApiModelProperty(value = "주관부서")
    private String mainDeptCd;
    @ApiModelProperty(value = "주관부서")
    private String mainDeptNm;
    @ApiModelProperty(value = "점검명")
    private String chkNm;
    @ApiModelProperty(value = "단계 COM_STEP")
    private String procStepCd;
    @ApiModelProperty(value = "단계 COM_STEP")
    private String procStepNm;
    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateCd;
    @ApiModelProperty(value = "상태 COM_STATE")
    private String stateNm;
    @ApiModelProperty(value = "결재상태 COM_BIZ_APPR_STEP")
    private String stepCd;
    @ApiModelProperty(value = "결재상태 COM_BIZ_APPR_STEP")
    private String stepNm;
    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자ID")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserNm;
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

    @ApiModelProperty(value = "개선요청")
    private String requestCnt;
    @ApiModelProperty(value = "조치 미완료")
    private String incompletCnt;
    @ApiModelProperty(value = "조치기한 초과")
    private String overdueCnt;
    @ApiModelProperty(value = "조치 완료")
    private String completCnt;
    @ApiModelProperty(value = "안전계획")
    private MajDisaInspSafe majDisaInspSafe;
    @ApiModelProperty(value = "법정점검 계획")
    private MajDisaInspSafe majDisaInspRaw;
    @ApiModelProperty(value = "우려사항 조치")
    private MajDisaInspSafe majDisaInspAct;
}
