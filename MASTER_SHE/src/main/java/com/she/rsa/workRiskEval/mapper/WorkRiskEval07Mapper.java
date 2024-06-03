package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval07;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval07Mapper")
public interface WorkRiskEval07Mapper {

    /**
     * 작업위험성평가 사업장 중요위험등록부 목록
     * 
     * @return 작업위험성평가 사업장 중요위험등록부 목록
     * @throws Exception
     */
    public List<WorkRiskEval07> getworkRiskEval07List(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, @Param("startYear") String startYear, @Param("endYear") String endYear,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
