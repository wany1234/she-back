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

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.she.impr.service.ImprService;
import com.she.safety.accident.service.NearmissService;
import com.she.safety.model.Nearmiss;
import com.she.utils.RequestMapper;

/**
 * 아차사고
 *
 */
@RestController
@RequestMapping("/api/saf/accident/")
public class NearmissController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private NearmissService nearmissService;

    @Autowired
    private ImprService imprService;

    /**
     * 아차사고 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고 목록
     */
    @GetMapping("getnearmisslist")
    public ResponseEntity<List<Nearmiss>> getNearmissList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 진행단계
        String processStepCd = map.containsKey("processStepCd") ? map.get("processStepCd").toString() : "";
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
        String nearmissTitle = map.containsKey("nearmissTitle") ? map.get("nearmissTitle").toString() : "";
        // 사고유형
        String occTypeCd = map.containsKey("occTypeCd") ? map.get("occTypeCd").toString() : "";
        // 사업장명
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
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

        return ResponseEntity.ok().body(nearmissService.getNearmissList(startDate, endDate, deptCd, deptSubYn, area, nearmissTitle, processStepCd, occTypeCd, plantCd, accidentType, occKindCd, occAttCd, immCauseCd, indCauseCd, defaultParam));
    }

    /**
     * 아차사고 상세 조회
     *
     * @param parameter
     *            검색조건
     * @return 사내사고
     */
    @GetMapping("getnearmiss/{safnearmissno}")
    public ResponseEntity<Nearmiss> getNearmiss(@PathVariable("safnearmissno") int safNearmissNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(nearmissService.getNearmiss(safNearmissNo, defaultParam));
    }

    /**
     * 아차사고 신규
     *
     * @param 아차사고
     * @return 아차사고번호
     * @throws Exception
     */

    @PostMapping("createnearmiss")
    public ResponseEntity<Integer> createNearmiss(@RequestBody Nearmiss nearmiss) throws Exception {
        return ResponseEntity.ok().body(nearmissService.createNearmiss(nearmiss));
    }

    /**
     * 아차사고 수정
     *
     * @param 아차사고
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("updatenearmiss")
    public ResponseEntity<Integer> updateNearmiss(@RequestBody Nearmiss nearmiss) throws Exception {
        return ResponseEntity.ok().body(nearmissService.updateNearmiss(nearmiss));
    }

    /**
     * 아차사고 완료
     *
     * @param 아차사고
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("complenearmiss")
    public ResponseEntity<Integer> compleNearmiss(@RequestBody Nearmiss nearmiss) throws Exception {
        return ResponseEntity.ok().body(nearmissService.compleNearmiss(nearmiss));
    }

    /*
     * 아차 사고 삭제
     *
     * @param safNearmissNo 아차사고번호
     *
     * @return 0
     *
     * @throws Exception
     */
    @DeleteMapping("deletenearmiss")
    public ResponseEntity<Integer> deleteNearmiss(@RequestBody String safNearmissNo) throws Exception {
        int safNearmissNoInt = Integer.parseInt(safNearmissNo);

        nearmissService.deleteNearmiss(safNearmissNoInt);
        int returnNum = 0;

        return ResponseEntity.ok().body(returnNum);
    }

}
