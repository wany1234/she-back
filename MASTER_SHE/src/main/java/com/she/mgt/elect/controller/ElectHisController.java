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
package com.she.mgt.elect.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.mgt.elect.service.ElectHisService;
import com.she.mgt.model.ElectHis;
import com.she.mgt.model.ElectTitlLcn;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/elect")
public class ElectHisController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ElectHisService electHisService;

    /**
     * 선해임 조회
     * 
     * @param parameter
     *            검색조건
     * @return 선해임 목록
     * @throws Exception
     */
    @GetMapping("/electhises")
    public ResponseEntity<List<ElectHis>> getElectHises(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 선해임일자
        String[] lcnGetPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String lcnGetStartDt = "";
        String lcnGetEndDt = "";
        if (lcnGetPeriod != null && lcnGetPeriod.length == 2) {
            lcnGetStartDt = lcnGetPeriod[0];
            lcnGetEndDt = lcnGetPeriod[1];
        }

        // 구분
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 선임자
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";

        String testEndDt = map.containsKey("testEndDt") ? map.get("testEndDt").toString() : "";
        // 선해임명번호
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? Integer.parseInt("".equals(map.get("safElectTitlNo").toString()) ? "0" : map.get("safElectTitlNo").toString()) : 0;
        // 재직 구분
        String employmentYn = map.containsKey("employmentYn") ? map.get("employmentYn").toString() : "";

        return ResponseEntity.ok().body(electHisService.getElectHises(lcnGetStartDt, lcnGetEndDt, plantCd, userNm, safElectTitlNo, testEndDt, employmentYn, defaultParam));
    }

    // 법정선임자 현황 통계 조회

    @GetMapping("/electStatusLists")
    public ResponseEntity<List<HashMap<String, Object>>> getElectStatus(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : ""; // 사업장코드
        int safElectTitlNo = convertedParameter.containsKey("safElectTitlNo") ? Integer.parseInt("".equals(convertedParameter.get("safElectTitlNo").toString()) ? "0" : convertedParameter.get("safElectTitlNo").toString()) : 0;
        return ResponseEntity.ok().body(electHisService.getElectStatus(plantCd, safElectTitlNo));
    }

    /**
     * 선해임 상세 조회
     * 
     * @param regulItmNo
     *            선해임번호
     * @return 선해임
     * @throws Exception
     */
    @GetMapping("/electhis/{safElectHisNo}")
    public ResponseEntity<ElectHis> getElectHis(@PathVariable(name = "safElectHisNo") int safElectHisNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.electHisService.getElectHis(safElectHisNo, defaultParam));
    }

    /**
     * 선해임 이력 조회
     * 
     * @param regulItmNo
     *            선해임번호
     * @return 선해임
     * @throws Exception
     */
    @GetMapping("/electhirevs/{safElectHisNo}")
    public ResponseEntity<List<ElectHis>> getElectHisesRevs(@PathVariable(name = "safElectHisNo") int safElectHisNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.electHisService.getElectHisesRevs(safElectHisNo, defaultParam));
    }

    /**
     * 선해임 신규등록
     * 
     * @param electHis
     *            선해임
     * @return 선해임 코드
     * @throws Exception
     */
    @PostMapping("/electhis")
    public ResponseEntity<Integer> createElectHis(@RequestBody ElectHis electHis) throws Exception {
        return ResponseEntity.ok().body(this.electHisService.createElectHis(electHis));
    }

    /**
     * 선해임 수정
     * 
     * @param electHis
     *            선해임
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/electhis")
    public ResponseEntity<Integer> updateElectHis(@RequestBody ElectHis electHis) throws Exception {
        return ResponseEntity.ok().body(this.electHisService.updateElectHis(electHis));
    }

    /**
     * 선해임 삭제
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/electhis/{safElectHisNo}")
    public ResponseEntity<Integer> deleteElectHis(@PathVariable(name = "safElectHisNo") int safElectHisNo) throws Exception {
        return ResponseEntity.ok().body(this.electHisService.deleteElectHis(safElectHisNo));
    }

    /**
     * 선해임 개정이력 삭제
     * 
     * @param ElectHis
     * @return 생성 행 수
     * @throws Exception
     */
    @DeleteMapping("/revdelete")
    public ResponseEntity<Integer> revDeleteElectHis(@RequestBody List<ElectHis> electHis) throws Exception {
        return ResponseEntity.ok().body(this.electHisService.revDeleteElectHis(electHis));
    }

    /**
     * 선해임 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkelecthis")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckElectHis(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 선해임자 ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 선해임명번호
        int safElectTitlNo = map.containsKey("safElectTitlNo") ? Integer.parseInt("".equals(map.get("safElectTitlNo").toString()) ? "0" : map.get("safElectTitlNo").toString()) : 0;
        // 자격증번호
        int safElectLcnNo = map.containsKey("safElectLcnNo") ? Integer.parseInt("".equals(map.get("safElectLcnNo").toString()) ? "0" : map.get("safElectLcnNo").toString()) : 0;
        // 선해임번호
        int safElectHisNo = map.containsKey("safElectHisNo") ? Integer.parseInt("".equals(map.get("safElectHisNo").toString()) ? "0" : map.get("safElectHisNo").toString()) : 0;

        return ResponseEntity.ok().body(electHisService.getCheckElectHis(userId, safElectTitlNo, safElectLcnNo, safElectHisNo, defaultParam));
    }

    /**
     * 선해임 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkelecthis/name/{plantCd}/{userId}/{safElectTitlNo}")
    public ResponseEntity<Integer> countElectHisNm(@PathVariable("plantCd") String plantCd, @PathVariable("userId") String userId, @PathVariable("safElectTitlNo") String safElectTitlNo) throws Exception {
        return ResponseEntity.ok(electHisService.countElectHisNm(plantCd, userId, safElectTitlNo));
    }

    /**
     * HR 자격 조회
     * 
     * @param userId
     *            선해임자 ID
     * @param userId
     *            선해임자 이름
     * 
     * @return HR 자격결과
     * @throws Exception
     */
    @GetMapping("/hrsearchlcns")
    public ResponseEntity<Map<String, Object>> getHrElectTitlLcns(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 선해임자 ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 선해임자 명
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        // 선해임자 명
        String licenseNm = map.containsKey("licenseNm") ? map.get("licenseNm").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        int pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        int pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        List<ElectTitlLcn> list = electHisService.getHrElectTitlLcns(userId, userNm, licenseNm, plantCd, pageNumber, pageSize, orderByExpression, defaultParam);

        int totalCount = electHisService.getHrElectTitlLcnsTotSize(userId, userNm, licenseNm, plantCd);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("items", list);
        returnMap.put("count", totalCount);

        return ResponseEntity.ok().body(returnMap);
    }

}
