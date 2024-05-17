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
package com.she.vendor.subconEval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.she.utils.RequestMapper;
import com.she.vendor.model.SubconEvalResult;
import com.she.vendor.subconEval.service.SubconEvalResultService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/vendor/subconeval")
public class SubconEvalResultController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SubconEvalResultService subconEvalResultService;

    /**
     * 협력업체평가결과 등록
     *
     * @param subconEvalResult
     *            협력업체평가결과
     * @return 협력업체평가결과No
     * @throws Exception
     */
    @ApiOperation(value = "협력업체평가결과 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "SubconEvalResult", value = "협력업체평가결과", required = false, dataType = "SubconEvalResult", paramType = "body") })
    @PostMapping("/subconevalresult")
    public ResponseEntity<Integer> createSubconEvalResult(@RequestBody SubconEvalResult subconEvalResult) throws Exception {
        return ResponseEntity.ok().body(this.subconEvalResultService.createSubconEvalResult(subconEvalResult));
    }

    /**
     * 협력업체평가결과 수정
     *
     * @param subconEvalResult
     *            협력업체평가결과
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "협력업체평가결과 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "SubconEvalResult", value = "협력업체평가결과", required = false, dataType = "SubconEvalResult", paramType = "body") })
    @PutMapping("/subconevalresult")
    public ResponseEntity<Integer> updateSubconEvalResult(@RequestBody SubconEvalResult subconEvalResult) throws Exception {
        return ResponseEntity.ok().body(this.subconEvalResultService.updateSubconEvalResult(subconEvalResult));
    }

    /**
     * 협력업체평가결과 삭제
     *
     * @param SubconEvalItemResult
     *            협력업체평가결과
     * @return 삭제 반환 수
     * @throws Exception
     */
    @ApiOperation(value = "협력업체평가결과 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "SubconEvalResult", value = "협력업체평가결과", required = false, dataType = "SubconEvalResult", paramType = "body") })
    @DeleteMapping("/subconevalresult")
    public ResponseEntity<Integer> deleteSubconEvalResult(@RequestBody SubconEvalResult subconEvalResult) throws Exception {
        return ResponseEntity.ok().body(subconEvalResultService.deleteSubconEvalResult(subconEvalResult));
    }
}
