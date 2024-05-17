package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.CodeDomain;

/**
 * 코드도메인 맵퍼
 *
 */
@Mapper
@Repository("com.she.manage.mapper.CodeDomainMapper")
public interface CodeDomainMapper {
    
    /**
     * 코드도메인 전체 조회
     * @param codeDomainNm 코드도메인명
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeDomain> getCodeDomains(@Param("codeDomainNm") String codeDomainNm) throws Exception;
    
    /**
     * 코드도메인 상세 조회
     * @param codeDomainCd 코드도메인코드
     * @return 코드도메인
     * @throws Exception
     */
    public CodeDomain getCodeDomain(@Param("codeDomainCd") String codeDomainCd) throws Exception;
    
    /**
     * 코드도메인 신규 등록
     * @param codeDomain 코드도메인
     * @return 생성행수
     * @throws Exception
     */
    public int createCodeDomain(CodeDomain codeDomain) throws Exception;
    
    /**
     * 코드도메인 수정
     * @param codeDomain 코드도메인
     * @return 수정행수
     * @throws Exception
     */
    public int updateCodeDomain(CodeDomain codeDomain) throws Exception;

}
