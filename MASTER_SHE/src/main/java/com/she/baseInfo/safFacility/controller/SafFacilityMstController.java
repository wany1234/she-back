package com.she.baseInfo.safFacility.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.baseInfo.model.SafFacilityMst;
import com.she.baseInfo.safFacility.service.SafFacilityMstService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/baseinfo/saffacility/")
@Api(value = "/api/baseinfo/saffacility/", description = "설비유형")
public class SafFacilityMstController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SafFacilityMstService safFacilityMstService;

    /**
     * 시설 리스트 조회
     * 
     * @return 시설 리스트
     * @throws Exception
     */
    @ApiOperation(value = "시설 리스트 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("facilitymsts")
    public ResponseEntity<List<SafFacilityMst>> getSafFacilityMsts(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 시설유형코드
        String comFacilityTypeCd = map.containsKey("comFacilityTypeCd") ? map.get("comFacilityTypeCd").toString() : "";
        // 시설코드
        String facilityCd = map.containsKey("facilityCd") ? map.get("facilityCd").toString() : "";
        // 시설명
        String facilityNm = map.containsKey("facilityNm") ? map.get("facilityNm").toString() : "";
        // 관리부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 마스터만 조회하는지 구분
        String flag = map.containsKey("flag") ? map.get("flag").toString() : "";

        return ResponseEntity.ok().body(safFacilityMstService.getSafFacilityMsts(plantCd, comFacilityTypeCd, facilityCd, facilityNm, deptCd, useYn, flag));
    }

    /**
     * 시설 상세조회
     * 
     * @param comFacilityMstCd
     *            시설코드
     * @return 시설
     */
    @ApiOperation(value = "시설 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "comFacilityMstCd", value = "시설코드", required = false, dataType = "String", paramType = "path") })
    @GetMapping("facilitymst/{comFacilityMstCd}")
    public ResponseEntity<SafFacilityMst> getSafFacilityMst(@PathVariable("comFacilityMstCd") String comFacilityMstCd) throws Exception {
        return ResponseEntity.ok().body(safFacilityMstService.getSafFacilityMst(comFacilityMstCd));
    }

    /**
     * 시설 등록
     * 
     * @param safFacilityMst
     *            시설
     * @return 키
     */
    @ApiOperation(value = "시설 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityMst", value = "시설마스터", required = false, dataType = "SafFacilityMst", paramType = "body") })
    @PostMapping("facilitymst")
    public ResponseEntity<String> createSafFacilityMst(@RequestBody SafFacilityMst safFacilityMst) throws Exception {
        return ResponseEntity.ok().body(!safFacilityMstService.createSafFacilityMst(safFacilityMst).equals("") ? safFacilityMst.getFacilityCd() : "");
    }

    /**
     * 시설 수정
     * 
     * @param safFacilityMst
     *            시설
     * @return 수정 행
     */

    @ApiOperation(value = "시설 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safFacilityMst", value = "시설마스터", required = false, dataType = "SafFacilityMst", paramType = "body") })
    @PutMapping("facilitymst")
    public ResponseEntity<Integer> updateSafFacilityMst(@RequestBody SafFacilityMst safFacilityMst) throws Exception {
        return ResponseEntity.ok().body(safFacilityMstService.updateSafFacilityMst(safFacilityMst));
    }
}
