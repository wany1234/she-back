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

import com.she.manage.model.CodeDomain;
import com.she.manage.service.CodeDomainService;
import com.she.utils.RequestMapper;

/**
 * 코드도메인
 *
 */
@RestController
public class CodeDomainController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeDomainService codeDomainService;

    /**
     * 코드도메인 전체 조회
     * 
     * @param parameter
     *            검색조건
     * @return 코드목록
     * @throws Exception
     */
    @GetMapping("/api/manage/codedomains")
    public ResponseEntity<List<CodeDomain>> getCodeDomains(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String codeDomainNm = map.containsKey("codeDomainNm") ? map.get("codeDomainNm").toString() : "";

        List<CodeDomain> codeDomains = this.codeDomainService.getCodeDomains(codeDomainNm);
        return ResponseEntity.ok().body(codeDomains);
    }

    /**
     * 코드도메인 상세 조회
     * 
     * @param codeGroupCd
     *            코드그룹
     * @param code
     *            코드
     * @param forSystemYn
     * @return 코드마스터
     * @throws Exception
     */
    @GetMapping("/api/manage/codedomain/{codeDomainCd}")
    public ResponseEntity<CodeDomain> getCodeDomain(@PathVariable String codeDomainCd) throws Exception {
        CodeDomain codeDomain = this.codeDomainService.getCodeDomain(codeDomainCd);
        return ResponseEntity.ok().body(codeDomain);
    }

    /**
     * 코드도메인 신규 등록
     * 
     * @param codeDomain
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    @PostMapping("/api/manage/codedomain")
    public ResponseEntity<Integer> createCodeDomain(@RequestBody CodeDomain codeDomain) throws Exception {
        return ResponseEntity.ok().body(this.codeDomainService.createCodeDomain(codeDomain));
    }

    /**
     * 코드도메인 수정
     * 
     * @param codeDomain
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    @PutMapping("/api/manage/codedomain")
    public ResponseEntity<Integer> updateCodeDomain(@RequestBody CodeDomain codeDomain) throws Exception {
        return ResponseEntity.ok().body(this.codeDomainService.updateCodeDomain(codeDomain));
    }

}
