package com.she.env.tms.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.tms.model.TmsItem;

@Mapper
@Repository("com.she.env.tms.baseInfo.mapper.TmsItemMapper")
public interface TmsItemMapper {

    /**
     * TMS 측정항목 조회
     * 
     * @return TMS 측정항목 목록
     * @throws Exception
     */
    public List<TmsItem> getTmsItems(@Param("tmsType") String tmsType,
            /* @Param("isComm") String isComm, */ @Param("itemName") String itemName) throws Exception;

    /**
     * TMS 측정항목 상세 조회
     * 
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 측정항목
     * @throws Exception
     */
    public TmsItem getTmsItem(@Param("itemCode") String itemCode) throws Exception;

    /**
     * TMS 측정항목 신규등록
     * 
     * @param DrugInout
     *            TMS 측정항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createTmsItem(TmsItem tmsItem) throws Exception;

    /**
     * TMS 측정항목 수정
     * 
     * @param DrugInout
     *            TMS 측정항목
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateTmsItem(TmsItem tmsItem) throws Exception;

    /**
     * TMS 측정항목명 체크
     * 
     * @param itemName
     *            TMS 측정항목명 (KOR)
     * @param itemCodeOrign
     *            TMS 측정항목코드 자기자신거
     * @param itemCode
     *            TMS 측정항목코드 바꿔 볼려고 하는거
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckTmsItem(@Param("itemName") String itemName, @Param("itemCodeOrign") String itemCodeOrign, @Param("itemCode") String itemCode) throws Exception;

    /**
     * TMS 측정항목 삭제
     * 
     * @param itemCode
     *            TMS 측정항목코드
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteTmsItem(@Param("itemCode") String itemCode) throws Exception;

    /**
     * TMS 측정항목 삭제 체크
     * 
     * @param itemCode
     *            TMS 측정항목코드
     * @return 체크 값
     * @throws Exception
     */
    public int getDeleteCheckTmsItem(@Param("itemCode") String itemCode) throws Exception;

}
