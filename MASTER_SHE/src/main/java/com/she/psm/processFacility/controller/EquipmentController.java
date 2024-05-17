/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.psm.processFacility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.psm.processFacility.service.EquipmentService;
import com.she.psm.model.Equipment;
import com.she.utils.RequestMapper;

/**
 * 공정시설정보 - 장치 및 설비
 */
@RestController
@RequestMapping("api/psm/processFacility")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 장치 및 설비 조회
     * 
     * @param parameter
     *            검색조건
     * @return 장치 및 설비 목록
     * @throws Exception
     */
    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getEquipments(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 공정
        // int processNo = map.containsKey("processNo")
        // ? Integer.parseInt("".equals(map.get("processNo").toString()) ? "0" :
        // map.get("processNo").toString())
        // : 0;
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 동력기계번호
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        // 동력기계명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // PSM 설비유형
        String facilityPsmCd = map.containsKey("facilityPsmCd") ? map.get("facilityPsmCd").toString() : "";

        List<Equipment> equipments = equipmentService.getEquipments(plantCd, processCd, safFacilityCd, facilityNm,
                facilityPsmCd);
        return ResponseEntity.ok().body(equipments);
    }

    /**
     * 장치 및 설비 상세 조회
     * 
     * @param safFacilityCd
     *            설비코드
     * @param equipmentNo
     *            설비별 장치번호
     * @return 장치 및 설비 목록
     * @throws Exception
     */
    @GetMapping("/equipment/{safFacilityCd}/{equipmentNo}")
    public ResponseEntity<Equipment> getEquipment(@PathVariable(name = "safFacilityCd") String safFacilityCd,
            @PathVariable(name = "equipmentNo") int equipmentNo) throws Exception {
        return ResponseEntity.ok().body(equipmentService.getEquipment(safFacilityCd, equipmentNo));
    }

    /**
     * 장치 및 설비 등록/수정
     * 
     * @param PowerMachine
     *            장치 및 설비
     * @return 설비별 설비번호
     * @throws Exception
     */
    @PostMapping("/equipment")
    public ResponseEntity<Integer> saveEquipment(@RequestBody Equipment equipment) throws Exception {
        return ResponseEntity.ok().body(equipmentService.saveEquipment(equipment) > 0 ? equipment.getEquipmentNo() : 0);
    }

}
