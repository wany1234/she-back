package com.she.safety.model;

import java.util.List;

import lombok.Data;

@Data
public class SpeGive {
    /**
     * 보호구출고번호
     */
    private int safSpeGiveNo;

    /**
     * 출고일
     */
    private String giveYmd;

    /**
     * 지급구분
     */
    private String giveKindCd;

    /**
     * 지급구분 명
     */
    private String giveKindNm;

    /**
     * 수령부서코드
     */
    private String receiptDeptCd;

    /**
     * 수령부서 명
     */
    private String receiptDeptNm;

    /**
     * 수령자 ID
     */
    private String receiptUserId;

    /**
     * 수령자 명
     */
    private String receiptUserNm;

    /**
     * 비고
     */
    private String remark;

    /**
     * 진행단계코드
     */
    private String processStepCd;

    /**
     * 진행단계 명
     */
    private String processStepNm;

    /**
     * 사업장 코드
     */
    private String plantCd;

    /**
     * 사업장
     */
    private String plantNm;

    /**
     * 등록자 ID
     */
    private String createUserId;

    /**
     * 등록일
     */
    private String createDt;

    /**
     * 수정자ID
     */
    private String updateUserId;

    /**
     * 수정일
     */
    private String updateDt;

    /**
     * 보호구명
     */
    private String speNm;

    /**
     * 보호구종류
     */
    private String speKindCd;

    /**
     * 보호구종류 명
     */
    private String speKindNm;

    /**
     * 지급단위코드
     */
    private String giveUnitCd;

    /**
     * 지급단위 명
     */
    private String giveUnitNm;

    /**
     * 출고갯수
     */
    private int giveNum;

    private List<SpeGiveDetail> speGiveDetails;
}
