package com.she.env.tms.total.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.tms.total.service.TmsMonthAmtTotalService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/total")
@Api(value = "/api/env/tms/total", description = "환경 tms 집계 서비스")
public class TmsMonthAmtTotalController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TmsMonthAmtTotalService tmsMonthAmtTotalService;

    /**
     * TMS 집계 조회
     * 
     * @return TMS 집계 데이터
     * @throws Exception
     */
    @ApiOperation(value = "TMS 현황 조회[ENV17001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "itemCode", value = "TMS 측정항목코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tmsamttotal")
    public ResponseEntity<List<HashMap<String, Object>>> getTmsAmtTotal(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 구분
        String tmsType = map.containsKey("tmsType") ? map.get("tmsType").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // TMS 측정소코드
        String stationCode = map.containsKey("stationCode") ? map.get("stationCode").toString() : "";
        // TMS 측정항목코드
        String itemCode = map.containsKey("itemCode") ? map.get("itemCode").toString() : "";
        // 측정년도
        String[] yearMonth = this.requestMapper.convertObjectListAsStringArray(map.get("yearMonth"));
        if (yearMonth != null && yearMonth.length > 0) {
            List<HashMap<String, Object>> searchYears = new ArrayList<HashMap<String, Object>>();
            for (String data : yearMonth) {
                List<String> searchYearMonths = new ArrayList<String>();
                for (int i = 1; i < 13; i++) {
                    searchYearMonths.add("[" + data + "_" + String.valueOf(i) + "]");
                }

                HashMap<String, Object> objectYear = new HashMap<String, Object>();
                objectYear.put("year", data);
                objectYear.put("yearMonth", searchYearMonths);
                objectYear.put("alias1", data + "_sum");
                objectYear.put("alias2", data + "_law_max");
                searchYears.add(objectYear);
            }
            return ResponseEntity.ok().body(this.tmsMonthAmtTotalService.getTmsAmtTotal(tmsType, plantCd, searchYears, stationCode, itemCode));
        } else {
            return ResponseEntity.ok().body(null);
        }

    }

}
