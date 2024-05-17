package com.she.env.tms.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.tms.baseInfo.mapper.TmsStationItemMapper;
import com.she.env.tms.model.TmsStationItem;

@Service
public class TmsStationItemService {
    @Autowired
    private TmsStationItemMapper tmsStationItemMapper;

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
    public List<TmsStationItem> getTmsStationItems(String plantCd, String tmsType, String itemName) throws Exception {
        return tmsStationItemMapper.getTmsStationItems(plantCd, tmsType, itemName);
    }

    /**
     * TMS 측정소항목 상세 조회
     * 
     * @param stationItemCode
     *            TMS 측정소항목코드
     * @return TMS 측정소항목
     * @throws Exception
     */
    public TmsStationItem getTmsStationItem(String stationItemCode) throws Exception {
        return this.tmsStationItemMapper.getTmsStationItem(stationItemCode);
    }

    /**
     * TMS 측정소항목 신규등록
     * 
     * @param TmsStationItem
     *            TMS 측정소항목
     * @return TMS 측정소항목코드
     * @throws Exception
     */
    @Transactional
    public String createTmsStationItem(TmsStationItem tmsStationItem) throws Exception {
        this.tmsStationItemMapper.createTmsStationItem(tmsStationItem);

        return tmsStationItem.getStationItemCode();
    }

    /**
     * TMS 측정소항목 수정
     * 
     * @param TmsStationItem
     *            TMS 측정소항목
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateTmsStationItem(TmsStationItem tmsStationItem) throws Exception {
        return this.tmsStationItemMapper.updateTmsStationItem(tmsStationItem);
    }

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
    public List<HashMap<String, Object>> getCheckTmsStationItem(String stationItemCodeOrign, String stationItemCode) throws Exception {
        return tmsStationItemMapper.getCheckTmsStationItem(stationItemCodeOrign, stationItemCode);
    }

    /**
     * TMS 측정소항목 삭제
     * 
     * @param TmsStationItems
     *            TMS 측정소항목s
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteTmsStationItem(List<TmsStationItem> tmsStationItems) throws Exception {
        int returnVal = 0;
        if (tmsStationItems != null && tmsStationItems.size() > 0) {
            for (TmsStationItem tmsStationItem : tmsStationItems) {
                returnVal += this.tmsStationItemMapper.deleteTmsStationItem(tmsStationItem.getStationItemCode());
            }
        }
        return returnVal;
    }

    /**
     * TMS 측정소항목 삭제 체크
     * 
     * @param stationItemCode
     *            TMS 측정소항목코드
     * @return 체크 값
     * @throws Exception
     */
    public int getDeleteCheckTmsStationItem(String stationItemCode) throws Exception {
        return tmsStationItemMapper.getDeleteCheckTmsStationItem(stationItemCode);
    }

}
