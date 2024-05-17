package com.she.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.CodeDomainMapper;
import com.she.manage.model.CodeDomain;

/**
 * 코드 도메인 기능정의
 *
 */
@Service
public class CodeDomainService {

    @Autowired
    private CodeDomainMapper codeDomainMapper;

    /**
     * 코드도메인 전체 조회
     * 
     * @param codeDomainNm
     *            코드도메인명
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeDomain> getCodeDomains(String codeDomainNm) throws Exception {
        return this.codeDomainMapper.getCodeDomains(codeDomainNm);
    }

    /**
     * 코드도메인 상세 조회
     * 
     * @param codeDomainCd
     *            코드그룹
     * @return 코드도메인
     * @throws Exception
     */
    public CodeDomain getCodeDomain(String codeDomainCd) throws Exception {
        return this.codeDomainMapper.getCodeDomain(codeDomainCd);
    }

    /**
     * 코드도메인 신규 등록
     * 
     * @param codeDomain
     *            코드도메인
     * @return 생성행수
     * @throws Exception
     */
    public int createCodeDomain(CodeDomain codeDomain) throws Exception {
        return this.codeDomainMapper.createCodeDomain(codeDomain);
    }

    /**
     * 코드도메인 수정
     * 
     * @param codeDomain
     *            코드도메인
     * @return 수정행수
     * @throws Exception
     */
    public int updateCodeDomain(CodeDomain codeDomain) throws Exception {
        return this.codeDomainMapper.updateCodeDomain(codeDomain);
    }

}
