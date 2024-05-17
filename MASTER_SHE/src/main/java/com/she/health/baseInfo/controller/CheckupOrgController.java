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
package com.she.health.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.health.baseInfo.service.CheckupOrgService;
import com.she.health.model.CheckupOrg;
import com.she.utils.RequestMapper;

/**
 * 건강검진기관
 *
 */
@RestController
public class CheckupOrgController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CheckupOrgService checkupOrgService;

    /**
     * 건강검진기관 조회
     *
     * @param parameter
     *            검색조건
     * @return 건강검진기관 목록
     */
    @GetMapping("/api/hea/baseinfo/checkuporgs")
    public ResponseEntity<List<CheckupOrg>> getCheckupOrgs(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사용유무
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 검진기관명
        String heaCheckupOrgNm = map.containsKey("heaCheckupOrgNm") ? map.get("heaCheckupOrgNm").toString() : "";

        List<CheckupOrg> checkupOrgs = checkupOrgService.getCheckupOrgs(useYn, heaCheckupOrgNm);
        return ResponseEntity.ok().body(checkupOrgs);
    }

    /**
     * 건강검진기관 상세 조회
     *
     * @param heaCheckupOrgNo
     * @return 건강검진기관
     * @throws Exception
     */
    @GetMapping("/api/hea/baseinfo/checkuporg/{heaCheckupOrgNo}")
    public ResponseEntity<CheckupOrg> getCheckupOrgs(@PathVariable("heaCheckupOrgNo") String heaCheckupOrgNo)
            throws Exception {
        CheckupOrg checkupOrg = checkupOrgService.getCheckupOrg(heaCheckupOrgNo);
        return ResponseEntity.ok().body(checkupOrg);
    }

    /**
     * 건강검진기관 생성
     *
     * @param checkOrg
     *            건강검진기관
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/api/hea/baseinfo/checkuporg")
    public ResponseEntity<Integer> createCheckupOrg(@RequestBody CheckupOrg checkupOrg) throws Exception {
        return ResponseEntity.ok()
                .body(checkupOrgService.createCheckupOrg(checkupOrg) > 0 ? checkupOrg.getHeaCheckupOrgNo() : 0);
    }

    /**
     * 건강검진기관 수정
     *
     * @param checkOrg
     *            건강검진기관
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/api/hea/baseinfo/checkuporg")
    public ResponseEntity<Integer> updateCheckupOrg(@RequestBody CheckupOrg checkupOrg) throws Exception {
        return ResponseEntity.ok().body(checkupOrgService.updateCheckupOrg(checkupOrg));
    }

}
