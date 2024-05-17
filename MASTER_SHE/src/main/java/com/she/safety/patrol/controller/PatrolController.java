package com.she.safety.patrol.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.she.safety.model.Patrol;
import com.she.safety.model.PatrolInspector;
import com.she.safety.model.PatrolItemResult;
import com.she.safety.patrol.service.PatrolService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 순회
 */
@RestController
@RequestMapping("/api/saf/patrol/")
@Api(value = "/api/saf/patrol/", description = "순회 서비스")
public class PatrolController {
	// TODO : queryString 변환을 위한 mapper 선언
	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private PatrolService patrolService;

	/**
	 * 순회 조회
	 * 
	 * @param parameter 검색조건
	 * @return 순회 목록
	 */
	@ApiOperation(value = "순회 조회[SAF10001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"),
			@ApiImplicitParam(name = "deptCd", value = "순회주관부서", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "safCheckKindNo", value = "순회종류", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "checkStepCd", value = "진행단계", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtClsCd", value = "순회대상", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtDeptCd", value = "순회대상부서", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtVendorCd", value = "순회대상업체", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "checkResultCd", value = "점검결과코드", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtVendorNm", value = "순회대상업체명", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("patrols")
	public ResponseEntity<List<Patrol>> getPatrols(@RequestParam HashMap<String, Object> parameter) throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		// 사업장명
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
		// 기간
		String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
		// from
		String startDate = "";
		// to
		String endDate = "";
		if (period != null && period.length == 2) {
			startDate = period[0];
			endDate = period[1];
		}

		String[] periodRes = this.requestMapper.convertObjectListAsStringArray(map.get("periodRes"));
		// from
		String startDateRes = "";
		// to
		String endDateRes = "";
		if (periodRes != null && periodRes.length == 2) {
			startDateRes = periodRes[0];
			endDateRes = periodRes[1];
		}
		// 사업장명
		String checkTitle = map.containsKey("checkTitle") ? map.get("checkTitle").toString() : "";

		// 순회주관부서
		String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
		// 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";
		// 순회종류
		int safCheckKindNo = map.containsKey("safCheckKindNo") ? Integer.parseInt("".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString()): 0;
		// 진행단계
		String checkStepCd = map.containsKey("checkStepCd") ? map.get("checkStepCd").toString() : "";
		// 순회대상부서
		String tgtClsCd = map.containsKey("tgtClsCd") ? map.get("tgtClsCd").toString() : "";
		// 순회대상부서
		String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
		// 하위부서 포함여부
        String tgtDeptSubYn = map.containsKey("tgtDeptSubYn") ? map.get("tgtDeptSubYn").toString() : "Y";
		// 순회대상부서
		String pgpDeptCd = map.containsKey("pgpDeptCd") ? map.get("pgpDeptCd").toString() : "";
		// 하위부서 포함여부
        String pgpDeptSubYn = map.containsKey("pgpDeptSubYn") ? map.get("pgpDeptSubYn").toString() : "Y";
		// 순회대상업체
		String tgtVendorCd = map.containsKey("tgtVendorCd") ? map.get("tgtVendorCd").toString() : "";
		// 순회대상업체명
		String tgtVendorNm = map.containsKey("tgtVendorNm") ? map.get("tgtVendorNm").toString() : "";
		// 결재진행상태
		String bizApprStepCd = map.containsKey("bizApprStepCd") ? map.get("bizApprStepCd").toString() : "";
		// 결재진행상태
		String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
		// 순회결과
		String checkResultCd = map.containsKey("checkResultCd") ? map.get("checkResultCd").toString() : "";

		return ResponseEntity.ok()
				.body(patrolService.getPatrols(tgtVendorNm, checkResultCd, plantCd, startDate, endDate, startDateRes,
						endDateRes, deptCd, deptSubYn, safCheckKindNo, checkStepCd, tgtClsCd, tgtDeptCd, tgtDeptSubYn, pgpDeptCd, pgpDeptSubYn,tgtVendorCd,
						bizApprStepCd, userNm, checkTitle));
	}

	/**
	 * 순회 상세 조회
	 * 
	 * @param safCheckRsltNo 순회번호
	 * @return 순회
	 */
	@ApiOperation(value = "순회 상세조회[SAF10002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "safCheckRsltNo", value = "순회번호", required = false, dataType = "int", paramType = "path") })
	@GetMapping("patrol/{safCheckRsltNo}")
	public ResponseEntity<Patrol> getPatrol(@PathVariable("safCheckRsltNo") int safCheckRsltNo) throws Exception {
		return ResponseEntity.ok().body(patrolService.getPatrol(safCheckRsltNo));
	}

	/**
	 * 순회 등록
	 * 
	 * @param patrol 순회
	 * @return 키
	 */
	@ApiOperation(value = "순회 등록[SAF10003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "patrol", value = "순회", required = false, dataType = "Patrol", paramType = "body") })
	@PostMapping("patrol")
	public ResponseEntity<Integer> createPatrol(@RequestBody Patrol patrol) throws Exception {
		return ResponseEntity.ok().body(patrolService.createPatrol(patrol) > 0 ? patrol.getSafCheckRsltNo() : 0);
	}

	/**
	 * 순회 저장
	 * /patrol
	 * @param patrol 순회
	 * @return 키
	 */
	@ApiOperation(value = "순회 수정[SAF10004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "patrol", value = "순회", required = false, dataType = "Patrol", paramType = "body") })
	@PutMapping("patrol")
	public ResponseEntity<Integer> updatePatrol(@RequestBody Patrol patrol) throws Exception {
		return ResponseEntity.ok().body(patrolService.updatePatrol(patrol) > 0 ? patrol.getSafCheckRsltNo() : 0);
	}

	/**
	 * 순회 삭제
	 * 
	 * @param safCheckRsltNo 순회번호
	 * @return 변경 행 수
	 */
	@ApiOperation(value = "순회 삭제[SAF10005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "safCheckRsltNo", value = "순회번호", required = false, dataType = "int", paramType = "path") })
	@DeleteMapping("patrol/{safCheckRsltNo}")
	public ResponseEntity<Integer> deletePatrol(@PathVariable("safCheckRsltNo") int safCheckRsltNo) throws Exception {
		return ResponseEntity.ok().body(patrolService.deletePatrol(safCheckRsltNo));
	}

	/**
	 * 순회 결과 조회
	 * 
	 * @param parameter 검색조건
	 * @return 순회 목록
	 */
	@ApiOperation(value = "순회 결과 조회[SAF10006]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "safCheckRsltNo", value = "순회번호", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "safCheckKindNo", value = "순회종류", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("patrolresultitems")
	public ResponseEntity<List<PatrolItemResult>> getPatrolResultItems(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		// 순회번호
		int safCheckRsltNo = map.containsKey("safCheckRsltNo")
				? Integer.parseInt(
						"".equals(map.get("safCheckRsltNo").toString()) ? "0" : map.get("safCheckRsltNo").toString())
				: 0;
		// 순회종류
		int safCheckKindNo = map.containsKey("safCheckKindNo")
				? Integer.parseInt(
						"".equals(map.get("safCheckKindNo").toString()) ? "0" : map.get("safCheckKindNo").toString())
				: 0;

		return ResponseEntity.ok().body(patrolService.getPatrolResultItems(safCheckRsltNo, safCheckKindNo));
	}

	@PutMapping("/patrol/complete/result/{safCheckRsltNo}/{patrolMstNo}")
	public ResponseEntity<Integer> completePatrolResultPlan(@PathVariable("safCheckRsltNo") int safCheckRsltNo,
			@PathVariable("patrolMstNo") int patrolMstNo) throws Exception {
		return ResponseEntity.ok().body(patrolService.completePatrolResultPlan(safCheckRsltNo, patrolMstNo));
	}
	
	
	/**
	 * 순회 점검자 목록 조회
	 * 
	 * @param parameter 검색조건
	 * @return 순회 목록
	 */
	
	@ApiOperation(value = "순회 점검자 목록 조회[SAF10012]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "safCheckRsltNo", value = "순회번호", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "safCheckKindNo", value = "순회종류", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("inspector")
	public ResponseEntity<List<PatrolInspector>> getCheckInspectors(@RequestParam HashMap<String, Object> parameter)
			throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		// 순회번호
		int safCheckRsltNo = map.containsKey("safCheckRsltNo")
				? Integer.parseInt(
						"".equals(map.get("safCheckRsltNo").toString()) ? "0" : map.get("safCheckRsltNo").toString())
				: 0;
		  // 점검자구분코드
	    String inspectorClassCd = map.containsKey("inspectorClassCd") ? map.get("inspectorClassCd").toString() : "";
	    
	    
	    List<PatrolInspector> checkInspectors = patrolService.getCheckInspectors(safCheckRsltNo, inspectorClassCd);
		return ResponseEntity.ok().body(checkInspectors);
	}


	/**
	 * 순회 사업장별 실적집계
	 * 
	 * @param parameter 검색조건
	 * @return 순회 사업장별 실적집계 목록
	 */
	@ApiOperation(value = "순회 사업장별 실적집계[SAF10010]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("patrolstatus")
	public ResponseEntity<List<HashMap<String, Object>>> getPatrolStatus(
			@RequestParam HashMap<String, Object> parameter) throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		// 사업장명
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
		// 점검년도
		String year = map.containsKey("year") ? map.get("year").toString() : "";

		return ResponseEntity.ok().body(patrolService.getPatrolStatus(plantCd, year));
	}

	/**
	 * 순회 사업장별 실적 세부집계
	 * 
	 * @param parameter 검색조건
	 * @return 순회 사업장별 실적 세부집계 목록
	 */
	@ApiOperation(value = "순회 사업장별 실적 세부집계[SAF10011]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "year", value = "점검년도", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "deptCd", value = "주관부서", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtClsCd", value = "순회대상", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtDeptCd", value = "순회대상부서", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "tgtVendorCd", value = "순회대상업체", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "checkResultCd", value = "순회결과", required = false, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
	@GetMapping("patrolstatussub")
	public ResponseEntity<List<HashMap<String, Object>>> getPatrolStatusSub(
			@RequestParam HashMap<String, Object> parameter) throws Exception {
		HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
		// 사업장명
		String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
		// 점검년도
		String year = map.containsKey("year") ? map.get("year").toString() : "";
		// 주관부서
		String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
		// 순회대상
		String tgtClsCd = map.containsKey("tgtClsCd") ? map.get("tgtClsCd").toString() : "";
		// 순회대상부서
		String tgtDeptCd = map.containsKey("tgtDeptCd") ? map.get("tgtDeptCd").toString() : "";
		// 순회대상업체
		String tgtVendorCd = map.containsKey("tgtVendorCd") ? map.get("tgtVendorCd").toString() : "";
		// 순회결과
		String checkResultCd = map.containsKey("checkResultCd") ? map.get("checkResultCd").toString() : "";

		return ResponseEntity.ok().body(patrolService.getPatrolStatusSub(plantCd, year, deptCd, tgtClsCd, tgtDeptCd,
				tgtVendorCd, checkResultCd));
	}

}
