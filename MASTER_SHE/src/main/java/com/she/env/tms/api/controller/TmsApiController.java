package com.she.env.tms.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.tms.api.service.TmsApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/api")
@Api(value = "/api/env/tms/api", description = "TMS 요청할 api")
public class TmsApiController {

	private final Logger logger = LoggerFactory.getLogger(TmsApiController.class);

	@Autowired
	private TmsApiService tmsApiService;

	@ApiOperation(value = "5분 데이터 저장", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tms5", value = "5분데이터", required = false, dataType = "List<HashMap<String, Object>>", paramType = "body") })
	@PostMapping("/tms5")
	public ResponseEntity<HashMap<String, Object>> saveTms5(@RequestBody List<HashMap<String, Object>> tms5) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result = tmsApiService.saveTms5(tms5);
			boolean isComplete = result > 0 ? true : false;
			String message = "";
			if (isComplete) {
				message = "성공 건 수 : " + result + ", 실패 건 수 : " + (tms5.size() - result);
			} else {
				message = "저장 된 데이터가 없습니다. 제공받은 5분 데이터의 건수 : " + tms5.size();
			}
			resultMap.put("IS_SUCCESS", isComplete);
			resultMap.put("MESSAGE", message);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("IS_SUCCESS", false);
			resultMap.put("MESSAGE", e.getMessage());
			throw new RuntimeException("Error message: " + e.getMessage());
		}

		return ResponseEntity.ok().body(resultMap);
	}

	@ApiOperation(value = "30분 데이터 저장", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "tms30", value = "30분데이터", required = false, dataType = "List<HashMap<String, Object>>", paramType = "body") })
	@PostMapping("/tms30")
	public ResponseEntity<HashMap<String, Object>> saveTms30(@RequestBody List<HashMap<String, Object>> tms30)
			throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result = tmsApiService.saveTms30(tms30);
			boolean isComplete = result > 0 ? true : false;
			String message = "";
			if (isComplete) {
				message = "성공 건 수 : " + result + ", 실패 건 수 : " + (tms30.size() - result);
			} else {
				message = "저장 된 데이터가 없습니다. 제공받은 30분 데이터의 건수 : " + tms30.size();
			}
			resultMap.put("IS_SUCCESS", isComplete);
			resultMap.put("MESSAGE", message);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			resultMap.put("IS_SUCCESS", false);
			resultMap.put("MESSAGE", e.getMessage());
			throw new RuntimeException("Error message: " + e.getMessage());
		}

		return ResponseEntity.ok().body(resultMap);
	}

}
