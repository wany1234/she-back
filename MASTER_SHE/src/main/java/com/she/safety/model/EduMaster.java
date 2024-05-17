package com.she.safety.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EduMaster {
    private int safEduMstNo;

    private int safEduCourseNo;

    private int eduYearPlanNo;

    private String eduCourseNm;

    private String year;

    private String eduTypeCd;

    private String eduTypeNm;

    private String eduAttCd;

    private String eduAttNm;

    // 교육과정(saf_edu_course)의 edu_type_cd 이름
    private String eduCourseTypeNm;

    private String eduClassCd;

    private String eduClassNm;

    private String userId;

    private String userNm;

    private String lawPerdNm;

    private String lawEduTm;

    private String dftEduTm;

    private String completYn;

    private String deptCd;

    private String deptNm;

    private String pbizApprStepCd;

    private String pbizApprStepNm;

    private String rbizApprStepCd;

    private String rbizApprStepNm;

    private String plantCd;

    private String plantNm;

    private String eduNm;

    private String eduSYmd;

    private int eduCost;

    private String eduEYmd;

    private String eduYmd;

    private String qrCode;

    private Float eduTime;

    private Float eduTimePlan;

    private String eduSHour;

    private String questionUseYn;

    private String eduEHour;

    private String eduPlace;

    private String eduTeacher;

    private String eduContent;

    private String eduResultSummary;

    private String pApprRqstNo;

    private String pApprRqstNm;

    private String rApprRqstNo;

    private String eduEvalOpin;

    // 단계 (결과/계획) (지워야함)
    private String processStepCd;

    private String processStepNm;

    private String pprocStepCd; // (대체)

    private String pprocStepNm;

    private String rprocStepCd;

    private String rprocStepNm;

    // 상태 (미작성/작성중/결재중/결재완료)
    private String pStateCd;

    private String pStateNm;

    private String rStateCd;

    private String rStateNm;

    //
    private String pStepCd;
    private String pStepNm;

    private String rStepCd;
    private String rStepNm;

    // 주관
    private String mainDeptCd;

    private String mainDeptNm;

    // 대상
    private String targetDeptCd;

    private String targetDeptNm;

    // 수행
    private String execDeptCd;

    private String execDeptNm;

    // 관련법규

    private String isTypeCd;

    private String isTypeNm;

    private String disYn;

    private String createUserId;

    private String createUserNm;

    private String createDt;

    private String updateUserId;

    private String updateUserNm;

    private String updateDt;

    private String[] eduUserId;

    private String[] eduDeptCd;

    private ArrayList<EduDetailPerson> personList;

    private ArrayList<EduOutsideUser> eduOutSideUsers;

    private int[] dataLsts; // 자료실

    private int[] questionLsts; // 문제

    private String tgtDeptNm;

    private String totalUserNum;

    private String passUserNum;

    private String passOutUserNum;

    private String totPassUserNum;

    private String failUserNum;

    private String exceptUserNum;

    private String reEduPerson;

    private String reEduAbsentPerson;

    private ArrayList period;

    private String month1Num;

    private String month2Num;

    private String month3Num;

    private String month4Num;

    private String month5Num;

    private String month6Num;

    private String month7Num;

    private String month8Num;

    private String month9Num;

    private String month10Num;

    private String month11Num;

    private String month12Num;

    private String reEduYn;

    private String reEduSYmd;

    private String reEduEYmd;

    private String reEduTime;

    private String reEduPlace;

    private String reEduTeacher;

    private String reEduEndYn;

    private String eduYm;

    List<EduDetailPerson> eduDetailPersons;

    private int safChngNo;

    private String chngNum;

    private String writerUserNm;

    private String writerDt;

    private String eduEvalPnt;

    private String eduEvalPntSec;

    private int processStepCnt;

    private String processStepYn;

    private String subJectId;

    private String subJectNm;

    private String subConnYn;

    private String eduEvalEnd;

    private String completYmd;

    private String reCompletYn;

    private String vendorNm;

    private String workerNm;

    private String reEduNotYn;

    private String eduCompletYn;
}
