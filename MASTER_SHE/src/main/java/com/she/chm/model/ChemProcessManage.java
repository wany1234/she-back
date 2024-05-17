package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "공정관리요령")
@Data
public class ChemProcessManage {

    @ApiModelProperty(value = "공정관리요령번호")
    private int chmProcessManageNo;

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

    @ApiModelProperty(value = "유해성/위험성")
    private String hazDangerDesc;

    @ApiModelProperty(value = "취급상의 주의사항")
    private String handleCareDesc;

    @ApiModelProperty(value = "적절한 보호구")
    private String safetyEquipDesc;

    @ApiModelProperty(value = "응급조치요령")
    private String fstHandleDesc;

    @ApiModelProperty(value = "사고시 대처방법")
    private String accHandleDesc;

    @ApiModelProperty(value = "기타")
    private String etc;

    @ApiModelProperty(value = "공정관리요령별취급자재목록")
    private List<ChemProcessManageChemprodVal> chemProcessManageChemprodVals;

    @ApiModelProperty(value = "사용 여부")
    private String useYn;

    @ApiModelProperty(value = "사용 여부명")
    private String useYnNm;

    @ApiModelProperty(value = "납품업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "납품업체명")
    private String vendorNm;

    @ApiModelProperty(value = "제조업체코드")
    private String makerCd;

    @ApiModelProperty(value = "제조업체명")
    private String makerNm;

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

    @ApiModelProperty(value = "그림문자목록")
    private List<String> processManagePicGraphs;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}
