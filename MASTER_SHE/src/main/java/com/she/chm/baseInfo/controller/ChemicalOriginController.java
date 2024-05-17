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
package com.she.chm.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.chm.baseInfo.service.ChemicalOriginService;
import com.she.chm.model.ChemicalOrigin;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/baseinfo")
public class ChemicalOriginController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalOriginService chemicalOriginService;

    /**
     * 원산지 조회
     * 
     * @param parameter
     *            검색조건
     * @return 원산지 목록
     * @throws Exception
     */
    @GetMapping("/chemicalorigins")
    public ResponseEntity<List<ChemicalOrigin>> getChemicalOrigins(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 원산지명 (KOR)
        String originNmKr = map.containsKey("originNmKr") ? map.get("originNmKr").toString() : "";

        return ResponseEntity.ok().body(chemicalOriginService.getChemicalOrigins(originNmKr, useYn));
    }

}
