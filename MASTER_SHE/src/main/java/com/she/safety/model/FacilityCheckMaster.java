package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비점검마스터")
@Getter
@Setter
public class FacilityCheckMaster {

    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilityCheckNo;
    @ApiModelProperty(value = "사업장")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "설비점검종류코드")
    private String safCheckTypeCd;
    @ApiModelProperty(value = "설비점검종류명")
    private String safCheckTypeNm;
    @ApiModelProperty(value = "설비점검명")
    private String safFacilityCheckTitle;
    @ApiModelProperty(value = "설비점검계획시작일")
    private String safFacilityCheckPlanSymd;
    @ApiModelProperty(value = "설비점검계획종료일")
    private String safFacilityCheckPlanEymd;
    @ApiModelProperty(value = "설비점검주기")
    private String safFacilityCheckPerd;
    @ApiModelProperty(value = "설비점검주기명")
    private String safFacilityCheckPerdNm;
    @ApiModelProperty(value = "등록자")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "점검상태")
    private String stepStatus;
    @ApiModelProperty(value = "점검상태명")
    private String stepStatusNm;
    @ApiModelProperty(value = "설비유형s")
    private String safFacilityTypeNms;
    @ApiModelProperty(value = "점검주관부서코드")
    private String deptCd;
    @ApiModelProperty(value = "점검주관부서명")
    private String deptNm;
    @ApiModelProperty(value = "결재진행번호")
    private String apprRqstNo;
    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;
    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;
    @ApiModelProperty(value = "일정단계갯수")
    private int cnt;

    @ApiModelProperty(value = "삭제데이터(일정)")
    private int[] deleteData;
    @ApiModelProperty(value = "섦비점검일정리스트")
    private List<FacilityCheckSchedule> facilityCheckSchedules;
}
