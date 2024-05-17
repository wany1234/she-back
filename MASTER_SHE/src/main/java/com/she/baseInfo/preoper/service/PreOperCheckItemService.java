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
package com.she.baseInfo.preoper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.baseInfo.model.FacilityCheckItem;
import com.she.baseInfo.preoper.mapper.PreOperCheckItemMapper;
import com.she.common.model.DefaultParam;

/**
 * 설비유형별 점검항목 기능정의
 *
 */
@Service
public class PreOperCheckItemService {

    @Autowired
    private PreOperCheckItemMapper preOperCheckItemMapper;

    /**
     * 설비유형별 점검항목 List 조회
     *
     * @param plantCd
     *            사업장코드
     * @param safFacilityTypeCd
     *            설비유형코드
     * @param safCheckTypeCd
     *            설비점검종류코드
     * @param useYn
     *            사용여부
     * @return 설비유형별 점검항목 List
     * @throws Exception
     */
    public List<FacilityCheckItem> getFacilityCheckItems(String plantCd, String safCheckTypeCd, String safFacilityTypeCd, String useYn, String dataLvlCd, DefaultParam defaultParam) throws Exception {
        return preOperCheckItemMapper.getFacilityCheckItems(plantCd, safCheckTypeCd, safFacilityTypeCd, useYn, dataLvlCd, defaultParam);
    }

    /**
     * 설비유형별 점검항목 상세 조회
     *
     * @param safFacilityCheckItemNo(설비점검항목번호)
     * @return 설비유형별 점검항목
     * @throws Exception
     */
    public FacilityCheckItem getFacilityCheckItem(int safFacilityCheckItemNo) throws Exception {
        return preOperCheckItemMapper.getFacilityCheckItem(safFacilityCheckItemNo);
    }

    /**
     * 설비유형별 점검항목 생성
     *
     * @param facilityCheckItem
     *            설비유형별 점검항목
     * @return 설비유형별 점검 항목 번호 key
     * @throws Exception
     */
    public int createFacilityCheckItem(FacilityCheckItem facilityCheckItem) throws Exception {
        return preOperCheckItemMapper.createFacilityCheckItem(facilityCheckItem);
    }

    /**
     * 설비유형별 점검항목 수정
     *
     * @param facilityCheckItem
     *            설비유형별 점검항목
     * @return 설비유형별 점검 항목 번호 key
     * @throws Exception
     */
    public int updateFacilityCheckItem(FacilityCheckItem facilityCheckItem) throws Exception {
        return preOperCheckItemMapper.updateFacilityCheckItem(facilityCheckItem);
    }

    /**
     * 설비유형별 점검항목명 중복 체크
     *
     * @param safFacilityCheckItemNo
     *            설비점검항목번호
     * @param safFacilityCheckNm
     *            설비점검항목명
     * @return 설비유형별 점검항목명 중복 체크값
     * @throws Exception
     */
    public int checkFacilityCheckItem(int safFacilityCheckItemNo, String safFacilityCheckNm, String plantCd, String safCheckTypeCd) throws Exception {
        return preOperCheckItemMapper.checkFacilityCheckItem(safFacilityCheckItemNo, safFacilityCheckNm, plantCd, safCheckTypeCd);
    }

    public List<FacilityCheckItem> getDataCategoryItems(String dataLvlCd, String safCheckTypeCd, String pSafFacilityCheckNm, String useYn, String plantCd) throws Exception {
        return preOperCheckItemMapper.getDataCategoryItems(dataLvlCd, safCheckTypeCd, pSafFacilityCheckNm, useYn, plantCd);
    }

}
