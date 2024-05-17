/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.safety.wkod.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.model.WkodChkItem;
import com.she.safety.wkod.mapper.WkodChkItemMapper;
import com.she.utils.ConstVal;

/**
 * 건강검진기관 기능정의
 *
 */
@Service
public class WkodChkItemService {

    @Autowired
    private WkodChkItemMapper wkodChkItemMapper;

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
    public List<WkodChkItem> getWkodChkItems(String plantCd, String wkodKindCd, String wkodDptyCd, String chkItemNm, String useYn, String[] wkodKindCds, int wkodNo, DefaultParam defaultParam) throws Exception {
        return wkodChkItemMapper.getWkodChkItems(plantCd, wkodKindCd, wkodDptyCd, chkItemNm, useYn, wkodKindCds, wkodNo, defaultParam);
    }

    /**
     * 작업허가서 항목 상세 조회
     * 
     * @param chkItemNo
     *            작업허가서 항목 코드
     * @return 작업허가서 항목
     * @throws Exception
     */
    public WkodChkItem getWkodChkItem(String chkItemNo, DefaultParam defaultParam) throws Exception {
        return wkodChkItemMapper.getWkodChkItem(chkItemNo, defaultParam);
    }

    /**
     * 작업허가서 항목 생성
     * 
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public String createWkodChkItem(WkodChkItem wkodChkItem) throws Exception {
        this.setKindDpty(wkodChkItem);
        wkodChkItemMapper.createWkodChkItem(wkodChkItem);
        return wkodChkItem.getChkItemNo();
    }

    /**
     * 작업허가서 항목 수정
     * 
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateWkodChkItem(WkodChkItem wkodChkItem) throws Exception {
        this.setKindDpty(wkodChkItem);
        return wkodChkItemMapper.updateWkodChkItem(wkodChkItem);
    }

    public HashMap<String, Object> getCheckWkodChkItem(String wkodKindCd, String chkItemNm, int chkItemNo, String plantCd) throws Exception {
        return wkodChkItemMapper.getCheckWkodChkItem(wkodKindCd, chkItemNm, chkItemNo, plantCd);
    }

    public void setKindDpty(WkodChkItem wkodChkItem) throws Exception {
        /**
         * 종류 점검확인사항 구분 값 정의
         * 
         * W997 : 종류가 빈 값이며 점검확인사항이 WDT03
         * 
         * W998 : 종류가 WKKDNS 이며 점검확인사항이 NULL
         * 
         * W999 : 종류가 WKKDSS 이며 점검확인사항이 NULL
         */
        if ("W997".equals(wkodChkItem.getWkodKindCd())) {
            wkodChkItem.setWkodKindCd("");
            wkodChkItem.setWkodDptyCd(ConstVal.SAF_WKOD_ITEM_DPTY_COMM);
        } else if ("W998".equals(wkodChkItem.getWkodKindCd())) {
            wkodChkItem.setWkodKindCd(ConstVal.SAF_WKOD_ITEM_KIND_NOMAL);
            wkodChkItem.setWkodDptyCd(null);
        } else if ("W999".equals(wkodChkItem.getWkodKindCd())) {
            wkodChkItem.setWkodKindCd(ConstVal.SAF_WKOD_ITEM_KIND_FIREARM);
            wkodChkItem.setWkodDptyCd(null);
        } else {
            wkodChkItem.setWkodDptyCd(null);
        }
    }
}
