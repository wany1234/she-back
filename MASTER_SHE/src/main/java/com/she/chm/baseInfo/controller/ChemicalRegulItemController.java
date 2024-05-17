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

import com.she.chm.baseInfo.service.ChemicalRegulItemService;
import com.she.chm.model.ChemicalRegulItem;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "/api/chm/baseinfo", description = "화학물질 규제항목")
@RestController
@RequestMapping("api/chm/baseinfo")
public class ChemicalRegulItemController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalRegulItemService chemicalRegulItemService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "규제항목 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"regulItmCd\": \"KR002009001\", " + STR_ENTER + PADDING_FOUR + "\"regulItmNm\": \"유독물질\", " + STR_ENTER + PADDING_FOUR + "\"regulLawCd\": \"화학물질관리법\", " + STR_ENTER + PADDING_FOUR + "\"regulOrgCd\": \"환경부\", " + STR_ENTER + PADDING_FOUR
            + "\"contents\": \"유해성이 있는 화학물질로서 대통령령으로 정하는 기준에 따라 환경부장관이 정하여 고시한 물질\", " + STR_ENTER + PADDING_FOUR + "\"sortOrder\": \"100\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    private static final String EDIT_JSON = "법인 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"regulItmNo\": \"1\", " + STR_ENTER + PADDING_FOUR + "\"regulItmCd\": \"KR002009001\", " + STR_ENTER + PADDING_FOUR + "\"regulItmNm\": \"유독물질\", " + STR_ENTER + PADDING_FOUR + "\"regulLawCd\": \"화학물질관리법\", " + STR_ENTER + PADDING_FOUR
            + "\"regulOrgCd\": \"환경부\", " + STR_ENTER + PADDING_FOUR + "\"contents\": \"유해성이 있는 화학물질로서 대통령령으로 정하는 기준에 따라 환경부장관이 정하여 고시한 물질\", " + STR_ENTER + PADDING_FOUR + "\"sortOrder\": \"100\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER + "} ";

    /**
     * 규제항목 조회
     *
     * @param parameter
     *            검색조건
     * @return 규제항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "규제항목 조회[CHM02001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "regulLawCd", value = "규제법규코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "regulItmNm", value = "규제법규명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "boolean", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/chemicalreguiItems")
    public ResponseEntity<List<ChemicalRegulItem>> getChemicalRegulItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 규제법규
        String regulLawCd = map.containsKey("regulLawCd") ? map.get("regulLawCd").toString() : "";
        // 규제항목명
        String regulItmNm = map.containsKey("regulItmNm") ? map.get("regulItmNm").toString() : "";

        return ResponseEntity.ok().body(chemicalRegulItemService.getChemicalRegulItems(regulLawCd, regulItmNm, useYn, defaultParam));
    }

    /**
     * 규제항목 상세 조회
     *
     * @param regulItmNo
     *            규제항목번호
     * @return 규제항목
     * @throws Exception
     */
    @ApiOperation(value = "규제항목 상세조회[CHM02002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "regulItmNo", value = "법인코드", required = true, dataType = "long", paramType = "path") })
    @GetMapping("/chemicalreguiItem/{regulItmNo}")
    public ResponseEntity<ChemicalRegulItem> getChemicalRegulItem(@PathVariable(name = "regulItmNo") int regulItmNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.chemicalRegulItemService.getChemicalRegulItem(regulItmNo, defaultParam));
    }

    /**
     * 규제항목 신규등록
     *
     * @param chemicalRegulItem
     *            규제항목
     * @return 규제항목 코드
     * @throws Exception
     */
    @ApiOperation(value = "규제항목 등록[CHM02003]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalRegulItem", value = "규제항목정보", required = false, dataType = "ChemicalRegulItem", paramType = "body") })
    @PostMapping("/chemicalreguiItem")
    public ResponseEntity<Integer> createChemicalRegulItem(@RequestBody ChemicalRegulItem chemicalRegulItem) throws Exception {
        return ResponseEntity.ok().body(this.chemicalRegulItemService.createChemicalRegulItem(chemicalRegulItem));
    }

    /**
     * 규제항목 수정
     *
     * @param chemicalRegulItem
     *            규제항목
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "규제항목 수정[CHM02004]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalRegulItem", value = "규제항목정보", required = false, dataType = "ChemicalRegulItem", paramType = "body") })
    @PutMapping("/chemicalreguiItem")
    public ResponseEntity<Integer> updateChemicalRegulItem(@RequestBody ChemicalRegulItem chemicalRegulItem) throws Exception {
        return ResponseEntity.ok().body(this.chemicalRegulItemService.updateChemicalRegulItem(chemicalRegulItem));
    }

    /**
     * 규제항목명 체크
     *
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "규제항목명 체크[CHM02005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "regulItmNm", value = "규제항목명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "regulItmNo", value = "규제항목번호", required = false, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checkchemicalreguiItem")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalRegulItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 규제항목명
        String regulItmNm = map.containsKey("regulItmNm") ? map.get("regulItmNm").toString() : "";
        // 규제항목번호
        int regulItmNo = map.containsKey("regulItmNo") ? Integer.parseInt("".equals(map.get("regulItmNo").toString()) ? "0" : map.get("regulItmNo").toString()) : 0;

        return ResponseEntity.ok().body(chemicalRegulItemService.getCheckChemicalRegulItem(regulItmNm, regulItmNo));
    }

}
