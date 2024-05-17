package com.she.safety.preOper.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.baseInfo.facility.mapper.FacilityMapper;
import com.she.baseInfo.model.FacilityMst;
import com.she.common.model.DefaultParam;
import com.she.config.GlobalSettings;
import com.she.impr.service.ImprService;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.safety.model.FacilChkItemResult;
import com.she.safety.model.FacilChkMaster;
import com.she.safety.model.FacilChkResult;
import com.she.safety.model.ForEachType;
import com.she.safety.model.SafPreOperChkPsn;
import com.she.safety.preOper.mapper.PreOperCheckResultMapper;
import com.she.utils.ConstVal;
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

@Service
public class PreOperCheckResultService {
    @Autowired
    private PreOperCheckResultMapper preOperCheckResultMapper;

    @Autowired
    private FacilityMapper facilityMapper;

    @Autowired
    private ImprService imprService;

    @Autowired
    private ChangeService changeService;

    @Autowired
    private GlobalSettings globalSettings;

    public List<HashMap<String, Object>> getFacilityCheckResultStatus(String plantCd, String year) throws Exception {
        return preOperCheckResultMapper.getFacilityCheckResultStatus(plantCd, year);
    }

    public List<HashMap<String, Object>> getFacilityCheckResultStatusSub(String plantCd, String year, String safCheckTypeCd, String safFacilityTypeCd, String deptCd, DefaultParam defaultParam) throws Exception {
        return preOperCheckResultMapper.getFacilityCheckResultStatusSub(plantCd, year, safCheckTypeCd, safFacilityTypeCd, deptCd, defaultParam);
    }

    // ============================================================================================================

    /**
     * 설비점검계획 목록 조회
     *
     * @param plantCd
     *            사업장코드
     * @param checkStepCd
     *            점검상태
     * @param startYmd
     *            기간(시작)
     * @param endYmd
     *            기간(종료)
     * @return 설비점검계획 목록
     * @throws Exception
     */
    public List<FacilChkMaster> getFacilChkPlans(String plantCd, String checkStepCd, String deptCd, String deptSubYn, String tDeptCd, String tDeptSubYn, String pDeptCd, String pDeptSubYn, String chkEndYn, String startYmd, String endYmd, String year, String safCheckTypeCd, String keyword, DefaultParam defaultParam) throws Exception {
        return preOperCheckResultMapper.getFacilChkPlans(plantCd, checkStepCd, deptCd, deptSubYn, tDeptCd, tDeptSubYn, pDeptCd, pDeptSubYn, chkEndYn, startYmd, endYmd, year, safCheckTypeCd, keyword, defaultParam);
    }

    /**
     * 설비점검계획 상세 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검 계획 상세
     */
    public FacilChkMaster getFacilChkPlan(int safFacilChkNo, DefaultParam defaultParam) throws Exception {
        FacilChkMaster result = preOperCheckResultMapper.getFacilChkPlan(safFacilChkNo, defaultParam);
        result.setFacilChkResults(preOperCheckResultMapper.getFacilChkResults(safFacilChkNo, defaultParam));

        if (result.getFacilChkResults() != null) {
            for (FacilChkResult facilChkResult : result.getFacilChkResults()) {
                facilChkResult.setChkUserId(preOperCheckResultMapper.getFacilChkUser(safFacilChkNo, facilChkResult.getSafFacilityCd()));
            }
        }
        return result;
    }

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkItemResult
     *            설비점검 계획
     * @return 등록 행 수
     */
    @Transactional
    public int createFacilChkSubPlan(FacilChkItemResult facilChkItemResult) throws Exception {
        // String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
        int result = 0;
        preOperCheckResultMapper.deleteForEachItem(facilChkItemResult.getSafFacilChkNo(), facilChkItemResult.getSafCheckTypeCd());
        preOperCheckResultMapper.updateForEachItem(facilChkItemResult);

        preOperCheckResultMapper.deleteForEachFacility(facilChkItemResult.getSafFacilChkNo(), facilChkItemResult.getSafCheckTypeCd());
        if (CollectionUtils.isNotEmpty(facilChkItemResult.getForEachFacilList())) {
            for (ForEachType forEachType : facilChkItemResult.getForEachFacilList()) {
                forEachType.setSafCheckTypeCd(facilChkItemResult.getSafCheckTypeCd());
                forEachType.setSafFacilChkNo(facilChkItemResult.getSafFacilChkNo());
                preOperCheckResultMapper.createForEachFacility(forEachType);
            }
        }

        if (CollectionUtils.isNotEmpty(facilChkItemResult.getForEachTypeList())) {
            for (FacilChkItemResult facilChkItemResult1 : facilChkItemResult.getForEachTypeList()) {
                facilChkItemResult1.setSafCheckTypeCd(facilChkItemResult.getSafCheckTypeCd());
                facilChkItemResult1.setSafFacilChkNo(facilChkItemResult.getSafFacilChkNo());
                facilChkItemResult1.setSafFacilityCheckItemNo(facilChkItemResult1.getSafFacilityCheckItemNo().replace("　", ""));
                result += preOperCheckResultMapper.updateFacilChkItem(facilChkItemResult1);
            }
        }

        return result;
    }

    /**
     * 설비점검 계획 등록
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 등록 행 수
     */
    @Transactional
    public int createFacilChkPlan(FacilChkMaster facilChkMaster) throws Exception {
        // 설비점검 계획 등록
        int result = preOperCheckResultMapper.createFacilChkPlan(facilChkMaster);

        // MOC 번호가 들어온 경우
        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(facilChkMaster.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(facilChkMaster.getSafFacilChkNo()));
        changeRefWork.setRefTableNm("saf_pre_operation_chk_plan");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_5);
        changeRefWork.setCreateUserId(facilChkMaster.getCreateUserId());
        changeService.taskChange(changeRefWork);

        return facilChkMaster.getSafFacilChkNo();
    }

    /**
     * 설비점검 계획 수정
     *
     * @param facilChkMaster
     *            설비점검 계획
     * @return 수정 행 수
     */
    @Transactional
    public int updateFacilChkPlan(FacilChkMaster facilChkMaster) throws Exception {
        // 설비점검 계획 수정
        int result = preOperCheckResultMapper.updateFacilChkPlan(facilChkMaster);

        if (CollectionUtils.isNotEmpty(facilChkMaster.getPreOperChkPsns())) {
            preOperCheckResultMapper.deletePreOperChkPsn(facilChkMaster.getSafFacilChkNo());
            for (SafPreOperChkPsn safPreOperChkPsn : facilChkMaster.getPreOperChkPsns()) {
                safPreOperChkPsn.setSafFacilChkNo(facilChkMaster.getSafFacilChkNo());
                preOperCheckResultMapper.createPreOperChkPsn(safPreOperChkPsn);
            }
        }

        // MOC 번호가 들어온 경우
        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(facilChkMaster.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(facilChkMaster.getSafFacilChkNo()));
        changeRefWork.setRefTableNm("saf_pre_operation_chk_plan");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_5);
        changeRefWork.setCreateUserId(facilChkMaster.getCreateUserId());
        changeService.taskChange(changeRefWork);

        return facilChkMaster.getSafFacilChkNo();
    }

    /**
     * 설비점검계획 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkPlan(int safFacilChkNo) throws Exception {
        int resultNo = 0;
        resultNo += preOperCheckResultMapper.deleteFacilChkResult(safFacilChkNo);
        resultNo += preOperCheckResultMapper.deleteFacilChkPlan(safFacilChkNo);
        resultNo += preOperCheckResultMapper.deleteFacilChkItem(safFacilChkNo, "", "");
        resultNo += preOperCheckResultMapper.deleteForEachItem(safFacilChkNo, "");
        resultNo += preOperCheckResultMapper.deleteFacility(safFacilChkNo);

        return resultNo;
    }

    /**
     * 설비점검계획 삭제
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilChkItem(String safCheckTypeCd, int safFacilChkNo, String safFacilityCd) throws Exception {
        int resultNo = 0;
        resultNo += preOperCheckResultMapper.deleteForEachItem(safFacilChkNo, safCheckTypeCd);
        return resultNo;
    }

    /**
     * 설비점검계획 설비별 점검항목 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검결과 상세 정보
     */
    public List<FacilChkItemResult> getFacilChkResultItems(int safFacilChkNo, String plantCd, String safCheckTypeCd, String noPlanYn, DefaultParam defaultParam) throws Exception {
        List<FacilChkItemResult> saveData = preOperCheckResultMapper.getFacilChkPlanItems(safFacilChkNo, safCheckTypeCd, defaultParam);
        // 저장된 데이터 값이 있는 경우
        if (saveData != null && saveData.size() > 0) {
            // 저장된 데이터값에 설비유형의 값이 현재 설비의 설비유형값과 동일한지 체크
            // 동일한 값이 들어 있다면 저장된 데이터 값을 그대로 가져가 사용
            return saveData;
        } else {
            // 저장된 값이 없는 경우에는 현재 설비의 설비유형값에 따른 상세항목들을 가지고 온다. (사용하는 것들만)
            saveData = preOperCheckResultMapper.getNewFacilChkItems(plantCd, safCheckTypeCd, noPlanYn, defaultParam);
        }
        return saveData;
    }

    /**
     * 설비점검계획 점검유형 조회
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @return 설비점검결과 상세 정보
     */
    public List<ForEachType> getForEachTypes(int safFacilChkNo, String plantCd, String safCheckTypeCd, DefaultParam defaultParam) throws Exception {

        List<ForEachType> forEachTypeList = preOperCheckResultMapper.getForEachTypes(safFacilChkNo, defaultParam);

        if (forEachTypeList != null || forEachTypeList.size() > 0) {
            for (ForEachType forEachType : forEachTypeList) {
                List<FacilChkItemResult> saveData = preOperCheckResultMapper.getFacilChkPlanItems(safFacilChkNo, forEachType.getSafCheckTypeCd(), defaultParam);
                if (saveData != null) {
                    forEachType.setFacilChkItemResultList(saveData);
                }
                List<FacilityMst> saveData2 = preOperCheckResultMapper.getForEachFacilitys(safFacilChkNo, forEachType.getSafCheckTypeCd(), defaultParam);
                if (saveData2 != null) {
                    forEachType.setForEachFacilityList(saveData2);
                }
            }
        }

        return forEachTypeList;
    }

    public List<FacilChkItemResult> setFacilChkItems(int safFacilChkNo, String plantCd, String safCheckTypeCd, DefaultParam defaultParam) throws Exception {
        List<FacilChkItemResult> saveData = preOperCheckResultMapper.getFacilChkItems(safFacilChkNo, safCheckTypeCd, "", defaultParam);
        return saveData;
    }

    /**
     * 설비별 점검결과 전체완료
     *
     * @param facilChkMaster
     *            설비점검계획
     * @return 수정 행 수
     */
    public int updateFacilChkResults(FacilChkMaster facilChkMaster) throws Exception {
        // 상세의 진행단계가 완료로 바뀔 시에 해당 상세 엮여 있는 개선조치사항건에 대해서 접수 상태로 변경
        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_PREOPER, facilChkMaster.getSafFacilChkNo(), "");

        return preOperCheckResultMapper.updateFacilChkResults(facilChkMaster);
    }

    /**
     * 설비점검계획 진행단계 변경
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessPreOperChkPlan(int safFacilChkNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        FacilChkMaster facilChkPlan = preOperCheckResultMapper.getFacilChkPlan(safFacilChkNo, new DefaultParam("kr"));
        String chkStepCd = facilChkPlan.getChkStepCd();

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            if (facilChkPlan != null) {
                if (facilChkPlan.getChkStepCd().equals(ConstVal.SAF_CHK_STEP_PLAN_CD)) {
                    // 계획상태의 경우
                    chkStepCd = ConstVal.SAF_CHK_STEP_RESULT_CD;
                }
            }
        }

        // 설비점검마스터에 따른 일정 진행단계 변경
        resultNo += preOperCheckResultMapper.completeFacilChkPlan(safFacilChkNo, apprRqstNo, chkStepCd);
        return resultNo;
    }

    /**
     * 설비점검계획 진행단계 변경
     *
     * @param safFacilChkNo
     *            설비점검번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessPreOperChkResult(int safFacilChkNo, String bizApprStepCd, int rApprRqstNo) throws Exception {
        int resultNo = 0;

        FacilChkMaster facilChkPlan = preOperCheckResultMapper.getFacilChkPlan(safFacilChkNo, new DefaultParam("kr"));

        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_PREOPER, facilChkPlan.getSafFacilChkNo(), "");

        String chkStepCd = facilChkPlan.getChkStepCd();

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            // 계획상태의 경우
            chkStepCd = ConstVal.SAF_CHK_STEP_IMPROVED_CD;
        }

        // 설비점검마스터에 따른 일정 진행단계 변경
        resultNo += preOperCheckResultMapper.completeResultFacilChkPlan(safFacilChkNo, rApprRqstNo, chkStepCd);
        return resultNo;
    }

    /**
     * 설비점검 사업장별 실적집계
     *
     * @param plantCd
     *            사업장코드
     * @param year
     *            년도(점검일)
     * @return 설비점검 사업장별 실적집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getFacilChkResultStatus(String plantCd, String year) throws Exception {
        return preOperCheckResultMapper.getFacilChkResultStatus(plantCd, year);
    }

    /**
     * 설비점검 사업장별 실적 세부집계
     *
     * @param plantCd
     *            사업장코드
     * @param year
     *            년도(점검일)
     * @param safCheckTypeCd
     *
     * @param safFacilityTypeCd
     *
     * @param deptCd
     *            점검주관부서
     * @return 설비점검 사업장별 실적 세부집계 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getFacilChkResultStatusSub(String plantCd, String year, String safCheckTypeCd, String safFacilityTypeCd, String deptCd, String deptSubYn, DefaultParam defaultParam) throws Exception {
        return preOperCheckResultMapper.getFacilChkResultStatusSub(plantCd, year, safCheckTypeCd, safFacilityTypeCd, deptCd, deptSubYn, defaultParam);
    }

    /**
     * 설비점검결과 목록 조회
     *
     * @return 설비점검결과 목록
     * @throws Exception
     */
    public List<FacilChkResult> getFacilChkResultTable(String plantCd, String startDate, String endDate, String chkEndYn, String deptCd, String safCheckTypeCd, String safFacilityTypeCd, String completeYn, String dtlYn) throws Exception {
        return preOperCheckResultMapper.getFacilChkResultTable(plantCd, startDate, endDate, chkEndYn, deptCd, safCheckTypeCd, safFacilityTypeCd, completeYn, dtlYn);
    }

    /**
     * 가동전점검 기준 출력
     *
     * @param plantCd
     * @param safCheckTypeCd
     * @param safFacilChkNo
     * @return
     * @throws Exception
     */
    public File getPreStartPrint(String plantCd, String safCheckTypeCd, int safFacilChkNo, String chkUserNm, String reportName, DefaultParam defaultParam) throws Exception {
        List<FacilChkItemResult> facilChkItemResults = preOperCheckResultMapper.getFacilChkItems(safFacilChkNo, safCheckTypeCd, "", defaultParam);

        if (CollectionUtils.isEmpty(facilChkItemResults) || facilChkItemResults == null) {
            facilChkItemResults.add(new FacilChkItemResult());
        }

        // 파일경로 setting
        String reportPath = globalSettings.getPreStartCheck();
        String subReportDir = globalSettings.getPreStartCheckDir();

        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = file.getAbsolutePath();
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
        String logoPath = globalSettings.getLogoImageFilePath();

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        List<FacilChkItemResult> data = new ArrayList<FacilChkItemResult>();
        data.add(facilChkItemResults.get(0));
        JRDataSource datasource = new JRBeanCollectionDataSource(data);

        JRDataSource facilChkItemResultsDataSource = new JRBeanCollectionDataSource(facilChkItemResults);

        // 파라미터값
        java.util.Map<String, Object> parameters = new HashMap<>();
        parameters.put("subreport_dir", subReportDir);
        parameters.put("logo_dir", logoPath);
        parameters.put("chkUserNm", chkUserNm);
        parameters.put("reportName", reportName);
        parameters.put("facilChkItemResults", facilChkItemResultsDataSource);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

        return new File(outputFileName);
    }

    /**
     * 가동전점검 기준 Excel 출력
     *
     * @param plantCd
     * @param safCheckTypeCd
     * @param safFacilChkNo
     * @return
     * @throws Exception
     */
    public File getPreStartPrintExcel(String plantCd, String safCheckTypeCd, int safFacilChkNo, String chkUserNm, String reportName, DefaultParam defaultParam) throws Exception {
        List<FacilChkItemResult> facilChkItemResults = preOperCheckResultMapper.getFacilChkItems(safFacilChkNo, safCheckTypeCd, "", defaultParam);

        if (CollectionUtils.isEmpty(facilChkItemResults) || facilChkItemResults == null) {
            facilChkItemResults.add(new FacilChkItemResult());
        }

        // 파일경로 setting
        String reportPath = globalSettings.getPreStartCheck();
        String subReportDir = globalSettings.getPreStartCheckDir();

        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = file.getAbsolutePath();
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
        String logoPath = globalSettings.getLogoImageFilePath();

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        List<FacilChkItemResult> data = new ArrayList<FacilChkItemResult>();
        data.add(facilChkItemResults.get(0));
        JRDataSource datasource = new JRBeanCollectionDataSource(data);

        JRDataSource facilChkItemResultsDataSource = new JRBeanCollectionDataSource(facilChkItemResults);

        // 파라미터값
        java.util.Map<String, Object> parameters = new HashMap<>();
        parameters.put("subreport_dir", subReportDir);
        parameters.put("logo_dir", logoPath);
        parameters.put("chkUserNm", chkUserNm);
        parameters.put("reportName", reportName);
        parameters.put("facilChkItemResults", facilChkItemResultsDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
        // Export the report to a PDF
        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // outputFileName);

        // Export the report to a excel
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File outputFile = new File(outputFileName);
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setAutoFitPageHeight(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        return new File(outputFileName);
    }

    /**
     * 가동전점검 보고서 출력
     *
     * @param plantCd
     * @param safCheckTypeCd
     * @param safFacilChkNo
     * @param chkUserNm
     * @param chkSchYmd
     * @param facilityNames
     * @return
     * @throws Exception
     */
    public File getPreStartReportPrint(String plantCd, String safCheckTypeCd, int safFacilChkNo, String chkUserNm, String chkSchYmd, String facilityNames, DefaultParam defaultParam) throws Exception {
        List<FacilChkItemResult> facilChkItemResults = preOperCheckResultMapper.getFacilChkItems(safFacilChkNo, safCheckTypeCd, "Y", defaultParam);

        if (CollectionUtils.isEmpty(facilChkItemResults) || facilChkItemResults == null) {
            facilChkItemResults.add(new FacilChkItemResult());
        }

        for (FacilChkItemResult facilChkItemResult : facilChkItemResults) {
            if (facilChkItemResult.getChkResult().equals("Y")) {
                facilChkItemResult.setChkResultNm("Yes");
            } else if (facilChkItemResult.getChkResult().equals("N")) {
                facilChkItemResult.setChkResultNm("No");
            } else {
                facilChkItemResult.setChkResultNm("N/A");
            }
        }
        // 파일경로 setting
        String reportPath = globalSettings.getPreStartCheckReport();
        String subReportDir = globalSettings.getPreStartCheckDir();

        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = file.getAbsolutePath();
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
        String logoPath = globalSettings.getLogoImageFilePath();

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        List<FacilChkItemResult> data = new ArrayList<FacilChkItemResult>();
        data.add(facilChkItemResults.get(0));
        JRDataSource datasource = new JRBeanCollectionDataSource(data);

        JRDataSource facilChkItemResultsDataSource = new JRBeanCollectionDataSource(facilChkItemResults);

        // 파라미터값
        java.util.Map<String, Object> parameters = new HashMap<>();
        parameters.put("subreport_dir", subReportDir);
        parameters.put("logo_dir", logoPath);
        parameters.put("chkUserNm", chkUserNm);
        parameters.put("chkSchYmd", chkSchYmd);
        parameters.put("facilityNames", facilityNames);
        parameters.put("facilChkItemResults", facilChkItemResultsDataSource);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

        return new File(outputFileName);
    }

    /**
     * 가동전점검 보고서 Excel 출력
     *
     * @param plantCd
     * @param safCheckTypeCd
     * @param safFacilChkNo
     * @param chkUserNm
     * @param chkSchYmd
     * @param facilityNames
     * @return
     * @throws Exception
     */
    public File getPreStartReportPrintExcel(String plantCd, String safCheckTypeCd, int safFacilChkNo, String chkUserNm, String chkSchYmd, String facilityNames, DefaultParam defaultParam) throws Exception {
        List<FacilChkItemResult> facilChkItemResults = preOperCheckResultMapper.getFacilChkItems(safFacilChkNo, safCheckTypeCd, "Y", defaultParam);

        if (CollectionUtils.isEmpty(facilChkItemResults) || facilChkItemResults == null) {
            facilChkItemResults.add(new FacilChkItemResult());
        }

        for (FacilChkItemResult facilChkItemResult : facilChkItemResults) {
            if (facilChkItemResult.getChkResult().equals("Y")) {
                facilChkItemResult.setChkResultNm("Yes");
            } else if (facilChkItemResult.getChkResult().equals("N")) {
                facilChkItemResult.setChkResultNm("No");
            } else {
                facilChkItemResult.setChkResultNm("N/A");
            }
        }
        // 파일경로 setting
        String reportPath = globalSettings.getPreStartCheckReport();
        String subReportDir = globalSettings.getPreStartCheckDir();

        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = file.getAbsolutePath();
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
        String logoPath = globalSettings.getLogoImageFilePath();

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        List<FacilChkItemResult> data = new ArrayList<FacilChkItemResult>();
        data.add(facilChkItemResults.get(0));
        JRDataSource datasource = new JRBeanCollectionDataSource(data);

        JRDataSource facilChkItemResultsDataSource = new JRBeanCollectionDataSource(facilChkItemResults);

        // 파라미터값
        java.util.Map<String, Object> parameters = new HashMap<>();
        parameters.put("subreport_dir", subReportDir);
        parameters.put("logo_dir", logoPath);
        parameters.put("chkUserNm", chkUserNm);
        parameters.put("chkSchYmd", chkSchYmd);
        parameters.put("facilityNames", facilityNames);
        parameters.put("facilChkItemResults", facilChkItemResultsDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
        // Export the report to a PDF
        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // outputFileName);

        // Export the report to a excel
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        File outputFile = new File(outputFileName);
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setAutoFitPageHeight(true);
        configuration.setCollapseRowSpan(false);
        exporter.setConfiguration(configuration);
        exporter.exportReport();

        return new File(outputFileName);
    }

    /**
     * 가동전점검 점검자 조회
     *
     * @param safFacilChkNo
     * @return
     * @throws Exception
     */
    public List<SafPreOperChkPsn> getPreOperChkPsns(int safFacilChkNo, String inspectorClassCd) throws Exception {
        return preOperCheckResultMapper.getPreOperChkPsns(safFacilChkNo, inspectorClassCd);
    }
}
