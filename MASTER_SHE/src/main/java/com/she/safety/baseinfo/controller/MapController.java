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
package com.she.safety.baseinfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.safety.baseinfo.service.MapService;
import com.she.safety.model.Map;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 안전 기준정보 지도 관리 항목
 *
 */
@RestController
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "안전 기준정보 서비스")
public class MapController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private MapService mapService;

    /**
     * 안전 기준정보 지도 조회
     * 
     * @param parameter
     * @return 안전 기준정보 지도 목록
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 지도 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("map")
    public ResponseEntity<List<Map>> getMaps(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 지도이름
        String mapName = map.containsKey("mapName") ? map.get("mapName").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(mapService.getMaps(plantCd, mapName, useYn));
    }

    /**
     * 사업부 기준 메인 표출될 MAP ID 조회
     *
     * @param plantCd
     *            사업부코드
     * @return 지도ID
     * @throws Exception
     */
    @GetMapping("getMapIdByPlantCd")
    public ResponseEntity<Map> getMapIdByPlantCd(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.mapService.getMapIdByPlantCd(plantCd));
    }

    /**
     * 안전 기준정보 지도 등록
     * 
     * @param map
     * @return 등록 행 수
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 지도 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("map")
    public ResponseEntity<Integer> createMap(@RequestBody Map map) throws Exception {
        return ResponseEntity.ok().body(mapService.createMap(map));
    }

    /**
     * 안전 기준정보 지도 수정
     *
     * @param map
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "안전 기준정보 지도 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PutMapping("map")
    public ResponseEntity<Integer> updateMap(@RequestBody Map map) throws Exception {
        return ResponseEntity.ok().body(mapService.updateMap(map));
    }
}
