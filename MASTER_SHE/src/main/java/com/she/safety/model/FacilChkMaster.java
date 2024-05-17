package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "설비점검마스터")
@Data
public class FacilChkMaster {

    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilChkNo;

    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;

    @ApiModelProperty(value = "사업장")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "점검종류코드")
    private String safCheckTypeCd;

    @ApiModelProperty(value = "점검종류명칭")
    private String safCheckTypeNm;

    @ApiModelProperty(value = "점검명")
    private String safFacilChkTitle;

    @ApiModelProperty(value = "점검부서코드") // 점검주관부서코드명
    private String deptCd; 

    @ApiModelProperty(value = "점검부서명") // 점검주관부서명
    private String deptNm;
    
    @ApiModelProperty(value = "점검상태코드")
    private String chkStepCd;

    @ApiModelProperty(value = "점검상태명")
    private String chkStepNm;
    
    @ApiModelProperty(value = "점검수행자ID")
    private String pUserId;

    @ApiModelProperty(value = "점검수행자 명")
    private String pUserNm;

    @ApiModelProperty(value = "등록자ID")
    private String createUserId;

    @ApiModelProperty(value = "등록자 명")
    private String createUserNm;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "결재진행번호")
    private String apprRqstNo;

    @ApiModelProperty(value = "결재진행코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행명")
    private String bizApprStepNm;

    // 결과
    @ApiModelProperty(value = "결재진행번호")
    private String rapprRqstNo;

    @ApiModelProperty(value = "결재진행코드")
    private String rbizApprStepCd;

    @ApiModelProperty(value = "결재진행명")
    private String rbizApprStepNm;

    @ApiModelProperty(value = "일정단계갯수")
    private int cnt;

    @ApiModelProperty(value = "삭제데이터(일정)")
    private int[] deleteData;

    @ApiModelProperty(value = "설비점검결과목록")
    private List<FacilChkResult> FacilChkResults;
    @ApiModelProperty(value = "변경관리번호")
    private int safChngNo;
    @ApiModelProperty(value = "MOC 번호")
    private String chngNum;

    @ApiModelProperty(value = "설비점검일련번호")
    private int safFacilityCheckNo;

    @ApiModelProperty(value = "설비점검일련번호")
    private String safFacilityCheckSchYmd;

    @ApiModelProperty(value = "점검일")
    private String safFacilityCheckYmd;

    @ApiModelProperty(value = "무계획결과등록여부")
    private String noPlanYn;

    @ApiModelProperty(value = "가동전점검 점검자 목록")
    private List<SafPreOperChkPsn> preOperChkPsns;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
    
    @ApiModelProperty(value = "점검대상부서명")
    private String tDeptNm;
    
    @ApiModelProperty(value = "점검대상부서코드")
    private String tDeptCd;
    
    @ApiModelProperty(value = "점검대상사업장코드")
    private String tPlantCd;
    
    @ApiModelProperty(value = "점검수행부서명")
    private String pDeptNm;
    
    @ApiModelProperty(value = "점검수행부서코드")
    private String pDeptCd;
    
    @ApiModelProperty(value = "점검수행사업장코드")
    private String pPlantCd;
    
    @ApiModelProperty(value = "설비명")
    private String facilityNm;
    
    @ApiModelProperty(value = "완료여부")
    private String chkEndYnNm;
    
    @ApiModelProperty(value = "점검대상부서 결과목록mergeKey")
    private String tMergeKey;
    
    @ApiModelProperty(value = "점검수행부서 결과목록mergeKey")
    private String pMergeKey;
    
    @ApiModelProperty(value = "재조회")
    private boolean reflash; 
    
    @ApiModelProperty(value = "")
    private FacilChkItemResult facilChkItemResult; 
}
