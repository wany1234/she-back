package com.she.env.gas.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.model.FacCalByYear;
import com.she.env.gas.service.FacCalByYearService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/env", description = "년도별 배출시설산정방법 관리")
public class FacCalByYearController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacCalByYearService service;

    /**
     * 년도별 배출시설산정방법 검색조회
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/cal/years")
    public ResponseEntity<List<FacCalByYear>> getdisCalByYears(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        String fcltNm = map.containsKey("fcltNm") ? map.get("fcltNm").toString() : "";

        String ghgFcltCd = map.containsKey("ghgFcltCd") ? map.get("ghgFcltCd").toString() : "";

        String isSave = map.containsKey("isSave") ? map.get("isSave").toString() : "";

        List<FacCalByYear> disFacilityByYearList = service.getdisCalByYears(year, plantCd, fcltNm, ghgFcltCd, isSave);

        return ResponseEntity.ok().body(disFacilityByYearList);
    }

    /**
     * 년도별 배출시설산정방법 검색조회
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/cal/year/{ghgFcltNo}/{year}/{ghgOutDaCd}/{ghgActDaCd}")
    public ResponseEntity<FacCalByYear> getdisCalByYear(@PathVariable("ghgFcltNo") int ghgFcltNo, @PathVariable("year") String year, @PathVariable("ghgOutDaCd") String ghgOutDaCd, @PathVariable("ghgActDaCd") String ghgActDaCd) throws Exception {
        return ResponseEntity.ok().body(service.getdisCalByYear(ghgFcltNo, year, ghgOutDaCd, ghgActDaCd));
    }

    /**
     * 년도별 배출시설산정방법 신규등록
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    // @PostMapping("/api/env/gas/discharge/cal/year")
    // public ResponseEntity<Integer> insertdisFacByYear(@RequestBody
    // List<FacCalByYear> fctByYear) throws Exception {
    // return
    // ResponseEntity.ok().body(this.service.insertdisCalByYear(fctByYear));
    // }

    /**
     * 년도별 배출시설산정방법 등록/수정
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("/api/env/gas/discharge/cal/year")
    public ResponseEntity<Integer> saveDisFacByYear(@RequestBody List<FacCalByYear> list) throws Exception {
        return ResponseEntity.ok().body(this.service.saveDisCalByYear(list));
    }

}
