package com.she.safety.wkod.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.WkodChkItem;

@Mapper
@Repository("com.she.safety.wkod.mapper.WkodChkItemMapper")
public interface WkodChkItemMapper {
    /**
     * 작업허가서 항목 조회
     * 
     * @param plantCd
     *            사업장 코드
     * @param wkodDptyCd
     *            점검부서구분 코드
     * @param chkItemNm
     *            항목명
     * @param wkodKindCds
     *            작업구분
     * @param wkodNo
     *            작업허가서번호
     * @return 작업허가서 항목 목록
     * @throws Exception
     */
    public List<WkodChkItem> getWkodChkItems(@Param("plantCd") String plantCd, @Param("wkodKindCd") String wkodKindCd, @Param("wkodDptyCd") String wkodDptyCd, @Param("chkItemNm") String chkItemNm, @Param("useYn") String useYn, @Param("wkodKindCds") String[] wkodKindCds, @Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 작업허가서 항목 상세 조회
     * 
     * @param chkItemNo
     *            작업허가서 항목 코드
     * @return 작업허가서 항목
     * @throws Exception
     */
    public WkodChkItem getWkodChkItem(@Param("chkItemNo") String chkItemNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업허가서 항목 생성
     * 
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createWkodChkItem(WkodChkItem wkodChkItem) throws Exception;

    /**
     * 작업허가서 항목 수정
     * 
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateWkodChkItem(WkodChkItem wkodChkItem) throws Exception;

    public HashMap<String, Object> getCheckWkodChkItem(@Param("wkodKindCd") String wkodKindCd, @Param("chkItemNm") String chkItemNm, @Param("chkItemNo") int chkItemNo, @Param("plantCd") String plantCd) throws Exception;
}
