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
package com.she.env.waste.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.env.waste.baseInfo.service.DisposalCompanyService;
import com.she.env.waste.model.DisposalCompany;
import com.she.utils.RequestMapper;

/**
 * 폐기물 처리업체
 *
 */
@RestController("EwstDisposalCompanyController")
@RequestMapping("api/env/waste/baseinfo")
public class DisposalCompanyController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DisposalCompanyService disposalCompanyService;

    /**
     * 폐기물 처리업체 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 처리업체 목록
     * @throws Exception
     */
    @GetMapping("/disposalcompanies")
    public ResponseEntity<List<DisposalCompany>> getDisposalCompanies(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        String ewstDispoCoNm = map.containsKey("ewstDispoCoNm") ? map.get("ewstDispoCoNm").toString() : "";
        String vendorTypeCd = "CVET2";

        List<DisposalCompany> disposalCompanies = this.disposalCompanyService.getDisposalCompanies(useYn, vendorTypeCd, ewstDispoCoNm);

        return ResponseEntity.ok().body(disposalCompanies);
    }
}
