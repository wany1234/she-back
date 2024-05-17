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
package com.she.chm.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.chm.baseInfo.service.ChemicalBranchService;
import com.she.chm.model.ChemicalBranch;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/chm/baseinfo")
@Api(value = "/api/chm/baseinfo", description = "법인 서비스")
public class ChemicalBranchController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalBranchService chemicalBranchService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "법인 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR
            + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    private static final String EDIT_JSON = "법인 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR
            + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \" Y \" " + STR_ENTER + "} ";

    /**
     * 법인 조회
     * 
     * @param parameter
     *            검색조건
     * @return 법인 목록
     * @throws Exception
     */
    @ApiOperation(value = "법인 조회[CHM01001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "branchNmKr", value = "법인명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemicalbranchs")
    public ResponseEntity<List<ChemicalBranch>> getChemicalBranchs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 법인명 (KOR)
        String branchNmKr = map.containsKey("branchNmKr") ? map.get("branchNmKr").toString() : "";

        return ResponseEntity.ok().body(chemicalBranchService.getChemicalBranchs(branchNmKr, useYn));
    }

    /**
     * 법인 상세 조회
     * 
     * @param branchCd
     *            법인코드
     * @return 법인
     * @throws Exception
     */
    @ApiOperation(value = "법인 상세조회[CHM01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "branchCd", value = "법인코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/chemicalbranch/{branchCd}")
    public ResponseEntity<ChemicalBranch> getChemicalBranch(@PathVariable(name = "branchCd") String branchCd) throws Exception {
        return ResponseEntity.ok().body(this.chemicalBranchService.getChemicalBranch(branchCd));
    }

    /**
     * 법인 신규등록
     * 
     * @param chemicalBranch
     *            법인
     * @return 법인 코드
     * @throws Exception
     */

    @ApiOperation(value = "법인등록[CHM01003]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalBranch", value = "법인정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PostMapping("/chemicalbranch")
    public ResponseEntity<String> createChemicalBranch(@RequestBody ChemicalBranch chemicalBranch) throws Exception {
        return ResponseEntity.ok().body(this.chemicalBranchService.createChemicalBranch(chemicalBranch));
    }

    /**
     * 법인 수정
     * 
     * @param chemicalBranch
     *            법인
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "법인수정[CHM01004]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalBranch", value = "법인정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PutMapping("/chemicalbranch")
    public ResponseEntity<Integer> updateChemicalBranch(@RequestBody ChemicalBranch chemicalBranch) throws Exception {
        return ResponseEntity.ok().body(this.chemicalBranchService.updateChemicalBranch(chemicalBranch));
    }

    /**
     * 법인명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "법인명 체크[CHM01005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "branchNmKr", value = "법인명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "branchCdOrgin", value = "변경전 법인코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "branchCd", value = "변경할 법인코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checkchemicalbranch")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalBranch(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 법인명 (KOR)
        String branchNmKr = map.containsKey("branchNmKr") ? map.get("branchNmKr").toString() : "";
        // 법인코드 자기자신거
        String branchCdOrgin = map.containsKey("branchCdOrgin") ? map.get("branchCdOrgin").toString() : "";
        // 법인코드 바꿔 볼려고 하는거
        String branchCd = map.containsKey("branchCd") ? map.get("branchCd").toString() : "";

        return ResponseEntity.ok().body(chemicalBranchService.getCheckChemicalBranch(branchNmKr, branchCdOrgin, branchCd));
    }

}
