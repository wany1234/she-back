package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.OccurrFormMapper")
public interface OccurrFormMapper {

    /**
     * 안전 기준정보 발생형태(대분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(대분류) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkOccurrForm(@Param("codeGroupCd") String codeGroupCd,
            @Param("code") String code, @Param("codeNm") String codeNm) throws Exception;

    /**
     * 안전 기준정보 발생형태(중분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkOccurrAtt(@Param("codeGroupCd") String codeGroupCd,
            @Param("code") String code, @Param("codeNm") String codeNm) throws Exception;
    
    /**
     * 안전 기준정보 발생형태(중분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkOccurrAtts(@Param("codeDomainCd") String codeDomainCd, 
            @Param("codeGroupCd") String codeGroupCd, @Param("code") List<String> code) throws Exception;

}
