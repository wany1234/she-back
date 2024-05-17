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
package com.she.health.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.health.baseInfo.service.DiseaseService;
import com.she.health.model.Disease;
import com.she.utils.RequestMapper;

/**
 * 질환
 *
 */
@RestController
public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 질환 조회
     * 
     * @param parameter
     *            (질환분류코드, 질환명)
     * @return 질환 목록
     * @throws Exception
     */
    @GetMapping("/api/hea/baseinfo/diseases")
    public ResponseEntity<List<Disease>> getDiseases(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String heaDiseaseClassCd = map.containsKey("heaDiseaseClassCd") ? map.get("heaDiseaseClassCd").toString() : "";
        String heaDiseaseNm = map.containsKey("heaDiseaseNm") ? map.get("heaDiseaseNm").toString() : "";

        List<Disease> disease = diseaseService.getDiseases(heaDiseaseClassCd, heaDiseaseNm);

        return ResponseEntity.ok().body(disease);
    }

    /**
     * 질환 상세 조회
     * 
     * @param heaDiseaseCd
     *            질환코드
     * @return 질환
     * @throws Exception
     */
    @GetMapping("/api/hea/baseinfo/disease/{heaDiseaseCd}")
    public ResponseEntity<Disease> getDisease(@PathVariable("heaDiseaseCd") String heaDiseaseCd) throws Exception {
        Disease disease = diseaseService.getDisease(heaDiseaseCd);
        return ResponseEntity.ok().body(disease);
    }

    /**
     * 질환 생성
     * 
     * @param disease
     *            질환
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/api/hea/baseinfo/disease")
    public ResponseEntity<String> createDisease(@RequestBody Disease disease) throws Exception {
        return ResponseEntity.ok().body(diseaseService.createDisease(disease) > 0 ? disease.getHeaDiseaseCd() : "");
    }

    /**
     * 질환 수정
     * 
     * @param disease
     *            질환
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/api/hea/baseinfo/disease")
    public ResponseEntity<Integer> updateDisease(@RequestBody Disease disease) throws Exception {
        return ResponseEntity.ok().body(diseaseService.updateDisease(disease));
    }

}
