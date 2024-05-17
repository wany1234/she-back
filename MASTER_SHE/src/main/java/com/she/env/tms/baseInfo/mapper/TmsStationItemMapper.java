package com.she.env.tms.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.tms.model.TmsStationItem;

@Mapper
@Repository("com.she.env.tms.baseInfo.mapper.TmsStationItemMapper")
public interface TmsStationItemMapper {

    /**
     * TMS 측정소항목 조회
     * 
     * @param plantCd
     *            사업장코드
     * @param type
     *            측정소항목 구분
     * @return TMS 측정소항목 목록
     * @throws Exception
     */
    public List<TmsStationItem> getTmsStationItems(@Param("plantCd") String plantCd, @Param("tmsType") String tmsType, @Param("itemName") String itemName) throws Exception;

    /**
     * TMS 측정소항목 상세 조회
     * 
     * @param stationItemCode
     *            TMS 측정소항목코드
     * @return TMS 측정소항목
     * @throws Exception
     */
    public TmsStationItem getTmsStationItem(@Param("stationItemCode") String stationItemCode) throws Exception;

    /**
     * TMS 측정소항목 신규등록
     * 
     * @param TmsStationItem
     *            TMS 측정소항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createTmsStationItem(TmsStationItem tmsStationItem) throws Exception;

    /**
     * TMS 측정소항목 수정
     * 
     * @param TmsStationItem
     *            TMS 측정소항목
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateTmsStationItem(TmsStationItem tmsStationItem) throws Exception;

    /**
     * TMS 측정소항목 체크
     * 
     * @param stationItemCodeOrign
     *            변경전 TMS 측정소항목코드
     * @param stationItemCode
     *            변경할 TMS 측정소항목코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckTmsStationItem(@Param("stationItemCodeOrign") String stationItemCodeOrign, @Param("stationItemCode") String stationItemCode) throws Exception;

    /**
     * TMS 측정소항목 삭제
     * 
     * @param stationItemCode
     *            TMS 측정소항목코드
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteTmsStationItem(@Param("stationItemCode") String stationItemCode) throws Exception;

    /**
     * TMS 측정소항목 삭제 체크
     * 
     * @param stationItemCode
     *            TMS 측정소항목코드
     * @return 체크 값
     * @throws Exception
     */
    public int getDeleteCheckTmsStationItem(@Param("stationItemCode") String stationItemCode) throws Exception;

}
