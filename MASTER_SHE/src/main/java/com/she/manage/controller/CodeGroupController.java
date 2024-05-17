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
package com.she.manage.controller;

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

import com.she.manage.model.CodeGroup;
import com.she.manage.service.CodeGroupService;
import com.she.utils.RequestMapper;

/**
 * 공용코드그룹
 *
 */
@RestController
public class CodeGroupController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeGroupService codeGroupService;

    /**
     * 공용코드그룹 전체 조회
     * 
     * @param codeDomainCd
     *            코드도메인
     * @param codeGroupCd
     *            코드그룹명
     * @param forSystemYn
     * @return 코드그룹목록
     * @throws Exception
     */
    @GetMapping("/api/manage/codegroups")
    public ResponseEntity<List<CodeGroup>> getCodeGroups(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String codeDomainCd = map.containsKey("codeDomainCd") ? map.get("codeDomainCd").toString() : "";
        String codeGroupNm = map.containsKey("codeGroupNm") ? map.get("codeGroupNm").toString() : "";
        String forSystemYn = map.containsKey("forSystemYn") ? map.get("forSystemYn").toString() : "";
        String settableYn = map.containsKey("settableYn") ? map.get("settableYn").toString() : "";

        List<CodeGroup> codeGroups = this.codeGroupService.getCodeGroups(codeDomainCd, codeGroupNm, forSystemYn, settableYn);
        return ResponseEntity.ok().body(codeGroups);
    }

    /**
     * 공용코드그룹 상세 조회
     * 
     * @param codeGroupCd
     *            코드그룹
     * @return 코드그룹
     * @throws Exception
     */
    @GetMapping("/api/manage/codegroup/{codeGroupCd}")
    public ResponseEntity<CodeGroup> getCodeGroup(@PathVariable String codeGroupCd) throws Exception {
        CodeGroup codeGroup = this.codeGroupService.getCodeGroup(codeGroupCd);
        return ResponseEntity.ok().body(codeGroup);
    }

    /**
     * 코드그룹 신규 등록
     * 
     * @param codeGroup
     *            코드그룹
     * @return 생성행수
     * @throws Exception
     */
    @PostMapping("/api/manage/codegroup")
    public ResponseEntity<Integer> createCodeGroup(@RequestBody CodeGroup codeGroup) throws Exception {
        return ResponseEntity.ok().body(this.codeGroupService.createCodeGroup(codeGroup));
    }

    /**
     * 코드그룹 신규 등록
     * 
     * @param codeGroup
     *            코드그룹
     * @return 생성행수
     * @throws Exception
     */
    @PutMapping("/api/manage/codegroup")
    public ResponseEntity<Integer> updateCodeGroup(@RequestBody CodeGroup codeGroup) throws Exception {
        return ResponseEntity.ok().body(this.codeGroupService.updateCodeGroup(codeGroup));
    }
}
