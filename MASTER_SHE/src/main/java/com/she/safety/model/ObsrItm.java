package com.she.safety.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "안전관찰체크항목(SK E&S)")
public class ObsrItm {
    @ApiModelProperty(value = "안전관찰체크항목번호")
    public int safObsrItmNo;

    @ApiModelProperty(value = "C사업장코드")
    public String plantCd;

    @ApiModelProperty(value = "사업장명칭")
    public String plantNm;

    @ApiModelProperty(value = "C관찰항목분류코드")
    public String itmTypeCd;

    @ApiModelProperty(value = "C관찰항목분류명칭")
    public String itmTypeNm;

    @ApiModelProperty(value = "관찰항목")
    public String obsrItmNm;

    @ApiModelProperty(value = "사용여부")
    public String useYn;

    @ApiModelProperty(value = "사용여부명칭")
    public String useYnNm;

    public ObsrItm() {
    }

    public int getSafObsrItmNo() {
        return safObsrItmNo;
    }

    public void setSafObsrItmNo(int safObsrItmNo) {
        this.safObsrItmNo = safObsrItmNo;
    }

    public String getPlantCd() {
        return plantCd;
    }

    public void setPlantCd(String plantCd) {
        this.plantCd = plantCd;
    }

    public String getPlantNm() {
        return plantNm;
    }

    public void setPlantNm(String plantNm) {
        this.plantNm = plantNm;
    }

    public String getItmTypeCd() {
        return itmTypeCd;
    }

    public void setItmTypeCd(String itmTypeCd) {
        this.itmTypeCd = itmTypeCd;
    }

    public String getItmTypeNm() {
        return itmTypeNm;
    }

    public void setItmTypeNm(String itmTypeNm) {
        this.itmTypeNm = itmTypeNm;
    }

    public String getObsrItmNm() {
        return obsrItmNm;
    }

    public void setObsrItmNm(String obsrItmNm) {
        this.obsrItmNm = obsrItmNm;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getUseYnNm() {
        return useYnNm;
    }

    public void setUseYnNm(String useYnNm) {
        this.useYnNm = useYnNm;
    }
}
