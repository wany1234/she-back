package com.she.env.tms.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.tms.baseInfo.mapper.TmsStationMapper;
import com.she.env.tms.model.OutletFacility;
import com.she.env.tms.model.TmsStation;

@Service
public class TmsStationService {
    @Autowired
    private TmsStationMapper tmsStationMapper;

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
    public List<TmsStation> getTmsStations(String plantCd, String tmsType, String stationName) throws Exception {
        return tmsStationMapper.getTmsStations(plantCd, tmsType, stationName);
    }

    /**
     * TMS 측정소 상세 조회
     * 
     * @param stationCode
     *            TMS 측정소코드
     * @return TMS 측정소
     * @throws Exception
     */
    public TmsStation getTmsStation(String stationCode) throws Exception {
        return this.tmsStationMapper.getTmsStation(stationCode);
    }

    /**
     * TMS 측정소 신규등록
     * 
     * @param TmsStation
     *            TMS 측정소
     * @return TMS 측정소코드
     * @throws Exception
     */
    @Transactional
    public String createTmsStation(TmsStation tmsStation) throws Exception {
        this.tmsStationMapper.createTmsStation(tmsStation);

        return tmsStation.getStationCode();
    }

    /**
     * TMS 측정소 수정
     * 
     * @param TmsStation
     *            TMS 측정소
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateTmsStation(TmsStation tmsStation) throws Exception {
        return this.tmsStationMapper.updateTmsStation(tmsStation);
    }

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
    public List<HashMap<String, Object>> getCheckTmsStation(String stationName, String stationCodeOrign, String stationCode, String plantCd) throws Exception {
        return tmsStationMapper.getCheckTmsStation(stationName, stationCodeOrign, stationCode, plantCd);
    }

    /**
     * TMS 측정소 삭제
     * 
     * @param tmsStations
     *            TMS 측정소s
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteTmsStation(List<TmsStation> tmsStations) throws Exception {
        int returnVal = 0;
        if (tmsStations != null && tmsStations.size() > 0) {
            for (TmsStation tmsStation : tmsStations) {
                returnVal += this.tmsStationMapper.deleteTmsStation(tmsStation.getStationCode());
            }
        }
        return returnVal;
    }

    /**
     * TMS 측정소명 삭제 체크
     * 
     * @param stationCode
     *            TMS 측정소코드
     * @return 체크 값
     * @throws Exception
     */
    public int getDeleteCheckTmsStation(String stationCode) throws Exception {
        return tmsStationMapper.getDeleteCheckTmsStation(stationCode);
    }

    public List<OutletFacility> getOuletFacilitys(String plantCd, String mgDeptCd) throws Exception {
        return tmsStationMapper.getOuletFacilitys(plantCd, mgDeptCd);
    }

}
