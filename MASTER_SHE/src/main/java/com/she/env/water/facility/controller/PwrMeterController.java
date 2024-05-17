package com.she.env.water.facility.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.she.env.water.facility.service.PwrMeterService;
import com.she.env.water.model.PowerMeter;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/facility/pwrmeter")
public class PwrMeterController {
    @Autowired
    private PwrMeterService pwrMeterService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 전력량계 조회
     *
     * @param useYn
     *            사용여부
     * @return 전력량계 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/pwrmeters")
    public ResponseEntity<List<PowerMeter>> getPwrMeters(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrPwrMeterNm = map.containsKey("ewtrPwrMeterNm") ? map.get("ewtrPwrMeterNm").toString() : "";

        List<PowerMeter> pwrMeterList = pwrMeterService.getPwrMeters(useYn, plantCd, ewtrPwrMeterNm, defaultParam);

        return ResponseEntity.ok().body(pwrMeterList);
    }

    /**
     * 전력량계 상세조회
     *
     * @param ewtrPwrMeterNo
     *            전력량계번호
     * @return PwrMeter 전력량계
     * @throws Exception
     *             예외
     */
    @GetMapping("/pwrmeter/{ewtrPwrMeterNo}")
    public ResponseEntity<PowerMeter> getPwrMeter(@PathVariable("ewtrPwrMeterNo") int ewtrPwrMeterNo) throws Exception {
        PowerMeter pwrMeter = pwrMeterService.getPwrMeter(ewtrPwrMeterNo);
        return ResponseEntity.ok().body(pwrMeter);
    }

    /**
     * 전력량계 중복체크
     *
     * @param ewtrPwrMeterNo
     *            전력량계번호
     * @return PwrMeter 전력량계
     * @throws Exception
     *             예외
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrPwrMeterNm = map.containsKey("ewtrPwrMeterNm") ? map.get("ewtrPwrMeterNm").toString() : ""; // 시설명
        int ewtrPwrMeterNo = map.containsKey("ewtrPwrMeterNo") ? Integer.parseInt("".equals(map.get("ewtrPwrMeterNo").toString()) ? "0" : map.get("ewtrPwrMeterNo").toString()) : 0;

        return ResponseEntity.ok().body(pwrMeterService.getCheck(plantCd, ewtrPwrMeterNm, ewtrPwrMeterNo));
    }

    /**
     * 전력량계 신규등록
     *
     * @param PowerMeter
     *            전력량계
     * @return ewtrPwrMeterNo 전력량계번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/pwrmeter")
    public ResponseEntity<Integer> createPwrMeter(@RequestBody PowerMeter pwrMeter) throws Exception {
        return ResponseEntity.ok().body(pwrMeterService.createPwrMeter(pwrMeter));
    }

    /**
     * 전력량계 수정
     *
     * @param PowerMeter
     *            전력량계
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/pwrmeter")
    public ResponseEntity<Integer> updatePwrMeter(@RequestBody PowerMeter pwrMeter) throws Exception {
        return ResponseEntity.ok().body(pwrMeterService.updatePwrMeter(pwrMeter));
    }
}
