package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.IndCauseMapper")
public interface IndCauseMapper {

    /**
     * 안전 기준정보 원인(간접) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 원인(간접) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkIndCause(@Param("codeGroupCd") String codeGroupCd,
            @Param("code") String code, @Param("codeNm") String codeNm) throws Exception;

}
