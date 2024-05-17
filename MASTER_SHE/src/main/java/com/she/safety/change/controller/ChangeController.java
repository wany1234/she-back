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
package com.she.safety.change.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.Change;
import com.she.safety.model.ChangeCommi;
import com.she.safety.model.ChangeDashboard;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/saf/change")
@Api(value = "/api/saf/change/", description = "변경관리 서비스")
public class ChangeController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChangeService changeService;

    /**
     * 변경관리 조회
     *
     * @param parameter
     *            검색조건
     * @return 변경관리 목록
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 조회[SAF05001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "plantCd", value = "사업장", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "rqstPeriod", value = "요청일", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "rqstDeptCd", value = "요청부서", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "chngStepCd", value = "진행단계", required = false, dataType = "array", paramType = "query"),
            @ApiImplicitParam(name = "chngAttCd", value = "변경종류", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "bizNm", value = "사업명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/changes")
    public ResponseEntity<List<Change>> getChanges(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 요청일
        String[] rqstPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("rqstPeriod"));
        String rqstStartDt = "";
        String rqstEndDt = "";
        if (rqstPeriod != null && rqstPeriod.length == 2) {
            rqstStartDt = rqstPeriod[0];
            rqstEndDt = rqstPeriod[1];
        }
        // 요청부서
        String rqstDeptCd = map.containsKey("rqstDeptCd") ? map.get("rqstDeptCd").toString() : "";
        // 하위부서 포함여부
        String rqstDeptSubYn = map.containsKey("rqstDeptSubYn") ? map.get("rqstDeptSubYn").toString() : "Y";

        // 진행단계
        String chngStepCd = map.containsKey("chngStepCd") ? map.get("chngStepCd").toString() : "";
        // 변경종류
        String chngAttCd = map.containsKey("chngAttCd") ? map.get("chngAttCd").toString() : "";
        // 사업명
        String bizNm = map.containsKey("bizNm") ? map.get("bizNm").toString() : "";
        // 진행업무 체크
        String chngRefWorkCd = map.containsKey("chngRefWorkCd") ? map.get("chngRefWorkCd").toString() : "";

        return ResponseEntity.ok().body(changeService.getChanges(plantCd, rqstStartDt, rqstEndDt, rqstDeptCd, rqstDeptSubYn, chngStepCd, chngAttCd, bizNm, chngRefWorkCd, defaultParam));
    }

    /**
     * 변경관리 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 상세조회[SAF05002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safChngNo", value = "변경관리번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/change/{safChngNo}")
    public ResponseEntity<Change> getChange(@PathVariable(name = "safChngNo") int safChngNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.changeService.getChange(safChngNo, defaultParam));
    }

    /**
     * 변경관리 default 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리
     * @throws Exception
     */
    @ApiOperation(value = "변경관리  default조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({})
    @GetMapping("/default/change")
    public ResponseEntity<Change> getChange(@ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.changeService.getDefaultChange(defaultParam));
    }

    /**
     * 변경관리 신규등록
     *
     * @param change
     *            변경관리
     * @return 변경관리 코드
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 신규등록[SAF05003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "change", value = "변경관리", required = false, dataType = "Change", paramType = "body") })
    @PostMapping("/change")
    public ResponseEntity<HashMap<String, Object>> createChange(@RequestBody Change change) throws Exception {
        return ResponseEntity.ok().body(this.changeService.createChange(change));
    }

    /**
     * 변경관리 수정
     *
     * @param change
     *            변경관리
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 수정[SAF05004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "change", value = "변경관리", required = false, dataType = "Change", paramType = "body") })
    @PutMapping("/change")
    public ResponseEntity<HashMap<String, Object>> updateChange(@RequestBody Change change) throws Exception {
        return ResponseEntity.ok().body(this.changeService.updateChange(change));
    }

    /**
     * 변경관리 삭제
     *
     * @param regulItmNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 삭제[SAF05005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "safChngNo", value = "변경관리번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("/change/{safChngNo}")
    public ResponseEntity<Integer> deleteChange(@PathVariable(name = "safChngNo") int safChngNo) throws Exception {
        return ResponseEntity.ok().body(this.changeService.deleteChange(safChngNo));
    }

    /**
     * 변경관리위원회 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리위원회
     * @throws Exception
     */
    @GetMapping("/changecommi/{safChngNo}")
    public ResponseEntity<ChangeCommi> getChangeCommi(@PathVariable(name = "safChngNo") int safChngNo) throws Exception {
        return ResponseEntity.ok().body(this.changeService.getChangeCommi(safChngNo));
    }

    /**
     * 진행관리 조회
     *
     * @param parameter
     *            검색조건
     * @return 진행관리 뷰 목록
     * @throws Exception
     */
    @ApiOperation(value = "진행관리 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/changerefwork")
    public ResponseEntity<List<ChangeRefWork>> getChangeRefWorks(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        int safChngNo = map.containsKey("safChngNo") ? Integer.parseInt("".equals(map.get("safChngNo").toString()) ? "0" : map.get("safChngNo").toString()) : 0;
        String chngRefWorkCd = map.containsKey("chngRefWorkCd") ? map.get("chngRefWorkCd").toString() : "";

        return ResponseEntity.ok().body(changeService.getChangeRefWorks(safChngNo, chngRefWorkCd, defaultParam));
    }

    /**
     * 변경관리 완료
     *
     * @param change
     *            변경관리
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 완료", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "change", value = "변경관리", required = false, dataType = "Change", paramType = "body") })
    @PutMapping("/change/step/4")
    public ResponseEntity<Integer> completeChange(@RequestBody Change change) throws Exception {
        return ResponseEntity.ok().body(this.changeService.completeChange(change));
    }

    /**
     * 변경관리 dashboard 조회
     *
     * @param parameter
     *            검색조건
     * @return 변경관리 목록
     * @throws Exception
     */
    @ApiOperation(value = "변경관리 dashboard 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/change/dashboad")
    public ResponseEntity<List<ChangeDashboard>> getChangeDashboad(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 요청일
        String[] rqstPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("rqstPeriod"));
        String rqstStartDt = "";
        String rqstEndDt = "";
        if (rqstPeriod != null && rqstPeriod.length == 2) {
            rqstStartDt = rqstPeriod[0];
            rqstEndDt = rqstPeriod[1];
        }
        // 요청부서
        String rqstDeptCd = map.containsKey("rqstDeptCd") ? map.get("rqstDeptCd").toString() : "";
        // 진행단계
        String chngStepCd = map.containsKey("chngStepCd") ? map.get("chngStepCd").toString() : "";
        // 변경종류
        String lvlCd = map.containsKey("lvlCd") ? map.get("lvlCd").toString() : "";
        // 사업명
        String bizNm = map.containsKey("bizNm") ? map.get("bizNm").toString() : "";

        return ResponseEntity.ok().body(changeService.getChangeDashboad(plantCd, rqstStartDt, rqstEndDt, rqstDeptCd, chngStepCd, lvlCd, bizNm, defaultParam));
    }

}
