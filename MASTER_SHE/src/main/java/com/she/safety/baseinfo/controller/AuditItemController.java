package com.she.safety.baseinfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.she.safety.baseinfo.service.AuditItemService;
import com.she.safety.model.AuditItem;
import com.she.safety.model.AuditStd;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * PSM 감사기준
 */
@RestController
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "PSM 감사기준 서비스")
public class AuditItemController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AuditItemService auditItemService;

    /**
     * PSM 감사기준 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 감사기준 목록
     */
    @ApiOperation(value = "PSM 감사기준 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query") })
    @GetMapping("audititems")
    public ResponseEntity<List<AuditStd>> getAuditStds(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        return ResponseEntity.ok().body(auditItemService.getAuditStds(map, defaultParam));
    }

    /**
     * PSM 감사기준 등록
     * 
     * @param auditStd
     *            PSM 감사기준
     * @return 키
     */
    @ApiOperation(value = "PSM 감사기준 등록[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditStd", value = "PSM 감사기준", required = false, dataType = "AuditStd", paramType = "body") })
    @PostMapping("audititem")
    public ResponseEntity<Integer> createAuditStd(@RequestBody AuditStd auditStd) throws Exception {
        return ResponseEntity.ok().body(auditItemService.createAuditStd(auditStd));
    }

    /**
     * PSM 감사기준 상세 조회
     * 
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return PSM 감사기준 상세
     */
    @ApiOperation(value = "PSM 감사기준 상세조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditStdNo", value = "PSM 감사기준 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("audititem/{auditStdNo}")
    public ResponseEntity<AuditStd> getAuditStd(@PathVariable("auditStdNo") int auditStdNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(auditItemService.getAuditStd(auditStdNo, defaultParam));
    }

    /**
     * PSM 감사기준 수정
     * 
     * @param auditStd
     *            PSM 감사기준
     * @return 키
     */
    @ApiOperation(value = "PSM 감사기준 수정[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditStd", value = "PSM 감사기준", required = false, dataType = "AuditStd", paramType = "body") })
    @PutMapping("audititem")
    public ResponseEntity<Integer> updateAuditStd(@RequestBody AuditStd auditStd) throws Exception {
        return ResponseEntity.ok().body(auditItemService.updateAuditStd(auditStd));
    }

    /**
     * PSM 감사기준 삭제
     * 
     * @param auditStd
     *            PSM 감사기준
     * @return 변경 행 수
     */
    @ApiOperation(value = "PSM 감사기준 삭제[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditStd", value = "PSM 감사기준", required = false, dataType = "List", paramType = "path") })
    @DeleteMapping("audititem")
    public ResponseEntity<Integer> deleteAuditStd(@RequestBody List<AuditStd> auditStds) throws Exception {
        return ResponseEntity.ok().body(auditItemService.deleteAuditStd(auditStds));
    }

    /**
     * PSM 감사항목 조회
     * 
     * @param parameter
     *            검색조건
     * @return PSM 감사항목 목록
     */
    @ApiOperation(value = "PSM 감사항목 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditStdNo", value = "감사기준번호", required = false, dataType = "string", paramType = "query") })
    @GetMapping("audititeminfos")
    public ResponseEntity<List<AuditItem>> getAuditItemInfos(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        int auditStdNo = map.containsKey("auditStdNo")
                ? Integer.parseInt(
                "".equals(map.get("auditStdNo").toString()) ? "0" : map.get("auditStdNo").toString())
                : 0;
        return ResponseEntity.ok().body(auditItemService.getAuditItemInfos(auditStdNo, defaultParam));
    }

    /**
     * PSM 감사기준 사용확인
     * 
     * @param auditStdNo
     *            PSM 감사기준 번호
     * @return PSM 감사기준 상세
     */
    @ApiOperation(value = "PSM 감사기준 사용확인[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditStdNo", value = "PSM 감사기준 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("audititemstdusecheck/{auditStdNo}")
    public ResponseEntity<Integer> getAuditItemStdUseCheck(@PathVariable("auditStdNo") int auditStdNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(auditItemService.getAuditItemStdUseCheck(auditStdNo, defaultParam));
    }

    /**
     * PSM 감사항목 사용확인
     * 
     * @param auditItemNo
     *            PSM 감사항목 번호
     * @return PSM 감사항목 상세
     */
    @ApiOperation(value = "PSM 감사항목 사용확인[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditItem", value = "PSM 감사기준", required = false, dataType = "List", paramType = "path") })
    @PostMapping("audititemusecheck")
    public ResponseEntity<List<AuditItem>> getAuditItemUseCheck(@RequestBody List<AuditItem> auditItem) throws Exception {
        return ResponseEntity.ok().body(auditItemService.getAuditItemUseCheck(auditItem));
    }

}
