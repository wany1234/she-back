package com.she.psm.buildingFacilitiesLayout.controller;

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

import com.she.psm.buildingFacilitiesLayout.service.WashupService;
import com.she.psm.model.Washup;
import com.she.utils.RequestMapper;

/**
 * 세척ㆍ세안 시설 및 안전 보호장구 설치계획
 */
@RestController
@RequestMapping("api/psm/buildingFacilitiesLayout")
public class WashupController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WashupService washupService;

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 조회
     * 
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 목록
     * @throws Exception
     */
    @GetMapping("/washups")
    public ResponseEntity<List<Washup>> getWashupLists(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String usage = map.containsKey("usage") ? map.get("usage").toString() : "";
        String location = map.containsKey("location") ? map.get("location").toString() : "";

        List<Washup> washup = washupService.getWashupLists(plantCd, usage, location);
        return ResponseEntity.ok().body(washup);
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획조회
     * 
     * @param washupNo
     *            운전상태 No
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 상세정보
     * @throws Exception
     */
    @GetMapping("/washup/{washupNo}")
    public ResponseEntity<Washup> getWashup(@PathVariable(name = "washupNo") int washupNo) throws Exception {
        return ResponseEntity.ok().body(washupService.getWashup(washupNo));
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 생성
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 Key값
     * @throws Exception
     */
    @PostMapping("/washup")
    public ResponseEntity<Integer> createWashup(@RequestBody Washup washup) throws Exception {
        return ResponseEntity.ok().body(washupService.createWashup(washup));
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 수정
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 Key값
     * @throws Exception
     */
    @PutMapping("/washup")
    public ResponseEntity<Integer> updateWashup(@RequestBody Washup washup) throws Exception {
        return ResponseEntity.ok().body(washupService.updateWashup(washup));
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 삭제
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/washup")
    public ResponseEntity<Integer> deleteWashup(@RequestBody Washup washup) throws Exception {
        return ResponseEntity.ok().body(washupService.deleteWashup(washup));
    }
}
