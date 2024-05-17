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
package com.she.env.waste.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.waste.model.DisposalCompany;

/**
 * 폐기물 처리업체 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.baseInfo.mapper.DisposalCompanyMapper")
public interface DisposalCompanyMapper {

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
    public List<DisposalCompany> getDisposalCompanies(@Param("useYn") String useYn, @Param("vendorTypeCd") String vendorTypeCd, @Param("ewstDispoCoNm") String ewstDispoCoNm) throws Exception;
}
