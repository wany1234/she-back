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

import com.she.env.tms.baseInfo.service.TmsStationItemService;
import com.she.env.tms.model.TmsStationItem;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/baseinfo")
@Api(value = "/api/env/tms/baseinfo", description = "환경 tms 기준정보 서비스")
public class TmsStationItemController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TmsStationItemService tmsStationItemService;

    /**
     * TMS 측정소항목 조회
     * 
     * @param parameter
     *            검색조건
     * @return TMS 측정소항목 목록
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소항목 조회[ENV12001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "tmsType", value = "측정소항목 구분", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tmsstationitems")
    public ResponseEntity<List<TmsStationItem>> getTmsStationItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 측정소항목 구분
        String tmsType = map.containsKey("tmsType") ? map.get("tmsType").toString() : "";
        String itemName = map.containsKey("itemName") ? map.get("itemName").toString() : "";

        return ResponseEntity.ok().body(tmsStationItemService.getTmsStationItems(plantCd, tmsType, itemName));
    }

    /**
     * TMS 측정소항목 상세 조회
     * 
     * @param stationCode
     *            측정소항목코드(태그)
     * @return 측정소항목
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소항목 상세조회[ENV12002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationItemCode", value = "TMS 측정소항목코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/tmsstationitem/{stationItemCode}")
    public ResponseEntity<TmsStationItem> getTmsStationItem(@PathVariable(name = "stationItemCode") String stationItemCode) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationItemService.getTmsStationItem(stationItemCode));
    }

    /**
     * TMS 측정소항목 신규등록
     * 
     * @param chemicalBranch
     *            TMS 측정소항목
     * @return TMS 측정소항목 코드
     * @throws Exception
     */

    @ApiOperation(value = "TMS 측정소항목 등록[ENV12003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "TmsStationItem", value = "TMS 측정소항목정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PostMapping("/tmsstationitem")
    public ResponseEntity<String> createTmsStationItem(@RequestBody TmsStationItem tmsStationItem) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationItemService.createTmsStationItem(tmsStationItem));
    }

    /**
     * TMS 측정소항목 수정
     * 
     * @param chemicalBranch
     *            TMS 측정소항목
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소항목 수정[ENV12004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalBranch", value = "TMS 측정소항목정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PutMapping("/tmsstationitem")
    public ResponseEntity<Integer> updateTmsStationItem(@RequestBody TmsStationItem tmsStationItem) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationItemService.updateTmsStationItem(tmsStationItem));
    }

    /**
     * TMS 측정소항목 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 체크[ENV12005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationItemCodeOrign", value = "변경전 TMS 측정소항목코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "stationItemCode", value = "변경할 TMS 측정소항목코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checktmsstationitem")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckTmsStationItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정소코드 자기자신거
        String stationItemCodeOrign = map.containsKey("stationItemCodeOrign") ? map.get("stationItemCodeOrign").toString() : "";
        // TMS 측정소코드 바꿔 볼려고 하는거
        String stationItemCode = map.containsKey("stationItemCode") ? map.get("stationItemCode").toString() : "";

        return ResponseEntity.ok().body(tmsStationItemService.getCheckTmsStationItem(stationItemCodeOrign, stationItemCode));
    }

    /**
     * TMS 측정소항목 삭제
     * 
     * @param chemicalBranch
     *            TMS 측정소항목
     * @return 삭제 행 수
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소항목 삭제[ENV12006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "TmsStationItems", value = "TMS 측정소항목정보s", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @DeleteMapping("/tmsstationitem")
    public ResponseEntity<Integer> deleteTmsStationItem(@RequestBody List<TmsStationItem> tmsStationItems) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationItemService.deleteTmsStationItem(tmsStationItems));
    }

    /**
     * TMS 측정소항목 삭제 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 삭제 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationItemCode", value = "TMS 측정소코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/deletechecktmsstationitem")
    public ResponseEntity<Integer> getDeleteCheckTmsStationItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정소코드
        String stationItemCode = map.containsKey("stationItemCode") ? map.get("stationItemCode").toString() : "";

        return ResponseEntity.ok().body(tmsStationItemService.getDeleteCheckTmsStationItem(stationItemCode));
    }

}
