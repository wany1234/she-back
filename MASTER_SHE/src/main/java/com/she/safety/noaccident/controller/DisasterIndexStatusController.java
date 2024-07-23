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
package com.she.safety.noaccident.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.she.safety.model.DisasterIndexAccident;
import com.she.safety.model.DisasterIndexStatus;
import com.she.safety.model.NoAccident;
import com.she.safety.noaccident.service.DisasterIndexStatusService;
import com.she.safety.noaccident.service.NoAccidentService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/saf/noaccident/")
public class DisasterIndexStatusController {
    @Autowired
    private RequestMapper requestMapper;
    
    @Autowired
    private DisasterIndexStatusService disasterIndexStatusService;

    /**
     * 종합재해지수 현황 목록 조회
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/disasterindexstatus")
    public ResponseEntity<List<DisasterIndexStatus>> getDisasterIndexStatusList(@RequestParam HashMap<String, Object> parameter) throws Exception{
    	HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
    	
    	String year = map.containsKey("year") ? map.get("year").toString() : "";
        String month = map.containsKey("month") ? map.get("month").toString() : "";
        
        return ResponseEntity.ok().body(disasterIndexStatusService.getDisasterIndexStatusList(year, month));
    }
    
    /**
     * 사고목록 조회
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/disasteraccident")
    public ResponseEntity<List<DisasterIndexAccident>> getAccidentList(@RequestParam HashMap<String, Object> parameter) throws Exception{
    	HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
    	
    	String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
    	String year = map.containsKey("year") ? map.get("year").toString() : "";
        String month = map.containsKey("month") ? map.get("month").toString() : "";
        
        return ResponseEntity.ok().body(disasterIndexStatusService.getAccidentList(plantCd, year, month));
    }
}
