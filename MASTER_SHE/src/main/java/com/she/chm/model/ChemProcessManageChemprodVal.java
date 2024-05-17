package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "공정관리요령취급자재")
@Data
public class ChemProcessManageChemprodVal {

    @ApiModelProperty(value = "공정관리요령번호")
    private int chmProcessManageNo;

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "취급자재명(KOR)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명(ENG)")
    private String chemProdNmEn;

    @ApiModelProperty(value = "SAP자재코드")
    private String sapMatCd;

    @ApiModelProperty(value = "제조업체코드")
    private String makecpCd;

    @ApiModelProperty(value = "제조업체명")
    private String makecpNm;

    @ApiModelProperty(value = "공급업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "공급업체명")
    private String vendorNm;
}
