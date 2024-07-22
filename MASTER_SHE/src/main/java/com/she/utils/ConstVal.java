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

package com.she.utils;

/**
 * 공통코드 및 상수
 *
 */
public class ConstVal {

    /**
     * 공통 코드도메인 - 경영
     */
    public static String COM_CODE_DOMAIN_MGT = "MGT";
    /**
     * 공통 코드그룹 - KPI 분야관리
     */
    public static String COM_CODE_GROUP_KPI_BIZFIELD = "MGT_BIZ_FIELD";

    /**
     * 공통 코드도메인 - 안전
     */
    public static String COM_CODE_DOMAIN_SAF = "SAF";

    /**
     * 공통 코드그룹 - 인적피해
     */
    public static String COM_CODE_GROUP_HUMAN_DAMAGE = "SAF_ACCIDENT_HD_CLS";

    /**
     * 공통 코드그룹 - 원인(직접)
     */
    public static String COM_CODE_GROUP_IMM_CAUSE = "SAF_ACCIDENT_IMM_CAUSE";
    /**
     * 공통 코드그룹 - 원인(간접)
     */
    public static String COM_CODE_GROUP_IND_CAUSE = "SAF_ACCIDENT_IND_CAUSE";
    /**
     * 공통 코드그룹 - 발생형태(대분류)
     */
    public static String COM_CODE_GROUP_OCC_PTN = "SAF_ACCIDENT_OCC_PTN_CLS";
    /**
     * 공통 코드그룹 - 안전관찰핵심행동항목
     */
    public static String COM_CODE_GROUP_CTO_ACT = "SAF_CTO_ACT";

    /**
     * 공통 결재자구분코드 - 기안자
     */
    public static String COM_APPR_TYPE_REQUESTER = "APTP0";

    /**
     * 공통 결재 - 작성중
     */
    public static String COM_BIZ_APPR_WRITE = "BAPS0";
    /**
     * 공통 결재 - 결재진행중
     */
    public static String COM_BIZ_APPR_APPR_ING = "BAPS1";
    /**
     * 공통 결재선 범위 구분 - 공통
     */
    public static String COM_PLANT_SAME_COMMON = "CSSE1";
    /**
     * 공통 결재진행단계 - 결재중
     */
    public static String COM_BIZ_APPR_STEP_ING = "BAPSA";
    /**
     * 공통 결재진행단계 - 반려
     */
    public static String COM_BIZ_APPR_STEP_REJECT = "BAPSD";
    /**
     * 공통 결재진행단계 - 결재완료
     */
    public static String COM_BIZ_APPR_STEP_COMPLETE = "BAPSG";
    /**
     * 공통 결재진행단계 - 재기안
     */
    public static String COM_BIZ_APPR_STEP_REAPPROVAL = "BAPST";
    /**
     * 공통 결재자 유형코드 - 기안
     */
    public static String COM_APPR_TYPE_DRAFT = "APTP0";
    /**
     * 공통 결재자 유형코드 - 결재
     */
    public static String COM_APPR_TYPE_APPROVAL = "APTPA";
    /**
     * 공통 결재자 유형코드 - 본사확인
     */
    public static String COM_APPR_TYPE_HQ_CONFIRM = "APTPC";
    /**
     * 공통 결재자 유형코드 - 사업장확인
     */
    public static String COM_APPR_TYPE_PLANT_CONFIRM = "APTPR";

    /**
     * 공통 결재자 처리상태코드 - 승인
     */
    public static String COM_APPR_STEP_APPROVAL = "APSPA";
    /**
     * 파일 경로 코드그룹코드 - FORM
     */
    public static String CODE_GROUP_FILE_PATH = "COM_FILE_PATH";

    /**
     * 파일명 코드그룹코드 - FORM
     */
    public static String CODE_GROUP_FILE_NAME = "COM_TEMPLETE_FILE_NAME";

    /**
     * 파일 경로 코드 - FORM
     */
    public static String CODE_FILE_PATH_FORM = "PFORM";

    /**
     * 파일 경로 코드 - TEMP
     */
    public static String CODE_FILE_PATH_TEMP = "PTEMP";

    /**
     * 파일 명 코드 - 유해위험요인
     */
    public static String FILE_NAME_RISK_HAZARD = "TFN01";

    /**
     * 파일 명 코드 - 작업환경측정결과(물리적)
     */
    public static String FILE_NAME_WORK_MEASURE_RESULT_PHYSICAL = "TFN02";

    /**
     * 파일 명 코드 - 작업환경측정결과(화학적)
     */
    public static String FILE_NAME_WORK_MEASURE_RESULT_CHEMICAL = "TFN03";

    /**
     * 파일 명 코드 - 직무
     */
    public static String FILE_NAME_JOB = "TFN04";

    /**
     * 코드도메인 - 환경
     */
    public static String CODE_DOMAIN_CD_ENV = "ENV";

    /**
     * 코드도메인 - 환경>폐기물
     */
    public static String CODE_DOMAIN_CD_ENV_WASTE = "EWST";

    /**
     * 코드그룹 - 결과진단코드(판정코드)명
     */
    public static String CODE_GROUP_HEA_DIAGNOSE_CD = "HEA_DIAGNOSE";

    /**
     * 코드그룹 - 업무수행적합 코드명
     */
    public static String CODE_GROUP_HEA_WORKABLE_CD = "HEA_WORKABLE";

    /**
     * 코드그룹 - 사후관리 코드명
     */
    public static String CODE_GROUP_HEA_FOLLOW_UP_CD = "HEA_FOLLOW_UP";

    /**
     * 코드마스터 - 건강검진 항목결과값 타입_수치형
     */
    public static String CODE_MASTER_HEA_RESULT_TYPE_NUNBER = "N";

    /**
     * 코드마스터 - 특수검진 코드명
     */
    public static String CODE_MASTER_HEA_CHECKUP_CLASS_SPECIAL = "B1";

    /**
     * 문진항목 - 흡연>흡연여부
     */
    public static String INTERVIEW_ITEM_SMOKING_CD = "00027";

    /**
     * 문진항목 - 음주>음주여부
     */
    public static String INTERVIEW_ITEM_DRINKING_CD = "00030";

    /**
     * 근골격계 질환 - 진행단계 (계획등록) SK E&S
     */
    public static String MUSC_RESEARCH_STATE_WRITE = "HMCS1";
    /**
     * 근골격계 질환 - 진행단계 (계획완료) SK E&S
     */
    public static String MUSC_RESEARCH_STATE_END = "HMCS2";
    /**
     * 근골격계 질환 - 진행단계 (조사결과제출중) SK E&S
     */
    public static String MUSC_RESEARCH_RESULT_STATE_WRITE = "HMCS3";
    /**
     * 근골격계 질환 - 진행단계 (조사결과제출완료) SK E&S
     */
    public static String MUSC_RESEARCH_HARMFUL_SEND_COMP = "HMCS4";
    /**
     * 근골격계 질환 - 진행단계 (유해요인조사중) SK E&S
     */
    public static String MUSC_RESEARCH_HARMFUL_SUR_WRITE = "HMCS5";
    /**
     * 근골격계 질환 - 진행단계 (유해요인조사완료) SK E&S
     */
    public static String MUSC_RESEARCH_HARMFUL_SUR_COMPLETE = "HMCS6";

    // 보건
    /**
     * 코드그룹 - 직무스트레스 (설문작성중)
     */
    public static String CHKLIST_STATE_WRITE = "30";

    /**
     * 코드그룹 - 직무스트레스 (설문작성중)
     */
    public static String CHKLIST_STATE_END = "40";

    /**
     * 코드마스터 - 작업환경측정 계획등록
     */
    public static String WORK_MEASURE_PLAN_INS = "WMS20";
    /**
     * 코드마스터 - 작업환경측정 계획접수
     */
    public static String WORK_MEASURE_PLAN_COMP = "WMS20";
    /**
     * 코드마스터 - 작업환경측정 결과입력중
     */
    public static String WORK_MEASURE_RESULT_WRITE = "WMS30";

    /**
     * 코드마스터 - 작업환경측정 결과완료
     */
    public static String WORK_MEASURE_RESULT_COMP = "WMS40";

    /**
     * 코드마스터 - 입법예고법규 검토 완료
     */
    public static String LAW_MAKING_COMPLETE = "LMCS4";

    // 환경
    /**
     * 코드마스터 - 운영일지 상태(작성중)
     */
    public static String ENV_OPERATION_LOG_STATUS_WRITE = "11";

    /**
     * 코드마스터 - 운영일지 상태(검토중)
     */
    public static String ENV_OPERATION_LOG_STATUS_CONFIRM = "21";

    /**
     * 코드마스터 - 운영일지 상태(검토완료)
     */
    public static String ENV_OPERATION_LOG_STATUS_CONFIRM_COMPLETE = "29";

    /**
     * 코드마스터 - 운영일지 상태(결재중)
     */
    public static String ENV_OPERATION_LOG_STATUS_APPROVAL = "31";

    /**
     * 코드마스터 - 운영일지 상태(결재완료)
     */
    public static String ENV_OPERATION_LOG_STATUS_APPROVAL_COMPLETE = "39";

    /**
     * 코드마스터 - 폐기물처리요청 상태(요청)
     */
    public static String ENV_WASTE_DISPOSAL_STATUS_REQUEST = "11";

    /**
     * 코드마스터 - 폐기물처리요청 상태(처리중)
     */
    public static String ENV_WASTE_DISPOSAL_STATUS_PROCESS = "31";

    /**
     * 코드마스터 - 폐기물처리요청 상태(완료)
     */
    public static String ENV_WASTE_DISPOSAL_STATUS_COMPLETE = "91";
    // 환경

    // 안전
    /**
     * 안전 변경관리 진행 - PSM TARCKING
     */
    public static String SAF_CHNG_REF_WORK_1 = "CGRW1";
    /**
     * 안전 변경관리 진행 - 공정위험성평가
     */
    public static String SAF_CHNG_REF_WORK_2 = "CGRW2";
    /**
     * 안전 변경관리 진행 - 교육/훈련
     */
    public static String SAF_CHNG_REF_WORK_3 = "CGRW3";
    /**
     * 안전 변경관리 진행 - 안전운전계획
     */
    public static String SAF_CHNG_REF_WORK_4 = "CGRW4";
    /**
     * 안전 변경관리 진행 - 가동전점검
     */
    public static String SAF_CHNG_REF_WORK_5 = "CGRW5";
    /**
     * 안전 변경관리 진행 - 비상조치계획
     */
    public static String SAF_CHNG_REF_WORK_6 = "CGRW6";
    /**
     * 안전 변경관리 진행 - 장외영향평가
     */
    public static String SAF_CHNG_REF_WORK_7 = "CGRW7";
    /**
     * 안전 변경관리 진행 - 위해관리계획
     */
    public static String SAF_CHNG_REF_WORK_8 = "CGRW8";
    /**
     * 안전 변경관리 진행 - 설치검사
     */
    public static String SAF_CHNG_REF_WORK_9 = "CGRW9";

    /**
     * 코드마스터 - 변경관리 변경요청
     */
    public static String SAF_CHNG_STEP_CGSP1 = "CGSP1";
    /**
     * 코드마스터 - 변경관리 기술검토
     */
    public static String SAF_CHNG_STEP_CGSPA = "CGSPA";
    /**
     * 코드마스터 - 변경관리 변경진행
     */
    public static String SAF_CHNG_STEP_CGSP2 = "CGSP2";
    /**
     * 코드마스터 - 변경관리 사후관리
     */
    public static String SAF_CHNG_STEP_CGSP3 = "CGSP3";
    /**
     * 코드마스터 - 변경관리 변경완료
     */
    public static String SAF_CHNG_STEP_CGSP4 = "CGSP4";

    /**
     * 코드마스터 - 안전점검진행상태 일정상태
     */
    public static String SAF_CHK_STEP_SCHEDULE_CD = "CHS00";

    /**
     * 코드마스터 - 안전점검진행상태 계획상태
     */
    public static String SAF_CHK_STEP_PLAN_CD = "CHS01";

    /**
     * 코드마스터 - 안전점검진행상태 결과상태
     */
    public static String SAF_CHK_STEP_RESULT_CD = "CHS02";

    /**
     * 코드마스터 - 안전점검진행상태 조치진행중상태
     */
    public static String SAF_CHK_STEP_CHECK_CD = "CHS03";

    /**
     * 코드마스터 - 안전점검진행상태 완료상태
     */
    public static String SAF_CHK_STEP_IMPROVED_CD = "CHS04";

    /**
     * 코드마스터 - 안전점검진행상태 일정상태
     */
    public static String SAF_CHECK_STEP_SCHEDULE_CD = "CHS00";
    /**
     * 코드마스터 - 안전점검진행상태 계획상태
     */
    public static String SAF_CHECK_STEP_PLAN_CD = "CHS01";

    /**
     * 코드마스터 - 안전점검진행상태 결과상태
     */
    public static String SAF_CHECK_STEP_RESULT_CD = "CHS02";

    /**
     * 코드마스터 - 안전점검진행상태 조치진행중상태
     */
    public static String SAF_CHECK_STEP_CHECK_CD = "CHS03";

    /**
     * 코드마스터 - 안전점검진행상태 완료상태
     */
    public static String SAF_CHECK_STEP_IMPROVED_CD = "CHS04";

    /**
     * 점검계획진행상태 - 완료
     */
    public static String SAF_CHECK_PLAN_STEP_COMPLETE_CD = "Y";

    /**
     * 점검계획진행상태 - 작성중
     */
    public static String SAF_CHECK_PLAN_STEP_NOT_COMPLETE_CD = "N";

    /**
     * 코드마스터 - 개선분류 사고
     */
    public static String SAF_IMPR_CLASS_ACCIDENT = "ICL01";

    /**
     * 코드마스터 - 개선분류 아차사고
     */
    public static String SAF_IMPR_CLASS_NEARMISS = "ICL02";

    /**
     * 코드마스터 - 개선분류 안전점검
     */
    public static String SAF_IMPR_CLASS_CHECK = "ICL03";

    /**
     * 코드마스터 - 개선분류 설비점검
     */
    public static String SAF_IMPR_CLASS_FACILITY = "ICL04";

    /**
     * 코드마스터 - 개선분류 PSM자체감사
     */
    public static String SAF_IMPR_CLASS_PSM = "ICL30";

    /**
     * 코드마스터 - 작업위험성평가
     */
    public static String SAF_IMPR_CLASS_JSA = "ICL05";

    /**
     * 코드마스터 - 개선분류 cto
     */
    public static String SAF_IMPR_CLASS_CTO = "ICL06";

    /**
     * 코드마스터 - 개선분류 협력업체자체점검
     */
    public static String SAF_IMPR_CLASS_VENDOR = "ICL07";

    /**
     * 코드마스터 - 개선분류 시설점검
     */
    public static String SAF_IMPR_CLASS_FACILITY_INSPECTION = "ICL08";

    /**
     * 코드마스터 - 개선분류 순회결과
     */
    public static String SAF_IMPR_PATROL_RESULT = "ICL09";

    /**
     * 코드마스터 - 개선분류 근골격계
     */
    public static String SAF_IMPR_CLASS_MUSC = "ICL10";

    /**
     * 코드마스터 - 개선분류 안전관찰(SK E&S)
     */
    public static String SAF_IMPR_CLASS_OBSR = "ICL11";

    /**
     * 코드마스터 - 개선분류 합동점검(SK E&S)
     */
    public static String SAF_IMPR_CLASS_CONSOL = "ICL12";

    /**
     * 코드마스터 - 개선분류 산업안전보건위원회(SK E&S)
     */
    public static String SAF_IMPR_CLASS_COMMITTEE = "ICL13";

    /**
     * 코드마스터 - 개선분류 근골격계(SK E&S)
     */
    public static String SAF_IMPR_CLASS_MUSC_RESEARCH = "ICL14";

    /**
     * 코드마스터 - 변경관리
     */
    public static String SAF_IMPR_CLASS_CHANGE = "ICL15";

    /**
     * 코드마스터 - 가동전점검
     */
    public static String SAF_IMPR_CLASS_PREOPER = "ICL17";

    /**
     * 코드마스터 - 법규준수평가결과
     */
    public static String SAF_IMPR_CLASS_LAWRSLT = "ICL20";
    /**
     * 코드마스터 - 평가결과 관리
     */
    public static String SAF_IMPR_CLASS_RSARESULT = "ICL36";
    /**
     * 코드마스터 - 비상대응훈련 관리
     */
    public static String SAF_IMPR_CLASS_EMERGENCY = "ICL37";
    /**
     * 코드마스터 - 안전보건법령 이행점검 관리
     */
    public static String SAF_IMPR_CLASS_SAFETYHEALTH = "ICL38";

    /**
     * 코드마스터 - 안전보건법령 이행점검 관리
     */
    public static String SAF_IMPR_CLASS_MAJ_DISA = "ICL40";
    /**
     * 코드마스터 - 작업 위험성평가 관리
     */
    public static String SAF_IMPR_CLASS_WORK_RSLT = "ICL41";
    /**
     * 코드마스터 - 공정 위험성평가 관리
     */
    public static String SAF_IMPR_CLASS_PROC_RSLT = "ICL42";

    /**
     * 코드마스터 - 개선분류 종사자청취의견관리
     */
    public static String SAF_IMPR_CLASS_COMMITTEE2 = "ICL43";

    /**
     * 코드마스터 - 개선분류 사고 조서결과
     */
    public static String SAF_IMPR_CLASS_ACCIDENT_RSLT = "ICL44";
    /**
     * 코드마스터 - 조치처리구분 즉시조치
     */
    public static String SAF_ACT_CLASS_IMMEDIATE_ACTION = "ACL01";

    /**
     * 코드마스터 - 조치처리구분 개선요청
     */
    public static String SAF_ACT_CLASS_REQUEST_IMPROVEMENT = "ACL02";

    /**
     * 코드마스터 - 개선진행단계 요청등록
     */
    public static String SAF_IMPR_STEP_REQUEST_REGISTRATION = "IMST1";

    /**
     * 코드마스터 - 개선진행단계 미접수
     */
    public static String SAF_IMPR_STEP_UNRELIEVED = "IMST2";

    /**
     * 코드마스터 - 개선진행단계 조치부서조치중
     */
    public static String SAF_IMPR_STEP_MEASURES_ACTION = "IMST3";

    /**
     * 코드마스터 - 개선진행단계 요청부서확인
     */
    public static String SAF_IMPR_STEP_REQUEST_DEPARTMENT_CONFIRMATION = "IMST4";

    /**
     * 코드마스터 - 개선진행단계 개선완료
     */
    public static String SAF_IMPR_STEP_IMPROVED = "IMST5";

    /**
     * 코드마스터 - 사내사고 원인유형 기타
     */
    public static String CAU_TYPE_ETC = "CAU13";

    /**
     * 코드마스터 - 사내사고유형 기타
     */
    public static String ACCIDENT_TYPE_ETC = "OCC10";

    /**
     * 코드마스터 - 사내사고진행단계 발생등록
     */
    public static String SAF_ACCIDENT_STEP_OCCURW = "ACCS1";

    /**
     * 코드마스터 - 사내사고진행단계 접수
     */
    public static String SAF_ACCIDENT_STEP_RECEIPT = "ACCS2";

    /**
     * 코드마스터 - 사내사고진행단계 조사결과
     */
    public static String SAF_ACCIDENT_STEP_RESULT = "ACCS3";

    /**
     * 코드마스터 - 사내사고진행단계 조사완료
     */
    public static String SAF_ACCIDENT_STEP_COMPLETE = "ACCS4";

    /**
     * 코드마스터 - 사내사고진행단계 조사완료
     */
    public static String SAF_ACCIDENT_STEP_CHECK_COMPLETE = "ACCS5";

    /**
     * 코드마스터 - 공사현황 진행단계 공사작성중
     */
    public static String SAF_CONST_STEP_WRITING = "CONS1";

    /**
     * 코드마스터 - 공사현황 진행단계 공사중
     */
    public static String SAF_CONST_STEP_WORKING = "CONS3";

    /**
     * 코드마스터 - 공사현황 진행단계 공사완료
     */
    public static String SAF_CONST_STEP_COMPLETE = "CONS4";

    /**
     * 코드마스터 - 안전작업 점검확인항목 확인사항 구분 - 공통
     */
    public static String SAF_WKOD_ITEM_DPTY_COMM = "WDT03";

    /**
     * 코드마스터 - 안전작업 점검확인항목 작업종류 - 일반
     */
    public static String SAF_WKOD_ITEM_KIND_NOMAL = "WKKDNS";

    /**
     * 코드마스터 - 안전작업 점검확인항목 작업종류 - 화기
     */
    public static String SAF_WKOD_ITEM_KIND_FIREARM = "WKKDSS";

    /**
     * 코드마스터 - 작업허가서진행단계 신청
     */
    public static String SAF_WKOD_STEP_REQUEST = "WKS01";

    /**
     * 코드마스터 - 작업허가서진행단계 발행
     */
    public static String SAF_WKOD_STEP_PUBLISH = "WKS02";

    /**
     * 코드마스터 - 작업허가서진행단계 승인
     */
    public static String SAF_WKOD_STEP_APPROVAL = "WKS03";

    /**
     * 코드마스터 - 작업허가서진행단계 출력
     */
    public static String SAF_WKOD_STEP_PRINT = "WKS04";

    /**
     * 코드마스터 - 작업허가서진행단계 완료
     */
    public static String SAF_WKOD_STEP_COMPLETION = "WKS05";

    /**
     * 코드마스터 - 작업허가서진행단계 취소
     */
    public static String SAF_WKOD_STEP_CANCEL = "WKS06";

    /**
     * 코드마스터 - 작업허가서진행단계 연장
     */
    public static String SAF_WKOD_STEP_OVER = "WKS07";

    /**
     * 코드마스터 - 작업허가서점검확인사항구분 요청부서
     */
    public static String SAF_WKOD_DPTY_REQUEST = "WDT01";

    /**
     * 코드마스터 - 작업허가서점검확인사항구분 작업부서
     */
    public static String SAF_WKOD_DPTY_WORK = "WDT02";

    /**
     * 코드마스터 - 진행단계 - 작성중
     */
    public static String SAF_PROCESS_STEP_CREATE = "STEP1";

    /**
     * 코드마스터 - 진행단계 - 완료
     */
    public static String SAF_PROCESS_STEP_COMPLTE = "STEP2";

    /**
     * 코드마스터 - 진행단계 - 작성중
     */
    public static String COM_PROCESS_STEP_CREATE = "PSREG";

    /**
     * 코드마스터 - 진행단계 - 완료
     */
    public static String COM_PROCESS_STEP_COMPLTE = "PSEND";

    /**
     * 코드마스터 - 아차사고 진행단계 - 발생등록
     */
    public static String SAF_NEARMISS_STEP_CREATE = "NEMS1";

    /**
     * 코드마스터 - 아차사고 진행단계 - 등록완료
     */
    public static String SAF_NEARMISS_STEP_REGISTRATION = "NEMS2";

    /**
     * 평가계획 - 작성중 code
     */
    public static String RSA_ASSESS_PLAN_STEP_WRITE = "P";
    /**
     * 평가계획 - 계획완료 code
     */
    public static String RSA_ASSESS_PLAN_STEP_CMPLT_PLAN_CODE = "A";
    /**
     * 평가계획 - 진행중 code
     */
    public static String RSA_ASSESS_PLAN_STEP_ING_CODE = "E";
    /**
     * 평가계획 - 평가완료 code
     */
    public static String RSA_ASSESS_PLAN_STEP_EVAL_CMPLT_CODE = "C";

    /**
     * 협력업체 출입신청 진행상태 - 신청
     */
    public static String COM_PASS_REQ_STEP_REQUEST = "PASS1";

    /**
     * 업체조회범위 - 업체유형
     */
    public static String COM_VENDOR_RGE_ATTRIBUTE = "CVR02";

    /**
     * 업체조회범위 - 업체지정
     */
    public static String COM_VENDOR_RGE_VENDOR = "CVR03";

    /**
     * 순회대상구분코드 - 부서
     */
    public static String COM_TGT_CLS_DEPT = "ACTDP";

    /**
     * 순회대상구분코드 - 협력업체
     */
    public static String COM_TGT_CLS_VENDOR = "ACTSC";

    /**
     * 경영정보항목 - 근로시간
     */
    public static int MGT_MG_INFO_ITM_WORK_TIME = 2101;

    /**
     * 경영정보항목 - 근로자수
     */
    public static int MGT_MG_INFO_ITM_WORKER = 2102;

    /**
     * 경영정보항목 - 근로손실일수
     */
    public static int MGT_MG_INFO_ITM_WORK_DAY = 2103;

    /**
     * 보호구진행단계코드 - 신청작성중
     */
    public static String SAF_SPE_STEP_REQ_ING = "SPES1";

    /**
     * 보호구진행단계코드 - 신청완료
     */
    public static String SAF_SPE_STEP_REQ_CMPLT = "SPES2";

    /**
     * 보호구진행단계코드 - 지급처리중
     */
    public static String SAF_SPE_STEP_GIV_ING = "SPES3";

    /**
     * 보호구진행단계코드 - 지급완료
     */
    public static String SAF_SPE_STEP_GIV_CMPLT = "SPES4";

    public static enum ROLE_TYPE {
        ROLE_USER, ROLE_ADMIN
    }

    // CRD10 업로드중 : 규제DB를 업로드시초기코드
    // CRD20 적용불가 : 규제DB를 업로드중 오류가발생한 경우
    // CRD30 적용대기 : 규제DB 업로드가 성공한 경우
    // CRD40 적용완료 : 적용대기 상태의 정상적으로 업로드한 규제DB를 실제 화학물질에 반영한 상태
    // CRD50 적용오류 : 적용완료처리시 오류가 발생한 경우
    // CRD60 처리완료 : 적용되어 화학물질에 반영된 후에, 반영된 화학물질 정보를 확인후 최종검증된 경우(실적에 최신반영건으로
    // 처리시영향)
    public static enum ChmRegulDbStatus {
        PREPARE("CRD10"), UPLOAD_ERR("CRD20"), UPLOAD_SUCCESS("CRD30"), APPLY_SUCCESS("CRD40"), APPLY_ERR("CRD50"), CONFIRM("CRD60");

        public final String code;

        private ChmRegulDbStatus(String code) {
            this.code = code;
        }
    }

    // 개정법규
    /**
     * 개정법규 - 법제처코드(법)
     */
    public static String LAW_ELAW_MST = "LAWMST";

    /**
     * 개정법규 - 법제처코드(시행령)
     */
    public static String LAW_ELAW_ENF = "LAWENF";

    /**
     * 개정법규 - 법제처코드(시행규칙)
     */
    public static String LAW_ELAW_RGL = "LAWRGL";

    /**
     * 입법예고 - 소관부처 코드
     */
    public static String LAW_MAKING_ORG = "1492000,1721000,1450000,1661000,1530000,1192000,1741000,1480000 ";

    // PSM 감사
    /**
     * 코드마스터 - PSM 계획등록
     */
    public static String CODE_MASTER_PSM_PROG_STATE_WRITE = "PPS10";

    /**
     * 코드마스터 - PSM 계획결재요청
     */
    public static String CODE_MASTER_PSM_PROG_STATE_APPR_REQUEST = "PPS20";

    /**
     * 코드마스터 - PSM 계획결재완료
     */
    public static String CODE_MASTER_PSM_PROG_STATE_APPR_COMPLETE = "PPS30";

    /**
     * 코드마스터 - PSM 결과완료
     */
    public static String CODE_MASTER_PSM_PROG_STATE_RESULT_COMPLETE = "PPS40";

    /**
     * 코드마스터 - PSM 결과결재요청
     */
    public static String CODE_MASTER_PSM_PROG_STATE_RESULT_APPR_REQUEST = "PPS50";

    /**
     * 코드마스터 - PSM 결과결재완료
     */
    public static String CODE_MASTER_PSM_PROG_STATE_RESULT_APPR_COMPLETE = "PPS60";

    /**
     * 코드마스터 - 선임 감사원
     */
    public static String AUDIT_TYPE_CD_SENIOR_AUDITOR = "AT10";

    /**
     * 코드마스터 - 감사원
     */
    public static String AUDIT_TYPE_CD_AUDITOR = "AT20";

    /**
     * 수시
     */
    public static String RSA_REG_REGDEM_FREQ = "REG02";

    /**
     * 계획
     */
    public static String SAF_EDU_STEP_PLAN = "SES01";

    /**
     * 결과
     */
    public static String SAF_EDU_STEP_RESULT = "SES02";

    /**
     * 완료
     */
    public static String SAF_EDU_STEP_COMPLETE = "SES03";

    // 교육마스터
    /**
     * 표시여부 - Y
     */
    public static String SAF_EDU_DIS_CD = "Y";

    /**
     * 표시여부 - N
     */
    public static String SAF_EDU_NOT_DIS_CD = "N";

    /**
     * 코드마스터 - 교육 대상 부서 등록
     */
    public static String CODE_MASTER_RSLT_APPR_STATE_WRITE = "RSLTAPPR10";

    /**
     * 코드마스터 - 교육진행단계코드 계획
     */
    public static String CODE_MASTER_SAF_EDU_STEP_PLAN = "SES01";

    /**
     * 코드마스터 - 교육진행단계코드 결과
     */
    public static String CODE_MASTER_SAF_EDU_STEP_RESULT = "SES02";

    /**
     * 코드마스터 - 교육진행단계코드 교육완료
     */
    public static String CODE_MASTER_SAF_EDU_STEP_COMPLETE = "SES03";

    /**
     * 환경-환경영향평가-진행단계-계획
     */
    public static String SAF_ENV_EFFECT_EVAL_STEP_PLAN = "EEE01";

    /**
     * 환경-환경영향평가-진행단계-결과
     */
    public static String SAF_ENV_EFFECT_EVAL_STEP_RSLT = "EEE02";

    /**
     * 경영-SHE예산관리-진행단계-편성
     */
    public static String MGT_BUDGET_STEP_BUDGETING = "MBS01";

    /**
     * 경영-SHE예산관리-진행단계-집행
     */
    public static String MGT_BUDGET_STEP_EXECUTION = "MBS02";

    /**
     * 공통-상태코드-미작성
     */
    public static String COM_STATE_INIT = "STATE1";

    /**
     * 공통-상태코드-작성중
     */
    public static String COM_STATE_ING = "STATE2";

    /**
     * 공통-상태코드-결재중
     */
    public static String COM_STATE_APPR = "STATE3";

    /**
     * 공통-상태코드-결재완료
     */
    public static String COM_STATE_COMPLETE = "STATE4";

    /**
     * 경영-법정선임자 평가관리-진행단계-계획
     */
    public static String MGT_ELECT_EVAL_STEP_PLAN = "SETS1";

    /**
     * 경영-법정선임자 평가관리-진행단계-본인평가
     */
    public static String MGT_ELECT_EVAL_STEP_ME_RSLT = "SETS2";

    /**
     * 경영-법정선임자 평가관리-진행단계-상위평가
     */
    public static String MGT_ELECT_EVAL_STEP_UP_RSLT = "SETS3";

    /**
     * 경영-법정선임자 평가관리 본인평가/상위평가 진행단계-미작성
     */
    public static String SAF_PROCESS_STEP2_UNWRITTEN = "PSURE";

    /**
     * 경영-법정선임자 평가관리 본인평가/상위평가 진행단계-작성중
     */
    public static String SAF_PROCESS_STEP2_ING = "PSREG";

    /**
     * 경영-법정선임자 평가관리 본인평가/상위평가 진행단계-완료
     */
    public static String SAF_PROCESS_STEP2_END = "PSEND";

    /**
     * 위험성평가 작업위험성평가 계획 대상선정 요청 알람ID
     */
    public static String ALARM_S10016 = "S10016";

    /**
     * 위험성평가 작업위험성평가 계획 검토 요청 알람ID
     */
    public static String ALARM_S10017 = "S10017";

    /**
     * 위험성평가 작업위험성평가 결과 검토 개선사항 접수 요청 알람ID
     */
    public static String ALARM_S10018 = "S10018";

    /**
     * 위험성평가 작업위험성평가 결과 검토 결재반려 및 부적합 알람ID
     */
    public static String ALARM_S10044 = "S10044";

    /**
     * 경영 부서별 추진계획 진행상태 코드 - 등록
     */
    public static String MGT_TGT_D_STEP_REG = "PSREG";

    /**
     * 교육방법코드 - 동영상
     */
    public static String EDU_METHOD_VIDEO = "EDM03";

    /**
     * 사업장코드 - 외주동(잉크테크)
     */
    public static String PLANT_CD_05 = "FD05";
    /**
     * 사업장코드 - 해외
     */
    public static String PLANT_CD_09 = "FD09";
    /**
     * 사업장코드 - 기타
     */
    public static String PLANT_CD_10 = "FD10";

}
