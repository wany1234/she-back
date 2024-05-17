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

import com.she.baseInfo.model.SafFacilityMst;

@Mapper
@Repository("com.she.baseInfo.safFacility.mapper.SafFacilityMstMapper")
public interface SafFacilityMstMapper {

    /**
     * 시설 리스트 조회
     * 
     * @return 시설 리스트
     * @throws Exception
     */
    public List<SafFacilityMst> getSafFacilityMsts(@Param("plantCd") String plantCd,
            @Param("comFacilityTypeCd") String comFacilityTypeCd, @Param("facilityCd") String facilityCd,
            @Param("facilityNm") String facilityNm, @Param("deptCd") String deptCd, @Param("useYn") String useYn)
            throws Exception;

    /**
     * 시설 상세조회
     * 
     * @param facilityCd
     *            시설코드
     * @return 시설
     * @throws Exception
     */
    public SafFacilityMst getSafFacilityMst(@Param("facilityCd") String facilityCd) throws Exception;

    /**
     * 시설 신규등록
     * 
     * @param safFacilityMst
     *            시설
     * @return 등록 행 수
     * @throws Exception
     */
    public int createSafFacilityMst(SafFacilityMst safFacilityMst) throws Exception;

    /**
     * 시설 수정
     * 
     * @param safFacilityMst
     *            시설
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateSafFacilityMst(SafFacilityMst safFacilityMst) throws Exception;
}