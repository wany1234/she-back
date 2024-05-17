package com.she.env.water.facility.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import com.she.env.water.facility.service.WasteDischFacService;
import com.she.env.water.model.WasteDischFac;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/facility/wastedischfac")
public class WasteDischFacController {
    @Autowired
    private WasteDischFacService wasteDischFacService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 폐수배출시설 조회
     *
     * @param useYn
     *            사용여부
     * @return 폐수배출시설 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/wastedischfacs")
    public ResponseEntity<List<WasteDischFac>> getWasteDischFacs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrWasteDischFacNm = map.containsKey("ewtrWasteDischFacNm") ? map.get("ewtrWasteDischFacNm").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt("".equals(map.get("ewtrCleanFacNo").toString()) ? "0" : map.get("ewtrCleanFacNo").toString()) : 0;

        List<WasteDischFac> wasteDischFacList = wasteDischFacService.getWasteDischFacs(useYn, deptCd, plantCd, ewtrWasteDischFacNm, ewtrCleanFacNo, defaultParam);

        return ResponseEntity.ok().body(wasteDischFacList);
    }

    /**
     * 폐수배출시설 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getWasteFacsCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrWasteDischFacNm = map.containsKey("ewtrWasteDischFacNm") ? map.get("ewtrWasteDischFacNm").toString() : "";
        int ewtrWasteDischFacNo = map.containsKey("ewtrWasteDischFacNo") ? Integer.parseInt("".equals(map.get("ewtrWasteDischFacNo").toString()) ? "0" : map.get("ewtrWasteDischFacNo").toString()) : 0;

        return ResponseEntity.ok().body(wasteDischFacService.getWasteFacsCheck(plantCd, ewtrWasteDischFacNm, ewtrWasteDischFacNo));
    }

    /**
     * 폐수배출시설 상세조회
     *
     * @param ewtrWasteDischFacNo
     *            폐수배출시설번호
     * @return WasteDischFac 폐수배출시설
     * @throws Exception
     *             예외
     */
    @GetMapping("/wastedischfac/{ewtrWasteDischFacNo}")
    public ResponseEntity<WasteDischFac> getWasteDischFac(@PathVariable("ewtrWasteDischFacNo") int ewtrWasteDischFacNo) throws Exception {
        WasteDischFac wasteDischFac = wasteDischFacService.getWasteDischFac(ewtrWasteDischFacNo);
        return ResponseEntity.ok().body(wasteDischFac);
    }

    /**
     * 폐수배출시설 신규등록
     *
     * @param WasteDischFac
     *            폐수배출시설
     * @return ewtrWasteDischFacNo 폐수배출시설번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/wastedischfac")
    public ResponseEntity<Integer> createWasteDischFac(@RequestBody WasteDischFac wasteDischFac) throws Exception {
        return ResponseEntity.ok().body(wasteDischFacService.createWasteDischFac(wasteDischFac));
    }

    /**
     * 폐수배출시설 수정
     *
     * @param WasteDischFac
     *            폐수배출시설
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/wastedischfac")
    public ResponseEntity<Integer> updateWasteDischFac(@RequestBody WasteDischFac wasteDischFac) throws Exception {
        return ResponseEntity.ok().body(wasteDischFacService.updateWasteDischFac(wasteDischFac));
    }
}
