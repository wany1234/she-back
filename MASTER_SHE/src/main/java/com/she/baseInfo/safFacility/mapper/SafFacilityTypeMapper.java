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
package com.she.baseInfo.safFacility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.SafFacilityType;

@Mapper
@Repository("com.she.baseInfo.safFacility.mapper.SafFacilityTypeMapper")
public interface SafFacilityTypeMapper {

    /**
     * 시설유형 리스트 조회
     *
     * @return 시설유형 리스트
     * @throws Exception
     */
    public List<SafFacilityType> getSafFacilityTypes(@Param("comFacilityCd") String comFacilityCd, @Param("comFacilityNm") String comFacilityNm, @Param("useYn") String useYn) throws Exception;
}