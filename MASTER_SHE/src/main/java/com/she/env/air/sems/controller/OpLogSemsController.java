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
package com.she.env.air.sems.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import com.she.env.air.sems.service.OpLogSemsService;
import com.she.utils.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.she.utils.RequestMapper;

/**
 * SEMS 조회
 *
 */
@RestController("OpLogSemsController")
@RequestMapping("api/env/air/oplog/sems")
public class OpLogSemsController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private OpLogSemsService oplogSemsService;

    @GetMapping("/sems")
    public ResponseEntity<List<LinkedHashMap<String, Object>>> getOperationStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String eairSemsRptTypeCd = map.containsKey("eairSemsRptTypeCd") ? map.get("eairSemsRptTypeCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String mainDischFacNm = map.containsKey("mainDischFacNm") ? map.get("mainDischFacNm").toString() : "";

        String[] searchPeriod = new String[2];

        if ("ESRT5".equals(eairSemsRptTypeCd) || "ESRT6".equals(eairSemsRptTypeCd)) {
            searchPeriod[0] = map.containsKey("period") ? map.get("period").toString() + "-01-01" : "";
            searchPeriod[1] = map.containsKey("period") ? map.get("period").toString() + "-12-31" : "";
        } else {
            searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
            if (searchPeriod[0].length() == 7) {
                searchPeriod[0] = searchPeriod[0] + "-01";
            }
            if (searchPeriod[1].length() == 7) {
                Calendar toDate = Calendar.getInstance();
                toDate.setTime(Methods.convertStringToDate(searchPeriod[1] + "-01"));
                searchPeriod[1] = searchPeriod[1] + "-" + Methods.leftPad(String.valueOf(toDate.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, '0');
            }
        }

        String fromYmd = "";
        String toYmd = "";

        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYmd = searchPeriod[0];
            toYmd = searchPeriod[1];
        }

        return ResponseEntity.ok().body(this.oplogSemsService.getSems(fromYmd, toYmd, plantCd, eairSemsRptTypeCd, deptCd, mainDischFacNm, defaultParam));
    }

    @GetMapping("/sems/excelDown")
    public byte[] getSemsExcelDownload(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        byte[] returnVal = null;

        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String eairSemsRptTypeCd = map.containsKey("eairSemsRptTypeCd") ? map.get("eairSemsRptTypeCd").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String mainDischFacNm = map.containsKey("mainDischFacNm") ? map.get("mainDischFacNm").toString() : "";

        String[] searchPeriod = new String[2];

        if ("ESRT5".equals(eairSemsRptTypeCd) || "ESRT6".equals(eairSemsRptTypeCd)) {
            searchPeriod[0] = map.containsKey("period") ? map.get("period").toString() + "-01-01" : "";
            searchPeriod[1] = map.containsKey("period") ? map.get("period").toString() + "-12-31" : "";
        } else {
            searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
            if (searchPeriod[0].length() == 7) {
                searchPeriod[0] = searchPeriod[0] + "-01";
            }
            if (searchPeriod[1].length() == 7) {
                Calendar toDate = Calendar.getInstance();
                toDate.setTime(Methods.convertStringToDate(searchPeriod[1] + "-01"));
                searchPeriod[1] = searchPeriod[1] + "-" + Methods.leftPad(String.valueOf(toDate.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, '0');
            }
        }

        String fromYmd = "";
        String toYmd = "";

        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYmd = searchPeriod[0];
            toYmd = searchPeriod[1];
        }
        returnVal = oplogSemsService.getSemsExcelDownload(fromYmd, toYmd, plantCd, eairSemsRptTypeCd, deptCd, mainDischFacNm, defaultParam);
        return returnVal;
    }

    //

}
