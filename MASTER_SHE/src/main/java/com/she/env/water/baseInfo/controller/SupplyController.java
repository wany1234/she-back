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
import com.she.env.water.baseInfo.service.SupplyService;
import com.she.env.water.model.Supply;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/baseinfo/supply")
public class SupplyController {
    @Autowired
    private SupplyService supplyService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 공급수 조회
     *
     * @param parameter
     *            검색조건
     * @return 공급수 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/supplys")
    public ResponseEntity<List<Supply>> getSupplys(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? map.get("ewtrCleanFacNo").toString() : "";
        String title = map.containsKey("title") ? map.get("title").toString() : "";
        List<Supply> supplyList = supplyService.getSupplys(useYn, plantCd, ewtrCleanFacNo, title, defaultParam);

        return ResponseEntity.ok().body(supplyList);
    }

    /**
     * 공급수 상세조회
     *
     * @param ewtrSuplNo
     *            공급수번호
     * @return Supply 공급수
     * @throws Exception
     *             예외
     */
    @GetMapping("/supply/{ewtrSuplNo}")
    public ResponseEntity<Supply> getSupply(@PathVariable("ewtrSuplNo") int ewtrSuplNo) throws Exception {
        Supply supply = supplyService.getSupply(ewtrSuplNo);
        return ResponseEntity.ok().body(supply);
    }

    /**
     * 공급수 체크
     *
     * @return 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/supplycheck")
    public ResponseEntity<HashMap<String, Object>> getSupplyCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrSuplClassCd = map.containsKey("ewtrSuplClassCd") ? map.get("ewtrSuplClassCd").toString() : "";
        String ewtrSuplNm = map.containsKey("ewtrSuplNm") ? map.get("ewtrSuplNm").toString() : "";
        int ewtrSuplNo = map.containsKey("ewtrSuplNo") ? Integer.parseInt("".equals(map.get("ewtrSuplNo").toString()) ? "0" : map.get("ewtrSuplNo").toString()) : 0;

        return ResponseEntity.ok().body(supplyService.getSupplyCheck(plantCd, ewtrSuplClassCd, ewtrSuplNm, ewtrSuplNo));
    }

    /**
     * 공급수 신규등록
     *
     * @param Supply
     *            공급수
     * @return ewtrSuplNo 공급수번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/supply")
    public ResponseEntity<Integer> createSupply(@RequestBody Supply supply) throws Exception {
        return ResponseEntity.ok().body(supplyService.createSupply(supply));
    }

    /**
     * 공급수 수정
     *
     * @param Supply
     *            공급수
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/supply")
    public ResponseEntity<Integer> updateSupply(@RequestBody Supply supply) throws Exception {
        return ResponseEntity.ok().body(supplyService.updateSupply(supply));
    }
}
