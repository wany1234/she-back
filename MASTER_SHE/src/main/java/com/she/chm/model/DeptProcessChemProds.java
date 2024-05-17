package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "부서공정별 다중 취급자재")
@Data
public class DeptProcessChemProds {

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

    @ApiModelProperty(value = "부서공정별 취급자재목록")
    private List<DeptChemprod> chemProds;

    @ApiModelProperty(value = "변경된 취급자재")
    private List<String> changeChemProds;

}
