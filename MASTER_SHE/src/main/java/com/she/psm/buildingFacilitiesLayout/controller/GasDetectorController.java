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

import com.she.psm.buildingFacilitiesLayout.service.GasDetectorService;
import com.she.psm.model.GasDetector;
import com.she.utils.RequestMapper;

/**
 * 가스누출감지 경보기 설치계획
 */
@RestController
@RequestMapping("api/psm/buildingFacilitiesLayout")
public class GasDetectorController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private GasDetectorService gasDetectorService;

    /**
     * 가스누출감지 경보기 설치계획 조회
     * 
     * @return 가스누출감지 경보기 설치계획 목록
     * @throws Exception
     */
    @GetMapping("/gasDetectors")
    public ResponseEntity<List<GasDetector>> getGasDetectorLists(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String mgrNum = map.containsKey("mgrNum") ? map.get("mgrNum").toString() : "";
        String sensingObj = map.containsKey("sensingObj") ? map.get("sensingObj").toString() : "";
        String location = map.containsKey("location") ? map.get("location").toString() : "";

        List<GasDetector> gasDetector = gasDetectorService.getGasDetectorLists(plantCd, mgrNum, sensingObj, location);
        return ResponseEntity.ok().body(gasDetector);
    }

    /**
     * 가스누출감지 경보기 설치계획조회
     * 
     * @param gasDetectorNo
     *            운전상태 No
     * @return 가스누출감지 경보기 설치계획 상세정보
     * @throws Exception
     */
    @GetMapping("/gasDetector/{gasDetectorNo}")
    public ResponseEntity<GasDetector> getGasDetector(@PathVariable(name = "gasDetectorNo") int gasDetectorNo)
            throws Exception {
        return ResponseEntity.ok().body(gasDetectorService.getGasDetector(gasDetectorNo));
    }

    /**
     * 가스누출감지 경보기 설치계획 항목 생성
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 가스누출감지 경보기 설치계획 항목 Key값
     * @throws Exception
     */
    @PostMapping("/gasDetector")
    public ResponseEntity<Integer> createGasDetector(@RequestBody GasDetector gasDetector) throws Exception {
        return ResponseEntity.ok().body(gasDetectorService.createGasDetector(gasDetector));
    }

    /**
     * 가스누출감지 경보기 설치계획 항목 수정
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 가스누출감지 경보기 설치계획 항목 Key값
     * @throws Exception
     */
    @PutMapping("/gasDetector")
    public ResponseEntity<Integer> updateGasDetector(@RequestBody GasDetector gasDetector) throws Exception {
        return ResponseEntity.ok().body(gasDetectorService.updateGasDetector(gasDetector));
    }

    /**
     * 가스누출감지 경보기 설치계획 항목 수정
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/gasDetector")
    public ResponseEntity<Integer> deleteGasDetector(@RequestBody GasDetector gasDetector) throws Exception {
        return ResponseEntity.ok().body(gasDetectorService.deleteGasDetector(gasDetector));
    }
}
