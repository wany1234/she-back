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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.Duty;
import com.she.manage.service.DutyService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/manage")
@Api(value = "/api/manage", description = "기초정보 서비스")
public class DutyController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DutyService dutyService;

    /**
     * 직책 조회
     * 
     * @param parameter
     *            검색조건
     * @return 직책 목록
     * @throws Exception
     */
    @ApiOperation(value = "직책 조회[BAS09001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/dutys")
    public ResponseEntity<List<Duty>> getDutys(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 직책명
        String dutyNm = map.containsKey("dutyNm") ? map.get("dutyNm").toString() : "";

        return ResponseEntity.ok().body(dutyService.getDutys(useYn, dutyNm));
    }

}
