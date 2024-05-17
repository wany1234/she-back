package com.she.chm.chem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.she.chm.chem.service.ChemStatService;
import com.she.chm.model.ChemicalByRegulItmRes;
import com.she.chm.model.ChemicalProdRegulRes;
import com.she.chm.model.ChemprodByRegulItmRes;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/chem/stat")
public class ChemStatController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemStatService chemStatService;

    /**
     * 취급자재 규제현황
     *
     * @param parameter
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    @GetMapping("/chemicalprodregul")
    public ResponseEntity<List<ChemicalProdRegulRes>> getChemicalProdRegul(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 규제항목
        int[] regulItmNos = this.requestMapper.convertObjectListAsIntArray(map.get("regulItmNos"));

        List<ChemicalProdRegulRes> chemicalProdRegulRes = this.chemStatService.getChemicalProdRegul(regulItmNos, defaultParam);

        return ResponseEntity.ok().body(chemicalProdRegulRes);
    }

    /**
     * 취급자재 규제현황 : 규제항목별 취급자재 개수
     *
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    @GetMapping("/chemprodbyregulitmno/{regulItmNo}")
    public ResponseEntity<Map<String, Object>> getChemprodByRegulItmNo(@PathVariable(name = "regulItmNo") int regulItmNo, @RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;
        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<ChemprodByRegulItmRes> body = this.chemStatService.getChemprodByRegulItmNo(regulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);

        returnMap.put("items", body);
        returnMap.put("count", body != null && body.size() > 0 ? body.get(0).getTotalCnt() : 0);
        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 취급자재 규제현황 : 규제항목별 화학물질 개수
     *
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    @GetMapping("/chemicalsbyregulitmno/{regulItmNo}")
    public ResponseEntity<Map<String, Object>> getChemicalsByRegulItmNo(@PathVariable(name = "regulItmNo") int regulItmNo, @RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;
        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<ChemicalByRegulItmRes> body = this.chemStatService.getChemicalsByRegulItmNo(regulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);

        returnMap.put("items", body);
        returnMap.put("count", body != null && body.size() > 0 ? body.get(0).getTotalCnt() : 0);
        return ResponseEntity.ok().body(returnMap);
    }

}
