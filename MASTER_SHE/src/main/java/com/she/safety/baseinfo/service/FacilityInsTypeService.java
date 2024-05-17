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

import com.she.safety.baseinfo.mapper.FacilityInsTypeMapper;
import com.she.safety.model.FacilityInsType;

/**
 * 안전점검종류 기능정의
 *
 */
@Service
public class FacilityInsTypeService {

    @Autowired
    private FacilityInsTypeMapper facilityInsTypeMapper;

    /**
     * 시설유형 리스트 조회
     *
     * @param facilityTypeNm
     *            사업장코드
     * @return 시설유형 목록
     * @throws Exception
     */
    public List<FacilityInsType> getFacilityTypeList(String facilityTypeNm) throws Exception {
        return facilityInsTypeMapper.getFacilityTypeList(facilityTypeNm);
    }

    /**
     * 시설유형 상세 조회
     *
     * @param facilityTypeCd
     *            시설유형코드
     * @return 시설유형
     * @throws Exception
     */
    public FacilityInsType getFacilityType(String facilityTypeCd) throws Exception {
        return facilityInsTypeMapper.getFacilityType(facilityTypeCd);
    }

    /**
     * 시설유형 등록
     *
     * @param FacilityInsType
     *            시설유형
     * @return 시설유형코드
     * @throws Exception
     */
    public String createFacilityInsType(FacilityInsType facilityInsType) throws Exception {
        this.facilityInsTypeMapper.createFacilityInsType(facilityInsType);
        return facilityInsType.getFacilityTypeCd();
    }

    /**
     * 시설유형 수정
     *
     * @param FacilityInsType
     *            시설유형
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateFacilityInsType(FacilityInsType facilityInsType) throws Exception {
        return facilityInsTypeMapper.updateFacilityInsType(facilityInsType);
    }

    /**
     * 시설유형코드 체크
     *
     * @param facilityTypeCd
     *            시설유형코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalVendorMaster(String facilityTypeCd) throws Exception {
        return facilityInsTypeMapper.getCheckFacilityInsType(facilityTypeCd);
    }
}
