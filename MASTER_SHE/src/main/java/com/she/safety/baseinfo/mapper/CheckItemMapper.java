package com.she.safety.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.CheckItem;

@Mapper
@Repository("com.she.safety.check.mapper.CheckItemMapper")
public interface CheckItemMapper {

    /*
     * 안전 점검 종류 List 조회
     * 
     * @param parameter (사업장코드, 점검종류 번호, 점검종류명, 사용여부 )
     * 
     * @return 안전점검항목 List
     * 
     * @throws Exception
     */
    public List<CheckItem> getCheckItemList(@Param("plantCd") String plantCd, @Param("safCheckKindNo") int safCheckKindNo, @Param("safCheckTypeNm") String safCheckTypeNm, @Param("useYn") String useYn, @Param("chngKind") String chngKind, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /*
     * 안전 점검 항목 상세 조회
     * 
     * @param parameter (점검항목번호)
     * 
     * @return 안전점검항목
     * 
     * @throws Exception
     */
    public CheckItem getCheckItem(@Param("safCheckItemNo") int safCheckItemNo) throws Exception;

    /*
     * 안전 점검 항목 신규
     * 
     * @param CheckItem 안전 점검 항목
     * 
     * @return 변경 행 수
     * 
     * @throws Exception
     */
    public int createCheckItem(CheckItem checkItem) throws Exception;

    /*
     * 안전 점검 항목 수정
     * 
     * @param CheckItem 안전 점검 항목
     * 
     * @return 변경 행 수
     * 
     * @throws Exception
     */
    public int updateCheckItem(CheckItem checkItem) throws Exception;

    /*
     * 안전 점검명 중복 검사
     * 
     * @param parameter (점검항목명)
     * 
     * @return 안전점검항목 List
     * 
     * @throws Exception
     */
    public List<CheckItem> checkCheckItem(CheckItem checkItem) throws Exception;

}
