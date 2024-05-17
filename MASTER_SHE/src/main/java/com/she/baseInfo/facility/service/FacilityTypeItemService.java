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
package com.she.baseInfo.facility.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.baseInfo.facility.mapper.FacilityTypeItemMapper;
import com.she.baseInfo.model.FacilityTypeItem;

/**
 * 설비유형별 관리항목 기능정의
 *
 */
@Service
public class FacilityTypeItemService {
    @Autowired
    private FacilityTypeItemMapper facilityTypeItemMapper;

    /**
     * 설비유형별 관리 항목 List 조회
     * 
     * @param parameter
     *            (안전설비유형코드, 안전설비유형항목명,사용여부 )
     * @return 설비유형별 관리 항목 List
     * @throws Exception
     */
    public List<FacilityTypeItem> getFacilityTypeItems(String safFacilityTypeCd, String safFacilityTypeItemNm,
            String useYn) throws Exception {
        return facilityTypeItemMapper.getFacilityTypeItems(safFacilityTypeCd, safFacilityTypeItemNm, useYn);
    }

    /**
     * 설비유형별 관리 항목 상세 조회
     * 
     * @param parameter
     *            (안전설비유형항목번호)
     * @return 설비유형별 관리 항목
     * @throws Exception
     */
    public FacilityTypeItem getFacilityTypeItem(int safCheckItemNo) throws Exception {
        return facilityTypeItemMapper.getFacilityTypeItem(safCheckItemNo);
    }

    /**
     * 설비 유형별 관리 항목 신규
     * 
     * @param FacilityTypeItem
     *            설비 유형별 관리 항목
     * @return 설비 유형별 관리 항목 key 값
     * @throws Exception
     */
    public int createFacilityTypeItem(FacilityTypeItem facilityTypeItem) throws Exception {
        return facilityTypeItemMapper.createFacilityTypeItem(facilityTypeItem);
    }

    /**
     * 설비 유형별 관리 항목 수정
     * 
     * @param FacilityTypeItem
     *            설비 유형별 관리 항목
     * @return 설비 유형별 관리 항목 key 값
     * @throws Exception
     */
    public int updateFacilityTypeItem(FacilityTypeItem facilityTypeItem) throws Exception {
        return facilityTypeItemMapper.updateFacilityTypeItem(facilityTypeItem);
    }

    /**
     * 설비유형별 상세항목 중복검사
     * 
     * @param parameter
     *            (설비유형관리항목번호, 안전설비유형항목명 )
     * @return 설비유형별 상세항목 중복검사
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityTypeItem(int safFacilityTypeItemNo,
            String safFacilityTypeItemNm, String safFacilityTypeCd) throws Exception {
        return facilityTypeItemMapper.getCheckFacilityTypeItem(safFacilityTypeItemNo, safFacilityTypeItemNm,
                safFacilityTypeCd);
    }

}
