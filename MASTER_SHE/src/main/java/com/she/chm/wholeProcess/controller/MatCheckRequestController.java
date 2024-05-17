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
package com.she.chm.wholeProcess.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.she.chm.model.MatCheckRequest;
import com.she.chm.model.MatCheckRequestCompo;
import com.she.chm.model.MatCheckRequestCompoRegul;
import com.she.chm.model.MatCheckRequestDbVerf;
import com.she.chm.model.RegulItmMatVal;
import com.she.chm.wholeProcess.service.MatCheckRequestService;
import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.DefaultParam;
import com.she.common.service.SapDataService;
import com.she.config.GlobalSettings;
import com.she.file.service.FileStorageService;
import com.she.utils.RequestMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/chm/wholeprocess")
@Api(value = "/api/chm/wholeprocess", description = "물질검토 서비스 ")
public class MatCheckRequestController {

    private final Logger logger = LoggerFactory.getLogger(MatCheckRequestController.class);

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private MatCheckRequestService matCheckRequestService;

    @Autowired
    private SapDataService sapDataService;

    @Autowired
    private GlobalSettings globalSettings;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * 물질검토 조회
     *
     * @param parameter
     *            검색조건
     * @return 물질검토 목록
     * @throws Exception
     */
    @ApiOperation(value = "물질검토 조회", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "search", value = "검색어 (취급물질명 및 CAS NO.)", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "vendorNm", value = "제조/납품업체명", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "chkRqstState", value = "검토상태", required = false, dataType = "string", paramType = "query"), @ApiImplicitParam(name = "rqstType", value = "요청구분", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "rqstPeriod", value = "검토요청기간", required = false, dataType = "array", paramType = "query"), @ApiImplicitParam(name = "plantCd", value = "대표사업장", required = false, dataType = "string", paramType = "query") })
    @GetMapping("/matcheckrequests")
    public ResponseEntity<List<MatCheckRequest>> getMatCheckRequests(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String search = map.containsKey("search") ? map.get("search").toString() : "";

        String vendorNm = map.containsKey("vendorNm") ? map.get("vendorNm").toString() : "";

        String chkRqstState = map.containsKey("chkRqstState") ? map.get("chkRqstState").toString() : "";

        String rqstType = map.containsKey("rqstType") ? map.get("rqstType").toString() : "";

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        String[] rqstPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("rqstPeriod"));
        String rqstStart = "";
        String rqstEnd = "";
        if (rqstPeriod != null && rqstPeriod.length == 2) {
            rqstStart = rqstPeriod[0];
            rqstEnd = rqstPeriod[1];
        }

        return ResponseEntity.ok().body(matCheckRequestService.getMatCheckRequests(search, vendorNm, chkRqstState, rqstType, plantCd, rqstStart, rqstEnd, defaultParam));
    }

    /**
     * 물질검토 상세 조회
     *
     * @param matChkRqstNo
     *            물질검토번호
     * @return 물질검토
     * @throws Exception
     */
    @GetMapping("/matcheckrequest/{matChkRqstNo}")
    public ResponseEntity<MatCheckRequest> getMatCheckRequest(@PathVariable(name = "matChkRqstNo") int matChkRqstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(matCheckRequestService.getMatCheckRequest(matChkRqstNo, defaultParam));
    }

    /**
     * 물질검토 상세 조회 하위정보 포함 취급자재에서 불러오기 하는 경우로 SAP자재번호가 매핑되어 있는 경우에는 신규인지 아닌지
     * 보여준다.
     *
     * @param matChkRqstNo
     *            물질검토번호
     * @return 물질검토
     * @throws Exception
     */
    @GetMapping("/matcheckrequest/child/{matChkRqstNo}")
    public ResponseEntity<MatCheckRequest> getMatCheckRequestChilds(@PathVariable(name = "matChkRqstNo") int matChkRqstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(matCheckRequestService.getMatCheckRequestChilds(matChkRqstNo, defaultParam));
    }

    /**
     * 중복검사
     *
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/checkmatcheckrequest")
    public ResponseEntity<List<HashMap<String, Object>>> checkMatCheckRequest(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 제조업체
        String makecpCd = map.containsKey("makecpCd") ? map.get("makecpCd").toString() : "";
        // 공급업체
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // 검토요청번호
        int matChkRqstNo = map.containsKey("matChkRqstNo") ? Integer.parseInt("".equals(map.get("matChkRqstNo").toString()) ? "0" : map.get("matChkRqstNo").toString()) : 0;
        // SAP자재코드
        String sapMatCd = map.containsKey("sapMatCd") ? map.get("sapMatCd").toString() : "";

        return ResponseEntity.ok().body(this.matCheckRequestService.checkMatCheckRequest(makecpCd, vendorCd, matChkRqstNo, sapMatCd));
    }

    /**
     * 물질검토 신규등록
     *
     * @param matCheckRequest
     * @return 물질검토 번호
     * @throws Exception
     */
    @PostMapping("/matcheckrequest")
    public ResponseEntity<MatCheckRequest> createMatCheckRequest(@RequestBody MatCheckRequest matCheckRequest) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.createMatCheckRequest(matCheckRequest));
    }

    /**
     * 물질검토 수정
     *
     * @param matCheckRequest
     * @return 변경 행 수
     * @throws Exception
     */
    @PutMapping("/matcheckrequest")
    public ResponseEntity<MatCheckRequest> updateMatCheckRequest(@RequestBody MatCheckRequest matCheckRequest) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.updateMatCheckRequest(matCheckRequest));
    }

    /**
     * 물질검토 삭제
     *
     * @param matChkRqstNo
     * @return 물질검토 번호
     * @throws Exception
     */
    @DeleteMapping("/matcheckrequest/{matChkRqstNo}")
    public ResponseEntity<Integer> deleteMatCheckRequest(@PathVariable(name = "matChkRqstNo") int matChkRqstNo) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.deleteMatCheckRequest(matChkRqstNo));
    }

    /**
     * 물질검토 구성성분 조회
     *
     * @param matChkRqstNo
     *            물질검토 번호
     * @return 물질검토 구성성분 목록
     * @throws Exception
     */
    @GetMapping("/matcheckrequestcompos")
    public ResponseEntity<List<MatCheckRequestCompo>> getMatCheckRequestCompos(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 물질검토 번호
        int matChkRqstNo = map.containsKey("matChkRqstNo") ? Integer.parseInt("".equals(map.get("matChkRqstNo").toString()) ? "0" : map.get("matChkRqstNo").toString()) : 0;

        return ResponseEntity.ok().body(this.matCheckRequestService.getMatCheckRequestCompos(matChkRqstNo));
    }

    /**
     * 물질검토 규제항목 조회
     *
     * @param matChkRqstNo
     *            물질검토 번호
     * @return 물질검토 규제항목 목록
     * @throws Exception
     */
    @GetMapping("/matcheckrequestcomporegul")
    public ResponseEntity<List<RegulItmMatVal>> getMatCheckRequestCompoRegul(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 물질검토 번호
        int matChkRqstNo = map.containsKey("matChkRqstNo") ? Integer.parseInt("".equals(map.get("matChkRqstNo").toString()) ? "0" : map.get("matChkRqstNo").toString()) : 0;

        return ResponseEntity.ok().body(this.matCheckRequestService.getMatCheckRequestCompoRegul(matChkRqstNo, defaultParam));
    }

    /**
     * 물질검토 구성성분 & 규제항목 신규등록
     *
     * @param chemprod
     *            취급물질
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/matcheckrequestcompo")
    public ResponseEntity<Integer> createMatCheckRequestCompo(@RequestBody MatCheckRequestCompoRegul matCheckRequestCompoRegul) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.createMatCheckRequestCompo(matCheckRequestCompoRegul, matCheckRequestCompoRegul.getMatChkRqstNo()));
    }

    /**
     * sap 요청자재 임시로 생성
     *
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/matcheckrequest/sapmatcheckrequest")
    public ResponseEntity<Integer> saveSapMatCheckRequest(@RequestBody MatCheckRequest matCheckRequest) throws Exception {
        return ResponseEntity.ok().body(this.sapDataService.insertMatChkRqstData(matCheckRequest.getFrom(), matCheckRequest.getTo()));
    }

    /**
     * 안전보건 검토완료처리
     *
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    @PutMapping("/matcheckrequest/confirm")
    public ResponseEntity<MatCheckRequest> confirmMatCheckRequest(@RequestBody MatCheckRequest matCheckRequest) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.confirmMatCheckRequest(matCheckRequest));
    }

    /**
     * 환경 검토완료처리
     *
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    @PutMapping("/matcheckrequest/confirmenv")
    public ResponseEntity<MatCheckRequest> confirmEnvMatCheckRequest(@RequestBody MatCheckRequest matCheckRequest) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.confirmEnvMatCheckRequest(matCheckRequest));
    }

    /**
     * 검토반려
     *
     * @param matCheckRequest
     * @return
     * @throws Exception
     */
    @PutMapping("/matcheckrequest/reject")
    public ResponseEntity<Integer> rejectMatCheckRequest(@RequestBody MatCheckRequest matCheckRequest) throws Exception {
        return ResponseEntity.ok().body(this.matCheckRequestService.rejectMatCheckRequest(matCheckRequest));
    }

    /**
     * 규제db검증
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/matcheckrequest/reguldbcheck")
    public ResponseEntity<List<MatCheckRequestDbVerf>> getRegulDbVertification(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // // 화학물질
        // int[] chemNos =
        // this.requestMapper.convertObjectListAsIntArray(map.get("chemNos"));
        // // 화학물질 대표값
        // String[] limitRepvals =
        // this.requestMapper.convertObjectListAsStringArray(map.get("limitRepvals"));
        // int chemCount = chemNos.length;

        String chemNos = map.containsKey("chemNos") ? map.get("chemNos").toString() : "";
        String limitRepvals = map.containsKey("limitRepvals") ? map.get("limitRepvals").toString() : "";
        int chemCount = map.containsKey("chemCount") ? Integer.parseInt("".equals(map.get("chemCount").toString()) ? "0" : map.get("chemCount").toString()) : 0;

        return ResponseEntity.ok().body(this.matCheckRequestService.getRegulDbVertification(chemNos, limitRepvals, chemCount, defaultParam));
    }

}
