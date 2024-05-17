package com.she.safety.model;

import java.util.List;

import com.she.manage.model.AlarmCycle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "유해위험기계기구")
@Getter
@Setter
public class SafCheckMachine {

    @ApiModelProperty(value = "기계기구번호")
    private int checkMachineNo;

    @ApiModelProperty(value = "사업장코드")
    private String plantCd;

    @ApiModelProperty(value = "사업장명")
    private String plantNm;

    @ApiModelProperty(value = "(C)기계분류")
    private String machineTypeCd;

    @ApiModelProperty(value = "(C)기계분류")
    private String machineTypeNm;

    @ApiModelProperty(value = "(C)압력용기유형")
    private String pressureClsCd;

    @ApiModelProperty(value = "ITEM NO")
    private String itemNo;

    @ApiModelProperty(value = "용량")
    private String volume;

    @ApiModelProperty(value = "허가(자격)번호")
    private String licenseNumber;

    @ApiModelProperty(value = "검사증교부일자")
    private String inspIssueDt;

    @ApiModelProperty(value = "최근검사일자")
    private String recentInspDt;

    @ApiModelProperty(value = "차기검사일자")
    private String nextIsnpDt;

    @ApiModelProperty(value = "(C)검사주기")
    private String inspCycleCd;

    @ApiModelProperty(value = "(C)검사주기명")
    private String inspCycleNm;

    @ApiModelProperty(value = "검사대상")
    private String inspYn;

    @ApiModelProperty(value = "검사대상")
    private String inspTxt;

    @ApiModelProperty(value = "검사비용(수수료)")
    private String inspFee;

    @ApiModelProperty(value = "담당자명(알람)")
    private String alarmUserNm;

    @ApiModelProperty(value = "담당부서")
    private String deptCd;

    @ApiModelProperty(value = "담당부서명")
    private String deptNm;

    @ApiModelProperty(value = "제외사유")
    private String excludeDesc;

    @ApiModelProperty(value = "비고")
    private String remark;

    @ApiModelProperty(value = "제조사(제작업체)")
    private String makerNm;

    @ApiModelProperty(value = "내용적(L)")
    private String contents;

    @ApiModelProperty(value = "설계압력(kg/cm2)")
    private String designPressKg;

    @ApiModelProperty(value = "설계압력(Mpa)")
    private String designPressMpa;

    @ApiModelProperty(value = "관리번호")
    private String manageNo;

    @ApiModelProperty(value = "검사합격일자")
    private String inspPassDt;

    @ApiModelProperty(value = "P&ID")
    private String pnid;

    @ApiModelProperty(value = "검사증번호")
    private String inspCertiNo;

    @ApiModelProperty(value = "psv")
    private String psv;

    @ApiModelProperty(value = "운전(압력)")
    private String operPresssure;

    @ApiModelProperty(value = "운전(온도)")
    private String operTemp;

    @ApiModelProperty(value = "설계(압력)")
    private String designPressure;

    @ApiModelProperty(value = "설계(온도)")
    private String designTemp;

    @ApiModelProperty(value = "service")
    private String service;

    @ApiModelProperty(value = "part")
    private String part;

    @ApiModelProperty(value = "최고사용압력")
    private String maxPressure;

    @ApiModelProperty(value = "조정자")
    private String coordiUserId;

    @ApiModelProperty(value = "설치장소")
    private String installPlace;

    @ApiModelProperty(value = "설치년도")
    private String installDt;

    @ApiModelProperty(value = "철거년도")
    private String withdrawDt;

    @ApiModelProperty(value = "합격번호")
    private String passNumber;

    @ApiModelProperty(value = "EWR NO.")
    private String ewrNo;

    @ApiModelProperty(value = "설계근거(정격하중)")
    private String designBasisWeight;

    @ApiModelProperty(value = "설계근거(종류)")
    private String designBasisClass;

    @ApiModelProperty(value = "알람주기")
    private String alarmNm;

    @ApiModelProperty(value = "등록자")
    private String createUserId;

    @ApiModelProperty(value = "등록일")
    private String createDt;

    @ApiModelProperty(value = "수정자")
    private String updateUserId;

    @ApiModelProperty(value = "수정일")
    private String updateDt;

    @ApiModelProperty(value = "점검이력 리스트")
    private List<SafCheckLog> safCheckLogs;

    @ApiModelProperty(value = "삭제할 점검이력 리스트")
    private List<SafCheckLog> deleteSafCheckLogs;

    @ApiModelProperty(value = "알람담당자 리스트")
    private List<SafCheckUser> safCheckUsers;

    @ApiModelProperty(value = "삭제할 알람담당자 리스트")
    private List<SafCheckUser> deleteSafCheckUsers;

    @ApiModelProperty(value = "알람주기")
    private List<AlarmCycle> alarmCycs;

    @ApiModelProperty(value = "d-day")
    private int dday;

    @ApiModelProperty(value = "점검자 id")
    private String alarmUserId;

    @ApiModelProperty(value = "점검자 email")
    private String email;
}
