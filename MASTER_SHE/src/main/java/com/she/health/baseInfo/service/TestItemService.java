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
package com.she.health.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.health.baseInfo.mapper.TestItemMapper;
import com.she.health.model.TestItem;

/**
 * 건강검진항목 기능정의
 *
 */
@Service
public class TestItemService {

    @Autowired
    private TestItemMapper testItemMapper;

    /**
     * 건강검진항목 조회
     *
     * @param heaTestClassCd
     *            검사 분류 코드
     * @param heaCheckupOrgNo
     *            건강검진기관
     * @param year
     *            검진년도
     * @param heaCheckupTypeCd
     *            종합건강검진유형
     * @param useYn
     *            사용유무
     * @param heaTestItemNm
     *            검사항목명
     * @return 건강검진항목 목록
     * @throws Exception
     */
    public List<TestItem> getTestItems(String heaTestClassCd, int heaCheckupOrgNo, String year, String heaCheckupTypeCd,
            String useYn, String optionalYn, String heaTestItemNm) throws Exception {
        return testItemMapper.getTestItems(heaTestClassCd, heaCheckupOrgNo, year, heaCheckupTypeCd, useYn, optionalYn,
                heaTestItemNm);
    }

    /**
     * 건강검진항목 상세 조회
     *
     * @param heaTestClassCd
     * @return 건강검진항목
     * @throws Exception
     */
    public TestItem getTestItem(String heaTestItemCd) throws Exception {
        return testItemMapper.getTestItem(heaTestItemCd);
    }

    /**
     * 건강검진항목 생성
     *
     * @param testItem
     *            건강검진항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createTestItem(TestItem testItem) throws Exception {
        return testItemMapper.createTestItem(testItem);
    }

    /**
     * 건강검진항목 수정
     *
     * @param testItem
     *            건강검진항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateTestItem(TestItem testItem) throws Exception {
        return testItemMapper.updateTestItem(testItem);
    }

}
