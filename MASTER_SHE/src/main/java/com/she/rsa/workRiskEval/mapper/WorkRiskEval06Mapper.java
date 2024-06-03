package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval06;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval06Mapper")
public interface WorkRiskEval06Mapper {

    /**
     * 작업위험성평가 팀 중요위험등록부 목록
     * 
     * @return 작업위험성평가 팀 중요위험등록부 목록
     * @throws Exception
     */
    public List<WorkRiskEval06> getworkRiskEval06List(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, @Param("startYear") String startYear, @Param("endYear") String endYear,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
