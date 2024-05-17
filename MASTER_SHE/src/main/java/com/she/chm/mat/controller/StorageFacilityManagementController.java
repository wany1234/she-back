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
package com.she.chm.mat.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.she.chm.mat.service.StorageFacilityManagementService;
import com.she.chm.model.MatInoutStatus;
import com.she.chm.model.StorageFacilityManagement;
import com.she.chm.model.StorageFacilityManagementDgrPerm;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/chm/mat/storage")
@Api(value = "/api/chm/mat/storage", description = "저장위치 서비스")
public class StorageFacilityManagementController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private StorageFacilityManagementService storageFacilityManagementService;

    /**
     * 저장위치 조회
     *
     * @param parameter
     *            검색조건
     * @return 저장위치 목록
     * @throws Exception
     */
    @ApiOperation(value = "저장위치 조회[CHM07001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "대표사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "location", value = "설치위치", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "matStrgNm", value = "저장위치명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/storagefacilitymanagements")
    public ResponseEntity<List<StorageFacilityManagement>> getStorageFacilityManagements(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 대표사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 설치위치
        String location = map.containsKey("location") ? map.get("location").toString() : "";
        // 저장위치명
        String matStrgNm = map.containsKey("matStrgNm") ? map.get("matStrgNm").toString() : "";

        return ResponseEntity.ok().body(storageFacilityManagementService.getStorageFacilityManagements(plantCd, location, matStrgNm, defaultParam));
    }

    /**
     * 저장위치 상세조회
     *
     * @param matStrgCd
     *            저장위치코드
     * @return 저장위치
     * @throws Exception
     */
    @ApiOperation(value = "저장위치 상세조회[CHM07002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "matStrgCd", value = "저장위치코드", required = false, dataType = "string", paramType = "path"), })
    @GetMapping("/storagefacilitymanagement/{plantCd}/{matStrgCd}")
    public ResponseEntity<StorageFacilityManagement> getStorageFacilityManagement(@PathVariable("plantCd") String plantCd, @PathVariable("matStrgCd") String matStrgCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(storageFacilityManagementService.getStorageFacilityManagement(plantCd, matStrgCd, defaultParam));
    }

    /**
     * 저장위치 키값 체크
     *
     * @param parameter
     *            검색조건
     * @return 저장위치 목록
     * @throws Exception
     */
    @ApiOperation(value = "저장위치 키값 체크[CHM07001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "대표사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "matStrgCd", value = "저장위치코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/storagefacilitymanagementcheck")
    public ResponseEntity<HashMap<String, Object>> getStorageFacilityManagementCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 대표사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 저장위치명
        String matStrgCd = map.containsKey("matStrgCd") ? map.get("matStrgCd").toString() : "";

        return ResponseEntity.ok().body(storageFacilityManagementService.getStorageFacilityManagementCheck(plantCd, matStrgCd));
    }

    /**
     * 저장위치 등록
     *
     * @param storageFacilityManagement
     *            저장위치
     * @return 키
     */
    @ApiOperation(value = "저장위치 등록[CHM01003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "storageFacilityManagement", value = "저장위치", required = false, dataType = "StorageFacilityManagement", paramType = "body") })
    @PostMapping("storagefacilitymanagement")
    public ResponseEntity<String> createStorageFacilityManagement(@RequestBody StorageFacilityManagement storageFacilityManagement) throws Exception {
        return ResponseEntity.ok().body(storageFacilityManagementService.createStorageFacilityManagement(storageFacilityManagement) > 0 ? storageFacilityManagement.getMatStrgCd() : "");
    }

    /**
     * 저장위치 저장
     *
     * @param storageFacilityManagement
     *            저장위치
     * @return 키
     */
    @ApiOperation(value = "저장위치 수정[CHM01004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "storageFacilityManagement", value = "저장위치", required = false, dataType = "StorageFacilityManagement", paramType = "body") })
    @PutMapping("storagefacilitymanagement")
    public ResponseEntity<String> updateStorageFacilityManagement(@RequestBody StorageFacilityManagement storageFacilityManagement) throws Exception {
        return ResponseEntity.ok().body(storageFacilityManagementService.updateStorageFacilityManagement(storageFacilityManagement) > 0 ? storageFacilityManagement.getMatStrgCd() : "");
    }

    /**
     * 위험물허가량 조회
     *
     * @param parameter
     *            검색조건
     * @return 위험물허가량
     * @throws Exception
     */
    @ApiOperation(value = "위험물허가량 조회[CHM07005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemdgrattamts")
    public ResponseEntity<List<StorageFacilityManagementDgrPerm>> getChemDgrAttAmts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 대표사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 저장위치
        String matStrgCd = map.containsKey("matStrgCd") ? map.get("matStrgCd").toString() : "";

        return ResponseEntity.ok().body(storageFacilityManagementService.getChemDgrAttAmts(plantCd, matStrgCd));
    }

    /**
     * 저장위치 sap에서 받은 데이터 조회
     *
     * @param parameter
     *            검색조건
     * @return 저장위치 sap에서 받은 데이터 목록
     * @throws Exception
     */
    @ApiOperation(value = "저장위치 sap에서 받은 데이터 조회[CHM07006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "대표사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "matStrgNm", value = "저장위치명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/sapmatstrg")
    public ResponseEntity<List<StorageFacilityManagement>> getSapMatStrg(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 대표사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 저장위치명
        String matStrgNm = map.containsKey("matStrgNm") ? map.get("matStrgNm").toString() : "";

        return ResponseEntity.ok().body(storageFacilityManagementService.getSapMatStrg(plantCd, matStrgNm, defaultParam));
    }

    /**
     * 저장위치에 따른 재고 현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 저장위치에 따른 재고 현황
     * @throws Exception
     */
    @ApiOperation(value = "저장위치 sap에서 받은 데이터 조회[CHM07007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "대표사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "matStrgCd", value = "저장위치", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemmatstoragestatus")
    public ResponseEntity<List<MatInoutStatus>> getChemMatStorageStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 대표사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 저장위치
        String matStrgCd = map.containsKey("matStrgCd") ? map.get("matStrgCd").toString() : "";

        return ResponseEntity.ok().body(storageFacilityManagementService.getChemMatStorageStatus(plantCd, matStrgCd, defaultParam));
    }

}
