package com.she.env.gas.baseInfo.controller;

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

import com.she.env.gas.baseInfo.service.ActDataMasterService;
import com.she.env.gas.model.ActDataMaster;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;

@RestController
@Api(value = "/api/env", description = "활동자료분류")
public class ActDataMasterController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ActDataMasterService service;

    /**
     * 자료활동 검색조회
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/activity/data/masters")
    public ResponseEntity<List<ActDataMaster>> getActDataMasters(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String actClsCd = map.containsKey("actClsCd") ? map.get("actClsCd").toString() : "";

        String actDataCd = map.containsKey("actDataCd") ? map.get("actDataCd").toString() : "";

        String dataLvlCd = map.containsKey("dataLvlCd") ? map.get("dataLvlCd").toString() : "";

        String pActDataCd = map.containsKey("pActDataCd") ? map.get("pActDataCd").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(service.getActDataMasters(actClsCd, actDataCd, dataLvlCd, pActDataCd, useYn));
    }

    /**
     * 자료활동 상세조회
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/activity/data/master/{actDataCd}")
    public ResponseEntity<List<ActDataMaster>> getActDataMaster(@PathVariable("actDataCd") String actDataCd) throws Exception {
        return ResponseEntity.ok(service.getActDataMaster(actDataCd));
    }

    /**
     * 자료활동 등록
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("/api/env/gas/activity/data/master")
    public ResponseEntity<String> insertActDataMaster(@RequestBody ActDataMaster actDataMaster) throws Exception {
        return ResponseEntity.ok().body(this.service.insertActDataMaster(actDataMaster));
    }

    /**
     * 자료활동 수정
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @PutMapping("/api/env/gas/activity/data/master")
    public ResponseEntity<String> updateActDataMaster(@RequestBody ActDataMaster actDataMaster) throws Exception {
        return ResponseEntity.ok().body(this.service.updateActDataMaster(actDataMaster));
    }

    /**
     * 자료활동 중분류대분류 조회
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/activity/data/master/parent")
    public ResponseEntity<List<ActDataMaster>> getDataCategoryParents(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String actClsCd = map.containsKey("actClsCd") ? map.get("actClsCd").toString() : "";

        String actDataNm = map.containsKey("actDataNm") ? map.get("actDataNm").toString() : "";

        return ResponseEntity.ok(service.getDataCategoryParents(actDataNm, actClsCd));
    }

    /**
     * 자료활동 중복검사
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/activity/data/master/count/{actDataCd}")
    public ResponseEntity<Integer> countDataCategory(@PathVariable("actDataCd") String actDataCd) throws Exception {
        return ResponseEntity.ok(service.countDataCategory(actDataCd));
    }

    /**
     * 자료활동 분류조회
     *
     * @param
     * @return 등록 행 수
     * @throws Exception
     */
    @GetMapping("/api/env/gas/activity/data/master/items/{dataLvlCd}/{pActDataCd}/{useYn}")
    public ResponseEntity<List<ActDataMaster>> getDataCategoryItems(@PathVariable("dataLvlCd") String dataLvlCd, @PathVariable("pActDataCd") String pActDataCd, @PathVariable("useYn") String useYn) throws Exception {
        return ResponseEntity.ok(service.getDataCategoryItems(dataLvlCd, pActDataCd, useYn));
    }
}
