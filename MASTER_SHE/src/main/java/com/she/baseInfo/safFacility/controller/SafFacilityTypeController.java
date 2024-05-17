package com.she.baseInfo.safFacility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.baseInfo.model.SafFacilityType;
import com.she.baseInfo.safFacility.service.SafFacilityTypeService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/baseinfo/saffacility/")
@Api(value = "/api/baseinfo/saffacility/", description = "설비유형")
public class SafFacilityTypeController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SafFacilityTypeService safFacilityTypeService;

    /**
     * 시설유형 리스트 조회
     *
     * @return 시설유형 리스트
     * @throws Exception
     */
    @ApiOperation(value = "시설유형 리스트 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("facilitytypes")
    public ResponseEntity<List<SafFacilityType>> getSafFacilityTypes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 시설유형코드
        String comFacilityCd = map.containsKey("comFacilityCd") ? map.get("comFacilityCd").toString() : "";
        // 시설유형명
        String comFacilityNm = map.containsKey("comFacilityNm") ? map.get("comFacilityNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(safFacilityTypeService.getSafFacilityTypes(comFacilityCd, comFacilityNm, useYn));
    }
}
