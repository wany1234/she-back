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
package com.she.env.waste.baseInfo.controller;

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
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeGroupService;
import com.she.manage.service.CodeMasterService;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

/**
 * 환경-폐기물 공용코드
 *
 */
@RestController
public class EwasteCodeMasterController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeGroupService codeGroupService;

    @Autowired
    private CodeMasterService codeMasterService;

    /**
     * 공용코드그룹 전체 조회
     * 
     * @return 코드그룹목록
     * @throws Exception
     */
    @GetMapping("/api/env/waste/baseinfo/codegroups")
    public ResponseEntity<List<CodeGroup>> getCodeGroups() throws Exception {
        String codeDomainCd = ConstVal.CODE_DOMAIN_CD_ENV_WASTE;
        String codeGroupNm = "";

        List<CodeGroup> codeGroups = this.codeGroupService.getCodeGroups(codeDomainCd, codeGroupNm, "N", "Y");
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
    @GetMapping("/api/env/waste/baseinfo/codegroup/{codeGroupCd}")
    public ResponseEntity<CodeGroup> getCodeMaster(@PathVariable String codeGroupCd) throws Exception {
        CodeGroup codeGroup = this.codeGroupService.getCodeGroup(codeGroupCd);
        return ResponseEntity.ok().body(codeGroup);
    }

    /**
     * 공용코드 상세 조회
     * 
     * @param codeGroupCd
     *            코드그룹
     * @param code
     *            코드
     * @return 코드마스터
     * @throws Exception
     */
    @GetMapping("/api/env/waste/baseinfo/codemaster/{codeGroupCd}/{code}")
    public ResponseEntity<CodeMaster> getCodeMaster(@PathVariable String codeGroupCd, @PathVariable String code) throws Exception {
        CodeMaster codeMaster = this.codeMasterService.getCodeMaster(codeGroupCd, code, "N");
        return ResponseEntity.ok().body(codeMaster);
    }

    /**
     * 공용코드 전체 조회
     * 
     * @param parameter
     *            검색조건
     * @return 코드목록
     * @throws Exception
     */
    @GetMapping("/api/env/waste/baseinfo/codemasters")
    public ResponseEntity<List<CodeMaster>> getCodeMasters(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String settableYn = "Y";
        String codeDomainCd = ConstVal.CODE_DOMAIN_CD_ENV_WASTE;
        String codeGroupCd = map.containsKey("codeGroupCd") ? map.get("codeGroupCd").toString() : "";
        String codeNm = map.containsKey("codeNm") ? map.get("codeNm").toString() : "";

        List<CodeMaster> codeMasters = this.codeMasterService.getCodeMasters(settableYn, codeDomainCd, codeGroupCd, codeNm, "Y");
        return ResponseEntity.ok().body(codeMasters);
    }

    /**
     * 공용코드 신규 등록
     * 
     * @param codeMaster
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    @PostMapping("/api/env/waste/baseinfo/codemaster")
    public ResponseEntity<Integer> createCodeMaster(@RequestBody CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeDomainCd(ConstVal.CODE_DOMAIN_CD_ENV_WASTE);
        return ResponseEntity.ok().body(this.codeMasterService.createCodeMaster(codeMaster));
    }

    /**
     * 공용코드 신규 등록
     * 
     * @param codeMaster
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    @PutMapping("/api/env/waste/baseinfo/codemaster")
    public ResponseEntity<Integer> updateCodeMaster(@RequestBody CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeDomainCd(ConstVal.CODE_DOMAIN_CD_ENV_WASTE);
        return ResponseEntity.ok().body(this.codeMasterService.updateCodeMaster(codeMaster));
    }
}
