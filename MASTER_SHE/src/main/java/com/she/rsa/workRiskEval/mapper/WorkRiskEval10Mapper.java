package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval10DeptProcess;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval10Mapper")
public interface WorkRiskEval10Mapper {

    /**
     * 작업위험성평가 부서별 위험성평가 공정 목록
     * 
     * @return 작업위험성평가 부서별 위험성평가 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval10DeptProcess> getworkRiskEval10Lists(@Param("plantCd") String plantCd, @Param("deptNm") String deptNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 작업위험성평가 공정별 부서
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정별 부서 상세조회
     * @throws Exception
     */
    public WorkRiskEval10DeptProcess getWorkRiskEval10Info(@Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
