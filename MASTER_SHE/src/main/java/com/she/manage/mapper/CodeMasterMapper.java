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

import com.she.common.model.DefaultParam;
import com.she.manage.model.CodeMaster;
import com.she.mgt.model.InvestItem;

/**
 * 코드마스터 맵퍼
 *
 */
@Mapper
@Repository("com.she.manage.mapper.CodeMasterMapper")
public interface CodeMasterMapper {

    /**
     * 선택박스에서 사용되는 코드목록 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeMaster> getSelect(@Param("codeGroupCd") String codeGroupCd, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택박스에서 사용되는 코드목록 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeMaster> getSelectAttr(@Param("codeGroupCd") String codeGroupCd, @Param("attr1") String attr1, @Param("attr2") String attr2, @Param("attr3") String attr3, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공용코드 전체 조회
     *
     * @param settableYn
     *            셋팅가능여부
     * @param codeDomainCd
     *            코드도메인
     * @param codeGroupCd
     *            코드그룹
     * @param codeNm
     *            코드명
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeMaster> getCodeMasters(@Param("settableYn") String settableYn, @Param("codeDomainCd") String codeDomainCd, @Param("codeGroupCd") String codeGroupCd, @Param("codeNm") String codeNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 공용코드 상세 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @param code
     *            코드
     * @param forSystemYn
     * @return 코드마스터
     * @throws Exception
     */
    public CodeMaster getCodeMaster(@Param("codeGroupCd") String codeGroupCd, @Param("code") String code, @Param("forSystemYn") String forSystemYn) throws Exception;

    /**
     * 공용코드 상세 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @param code
     *            코드
     * @param forSystemYn
     * @return InvestItem
     * @throws Exception
     */
    public List<InvestItem> getInvestItemsInCodeMaster(@Param("codeGroupCd") String codeGroupCd, @Param("code") String code, @Param("forSystemYn") String forSystemYn) throws Exception;

    /**
     * 공용코드 신규 등록
     *
     * @param codeMaster
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    public int createCodeMaster(CodeMaster codeMaster) throws Exception;

    /**
     * 공용코드 수정
     *
     * @param codeMaster
     *            코드마스터
     * @return 수정행수
     * @throws Exception
     */
    public int updateCodeMaster(CodeMaster codeMaster) throws Exception;

    /**
     * 공용코드 전체 조회
     *
     * @param forSystemYn
     *            시스템용 여부
     * @param settableYn
     *            셋팅가능여부
     * @param codeDomainCd
     *            코드도메인
     * @param codeGroupCd
     *            코드그룹
     * @param codeNm
     *            코드명
     * @param attr1
     * @param attr2
     * @param attr3
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeMaster> getCodeAllMasters(@Param("forSystemYn") String forSystemYn, @Param("settableYn") String settableYn, @Param("codeDomainCd") String codeDomainCd, @Param("codeGroupCd") String codeGroupCd, @Param("codeNm") String codeNm, @Param("attr1") String attr1, @Param("attr2") String attr2, @Param("attr3") String attr3,
            @Param("codeGroupNm") String codeGroupNm) throws Exception;

    public int createInvestItem(InvestItem investItem) throws Exception;

    public int updateInvestItem(InvestItem investItem) throws Exception;

    public int deleteInvestItem(InvestItem investItem) throws Exception;

    public int selectInvestItem(InvestItem investItem) throws Exception;

    public int getDuplicationCheck(String mgtMgInfoItmNo) throws Exception;

    public int selectMiddleItem(@Param("code") String code, @Param("middleCode") String middleCode) throws Exception;

    public int deleteCodeMaster(@Param("code") String code, @Param("middleCode") String middleCode) throws Exception;

}
