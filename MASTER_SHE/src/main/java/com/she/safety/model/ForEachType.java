package com.she.safety.model;

import com.she.baseInfo.model.FacilityMst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "설비별 유형")
public class ForEachType {

    @ApiModelProperty(value = "설비점검번호")
    private int safFacilChkNo;
    @ApiModelProperty(value = "점검종류코드")
    private String safCheckTypeCd;
    @ApiModelProperty(value = "점검종류명")
    private String safCheckTypeNm;
    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;
    @ApiModelProperty(value = "설비코드")
    private String chkUserNm;
    @ApiModelProperty(value = "설비코드")
    private String chkSchYmd;
    @ApiModelProperty(value = "설비코드")
    private String chkEndYn;
    @ApiModelProperty(value = "점검완료여부")
    private String chkResult;
    @ApiModelProperty(value = "점검결과요약")
    
    private String pDeptCd;
	private String pDeptNm;
    private List<FacilChkItemResult> facilChkItemResultList;

    @ApiModelProperty(value = "설비코드")
    private List<FacilityMst> forEachFacilityList;

    public List<FacilityMst> getForEachFacilityList() {
        return forEachFacilityList;
    }

    public String getChkEndYn() {
        return chkEndYn;
    }

    public void setChkEndYn(String chkEndYn) {
        this.chkEndYn = chkEndYn;
    }

    public String getChkResult() {
        return chkResult;
    }

    public void setChkResult(String chkResult) {
        this.chkResult = chkResult;
    }

    public void setForEachFacilityList(List<FacilityMst> forEachFacilityList) {
        this.forEachFacilityList = forEachFacilityList;
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

    public int getSafFacilChkNo() {
        return safFacilChkNo;
    }

    public void setSafFacilChkNo(int safFacilChkNo) {
        this.safFacilChkNo = safFacilChkNo;
    }

    public String getSafCheckTypeCd() {
        return safCheckTypeCd;
    }

    public void setSafCheckTypeCd(String safCheckTypeCd) {
        this.safCheckTypeCd = safCheckTypeCd;
    }

    public String getSafFacilityCd() {
        return safFacilityCd;
    }

    public void setSafFacilityCd(String safFacilityCd) {
        this.safFacilityCd = safFacilityCd;
    }

    public String getSafCheckTypeNm() {
        return safCheckTypeNm;
    }

    public void setSafCheckTypeNm(String safCheckTypeNm) {
        this.safCheckTypeNm = safCheckTypeNm;
    }

    public List<FacilChkItemResult> getFacilChkItemResultList() {
        return facilChkItemResultList;
    }

    public void setFacilChkItemResultList(List<FacilChkItemResult> facilChkItemResultList) {
        this.facilChkItemResultList = facilChkItemResultList;
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