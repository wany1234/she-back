/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */

package com.she.safety.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "작업허가서")
@Getter
@Setter
public class WkodMaster extends WkodMasterTabData {
    @ApiModelProperty(value = "작업허가서번호")
    private int wkodNo;

    @ApiModelProperty(value = "작업허가NO")
    private String wkodNum;

    @ApiModelProperty(value = "공사번호")
    private String constNo;

    @ApiModelProperty(value = "공사명")
    private String constTitle;

    @ApiModelProperty(value = "공사내용")
    private String constContent;

    @ApiModelProperty(value = "PO번호")
    private String constNum;

    @ApiModelProperty(value = "공사시작일")
    private String constStartYmd;

    @ApiModelProperty(value = "공사종료일")
    private String constEndYmd;

    @ApiModelProperty(value = "결재진행단계코드")
    private String bizApprStepCd;

    @ApiModelProperty(value = "결재진행단계명")
    private String bizApprStepNm;

    @ApiModelProperty(value = "결재진행단계명")
    private String apprStepNm;

    @ApiModelProperty(value = "작업허가구분코드")
    private String wkodClsCd;

    @ApiModelProperty(value = "작업허가구분명")
    private String wkodClsNm;

    @ApiModelProperty(value = "법인코드")
    private String wkodSpeKindNm;

    @ApiModelProperty(value = "작업구분명")
    private String wkodKindNm;

    @ApiModelProperty(value = "작업일자")
    private String workYmd;

    @ApiModelProperty(value = "작업시작시간")
    private String workStartTime;

    @ApiModelProperty(value = "작업종료시간")
    private String workEndTime;

    @ApiModelProperty(value = "작업일시작시간")
    private String workYmdStartTime;

    @ApiModelProperty(value = "작업일종료시간")
    private String workYmdEndTime;

    @ApiModelProperty(value = "작업연장시간")
    private String workOverTime;

    @ApiModelProperty(value = "작업완료일시")
    private String workCompleteTime;

    @ApiModelProperty(value = "작업명")
    private String workTitle;

    @ApiModelProperty(value = "작업내용")
    private String workContent;

    @ApiModelProperty(value = "진행단계코드")
    private String wkodStepCd;

    @ApiModelProperty(value = "진행단계명")
    private String wkodStepNm;

    @ApiModelProperty(value = "신청단계확인여부")
    private String reqStepConfirmYn;

    @ApiModelProperty(value = "허가단계확인여부")
    private String pubStepConfirmYn;

    @ApiModelProperty(value = "승인단계확인여부")
    private String appStepConfirmYn;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "공정코드")
    private String processCd;

    @ApiModelProperty(value = "공정명")
    private String processNm;

    @ApiModelProperty(value = "작업지역_상세장소")
    private String workArea;

    @ApiModelProperty(value = "신청부서담당자ID")
    private String reqUserId;

    @ApiModelProperty(value = "신청부서담당자성명")
    private String reqUserNm;

    @ApiModelProperty(value = "요청자사인")
    private String reqUserSign;

    @ApiModelProperty(value = "신청부서코드")
    private String reqDeptCd;

    @ApiModelProperty(value = "신청부서명")
    private String reqDeptNm;

    @ApiModelProperty(value = "신청일")
    private String reqYmd;

    @ApiModelProperty(value = "주관부서코드")
    private String pubDeptCd;

    @ApiModelProperty(value = "주관부서명")
    private String pubDeptNm;

    @ApiModelProperty(value = "주관부서감독자")
    private String pubMgrNm;

    @ApiModelProperty(value = "주관부서감독자ID")
    private String pubMgrId;

    @ApiModelProperty(value = "협조부서코드")
    private String coopDeptCd;

    @ApiModelProperty(value = "협조부서명")
    private String coopDeptNm;

    @ApiModelProperty(value = "업체코드")
    private String vendorCd;

    @ApiModelProperty(value = "주관부서감독자연락처")
    private String pubMgrTel;

    @ApiModelProperty(value = "작업업체명")
    private String subconnNm;

    @ApiModelProperty(value = "작업업체담당자명")
    private String subconnUserNm;

    @ApiModelProperty(value = "작업업체담당자연락처")
    private String subconnUserTel;

    @ApiModelProperty(value = "작업인원수")
    private int workerNumb;

    @ApiModelProperty(value = "특별지시사항")
    private String specialInstructions;

    @ApiModelProperty(value = "취급유해인자(내용)")
    private String handleChemContent;

    @ApiModelProperty(value = "주관부서확인자ID")
    private String pubUserId;

    @ApiModelProperty(value = "주관부서확인자성명")
    private String pubUserNm;

    @ApiModelProperty(value = "주관부서확인일시")
    private String pubConfirmDt;

    @ApiModelProperty(value = "안환팀확인자ID")
    private String appUserId;

    @ApiModelProperty(value = "안환팀확인자성명")
    private String appUserNm;

    @ApiModelProperty(value = "안환팀코드")
    private String appDeptCd;

    @ApiModelProperty(value = "안환팀명")
    private String appDeptNm;

    @ApiModelProperty(value = "안환팀확인일시")
    private String appConfirmDt;

    @ApiModelProperty(value = "연장확인자ID")
    private String overUserId;

    @ApiModelProperty(value = "연장확인자명")
    private String overUserNm;

    @ApiModelProperty(value = "연장확인자부서명")
    private String overDeptCd;

    @ApiModelProperty(value = "연장확인자부서코드")
    private String overDeptNm;

    @ApiModelProperty(value = "연장확인일시")
    private String overConfirmDt;

    @ApiModelProperty(value = "X축_좌표위치")
    private int locatePntX;

    @ApiModelProperty(value = "Y축_좌표위치")
    private int locatePntY;

    @ApiModelProperty(value = "공장전도번호")
    private String mapNo;

    @ApiModelProperty(value = "삭제여부")
    private String delYn;

    @ApiModelProperty(value = "보호구기타")
    private String spmEtc;

    @ApiModelProperty(value = "연장여부")
    private String overYn;

    @ApiModelProperty(value = "연장완료취소관련비고")
    private String refRemark;

    @ApiModelProperty(value = "승인플래그")
    private String confirmFlag;

    @ApiModelProperty(value = "평가계획no")
    private int assessPlanNo;

    @ApiModelProperty(value = "평가명")
    private String assessNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록자명")
    private String createUserNm;

    @ApiModelProperty(value = "공사업체작성여부")
    private String subconnRegYn;

    @ApiModelProperty(value = "신청_결재단계")
    private String reqApprStepCd;

    @ApiModelProperty(value = "신청_결재단계명")
    private String reqApprStepNm;

    @ApiModelProperty(value = "주관_결재단계")
    private String pubApprStepCd;

    @ApiModelProperty(value = "주관_결재단계명")
    private String pubApprStepNm;

    @ApiModelProperty(value = "안환_결재단계")
    private String appApprStepCd;

    @ApiModelProperty(value = "안환_결재단계명")
    private String appApprStepNm;

    @ApiModelProperty(value = "결재여부")
    private String isApprRequest;

    @ApiModelProperty(value = "작업자수")
    private int workerCount;

    @ApiModelProperty(value = "도급여부")
    private String subconn2ndYn;

    @ApiModelProperty(value = "교대시인수자")
    private String chngAccPsn;

    @ApiModelProperty(value = "교대시인계자")
    private String chngTakePsn;

    @ApiModelProperty(value = "안전대책")
    private String safMeasure;

    @ApiModelProperty(value = "작업자준수사항")
    private String wokerTerms;

    @ApiModelProperty(value = "안전조치사항")
    private String chkStepCd;

    @ApiModelProperty(value = "psm여부")
    private String psmYn;

    @ApiModelProperty(value = "psm여부명")
    private String psmYnNm;

    @ApiModelProperty(value = "결재번호")
    private int apprRqstNo;

    @ApiModelProperty(value = "1회출력여부")
    private String prtYn;

    @ApiModelProperty(value = "업체작성완료여부")
    private String vendorWriteYn;

    @ApiModelProperty(value = "등록일시")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정자명")
    private String updateUserNm;

    @ApiModelProperty(value = "수정일시")
    private String updateDt;

    @ApiModelProperty(value = "공사현황의 작업자정보를 가져오기 위한 공자작업구분별 번호")
    private int[] constKindSubconnNos;

    @ApiModelProperty(value = "결재승인일시")
    private String apprEndDt;

    @ApiModelProperty(value = "작성자")
    private String writerUserNm;

    @ApiModelProperty(value = "작성일")
    private String writerDt;
}