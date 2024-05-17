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

import com.she.env.gas.baseInfo.service.GasTypeService;
import com.she.env.gas.model.GasType;
import com.she.utils.RequestMapper;

@RestController
public class GasTypeController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private GasTypeService gasTypeService;

    /**
     * 온실가스코드 조회
     * 
     * @param parameter
     *            검색조건
     * @return 온실가스코드 목록
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/gastypes")
    public ResponseEntity<List<GasType>> getGasTypes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 온실가스명
        String gasTypeNm = map.containsKey("gasTypeNm") ? map.get("gasTypeNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(gasTypeService.getGasTypes(gasTypeNm, useYn));
    }

    /**
     * 온실가스코드 상세조회
     * 
     * @param gasTypeCd
     *            온실가스코드
     * @return 온실가스코드 상세내역
     * @throws Exception
     */
    @GetMapping("api/env/gas/baseinfo/gastype/{gasTypeCd}")
    public ResponseEntity<GasType> getGasType(@PathVariable("gasTypeCd") String gasTypeCd) throws Exception {
        return ResponseEntity.ok().body(this.gasTypeService.getGasType(gasTypeCd));
    }

    /**
     * 온실가스코드 체크
     * 
     * @param gasTypeCd
     *            온실가스코드
     * @return 온실가스 중복코드 갯수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/baseinfo/count/gastype/{gasTypeCd}")
    public ResponseEntity<Integer> countGasType(@PathVariable("gasTypeCd") String gasTypeCd) throws Exception {
        return ResponseEntity.ok(gasTypeService.countGasType(gasTypeCd));
    }

    /**
     * 온실가스코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 온실가스코드
     * @throws Exception
     */
    @PostMapping("api/env/gas/baseinfo/gastype")
    public ResponseEntity<String> createGasType(@RequestBody GasType gasType) throws Exception {
        return ResponseEntity.ok().body(this.gasTypeService.createGasType(gasType));
    }

    /**
     * 온실가스코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 온실가스코드
     * @throws Exception
     */
    @PutMapping("api/env/gas/baseinfo/gastype")
    public ResponseEntity<String> updateGasType(@RequestBody GasType gasType) throws Exception {
        return ResponseEntity.ok().body(this.gasTypeService.updateGasType(gasType));
    }
}
