package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEvalImprove;
import com.she.rsa.model.WorkRiskEvalImpt;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEvalImptMapper")
public interface WorkRiskEvalImptMapper {

    /**
     * 작업위험성평가 팀/사업장 중요위험등록부 목록
     * 
     * @return 작업위험성평가 팀/사업장 중요위험등록부 목록
     * @throws Exception
     */
    public List<WorkRiskEvalImpt> getworkRiskEvalImptList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, @Param("startYear") String startYear, @Param("endYear") String endYear,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;
    
    /**
     * 작업위험성평가 중요위험 개선표 목록
     * 
     * @return 작업위험성평가 중요위험 개선표 목록
     * @throws Exception
     */
    public List<WorkRiskEvalImprove> getworkRiskEvalImproveList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, @Param("startYear") String startYear, @Param("endYear") String endYear,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
