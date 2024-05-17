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

package com.she.safety.spe.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.manage.model.CodeMaster;
import com.she.safety.model.Spe;
import com.she.safety.model.SpeGiDtl;
import com.she.safety.model.SpeRqstGive;
import com.she.safety.spe.service.SpeService;
import com.she.utils.RequestMapper;

/**
 * 안전보호구 컨트롤러
 *
 * @author kga
 *
 */
@RestController
@RequestMapping("api/saf/spe")
public class SpeController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private SpeService speService;

    /**
     * 보호구 조회
     *
     * @param parameter
     *            검색조건
     * @return 보호구 목록
     * @throws Exception
     */
    @GetMapping("/spes")
    public ResponseEntity<List<Spe>> getSpes(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 보호구 종류
        String speKindCd = map.containsKey("speKindCd") ? map.get("speKindCd").toString() : "";
        // 보호구명
        String speNm = map.containsKey("speNm") ? map.get("speNm").toString() : "";
        // 보호구명
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<Spe> spes = speService.getSpes(plantCd, speKindCd, speNm, useYn);
        return ResponseEntity.ok().body(spes);
    }

    /**
     * 보호구 상세조회
     *
     * @param safSpeNo
     *            보호구 번호
     * @return 보호구 상세
     * @throws Exception
     */
    @GetMapping("spe/{safSpeNo}")
    public ResponseEntity<Spe> getSpe(@PathVariable(name = "safSpeNo") String safSpeNo) throws Exception {
        return ResponseEntity.ok().body(this.speService.getSpe(safSpeNo));
    }

    /**
     * 보호구 신규등록
     *
     * @param spe
     * @return 보호구 번호
     * @throws Exception
     */
    @PostMapping("/spe")
    public ResponseEntity<Integer> createSpe(@RequestBody Spe spe) throws Exception {
        return ResponseEntity.ok().body(this.speService.createSpe(spe));
    }

    /**
     * 보호구 수정
     *
     * @param spe
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/spe")
    public ResponseEntity<Integer> updateSpe(@RequestBody Spe spe) throws Exception {
        return ResponseEntity.ok().body(this.speService.updateSpe(spe));
    }

    /**
     * 보호구 신청 등록
     *
     * @param speRqstGive
     * @return 보호구 신청 번호
     * @throws Exception
     */
    @PostMapping("/sperqst")
    public ResponseEntity<Integer> createSpeRqstGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.createSpeRqstGive(speRqstGive));
    }

    /**
     * 보호구 신청 수정
     *
     * @param speRqstGive
     * @return 보호구 신청 번호
     * @throws Exception
     */
    @PutMapping("/sperqst")
    public ResponseEntity<Integer> updateSpeRequest(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.updateSpeRequest(speRqstGive));
    }

    /**
     * 보호구 신청 완료
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @PutMapping("/sperqstcmpt")
    public ResponseEntity<Integer> completeSpeRequest(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.completeSpeRequest(speRqstGive));
    }

    /**
     * 보호구 신청 삭제
     *
     * @param safSpeRqstGiveNo
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/sperqst/{safSpeRqstGiveNo}")
    public ResponseEntity<Integer> deleteSpeRequest(@PathVariable(name = "safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception {
        return ResponseEntity.ok().body(this.speService.deleteSpeRqstGive(safSpeRqstGiveNo));
    }

    /**
     * 보호구 신청 조회
     *
     * @param parameter
     *            검색조건
     * @return 보호구 신청 목록
     * @throws Exception
     */
    @GetMapping("/sperqsts")
    public ResponseEntity<List<SpeRqstGive>> getSpeRequests(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 신청/지급 기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 신청/지급 요청부서
        String rqstDeptCd = map.containsKey("rqstDeptCd") ? map.get("rqstDeptCd").toString() : "";
        // 신청/지급 요청자
        String rqstUserId = map.containsKey("rqstUserId") ? map.get("rqstUserId").toString() : "";
        // 진행단계
        String speStepCd = map.containsKey("speStepCd") ? map.get("speStepCd").toString() : "";
        return ResponseEntity.ok().body(speService.getSpeRqstGiveList(plantCd, startYmd, endYmd, rqstDeptCd, rqstUserId, speStepCd));
    }

    /**
     * 보호구 신청 상세 상세조회
     *
     * @param safSpeRqstGiveNo
     * @return 보호구 신청 상세
     * @throws Exception
     */
    @GetMapping("/sperqst/{safSpeRqstGiveNo}")
    public ResponseEntity<SpeRqstGive> getSpeRqstGive(@PathVariable(name = "safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception {
        return ResponseEntity.ok().body(this.speService.getSpeRqstGive(safSpeRqstGiveNo));
    }

    // 보호구 지급 목록
    @GetMapping("/paylist")
    public ResponseEntity<SpeGiDtl> getPayList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 보호구 번호
        int safSpeNo = map.containsKey("safSpeNo") ? Integer.parseInt("".equals(map.get("safSpeNo").toString()) ? "0" : map.get("safSpeNo").toString()) : 0;
        return ResponseEntity.ok().body(speService.getPayList(safSpeNo));
    }

    /**
     * 보호구 지급 등록
     *
     * @param speRqstGive
     * @return 보호구 출고 번호
     * @throws Exception
     */
    @PostMapping("/spegive")
    public ResponseEntity<Integer> createSpeGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.createSpeGive(speRqstGive));
    }

    /**
     * 보호구 지급 수정
     *
     * @param speRqstGive
     * @return 보호구 출고 번호
     * @throws Exception
     */
    @PutMapping("/spegive")
    public ResponseEntity<Integer> updateSpeGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.updateSpeGive(speRqstGive));
    }

    /**
     * 보호구 지급 완료
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @PutMapping("/spegivecmpt")
    public ResponseEntity<Integer> completeSpeGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.completeSpeGive(speRqstGive));
    }

    /**
     * 보호구 무신청 등록
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @PostMapping("/spenorqstgive")
    public ResponseEntity<Integer> createSpeNoRqstGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.createSpeNoRqstGive(speRqstGive));
    }

    /**
     * 보호구 무신청 수정
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @PutMapping("/spenorqstgive")
    public ResponseEntity<Integer> updateSpeNoRqstGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.updateSpeNoRqstGive(speRqstGive));
    }

    /**
     * 보호구 무신청 완료
     *
     * @param speRqstGive
     * @return
     * @throws Exception
     */
    @PutMapping("/spenorqstgivecmpt")
    public ResponseEntity<Integer> completeSpeNoRqstGive(@RequestBody SpeRqstGive speRqstGive) throws Exception {
        return ResponseEntity.ok().body(this.speService.completeSpeNoRqstGive(speRqstGive));
    }

    /**
     * 보호구 무신청 삭제
     *
     * @param safSpeRqstGiveNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/spenorqstgive/{safSpeRqstGiveNo}")
    public ResponseEntity<Integer> deleteSpeNoRqstGive(@PathVariable(name = "safSpeRqstGiveNo") int safSpeRqstGiveNo) throws Exception {
        return ResponseEntity.ok().body(this.speService.deleteSpeNoRqstGive(safSpeRqstGiveNo));
    }

    /**
     * 보호구 지급현황 목록 조회
     *
     * @param parameter
     *            검색조건
     * @return 보호구 출고 목록
     * @throws Exception
     */
    @GetMapping("/spegives")
    public ResponseEntity<List<SpeRqstGive>> getSpeGives(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 보호구 종류
        String speKindCd = map.containsKey("speKindCd") ? map.get("speKindCd").toString() : "";
        // 보호구명
        String speNm = map.containsKey("speNm") ? map.get("speNm").toString() : "";
        // 요청부서
        String rqstDeptCd = map.containsKey("rqstDeptCd") ? map.get("rqstDeptCd").toString() : "";
        // 요청자
        String rqstUserId = map.containsKey("rqstUserId") ? map.get("rqstUserId").toString() : "";
        // 수령자
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 출고기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }

        List<SpeRqstGive> speGives = speService.getSpeGives(plantCd, speKindCd, speNm, rqstDeptCd, rqstUserId, startYmd, endYmd, userId);
        return ResponseEntity.ok().body(speGives);
    }

    /**
     * 보호구 조회
     *
     * @param parameter
     *            검색조건
     * @return 보호구 목록
     * @throws Exception
     */
    @GetMapping("/spekinds")
    public ResponseEntity<List<CodeMaster>> getSpeKinds(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장 코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(speService.getSpeKinds(plantCd));
    }

}
