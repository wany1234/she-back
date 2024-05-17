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
package com.she.vendor.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.vendor.model.ChemicalVendorMaster;

@Mapper
@Repository("com.she.vendor.baseInfo.mapper.ChemicalVendorMasterMapper")
public interface ChemicalVendorMasterMapper {

    /**
     * 업체 조회
     * 
     * @param originCd
     *            원산지
     * @param vendorNm
     *            업체명
     * @param vendorTypeCd
     *            업체분류
     * @param useYn
     *            사용여부
     * @return 업체 목록
     * @throws Exception
     */
    public List<ChemicalVendorMaster> getChemicalVendorMasters(@Param("originCd") String originCd, @Param("vendorNm") String vendorNm, @Param("vendorTypeCd") String vendorTypeCd, @Param("useYn") String useYn) throws Exception;

}
