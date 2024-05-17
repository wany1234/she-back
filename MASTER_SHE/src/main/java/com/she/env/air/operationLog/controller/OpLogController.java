package com.she.env.air.operationLog.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.she.env.air.model.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.env.air.operationLog.service.OpLogService;
import com.she.utils.Methods;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("OpLogController")
@RequestMapping("api/env/air/oplog")
public class OpLogController {

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private OpLogService opLogService;

    /**
     * 운영일지-기본정보 조회
     *
     * @param plantCd
     * @param deptCd
     * @param mgDeptCd
     * @param fromDate
     * @param toDate
     * @return
     * @throws Exception
     */
    @GetMapping("/oplogs")
    public ResponseEntity<List<OpLogRslt>> getOplogs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 시작일
        String fromDate = map.containsKey("fromDate") ? map.get("fromDate").toString() : "";
        // 종료일
        String toDate = map.containsKey("toDate") ? map.get("toDate").toString() : "";
        // 작성부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 관리부서
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 진행상태
        String envOpLogStCd = map.containsKey("envOpLogStCd") ? map.get("envOpLogStCd").toString() : "";

        return ResponseEntity.ok().body(this.opLogService.getOplogs(plantCd, deptCd, mgDeptCd, fromDate, toDate, envOpLogStCd));
    }

    /**
     * 운영정보-기본정보 상세
     *
     * @param deptCd
     * @param measureYmd
     * @throws Exception
     */
    @GetMapping("/oplog/{plantCd}/{measureYmd}/{deptCd}")
    public ResponseEntity<OpLogRslt> getOplog(@PathVariable String plantCd, @PathVariable String measureYmd, @PathVariable String deptCd) throws Exception {
        return ResponseEntity.ok().body(this.opLogService.getOplog(plantCd, deptCd, measureYmd));

    }

    /**
     * 운영일지 전체 조회
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    @GetMapping("/oplogall/{plantCd}/{measureYmd}/{deptCd}") // /api/env/air/oplog/oplogall/3000/2022-05-04/30E9999
    public ResponseEntity<OpLogRslt> getAllOplog(@PathVariable String plantCd, @PathVariable String deptCd, @PathVariable String measureYmd) throws Exception {
        return ResponseEntity.ok().body(this.opLogService.getAllOplog(plantCd, deptCd, measureYmd));

    }

    /**
     * 배출구별 가동시간 조회
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    @GetMapping("/outletdischcheck/{plantCd}/{deptCd}/{measureYmd}")
    public ResponseEntity<List<OutletDischChkResult>> getOutletDischChkResult(@PathVariable String plantCd, @PathVariable String deptCd, @PathVariable String measureYmd) throws Exception {
        return ResponseEntity.ok().body(this.opLogService.getOutletDischChkResult(plantCd, deptCd, measureYmd));

    }

    /**
     * 운전사항
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    @GetMapping("/outletpeventcheck/{plantCd}/{deptCd}/{measureYmd}")
    public ResponseEntity<List<OutletPreventChkResult>> getOutletPreventChkResult(@PathVariable String plantCd, @PathVariable String deptCd, @PathVariable String measureYmd) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.getOutletPreventChkResult(plantCd, deptCd, measureYmd));

    }

    @GetMapping("/outletpreventchem/{deptCd}/{measureYmd}")
    public ResponseEntity<List<PreventChemResult>> getPreventChemResult(@PathVariable String deptCd, @PathVariable String measureYmd) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.getPreventChemResult(deptCd, measureYmd));
    }

    /**
     * 방지시설-보수내역
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    @GetMapping("/preventmainthist/{deptCd}/{measureYmd}")
    public ResponseEntity<List<PreventFacMaintResults>> getPreventMaintHist(@PathVariable String deptCd, @PathVariable String measureYmd) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.getPreventMaintHist(deptCd, measureYmd));

    }

    /**
     * 배출시설-연료
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    @GetMapping("/dischfuelresult/{deptCd}/{measureYmd}")
    public ResponseEntity<List<FuelCheckResult>> getDischFuelResult(@PathVariable String deptCd, @PathVariable String measureYmd) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.getDischFuelResult(deptCd, measureYmd));
    }

    /**
     * 운영일지-원료
     *
     * @param deptCd
     * @param measureYmd
     * @param plantCd
     * @return
     * @throws Exception
     */
    @GetMapping("/getingrchkresult/{deptCd}/{measureYmd}/{plantCd}")
    public ResponseEntity<List<IngrCheckResult>> getIngrChkResult(@PathVariable String deptCd, @PathVariable String measureYmd, @PathVariable String plantCd) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.getIngrChkResult(deptCd, measureYmd, plantCd));
    }

    /**
     * 운영일지 생성
     *
     * @param opLogRslt
     * @return
     * @throws Exception
     */
    @PutMapping("/updateoplogresult")
    public ResponseEntity<Integer> updateOplogResult(@RequestBody OpLogRslt opLogRslt) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.updateOplogResult(opLogRslt));

    }

    /**
     * 운영현황 조회
     *
     * @param fromYmd
     * @param toYmd
     * @param totalTypeCd
     * @param plantCd
     * @param search
     * @param deptCd
     * @param defaultParam
     * @return
     * @throws Exception
     */
    @GetMapping("/operationstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getOperationStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("searchPeriod"));
        //
        String totalTypeCd = map.containsKey("totalTypeCd") ? map.get("totalTypeCd").toString() : "";
        //
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        //
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        //
        String search = map.containsKey("search") ? map.get("search").toString() : "";
        String mainDischFacNm = map.containsKey("mainDischFacNm") ? map.get("mainDischFacNm").toString() : "";

        if (searchPeriod[0].length() == 7) {
            searchPeriod[0] = searchPeriod[0] + "-01";
        }
        if (searchPeriod[1].length() == 7) {
            Calendar toDate = Calendar.getInstance();
            toDate.setTime(Methods.convertStringToDate(searchPeriod[1] + "-01"));
            searchPeriod[1] = searchPeriod[1] + "-" + Methods.leftPad(String.valueOf(toDate.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, '0');
        }

        String fromYmd = "";
        String toYmd = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromYmd = searchPeriod[0];
            toYmd = searchPeriod[1];
        }

        return ResponseEntity.ok().body(this.opLogService.getOperationStatus(fromYmd, toYmd, totalTypeCd, plantCd, search, deptCd, mainDischFacNm, defaultParam));
    }

    /**
     * 대기운영일지 출력
     *
     * @param measureYmd
     *            측정날짜
     * @param deptCd
     *            부서코드
     * @return 운영일지 출력
     * @throws Exception
     */
    @ApiOperation(value = "운영일지 출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "measureYmd", value = "측정날짜", required = false, dataType = "String", paramType = "path"), @ApiImplicitParam(name = "deptCd", value = "부서코드", required = false, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "parameter", value = "안쓰는값", required = false, dataType = "java.util.HashMap", paramType = "query"), })
    @GetMapping("/operationlogreportexcel/{plantCd}/{measureYmd}/{deptCd}")
    public @ResponseBody byte[] getPrint(@PathVariable("plantCd") String plantCd, @PathVariable("measureYmd") String measureYmd, @PathVariable("deptCd") String deptCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        File file = this.opLogService.getEirOpLogResultPrint(plantCd, measureYmd, deptCd, defaultParam);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 대기 운영일지 기본정보 중복 조회
     *
     * @param parameter
     *            검색조건
     * @return 운영일지-기본정보 중복 행 수
     * @throws Exception
     */
    @GetMapping("/operationlogresult/check")
    public ResponseEntity<Integer> createOperationLogChk(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 작성부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 작성일자
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";

        return ResponseEntity.ok().body(this.opLogService.createOperationLogChk(measureYmd, deptCd, plantCd));
    }

    /**
     * 운영일지(관리자) 목록 조회
     *
     * @param plantCd
     * @param startYmd
     * @param endYmd
     * @return List<OpLogAdminRslt>
     * @throws Exception
     */
    @GetMapping("/oplogadmins")
    public ResponseEntity<List<OpLogAdminRslt>> getOplogAdmins(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        // 시작일
        String startYmd = map.containsKey("startYmd") ? map.get("startYmd").toString() : "";
        // 종료일
        String endYmd = map.containsKey("endYmd") ? map.get("endYmd").toString() : "";
        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 진행상태
        String stepCd = map.containsKey("stepCd") ? map.get("stepCd").toString() : "";
        System.out.println("tttttttt : " + stepCd);
        return ResponseEntity.ok().body(this.opLogService.getOplogAdmins(plantCd, startYmd, endYmd, stepCd));
    }

    /**
     * 운영일지(관리자) 상세 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return OpLogAdminRslt
     * @throws Exception
     */
    @GetMapping("/oplogadminall/{plantCd}/{measureYmd}")
    public ResponseEntity<OpLogAdminRslt> getOplogAdminAll(@PathVariable("plantCd") String plantCd, @PathVariable("measureYmd") String measureYmd) throws Exception {
        return ResponseEntity.ok().body(this.opLogService.getOplogAdminAll(plantCd, measureYmd));
    }

    /**
     * 운영일지(관리자) 작성부서 정보 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return List<HashMap<String, Object>>
     * @throws Exception
     */
    @GetMapping("/oplogadmin/dept/{plantCd}/{measureYmd}")
    public ResponseEntity<List<HashMap<String,Object>>> getOplogAdminDepts(@PathVariable("plantCd") String plantCd, @PathVariable("measureYmd") String measureYmd) throws Exception {
        return ResponseEntity.ok().body(this.opLogService.getOplogAdminDepts(plantCd, measureYmd));
    }

    /**
     * 운영일지(관리자) 생성
     *
     * @param opLogRslt
     * @return
     * @throws Exception
     */
    @PutMapping("/updateoplogresultadmin")
    public ResponseEntity<Integer> updateOplogResultAdmin(@RequestBody OpLogAdminRslt opLogAdminRslt) throws Exception {

        return ResponseEntity.ok().body(this.opLogService.updateOplogResultAdmin(opLogAdminRslt));

    }

}
