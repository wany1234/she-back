package com.she.health.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "유소견자")
@Data
public class Suspect {
    @ApiModelProperty(value = "사용자 아이디")
    private String userId;

    @ApiModelProperty(value = "사업장 코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장 명칭")
    private String plantNm;

    @ApiModelProperty(value = "유소견자 여부")
    private String suspectYn;

    @ApiModelProperty(value = "유소견자 여부 명")
    private String suspectYnNm;

    @ApiModelProperty(value = "지정일")
    private String beManagedYmd;

    @ApiModelProperty(value = "해제일")
    private String notManagedYmd;

    @ApiModelProperty(value = "비고_지정")
    private String remark;

    @ApiModelProperty(value = "비고_해제")
    private String notRemark;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "성명")
    private String userNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "건강검진 계획 번호")
    private int heaCheckupPlanNo;

    @ApiModelProperty(value = "건강검진 분류 명")
    private String heaCheckupClassNm;

    @ApiModelProperty(value = "건강검진 계획 명")
    private String heaCheckupPlanNm;

    @ApiModelProperty(value = "검진 받은 날짜")
    private String heaCheckedYmd;

    @ApiModelProperty(value = "검진 받은 기관 번호")
    private String heaCheckedOrgNo;

    @ApiModelProperty(value = "검진 받은 기관 명")
    private String heaCheckedOrgNm;

    @ApiModelProperty(value = "진단 코드")
    private String heaDiagnoseCd;

    @ApiModelProperty(value = "진단 명")
    private String heaDiagnoseNms;

    @ApiModelProperty(value = "질환 명")
    private String heaDiseaseNms;

    @ApiModelProperty(value = "")
    private String processNm;

    @ApiModelProperty(value = "진행상태코드")
    private String suspStepCd;

    @ApiModelProperty(value = "진행상태명")
    private String suspStepNm;

    @ApiModelProperty(value = "결재진행상태")
    private String apprRqstNm;

    @ApiModelProperty(value = "결재진행상태코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행상태번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "요청일")
    private String rqstDt;

    @ApiModelProperty(value = "유소견자 요청번호")
    private int suspectReqNo;

    @ApiModelProperty(value = "유소견자 이력번호")
    private int suspectHstNo;

    @ApiModelProperty(value = "지정인원수")
    private int reqActCnt;

    @ApiModelProperty(value = "해제인원수")
    private int reqRelCnt;

    @ApiModelProperty(value = "지정자 목록")
    private List<Suspect> actUserItems;

    @ApiModelProperty(value = "해제자 목록")
    private List<Suspect> relUserItems;

}
