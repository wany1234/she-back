package com.she.health.infirmary.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.she.health.infirmary.service.InfirmaryService;
import com.she.health.model.CheckupResult;
import com.she.health.model.Consult;
import com.she.health.model.HearingMgr;
import com.she.health.model.InfirmaryUsage;
import com.she.health.model.Suspect;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

/**
 * 건강관리실메뉴
 *
 */
@RestController
@RequestMapping("/api/hea/infirmary")
public class InfirmaryController {

    private final Logger logger = LoggerFactory.getLogger(InfirmaryController.class);

    @Autowired
    private InfirmaryService infirmaryService;

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private String excelDownloadFileName = "청력관리대상자.xlsx";
    private String excelDownloadFileConsult = "상담이력업로드_양식.xlsx";

    /**
     * 건강관리실 방문이력 조회
     *
     * @param parameter
     *            검색조건
     * @return 건강관리실 방문이력 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/infirmaryusagehistorys")
    public ResponseEntity<List<InfirmaryUsage>> getInfirmaryUsageHistorys(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String counselTypeCd = convertedParameter.containsKey("counselTypeCd") ? convertedParameter.get("counselTypeCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String[] duration = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("duration"));
        String startDt = "";
        String endDt = "";

        if (duration != null && duration.length == 2) {
            startDt = duration[0];
            endDt = duration[1];
        }

        return ResponseEntity.ok().body(infirmaryService.getInfirmaryUsageHistorys(plantCd, counselTypeCd, userNm, deptCd, startDt, endDt));
    }

    /**
     * 건강검진이력 조회
     *
     * @param parameter
     *            검색조건
     * @return 건강검진이력 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/checkuphistorys")
    public ResponseEntity<List<CheckupResult>> getCheckupHistorys(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";

        // 보건 홈>건강검진 이력
        String[] heaCheckedPeriod = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("heaCheckedPeriod"));
        String startYmd = "";
        String endYmd = "";
        if (heaCheckedPeriod != null && heaCheckedPeriod.length == 2) {
            startYmd = heaCheckedPeriod[0];
            endYmd = heaCheckedPeriod[1];
        }
        String heaCheckupClassCd = convertedParameter.containsKey("heaCheckupClassCd") ? convertedParameter.get("heaCheckupClassCd").toString() : "";
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";

        return ResponseEntity.ok().body(infirmaryService.getCheckupHistorys(userNm, deptCd, startYmd, endYmd, heaCheckupClassCd, plantCd));
    }

    /**
     * 관리대상 유소견자 조회
     *
     * @param
     * @return 관리대상 유소견자 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/suspectusers")
    public ResponseEntity<List<Suspect>> getSuspectUsers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String userId = convertedParameter.containsKey("userId") ? convertedParameter.get("userId").toString() : "";
        String suspectYn = convertedParameter.containsKey("suspectYn") ? convertedParameter.get("suspectYn").toString() : "";
        String startYear = convertedParameter.containsKey("startYear") ? convertedParameter.get("startYear").toString() : "";
        String endYear = convertedParameter.containsKey("endYear") ? convertedParameter.get("endYear").toString() : "";

        // 하위부서 포함여부
        String deptSubYn = convertedParameter.containsKey("deptSubYn") ? convertedParameter.get("deptSubYn").toString() : "Y";

        List<Suspect> suspectUserList = infirmaryService.getSuspectUsers(plantCd, deptCd, userId, suspectYn, startYear, endYear, deptSubYn);
        return ResponseEntity.ok().body(suspectUserList);
    }

    /**
     * 관리대상 유소견자 지정 목록 조회(지정자 추가 팝업)
     *
     * @param
     * @return 관리대상 유소견자 지정 목록 조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/suspectappointusers")
    public ResponseEntity<List<Suspect>> getSuspectAppointUsers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);

        int heaCheckupPlanNo = convertedParameter.containsKey("heaCheckupPlanNo") ? Integer.parseInt(convertedParameter.get("heaCheckupPlanNo").toString()) : 0;
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String suspectYn = convertedParameter.containsKey("suspectYn") ? convertedParameter.get("suspectYn").toString() : "";
        String heaCheckedYear = convertedParameter.containsKey("heaCheckedYear") ? convertedParameter.get("heaCheckedYear").toString() : "";
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";

        List<Suspect> suspects = infirmaryService.getSuspectAppointUsers(heaCheckupPlanNo, deptCd, userNm, suspectYn, heaCheckedYear, plantCd);
        return ResponseEntity.ok().body(suspects);
    }

    /**
     * 관리대상 유소견자 해제 목록 조회(해제자 추가 팝업)
     *
     * @param
     * @return 관리대상 유소견자 해제 목록 조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/suspectreleaseusers")
    public ResponseEntity<List<Suspect>> getSuspectReleaseUsers(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);

        int heaCheckupPlanNo = convertedParameter.containsKey("heaCheckupPlanNo") ? Integer.parseInt(convertedParameter.get("heaCheckupPlanNo").toString()) : 0;
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String heaCheckedYear = convertedParameter.containsKey("heaCheckedYear") ? convertedParameter.get("heaCheckedYear").toString() : "";
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";

        List<Suspect> suspects = infirmaryService.getSuspectReleaseUsers(heaCheckupPlanNo, deptCd, userNm, heaCheckedYear, plantCd);
        return ResponseEntity.ok().body(suspects);
    }

    /**
     * 관리대상 유소견자 지정 목록 조회
     *
     * @param
     * @return 관리대상 유소견자 지정 목록 조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/suspectusersofall")
    public ResponseEntity<List<Suspect>> getSuspectUsersOfAll(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String[] period = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("period"));
        String startDt = "";
        String endDt = "";
        if (period != null && period.length == 2) {
            startDt = period[0];
            endDt = period[1];
        }

        List<Suspect> suspects = infirmaryService.getSuspectUsersOfAll(plantCd, userNm, startDt, endDt);
        return ResponseEntity.ok().body(suspects);
    }

    /**
     * 관리대상 유소견자 지정/해제 목록 조회
     *
     * @param 검색조건
     * @return 관리대상 유소견자 지정/해제 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/suspectusers/{suspectReqNo}")
    public ResponseEntity<Suspect> getSuspectUsersOfTarget(@PathVariable("suspectReqNo") int suspectReqNo) throws Exception {
        Suspect results = infirmaryService.getSuspectUsersOfTarget(suspectReqNo);
        return ResponseEntity.ok().body(results);
    }

    /**
     * 관리대상 유소견자 지정
     *
     * @param suspect
     *            유소견자
     * @return 오류여부
     * @throws Exception
     *             예외
     */
    @PostMapping("/requstsuspectusers")
    public ResponseEntity<Integer> requestSuspectUsers(@RequestBody Suspect suspect) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.requestSuspectUsers(suspect));
    }

    /**
     * 관리대상 유소견자 지정
     *
     * @param List<CheckupResult>
     * @return 오류여부
     * @throws Exception
     *             예외
     */
    @PutMapping("/requstsuspectusers")
    public ResponseEntity<Integer> updateSuspectUsers(@RequestBody Suspect suspect) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.updateSuspectUsers(suspect));
    }

    /**
     * 관리대상 유소견자 지정
     *
     * @param List<CheckupResult>
     * @return 오류여부
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/createsuspectuser")
    public ResponseEntity<Integer> createSuspectUser(@RequestBody List<CheckupResult> checkupResults) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.createSuspectUser(checkupResults));
    }

    /**
     * 유소견자 건강상담이력 조회
     *
     * @param userId
     *            사용자아이디
     * @return 유소견자 건강상담이력 목록
     * @throws Exception
     *             예외
     */
    @GetMapping("/consults")
    public ResponseEntity<List<Consult>> getConsults(@RequestParam HashMap<String, Object> parameter) throws Exception {

        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String counselTypeCd = convertedParameter.containsKey("counselTypeCd") ? convertedParameter.get("counselTypeCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String userId = convertedParameter.containsKey("userId") ? convertedParameter.get("userId").toString() : "";
        String[] visitPeriod = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("visitPeriod"));

        String startYmd = "";
        String endYmd = "";

        if (visitPeriod != null && visitPeriod.length == 2) {
            startYmd = visitPeriod[0];
            endYmd = visitPeriod[1];
        }

        return ResponseEntity.ok().body(infirmaryService.getConsults(userId, startYmd, endYmd, plantCd, counselTypeCd, userNm, deptCd));
    }

    /**
     * 유소견자 건강상담이력 상세 조회
     *
     * @param heaConsultNo
     *            건강상담번호
     * @return 유소견자 건강상담이력 상세 조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/consult/{heaConsultNo}")
    public ResponseEntity<Consult> getConsult(@PathVariable("heaConsultNo") int heaConsultNo) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.getConsult(heaConsultNo));
    }

    /**
     * 유소견자 건강상담이력 신규 등록
     *
     * @param consult
     *            (유소견자)건강 상담
     * @return heaConsultNo 건강상담번호
     * @throws Exception
     *             예외
     */
    @PostMapping("/consult")
    public ResponseEntity<Integer> createConsult(@RequestBody Consult consult) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.createConsult(consult));
    }

    /**
     * 유소견자 건강상담이력 수정
     *
     * @param consult
     *            (유소견자)건강 상담
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    @PutMapping("/consult")
    public ResponseEntity<Integer> updateConsult(@RequestBody Consult consult) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.updateConsult(consult));
    }

    /**
     * 유소견자 건강상담 이력 삭제
     *
     * @param List<Consult>
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/consult")
    public ResponseEntity<Integer> deleteConsult(@RequestBody List<Consult> consults) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.deleteConsult(consults));
    }

    /**
     * 청력관리대상자 조회
     *
     * @param plantCd
     *            사업장코드
     * @param chkDt
     *            검사기간
     * @param deptCd
     *            부서코드
     * @param userNm
     *            성명
     * @return 청력관리대상자 조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/hearingmgrs")
    public ResponseEntity<List<HearingMgr>> getHearingMgrs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String[] duration = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("duration"));
        String startDt = "";
        String endDt = "";

        // 하위부서 포함여부
        String deptSubYn = convertedParameter.containsKey("deptSubYn") ? convertedParameter.get("deptSubYn").toString() : "Y";

        if (duration != null && duration.length == 2) {
            startDt = duration[0];
            endDt = duration[1];
        }

        List<HearingMgr> hearingMgr = infirmaryService.getHearingMgrs(plantCd, userNm, deptCd, startDt, endDt, deptSubYn);
        return ResponseEntity.ok().body(hearingMgr);
    }

    /**
     * 청력관리대상자 엑셀 다운로드
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/exceldown/hearingmgrs")
    public byte[] getHearingMgrsExcel(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> convertedParameter = requestMapper.convertAsParameter(parameter);
        String plantCd = convertedParameter.containsKey("plantCd") ? convertedParameter.get("plantCd").toString() : "";
        String userNm = convertedParameter.containsKey("userNm") ? convertedParameter.get("userNm").toString() : "";
        String deptCd = convertedParameter.containsKey("deptCd") ? convertedParameter.get("deptCd").toString() : "";
        String[] duration = this.requestMapper.convertObjectListAsStringArray(convertedParameter.get("duration"));
        String startDt = "";
        String endDt = "";

        if (duration != null && duration.length == 2) {
            startDt = duration[0].substring(0, 4);
            endDt = duration[1].substring(0, 4);
        }

        return infirmaryService.getHearingMgrsExcel(plantCd, userNm, deptCd, startDt, endDt).getBytes("UTF-8");
    }

    /**
     * 청력관리대상자 엑셀 업로드
     *
     * @return 업로드 결과
     * @throws Exception
     */
    @GetMapping("/excel/hearingmgrs")
    public ResponseEntity<Map<String, Object>> getUploadExcelHearingMgrs(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String uploadUserId = map.containsKey("createUserId") ? map.get("createUserId").toString() : "";

        Map<String, Object> resultMap = infirmaryService.uploadExcelHearingMgrs(taskClassNm, taskKey, uploadUserId);
        return ResponseEntity.ok().body(resultMap);
    }

    /**
     * 청력관리대상자 엑셀 양식 다운로드
     *
     * @return 엑셀업로드 양식
     * @throws Exception
     */
    @GetMapping("/excel/hearingmgrsexceldownload")
    public @ResponseBody byte[] downloadExcelHearingMgrs() throws Exception {
        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource classPathResource = new ClassPathResource("templates" + filePath.getCodeNm() + excelDownloadFileName);
        File file = classPathResource.getFile();
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
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

    /**
     * 청력관리대상자 수정
     *
     * @param hearingMgr
     *            청력관리대상자
     * @return 변경 행 수
     * @throws Exception
     *             예외
     */
    @PutMapping("/hearingmgr")
    public ResponseEntity<Integer> updateHearingMgrs(@RequestBody HearingMgr hearingMgr) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.saveHearingMgr(hearingMgr));
    }

    /**
     * 청력관리대상자 조회
     *
     * @param heaHearingMgrListNo
     *            청력관리대상자 번호
     * @return 청력관리대상자 조회
     * @throws Exception
     *             예외
     */
    @GetMapping("/hearingmgr/{heaHearingMgrListNo}")
    public ResponseEntity<HearingMgr> getHearingMgrs(@PathVariable int heaHearingMgrListNo) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.getHearingMgr(heaHearingMgrListNo));
    }

    /**
     * 청력관리대상자 조회
     *
     * @param heaHearingMgrListNo
     *            청력관리대상자 번호
     * @return 청력관리대상자 조회
     * @throws Exception
     *             예외
     */
    @DeleteMapping("/hearingmgr/{heaHearingMgrListNo}")
    public ResponseEntity<Integer> deleteHearingMgrs(@PathVariable int heaHearingMgrListNo) throws Exception {
        return ResponseEntity.ok().body(infirmaryService.deleteHearingMgr(heaHearingMgrListNo));
    }
    
    
    @GetMapping("/excel/consultdownload")
    public @ResponseBody byte[] downloadExcelConsult() throws Exception {
        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        ClassPathResource classPathResource = new ClassPathResource("templates" + filePath.getCodeNm() + excelDownloadFileConsult);
        File file = classPathResource.getFile();
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
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
    
    /**
     * 상담이력 엑셀 업로드
     *
     * @throws Exception
     */
    @PostMapping("/excelupload/consult")
    public ResponseEntity<Map<String, Object>> excelUploadConsult(@RequestParam("createUserId") String createUserId, @RequestParam("files") MultipartFile[] files) throws Exception {
        return ResponseEntity.ok().body(this.infirmaryService.consultUploadExcel(createUserId, files));
    }
}
