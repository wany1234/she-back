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
package com.she.env.waste.disporate.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.waste.model.Disporate;
import com.she.env.waste.model.DisporateDeptItem;

/**
 * 폐기물 관리대장 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.operationLog.mapper.DisporateMapper")
public interface DisporateMapper {

    /**
     * 분배율 목록
     * 
     * @param fromDate
     *            시작기간
     * @param toDate
     *            마감기간
     * @param plantCd
     *            사업장코드
     * @param ewstClassCd
     *            폐기물 분류 코드
     * @param ewstWasteNo
     *            폐기물 고유번호
     * @return 부서별 분배율 목록
     * @throws Exception
     */
    public List<Disporate> getDisporates(@Param("dispoDeptCd") String dispoDeptCd, @Param("fromYear") String fromDate, @Param("toYear") String toDate, @Param("plantCd") String plantCd, @Param("ewstClassCd") String ewstClassCd, @Param("ewstWasteNo") String ewstWasteNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 분배율 조회
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * 
     * @throws Exception
     */

    public Disporate getDisporate(@Param("ewstWasteRateNo") String ewstWasteRateNo) throws Exception;

    /**
     * 분배율 생성
     * 
     * @param Disporate
     *            분배율
     * 
     * @throws Exception
     */

    public int createDisporate(Disporate disporate) throws Exception;

    /**
     * 분배율 업데이트
     * 
     * @param Disporate
     *            분배율
     * 
     * @throws Exception
     */

    public int updateDisporate(Disporate disporate) throws Exception;

    /**
     * 분배율 부서조회
     * 
     * @param plantCd
     *            사업장 코드
     * @param ewstWasteRateNo
     *            분배율 번호
     * 
     * @throws Exception
     */

    public List<DisporateDeptItem> getDisporateDept(@Param("plantCd") String plantCd, @Param("ewstWasteRateNo") String ewstWasteRateNo) throws Exception;

    /**
     * 분배율 부서생성
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * @param deptCd
     *            부서 코드
     * @param month
     *            월
     * @param rate
     *            분배율
     * 
     * @throws Exception
     */

    public int createDisporateDept(@Param("ewstWasteRateNo") String ewstWasteRateNo, @Param("deptCd") String deptCd, @Param("month") String month, @Param("rate") Float rate);

    /**
     * 분배율 부서 삭제
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * @throws Exception
     */

    public int deleteDisporateDept(@Param("ewstWasteRateNo") String ewstWasteRateNo);

    /**
     * 분배율 부서 체크
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * @throws Exception
     */

    public List<String> getDisporateCheckedDepts(@Param("ewstWasteRateNo") String ewstWasteRateNo);

    /**
     * 분배율 (사업장, 연도, 폐기물, 폐기물분류) 중복 체크
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * @param ewstWasteNo
     *            폐기물 번호
     * @param plantCd
     *            사업장
     * @param year
     *            연도
     * @throws Exception
     */

    public int getDisporateCheck(@Param("ewstWasteRateNo") String ewstWasteRateNo, @Param("ewstWasteNo") String ewstWasteNo, @Param("plantCd") String plantCd, @Param("year") String year);

}
