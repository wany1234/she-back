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
package com.she.safety.constSafe.controller;

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
import com.she.safety.constSafe.service.ConstService;
import com.she.safety.model.Const;
import com.she.safety.model.ConstKindSubconn;
import com.she.safety.model.ConstKindSubconnWorker;
import com.she.safety.model.LOTO;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/saf/const/")
@Api(value = "api/saf/const/", description = "공사현황 서비스")
public class ConstController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ConstService constService;

    private static final String PADDING_FOUR = "&nbsp;&nbsp;&nbsp;&nbsp;";
    private static final String STR_ENTER = " \n ";

    private static final String ADD_JSON = "공사 등록 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchCdOrgin\": \"\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR
            + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \"Y\" " + STR_ENTER
            + "} ";

    private static final String EDIT_JSON = "공사 수정 JSON 예제 " + STR_ENTER + "{ " + STR_ENTER + PADDING_FOUR + "\"branchCd\": \"법인코드\", " + STR_ENTER + PADDING_FOUR + "\"branchNmKr\": \"법인명(국문)\", " + STR_ENTER + PADDING_FOUR + "\"branchNmEn\": \"법인명(영문)\", " + STR_ENTER + PADDING_FOUR + "\"branchTel\": \"연락처\", " + STR_ENTER + PADDING_FOUR
            + "\"branchAddrKr\": \"법인주소\", " + STR_ENTER + PADDING_FOUR + "\"branchCeoKr\": \"대표자\", " + STR_ENTER + PADDING_FOUR + "\"createUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"updateUserId\": \"system\", " + STR_ENTER + PADDING_FOUR + "\"useYn\": \" Y \" " + STR_ENTER + "} ";

    /**
     * 공사현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 공사현황 목록
     * @throws Exception
     */
    @ApiOperation(value = "공사현황 조회[CSF01001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "constStepCd", value = "공사진행단계", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "bizApprStepCd", value = "결재진행단계", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "constPeriod", value = "작업기간", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "constTitle", value = "공사명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "vendorCd", value = "협력업체", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "showVendorYn", value = "협력업체 노출여부", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("consts")
    public ResponseEntity<List<Const>> getConsts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 공사진행단계
        String constStepCd = map.containsKey("constStepCd") ? map.get("constStepCd").toString() : "";
        // 결재진행단계
        String bizApprStepCd = map.containsKey("bizApprStepCd") ? map.get("bizApprStepCd").toString() : "";
        // 작업기간
        String[] constPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("constPeriod"));
        String constStartYmd = "";
        String constEndYmd = "";
        if (constPeriod != null && constPeriod.length == 2) {
            constStartYmd = constPeriod[0];
            constEndYmd = constPeriod[1];
        }
        // 공사명
        String constTitle = map.containsKey("constTitle") ? map.get("constTitle").toString() : "";
        // 협력업체
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 협력업체 노출여부
        String showVendorYn = map.containsKey("showVendorYn") ? map.get("showVendorYn").toString() : "";

        return ResponseEntity.ok().body(constService.getConsts(plantCd, constStepCd, bizApprStepCd, constStartYmd, constEndYmd, constTitle, vendorCd, showVendorYn, defaultParam));
    }

    /**
     * 공사 상세 조회
     *
     * @param constNo
     *            공사번호
     * @return 공사현황
     * @throws Exception
     */
    @ApiOperation(value = "공사 상세조회[CSF01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("const/{constNo}")
    public ResponseEntity<Const> getConst(@PathVariable(name = "constNo") String constNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.constService.getConst(constNo, defaultParam));
    }

    /**
     * 공사 상세 조회(협력업체 포탈)
     *
     * @param constNo
     *            공사번호
     * @return 공사현황
     * @throws Exception
     */
    @ApiOperation(value = "공사 상세조회[CSF01002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("const/vendor/{constNo}/{vendorCd}")
    public ResponseEntity<Const> getConst(@PathVariable(name = "constNo") String constNo, @PathVariable(name = "vendorCd") String vendorCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.constService.getConstVendor(constNo, vendorCd, defaultParam));
    }

    /**
     * 공사현황 PO번호 중복체크
     *
     * @param parameter
     *            검색조건
     * @return 공사현황 PO번호 중복체크
     * @throws Exception
     */
    @ApiOperation(value = "공사현황 PO번호 중복체크[CSF01031]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "constNum", value = "po번호", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("constcheck")
    public ResponseEntity<HashMap<String, Object>> getConstCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 공사번호
        String constNo = map.containsKey("constNo") ? map.get("constNo").toString() : "";
        // po번호
        String constNum = map.containsKey("constNum") ? map.get("constNum").toString() : "";

        return ResponseEntity.ok().body(constService.getConstCheck(constNo, constNum));
    }

    /**
     * 공사 신규등록
     *
     * @param constData
     *            공사
     * @return 공사 번호
     * @throws Exception
     */

    @ApiOperation(value = "공사등록[CSF01003]", notes = ADD_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constData", value = "공사", required = false, dataType = "Const", paramType = "body") })
    @PostMapping("/const")
    public ResponseEntity<String> createConst(@RequestBody Const constData) throws Exception {
        return ResponseEntity.ok().body(this.constService.createConst(constData));
    }

    /**
     * 공사 수정
     *
     * @param constData
     *            공사
     * @return 수정 행 수
     * @throws Exception
     */

    @ApiOperation(value = "공사수정[CSF01004]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constData", value = "공사", required = false, dataType = "Const", paramType = "body") })
    @PutMapping("/const")
    public ResponseEntity<Integer> updateConst(@RequestBody Const constData) throws Exception {
        return ResponseEntity.ok().body(this.constService.updateConst(constData));
    }

    /**
     * 공사 삭제
     *
     * @param constData
     *            공사
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "공사 삭제[CSF01005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("const/{constNo}")
    public ResponseEntity<Integer> deleteConst(@PathVariable(name = "constNo") String constNo) throws Exception {
        return ResponseEntity.ok().body(this.constService.deleteConst(constNo));
    }

    /**
     * LOTO 조회
     *
     * @param parameter
     *            검색조건
     * @return LOTO 목록
     * @throws Exception
     */
    @ApiOperation(value = "LOTO 조회[CSF01006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("constlotos")
    public ResponseEntity<List<LOTO>> getLotos(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 공사번호
        // int constNo = map.containsKey("constNo")
        // ? Integer.parseInt("".equals(map.get("constNo").toString()) ? "0" :
        // map.get("constNo").toString())
        // : 0;
        String constNo = map.containsKey("constNo") ? map.get("constNo").toString() : "";

        return ResponseEntity.ok().body(constService.getLotos(constNo));
    }

    /**
     * 공사현황에 따른 작업구분별 업체정보 조회
     *
     * @param constNo
     *            공사번호
     * @return 공사현황에 따른 작업구분별 업체정보
     * @throws Exception
     */
    @ApiOperation(value = "공사 상세조회[CSF01007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "isDateCondition", value = "공사기간조건사용", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "vendorCd", value = "업체코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query") })
    @GetMapping("constkindsubconns")
    public ResponseEntity<List<ConstKindSubconn>> getConstKindSubconns(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 공사번호
        // int constNo = map.containsKey("constNo")
        // ? Integer.parseInt("".equals(map.get("constNo").toString()) ? "0" :
        // map.get("constNo").toString())
        // : 0;
        String constNo = map.containsKey("constNo") ? map.get("constNo").toString() : "";
        // 공사기간조건사용
        String isDateCondition = map.containsKey("isDateCondition") ? map.get("isDateCondition").toString() : "";
        // 업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 작업구분
        String[] wkodKindCds = this.requestMapper.convertObjectListAsStringArray(map.get("wkodKindCds"));
        // 공사명
        String constTitle = map.containsKey("constTitle") ? map.get("constTitle").toString() : "";

        return ResponseEntity.ok().body(this.constService.getConstKindSubconns(constNo, isDateCondition, vendorCd, plantCd, wkodKindCds, constTitle, defaultParam));
    }

    /**
     * 공사현황 작업구분별 업체번호 별 작업자들 조회
     *
     * @param constKindSubconnNos
     *            공사작업구분별_번호 리스트
     * @return 공사현황 작업구분별 업체번호 별 작업자들
     * @throws Exception
     */
    @ApiOperation(value = "공사 상세조회[CSF01007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constKindSubconnNos", value = "공사작업구분별_번호 리스트", required = false, dataType = "array", paramType = "query") })
    @GetMapping("constkindsubconnsworkers")
    public ResponseEntity<List<ConstKindSubconnWorker>> getConstKindSubconnsWorkers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 공사작업구분별_번호 리스트
        int[] constKindSubconnNos = this.requestMapper.convertObjectListAsIntArray(map.get("constKindSubconnNos"));

        return ResponseEntity.ok().body(this.constService.getConstKindSubconnsWorkers(constKindSubconnNos));
    }

    /**
     * 공사 수정
     *
     * @param constData
     *            공사
     * @return 수정 행 수
     * @throws Exception
     */

    @ApiOperation(value = "공사업체 진행단계 수정[CSF01008]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constKindSubconnNo", value = "공사업체번호", required = false, dataType = "int", paramType = "body"), @ApiImplicitParam(name = "subconnStepCd", value = "진행단계코드", required = false, dataType = "String", paramType = "body") })
    @PutMapping("/constsubconnstep")
    public ResponseEntity<Integer> updateConstSubconnStep(@RequestBody ConstKindSubconn constKindSubconn) throws Exception {
        return ResponseEntity.ok().body(this.constService.updateConstSubconnStep(constKindSubconn));
    }

    /**
     * 공사 진행단계 변경
     *
     * @param constNo
     *            공사번호
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "공사 진행단계 수정[CSF01009]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "path"), @ApiImplicitParam(name = "apprRqstNo", value = "결재진행no", required = false, dataType = "int", paramType = "path"), })
    @PutMapping("/constcomplete/{constNo}/{apprRqstNo}")
    public ResponseEntity<Integer> completeConstSafe(@PathVariable("constNo") String constNo, @PathVariable("apprRqstNo") int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(constService.completeConstSafe(constNo, apprRqstNo));
    }

    /**
     * 공사 진행단계 변경
     *
     * @param constNo
     *            공사번호
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    @ApiOperation(value = "공사 진행단계 완료[CSF01010]", notes = EDIT_JSON, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "constNo", value = "공사번호", required = false, dataType = "int", paramType = "path") })
    @PutMapping("/conststepcomplete/{constNo}/{constStepCd}")
    public ResponseEntity<Integer> completeConstSafeStep(@PathVariable("constNo") String constNo, @PathVariable("constStepCd") String constStepCd) throws Exception {
        return ResponseEntity.ok().body(constService.completeConstSafeStep(constNo, constStepCd));
    }

}
