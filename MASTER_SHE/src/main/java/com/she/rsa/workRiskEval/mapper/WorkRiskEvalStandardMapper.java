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
package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval09Line;
import com.she.rsa.model.WorkRiskEval09Process;
import com.she.rsa.model.WorkRiskEval11UnitWork;
import com.she.rsa.model.WorkRiskEval12RefInd;
import com.she.rsa.model.WorkRiskEval13DtlUnitWork;
import com.she.rsa.model.WorkRiskEval13UnitWork;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval09Mapper")
public interface WorkRiskEvalStandardMapper {

	
	// 09 공정
    /**
     * 작업위험성평가 공정 목록
     * 
     * @return 작업위험성평가 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getworkRiskEval09Lists(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("processNm") String processNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 공정 등록
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public int createWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception;

    /**
     * 작업위험성평가 공정 수정
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception;

    /**
     * 작업위험성평가 공정 상세
     * 
     * @param plantCd,
     *            processCd 공정코드
     * @return 작업위험성평가 공정 상세조회
     * @throws Exception
     */
    public WorkRiskEval09Process getWorkRiskEval09Info(@Param("plantCd") String plantCd, @Param("processCd") String processCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 공정 라인 조회
     * 
     * @return 작업위험성평가 공정 라인 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Line> getworkRiskEval09LineLists(@Param("plantCd") String plantCd, @Param("processClsCd") String processClsCd, @Param("processNm") String processNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getWorkRiskEval09UpProcessLvl2(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getWorkRiskEval09UpProcessLvl3(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    
    // 11 단위작업
    /**
     * 작업위험성평가 단위작업 목록
     * 
     * @return 작업위험성평가 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval11UnitWork> getworkRiskEval11Lists(@Param("plantCd") String plantCd, @Param("unitWorkNm") String unitWorkNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int createWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception;

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception;

    /**
     * 작업위험성평가 단위작업 상세
     * 
     * @param plantCd,
     *            unitWorkCd 평가번호
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    public WorkRiskEval11UnitWork getWorkRiskEval11Info(@Param("plantCd") String plantCd, @Param("unitWorkCd") String unitWorkCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
    
    
    // 13 세부작업
    /**
     * 작업위험성평가 부서공정별 단위작업 목록
     * 
     * @return 작업위험성평가 부서공정별 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13Lists(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkNm") String unitWorkNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 공정검색팝업 공정 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정검색팝업 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13ProcessLists(@Param("plantCd") String plantCd, @Param("prcsLvlCd") String prcsLvlCd, @Param("processNm") String processNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 단위작업검색팝업 단위작업 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업검색팝업 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13UnitLists(@Param("plantCd") String plantCd, @Param("unitWorkNm") String unitWorkNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 부서공정별 상세
     * 
     * @param plantCd,
     *            deptCd, processCd, unitWorkCd
     * @return 작업위험성평가 부서공정별 상세조회
     * @throws Exception
     */
    public WorkRiskEval13UnitWork getWorkRiskEval13Info(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 부서공정 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public int createWorkRiskEval13(WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception;

    /**
     * 작업위험성평가 부서공정 수정
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval13(WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception;

    /**
     * 작업위험성평가 부서공정별 세부작업 조회
     * 
     * @param plantCd,
     *            deptCd, processCd, unitWorkCd
     * @return 작업위험성평가 부서공정별 세부작업 목록 조회
     * @throws Exception
     */
    public List<WorkRiskEval13DtlUnitWork> getWorkRiskEval13DtlUnitWorkList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 부서공정 세부작업 등록
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public int createWorkRiskEval13DtlUnitWork(WorkRiskEval13DtlUnitWork workRiskEval13DtlUnitWork) throws Exception;

    /**
     * 작업위험성평가 부서공정 세부작업 수정
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval13DtlUnitWork(WorkRiskEval13DtlUnitWork workRiskEval13DtlUnitWork) throws Exception;
    
    
    // 12 평가지표
    /**
     * 작업위험성평가 관련지표 목록
     * 
     * @return 작업위험성평가 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval12RefInd> getworkRiskEval12Lists(@Param("plantCd") String plantCd, @Param("indTypeCd") String indTypeCd, @Param("setNm") String setNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 관련지표 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int createWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception;

    /**
     * 작업위험성평가 관련지표 수정
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception;

    /**
     * 작업위험성평가 관련지표 상세
     * 
     * @param plantCd,
     *            unitWorkCd 평가번호
     * @return 작업위험성평가 관련지표 상세조회
     * @throws Exception
     */
    public WorkRiskEval12RefInd getWorkRiskEval12Info(@Param("plantCd") String plantCd, @Param("indTypeCd") String indTypeCd, @Param("setVal") String setVal, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
