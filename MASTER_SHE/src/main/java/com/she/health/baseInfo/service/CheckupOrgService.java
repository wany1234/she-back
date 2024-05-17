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
package com.she.health.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.health.baseInfo.mapper.CheckupOrgMapper;
import com.she.health.model.CheckupOrg;

/**
 * 건강검진기관 기능정의
 *
 */
@Service
public class CheckupOrgService {
    @Autowired
    private CheckupOrgMapper checkupOrgMapper;

    /**
     * 건강검진기관 조회
     *
     * @param heaCheckupOrgNo
     *            사용여부
     * @return 건강검진기관 목록
     * @throws Exception
     */
    public List<CheckupOrg> getCheckupOrgs(String useYn, String heaCheckupOrgNm) throws Exception {
        return checkupOrgMapper.getCheckupOrgs(useYn, heaCheckupOrgNm);
    }

    /**
     * 건강검진기관 상세 조회
     *
     * @param heaCheckupOrgNo
     * @return 건강검진기관
     * @throws Exception
     */
    public CheckupOrg getCheckupOrg(String heaCheckupOrgNo) throws Exception {
        return checkupOrgMapper.getCheckupOrg(heaCheckupOrgNo);
    }

    /**
     * 건강검진기관 생성
     *
     * @param checkOrg
     *            건강검진기관
     * @return 변경 행 수
     * @throws Exception
     */
    public int createCheckupOrg(CheckupOrg checkupOrg) throws Exception {
        return checkupOrgMapper.createCheckupOrg(checkupOrg);
    }

    /**
     * 건강검진기관 수정
     *
     * @param checkOrg
     *            건강검진기관
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateCheckupOrg(CheckupOrg checkupOrg) throws Exception {
        return checkupOrgMapper.updateCheckupOrg(checkupOrg);
    }

}
