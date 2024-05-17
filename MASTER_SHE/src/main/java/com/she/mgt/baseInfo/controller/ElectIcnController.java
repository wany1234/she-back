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
package com.she.mgt.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.mgt.baseInfo.service.ElectIcnService;
import com.she.mgt.model.InvestItem;
import com.she.mgt.model.MgtMgInfoItm;
import com.she.mgt.model.MgtMgInfoItmDeptAtt;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/saf/baseinfo")
public class ElectIcnController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ElectIcnService electIcnService;

    /**
     * 경영정보부서 구분명 조회
     *
     * @param parameter
     *            검색조건
     * @return
     * @throws Exception
     */
    @GetMapping("/mgtinfoitem")
    public ResponseEntity<List<MgtMgInfoItmDeptAtt>> getMgtInfoItem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 분류
        String infoItmAttCd = map.containsKey("infoItmAttCd") ? map.get("infoItmAttCd").toString() : "";

        List<MgtMgInfoItmDeptAtt> mgtInfoItemList = electIcnService.getMgtInfoItem(plantCd, infoItmAttCd);
        return ResponseEntity.ok().body(mgtInfoItemList);
    }

    /**
     * 경영정보항목 상세 조회
     *
     * @param mgtMgInfoDeptAttNo
     *            경영정보부서 번호
     * @return mgtMgInfoDeptAttNo 경영정보부서
     * @throws Exception
     *             예외
     */
    @GetMapping("/mgtinfoitem/{mgtMgInfoDeptAttNo}")
    public ResponseEntity<MgtMgInfoItmDeptAtt> getMgtInfoItemDetail(@PathVariable("mgtMgInfoDeptAttNo") String mgtMgInfoDeptAttNo) throws Exception {
        return ResponseEntity.ok().body(electIcnService.getMgtInfoItemDetail(mgtMgInfoDeptAttNo));
    }

    /**
     * 경영정보부서 항목 조회
     *
     * @param parameter
     *            검색조건
     * @return
     * @throws Exception
     */
    @GetMapping("/mgtinfoitemNm")
    public ResponseEntity<List<MgtMgInfoItm>> getMgtMgInfoItemList() throws Exception {
        return ResponseEntity.ok().body(electIcnService.getMgtMgInfoItemList());
    }

    /**
     * 등록된 경영정보항목 확인
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/checkmgtinfoitem")
    public ResponseEntity<Integer> checkMgtinfoitem(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 경영정보항목번호
        int mgtMgInfoItmNo = map.containsKey("mgtMgInfoItmNo") ? Integer.parseInt("".equals(map.get("mgtMgInfoItmNo").toString()) ? "0" : map.get("mgtMgInfoItmNo").toString()) : 0;
        // 부서구분명
        String deptAttNm = map.containsKey("deptAttNm") ? map.get("deptAttNm").toString() : "";
        return ResponseEntity.ok().body(electIcnService.checkMgtinfoitem(plantCd, mgtMgInfoItmNo, deptAttNm));
    }

    /**
     * 경영정보부서 등록
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/mgtinfoitem")
    public ResponseEntity<Integer> createMgtinfoitem(@RequestBody MgtMgInfoItmDeptAtt mgtMgInfoItmDeptAtt) throws Exception {
        return ResponseEntity.ok().body(this.electIcnService.createMgtinfoitem(mgtMgInfoItmDeptAtt));
    }

    /**
     * 경영정보부서 수정
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @PutMapping("/mgtinfoitem")
    public ResponseEntity<Integer> updateMgtinfoitem(@RequestBody MgtMgInfoItmDeptAtt mgtMgInfoItmDeptAtt) throws Exception {
        return ResponseEntity.ok().body(this.electIcnService.updateMgtinfoitem(mgtMgInfoItmDeptAtt));
    }

    /**
     * 투자항목관리 조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/investItem")
    public ResponseEntity<List<InvestItem>> getInvestItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 투자분류이름
        String codeNm = map.containsKey("codeNm") ? map.get("codeNm").toString() : "";
        // 투자항목
        int mgtMgInfoItmNo = map.containsKey("mgtMgInfoItmNo") ? Integer.parseInt("".equals(map.get("mgtMgInfoItmNo").toString()) ? "0" : map.get("mgtMgInfoItmNo").toString()) : 0;
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.electIcnService.getInvestItems(codeNm, mgtMgInfoItmNo, useYn));
    }

    /**
     * 투자항목(중분류) 조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/investItemMiddle")
    public ResponseEntity<List<InvestItem>> getInvestItemsMiddle(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 투자분류코드
        String infoItmAttCd = map.containsKey("infoItmAttCd") ? map.get("infoItmAttCd").toString() : "";
        // 투자항목
        int mgtMgInfoItmNo = map.containsKey("mgtMgInfoItmNo") ? Integer.parseInt("".equals(map.get("mgtMgInfoItmNo").toString()) ? "0" : map.get("mgtMgInfoItmNo").toString()) : 0;
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        return ResponseEntity.ok().body(this.electIcnService.getInvestItemsMiddle(infoItmAttCd, mgtMgInfoItmNo, useYn));
    }

    /**
     * 투자항목관리 상세조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/investItem/{infoItmAttCd}/{mgtMgInfoItmNo}")
    public ResponseEntity<InvestItem> getInvestItem(@PathVariable("infoItmAttCd") String infoItmAttCd, @PathVariable("mgtMgInfoItmNo") int mgtMgInfoItmNo) throws Exception {
        return ResponseEntity.ok().body(electIcnService.getInvestItem(infoItmAttCd, mgtMgInfoItmNo));
    }

    /**
     * 투자항목 등록
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/investItemAdd")
    public ResponseEntity<Integer> createInvestItemAdd(@RequestBody InvestItem investItem) throws Exception {
        return ResponseEntity.ok().body(this.electIcnService.createInvestItemAdd(investItem));
    }

    /**
     * 투자항목 수정
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    @PutMapping("/investItemAdd")
    public ResponseEntity<Integer> updateInvestItemAdd(@RequestBody InvestItem investItem) throws Exception {
        return ResponseEntity.ok().body(this.electIcnService.updateInvestItemAdd(investItem));
    }

}
