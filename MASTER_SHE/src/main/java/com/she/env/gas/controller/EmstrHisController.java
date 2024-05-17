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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.model.EmstrHis;
import com.she.env.gas.service.EmstrHisService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/env/gas/emstr")
public class EmstrHisController {

    @Autowired
    private EmstrHisService emstrHisService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 거래이력 신규등록
     *
     * @param emstrHis
     * @return
     * @throws Exception
     */
    @PostMapping("/editemstr")
    public ResponseEntity<Integer> createEmstrHis(@RequestBody EmstrHis emstrHis) throws Exception {

        return ResponseEntity.ok().body(emstrHisService.createEmstrHis(emstrHis));
    }

    /**
     * 거래이력 수정
     *
     * @param emstrHis
     * @return
     * @throws Exception
     */
    @PutMapping("/editemstr")
    public ResponseEntity<Integer> updateEmstrHis(@RequestBody EmstrHis emstrHis) throws Exception {
        return ResponseEntity.ok().body(emstrHisService.updateEmstrHis(emstrHis));
    }

    /**
     * 거래이력 조회
     *
     * @param plantCd
     * @param fromDate
     * @param toDate
     * @param trVendorCd
     * @param gubun
     * @param trGubun
     * @param trPlantCd
     * @return
     * @throws Exception
     */
    @GetMapping("/getemstrs")
    public ResponseEntity<List<EmstrHis>> getEmstrHisList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 거래일자
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String fromDate = "";
        String toDate = "";
        if (period != null && period.length == 2) {
            fromDate = period[0];
            toDate = period[1];
        }
        // 거래업체
        String trVendorCd = map.containsKey("trVendorCd") ? map.get("trVendorCd").toString() : "";
        // 구매/판매 구분
        String gubun = map.containsKey("gubun") ? map.get("gubun").toString() : "";
        // 거래구분
        String trGubun = map.containsKey("trGubun") ? map.get("trGubun").toString() : "";
        // 거래구분
        String trPlantCd = map.containsKey("trPlantCd") ? map.get("trPlantCd").toString() : "";

        return ResponseEntity.ok().body(emstrHisService.getEmstrHisList(plantCd, fromDate, toDate, trVendorCd, gubun, trGubun, trPlantCd));
    }

    /**
     * 거래이력 상세
     *
     * @param emstrHisNo
     * @return
     * @throws Exception
     */
    @GetMapping("/getemstr/{emstrHisNo}")
    public ResponseEntity<EmstrHis> getEmstHis(@PathVariable int emstrHisNo) throws Exception {
        return ResponseEntity.ok().body(emstrHisService.getEmstHis(emstrHisNo));
    }

    /**
     * 거래이력 중복체크
     *
     * @param plantCd
     * @param trYmd
     * @param trVendorCd
     * @param gubun
     * @return
     * @throws Exception
     */
    @GetMapping("/duplecheck/{plantCd}/{trYmd}/{trGubun}/{gubun}")
    public ResponseEntity<Integer> dupleCheck(@PathVariable String plantCd, @PathVariable String trYmd, @PathVariable String trGubun, @PathVariable String gubun) throws Exception {
        return ResponseEntity.ok().body(emstrHisService.dupleCheck(plantCd, trYmd, trGubun, gubun));
    }

}
