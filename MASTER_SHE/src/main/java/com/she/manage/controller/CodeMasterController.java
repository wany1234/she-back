package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.utils.RequestMapper;

/**
 * 코드마스터
 *
 */
@RestController
public class CodeMasterController {

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeMasterService codeMasterService;

    private static final Logger logger = LoggerFactory.getLogger(CodeMasterController.class);

    /**
     * 선택박스에서 사용되는 코드목록 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @return 코드목록
     * @throws Exception
     */
    @GetMapping("/api/manage/codemaster/getselect/{codeGroupCd}")
    public ResponseEntity<List<CodeMaster>> getSelect(@PathVariable String codeGroupCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        /**
         * 2020-10-12 kdh
         *
         * 해당 api로 오는 경우는 frontend에서 select, check, radio, multi에서 item을 setting
         * 하는 경우
         *
         * 위의 경우에 사용여부 상관 없이 전체 조회하도록 조치
         *
         * frontend에서 활성화 여부를 따져 컴포넌트에서 필터링을 거치게 처리
         *
         * 그 외에 해당 service를 직접 호출하는 경우에는 사용여부를 컨트롤 할 수 있도록 조치
         *
         * case 정리
         *
         * - 비활성화 : 무조건 값이 찍히는 상황임
         *
         * - 활성화 : 1) N처리된 item이 있는 경우 : 사용자가 다시 입력
         *
         * ________2) N처리된 item이 없는 경우 : 기존 그대로 사용
         */
        return ResponseEntity.ok().body(this.codeMasterService.getSelect(codeGroupCd, null, defaultParam));
    }

    /**
     * 선택박스에서 사용되는 코드목록 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @return 코드목록
     * @throws Exception
     */
    @GetMapping("/api/manage/codemaster/getselectattr/{codeGroupCd}/{attr1}/{attr2}/{attr3}")
    public ResponseEntity<List<CodeMaster>> getSelectAttr(@PathVariable(name = "codeGroupCd") String codeGroupCd, @PathVariable(name = "attr1") String attr1, @PathVariable(name = "attr2") String attr2, @PathVariable(name = "attr3") String attr3, @ModelAttribute DefaultParam defaultParam) throws Exception {
        if ("null".equals(attr1)) {
            attr1 = null;
        }
        if ("null".equals(attr2)) {
            attr2 = null;
        }
        if ("null".equals(attr3)) {
            attr3 = null;
        }

        List<CodeMaster> codeMasters = this.codeMasterService.getSelectAttr(codeGroupCd, attr1, attr2, attr3, defaultParam);
        return ResponseEntity.ok().body(codeMasters);
    }

    /**
     * 공용코드 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 코드목록
     * @throws Exception
     */
    @GetMapping("/api/manage/codemasters")
    public ResponseEntity<List<CodeMaster>> getCodeMasters(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String settableYn = map.containsKey("settableYn") ? map.get("settableYn").toString() : "";
        String codeDomainCd = map.containsKey("codeDomainCd") ? map.get("codeDomainCd").toString() : "";
        String codeGroupCd = map.containsKey("codeGroupCd") ? map.get("codeGroupCd").toString() : "";
        String codeNm = map.containsKey("codeNm") ? map.get("codeNm").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.codeMasterService.getCodeMasters(settableYn, codeDomainCd, codeGroupCd, codeNm, useYn));
    }

    /**
     * 공용코드 상세 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @param code
     *            코드
     * @param forSystemYn
     * @return 코드마스터
     * @throws Exception
     */
    @GetMapping("/api/manage/codemaster/codemaster/{codeGroupCd}/{code}")
    public ResponseEntity<CodeMaster> getCodeMaster(@PathVariable String codeGroupCd, @PathVariable String code) throws Exception {
        CodeMaster codeMaster = this.codeMasterService.getCodeMaster(codeGroupCd, code, "");
        return ResponseEntity.ok().body(codeMaster);
    }

    /**
     * 공용코드 신규 등록
     *
     * @param codeMaster
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    @PostMapping("/api/manage/codemaster")
    public ResponseEntity<Integer> createCodeMaster(@RequestBody CodeMaster codeMaster) throws Exception {
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
    @PutMapping("/api/manage/codemaster")
    public ResponseEntity<Integer> updateCodeMaster(@RequestBody CodeMaster codeMaster) throws Exception {
        return ResponseEntity.ok().body(this.codeMasterService.updateCodeMaster(codeMaster));
    }

    /**
     * 공용코드 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 코드목록
     * @throws Exception
     */
    @GetMapping("/api/manage/codemaster/codeallmasters")
    public ResponseEntity<List<CodeMaster>> getCodeAllMasters(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String forSystemYn = map.containsKey("forSystemYn") ? map.get("forSystemYn").toString() : "";
        String settableYn = map.containsKey("settableYn") ? map.get("settableYn").toString() : "";
        String codeDomainCd = map.containsKey("codeDomainCd") ? map.get("codeDomainCd").toString() : "";
        String codeGroupCd = map.containsKey("codeGroupCd") ? map.get("codeGroupCd").toString() : "";
        String codeNm = map.containsKey("codeNm") ? map.get("codeNm").toString() : "";
        String attr1 = map.containsKey("attr1") ? map.get("attr1").toString() : "";
        String attr2 = map.containsKey("attr2") ? map.get("attr2").toString() : "";
        String attr3 = map.containsKey("attr3") ? map.get("attr3").toString() : "";
        String codeGroupNm = map.containsKey("codeGroupNm") ? map.get("codeGroupNm").toString() : "";

        return ResponseEntity.ok().body(this.codeMasterService.getCodeAllMasters(forSystemYn, settableYn, codeDomainCd, codeGroupCd, codeNm, attr1, attr2, attr3, codeGroupNm));
    }

    @GetMapping("/api/manage/codemaster/duplicationCheck/{mgtMgInfoItmNo}")
    public ResponseEntity<Integer> getDuplicationCheck(@PathVariable String mgtMgInfoItmNo) throws Exception {
        return ResponseEntity.ok().body(this.codeMasterService.getDuplicationCheck(mgtMgInfoItmNo));
    }
}
