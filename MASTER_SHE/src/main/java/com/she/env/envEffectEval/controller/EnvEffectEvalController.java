package com.she.env.envEffectEval.controller;

import com.she.common.model.DefaultParam;
import com.she.env.envEffectEval.model.EnvEffectEval;
import com.she.env.envEffectEval.service.EnvEffectEvalService;
import com.she.utils.RequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/env/envEffectEval")
public class EnvEffectEvalController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private EnvEffectEvalService envEffectEvalService;

    /**
     * 환경 > 환경영향평가 > 환경영향평가 목록 조회
     *
     * @param parameter 검색필드
     * @return 환경영향평가 목록
     * @throws Exception
     */
    @GetMapping("/envEffectEvals")
    public ResponseEntity<List<EnvEffectEval>> getEnvEffectEvals(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String evalGubun = map.containsKey("evalGubun") ? map.get("evalGubun").toString() : "";
        String evalProgState = map.containsKey("evalProgState") ? map.get("evalProgState").toString() : "";
        String evalStepCd = map.containsKey("evalStepCd") ? map.get("evalStepCd").toString() : "";
        String proxyVendorCd = map.containsKey("proxyVendorCd") ? map.get("proxyVendorCd").toString() : "";
        String businessName = map.containsKey("businessName") ? map.get("businessName").toString() : "";

        return ResponseEntity.ok().body(envEffectEvalService.getEnvEffectEvals(plantCd, deptCd, evalGubun, evalProgState, evalStepCd, proxyVendorCd, businessName, defaultParam));
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 조회
     *
     * @param envEffectEvalNo
     * @return 환경영향평가
     * @throws Exception
     */
    @GetMapping("/envEffectEval/{envEffectEvalNo}")
    public ResponseEntity<EnvEffectEval> getEnvEffectEval(@PathVariable(name = "envEffectEvalNo") int envEffectEvalNo) throws Exception {

        return ResponseEntity.ok().body(envEffectEvalService.getEnvEffectEval(envEffectEvalNo));
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 등록
     *
     * @param EnvEffectEval envEffectEval
     * @return 환경영향평가 번호
     * @throws Exception
     */
    @PostMapping("/envEffectEvals")
    public ResponseEntity<Integer> createEnvEffectEval(@RequestBody EnvEffectEval envEffectEval) throws Exception {
        return ResponseEntity.ok().body(this.envEffectEvalService.createEnvEffectEval(envEffectEval));
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 수정
     *
     * @param EnvEffectEval envEffectEval
     * @return 환경영향평가 번호
     * @throws Exception
     */
    @PutMapping("/envEffectEval")
    public ResponseEntity<Integer> updateEnvEffectEval(@RequestBody EnvEffectEval envEffectEval) throws Exception {
        return ResponseEntity.ok().body(this.envEffectEvalService.updateEnvEffectEval(envEffectEval));
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가탭 수정
     *
     * @param EnvEffectEval envEffectEval
     * @return 환경영향평가 번호
     * @throws Exception
     */
    @PutMapping("/envEffectEval/tabs")
    public ResponseEntity<Integer> updateEnvEffectEvalTab(@RequestBody EnvEffectEval envEffectEval) throws Exception {
        return ResponseEntity.ok().body(this.envEffectEvalService.updateEnvEffectEvalTab(envEffectEval));
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 삭제
     *
     * @param EnvEffectEval envEffectEval
     * @return 삭제행수
     * @throws Exception
     */
    @DeleteMapping("/envEffectEval/{envEffectEvalNo}")
    public ResponseEntity<Integer> deleteEnvEffectEval(@PathVariable(name = "envEffectEvalNo") int envEffectEvalNo) throws Exception {
        return ResponseEntity.ok().body(this.envEffectEvalService.deleteEnvEffectEval(envEffectEvalNo));
    }

}
