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

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.she.manage.model.AlarmLog;
import com.she.manage.model.BatchLog;
import com.she.manage.model.CrudLog;
import com.she.manage.model.ErrorLog;
import com.she.manage.model.ImpoAction;
import com.she.manage.model.MailLog;
import com.she.manage.model.MenuLog;
import com.she.manage.service.LogListService;
import com.she.security.model.LoginLog;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/manage/log")
public class LogListController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private LogListService logService;

    @GetMapping("/loginlog")
    public ResponseEntity<List<LoginLog>> getLoginLogList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString().replace("-", "") : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString().replace("-", "") : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<LoginLog> loginLog = logService.getLoginLogList(fromDt, toDt, deptCd, plantCd);

        return ResponseEntity.ok().body(loginLog);
    }

    @GetMapping("/errorlog")
    public ResponseEntity<List<ErrorLog>> getErrorLogList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString().replace("-", "") : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString().replace("-", "") : "";

        List<ErrorLog> errorLog = logService.getErrorLogList(fromDt, toDt);

        return ResponseEntity.ok().body(errorLog);
    }

    @GetMapping("/errorlogdetail/{logNo}")
    public ResponseEntity<ErrorLog> getErrorLogDetail(@PathVariable("logNo") int logNo) throws Exception {
        ErrorLog errorLog = logService.getErrorLogDetail(logNo);
        return ResponseEntity.ok().body(errorLog);
    }

    @GetMapping("/maillog")
    public ResponseEntity<List<MailLog>> getMailLogs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString() : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString() : "";
        String keyword = map.containsKey("keyword") ? map.get("keyword").toString() : "";

        return ResponseEntity.ok().body(logService.getMailLogs(fromDt, toDt, keyword));
    }

    @PutMapping("/try/maillog")
    public ResponseEntity<HashMap<String, Integer>> resendMailLog(@RequestBody List<MailLog> mailLogs) throws Exception {
        return ResponseEntity.ok().body(this.logService.resendMailLog(mailLogs));
    }

    @GetMapping("/smslog")
    public ResponseEntity<List<AlarmLog>> getSmsLogs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString() : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString() : "";
        String word = map.containsKey("word") ? map.get("word").toString() : "";

        return ResponseEntity.ok().body(logService.getSmsLogs(fromDt, toDt, word));
    }

    @PutMapping("/try/smslog")
    public ResponseEntity<HashMap<String, Integer>> resendSmsLog(@RequestBody List<AlarmLog> alarmLogs) throws Exception {
        return ResponseEntity.ok().body(this.logService.resendSmsLog(alarmLogs));
    }

    @GetMapping("/batchlogs")
    public ResponseEntity<List<BatchLog>> getBatchLogs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString().replace("-", "") : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString().replace("-", "") : "";

        return ResponseEntity.ok().body(logService.getBatchLogs(fromDt, toDt));
    }

    @PostMapping("/menulog")
    public void insertMenuLog(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String menuId = map.containsKey("menuId") ? map.get("menuId").toString() : "";
        String menuNm = map.containsKey("menuNm") ? map.get("menuNm").toString() : "";
        String menuPath = map.containsKey("menuPath") ? map.get("menuPath").toString() : "";
        String url = map.containsKey("url") ? map.get("url").toString() : "";
        String createUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";

        logService.insertMenuLog(menuId, menuNm, menuPath, url, createUserId);
    }

    @GetMapping("/menulog")
    public ResponseEntity<List<MenuLog>> getMenuLog(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String menuNm = map.containsKey("menuNm") ? map.get("menuNm").toString() : "";
        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString() : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString() : "";

        return ResponseEntity.ok().body(logService.getMenuLog(menuNm, fromDt, toDt));
    }

    @GetMapping("/crudlog")
    public void getImpoConform(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String crudCd = map.containsKey("crudCd") ? map.get("crudCd").toString() : "";
        String servletPath = map.containsKey("servletPath") ? map.get("servletPath").toString() : "";
        String deleteUrl = map.containsKey("deleteUrl") ? map.get("deleteUrl").toString() : "";
        servletPath = servletPath.replace(deleteUrl, "");
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";

        ImpoAction impoAction = logService.getImpoConform(servletPath, crudCd);

        if (impoAction != null) {
            String crudNm = impoAction.getCrudNm();
            String crudDesc = impoAction.getCrudDesc();

            String[] data = this.requestMapper.convertObjectListAsStringArray((map.get("data")));

            String option = "";
            if (data != null) {
                for (String temp : data) {
                    option = option + "【" + temp + "】";
                }
            }
            logService.insertCrudLog(crudCd, crudNm, crudDesc, userId, servletPath, option);
        }
    }

    @GetMapping("/crudloglist")
    public ResponseEntity<List<CrudLog>> getCrudLog(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String crudNm = map.containsKey("crudNm") ? map.get("crudNm").toString() : "";
        String fromDt = map.containsKey("fromDt") ? map.get("fromDt").toString() : "";
        String toDt = map.containsKey("toDt") ? map.get("toDt").toString() : "";

        return ResponseEntity.ok().body(logService.getCrudLog(crudNm, fromDt, toDt));
    }

    @GetMapping("/impoaction")
    public ResponseEntity<List<ImpoAction>> getImpoActionList() throws Exception {
        return ResponseEntity.ok().body(logService.getImpoActionList());
    }

    @PostMapping("/impoaction")
    public void insertImpoAction(@RequestBody HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String servletPath = map.containsKey("servletPath") ? map.get("servletPath").toString() : "";
        String crudCd = map.containsKey("crudCd") ? map.get("crudCd").toString() : "";
        String crudNm = map.containsKey("crudNm") ? map.get("crudNm").toString() : "";
        String crudDesc = map.containsKey("crudDesc") ? map.get("crudDesc").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        logService.insertImpoAction(servletPath, crudCd, crudNm, crudDesc, useYn);
    }

    @PutMapping("/impoaction")
    public void updateImpoAction(@RequestBody HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String servletPath = map.containsKey("servletPath") ? map.get("servletPath").toString() : "";
        String crudCd = map.containsKey("crudCd") ? map.get("crudCd").toString() : "";
        String crudNm = map.containsKey("crudNm") ? map.get("crudNm").toString() : "";
        String crudDesc = map.containsKey("crudDesc") ? map.get("crudDesc").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        logService.updateImpoAction(servletPath, crudCd, crudNm, crudDesc, useYn);
    }

}
