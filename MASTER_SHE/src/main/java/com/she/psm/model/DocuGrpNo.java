package com.she.psm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Country & Site")
@Data
public class DocuGrpNo {
	  @ApiModelProperty(value = "문서그룹번호")
	  private String docuGrpNo;





}
