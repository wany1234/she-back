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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.she.common.model.DefaultParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

import com.she.env.waste.disposal.service.DisposalResultService;
import com.she.env.waste.model.DisposalMtd;
import com.she.env.waste.model.DisposalRequest;
import com.she.env.waste.model.DisposalResult;
import com.she.utils.JcoUtil;
import com.she.utils.RequestMapper;

/**
 * 폐기물 처리결과
 *
 */
@RestController("EwstDisposalResultController")
@RequestMapping("api/env/waste/disposal")
public class DisposalResultController {
	private final Logger logger = LoggerFactory.getLogger(DisposalResultController.class);



    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private DisposalResultService disposalResultService;

    /**
     * 폐기물 처리결과 전체 조회
     *
     * @param parameter
     *            검색조건
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    @GetMapping("/disposalresults")
    public ResponseEntity<List<DisposalResult>> getDisposalResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        int ewstDispoReqNo = map.containsKey("ewstDispoReqNo") ? Integer.parseInt(map.get("ewstDispoReqNo").toString()) : 0;
        String ewstClassCd = map.containsKey("ewstClassCd") ? map.get("ewstClassCd").toString() : "";
        String dispoDeptCd = map.containsKey("dispoDeptCd") ? map.get("dispoDeptCd").toString() : "";

        String ewstDispoStCd = map.containsKey("ewstDispoStCd") ? map.get("ewstDispoStCd").toString() : "";
        String[] reqYmdPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("reqYmdPeriod"));
        String reqYmdFrom = "";
        String reqYmdTo = "";
        if (reqYmdPeriod != null && reqYmdPeriod.length == 2) {
            reqYmdFrom = reqYmdPeriod[0];
            reqYmdTo = reqYmdPeriod[1];
        }

        String ewstDispoCoNo = map.containsKey("ewstDispoCoNo") ? map.get("ewstDispoCoNo").toString() : "";
        String ewstFreightCoNo = map.containsKey("ewstFreightCoNo") ? map.get("ewstFreightCoNo").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String ewstWasteNo = map.containsKey("ewstWasteNo") ? map.get("ewstWasteNo").toString() : "";

        List<DisposalResult> disposalResults = this.disposalResultService.getDisposalResults(dispoDeptCd, reqYmdFrom, reqYmdTo, ewstDispoStCd, ewstClassCd, ewstDispoReqNo, ewstDispoCoNo, ewstFreightCoNo, plantCd, ewstWasteNo, defaultParam);

        return ResponseEntity.ok().body(disposalResults);
    }

    /**
     * 선택된 폐기물 처리결과 상세 조회
     *
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 폐기물 처리결과
     * @throws Exception
     */
    @GetMapping("/disposalresult/{ewstDispoResultNo}")
    public ResponseEntity<DisposalResult> getDisposalResult(@PathVariable(name = "ewstDispoResultNo") int ewstDispoResultNo, DefaultParam defaultParam) throws Exception {
        DisposalResult disposalResult = this.disposalResultService.getDisposalResult(ewstDispoResultNo, defaultParam);
        return ResponseEntity.ok().body(disposalResult);
    }


    /**
     * 선택된 폐기물 처리요청 조회
     *
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 폐기물 요청결과
     * @throws Exception
     */
    @GetMapping("/getresult/{ewstDispoResultNo}")
    public ResponseEntity<List<DisposalRequest>> getResult(@PathVariable(name = "ewstDispoResultNo") int ewstDispoResultNo, DefaultParam defaultParam) throws Exception {
    	List<DisposalRequest> disposalResult = this.disposalResultService.getResult(ewstDispoResultNo, defaultParam);
        return ResponseEntity.ok().body(disposalResult);
    }

    /**
     * 폐기물 처리결과 신규 생성
     *
     * @param disposalResult
     *            폐기물 처리결과
     * @return 폐기물 처리결과 번호
     * @throws Exception
     */
    @PostMapping("/disposalresult")
    public ResponseEntity<Integer> createDisposalResult(@RequestBody DisposalResult disposalResult) throws Exception {
        return ResponseEntity.ok().body(this.disposalResultService.createDisposalResult(disposalResult));
    }

    /**
     * 선택된 폐기물 처리결과 수정
     *
     * @param disposalResult
     *            폐기물 처리결과
     * @return 수정행수
     * @throws Exception
     */
    @PutMapping("/disposalresult")
    public ResponseEntity<Integer> updateDisposalResult(@RequestBody DisposalResult disposalResult, DefaultParam defaultParam) throws Exception {
        Integer count = this.disposalResultService.updateDisposalResult(disposalResult, defaultParam);

        return ResponseEntity.ok().body(count);
    }

    /**
     * 선택된 폐기물 처리결과 완료처리
     *
     * @param disposalResult
     * @return 수정행수
     * @throws Exception
     */
    @PostMapping("/disposalresultcomplete")
    public ResponseEntity<Integer> completeDisposalResult(@RequestBody DisposalResult disposalResult) throws Exception {
        return ResponseEntity.ok().body(this.disposalResultService.completeDisposalResult(disposalResult));
    }

    /**
     * 폐기물 처리방법 전체 조회
     *
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    @GetMapping("/disposalresultmtd")
    public ResponseEntity<List<DisposalMtd>> getDisposalResultsMtd() throws Exception {
        return ResponseEntity.ok().body(this.disposalResultService.getDisposalResultsMtd());
    }

    /**
     * 선택된 폐기물 처리결과 완료처리
     *
     * @param disposalResult
     * @return 수정행수
     * @throws Exception
     */
    @DeleteMapping("/disposalresult/{ewstDispoResultNo}")
    public ResponseEntity<Integer> deleteDisposalResult(@PathVariable(name = "ewstDispoResultNo") int ewstDispoResultNo) throws Exception {
        return ResponseEntity.ok().body(this.disposalResultService.deleteDisposalResult(ewstDispoResultNo));
    }

    /**
     * 폐기물 계근정보 조회
     *
     * @param 처리일자
     * @return 계근정보
     * @throws Exception
     */

    @GetMapping("/disposalresult/sysinfo")
    public List<Map<String, Object>> ifData(String reqYmdPeriod) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("I_ZIODAT", reqYmdPeriod);

        List<Map<String, Object>> datas = JcoUtil.getSapTableData("ZSHE_INTF_WASTE", "ET_WEIGH", params, true);
        for (Map<String, Object> data : datas) {
            String zIOGBNM = String.valueOf(data.get("ZIOGB")).toUpperCase();
            if ("I".equals(zIOGBNM)) {
                zIOGBNM = "반입";
            } else if ("O".equals(zIOGBNM)) {
                zIOGBNM = "반출";
            } else {
                data.put("ZIOGB", "O");
                zIOGBNM = "반출";
            }
            data.put("ZIOGBNM", zIOGBNM);
        }

        return datas;
    }

    /**
     * 폐기물 처리결과 엑셀 조회
     *
     * @param reqYmdFrom
     *            요청시작일
     * @param reqYmdTo
     *            요청종료일
     * @return 폐기물 처리결과 엑셀
     * @throws Exception
     */
    @GetMapping("/disposalresultexcel/{reqYmdFrom}/{reqYmdTo}")
    public ResponseEntity<InputStreamResource> createOperationExcelForm(@PathVariable String reqYmdFrom, @PathVariable String reqYmdTo, DefaultParam defaultParam) throws Exception {
        File file = this.disposalResultService.createDisposalResultExcel(reqYmdFrom, reqYmdTo, defaultParam);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);

            String downloadfileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadfileName + "\"");
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            return ResponseEntity.ok().headers(header).contentLength(file.length()).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(new InputStreamResource(inputStream));
        } catch (IOException e) {
        	logger.error(e.getMessage());
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	throw e;
        } finally {
            inputStream.close();
        }
        return null;
    }
}
