package com.she.env.tms.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.tms.model.OutletFacility;
import com.she.env.tms.model.TmsStation;

@Mapper
@Repository("com.she.env.tms.baseInfo.mapper.TmsStationMapper")
public interface TmsStationMapper {

    /**
     * TMS 측정소 조회
     * 
     * @param plantCd
     *            사업장코드
     * @param type
     *            측정소 구분
     * @return TMS 측정소 목록
     * @throws Exception
     */
    public List<TmsStation> getTmsStations(@Param("plantCd") String plantCd, @Param("tmsType") String tmsType, @Param("stationName") String stationName) throws Exception;

    /**
     * TMS 측정소 상세 조회
     * 
     * @param stationCode
     *            TMS 측정소코드
     * @return TMS 측정소
     * @throws Exception
     */
    public TmsStation getTmsStation(@Param("stationCode") String stationCode) throws Exception;

    /**
     * TMS 측정소 신규등록
     * 
     * @param TmsStation
     *            TMS 측정소
     * @return 등록 행 수
     * @throws Exception
     */
    public int createTmsStation(TmsStation tmsStation) throws Exception;

    /**
     * TMS 측정소 수정
     * 
     * @param TmsStation
     *            TMS 측정소
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateTmsStation(TmsStation tmsStation) throws Exception;

    /**
     * TMS 측정소명 체크
     * 
     * @param stationName
     *            TMS 측정소명 (KOR)
     * @param stationCodeOrgin
     *            TMS 측정소코드 자기자신거
     * @param stationCode
     *            TMS 측정소코드 바꿔 볼려고 하는거
     * @param plantCd
     *            사업장코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckTmsStation(@Param("stationName") String stationName, @Param("stationCodeOrign") String stationCodeOrign, @Param("stationCode") String stationCode, @Param("plantCd") String plantCd) throws Exception;

    /**
     * TMS 측정소 삭제
     * 
     * @param stationCode
     *            TMS 측정소코드
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteTmsStation(@Param("stationCode") String stationCode) throws Exception;

    /**
     * TMS 측정소명 삭제 체크
     * 
     * @param stationCode
     *            TMS 측정소코드
     * @return 체크 값
     * @throws Exception
     */
    public int getDeleteCheckTmsStation(@Param("stationCode") String stationCode) throws Exception;

    public List<OutletFacility> getOuletFacilitys(@Param("plantCd") String plantCd, @Param("mgDeptCd") String mgDeptCd) throws Exception;
    
    public List<OutletFacility> getcleanCenters(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;
 

}
