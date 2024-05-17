package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "시설점검마스터")
@Getter
@Setter
public class FacilityInspectionMaster {

    @ApiModelProperty(value = "시설점검일련번호")
    private int comFacilityCheckNo;

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "시설유형코드")
    private String comFacilityTypeCd;

    @ApiModelProperty(value = "설비유형명")
    private String comFacilityTypeNm;

    @ApiModelProperty(value = "시설코드")
    private String facilityCd;

    @ApiModelProperty(value = "시설명")
    private String facilityNm;

    @ApiModelProperty(value = "시설점검종류코드")
    private String comFacilityCheckCd;

    @ApiModelProperty(value = "시설점검종류명")
    private String comFacilityCheckNm;

    @ApiModelProperty(value = "시설점검명")
    private String comFacilityCheckTitle;

    @ApiModelProperty(value = "시설점검계획시작일")
    private String comFacilityCheckPlanSymd;

    @ApiModelProperty(value = "최근법정점검일")
    private String lastLawckYmd;

    @ApiModelProperty(value = "최근법정점검일")
    private String lastSelfcYmd;

    @ApiModelProperty(value = "시설점검계획종료일")
    private String comFacilityCheckPlanEymd;

    @ApiModelProperty(value = "시설점검주기")
    private String comFacilityCheckPerd;

    @ApiModelProperty(value = "시설점검주기명") // ?
    private String comFacilityCheckPerdNm;

    @ApiModelProperty(value = "주관부서코드")
    private String deptCd;

    @ApiModelProperty(value = "주관부서명")
    private String deptNm;

    @ApiModelProperty(value = "대상부서코드")
    private String tgtDeptCd;

    @ApiModelProperty(value = "대상부서명")
    private String tgtDeptNm;
    
    @ApiModelProperty(value = "수행부서코드")
    private String pfmDeptCd;

    @ApiModelProperty(value = "수행부서명")
    private String pfmDeptNm;
    
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

    @ApiModelProperty(value = "결재요청번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "결재상태코드")
    private String apprRqstCd;

    @ApiModelProperty(value = "결재상태명")
    private String apprRqstNm;

    @ApiModelProperty(value = "시설유형s")
    private String comFacilityTypeNms;

    @ApiModelProperty(value = "일정단계갯수")
    private int cnt;

    @ApiModelProperty(value = "삭제데이터(일정)")
    private int[] deleteData;

    @ApiModelProperty(value = "시설점검일정리스트")
    private List<FacilityInspectionSchedule> facilityInspectionSchedules;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "무일정계획유무")
    private String noSchYn;
}
