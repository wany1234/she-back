package com.she.env.gas.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "활동데이터")
@Data
public class ActData {

    @ApiModelProperty(value = "활동데이터보고순번")
    public int actDaRptNo;

    @ApiModelProperty(value = "황동데이터보고결과순번")
    public int actDaRsltNo;

    @ApiModelProperty(value = "배출시설순번")
    public int ghgFcltNo;

    @ApiModelProperty(value = "활동자료코드")
    public String actDataCd;

    @ApiModelProperty(value = "배출시설산정방법순번")
    public int fcltCalcMtdNo;

    @ApiModelProperty(value = "사업장")
    public String plantCd;

    @ApiModelProperty(value = "사업장명")
    public String plantNm;

    @ApiModelProperty(value = "공장(인)")
    public String plantCd2;

    @ApiModelProperty(value = "부서")
    public String deptNm;

    @ApiModelProperty(value = "공정")
    public String processNm;

    @ApiModelProperty(value = "배출년월")
    public String actMonth;

    @ApiModelProperty(value = "배출년월")
    public String startYear;

    @ApiModelProperty(value = "배출년월")
    public String endYear;

    @ApiModelProperty(value = "배출년")
    public String year;

    @ApiModelProperty(value = "배출유형")
    public String disClsCd;

    @ApiModelProperty(value = "배출활동코드")
    public String disActCd;

    @ApiModelProperty(value = "배출활동명")
    public String disActNm;

    @ApiModelProperty(value = "배출활동코드")
    public String ghgOutActCnt;

    @ApiModelProperty(value = "배출유형")
    public String ghgOutActType;

    @ApiModelProperty(value = "배출시설코드")
    public String disFacCd;

    @ApiModelProperty(value = "배출시설명")
    public String disFacNm;

    @ApiModelProperty(value = "배출시설명")
    public String ghgFcltCnt;

    @ApiModelProperty(value = "배출시설일련번호")
    public String disFacNo;

    @ApiModelProperty(value = "활동중분류")
    public String actClsSec;

    @ApiModelProperty(value = "활동자료명")
    public String actDataNm;

    @ApiModelProperty(value = "단위")
    public String unitType;

    @ApiModelProperty(value = "활동량")
    public float actAmt;

    @ApiModelProperty(value = "작성상태코드")
    public String stepCd;

    @ApiModelProperty(value = "작성상태")
    public String stepNm;

    @ApiModelProperty(value = "등록자")
    public String createUserId;

    @ApiModelProperty(value = "등록자이름")
    public String createUserNm;

    @ApiModelProperty(value = "등록부서코드")
    public String createDeptCd;

    @ApiModelProperty(value = "등록부서이름")
    public String createDeptNm;

    @ApiModelProperty(value = "등록일")
    public String createDt;

    @ApiModelProperty(value = "수정자")
    public String updateUserId;

    @ApiModelProperty(value = "수정자이름")
    public String updateUserNm;

    @ApiModelProperty(value = "수정부서코드")
    public String updateDeptCd;

    @ApiModelProperty(value = "수정부서이름")
    public String updateDeptNm;

    @ApiModelProperty(value = "수정일")
    public String updateDt;

    @ApiModelProperty(value = "기타 파라미터")
    public String parameter;

    @ApiModelProperty(value = "활동데이터보고결과 리스트")
    public List<ActDataResult> actDataList;

    @ApiModelProperty(value = "CO2 배출량")
    private float emsCo2;
    @ApiModelProperty(value = "CH4 배출량")
    private float emsCh4;
    @ApiModelProperty(value = "N2O 배출량")
    private float emsN2o;
    @ApiModelProperty(value = "HFCs 배출량")
    private float emsHfcs;
    @ApiModelProperty(value = "PFCs 배출량")
    private float emsPfcs;
    @ApiModelProperty(value = "SF6 배출량")
    private float emsSf6;
    @ApiModelProperty(value = "에너지 사용량")
    private float energyUsage;
    @ApiModelProperty(value = "전략 사용량")
    private float elecUsage;
    @ApiModelProperty(value = "스팀 사용량")
    private float steamUsage;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;
    @ApiModelProperty(value = "작성일")
    private String writerDt;

    public ActData() {
        super();
    }

    public ActData(int actDaRptNo, int actDaRsltNo, int ghgFcltNo, String actDataCd, int fcltCalcMtdNo, String plantCd, String plantCd2, String deptNm, String processNm, String actMonth, String startYear, String endYear, String year, String disClsCd, String disActCd, String disActNm, String disFacCd, String disFacNm, String disFacNo,
            String actClsSec, String actDataNm, String unitType, float actAmt, String stepCd, String stepNm, String createUserId, String createUserNm, String createDeptCd, String createDeptNm, String createDt, String updateUserId, String updateUserNm, String updateDeptCd, String updateDeptNm, String updateDt, String parameter,
            List<ActDataResult> actDataList, String writerUserNm, String writerDt) {
        super();
        this.actDaRptNo = actDaRptNo;
        this.actDaRsltNo = actDaRsltNo;
        this.ghgFcltNo = ghgFcltNo;
        this.actDataCd = actDataCd;
        this.fcltCalcMtdNo = fcltCalcMtdNo;
        this.plantCd = plantCd;
        this.plantCd2 = plantCd2;
        this.deptNm = deptNm;
        this.processNm = processNm;
        this.actMonth = actMonth;
        this.startYear = startYear;
        this.endYear = endYear;
        this.year = year;
        this.disClsCd = disClsCd;
        this.disActCd = disActCd;
        this.disActNm = disActNm;
        this.disFacCd = disFacCd;
        this.disFacNm = disFacNm;
        this.disFacNo = disFacNo;
        this.actClsSec = actClsSec;
        this.actDataNm = actDataNm;
        this.unitType = unitType;
        this.actAmt = actAmt;
        this.stepCd = stepCd;
        this.stepNm = stepNm;
        this.createUserId = createUserId;
        this.createUserNm = createUserNm;
        this.createDeptCd = createDeptCd;
        this.createDeptNm = createDeptNm;
        this.createDt = createDt;
        this.updateUserId = updateUserId;
        this.updateUserNm = updateUserNm;
        this.updateDeptCd = updateDeptCd;
        this.updateDeptNm = updateDeptNm;
        this.updateDt = updateDt;
        this.parameter = parameter;
        this.actDataList = actDataList;
        this.writerUserNm = writerUserNm;
        this.writerDt = writerDt;
    }

}
