package com.she.env.water.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.service.DischargeService;
import com.she.env.water.model.Discharge;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/baseinfo/discharge")
public class DischargeController {
    @Autowired
    private DischargeService dischargeService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 배출수 조회
     *
     * @param useYn
     *            사용여부
     * @return 배출수 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/discharges")
    public ResponseEntity<List<Discharge>> getDischarges(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        String ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? map.get("ewtrCleanFacNo").toString() : "";
        String title = map.containsKey("title") ? map.get("title").toString() : "";
        List<Discharge> dischargeList = dischargeService.getDischarges(useYn, plantCd, ewtrCleanFacNo, deptCd, title, defaultParam);

        return ResponseEntity.ok().body(dischargeList);
    }

    /**
     * 배출수 상세조회
     *
     * @param ewtrDischNo
     *            배출수번호
     * @return Discharge 배출수
     * @throws Exception
     *             예외
     */
    @GetMapping("/discharge/{ewtrDischNo}")
    public ResponseEntity<Discharge> getDischarge(@PathVariable("ewtrDischNo") int ewtrDischNo) throws Exception {
        Discharge discharge = dischargeService.getDischarge(ewtrDischNo);
        return ResponseEntity.ok().body(discharge);
    }

    /**
     * 배출수 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/dischargecheck")
    public ResponseEntity<HashMap<String, Object>> getDischargeCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrDischClassCd = map.containsKey("ewtrDischClassCd") ? map.get("ewtrDischClassCd").toString() : "";
        String ewtrDischNm = map.containsKey("ewtrDischNm") ? map.get("ewtrDischNm").toString() : "";
        int ewtrDischNo = map.containsKey("ewtrDischNo") ? Integer.parseInt("".equals(map.get("ewtrDischNo").toString()) ? "0" : map.get("ewtrDischNo").toString()) : 0;

        return ResponseEntity.ok().body(dischargeService.getDischargeCheck(plantCd, ewtrDischClassCd, ewtrDischNm, ewtrDischNo));
    }

    /**
     * 배출수 신규등록
     *
     * @param Discharge
     *            배출수
     * @return ewtrDischNo 배출수번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/discharge")
    public ResponseEntity<Integer> createDischarge(@RequestBody Discharge discharge) throws Exception {
        return ResponseEntity.ok().body(dischargeService.createDischarge(discharge));
    }

    /**
     * 배출수 수정
     *
     * @param Discharge
     *            배출수
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/discharge")
    public ResponseEntity<Integer> updateDischarge(@RequestBody Discharge discharge) throws Exception {
        return ResponseEntity.ok().body(dischargeService.updateDischarge(discharge));
    }
}
