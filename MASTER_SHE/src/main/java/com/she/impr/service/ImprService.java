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
package com.she.impr.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.config.GlobalSettings;
import com.she.impr.mapper.ImprMapper;
import com.she.impr.model.ImprAct;
import com.she.impr.model.ImprActDashboard;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.safety.facilityCheck.mapper.FacilityCheckResultMapper;
import com.she.safety.facilityInspection.mapper.FacilityInspectionMapper;
import com.she.safety.model.FacilityCheckSchedule;
import com.she.safety.model.FacilityInspectionSchedule;
import com.she.security.util.StringUtil;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;
import com.she.utils.FileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * 개선조치사항 기능정의
 *
 */
@Service
public class ImprService {

    private static final Logger logger = LoggerFactory.getLogger(ImprService.class);

    private static final String RSA_PLAN_TASK_CLASS = "RSA_PLAN";

    @Autowired
    private ImprMapper imprMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private FacilityCheckResultMapper facilityCheckResultMapper;

    @Autowired
    private FacilityInspectionMapper facilityInspectionMapper;

    @Autowired
    private AttachFileService attachFileService;

    // TODO : 파일 업로드 정보 처리용 서비스
    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private GlobalSettings globalSettings;

    /**
     * 개선조치사항 항목 조회
     *
     * @param imprClassCd
     *            개선분류코드 코드
     * @param imprStepCd
     *            개선진행간계 코드
     * @param actClassCd
     *            조치구분
     * @param imprTitle
     *            제목
     * @param imprRqstDeptCd
     *            개선요청부서 코드
     * @param actDeptCd
     *            조치부서 코드
     * @param startYmd
     *            요청일(from)
     * @param endYmd
     *            요청일(to)
     * @param refTableId
     *            관련테이블 PKID
     * @return 개선조치사항 항목
     * @throws Exception
     */
    public List<ImprAct> getImprActs(String year, String plantCd, String imprClassCd, String imprStepCd, String actClassCd, String imprTitle, String imprRqstDeptCd, String imprRqstDeptSubYn, String actDeptCd, String actDeptSubYn, String actVendorCd, String startYmd, String endYmd, String subconnNm, int refTableId, String vendorSearchFlag,
            String apprFlag, ArrayList<Integer> refTableIdList, int monFlag, String strParam, String subPlantCd, String popupMode, String startDt, String endDt, String limitStartYmd, String limitEndYmd, String imprChk, String imprGubun, String stateCd, DefaultParam defaultParam) throws Exception {

        if (refTableIdList != null && refTableIdList.size() > 0) {
            List<ImprAct> imprActList = new ArrayList<>();
            for (int i = 0; i < refTableIdList.size(); i++) {
                int id = Integer.parseInt(String.valueOf(refTableIdList.get(i)));
                imprActList.addAll(imprMapper.getImprActs(year, plantCd, imprClassCd, imprStepCd, actClassCd, imprTitle, imprRqstDeptCd, imprRqstDeptSubYn, actDeptCd, actDeptSubYn, actVendorCd, startYmd, endYmd, subconnNm, id, vendorSearchFlag, "", apprFlag, monFlag, strParam, subPlantCd, popupMode, startDt, endDt, limitStartYmd, limitEndYmd,
                        imprChk, imprGubun, stateCd, defaultParam));
            }
            return imprActList;
        }
        return imprMapper.getImprActs(year, plantCd, imprClassCd, imprStepCd, actClassCd, imprTitle, imprRqstDeptCd, imprRqstDeptSubYn, actDeptCd, actDeptSubYn, actVendorCd, startYmd, endYmd, subconnNm, refTableId, vendorSearchFlag, "", apprFlag, monFlag, strParam, subPlantCd, popupMode, startDt, endDt, limitStartYmd, limitEndYmd, imprChk,
                imprGubun, stateCd, defaultParam);
    }

    /**
     * 개선조치사항 항목 조회
     *
     * @param safImprActNo
     *            개선조치사항 번호
     * @return 개선조치사항 항목
     * @throws Exception
     */
    public ImprAct getImprAct(int safImprActNo, DefaultParam defaultParam) throws Exception {
        return imprMapper.getImprAct(safImprActNo, defaultParam);
    }

    /**
     * 개선조치사항 항목 생성
     *
     * @param imprAct
     *            개선조치사항 항목
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    public int createImprAct(ImprAct imprAct) throws Exception {
        imprMapper.createImprAct(imprAct);
        return imprAct.getSafImprActNo();
    }

    /**
     * 개선조치사항 항목 수정
     *
     * @param imprAct
     *            개선조치사항 항목
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    public int updateImprAct(ImprAct imprAct) throws Exception {
        imprMapper.updateImprAct(imprAct);
        return imprAct.getSafImprActNo();
    }

    /**
     * 개선조치사항 항목 취소
     *
     * @param safImprActNo
     *            개선조치사항 Key값
     * @return 취소 행 수
     * @throws Exception
     */
    public int cancelImprAct(int safImprActNo) throws Exception {
        return imprMapper.cancelImprAct(safImprActNo);
    }

    /**
     * 개선조치사항 항목 삭제
     *
     * @param safImprActNo
     *            개선조치사항 Key값
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteImprAct(String safImprActNo) throws Exception {
        return imprMapper.deleteImprAct(safImprActNo);
    }

    /**
     * 개선조치사항 항목 연관테이블 key에 따른 삭제
     *
     * @param refTableId
     *            테이블 key
     * @return 삭제 성공 여부
     * @throws Exception
     */
    public int deleteImprActRefTableId(int refTableId) throws Exception {
        return imprMapper.deleteImprActRefTableId(refTableId);
    }

    /**
     * 개선조치사항 완료 버튼을 통해 즉시조치의 경우 마지막 step으로 개선요청 사항의 경우 미접수 step으로 update한다.
     *
     * @param imprClassCd
     *            개선분류코드
     * @param refTableId
     *            관련테이블PKID
     * @return 개선요청 데이터 건수
     * @throws Exception
     */
    public int updateImprStepCd(String imprClassCd, int refTableId, String updateUserId) throws Exception {
        // 즉시 조치 데이터
        List<ImprAct> immediateActions = imprMapper.getImprActs("", "", imprClassCd, "", ConstVal.SAF_ACT_CLASS_IMMEDIATE_ACTION, "", "", "", "", "", "", "", "", "", refTableId, "", "", "", 0, "", "", "", "", "", "", "", "", "", "", new DefaultParam("kr"));

        if (immediateActions != null && immediateActions.size() > 0) {
            for (ImprAct imprAct : immediateActions) {
                imprAct.setUpdateUserId(updateUserId);
                imprAct.setImprStepCd(ConstVal.SAF_IMPR_STEP_IMPROVED);
                imprMapper.updateImprAct(imprAct);
            }
        }

        // 개선요청 데이터
        List<ImprAct> requestImprovements = imprMapper.getImprActs("", "", imprClassCd, "", ConstVal.SAF_ACT_CLASS_REQUEST_IMPROVEMENT, "", "", "", "", "", "", "", "", "", refTableId, "", "", "", 0, "", "", "", "", "", "", "", "", "", "", new DefaultParam("kr"));

        if (requestImprovements != null && requestImprovements.size() > 0) {
            for (ImprAct imprAct : requestImprovements) {
                imprAct.setUpdateUserId(updateUserId);
                imprAct.setImprStepCd(ConstVal.SAF_IMPR_STEP_UNRELIEVED);
                imprMapper.updateImprAct(imprAct);
            }
        }

        return (requestImprovements != null && requestImprovements.size() > 0) ? requestImprovements.size() : 0;
    }

    /**
     * 개선조치사항 실적 조회
     *
     * @return 작업허가서 실적 목록
     * @throws Exception
     */
    public List<ImprAct> getImprActStatus(String startYmd, String endYmd, String plantCd, String imprClassCd, String actDeptCd, String actDeptSubYn, String vendorCd, String actClassCd, DefaultParam defaultParam) throws Exception {
        return imprMapper.getImprActStatus(startYmd, endYmd, plantCd, imprClassCd, actDeptCd, actDeptSubYn, vendorCd, actClassCd, defaultParam);
    }

    /**
     * 변경관리 진행단계 변경
     *
     * @param safImprActNo
     *            변경관리 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessImpr(int safImprActNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String imprStepCd = "";
        String stateCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(bizApprStepCd)) {
            ImprAct imprAct = this.imprMapper.getImprAct(safImprActNo, new DefaultParam("kr"));
            if (imprAct != null) {
                if (imprAct.getImprStepCd().equals(ConstVal.SAF_IMPR_STEP_MEASURES_ACTION)) {
                    stateCd = "IMPR4";
                }
            }

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            ImprAct imprAct = this.imprMapper.getImprAct(safImprActNo, new DefaultParam("kr"));
            if (imprAct != null) {
                if (imprAct.getImprStepCd().equals(ConstVal.SAF_IMPR_STEP_MEASURES_ACTION)) {
                    imprStepCd = ConstVal.SAF_IMPR_STEP_IMPROVED;
                    stateCd = "IMPR5";
                }
            }
        }

        // 변경관리 결재 반영
        resultNo += imprMapper.completeImpr(safImprActNo, apprRqstNo, imprStepCd, stateCd);
        return resultNo;
    }

    /**
     * 개선결과 출력
     *
     * @param actDeptCd
     *            조치부서코드
     * @return 대기운영일지 파일
     * @throws Exception
     */
    public File getImprResultPrint(String year, String plantCd, String imprClassCd, String imprStepCd, String actClassCd, String imprTitle, String imprRqstDeptCd, String imprRqstDeptSubYn, String actDeptCd, String actDeptSubYn, String actVendorCd, String startYmd, String endYmd, String subconnNm, int refTableId, String vendorSearchFlag,
            String apprFlag) throws Exception {
        // 개선결과 목록
        List<ImprAct> imprActs = imprMapper.getImprActs(year, plantCd, imprClassCd, imprStepCd, actClassCd, imprTitle, imprRqstDeptCd, imprRqstDeptSubYn, actDeptCd, actDeptSubYn, actVendorCd, startYmd, endYmd, subconnNm, refTableId, vendorSearchFlag, "", apprFlag, 0, "", "", "", "", "", "", "", "", "", "", new DefaultParam("kr"));

        // 이미지 없을때 경로
        String noImage = globalSettings.getNoImageFilePath();
        // 개선 전 사진
        String beforeImage = "";
        // 개선 후 사진
        String afterImage = "";

        List<ImprAct> newImprActs = new ArrayList<ImprAct>();
        for (int i = 0; i < imprActs.size(); i++) {
            ImprAct imprAct = imprActs.get(i);
            // 개선결과 개선 전,후 사진 세팅
            AttachFile beforeAttachFile = this.attachFileMapper.getFirstUploadFile("ACCTION_BEFORE", Integer.toString(imprAct.getSafImprActNo())) == null ? new AttachFile() : this.attachFileMapper.getFirstUploadFile("ACCTION_BEFORE", Integer.toString(imprAct.getSafImprActNo()));
            AttachFile afterAttachFile = this.attachFileMapper.getFirstUploadFile("ACCTION_AFTER", Integer.toString(imprAct.getSafImprActNo())) == null ? new AttachFile() : this.attachFileMapper.getFirstUploadFile("ACCTION_AFTER", Integer.toString(imprAct.getSafImprActNo()));

            // 개선 전 사진
            if (beforeAttachFile.getFileDownPath() == null) {
                beforeImage = noImage;
            } else {
                File check = new File(FileUtil.getStoreFilePath() + File.separator + beforeAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\"));
                beforeImage = check.isFile() && check.exists() ? FileUtil.getStoreFilePath() + File.separator + beforeAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\") : noImage;
            }
            // 개선 후 사진
            if (afterAttachFile.getFileDownPath() == null) {
                afterImage = noImage;
            } else {
                File check = new File(FileUtil.getStoreFilePath() + File.separator + afterAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\"));
                afterImage = check.isFile() && check.exists() ? FileUtil.getStoreFilePath() + File.separator + afterAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\") : noImage;
            }

            imprAct.setBefImg(beforeImage);
            imprAct.setAftImg(afterImage);

            // imprAct.setBefImg(beforeAttachFile.getFileDownPath() == null ?
            // noImage : FileUtil.getStoreFilePath() + File.separator +
            // beforeAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\"));
            // imprAct.setAftImg(afterAttachFile.getFileDownPath() == null ?
            // noImage : FileUtil.getStoreFilePath() + File.separator +
            // afterAttachFile.getFileDownPath().replaceAll("\\\\+", "\\\\"));

            // 안전점검-세부위치,설비점검-설비명,시설점검-시설명 세팅
            String gubun = imprAct.getImprClassCd();
            String locate = "";
            if (gubun.equals(ConstVal.SAF_IMPR_CLASS_CHECK)) {
                // 안전점검 일때
                locate = imprAct.getDtlLocat() == null ? "" : imprAct.getDtlLocat();
            } else if (gubun.equals(ConstVal.SAF_IMPR_CLASS_FACILITY)) {
                // 설비점검일때
                FacilityCheckSchedule facilityCheckSchedule = facilityCheckResultMapper.getFacilityCheckResult(imprAct.getRefTableId(), new DefaultParam("kr"));
                locate = facilityCheckSchedule == null ? "" : facilityCheckSchedule.getFacilityNm();
            } else if (gubun.equals(ConstVal.SAF_IMPR_CLASS_FACILITY_INSPECTION)) {
                // 시설점검일때
                FacilityInspectionSchedule facilityInspectionSchedule = facilityInspectionMapper.getFacilityInspectionPlan(imprAct.getRefTableId());
                locate = facilityInspectionSchedule == null ? "" : facilityInspectionSchedule.getFacilityNm();
            }
            imprAct.setDtlLocat(locate);
            newImprActs.add(imprAct);
        }

        // List to Datasource
        JRDataSource imprActList = new JRBeanCollectionDataSource(newImprActs);
        // 파일경로 setting
        String logoPath = globalSettings.getLogoImageFilePath();
        // String logoPath =
        // URLDecoder.decode(getClass().getResource("/public/yullin-ci-ko.png").getPath(),"UTF-8");
        String reportPath = globalSettings.getReportImprActResult();
        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = (file != null ? file.getAbsolutePath() : null);
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        String outputFileNamepdf = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo_dir", logoPath);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, imprActList);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileNamepdf);
        // Export the report to a excel
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File outputFile = new File(outputFileName);
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setAutoFitPageHeight(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        // Sheet sheet = this.workbook.getSheetAt(0);
        // sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        // exporter.setWorkbookTemplateKeepSheets(true);
        exporter.exportReport();

        return new File(outputFileName);
    }

    /**
     * 개선조치사항 항목 생성
     *
     * @param imprActs
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    @Transactional
    public int saveImmImprAct(List<ImprAct> imprActs) throws Exception {
        int reusltNo = 0;
        if (imprActs != null && imprActs.size() > 0) {
            for (ImprAct imprAct : imprActs) {
                if (imprAct.getSafImprActNo() == 0) {
                    // 신규등록
                    reusltNo += imprMapper.createImprAct(imprAct);
                } else {
                    if ("".equals(imprAct.getDtlLocat()) && "".equals(imprAct.getDiscoverMatter()) && "".equals(imprAct.getActResultContents())) {
                        // 세부위치, 발견사항, 조치결과내용의 값이 비어있다면 삭제처리한다.
                        reusltNo += imprMapper.deleteImprAct(String.valueOf(imprAct.getSafImprActNo()));
                    } else {
                        // 수정
                        reusltNo += imprMapper.updateImprAct(imprAct);
                    }
                }
            }
        }
        return reusltNo;
    }

    /**
     * 개선조치사항 항목 삭제
     *
     * @param imprActs
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteImprActs(List<ImprAct> imprActs) throws Exception {
        int reusltNo = 0;
        if (imprActs != null && imprActs.size() > 0) {
            for (ImprAct imprAct : imprActs) {
                reusltNo += imprMapper.deleteImprAct(String.valueOf(imprAct.getSafImprActNo()));
            }
        }
        return reusltNo;
    }

    public List<ImprActDashboard> getImprActDashboards(int refTableId, String imprClassCd, DefaultParam defaultParam) throws Exception {
        return imprMapper.getImprActDashboards(refTableId, imprClassCd, defaultParam);
    }

    public List<ImprAct> createImprActs(List<ImprAct> imprActs) throws Exception {

        if (CollectionUtils.isNotEmpty(imprActs)) {
            for (ImprAct imprAct : imprActs) {
                imprAct.setSafImprActNo(this.createImprAct(imprAct));
            }
        }

        return imprActs;
    }

    public List<ImprAct> updateImprActs(List<ImprAct> imprActs) throws Exception {

        if (CollectionUtils.isNotEmpty(imprActs)) {
            for (ImprAct imprAct : imprActs) {
                imprAct.setSafImprActNo(this.updateImprAct(imprAct));
            }
        }

        return imprActs;
    }

    public Map<String, Object> getUploadExcelData(int refTableId, MultipartFile[] files) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String taskKey = formatter.format(new Date());
        AttachFileGrp attachFileGrp = new AttachFileGrp(RSA_PLAN_TASK_CLASS, taskKey, "", "", "", new ArrayList<>(Arrays.asList(files)));
        List<AttachFile> attachFiles = this.attachFileService.uploadFiles(attachFileGrp);

        // 저장된 엑셀 파일의 정보를 읽는다.
        List<AttachFile> excelFiles = attachFileService.getUploadFiles(RSA_PLAN_TASK_CLASS, taskKey, "");

        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("success", false);
        map.put("message", "");
        map.put("uploadList", null);
        map.put("totCount", null);
        map.put("completeCount", null);
        map.put("dontCount", null);

        int completeCount = 0;
        int dontCount = 0;
        int totCount = 0;
        int failCount = 0;
        int rowIndex = 0;
        try {
            File excel = new File(excelFiles.get(0).getFilePath());
            ExcelReader reader = new ExcelReader();
            List<String[][]> sheets = reader.read(excel, 0);
            ClassPathResource classPathResource = new ClassPathResource("templates" + path.getCodeNm() + "개선사항 업로드양식_샘플.xlsx");
            File templete = classPathResource.getFile();
            reader = new ExcelReader();
            List<String[][]> templeteSheets = reader.read(templete, 0);
            if (sheets != null && templeteSheets != null) {
                String[][] sheet = sheets.get(0);
                String[][] templeteData = templeteSheets.get(0);
                // 헤더 양식확인
                if (sheet.length > 0 && !reader.excelHeaderCheck(sheet[0], templeteData[0])) {
                    map.put("message", "업로드양식이 오류: 시트의 헤더가 다릅니다.");
                } else {

                    if (sheet.length == 1) {
                        map.put("message", "업로드 오류: 데이터가 없습니다.");
                        map.put("uploadList", null);
                        map.put("totCount", 0);
                        map.put("completeCount", 0);
                        map.put("dontCount", 0);
                        attachFileGrp.setTaskKey(String.valueOf(refTableId));
                        this.attachFileService.uploadFiles(attachFileGrp);
                    } else {
                        List<LinkedHashMap<String, Object>> uploadList = new ArrayList<LinkedHashMap<String, Object>>();
                        StringBuilder rowCheck;
                        for (int row = 1; row < sheet.length; row++) {

                            rowCheck = new StringBuilder();
                            for (int i = 0; i < sheet[row].length; i++) {
                                if (sheet[row][i] == null) {
                                    break;
                                }
                                rowCheck.append(sheet[row][i].trim());
                            }
                            if (rowCheck.length() > 0) {
                                LinkedHashMap<String, Object> rowData = new LinkedHashMap<String, Object>();
                                LinkedHashMap<String, Object> subData = new LinkedHashMap<String, Object>();
                                // 조치구분
                                rowData.put("actClassNm", sheet[row][0].trim());
                                String actClassNm = String.valueOf(rowData.get("actClassNm"));
                                if ("향후조치".equals(actClassNm)) {
                                    actClassNm = "개선요청";
                                }
                                rowData.put("actClassNm", actClassNm);
                                String actClassCd = imprMapper.checkCodeMaster(actClassNm, "SAF_ACT_CLASS");
                                rowData.put("actClassCd", actClassCd);
                                // 사업장
                                rowData.put("plantNm", sheet[row][1].trim());
                                String plantNm = String.valueOf(rowData.get("plantNm"));
                                String plantCd = imprMapper.checkCodeMaster(plantNm, "COM_PLANT_CD");
                                rowData.put("plantCd", plantCd);
                                // 요청 제목
                                rowData.put("imprTitle", sheet[row][2].trim());
                                // 조치요청내용
                                rowData.put("imprRqstContents", sheet[row][3].trim());
                                // 조치결과
                                rowData.put("actResultContents", sheet[row][4].trim());
                                // 조치 부서
                                rowData.put("actDeptNm", sheet[row][5].trim());
                                String actDeptNm = "";
                                String actDeptCd = "";
                                if (!"".equals(plantCd)) {
                                    actDeptNm = String.valueOf(rowData.get("actDeptNm"));
                                    actDeptCd = imprMapper.checkDeptMaster(actDeptNm, plantCd);
                                    rowData.put("actDeptCd", actDeptCd);
                                } else {
                                    rowData.put("actDeptCd", null);
                                }
                                Map<String, Object> actDept = new HashMap<String, Object>();
                                Map<String, Object> actUser = new HashMap<String, Object>();
                                // 조치자
                                rowData.put("actUserNm", sheet[row][6].trim());
                                // 조치자 사번
                                rowData.put("actUserId", sheet[row][7].trim());

                                String actUserNm = "";
                                String actUserId = "";

                                if (!"".equals(rowData.get("actDeptCd")) && rowData.get("actDeptCd") != null) {
                                    actUserNm = String.valueOf(rowData.get("actUserNm"));
                                    actUserId = imprMapper.checkUserMaster(actUserNm, String.valueOf(rowData.get("actUserId")), actDeptCd);
                                    rowData.put("actUserId", actUserId);
                                } else {
                                    rowData.put("actUserId", null);
                                }

                                if (rowData.get("actUserNm") == null || rowData.get("actUserId") == null) {
                                    rowData.put("actUserNm", null);
                                    rowData.put("actUserId", null);

                                    actUser.put("userNm", null);
                                    actUser.put("userId", null);
                                } else {
                                    rowData.put("actUserNm", actUserNm);
                                    rowData.put("actUserId", actUserId);

                                    actUser.put("userNm", actUserNm);
                                    actUser.put("userId", actUserId);
                                }
                                if (rowData.get("actDeptNm") == null || rowData.get("actDeptCd") == null) {
                                    rowData.put("actDeptCd", null);
                                    rowData.put("actDeptNm", null);

                                    actDept.put("deptCd", null);
                                    actDept.put("deptNm", null);
                                } else {
                                    rowData.put("actDeptCd", actDeptCd);
                                    rowData.put("actDeptNm", actDeptNm);

                                    actDept.put("deptCd", actDeptCd);
                                    actDept.put("deptNm", actDeptNm);
                                }
                                rowData.put("actDept", actDept);
                                rowData.put("actUser", actUser);
                                int ymdChk = 0;
                                // 조치기한
                                rowData.put("actLimitYmd", sheet[row][8].trim());
                                String actLimitYmd = String.valueOf(rowData.get("actLimitYmd"));
                                String actLimitYmdChk[] = actLimitYmd.split("-");
                                if (actLimitYmdChk.length == 3) {
                                    if (actLimitYmdChk[0].length() != 4) {
                                        ymdChk++;
                                    }
                                    if (actLimitYmdChk[1].length() != 2) {
                                        ymdChk++;
                                    }
                                    if (actLimitYmdChk[2].length() != 2) {
                                        ymdChk++;
                                    }
                                } else {
                                    ymdChk++;
                                }
                                if (ymdChk == 0 && "ACL01".equals(actClassCd)) {
                                    rowData.put("actConfirmYmd", actLimitYmd);
                                    rowData.put("actResultContents", "조치완료");
                                }
                                boolean checkActClassCd = String.valueOf(rowData.get("actClassCd")).equals(actClassCd) ? true : false;

                                boolean checkPlantCd = String.valueOf(rowData.get("plantCd")).equals(plantCd) ? true : false;

                                boolean checkImprTitle = StringUtil.isEmpty(String.valueOf(rowData.get("imprTitle"))) ? false : true;

                                boolean checkImprRqstContents = StringUtil.isEmpty(String.valueOf(rowData.get("imprRqstContents"))) ? false : true;
                                boolean checkActDeptCd = rowData.get("actDeptCd") == null ? false : true;
                                boolean checkActUserId = rowData.get("actUserId") == null ? false : true;
                                if ("개선요청".equals(rowData.get("actClassNm"))) {
                                    checkActUserId = true;
                                    rowData.put("actUserNm", null);
                                    rowData.put("actUserId", null);

                                    actUser.put("userNm", null);
                                    actUser.put("userId", null);
                                    rowData.put("actUser", actUser);
                                }

                                String failMessage = "";

                                int faliChk = 0;
                                if (!checkActClassCd) {
                                    failMessage = this.appendString(failMessage, "조치구분을 확인해주세요.");
                                    faliChk++;
                                }
                                if (!checkPlantCd) {
                                    failMessage = this.appendString(failMessage, "사업장을 확인해주세요.");
                                    faliChk++;
                                }

                                if (!checkImprTitle) {
                                    failMessage = this.appendString(failMessage, "제목을 입력해주세요.");
                                    faliChk++;
                                }
                                if (!checkImprRqstContents) {
                                    failMessage = this.appendString(failMessage, "조치요청내용을 입력해주세요");
                                    faliChk++;
                                }
                                if (!checkActDeptCd) {
                                    failMessage = this.appendString(failMessage, "조치부서를 확인해주세요.");
                                    faliChk++;
                                }
                                if (!checkActUserId) {
                                    failMessage = this.appendString(failMessage, "조치자사번을 확인해주세요.");
                                    faliChk++;
                                }
                                if (ymdChk > 0) {
                                    failMessage = this.appendString(failMessage, "날짜기한 양식을 확인해주세요. ex) yyyy-mm-dd");
                                    faliChk++;
                                }
                                if (faliChk > 0) {
                                    dontCount++;
                                    failCount++;
                                } else {
                                    completeCount++;
                                }
                                rowData.put("rowIndex", rowIndex);
                                rowData.put("failMessage", failMessage);
                                uploadList.add(rowData);
                                rowIndex++;

                                totCount++;
                            }
                        }
                        map.put("totCount", totCount);
                        map.put("completeCount", completeCount);
                        map.put("dontCount", dontCount);
                        map.put("success", true);
                        map.put("uploadList", uploadList);
                    }
                }
            }

        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            map.put("success", false);
            map.put("message", "업로드 도중 에러발생");
        } finally {
            // 다 읽어드린 엑셀 파일은 지운다.
            // this.fileStorageService.deleteFile(attachFiles.get(0).getFilePath());

            attachFileService.deleteFile(String.valueOf(attachFiles.get(0).getFileNo()));
            if (failCount == 0) {
                // 엑셀 파일 삭제
                attachFileService.deleteFilesAll(attachFileGrp.getTaskClassNm(), String.valueOf(refTableId));
                attachFileGrp.setTaskKey(String.valueOf(refTableId));
                this.attachFileService.uploadFiles(attachFileGrp);
            }
        }
        return map;
    }

    // 문자열 더하기
    private String appendString(String source, String append) {
        source += "■" + append;

        return source;
    }

    public Map<String, Object> validateChk(List<HashMap<String, Object>> parameter) throws Exception {
        List<LinkedHashMap<String, Object>> uploadList = new ArrayList<LinkedHashMap<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", false);
        map.put("message", "");
        map.put("uploadList", null);
        map.put("totCount", null);
        map.put("completeCount", null);
        map.put("dontCount", null);

        int completeCount = 0;
        int dontCount = 0;
        int totCount = 0;
        int failCount = 0;
        Map<String, Object> deptMap = new HashMap<String, Object>();
        Map<String, Object> userMap = new HashMap<String, Object>();
        for (int i = 0; i < parameter.size(); i++) {
            LinkedHashMap<String, Object> rowData = new LinkedHashMap<String, Object>();
            HashMap<String, Object> uploadinfo = new HashMap<String, Object>();
            uploadinfo = parameter.get(i);
            HashMap<String, Object> deptInfo = (LinkedHashMap<String, Object>) uploadinfo.get("actDept");
            HashMap<String, Object> userInfo = (LinkedHashMap<String, Object>) uploadinfo.get("actUser");
            // rowIndex
            rowData.put("rowIndex", uploadinfo.get("rowIndex"));
            // 구분
            String actClassNm = String.valueOf(uploadinfo.get("actClassNm"));
            if ("향후조치".equals(actClassNm)) {
                actClassNm = "개선요청";
            }
            rowData.put("actClassNm", actClassNm);
            String actClassCd = imprMapper.checkCodeMaster(actClassNm, "SAF_ACT_CLASS");
            rowData.put("actClassCd", actClassCd);
            // 사업장
            String plantNm = String.valueOf(uploadinfo.get("plantNm"));
            String plantCd = imprMapper.checkCodeMaster(plantNm, "COM_PLANT_CD");
            rowData.put("plantCd", plantCd);
            rowData.put("plantNm", plantNm);
            // 제목
            String imprTitle = String.valueOf(uploadinfo.get("imprTitle"));
            rowData.put("imprTitle", imprTitle);
            // 조치요청내용
            String imprRqstContents = String.valueOf(uploadinfo.get("imprRqstContents"));
            rowData.put("imprRqstContents", imprRqstContents);
            // 조치결과
            String actResultContents = String.valueOf(uploadinfo.get("actResultContents"));
            rowData.put("actResultContents", actResultContents);
            Map<String, Object> actDept = new HashMap<String, Object>();
            Map<String, Object> actUser = new HashMap<String, Object>();
            String deptNm = "";
            String deptCd = "";
            // 조치부서
            if (!"".equals(plantCd)) {
                if (deptInfo != null) {
                    if (deptInfo.get("deptNm") != null || deptInfo.get("deptCd") != null) {
                        deptNm = String.valueOf(deptInfo.get("deptNm"));
                        deptCd = imprMapper.checkDeptMaster(deptNm, plantCd);
                        actDept.put("deptNm", deptNm);
                        rowData.put("actDeptNm", deptNm);
                        if (deptCd == null) {
                            actDept.put("deptNm", null);
                            rowData.put("actDeptNm", null);
                        }

                        actDept.put("deptCd", deptCd);

                        rowData.put("actDeptCd", deptCd);
                    } else {
                        actDept.put("deptNm", null);
                        actDept.put("deptCd", null);
                        rowData.put("actDeptNm", null);
                        rowData.put("actDeptCd", null);
                    }
                } else {
                    actDept.put("deptNm", null);
                    actDept.put("deptCd", null);
                    rowData.put("actDeptNm", null);
                    rowData.put("actDeptCd", null);
                }
            } else {
                actDept.put("deptNm", null);
                actDept.put("deptCd", null);
                rowData.put("actDeptNm", null);
                rowData.put("actDeptCd", null);
            }
            rowData.put("actDept", actDept);
            String userNm = "";
            String userId = "";
            // 조치 담당자
            if (deptCd != null) {
                if (userInfo != null) {
                    if (userInfo.get("userId") != null || userInfo.get("userNm") != null) {
                        userNm = String.valueOf(userInfo.get("userNm"));
                        userId = imprMapper.checkUserMaster(userNm, String.valueOf(userInfo.get("userId")), deptCd);
                        rowData.put("actUserNm", userNm);
                        actUser.put("userNm", userNm);
                        if (userId == null) {
                            actUser.put("userNm", null);
                            rowData.put("actUserNm", null);
                        }
                        rowData.put("actUserId", userId);
                        actUser.put("userId", userId);
                    } else {
                        rowData.put("actUserNm", null);
                        rowData.put("actUserId", null);
                        actUser.put("userNm", null);
                        actUser.put("userId", null);
                    }
                } else {
                    rowData.put("actUserNm", null);
                    rowData.put("actUserId", null);
                    actUser.put("userNm", null);
                    actUser.put("userId", null);
                }

            } else {
                rowData.put("actUserNm", null);
                rowData.put("actUserId", null);
                actUser.put("userNm", null);
                actUser.put("userId", null);
            }
            rowData.put("actUser", actUser);
            // 조치기한
            String actLimitYmd = String.valueOf(uploadinfo.get("actLimitYmd"));
            rowData.put("actLimitYmd", actLimitYmd);
            // 조치완료일
            String actConfirmYmd = String.valueOf(uploadinfo.get("actConfirmYmd"));
            rowData.put("actConfirmYmd", actConfirmYmd);

            boolean checkActClassCd = rowData.get("actClassCd") == null ? false : true;

            boolean checkPlantCd = rowData.get("plantCd") == null ? false : true;

            boolean checkImprTitle = rowData.get("imprTitle") == null ? false : true;

            boolean checkImprRqstContents = StringUtil.isEmpty(String.valueOf(rowData.get("imprRqstContents"))) ? false : true;
            boolean checkActDeptCd = rowData.get("actDeptCd") == null ? false : true;
            boolean checkActUserId = rowData.get("actUserId") == null ? false : true;
            if ("개선요청".equals(rowData.get("actClassNm"))) {
                checkActUserId = true;
                rowData.put("actUserNm", null);
                rowData.put("actUserId", null);

                actUser.put("userNm", null);
                actUser.put("userId", null);
                rowData.put("actUser", actUser);
            }
            String failMessage = "";
            int faliChk = 0;
            if (!checkActClassCd) {
                failMessage = this.appendString(failMessage, "조치구분을 확인해주세요.\n");
                faliChk++;
            }
            if (!checkPlantCd) {
                failMessage = this.appendString(failMessage, "사업장을 확인해주세요.\n");
                faliChk++;
            }

            if (!checkImprTitle) {
                failMessage = this.appendString(failMessage, "제목을 입력해주세요.\n");
                faliChk++;
            }
            if (!checkImprRqstContents) {
                failMessage = this.appendString(failMessage, "조치요청내용을 입력해주세요.\n");
                faliChk++;
            }
            if (!checkActDeptCd) {
                failMessage = this.appendString(failMessage, "조치부서를 확인해주세요.\n");
                faliChk++;
            }
            if (!checkActUserId) {
                failMessage = this.appendString(failMessage, "조치자사번을 확인해주세요.\n");
                faliChk++;
            }
            if (faliChk > 0) {
                dontCount++;
                failCount++;
            } else {
                completeCount++;
            }

            rowData.put("failMessage", failMessage);
            uploadList.add(rowData);
            totCount++;

        }
        map.put("totCount", totCount);
        map.put("completeCount", completeCount);
        map.put("dontCount", dontCount);
        map.put("success", true);
        map.put("uploadList", uploadList);
        return map;
    }

    /**
     * 엑셀 업로드 등록
     * 
     * @param hashmap
     *            개선사항 항목
     * @throws Exception
     */
    public int excelUploadCreateImprAct(List<HashMap<String, Object>> parameter) throws Exception {
        int insertCnt = 0;

        for (int i = 0; i < parameter.size(); i++) {
            ImprAct imprAct = new ImprAct();
            HashMap<String, Object> uploadinfo = new HashMap<String, Object>();

            uploadinfo = parameter.get(i);
            imprAct.setPlantCd(String.valueOf(uploadinfo.get("plantCd")));
            imprAct.setImprClassCd(String.valueOf(uploadinfo.get("imprClassCd")));
            imprAct.setActClassCd(String.valueOf(uploadinfo.get("actClassCd")));
            // 즉시조치 (요청일 = 조치일)
            if ("ACL01".equals(String.valueOf(uploadinfo.get("actClassCd")))) {
                //
                imprAct.setImprRqstYmd(String.valueOf(uploadinfo.get("actConfirmYmd")));
                // 조치일
                imprAct.setActConfirmYmd(String.valueOf(uploadinfo.get("actConfirmYmd")));
                // 조치부서
                imprAct.setImprRqstDeptCd(String.valueOf(uploadinfo.get("actDeptCd") == null ? uploadinfo.get("createDeptCd") : uploadinfo.get("actDeptCd")));
                // 담당자
                imprAct.setImprRqstUserId(String.valueOf(uploadinfo.get("createUserId")));
                // 조치결과내용
                imprAct.setActResultContents(String.valueOf(uploadinfo.get("actResultContents")));
                // 세부위치
                imprAct.setDtlLocat(uploadinfo.get("dtlLocat") == null ? "" : String.valueOf(uploadinfo.get("dtlLocat")));
                // 발견사항
                imprAct.setDiscoverMatter(uploadinfo.get("discoverMatter") == null ? "" : String.valueOf(uploadinfo.get("discoverMatter")));

            } else {
                imprAct.setImprRqstYmd(""); // getdate
                imprAct.setActLimitYmd(String.valueOf(uploadinfo.get("actLimitYmd")));
                imprAct.setImprRqstDeptCd(String.valueOf(uploadinfo.get("createDeptCd")));
                // 개선후빈도
                imprAct.setBefFreq("0");
                // 개선후강도
                imprAct.setBefInten("0");
                // 개선후위험도
                imprAct.setBefDegRisk("0");
                // 조치요청내용
                imprAct.setActResultContents(String.valueOf(uploadinfo.get("actResultContents")));
                // 담당자
                imprAct.setImprRqstUserId(String.valueOf(uploadinfo.get("createUserId")));
            }
            imprAct.setImprRqstContents(String.valueOf(uploadinfo.get("imprRqstContents")));
            imprAct.setImprTitle(String.valueOf(uploadinfo.get("imprTitle")));
            imprAct.setImprStepCd("IMST1");
            imprAct.setRefTableId(Integer.parseInt(String.valueOf(uploadinfo.get("refTableId"))));
            imprAct.setActDeptCd(String.valueOf(uploadinfo.get("actDeptCd")));
            imprAct.setActUserId(String.valueOf(uploadinfo.get("actUserId")));
            imprAct.setCreateUserId(String.valueOf(uploadinfo.get("createUserId")));
            imprAct.setCreateDeptCd(String.valueOf(uploadinfo.get("createDeptCd")));
            imprAct.setCreateDeptNm(String.valueOf(uploadinfo.get("createDeptNm")));
            imprAct.setCreatePositionCd(String.valueOf(uploadinfo.get("createPositionCd")));
            imprAct.setCreatePositionNm(String.valueOf(uploadinfo.get("createPositionNm")));

            insertCnt += imprMapper.excelUploadCreateImprAct(imprAct);
        }

        return insertCnt;
    }

    /**
     * 신규개선조치사항 탭항목 조회
     *
     * @param parameter
     *            imprClassCd, refTableId
     * @return 신규개선조치사항 탭항목 조회
     * @throws Exception
     */
    public List<ImprAct> getNewImprActs(String imprClassCd, int refTableId, DefaultParam defaultParam) throws Exception {
        return imprMapper.getNewImprActs(imprClassCd, refTableId, defaultParam);
    }
}
