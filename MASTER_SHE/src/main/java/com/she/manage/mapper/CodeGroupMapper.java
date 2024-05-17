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
package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.CodeGroup;

/**
 * 코드그룹 맵퍼
 *
 */
// @TestConnMapper
@Mapper
@Repository("com.she.manage.mapper.CodeGroupMapper")
public interface CodeGroupMapper {
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
    public List<CodeGroup> getCodeGroups(@Param("codeDomainCd") String codeDomainCd, @Param("codeGroupNm") String codeGroupNm, @Param("forSystemYn") String forSystemYn, @Param("settableYn") String settableYn) throws Exception;

    /**
     * 공용코드그룹 상세 조회
     * 
     * @param codeGroupCd
     *            코드그룹
     * @return 코드그룹
     * @throws Exception
     */
    public CodeGroup getCodeGroup(@Param("codeGroupCd") String codeGroupCd) throws Exception;

    /**
     * 코드그룹 신규 등록
     * 
     * @param codeGroup
     *            코드그룹
     * @return 생성행수
     * @throws Exception
     */
    public int createCodeGroup(CodeGroup codeGroup) throws Exception;

    /**
     * 코드그룹 수정
     * 
     * @param codeGroup
     *            코드그룹
     * @return 수정행수
     * @throws Exception
     */
    public int updateCodeGroup(CodeGroup codeGroup) throws Exception;

}
