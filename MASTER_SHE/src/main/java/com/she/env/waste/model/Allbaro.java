package com.she.env.waste.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Allbaro {

    @ApiModelProperty("인계일자")
    String transDt; // 인계일자
    @ApiModelProperty("인계서번호")
    int ewstAllbaroTransNo; // 인계서번호
    @ApiModelProperty("사업장코드")
    String plantCd; // 사업장코드
    @ApiModelProperty("인계서파일구분번호")
    String yyyymmddhhmiss; // 인계서파일구분번호
    @ApiModelProperty("인계번호")
    String allbaroTransNum; // 인계번호
    @ApiModelProperty("확정여부")
    String endStepNm; // 확정여부
    @ApiModelProperty("폐기물종류")
    String ewstClassNm; // 폐기물종류(성상)
    @ApiModelProperty("성상")
    String ewstClassItem;// 성상
    @ApiModelProperty("구분")
    String typeNm; // 구분
    @ApiModelProperty("위탁량")
    Float amtGen; // 위탁량
    @ApiModelProperty("단위")
    String unitNm; // 단위
    @ApiModelProperty("처리자인수량")
    int dispoAmtGen; // 처리자인수량
    @ApiModelProperty("운반자명(업체)")
    String freightVendorNm; // 운반자명(업체)
    @ApiModelProperty("차량번호")
    String carrierNum; // 차량번호
    @ApiModelProperty("처리자명(업체)")
    String dispoVendorNm; // 처리자명(업체)
    @ApiModelProperty("처리방법")
    String ewstDispoMtdNm; // 처리방법
    @ApiModelProperty("처리장소")
    String ewstDispoPlce; // 처리장소
    @ApiModelProperty("인계자명")
    String transPsnNm; // 인계자명
    @ApiModelProperty("공동배출자여부")
    String assGenYnNm; // 공동배출자여부
    @ApiModelProperty("입력구분")
    String inputYnNm; // 입력구분
    @ApiModelProperty("관할구청")
    String responOffice; // 관할구청
    @ApiModelProperty("진행상태")
    String processStepNm; // 진행상태
    @ApiModelProperty("처리결과")
    String processRsltNm; // 처리결과
    @ApiModelProperty("예외사유")
    String excepReason; // 예외사유
    @ApiModelProperty("폐기물 번호")
    int ewstWasteNo; // 폐기물 번호
    @ApiModelProperty("운반업체번호")
    String freightVendorCd; // 운반업체번호
    @ApiModelProperty("처리업체번호")
    String dispoVendorCd; // 처리업체번호
    @ApiModelProperty("처리방법코드")
    String ewstDispoMtdCd; // 처리방법코드
    @ApiModelProperty("운반단가")
    Float freightCost; // 운반단가
    @ApiModelProperty("처리단가")
    Float dispoCost; // 처리단가
    @ApiModelProperty("운반비용")
    Float freightCostSum; // 운반비용
    @ApiModelProperty("처리비용")
    Float dispoCostSum; // 처리비용
    @ApiModelProperty("비용총합계")
    Float totalCost; // 비용총합계
    @ApiModelProperty("매각해당여부")
    String sellYn; // 매각해당여부
    @ApiModelProperty("처리내역반영여부")
    String dispoInputYnDesc; // 처리내역반영여부
    @ApiModelProperty("등록일")
    String createDt; // 등록일
    @ApiModelProperty("등록자ID")
    String createUserId; // 등록자ID
    @ApiModelProperty("수정일")
    String updateDt; // 수정일
    @ApiModelProperty("수정자ID")
    String updateUserId; // 수정자ID

    @ApiModelProperty("dispoYmd")
    String dispoYmd;

    @ApiModelProperty("ewstWasteCd")
    String ewstWasteCd;

    @ApiModelProperty("status")
    String status; // 중복 데이터 체크

    @ApiModelProperty("errorMessage")
    String errorMessage;

}
