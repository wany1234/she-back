package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "자재검토요청 취급자재 규제정보")
@Data
public class RegulItmMatVal {
    @ApiModelProperty(value = "자재검토요청 취급자재 규제정보번호")
    private int regulItmMatValNo;

    @ApiModelProperty(value = "법규별분류번호")
    private int regulItmNo;

    @ApiModelProperty(value = "검토요청번호")
    private int matChkRqstNo;

    @ApiModelProperty(value = "법규별분류코드")
    private String regulItmCd;

    @ApiModelProperty(value = "법규별분류명")
    private String regulItmNm;

    @ApiModelProperty(value = "법규별분류설명")
    private String contents;

    @ApiModelProperty(value = "물질규제법규코드")
    private String regulLawCd;

    @ApiModelProperty(value = "물질규제법규명")
    private String regulLawNm;

    @ApiModelProperty(value = "규제기관코드")
    private String regulOrgCd;

    @ApiModelProperty(value = "규제기관명")
    private String regulOrgNm;

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
    //
    // public int getRegulItmMatValNo()
    // {
    // return regulItmMatValNo;
    // }
    // public void setRegulItmMatValNo(int regulItmMatValNo)
    // {
    // this.regulItmMatValNo = regulItmMatValNo;
    // }
    // public int getRegulItmNo()
    // {
    // return regulItmNo;
    // }
    // public void setRegulItmNo(int regulItmNo)
    // {
    // this.regulItmNo = regulItmNo;
    // }
    // public int getMatChkRqstNo()
    // {
    // return matChkRqstNo;
    // }
    // public void setMatChkRqstNo(int matChkRqstNo)
    // {
    // this.matChkRqstNo = matChkRqstNo;
    // }
    // public String getRegulItmCd()
    // {
    // return regulItmCd;
    // }
    // public void setRegulItmCd(String regulItmCd)
    // {
    // this.regulItmCd = regulItmCd;
    // }
    // public String getRegulItmNm()
    // {
    // return regulItmNm;
    // }
    // public void setRegulItmNm(String regulItmNm)
    // {
    // this.regulItmNm = regulItmNm;
    // }
    // public String getContents()
    // {
    // return contents;
    // }
    // public void setContents(String contents)
    // {
    // this.contents = contents;
    // }
    // public String getRegulLawCd()
    // {
    // return regulLawCd;
    // }
    // public void setRegulLawCd(String regulLawCd)
    // {
    // this.regulLawCd = regulLawCd;
    // }
    // public String getRegulLawNm()
    // {
    // return regulLawNm;
    // }
    // public void setRegulLawNm(String regulLawNm)
    // {
    // this.regulLawNm = regulLawNm;
    // }
    // public String getCreateUserId()
    // {
    // return createUserId;
    // }
    // public void setCreateUserId(String createUserId)
    // {
    // this.createUserId = createUserId;
    // }
    // public String getCreateUserNm()
    // {
    // return createUserNm;
    // }
    // public void setCreateUserNm(String createUserNm)
    // {
    // this.createUserNm = createUserNm;
    // }
    // public String getCreateDt()
    // {
    // return createDt;
    // }
    // public void setCreateDt(String createDt)
    // {
    // this.createDt = createDt;
    // }
    // public String getUpdateUserId()
    // {
    // return updateUserId;
    // }
    // public void setUpdateUserId(String updateUserId)
    // {
    // this.updateUserId = updateUserId;
    // }
    // public String getUpdateUserNm()
    // {
    // return updateUserNm;
    // }
    // public void setUpdateUserNm(String updateUserNm)
    // {
    // this.updateUserNm = updateUserNm;
    // }
    // public String getUpdateDt()
    // {
    // return updateDt;
    // }
    // public void setUpdateDt(String updateDt)
    // {
    // this.updateDt = updateDt;
    // }

}
