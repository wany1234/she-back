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
package com.she.chm.baseInfo.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.she.chm.baseInfo.service.ChemicalRegulDbHistService;
import com.she.chm.baseInfo.service.ChemicalRegulDbService;
import com.she.chm.model.ChemicalRegulDb;
import com.she.chm.model.ChemicalRegulDbHist;
import com.she.chm.model.ChemicalRegulDbValid;
import com.she.security.auth.IAuthenticationFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/chm/baseinfo")
@Api(value = "/api/chm/baseinfo", description = "규제DB업로드 서비스")
public class ChemicalRegulDbController {

	private static Logger LOGGER = LoggerFactory.getLogger(ChemicalRegulDbController.class);

	@Autowired
	private ChemicalRegulDbHistService chemicalRegulDbHistService;

	@Autowired
	private ChemicalRegulDbService chemicalRegulDbService;

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private CodeMasterMapper codeMasterMapper;

	private String excelDownloadFileName = "규제DB업로드양식.xlsx";

	private String templetePath = "templates"; // 엑셀 양식 경로

	@ApiOperation(value = "규제DB업로드 조회[CHM06001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("/reguldbhists")
	public ResponseEntity<List<ChemicalRegulDbHist>> getChemicalRegulDbHists(
			@RequestParam HashMap<String, Object> parameter) throws Exception {
		return ResponseEntity.ok().body(chemicalRegulDbHistService.getChemicalRegulDbHists());
	}

	@ApiOperation(value = "규제DB업로드 상세조회[CHM06002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbhists/{chmRegulDbCd}")
	public ResponseEntity<ChemicalRegulDbHist> getChemicalRegulDbHist(
			@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd) throws Exception {
		return ResponseEntity.ok().body(chemicalRegulDbHistService.getChemicalRegulDbHist(chmRegulDbCd));
	}

	@ApiOperation(value = "규제DB업로드별 규제 목록조회[CHM06003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbs")
	public ResponseEntity<Map<String, Object>> getChemicalRegulDbs(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

		String chmRegulDbCd = map.containsKey("chmRegulDbCd") ? map.get("chmRegulDbCd").toString() : "";

		Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
		Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

		String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<ChemicalRegulDb> body = chemicalRegulDbService.getChemicalRegulDbsPage(chmRegulDbCd, pageNumber, pageSize,
				orderByExpression);

		returnMap.put("items", body);
		returnMap.put("count", body != null && body.size() > 0 ? body.get(0).getTotalCnt() : 0);

		return ResponseEntity.ok().body(returnMap);
	}

	@ApiOperation(value = "규제DB업로드[CHM06004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "files", value = "업로드할파일", required = true, dataType = "__file", paramType = "form"), })
	@PostMapping(path = "/reguldbhist", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ChemicalRegulDbHist> uploadChemicalRegulDbHist(@RequestParam("files") MultipartFile[] files)
			throws Exception {
		String createUserId = authenticationFacade.getUserName("");
		ChemicalRegulDbHist chemicalRegulDbHist = chemicalRegulDbHistService.uploadChemicalRegulDB(createUserId, files);
		return ResponseEntity.ok().body(chemicalRegulDbHist);
	}

	@ApiOperation(value = "규제DB적용[CHM06005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@PostMapping(path = "/reguldbhistapply/{chmRegulDbCd}")
	public ResponseEntity<ChemicalRegulDbHist> applyChemicalRegulDbHist(
			@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd) throws Exception {
		ChemicalRegulDbHist chemicalRegulDbHist = chemicalRegulDbHistService.getChemicalRegulDbHist(chmRegulDbCd);
		chemicalRegulDbHistService.applyChemicalRegulDbHist(chemicalRegulDbHist);
		return ResponseEntity.ok().body(chemicalRegulDbHist);
	}

	@ApiOperation(value = "규제DB이력삭제[CHM06006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@DeleteMapping("/reguldbhist/{chmRegulDbCd}")
	public ResponseEntity<String> createChemicalBranch(@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd)
			throws Exception {
		return ResponseEntity.ok().body(this.chemicalRegulDbHistService.deleteChemicalRegulDbHist(chmRegulDbCd));
	}

	@ApiOperation(value = "물질의영향을받은 취급자재조회[CHM06007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbhistaffected/chemprod/{chmRegulDbCd}")
	public ResponseEntity<List<ChemicalRegulDbValid>> getAffectedProdBefore(
			@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd) throws Exception {
		return ResponseEntity.ok().body(this.chemicalRegulDbHistService.getAffectedProdBefore(chmRegulDbCd));
	}

	@ApiOperation(value = "물질의영향을받은 취급물질조회[CHM06008]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbhistaffected/chem/{chmRegulDbCd}")
	public ResponseEntity<List<ChemicalRegulDbValid>> getAffectedChemBefore(
			@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd) throws Exception {
		return ResponseEntity.ok().body(this.chemicalRegulDbHistService.getAffectedChemBefore(chmRegulDbCd));
	}

	@ApiOperation(value = "물질의영향을받은 취급자재조회[CHM06007]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbhistaffected/chemprodapply/{chmRegulDbCd}")
	public ResponseEntity<List<ChemicalRegulDbValid>> getAffectedProdAfter(
			@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd) throws Exception {
		return ResponseEntity.ok().body(this.chemicalRegulDbHistService.getAffectedProdAfter(chmRegulDbCd));
	}

	@ApiOperation(value = "물질의영향을받은 취급물질조회[CHM06008]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbhistaffected/chemapply/{chmRegulDbCd}")
	public ResponseEntity<List<ChemicalRegulDbValid>> getAffectedChemAfter(
			@PathVariable(name = "chmRegulDbCd") String chmRegulDbCd) throws Exception {
		return ResponseEntity.ok().body(this.chemicalRegulDbHistService.getAffectedChemAfter(chmRegulDbCd));
	}

	@ApiOperation(value = "규제변경결과 EXCEL 업로드 양식 다운로드[CHM30020]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "chmRegulDbCd", value = "규제DB업로드코드", required = false, dataType = "string", paramType = "path") })
	@GetMapping("/reguldbhist/excel")
	public @ResponseBody byte[] reguldbExelDown() throws Exception {
		CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH,
				ConstVal.CODE_FILE_PATH_FORM, "Y");

		ClassPathResource classPathResource = new ClassPathResource(
				templetePath + filePath.getCodeNm() + excelDownloadFileName);
		File file = classPathResource.getFile();

		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
			String encodedString = new String(encoded);
			return encodedString.getBytes("UTF-8");
		} catch (FileNotFoundException e) {
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		} finally {
			inputStream.close();
		}
		return null;
	}

}
