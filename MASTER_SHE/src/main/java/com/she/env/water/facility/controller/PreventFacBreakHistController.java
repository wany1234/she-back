package com.she.env.water.facility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.water.facility.service.PreventFacBreakHistService;
import com.she.env.water.model.PreventFacBreakHist;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/facility/preventfacbreakhist")
public class PreventFacBreakHistController {
    @Autowired
    private PreventFacBreakHistService preventFacBreakHistService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 고장이력정보 조회
     *
     * @param ewtrPreventFacNo
     *            오염방지시설번호
     * @return 고장이력정보 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/preventfacbreakhists")
    public ResponseEntity<List<PreventFacBreakHist>> getPreventFacBreakHists(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int ewtrPreventFacNo = map.containsKey("ewtrPreventFacNo") ? Integer.parseInt("".equals(map.get("ewtrPreventFacNo").toString()) ? "0" : map.get("ewtrPreventFacNo").toString()) : 0;
        List<PreventFacBreakHist> preventFacBreakHistList = preventFacBreakHistService.getPreventFacBreakHists(ewtrPreventFacNo, "");

        return ResponseEntity.ok().body(preventFacBreakHistList);
    }

    /**
     * 고장이력정보 상세조회
     *
     * @param ewtrPreventFacBreakHistNo
     *            고장이력정보번호
     * @return PreventFacBreakHist 고장이력정보
     * @throws Exception
     *             예외
     */
    @GetMapping("/preventfacbreakhist/{ewtrPreventFacBreakHistNo}")
    public ResponseEntity<PreventFacBreakHist> getPreventFacBreakHist(@PathVariable("ewtrPreventFacBreakHistNo") int ewtrPreventFacBreakHistNo) throws Exception {
        PreventFacBreakHist preventFacBreakHist = preventFacBreakHistService.getPreventFacBreakHist(ewtrPreventFacBreakHistNo);
        return ResponseEntity.ok().body(preventFacBreakHist);
    }

    /**
     * 고장이력정보 신규등록
     *
     * @param PreventFacBreakHist
     *            고장이력정보
     * @return ewtrPreventFacBreakHistNo 고장이력정보번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/preventfacbreakhist")
    public ResponseEntity<Integer> createPreventFacBreakHist(@RequestBody PreventFacBreakHist preventFacBreakHist) throws Exception {
        return ResponseEntity.ok().body(preventFacBreakHistService.createPreventFacBreakHist(preventFacBreakHist));
    }

    /**
     * 고장이력정보 수정
     *
     * @param PreventFacBreakHist
     *            고장이력정보
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/preventfacbreakhist")
    public ResponseEntity<Integer> updatePreventFacBreakHist(@RequestBody PreventFacBreakHist preventFacBreakHist) throws Exception {
        return ResponseEntity.ok().body(preventFacBreakHistService.updatePreventFacBreakHist(preventFacBreakHist));
    }

    /**
     * 선택된 고장이력정보 삭제
     *
     * @param List<PreventFacBreakHist>
     * @return 삭제행수
     * @throws Exception
     */
    @DeleteMapping("/preventfacbreakhist")
    public ResponseEntity<Integer> deletePreventFacBreakHist(@RequestBody List<PreventFacBreakHist> preventFacBreakHists) throws Exception {
        return ResponseEntity.ok().body(preventFacBreakHistService.deletePreventFacBreakHist(preventFacBreakHists));
    }
}
