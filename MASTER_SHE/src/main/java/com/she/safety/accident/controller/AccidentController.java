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
package com.she.safety.accident.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.baseInfo.model.FacilityMst;
import com.she.chm.model.Chemprod;
import com.she.common.model.DefaultParam;
import com.she.safety.accident.service.AccidentService;
import com.she.safety.model.Accident;
import com.she.safety.model.AccidentCauMeas;
import com.she.safety.model.AccidentInvestPsn;
import com.she.safety.model.AccidentRefPsn;
import com.she.safety.model.AccidentVictim;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 사사내고
 */
@RestController
@RequestMapping("/api/saf/accident/")
@Api(value = "/api/saf/accident/", description = "사고관리 서비스")
public class AccidentController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AccidentService accidentService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "사고 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchCdOrgin\": \"\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR
            + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER
            + "} ";

    private static final String EDIT_JSON = "사고 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR
            + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \" Y \" " + STR_ENTER + "} ";

    /**
     * 사내사고 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 목록
     */
    @ApiOperation(value = "사고 조회[SAF01001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "accidentStepCd", value = "사내사고진행단계", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "from", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "endDate", value = "to", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "deptCd", value = "발생부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "area", value = "장소", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "accidentTitle", value = "사고명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "searchFlag", value = "진행단계에 따른 조회", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidents")
    public ResponseEntity<List<Accident>> getAccidents(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고진행단계
        String accidentStepCd = map.containsKey("accidentStepCd") ? map.get("accidentStepCd").toString() : "";
        // String[] accidentStepCds =
        // this.requestMapper.convertObjectListAsStringArray(map.get("accidentStepCds"));
        // from
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // to
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 발생부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 장소
        String area = map.containsKey("area") ? map.get("area").toString() : "";
        // 사고명
        String accidentTitle = map.containsKey("accidentTitle") ? map.get("accidentTitle").toString() : "";
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 진행단계에 따른 조회
        String searchFlag = map.containsKey("searchFlag") ? map.get("searchFlag").toString() : "";

        String bizApprStepNm = map.containsKey("bizApprStepNm") ? map.get("bizApprStepNm").toString() : "";

        List<Accident> accidents = new ArrayList<Accident>();
        if (searchFlag.equals("ocuur") || searchFlag.equals("")) {
            // 사고등록 or 속보
            accidents = accidentService.getAccidentOccurs(accidentStepCd, startDate, endDate, deptCd, deptSubYn, area, accidentTitle, plantCd, bizApprStepNm, defaultParam);
        } else if (searchFlag.equals("reception")) {
            // 사고 접수
            accidents = accidentService.getAccidentReceptions(accidentStepCd, startDate, endDate, deptCd, deptSubYn, area, accidentTitle, plantCd, bizApprStepNm, defaultParam);
        } else if (searchFlag.equals("result")) {
            // 사고유형
            String accidentType = map.containsKey("accidentType") ? map.get("accidentType").toString() : "";
            // 발생형태 대분류
            String occKindCd = map.containsKey("occKindCd") ? map.get("occKindCd").toString() : "";
            // 발생형태 중분류
            String occAttCd = map.containsKey("occAttCd") ? map.get("occAttCd").toString() : "";
            // 사고원인 직접
            String immCauseCd = map.containsKey("immCauseCd") ? map.get("immCauseCd").toString() : "";
            // 사고원인 간접
            String indCauseCd = map.containsKey("indCauseCd") ? map.get("indCauseCd").toString() : "";
            String assessYear = map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
            int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;
            // 사고 조사결과
            accidents = accidentService.getAccidentResults(accidentStepCd, startDate, endDate, deptCd, deptSubYn, area, accidentTitle, plantCd, accidentType, occKindCd, occAttCd, immCauseCd, indCauseCd, assessYear, monFlag, bizApprStepNm, defaultParam);
        }
        return ResponseEntity.ok().body(accidents);
    }

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     */
    @ApiOperation(value = "사고관리 상세조회[SAF01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("accident/1/{safAccidentNo}")
    public ResponseEntity<Accident> getAccidentOccur(@PathVariable("safAccidentNo") int safAccidentNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(accidentService.getAccident(safAccidentNo, "occur", defaultParam));
    }

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     */
    @ApiOperation(value = "사고관리 상세조회[SAF01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("accident/2/{safAccidentNo}")
    public ResponseEntity<Accident> getAccidentReception(@PathVariable("safAccidentNo") int safAccidentNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(accidentService.getAccident(safAccidentNo, "reception", defaultParam));
    }

    /**
     * 사내사고 상세 조회
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 사내사고
     */
    @ApiOperation(value = "사고관리 상세조회[SAF01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("accident/3/{safAccidentNo}")
    public ResponseEntity<Accident> getAccidentResult(@PathVariable("safAccidentNo") int safAccidentNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(accidentService.getAccident(safAccidentNo, "result", defaultParam));
    }

    /**
     * 사내사고 등록
     *
     * @param accident
     *            사내사고
     * @return 키
     */
    @ApiOperation(value = "사고관리 등록[SAF01003]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "accident", value = "사고관리", required = false, dataType = "Accident", paramType = "body") })
    @PostMapping("accident")
    public ResponseEntity<HashMap<String, Object>> createAccident(@RequestBody Accident accident) throws Exception {
        return ResponseEntity.ok().body(accidentService.createAccident(accident));
    }

    /**
     * 사내사고 저장
     *
     * @param accident
     *            사내사고
     * @return 키
     */
    @ApiOperation(value = "사고관리 수정[SAF01004]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "accident", value = "사고관리", required = false, dataType = "Accident", paramType = "body") })
    @PutMapping("accident")
    public ResponseEntity<HashMap<String, Object>> updateAccident(@RequestBody Accident accident) throws Exception {
        return ResponseEntity.ok().body(accidentService.updateAccident(accident));
    }

    /**
     * 사내사고 삭제
     *
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     */
    @ApiOperation(value = "사고관리 삭제[SAF01005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("accident/{safAccidentNo}")
    public ResponseEntity<Integer> deleteAccident(@PathVariable("safAccidentNo") int safAccidentNo) throws Exception {
        return ResponseEntity.ok().body(accidentService.deleteAccident(safAccidentNo));
    }

    /**
     * 사내사고 설비 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 설비 목록
     */
    @ApiOperation(value = "사고관리 설비조회[SAF01006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentfacilitys")
    public ResponseEntity<List<FacilityMst>> getAccidentFacilitys(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safAccidentNo = map.containsKey("safAccidentNo") ? Integer.parseInt("".equals(map.get("safAccidentNo").toString()) ? "0" : map.get("safAccidentNo").toString()) : 0;

        return ResponseEntity.ok().body(accidentService.getAccidentFacilitys(safAccidentNo));
    }

    /**
     * 사내사고 취급물질 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 취급물질 목록
     */
    @ApiOperation(value = "사고관리 취급물질 조회[SAF01007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentchemprods")
    public ResponseEntity<List<Chemprod>> getAccidentChemprods(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safAccidentNo = map.containsKey("safAccidentNo") ? Integer.parseInt("".equals(map.get("safAccidentNo").toString()) ? "0" : map.get("safAccidentNo").toString()) : 0;

        return ResponseEntity.ok().body(accidentService.getAccidentChemprods(safAccidentNo, defaultParam));
    }

    /**
     * 사내사고조사 인원 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고조사 인원 목록
     */
    @ApiOperation(value = "사고관리 사고조사 인원 조회[SAF01009]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentinvestpsns")
    public ResponseEntity<List<AccidentInvestPsn>> getAccidentInvestPsns(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safAccidentNo = map.containsKey("safAccidentNo") ? Integer.parseInt("".equals(map.get("safAccidentNo").toString()) ? "0" : map.get("safAccidentNo").toString()) : 0;

        return ResponseEntity.ok().body(accidentService.getAccidentInvestPsns(safAccidentNo));
    }

    /**
     * 사내사고 피해자정보 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 피해자정보 목록
     */
    @ApiOperation(value = "사고관리 피해자정보 조회[SAF01010]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "victimTypeCd", value = "피해자구분코드", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentvictims")
    public ResponseEntity<List<AccidentVictim>> getAccidentVictims(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safAccidentNo = map.containsKey("safAccidentNo") ? Integer.parseInt("".equals(map.get("safAccidentNo").toString()) ? "0" : map.get("safAccidentNo").toString()) : 0;
        // 피해자구분코드
        String victimTypeCd = map.containsKey("victimTypeCd") ? map.get("victimTypeCd").toString() : "";

        return ResponseEntity.ok().body(accidentService.getAccidentVictims(safAccidentNo, victimTypeCd, defaultParam));
    }

    /**
     * 사내사고 원인 및 대책 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 원인 및 대책 목록
     */
    @ApiOperation(value = "사고관리 원인 및 대책 조회[SAF01011]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentcaumeass")
    public ResponseEntity<List<AccidentCauMeas>> getAccidentCauMeass(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safAccidentNo = map.containsKey("safAccidentNo") ? Integer.parseInt("".equals(map.get("safAccidentNo").toString()) ? "0" : map.get("safAccidentNo").toString()) : 0;

        return ResponseEntity.ok().body(accidentService.getAccidentCauMeass(safAccidentNo, defaultParam));
    }

    /**
     * 사내사고 원인 및 대책 삭제
     *
     * @param safAccidentCauMeasNo
     *            원인및대책번호
     * @param safAccidentNo
     *            사내사고번호
     * @return 변경 행 수
     */
    @ApiOperation(value = "사고관리 원인 및 대책 삭제[SAF01012]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentCauMeasNo", value = "원인및대책번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "path"), })
    @DeleteMapping("accidentcaumeas/{safAccidentCauMeasNo}/{safAccidentNo}")
    public ResponseEntity<Integer> deleteAccidentCauMeas(@PathVariable("safAccidentCauMeasNo") int safAccidentCauMeasNo, @PathVariable("safAccidentNo") int safAccidentNo) throws Exception {
        return ResponseEntity.ok().body(accidentService.deleteAccidentCauMeas(safAccidentCauMeasNo, safAccidentNo));
    }

    /**
     * 사내사고 관계자 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 관계자 목록
     */
    @ApiOperation(value = "사고관리 관계자 조회[SAF01013]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사내사고번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "victimTypeCd", value = "피해자구분코드", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentrefpsns")
    public ResponseEntity<List<AccidentRefPsn>> getAccidentRefPsns(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사내사고번호
        int safAccidentNo = map.containsKey("safAccidentNo") ? Integer.parseInt("".equals(map.get("safAccidentNo").toString()) ? "0" : map.get("safAccidentNo").toString()) : 0;

        return ResponseEntity.ok().body(accidentService.getAccidentRefPsns(safAccidentNo, defaultParam));
    }

    /**
     * 사내사고집계 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고집계 목록
     */
    @ApiOperation(value = "사고 집계[SAF01014]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "accidentYearPeriod", value = "기간-년도(발생일)", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "yearMonth", value = "년월", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "deptCd", value = "발생부서", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "accidentGubun", value = "사고구분(아차사고, 사고)", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "accidentType", value = "사고유형", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "occKindCd", value = "발생형태 대분류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "occAttCd", value = "발생형태 중분류", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "immCauseCd", value = "사고원인 직접", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "indCauseCd", value = "사고원인 간접", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("accidentstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getAccidentStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 점검년도
        String accidentYear = map.containsKey("accidentYear") ? map.get("accidentYear").toString() : "";
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 발생부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 사고구분(아차사고, 사고)
        String accidentGubun = map.containsKey("accidentGubun") ? map.get("accidentGubun").toString() : "";
        // 사고유형
        String accidentType = map.containsKey("accidentType") ? map.get("accidentType").toString() : "";

        // 발생형태 대분류
        String occKindCd = map.containsKey("occKindCd") ? map.get("occKindCd").toString() : "";
        // 발생형태 중분류
        String occAttCd = map.containsKey("occAttCd") ? map.get("occAttCd").toString() : "";
        // 사고원인 직접
        String immCauseCd = map.containsKey("immCauseCd") ? map.get("immCauseCd").toString() : "";
        // 사고원인 간접
        String indCauseCd = map.containsKey("indCauseCd") ? map.get("indCauseCd").toString() : "";

        return ResponseEntity.ok().body(accidentService.getAccidentStatus(accidentYear, plantCd, deptCd, deptSubYn, accidentGubun, accidentType, occKindCd, occAttCd, immCauseCd, indCauseCd, defaultParam));
    }

    /**
     * 안전사고 보고서 출력
     *
     * @param safAccidentNo
     *            사고번호
     * @return 안전사고 보고서 출력
     * @throws Exception
     */
    @ApiOperation(value = "개선결과 출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safAccidentNo", value = "사고번호", required = true, dataType = "int", paramType = "path"), })
    @GetMapping("/accidentprint/{safAccidentNo}")
    public @ResponseBody byte[] getPrint(@PathVariable("safAccidentNo") int safAccidentNo) throws Exception {
        File file = this.accidentService.getAccidentPrint(safAccidentNo);

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (Exception e) {
            throw e;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 조사결과 현황
     * 
     * @param parameter
     *            검색조건
     * @return 조사결과 현황
     * @throws Exception
     */
    @GetMapping("/accidentresultstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getAccidentResultsStatusList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 평가계획 ID
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String assessYear = map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
        String regRegdemCd = map.containsKey("regRegdemCd") ? map.get("regRegdemCd").toString() : "";
        String riskType = map.containsKey("riskType") ? map.get("riskType").toString() : "";
        String totalFlag = map.containsKey("totalFlag") ? map.get("totalFlag").toString() : "";
        return ResponseEntity.ok().body(accidentService.getAccidentResultsStatusList(plantCd, assessYear, regRegdemCd, riskType, totalFlag, defaultParam));
    }
}
