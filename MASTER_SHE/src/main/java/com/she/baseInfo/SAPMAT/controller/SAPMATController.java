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
package com.she.baseInfo.SAPMAT.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.baseInfo.SAPMAT.service.SAPMATService;
import com.she.baseInfo.model.SAPMAT;
import com.she.baseInfo.model.SapMatGroup;
import com.she.common.model.DefaultParam;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/baseinfo/sapmat")
@Api(value = "api/baseinfo/sapmat", description = "자재 서비스")
public class SAPMATController {
    @Autowired
    private SAPMATService sAPMATService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 자재 조회
     * 
     * @param parameter
     *            검색조건
     * @return 자재 목록
     * @throws Exception
     */
    @ApiOperation(value = "자재 조회[BAS02001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "matCode", value = "자재코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "matName", value = "자재명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "matGroupCd", value = "자재그룹", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/sapmats")
    public ResponseEntity<Map<String, Object>> getSapMats(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 자재코드
        String matCode = map.containsKey("matCode") ? map.get("matCode").toString() : "";
        // 자재명
        String matName = map.containsKey("matName") ? map.get("matName").toString() : "";
        // 자재그룹
        String matGroupCd = map.containsKey("matGroupCd") ? map.get("matGroupCd").toString() : "";

        int pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        int pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<HashMap<String, Object>> body = sAPMATService.getSapMats(matCode, matName, useYn, matGroupCd, pageNumber, pageSize, orderByExpression);
        Integer totalCount = CollectionUtils.isNotEmpty(body) ? Integer.parseInt(String.valueOf(body.get(0).get("total_cnt"))) : 0;
        returnMap.put("items", body);
        returnMap.put("count", totalCount);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 자재 상세조회
     * 
     * @param matCode
     *            자재코드
     * @return 자재
     * @throws Exception
     */
    @ApiOperation(value = "자재 조회[BAS02001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "matCode", value = "자재코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/sapmat/{matCode}")
    public ResponseEntity<SAPMAT> getSapMat(@PathVariable(name = "matCode") String matCode, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(sAPMATService.getSapMat(matCode, defaultParam));
    }

    /**
     * 자재별 제조업체 저장
     * 
     * @param sapmat
     *            법인
     * @return 법인 코드
     * @throws Exception
     */

    @ApiOperation(value = "자재별 제조업체 저장[BAS02004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "sapmat", value = "자재", required = false, dataType = "SAPMAT", paramType = "body") })
    @PostMapping("/sapmatvendor")
    public ResponseEntity<Integer> saveSapMatVendor(@RequestBody SAPMAT sapmat) throws Exception {
        return ResponseEntity.ok().body(this.sAPMATService.saveSapMatVendor(sapmat));
    }

    /**
     * 자재 그룹 조회
     * 
     * @param parameter
     *            검색조건
     * @return 자재 그룹 목록
     * @throws Exception
     */
    @ApiOperation(value = "자재 그룹 조회[BAS02005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/sapmatgroups")
    public ResponseEntity<List<SapMatGroup>> getSapMatGroups(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        return ResponseEntity.ok().body(sAPMATService.getSapMatGroups());
    }

}
