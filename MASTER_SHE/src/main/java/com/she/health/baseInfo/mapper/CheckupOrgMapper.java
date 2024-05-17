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
package com.she.health.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.health.model.CheckupOrg;

@Mapper
@Repository("com.she.health.baseInfo.mapper.CheckupOrgMapper")
public interface CheckupOrgMapper {

    /**
     * 건강검진기관 조회
     *
     * @param heaCheckupOrgNo
     *            사용여부
     * @return 건강검진기관 목록
     * @throws Exception
     */
    public List<CheckupOrg> getCheckupOrgs(@Param("useYn") String useYn,
            @Param("heaCheckupOrgNm") String heaCheckupOrgNm) throws Exception;

    /**
     * 건강검진기관 상세 조회
     *
     * @param heaCheckupOrgNo
     *            건강검진기관번호
     * @return 건강검진기관
     * @throws Exception
     */
    public CheckupOrg getCheckupOrg(@Param("heaCheckupOrgNo") String heaCheckupOrgNo) throws Exception;

    /**
     * 건강검진기관 생성
     *
     * @param checkupOrg
     *            건강검진기관
     * @return 변경 행 수
     * @throws Exception
     */
    public int createCheckupOrg(CheckupOrg checkupOrg) throws Exception;

    /**
     * 건강검진기관 수정
     *
     * @param checkupOrg
     *            건강검진기관
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateCheckupOrg(CheckupOrg checkupOrg) throws Exception;

}
