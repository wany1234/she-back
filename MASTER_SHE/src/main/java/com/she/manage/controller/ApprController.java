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
package com.she.manage.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.model.Appr;
import com.she.manage.model.ApprBiz;
import com.she.manage.model.ApprDelegate;
import com.she.manage.model.ApprRqst;
import com.she.manage.model.ApprRqstLine;
import com.she.manage.service.ApprService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/api/manage")
public class ApprController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ApprService apprService;

    /**
     * 결재문서 마스터 목록 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/apprbizlist")
    public ResponseEntity<List<ApprBiz>> getApprBizList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 결재문서유형코드
        String apprBizCd = map.containsKey("apprBizCd") ? map.get("apprBizCd").toString() : "";
        // 업무명
        String bizNm = map.containsKey("bizNm") ? map.get("bizNm").toString() : "";
        // 결재선범위구분코드
        String apprBizTypeCd = map.containsKey("apprBizTypeCd") ? map.get("apprBizTypeCd").toString() : "";

        return ResponseEntity.ok().body(apprService.getApprBizList(apprBizCd, bizNm, apprBizTypeCd, defaultParam));
    }

    /**
     * 결재선 등록
     * 
     * @param apprBiz
     * @return
     * @throws Exception
     */
    @PostMapping("/apprline")
    public ResponseEntity<Integer> createApprLine(@RequestBody ApprBiz apprBiz) throws Exception {
        return ResponseEntity.ok().body(apprService.createApprLine(apprBiz));
    }

    /**
     * 결재선 수정
     * 
     * @param apprBiz
     * @return
     * @throws Exception
     */
    @PutMapping("/apprline")
    public ResponseEntity<Integer> updateApprLine(@RequestBody ApprBiz apprBiz) throws Exception {
        return ResponseEntity.ok().body(apprService.updateApprLine(apprBiz));
    }

    /**
     * 결재선 상세 조회
     * 
     * @param apprBizNo
     * @return
     * @throws Exception
     */
    @GetMapping("/apprline/{apprBizNo}")
    public ResponseEntity<ApprBiz> getApprBizDetail(@PathVariable("apprBizNo") int apprBizNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(apprService.getApprBizDetail(apprBizNo, defaultParam));
    }

    /**
     * 결재문서 유형코드 조회
     * 
     * @param apprBizCd
     * @return
     * @throws Exception
     */
    @GetMapping("/apprbizcodecount/{apprBizCd}")
    public ResponseEntity<Integer> getApprBizCodeCount(@PathVariable("apprBizCd") String apprBizCd) throws Exception {
        return ResponseEntity.ok().body(apprService.getApprBizCodeCount(apprBizCd));
    }

    /**
     * 결재 정보 조회
     * 
     * @param apprBiz
     * @return
     * @throws Exception
     */
    @GetMapping("/apprrequest")
    public ResponseEntity<ApprBiz> getApprRequestInfo(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 결재문서유형코드
        String apprBizCd = map.containsKey("apprBizCd") ? map.get("apprBizCd").toString() : "";
        // 사업장코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 사용자ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        return ResponseEntity.ok().body(apprService.getApprRequestInfo(apprBizCd, plantCd, userId, defaultParam));
    }

    /**
     * 결재요청 등록
     * 
     * @param apprRqst
     * @return
     * @throws Exception
     */
    @PostMapping("/apprrequest")
    public ResponseEntity<Integer> createApprRequest(@RequestBody ApprRqst apprRqst) throws Exception {
        return ResponseEntity.ok().body(apprService.createApprRequest(apprRqst));
    }

    /**
     * 결재요청 수정(재결재요청)
     * 
     * @param apprRqst
     * @return
     * @throws Exception
     */
    @PutMapping("/apprrequest")
    public ResponseEntity<Integer> updateApprRequest(@RequestBody ApprRqst apprRqst) throws Exception {
        return ResponseEntity.ok().body(apprService.updateApprRequest(apprRqst));
    }

    /**
     * 결재 할 문서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 결재 할 문서 목록
     */
    @GetMapping("/whichapprs")
    public ResponseEntity<List<Appr>> getWhichApprs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 결재요청일
        String[] createPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("createPeriod"));
        String startDate = "";
        String endDate = "";
        if (createPeriod != null && createPeriod.length == 2) {
            startDate = createPeriod[0];
            endDate = createPeriod[1];
        }
        // 처리여부
        String apprYn = map.containsKey("apprYn") ? map.get("apprYn").toString() : "";
        // 반려여부
        String returnYn = map.containsKey("returnYn") ? map.get("returnYn").toString() : "";
        // 사용자ID
        String apprUserId = map.containsKey("apprUserId") ? map.get("apprUserId").toString() : "";
        // 결재요청명
        String apprRqstNm = map.containsKey("apprRqstNm") ? map.get("apprRqstNm").toString() : "";

        return ResponseEntity.ok().body(apprService.getWhichApprs(startDate, endDate, apprYn, returnYn, apprUserId, apprRqstNm, defaultParam));
    }

    /**
     * 결재 할 문서 결재선 목록 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    @GetMapping("/whichapprline/{apprRqstNo}")
    public ResponseEntity<List<ApprRqstLine>> getWhichApprLine(@PathVariable int apprRqstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(apprService.getWhichApprLine(apprRqstNo, defaultParam));
    }

    /**
     * 결재 처리 상태 수정
     * 
     * @param apprRqstLine
     * @return
     * @throws Exception
     */
    @PutMapping("/appr")
    public ResponseEntity<Integer> updateApprStep(@RequestBody ApprRqstLine apprRqstLine) throws Exception {
        return ResponseEntity.ok().body(apprService.updateApprStep(apprRqstLine));
    }

    /**
     * 일괄결제 처리
     * 
     * @param appr
     * @return
     * @throws Exception
     */
    @PutMapping("/apprall")
    public ResponseEntity<Integer> updateApprStepAll(@RequestBody Appr appr) throws Exception {
        return ResponseEntity.ok().body(apprService.updateApprStepAll(appr));
    }

    /**
     * 결재 요청 문서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 결재 할 문서 목록
     */
    @GetMapping("/requestapprs")
    public ResponseEntity<List<Appr>> getApprRequestList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 결재요청일
        String[] createPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("createPeriod"));
        String startDate = "";
        String endDate = "";
        if (createPeriod != null && createPeriod.length == 2) {
            startDate = createPeriod[0];
            endDate = createPeriod[1];
        }
        // 결재진행단계코드
        String bizApprStepCd = map.containsKey("bizApprStepCd") ? map.get("bizApprStepCd").toString() : "";
        // 사용자ID
        String apprUserId = map.containsKey("apprUserId") ? map.get("apprUserId").toString() : "";
        // 결재요청명
        String apprRqstNm = map.containsKey("apprRqstNm") ? map.get("apprRqstNm").toString() : "";

        return ResponseEntity.ok().body(apprService.getApprRequestList(startDate, endDate, bizApprStepCd, apprUserId, apprRqstNm, defaultParam));
    }

    /**
     * 결재요청 상세 정보 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    @GetMapping("/groupwareappr/{apprRqstNo}")
    public ResponseEntity<Appr> getApprRequestDetail(@PathVariable int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(apprService.getApprRqstDetail(apprRqstNo));
    }

    /**
     * 반려 결재 상세 조회
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    @GetMapping("/rejectappr/{apprRqstNo}")
    public ResponseEntity<Appr> getRejectApprDetail(@PathVariable int apprRqstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(apprService.getRejectApprDetail(apprRqstNo, defaultParam));
    }

    /**
     * 상위결재자 변경
     * 
     * @param apprLines
     * @return
     * @throws Exception
     */
    @PutMapping("/changeapprline/{apprRqstNo}")
    public ResponseEntity<Integer> updateApprLine(@RequestBody List<ApprRqstLine> apprLines, @PathVariable int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(apprService.updateApprLine(apprLines, apprRqstNo));
    }

    /**
     * 결재 중 문서 수정 권한 조회
     * 
     * @param apprRqstNo
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/authcheck/{apprRqstNo}/{userId}")
    public ResponseEntity<Boolean> getAuthCheck(@PathVariable int apprRqstNo, @PathVariable String userId) throws Exception {
        return ResponseEntity.ok().body(apprService.getAuthCheck(apprRqstNo, userId));
    }

    /**
     * 결재 회수 가능한지 여부 조회
     * 
     * @param apprRqstNo
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/collectcheck/{apprRqstNo}/{userId}")
    public ResponseEntity<Boolean> getCollectCheck(@PathVariable int apprRqstNo, @PathVariable String userId) throws Exception {
        return ResponseEntity.ok().body(apprService.getCollectCheck(apprRqstNo, userId));
    }

    /**
     * 결재 회수
     * 
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    @DeleteMapping("/collect/{apprRqstNo}")
    public @ResponseBody int deleteAppr(@PathVariable int apprRqstNo) throws Exception {
        return apprService.deleteAppr(apprRqstNo);
    }

    /**
     * 결재 파라미터 수정
     * 
     * @param apprRqstNo
     * @param apprParam
     * @return
     * @throws Exception
     */
    @PutMapping("/apprparam/{apprRqstNo}/{apprParam}")
    public @ResponseBody int updateApprParam(@PathVariable int apprRqstNo, @PathVariable String apprParam) throws Exception {
        return apprService.updateApprParam(apprRqstNo, apprParam);
    }

    /**
     * 결재 회수
     * 
     * @param apprRqstNo
     *            결재번호
     * 
     * @return
     * @throws Exception
     */
    @PutMapping("/collectappr/{apprRqstNo}")
    public @ResponseBody int collectAppr(@PathVariable int apprRqstNo) throws Exception {
        return apprService.collectAppr(apprRqstNo);
    }

    /**
     * 대리 결재자 등록
     * 
     * @param apprDelegate
     * @return
     * @throws Exception
     */
    @PostMapping("/apprdelegate/{userId}")
    public ResponseEntity<HashMap<String, Object>> createApprDelegate(@RequestBody List<ApprDelegate> apprDelegate, @PathVariable String userId) throws Exception {
        return ResponseEntity.ok().body(apprService.createApprDelegate(apprDelegate, userId));
    }

    /**
     * 대리 결재자 조회
     * 
     * @param apprRqstLines
     * @return
     * @throws Exception
     */
    @PutMapping("/apprdelegators")
    public ResponseEntity<List<ApprRqstLine>> getApprdelegators(@RequestBody List<ApprRqstLine> apprRqstLines) throws Exception {
        return ResponseEntity.ok().body(apprService.getApprdelegators(apprRqstLines));
    }

    /**
     * 결재 완료일 조회
     *
     * @param apprRqstNo
     *            결재번호
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/apprenddt/{apprRqstNo}")
    public ResponseEntity<String> getApprEndDt(@PathVariable int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(apprService.getApprEndDt(apprRqstNo));
    }

    /**
     * 결재진행상태 조회
     *
     * @param apprRqstNo
     *            결재번호
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/apprprogress/{apprRqstNo}")
    public ResponseEntity<List<HashMap<String, Object>>> getApprProgress(@PathVariable int apprRqstNo) throws Exception {
        return ResponseEntity.ok().body(apprService.getApprProgress(apprRqstNo));
    }

}
