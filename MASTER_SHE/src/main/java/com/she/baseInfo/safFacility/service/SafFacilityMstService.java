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
import org.springframework.transaction.annotation.Transactional;

import com.she.baseInfo.model.SafFacilityMst;
import com.she.baseInfo.safFacility.mapper.SafFacilityMstMapper;

/**
 * 시설
 */
@Service
public class SafFacilityMstService {
    @Autowired
    private SafFacilityMstMapper safFacilityMstMapper;

    /**
     * 시설 리스트 조회
     * 
     * @return 시설 유형 리스트
     * @throws Exception
     */
    public List<SafFacilityMst> getSafFacilityMsts(String plantCd, String comFacilityTypeCd, String facilityCd,
            String facilityNm, String deptCd, String useYn, String flag) throws Exception {
        return safFacilityMstMapper.getSafFacilityMsts(plantCd, comFacilityTypeCd, facilityCd, facilityNm, deptCd,
                useYn);
    }

    /**
     * 시설 상세조회
     * 
     * @param facilityCd
     *            시설코드
     * @return 시설
     * @throws Exception
     */
    public SafFacilityMst getSafFacilityMst(String facilityCd) throws Exception {
        return safFacilityMstMapper.getSafFacilityMst(facilityCd);
    }

    /**
     * 시설 신규등록
     * 
     * @param SafFacilityMst
     *            시설
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public String createSafFacilityMst(SafFacilityMst safFacilityMst) throws Exception {
        safFacilityMstMapper.createSafFacilityMst(safFacilityMst);
        return safFacilityMst.getFacilityCd();
    }

    /**
     * 시설 수정
     * 
     * @param SafFacilityMst
     *            시설
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateSafFacilityMst(SafFacilityMst safFacilityMst) throws Exception {
        return safFacilityMstMapper.updateSafFacilityMst(safFacilityMst);
    }
}