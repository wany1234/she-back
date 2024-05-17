/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.CodeGroupMapper;
import com.she.manage.model.CodeGroup;

/**
 * 코드그룹 기능정의
 *
 */
@Service
public class CodeGroupService {
    @Autowired
    private CodeGroupMapper codeGroupMapper;

    /**
     * 공용코드그룹 전체 조회
     * 
     * @param codeDomainCd
     *            코드도메인
     * @param codeGroupCd
     *            코드그룹명
     * @return 코드그룹목록
     * @throws Exception
     */
    public List<CodeGroup> getCodeGroups(String codeDomainCd, String codeGroupNm, String forSystemYn, String settableYn) throws Exception {
        return this.codeGroupMapper.getCodeGroups(codeDomainCd, codeGroupNm, forSystemYn, settableYn);
    }

    /**
     * 공용코드그룹 상세 조회
     * 
     * @param codeGroupCd
     *            코드그룹
     * @return 코드그룹
     * @throws Exception
     */
    public CodeGroup getCodeGroup(String codeGroupCd) throws Exception {
        return this.codeGroupMapper.getCodeGroup(codeGroupCd);
    }

    /**
     * 코드그룹 신규 등록
     * 
     * @param codeGroup
     *            코드그룹
     * @return 생성행수
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "ComCodeSelectCashe", "ComCodeAttrSelectCashe" }, allEntries = true)
    public int createCodeGroup(CodeGroup codeGroup) throws Exception {
        return this.codeGroupMapper.createCodeGroup(codeGroup);
    }

    /**
     * 코드그룹 수정
     * 
     * @param codeGroup
     *            코드그룹
     * @return 수정행수
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "ComCodeSelectCashe", "ComCodeAttrSelectCashe" }, allEntries = true)
    public int updateCodeGroup(CodeGroup codeGroup) throws Exception {
        return this.codeGroupMapper.updateCodeGroup(codeGroup);
    }
}
