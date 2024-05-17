package com.she.env.tms.status.controller;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.tms.model.TmsStatus;
import com.she.env.tms.status.service.TmsStatusService;
import com.she.utils.ExcelUtil;
import com.she.utils.FileUtil;
import com.she.utils.RequestMapper;
import com.she.utils.model.ExcelParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/env/tms/status")
@Api(value = "/api/env/tms/status", description = "환경 tms 현황 서비스")
public class TmsStatusController {

	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private TmsStatusService tmsStatusService;

	private static final Logger logger = LoggerFactory.getLogger(TmsStatusController.class);

	/**
	 * TMS 현황 조회
	 * 
	 * @return TMS 현황 데이터
	 * @throws Exception
	 */
	@ApiOperation(value = "TMS 현황 조회[ENV16001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "itemCode", value = "TMS 측정항목코드", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("/tms5status")
	public ResponseEntity<Map<String, Object>> getTms5Status(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

		// 구분
		String tmsType = map.containsKey("tmsType") ? map.get("tmsType").toString() : "";
		// 사업장
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
		// 측정년도
		String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
		String startDate = "";
		String endDate = "";
		if (period != null && period.length == 2) {
			startDate = period[0];
			endDate = period[1];
		}

		// TMS 측정소코드
		String stationCode = map.containsKey("stationCode") ? map.get("stationCode").toString() : "";
		// TMS 측정항목코드
		String itemCode = map.containsKey("itemCode") ? map.get("itemCode").toString() : "";

		Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
		Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

		String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

		Map<String, Object> returnMap = new HashMap<String, Object>();
		List<TmsStatus> body = this.tmsStatusService.getTms5Status(tmsType, plantCd, startDate, endDate, stationCode,
				itemCode, pageNumber, pageSize, orderByExpression);
		List<TmsStatus> total = tmsStatusService.getTms5Status(tmsType, plantCd, startDate, endDate, stationCode,
				itemCode, -1, -1, orderByExpression);
		Integer totalCount = total.size();
		returnMap.put("items", body);
		returnMap.put("count", totalCount);

		return ResponseEntity.ok().body(returnMap);
	}

	@ApiOperation(value = "TMS 현황 출력[ENV16002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@PostMapping("/exceltms5status")
	public @ResponseBody byte[] getWkodMasterTemplete(@RequestBody String data) throws Exception {
		ExcelParam excelParamObject = RequestMapper.convertJSONStringToClass(ExcelParam.class, data);

		// param setting
		Map<String, Object> searchParamMap = excelParamObject.getSearchParam();
		// 측정년도
		String[] period = this.requestMapper.convertObjectListAsStringArray(searchParamMap.get("period"));
		String startDate = "";
		String endDate = "";
		if (period != null && period.length == 2) {
			startDate = period[0];
			endDate = period[1];
		}

		List<TmsStatus> total = null;
		if (String.valueOf(searchParamMap.get("tmsType")) != null
				&& String.valueOf(searchParamMap.get("plantCd")) != null
				&& String.valueOf(searchParamMap.get("stationCode")) != null
				&& String.valueOf(searchParamMap.get("itemCode")) != null) {
			total = tmsStatusService.getTms5Status(String.valueOf(searchParamMap.get("tmsType")),
					String.valueOf(searchParamMap.get("plantCd")), startDate, endDate,
					String.valueOf(searchParamMap.get("stationCode")), String.valueOf(searchParamMap.get("itemCode")),
					-1, -1, null);
		}

		File file = ExcelUtil.createExcelFile(excelParamObject.getTableHeaders(), total, "TMS 현황",
				FileUtil.getStoreFilePath());

		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
			String encodedString = new String(encoded);
			return encodedString.getBytes("UTF-8");

		} catch (IllegalArgumentException iae) {
			logger.error(iae.getMessage());
			throw iae;
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
