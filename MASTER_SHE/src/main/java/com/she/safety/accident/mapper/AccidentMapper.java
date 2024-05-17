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
package com.she.safety.accident.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.FacilityMst;
import com.she.chm.model.Chemprod;
import com.she.common.model.DefaultParam;
import com.she.safety.model.Accident;
import com.she.safety.model.AccidentCauMeas;
import com.she.safety.model.AccidentChemprod;
import com.she.safety.model.AccidentFacility;
import com.she.safety.model.AccidentInvest;
import com.she.safety.model.AccidentInvestPsn;
import com.she.safety.model.AccidentKind;
import com.she.safety.model.AccidentOcctype;
import com.she.safety.model.AccidentRefPsn;
import com.she.safety.model.AccidentVictim;
import com.she.safety.model.AccidentVictimHumanInjuryCls;
import com.she.safety.model.AccidentVictimHumanInjuryPart;

@Mapper
@Repository("com.she.safety.accident.mapper.AccidentMapper")
public interface AccidentMapper {

    /**
     * 사내사고 속보/등록 조회
     *
     * @param accidentStepCd
     *            사내사고진행단계
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            발생부서
     * @param area
     *            장소
     * @param accidentTitle
     *            사고명
     * @param plantCd
     *            사업장명
     * @return 사내사고 목록
     * @throws Exception
     */
    public List<Accident> getAccidentOccurs(@Param("accidentStepCd") String accidentStepCd, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("area") String area, @Param("accidentTitle") String accidentTitle, @Param("plantCd") String plantCd,
            @Param("bizApprStepNm") String bizApprStepNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 접수 조회
     *
     * @param accidentStepCd
     *            사내사고진행단계
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            발생부서
     * @param area
     *            장소
     * @param accidentTitle
     *            사고명
     * @param plantCd
     *            사업장명
     * @return 사내사고 목록
     * @throws Exception
     */
    public List<Accident> getAccidentReceptions(@Param("accidentStepCd") String accidentStepCd, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("area") String area, @Param("accidentTitle") String accidentTitle, @Param("plantCd") String plantCd,
            @Param("bizApprStepNm") String bizApprStepNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 조사결과 조회
     *
     * @param accidentStepCd
     *            사내사고진행단계
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            발생부서
     * @param area
     *            장소
     * @param accidentTitle
     *            사고명
     * @param plantCd
     *            사업장명
     * @return 사내사고 목록
     * @throws Exception
     */
    public List<Accident> getAccidentResults(@Param("accidentStepCd") String accidentStepCd, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("area") String area, @Param("accidentTitle") String accidentTitle, @Param("plantCd") String plantCd,
            @Param("accidentType") String accidentType, @Param("occKindCd") String occKindCd, @Param("occAttCd") String occAttCd, @Param("immCauseCd") String immCauseCd, @Param("indCauseCd") String indCauseCd, @Param("assessYear") String assessYear, @Param("monFlag") int monFlag, @Param("bizApprStepNm") String bizApprStepNm,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     * @throws Exception
     */
    public Accident getAccidentOccur(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     * @throws Exception
     */
    public Accident getAccidentReception(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     * @throws Exception
     */
    public Accident getAccidentResult(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고구분 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고구분
     * @throws Exception
     */
    public List<AccidentKind> getAccidentKinds(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 발생유형 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 발생유형 목록
     * @throws Exception
     */
    public List<AccidentOcctype> getAccidentOcctypes(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 등록
     *
     * @param accident
     *            사내사고
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccident(Accident accident) throws Exception;

    /**
     * 사내사고 저장
     *
     * @param accident
     *            사내사고
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateAccident(Accident accident) throws Exception;

    /**
     * 사내사고 설비 등록
     *
     * @param accidentFacility
     *            설비
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentFacility(AccidentFacility accidentFacility) throws Exception;

    /**
     * 사내사고 설비 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentFacility(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 물질 등록
     *
     * @param accidentChemprod
     *            취급물질
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentChemprod(AccidentChemprod accidentChemprod) throws Exception;

    /**
     * 사내사고 물질 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentChemprod(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 설비 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 설비 목록
     * @throws Exception
     */
    public List<FacilityMst> getAccidentFacilitys(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 취급물질 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 취급물질 목록
     * @throws Exception
     */
    public List<Chemprod> getAccidentChemprods(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고종류 등록
     *
     * @param accidentOccurtype
     *            사내사고종류
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentOccurtype(AccidentOcctype accidentOccurtype) throws Exception;

    /**
     * 사내사고구분 등록
     *
     * @param accidentKind
     *            사내사고구분
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentKind(AccidentKind accidentKind) throws Exception;

    /**
     * 사내사고 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccident(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고종류 삭제
     *
     * @param accidentTypeCd
     *            사내사고유형코드
     * @param safAccidentNo
     *            사내사고번
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentOccurtype(@Param("accidentTypeCd") String accidentTypeCd, @Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고구분 삭제
     *
     * @param safAccidentNo
     *            사내사고번
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentKind(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고조사 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고조사
     * @throws Exception
     */
    public AccidentInvest getAccidentInvest(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고조사 인원 등록
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentInvest(AccidentInvest accidentInvest) throws Exception;

    /**
     * 사내사고조사 인원 수정
     *
     * @param AccidentInvest
     *            사내사고조사 인원
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateAccidentInvest(AccidentInvest accidentInvest) throws Exception;

    /**
     * 사내사고조사 인원 수 조회
     *
     * @param AccidentInvest
     *            사내사고조사 인원
     * @return 사내사고조사 수
     * @throws Exception
     */
    public int countAccidentInvest(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고조사 인원 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고조사
     * @throws Exception
     */
    public List<AccidentInvestPsn> getAccidentInvestPsns(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고조사 인원 등록
     *
     * @param accidentInvestPsn
     *            사내사고조사인원
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentInvestPsn(AccidentInvestPsn accidentInvestPsn) throws Exception;

    /**
     * 사내사고조사 인원 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentInvestPsn(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사고관계자 등록
     *
     * @param accidentInvestPsn
     *            사고관계자
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentRefPsn(AccidentRefPsn accidentRefPsn) throws Exception;

    /**
     * 사고관계자 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentRefPsn(@Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 피해자정보 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @param victimTypeCd
     *            피해자구분코드
     * @return 사내사고 피해자정보 목록
     * @throws Exception
     */
    public List<AccidentVictim> getAccidentVictims(@Param("safAccidentNo") int safAccidentNo, @Param("victimTypeCd") String victimTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사고자 상해종류 조회
     *
     * @param safAccidentVictimNo
     *            사내사고피해자번호
     * @return 사고자 상해종류 목록
     * @throws Exception
     */
    public String[] getAccidentVictimHumanInjuryClss(@Param("safAccidentVictimNo") int safAccidentVictimNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사고자 상해부위 조회
     *
     * @param safAccidentVictimNo
     *            사내사고피해자번호
     * @return 사고자 상해부위 목록
     * @throws Exception
     */
    public String[] getAccidentVictimHumanInjuryParts(@Param("safAccidentVictimNo") int safAccidentVictimNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 피해자정보 등록
     *
     * @param AccidentVictim
     *            사내사고피해자정보
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentVictim(AccidentVictim accidentVictim) throws Exception;

    /**
     * 사내사고 피해자정보 삭제
     *
     * @param safAccidentVictimNo
     *            사내사고피해자번호
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentVictim(@Param("safAccidentVictimNo") int safAccidentVictimNo, @Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 상해부위 등록
     *
     * @param accidentVictimHumanInjuryPart
     *            사내사고 상해부위
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentVictimInjuryPart(AccidentVictimHumanInjuryPart accidentVictimHumanInjuryPart) throws Exception;

    /**
     * 사고자별 상해부위 삭제
     *
     * @param safAccidentVictimNo
     *            사내사고피해자번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentVictimInjuryPart(@Param("safAccidentVictimNo") int safAccidentVictimNo) throws Exception;

    /**
     * 사내사고 상해종류 등록
     *
     * @param accidentVictimHumanInjuryPart
     *            사내사고 상해종류
     * @return 변경 행 수
     * @throws Exception
     */
    public int createAccidentVictimInjuryCls(AccidentVictimHumanInjuryCls accidentVictimHumanInjuryCls) throws Exception;

    /**
     * 사고자별 상해종류 삭제
     *
     * @param safAccidentVictimNo
     *            사내사고피해자번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteAccidentVictimInjuryCls(@Param("safAccidentVictimNo") int safAccidentVictimNo) throws Exception;

    /**
     * 사내사고 원인 및 대책 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 원인 및 대책 목록
     * @throws Exception
     */
    public List<AccidentCauMeas> getAccidentCauMeass(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고 원인 및 대책 등록
     *
     * @param accidentCauMeas
     *            사내사고 원인 및 대책
     * @return 변경 행 수
     */
    public int createAccidentCauMeas(AccidentCauMeas accidentCauMeas) throws Exception;

    /**
     * 사내사고 원인 및 대책 수정
     *
     * @param accidentCauMeas
     *            사내사고 원인 및 대책
     * @return 변경 행 수
     */
    public int updateAccidentCauMeas(AccidentCauMeas accidentCauMeas) throws Exception;

    /**
     * 사내사고 원인 및 대책 삭제
     *
     * @param safAccidentCauMeasNo
     *            원인및대책번호
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     */
    public int deleteAccidentCauMeas(@Param("safAccidentCauMeasNo") int safAccidentCauMeasNo, @Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 진행상태 수정
     *
     * @param accidentStepCd
     *            원인및대책번호
     * @param updateUserId
     *            수정자ID
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     */
    public int updateAccidentStepCd(@Param("accidentStepCd") String accidentStepCd, @Param("updateUserId") String updateUserId, @Param("safAccidentNo") int safAccidentNo) throws Exception;

    /**
     * 사내사고 관계자 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고 관계자 목록
     * @throws Exception
     */
    public List<AccidentRefPsn> getAccidentRefPsns(@Param("safAccidentNo") int safAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사내사고집계 조회
     *
     * @param fromYear
     *            기간-년도(발생일) from
     * @param toYear
     *            기간-년도(발생일) to
     * @param yearMonth
     *            년월
     * @param plantCd
     *            사업장명
     * @param deptCd
     *            발생부서
     * @param accidentGubun
     *            사고구분(아차사고, 사고)
     * @param accidentType
     *            사고유형
     * @return 사내사고집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAccidentStatus(@Param("accidentYear") String accidentYear, @Param("yearMonth") List<String> yearMonth, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("accidentGubun") String accidentGubun, @Param("accidentType") String accidentType,
            @Param("occKindCd") String occKindCd, @Param("occAttCd") String occAttCd, @Param("immCauseCd") String immCauseCd, @Param("indCauseCd") String indCauseCd, @Param("yearMonthStr") String yearMonthStr, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사고등록 진행단계 변경
     *
     * @param safAccidentNo
     *            사고 번호
     * @param apprRqstNo
     *            결재진행번호
     * @param accidentStepCd
     *            진행단계
     * @return 사고등록 진행단계 변경 행 수
     * @throws Exception
     */
    public int completeAccidentOccur(@Param("safAccidentNo") int safAccidentNo, @Param("apprRqstNo") int apprRqstNo, @Param("accidentStepCd") String accidentStepCd) throws Exception;

    /**
     * 사고접수 진행단계 변경
     *
     * @param safAccidentNo
     *            사고 번호
     * @param apprRqstNo
     *            결재진행번호
     * @param accidentStepCd
     *            진행단계
     * @return 사고접수 진행단계 변경 행 수
     * @throws Exception
     */
    public int completeAccidentReception(@Param("safAccidentNo") int safAccidentNo, @Param("apprRqstNo") int apprRqstNo, @Param("accidentStepCd") String accidentStepCd) throws Exception;

    /**
     * 사고조사결과 진행단계 변경
     *
     * @param safAccidentNo
     *            사고 번호
     * @param apprRqstNo
     *            결재진행번호
     * @param accidentStepCd
     *            진행단계
     * @return 사고조사결과 진행단계 변경 행 수
     * @throws Exception
     */
    public int completeAccidentResult(@Param("safAccidentNo") int safAccidentNo, @Param("apprRqstNo") int apprRqstNo, @Param("accidentStepCd") String accidentStepCd) throws Exception;

    /**
     * 조사결과 현황
     * 
     * @param parameter
     *            검색조건
     * @return 조사결과 현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAccidentResultsStatusList(@Param("plantCd") String plantCd, @Param("assessYear") String assessYear, @Param("regRegdemCd") String regRegdemCd, @Param("riskType") String riskType, @Param("totalFlag") String totalFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
