package com.she.env.tms.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.tms.baseInfo.mapper.TmsItemMapper;
import com.she.env.tms.model.TmsItem;

@Service
public class TmsItemService {
    @Autowired
    private TmsItemMapper tmsItemMapper;

    /**
     * TMS 측정항목 조회
     * 
     * @return TMS 측정항목 목록
     * @throws Exception
     */
    public List<TmsItem> getTmsItems(String tmsType, /* String isComm, */ String itemName) throws Exception {
        return tmsItemMapper.getTmsItems(tmsType, /* isComm, */ itemName);
    }

    /**
     * TMS 측정항목 상세 조회
     * 
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 측정항목
     * @throws Exception
     */
    public TmsItem getTmsItem(String itemCode) throws Exception {
        return this.tmsItemMapper.getTmsItem(itemCode);
    }

    /**
     * TMS 측정항목 신규등록
     * 
     * @param DrugInout
     *            TMS 측정항목
     * @return TMS 측정항목코드
     * @throws Exception
     */
    @Transactional
    public String createTmsItem(TmsItem tmsItem) throws Exception {
        this.tmsItemMapper.createTmsItem(tmsItem);

        return tmsItem.getItemCode();
    }

    /**
     * TMS 측정항목 수정
     * 
     * @param DrugInout
     *            TMS 측정항목
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateTmsItem(TmsItem tmsItem) throws Exception {
        return this.tmsItemMapper.updateTmsItem(tmsItem);
    }

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
    public List<HashMap<String, Object>> getCheckTmsItem(String itemName, String itemCodeOrign, String itemCode) throws Exception {
        return tmsItemMapper.getCheckTmsItem(itemName, itemCodeOrign, itemCode);
    }

    /**
     * TMS 측정항목 삭제
     * 
     * @param TmsItems
     *            TMS 측정항목s
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteTmsItem(List<TmsItem> tmsItems) throws Exception {
        int returnVal = 0;
        if (tmsItems != null && tmsItems.size() > 0) {
            for (TmsItem tmsItem : tmsItems) {
                returnVal += this.tmsItemMapper.deleteTmsItem(tmsItem.getItemCode());
            }
        }
        return returnVal;
    }

    /**
     * TMS 측정항목명 삭제 체크
     * 
     * @param itemCode
     *            TMS 측정항목코드
     * @return 체크 값
     * @throws Exception
     */
    public int getDeleteCheckTmsItem(String itemCode) throws Exception {
        return tmsItemMapper.getDeleteCheckTmsItem(itemCode);
    }

}
