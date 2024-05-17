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

import com.she.manage.model.CodeMaster;
import com.she.mgt.mgtLaw.service.SheLawRevisionService;
import com.she.mgt.model.MgtLawRevision;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/mgt/mgtlaw/shelawrevision")
public class SheLawRevisionController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SheLawRevisionService sheLawRevisionService;

    /***
     * 개정법규 목록 조회
     * 
     * @param parameter
     *            검색조건
     * @return 개정법규 목록
     * @throws Exception
     */
    @GetMapping("/lawrevisionlist")
    public ResponseEntity<List<MgtLawRevision>> getLawRevisionList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String stYd = "";
        String edYd = "";

        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));

        if (period != null) {
            stYd = period[0];
            edYd = period[1];
            stYd = stYd.replace("-", "");
            edYd = edYd.replace("-", "");
        }

        String lkey = map.containsKey("lkey") ? map.get("lkey").toString() : "";

        return ResponseEntity.ok().body(sheLawRevisionService.getLawRevisionList(lkey, stYd, edYd));
    }

    /***
     * 법령 콤보 조회
     *
     * @return 법령
     * @throws Exception
     */
    @GetMapping("/lawrevisioncombo")
    public ResponseEntity<List<CodeMaster>> getLawRevisionCombo() throws Exception {
        return ResponseEntity.ok().body(sheLawRevisionService.getLawRevisionCombo());
    }

    /***
     * 법조 조회
     *
     * @return 법조
     * @throws Exception
     */
    @GetMapping("/law-jomun")
    public ResponseEntity<List<HashMap<String, Object>>> getLawJomun(@RequestParam String legiKey) throws Exception {

        return ResponseEntity.ok().body(sheLawRevisionService.getLawJomun(legiKey));
    }

}
