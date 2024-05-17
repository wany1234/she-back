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

import com.she.baseInfo.facility.mapper.FacilityTypeMapper;
import com.she.baseInfo.model.FacilityType;

/**
 * 설비유형관리 기능정의
 *
 */
@Service
public class FacilityTypeService {
    @Autowired
    private FacilityTypeMapper facilityTypeMapper;

    /**
     * 설비 유형 List 조회
     * @param safFacilityTypeNm 설비유형명
     * @param psafFacilityTypeCd 상위설비유형코드
     * @param useYn 사용여부
     * @return 설비유형 목록
     * @throws Exception
     */
    public List<FacilityType> getFacilityTypes(String  safFacilityTypeNm, String psafFacilityTypeCd, String useYn) throws Exception {
        return facilityTypeMapper.getFacilityTypes(safFacilityTypeNm, psafFacilityTypeCd, useYn);
    }

    /**
     * 설비유형 상세 조회
     * @param safFacilityTypeCd 설비유형코드
     * @return 설비유형
     * @throws Exception
     */
    public FacilityType getFacilityType(String safFacilityTypeCd) throws Exception {
        return facilityTypeMapper.getFacilityType(safFacilityTypeCd);
    }

    /**
     * 설비 유형 신규
     * @param  FacilityInsType 설비 유형
     * @return  설비 유형 코드
     * @throws Exception
     */
    public String createFacilityType(FacilityType facilityType) throws Exception {
        int resultNo = facilityTypeMapper.createFacilityType(facilityType);
        return resultNo > 0 ? facilityType.getSafFacilityTypeCd() : "";
    }

    /**
     * 설비 유형 수정
     * @param  FacilityInsType 설비 유형
     * @return 설비 유형 코드
     * @throws Exception
     */
    public int updateFacilityType(FacilityType facilityType) throws Exception {
        return facilityTypeMapper.updateFacilityType(facilityType);
    }

    /**
     * 설비유형 중복검사
     * @param safFacilityTypeNm 설비유형명
     * @param safFacilityTypeCd 설비유형코드
     * @return 설비유형 중복검사
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityType(String  safFacilityTypeNm, String safFacilityTypeCd) throws Exception {
        return facilityTypeMapper.getCheckFacilityType(safFacilityTypeNm, safFacilityTypeCd);
    }

}
