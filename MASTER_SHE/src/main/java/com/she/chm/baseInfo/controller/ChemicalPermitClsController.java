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

import com.she.chm.baseInfo.service.ChemicalPermitClsService;
import com.she.chm.model.ChemicalPermitCls;
import com.she.chm.model.ChemicalRegulItem;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/chm/baseinfo")
@Api(value = "/api/chm/baseinfo", description = "인허가신고서종류")
public class ChemicalPermitClsController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalPermitClsService chemicalPermitClsService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    /**
     * 인허가신고서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 인허가신고서 목록
     * @throws Exception
     */
    @ApiOperation(value = "인허가신고서 조회[CHM03001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "permitKindCd", value = "인허가구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "permitClsNm", value = "인허가신고서명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemicalpermitclses")
    public ResponseEntity<List<ChemicalPermitCls>> getChemicalPermitClses(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 인허가구분
        String permitKindCd = map.containsKey("permitKindCd") ? map.get("permitKindCd").toString() : "";
        // 인허가신고명
        String permitClsNm = map.containsKey("permitClsNm") ? map.get("permitClsNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(chemicalPermitClsService.getChemicalPermitClses(permitKindCd, permitClsNm, useYn, plantCd));
    }

    @ApiOperation(value = "인허가신고서 조회(규제정보제외리스트만)[CHM03010]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "permitKindCd", value = "인허가구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "permitClsNm", value = "인허가신고서명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemicalpermitclslist")
    public ResponseEntity<List<ChemicalPermitCls>> getChemicalPermitClsList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 인허가구분
        String permitKindCd = map.containsKey("permitKindCd") ? map.get("permitKindCd").toString() : "";
        // 인허가신고명
        String permitClsNm = map.containsKey("permitClsNm") ? map.get("permitClsNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(chemicalPermitClsService.getChemicalPermitClsList(permitKindCd, permitClsNm, useYn, plantCd));
    }

    /**
     * 인허가신고서 상세 조회
     * 
     * @param regulItmNo
     *            인허가신고서번호
     * @return 인허가신고서
     * @throws Exception
     */
    @GetMapping("/chemicalpermitcls/{permitClsNo}")
    public ResponseEntity<ChemicalPermitCls> getChemicalPermitCls(@PathVariable(name = "permitClsNo") int permitClsNo) throws Exception {
        return ResponseEntity.ok().body(this.chemicalPermitClsService.getChemicalPermitCls(permitClsNo));
    }

    /**
     * 인허가신고서 신규등록
     * 
     * @param chemicalRegulItem
     *            인허가신고서
     * @return 인허가신고서 코드
     * @throws Exception
     */
    @PostMapping("/chemicalpermitcls")
    public ResponseEntity<Integer> createChemicalPermitCls(@RequestBody ChemicalPermitCls chemicalPermitCls) throws Exception {
        return ResponseEntity.ok().body(this.chemicalPermitClsService.createChemicalPermitCls(chemicalPermitCls));
    }

    /**
     * 인허가신고서 수정
     * 
     * @param chemicalRegulItem
     *            인허가신고서
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/chemicalpermitcls")
    public ResponseEntity<Integer> updateChemicalPermitCls(@RequestBody ChemicalPermitCls chemicalPermitCls) throws Exception {
        return ResponseEntity.ok().body(this.chemicalPermitClsService.updateChemicalPermitCls(chemicalPermitCls));
    }

    /**
     * 인허가신고서명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkchemicalpermitcls")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalPermitCls(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 인허가신고서명
        String permitClsNm = map.containsKey("permitClsNm") ? map.get("permitClsNm").toString() : "";
        // 인허가신고서코드
        String permitClsCd = map.containsKey("permitClsCd") ? map.get("permitClsCd").toString() : "";
        // 인허가신고서번호
        int permitClsNo = map.containsKey("permitClsNo") ? Integer.parseInt("".equals(map.get("permitClsNo").toString()) ? "0" : map.get("permitClsNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalPermitClsService.getCheckChemicalPermitCls(permitClsNm, permitClsCd, permitClsNo));
    }

    /**
     * 인허가신고 규제사항 조회
     * 
     * @param parameter
     *            검색조건
     * @return 인허가신고 규제사항 목록
     * @throws Exception
     */
    @GetMapping("/chemicalpermitreguls")
    public ResponseEntity<List<ChemicalRegulItem>> getChemicalPermitReguls(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 인허가신고서번호
        int permitClsNo = map.containsKey("permitClsNo") ? Integer.parseInt("".equals(map.get("permitClsNo").toString()) ? "0" : map.get("permitClsNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalPermitClsService.getChemicalPermitReguls(permitClsNo));
    }

}
