package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval08;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval08Mapper")
public interface WorkRiskEval08Mapper {

    /**
     * 작업위험성평가 중요위험 개선표 목록
     * 
     * @return 작업위험성평가 중요위험 개선표 목록
     * @throws Exception
     */
    public List<WorkRiskEval08> getworkRiskEval08List(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("processNm") String processNm, @Param("unitWorkNm") String unitWorkNm, @Param("startYear") String startYear, @Param("endYear") String endYear,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
