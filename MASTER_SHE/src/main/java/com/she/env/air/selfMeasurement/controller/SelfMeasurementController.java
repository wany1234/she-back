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
package com.she.env.air.selfMeasurement.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.she.common.model.DefaultParam;
import org.apache.commons.codec.binary.Base64;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.she.env.air.model.SelfMeasurement;
import com.she.env.air.selfMeasurement.service.SelfMeasurementService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 대기-자가측정
 *
 */
@RestController("SelfMeasurementController")
@RequestMapping("api/env/air/selfmeasurement")
public class SelfMeasurementController {

	private final Logger logger = LoggerFactory.getLogger(SelfMeasurementController.class);

	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private SelfMeasurementService selfMeasurementService;

	@Autowired
	private CodeMasterMapper codeMasterMapper;

	/**
	 * 자가측정 전체검색
	 *
	 * @param parameter 검색조건
	 * @return 자가측정목록
	 * @throws Exception
	 */
	@GetMapping("/selfmeasurements")
	public ResponseEntity<List<SelfMeasurement>> getSelfMeasurements(@RequestParam HashMap<String, Object> parameter,
			@ModelAttribute DefaultParam defaultParam) throws Exception {
		HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

		String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

		String selfManageStatus = map.containsKey("selfManageStatus") ? map.get("selfManageStatus").toString() : "";
		String searchEairOutletNm = map.containsKey("searchEairOutletNm") ? map.get("searchEairOutletNm").toString()
				: "";

		String fromYmd = map.containsKey("fromYmd") ? map.get("fromYmd").toString() : "";
		String toYmd = map.containsKey("toYmd") ? map.get("toYmd").toString() : "";
		String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";

		return ResponseEntity.ok().body(this.selfMeasurementService.getSelfMeasurements(fromYmd, toYmd, deptCd,
				selfManageStatus, searchEairOutletNm, plantCd, mgDeptCd, defaultParam));
	}

	/**
	 * 자가측정 상세검색
	 *
	 * @param eairOpMeasNo 자가측정번호
	 * @return 자가측정
	 * @throws Exception
	 */
	@GetMapping("/selfmeasurement/{eairOpMeasNo}")
	public ResponseEntity<SelfMeasurement> getSelfMeasurement(@PathVariable int eairOpMeasNo,
			@ModelAttribute DefaultParam defaultParam) throws Exception {
		return ResponseEntity.ok().body(this.selfMeasurementService.getSelfMeasurement(eairOpMeasNo, defaultParam));
	}

	/**
	 * 자가측정 신규등록
	 *
	 * @param selfMeasurement 자가측정
	 * @return 자가측정번호
	 * @throws Exception
	 */
	@PostMapping("/selfmeasurement")
	public ResponseEntity<Integer> createSelfMeasurement(@RequestBody SelfMeasurement selfMeasurement)
			throws Exception {
		return ResponseEntity.ok().body(selfMeasurementService.createSelfMeasurement(selfMeasurement));
	}

	/**
	 * 자가측정 수정
	 *
	 * @param selfMeasurement 자가측정
	 * @return 자가측정번호
	 * @throws Exception
	 */
	@PutMapping("/selfmeasurement")
	public ResponseEntity<Integer> updateSelfMeasurement(@RequestBody SelfMeasurement selfMeasurement)
			throws Exception {
		return ResponseEntity.ok().body(this.selfMeasurementService.updateSelfMeasurement(selfMeasurement));
	}

	/**
	 * 자가측정 삭제
	 *
	 * @param eairOpMeasNo 자가측정번호
	 * @return 자가측정번호
	 * @throws Exception
	 */
	@DeleteMapping("/selfmeasurement/{eairOpMeasNo}")
	public ResponseEntity<Integer> deleteSelfMeasurement(@PathVariable int eairOpMeasNo) throws Exception {
		return ResponseEntity.ok().body(this.selfMeasurementService.deleteSelfMeasurement(eairOpMeasNo));
	}

	/**
	 * 자가측정 중복체크
	 *
	 * @param parameter 중복조건
	 * @return 중복 행 수
	 * @throws Exception
	 */
	@ApiOperation(value = "자가측정 중복체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "eairOpMeasNo", value = "자가측정번호", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "eairOutletNo", value = "배출구번호", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "measureYmd", value = "측정일자", required = false, dataType = "string", paramType = "query") })
	@GetMapping("/check")
	public ResponseEntity<Integer> checkSelfMeasurement(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

		// 자가측정번호
		int eairOpMeasNo = map.containsKey("eairOpMeasNo") ? Integer.parseInt(map.get("eairOpMeasNo").toString()) : 0;
		// 배출구번호
		int eairOutletNo = map.containsKey("eairOutletNo") ? Integer.parseInt(map.get("eairOutletNo").toString()) : 0;
		// 사업장코드
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
		// 측정일자
		String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";

		return ResponseEntity.ok().body(
				this.selfMeasurementService.checkSelfMeasurement(eairOpMeasNo, eairOutletNo, plantCd, measureYmd));
	}

	/**
	 * 자가측정결과업로드 양식 엑셀다운로드
	 *
	 * @return 엑셀업로드 양식
	 * @throws Exception
	 */
	@GetMapping("/excel/down")
	public @ResponseBody byte[] excelDownSelfMeasurement() throws Exception {
		try {
			String fileName = "대기자가측정결과업로드양식.xlsx";
			String templetePath = "templates";

			CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH,
					ConstVal.CODE_FILE_PATH_FORM, "Y");

			ClassPathResource classPathResource = new ClassPathResource(templetePath + filePath.getCodeNm() + fileName);

			File file = classPathResource.getFile();

			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(file));
				byte[] encodeBase64 = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
				String encodedString = new String(encodeBase64);
				return encodedString.getBytes(StandardCharsets.UTF_8);
			} catch (IOException exception) {
				logger.error(exception.getMessage());
			} catch (Exception exception) {
				logger.error(exception.getMessage());
				throw exception;
			} finally {
				inputStream.close();
			}
		} catch (FileNotFoundException fileNotFoundException) {
			logger.error(fileNotFoundException.getMessage());
			throw fileNotFoundException;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		return null;
	}

	/**
	 * 자가측정결과 엑셀 업로드
	 *
	 * @return map(message, uploadList, totCount, completeCount, dontCount)
	 * @throws Exception
	 */
	@PostMapping("/excel/upload")
	public ResponseEntity<Map<String, Object>> getUploadExcelDataSelfMeasurement(
			@RequestParam("plantCd") String plantCd, @RequestParam("createUserId") String createUserId,
			@RequestParam("files") MultipartFile[] files) throws Exception {
		return ResponseEntity.ok()
				.body(this.selfMeasurementService.getUploadExcelDataSelfMeasurement(plantCd, createUserId, files));
	}
}
