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
package com.she.mgt.mgtLaw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.model.MgtLawMaking;

@Mapper
@Repository("com.she.mgt.mgtLaw.mapper.SheLawMakingMapper")
public interface SheLawMakingMapper {

    /***
     * 입법예고법규 목록 조회
     *
     * @param isNm
     *            입법예고명
     * @param mgrGovCd
     *            소관부처코드
     * @param stYd
     *            시작일
     * @param edYd
     *            종료일
     * @return 입법예고 목록
     * @throws Exception
     */
    public List<MgtLawMaking> getLawmakingList(@Param("isNm") String isNm, @Param("mgrGovCd") String mgrGovCd,
            @Param("stYd") String stYd, @Param("edYd") String edYd) throws Exception;
}
