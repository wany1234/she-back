package com.she.env.gas.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.model.FacMgtByYear;
import com.she.env.gas.service.FacMgtByYearService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/env", description = "년도별 배출시설 관리")
public class FacMgtByYearController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FacMgtByYearService service;

    @GetMapping("/api/env/gas/discharge/facility/years")
    public ResponseEntity<List<FacMgtByYear>> getdisFacByYears(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String fcltNm = map.containsKey("fcltNm") ? map.get("fcltNm").toString() : "";

        List<FacMgtByYear> disFacilityByYearList = service.getdisFacByYears(year, plantCd, fcltNm);

        return ResponseEntity.ok().body(disFacilityByYearList);
    }

    @GetMapping("/api/env/gas/discharge/facility/year/{ghgFcltNo}/{year}")
    public ResponseEntity<FacMgtByYear> getdisFacByYear(@PathVariable("ghgFcltNo") int ghgFcltNo, @PathVariable("year") String year) throws Exception {
        return ResponseEntity.ok(service.getdisFacByYear(ghgFcltNo, year));
    }

    @PostMapping("/api/env/gas/discharge/facility/year")
    public ResponseEntity<Integer> insertdisFacByYear(@RequestBody List<FacMgtByYear> fctByYear) throws Exception {
        return ResponseEntity.ok().body(this.service.insertdisFacByYear(fctByYear));
    }

    @DeleteMapping("/api/env/gas/discharge/facility/year/{ghgFcltNo}/{year}")
    public ResponseEntity<Integer> deletedisFacByYear(@PathVariable("ghgFcltNo") int ghgFcltNo, @PathVariable("year") String year) throws Exception {
        return ResponseEntity.ok().body(this.service.deletedisFacByYear(ghgFcltNo, year));
    }
}
