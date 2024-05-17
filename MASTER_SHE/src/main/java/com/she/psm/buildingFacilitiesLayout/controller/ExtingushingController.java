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

import com.she.psm.buildingFacilitiesLayout.service.ExtingushingService;
import com.she.psm.model.Extingushing;
import com.she.utils.RequestMapper;

/**
 * 소화설비 설치계획
 */
@RestController
@RequestMapping("api/psm/buildingFacilitiesLayout")
public class ExtingushingController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ExtingushingService extingushingService;

    /**
     * 소화설비 설치계획 조회
     * 
     * @return 소화설비 설치계획 목록
     * @throws Exception
     */
    @GetMapping("/extingushings")
    public ResponseEntity<List<Extingushing>> getExtingushingLists(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String location = map.containsKey("location") ? map.get("location").toString() : "";

        List<Extingushing> extingushing = extingushingService.getExtingushingLists(plantCd, location);
        return ResponseEntity.ok().body(extingushing);
    }

    /**
     * 소화설비 설치계획조회
     * 
     * @param extingushingNo
     *            운전상태 No
     * @return 소화설비 설치계획 상세정보
     * @throws Exception
     */
    @GetMapping("/extingushing/{extingushingNo}")
    public ResponseEntity<Extingushing> getExtingushing(@PathVariable(name = "extingushingNo") int extingushingNo)
            throws Exception {
        return ResponseEntity.ok().body(extingushingService.getExtingushing(extingushingNo));
    }

    /**
     * 소화설비 설치계획 항목 생성
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 소화설비 설치계획 항목 Key값
     * @throws Exception
     */
    @PostMapping("/extingushing")
    public ResponseEntity<Integer> createExtingushing(@RequestBody Extingushing extingushing) throws Exception {
        return ResponseEntity.ok().body(extingushingService.createExtingushing(extingushing));
    }

    /**
     * 소화설비 설치계획 항목 수정
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 소화설비 설치계획 항목 Key값
     * @throws Exception
     */
    @PutMapping("/extingushing")
    public ResponseEntity<Integer> updateExtingushing(@RequestBody Extingushing extingushing) throws Exception {
        return ResponseEntity.ok().body(extingushingService.updateExtingushing(extingushing));
    }

    /**
     * 소화설비 설치계획 항목 삭제
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/extingushing")
    public ResponseEntity<Integer> deleteExtingushing(@RequestBody Extingushing extingushing) throws Exception {
        return ResponseEntity.ok().body(extingushingService.deleteExtingushing(extingushing));
    }
}
