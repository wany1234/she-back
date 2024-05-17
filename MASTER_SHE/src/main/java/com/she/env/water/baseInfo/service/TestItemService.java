package com.she.env.water.baseInfo.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.baseInfo.mapper.TestItemMapper;
import com.she.env.water.model.TestItem;

@Service("ewtrTestItemService")
public class TestItemService {
    @Autowired
    private TestItemMapper testItemMapper;

    /**
     * 수질 검사항목 조회
     * 
     * @param useYn
     *            사용여부
     * @return 수질 검사항목 목록
     * @throws Exception
     *             예외
     */
    public List<TestItem> getTestItems(String useYn, String plantCd, String ewtrTestItemNm, DefaultParam defaultParam) throws Exception {
        return testItemMapper.getTestItems(useYn, plantCd, ewtrTestItemNm, defaultParam);
    }

    /**
     * 수질 검사항목 상세조회
     * 
     * @param ewtrTestItemCd
     *            수질 검사항목코드
     * @return TestItem 수질 검사항목
     * @throws Exception
     *             예외
     */
    public TestItem getTestItem(String ewtrTestItemCd) throws Exception {
        return testItemMapper.getTestItem(ewtrTestItemCd);
    }
    
    /**
     * 수질 검사항목 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getTestItemCheck(String plantCd, String ewtrTestItemNm, int ewtrTestItemCd) throws Exception {
        return testItemMapper.getTestItemCheck(plantCd, ewtrTestItemNm, ewtrTestItemCd);
    }
    
    /**
     * 수질 검사항목 신규등록
     * 
     * @param TestItem
     *            수질 검사항목
     * @return ewtrTestItemCd 수질 검사항목코드
     * @throws Exception
     *             예외
     */
    public String createTestItem(TestItem testItem) throws Exception {
        this.testItemMapper.createTestItem(testItem);
        return testItem.getEwtrTestItemCd();
    }

    /**
     * 수질 검사항목 수정
     * 
     * @param TestItem
     *            수질 검사항목
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateTestItem(TestItem testItem) throws Exception {
        return testItemMapper.updateTestItem(testItem);
    }
}
