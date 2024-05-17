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
import com.she.safety.baseinfo.service.AuditUserService;
import com.she.safety.model.AuditUser;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * PSM 감사원
 */
@RestController
@RequestMapping("/api/saf/baseinfo/")
@Api(value = "/api/saf/baseinfo/", description = "PSM 감사원 서비스")
public class AuditUserController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AuditUserService auditUserService;

    /**
     * PSM 감사원 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 감사원 목록
     */
    @ApiOperation(value = "PSM 감사원 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "연도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "auditTypeCd", value = "감사원 구분", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query") })
    @GetMapping("auditusers")
    public ResponseEntity<List<AuditUser>> getAuditUsers(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        return ResponseEntity.ok().body(auditUserService.getAuditUsers(map, defaultParam));
    }

    /**
     * PSM 감사원 등록
     *
     * @param auditUser
     *            PSM 감사원
     * @return 키
     */
    @ApiOperation(value = "PSM 감사원 등록[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditUser", value = "PSM 감사원", required = false, dataType = "AuditUser", paramType = "body") })
    @PostMapping("audituser")
    public ResponseEntity<Integer> createAuditUser(@RequestBody AuditUser auditUser) throws Exception {
        return ResponseEntity.ok().body(auditUserService.createAuditUser(auditUser));
    }

    /**
     * PSM 감사원 상세 조회
     *
     * @param auditUserNo
     *            PSM 감사원 번호
     * @return PSM 감사원 상세
     */
    @ApiOperation(value = "PSM 감사원 상세조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditUserNo", value = "PSM 감사원 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("audituser/{auditUserNo}")
    public ResponseEntity<AuditUser> getAuditUser(@PathVariable("auditUserNo") int auditUserNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(auditUserService.getAuditUser(auditUserNo, defaultParam));
    }

    /**
     * PSM 감사원 수정
     *
     * @param auditUser
     *            PSM 감사원
     * @return 키
     */
    @ApiOperation(value = "PSM 감사원 수정[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditUser", value = "PSM 감사원", required = false, dataType = "AuditUser", paramType = "body") })
    @PutMapping("audituser")
    public ResponseEntity<Integer> updateAuditUser(@RequestBody AuditUser auditUser) throws Exception {
        return ResponseEntity.ok().body(auditUserService.updateAuditUser(auditUser));
    }

    /**
     * PSM 감사원 삭제
     *
     * @param auditUsers
     *            PSM 감사원
     * @return 변경 행 수
     */
    @ApiOperation(value = "PSM 감사원 삭제[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditUsers", value = "PSM 감사원", required = false, dataType = "List", paramType = "path") })
    @DeleteMapping("audituser")
    public ResponseEntity<Integer> deleteAuditUser(@RequestBody List<AuditUser> auditUsers) throws Exception {
        return ResponseEntity.ok().body(auditUserService.deleteAuditUser(auditUsers));
    }

    /**
     * PSM 감사원 사용확인
     *
     * @param auditUserNo
     *            PSM 감사원 번호
     * @return PSM 감사기준 상세
     */
    @ApiOperation(value = "PSM 감사원 사용확인[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditUserNo", value = "PSM 감사원 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("auditusercheck/{auditUserNo}")
    public ResponseEntity<Integer> getAuditUserCheck(@PathVariable("auditUserNo") int auditUserNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(auditUserService.getAuditUserCheck(auditUserNo, defaultParam));
    }
}
