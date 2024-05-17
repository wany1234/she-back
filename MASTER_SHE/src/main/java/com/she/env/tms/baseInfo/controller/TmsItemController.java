package com.she.env.tms.baseInfo.controller;

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

import com.she.env.tms.baseInfo.service.TmsItemService;
import com.she.env.tms.model.TmsItem;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/baseinfo")
@Api(value = "/api/env/tms/baseinfo", description = "환경 tms 기준정보 서비스")
public class TmsItemController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TmsItemService tmsItemService;

    /**
     * TMS 측정항목 조회
     * 
     * @param parameter
     *            검색조건
     * @return TMS 측정항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정항목 조회[ENV11001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tmsitems")
    public ResponseEntity<List<TmsItem>> getTmsItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 측정소항목 구분
        String tmsType = map.containsKey("tmsType") ? map.get("tmsType").toString() : "";
        
        // 측정소항목 구분
        // String isComm = map.containsKey("isComm") ? map.get("isComm").toString() : "false";

        String itemName = map.containsKey("itemName") ? map.get("itemName").toString() : "";

        return ResponseEntity.ok().body(tmsItemService.getTmsItems(tmsType, /*isComm,*/ itemName));
    }

    /**
     * TMS 측정항목 상세 조회
     * 
     * @param itemCode
     *            측정항목코드(태그)
     * @return 측정항목
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정항목 상세조회[ENV11002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "itemCode", value = "TMS 측정항목코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/tmsitem/{itemCode}")
    public ResponseEntity<TmsItem> getTmsItem(@PathVariable(name = "itemCode") String itemCode) throws Exception {
        return ResponseEntity.ok().body(this.tmsItemService.getTmsItem(itemCode));
    }

    /**
     * TMS 측정항목 신규등록
     * 
     * @param chemicalBranch
     *            TMS 측정항목
     * @return TMS 측정항목 코드
     * @throws Exception
     */

    @ApiOperation(value = "TMS 측정항목 등록[ENV11003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "TmsItem", value = "TMS 측정항목정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PostMapping("/tmsitem")
    public ResponseEntity<String> createTmsItem(@RequestBody TmsItem tmsItem) throws Exception {
        return ResponseEntity.ok().body(this.tmsItemService.createTmsItem(tmsItem));
    }

    /**
     * TMS 측정항목 수정
     * 
     * @param chemicalBranch
     *            TMS 측정항목
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정항목 수정[ENV11004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalBranch", value = "TMS 측정항목정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PutMapping("/tmsitem")
    public ResponseEntity<Integer> updateTmsItem(@RequestBody TmsItem tmsItem) throws Exception {
        return ResponseEntity.ok().body(this.tmsItemService.updateTmsItem(tmsItem));
    }

    /**
     * TMS 측정항목명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정항목 체크[ENV11005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationName", value = "TMS 측정항목명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "stationCodeOrgin", value = "변경전 TMS 측정항목코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "stationCode", value = "변경할 TMS 측정항목코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checktmsitem")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckTmsItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정항목명
        String itemName = map.containsKey("itemName") ? map.get("itemName").toString() : "";
        // TMS 측정항목코드 자기자신거
        String itemCodeOrign = map.containsKey("itemCodeOrign") ? map.get("itemCodeOrign").toString() : "";
        // TMS 측정항목코드 바꿔 볼려고 하는거
        String itemCode = map.containsKey("itemCode") ? map.get("itemCode").toString() : "";

        return ResponseEntity.ok().body(tmsItemService.getCheckTmsItem(itemName, itemCodeOrign, itemCode));
    }

    /**
     * TMS 측정항목 삭제
     * 
     * @param chemicalBranch
     *            TMS 측정항목
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정항목 삭제[ENV11006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "TmsItems", value = "TMS 측정항목정보s", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @DeleteMapping("/tmsitem")
    public ResponseEntity<Integer> deleteTmsItem(@RequestBody List<TmsItem> tmsItems) throws Exception {
        return ResponseEntity.ok().body(this.tmsItemService.deleteTmsItem(tmsItems));
    }

    /**
     * TMS 측정항목명 삭제 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정항목 삭제 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "itemCode", value = "TMS 측정항목코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/deletechecktmsitem")
    public ResponseEntity<Integer> getDeleteCheckTmsItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정항목코드
        String itemCode = map.containsKey("itemCode") ? map.get("itemCode").toString() : "";

        return ResponseEntity.ok().body(tmsItemService.getDeleteCheckTmsItem(itemCode));
    }

}
