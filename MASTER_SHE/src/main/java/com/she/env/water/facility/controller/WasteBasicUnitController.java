package com.she.env.water.facility.controller;

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
import com.she.env.water.baseInfo.service.DischargeService;
import com.she.env.water.baseInfo.service.IngredientService;
import com.she.env.water.baseInfo.service.MonPosService;
import com.she.env.water.baseInfo.service.SupplyService;
import com.she.env.water.facility.service.WasteBasicUnitService;
import com.she.env.water.model.WasteBasicUnit;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/facility/wastebasicunit")
public class WasteBasicUnitController {

    @Autowired
    private WasteBasicUnitService wasteBasicUnitService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ChemicalService chemicalService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private DischargeService dischargeService;

    @Autowired
    private MonPosService monPosService;

    @Autowired
    private RequestMapper requestMapper;

    @GetMapping("/wastebasicunits")
    public ResponseEntity<List<WasteBasicUnit>> getWasteBasicUnits(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String ewtrCleanFacNm = map.containsKey("ewtrCleanFacNm") ? map.get("ewtrCleanFacNm").toString() : ""; // 시설명
        String ewtrCleanFacClassCd = map.containsKey("ewtrCleanFacClassCd") ? map.get("ewtrCleanFacClassCd").toString() : "";
        String ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? map.get("ewtrCleanFacNo").toString() : ""; // 시설번호
        List<WasteBasicUnit> wasteBasicUnitList = wasteBasicUnitService.getWasteBasicUnits(plantCd, useYn, deptCd, ewtrCleanFacNm, ewtrCleanFacClassCd, ewtrCleanFacNo, defaultParam);

        return ResponseEntity.ok().body(wasteBasicUnitList);
    }

    @GetMapping("/wastebasicunit/{ewtrCleanFacNo}")
    public ResponseEntity<WasteBasicUnit> getWasteBasicUnit(@PathVariable("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception {
        return ResponseEntity.ok().body(wasteBasicUnitService.getWasteBasicUnit(ewtrCleanFacNo));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewtrCleanFacNm = map.containsKey("ewtrCleanFacNm") ? map.get("ewtrCleanFacNm").toString() : ""; // 시설명
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt("".equals(map.get("ewtrCleanFacNo").toString()) ? "0" : map.get("ewtrCleanFacNo").toString()) : 0;

        return ResponseEntity.ok().body(wasteBasicUnitService.getCheck(plantCd, ewtrCleanFacNm, ewtrCleanFacNo));
    }

    @PostMapping("/wastebasicunit")
    public ResponseEntity<Integer> insertWasteBasicUnit(@RequestBody WasteBasicUnit wasteBasicUnit) throws Exception {
        return ResponseEntity.ok().body(wasteBasicUnitService.insertWasteBasicUnit(wasteBasicUnit));

    }

    @PutMapping("/wastebasicunit")
    public ResponseEntity<Integer> updateMonPos(@RequestBody WasteBasicUnit wasteBasicUnit) throws Exception {
        return ResponseEntity.ok().body(wasteBasicUnitService.updateWasteBasicUnit(wasteBasicUnit));
    }

}
