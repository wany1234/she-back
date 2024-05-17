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

package com.she.common.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.service.PortletService;
import com.she.mgt.model.MgtMrDeptCalc;
import com.she.safety.model.NoAccident;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 메인 포틀릿
 *
 */
@RestController
@RequestMapping("api/main/portlet")
public class PortletController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private PortletService portletService;

    /**
     * 일일작업현황
     *
     * @param parameter
     * @return 일일작업현황
     * @throws Exception
     */
    @ApiOperation(value = "일일작업현황", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({})
    @GetMapping("/dailyworkstatus")
    public ResponseEntity<HashMap<String, Object>> getDailyWorkStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int mapNo = map.containsKey("mapNo") ? Integer.parseInt(map.get("mapNo").toString()) : 0;

        return ResponseEntity.ok().body(portletService.getDailyWorkStatus(mapNo));
    }

    /**
     * 일정계획 현황
     *
     * @param parameter
     * @return 일정계획
     * @throws Exception
     */
    @ApiOperation(value = "일정계획 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({})
    @GetMapping("/planstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getPlanStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        return ResponseEntity.ok().body(portletService.getPlanStatus(userId));
    }

    /**
     * 사업장무재해 조회
     *
     * @param parameter
     * @return 사업장무재해
     * @throws Exception
     */
    @ApiOperation(value = "사업장무재해 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({})
    @GetMapping("/noaccidentstatus")
    public ResponseEntity<List<NoAccident>> getNoAccidentStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        return ResponseEntity.ok().body(portletService.getNoAccidentStatus());
    }

    /**
     * 공사&작업허가서 조회
     *
     * @param parameter
     * @return 공사&작업허가서
     * @throws Exception
     */
    @ApiOperation(value = "공사&작업허가서 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "array", paramType = "query"), })
    @GetMapping("/constwkodstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getConstWkodStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String[] dates = this.requestMapper.convertObjectListAsStringArray(map.get("dates"));
        String[] searchDates = this.requestMapper.convertObjectListAsStringArray(map.get("searchDates"));

        return ResponseEntity.ok().body(portletService.getConstWkodStatus(plantCd, dates[0], dates[1], searchDates));
    }

    /**
     * 나의 할 일 조회
     *
     * @param parameter
     * @return 나의 할 일
     * @throws Exception
     */
    @ApiOperation(value = "나의 할 일 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "userId", value = "사용자ID", required = false, dataType = "array", paramType = "query"), })
    @GetMapping("/myinfo")
    public ResponseEntity<HashMap<String, Object>> getMyInfo(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String from = map.containsKey("from") ? map.get("from").toString() : "";
        String to = map.containsKey("to") ? map.get("to").toString() : "";

        return ResponseEntity.ok().body(portletService.getMyInfo(userId, from, to));
    }


    /**
     * SHE지수 현황 조회
     *
     * @param parameter
     * @return SHE지수 현황 목록
     * @throws Exception
     */
    @ApiOperation(value = "SHE지수 현황 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "yearPeriod", value = "평가기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "부서", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/she")
    public ResponseEntity<List<MgtMrDeptCalc>> getSHEs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] yearPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("yearPeriod"));
        // String curentYear = map.containsKey("bizTgtYear") ?
        // map.get("bizTgtYear").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        return ResponseEntity.ok().body(portletService.getSHEs(yearPeriod[1], yearPeriod[0], plantCd, deptCd));
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
    @GetMapping("/impractstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getImprActStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String fromDate = map.containsKey("fromDate") ? map.get("fromDate").toString() : "";
        String toDate = map.containsKey("toDate") ? map.get("toDate").toString() : "";
        String[] depts = this.requestMapper.convertObjectListAsStringArray(map.get("depts"));
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<HashMap<String, Object>> list = this.portletService.getImprActStatus(fromDate, toDate, depts, plantCd);

        return ResponseEntity.ok().body(list);
    }

    /**
     * 무재해 현황
     *
     * @param dataDate
     *            기준일
     * @return 목록
     * @throws Exception
     */
    @GetMapping("/moaccident")
    public ResponseEntity<List<HashMap<String, Object>>> getNoAccident(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String dataDate = map.containsKey("dataDate") ? map.get("dataDate").toString() : "";

        List<HashMap<String, Object>> list = this.portletService.getNoAccident(dataDate);

        return ResponseEntity.ok().body(list);
    }

    /**
     * 최근 공지사항 조회
     *
     * @return 최근 공지사항 목록
     * @throws Exception
     */
    @GetMapping("/recentnotices")
    public ResponseEntity<List<HashMap<String, Object>>> getRecentNotices() throws Exception {
        List<HashMap<String, Object>> list = this.portletService.getRecentNotices();

        return ResponseEntity.ok().body(list);
    }

    /**
     * 팝업공지이고, 오늘이 공지기간에 포함된 경우의 공지사항 목록 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/opennotices")
    public ResponseEntity<List<HashMap<String, Object>>> getOpenNotices() throws Exception {
        List<HashMap<String, Object>> list = this.portletService.getOpenNotices();

        return ResponseEntity.ok().body(list);
    }
}
