package com.she.env.water.facility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.water.facility.service.GuidedService;
import com.she.env.water.model.GuidedHist;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/facility/guided")
public class GuidedController {
    @Autowired
    private GuidedService guidedService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 지도점검 조회
     *
     * @param
     * @return 지도점검 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/guideds")
    public ResponseEntity<List<GuidedHist>> getGuideds() throws Exception {
        List<GuidedHist> guidedList = guidedService.getGuideds();

        return ResponseEntity.ok().body(guidedList);
    }

    /**
     * 지도점검 상세조회
     *
     * @param ewtrGuidedHistNo
     *            지도점검번호
     * @return GuidedHist 지도점검
     * @throws Exception
     *             예외
     */
    @GetMapping("/guided/{measureYmd}/{ewtrCleanFacNo}/{plantCd}/{deptCd}")
    public ResponseEntity<GuidedHist> getGuided(@PathVariable("measureYmd") String measureYmd, @PathVariable("ewtrCleanFacNo") int ewtrCleanFacNo, @PathVariable("plantCd") String plantCd, @PathVariable("deptCd") String deptCd) throws Exception {
        GuidedHist guidedHist = guidedService.getGuided(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
        return ResponseEntity.ok().body(guidedHist);
    }

    /**
     * 지도점검 수정
     *
     * @param GuidedHist
     *            지도점검
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/guided")
    public ResponseEntity<Integer> updateGuided(@RequestBody GuidedHist guidedHist) throws Exception {
        return ResponseEntity.ok().body(guidedService.updateGuided(guidedHist));
    }
}
