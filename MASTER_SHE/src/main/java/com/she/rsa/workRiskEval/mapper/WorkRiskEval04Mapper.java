package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval04Appr;
import com.she.rsa.model.WorkRiskEval05Exam;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval04Mapper")
public interface WorkRiskEval04Mapper {

    /**
     * 작업위험성평가 결재 목록
     * 
     * @return 작업위험성평가 결재 목록
     * @throws Exception
     */
    public List<WorkRiskEval04Appr> getworkRiskEval04List(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int updateAppr(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("apprRqstNo") int apprRqstNo, @Param("bizApprStepCd") String bizApprStepCd);

    public int updateWorkRiskEval04DeptPrcsInit(WorkRiskEval05Exam workRiskEval05Exam) throws Exception;
}
