package com.she.mgt.schedule.controller;

import java.util.Calendar;
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

import com.she.mgt.model.ScehduleManagement;
import com.she.mgt.schedule.service.ScheduleManagementService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/mgt")
@Api(value = "/api/mgt", description = "일정 서비스")
public class ScheduleManagementController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ScheduleManagementService scheduleManagementService;

    /**
     * 일정 조회
     * 
     * @param parameter
     *            검색조건
     * @return 일정 목록
     * @throws Exception
     */
    @ApiOperation(value = "일정 조회[MGT15001]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "period", value = "기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "사업장코드", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "mgtCalTypeCd", value = "업무유형", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "title", value = "제목", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createUserId", value = "작성자", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "useYn", value = "사용여부", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/schedulemanagements")
    public ResponseEntity<List<ScehduleManagement>> getScehduleManagements(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        Calendar cal = Calendar.getInstance();

        // 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startDt = "";
        String endDt = ""; // 신고일시작일
        if (period != null && period.length == 2) {
            startDt = period[0];
            endDt = period[1];
        }
        // 기간이 연간캘린더 값으로 넘어올때 말일셋팅로직
        if (startDt.length() == 7) {
            startDt += "-01";
        }
        if (endDt.length() == 7) {
            int endYear = Integer.parseInt(endDt.substring(0, 4));
            int endMonth = Integer.parseInt(endDt.substring(5, 7));
            cal.set(endYear, endMonth - 1, 1);
            endDt += '-';
            endDt += cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 업무유형
        String mgtCalTypeCd = map.containsKey("mgtCalTypeCd") ? map.get("mgtCalTypeCd").toString() : "";
        // 제목
        String title = map.containsKey("title") ? map.get("title").toString() : "";
        // 작성자
        String createUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(scheduleManagementService.getScehduleManagements(startDt, endDt, plantCd, mgtCalTypeCd, title, createUserId, useYn));

    }

    /**
     * 일정 상세 조회
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 일정
     * @throws Exception
     */
    @ApiOperation(value = "일정 상세조회[MGT15002]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtCalendarNo", value = "일정번호", required = false, dataType = "int", paramType = "path") })
    @GetMapping("/schedulemanagement/{mgtCalendarNo}")
    public ResponseEntity<ScehduleManagement> getScehduleManagement(@PathVariable(name = "mgtCalendarNo") int mgtCalendarNo) throws Exception {
        return ResponseEntity.ok().body(this.scheduleManagementService.getScehduleManagement(mgtCalendarNo));
    }

    /**
     * 일정 신규등록
     * 
     * @param ScehduleManagement
     *            일정
     * @return 일정 코드
     * @throws Exception
     */

    @ApiOperation(value = "일정등록[MGT15003]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "ScehduleManagement", value = "일정정보", required = false, dataType = "scehduleManagement", paramType = "body") })
    @PostMapping("/schedulemanagement")
    public ResponseEntity<Integer> createScehduleManagement(@RequestBody ScehduleManagement scehduleManagement) throws Exception {
        return ResponseEntity.ok().body(this.scheduleManagementService.createScehduleManagement(scehduleManagement));
    }

    /**
     * 일정 수정
     * 
     * @param ScehduleManagement
     *            일정
     * @return 수정 행 수
     * @throws Exception
     */
    @ApiOperation(value = "일정수정[MGT15004]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "ScehduleManagement", value = "일정정보", required = false, dataType = "ScehduleManagement", paramType = "body") })
    @PutMapping("/schedulemanagement")
    public ResponseEntity<Integer> updateScehduleManagement(@RequestBody ScehduleManagement scehduleManagement) throws Exception {
        return ResponseEntity.ok().body(this.scheduleManagementService.updateScehduleManagement(scehduleManagement));
    }

    /**
     * 일정 상세 조회
     * 
     * @param mgtCalendarNo
     *            일정번호
     * @return 일정
     * @throws Exception
     */
    @ApiOperation(value = "일정 삭제[MGT15005]", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "mgtCalendarNo", value = "일정번호", required = false, dataType = "int", paramType = "path") })
    @DeleteMapping("/schedulemanagement/{mgtCalendarNo}")
    public ResponseEntity<Integer> deleteScehduleManagement(@PathVariable(name = "mgtCalendarNo") int mgtCalendarNo) throws Exception {
        return ResponseEntity.ok().body(this.scheduleManagementService.deleteScehduleManagement(mgtCalendarNo));
    }

}
