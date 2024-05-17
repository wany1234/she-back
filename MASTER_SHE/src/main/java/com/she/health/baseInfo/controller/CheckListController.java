package com.she.health.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.health.baseInfo.service.CheckListService;
import com.she.health.model.CheckList;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/hea/baseinfo")
@Api(value = "/api/hea/baseinfo", description = "보건 기준정보 서비스")
public class CheckListController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CheckListService checkListService;

    /**
     * 체크리스트 조회
     *
     * @param parameter
     *            검색조건
     * @return 체크리스트 목록
     * @throws Exception
     */
    @ApiOperation(value = "체크리스트 조회[HEA10001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checklists")
    public ResponseEntity<List<CheckList>> getCheckLists(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 측정소항목 구분
        String chklistType = map.containsKey("chklistType") ? map.get("chklistType").toString() : "";
        // 측정소항목 구분
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(checkListService.getCheckLists(plantCd, chklistType, useYn, defaultParam));
    }

    /**
     * 체크리스트 상세 조회
     *
     * @param itemCode
     *            측정항목코드(태그)
     * @return 측정항목
     * @throws Exception
     */
    @ApiOperation(value = "체크리스트 상세조회[HEA10002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "itemCode", value = "체크리스트코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/checklist/{chklistNo}")
    public ResponseEntity<CheckList> getCheckList(@PathVariable(name = "chklistNo") int chklistNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.checkListService.getCheckList(chklistNo, defaultParam));
    }

    /**
     * 체크리스트명 체크
     *
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "체크리스트 체크[HEA10005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checkchecklist")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckCheckList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 체크리스트명
        String chklistNm = map.containsKey("chklistNm") ? map.get("chklistNm").toString() : "";
        // 체크리스트번호
        int chklistNo = map.containsKey("chklistNo") ? Integer.parseInt("".equals(map.get("chklistNo").toString()) ? "0" : map.get("chklistNo").toString()) : 0;

        return ResponseEntity.ok().body(checkListService.getCheckCheckList(plantCd, chklistNm, chklistNo));
    }

}
