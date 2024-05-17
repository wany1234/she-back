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

import com.she.psm.buildingFacilitiesLayout.service.FireDetectionService;
import com.she.psm.model.FireDetection;
import com.she.utils.RequestMapper;

/**
 * 화재탐지 및 경보설비 설치계획
 */
@RestController
@RequestMapping("api/psm/buildingFacilitiesLayout")
public class FireDetectionController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FireDetectionService fireDetectionService;

    /**
     * 화재탐지 및 경보설비 설치계획 조회
     * 
     * @return 화재탐지 및 경보설비 설치계획 목록
     * @throws Exception
     */
    @GetMapping("/fireDetections")
    public ResponseEntity<List<FireDetection>> getFireDetectionLists(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String location = map.containsKey("location") ? map.get("location").toString() : "";

        List<FireDetection> fireDetection = fireDetectionService.getFireDetectionLists(plantCd, location);
        return ResponseEntity.ok().body(fireDetection);
    }

    /**
     * 화재탐지 및 경보설비 설치계획조회
     * 
     * @param fireDetectionNo
     *            운전상태 No
     * @return 화재탐지 및 경보설비 설치계획 상세정보
     * @throws Exception
     */
    @GetMapping("/fireDetection/{fireDetectionNo}")
    public ResponseEntity<FireDetection> getFireDetection(@PathVariable(name = "fireDetectionNo") int fireDetectionNo)
            throws Exception {
        return ResponseEntity.ok().body(fireDetectionService.getFireDetection(fireDetectionNo));
    }

    /**
     * 화재탐지 및 경보설비 설치계획 항목 생성
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 화재탐지 및 경보설비 설치계획 항목 Key값
     * @throws Exception
     */
    @PostMapping("/fireDetection")
    public ResponseEntity<Integer> createFireDetection(@RequestBody FireDetection fireDetection) throws Exception {
        return ResponseEntity.ok().body(fireDetectionService.createFireDetection(fireDetection));
    }

    /**
     * 화재탐지 및 경보설비 설치계획 항목 수정
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 화재탐지 및 경보설비 설치계획 항목 Key값
     * @throws Exception
     */
    @PutMapping("/fireDetection")
    public ResponseEntity<Integer> updateFireDetection(@RequestBody FireDetection fireDetection) throws Exception {
        return ResponseEntity.ok().body(fireDetectionService.updateFireDetection(fireDetection));
    }

    /**
     * 화재탐지 및 경보설비 설치계획 항목 삭제
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/fireDetection")
    public ResponseEntity<Integer> deleteFireDetection(@RequestBody FireDetection fireDetection) throws Exception {
        return ResponseEntity.ok().body(fireDetectionService.deleteFireDetection(fireDetection));
    }
}
