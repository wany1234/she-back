package com.she.env.water.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OpLogResult {

    private String measureYmd;

    private String ewtrPreventFacNo;

    private String day;

    private String weather;

    private String weatherNm;

    private Float temp;

    private String plantCd;

    private String plantNm;

    private Float amtSludgeGen;

    private String amtSludgeGent;

    private Float amtSludgeTx;

    private String amtSludgeTxt;

    private Float amtSludgeSto;

    private String amtSludgeStot;

    private Float sludgeMc;

    private String sludgeMct;

    private String sludgeStoPo;

    private String sludgeTxPo;

    private String regulator;

    private String regulatorWorker;

    private String violation;

    private String action;

    private Float pwrcPerDay;

    private String deptNm;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String writerUserNm;

    private String writerDt;

    private String envOpLogStCd;

    private String envOpLogStNm;

    private String ymd;

    private String ewtrCleanFacNm;

    private int ewtrCleanFacNo;

    private String shiftWorker1;

    private String shiftWorker2;

    private String shiftWorker3;

    private String shiftWorker4;

    private String shiftWorker5;

    private String shiftWorker6;

    private String shiftWktm1;

    private String shiftWktm2;

    private String shiftWktm3;

    private String shiftWktm4;

    private String shiftWktm5;

    private String shiftWktm6;

    private String shiftConcat1;

    private String shiftConcat2;

    private String shiftConcat3;

    private String shiftConcat4;

    private String shiftConcat5;

    private String shiftConcat6;

    private int apprRqstNo;

    private String bizApprStepCd;

    private String bizApprStepNm;

    private boolean measureEditable;

    private int ewtrGuidedHistNo;

    private int preventRunFacNo;

    private boolean updateFlag;

    @ApiModelProperty(value = "작성부서")
    private String deptCd;

    @ApiModelProperty(value = "관리부서")
    private String mgDeptCd;

}
