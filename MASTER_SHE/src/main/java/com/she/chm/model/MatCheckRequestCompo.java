package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "자재검토요청조성정보")
@Data
public class MatCheckRequestCompo {
    @ApiModelProperty(value = "자재검토요청조성정보번호")
    private int matChkRqstCompoNo;

    @ApiModelProperty(value = "검토요청번호")
    private int matChkRqstNo;

    @ApiModelProperty(value = "정렬순서")
    private int sortOrder;

    @ApiModelProperty(value = "화학물질id")
    private int chemNo;

    @ApiModelProperty(value = "화학물질CASNO")
    private String casNo;

    @ApiModelProperty(value = "화학물질명(국문)")
    private String chemNmKr;

    @ApiModelProperty(value = "화학물질명(영문)")
    private String chemNmEn;

    @ApiModelProperty(value = "화학물질총칭명")
    private String chemNameGeneric;

    @ApiModelProperty(value = "함량하한")
    private double limitLow;

    @ApiModelProperty(value = "함량상한")
    private double limitHigh;

    @ApiModelProperty(value = "함량대표값")
    private double limitRepval;

    @ApiModelProperty(value = "구성성분_함유량내용")
    private String chemContent;

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

    @ApiModelProperty(value = "영업비밀여부")
    private String secretWhether;

    @ApiModelProperty(value = "대체물질명")
    private String substitute;
    //
    // public String getCasNo()
    // {
    // return casNo;
    // }
    // public void setCasNo(String casNo)
    // {
    // this.casNo = casNo;
    // }
    // public String getChemNmKr()
    // {
    // return chemNmKr;
    // }
    // public void setChemNmKr(String chemNmKr)
    // {
    // this.chemNmKr = chemNmKr;
    // }
    // public String getChemNmEn()
    // {
    // return chemNmEn;
    // }
    // public void setChemNmEn(String chemNmEn)
    // {
    // this.chemNmEn = chemNmEn;
    // }
    // public int getMatChkRqstCompoNo()
    // {
    // return matChkRqstCompoNo;
    // }
    // public void setMatChkRqstCompoNo(int matChkRqstCompoNo)
    // {
    // this.matChkRqstCompoNo = matChkRqstCompoNo;
    // }
    // public int getMatChkRqstNo()
    // {
    // return matChkRqstNo;
    // }
    // public void setMatChkRqstNo(int matChkRqstNo)
    // {
    // this.matChkRqstNo = matChkRqstNo;
    // }
    // public int getSortOrder()
    // {
    // return sortOrder;
    // }
    // public void setSortOrder(int sortOrder)
    // {
    // this.sortOrder = sortOrder;
    // }
    // public int getChemNo()
    // {
    // return chemNo;
    // }
    // public void setChemNo(int chemNo)
    // {
    // this.chemNo = chemNo;
    // }
    // public String getChemNameGeneric()
    // {
    // return chemNameGeneric;
    // }
    // public void setChemNameGeneric(String chemNameGeneric)
    // {
    // this.chemNameGeneric = chemNameGeneric;
    // }
    // public double getLimitLow()
    // {
    // return limitLow;
    // }
    // public void setLimitLow(double limitLow)
    // {
    // this.limitLow = limitLow;
    // }
    // public double getLimitHigh()
    // {
    // return limitHigh;
    // }
    // public void setLimitHigh(double limitHigh)
    // {
    // this.limitHigh = limitHigh;
    // }
    // public double getLimitRepval()
    // {
    // return limitRepval;
    // }
    // public void setLimitRepval(double limitRepval)
    // {
    // this.limitRepval = limitRepval;
    // }
    // public String getChemContent()
    // {
    // return chemContent;
    // }
    // public void setChemContent(String chemContent)
    // {
    // this.chemContent = chemContent;
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
    //

}
