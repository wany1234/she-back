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
package com.she.mgt.mgtLaw.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.mgt.mgtLaw.service.SheLawMakingService;
import com.she.mgt.model.MgtLawMaking;
import com.she.utils.RequestMapper;

//
@RestController
@RequestMapping("api/mgt/mgtlaw/shelawmaking")
public class SheLawMakingController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SheLawMakingService sheLawMakingService;

    /***
     * 입법예고법규 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 입법예고 목록
     * @throws Exception
     */
    @GetMapping("/lawmakinglist")
    public ResponseEntity<List<MgtLawMaking>> getlawmakingList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String stYd = "";
        String edYd = "";

        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        if (period != null && period.length == 2) {
            stYd = period[0];
            edYd = period[1];
        }

        String isNm = map.containsKey("isNm") ? map.get("isNm").toString() : "";
        // like 검색 시 대괄호([])가 와일드카드 문자로 인식되어 제대로 검색이 안됨.
        // '[' -> '[[]' 으로 치환하여 검색이 이루어지도록 함.
        isNm = isNm.replace("[", "[[]").trim();

        String mgrGovCd = map.containsKey("mgrGovCd") ? map.get("mgrGovCd").toString() : "";

        return ResponseEntity.ok().body(sheLawMakingService.getLawMakingList(isNm, mgrGovCd, stYd, edYd));
    }
}
