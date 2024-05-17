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
package com.she.mgt.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.she.mgt.baseInfo.service.ElectTitleService;
import com.she.mgt.model.ElectTitle;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/baseinfo")
public class ElectTitleController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ElectTitleService electTitleService;

    /**
     * 선해임명 조회
     *
     * @param parameter
     *            검색조건
     * @return 선해임명 목록
     * @throws Exception
     */
    @GetMapping("/electtitles")
    public ResponseEntity<List<ElectTitle>> getElectTitles(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String electAttCd = map.containsKey("electAttCd") ? map.get("electAttCd").toString() : "";
        String refLawCd = map.containsKey("refLawCd") ? map.get("refLawCd").toString() : "";
        String electClsCd = map.containsKey("electClsCd") ? map.get("electClsCd").toString() : "";

        String electTitlNm = map.containsKey("electTitlNm") ? map.get("electTitlNm").toString() : "";
        String evalTgtYn = map.containsKey("evalTgtYn") ? map.get("evalTgtYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(electTitleService.getElectTitles(refLawCd, electAttCd, electTitlNm, useYn, electClsCd, evalTgtYn, plantCd, defaultParam));
    }

    /**
     * 선해임명 상세 조회
     *
     * @param safElectTitlNo
     *            선해임명번호
     * @return 선해임명
     * @throws Exception
     */
    @GetMapping("/electtitle/{safElectTitlNo}")
    public ResponseEntity<ElectTitle> getElectTitle(@PathVariable(name = "safElectTitlNo") int safElectTitlNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.electTitleService.getElectTitle(safElectTitlNo, defaultParam));
    }

    /**
     * 선해임명 신규등록
     *
     * @param subconEvalItem
     *            선해임명
     * @return 선해임명 코드
     * @throws Exception
     */
    @PostMapping("/electtitle")
    public ResponseEntity<Integer> createElectTitle(@RequestBody ElectTitle electTitle) throws Exception {
        return ResponseEntity.ok().body(this.electTitleService.createElectTitle(electTitle));
    }

    /**
     * 선해임명 수정
     *
     * @param subconEvalItem
     *            선해임명
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/electtitle")
    public ResponseEntity<Integer> updateElectTitle(@RequestBody ElectTitle electTitle) throws Exception {
        return ResponseEntity.ok().body(this.electTitleService.updateElectTitle(electTitle));
    }

    /**
     * 선해임명명 체크
     *
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkelecttitle")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckElectTitle(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 선해임명명
        String electTitlNm = map.containsKey("electTitlNm") ? map.get("electTitlNm").toString() : "";
        // 선해임명번호
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? Integer.parseInt("".equals(map.get("safElectTitlNo").toString()) ? "0" : map.get("safElectTitlNo").toString()) : 0;

        return ResponseEntity.ok().body(electTitleService.getCheckElectTitle(electTitlNm, safElectTitlNo));
    }

}
