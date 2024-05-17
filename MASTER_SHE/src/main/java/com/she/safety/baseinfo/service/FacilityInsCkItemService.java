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
package com.she.safety.baseinfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.safety.baseinfo.mapper.FacilityInsCkItemMapper;
import com.she.safety.model.FacilityTypeCheckItem;

/**
 * 안전점검종류 기능정의
 *
 */
@Service
public class FacilityInsCkItemService {

    @Autowired
    private FacilityInsCkItemMapper facilityInsCheckItemMapper;

    /**
     * 시설유형별 점검항목 조회
     *
     * @param plantCd
     *            사업장코드
     * @param facilityTypeCd
     *            시설유형코드
     * @param facilityCheckCd
     *            시설점검종류코드
     * @return 시설유형별 점검항목 목록
     * @throws Exception
     */
    public List<FacilityTypeCheckItem> getFacilityTypeCkItemList(String plantCd, String facilityTypeCd, String facilityCheckCd, String facilityCheckItemNm) throws Exception {
        return facilityInsCheckItemMapper.getFacilityTypeCkItemList(plantCd, facilityTypeCd, facilityCheckCd, facilityCheckItemNm);
    }

    /**
     * 시설유형별 점검항목 상세 조회
     *
     * @param facilityCheckItemNo
     *            시설유형별 점검항목No
     * @return 시설유형별 점검항목
     * @throws Exception
     */
    public FacilityTypeCheckItem getFacilityTypeCkItem(int facilityCheckItemNo) throws Exception {
        return facilityInsCheckItemMapper.getFacilityTypeCkItem(facilityCheckItemNo);
    }

    /**
     * 시설유형별 점검항목 등록
     *
     * @param FacilityTypeCheckItem
     *            시설유형별 점검항목
     * @return 점검항목번호
     * @throws Exception
     */
    public int createFacilityInsType(FacilityTypeCheckItem facilityTypeCheckItem) throws Exception {
        this.facilityInsCheckItemMapper.createFacilityTypeCkItem(facilityTypeCheckItem);
        return facilityTypeCheckItem.getFacilityCheckItemNo();
    }

    /**
     * 시설유형별 점검항목 수정
     *
     * @param FacilityTypeCheckItem
     *            시설유형별 점검항목
     * @return 점검항목번호
     * @throws Exception
     */
    public int updateFacilityInsType(FacilityTypeCheckItem facilityTypeCheckItem) throws Exception {
        facilityInsCheckItemMapper.updateFacilityTypeCkItem(facilityTypeCheckItem);
        return facilityTypeCheckItem.getFacilityCheckItemNo();
    }

    /**
     * 시설유형별 점검항목명 체크
     *
     * @param facilityCheckItemNo
     *            시설유형별점검항목No
     * @param facilityCheckItemNm
     *            시설유형별점검항목명
     * @param facilityTypeCd
     *            시설유형코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityTypeCkItemNm(int facilityCheckItemNo, String facilityCheckItemNm, String facilityTypeCd) throws Exception {
        return facilityInsCheckItemMapper.getCheckFacilityTypeCkItemNm(facilityCheckItemNo, facilityCheckItemNm, facilityTypeCd);
    }
}
