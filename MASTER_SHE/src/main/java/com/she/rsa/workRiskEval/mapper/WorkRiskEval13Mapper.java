package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval13DtlUnitWork;
import com.she.rsa.model.WorkRiskEval13UnitWork;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval13Mapper")
public interface WorkRiskEval13Mapper {

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

}
