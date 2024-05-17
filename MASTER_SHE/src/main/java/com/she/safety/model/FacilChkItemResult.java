package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@ApiModel(description = "설비점검 항목별결과")
public class FacilChkItemResult {

    @ApiModelProperty(value = "설비점검번호")
    private int safFacilChkNo;

    @ApiModelProperty(value = "개선요청 key")
    private int safImprActNo;

    @ApiModelProperty(value = "개선요청 key")
    private int safFacilSysNo;

    @ApiModelProperty(value = "점검종류코드")
    private String safCheckTypeCd;

    @ApiModelProperty(value = "점검종류명칭")
    private String safCheckTypeNm;

    @ApiModelProperty(value = "점검종류코드")
    private String chkUserNm;

    @ApiModelProperty(value = "점검종류코드")
    private String chkSchYmd;

    @ApiModelProperty(value = "개선요청 명칭")
    private String improvement;

    @ApiModelProperty(value = "설비유형")
    private String safFacilityTypeCd;

    @ApiModelProperty(value = "설비점검항목번호")
    private String safFacilityCheckItemNo;

    @ApiModelProperty(value = "설비점검항목명")
    private String safFacilityCheckNm;

    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;

    @ApiModelProperty(value = "점검결과1")
    private String chkResult;
    private String chkResultNm;

    @ApiModelProperty(value = "점검결과2")
    private String chkResultTwo;

    @ApiModelProperty(value = "점검시기")
    private String chkYmd;

    @ApiModelProperty(value = "점검시기")
    private String chkEndYn;

    @ApiModelProperty(value = "개선항목")
    private String chkImpr;

    @ApiModelProperty(value = "점검결과")
    private int pSafFacilityCheckItemNo;

    @ApiModelProperty(value = "계획/결과")
    private String kind;

    @ApiModelProperty(value = "부모명")
    private String pName;

    @ApiModelProperty(value = "대상여부 체크")
    private String checkYn;

    @ApiModelProperty(value = "부모부모명")
    private String ppName;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "뎁스에 따른 선택여부")
    private String checking;

    @ApiModelProperty(value = "부모여부")
    private String parentYn;

    @ApiModelProperty(value = "점검항목")
    private List<FacilChkItemResult> forEachTypeList;

    @ApiModelProperty(value = "점검유형별 설비 목록")
    private List<ForEachType> forEachFacilList;

	@ApiModelProperty(value = "점검수행부서")
    private String pDeptCd;
	
	@JsonProperty("pDeptNm")
    @ApiModelProperty(value = "점검수행부서")
    private String pDeptNm;

    private String chkYmdNm;

    public FacilChkItemResult(int safFacilChkNo, int safImprActNo, String safFacilityCheckItemNo, String safFacilityCd, String safCheckTypeCd) {
        this.safFacilChkNo = safFacilChkNo;
        this.safImprActNo = safImprActNo;
        this.safFacilityCd = safFacilityCd;
        this.safFacilityCheckItemNo = safFacilityCheckItemNo;
        this.safCheckTypeCd = safCheckTypeCd;
    }

    public FacilChkItemResult() {
    }

    public String getChkYmdNm() {
        return chkYmdNm;
    }

    public void setChkYmdNm(String chkYmdNm) {
        this.chkYmdNm = chkYmdNm;
    }

    public String getChkEndYn() {
        return chkEndYn;
    }

    public void setChkEndYn(String chkEndYn) {
        this.chkEndYn = chkEndYn;
    }

    public int getSafFacilChkNo() {
        return safFacilChkNo;
    }

    public void setSafFacilChkNo(int safFacilChkNo) {
        this.safFacilChkNo = safFacilChkNo;
    }

    public String getSafFacilityCheckItemNo() {
        return safFacilityCheckItemNo;
    }

    public void setSafFacilityCheckItemNo(String safFacilityCheckItemNo) {
        this.safFacilityCheckItemNo = safFacilityCheckItemNo;
    }

    public String getSafFacilityCheckNm() {
        return safFacilityCheckNm;
    }

    public void setSafFacilityCheckNm(String safFacilityCheckNm) {
        this.safFacilityCheckNm = safFacilityCheckNm;
    }

    public String getSafFacilityCd() {
        return safFacilityCd;
    }

    public void setSafFacilityCd(String safFacilityCd) {
        this.safFacilityCd = safFacilityCd;
    }

    public String getChkResult() {
        return chkResult;
    }

    public void setChkResult(String chkResult) {
        this.chkResult = chkResult;
    }

    public String getChkYmd() {
        return chkYmd;
    }

    public void setChkYmd(String chkYmd) {
        this.chkYmd = chkYmd;
    }

    public String getChkImpr() {
        return chkImpr;
    }

    public void setChkImpr(String chkImpr) {
        this.chkImpr = chkImpr;
    }

    public int getpSafFacilityCheckItemNo() {
        return pSafFacilityCheckItemNo;
    }

    public void setpSafFacilityCheckItemNo(int pSafFacilityCheckItemNo) {
        this.pSafFacilityCheckItemNo = pSafFacilityCheckItemNo;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPpName() {
        return ppName;
    }

    public void setPpName(String ppName) {
        this.ppName = ppName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSafFacilityTypeCd() {
        return safFacilityTypeCd;
    }

    public String getCheckYn() {
        return checkYn;
    }

    public void setCheckYn(String checkYn) {
        this.checkYn = checkYn;
    }

    public String getChecking() {
        return checking;
    }

    public String getChkResultTwo() {
        return chkResultTwo;
    }

    public void setChkResultTwo(String chkResultTwo) {
        this.chkResultTwo = chkResultTwo;
    }

    public void setChecking(String checking) {
        this.checking = checking;
    }

    public void setSafFacilityTypeCd(String safFacilityTypeCd) {
        this.safFacilityTypeCd = safFacilityTypeCd;
    }

    public String getSafCheckTypeCd() {
        return safCheckTypeCd;
    }

    public void setSafCheckTypeCd(String safCheckTypeCd) {
        this.safCheckTypeCd = safCheckTypeCd;
    }

    public String getSafCheckTypeNm() {
        return safCheckTypeNm;
    }

    public void setSafCheckTypeNm(String safCheckTypeNm) {
        this.safCheckTypeNm = safCheckTypeNm;
    }

    public String getImprovement() {
        return improvement;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public int getSafImprActNo() {
        return safImprActNo;
    }

    public void setSafImprActNo(int safImprActNo) {
        this.safImprActNo = safImprActNo;
    }

    public int getSafFacilSysNo() {
        return safFacilSysNo;
    }

    public void setSafFacilSysNo(int safFacilSysNo) {
        this.safFacilSysNo = safFacilSysNo;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<FacilChkItemResult> getForEachTypeList() {
        return forEachTypeList;
    }

    public void setForEachTypeList(List<FacilChkItemResult> forEachTypeList) {
        this.forEachTypeList = forEachTypeList;
    }

    public List<ForEachType> getForEachFacilList() {
        return forEachFacilList;
    }

    public void setForEachFacilList(List<ForEachType> forEachFacilList) {
        this.forEachFacilList = forEachFacilList;
    }

    public String getChkUserNm() {
        return chkUserNm;
    }

    public void setChkUserNm(String chkUserNm) {
        this.chkUserNm = chkUserNm;
    }

    public String getChkSchYmd() {
        return chkSchYmd;
    }

    public void setChkSchYmd(String chkSchYmd) {
        this.chkSchYmd = chkSchYmd;
    }

    public String getParentYn() {
        return parentYn;
    }

    public void setParentYn(String parentYn) {
        this.parentYn = parentYn;
    }

    public String getChkResultNm() {
        return chkResultNm;
    }

    public void setChkResultNm(String chkResultNm) {
        this.chkResultNm = chkResultNm;
    }
    
    public String getpDeptCd() {
  		return pDeptCd;
  	}

  	public void setpDeptCd(String pDeptCd) {
  		this.pDeptCd = pDeptCd;
  	}

  	public String getpDeptNm() {
  		return pDeptNm;
  	}

  	public void setpDeptNm(String pDeptNm) {
  		this.pDeptNm = pDeptNm;
  	}
}
