package com.she.baseInfo.model;

import java.util.List;

import com.she.chm.model.Chemprod;
import com.she.psm.model.PowerMachine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "설비마스터")
@Getter
@Setter
public class FacilityMst {
    @ApiModelProperty(value = "설비코드")
    private String safFacilityCd;
    @ApiModelProperty(value = "사업장코드")
    private String plantCd;
    @ApiModelProperty(value = "사업장명")
    private String plantNm;
    @ApiModelProperty(value = "설비명")
    private String facilityNm;
    @ApiModelProperty(value = "설치위치")
    private String installLocate;
    @ApiModelProperty(value = "공정코드")
    private String processCd;
    @ApiModelProperty(value = "공정명")
    private String processNm;
    @ApiModelProperty(value = "설치일자")
    private String installYmd;
    @ApiModelProperty(value = "설치업체")
    private String installConn;
    @ApiModelProperty(value = "용량")
    private String vol;
    @ApiModelProperty(value = "용량단위")
    private String volUnit;
    @ApiModelProperty(value = "관리부서")
    private String deptCd;
    @ApiModelProperty(value = "관리부서명")
    private String deptNm;
    @ApiModelProperty(value = "안전관리자")
    private String safMgrPsn;
    @ApiModelProperty(value = "법정점검대상여부")
    private String lawChkYn;
    @ApiModelProperty(value = "법정점검대상여부명")
    private String lawChkYnNm;
    @ApiModelProperty(value = "자체점검대상여부")
    private String selfChkYn;
    @ApiModelProperty(value = "자체점검대상여부명")
    private String selfChkYnNm;
    @ApiModelProperty(value = "최근법정점검일")
    private String lawChkYmd;
    @ApiModelProperty(value = "최근자체점검일")
    private String selfChkYmd;
    @ApiModelProperty(value = "최근가동전점검일")
    private String inspChkYmd;
    @ApiModelProperty(value = "법정점검주기코드")
    private String lawChkCyeCd;
    @ApiModelProperty(value = "법정점검주기코드명")
    private String lawChkCyeNm;
    @ApiModelProperty(value = "자체점검주기코드")
    private String selfChkCyeCd;
    @ApiModelProperty(value = "자체점검주기명")
    private String selfChkCyeNm;
    @ApiModelProperty(value = "폐기여부")
    private String disuseYn;
    @ApiModelProperty(value = "폐기여부명")
    private String disuseYnNm;
    @ApiModelProperty(value = "폐기년도")
    private String disuseYy;
    @ApiModelProperty(value = "비고")
    private String remark;
    @ApiModelProperty(value = "안전인증대상여부")
    private String safLsnYn;
    @ApiModelProperty(value = "안전인증대상여부명")
    private String safLsnYnNm;
    @ApiModelProperty(value = "안전인증주기코드")
    private String safLsnCyeCd;
    @ApiModelProperty(value = "안전인증주기명")
    private String safLsnCyeNm;
    @ApiModelProperty(value = "안전검사대상여부")
    private String safChkYn;
    @ApiModelProperty(value = "안전검사대상여부명")
    private String safChkYnNm;
    @ApiModelProperty(value = "안전검사주기코드")
    private String safChkCyeCd;
    @ApiModelProperty(value = "안전검사주기명")
    private String safChkCyeNm;
    @ApiModelProperty(value = "설비유형코드")
    private String safFacilityTypeCd;
    @ApiModelProperty(value = "설비유형명")
    private String safFacilityTypeNm;
    @ApiModelProperty(value = "설비관리항목값 리스트")
    private List<FacilityTypeItemVal> facilityTypeItemVals;
    @ApiModelProperty(value = "동력기계")
    private PowerMachine powerMachine;
    @ApiModelProperty(value = "취급자재 리스트")
    private List<Chemprod> chemprods;
    @ApiModelProperty(value = "튜브체크값")
    private boolean tubeCheck;
    @ApiModelProperty(value = "장치번호")
    private int equipmentNo;
    @ApiModelProperty(value = "PSM코드")
    private String facilityPsmCd;
    @ApiModelProperty(value = "PSM명")
    private String facilityPsmNm;
    @ApiModelProperty(value = "등록자ID")
    private String createUserId;
    @ApiModelProperty(value = "등록자명")
    private String createUserNm;
    @ApiModelProperty(value = "등록일")
    private String createDt;
    @ApiModelProperty(value = "수정자ID")
    private String updateUserId;
    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;
    @ApiModelProperty(value = "수정일")
    private String updateDt;
    @ApiModelProperty(value = "상위부서")
    private String pdeptCd;
    @ApiModelProperty(value = "설비갯수")
    private String facilityCount;
    @ApiModelProperty(value = "설비관리번호")
    private String facilityMgrNum;
    @ApiModelProperty(value = "검사합격번호")
    private String chkPassNum;
    @ApiModelProperty(value = "합격일자")
    private String chkPassDt;
    @ApiModelProperty(value = "변경된 취급자재")
    private List<String> changeChemProds;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

}
