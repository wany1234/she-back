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
package com.she.env.air.baseInfo.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.TestItem;

/**
 * 대기 검사항목 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.baseInfo.mapper.TestItemMapper")
public interface TestItemMapper {

    /**
     * 검사항목 전체 조회
     *
     * @param useYn
     *            사용여부
     * @param plantCd
     *            사업장코드
     * @return 검사항목 목록
     * @throws Exception
     */
    public List<TestItem> getTestItems(@Param("useYn") String useYn, @Param("plantCd") String plantCd,  @Param("eairTestItemNm") String eairTestItemNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 검사항목 상세 조회
     *
     * @param eairTestItemCd
     *            검사항목코드
     * @return 검사항목
     * @throws Exception
     */
    public TestItem getTestItem(@Param("eairTestItemCd") String eairTestItemCd) throws Exception;

    /**
     * 검사항목 신규등록
     *
     * @param testItem
     *            검사항목
     * @return 검사항목코드
     * @throws Exception
     */
    public int createTestItem(TestItem testItem) throws Exception;

    /**
     * 검사항목 수정
     *
     * @param testItem
     *            검사항목
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateTestItem(TestItem testItem) throws Exception;

    /**
     * 검사항목명 중복체크
     *
     * @param eairTestItemCd
     *            검사항목코드
     * @param eairTestItemNm
     *            검사항목명
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkTestItem(@Param("eairTestItemCd") String eairTestItemCd, @Param("eairTestItemNm") String eairTestItemNm, @Param("plantCd") String plantCd) throws Exception;
}
