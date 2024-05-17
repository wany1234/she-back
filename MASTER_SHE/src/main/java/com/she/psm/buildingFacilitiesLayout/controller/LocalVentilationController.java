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

import com.she.psm.buildingFacilitiesLayout.service.LocalVentilationService;
import com.she.psm.model.LocalVentilation;
import com.she.utils.RequestMapper;

/**
 * 국소배기장치 설치계획
 */
@RestController
@RequestMapping("api/psm/buildingFacilitiesLayout")
public class LocalVentilationController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private LocalVentilationService localVentilationService;

    /**
     * 국소배기장치 설치계획 조회
     * 
     * @return 국소배기장치 설치계획 목록
     * @throws Exception
     */
    @GetMapping("/localventilations")
    public ResponseEntity<List<LocalVentilation>> getLocalVentilationLists(
            @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String source = map.containsKey("source") ? map.get("source").toString() : "";
        String processNm = map.containsKey("processNm") ? map.get("processNm").toString() : "";
        String inoutFlag = map.containsKey("inoutFlag") ? map.get("inoutFlag").toString() : "";
        String harmfulType = map.containsKey("harmfulType") ? map.get("harmfulType").toString() : "";

        List<LocalVentilation> localVentilation = localVentilationService.getLocalVentilationLists(plantCd, source,
                processNm, inoutFlag, harmfulType);
        return ResponseEntity.ok().body(localVentilation);
    }

    /**
     * 국소배기장치 설치계획조회
     * 
     * @param localVentilationNo
     *            운전상태 No
     * @return 국소배기장치 설치계획 상세정보
     * @throws Exception
     */
    @GetMapping("/localventilation/{localVentilationNo}")
    public ResponseEntity<LocalVentilation> getLocalVentilation(
            @PathVariable(name = "localVentilationNo") int localVentilationNo) throws Exception {
        return ResponseEntity.ok().body(localVentilationService.getLocalVentilation(localVentilationNo));
    }

    /**
     * 국소배기장치 설치계획 항목 생성
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 국소배기장치 설치계획 항목 Key값
     * @throws Exception
     */
    @PostMapping("/localventilation")
    public ResponseEntity<Integer> createLocalVentilation(@RequestBody LocalVentilation localVentilation)
            throws Exception {
        return ResponseEntity.ok().body(localVentilationService.createLocalVentilation(localVentilation));
    }

    /**
     * 국소배기장치 설치계획 항목 수정
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 국소배기장치 설치계획 항목 Key값
     * @throws Exception
     */
    @PutMapping("/localventilation")
    public ResponseEntity<Integer> updateLocalVentilation(@RequestBody LocalVentilation localVentilation)
            throws Exception {
        return ResponseEntity.ok().body(localVentilationService.updateLocalVentilation(localVentilation));
    }

    /**
     * 국소배기장치 설치계획 항목 삭제
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/localventilation")
    public ResponseEntity<Integer> deleteLocalVentilation(@RequestBody LocalVentilation localVentilation)
            throws Exception {
        return ResponseEntity.ok().body(localVentilationService.deleteLocalVentilation(localVentilation));
    }
}
