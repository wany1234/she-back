package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "취급자재")
@Data
public class Chemprod {
    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "SAP자재코드")
    private String sapMatCd;

    @ApiModelProperty(value = "취급자재명(KOR)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명(ENG)")
    private String chemProdNmEn;

    @ApiModelProperty(value = "공급업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "공급업체명")
    private String vendorNm;

    @ApiModelProperty(value = "공급업체국가코드")
    private String originCd;

    @ApiModelProperty(value = "공급업체국가명")
    private String originNm;

    @ApiModelProperty(value = "제조업체코드")
    private String makerCd;

    @ApiModelProperty(value = "제조업체명")
    private String makerNm;

    @ApiModelProperty(value = "제조업체국가코드")
    private String makerOriginCd;

    @ApiModelProperty(value = "제조업체국가명")
    private String makerOriginNm;

    @ApiModelProperty(value = "대표사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "대표사업장명")
    private String plantNm;

    @ApiModelProperty(value = "성상코드")
    private String property;

    @ApiModelProperty(value = "성상명")
    private String propertyNm;

    @ApiModelProperty(value = "비산성")
    private String fugacity;

    @ApiModelProperty(value = "물질구성")
    private String mixYn;

    @ApiModelProperty(value = "물질구성명")
    private String mixYnNm;

    @ApiModelProperty(value = "끓는점")
    private String boilpoint;

    @ApiModelProperty(value = "녹는점")
    private String meltingPnt;

    @ApiModelProperty(value = "분자량")
    private String moleWeight;

    @ApiModelProperty(value = "사업구분코드")
    private String bizCatCd;

    @ApiModelProperty(value = "사업구분명")
    private String bizCatNm;

    @ApiModelProperty(value = "제품구분코드")
    private String prodCatCd;

    @ApiModelProperty(value = "제품구분명")
    private String prodCatNm;

    @ApiModelProperty(value = "용도코드")
    private String usageCd;

    @ApiModelProperty(value = "용도명")
    private String usageNm;

    @ApiModelProperty(value = "비중")
    private String specificGravity;

    @ApiModelProperty(value = "대표CASno")
    private String casNo;

    @ApiModelProperty(value = "용도(기타)")
    private String usageEtc;

    @ApiModelProperty(value = "HS번호")
    private String hsNum;

    @ApiModelProperty(value = "보관장소")
    private String stckArea;

    @ApiModelProperty(value = "대기오염물질여부")
    private String airPolYn;

    @ApiModelProperty(value = "수질오염물질여부")
    private String wtrPolYn;

    @ApiModelProperty(value = "인허가대상물질여부")
    private String licensingYn;

    @ApiModelProperty(value = "인허가대상물질여부명")
    private String licensingYnNm;

    @ApiModelProperty(value = "위험물질여부")
    private String dgrYn;

    @ApiModelProperty(value = "성분확인방법코드")
    private String cfmMtdCd;

    @ApiModelProperty(value = "성분확인방법명")
    private String cfmMtdNm;

    @ApiModelProperty(value = "제조해당여부")
    private String makeYn;

    @ApiModelProperty(value = "제조해당여부명")
    private String makeYnNm;

    @ApiModelProperty(value = "수입해당여부")
    private String impYn;

    @ApiModelProperty(value = "수입해당여부명")
    private String impYnNm;

    @ApiModelProperty(value = "구매해당여부")
    private String buyYn;

    @ApiModelProperty(value = "구매해당여부명")
    private String buyYnNm;

    @ApiModelProperty(value = "판매해당여부")
    private String salesYn;

    @ApiModelProperty(value = "판매해당여부명")
    private String salesYnNm;

    @ApiModelProperty(value = "사용해당여부")
    private String usingYn;

    @ApiModelProperty(value = "사용해당여부명")
    private String usingYnNm;

    @ApiModelProperty(value = "수출해당여부")
    private String expYn;

    @ApiModelProperty(value = "수출해당여부명")
    private String expYnNm;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "PSM-폭발한계상한")
    private String explUpLimit;

    @ApiModelProperty(value = "PSM-폭발한계하한")
    private String explLowLimit;

    @ApiModelProperty(value = "PSM-노출기준")
    private String leakThresh;

    @ApiModelProperty(value = "PSM-독성치")
    private String lc50;

    @ApiModelProperty(value = "PSM-인화점")
    private String flashingPnt;

    @ApiModelProperty(value = "PSM-발화점")
    private String ignitionPnt;

    @ApiModelProperty(value = "PSM-증기압")
    private String vaporPressure;

    @ApiModelProperty(value = "PSM-이상반응유무")
    private String extraReactionYn;

    @ApiModelProperty(value = "PSM-이상반응유무명")
    private String extraReactionYnNm;

    @ApiModelProperty(value = "PSM-부식성유무")
    private String corrosivenessYn;

    @ApiModelProperty(value = "PSM-부식성유무명")
    private String corrosivenessYnNm;

    @ApiModelProperty(value = "일일취급량")
    private String dailyVol;

    @ApiModelProperty(value = "저장량")
    private String maxVol;

    @ApiModelProperty(value = "사용 여부")
    private String useYn;

    @ApiModelProperty(value = "사용 여부명")
    private String useYnNm;

    @ApiModelProperty(value = "작성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "작성자명")
    private String createUserNm;

    @ApiModelProperty(value = "작성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "취급자재 구성성분 및 규제정보")
    private ChemprodChemRegul chemprodChemRegul;

    @ApiModelProperty(value = "chem승인코드?")
    private String chemCode;

    @ApiModelProperty(value = "")
    private String complexYn;

    @ApiModelProperty(value = "")
    private String complexYnNm;

    @ApiModelProperty(value = "")
    private String revNum;

    @ApiModelProperty(value = "")
    private String revContent;

    @ApiModelProperty(value = "")
    private String moleFormula;

    @ApiModelProperty(value = "")
    private String chemNmKr;

    @ApiModelProperty(value = "")
    private String msds;

    @ApiModelProperty(value = "")
    private String safFacilityCd;

    @ApiModelProperty(value = "")
    private String facilityNm;

    @ApiModelProperty(value = "")
    private int equipmentNo;

    @ApiModelProperty(value = "")
    private int pipeNo;

    @ApiModelProperty(value = "화학물질번호")
    private int chemNo;

    @ApiModelProperty(value = "MSDS파일명")
    private String fileOrgNm;

    @ApiModelProperty(value = "MSDS파일저장경로")
    private String fileDownPath;

    @ApiModelProperty(value = "취급자재 위험물질")
    private int chemprodRegulItmNo;

    @ApiModelProperty(value = "취급자재 위험물질명")
    private String chemprodRegulItmNm;

    @ApiModelProperty(value = "[server paging] 총 갯수")
    private int totalCnt;

    // @ApiModelProperty(value = "안씀. 1:n에서 1:1로 변경됨")
    // private List<SAPMAT> SAPMATs;
    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
