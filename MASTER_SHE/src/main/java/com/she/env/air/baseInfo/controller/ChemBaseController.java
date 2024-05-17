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
package com.she.env.air.baseInfo.controller;

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

import com.she.env.air.baseInfo.service.ChemBaseService;
import com.she.env.air.model.ChemBase;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/air/baseinfo")
@Api(value = "api/env/air/baseinfo", description = "대기약품")
public class ChemBaseController {
    @Autowired
    private ChemBaseService chemBaseService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 대기 약품 조회
     *
     * @param parameter
     *            검색조건
     * @return 대기약품 목록
     * @throws Exception
     */
    @ApiOperation(value = "대기약품 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/chembases")
    public ResponseEntity<List<ChemBase>> getChemBases(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String eairChemNm = map.containsKey("eairChemNm") ? map.get("eairChemNm").toString() : "";

        return ResponseEntity.ok().body(chemBaseService.getChemBases(useYn, plantCd, eairChemNm, defaultParam));
    }

    /**
     * 대기약품 상세조회
     *
     * @param eairChemCd
     *            대기약품코드
     * @return ChemBase 대기약품
     * @throws Exception
     */
    @ApiOperation(value = "대기약품 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "eairChemCd", value = "약품코드", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/chembase/{eairChemCd}")
    public ResponseEntity<ChemBase> getChemBase(@PathVariable("eairChemCd") String eairChemCd) throws Exception {
        return ResponseEntity.ok().body(chemBaseService.getChemBase(eairChemCd));
    }

    /**
     * 대기약품 신규등록
     *
     * @param chemBase
     *            대기약품
     * @return ewtrChemNo 대기약품번호
     * @throws Exception
     */
    @ApiOperation(value = "대기약품 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "ChemBase", value = "대기약품", required = false, dataType = "ChemBase", paramType = "query"), })
    @PostMapping("/chembase")
    public ResponseEntity<String> createChemBase(@RequestBody ChemBase chemBase) throws Exception {
        return ResponseEntity.ok().body(chemBaseService.createChemBase(chemBase));
    }

    /**
     * 대기약품 수정
     *
     * @param chemBase
     *            대기약품
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @ApiOperation(value = "대기약품 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "ChemBase", value = "대기약품", required = false, dataType = "ChemBase", paramType = "query"), })
    @PutMapping("/chembase")
    public ResponseEntity<String> updateChemBase(@RequestBody ChemBase chemBase) throws Exception {
        return ResponseEntity.ok().body(chemBaseService.updateChemBase(chemBase));
    }

    /**
     * 대기약품 중복체크
     *
     * @param parameter
     *            중복조건
     * @return 중복 행 수
     * @throws Exception
     */
    @ApiOperation(value = "대기약품 중복체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "ChemBase", value = "대기약품", required = false, dataType = "ChemBase", paramType = "query"), })
    @GetMapping("/check/chembase")
    public ResponseEntity<Integer> checkChemBase(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 약품코드
        String eairChemCd = map.containsKey("eairChemCd") ? map.get("eairChemCd").toString() : "";
        // 약품명
        String eairChemNm = map.containsKey("eairChemNm") ? map.get("eairChemNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(chemBaseService.checkChemBase(eairChemCd, eairChemNm, plantCd));
    }
}