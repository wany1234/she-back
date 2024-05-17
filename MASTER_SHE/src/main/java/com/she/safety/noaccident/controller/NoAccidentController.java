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
package com.she.safety.noaccident.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.she.safety.model.NoAccident;
import com.she.safety.noaccident.service.NoAccidentService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 사사내고
 *
 */
@RestController
@RequestMapping("/api/saf/noaccident/")
public class NoAccidentController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private NoAccidentService noaccidentService;

    /**
     * 사업장 무재해 조회
     * 
     * @param parameter
     *            검색조건
     * @return 사내사고 목록
     */
    @ApiOperation(value = "사업장 무재해 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "duration", value = "기간", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "searchFlag", value = "조회구분", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("noaccidents")
    public ResponseEntity<List<NoAccident>> getNoAccidents(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] duration = this.requestMapper.convertObjectListAsStringArray(map.get("duration")); // 기간
        String startYmd = "";
        String endSchYmd = "";

        if (duration != null && duration.length == 2) {
            startYmd = duration[0];
            endSchYmd = duration[1];
        }
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";

        List<NoAccident> noaccidents = noaccidentService.getNoAccidents(startYmd, endSchYmd, plantCd, searchFlag, defaultParam);
        return ResponseEntity.ok().body(noaccidents);
    }

    /**
     * 사업장 무재해 상세 조회
     * 
     * @param safNoAccidentNo
     * @return
     * @throws Exception
     */
    @GetMapping("getnoaccident/{safNoAccidentNo}")
    public ResponseEntity<NoAccident> getNoAccident(@PathVariable("safNoAccidentNo") int safNoAccidentNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        NoAccident noaccident = noaccidentService.getNoAccident(safNoAccidentNo, defaultParam);
        return ResponseEntity.ok().body(noaccident);
    }

    /**
     * 사업장 무재해 등록
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    @PostMapping("createnoaccident")
    public ResponseEntity<Integer> createNoAccident(@RequestBody NoAccident noaccident) throws Exception {
        int returnNum = noaccidentService.createNoAccident(noaccident);

        return ResponseEntity.ok().body(returnNum);
    }

    /**
     * 사업장 무재해 수정
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    @PutMapping("updatenoaccident")
    public ResponseEntity<Integer> updateNoAccident(@RequestBody NoAccident noaccident) throws Exception {
        int returnNum = noaccidentService.updateNoAccident(noaccident);
        return ResponseEntity.ok().body(returnNum);
    }

    /**
     * 사업장 무재해 삭제
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    @PutMapping("deletenoaccident")
    public ResponseEntity<Integer> deleteNoAccident(@RequestBody NoAccident noaccident) throws Exception {
        return ResponseEntity.ok().body(noaccidentService.deleteNoAccident(noaccident));
    }

    /**
     * 부서 무재해 조회
     * 
     * @param parameter
     *            검색조건
     * @return 사내사고 목록
     */
    @ApiOperation(value = "부서 무재해 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "duration", value = "기간", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "부서코드", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "searchFlag", value = "조회구분", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("deptnoaccidents")
    public ResponseEntity<List<NoAccident>> getDeptNoAccidents(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String[] duration = this.requestMapper.convertObjectListAsStringArray(map.get("duration")); // 기간
        String startYmd = "";
        String endSchYmd = "";

        if (duration != null && duration.length == 2) {
            startYmd = duration[0];
            endSchYmd = duration[1];
        }
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        List<NoAccident> noaccidents = noaccidentService.getDeptNoAccidents(startYmd, endSchYmd, plantCd, deptCd, searchFlag, deptSubYn, defaultParam);
        return ResponseEntity.ok().body(noaccidents);
    }

    /**
     * 부서 무재해 상세 조회
     * 
     * @param safNoAccidentDeptNo
     * @return
     * @throws Exception
     */
    @GetMapping("getdeptnoaccident/{safNoAccidentDeptNo}")
    public ResponseEntity<NoAccident> getDeptNoAccident(@PathVariable("safNoAccidentDeptNo") int safNoAccidentDeptNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        NoAccident noaccident = noaccidentService.getDeptNoAccident(safNoAccidentDeptNo, defaultParam);
        return ResponseEntity.ok().body(noaccident);
    }

    /**
     * 부서 무재해 등록
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    @PostMapping("createdeptnoaccident")
    public ResponseEntity<Integer> createDeptNoAccident(@RequestBody NoAccident noaccident) throws Exception {
        int returnNum = noaccidentService.createDeptNoAccident(noaccident);

        return ResponseEntity.ok().body(returnNum);
    }

    /**
     * 부서 무재해 수정
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    @PutMapping("updatedeptnoaccident")
    public ResponseEntity<Integer> updateDeptNoAccident(@RequestBody NoAccident noaccident) throws Exception {
        int returnNum = noaccidentService.updateDeptNoAccident(noaccident);
        return ResponseEntity.ok().body(returnNum);
    }

    /**
     * 부서 무재해 삭제
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    @PutMapping("deletedeptnoaccident")
    public ResponseEntity<Integer> deleteDeptNoAccident(@RequestBody NoAccident noaccident) throws Exception {
        return ResponseEntity.ok().body(noaccidentService.deleteDeptNoAccident(noaccident));
    }

    /**
     * 사업장 무재해 건수 조회
     * 
     * @param parameter
     *            검색조건
     * @return 건수
     */
    @ApiOperation(value = "사업장 무재해 건수 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("countnoaccident")
    public ResponseEntity<Integer> countNoAccident(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        int returnNum = noaccidentService.countNoAccident(plantCd, defaultParam);
        return ResponseEntity.ok().body(returnNum);
    }

    /**
     * 부서 무재해 건수 조회
     * 
     * @param parameter
     *            검색조건
     * @return 건수
     */
    @ApiOperation(value = "부서 무재해 건수 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "deptCd", value = "부서코드", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "dtlDeptNm", value = "세부부서명", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("countdeptnoaccident")
    public ResponseEntity<Integer> countDeptNoAccident(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        int returnNum = noaccidentService.countDeptNoAccident(plantCd, deptCd, defaultParam);
        return ResponseEntity.ok().body(returnNum);
    }
}
