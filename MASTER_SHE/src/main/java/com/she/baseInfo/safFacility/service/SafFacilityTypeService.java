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
package com.she.baseInfo.safFacility.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.baseInfo.model.SafFacilityType;
import com.she.baseInfo.safFacility.mapper.SafFacilityTypeMapper;

/**
 * 시설유형
 */
@Service
public class SafFacilityTypeService {
    @Autowired
    private SafFacilityTypeMapper safFacilityTypeMapper;

    /**
     * 시설유형 리스트 조회
     *
     * @return 시설 유형 리스트
     * @throws Exception
     */
    public List<SafFacilityType> getSafFacilityTypes(String comFacilityCd, String comFacilityNm, String useYn) throws Exception {
        return safFacilityTypeMapper.getSafFacilityTypes(comFacilityCd, comFacilityNm, useYn);
    }
}