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
package com.she.mgt.mgtTarget.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.mgt.mgtTarget.mapper.MgtTargetMapper;
import com.she.mgt.mgtTarget.service.MgtTargetService;
import com.she.mgt.model.MgtTgt;
import com.she.mgt.model.MgtTgtItemEvalRslt;
import com.she.mgt.model.MgtTgtItemPlanRslt;
import com.she.mgt.model.MgtTgtStatus;
import com.she.mgt.model.MgtTgtStatusGraph;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/mgt/mgttarget")
public class MgtTargetController {
    @Autowired
    private MgtTargetService mgtTargetService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private MgtTargetMapper mgtTargetMapper;

    /**
     * 목표/실적/평가 관리 목록
     * 
     * @param from
     *            대상연월 시작 to 대상연월 종료 plantCd 사업장코드 processCd 공정코드 bizFieldCd 분야코드
     *            bizFieldItemNm 항목명 unregistered 미등록건 areaType 전사/사업장/부서 구분
     * @return MgtTgtItemEvalRslt 목표/실적/평가 목폭
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgttargets")
    public ResponseEntity<List<MgtTgtItemEvalRslt>> getMgtTargets(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String from = map.containsKey("from") ? map.get("from").toString() : "";
        String to = map.containsKey("to") ? map.get("to").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String bizFieldCd = map.containsKey("bizFieldCd") ? map.get("bizFieldCd").toString() : "";
        String bizFieldItemNm = map.containsKey("bizFieldItemNm") ? map.get("bizFieldItemNm").toString() : "";
        String unregistered = map.containsKey("unregistered") ? map.get("unregistered").toString() : "";
        String areaType = map.containsKey("areaType") ? map.get("areaType").toString() : "";
        String plantRoleYn = map.containsKey("plantEditable") ? map.get("plantEditable").toString() == "true" ? "Y" : "N" : "";
        String deptRoleYn = map.containsKey("deptEditable") ? map.get("deptEditable").toString() == "true" ? "Y" : "N" : "";
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        return ResponseEntity.ok().body(mgtTargetService.getMgtTargets(from, to, plantCd, bizFieldCd, bizFieldItemNm, unregistered, areaType, plantRoleYn, deptRoleYn, userId, processCd, defaultParam));
    }

    /**
     * 목표 계획 상세
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return MgtTgt 목표 상세정보
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgttarget/{mgtTargetGrpNo}")
    public ResponseEntity<MgtTgt> getMgtTarget(@PathVariable(name = "mgtTargetGrpNo") String mgtTargetGrpNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        MgtTgt mgtTgt = mgtTargetService.getMgtTarget(mgtTargetGrpNo, defaultParam);
        return ResponseEntity.ok().body(mgtTgt);
    }

    /**
     * 목표 계획 목록
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return MgtTgtItemPlanRslt 목표 계획 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgttargetitems")
    public ResponseEntity<List<MgtTgtItemPlanRslt>> getMgtTargetItems(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int mgtTargetGrpNo = map.containsKey("mgtTargetGrpNo") ? Integer.parseInt(map.get("mgtTargetGrpNo").toString()) : 0;

        return ResponseEntity.ok().body(mgtTargetService.getMgtTargetItems(mgtTargetGrpNo, defaultParam));
    }

    /**
     * 목표 중복 체크
     * 
     * @param year
     *            대상연도 plantCd 사업장코드 processCd 공정코드
     * @return HashMap 중복데이터 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/checkmgttarget")
    public ResponseEntity<List<HashMap<String, Object>>> checkMgtTarget(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);

        String year = convertedParameter.containsKey("year") ? convertedParameter.get("year").toString() : ""; // 목표년도
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : ""; // 사업장코드
        String processCd = convertedParameter.containsKey("processCd") ? convertedParameter.get("processCd").toString() : ""; // 부서코드
        return ResponseEntity.ok().body(mgtTargetService.checkMgtTarget(year, plantCd, processCd, defaultParam));
    }

    /**
     * 목표상세, 목표계획 데이터 등록/수정
     * 
     * @param MgtTgt
     *            목표데이터 모델
     * @return Integer 목표그룹번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/mgttarget")
    public ResponseEntity<Integer> createMgtTargetData(@RequestBody MgtTgt mgtTgt) throws Exception {
        return ResponseEntity.ok().body(mgtTargetService.createMgtTargetData(mgtTgt));
    }

    /**
     * 실적/평가 데이터 등록/수정
     * 
     * @param MgtTgt
     *            목표데이터 모델
     * @return Integer 목표월번호
     * @throws Exception
     */
    @PostMapping("/mgttargetrslt")
    public ResponseEntity<Integer> createMgtTargetRsltData(@RequestBody MgtTgt mgtTgt) throws Exception {
        return ResponseEntity.ok().body(mgtTargetService.createMgtTargetRsltData(mgtTgt));
    }

    /**
     * 목표데이터 삭제
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return Integer 0
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/mgttarget/{mgtTargetGrpNo}")
    public ResponseEntity<Integer> deleteMgtTargetData(@PathVariable("mgtTargetGrpNo") String mgtTargetGrpNo) throws Exception {
        return ResponseEntity.ok().body(mgtTargetService.deleteMgtTargetData(mgtTargetGrpNo));
    }

    /**
     * 실적/평가 데이터 삭제
     * 
     * @param mgtTargetMonthNo
     *            목표월번호 mgtTargetType 실적/평가 구분
     * @return Integer 0
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/mgttarget/{mgtTargetMonthNo}/{mgtTargetType}")
    public ResponseEntity<Integer> deleteMgtTargetMonthData(@PathVariable("mgtTargetMonthNo") String mgtTargetMonthNo, @PathVariable("mgtTargetType") String mgtTargetType) throws Exception {
        return ResponseEntity.ok().body(mgtTargetService.deleteMgtTargetMonthData(mgtTargetMonthNo, mgtTargetType));
    }

    /**
     * 실적/평가 상세 데이터
     * 
     * @param mgtTargetMonthNo
     *            목표월번호
     * @return MgtTgt 목표데이터 모델
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgttargetrslt/{mgtTargetMonthNo}")
    public ResponseEntity<MgtTgt> getMgtTargetRslt(@PathVariable(name = "mgtTargetMonthNo") String mgtTargetMonthNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        MgtTgt mgtTgt = mgtTargetService.getMgtTargetRslt(mgtTargetMonthNo, defaultParam);
        return ResponseEntity.ok().body(mgtTgt);
    }

    /**
     * 실적/평가 목록
     * 
     * @param mgtTargetMonthNo
     *            목표월번호
     * @return MgtTgtItemEvalRslt 실적/평가 목록 모델
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgttargetrslts")
    public ResponseEntity<List<MgtTgtItemEvalRslt>> getMgtTargetRslts(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int mgtTargetMonthNo = map.containsKey("mgtTargetMonthNo") ? Integer.parseInt(map.get("mgtTargetMonthNo").toString()) : 0;

        return ResponseEntity.ok().body(mgtTargetService.getMgtTargetRslts(mgtTargetMonthNo, defaultParam));
    }

    /**
     * SHE목표달성 현황 목록
     * 
     * @param from
     *            대상연월 시작 to 대상연월 종료 plantCd 사업장코드 processCd 공정코드 bizFieldCd 분야코드
     *            bizFieldItemNm 항목명 areaType 전사/사업장/부서 구분
     * @return MgtTgtStatus SHE목표달성 현황 목폭
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgttargetstatus")
    public ResponseEntity<List<MgtTgtStatus>> getMgtTargetStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String from = map.containsKey("from") ? map.get("from").toString() : "";
        String to = map.containsKey("to") ? map.get("to").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String bizFieldCd = map.containsKey("bizFieldCd") ? map.get("bizFieldCd").toString() : "";
        String bizFieldItemNm = map.containsKey("bizFieldItemNm") ? map.get("bizFieldItemNm").toString() : "";
        String areaType = map.containsKey("areaType") ? map.get("areaType").toString() : "";

        return ResponseEntity.ok().body(mgtTargetService.getMgtTargetStatus(from, to, plantCd, processCd, bizFieldCd, bizFieldItemNm, areaType, defaultParam));
    }
    
    @GetMapping("/mgttargetstatusgraph")
    public ResponseEntity<List<MgtTgtStatusGraph>> getMgtTargetStatusGraph(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String from = map.containsKey("from") ? map.get("from").toString() : "";
        String to = map.containsKey("to") ? map.get("to").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String bizFieldCd = map.containsKey("bizFieldCd") ? map.get("bizFieldCd").toString() : "";
        String bizFieldItemNm = map.containsKey("bizFieldItemNm") ? map.get("bizFieldItemNm").toString() : "";

        return ResponseEntity.ok().body(mgtTargetService.getMgtTargetStatusGraph(from, to, plantCd, processCd, bizFieldCd, bizFieldItemNm, defaultParam));
    }
}
