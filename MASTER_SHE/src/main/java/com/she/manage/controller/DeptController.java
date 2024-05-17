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

package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.she.manage.model.Dept;
import com.she.manage.model.Process;
import com.she.manage.service.DeptService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 부서 컨트롤러
 *
 */
@RestController
@RequestMapping("api/manage")
@Api(value = "/api/manage", description = "기초정보 서비스")
public class DeptController {
    @Autowired
    private DeptService deptService;

    private RequestMapper requestMapper = new RequestMapper();

    /**
     * 부서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 부서목록
     * @throws Exception
     */
    @ApiOperation(value = "부서 조회[MNG01001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "processCd", value = "공정", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "deptNm", value = "부서명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/depts")
    public ResponseEntity<List<Dept>> getDepts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String deptNm = map.containsKey("deptNm") ? map.get("deptNm").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.deptService.getDepts(plantCd, processCd, deptCd, deptNm, useYn, defaultParam));
    }

    /**
     * 부서 상세 조회
     * 
     * @param deptCd
     *            부서코드
     * @return 부서
     * @throws Exception
     */
    @GetMapping("/dept/{deptCd}")
    public ResponseEntity<Dept> getDept(@PathVariable("deptCd") String deptCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.deptService.getDept(deptCd, defaultParam));
    }

    /**
     * 부서 트리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 부서목록
     * @throws Exception
     */
    @ApiOperation(value = "부서 트리 조회[MNG01003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "deptCd", value = "부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptNm", value = "부서명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "pdeptCd", value = "상위부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/treedepts")
    public ResponseEntity<List<Dept>> getTreeDepts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String deptNm = map.containsKey("deptNm") ? map.get("deptNm").toString() : "";
        String pdeptCd = map.containsKey("pdeptCd") ? map.get("pdeptCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.deptService.getTreeDepts(plantCd, deptCd, deptNm, pdeptCd, useYn, defaultParam));
    }

    /**
     * 부서 트리 조회
     * 
     * @param parameter
     *            검색조건
     * @return 부서목록
     * @throws Exception
     */
    @ApiOperation(value = "부서 트리 조회[MNG01003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "deptCd", value = "부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptNm", value = "부서명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "pdeptCd", value = "상위부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/treedepts-mobile")
    public ResponseEntity<List<Dept>> getTreeDeptsForMobile(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String deptNm = map.containsKey("deptNm") ? map.get("deptNm").toString() : "";
        String pdeptCd = map.containsKey("pdeptCd") ? map.get("pdeptCd").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.deptService.getTreeDeptsForMobile(plantCd, deptCd, deptNm, pdeptCd, useYn, defaultParam));
    }

    /**
     * 부서 생성
     * 
     * @param dept
     *            부서정보
     * @return 부서코드
     * @throws Exception
     */
    @PostMapping("/depts")
    public ResponseEntity<String> createDept(@RequestBody Dept dept) throws Exception {
        String deptCd = this.deptService.createDept(dept);

        return ResponseEntity.ok().body(deptCd);
    }

    /**
     * 부서 수정
     * 
     * @param dept
     *            부서정보
     * @return 부서코드
     * @throws Exception
     */
    @PutMapping("/depts")
    public ResponseEntity<String> updateDept(@RequestBody Dept dept) throws Exception {
        String deptCd = this.deptService.updateDept(dept);

        return ResponseEntity.ok().body(deptCd);
    }

    /**
     * 부서별 공정 신규등록
     * 
     * @param Process
     *            공정
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("/deptprocess")
    public ResponseEntity<Integer> createDeptProcess(@RequestBody List<Process> process) throws Exception {
        return ResponseEntity.ok().body(this.deptService.createDeptProcess(process));
    }

    /**
     * 부서별 공정 삭제
     * 
     * @param Process
     *            공정
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/deptprocess")
    public ResponseEntity<Integer> deleteDeptProcess(@RequestBody List<Process> process) throws Exception {
        return ResponseEntity.ok().body(this.deptService.deleteDeptProcess(process));
    }

    /**
     * 부서코드 중복검사
     * 
     * @param deptCd
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/countdept")
    public ResponseEntity<Integer> countdept(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        return ResponseEntity.ok().body(this.deptService.countDept(deptCd));
    }

}
