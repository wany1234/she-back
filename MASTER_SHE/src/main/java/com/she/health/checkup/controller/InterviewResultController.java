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
package com.she.health.checkup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.she.health.checkup.service.InterviewResultService;
import com.she.health.model.InterviewResult;
import com.she.utils.RequestMapper;

/**
 * 문진결과 - 사용하지 않음. 문진항목 마스터 개발완료, 결과 업로드 및 조회의 경우 구현하지 않기로함.
 *
 */
@RestController
@RequestMapping("api/hea/checkup")
public class InterviewResultController {

    @Autowired
    private InterviewResultService interviewResultService;

    @Autowired
    private RequestMapper requestMapper;

    /**
     * 문진결과 조회
     * 
     * @param parameter
     *            검색조건
     * @return 문진결과 목록
     * @throws Exception
     */
    @GetMapping("/interviewresults")
    public ResponseEntity<List<InterviewResult>> getInterviewResults(@RequestParam HashMap<String, Object> parameter)
            throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String checkupYear = map.containsKey("checkupYear") ? map.get("checkupYear").toString() : "";
        int heaCheckupPlanNo = map.containsKey("heaCheckupPlanNo")
                ? Integer.parseInt(map.get("heaCheckupPlanNo").toString()) : 0;
        String retirementYn = map.containsKey("retirementYn") ? map.get("retirementYn").toString() : "";
        int[] heaCheckedOrgNos = this.requestMapper.convertObjectListAsIntArray(map.get("heaCheckedOrgNos"));
        String userNm = map.containsKey("userNm") ? map.get("userNm").toString() : "";
        String smokingYn = map.containsKey("smokingYn") ? map.get("smokingYn").toString() : "";
        String drinkingYn = map.containsKey("drinkingYn") ? map.get("drinkingYn").toString() : "";

        List<InterviewResult> interviewResults = this.interviewResultService.getInterviewResults(checkupYear,
                heaCheckupPlanNo, retirementYn, userNm, heaCheckedOrgNos, smokingYn, drinkingYn);

        return ResponseEntity.ok().body(interviewResults);
    }

    /**
     * 문진결과 상세 조회
     * 
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param userId
     *            사용자아이디
     * @return 문진결과
     * @throws Exception
     */
    @GetMapping("/interviewresult/{heaCheckupPlanNo}/{userId}")
    public ResponseEntity<InterviewResult> getInterviewResult(@PathVariable int heaCheckupPlanNo,
            @PathVariable String userId) throws Exception {
        InterviewResult interviewResult = this.interviewResultService.getInterviewResult(heaCheckupPlanNo, userId);

        return ResponseEntity.ok().body(interviewResult);
    }

    /**
     * 문진결과 신규 생성
     * 
     * @param interviewResult
     *            문진결과
     * @return 생성행수
     * @throws Exception
     */
    @PostMapping("/interviewresult")
    public ResponseEntity<Integer> createInterviewResult(InterviewResult interviewResult) throws Exception {
        return ResponseEntity.ok().body(this.interviewResultService.createInterviewResult(interviewResult));
    }

    /**
     * 문진결과 수정
     * 
     * @param interviewResult
     *            문진결과
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/interviewresult")
    public ResponseEntity<Integer> updateInterviewResult(InterviewResult interviewResult) throws Exception {
        return ResponseEntity.ok().body(this.interviewResultService.updateInterviewResult(interviewResult));
    }

    /**
     * 문진결과 삭제
     * 
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param userId
     *            사용자아이디
     * @param heaInteItemCd
     *            문진항목코드
     * @return 삭제행수
     * @throws Exception
     */
    @DeleteMapping("/interviewresult/{heaCheckupPlanNo}/{userId}/{heaInteItemCd}")
    public ResponseEntity<Integer> deleteInterviewResult(@PathVariable int heaCheckupPlanNo,
            @PathVariable String userId, @PathVariable String heaInteItemCd) throws Exception {
        return ResponseEntity.ok()
                .body(this.interviewResultService.deleteInterviewResult(heaCheckupPlanNo, userId, heaInteItemCd));
    }

    /**
     * 건강검진결과 엑셀업로드
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param upfile
     *            업로드엑셀파일
     * @return 업로드결과
     * @throws Exception
     */
    @PostMapping(path = "/uploadexcelinterviewresult")
    public ResponseEntity<Map<String, Object>> uploadExcelInterviewResult(@RequestParam("file") MultipartFile upfile,
            @RequestParam("heaCheckupPlanNo") int heaCheckupPlanNo) throws Exception {
        Map<String, Object> resultMap = this.interviewResultService.uploadExcelCheckupResult(heaCheckupPlanNo, upfile);

        return ResponseEntity.ok().body(resultMap);
    }
}
