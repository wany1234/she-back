package com.she.env.water.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.service.ChemicalService;
import com.she.env.water.model.Chemical;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/baseinfo/chemical")
public class ChemicalController {
    @Autowired
    private ChemicalService chemicalService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 수질약품 조회
     *
     * @param useYn
     *            사용여부
     * @return 수질약품 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/chemicals")
    public ResponseEntity<List<Chemical>> getChemicals(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String title = map.containsKey("title") ? map.get("title").toString() : "";
        List<Chemical> chemicalList = chemicalService.getChemicals(useYn, plantCd, title, defaultParam);

        return ResponseEntity.ok().body(chemicalList);
    }

    /**
     * 수질약품 상세조회
     *
     * @param ewtrChemNo
     *            수질약품번호
     * @return Chemical 수질약품
     * @throws Exception
     *             예외
     */
    @GetMapping("/chemical/{ewtrChemNo}")
    public ResponseEntity<Chemical> getChemical(@PathVariable("ewtrChemNo") int ewtrChemNo) throws Exception {
        Chemical chemical = chemicalService.getChemical(ewtrChemNo);
        return ResponseEntity.ok().body(chemical);
    }

    /**
     * 수질약품 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    @GetMapping("/checkChemical")
    public ResponseEntity<Integer> getChemicalCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrChemNm = map.containsKey("ewtrChemNm") ? map.get("ewtrChemNm").toString() : "";
        int ewtrChemNo = map.containsKey("ewtrChemNo") ? Integer.parseInt("".equals(map.get("ewtrChemNo").toString()) ? "0" : map.get("ewtrChemNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalService.getChemicalCheck(plantCd, ewtrChemNm, ewtrChemNo));
    }

    /**
     * 수질약품 신규등록
     *
     * @param Chemical
     *            수질약품
     * @return ewtrChemNo 수질약품번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/chemical")
    public ResponseEntity<Integer> createChemical(@RequestBody Chemical chemical) throws Exception {
        return ResponseEntity.ok().body(chemicalService.createChemical(chemical));
    }

    /**
     * 수질약품 수정
     *
     * @param Chemical
     *            수질약품
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/chemical")
    public ResponseEntity<Integer> updateChemical(@RequestBody Chemical chemical) throws Exception {
        return ResponseEntity.ok().body(chemicalService.updateChemical(chemical));
    }

}
