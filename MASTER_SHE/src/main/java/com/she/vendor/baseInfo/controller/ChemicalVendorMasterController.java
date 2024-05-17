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
package com.she.vendor.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.utils.RequestMapper;
import com.she.vendor.baseInfo.service.ChemicalVendorMasterService;
import com.she.vendor.model.ChemicalVendorMaster;

@RestController
@RequestMapping("api/chm/baseinfo")
public class ChemicalVendorMasterController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ChemicalVendorMasterService chemicalVendorMasterService;

    /**
     * 업체 조회
     * 
     * @param parameter
     *            검색조건
     * @return 업체 목록
     * @throws Exception
     */
    @GetMapping("/chemicalvendormasters")
    public ResponseEntity<List<ChemicalVendorMaster>> getChemicalVendorMasters(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 원산지
        String originCd = map.containsKey("originCd") ? map.get("originCd").toString() : "";
        // 업체명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        // 업체분류
        String vendorTypeCd = map.containsKey("vendorTypeCd") ? map.get("vendorTypeCd").toString() : "";

        return ResponseEntity.ok().body(chemicalVendorMasterService.getChemicalVendorMasters(originCd, vendorNm, vendorTypeCd, useYn));
    }

}
