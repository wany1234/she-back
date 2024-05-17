/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 * <p>
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 * <p>
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
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

import com.she.env.air.baseInfo.service.FuelService;
import com.she.env.air.model.Fuel;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 대기 연료
 */
@RestController("EairFuelController")
@RequestMapping("api/env/air/baseinfo")
@Api(value = "api/env/air/baseinfo", description = "대기연료")
public class FuelController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private FuelService fuelService;

    /**
     * 연료 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 연료목록
     * @throws Exception
     */
    @ApiOperation(value = "대기연료 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/fuels")
    public ResponseEntity<List<Fuel>> getFuels(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String eairFuelNm = map.containsKey("eairFuelNm") ? map.get("eairFuelNm").toString() : "";

        return ResponseEntity.ok().body(this.fuelService.getFuels(useYn, plantCd, eairFuelNm, defaultParam));
    }

    /**
     * 연료 상세 조회
     *
     * @param eairFuelCd
     *            연료코드
     * @return 연료
     * @throws Exception
     */

    @ApiOperation(value = "대기연료 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "eairFuelCd", value = "연료코드", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/fuel/{eairFuelCd}")
    public ResponseEntity<Fuel> getFuel(@PathVariable(name = "eairFuelCd") String eairFuelCd) throws Exception {
        Fuel fuel = this.fuelService.getFuel(eairFuelCd);
        return ResponseEntity.ok().body(fuel);
    }

    /**
     * 연료 신규등록
     *
     * @param fuel
     *            연료
     * @return 연료코드
     * @throws Exception
     */
    @ApiOperation(value = "대기연료 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Fuel", value = "대기연료", required = false, dataType = "Fuel", paramType = "query"), })
    @PostMapping("/fuel")
    public ResponseEntity<String> createFuel(@RequestBody Fuel fuel) throws Exception {
        return ResponseEntity.ok().body(this.fuelService.createFuel(fuel));
    }

    /**
     * 연료 수정
     *
     * @param fuel
     *            연료
     * @return 수정행수
     * @throws Exception
     */

    @ApiOperation(value = "대기연료 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "Fuel", value = "대기연료", required = false, dataType = "Fuel", paramType = "query"), })
    @PutMapping("/fuel")
    public ResponseEntity<String> updateFuel(@RequestBody Fuel fuel) throws Exception {
        return ResponseEntity.ok().body(this.fuelService.updateFuel(fuel));
    }

    /**
     * 연료 중복체크
     *
     * @param parameter
     *            중복조건
     * @return 중복 행 수
     * @throws Exception
     */
    @ApiOperation(value = "연료 중복체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "eairFuelCd", value = "대기연료 코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "eairFuelNm", value = "대기연료 명칭", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/check/fuel")
    public ResponseEntity<Integer> checkFuel(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 대기연료 코드
        String eairFuelCd = map.containsKey("eairFuelCd") ? map.get("eairFuelCd").toString() : "";
        // 대기연료 명칭
        String eairFuelNm = map.containsKey("eairFuelNm") ? map.get("eairFuelNm").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(this.fuelService.checkFuel(eairFuelCd, eairFuelNm, plantCd));
    }
}
