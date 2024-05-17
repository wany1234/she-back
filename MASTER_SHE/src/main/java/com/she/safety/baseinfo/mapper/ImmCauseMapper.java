package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.ImmCauseMapper")
public interface ImmCauseMapper {

    /**
     * 안전 기준정보 원인(직접) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 원인(직접) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkImmCause(@Param("codeGroupCd") String codeGroupCd,
            @Param("code") String code, @Param("codeNm") String codeNm) throws Exception;

}
