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
package com.she.safety.wkod.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.safety.constSafe.service.ConstService;
import com.she.safety.model.ConstLegalLcnMaster;
import com.she.safety.model.FacilityUsed;
import com.she.safety.model.LOTO;
import com.she.safety.model.Map;
import com.she.safety.model.Signature;
import com.she.safety.model.WkodChkResult;
import com.she.safety.model.WkodMapBig;
import com.she.safety.model.WkodMapMid;
import com.she.safety.model.WkodMapSml;
import com.she.safety.model.WkodMaster;
import com.she.safety.model.WkodSubconnWorker;
import com.she.safety.wkod.service.WkodMasterService;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 작업허가서
 *
 */
@RestController
@RequestMapping("/api/saf/wkod")
@Api(value = "/api/saf/wkod", description = "작업허가서 서비스")
public class WkodMasterController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WkodMasterService wkodMasterService;

    @Autowired
    private ConstService constService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "작업허가서 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchCdOrgin\": \"\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR
            + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER
            + "} ";

    private static final String EDIT_JSON = "작업허가서 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR
            + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \" Y \" " + STR_ENTER + "} ";

    private static final Logger logger = LoggerFactory.getLogger(WkodMasterController.class);

    /**
     * 작업허가서 조회
     *
     * @param parameter
     * @return 작업허가서 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 조회[SAF01001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "constTitle", value = "공사명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "wkodClsCd", value = "허가서구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "vendorNm", value = "공사업체", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "workYmd", value = "작업기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "reqDeptCd", value = "신청부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "wkodKindCds", value = "reqDeptCd", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "pubDeptCd", value = "주관부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "wkodStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "wkodNum", value = "작업NO", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "workTitle", value = "작업명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmasters")
    public ResponseEntity<List<WkodMaster>> getWkodMasters(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String startYmd = "";
        String endYmd = "";

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 허가서구분
        String wkodClsCd = map.containsKey("wkodClsCd") ? map.get("wkodClsCd").toString() : "";
        // 공사업체
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 작업기간
        String[] workYmd = this.requestMapper.convertObjectListAsStringArray(map.get("workYmd"));
        // 신청부서
        String reqDeptCd = map.containsKey("reqDeptCd") ? map.get("reqDeptCd").toString() : "";
        // 하위신청부서 포함여부
        String reqDeptSubYn = map.containsKey("reqDeptSubYn") ? map.get("reqDeptSubYn").toString() : "Y";

        // 작업구분
        String[] wkodKindCds = this.requestMapper.convertObjectListAsStringArray(map.get("wkodKindCds"));
        // 주관부서
        String pubDeptCd = map.containsKey("pubDeptCd") ? map.get("pubDeptCd").toString() : "";
        // 하위주관부서 포함여부
        String pubDeptSubYn = map.containsKey("pubDeptSubYn") ? map.get("pubDeptSubYn").toString() : "Y";

        // 진행단계
        String wkodStepCd = map.containsKey("wkodStepCd") ? map.get("wkodStepCd").toString() : "";
        // 작업NO
        String wkodNum = map.containsKey("wkodNum") ? map.get("wkodNum").toString() : "";
        // 검색어
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";
        // 작업업체
        String subconnNm = map.containsKey("subconnNm") ? map.get("subconnNm").toString() : "";

        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";
        String psmYn = map.containsKey("psmYn") ? map.get("psmYn").toString() : "";
        String constNo = map.containsKey("constNo") ? map.get("constNo").toString() : "";

        if (workYmd != null && workYmd.length == 2) {
            startYmd = workYmd[0];
            endYmd = workYmd[1];
        }

        return ResponseEntity.ok().body(wkodMasterService.getWkodMasters(plantCd, wkodClsCd, vendorCd, startYmd, endYmd, reqDeptCd, reqDeptSubYn, wkodKindCds, pubDeptCd, pubDeptSubYn, wkodStepCd, wkodNum, keyword, searchFlag, constNo, psmYn, subconnNm, defaultParam));
    }

    /**
     * 작업허가서 상세 조회
     *
     * @param parameter
     *            (작업허가서 ID)
     * @return 작업허가서 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 상세 조회[SAF01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmaster/{wkodNo}")
    public ResponseEntity<WkodMaster> getWkodMaster(@PathVariable("wkodNo") int wkodNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.getWkodMaster(wkodNo, defaultParam));
    }

    @ApiOperation(value = "작업허가서 안전조치 점검항목 base 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmaster/base/wkodchkitems")
    public ResponseEntity<List<WkodChkResult>> getSafWkodChkResultBase(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int wkodNo = map.containsKey("wkodNo") ? Integer.parseInt("".equals(map.get("wkodNo").toString()) ? "0" : map.get("wkodNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        return ResponseEntity.ok().body(wkodMasterService.getSafWkodChkResultBase(wkodNo, plantCd, defaultParam));
    }

    @ApiOperation(value = "작업허가서 지도 조회(최대1)", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmaster/map")
    public ResponseEntity<Map> getSafWkodMap(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int mapNo = map.containsKey("mapNo") ? Integer.parseInt("".equals(map.get("mapNo").toString()) ? "0" : map.get("mapNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(wkodMasterService.getSafWkodMap(mapNo, plantCd, defaultParam));
    }

    /**
     * 작업허가서 현황 조회
     *
     * @param parameter
     * @return 작업허가서 현황
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 현황 조회[SAF01007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "reqDeptCd", value = "신청부서", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "subconnNm", value = "공사업체", required = false, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "workYmd", value = "작업기간", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmasterstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getWkodMasterStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 신청부서
        String reqDeptCd = map.containsKey("reqDeptCd") ? map.get("reqDeptCd").toString() : "";
        // 공사업체
        String subconnNm = map.containsKey("subconnNm") ? map.get("subconnNm").toString() : "";
        // 작업기간
        String[] workYmd = this.requestMapper.convertObjectListAsStringArray(map.get("workYmd"));
        String startYmd = "";
        String endYmd = "";
        if (workYmd != null && workYmd.length == 2) {
            startYmd = workYmd[0];
            endYmd = workYmd[1];
        }

        return ResponseEntity.ok().body(wkodMasterService.getWkodMasterStatus(plantCd, reqDeptCd, subconnNm, startYmd, endYmd, defaultParam));
    }

    /**
     * 작업허가서 현황 조회 (사업장에 따른 신청부서 별 주관부서별 공사업체)
     *
     * @param parameter
     * @return 작업허가서 현황 (사업장에 따른 신청부서 별 주관부서별 공사업체)
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 현황 조회(사업장에 따른 신청부서 별 주관부서별 공사업체)[SAF01007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "reqDeptNm", value = "신청부서", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pubDeptNm", value = "주관부서", required = false, dataType = "String", paramType = "query"), @ApiImplicitParam(name = "subconnNm", value = "공사업체", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmasterstatussub")
    public ResponseEntity<List<HashMap<String, Object>>> getWkodMasterStatusSub(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 신청부서
        String reqDeptCd = map.containsKey("reqDeptCd") ? map.get("reqDeptCd").toString() : "";
        // 신청부서
        String pubDeptCd = map.containsKey("pubDeptCd") ? map.get("pubDeptCd").toString() : "";
        // 공사업체
        String subconnNm = map.containsKey("subconnNm") ? map.get("subconnNm").toString() : "";
        // 작업기간
        String[] workYmd = this.requestMapper.convertObjectListAsStringArray(map.get("workYmd"));
        String startYmd = "";
        String endYmd = "";
        if (workYmd != null && workYmd.length == 2) {
            startYmd = workYmd[0];
            endYmd = workYmd[1];
        }

        return ResponseEntity.ok().body(wkodMasterService.getWkodMasterStatusSub(plantCd, reqDeptCd, pubDeptCd, subconnNm, startYmd, endYmd, defaultParam));
    }

    /**
     * 작업허가서 항목 생성
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 작업허가서 항목 Key값
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 등록[CHM01004]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodMaster", value = "작업허가서", required = false, dataType = "wkodMaster", paramType = "body") })
    @PostMapping("/wkodmaster")
    @Transactional
    public ResponseEntity<Integer> createWkodMaster(@RequestBody WkodMaster wkodMaster) throws Exception {
        // 작업허가서 등록
        return ResponseEntity.ok().body(wkodMasterService.createWkodMaster(wkodMaster));
    }

    /**
     * 작업허가서 항목 수정
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 수정[CHM01005]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodMaster", value = "작업허가서", required = false, dataType = "wkodMaster", paramType = "body") })
    @PutMapping("/wkodmaster")
    public ResponseEntity<Integer> updateWkodMaster(@RequestBody WkodMaster wkodMaster) throws Exception {
        if (wkodMaster.getCoopDeptCd() == null) {
            wkodMaster.setCoopDeptCd("");
        }
        // if (wkodMaster.getPubDeptCd() == null)
        // wkodMaster.setPubDeptCd("");
        if (wkodMaster.getOverConfirmDt() == null) {
            wkodMaster.setOverConfirmDt("");
        }

        // 작업허가서 수정
        int wkodNo = wkodMasterService.updateWkodMaster(wkodMaster);

        return ResponseEntity.ok().body(wkodNo);
    }

    /**
     * 작업허가서 삭제
     *
     * @param wkodNo
     *            방지시설번호
     * @return 작업허가서 번호
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 삭제[SAF01006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodMaster", value = "작업허가서", required = false, dataType = "wkodMaster", paramType = "body") })
    @DeleteMapping("/wkodmaster")
    public ResponseEntity<Integer> deleteWkodMaster(@RequestBody WkodMaster wkodMaster) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.deleteWkodMaster(wkodMaster));
    }

    @ApiOperation(value = "작업허가서 작업완료", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodMaster", value = "작업허가서", required = false, dataType = "wkodMaster", paramType = "body") })
    @PutMapping("/wkodmaster/step/1")
    public ResponseEntity<Integer> constCompleteWkodMaster(@RequestBody WkodMaster wkodMaster) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.constCompleteWkodMaster(wkodMaster));
    }

    @ApiOperation(value = "작업허가서 연장", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodMaster", value = "작업허가서", required = false, dataType = "wkodMaster", paramType = "body") })
    @PutMapping("/wkodmaster/step/2")
    public ResponseEntity<Integer> overWkodMaster(@RequestBody WkodMaster wkodMaster) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.overWkodMaster(wkodMaster));
    }

    @ApiOperation(value = "작업허가서 취소", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodMaster", value = "작업허가서", required = false, dataType = "wkodMaster", paramType = "body") })
    @PutMapping("/wkodmaster/step/3")
    public ResponseEntity<Integer> cancelWkodMaster(@RequestBody WkodMaster wkodMaster) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.cancelWkodMaster(wkodMaster));
    }

    /**
     * 작업허가서별 설비 조회
     *
     * @param parameter
     *            조회조건
     * @return 작업허가서별 설비 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서별 설비 조회[SAF01008]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서 번호", required = false, dataType = "int", paramType = "body"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmasterfacility")
    public ResponseEntity<List<FacilityUsed>> getWkodMasterFacility(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 작업허가서 번호
        int wkodNo = map.containsKey("wkodNo") ? Integer.parseInt("".equals(map.get("wkodNo").toString()) ? "0" : map.get("wkodNo").toString()) : 0;

        return ResponseEntity.ok().body(wkodMasterService.getWkodMasterFacility(wkodNo, defaultParam));
    }

    /**
     * 작업허가서별 설비 삭제
     *
     * @param facilityUsed
     *            작업허가서별 설비목록
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서별 설비 삭제[SAF01009]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "facilityUsed", value = "작업허가서별 설비목록", required = false, dataType = "List<FacilityUsed>", paramType = "body") })
    @DeleteMapping("/wkodmasterfacility")
    public ResponseEntity<Integer> deleteWkodMasterFacility(@RequestBody List<FacilityUsed> facilityUsed) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.deleteWkodMasterFacility(facilityUsed));
    }

    /**
     * 법적인허가대상 조회
     *
     * @param parameter
     * @return 법적인허가대상 목록
     * @throws Exception
     */
    @ApiOperation(value = "법적인허가대상 조회[SAF01010]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodlegallcns")
    public ResponseEntity<List<ConstLegalLcnMaster>> getWkodLegalLcns(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 공사번호
        String constNo = map.containsKey("constNo") ? map.get("constNo").toString() : "";
        // 작업허가서 번호
        int wkodNo = map.containsKey("wkodNo") ? Integer.parseInt("".equals(map.get("wkodNo").toString()) ? "0" : map.get("wkodNo").toString()) : 0;

        List<ConstLegalLcnMaster> constLegalLcnMasters = new ArrayList<ConstLegalLcnMaster>();

        if (wkodNo > 0) {
            constLegalLcnMasters = wkodMasterService.getWkodNoLegalLcns(wkodNo);
        } else {
            constLegalLcnMasters = wkodMasterService.getWkodLegalLcns(constNo);
        }

        return ResponseEntity.ok().body(constLegalLcnMasters);
    }

    /**
     * 작업허가서 LOTO 조회
     *
     * @param parameter
     * @return 작업허가서 LOTO 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 LOTO 조회[SAF01010]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodlotos")
    public ResponseEntity<List<LOTO>> getWkodLOTOs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 공사번호
        String constNo = map.containsKey("constNo") ? map.get("constNo").toString() : "";
        // 작업허가서 번호
        int wkodNo = map.containsKey("wkodNo") ? Integer.parseInt("".equals(map.get("wkodNo").toString()) ? "0" : map.get("wkodNo").toString()) : 0;

        List<LOTO> lotos = new ArrayList<LOTO>();

        if (wkodNo > 0) {
            lotos = wkodMasterService.getWkodLotos(wkodNo);
        } else {
            lotos = constService.getLotos(constNo);
        }

        return ResponseEntity.ok().body(lotos);
    }

    /**
     * 작업허가서별 작업자 조회
     *
     * @param parameter
     * @return 작업허가서별 작업자 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서별 작업자 조회[SAF01011]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodsubconnworkers")
    public ResponseEntity<List<WkodSubconnWorker>> getWkodSubconnWorkers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 작업허가서번호
        int wkodNo = map.containsKey("wkodNo") ? Integer.parseInt("".equals(map.get("wkodNo").toString()) ? "0" : map.get("wkodNo").toString()) : 0;

        return ResponseEntity.ok().body(wkodMasterService.getWkodSubconnWorkers(wkodNo));
    }

    /**
     * 작업허가서 항목 진행단계 변경
     *
     * @param wkodNo
     *            작업허가서 ID
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 수정[CHM01005]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "apprRqstNo", value = "결재진행no", required = false, dataType = "int", paramType = "path"), })
    @PutMapping("/wkodmastercomplete/{wkodNo}/{apprRqstNo}")
    public ResponseEntity<Integer> completeWkodMaster(@PathVariable("wkodNo") int wkodNo, @PathVariable("apprRqstNo") int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.completeWkodMaster(wkodNo, apprRqstNo));
    }

    /**
     * 작업허가서 출력
     * 
     * @param parameter
     *            (작업허가서 ID)
     * @return 작업허가서 출력
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 출력[SAF01020]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmasterprint/{wkodNo}")
    public @ResponseBody byte[] getWkodMasterPrint(@PathVariable("wkodNo") int wkodNo, HttpServletResponse response, @ModelAttribute DefaultParam defaultParam) throws Exception {
        File file = this.wkodMasterService.getWkodMasterPrint(wkodNo, defaultParam);

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw fe;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            throw ie;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 작업허가서 양식 출력
     * 
     * @param parameter
     *            (작업허가서 ID)
     * @return 작업허가서 출력
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 출력[SAF01020]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/wkodmastertemplete/{gubun}")
    public @ResponseBody byte[] getWkodMasterTemplete(@PathVariable("gubun") int gubun, HttpServletResponse response) throws Exception {
        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");
        File file = null;
        if (gubun == 1) {
            file = new File(path.getCodeNm() + "\\가동전 점검표.hwp");
        } else if (gubun == 2) {
            file = new File(path.getCodeNm() + "\\중량물작업계획서(양식).xlsx");
        } else if (gubun == 3) {
            file = new File(path.getCodeNm() + "\\작업안전분석양식(JSA).hwp");
        }

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw fe;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            throw ie;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 작업허가서 서명 확인 등록(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 서명 확인 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "signature", value = "서명", required = false, dataType = "signature", paramType = "body") })
    @PostMapping("/confirmsign")
    public ResponseEntity<Integer> createConfirmSign(@RequestBody Signature signature) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.createConfirmSign(signature));
    }

    /**
     * 작업허가서 서명 확인 수정(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 서명 확인 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "signature", value = "서명", required = false, dataType = "signature", paramType = "body") })
    @PutMapping("/confirmsign")
    public ResponseEntity<Integer> updateConfirmSign(@RequestBody Signature signature) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.updateConfirmSign(signature));
    }

    /**
     * 작업허가서 서명 확인 삭제(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 서명 확인 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @DeleteMapping("/confirmsign/{signNo}")
    public ResponseEntity<Integer> deleteConfirmSign(@PathVariable("signNo") int signNo) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.deleteConfirmSign(signNo));
    }

    /**
     * 작업허가서 서명 확인 조회 (모바일)
     *
     * @param parameter
     * @return 서명번호
     * @throws Exception
     */
    @ApiOperation(value = "작업허가서 서명 확인 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "wkodNo", value = "작업허가서번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/confirmsign/{wkodNo}")
    public ResponseEntity<List<Signature>> getConfirmSigns(@PathVariable("wkodNo") int wkodNo) throws Exception {
        ;
        return ResponseEntity.ok().body(wkodMasterService.getConfirmSigns(wkodNo));
    }
    
    @GetMapping("/user/wkodMapBig/{safEduCourseNo}")
    public ResponseEntity<List<WkodMapBig>> wkodMapBig(@PathVariable String safEduCourseNo) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.getSafWkodMapBig(safEduCourseNo));
    }
    @GetMapping("/user/wkodMapMid/{safEduCourseNo}/{safEduMstNo}")
    public ResponseEntity<List<WkodMapMid>> wkodMapMid(@PathVariable String safEduCourseNo, @PathVariable String safEduMstNo) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.getSafWkodMapMid(safEduCourseNo, safEduMstNo));
    }
    @GetMapping("/user/wkodMapSml/{safEduCourseNo}/{safEduMstNo}/{completYn}")
    public ResponseEntity<List<WkodMapSml>> wkodMapSml(@PathVariable String safEduCourseNo, @PathVariable String safEduMstNo, @PathVariable String completYn) throws Exception {
        return ResponseEntity.ok().body(wkodMasterService.getSafWkodMapSml(safEduCourseNo, safEduMstNo, completYn));
    }
}
