package com.she.safety.psm.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import com.she.common.model.DefaultParam;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.service.AttachFileService;
import com.she.config.GlobalSettings;
import com.she.impr.mapper.ImprMapper;
import com.she.impr.service.ImprService;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.model.SafPsmAuditRsltDept;
import com.she.safety.model.SafPsmAuditRsltImpr;
import com.she.safety.model.SafPsmAuditRsltItem;
import com.she.safety.model.SafPsmAuditRsltStd;
import com.she.safety.model.SafPsmAuditRsltUser;
import com.she.safety.psm.mapper.AuditPlanMapper;
import com.she.safety.psm.mapper.AuditResultMapper;
import com.she.utils.ConstVal;
import com.she.utils.FileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/*
* PSM 결과 기능정의
*
*/
@Service
public class AuditResultService {

    @Autowired
    private AuditResultMapper auditResultMapper;

    @Autowired
    private ImprMapper imprMapper;

    @Autowired
    private GlobalSettings globalSettings;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private ImprService imprService;

    @Autowired
    private AuditPlanMapper auditPlanMapper;

    /**
     * PSM 결과 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 결과 목록
     * @throws Exception
     */
    public List<SafPsmAuditRslt> getAuditResults(HashMap<String, Object> parameter, DefaultParam defaultParam) throws Exception {
        return auditResultMapper.getAuditResults(parameter, defaultParam);
    }

    /**
     * PSM 결과 상세 조회
     *
     * @param auditRsltNo
     *            PSM 결과 번호
     * @return PSM 결과
     * @throws Exception
     */
    public SafPsmAuditRslt getAuditResult(int auditRsltNo, DefaultParam defaultParam) throws Exception {

        SafPsmAuditRslt safPsmAuditRslt = new SafPsmAuditRslt();
        // 결과 조회
        safPsmAuditRslt = auditResultMapper.getSafPsmAuditRslt(auditRsltNo, defaultParam);

        // 대상부서
        safPsmAuditRslt.setSafPsmAuditRsltDepts(auditResultMapper.getSafPsmAuditRsltDepts(auditRsltNo, defaultParam));

        for (SafPsmAuditRsltDept safPsmAuditRsltDept : safPsmAuditRslt.getSafPsmAuditRsltDepts()) {
            // 감사기준
            safPsmAuditRsltDept.setSafPsmAuditRsltDeptStds(auditResultMapper.getSafPsmAuditRsltStds(auditRsltNo, safPsmAuditRsltDept.getTargetDeptCd(), defaultParam));
            // 감사항목
            if (safPsmAuditRsltDept.getSafPsmAuditRsltDeptStds() != null && safPsmAuditRsltDept.getSafPsmAuditRsltDeptStds().size() > 0) {
                for (SafPsmAuditRsltStd safPsmAuditRsltStd : safPsmAuditRsltDept.getSafPsmAuditRsltDeptStds()) {
                    safPsmAuditRsltStd.setSafPsmAuditRsltItems(auditResultMapper.getSafPsmAuditRsltItems(safPsmAuditRsltStd, defaultParam));

                    if (safPsmAuditRsltStd.getSafPsmAuditRsltItems() != null && safPsmAuditRsltStd.getSafPsmAuditRsltItems().size() > 0) {
                        for (SafPsmAuditRsltItem safPsmAuditRsltItem : safPsmAuditRsltStd.getSafPsmAuditRsltItems()) {
                            safPsmAuditRsltItem.setFiles(attachFileMapper.getUploadFiles("AUDIT_RSLT", Integer.toString(safPsmAuditRsltItem.getAuditRsltDeptItemNo())));
                        }
                    }
                }
            }
        }

        // 감사원
        safPsmAuditRslt.setSafPsmAuditRsltUsers(auditResultMapper.getSafPsmAuditRsltUsers(auditRsltNo, defaultParam));

        // 개선조치사항 항목 조회
        safPsmAuditRslt.setSafPsmAuditRsltImprs(auditResultMapper.getAuditResultImpr(ConstVal.SAF_IMPR_CLASS_PSM, auditRsltNo, defaultParam));

        // 감사기준
        safPsmAuditRslt.setSafPsmAuditRsltStds(auditPlanMapper.getSafPsmAuditRsltStds(auditRsltNo, defaultParam));

        // 감사항목
        if (safPsmAuditRslt.getSafPsmAuditRsltStds() != null && safPsmAuditRslt.getSafPsmAuditRsltStds().size() > 0) {
            for (SafPsmAuditRsltStd safPsmAuditRsltStd : safPsmAuditRslt.getSafPsmAuditRsltStds()) {
                safPsmAuditRsltStd.setFiles(attachFileMapper.getUploadFiles("AUDIT_STD", Integer.toString(safPsmAuditRsltStd.getAuditStdNo())));
                safPsmAuditRsltStd.setSafPsmAuditRsltItems(auditPlanMapper.getSafPsmAuditRsltItems(safPsmAuditRsltStd.getAuditStdNo(), defaultParam));
            }
        }

        return safPsmAuditRslt;
    }

    /**
     * 개선조치사항 항목 조회
     *
     * @param imprClassCd
     *            개선분류코드 코드
     * @param auditRsltNo
     *            감사결과번호
     * @return PSM 감사결과_개선사항 목록
     * @throws Exception
     */
    public List<SafPsmAuditRsltImpr> getAuditResultImpr(String imprClassCd, int auditRsltNo, DefaultParam defaultParam) throws Exception {
        return auditResultMapper.getAuditResultImpr(imprClassCd, auditRsltNo, defaultParam);
    }

    /**
     * PSM 결과 수정
     *
     * @param safPsmAuditRslt
     *            PSM 결과
     * @return PSM 결과 키
     */
    @Transactional
    public int updateAuditResult(SafPsmAuditRslt safPsmAuditRslt, String taskClassNm, List<String> tempIds, MultipartFile[] files) throws Exception {

        // 결과 수정
        auditResultMapper.updateSafPsmAuditRslt(safPsmAuditRslt);

        if (safPsmAuditRslt.getSafPsmAuditRsltDepts() != null && safPsmAuditRslt.getSafPsmAuditRsltDepts().size() > 0) {
            for (SafPsmAuditRsltDept safPsmAuditRsltDept : safPsmAuditRslt.getSafPsmAuditRsltDepts()) {
                if (safPsmAuditRsltDept.getSafPsmAuditRsltDeptStds() != null && safPsmAuditRsltDept.getSafPsmAuditRsltDeptStds().size() > 0) {
                    for (SafPsmAuditRsltStd safPsmAuditRsltStd : safPsmAuditRsltDept.getSafPsmAuditRsltDeptStds()) {
                        if (safPsmAuditRsltStd.getAuditRsltNo() > 0) {
                            safPsmAuditRsltStd.setUpdateUserId(safPsmAuditRslt.getUpdateUserId());
                            safPsmAuditRsltStd.setTargetDeptCd(safPsmAuditRsltDept.getTargetDeptCd());
                            auditResultMapper.updateSafPsmAuditRsltStd(safPsmAuditRsltStd);

                            // attachFileService.uploadTableFiles(String.valueOf(safPsmAuditRsltStd.getAuditRsltDeptStdNo()),
                            // safPsmAuditRsltStd.getTempId(),
                            // safPsmAuditRslt.getUpdateUserId(), taskClassNm,
                            // tempIds, files);

                            if (safPsmAuditRsltStd.getSafPsmAuditRsltItems() != null && safPsmAuditRsltStd.getSafPsmAuditRsltItems().size() > 0) {
                                for (SafPsmAuditRsltItem safPsmAuditRsltItem : safPsmAuditRsltStd.getSafPsmAuditRsltItems()) {
                                    if (safPsmAuditRsltItem.getAuditRsltNo() > 0) {
                                        safPsmAuditRsltItem.setTargetDeptCd(safPsmAuditRsltDept.getTargetDeptCd());
                                        safPsmAuditRsltItem.setUpdateUserId(safPsmAuditRslt.getUpdateUserId());
                                        auditResultMapper.updateSafPsmAuditRsltItem(safPsmAuditRsltItem);

                                        attachFileService.uploadTableFiles(String.valueOf(safPsmAuditRsltItem.getAuditRsltDeptItemNo()), safPsmAuditRsltItem.getTempId(), safPsmAuditRslt.getUpdateUserId(), taskClassNm, tempIds, files);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // 개선사항 수정
        if (safPsmAuditRslt.getSafPsmAuditRsltImprs() != null && safPsmAuditRslt.getSafPsmAuditRsltImprs().size() > 0) {
            for (SafPsmAuditRsltImpr safPsmAuditRsltImpr : safPsmAuditRslt.getSafPsmAuditRsltImprs()) {
                if (safPsmAuditRsltImpr.getAuditRsltNo() > 0) {
                    if (safPsmAuditRslt.getPsmProgState() == ConstVal.CODE_MASTER_PSM_PROG_STATE_RESULT_COMPLETE) {
                        safPsmAuditRsltImpr.setImprStepCd(ConstVal.SAF_IMPR_STEP_UNRELIEVED);
                    }
                    safPsmAuditRsltImpr.setUpdateUserId(safPsmAuditRslt.getUpdateUserId());
                    auditResultMapper.updateSafPsmAuditRsltImpr(safPsmAuditRsltImpr);
                }
            }
        }

        // 개선사항 삭제
        if (safPsmAuditRslt.getDeleteSafPsmAuditRsltImprs() != null && safPsmAuditRslt.getDeleteSafPsmAuditRsltImprs().size() > 0) {
            for (SafPsmAuditRsltImpr safPsmAuditRsltImpr : safPsmAuditRslt.getDeleteSafPsmAuditRsltImprs()) {
                if (safPsmAuditRsltImpr.getSafImprActNo() > 0) {
                    imprMapper.deleteImprAct(String.valueOf(safPsmAuditRsltImpr.getSafImprActNo()));
                }
            }
        }

        if (safPsmAuditRslt.getPsmProgState().equals(ConstVal.CODE_MASTER_PSM_PROG_STATE_RESULT_COMPLETE)) {
            // 개선사항 접수단계로 변경처리
            imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_PSM, safPsmAuditRslt.getAuditRsltNo(), safPsmAuditRslt.getUpdateUserId());

        }
        return safPsmAuditRslt.getAuditRsltNo();
    }

    /**
     * PSM 결과결재요청
     *
     * @param auditRsltNo
     *            PSM 결과
     * @return PSM 결과 키
     */
    public int apprProcessAuditResult(int auditRsltNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String psmProgState = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
            psmProgState = ConstVal.CODE_MASTER_PSM_PROG_STATE_APPR_COMPLETE;
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결과결재완료
            psmProgState = ConstVal.CODE_MASTER_PSM_PROG_STATE_RESULT_APPR_COMPLETE;
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(bizApprStepCd)) {
            // 결재요청
            psmProgState = ConstVal.CODE_MASTER_PSM_PROG_STATE_RESULT_APPR_REQUEST;
        }
        return auditResultMapper.apprProcessAuditResult(auditRsltNo, psmProgState, apprRqstNo);
    }

    /**
     * 감사결과 보고서 출력
     *
     * @param
     * @return 감사결과 보고서
     * @throws Exception
     *             예외
     */
    public File printAuditResult(int auditRsltNo, DefaultParam defaultParam) throws Exception {
        // 사고 상세조회
        SafPsmAuditRslt safPsmAuditRslt = this.getAuditResult(auditRsltNo, defaultParam);

        List<SafPsmAuditRsltUser> seniorAuditorUsers = new ArrayList<SafPsmAuditRsltUser>();
        List<SafPsmAuditRsltUser> auditorUsers = new ArrayList<SafPsmAuditRsltUser>();

        if (safPsmAuditRslt.getSafPsmAuditRsltUsers() != null) {
            for (SafPsmAuditRsltUser safPsmAuditRsltUser : safPsmAuditRslt.getSafPsmAuditRsltUsers()) {
                safPsmAuditRsltUser.setUserNm(" 성명 : " + safPsmAuditRsltUser.getUserNm());
                if ("AT10".equals(safPsmAuditRsltUser.getAuditTypeCd())) {
                    seniorAuditorUsers.add(safPsmAuditRsltUser);
                } else {
                    auditorUsers.add(safPsmAuditRsltUser);
                }
            }
        }

        List<SafPsmAuditRsltUser> dataSeniorAuditorUsers = new ArrayList<SafPsmAuditRsltUser>();
        List<SafPsmAuditRsltUser> dataAuditorUsers = new ArrayList<SafPsmAuditRsltUser>();
        if (seniorAuditorUsers != null && seniorAuditorUsers.size() > 0) {
            SafPsmAuditRsltUser user = new SafPsmAuditRsltUser();
            for (int i = 0; i < seniorAuditorUsers.size(); i++) {
                int idx = i % 3;
                switch (idx) {
                case 0:
                    user.setUserNm(seniorAuditorUsers.get(i).getUserNm());
                    break;
                case 1:
                    user.setUserNm1(seniorAuditorUsers.get(i).getUserNm());
                    break;
                case 2:
                    user.setUserNm2(seniorAuditorUsers.get(i).getUserNm());
                    break;
                }

                if (i == seniorAuditorUsers.size() - 1 || idx == 2) {

                    dataSeniorAuditorUsers.add(user);
                }
            }
        }

        if (auditorUsers != null && auditorUsers.size() > 0) {
            SafPsmAuditRsltUser audiUser = new SafPsmAuditRsltUser();
            for (int i = 0; i < auditorUsers.size(); i++) {
                int idx = i % 3;
                switch (idx) {
                case 0:
                    audiUser.setUserNm(auditorUsers.get(i).getUserNm());
                    break;
                case 1:
                    audiUser.setUserNm1(auditorUsers.get(i).getUserNm());
                    break;
                case 2:
                    audiUser.setUserNm2(auditorUsers.get(i).getUserNm());
                    break;
                }

                if (i == auditorUsers.size() - 1 || idx == 2) {

                    dataAuditorUsers.add(audiUser);
                    audiUser = new SafPsmAuditRsltUser();
                }
            }
        }

        List<SafPsmAuditRsltDept> dataAuditorDepts = new ArrayList<SafPsmAuditRsltDept>();
        if (safPsmAuditRslt.getSafPsmAuditRsltDepts() != null && safPsmAuditRslt.getSafPsmAuditRsltDepts().size() > 0) {
            SafPsmAuditRsltDept dept = new SafPsmAuditRsltDept();
            for (int i = 0; i < safPsmAuditRslt.getSafPsmAuditRsltDepts().size(); i++) {
                int deptIdx = i % 2;
                switch (deptIdx) {
                case 0:
                    dept.setTargetDeptNm(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                case 1:
                    dept.setTargetDeptNm1(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                case 2:
                    dept.setTargetDeptNm2(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                case 3:
                    dept.setTargetDeptNm3(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                case 4:
                    dept.setTargetDeptNm4(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                case 5:
                    dept.setTargetDeptNm5(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                case 6:
                    dept.setTargetDeptNm6(safPsmAuditRslt.getSafPsmAuditRsltDepts().get(i).getTargetDeptNm());
                    break;
                }
                if (i == safPsmAuditRslt.getSafPsmAuditRsltDepts().size() - 1 || deptIdx == 1) {

                    dataAuditorDepts.add(dept);
                    dept = new SafPsmAuditRsltDept();
                }
            }
        }

        JRBeanCollectionDataSource dataSourceSeniorAuditorUser = new JRBeanCollectionDataSource(dataSeniorAuditorUsers);
        JRBeanCollectionDataSource dataSourceAuditorUser = new JRBeanCollectionDataSource(dataAuditorUsers);
        JRBeanCollectionDataSource dataAuditorDept = new JRBeanCollectionDataSource(dataAuditorDepts);

        // List to Datasource
        List<SafPsmAuditRslt> data = new ArrayList<SafPsmAuditRslt>();
        data.add(safPsmAuditRslt);
        JRDataSource datasource = new JRBeanCollectionDataSource(data);

        // 파일경로 setting
        String reportPath = globalSettings.getSelfAuditResult();
        String subReportDir = globalSettings.getReportAuditResultDir();
        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = (file != null ? file.getAbsolutePath() : null);
        String outputFileNamepdf = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        String auditTypeTxt = "□ 정기감사                   □ 특별감사                   □ 외부감사";
        if (safPsmAuditRslt.getAuditType().equals("10")) {
            auditTypeTxt = "■ 정기감사                   □ 특별감사                   □ 외부감사";
        } else if (safPsmAuditRslt.getAuditType().equals("20")) {
            auditTypeTxt = "□ 정기감사                   □ 특별감사                   ■ 외부감사";
        } else if (safPsmAuditRslt.getAuditType().equals("30")) {
            auditTypeTxt = "□ 정기감사                   ■ 특별감사                   □ 외부감사";
        }

        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("subreport_dir", subReportDir);
        parameters.put("auditNm", safPsmAuditRslt.getAuditNm()); // 감사명
        parameters.put("auditType", safPsmAuditRslt.getAuditType()); // 감사종류
        parameters.put("auditDt", safPsmAuditRslt.getAuditDt()); // 감사기간
        parameters.put("targetDt", safPsmAuditRslt.getTargetDt()); // 대상기간
        parameters.put("seniorAuditorRemark", safPsmAuditRslt.getSeniorAuditorRemark()); // 비고(선임감사원)
        parameters.put("auditorRemark", safPsmAuditRslt.getAuditorRemark()); // 비고(감사원)
        parameters.put("nextRemark", safPsmAuditRslt.getNextRemark()); // 차기감사시반영사항
        parameters.put("auditTypeTxt", auditTypeTxt); // 감사종류텍스트
        parameters.put("dataSourceSeniorAuditorUser", dataSourceSeniorAuditorUser); // 선임감사원
        parameters.put("dataSourceAuditorUser", dataSourceAuditorUser); // 감사원
        parameters.put("dataAuditorDept", dataAuditorDept); // 대상부서

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileNamepdf);

        return new File(outputFileNamepdf);
    }

}
