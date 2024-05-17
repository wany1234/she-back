package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "공정별 취급자재")
@Data
public class DeptChemprod extends ChemprodChem {

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "부서코드")
    private String deptCd;

    @ApiModelProperty(value = "부서명")
    private String deptNm;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "취급자재명(국문)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명(영문)")
    private String chemProdNmEn;

    @ApiModelProperty(value = "취급자재명(국문/영문)")
    private String chemProdNm;

    @ApiModelProperty(value = "SAP자재코드")
    private String sapMatCd;

    @ApiModelProperty(value = "일사용량")
    private String dayUsage;

    @ApiModelProperty(value = "사용단위코드")
    private String usageUnit;

    @ApiModelProperty(value = "사용단위명")
    private String usageUnitNm;

    @ApiModelProperty(value = "사용용도")
    private String substanceUsing;

    @ApiModelProperty(value = "취급형태코드")
    private String handlingType;

    @ApiModelProperty(value = "취급형태명")
    private String handlingTypeNm;

    @ApiModelProperty(value = "유소견자여부(사용안함)")
    private String suspectYn;

    @ApiModelProperty(value = "유소견자여부명(사용안함)")
    private String suspectYnNm;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "제조업체코드")
    private String makerCd;

    @ApiModelProperty(value = "제조업체명")
    private String makerNm;

    @ApiModelProperty(value = "납품업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "납품업체명")
    private String vendorNm;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
