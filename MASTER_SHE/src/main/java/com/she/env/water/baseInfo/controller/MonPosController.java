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
import com.she.env.water.baseInfo.service.MonPosService;
import com.she.env.water.model.MonPos;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/baseinfo/monpos")
public class MonPosController {
    @Autowired
    private MonPosService monPosService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 측정위치 조회
     *
     * @param useYn
     *            사용여부
     * @return 측정위치 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/monposs")
    public ResponseEntity<List<MonPos>> getMonPoss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String ewtrMonPosNm = map.containsKey("ewtrMonPosNm") ? map.get("ewtrMonPosNm").toString() : "";
        List<MonPos> monPosList = monPosService.getMonPoss(useYn, plantCd, deptCd, ewtrMonPosNm, defaultParam);

        return ResponseEntity.ok().body(monPosList);
    }

    /**
     * 측정위치 상세조회
     *
     * @param ewtrMonPosNo
     *            측정위치번호
     * @return MonPos 측정위치
     * @throws Exception
     *             예외
     */
    @GetMapping("/monpos/{ewtrMonPosNo}")
    public ResponseEntity<MonPos> getMonPos(@PathVariable("ewtrMonPosNo") int ewtrMonPosNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        MonPos monPos = monPosService.getMonPos(ewtrMonPosNo, defaultParam);
        monPos.setMonPosTestItem(this.monPosService.getMonPosTestItems(ewtrMonPosNo, "", defaultParam));

        return ResponseEntity.ok().body(monPos);
    }

    /**
     * 측정위치 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/check")
    public ResponseEntity<Integer> getMonPosCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String ewtrMonPosNm = map.containsKey("ewtrMonPosNm") ? map.get("ewtrMonPosNm").toString() : "";
        int ewtrMonPosNo = map.containsKey("ewtrMonPosNo") ? Integer.parseInt("".equals(map.get("ewtrMonPosNo").toString()) ? "0" : map.get("ewtrMonPosNo").toString()) : 0;

        return ResponseEntity.ok().body(monPosService.getMonPosCheck(plantCd, ewtrMonPosNm, deptCd, ewtrMonPosNo));
    }

    /**
     * 측정위치 신규등록
     *
     * @param MonPos
     *            측정위치
     * @return ewtrMonPosNo 측정위치번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/monpos")
    public ResponseEntity<Integer> createMonPos(@RequestBody MonPos monPos) throws Exception {
        return ResponseEntity.ok().body(monPosService.createMonPos(monPos));
    }

    /**
     * 측정위치 수정
     *
     * @param MonPos
     *            측정위치
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/monpos")
    public ResponseEntity<Integer> updateMonPos(@RequestBody MonPos monPos) throws Exception {
        return ResponseEntity.ok().body(monPosService.updateMonPos(monPos));
    }

}
