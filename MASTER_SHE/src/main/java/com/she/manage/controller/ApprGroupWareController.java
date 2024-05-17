package com.she.manage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.manage.model.ApprGroupWare;
import com.she.manage.model.ApprRqstGroupWare;
import com.she.manage.service.ApprService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("/sheappr")
public class ApprGroupWareController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ApprService apprService;

    /**
     * 그룹웨어 결재 할 문서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 결재 할 문서 목록
     */
    @GetMapping("/appr-list")
    @ResponseBody
    public Map<String, Object> getApprList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사용자ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 결재요청 시작일
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // 결재요청 종료일
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 결재처리여부
        String apprYn = map.containsKey("apprYn") ? map.get("apprYn").toString() : "";

        if (StringUtils.isNotBlank(userId)) {
            List<ApprGroupWare> list = new ArrayList<ApprGroupWare>();
            list = apprService.getWhichApprsForGroupWare(startDate, endDate, apprYn, userId, "", defaultParam);
            result.put("listCount", list.size());
            result.put("list", list);
            result.put("resultMsg", list.size() + "건 조회를 완료하였습니다.");
        } else {
            result.put("resultMsg", "사용자ID 를 확인해 주십시오.");
        }
        return result;
    }

    /**
     * 그룹웨어 결재 완료 문서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 결재 완료 문서 목록
     */
    @GetMapping("/appr-complete-list")
    @ResponseBody
    public Map<String, Object> getApprCompleteList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사용자ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 결재요청 시작일
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // 결재요청 종료일
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 결재자처리상태코드
        String apprStepCd = "APSPA";

        if (StringUtils.isNotBlank(userId)) {
            List<ApprGroupWare> list = new ArrayList<ApprGroupWare>();
            list = apprService.getWhichApprsForGroupWare(startDate, endDate, "", userId, apprStepCd, defaultParam);
            result.put("listCount", list.size());
            result.put("list", list);
            result.put("resultMsg", list.size() + "건 조회를 완료하였습니다.");
        } else {
            result.put("resultMsg", "사용자ID 를 확인해 주십시오.");
        }
        return result;
    }

    /**
     * 결재 요청 문서 조회
     * 
     * @param parameter
     *            검색조건
     * @return 결재 할 문서 목록
     */
    @GetMapping("/appr-request-list")
    @ResponseBody
    public Map<String, Object> getApprRequestList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사용자ID
        String userId = map.containsKey("userId") ? map.get("userId").toString() : "";
        // 결재요청 시작일
        String startDate = map.containsKey("startDate") ? map.get("startDate").toString() : "";
        // 결재요청 종료일
        String endDate = map.containsKey("endDate") ? map.get("endDate").toString() : "";
        // 결재진행단계코드
        String bizApprStepCd = map.containsKey("bizApprStepCd") ? map.get("bizApprStepCd").toString() : "";

        if (StringUtils.isNotBlank(userId)) {
            List<ApprRqstGroupWare> list = new ArrayList<ApprRqstGroupWare>();
            list = apprService.getApprRequestListForGroupWare(startDate, endDate, bizApprStepCd, userId, defaultParam);
            result.put("listCount", list.size());
            result.put("list", list);
            result.put("resultMsg", list.size() + "건 조회를 완료하였습니다.");
        } else {
            result.put("resultMsg", "사용자ID 를 확인해 주십시오.");
        }
        return result;
    }
}
