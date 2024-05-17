package com.she.common.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.CodeDomain;
import com.she.common.model.DefaultParam;
import com.she.common.model.Dept;
import com.she.common.model.Menu;
import com.she.common.model.MenuTreeBase;
import com.she.common.service.CommonService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class CommonController {
    @Autowired
    private CommonService commonService;

    @Autowired
    private RequestMapper requestMapper;

    @GetMapping("/api/common/depts")
    public ResponseEntity<List<Dept>> getDepts() throws Exception {
        return ResponseEntity.ok().body(commonService.getDepts());
    }

    /**
     * ///// 메뉴 영역 시작 /////
     */

    /**
     * 메뉴 등록 API
     *
     * @param menu
     *            : Menu 클래스 모델
     * @return : 등록된 메뉴의 id
     * @throws Exception
     */
    @PostMapping("/api/common/menu")
    public ResponseEntity<String> createMenu(@RequestBody Menu menu) throws Exception {
        return ResponseEntity.ok().body(commonService.createMenu(menu));
    }

    /**
     * 메뉴 수정 API
     *
     * @param menu
     * @param menu
     * @return
     * @throws Exception
     */
    @PutMapping("/api/common/menu")
    public ResponseEntity<Integer> updateMenu(@RequestBody Menu menu) throws Exception {
        return ResponseEntity.ok().body(commonService.updateMenu(menu));
    }

    /**
     * 메뉴 목록 조회 함수
     *
     * @param menu
     *            : 메뉴 모델 형태 검색 조건
     * @return 검색 조건에 맞는 메뉴 목록
     * @throws Exception
     */
    @GetMapping("/api/common/menus")
    public ResponseEntity<List<Menu>> getMenus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String taskGrpCd = map.containsKey("taskGrpCd") ? map.get("taskGrpCd").toString() : "";
        String menuNm = map.containsKey("menuNm") ? map.get("menuNm").toString() : "";
        String upMenuId = map.containsKey("upMenuId") ? map.get("upMenuId").toString() : "";
        Integer menuLvl = map.containsKey("menuLvl") ? Integer.valueOf(map.get("menuLvl").toString()) : null;
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(commonService.getMenus(taskGrpCd, menuNm, upMenuId, menuLvl, useYn, defaultParam));
    }

    /**
     * 트리 메뉴를 구성할 기초 메뉴 정보 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/api/common/menutreebases")
    public ResponseEntity<List<MenuTreeBase>> getMenuTreeBases(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String taskGrpCd = map.containsKey("taskGrpCd") ? map.get("taskGrpCd").toString() : "";

        String menuNm = map.containsKey("menuNm") ? map.get("menuNm").toString() : "";
        String upMenuId = map.containsKey("upMenuId") ? map.get("upMenuId").toString() : "";
        Integer menuLvl = map.containsKey("menuLvl") ? Integer.valueOf(map.get("menuLvl").toString()) : null;
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(commonService.getMenuTreeBases(taskGrpCd, menuNm, upMenuId, menuLvl, useYn));
    }

    @GetMapping("/api/common/menutreebasesauth")
    public ResponseEntity<List<MenuTreeBase>> getMenuTreeBasesAuth(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String taskGrpCd = map.containsKey("taskGrpCd") ? map.get("taskGrpCd").toString() : "";

        String menuNm = map.containsKey("menuNm") ? map.get("menuNm").toString() : "";
        String upMenuId = map.containsKey("upMenuId") ? map.get("upMenuId").toString() : "";
        Integer menuLvl = map.containsKey("menuLvl") ? Integer.valueOf(map.get("menuLvl").toString()) : null;
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(commonService.getMenuTreeBases(taskGrpCd, menuNm, upMenuId, menuLvl, useYn));
    }

    /**
     * ///// 메뉴 영역 종료 /////
     */

    /**
     * ///// 공통 코드 영역 /////
     */
    /**
     * 코드 도메인 목록 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/api/common/codedomains")
    public ResponseEntity<List<CodeDomain>> getCodeDomains() throws Exception {
        return ResponseEntity.ok().body(commonService.getCodeDomains());
    }

    /**
     * 코드 도메인 단일건 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/api/common/codedomain/{codeGrpCd}")
    public ResponseEntity<CodeDomain> getCodeDomain() throws Exception {
        return ResponseEntity.ok().body(commonService.getCodeDomain());
    }

    /**
     * aui grid에서 export를 하면 호출된다. aui grid에서 넘겨주는 grid data와 파일명으로 다운로드가 되도록
     * 한다.
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "엑셀 내보내기 [반환값 : 엑셀 파일 스트림]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "data", value = "데이터", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "extension", value = "확장자", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "파일명", required = true, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "파라미터", required = false, dataType = "object", paramType = "query") })
    @PostMapping("/api/common/export")
    public ResponseEntity<byte[]> export(@RequestParam final HashMap<String, Object> parameter) throws Exception {
        final HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        final String data = map.containsKey("data") ? map.get("data").toString() : "";
        final String extension = map.containsKey("extension") ? map.get("extension").toString() : "";
        final String filename = map.containsKey("filename") ? map.get("filename").toString() : "export";

        // 파일이 한글명이면 다운로드시 이름이 export로 된다.
        final String encordedFilename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20").concat(!extension.equals("") ? ".".concat(extension) : "");

        final byte[] dataByte = Base64.decodeBase64(data.getBytes());

        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();

            if (extension.equals("csv")) {
                final String sting = new String(dataByte, StandardCharsets.UTF_8);
                outputStream.write(sting.getBytes("euc-kr"));
            } else {
                outputStream.write(dataByte);
            }

            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename).header(HttpHeaders.CONTENT_TYPE, "application/octet-stream").contentLength(outputStream.size()) //
                    .body(outputStream.toByteArray());
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ///// 공통 코드 영역 종료 /////
     */
}
