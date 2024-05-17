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
package com.she.safety.cto.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.CodeMaster;
import com.she.safety.cto.service.CtoService;
import com.she.safety.model.Cto;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/saf/cto")
@Api(value = "/api/saf/cto/", description = "cto 서비스")
public class CtoController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CtoService ctoService;

    /**
     * 변경관리 조회
     *
     * @param parameter
     *            검색조건
     * @return 변경관리 목록
     * @throws Exception
     */
    @ApiOperation(value = "cto 조회[SAF06001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "period", value = "관찰일", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "관찰부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "processCd", value = "관찰공정", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "jobNm", value = "작업명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), @ApiImplicitParam(name = "stepCd", value = "진행상태", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/ctos")
    public ResponseEntity<List<Cto>> getCtos(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관찰일
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startDt = "";
        String endDt = "";
        if (period != null && period.length == 2) {
            startDt = period[0];
            endDt = period[1];
        }
        // 관찰부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 관찰공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 작업명
        String jobNm = map.containsKey("jobNm") ? map.get("jobNm").toString() : "";

        // 진행상태
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";

        return ResponseEntity.ok().body(ctoService.getCtos(plantCd, startDt, endDt, deptCd, processCd, jobNm, stepCd, deptSubYn));
    }

    /**
     * cto 상세 조회
     *
     * @param safCtoNo
     *            cto번호
     * @return cto
     * @throws Exception
     */
    @ApiOperation(value = "cto 상세조회[SAF06002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safCtoNo", value = "cto번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/cto/{safCtoNo}")
    public ResponseEntity<Cto> getCto(@PathVariable(name = "safCtoNo") int safCtoNo) throws Exception {
        return ResponseEntity.ok().body(this.ctoService.getCto(safCtoNo));
    }

    /**
     * cto 신규등록
     *
     * @param cto
     *            cto
     * @return cto번호
     * @throws Exception
     */
    @ApiOperation(value = "cto 신규등록[SAF06003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "cto", value = "cto", required = false, dataType = "Change", paramType = "body") })
    @PostMapping("/cto")
    public ResponseEntity<Integer> createCto(@RequestBody Cto cto) throws Exception {
        return ResponseEntity.ok().body(this.ctoService.createCto(cto));
    }

    /**
     * cto 수정
     *
     * @param cto
     *            cto
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "cto 수정[SAF06004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "cto", value = "cto", required = false, dataType = "Change", paramType = "body") })
    @PutMapping("/cto")
    public ResponseEntity<Integer> updateCto(@RequestBody Cto cto) throws Exception {
        return ResponseEntity.ok().body(this.ctoService.updateCto(cto));
    }

    /**
     * cto 삭제
     *
     * @param safCtoNo
     *            cto번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @ApiOperation(value = "cto 삭제[SAF06005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safCtoNo", value = "변경관리번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("/cto/{safCtoNo}")
    public ResponseEntity<Integer> deleteCto(@PathVariable(name = "safCtoNo") int safCtoNo) throws Exception {
        return ResponseEntity.ok().body(this.ctoService.deleteCto(safCtoNo));
    }

    /**
     * 체크리스트 조회
     *
     * @param
     * @return 체크리스트 조회
     * @throws Exception
     */
    @GetMapping("/checkList")
    public ResponseEntity<List<CodeMaster>> getCheckList(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);

        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        String useYn = convertedParameter.containsKey("useYn") ? convertedParameter.get("useYn").toString() : "";

        return ResponseEntity.ok().body(ctoService.getCheckList(codeNm, useYn));
    }

    @GetMapping("/checkList/{code}")
    public ResponseEntity<CodeMaster> getSafetyActionBizField(@PathVariable("code") String code) throws Exception {
        return ResponseEntity.ok().body(ctoService.getCheckItem(code));
    }

    @GetMapping("/checkList/check")
    public ResponseEntity<List<HashMap<String, Object>>> checkCheckListItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        // 코드
        String code = convertedParameter.containsKey("code") ? convertedParameter.get("code").toString() : "";
        // 코드명(분야명)
        String codeNm = convertedParameter.containsKey("codeNm") ? convertedParameter.get("codeNm").toString() : "";

        return ResponseEntity.ok().body(ctoService.checkCheckListItem(code, codeNm));
    }

    @PostMapping("/checkList")
    public ResponseEntity<String> createCheckList(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(ctoService.createCheckList(codeMaster) > 0 ? codeMaster.getCode() : "");
    }

    @PutMapping("/checkList")
    public ResponseEntity<Integer> updateCheckList(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(ctoService.updateCheckList(codeMaster));
    }

}
