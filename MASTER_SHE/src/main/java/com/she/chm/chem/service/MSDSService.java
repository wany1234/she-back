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
package com.she.chm.chem.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.controller.MSDSController;
import com.she.chm.chem.mapper.MSDSMapper;
import com.she.chm.model.MSDS;
import com.she.chm.model.MSDSPicGraph;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.config.GlobalSettings;
import com.she.security.auth.IAuthenticationFacade;
import com.she.utils.FileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class MSDSService {

    private final Logger logger = LoggerFactory.getLogger(MSDSController.class);

    @Autowired
    private MSDSMapper msdsMapper;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private GlobalSettings globalSettings;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    /**
     * MSDS 조회
     *
     * @param search
     *            검색어 (MSDS명 및 CAS NO.)
     * @param delYn
     *            삭제여부
     * @param newYn
     *            최신본여부
     * @param chemProdNo
     *            취급물질번호
     * @param msdsRqstNo
     *            MSDS 그룹번호
     * @return MSDS 목록
     * @throws Exception
     */
    public List<MSDS> getMsdss(String search, String delYn, String newYn, String plantCd, String fromYmd, String toYmd, int chemProdNo, int msdsGrpNo, DefaultParam defaultParam) throws Exception {
        return msdsMapper.getMsdss(search, delYn, newYn, plantCd, fromYmd, toYmd, chemProdNo, msdsGrpNo, defaultParam);
    }

    /**
     * MSDS 상세 조회
     *
     * @param msdsRqstNo
     *            MSDS 번호
     * @return MSDS
     * @throws Exception
     */
    public MSDS getMsds(int msdsRqstNo, DefaultParam defaultParam) throws Exception {
        MSDS msds = this.msdsMapper.getMsds(msdsRqstNo, defaultParam);
        msds.setMsdsPicGraphs(this.msdsMapper.getMsdsPicGraphs(msdsRqstNo));
        return msds;
    }

    /**
     * 중복검사
     *
     * @param makecpCd
     * @param vendorCd
     * @param chemProdNmKr
     * @param chemProdNmEn
     * @param msdsRqstNo
     * @param revNum
     * @param msdsGrpNo
     * @return 중복 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkMsds(String makecpCd, String vendorCd, String chemProdNmKr, String chemProdNmEn, int msdsRqstNo, int revNum, int msdsGrpNo) throws Exception {
        return this.msdsMapper.checkMsds(makecpCd, vendorCd, chemProdNmKr, chemProdNmEn, msdsRqstNo, revNum, msdsGrpNo);
    }

    /**
     * MSDS 신규등록
     *
     * @param MSDS
     * @return MSDS 번호
     * @throws Exception
     */
    @Transactional
    public MSDS createMsds(MSDS msds) throws Exception {
        if (msds.getMsdsGrpNo() != 0) {
            // 개정을 위한 용도로 이전 버전의 경우 최신본여부를 N으로 바꾸어 준다.
            this.msdsMapper.updateNewMsds(msds.getMsdsGrpNo());
        }
        // MSDS 등록
        this.msdsMapper.createMsds(msds);
        saveMsdsPicGraphs(msds.getMsdsRqstNo(), msds.getMsdsPicGraphs());

        // 저장시 취급자재가 변경되어, 취급자재의 MSDS파일이 자동 첨부하는 경우
        if ("Y".equalsIgnoreCase(msds.getChemProdMsdsYn())) {
            List<AttachFile> prodAttachMsdsLists = attachFileService.getFileList("CHEMPROD_MSDS", String.valueOf(msds.getChemProdNo()));
            for (AttachFile attachFile : prodAttachMsdsLists) {
                for (int fileNo : msds.getChemProdMsdsFiles()) {
                    if (attachFile.getFileNo() == fileNo) {
                        logger.info("::적용할 취급자재의 MSDS파일::");
                        attachFileService.copyFile(fileNo, "MSDS", String.valueOf(msds.getMsdsRqstNo()), iAuthenticationFacade.getUserName(""));
                    }
                }
            }
        }

        return this.getMsds(msds.getMsdsRqstNo(), new DefaultParam("kr"));
    }

    /**
     * MSDS 수정
     *
     * @param MSDS
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public MSDS updateMsds(MSDS msds) throws Exception {
        this.msdsMapper.updateMsds(msds);
        saveMsdsPicGraphs(msds.getMsdsRqstNo(), msds.getMsdsPicGraphs());

        // 저장시 취급자재가 변경되어, 취급자재의 MSDS파일이 자동 첨부하는 경우
        if ("Y".equalsIgnoreCase(msds.getChemProdMsdsYn())) {
            List<AttachFile> prodAttachMsdsLists = attachFileService.getFileList("CHEMPROD_MSDS", String.valueOf(msds.getChemProdNo()));
            for (AttachFile attachFile : prodAttachMsdsLists) {
                for (int fileNo : msds.getChemProdMsdsFiles()) {
                    if (attachFile.getFileNo() == fileNo) {
                        logger.info("::적용할 취급자재의 MSDS파일::");
                        attachFileService.copyFile(fileNo, "MSDS", String.valueOf(msds.getMsdsRqstNo()), iAuthenticationFacade.getUserName(""));
                    }
                }
            }
        }
        return msds;
    }

    /**
     * MSDS 요약정보 - 그림문자 저장
     *
     * @param msdsRqstNo
     * @param msdsPicGraphs
     * @throws Exception
     */
    public void saveMsdsPicGraphs(int msdsRqstNo, List<String> msdsPicGraphs) throws Exception {
        // MSDS 요약정보 - 그림문자 삭제
        this.msdsMapper.deleteMsdsPicGraphs(msdsRqstNo);
        // MSDS 요약정보 - 그림문자 등록
        if (msdsPicGraphs != null && msdsPicGraphs.size() > 0) {
            for (String picGraphCd : msdsPicGraphs) {
                MSDSPicGraph msdsPicGraph = new MSDSPicGraph();
                msdsPicGraph.setMsdsRqstNo(msdsRqstNo);
                msdsPicGraph.setPicGraphCd(picGraphCd);
                this.msdsMapper.createMsdsPicGraphs(msdsPicGraph);
            }
        }
    }

    /**
     * 경고표시출력
     *
     * @param msdsRqstNo
     * @return
     * @throws Exception
     */
    public File getMsdsWarnSignReport(int msdsRqstNo) throws Exception {
        // MSDS 기본정보
        MSDS msds = this.getMsds(msdsRqstNo, new DefaultParam("kr"));
        List<MSDS> msdss = new ArrayList<MSDS>();
        msdss.add(msds);
        // MSDS 그림문자 목록
        String imageFilePath = globalSettings.getReportChmMsdsImages();
        List<String> picGraphs = this.msdsMapper.getMsdsPicGraphNms(msdsRqstNo, new DefaultParam("kr"));
        List<Map<String, String>> picGraphsMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < picGraphs.size(); i++) {
            Map<String, String> pic = new HashMap<String, String>();
            switch (picGraphs.size()) {
            case 1:
                pic.put("picGraphNm1", picGraphs.get(i));
                break;
            case 2:
                pic.put("picGraphNm2", picGraphs.get(i));
                break;
            case 3:
                pic.put("picGraphNm3", picGraphs.get(i));
                break;
            case 4:
                pic.put("picGraphNm4", picGraphs.get(i));
                break;
            case 5:
                pic.put("picGraphNm5", picGraphs.get(i));
                break;
            case 6:
                pic.put("picGraphNm6", picGraphs.get(i));
                break;
            case 7:
                pic.put("picGraphNm7", picGraphs.get(i));
                break;
            case 8:
                pic.put("picGraphNm8", picGraphs.get(i));
                break;
            case 9:
                pic.put("picGraphNm9", picGraphs.get(i));
                break;
            default:
                pic.put("picGraphNm1", picGraphs.get(i));
                break;
            }

            picGraphsMap.add(pic);
        }

        JRDataSource msdsList = new JRBeanCollectionDataSource(msdss);
        JRDataSource msdsPicList = new JRBeanCollectionDataSource(picGraphsMap);

        // 파일경로 setting
        String reportPath = globalSettings.getReportChmMsdsWarnSign();
        String subReportDir = globalSettings.getReportChmMsdsWarnSignDir();

        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = (file != null ? file.getAbsolutePath() : null);
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("report_dir", subReportDir);
        parameters.put("image_dir", imageFilePath);
        parameters.put("chemProdNmKr", msds.getChemProdNmKr());
        parameters.put("msdsPicList", msdsPicList);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, msdsList);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

        return new File(outputFileName);
    }

    /**
     * 공정관리요령출력
     *
     * @param msdsRqstNo
     * @return
     * @throws Exception
     */
    public File getMsdsSaftyMngReport(int msdsRqstNo) throws Exception {

        File file = this.makeJasperReportToPdf(msdsRqstNo, globalSettings.getReportChmMsdsWarnSign());
        return file;
    }

    /**
     * MSDS요약본출력
     *
     * @param msdsRqstNo
     * @return
     * @throws Exception
     */
    public File getMsdsReport(int msdsRqstNo) throws Exception {
        File file = this.makeJasperReportToPdf(msdsRqstNo, globalSettings.getReportChmMsdsWarnSign());
        return file;
    }

    public AttachFile searchFile(String fileNo) throws Exception {
        return msdsMapper.searchFile(fileNo);
    }

    /**
     * msds요청번호로 pdf파일생성
     *
     * @param msdsRqstNo
     * @param filePath
     * @return
     * @throws Exception
     */
    private File makeJasperReportToPdf(int msdsRqstNo, String filePath) throws Exception {
        String fileName = FilenameUtils.getBaseName(filePath);
        String fileExt = "." + FilenameUtils.getExtension(filePath);
        logger.debug("::: fileName > " + fileName);
        logger.info("::: fileExt > " + fileExt);

        File file = FileUtil.getFileFixedBase64(filePath, fileName, fileExt);
        String reportFileName = (file != null ? file.getAbsolutePath() : null);
        logger.info("::: reportFileName > " + reportFileName);
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
        logger.info("::: outputFileName > " + outputFileName);

        MSDS msds = this.getMsds(msdsRqstNo, new DefaultParam("kr"));
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            List<MSDS> msdsReport = new ArrayList<MSDS>();
            msdsReport.add(msds);
            JRDataSource datasource = new JRBeanCollectionDataSource(msdsReport);
            map.put("msdsRqstNo", msdsRqstNo);
            map.put("datasource", datasource);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, datasource);
            // JasperFillManager.fillReportToFile(jasperReport, outputFileName,
            // map, datasource)
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);
        } catch (JRException e) {
            throw e;
        }

        return new File(outputFileName);
    }

    @Transactional
    public int msdsDeleteLicensingStatus(List<MSDS> licensingStatus) throws Exception {
        int count = 0;
        for (MSDS licensingStatu : licensingStatus) {
            count += this.msdsMapper.msdsDeleteLicensingStatus(licensingStatu);
        }

        return count;
    }

    public int deleteLicensingStatus(int msdsRqstNo) throws Exception {
        return msdsMapper.deleteLicensingStatus(msdsRqstNo);
    }
}
