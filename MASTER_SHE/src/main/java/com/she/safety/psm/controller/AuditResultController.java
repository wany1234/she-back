package com.she.safety.psm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.model.SafPsmAuditRsltImpr;
import com.she.safety.psm.service.AuditResultService;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * PSM 결과 등록
 */
@RestController
@RequestMapping("/api/saf/psm/auditresult/")
@Api(value = "/api/saf/psm/auditresult/", description = "PSM 결과 등록 서비스")
public class AuditResultController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AuditResultService auditResultService;

    private static final Logger logger = LoggerFactory.getLogger(AuditResultController.class);

    /**
     * PSM 결과 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 결과 목록
     */
    @ApiOperation(value = "PSM 결과 조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "auditYear", value = "감사년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "auditType", value = "(C)감사종류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "psmProgState", value = "(C)진행상태", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "감사팀", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "userId", value = "감사원", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "searchType", value = "감사원", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "startYear", value = "시작년도", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "endYear", value = "끝년도", required = false, dataType = "string", paramType = "query") })
    @GetMapping("auditresults")
    public ResponseEntity<List<SafPsmAuditRslt>> getAuditResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        return ResponseEntity.ok().body(auditResultService.getAuditResults(map, defaultParam));
    }

    /**
     * PSM 결과 상세 조회
     *
     * @param auditRsltNo
     *            PSM 결과 번호
     * @return PSM 결과 상세
     */
    @ApiOperation(value = "PSM 결과 상세조회[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditRsltNo", value = "PSM 결과 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("auditresult/{auditRsltNo}")
    public ResponseEntity<SafPsmAuditRslt> getAuditResult(@PathVariable("auditRsltNo") int auditRsltNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(auditResultService.getAuditResult(auditRsltNo, defaultParam));
    }

    /**
     * 개선조치사항 항목 조회
     *
     * @param parameter
     *            (개선분류코드 코드, 개선진행간계 코드, 조치구분, 제목, 개선요청부서 코드, 조치부서 코드, 요청일,
     *            관련테이블 PKID)
     * @return PSM 감사결과_개선사항 목록
     * @throws Exception
     */
    @GetMapping("auditresultimpr")
    public ResponseEntity<List<SafPsmAuditRsltImpr>> getAuditResultImpr(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";
        int auditRsltNo = map.containsKey("auditRsltNo") ? Integer.parseInt(map.get("auditRsltNo").toString()) : 0;

        return ResponseEntity.ok().body(auditResultService.getAuditResultImpr(imprClassCd, auditRsltNo, defaultParam));

    }

    /**
     * PSM 결과 수정
     *
     * @param safPsmAuditRslt
     *            PSM 결과
     * @return 키
     */
    @ApiOperation(value = "PSM 결과 수정[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safPsmAuditRslt", value = "PSM 결과", required = false, dataType = "SafPsmAuditRslt", paramType = "body") })
    @PutMapping("auditresult")
    public ResponseEntity<Integer> updateAuditResult(@RequestParam("safPsmAuditRslt") String safPsmAuditRslt, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {
        SafPsmAuditRslt objSafPsmAuditRslt = RequestMapper.convertJSONStringToClass(SafPsmAuditRslt.class, safPsmAuditRslt);
        return ResponseEntity.ok().body(auditResultService.updateAuditResult(objSafPsmAuditRslt, taskClassNm, tempIds, files));
    }

    /**
     * 결과완료
     *
     * @param safPsmAuditRslt
     *            PSM 결과
     * @return 키
     */
    @ApiOperation(value = "PSM 결과완료[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safPsmAuditRslt", value = "PSM 결과", required = false, dataType = "SafPsmAuditRslt", paramType = "body") })
    @PutMapping("auditresultcomplete")
    public ResponseEntity<Integer> auditResultComplete(@RequestParam("safPsmAuditRslt") String safPsmAuditRslt, @RequestParam("taskClassNm") String taskClassNm, @RequestParam("tempId") List<String> tempIds, @RequestParam("files") MultipartFile[] files) throws Exception {
        SafPsmAuditRslt objSafPsmAuditRslt = RequestMapper.convertJSONStringToClass(SafPsmAuditRslt.class, safPsmAuditRslt);
        objSafPsmAuditRslt.setPsmProgState(ConstVal.CODE_MASTER_PSM_PROG_STATE_RESULT_COMPLETE);
        return ResponseEntity.ok().body(auditResultService.updateAuditResult(objSafPsmAuditRslt, taskClassNm, tempIds, files));
    }

    /**
     * PSM 감사결과 보고서 출력
     *
     * @param
     * @return 감사결과 보고서
     * @throws Exception
     *             예외
     */
    @ApiOperation(value = "감사결과 보고서 출력[]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "auditRsltNo", value = "PSM 결과 번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/printauditresult/{auditRsltNo}")
    public @ResponseBody byte[] printAuditResult(@PathVariable("auditRsltNo") int auditRsltNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        File file = this.auditResultService.printAuditResult(auditRsltNo, defaultParam);

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw fe;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            throw ie;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
    }
}
