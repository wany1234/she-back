package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "MSDS")
@Data
public class MSDS {
    @ApiModelProperty(value = "MSDS정보번호")
    private int msdsRqstNo;

    @ApiModelProperty(value = "MSDS그룹번호")
    private int msdsGrpNo;

    @ApiModelProperty(value = "취급자재번호")
    private int chemProdNo;

    @ApiModelProperty(value = "취급자재명(국문)")
    private String chemProdNmKr;

    @ApiModelProperty(value = "취급자재명(영문)")
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

    @ApiModelProperty(value = "비고")
    private String remarks;

    @ApiModelProperty(value = "개정구분코드")
    private String revType;

    @ApiModelProperty(value = "개정구분명")
    private String revTypeNm;

    @ApiModelProperty(value = "개정번호")
    private double revNum;

    @ApiModelProperty(value = "최신본여부")
    private String newYn;

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

    @ApiModelProperty(value = "삭제여부")
    private String delYn;

    @ApiModelProperty(value = "삭제명")
    private String delYnNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "자동등록여부")
    private String autoInsYn;

    @ApiModelProperty(value = "자동등록여부명")
    private String autoInsYnNm;

    @ApiModelProperty(value = "4유해위험문구_신호어")
    private String signalDesc;

    @ApiModelProperty(value = "4유해위험문구_유해위험문구")
    private String hazDangerDesc;

    @ApiModelProperty(value = "4유해위험문구_예방조치문구")
    private String preventActDesc;

    @ApiModelProperty(value = "5응급처치_눈")
    private String fstAidEyeDesc;

    @ApiModelProperty(value = "5응급처치_피부")
    private String fstAidSkinDesc;

    @ApiModelProperty(value = "5응급처치_흡입")
    private String fstAidBreathDesc;

    @ApiModelProperty(value = "5응급처치_먹음")
    private String fstAidEatDesc;

    @ApiModelProperty(value = "6저장방법")
    private String strgMthdDesc;

    @ApiModelProperty(value = "7회피조건")
    private String evasCondDesc;

    @ApiModelProperty(value = "7회피물질")
    private String evasMatDesc;

    @ApiModelProperty(value = "8누출대처방법")
    private String leakHandleDesc;

    @ApiModelProperty(value = "8폭발대처방법")
    private String explHandleDesc;

    @ApiModelProperty(value = "8화재대처방법")
    private String fireHandleDesc;

    @ApiModelProperty(value = "9법적규제_노출기준")
    private String lawExpoStndDesc;

    @ApiModelProperty(value = "9법적규제_특수검진주기")
    private String lawSphkPerDesc;

    @ApiModelProperty(value = "9법적규제_작업환경측정주기")
    private String lawWkenMeasDesc;

    @ApiModelProperty(value = "9법적규제_산안법")
    private String lawIndSafDesc;

    @ApiModelProperty(value = "9법적규제_화관법")
    private String lawChemMgrDesc;

    @ApiModelProperty(value = "9법적규제_위험물관리법")
    private String lawDngrMgrDesc;

    @ApiModelProperty(value = "10취급시주의사항")
    private String handleCareDesc;

    @ApiModelProperty(value = "10.기타중독사례")
    private String etcPoisonCase;

    @ApiModelProperty(value = "11공정관리요령-적절한보호구")
    private String safetyEquipDesc;

    @ApiModelProperty(value = "11공정관리요령-기타")
    private String etc;

    @ApiModelProperty(value = "MSDS파일명")
    private String fileOrgNm;

    @ApiModelProperty(value = "MSDS파일저장경로")
    private String fileDownPath;

    @ApiModelProperty(value = "MSDS파일번호")
    private String fileNo;

    @ApiModelProperty(value = "MSDS파일확장자")
    private String fileExt;

    @ApiModelProperty(value = "MSDS요약파일명")
    private String summaryFileOrgNm;

    @ApiModelProperty(value = "MSDS요약파일저장경로")
    private String summaryFileDownPath;

    @ApiModelProperty(value = "MSDS요약파일번호")
    private String summaryFileNo;

    @ApiModelProperty(value = "MSDS요약파일확장자")
    private String summaryFileExt;

    @ApiModelProperty(value = "그림문자목록")
    private List<String> msdsPicGraphs;

    @ApiModelProperty(value = "취급자재-성상코드")
    private String property;

    @ApiModelProperty(value = "취급자재-성상명")
    private String propertyNm;

    @ApiModelProperty(value = "취급자재-분자량")
    private String moleWeight;

    @ApiModelProperty(value = "취급자재-끓는점")
    private String boilpoint;

    @ApiModelProperty(value = "취급자재-녹는점")
    private String meltingPnt;

    @ApiModelProperty(value = "취급자재-인화점")
    private String flashingPnt;

    @ApiModelProperty(value = "취급자재-용도코드")
    private String usageCd;

    @ApiModelProperty(value = "취급자재-용도명")
    private String usageNm;

    @ApiModelProperty(value = "취급자재-용도기타")
    private String usageEtc;

    @ApiModelProperty(value = "MSDS작성일")
    private String writeDt;

    @ApiModelProperty(value = "취급자재-비중")
    private String specificGravity;

    @ApiModelProperty(value = "취급자재-보관장소")
    private String stckArea;

    @ApiModelProperty(value = "저장시 취급자재가 변경되어, 취급자재의 MSDS파일이 자동 첨부하는 경우")
    private String chemProdMsdsYn;

    @ApiModelProperty(value = "저장시 취급자재가 변경되어, 취급자재의 MSDS파일이 자동 첨부하는 경우, 첨부할 파일번호")
    private int[] chemProdMsdsFiles;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "MSDS 번호")
    private String msdsNum;

    @ApiModelProperty(value = "비공개 승인 번호")
    private String secretNum;

    @ApiModelProperty(value = "비공개 승인일")
    private String secretApproval;

    @ApiModelProperty(value = "비공개 만료일")
    private String secretExpiration;
}
