package com.she.health.workingEnvManage.controller;

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

import com.she.health.model.Hazard;
import com.she.health.workingEnvManage.service.HazardService;
import com.she.utils.RequestMapper;

/**
 * 유해인자 조회
 * 
 * @param parameter
 *            검색조건
 * @return 유해인자 목록
 * @throws Exception
 *             예외
 */
@RestController
@RequestMapping("api/hea/workingenvmanage")
public class HazardController {

    @Autowired
    private HazardService hazardService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 유해인자 목록 조회
     * 
     * @param parameter
     *            검색조건
     * @return 유해인자 목록
     * @throws Exception
     */
    @GetMapping("/hazards")
    public ResponseEntity<List<Hazard>> getHazards(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String hazardClsCd = map.containsKey("hazardClsCd") ? map.get("hazardClsCd").toString() : "";
        String hazardGradCd = map.containsKey("hazardGradCd") ? map.get("hazardGradCd").toString() : "";
        String hazardNmKo = map.containsKey("hazardNmKo") ? map.get("hazardNmKo").toString() : "";
        String hazardNmEn = map.containsKey("hazardNmEn") ? map.get("hazardNmEn").toString() : "";
        String specialYn = map.containsKey("specialYn") ? map.get("specialYn").toString() : "N";
        String workEnvYn = map.containsKey("workEnvYn") ? map.get("workEnvYn").toString() : "";

        List<Hazard> hazards = this.hazardService.getHazards(hazardClsCd, hazardGradCd, hazardNmKo, hazardNmEn,
                specialYn, workEnvYn);

        return ResponseEntity.ok().body(hazards);
    }

    /**
     * 유해인자 상세 조회
     * 
     * @param heaHazardCd
     *            유해인자 코드
     * @return 유해인자
     * @throws Exception
     */
    @GetMapping("/hazard/{hazardCd}")
    public ResponseEntity<Hazard> getHazard(@PathVariable(name = "hazardCd") String hazardCd) throws Exception {
        return ResponseEntity.ok().body(this.hazardService.getHazard(hazardCd));
    }

    /**
     * 유해인자 중복검사
     * 
     * @param parameter
     *            중복검사 정보
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/counthazard")
    public ResponseEntity<List<HashMap<String, Object>>> getCountHazard(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String hazardNmKo = map.containsKey("hazardNmKo") ? map.get("hazardNmKo").toString() : "";
        String hazardNmEn = map.containsKey("hazardNmEn") ? map.get("hazardNmEn").toString() : "";
        String hazardCd = map.containsKey("hazardCd") ? map.get("hazardCd").toString() : "";

        List<HashMap<String, Object>> results = this.hazardService.getCountHazard(hazardNmKo, hazardNmEn, hazardCd);

        return ResponseEntity.ok().body(results);
    }

    /**
     * 유해인자 신규등록
     * 
     * @param hazard
     *            유해인자
     * @return 유해인자 코드
     * @throws Exception
     */
    @PostMapping("/hazard")
    public ResponseEntity<String> createHazard(@RequestBody Hazard hazard) throws Exception {
        return ResponseEntity.ok().body(this.hazardService.createHazard(hazard));
    }

    /**
     * 유해인자 수정
     * 
     * @param hazard
     *            유해인자
     * @return 유해인자 코드
     * @throws Exception
     */
    @PutMapping("/hazard")
    public ResponseEntity<String> updateHazard(@RequestBody Hazard hazard) throws Exception {
        return ResponseEntity.ok().body(this.hazardService.updateHazard(hazard));
    }

}
