package com.she.rsa.baseInfo.controller;

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

import com.she.rsa.baseInfo.service.RiskHazardService;
import com.she.rsa.model.RiskHazard;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/rsa/baseinfo")
public class RiskHazardController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private RiskHazardService riskHazardService;

    /**
     * 유해위험요인 조회
     *
     * @param parameter
     *            검색조건
     * @return 유해위험요인 목록
     * @throws Exception
     */
    @GetMapping("/riskhazards")
    public ResponseEntity<List<RiskHazard>> getRiskHazards(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 유해위험요인 Lv 1
        int priskHazardNo = map.containsKey("priskHazardNo") ? Integer.parseInt("".equals(map.get("priskHazardNo").toString()) ? "0" : map.get("priskHazardNo").toString()) : 0;
        // 유해위험요인 Lv 2
        String riskHazardNm = map.containsKey("riskHazardNm") ? map.get("riskHazardNm").toString() : "";
        // 유해위험요소 레벨
        int riskHazardLevel = map.containsKey("riskHazardLevel") ? Integer.parseInt("".equals(map.get("riskHazardLevel").toString()) ? "2" : map.get("riskHazardLevel").toString()) : 2;
        // 유해위험요인 Lv 2
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<RiskHazard> riskHazards = riskHazardService.getRiskHazards(priskHazardNo, riskHazardNm, riskHazardLevel, useYn);
        return ResponseEntity.ok().body(riskHazards);
    }

    /**
     * 유해위험요인 상세 조회
     *
     * @param riskHazardNo
     *            유해위험요인 번호
     * @return 유해위험요인
     * @throws Exception
     */
    @GetMapping("/riskhazard/{riskHazardNo}")
    public ResponseEntity<RiskHazard> getRiskHazard(@PathVariable(name = "riskHazardNo") int riskHazardNo) throws Exception {
        return ResponseEntity.ok().body(this.riskHazardService.getRiskHazard(riskHazardNo));
    }

    /**
     * 유해위험요인 신규등록
     *
     * @param riskHazard
     *            유해위험요인
     * @return 유해위험요인 번호
     * @throws Exception
     */
    @PostMapping("/riskhazard")
    public ResponseEntity<Integer> createRiskHazard(@RequestBody RiskHazard riskHazard) throws Exception {
        return ResponseEntity.ok().body(this.riskHazardService.createRiskHazard(riskHazard));
    }

    /**
     * 유해위험요인 수정
     *
     * @param riskHazard
     *            유해위험요인
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/riskhazard")
    public ResponseEntity<Integer> updateRiskHazard(@RequestBody RiskHazard riskHazard) throws Exception {
        return ResponseEntity.ok().body(this.riskHazardService.updateRiskHazard(riskHazard));
    }

    /**
     * 유해위험요인 명칭 체크
     *
     * @param parameter
     *            검색조건
     * @return 체크 목록
     * @throws Exception
     */
    @GetMapping("/riskhazardcheck")
    public ResponseEntity<List<HashMap<String, Object>>> getRiskHazardCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 상위 번호
        int priskHazardNo = map.containsKey("priskHazardNo") ? Integer.parseInt("".equals(map.get("priskHazardNo").toString()) ? "0" : map.get("priskHazardNo").toString()) : 0;
        // 유해위험요인 번호
        int riskHazardNo = map.containsKey("riskHazardNo") ? Integer.parseInt("".equals(map.get("riskHazardNo").toString()) ? "0" : map.get("riskHazardNo").toString()) : 0;
        // 유해위험요인명
        String riskHazardNm = map.containsKey("riskHazardNm") ? map.get("riskHazardNm").toString() : "";

        return ResponseEntity.ok().body(riskHazardService.getRiskHazardCheck(priskHazardNo, riskHazardNo, riskHazardNm));
    }

}
