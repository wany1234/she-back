package com.she.env.tms.recent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.tms.recent.service.RecentService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/recent")
@Api(value = "/api/env/tms/recent", description = "환경 tms 현재값 서비스")
public class RecentController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RecentService recentService;

    /**
     * TMS 현재값 조회
     * 
     * @param parameter
     *            검색조건
     * @return TMS 현재값 목록
     * @throws Exception
     */
    @ApiOperation(value = "TMS 현재값 조회[ENV15001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tms5recents")
    public ResponseEntity<List<HashMap<String, Object>>> getTms5Recents(@RequestParam HashMap<String, Object> parameter) throws Exception {
        return ResponseEntity.ok().body(recentService.getTms5Recents());
    }

    /**
     * TMS 배출구별 측정치, 법적기준 차트 데이터 조회
     * 
     * @return TMS 배출구별 측정치, 법적기준 차트 데이터
     * @throws Exception
     */
    @ApiOperation(value = "TMS 배출구별 측정치, 법적기준 차트 데이터 조회[ENV15002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "itemCode", value = "TMS 측정항목코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tms5recentchart")
    public ResponseEntity<Map<String, Object>> getTms5RecentChart(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }
        // 측정항목
        String itemCode = map.containsKey("itemCode") ? map.get("itemCode").toString() : "";

        return ResponseEntity.ok().body(this.recentService.getTms5RecentChart(itemCode, startYmd, endYmd));
    }

}
