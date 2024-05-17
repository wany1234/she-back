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
package com.she.psm.processFacility.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.she.chm.model.Chemprod;
import com.she.psm.processFacility.service.FacilityChemprodService;
import com.she.safety.model.FacilityChemprod;
import com.she.utils.RequestMapper;

/**
 * 설비별 취급물질
 */
@RestController
@RequestMapping("api/psm/chemprodchem")
public class FacilityChemprodController {
    @Autowired
    private FacilityChemprodService facilityChemprodService;

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 유해위험요인 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비별 취급물질 목록
     * @throws Exception
     */
    @GetMapping("riskHazards")
    public ResponseEntity<List<Chemprod>> getRiskChemprodchems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 취급물질 한글명
        String chemProdNmKr = map.containsKey("chemProdNmKr") ? map.get("chemProdNmKr").toString() : "";
        // 취급물질 한글명
        String casNo = map.containsKey("casNo") ? map.get("casNo").toString() : "";
        // 취금물질번호
        int[] saveChemprodNos = this.requestMapper.convertObjectListAsIntArray(map.get("saveChemprodNos"));

        List<Chemprod> chemProds = facilityChemprodService.getRiskChemprodchems(plantCd, chemProdNmKr, casNo, saveChemprodNos);
        return ResponseEntity.ok().body(chemProds);
    }

    /**
     * 설비별 취급물질 조회
     *
     * @param parameter
     *            검색조건
     * @return 설비별 취급물질 목록
     * @throws Exception
     */
    @GetMapping("facilitychemprods")
    public ResponseEntity<List<FacilityChemprod>> getFacilityChemprods(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 취급물질명
        String chemProdNmKr = map.containsKey("chemProdNmKr") ? map.get("chemProdNmKr").toString() : "";
        // 설비 코드
        String safFacilityCd = map.containsKey("safFacilityCd") ? map.get("safFacilityCd").toString() : "";
        // 취금물질번호
        // int[] chemProdNos =
        // this.requestMapper.convertObjectListAsIntArray(map.get("chemProdNos"));
        // int chemProdNo = map.containsKey("chemProdNo") ?
        // Integer.parseInt("".equals(map.get("chemProdNo").toString()) ? "0" :
        // map.get("chemProdNo").toString()) : 0;
        // 설비별 장치번호
        int equipmentNo = map.containsKey("equipmentNo") ? Integer.parseInt("".equals(map.get("equipmentNo").toString()) ? "-1" : map.get("equipmentNo").toString()) : -1;

        List<FacilityChemprod> facilityChemprods = facilityChemprodService.getFacilityChemprods(plantCd, chemProdNmKr, safFacilityCd, equipmentNo);
        return ResponseEntity.ok().body(facilityChemprods);
    }

    /**
     * 유해위험물질 출력
     *
     * @param plantCd
     * @return
     * @throws Exception
     */
    @GetMapping("print/{plantCd}/{plantNm}")
    public @ResponseBody byte[] getRiskHazardPrint(@PathVariable("plantCd") String plantCd, @PathVariable("plantNm") String plantNm) throws Exception {

        File file = facilityChemprodService.getRiskHazardPrint(plantCd, plantNm);

        if (file.exists() && file.isFile() && file != null) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }

    /**
     * 유해위험물질(excel) 출력
     *
     * @param plantCd
     * @return
     * @throws Exception
     */
    @GetMapping("excel/print/{plantCd}/{plantNm}")
    public @ResponseBody byte[] getRiskHazardExcelPrint(@PathVariable("plantCd") String plantCd, @PathVariable("plantNm") String plantNm) throws Exception {

        File file = facilityChemprodService.getRiskHazardExcelPrint(plantCd, plantNm);

        if (file.exists() && file.isFile() && file != null) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes(StandardCharsets.UTF_8);
        } else {
            return null;
        }
    }
}
