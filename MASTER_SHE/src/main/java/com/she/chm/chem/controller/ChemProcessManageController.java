package com.she.chm.chem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.chm.chem.service.ChemProcessManageService;
import com.she.chm.model.ChemProcessManage;
import com.she.chm.model.ChemProcessManageChemprodVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/chm/chem")
@Api(value = "/api/chm/chem", description = "공정관리요령")
public class ChemProcessManageController {

    private final Logger logger = LoggerFactory.getLogger(MSDSController.class);

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemProcessManageService chemProcessManageService;

    /**
     * 공정관리요령 목록 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "공정관리요령 목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "search", value = "검색어", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deptCd", value = "부서코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "processCd", value = "공정코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/processmanages")
    public ResponseEntity<List<ChemProcessManage>> getChemProcessManages(@RequestParam HashMap<String, Object> parameter) throws Exception {
        logger.debug("공정관리요령 목록 조회");

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 검색어
        String search = map.containsKey("search") ? map.get("search").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 부서코드
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정코드
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        return ResponseEntity.ok().body(chemProcessManageService.getChemProcessManages(search, useYn, plantCd, deptCd, processCd));
    }

    @ApiOperation(value = "공정관리요령 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chmProcessManageNo", value = "공정관리요령번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/processmanage/{chmProcessManageNo}")
    public ResponseEntity<ChemProcessManage> getChemProcessManage(@PathVariable(name = "chmProcessManageNo") int chmProcessManageNo) throws Exception {
        return ResponseEntity.ok().body(this.chemProcessManageService.getChemProcessManage(chmProcessManageNo));
    }

    /**
     * 공정관리요령 등록
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    @PostMapping("/processmanage")
    public ResponseEntity<ChemProcessManage> createChemProcessManage(@RequestBody ChemProcessManage chemProcessManage) throws Exception {
        return ResponseEntity.ok().body(this.chemProcessManageService.createChemProcessManage(chemProcessManage));
    }

    /**
     * 공정관리요령 수정
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    @PutMapping("/processmanage")
    public ResponseEntity<ChemProcessManage> updateChemProcessManage(@RequestBody ChemProcessManage chemProcessManage) throws Exception {
        return ResponseEntity.ok().body(this.chemProcessManageService.updateChemProcessManage(chemProcessManage));
    }

    @ApiOperation(value = "공정관리요령 취급자재목록 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chmProcessManageNo", value = "공정관리요령번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/processmanagechemprodvals/{chmProcessManageNo}")
    public ResponseEntity<List<ChemProcessManageChemprodVal>> getChemProcessManageChemprodVals(@PathVariable(name = "chmProcessManageNo") int chmProcessManageNo) throws Exception {
        return ResponseEntity.ok().body(chemProcessManageService.getChemProcessManageChemprodVals(chmProcessManageNo));
    }

    @ApiOperation(value = "공정관리요령 출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chmProcessManageNos", value = "출력할 공정관리요령번호", required = false, dataType = "array", paramType = "query"), })
    @GetMapping("/print")
    public ResponseEntity<List<HashMap<String, Object>>> print(@RequestParam("chmProcessManageNos") int[] chmProcessManageNos) throws Exception {
        return ResponseEntity.ok().body(chemProcessManageService.getPrintChemProcessManage(chmProcessManageNos));
    }

    /**
     * 공정별관리요령 출력
     * 
     * @param chmProcessManageNo
     *            공정별관리요령 ID
     * @return 공정별관리요령 출력
     * @throws Exception
     */
    @ApiOperation(value = "공정별관리요령 출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "chmProcessManageNo", value = "공정별관리요령번호", required = false, dataType = "int", paramType = "path"), })
    @GetMapping("/processmanageprint/{chmProcessManageNo}")
    public @ResponseBody byte[] getProcessManagePrint(@PathVariable("chmProcessManageNo") int chmProcessManageNo) throws Exception {
        File file = this.chemProcessManageService.getPrintChemProcessManage(chmProcessManageNo);
        InputStream inputStream = null;

        try {

            if (file.exists() && file.isFile()) {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
                String encodedString = new String(encoded);
                return encodedString.getBytes("UTF-8");

            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

}
