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
package com.she.mgt.mgtLaw.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.mgt.model.MgtLawMaking;
import com.she.mgt.mgtLaw.mapper.SheLawMakingMapper;

@Service
public class SheLawMakingService {
    @Autowired
    private SheLawMakingMapper sheLawMakingMapper;

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
    public List<MgtLawMaking> getLawMakingList(String isNm, String mgrGovCd, String stYd, String edYd) throws Exception {
        return sheLawMakingMapper.getLawmakingList(isNm, mgrGovCd, stYd, edYd);
    }
}
