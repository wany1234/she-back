package com.she.safety.hazardousRiskMechanism.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.she.safety.hazardousRiskMechanism.service.HazardousRiskMechanismService;
import com.she.safety.model.HazardousRiskMechanism;
import com.she.safety.model.SafCheckLog;
import com.she.safety.model.SafCheckMachine;
import com.she.safety.model.SafCheckUser;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 유해위험기계기구
 */
@RestController
@RequestMapping("/api/saf/hazardousriskmechanism/")
@Api(value = "/api/saf/hazardousriskmechanism/", description = "유해위험기계기구 서비스")
public class HazardousRiskMechanismController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private HazardousRiskMechanismService hazardousRiskMechanismService;

    /**
     * 유해위험기계기구 조회
     * 
     * @param parameter
     *            검색조건
     * @return 유해위험기계기구 목록
     */
    @ApiOperation(value = "유해위험기계기구 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "machineTypeCd", value = "(C)기계분류", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "inspYn", value = "검사대상", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "startDate", value = "차기검사일자 검색 시작일", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "차기검사일자 검색 종료일", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "periodYn", value = "차기검사일자 검색 유무", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "itemNo", value = "ITEM NO", required = false, dataType = "string", paramType = "query") })
    @GetMapping("hazardousriskmechanisms")
    public ResponseEntity<HazardousRiskMechanism> getHazardousRiskMechanisms(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        HazardousRiskMechanism hazardousRiskMechanism = new HazardousRiskMechanism();

        // 사업장명
        hazardousRiskMechanism.setPlantCd(map.containsKey("plantCd") ? map.get("plantCd").toString() : "");
        hazardousRiskMechanism.setMachineTypeCd(map.containsKey("machineTypeCd") ? map.get("machineTypeCd").toString() : "");
        hazardousRiskMechanism.setInspYn(map.containsKey("inspYn") ? map.get("inspYn").toString() : "");
        if ("Y".equals(map.get("periodYn").toString())) {
            hazardousRiskMechanism.setStartDate(map.containsKey("startDate") ? map.get("startDate").toString() : "");
            hazardousRiskMechanism.setEndDate(map.containsKey("endDate") ? map.get("endDate").toString() : "");
        }
        hazardousRiskMechanism.setItemNo(map.containsKey("itemNo") ? map.get("itemNo").toString() : "");

        return ResponseEntity.ok().body(hazardousRiskMechanismService.getHazardousRiskMechanisms(hazardousRiskMechanism, defaultParam));
    }

    /**
     * 유해위험기계기구 등록
     * 
     * @param SafCheckMachine
     *            유해위험기계기구
     * @return 키
     */
    @ApiOperation(value = "유해위험기계기구 등록[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safCheckMachine", value = "유해위험기계기구", required = false, dataType = "SafCheckMachine", paramType = "body") })
    @PostMapping("hazardousriskmechanism")
    public ResponseEntity<Integer> createHazardousRiskMechanism(@RequestBody SafCheckMachine safCheckMachine) throws Exception {
        return ResponseEntity.ok().body(hazardousRiskMechanismService.createHazardousRiskMechanism(safCheckMachine));
    }

    /**
     * 유해위험기계기구 상세 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 유해위험기계기구 상세
     */
    @ApiOperation(value = "유해위험기계기구 상세조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "checkMachineNo", value = "유해위험기계기구 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("hazardousriskmechanism/{checkMachineNo}")
    public ResponseEntity<SafCheckMachine> getHazardousRiskMechanism(@PathVariable("checkMachineNo") int checkMachineNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(hazardousRiskMechanismService.getHazardousRiskMechanism(checkMachineNo, defaultParam));
    }

    /**
     * 유해위험기계기구 수정
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 키
     */
    @ApiOperation(value = "유해위험기계기구 수정[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safCheckMachine", value = "유해위험기계기구", required = false, dataType = "SafCheckMachine", paramType = "body") })
    @PutMapping("hazardousriskmechanism")
    public ResponseEntity<Integer> updateHazardousRiskMechanism(@RequestBody SafCheckMachine safCheckMachine) throws Exception {
        return ResponseEntity.ok().body(hazardousRiskMechanismService.updateHazardousRiskMechanism(safCheckMachine));
    }

    /**
     * 유해위험기계기구 삭제
     * 
     * @param safCheckMachines
     *            유해위험기계기구
     * @return 변경 행 수
     */
    @ApiOperation(value = "유해위험기계기구 삭제[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safCheckMachines", value = "유해위험기계기구", required = false, dataType = "List", paramType = "path") })
    @DeleteMapping("hazardousriskmechanism")
    public ResponseEntity<Integer> deleteHazardousRiskMechanism(@RequestBody List<SafCheckMachine> safCheckMachines) throws Exception {
        return ResponseEntity.ok().body(hazardousRiskMechanismService.deleteHazardousRiskMechanism(safCheckMachines));
    }

    /**
     * 검사 이력 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 점검 이력
     */
    @ApiOperation(value = "검사 이력 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "checkMachineNo", value = "유해위험기계기구 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("safchecklog/{checkMachineNo}")
    public ResponseEntity<List<SafCheckLog>> getSafCheckLog(@PathVariable("checkMachineNo") int checkMachineNo) throws Exception {
        return ResponseEntity.ok().body(hazardousRiskMechanismService.getSafCheckLog(checkMachineNo));
    }

    /**
     * 알람 담당자 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 알람 담당자
     */
    @ApiOperation(value = "알람 담당자 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "checkMachineNo", value = "유해위험기계기구 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("safcheckuser/{checkMachineNo}")
    public ResponseEntity<List<SafCheckUser>> getSafCheckUser(@PathVariable("checkMachineNo") int checkMachineNo) throws Exception {
        return ResponseEntity.ok().body(hazardousRiskMechanismService.getSafCheckUsers(checkMachineNo));
    }
}
