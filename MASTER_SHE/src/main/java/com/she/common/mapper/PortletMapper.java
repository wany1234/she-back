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
package com.she.common.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.model.MgtMrDeptCalc;
import com.she.safety.model.NoAccident;

/**
 * 메인 포틀릿 맵퍼
 *
 */
@Mapper
@Repository("com.she.common.mapper.PortletMapper")
public interface PortletMapper {

    /**
     * 일일작업현황
     *
     * @return 일일작업현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getDailyWorkStatus() throws Exception;

    /**
     * 일일작업현황
     *
     * @return 일일작업현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getDailyWorkStatusLocation(int mapNo) throws Exception;

    /**
     * 일정계획 현황
     *
     * @return 일정계획
     * @throws Exception
     */
    public List<HashMap<String, Object>> getPlanStatus(@Param("userId") String userId) throws Exception;

    /**
     * 사업장무재해 조회
     *
     * @return 사업장무재해
     * @throws Exception
     */
    public List<NoAccident> getNoAccidentStatus() throws Exception;

    /**
     * 공사&작업허가서 조회
     *
     * @param plantCd
     * @param startYmd
     * @param endYmd
     * @return 공사&작업허가서
     * @throws Exception
     */
    public List<HashMap<String, Object>> getConstWkodStatus(@Param("plantCd") String plantCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("searchDates") String[] searchDates, @Param("searchDateStr") String searchDateStr) throws Exception;

    /**
     * 나의 할 일 조회
     *
     * @param userId
     * @return 나의 할 일
     * @throws Exception
     */
    public HashMap<String, Object> getRequestCnt(@Param("userId") String userId, @Param("from") String from, @Param("to") String to) throws Exception;

    /**
     * 나의 할 일 조회
     *
     * @param userId
     * @return 나의 할 일
     * @throws Exception
     */
    public HashMap<String, Object> getApprCnt(@Param("userId") String userId, @Param("from") String from, @Param("to") String to) throws Exception;

    /**
     * 나의 할 일 조회
     *
     * @param userId
     * @return 나의 할 일
     * @throws Exception
     */
    public HashMap<String, Object> getApprdCnt(@Param("userId") String userId, @Param("from") String from, @Param("to") String to) throws Exception;

    /**
     * 나의 할 일 조회
     *
     * @param userId
     * @return 나의 할 일
     * @throws Exception
     */
    public HashMap<String, Object> getReturnCnt(@Param("userId") String userId, @Param("from") String from, @Param("to") String to) throws Exception;

    /**
     * SHE지수 현황 조회
     *
     * @param plantCd
     *            사업장
     * @return SHE지수 현황 목록
     * @throws Exception
     */
    public List<MgtMrDeptCalc> getSHEs(@Param("curentYear") String curentYear, @Param("preYear") String preYear, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 요청/조치사항 현황
     *
     * @param fromDate
     *            검색시작일
     * @param toDate
     *            검색종료일
     * @return 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getImprActStatus(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("depts") String[] depts, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 무재해 현황
     *
     * @param dataDate
     *            기준일
     * @return 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getNoAccident(@Param("dataDate") String dataDate) throws Exception;

    /**
     * 최근 공지사항 조회
     *
     * @return 최근 공지사항 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getRecentNotices() throws Exception;

    /**
     * 팝업공지이고, 오늘이 공지기간에 포함된 경우의 공지사항 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOpenNotices() throws Exception;

    public void deleteUserPortlet(@Param("userId") String userId);
}
