package com.she.health.workMeasure.controller;

import com.she.health.model.WorkMeasurePlan;
import com.she.health.model.WorkMeasureResult;
import com.she.health.workMeasure.service.WorkMeasurePlanService;
import com.she.utils.RequestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;

/**
 * 작업환경측정계획
 */
@RestController
@RequestMapping("api/hea/workmeasure")
@Api(value = "/api/hea/workmeasure", description = "작업환경측정")
public class WorkMeasurePlanController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private WorkMeasurePlanService workMeasurePlanService;

    /**
     * 작업환경측정계획 신규등록
     *
     * @param workMeasurePlan
     *            작업환경측정계획
     * @return 작업환경측정계획번호
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 등록", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "WorkMeasurePlan", value = "작업환경측정계획", required = false, dataType = "WorkMeasurePlan", paramType = "body") })
    @PostMapping("/workmeasureplan")
    public ResponseEntity<Integer> createWorkMeasurePlan(@RequestBody WorkMeasurePlan workMeasurePlan) throws Exception {
        return ResponseEntity.ok().body(this.workMeasurePlanService.createWorkMeasurePlan(workMeasurePlan));
    }

    /**
     * 작업환경측정계획 조회
     *
     * @param parameter
     *            검색조건
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "측정년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "measPlanNm", value = "측정계획명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "workMeasStateCd", value = "진행상태", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/workmeasureplans")
    public ResponseEntity<List<WorkMeasurePlan>> getWorkMeasurePlans(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 측정계획명
        String measPlanNm = map.containsKey("measPlanNm") ? map.get("measPlanNm").toString() : "";
        // 진행상태
        String workMeasStateCd = map.containsKey("workMeasStateCd") ? map.get("workMeasStateCd").toString() : "";

        return ResponseEntity.ok().body(workMeasurePlanService.getWorkMeasurePlans(plantCd, year, measPlanNm, workMeasStateCd));
    }

    /**
     * 작업환경측정계획 상세 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 측정항목
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasPlanNo", value = "작업환경측정계획번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/workmeasureplan/{workMeasPlanNo}")
    public ResponseEntity<WorkMeasurePlan> getWorkMeasurePlan(@PathVariable(name = "workMeasPlanNo") int workMeasPlanNo) throws Exception {
        return ResponseEntity.ok().body(this.workMeasurePlanService.getWorkMeasurePlan(workMeasPlanNo));
    }

    /**
     * 작업환경측정계획 수정
     *
     * @param workMeasurePlan
     *            작업환경측정계획
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 수정", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "WorkMeasurePlan", value = "작업환경측정계획", required = false, dataType = "WorkMeasurePlan", paramType = "body") })
    @PutMapping("/workmeasureplan")
    public ResponseEntity<Integer> updateWorkMeasurePlan(@RequestBody WorkMeasurePlan workMeasurePlan) throws Exception {
        return ResponseEntity.ok().body(this.workMeasurePlanService.updateWorkMeasurePlan(workMeasurePlan));
    }

    /**
     * 작업환경측정계획명 체크
     *
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 체크", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "측정년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "halfYearCd", value = "측정분기", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "measAgency", value = "측정기관", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "workMeasPlanNo", value = "작업환경측정계획번호", required = false, dataType = "int", paramType = "query"), @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/checkworkmeasureplan")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckWorkMeasurePlan(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 측정년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 측정분기
        String halfYearCd = map.containsKey("halfYearCd") ? map.get("halfYearCd").toString() : "";
        // 작업환경측정계획번호
        int workMeasPlanNo = map.containsKey("workMeasPlanNo") ? Integer.parseInt("".equals(map.get("workMeasPlanNo").toString()) ? "0" : map.get("workMeasPlanNo").toString()) : 0;
        // 측정일자
        String measDt = map.containsKey("measDt") ? map.get("measDt").toString() : "";

        return ResponseEntity.ok().body(workMeasurePlanService.getCheckWorkMeasurePlan(plantCd, year, halfYearCd, workMeasPlanNo, measDt));
    }

    /**
     * 작업환경측정계획 삭제
     *
     * @param workMeasurePlan
     *            작업환경측정계획
     * @return 삭제 행 수
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 삭제", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasurePlan", value = "작업환경측정계획정보s", required = false, dataType = "List", paramType = "body") })
    @DeleteMapping("/workmeasureplan")
    public ResponseEntity<Integer> deleteWorkMeasurePlan(@RequestBody WorkMeasurePlan workMeasurePlan) throws Exception {
        return ResponseEntity.ok().body(this.workMeasurePlanService.deleteWorkMeasurePlan(workMeasurePlan));
    }

    /**
     * 작업환경측정결과 화면 조회
     *
     * @param parameter
     *            검색조건
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 화면 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "measPlanNm", value = "측정계획명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "workMeasStateCd", value = "진행상태", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/workmeasureplanresults")
    public ResponseEntity<List<WorkMeasurePlan>> getWorkMeasurePlanResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 측정계획명
        String measPlanNm = map.containsKey("measPlanNm") ? map.get("measPlanNm").toString() : "";
        // 진행상태
        String workMeasStateCd = map.containsKey("workMeasStateCd") ? map.get("workMeasStateCd").toString() : "";

        return ResponseEntity.ok().body(workMeasurePlanService.getWorkMeasurePlanResults(plantCd, year, measPlanNm, workMeasStateCd));
    }

    /**
     * 작업환경측정결과 실적 조회
     *
     * @param parameter
     *            검색조건
     * @return 작업환경측정계획 목록
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정결과 실적 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "year", value = "년도", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "measPlanNm", value = "측정계획명", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "workMeasStateCd", value = "진행상태", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/workmeasurestatss")
    public ResponseEntity<List<WorkMeasureResult>> workmeasurestatss(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 년도
        String year = map.containsKey("year") ? map.get("year").toString() : "";
        // 부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        // 분기
        String halfYearCd = map.containsKey("halfYearCd") ? map.get("halfYearCd").toString() : "";
        // 유해인자대분류
        String workAreaGradeCd = map.containsKey("workAreaGradeCd") ? map.get("workAreaGradeCd").toString() : "";

        return ResponseEntity.ok().body(workMeasurePlanService.workmeasurestatss(plantCd, year, deptCd, processCd, halfYearCd, workAreaGradeCd));
    }

    /**
     * 작업환경측정계획 상세 조회
     *
     * @param workMeasPlanNo
     *            작업환경측정계획번호
     * @return 측정항목
     * @throws Exception
     */
    @ApiOperation(value = "작업환경측정계획 결과용 상세조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "workMeasPlanNo", value = "작업환경측정계획번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/workmeasureplanresult/{workMeasPlanNo}")
    public ResponseEntity<WorkMeasurePlan> getWorkMeasurePlanResult(@PathVariable(name = "workMeasPlanNo") int workMeasPlanNo) throws Exception {
        return ResponseEntity.ok().body(this.workMeasurePlanService.getWorkMeasurePlanResult(workMeasPlanNo));
    }
}