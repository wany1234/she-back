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

import com.she.psm.buildingFacilitiesLayout.service.FireProofsService;
import com.she.psm.model.FireProofs;
import com.she.utils.RequestMapper;

/**
 * 내화구조명세
 */
@RestController
@RequestMapping("api/psm/buildingFacilitiesLayout")
public class FireProofsController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FireProofsService fireProofsService;

    /**
     * 내화구조명세 조회
     * 
     * @return 내화구조명세 목록
     * @throws Exception
     */
    @GetMapping("/fireproofses")
    public ResponseEntity<List<FireProofs>> getFireProofsLists(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String category = map.containsKey("category") ? map.get("category").toString() : "";
        String fireProofsNum = map.containsKey("fireProofsNum") ? map.get("fireProofsNum").toString() : "";
        String fireProofsNm = map.containsKey("fireProofsNm") ? map.get("fireProofsNm").toString() : "";

        List<FireProofs> fireProofs = fireProofsService.getFireProofsLists(plantCd, category, fireProofsNum,
                fireProofsNm);
        return ResponseEntity.ok().body(fireProofs);
    }

    /**
     * 내화구조명세조회
     * 
     * @param fireProofsNo
     *            운전상태 No
     * @return 내화구조명세 상세정보
     * @throws Exception
     */
    @GetMapping("/fireproofs/{fireProofsNo}")
    public ResponseEntity<FireProofs> getFireProofs(@PathVariable(name = "fireProofsNo") int fireProofsNo)
            throws Exception {
        return ResponseEntity.ok().body(fireProofsService.getFireProofs(fireProofsNo));
    }

    /**
     * 내화구조명세 항목 생성
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 내화구조명세 항목 Key값
     * @throws Exception
     */
    @PostMapping("/fireproofs")
    public ResponseEntity<Integer> createFireProofs(@RequestBody FireProofs fireProofs) throws Exception {
        return ResponseEntity.ok().body(fireProofsService.createFireProofs(fireProofs));
    }

    /**
     * 내화구조명세 항목 수정
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 내화구조명세 항목 Key값
     * @throws Exception
     */
    @PutMapping("/fireproofs")
    public ResponseEntity<Integer> updateFireProofs(@RequestBody FireProofs fireProofs) throws Exception {
        return ResponseEntity.ok().body(fireProofsService.updateFireProofs(fireProofs));
    }

    /**
     * 내화구조명세 항목 삭제
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/fireproofs")
    public ResponseEntity<Integer> deleteFireProofs(@RequestBody FireProofs fireProofs) throws Exception {
        return ResponseEntity.ok().body(fireProofsService.deleteFireProofs(fireProofs));
    }
}
