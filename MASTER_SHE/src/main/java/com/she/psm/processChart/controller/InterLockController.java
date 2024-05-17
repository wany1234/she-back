package com.she.psm.processChart.controller;

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

import com.she.psm.model.InterLock;
import com.she.psm.processChart.service.InterLockService;
import com.she.utils.RequestMapper;

/**
 * 인터록 작동조건 및 가동중지 범위
 */
@RestController
@RequestMapping("api/psm/processChart")
public class InterLockController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private InterLockService interLockService;

    /**
     * 인터록 작동조건 및 가동중지 범위 조회
     * 
     * @return 인터록 작동조건 및 가동중지 범위 목록
     * @throws Exception
     */
    @GetMapping("/interlocks")
    public ResponseEntity<List<InterLock>> getInterLockLists(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String interLockNo = map.containsKey("interLockNo") ? map.get("interLockNo").toString() : "";
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";

        List<InterLock> interLock = interLockService.getInterLockLists(plantCd, interLockNo, facilityNm);
        return ResponseEntity.ok().body(interLock);
    }

    /**
     * 인터록 작동조건 및 가동중지 범위조회
     * 
     * @param interLockNo
     *            운전상태 No
     * @return 인터록 작동조건 및 가동중지 범위 상세정보
     * @throws Exception
     */
    @GetMapping("/interlock/{interLockSeq}")
    public ResponseEntity<InterLock> getInterLock(@PathVariable(name = "interLockSeq") int interLockSeq)
            throws Exception {
        return ResponseEntity.ok().body(interLockService.getInterLock(interLockSeq));
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 중복체크
     * 
     * @param interLockSeq
     *            인터록 Seq
     * @return 동일 인터록 수량
     * @throws Exception
     */
    @GetMapping("/interlockcheck/{interLockSeq}/{interLockNo}")
    public ResponseEntity<Integer> checkInterLock(@PathVariable(name = "interLockSeq") int interLockSeq,
            @PathVariable(name = "interLockNo") String interLockNo) throws Exception {
        return ResponseEntity.ok().body(interLockService.checkInterLock(interLockSeq, interLockNo));
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 생성
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 인터록 작동조건 및 가동중지 범위 항목 Key값
     * @throws Exception
     */
    @PostMapping("/interlock")
    public ResponseEntity<Integer> createInterLock(@RequestBody InterLock interLock) throws Exception {
        return ResponseEntity.ok().body(interLockService.createInterLock(interLock));
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 수정
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 인터록 작동조건 및 가동중지 범위 항목 Key값
     * @throws Exception
     */
    @PutMapping("/interlock")
    public ResponseEntity<Integer> updateInterLock(@RequestBody InterLock interLock) throws Exception {
        return ResponseEntity.ok().body(interLockService.updateInterLock(interLock));
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 삭제
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    @DeleteMapping("/interlock")
    public ResponseEntity<Integer> deleteInterLock(@RequestBody InterLock interLock) throws Exception {
        return ResponseEntity.ok().body(interLockService.deleteInterLock(interLock));
    }
}
