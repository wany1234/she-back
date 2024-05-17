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
package com.she.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.PortletMapper;
import com.she.mgt.model.MgtMrDeptCalc;
import com.she.safety.model.NoAccident;

/**
 * 메민 포틀릿 기능정의
 *
 */
@Service
public class PortletService {
    @Autowired
    private PortletMapper portletMapper;

    /**
     * 일일작업현황
     *
     * @return 일일작업현황
     * @throws Exception
     */
    public HashMap<String, Object> getDailyWorkStatus(int mapNo) throws Exception {
        HashMap<String, Object> returnData = new HashMap<String, Object>();
        List<HashMap<String, Object>> list = portletMapper.getDailyWorkStatus();
        returnData.put("list", list);
        List<HashMap<String, Object>> locations = portletMapper.getDailyWorkStatusLocation(mapNo);
        returnData.put("locations", locations);
        return returnData;
    }

    /**
     * 일정계획 현황
     *
     * @return 일정계획
     * @throws Exception
     */
    public List<HashMap<String, Object>> getPlanStatus(String userId) throws Exception {
        return portletMapper.getPlanStatus(userId);
    }

    /**
     * 사업장무재해 조회
     *
     * @return 사업장무재해
     * @throws Exception
     */
    public List<NoAccident> getNoAccidentStatus() throws Exception {
        return portletMapper.getNoAccidentStatus();
    }

    /**
     * 공사&작업허가서 조회
     *
     * @param plantCd
     * @param startYmd
     * @param endYmd
     * @return 공사&작업허가서
     * @throws Exception
     */
    public List<HashMap<String, Object>> getConstWkodStatus(String plantCd, String startYmd, String endYmd, String[] searchDates) throws Exception {
        String searchDateStr = String.join(", ", searchDates);
        return portletMapper.getConstWkodStatus(plantCd, startYmd, endYmd, searchDates, searchDateStr);
    }

    /**
     * 나의 할 일 조회
     *
     * @param userId
     * @return 나의 할 일
     * @throws Exception
     */
    public HashMap<String, Object> getMyInfo(String userId, String from, String to) throws Exception {
        HashMap<String, Object> returnData = new HashMap<String, Object>();
        // 요청한 문서
        returnData.put("requestCnt", portletMapper.getRequestCnt(userId, from, to).get("requestCnt"));
        // 결재할 문서
        returnData.put("apprCnt", portletMapper.getApprCnt(userId, from, to).get("apprCnt"));
        // 승인한 문서
        returnData.put("apprdCnt", portletMapper.getApprdCnt(userId, from, to).get("apprdCnt"));
        // 반려한 문서
        returnData.put("returnCnt", portletMapper.getReturnCnt(userId, from, to).get("returnCnt"));
        return returnData;
    }


    /**
     * SHE지수 현황 조회
     *
     * @param plantCd
     *            사업장
     * @return SHE지수 현황 목록
     * @throws Exception
     */
    public List<MgtMrDeptCalc> getSHEs(String curentYear, String preYear, String plantCd, String deptCd) throws Exception {
        return portletMapper.getSHEs(curentYear, preYear, plantCd, deptCd);
    }

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
    public List<HashMap<String, Object>> getImprActStatus(String fromDate, String toDate, String[] depts, String plantCd) throws Exception {
        return this.portletMapper.getImprActStatus(fromDate, toDate, depts, plantCd);
    }

    /**
     * 무재해 현황
     *
     * @param dataDate
     *            기준일
     * @return 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getNoAccident(String dataDate) throws Exception {
        return this.portletMapper.getNoAccident(dataDate);
    }

    /**
     * 최근 공지사항 조회
     *
     * @return 최근 공지사항 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getRecentNotices() throws Exception {
        return this.portletMapper.getRecentNotices();
    }

    /**
     * 팝업공지이고, 오늘이 공지기간에 포함된 경우의 공지사항 목록 조회
     *
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOpenNotices() throws Exception {
        return this.portletMapper.getOpenNotices();
    }
}
