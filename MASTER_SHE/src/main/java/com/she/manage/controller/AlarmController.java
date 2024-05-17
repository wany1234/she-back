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
package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.Alarm;
import com.she.manage.service.AlarmService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/manage")
public class AlarmController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private AlarmService alarmService;

    @GetMapping("/alarms")
    public ResponseEntity<List<Alarm>> getAlarms(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 알람방식
        String alarmType = map.containsKey("alarmType") ? map.get("alarmType").toString() : "";
        // 알람명
        String alarmNm = map.containsKey("alarmNm") ? map.get("alarmNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(alarmService.getAlarms(alarmType, alarmNm, useYn));
    }

    @GetMapping("/alarm/{alarmNo}")
    public Alarm getAlarm(@PathVariable int alarmNo) throws Exception {
        return alarmService.getAlarm(alarmNo);
    }

    @GetMapping("/alarm/check")
    public ResponseEntity<Integer> getAlarmCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 알람번호
        int alarmNo = map.containsKey("alarmNo") ? Integer.parseInt("".equals(map.get("alarmNo").toString()) ? "0" : map.get("alarmNo").toString()) : 0;
        // 알람명
        String alarmNm = map.containsKey("alarmNm") ? map.get("alarmNm").toString() : "";

        return ResponseEntity.ok().body(alarmService.getAlarmCheck(alarmNo, alarmNm));
    }

    @PostMapping("/alarm")
    public ResponseEntity<Integer> insertAlarm(@RequestBody Alarm alarm) throws Exception {
        return ResponseEntity.ok().body(alarmService.insertAlarm(alarm));
    }

    @PutMapping("/alarm")
    public ResponseEntity<Integer> updateAlarm(@RequestBody Alarm alarm) throws Exception {
        return ResponseEntity.ok().body(alarmService.updateAlarm(alarm));
    }

}
