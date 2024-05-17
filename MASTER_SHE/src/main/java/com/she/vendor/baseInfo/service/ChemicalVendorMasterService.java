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
package com.she.vendor.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.vendor.baseInfo.mapper.ChemicalVendorMasterMapper;
import com.she.vendor.model.ChemicalVendorMaster;

@Service
public class ChemicalVendorMasterService {
    @Autowired
    private ChemicalVendorMasterMapper chemicalVendorMasterMapper;

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
    public List<ChemicalVendorMaster> getChemicalVendorMasters(String originCd, String vendorNm, String vendorTypeCd, String useYn) throws Exception {
        return chemicalVendorMasterMapper.getChemicalVendorMasters(originCd, vendorNm, vendorTypeCd, useYn);
    }

}
