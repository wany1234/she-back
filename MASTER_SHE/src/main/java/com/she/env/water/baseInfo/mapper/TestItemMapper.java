package com.she.env.water.baseInfo.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.TestItem;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.TestItemMapper")
public interface TestItemMapper {

    /**
     * 수질 검사항목 조회
     * 
     * @param useYn
     *            사용여부
     * @return 수질 검사항목 목록
     * @throws Exception
     *             예외
     */
    public List<TestItem> getTestItems(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("ewtrTestItemNm") String ewtrTestItemNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 수질 검사항목 상세조회
     * 
     * @param ewtrTestItemCd
     *            수질 검사항목코드
     * @return TestItem 수질 검사항목
     * @throws Exception
     *             예외
     */
    public TestItem getTestItem(@Param("ewtrTestItemCd") String ewtrTestItemCd) throws Exception;

    /**
     * 수질 검사항목 신규등록
     * 
     * @param TestItem
     *            수질 검사항목
     * @return ewtrTestItemCd 수질 검사항목코드
     * @throws Exception
     *             예외
     */
    
    public int createTestItem(TestItem testItem) throws Exception;
    
    /**
     * 수질검사항목 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getTestItemCheck(@Param("plantCd") String plantCd,
            @Param("ewtrTestItemNm") String ewtrTestItemNm, @Param("ewtrTestItemCd") int ewtrTestItemCd) throws Exception;
       
    
    /**
     * 수질 검사항목 수정
     * 
     * @param TestItem
     *            수질 검사항목
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateTestItem(TestItem testItem) throws Exception;
}
