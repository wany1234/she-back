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

import com.she.env.tms.baseInfo.service.TmsStationService;
import com.she.env.tms.model.OutletFacility;
import com.she.env.tms.model.TmsStation;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/baseinfo")
@Api(value = "/api/env/tms/baseinfo", description = "환경 tms 기준정보 서비스")
public class TmsStationController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private TmsStationService tmsStationService;

    /**
     * TMS 측정소 조회
     * 
     * @param parameter
     *            검색조건
     * @return TMS 측정소 목록
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 조회[ENV10001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "tmsType", value = "측정소 구분", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/tmsstations")
    public ResponseEntity<List<TmsStation>> getTmsStations(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 측정소 구분
        String tmsType = map.containsKey("tmsType") ? map.get("tmsType").toString() : "";
        String stationName = map.containsKey("stationName") ? map.get("stationName").toString() : "";

        return ResponseEntity.ok().body(tmsStationService.getTmsStations(plantCd, tmsType, stationName));
    }

    /**
     * TMS 측정소 상세 조회
     * 
     * @param stationCode
     *            측정소코드(태그)
     * @return 측정소
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 상세조회[ENV10002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "branchCd", value = "TMS 측정소코드", required = false, dataType = "string", paramType = "path") })
    @GetMapping("/tmsstation/{stationCode}")
    public ResponseEntity<TmsStation> getTmsStation(@PathVariable(name = "stationCode") String stationCode) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationService.getTmsStation(stationCode));
    }

    /**
     * TMS 측정소 신규등록
     * 
     * @param chemicalBranch
     *            TMS 측정소
     * @return TMS 측정소 코드
     * @throws Exception
     */

    @ApiOperation(value = "TMS 측정소 등록[ENV10003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "tmsStation", value = "TMS 측정소정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PostMapping("/tmsstation")
    public ResponseEntity<String> createTmsStation(@RequestBody TmsStation tmsStation) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationService.createTmsStation(tmsStation));
    }

    /**
     * TMS 측정소 수정
     * 
     * @param chemicalBranch
     *            TMS 측정소
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 수정[ENV10004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chemicalBranch", value = "TMS 측정소정보", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @PutMapping("/tmsstation")
    public ResponseEntity<Integer> updateTmsStation(@RequestBody TmsStation tmsStation) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationService.updateTmsStation(tmsStation));
    }

    /**
     * TMS 측정소명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 체크[ENV10005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationName", value = "TMS 측정소명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "stationCodeOrgin", value = "변경전 TMS 측정소코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "stationCode", value = "변경할 TMS 측정소코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checktmsstation")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckTmsStation(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정소명
        String stationName = map.containsKey("stationName") ? map.get("stationName").toString() : "";
        // TMS 측정소코드 자기자신거
        String stationCodeOrign = map.containsKey("stationCodeOrign") ? map.get("stationCodeOrign").toString() : "";
        // TMS 측정소코드 바꿔 볼려고 하는거
        String stationCode = map.containsKey("stationCode") ? map.get("stationCode").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(tmsStationService.getCheckTmsStation(stationName, stationCodeOrign, stationCode, plantCd));
    }

    /**
     * TMS 측정소 삭제
     * 
     * @param chemicalBranch
     *            TMS 측정소
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 삭제[ENV10006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "tmsStations", value = "TMS 측정소정보s", required = false, dataType = "ChemicalBranch", paramType = "body") })
    @DeleteMapping("/tmsstation")
    public ResponseEntity<Integer> deleteTmsStation(@RequestBody List<TmsStation> tmsStations) throws Exception {
        return ResponseEntity.ok().body(this.tmsStationService.deleteTmsStation(tmsStations));
    }

    /**
     * TMS 측정소명 삭제 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "TMS 측정소 삭제 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "stationCode", value = "변경할 TMS 측정소코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/deletechecktmsstation")
    public ResponseEntity<Integer> getDeleteCheckTmsStation(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // TMS 측정소코드
        String stationCode = map.containsKey("stationCode") ? map.get("stationCode").toString() : "";

        return ResponseEntity.ok().body(tmsStationService.getDeleteCheckTmsStation(stationCode));
    }

    @ApiOperation(value = "TMS 매핑 배출시설에 따른 배출구 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "mgDeptCd", value = "관리부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/outletfacilitys")
    public ResponseEntity<List<OutletFacility>> getOuletFacilitys(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 관리부서
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";

        return ResponseEntity.ok().body(tmsStationService.getOuletFacilitys(plantCd, mgDeptCd));
    }

}
