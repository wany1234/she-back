package com.she.mgt.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.mgt.baseinfo.mapper.SafetyActionBizFieldMapper")
public interface SafetyActionBizFieldMapper {

    /**
     * 경영 기준정보 KPI 분야관리 중복 검사
     * 
     * @param parameter
     * @return 경영 기준정보 KPI 분야관리 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkSafetyActionBizField(@Param("codeGroupCd") String codeGroupCd, @Param("code") String code, @Param("codeNm") String codeNm) throws Exception;

}
