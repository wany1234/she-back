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
package com.she.mgt.mgtLaw.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.mgt.mgtLaw.service.SheLawMakingCheckService;
import com.she.mgt.model.MgtLawMakingCheck;
import com.she.mgt.model.MgtLawMakingCheckDept;
import com.she.mgt.model.MgtLawMakingChecker;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//
@RestController
@RequestMapping("api/mgt/mgtlaw")
public class SheLawMakingCheckController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SheLawMakingCheckService sheLawMakingCheckService;

    /***
     * 입법예고법규 검토 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 입법예고 목록
     * @throws Exception
     */
    @GetMapping("/lawmakingchecks")
    public ResponseEntity<List<MgtLawMakingCheck>> getLawMakingChecks(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String stYd = "";
        String edYd = "";
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        if (period != null && period.length == 2) {
            stYd = period[0];
            edYd = period[1];
        }

        String isNm = map.containsKey("isNm") ? map.get("isNm").toString() : "";
        // like 검색 시 대괄호([])가 와일드카드 문자로 인식되어 제대로 검색이 안됨.
        // '[' -> '[[]' 으로 치환하여 검색이 이루어지도록 함.
        isNm = isNm.replace("[", "[[]").trim();

        String createUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";
        String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        String bizApprStepCd = map.containsKey("bizApprStepCd") ? map.get("bizApprStepCd").toString() : "";

        return ResponseEntity.ok().body(sheLawMakingCheckService.getLawMakingChecks(isNm, stYd, edYd, createUserId, checkStepCd, deptCd, deptSubYn, bizApprStepCd, defaultParam));
    }

    /**
     * 입법예고법규 검토 상세조회
     *
     * @param comFacilityMstCd
     *            입법예고법규 검토코드
     * @return 입법예고법규 검토
     */
    @ApiOperation(value = "입법예고법규 검토 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("lawmakingcheck")
    public ResponseEntity<MgtLawMakingCheck> getLawMakingCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int lmcSeqNo = map.containsKey("lmcSeqNo") ? Integer.parseInt("".equals(map.get("lmcSeqNo").toString()) ? "0" : map.get("lmcSeqNo").toString()) : 0;
        int seqNo = map.containsKey("seqNo") ? Integer.parseInt("".equals(map.get("seqNo").toString()) ? "0" : map.get("seqNo").toString()) : 0;
        String createUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";

        return ResponseEntity.ok().body(sheLawMakingCheckService.getLawMakingCheck(lmcSeqNo, seqNo, createUserId));
    }

    /**
     * 입법예고법규 검토 등록
     *
     * @param lawMakingCheck
     *            입법예고법규 검토
     * @return 키
     */
    @ApiOperation(value = "입법예고법규 검토 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "LawMakingCheck", value = "입법예고법규 검토", required = false, dataType = "MgtLawMakingCheck", paramType = "body") })
    @PostMapping("lawmakingcheck")
    public ResponseEntity<Integer> createLawMakingCheck(@RequestBody MgtLawMakingCheck lawMakingCheck) throws Exception {
        return ResponseEntity.ok().body(sheLawMakingCheckService.createLawMakingCheck(lawMakingCheck) > 0 ? lawMakingCheck.getLmcSeqNo() : 0);
    }

    /**
     * 입법예고법규 검토 수정
     *
     * @param lawMakingCheck
     *            입법예고법규 검토
     * @return 수정 행
     */

    @ApiOperation(value = "입법예고법규 검토 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "LawMakingCheck", value = "입법예고법규 검토", required = false, dataType = "MgtLawMakingCheck", paramType = "body") })
    @PutMapping("lawmakingcheck")
    public ResponseEntity<Integer> updateLawMakingCheck(@RequestBody MgtLawMakingCheck lawMakingCheck) throws Exception {
        return ResponseEntity.ok().body(sheLawMakingCheckService.updateLawMakingCheck(lawMakingCheck));
    }

    /**
     * 입법예고법규 검토부서 사용자 저장
     *
     * @param mgtLawMakingCheckDept
     *            입법예고법규 검토부서
     * @return 수정 행
     */

    @ApiOperation(value = "입법예고법규 검토부서 사용자 저장", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtLawMakingCheckDept", value = "입법예고법규 검토부서", required = false, dataType = "mgtLawMakingCheckDept", paramType = "body") })
    @PostMapping("lawmakingcheck/user")
    public ResponseEntity<Integer> createLawMakingCheckUser(@RequestBody MgtLawMakingCheckDept mgtLawMakingCheckDept) throws Exception {
        return ResponseEntity.ok().body(sheLawMakingCheckService.createLawMakingCheckUser(mgtLawMakingCheckDept));
    }

    /**
     * 입법예고법규 검토부서 사용자 지정 완료
     *
     * @param mgtLawMakingCheckDept
     *            입법예고법규 검토부서
     * @return 수정 행
     */

    @ApiOperation(value = "입법예고법규 검토부서 사용자 지정 완료", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtLawMakingCheckDept", value = "입법예고법규 검토부서", required = false, dataType = "mgtLawMakingCheckDept", paramType = "body") })
    @PutMapping("lawmakingcheck/confirm/user")
    public ResponseEntity<Integer> updateLawMakingCheckConfirmUser(@RequestBody MgtLawMakingCheckDept mgtLawMakingCheckDept) throws Exception {
        return ResponseEntity.ok().body(sheLawMakingCheckService.updateLawMakingCheckConfirmUser(mgtLawMakingCheckDept));
    }

    /**
     * 입법예고법규 검토자 의견 저장
     *
     * @param mgtLawMakingChecker
     *            입법예고법규 검토자
     * @return 수정 행
     */

    @ApiOperation(value = "입법예고법규 검토부서 사용자 저장", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtLawMakingChecker", value = "입법예고법규 검토자", required = false, dataType = "mgtLawMakingChecker", paramType = "body") })
    @PutMapping("lawmakingcheck/opinion")
    public ResponseEntity<Integer> updateLawMakingCheckOpinion(@RequestBody MgtLawMakingChecker mgtLawMakingChecker) throws Exception {
        return ResponseEntity.ok().body(sheLawMakingCheckService.updateLawMakingCheckOpinion(mgtLawMakingChecker));
    }

    /**
     * 입법예고법규 검토자 완료
     *
     * @param mgtLawMakingChecker
     *            입법예고법규 검토자
     * @return 수정 행
     */

    @ApiOperation(value = "입법예고법규 검토부서 사용자 지정 완료", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtLawMakingChecker", value = "입법예고법규 검토자", required = false, dataType = "mgtLawMakingChecker", paramType = "body") })
    @PutMapping("lawmakingcheck/confirm/opinion")
    public ResponseEntity<Integer> updateLawMakingCheckConfirmOpinion(@RequestBody MgtLawMakingChecker mgtLawMakingChecker) throws Exception {
        return ResponseEntity.ok().body(sheLawMakingCheckService.updateLawMakingCheckConfirmOpinion(mgtLawMakingChecker));
    }
}
