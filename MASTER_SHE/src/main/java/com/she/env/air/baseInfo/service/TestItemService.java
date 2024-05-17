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
package com.she.env.air.baseInfo.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.air.baseInfo.mapper.TestItemMapper;
import com.she.env.air.model.TestItem;

/**
 * 대기 검사항목 기능정의
 *
 */
@Service("EairTestItemService")
public class TestItemService {

    @Autowired
    private TestItemMapper testItemMapper;

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
    public List<TestItem> getTestItems(String useYn, String plantCd, String eairTestItemNm, DefaultParam defaultParam) throws Exception {
        return this.testItemMapper.getTestItems(useYn, plantCd,  eairTestItemNm, defaultParam);
    }

    /**
     * 검사항목 상세 조회
     *
     * @param eairTestItemCd
     *            검사항목코드
     * @return 검사항목
     * @throws Exception
     */
    public TestItem getTestItem(String eairTestItemCd) throws Exception {
        return this.testItemMapper.getTestItem(eairTestItemCd);
    }

    /**
     * 검사항목 신규등록
     * 
     * @param testItem
     *            검사항목
     * @return 검사항목코드
     * @throws Exception
     */
    public String createTestItem(TestItem testItem) throws Exception {
        int result = 0;
        result += this.testItemMapper.createTestItem(testItem);
        return result > 0 ? testItem.getEairTestItemCd() : "";
    }

    /**
     * 검사항목 수정
     * 
     * @param testItem
     *            검사항목
     * @return 수정 행 수
     * @throws Exception
     */
    public String updateTestItem(TestItem testItem) throws Exception {
        int result = 0;
        result += this.testItemMapper.updateTestItem(testItem);
        return result > 0 ? testItem.getEairTestItemCd() : "";
    }

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
    public int checkTestItem(String eairTestItemCd, String eairTestItemNm, String plantCd) throws Exception {
        return this.testItemMapper.checkTestItem(eairTestItemCd, eairTestItemNm, plantCd);
    }
}
