package com.she.chm.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "인허가신고")
@Getter
@Setter
public class ChemPermitProdHis {

    @ApiModelProperty(value = "인허가신고번호")
    private int permitProdHisNo;

    @ApiModelProperty(value = "인허가신고번호개정그룹")
    private int permitProdHisGrpNo;

    @ApiModelProperty(value = "인허가신고종류(인허가신고서)")
    private int permitClsNo;

    @ApiModelProperty(value = "인허가신고서명")
    private String permitClsNm;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장코드")
    private String plantNm;

    @ApiModelProperty(value = "신고일")
    private String permitDt;

    @ApiModelProperty(value = "신고기관")
    private String permitOrg;

    @ApiModelProperty(value = "신고번호")
    private String permitNum;

    @ApiModelProperty(value = "사용부서코드")
    private String useDeptCd;

    @ApiModelProperty(value = "사용부서명")
    private String useDeptNm;

    @ApiModelProperty(value = "저장시설")
    private String storFac;

    @ApiModelProperty(value = "신고자아이디")
    private String permitEmpId;

    @ApiModelProperty(value = "신고자명")
    private String permitEmpNm;

    @ApiModelProperty(value = "비고")
    private String permitDesc;

    @ApiModelProperty(value = "첨부파일명")
    private String fileNm;

    @ApiModelProperty(value = "첨부파일번호")
    private String fileNo;

    @ApiModelProperty(value = "첨부파일확장자")
    private String fileExt;

    @ApiModelProperty(value = "사용여부")
    private String useYn;

    @ApiModelProperty(value = "사용여부명")
    private String useYnNm;

    @ApiModelProperty(value = "생성자아이디")
    private String createUserId;

    @ApiModelProperty(value = "생성자명")
    private String createUserNm;

    @ApiModelProperty(value = "생성일")
    private String createDt;

    @ApiModelProperty(value = "수정자아이디")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "인허가 취급자재")
    private List<Chemprod> chmPermitRefProd;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;

    @ApiModelProperty(value = "개정번호")
    private String revNum;

    @ApiModelProperty(value = "개정내용")
    private String revContents;

    @ApiModelProperty(value = "개정일자")
    private String revYmd;

    // 안쓰는 컬럼 확인 필요.
    // ,chng_yn
    // ,prod_amt
    // ,hs_no
    // ,cfm_mtd
    // ,usage_cd
    // ,appr_dt
    // ,appr_yn
    // ,chng_desc
    // ,permit_status
    // ,file_url
    // ,branch_cd
    // ,origin_cd

}
