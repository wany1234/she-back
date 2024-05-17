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

package com.she.health.checkup.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.she.health.checkup.service.CheckupResultService;
import com.she.health.model.CheckupResult;
import com.she.health.model.CheckupResultDiag;
import com.she.health.model.TestItemResult;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 건강검진 결과 검색
 *
 */
@RestController
@RequestMapping("api/hea/checkup")
public class CheckupResultController {
	private final Logger logger = LoggerFactory.getLogger(CheckupResultController.class);

	@Autowired
	private CheckupResultService checkupResultService;

	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private CodeMasterMapper codeMasterMapper;

	private String normalExcelDownloadFileName = "일반검진결과_엑셀업로드양식.xlsx";

	private String specialExcelDownloadFileName = "특수검진결과_엑셀업로드양식.xlsx";

	private String templetePath = "templates"; // 엑셀 양식 경로

	/**
	 * 건강검진결과 조회
	 *
	 * @param parameter 검색조건
	 * @return 건강검진결과목록
	 * @throws Exception 예외
	 */
	@GetMapping("/checkupresults")
	public ResponseEntity<List<CheckupResult>> getCheckupResults(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
		String checkupYear = map.containsKey("checkupYear") ? map.get("checkupYear").toString() : "";
		int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo")
				? Integer.parseInt(map.get("heaCheckupPlanNo").toString())
				: 0;
		String retirementYn = map.containsKey("retirementYn") ? map.get("retirementYn").toString() : "";
		String[] heaDiagnoseCds = this.requestMapper.convertObjectListAsStringArray(map.get("heaDiagnoseCds"));
		String heaDiseaseClassCd = map.containsKey("heaDiseaseClassCd") ? map.get("heaDiseaseClassCd").toString() : "";
		String heaDiseaseCd = map.containsKey("heaDiseaseCd") ? map.get("heaDiseaseCd").toString() : "";
		int[] heaCheckedOrgNos = this.requestMapper.convertObjectListAsIntArray(map.get("heaCheckedOrgNos"));
		String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
		String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

		// 하위부서 포함여부
		String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

		List<CheckupResult> checkupResults = this.checkupResultService.getCheckupResults(userId, checkupYear,
				heaCheckupPlanNo, retirementYn, heaDiagnoseCds, heaDiseaseClassCd, heaDiseaseCd, heaCheckedOrgNos,
				userNm, plantCd, deptCd, deptSubYn);

		return ResponseEntity.ok().body(checkupResults);
	}

	/**
	 * 건강검진결과 상세 조회
	 *
	 * @param heaCheckupPlanNo 건강검진계획번호
	 * @param userId           사용자아이디
	 * @return 검진정보
	 * @throws Exception
	 */
	@ApiOperation(value = "건강검진결과 상세 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "heaCheckupPlanNo", value = "건강검진계획번호", required = false, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "userId", value = "사용자ID", required = false, dataType = "String", paramType = "path") })
	@GetMapping("/checkupresult/{heaCheckupPlanNo}/{userId}")
	public ResponseEntity<CheckupResult> getCheckupResult(@PathVariable int heaCheckupPlanNo,
			@PathVariable String userId) throws Exception {
		return ResponseEntity.ok().body(this.checkupResultService.getCheckupResult(heaCheckupPlanNo, userId));
	}

	/**
	 * 건강검진진단결과 조회
	 *
	 * @param parameter parameter
	 * @return 소견및판정정보
	 * @throws Exception
	 */
	@GetMapping("/checkupresultdiags")
	public ResponseEntity<List<CheckupResultDiag>> getCheckupResultDiags(
			@RequestParam HashMap<String, Object> parameter) throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo")
				? Integer.parseInt(map.get("heaCheckupPlanNo").toString())
				: 0;
		String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

		List<CheckupResultDiag> checkupResultDiags = this.checkupResultService.getCheckupResultDiags(heaCheckupPlanNo,
				userId);

		return ResponseEntity.ok().body(checkupResultDiags);
	}

	/**
	 * 건강검진항목별 결과 조회
	 *
	 * @param parameter parameter
	 * @return 검진항목 목록
	 * @throws Exception
	 */
	@GetMapping("/testitemresults")
	public ResponseEntity<List<TestItemResult>> getTestItemResults(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo")
				? Integer.parseInt(map.get("heaCheckupPlanNo").toString())
				: 0;
		String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
		String optionalYn = map.containsKey("optionalYn") ? map.get("optionalYn").toString() : "";

		List<TestItemResult> testItemResults = this.checkupResultService.getTestItemResults(heaCheckupPlanNo, userId,
				optionalYn);

		return ResponseEntity.ok().body(testItemResults);
	}

	/**
	 * 건강검진결과 등록 전 유효성 체크
	 *
	 * @param parameter parameter
	 * @return 중복 행 수
	 * @throws Exception
	 */
	@ApiOperation(value = "건강검진결과 등록 전 유효성 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "heaCheckupPlanNo", value = "검진계획번호", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "사용자Id", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("/validcheckupresult")
	public ResponseEntity<Integer> validCheckupResult(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

		int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo")
				? Integer.parseInt(map.get("heaCheckupPlanNo").toString())
				: 0;
		String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

		return ResponseEntity.ok().body(this.checkupResultService.validCheckupResult(heaCheckupPlanNo, userId));
	}

	/**
	 * 건강검진결과 등록
	 *
	 * @param checkupResult 건강검진결과
	 * @return 건강검진계획번호
	 * @throws Exception
	 */
	@ApiOperation(value = "건강검진결과 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "checkupResult", value = "건강검진결과", required = false, dataType = "CheckupResult", paramType = "body") })
	@PostMapping("/checkupresult")
	public ResponseEntity<Integer> createCheckupResult(@RequestBody CheckupResult checkupResult) throws Exception {
		return ResponseEntity.ok().body(this.checkupResultService.createCheckupResult(checkupResult));
	}

	/**
	 * 건강검진결과 수정
	 *
	 * @param checkupResult 건강검진결과
	 * @return 건강검진계획번호
	 * @throws Exception
	 */
	@ApiOperation(value = "건강검진결과 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "checkupResult", value = "건강검진결과", required = false, dataType = "CheckupResult", paramType = "body") })
	@PutMapping("/checkupresult")
	public ResponseEntity<Integer> updateCheckupResult(@RequestBody CheckupResult checkupResult) throws Exception {
		return ResponseEntity.ok().body(this.checkupResultService.updateCheckupResult(checkupResult));
	}

	/**
	 * 건강검진진단결과 등록
	 *
	 * @param checkupResultDiag 건강검진진단결과
	 * @return 등록행수
	 * @throws Exception
	 */
	@PostMapping("/checkupresultdiag")
	public ResponseEntity<Integer> createCheckupResultDiag(@RequestBody CheckupResultDiag checkupResultDiag)
			throws Exception {
		Integer count = this.checkupResultService.createCheckupResultDiag(checkupResultDiag);

		return ResponseEntity.ok().body(count);
	}

	/**
	 * 건강검진진단결과 삭제
	 *
	 * @param checkupResultDiags 삭제목록
	 * @return 삭제행수
	 * @throws Exception
	 */
	@DeleteMapping("/checkupresultdiags")
	public ResponseEntity<Integer> deleteCheckupResultDiags(@RequestBody List<CheckupResultDiag> checkupResultDiags)
			throws Exception {
		Integer count = this.checkupResultService.deleteCheckupResultDiags(checkupResultDiags);

		return ResponseEntity.ok().body(count);
	}

	/**
	 * 건강검진항목별 결과 등록
	 *
	 * @param testItemResult 건강검진항목별 결과
	 * @return 등록행수
	 * @throws Exception
	 */
	@PostMapping("/testitemresult")
	public ResponseEntity<Integer> createTestItemResult(@RequestBody TestItemResult testItemResult) throws Exception {
		Integer count = this.checkupResultService.createTestItemResult(testItemResult);
		return ResponseEntity.ok().body(count);
	}

	/**
	 * 건강검진항목별 결과 삭제
	 *
	 * @param testItemResults 삭제목록
	 * @return 삭제행수
	 * @throws Exception
	 */
	@DeleteMapping("/testitemresults")
	public ResponseEntity<Integer> deleteTestItemResults(@RequestBody List<TestItemResult> testItemResults)
			throws Exception {
		Integer count = this.checkupResultService.deleteTestItemResults(testItemResults);

		return ResponseEntity.ok().body(count);
	}

	/**
	 * 건강검진결과 엑셀업로드 파일 조회 및 validation check
	 *
	 * @param taskClassNm      업무구분
	 * @param taskKey          업무키
	 * @param heaCheckupPlanNo 건강검진계획번호
	 * @param heaCheckupOrgNo  건강검진기관번호
	 * @param createUserId     등록자
	 * @param files            파일
	 * @return 건강검진결과 엑셀업로드 파일 데이터 목록
	 * @throws Exception
	 */
	@ApiOperation(value = "건강검진결과 엑셀업로드 파일 조회 및 validation check", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "taskClassNm", value = "업무구분", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "taskKey", value = "업무키", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "heaCheckupPlanNo", value = "건강검진계획번호", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "heaCheckupOrgNo", value = "건강검진기관번호", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "createUserId", value = "등록자", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query") })
	@PostMapping("/excel/checkupresults")
	public ResponseEntity<Map<String, Object>> uploadExcelCheckupResult(@RequestParam("taskClassNm") String taskClassNm,
			@RequestParam("taskKey") String taskKey, @RequestParam("heaCheckupPlanNo") int heaCheckupPlanNo,
			@RequestParam("heaCheckupOrgNo") int heaCheckupOrgNo, @RequestParam("createUserId") String createUserId,
			@RequestParam("files") MultipartFile[] files) throws Exception {
		return ResponseEntity.ok().body(checkupResultService.uploadExcelCheckupResult(taskClassNm, taskKey,
				heaCheckupPlanNo, heaCheckupOrgNo, createUserId, files));
	}

	/**
	 * 건강검진결과 일반검진 양식 엑셀다운로드
	 *
	 * @return 엑셀업로드 양식
	 * @throws Exception
	 */
	@GetMapping("/checkupresultexceldownload/normal")
	public @ResponseBody byte[] downloadExcelCheckupResultNormal() throws Exception {
		CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH,
				ConstVal.CODE_FILE_PATH_FORM, "Y");

		ClassPathResource classPathResource = new ClassPathResource(
				templetePath + filePath.getCodeNm() + normalExcelDownloadFileName);
		File file = classPathResource.getFile();

		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
			String encodedString = new String(encoded);
			return encodedString.getBytes("UTF-8");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			inputStream.close();
		}
		return null;
	}

	/**
	 * 건강검진결과 특수검진 양식 엑셀다운로드
	 *
	 * @return 엑셀업로드 양식
	 * @throws Exception
	 */
	@GetMapping("/checkupresultexceldownload/special")
	public @ResponseBody byte[] downloadExcelCheckupResultSpecial() throws Exception {
		CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH,
				ConstVal.CODE_FILE_PATH_FORM, "Y");

		ClassPathResource classPathResource = new ClassPathResource(
				templetePath + filePath.getCodeNm() + specialExcelDownloadFileName);
		File file = classPathResource.getFile();
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
			String encodedString = new String(encoded);
			return encodedString.getBytes("UTF-8");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			inputStream.close();
		}
		return null;
	}

	/**
	 * 과거판정데이터 > 검진 결과 이력 목록 조회
	 *
	 * @param parameter 검색조건
	 * @return 건강검진결과이력
	 * @throws Exception 예외
	 */
	@GetMapping("/checkuppastresults")
	public ResponseEntity<List<CheckupResult>> getCheckupPastResults(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

		// 건강검진대상자
		String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
		// 검진계획
		int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo")
				? Integer.parseInt(map.get("heaCheckupPlanNo").toString())
				: 0;

		List<CheckupResult> checkupResults = this.checkupResultService.getCheckupPastResults(userId, heaCheckupPlanNo);
		return ResponseEntity.ok().body(checkupResults);
	}
}
