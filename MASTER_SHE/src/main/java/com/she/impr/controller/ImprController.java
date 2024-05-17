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
package com.she.impr.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.impr.service.ImprService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.RequestMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 개선조치사항 항목
 *
 */
@RestController
public class ImprController {
    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private ImprService imprService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;
    private static final Logger logger = LoggerFactory.getLogger(ImprController.class);

    /**
     * 개선조치사항 항목 조회
     *
     * @param parameter
     *            (개선분류코드 코드, 개선진행간계 코드, 조치구분, 제목, 개선요청부서 코드, 조치부서 코드, 요청일,
     *            관련테이블 PKID)
     * @return 작업허가서 항목 목록
     * @throws Exception
     */
    @GetMapping("/api/saf/impract/impracts")
    public ResponseEntity<List<ImprAct>> getImprActs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String startYmd = "";
        String endYmd = "";

        String limitStartYmd = "";
        String limitEndYmd = "";

        String year = map.containsKey("year") ? map.get("year").toString() : map.containsKey("assessYear") ? map.get("assessYear").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";
        String imprStepCd = map.containsKey("imprStepCd") ? map.get("imprStepCd").toString() : "";
        String actClassCd = map.containsKey("actClassCd") ? map.get("actClassCd").toString() : "";
        String imprTitle = map.containsKey("imprTitle") ? map.get("imprTitle").toString() : "";

        String imprRqstDeptCd = map.containsKey("imprRqstDeptCd") ? map.get("imprRqstDeptCd").toString() : "";
        // 하위부서 포함여부
        String imprRqstDeptSubYn = map.containsKey("imprRqstDeptSubYn") ? map.get("imprRqstDeptSubYn").toString() : "Y";

        String actDeptCd = map.containsKey("actDeptCd") ? map.get("actDeptCd").toString() : "";
        // 하위부서 포함여부
        String actDeptSubYn = map.containsKey("actDeptSubYn") ? map.get("actDeptSubYn").toString() : "Y";

        String actVendorCd = map.containsKey("actVendorCd") ? map.get("actVendorCd").toString() : "";
        // String deptVendorGubun = map.containsKey("deptVendorGubun") ?
        // map.get("deptVendorGubun").toString() : "";
        String subconnNm = map.containsKey("subconnNm") ? map.get("subconnNm").toString() : "";
        String[] imprRqstYmd = this.requestMapper.convertObjectListAsStringArray(map.get("imprRqstYmd"));
        int refTableId = map.containsKey("refTableId") ? Integer.parseInt(map.get("refTableId").toString()) : 0;
        String vendorSearchFlag = map.containsKey("vendorSearchFlag") ? map.get("vendorSearchFlag").toString() : "";
        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";
        ArrayList<Integer> refTableIdList = map.containsKey("refTableIdList") ? (ArrayList<Integer>) map.get("refTableIdList") : null;

        int monFlag = parameter.containsKey("monFlag") ? Integer.parseInt(parameter.get("monFlag").toString()) : 0;

        String strParam = map.containsKey("strParam") ? map.get("strParam").toString() : "";
        String subPlantCd = map.containsKey("subPlantCd") ? map.get("subPlantCd").toString() : "";
        String popupMode = map.containsKey("popupMode") ? map.get("popupMode").toString() : "";
        String startDt = map.containsKey("startDt") ? map.get("startDt").toString() : "";
        String endDt = map.containsKey("endDt") ? map.get("endDt").toString() : "";
        String imprChk = map.containsKey("imprChk") ? map.get("imprChk").toString() : "";
        String imprGubun = map.containsKey("imprGubun") ? map.get("imprGubun").toString() : "";
        String stateCd = map.containsKey("stateCd") ? map.get("stateCd").toString() : "";

        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));

        if (imprRqstYmd != null && imprRqstYmd.length == 2) {
            startYmd = imprRqstYmd[0];
            endYmd = imprRqstYmd[1];
        }

        if (period != null && period.length == 2) {
            limitStartYmd = period[0];
            limitEndYmd = period[1];
        }

        return ResponseEntity.ok().body(imprService.getImprActs(year, plantCd, imprClassCd, imprStepCd, actClassCd, imprTitle, imprRqstDeptCd, imprRqstDeptSubYn, actDeptCd, actDeptSubYn, actVendorCd, startYmd, endYmd, subconnNm, refTableId, vendorSearchFlag, apprFlag, refTableIdList, monFlag, strParam, subPlantCd, popupMode, startDt, endDt,
                limitStartYmd, limitEndYmd, imprChk, imprGubun, stateCd, defaultParam));
    }

    /**
     * 개선조치사항 상세 조회
     *
     * @param safImprActNo
     *            개선조치사항 번호
     * @return 개선조치사항 항목
     * @throws Exception
     */
    @GetMapping("/api/saf/impract/impract/{safImprActNo}")
    public ResponseEntity<ImprAct> getImprAct(@PathVariable("safImprActNo") int safImprActNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(imprService.getImprAct(safImprActNo, defaultParam));
    }

    /**
     * 개선조치사항 항목 생성
     *
     * @param imprAct
     *            개선조치사항 항목
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    @PostMapping("/api/saf/impract/impract")
    public ResponseEntity<Integer> createImprAct(@RequestBody ImprAct imprAct) throws Exception {
        return ResponseEntity.ok().body(imprService.createImprAct(imprAct));
    }

    /**
     * 개선조치사항 항목 수정
     *
     * @param imprAct
     *            개선조치사항 항목
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    @PutMapping("/api/saf/impract/impract")
    public ResponseEntity<Integer> updateImprAct(@RequestBody ImprAct imprAct) throws Exception {
        return ResponseEntity.ok().body(imprService.updateImprAct(imprAct));
    }

    /**
     * 개선조치사항 항목 취소(delYn 처리)
     *
     * @param safImprActNo
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    @PutMapping("/api/saf/impract/cancelimpract/{safImprActNo}")
    public ResponseEntity<Integer> cancelImprAct(@PathVariable("safImprActNo") int safImprActNo) throws Exception {
        return ResponseEntity.ok().body(imprService.cancelImprAct(safImprActNo));
    }

    /**
     * 개선조치사항 항목 삭제
     *
     * @param safImprActNo
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    @DeleteMapping("/api/saf/impract/impract")
    public ResponseEntity<Integer> deleteImprAct(@RequestBody String safImprActNo) throws Exception {
        return ResponseEntity.ok().body(imprService.deleteImprAct(safImprActNo));
    }

    /**
     * 개선조치사항 실적 조회
     *
     * @param parameter
     * @return 작업허가서 실적 목록
     * @throws Exception
     */
    @GetMapping("/api/saf/impract/impractstatus")
    public ResponseEntity<List<ImprAct>> getImprActStatus(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";

        String actDeptCd = map.containsKey("actDeptCd") ? map.get("actDeptCd").toString() : "";
        // 하위부서 포함여부
        String actDeptSubYn = map.containsKey("actDeptSubYn") ? map.get("actDeptSubYn").toString() : "Y";

        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        String actClassCd = map.containsKey("actClassCd") ? map.get("actClassCd").toString() : "";

        return ResponseEntity.ok().body(imprService.getImprActStatus(startYmd, endYmd, plantCd, imprClassCd, actDeptCd, actDeptSubYn, vendorCd, actClassCd, defaultParam));
    }

    /**
     * 개선결과 출력
     *
     * @param parameter
     *            조치부서코드
     * @return 개선결과 출력
     * @throws Exception
     */
    @ApiOperation(value = "개선결과 출력", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({ @ApiImplicitParam(name = "actDeptCd", value = "조치부서코드", required = false, dataType = "String", paramType = "path"), })
    @GetMapping("/api/saf/impract/printimpract")
    public @ResponseBody byte[] getPrint(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String startYmd = "";
        String endYmd = "";

        String year = map.containsKey("year") ? map.get("year").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";
        String imprStepCd = map.containsKey("imprStepCd") ? map.get("imprStepCd").toString() : "";
        String actClassCd = map.containsKey("actClassCd") ? map.get("actClassCd").toString() : "";
        String imprTitle = map.containsKey("imprTitle") ? map.get("imprTitle").toString() : "";

        String imprRqstDeptCd = map.containsKey("imprRqstDeptCd") ? map.get("imprRqstDeptCd").toString() : "";
        // 하위부서 포함여부
        String imprRqstDeptSubYn = map.containsKey("imprRqstDeptSubYn") ? map.get("imprRqstDeptSubYn").toString() : "Y";

        String actDeptCd = map.containsKey("actDeptCd") ? map.get("actDeptCd").toString() : "";
        // 하위부서 포함여부
        String actDeptSubYn = map.containsKey("actDeptSubYn") ? map.get("actDeptSubYn").toString() : "Y";

        String actVendorCd = map.containsKey("actVendorCd") ? map.get("actVendorCd").toString() : "";
        String subconnNm = map.containsKey("subconnNm") ? map.get("subconnNm").toString() : "";
        String[] imprRqstYmd = this.requestMapper.convertObjectListAsStringArray(map.get("imprRqstYmd"));
        int refTableId = map.containsKey("refTableId") ? Integer.parseInt(map.get("refTableId").toString()) : 0;
        String vendorSearchFlag = map.containsKey("vendorSearchFlag") ? map.get("vendorSearchFlag").toString() : "";
        String apprFlag = map.containsKey("apprFlag") ? map.get("apprFlag").toString() : "";

        if (imprRqstYmd != null && imprRqstYmd.length == 2) {
            startYmd = imprRqstYmd[0];
            endYmd = imprRqstYmd[1];
        }
        File file = this.imprService.getImprResultPrint(year, plantCd, imprClassCd, imprStepCd, actClassCd, imprTitle, imprRqstDeptCd, imprRqstDeptSubYn, actDeptCd, actDeptSubYn, actVendorCd, startYmd, endYmd, subconnNm, refTableId, vendorSearchFlag, apprFlag);

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

    /**
     * 즉시조치사항 저장
     *
     * @param imprActs
     *            개선조치사항 저장 목록
     * @return 변경 행 수
     * @throws Exception
     */
    @PostMapping("/api/saf/impract/immimpract")
    public ResponseEntity<Integer> saveImmImprAct(@RequestBody List<ImprAct> imprActs) throws Exception {
        return ResponseEntity.ok().body(imprService.saveImmImprAct(imprActs));
    }

    /**
     * 개선조치사항 항목 삭제
     *
     * @param imprActs
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    @DeleteMapping("/api/saf/impract/immimpract")
    public ResponseEntity<Integer> deleteImmImprAct(@RequestBody List<ImprAct> imprActs) throws Exception {
        return ResponseEntity.ok().body(imprService.deleteImprActs(imprActs));
    }

    /**
     * 개선조치사항 항목 일괄생성
     *
     * @param imprActs
     *            개선조치사항 항목 목록
     * @return 개선조치사항 항목 목록
     * @throws Exception
     */
    @PostMapping("/api/saf/impract/impracts")
    public ResponseEntity<List<ImprAct>> createImprActs(@RequestBody List<ImprAct> imprActs) throws Exception {
        return ResponseEntity.ok().body(imprService.createImprActs(imprActs));
    }

    /**
     * 개선조치사항 항목 일괄생성 linshe98@
     * 
     * @param imprActs
     *            개선조치사항 항목 목록
     * @return 개선조치사항 항목 목록
     * @throws Exception
     */
    @PutMapping("/api/saf/impract/impracts")
    public ResponseEntity<List<ImprAct>> updateImprActs(@RequestBody List<ImprAct> imprActs) throws Exception {
        return ResponseEntity.ok().body(imprService.updateImprActs(imprActs));
    }

    /**
     * 개선사항 템플릿 다운로드
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/api/saf/impract/planTemplate")
    public byte[] downloadPlanTemplate(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = requestMapper.convertAsParameter(parameter);
        String templetePath = "templates";
        String fileName = map.containsKey("fileName") ? map.get("fileName").toString() : ""; // 양식파일명
        // 파일 경로
        CodeMaster filePath = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");
        ClassPathResource resource = new ClassPathResource(templetePath + filePath.getCodeNm() + fileName);

        InputStream inputStream = null;
        try {
            File file = resource.getFile();
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException fe) {
            throw fe;
        } catch (IOException ie) {
            throw ie;
        } catch (Exception e) {
            throw e;
        } finally {
            inputStream.close();
        }
    }

    /**
     * 개선사항 엑셀 업로드
     *
     * @throws Exception
     */
    @PostMapping("/api/saf/impract/excelUpload")
    public ResponseEntity<Map<String, Object>> getUploadExcelData(@RequestParam("refTableId") int refTableId, @RequestParam("files") MultipartFile[] files) throws Exception {
        return ResponseEntity.ok().body(this.imprService.getUploadExcelData(refTableId, files));
    }

    /**
     * 개선사항 검증
     *
     * @throws Exception
     */
    @PostMapping("/api/saf/impract/validate")
    public ResponseEntity<Map<String, Object>> validateChk(@RequestBody List<HashMap<String, Object>> parameter) throws Exception {
        return ResponseEntity.ok().body(this.imprService.validateChk(parameter));
    }

    /**
     * 엑셀 업로드 등록
     * 
     * @param hashmap
     *            개선사항 항목
     * @throws Exception
     */
    @PostMapping("/api/saf/impract/excelinsert")
    public ResponseEntity<Integer> excelUploadCreateImprAct(@RequestBody List<HashMap<String, Object>> parameter) throws Exception {
        return ResponseEntity.ok().body(imprService.excelUploadCreateImprAct(parameter));
    }

    /**
     * 신규개선조치사항 탭항목 조회
     *
     * @param parameter
     *            imprClassCd, refTableId
     * @return 신규개선조치사항 탭항목 조회
     * @throws Exception
     */
    @GetMapping("/api/saf/impract/newimpracts")
    public ResponseEntity<List<ImprAct>> getNewImprActs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {

        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String imprClassCd = map.containsKey("imprClassCd") ? map.get("imprClassCd").toString() : "";
        int refTableId = map.containsKey("refTableId") ? Integer.parseInt(map.get("refTableId").toString()) : 0;

        return ResponseEntity.ok().body(imprService.getNewImprActs(imprClassCd, refTableId, defaultParam));
    }
}
