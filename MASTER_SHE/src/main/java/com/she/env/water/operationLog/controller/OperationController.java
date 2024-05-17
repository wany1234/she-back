package com.she.env.water.operationLog.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.she.env.water.model.ChemChkResult;
import com.she.env.water.model.DischChkResult;
import com.she.env.water.model.IngrChkResult;
import com.she.env.water.model.OpLogResult;
import com.she.env.water.model.PreventFacBreakHist;
import com.she.env.water.model.PreventFacRunTm;
import com.she.env.water.model.PwrcChkResult;
import com.she.env.water.model.SuplChkResult;
import com.she.env.water.model.TestItemResult;
import com.she.env.water.model.WasteDischFacRunTm;
import com.she.env.water.operationLog.service.OperationService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/env/water/operationlog/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private RequestMapper requestMapper;

    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    /**
     * 운영일지 조회
     *
     * @param yearMonth
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/dailyreports")
    public ResponseEntity<List<OpLogResult>> getDailyReports(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String[] searchPeriod = this.requestMapper.convertObjectListAsStringArray(map.get("evalPeriod"));
        String fromDate = "";
        String toDate = "";
        if (searchPeriod != null && searchPeriod.length == 2) {
            fromDate = searchPeriod[0];
            toDate = searchPeriod[1];
        }

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? map.get("ewtrCleanFacNo").toString() : "";
        String mgDeptCd = map.containsKey("mgDeptCd") ? map.get("mgDeptCd").toString() : "";
        String envOpLogStCd = map.containsKey("envOpLogStCd") ? map.get("envOpLogStCd").toString() : "";

        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        List<OpLogResult> opLogResultList = operationService.getDailyReports(fromDate, toDate, deptCd, mgDeptCd, plantCd, ewtrCleanFacNo, envOpLogStCd, defaultParam, deptSubYn);

        return ResponseEntity.ok().body(opLogResultList);
    }

    /**
     * 운영일지 조회
     *
     * @param yearMonth
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/dailyreport/check")
    public ResponseEntity<Integer> createOperationLogChk(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        String ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? map.get("ewtrCleanFacNo").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        int check = operationService.createOperationLogChk(measureYmd, ewtrCleanFacNo, plantCd, deptCd);

        return ResponseEntity.ok().body(check);
    }

    /**
     * 운영일지 삭제
     *
     * @param measureYmd
     * @param ewtrCleanFacNo
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/dailyreport/{measureYmd}/{ewtrCleanFacNo}")
    public ResponseEntity<Integer> deleteOperationLog(@PathVariable("measureYmd") String measureYmd, @PathVariable("ewtrCleanFacNo") String ewtrCleanFacNo) throws Exception {
        int delete = operationService.deleteOperationLog(measureYmd, ewtrCleanFacNo);
        return ResponseEntity.ok().body(delete);
    }

    /**
     * 운영일지 상세조회
     *
     * @param measureYmd
     *            운영일지측정일
     * @return OpLogResult 운영일지
     * @throws Exception
     *             예외
     */
    @GetMapping("/dailyreportinfo")
    public ResponseEntity<OpLogResult> getDailyReport(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        OpLogResult opLogResult = operationService.getDailyReport(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
        return ResponseEntity.ok().body(opLogResult);
    }

    /**
     * 운영일지 수정
     *
     * @param OpLogResult
     *            운영일지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/dailyreport")
    public ResponseEntity<Integer> updateDailyReport(@RequestBody OpLogResult opLogResult) throws Exception {
        return ResponseEntity.ok().body(operationService.updateDailyReport(opLogResult));
    }

    /**
     * 운영일지 슬러지 수정
     *
     * @param OpLogResult
     *            운영일지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/sludge")
    public ResponseEntity<Integer> updateSludge(@RequestBody OpLogResult opLogResult) throws Exception {
        return ResponseEntity.ok().body(operationService.updateSludge(opLogResult));
    }

    /**
     * 수질 운영일지 신규등록
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    @PostMapping("/dailyreport")
    public ResponseEntity<Integer> createOperationLog(@RequestBody OpLogResult opLogResult) throws Exception {
        return ResponseEntity.ok().body(this.operationService.createOperationLog(opLogResult));
    }

    /**
     * 방지시설 고장유무 및 특기사항
     *
     * @param measureYmd
     * @return 방지시설 고장유무 및 특기사항 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/prevnetbreakhists")
    public ResponseEntity<List<PreventFacBreakHist>> getPreventBreakHists(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<PreventFacBreakHist> prevnetbreakhists = operationService.getPreventBreakHists(measureYmd, ewtrCleanFacNo, plantCd);

        return ResponseEntity.ok().body(prevnetbreakhists);
    }

    /**
     * 공급수 사용량 조회
     *
     * @param measureYmd
     * @return 공급수 사용량 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/suplchkresults")
    public ResponseEntity<List<SuplChkResult>> getSuplChkResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<SuplChkResult> suplChkResultList = operationService.getSuplChkResults(measureYmd, ewtrCleanFacNo, plantCd, deptCd, defaultParam);

        return ResponseEntity.ok().body(suplChkResultList);
    }

    /**
     * 공급수 사용량 수정
     *
     * @param List<SuplChkResult>
     * @return 성공여부
     * @throws Exception
     *             예외
     */
    @PutMapping("/suplchkresult")
    public ResponseEntity<Integer> updateSuplChkResult(@RequestBody List<SuplChkResult> suplChkResults) throws Exception {
        return ResponseEntity.ok().body(operationService.updateSuplChkResult(suplChkResults));
    }

    /**
     * 배출수 사용량 조회
     *
     * @param measureYmd
     * @return 배출수 사용량 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/dischchkresults")
    public ResponseEntity<List<DischChkResult>> getDischChkResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<DischChkResult> dischChkResultList = operationService.getDischChkResults(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);

        return ResponseEntity.ok().body(dischChkResultList);
    }

    /**
     * 배출수 사용량 수정
     *
     * @param List<DischChkResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/dischchkresult")
    public ResponseEntity<Integer> updateDischChkResult(@RequestBody List<DischChkResult> dischChkResults) throws Exception {
        return ResponseEntity.ok().body(operationService.updateDischChkResult(dischChkResults));
    }

    /**
     * 약품 사용량 조회
     *
     * @param measureYmd
     * @return 약품 사용량 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/chemchkresults")
    public ResponseEntity<List<ChemChkResult>> getChemChkResults(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<ChemChkResult> chemChkResultList = operationService.getChemChkResults(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);

        return ResponseEntity.ok().body(chemChkResultList);
    }

    /**
     * 약품 사용량 수정
     *
     * @param List<ChemChkResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/chemchkresult")
    public ResponseEntity<Integer> updateChemChkResult(@RequestBody List<ChemChkResult> chemChkResults) throws Exception {
        return ResponseEntity.ok().body(operationService.updateChemChkResult(chemChkResults));
    }

    /**
     * 전력 사용량 조회
     *
     * @param measureYmd
     * @return 전력 사용량 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/pwrcchkresults")
    public ResponseEntity<List<PwrcChkResult>> getPwrcChkResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<PwrcChkResult> pwrcChkResultList = operationService.getPwrcChkResults(measureYmd, ewtrCleanFacNo, deptCd);

        return ResponseEntity.ok().body(pwrcChkResultList);
    }

    /**
     * 전력 사용량 수정
     *
     * @param List<PwrcChkResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/pwrcchkresult")
    public ResponseEntity<Integer> updatePwrcChkResult(@RequestBody List<PwrcChkResult> pwrcChkResults) throws Exception {
        return ResponseEntity.ok().body(operationService.updatePwrcChkResult(pwrcChkResults));
    }

    /**
     * 약품/첨가제 사용량 조회
     *
     * @param measureYmd
     * @return 약품/첨가제 사용량 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/ingrchkresults")
    public ResponseEntity<List<IngrChkResult>> getIngrChkResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<IngrChkResult> ingrChkResultList = operationService.getIngrChkResults(measureYmd, ewtrCleanFacNo, deptCd);

        return ResponseEntity.ok().body(ingrChkResultList);
    }

    /**
     * 약품/첨가제 사용량 수정
     *
     * @param List<IngrChkResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/ingrchkresult")
    public ResponseEntity<Integer> updateIngrChkResult(@RequestBody List<IngrChkResult> ingrChkResults) throws Exception {
        return ResponseEntity.ok().body(operationService.updateIngrChkResult(ingrChkResults));
    }

    /**
     * 자가측정 검사결과 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/testitemresultsNew")
    public ResponseEntity<List<HashMap<String, Object>>> getTestItemResultsNew(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<HashMap<String, Object>> testItemResultList = operationService.getTestItemResultsNew(measureYmd, ewtrCleanFacNo, plantCd, operationService.getTestItemResultsNewItems(measureYmd, ewtrCleanFacNo, plantCd));

        return ResponseEntity.ok().body(testItemResultList);
    }

    /**
     * 자가측정 검사결과 신규 항목 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/newitems")
    public ResponseEntity<List<TestItemResult>> getTestItemResultsNewItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";

        List<TestItemResult> testItemResultList = operationService.getTestItemResultsNewItems(measureYmd, ewtrCleanFacNo, plantCd);

        return ResponseEntity.ok().body(testItemResultList);
    }

    /**
     * 자가측정 검사결과 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/testitemresults")
    public ResponseEntity<List<HashMap<String, Object>>> getTestItemResults(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<HashMap<String, Object>> testItemResultList = operationService.getTestItemResults(measureYmd, ewtrCleanFacNo, deptCd, plantCd);

        return ResponseEntity.ok().body(testItemResultList);
    }

    /**
     * 자가측정 검사결과 수정
     *
     * @param List<TestItemResult>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/testitemresult")
    public ResponseEntity<Integer> updateTestItemResult(@RequestBody List<HashMap<String, Object>> testItemResults) throws Exception {
        return ResponseEntity.ok().body(operationService.updateTestItemResult(testItemResults));
    }

    /**
     * 배출시설 가동시간 조회
     *
     * @param measureYmd
     * @return 배출시설 가동시간 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/dischruntms")
    public ResponseEntity<List<WasteDischFacRunTm>> getDischRunTms(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<WasteDischFacRunTm> wasteDischFacRunTmList = operationService.getDischRunTms(measureYmd, ewtrCleanFacNo, deptCd, defaultParam);

        return ResponseEntity.ok().body(wasteDischFacRunTmList);
    }

    /**
     * 배출시설 가동시간 수정
     *
     * @param List<WasteDischFacRunTm>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/dischruntm")
    public ResponseEntity<Integer> updateDischRunTm(@RequestBody List<WasteDischFacRunTm> wasteDischFacRunTms) throws Exception {
        return ResponseEntity.ok().body(operationService.updateDischRunTm(wasteDischFacRunTms));
    }

    /**
     * 방지시설 가동시간 조회
     *
     * @param measureYmd
     * @return 방지시설 가동시간 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/preventruntms")
    public ResponseEntity<List<PreventFacRunTm>> getPreventRunTms(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<PreventFacRunTm> preventFacRunTmList = operationService.getPreventRunTms(measureYmd, ewtrCleanFacNo, deptCd);

        return ResponseEntity.ok().body(preventFacRunTmList);
    }

    /**
     * 방지시설 가동시간 수정
     *
     * @param List<PreventFacRunTm>
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/preventruntm")
    public ResponseEntity<Integer> updatePreventRunTm(@RequestBody List<PreventFacRunTm> preventFacRunTms) throws Exception {
        return ResponseEntity.ok().body(operationService.updatePreventRunTm(preventFacRunTms));
    }

    /**
     * 수질 운영현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 수질 운영현황
     * @throws Exceptionoperationstatus
     */
    @GetMapping("/operationstatus")
    public ResponseEntity<List<HashMap<String, Object>>> getOperationStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : ""; // 사업장
        String searchPeriod = map.containsKey("searchPeriod") ? map.get("searchPeriod").toString() : ""; // 검색기간
        String totalTypeCd = map.containsKey("totalTypeCd") ? map.get("totalTypeCd").toString() : ""; // 집계유형
        String ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? map.get("ewtrCleanFacNo").toString() : ""; // 클린센터
        String search = map.containsKey("search") ? map.get("search").toString() : ""; // 명칭
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : ""; // 관리부서

        List<HashMap<String, Object>> list = this.operationService.getOperationStatus(searchPeriod, plantCd, totalTypeCd, ewtrCleanFacNo, search, deptCd, defaultParam);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/statusnewitems")
    public ResponseEntity<List<TestItemResult>> getTestItemResultStatusNewItems(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        List<TestItemResult> list = this.operationService.getTestItemResultStatusNewItems(plantCd);

        return ResponseEntity.ok().body(list);
    }

    /**
     * 수질 운영일지 출력
     *
     * @param parameter
     *            검색조건
     * @return 수질 운영현황
     * @throws Exception
     */
    @GetMapping("/operationlogreportexcel/{ewtrCleanFacNo}/{measureYmd}/{plantCd}/{deptCd}")
    public @ResponseBody byte[] getPrintOplog(@PathVariable("ewtrCleanFacNo") int ewtrCleanFacNo, @PathVariable("measureYmd") String measureYmd, @PathVariable("plantCd") String plantCd, @PathVariable("deptCd") String deptCd, @ModelAttribute DefaultParam defaultParam) throws Exception {
        File file = this.operationService.getPrintOplog(ewtrCleanFacNo, measureYmd, plantCd, deptCd, defaultParam);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            throw fe;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            throw ie;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        } finally {
            inputStream.close();
        }
    }

    @GetMapping("/dailyreportlist")
    public ResponseEntity<List<OpLogResult>> DailyReportList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String measureYmd = map.containsKey("measureYmd") ? map.get("measureYmd").toString() : "";
        int ewtrCleanFacNo = map.containsKey("ewtrCleanFacNo") ? Integer.parseInt(map.get("ewtrCleanFacNo").toString()) : 0;
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";

        List<OpLogResult> opLogResultList = operationService.DailyReportList(measureYmd, ewtrCleanFacNo, plantCd, deptCd);

        return ResponseEntity.ok().body(opLogResultList);
    }

}
