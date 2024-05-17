package com.she.env.gas.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.gas.model.GhgAlloc;
import com.she.env.gas.service.AllocMgtService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/env/gas/alloc")
public class AllocMgtController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AllocMgtService allocMgtService;

    /**
     * 할당량 관리 신규 조회
     *
     * @param ghgAlloc
     * @return
     * @throws Exception
     */
    @PostMapping("/editalloc")
    public ResponseEntity<Integer> createAllocMgt(@RequestBody GhgAlloc ghgAlloc) throws Exception {
        return ResponseEntity.ok().body(allocMgtService.createAllocMgt(ghgAlloc));
    }

    /**
     * 할당량 관리 수정
     *
     * @param ghgAlloc
     * @return
     * @throws Exception
     */
    @PutMapping("/editalloc")
    public ResponseEntity<Integer> updateAllocMgt(@RequestBody GhgAlloc ghgAlloc) throws Exception {
        return ResponseEntity.ok().body(allocMgtService.updateAllocMgt(ghgAlloc));

    }

    /**
     * 할당량 관리 조회
     *
     * @param plantCd
     * @param fromYear
     * @param toYear
     * @return
     * @throws Exception
     */
    @GetMapping("/getallocs")
    public ResponseEntity<List<GhgAlloc>> getAllocMgts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));

        String fromYear = "";
        String toYear = "";

        if (period != null && period.length == 2) {
            fromYear = period[0];
            toYear = period[1];
        }

        return ResponseEntity.ok().body(allocMgtService.getAllocMgts(plantCd, fromYear, toYear));
    }

    /**
     * 할당량 관리 상세
     *
     * @param ghgAllocNo
     * @return
     * @throws Exception
     */
    @GetMapping("/getalloc/{ghgAllocNo}")
    public ResponseEntity<GhgAlloc> getAllocMgt(@PathVariable int ghgAllocNo) throws Exception {
        return ResponseEntity.ok().body(allocMgtService.getAllocMgt(ghgAllocNo));
    }

    /**
     * 할당량 관리 중복체크
     *
     * @param plantCd
     * @param year
     * @return
     */
    @GetMapping("/checkduple/{plantCd}/{year}")
    public ResponseEntity<Integer> checkDuple(@PathVariable String plantCd, @PathVariable String year) {
        return ResponseEntity.ok().body(allocMgtService.checkDuple(plantCd, year));
    }
}
