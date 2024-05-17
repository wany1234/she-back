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
package com.she.env.waste.disposal.controller;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.she.env.waste.disposal.service.DisposalRequestService;
import com.she.env.waste.model.DisposalRequest;
import com.she.utils.RequestMapper;

/**
 * 폐기물 처리요청
 *
 */
@RestController("EwstDisposalRequestController")
@RequestMapping("api/env/waste/disposal")
public class DisposalRequestController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DisposalRequestService disposalRequestService;

    /**
     * 폐기물 처리요청 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 처리요청 목록
     * @throws Exception
     */
    @GetMapping("/disposalrequests")
    public ResponseEntity<List<DisposalRequest>> getDisposalRequests(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        //
        String ewstClassCd = map.containsKey("ewstClassCd") ? map.get("ewstClassCd").toString() : "";
        //
        String ewstDispoStCd = map.containsKey("ewstDispoStCd") ? map.get("ewstDispoStCd").toString() : "";
        //
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String rqstDeptCd = map.containsKey("rqstDeptCd") ? map.get("rqstDeptCd").toString() : "";
        String ewstWasteNo = map.containsKey("ewstWasteNo") ? map.get("ewstWasteNo").toString() : "";

        String[] reqYmdPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("reqYmdPeriod"));
        String reqYmdFrom = "";
        String reqYmdTo = "";
        if (reqYmdPeriod != null && reqYmdPeriod.length == 2) {
            reqYmdFrom = reqYmdPeriod[0];
            reqYmdTo = reqYmdPeriod[1];
        }

        return ResponseEntity.ok().body(this.disposalRequestService.getDisposalRequests(rqstDeptCd, reqYmdFrom, reqYmdTo, ewstDispoStCd, ewstClassCd, plantCd, ewstWasteNo,defaultParam));
    }

    /**
     * 선택된 폐기물 처리요청 상세 조회
     *
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 처리물 처리요청 정보
     * @throws Exception
     */
    @GetMapping("/disposalrequest/{ewstDispoReqNo}")
    public ResponseEntity<DisposalRequest> getDisposalRequest(@PathVariable(name = "ewstDispoReqNo") int ewstDispoReqNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        DisposalRequest disposalRequest = this.disposalRequestService.getDisposalRequest(ewstDispoReqNo, defaultParam);
        return ResponseEntity.ok().body(disposalRequest);
    }

    /**
     * 폐기물 처리요청 신규 생성
     *
     * @param disposalRequest
     *            폐기물 처리요청 정보
     * @return 폐기물 처리요청 번호
     * @throws Exception
     */
    @PostMapping("/disposalrequest")
    public ResponseEntity<Integer> createDisposalRequest(@RequestBody DisposalRequest disposalRequest) throws Exception {
        return ResponseEntity.ok().body(this.disposalRequestService.createDisposalRequest(disposalRequest));
    }

    /**
     * 선택된 폐기물 처리업체 수정
     *
     * @param disposalCompany
     *            폐기물 처리업체
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/disposalrequest")
    public ResponseEntity<Integer> updateDisposalRequest(@RequestBody DisposalRequest disposalRequest) throws Exception {
        Integer count = this.disposalRequestService.updateDisposalRequest(disposalRequest);
        return ResponseEntity.ok().body(count);
    }

    /**
     * 선택된 폐기물 처리업체 수정
     *
     * @param disposalCompany
     *            폐기물 처리업체
     * @return 수정행수
     * @throws Exception
     */
    @DeleteMapping("/disposalrequest/{ewstDispoReqNo}")
    public ResponseEntity<Integer> updateDisposalRequestStatus(@PathVariable(name = "ewstDispoReqNo") int ewstDispoReqNo) throws Exception {
        Integer count = this.disposalRequestService.deleteDisposalRequest(ewstDispoReqNo);
        return ResponseEntity.ok().body(count);
    }
}
