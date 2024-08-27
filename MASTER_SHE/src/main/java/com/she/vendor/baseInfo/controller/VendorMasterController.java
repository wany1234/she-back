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
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.she.utils.RequestMapper;
import com.she.vendor.baseInfo.service.VendorMasterService;
import com.she.vendor.model.ChemicalVendorMaster;
import com.she.vendor.model.VendorWorker;

@RestController
@RequestMapping("/api/vendor/baseinfo")
public class VendorMasterController {
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private VendorMasterService vendorMasterService;

    /**
     * 업체 조회
     * 
     * @param parameter
     *            검색조건
     * @return 업체 목록
     * @throws Exception
     */
    @GetMapping("/chemicalvendormasters")
    public ResponseEntity<Map<String, Object>> getChemicalVendorMasters(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 업체분류
        String vendorTypeCd = map.containsKey("vendorTypeCd") ? map.get("vendorTypeCd").toString() : "";
        // 업체유형
        String vendorAttCd = map.containsKey("vendorAttCd") ? map.get("vendorAttCd").toString() : "";
        // 업체명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        // 인증요청 발송여부
        String authYn = map.containsKey("authYn") ? map.get("authYn").toString() : "";
        // 추가정보 등록여부
        String addYn = map.containsKey("addYn") ? map.get("addYn").toString() : "";

        Integer pageNumber = map.containsKey("pageNumber") ? Integer.parseInt(map.get("pageNumber").toString()) : 1;
        Integer pageSize = map.containsKey("pageSize") ? Integer.parseInt(map.get("pageSize").toString()) : 10000;

        String orderByExpression = map.containsKey("orderByExpression") ? map.get("orderByExpression").toString() : "";

        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<ChemicalVendorMaster> body = vendorMasterService.getChemicalVendorMasters(plantCd, vendorNm, vendorTypeCd, vendorAttCd, useYn, pageNumber, pageSize, orderByExpression, authYn, addYn, defaultParam);
        Integer totalCount = CollectionUtils.isNotEmpty(body) ? Integer.parseInt(String.valueOf(body.get(0).getTotalCnt())) : 0;
        returnMap.put("items", body);
        returnMap.put("count", totalCount);

        return ResponseEntity.ok().body(returnMap);
    }

    /**
     * 업체 상세 조회
     * 
     * @param vendorCd
     *            업체코드
     * @return 업체
     * @throws Exception
     */
    @GetMapping("/chemicalvendormaster/{vendorCd}")
    public ResponseEntity<ChemicalVendorMaster> getChemicalVendorMaster(@PathVariable(name = "vendorCd") String vendorCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.vendorMasterService.getChemicalVendorMaster(vendorCd, defaultParam));
    }

    /**
     * 업체 근무자 조회
     * 
     * @param vendorCd
     *            업체코드
     * @param workerNm
     *            작업자성명
     * @return 업체
     * @throws Exception
     */
    @GetMapping("/chemicalvendormaster/worker")
    public ResponseEntity<List<VendorWorker>> getChemicalVendorMasterWorkers(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 작업자성명
        String workerNm = map.containsKey("workerNm") ? map.get("workerNm").toString() : "";
        // 업체코드
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 작업자성명
        String vendorTypeCd = map.containsKey("vendorTypeCd") ? map.get("vendorTypeCd").toString() : "";
        // 업체코드
        String vendorAttCd = map.containsKey("vendorAttCd") ? map.get("vendorAttCd").toString() : "";
        // 작업자성명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        return ResponseEntity.ok().body(this.vendorMasterService.getChemicalVendorMasterWorkers(vendorCd, workerNm, plantCd, vendorTypeCd, vendorAttCd, vendorNm, defaultParam));
    }

    /**
     * 업체 신규등록
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 업체 코드
     * @throws Exception
     */
    @PostMapping("/chemicalvendormaster")
    public ResponseEntity<String> createChemicalVendorMaster(@RequestBody ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        String portalId = chemicalVendorMaster.getPortalId();
        if (!portalId.equals("")) {
            String sha256hex = DigestUtils.sha256Hex(portalId);
            chemicalVendorMaster.setPortalPwd(sha256hex);
        }
        return ResponseEntity.ok().body(this.vendorMasterService.createChemicalVendorMaster(chemicalVendorMaster));
    }

    /**
     * 업체 수정
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/chemicalvendormaster")
    public ResponseEntity<Integer> updateChemicalVendorMaster(@RequestBody ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        return ResponseEntity.ok().body(this.vendorMasterService.updateChemicalVendorMaster(chemicalVendorMaster));
    }

    /**
     * 포털 비밀번호 초기화
     * 
     * @param vendorCd
     *            업체코드
     * @Param portalId 포털접속ID
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/chemicalvendormasterpwdreset/{vendorCd}/{portalId}")
    public ResponseEntity<Integer> updateChemicalVendorMasterPwdReset(@PathVariable("vendorCd") String vendorCd, @PathVariable("portalId") String portalId) throws Exception {

        ChemicalVendorMaster chemicalVendorMaster = new ChemicalVendorMaster();
        chemicalVendorMaster.setVendorCd(vendorCd);

        String sha256hex = DigestUtils.sha256Hex(portalId);
        chemicalVendorMaster.setPortalPwd(sha256hex);
        return ResponseEntity.ok().body(this.vendorMasterService.updateChemicalVendorMasterPwdReset(chemicalVendorMaster));
    }

    /**
     * 업체명 체크
     * 
     * @param parameter
     *            검색조건
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkchemicalvendormaster")
    public ResponseEntity<List<HashMap<String, Object>>> getCheckChemicalVendorMaster(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 업체명
        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";
        // 업체코드 (저장된)
        String vendorCdOrigin = map.containsKey("vendorCdOrigin") ? map.get("vendorCdOrigin").toString() : "";
        // 업체코드 (수정할)
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";

        return ResponseEntity.ok().body(vendorMasterService.getCheckChemicalVendorMaster(vendorNm, vendorCdOrigin, vendorCd));
    }

    /**
     * 협력업체 상세 정보 조회
     * 
     * @param vendorCd
     * @return
     * @throws Exception
     */
    @GetMapping("/vendorinfo/{vendorCd}")
    public ResponseEntity<ChemicalVendorMaster> getVendorInfoDetail(@PathVariable("vendorCd") String vendorCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(vendorMasterService.getVendorInfoDetail(vendorCd, defaultParam));
    }

    /**
     * 협력업체 정보 수정
     * 
     * @param chemicalVendorMaster
     * @return
     * @throws Exception
     */
    @PutMapping("/vendorinfo")
    public ResponseEntity<Integer> updateVendorInfo(@RequestBody ChemicalVendorMaster chemicalVendorMaster) throws Exception {
        return ResponseEntity.ok().body(vendorMasterService.updateVendorInfo(chemicalVendorMaster));
    }

    /**
     * 포탈아이디 중복 체크
     *
     * @param parameter
     *            포탈아이디
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkportalid")
    public ResponseEntity<Integer> getCheckPortalId(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 포탈아이디
        String portalId = map.containsKey("portalId") ? map.get("portalId").toString() : "";

        return ResponseEntity.ok().body(vendorMasterService.getCheckPortalId(portalId));
    }

    /**
     * 사업자번호 중복 체크
     *
     * @param parameter
     *            사업자번호
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/checkbiznum")
    public ResponseEntity<Integer> getCheckBizNum(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업자번호
        String bizNum = map.containsKey("bizNum") ? map.get("bizNum").toString() : "";

        return ResponseEntity.ok().body(vendorMasterService.getCheckBizNum(bizNum));
    }

    /**
     * 협력사 가입요청
     *
     * @param parameter
     *            업체코드
     * @return 체크값
     * @throws Exception
     */
    @GetMapping("/joinRequest")
    public ResponseEntity<String> getJoinRequest(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 업체코드
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 담당자 이메일
        String email = map.containsKey("email") ? map.get("email").toString() : "";

        return ResponseEntity.ok().body(vendorMasterService.getJoinRequest(vendorCd, email));
    }

    /**
     * 인증번호 체크
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/certification")
    public ResponseEntity<ChemicalVendorMaster> vendorCertification(@RequestParam HashMap<String, Object> parameter) throws Exception {
    	HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 인증번호
        String authNumber = map.containsKey("authNumber") ? map.get("authNumber").toString() : "";
    	
    	return ResponseEntity.ok().body(vendorMasterService.getVendorAuth(authNumber));
    }
}
