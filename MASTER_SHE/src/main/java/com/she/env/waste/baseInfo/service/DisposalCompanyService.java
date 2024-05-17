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
package com.she.env.waste.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.waste.baseInfo.mapper.DisposalCompanyMapper;
import com.she.env.waste.model.DisposalCompany;

/**
 * 폐기물 처리업체 기능정의
 *
 */
@Service("EwstDisposalCompanyService")
public class DisposalCompanyService {
    @Autowired
    private DisposalCompanyMapper disposalCompanyMapper;

    /**
     * 폐기물 처리업체 전체 조회
     *
     * @param useYn
     *            사용여부
     * @param dispoYn
     *            처리업체여부
     * @param freightYn
     *            운반업체여부
     * @param ewstDispoCoNm
     *            업체명
     * @return 폐기물 처리업체 목록
     * @throws Exception
     */
    public List<DisposalCompany> getDisposalCompanies(String useYn, String vendorTypeCd, String ewstDispoCoNm) throws Exception {
        return this.disposalCompanyMapper.getDisposalCompanies(useYn, vendorTypeCd, ewstDispoCoNm);
    }
}
