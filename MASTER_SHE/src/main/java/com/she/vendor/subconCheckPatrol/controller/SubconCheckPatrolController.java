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
package com.she.vendor.subconCheckPatrol.controller;

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
import com.she.safety.model.CheckItem;
import com.she.utils.RequestMapper;
import com.she.vendor.model.SubconCheckInspector;
import com.she.vendor.model.SubconCheckItemResult;
import com.she.vendor.model.SubconCheckResult;
import com.she.vendor.subconCheckPatrol.service.SubconCheckPatrolService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/vendor/subconcheckpatrol")
public class SubconCheckPatrolController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SubconCheckPatrolService subconCheckPatrolService;

    /**
     * 협력업체자체점검 조회
     *
     * @param parameter
     * @return 협력업체자체점검 목록
     * @throws Exception
     */
    @ApiOperation(value = "협력업체자체점검 목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "checkKindCd", value = "점검종류코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "searchPeriod", value = "기간", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "checkStepCd", value = "진행상태", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "vendorCd", value = "업체코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "vendorNm", value = "업체명", required = false, dataType = "string", paramType = "query"), })
    @GetMapping("/subconcheckpatrols")
    public ResponseEntity<List<SubconCheckResult>> getSubconCheckResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 점검종류코드
        String checkKindCd = map.containsKey("checkKindCd") ? map.get("checkKindCd").toString() : "";
        // 기간
        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        String fromDt = "";
        String toDt = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromDt = searchPeriod[0];
            toDt = searchPeriod[1];
        }
        // 진행상태
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        // 업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 업체명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        // 점검명
        String checkTitle = map.containsKey("checkTitle") ? map.get("checkTitle").toString() : "";
        return ResponseEntity.ok().body(subconCheckPatrolService.getSubconCheckResults(plantCd, checkKindCd, fromDt, toDt, checkStepCd, vendorCd, vendorNm ,checkTitle));
    }

    /**
     * 협력업체자체점검종류
     *
     * @param plantCd
     *            사업장코드
     * @return 협력업체자체점검종류
     * @throws Exception
     */
    @ApiOperation(value = "협력업체자체점검종류", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = true, dataType = "String", paramType = "query"), })
    @GetMapping("/subconcheckkinds")
    public ResponseEntity<List<CodeMaster>> getSubconCheckKinds() throws Exception {
        return ResponseEntity.ok().body(this.subconCheckPatrolService.getSubconCheckKinds());
    }

    /**
     * 점검항목 목록 조회
     * 
     * @param safCheckKindNo
     * @return
     * @throws Exception
     */
    @GetMapping("/checkitemlist")
    public ResponseEntity<List<CheckItem>> getCheckItemList(@RequestParam("safCheckKindNo") String checkKindCd) throws Exception {
        return ResponseEntity.ok().body(subconCheckPatrolService.getCheckItemList(checkKindCd));
    }

    /**
     * 협력업체자체점검 결과 상세 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가
     * @throws Exception
     */
    @ApiOperation(value = "협력업체자체점검 결과 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "vendorCheckRsltNo", value = "협력업체자체점검결과No", required = true, dataType = "int", paramType = "query"), })
    @GetMapping("/subconcheckpatrol/{vendorCheckRsltNo}")
    public ResponseEntity<SubconCheckResult> getSubconCheckResult(@PathVariable(name = "vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception {
        return ResponseEntity.ok().body(this.subconCheckPatrolService.getSubconCheckResult(vendorCheckRsltNo));
    }

    /**
     * 협력업체자체점검 결과 항목 리스트 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가 결과 항목 리스트
     * @throws Exception
     */
    @ApiOperation(value = "협력업체자체점검 결과 항목 리스트 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safCheckKindNo", value = "협력업체자체점검결과No", required = true, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "checkStepCd", value = "진행단계", required = true, dataType = "int", paramType = "query") })
    @GetMapping("/subconcheckpatrolitemresult/{safCheckKindNo}")
    public ResponseEntity<List<SubconCheckItemResult>> getSubconCheckItemResult(@PathVariable int safCheckKindNo, @RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int vendorCheckRsltNo = map.containsKey("vendorCheckRsltNo") ? Integer.parseInt("".equals(map.get("vendorCheckRsltNo").toString()) ? "0" : map.get("vendorCheckRsltNo").toString()) : 0;
        return ResponseEntity.ok().body(this.subconCheckPatrolService.getSubconCheckItemResult(safCheckKindNo, vendorCheckRsltNo));
    }

    /**
     * 협력업체자체점검 결과 외부 점검자 리스트 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체평가 결과 외부 점검자 리스트
     * @throws Exception
     */
    @ApiOperation(value = "협력업체자체점검 결과 외부 점검자 리스트 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "vendorCheckRsltNo", value = "협력업체자체점검결과No", required = true, dataType = "int", paramType = "query"), })
    @GetMapping("/subconcheckpatrolinspector/{vendorCheckRsltNo}")
    public ResponseEntity<List<SubconCheckInspector>> getSubconCheckInspector(@PathVariable(name = "vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception {
        return ResponseEntity.ok().body(this.subconCheckPatrolService.getSubconCheckInspector(vendorCheckRsltNo));
    }

    /**
     * 업체자체점검결과 등록
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    @PostMapping("/subconcheckresult")
    public ResponseEntity<Integer> createCheckResult(@RequestBody SubconCheckResult subconCheckResult) throws Exception {
        return ResponseEntity.ok().body(subconCheckPatrolService.createCheckResult(subconCheckResult));
    }

    /**
     * 업체자체점검결과 수정
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    @PutMapping("/subconcheckresult")
    public ResponseEntity<Integer> updateCheckResult(@RequestBody SubconCheckResult subconCheckResult) throws Exception {
        return ResponseEntity.ok().body(subconCheckPatrolService.updateCheckResult(subconCheckResult));
    }

    /**
     * 업체자체점검결과 완료
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    @PutMapping("/subconcheckresultcomplete")
    public ResponseEntity<Integer> completeCheckResult(@RequestBody SubconCheckResult subconCheckResult) throws Exception {
        return ResponseEntity.ok().body(subconCheckPatrolService.completeCheckResult(subconCheckResult));
    }

    /**
     * vendorCheckRsltNo
     * 
     * @throws Exception
     */
    @DeleteMapping("/subconcheckresult/{vendorCheckRsltNo}")
    public ResponseEntity<Integer> deleteCheckResult(@PathVariable("vendorCheckRsltNo") int vendorCheckRsltNo) throws Exception {
        return ResponseEntity.ok().body(subconCheckPatrolService.deleteCheckResult(vendorCheckRsltNo));
    }
}
