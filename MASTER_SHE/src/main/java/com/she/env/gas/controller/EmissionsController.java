package com.she.env.gas.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.model.Emissions;
import com.she.env.gas.service.EmissionsService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/env", description = "배출량/에너지사용량 조회")
public class EmissionsController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private EmissionsService service;

    /**
     * 월별배출량 조회
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/emissions")
    public ResponseEntity<List<Emissions>> getDisByMonth(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(service.getDisByMonth(year, plantCd));
    }

    /**
     * 월별배출량과 매개변수가 있는지 비교를 해서 조회
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/et/emissions")
    public ResponseEntity<List<Emissions>> getDisByMonthEt(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String year = map.containsKey("year") ? map.get("year").toString() : "";

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(service.getDisByMonthEt(year, plantCd));
    }

    /**
     * 월별배출량 상세조회
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/emission/{ghgFcltNo}/{year}/{ghgOutDaCd}/{ghgActDaCd}")
    public ResponseEntity<Emissions> getDisByMon(@PathVariable("ghgFcltNo") int ghgFcltNo, @PathVariable("year") String year, @PathVariable("ghgOutDaCd") String ghgOutDaCd, @PathVariable("ghgActDaCd") String ghgActDaCd) throws Exception {
        return ResponseEntity.ok().body(service.getDisByMon(ghgFcltNo, year, ghgOutDaCd, ghgActDaCd));
    }

    /**
     * 월별배출량 신규등록
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("/api/env/gas/discharge/emission")
    public ResponseEntity<Integer> insertDisByMonth(@RequestBody Emissions fctByYear) throws Exception {
        return ResponseEntity.ok().body(this.service.insertDisByMonth(fctByYear));
    }

    /**
     * 월별배출량 수정
     * 
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @PutMapping("/api/env/gas/discharge/emission")
    public ResponseEntity<Integer> updateDisByMonth(@RequestBody Emissions fctByYear) throws Exception {
        return ResponseEntity.ok().body(this.service.updateDisByMonth(fctByYear));
    }

    @PutMapping("/api/env/gas/discharge/emission/accept")
    public ResponseEntity<Integer> acceptDisByMonth(@RequestBody List<Emissions> emissionses) throws Exception {
        return ResponseEntity.ok().body(this.service.acceptDisByMonth(emissionses));
    }

    /**
     * 배출량 현황 조회
     * 
     * @param mCols
     * @param plantCd
     * @param fromYear
     * @param toYear
     * @return
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/emission/actdatastatus")
    public ResponseEntity<List<HashMap<String, Object>>> getActDataStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String[] mCols = this.requestMapper.convertObjectListAsStringArray(map.get("mCols"));
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String fromYear = "";
        String toYear = "";

        if (period != null && period.length == 2) {
            fromYear = period[0];
            toYear = period[1];
        }

        return ResponseEntity.ok().body(this.service.getActDataStatus(mCols, plantCd, fromYear, toYear));
    }

    /**
     * 명세표 출력
     * 
     * @param plantCd
     * @param year
     * @return
     * @throws Exception
     */
    @GetMapping("/api/env/gas/discharge/emission/getspesheet")
    public ResponseEntity<List<HashMap<String, Object>>> getSpecSheet(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        year = year.substring(0, 4);
        return ResponseEntity.ok().body(service.getSpecSheet(plantCd, year));

    }

}
