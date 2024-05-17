package com.she.env.gas.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.baseInfo.service.DischargeFacilityService;
import com.she.env.gas.model.DischargeFacility;
import com.she.env.gas.model.DischargeFacilityData;
import com.she.env.gas.model.DischargeFacilityHistory;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/env", description = "배출시설관리")
public class DischargeFacilityController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DischargeFacilityService service;

    @GetMapping("/api/env/gas/discharge/facility/masters")
    public ResponseEntity<List<DischargeFacility>> getdisFacMasters(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        String ghgFcltCd = map.containsKey("ghgFcltCd") ? map.get("ghgFcltCd").toString() : "";

        String fcltNm = map.containsKey("fcltNm") ? map.get("fcltNm").toString() : "";

        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String filterSave = map.containsKey("filterSave") ? map.get("filterSave").toString() : "";

        return ResponseEntity.ok().body(service.getdisFacMasters(plantCd, ghgFcltCd, fcltNm, useYn, filterSave, year));
    }

    @GetMapping("/api/env/gas/discharge/facility/master/{ghgFcltNo}")
    public ResponseEntity<DischargeFacility> getdisFacMaster(@PathVariable("ghgFcltNo") int ghgFcltNo) throws Exception {
        return ResponseEntity.ok(service.getdisFacMaster(ghgFcltNo));
    }

    @GetMapping("/api/env/gas/discharge/facility/histroys/{ghgFcltNo}")
    public ResponseEntity<List<DischargeFacilityHistory>> getHistorys(@PathVariable("ghgFcltNo") int ghgFcltNo) throws Exception {
        return ResponseEntity.ok(service.getHistorys(ghgFcltNo));
    }

    @GetMapping("/api/env/gas/discharge/facility/datas/{ghgFcltNo}")
    public ResponseEntity<List<DischargeFacilityData>> getdisFacDatas(@PathVariable("ghgFcltNo") int ghgFcltNo) throws Exception {
        return ResponseEntity.ok(service.getdisFacDatas(ghgFcltNo));
    }

    @PostMapping("/api/env/gas/discharge/facility/master")
    public ResponseEntity<Integer> insertdisFacMaster(@RequestBody DischargeFacility dischargeFacility) throws Exception {
        return ResponseEntity.ok().body(this.service.insertdisFacMaster(dischargeFacility));
    }

    @PutMapping("/api/env/gas/discharge/facility/master")
    public ResponseEntity<Integer> updatedisFacMaster(@RequestBody DischargeFacility dischargeFacility) throws Exception {
        return ResponseEntity.ok().body(this.service.updatedisFacMaster(dischargeFacility));
    }

    @DeleteMapping("/api/env/gas/discharge/facility/master/revdelete")
    public ResponseEntity<Integer> deletedisFacMasterHisotry(@RequestBody List<DischargeFacilityHistory> dischargeFacilityHistorys) throws Exception {
        System.out.println("컨트롤럴");
        return ResponseEntity.ok().body(this.service.deletedisFacMasterHisotry(dischargeFacilityHistorys));
    }

    @PutMapping("/api/env/gas/discharge/facility/master/revInsert")
    public ResponseEntity<Integer> revInsertisFacMaster(@RequestBody DischargeFacility dischargeFacility) throws Exception {
        return ResponseEntity.ok().body(this.service.revInsertisFacMaster(dischargeFacility));
    }

    @GetMapping("/api/env/gas/discharge/facility/master/check")
    public ResponseEntity<Integer> getDisFacMasterCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String fcltNm = map.containsKey("fcltNm") ? map.get("fcltNm").toString() : "";

        int ghgFcltNo = map.containsKey("ghgFcltNo") ? Integer.parseInt("".equals(map.get("ghgFcltNo").toString()) ? "0" : map.get("ghgFcltNo").toString()) : 0;

        return ResponseEntity.ok().body(service.getDisFacMasterCheck(fcltNm, ghgFcltNo));
    }
}
