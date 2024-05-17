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

import com.she.env.gas.baseInfo.service.GasUnitService;
import com.she.env.gas.model.GasUnit;
import com.she.utils.RequestMapper;

@RestController
public class GasUnitController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private GasUnitService gasUnitService;

    /**
     * 산정기준단위코드 조회
     * 
     * @param parameter
     *            검색조건
     * @return 산정기준단위코드 목록
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/gasunits")
    public ResponseEntity<List<GasUnit>> getGasUnits(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 산정기준단위명
        String unitClsCd = map.containsKey("unitClsCd") ? map.get("unitClsCd").toString() : "";
        // 산정기준단위
        String unitNm = map.containsKey("unitNm") ? map.get("unitNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(gasUnitService.getGasUnits(unitClsCd, unitNm, useYn));
    }

    /**
     * 산정기준단위코드 상세조회
     * 
     * @param unitCd
     *            산정기준단위코드
     * @return 산정기준단위코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/gasunit/{unitCd}")
    public ResponseEntity<GasUnit> getGasUnit(@PathVariable("unitCd") String unitCd) throws Exception {
        return ResponseEntity.ok().body(this.gasUnitService.getGasUnit(unitCd));
    }

    /**
     * 산정기준단위코드 체크
     * 
     * @param unitCd
     *            산정기준단위코드
     * @return 산정기준단위 중복코드 갯수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/baseinfo/count/gasunit/{unitCd}")
    public ResponseEntity<Integer> countGasUnit(@PathVariable("unitCd") String unitCd) throws Exception {
        return ResponseEntity.ok(gasUnitService.countGasUnit(unitCd));
    }

    /**
     * 산정기준단위코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 산정기준단위코드
     * @throws Exception
     */
    @PostMapping("api/env/gas/baseinfo/gasunit")
    public ResponseEntity<String> createGasUnit(@RequestBody GasUnit gasUnit) throws Exception {
        return ResponseEntity.ok().body(this.gasUnitService.createGasUnit(gasUnit));
    }

    /**
     * 산정기준단위코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 산정기준단위코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/baseinfo/gasunit")
    public ResponseEntity<String> updateGasUnit(@RequestBody GasUnit gasUnit) throws Exception {
        return ResponseEntity.ok().body(this.gasUnitService.updateGasUnit(gasUnit));
    }

    /**
     * 산정기준단위코드 조회
     * 
     * @param parameter
     *            검색조건
     * @return 산정기준단위코드 목록
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/gasunitcdnms")
    public ResponseEntity<List<GasUnit>> getGasUnitCdNms() throws Exception {
        List<GasUnit> gasUnitCdNms = gasUnitService.getGasUnitCdNms();
        return ResponseEntity.ok().body(gasUnitCdNms);
    }

}
