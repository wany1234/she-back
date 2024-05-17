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
import com.she.env.air.baseInfo.service.IngredientService;
import com.she.env.air.model.Ingredient;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 대기 원료
 *
 */
@RestController("EairIngredientController")
@RequestMapping("api/env/air/baseinfo")
@Api(value = "api/env/air/baseinfo", description = "대기원료")
public class IngredientController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private IngredientService ingredientService;

    /**
     * 원료 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 원료목록
     * @throws Exception
     */
    @ApiOperation(value = "대기원료 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getIngredients(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 원료명
        String eairIngrNm = map.containsKey("eairIngrNm") ? map.get("eairIngrNm").toString() : "";

        return ResponseEntity.ok().body(this.ingredientService.getIngredients(useYn, plantCd, eairIngrNm, defaultParam));
    }

    /**
     * 원료 상세 조회
     *
     * @param eairIngrCd
     *            원료코드
     * @return 원료
     * @throws Exception
     */

    @ApiOperation(value = "대기원료 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "eairIngrCd", value = "원료코드", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/ingredient/{eairIngrCd}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable(name = "eairIngrCd") String eairIngrCd) throws Exception {
        return ResponseEntity.ok().body(this.ingredientService.getIngredient(eairIngrCd));
    }

    /**
     * 원료 신규등록
     *
     * @param ingredient
     *            원료
     * @return 원료코드
     * @throws Exception
     */

    @ApiOperation(value = "대기원료 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Ingredient", value = "대기원료", required = false, dataType = "Ingredient", paramType = "query"), })
    @PostMapping("/ingredient")
    public ResponseEntity<String> createIngredient(@RequestBody Ingredient ingredient) throws Exception {
        return ResponseEntity.ok().body(this.ingredientService.createIngredient(ingredient));
    }

    /**
     * 원료 수정
     *
     * @param ingredient
     *            원료
     * @return 수정행수
     * @throws Exception
     */

    @ApiOperation(value = "대기원료 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Ingredient", value = "대기원료", required = false, dataType = "Ingredient", paramType = "query"), })
    @PutMapping("/ingredient")
    public ResponseEntity<String> updateIngredient(@RequestBody Ingredient ingredient) throws Exception {
        return ResponseEntity.ok().body(this.ingredientService.updateIngredient(ingredient));
    }

    /**
     * 원료명 중복체크
     *
     * @param parameter
     *            중복체크조건
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/check/ingredient")
    public ResponseEntity<Integer> checkIngredient(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 원료코드
        String eairIngrCd = map.containsKey("eairIngrCd") ? map.get("eairIngrCd").toString() : "";
        // 원료명
        String eairIngrNm = map.containsKey("eairIngrNm") ? map.get("eairIngrNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.ingredientService.checkIngredient(eairIngrCd, eairIngrNm, plantCd));
    }
}
