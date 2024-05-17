package com.she.chm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "자재검토요청정보")
@Data
public class MatCheckRequest {
    @ApiModelProperty(value = "검토요청번호")
    private int matChkRqstNo;

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "회사코드")
    private String branchCd;

    @ApiModelProperty(value = "요청번호")
    private String rqstNo;

    @ApiModelProperty(value = "요청품목번호")
    private String rqstItmNo;

    @ApiModelProperty(value = "공급업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "공급업체명")
    private String vendorNm;

    @ApiModelProperty(value = "공급업체국가코드")
    private String originCd;

    @ApiModelProperty(value = "")
    private String originNmKr;

    @ApiModelProperty(value = "")
    private String originNmEn;

    @ApiModelProperty(value = "제조업체코드")
    private String makecpCd;

    @ApiModelProperty(value = "제조업체명")
    private String makecpNm;

    @ApiModelProperty(value = "제조업체국가코드")
    private String makeoriginCd;

    @ApiModelProperty(value = "")
    private String makeOriginNmKr;

    @ApiModelProperty(value = "")
    private String makeOriginNmEn;

    @ApiModelProperty(value = "SAP자재코드")
    private String sapMatCd;

    @ApiModelProperty(value = "자재명(국)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "자재명(영)")
    private String chemProdNmEn;

    @ApiModelProperty(value = "chem승인코드")
    private String chemCode;

    @ApiModelProperty(value = "물질구성여부")
    private String mixYn;

    @ApiModelProperty(value = "물질구성여부명")
    private String mixYnNm;

    @ApiModelProperty(value = "사업구분코드")
    private String bizCatCd;

    @ApiModelProperty(value = "사업구분명")
    private String bizCatNm;

    @ApiModelProperty(value = "제품구분코드")
    private String prodCatCd;

    @ApiModelProperty(value = "제품구분명")
    private String prodCatNm;

    @ApiModelProperty(value = "성상코드")
    private String property;

    @ApiModelProperty(value = "성상")
    private String propertyNm;

    @ApiModelProperty(value = "비중")
    private String specificGravity;

    @ApiModelProperty(value = "연간예정량")
    private int expectAmt;

    @ApiModelProperty(value = "비고")
    private String etcDesc;

    @ApiModelProperty(value = "검토요청구분")
    private String rqstType;

    @ApiModelProperty(value = "검토요청구분명")
    private String rqstTypeNm;

    @ApiModelProperty(value = "검토요청일")
    private String rqstDt;

    @ApiModelProperty(value = "검토요청자ID")
    private String chkRqsterId;

    @ApiModelProperty(value = "검토요청자명")
    private String chkRqsterNm;

    @ApiModelProperty(value = "검토요청자부서명")
    private String deptNm;

    @ApiModelProperty(value = "검토요청사항")
    private String chkRqstDesc;

    @ApiModelProperty(value = "검토진행상태")
    private String chkRqstState;

    @ApiModelProperty(value = "검토진행상태명")
    private String chkRqstStateNm;

    @ApiModelProperty(value = "검토확인일")
    private String chkDt;

    @ApiModelProperty(value = "검토자ID")
    private String reviewerId;

    @ApiModelProperty(value = "검토자명")
    private String reviewerNm;

    @ApiModelProperty(value = "검토결과_안전보건팀")
    private String chkResult;

    @ApiModelProperty(value = "검토결과_안전보건팀 적합여부")
    private String chkResultYn;

    @ApiModelProperty(value = "검토결과_안전보건팀 완료여부")
    private String chkResultFinYn;

    @ApiModelProperty(value = "검토결과_안전보건팀 완료처리자아이디")
    private String chkResultFinUserId;

    @ApiModelProperty(value = "검토결과_안전보건팀 완료처리자")
    private String chkResultFinUserNm;

    @ApiModelProperty(value = "검토결과_안전보건팀 완료처리일")
    private String chkResultFinDt;

    @ApiModelProperty(value = "검토결과_환경팀")
    private String chkResultEnv;

    @ApiModelProperty(value = "검토결과_환경팀 적합여부")
    private String chkResultEnvYn;

    @ApiModelProperty(value = "검토결과_환경팀 완료여부")
    private String chkResultEnvFinYn;

    @ApiModelProperty(value = "검토결과_환경팀 완료처리자아이디")
    private String chkResultEnvFinUserId;

    @ApiModelProperty(value = "검토결과_환경팀 완료처리자")
    private String chkResultEnvFinUserNm;

    @ApiModelProperty(value = "검토결과_환경팀 완료처리일")
    private String chkResultEnvFinDt;

    @ApiModelProperty(value = "검토결과(안씀)")
    private String chkResultHea;

    @ApiModelProperty(value = "HS번호")
    private String hsNum;

    @ApiModelProperty(value = "용도코드")
    private String usageCd;

    @ApiModelProperty(value = "용도")
    private String usageNm;

    @ApiModelProperty(value = "용도(기타)")
    private String usageEtc;

    @ApiModelProperty(value = "성분확인방법")
    private String cfmMtdCd;

    @ApiModelProperty(value = "제조여부")
    private String makeYn;

    @ApiModelProperty(value = "수입여부")
    private String impYn;

    @ApiModelProperty(value = "구매여부")
    private String buyYn;

    @ApiModelProperty(value = "사용여부")
    private String usingYn;

    @ApiModelProperty(value = "판매여부")
    private String salesYn;

    @ApiModelProperty(value = "수출여부")
    private String expYn;

    @ApiModelProperty(value = "인허가대상물질여부")
    private String licensingYn;

    @ApiModelProperty(value = "사내자재추가여부")
    private String spcChemYn;

    @ApiModelProperty(value = "자재코드생성여부")
    private String sapMatYn;

    @ApiModelProperty(value = "SAP검토요청고유코드")
    private String sapIfCd;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "위험물질여부")
    private String dgrYn;

    @ApiModelProperty(value = "보관장소")
    private String stckArea;

    @ApiModelProperty(value = "대기오염물질여부")
    private String airPolYn;

    @ApiModelProperty(value = "수질오염물질여부")
    private String wtrPolYn;

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

    @ApiModelProperty(value = "취급자재 위험물질")
    private int chemprodRegulItmNo;

    @ApiModelProperty(value = "취급자재 위험물질명")
    private String chemprodRegulItmNm;

    @ApiModelProperty(value = "자재검토요청 구성성분/취급자재규제정보")
    private MatCheckRequestCompoRegul matCheckRequestCompoRegul;

    @ApiModelProperty(value = "취급자재 자재검토 불러오기시 이미 있는 취급자재번호")
    private Integer existChemProdNo;

    @ApiModelProperty(value = "(SAP)자재검토요청시작일")
    private String from;

    @ApiModelProperty(value = "(SAP)자재검토요청종료일")
    private String to;

    @ApiModelProperty(value = "등록구분")
    private String matRqAttCd;

    @ApiModelProperty(value = "SAP 저장위치")
    private String bdsCode;

    @ApiModelProperty(value = "MSDS 파일명")
    private String msdsFileNm;

    @ApiModelProperty(value = "안전/보건 검토")
    private String chkResultLink;

    @ApiModelProperty(value = "환경 검토")
    private String chkResultEnvLink;

    @ApiModelProperty(value = "MSDS 번호")
    private String msdsNum;

    @ApiModelProperty(value = "비공개 승인 번호")
    private String secretNum;

    @ApiModelProperty(value = "비공개 승인일")
    private String secretApproval;

    @ApiModelProperty(value = "비공개 만료일")
    private String secretExpiration;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
