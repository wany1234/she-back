package com.she.env.gas.baseInfo.controller;

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

import com.she.env.gas.baseInfo.service.DischargeFacService;
import com.she.env.gas.model.DischargeFac;
import com.she.utils.RequestMapper;

@RestController
public class DischargeFacController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DischargeFacService dischargeFacService;

    /**
     * 배출시설코드 조회
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드 목록
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/disfacs")
    public ResponseEntity<List<DischargeFac>> getDischargeFacs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 배출활동구분
        String disClsCd = map.containsKey("disClsCd") ? map.get("disClsCd").toString() : "";
        // 배출시설코드명
        String disFacNm = map.containsKey("disFacNm") ? map.get("disFacNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(dischargeFacService.getDischargeFacs(disClsCd, disFacNm, useYn));
    }

    /**
     * 배출시설코드 상세조회
     * 
     * @param disFacCd
     *            배출시설코드
     * @return 배출시설코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/disfac/{disFacCd}")
    public ResponseEntity<DischargeFac> getDischargeFac(@PathVariable("disFacCd") String dischargeFac) throws Exception {
        return ResponseEntity.ok().body(this.dischargeFacService.getDischargeFac(dischargeFac));
    }

    /**
     * 배출시설코드 체크
     * 
     * @param disFacCd
     *            배출시설코드
     * @return 배출시설 중복코드 갯수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/baseinfo/count/disfac/{disFacCd}")
    public ResponseEntity<Integer> countDischargeFac(@PathVariable("disFacCd") String disFacCd) throws Exception {
        return ResponseEntity.ok(dischargeFacService.countDischargeFac(disFacCd));
    }

    /**
     * 배출시설코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드
     * @throws Exception
     */
    @PostMapping("api/env/gas/baseinfo/disfac")
    public ResponseEntity<String> createDischargeFac(@RequestBody DischargeFac dischargeFac) throws Exception {
        return ResponseEntity.ok().body(this.dischargeFacService.createDischargeFac(dischargeFac));
    }

    /**
     * 배출시설코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/baseinfo/disfac")
    public ResponseEntity<String> updateDischargeFac(@RequestBody DischargeFac dischargeFac) throws Exception {
        return ResponseEntity.ok().body(this.dischargeFacService.updateDischargeFac(dischargeFac));
    }
}
