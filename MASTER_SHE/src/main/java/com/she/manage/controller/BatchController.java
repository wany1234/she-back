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

import com.she.common.model.DefaultParam;
import com.she.manage.model.Batch;
import com.she.manage.service.BatchService;
import com.she.utils.RequestMapper;
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

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/manage/log")
public class BatchController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private BatchService batchService;

    @GetMapping("/batchs")
    public ResponseEntity<List<Batch>> getBatchs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String batchCd = map.containsKey("batchCd") ? map.get("batchCd").toString() : "";
        String batchNm = map.containsKey("batchNm") ? map.get("batchNm").toString() : "";
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(batchService.getBatchs(batchCd, batchNm, useYn, defaultParam));
    }

    @GetMapping("/batch/{batchCd}")
    public ResponseEntity<Batch> getBatch(@PathVariable("batchCd") String batchCd) throws Exception {
        return ResponseEntity.ok().body(batchService.getBatch(batchCd));
    }

    @GetMapping("/batch/check")
    public ResponseEntity<Integer> getBatchCheck(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 알람명
        String batchCd = map.containsKey("batchCd") ? map.get("batchCd").toString() : "";
        // 알람명
        String batchCdSave = map.containsKey("batchCdSave") ? map.get("batchCdSave").toString() : "";

        return ResponseEntity.ok().body(batchService.getBatchCheck(batchCd, batchCdSave));
    }

    @PostMapping("/batch")
    public ResponseEntity<String> insertBatch(@RequestBody Batch batch) throws Exception {
        return ResponseEntity.ok().body(batchService.insertBatch(batch));
    }

    @PutMapping("/batch")
    public ResponseEntity<Integer> updateBatch(@RequestBody Batch batch) throws Exception {
        return ResponseEntity.ok().body(batchService.updateBatch(batch));
    }

}
