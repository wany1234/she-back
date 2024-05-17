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

import com.she.env.water.facility.service.PreventFacService;
import com.she.env.water.model.PreventFac;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/facility/preventfac")
public class PreventFacController {
    @Autowired
    private PreventFacService preventFacService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 방지시설 조회
     *
     * @param useYn
     *            사용여부
     * @return 방지시설 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/preventfacs")
    public ResponseEntity<List<PreventFac>> getPreventFacs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrPreventFacNm = map.containsKey("ewtrPreventFacNm") ? map.get("ewtrPreventFacNm").toString() : "";

        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt("".equals(map.get("ewtrCleanFacNo").toString()) ? "0" : map.get("ewtrCleanFacNo").toString()) : 0;

        List<PreventFac> preventFacList = preventFacService.getPreventFacs(useYn, deptCd, plantCd, ewtrPreventFacNm, ewtrCleanFacNo, defaultParam);

        return ResponseEntity.ok().body(preventFacList);
    }

    /**
     * 방지시설 상세조회
     *
     * @param ewtrPreventFacNo
     *            방지시설번호
     * @return PreventFac 방지시설
     * @throws Exception
     *             예외
     */
    @GetMapping("/preventfac/{ewtrPreventFacNo}")
    public ResponseEntity<PreventFac> getPreventFac(@PathVariable("ewtrPreventFacNo") int ewtrPreventFacNo) throws Exception {
        PreventFac preventFac = preventFacService.getPreventFac(ewtrPreventFacNo);
        return ResponseEntity.ok().body(preventFac);
    }

    /**
     * 방지시설 신규등록
     *
     * @param PreventFac
     *            방지시설
     * @return ewtrPreventFacNo 방지시설번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/preventfac")
    public ResponseEntity<Integer> createPreventFac(@RequestBody PreventFac preventFac) throws Exception {
        return ResponseEntity.ok().body(preventFacService.createPreventFac(preventFac));
    }

    /**
     * 방지시설 수정
     *
     * @param PreventFac
     *            방지시설
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/preventfac")
    public ResponseEntity<Integer> updatePreventFac(@RequestBody PreventFac preventFac) throws Exception {
        return ResponseEntity.ok().body(preventFacService.updatePreventFac(preventFac));
    }
}
