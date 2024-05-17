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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.mapper.ChemProceessManageMapper;
import com.she.chm.model.ChemProcessManage;
import com.she.chm.model.ChemProcessManageChemprodVal;
import com.she.chm.model.ProcessManagePicGraph;
import com.she.config.GlobalSettings;
import com.she.utils.FileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ChemProcessManageService {

    @Autowired
    private ChemProceessManageMapper chemProceessManageMapper;

    @Autowired
    private GlobalSettings globalSettings;

    /**
     * 공정관리요령 조회
     * 
     * @param useYn
     * @param plantCd
     * @param deptCd
     * @param processCd
     * @return
     * @throws Exception
     */
    public List<ChemProcessManage> getChemProcessManages(String search, String useYn, String plantCd, String deptCd, String processCd) throws Exception {
        return chemProceessManageMapper.getChemProcessManages(search, useYn, plantCd, deptCd, processCd);
    }

    /**
     * 공정관리요령 상세정보 조회
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public ChemProcessManage getChemProcessManage(int chmProcessManageNo) throws Exception {
        ChemProcessManage chemProcessManage = chemProceessManageMapper.getChemProcessManage(chmProcessManageNo);
        chemProcessManage.setChemProcessManageChemprodVals(chemProceessManageMapper.getChemProcessManageChemprodVals(chmProcessManageNo));
        chemProcessManage.setProcessManagePicGraphs(this.chemProceessManageMapper.getProcessManagePicGraphs(chmProcessManageNo));
        return chemProcessManage;
    }

    /**
     * 공정관리요령 신규등록
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    @Transactional
    public ChemProcessManage createChemProcessManage(ChemProcessManage chemProcessManage) throws Exception {
        this.chemProceessManageMapper.createChemProcessManage(chemProcessManage);
        saveChemProcessManageChemprodVal(chemProcessManage);
        saveProcessManagePicGraphs(chemProcessManage.getChmProcessManageNo(), chemProcessManage.getProcessManagePicGraphs());
        return chemProcessManage;
    }

    /**
     * 공정관리요령 수정
     * 
     * @param chemProcessManage
     * @return
     * @throws Exception
     */
    @Transactional
    public ChemProcessManage updateChemProcessManage(ChemProcessManage chemProcessManage) throws Exception {
        this.chemProceessManageMapper.updateChemProcessManage(chemProcessManage);
        saveChemProcessManageChemprodVal(chemProcessManage);
        saveProcessManagePicGraphs(chemProcessManage.getChmProcessManageNo(), chemProcessManage.getProcessManagePicGraphs());
        return chemProcessManage;
    }

    /***
     * 공정관리요령 그림문자 등록
     * 
     * @param chmProcessManageNo
     * @param processManagePicGraphs
     * @throws Exception
     */
    public void saveProcessManagePicGraphs(int chmProcessManageNo, List<String> processManagePicGraphs) throws Exception {
        this.chemProceessManageMapper.deleteProcessManagePicGraphs(chmProcessManageNo);
        if (processManagePicGraphs != null && processManagePicGraphs.size() > 0) {
            for (String picGraphCd : processManagePicGraphs) {
                ProcessManagePicGraph processManagePicGraph = new ProcessManagePicGraph();
                processManagePicGraph.setChmProcessManageNo(chmProcessManageNo);
                processManagePicGraph.setPicGraphCd(picGraphCd);
                this.chemProceessManageMapper.createProcessManagePicGraphs(processManagePicGraph);
            }
        }
    }

    /**
     * 공정관리요청 취급자재 등록
     * 
     * @param chemProcessManage
     * @throws Exception
     */
    public void saveChemProcessManageChemprodVal(ChemProcessManage chemProcessManage) throws Exception {
        this.chemProceessManageMapper.deleteChemProcessManageChemprodVals(chemProcessManage.getChmProcessManageNo());

        if (chemProcessManage.getChemProcessManageChemprodVals() != null && chemProcessManage.getChemProcessManageChemprodVals().size() != 0) {
            for (ChemProcessManageChemprodVal chemProcessManageChemprodVal : chemProcessManage.getChemProcessManageChemprodVals()) {
                chemProcessManageChemprodVal.setChmProcessManageNo(chemProcessManage.getChmProcessManageNo());
                this.chemProceessManageMapper.createChemProcessManageChemprodVal(chemProcessManageChemprodVal);
            }
        }
    }

    /**
     * 공정관리요령 취급자재목록 조회
     * 
     * @param chmProcessManageNo
     * @return
     * @throws Exception
     */
    public List<ChemProcessManageChemprodVal> getChemProcessManageChemprodVals(int chmProcessManageNo) throws Exception {
        return this.chemProceessManageMapper.getChemProcessManageChemprodVals(chmProcessManageNo);
    }

    /**
     * 출력할 정보
     * 
     * @param chmProcessManageNos
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getPrintChemProcessManage(int[] chmProcessManageNos) throws Exception {
        // 출력 후 pdf로 반환 처리되도록 수정할 것
        List<HashMap<String, Object>> list = this.chemProceessManageMapper.getPrintChemProcessManage(chmProcessManageNos);
        for (HashMap<String, Object> map : list) {
            List<String> graphics = this.chemProceessManageMapper.getProcessManagePicGraphs(Integer.parseInt(map.get("chm_process_manage_no").toString()));
            map.put("processManagePicGraphs", graphics);
        }

        return list;
    }

    /**
     * 공정별 관리요령 출력
     * 
     * @param chmProcessManageNo
     *            공정번호
     * @return
     * @throws Exception
     */
    public File getPrintChemProcessManage(int chmProcessManageNo) throws Exception {
        // 공정별 관리요령 기본정보
        ChemProcessManage chemProcessManage = this.chemProceessManageMapper.getChemProcessManage(chmProcessManageNo);
        List<ChemProcessManage> chemProcessManages = new ArrayList<ChemProcessManage>();
        chemProcessManages.add(chemProcessManage);
        // 공정별 취급물질 목록
        List<ChemProcessManageChemprodVal> chemprodVals = this.chemProceessManageMapper.getChemProcessManageChemprodVals(chmProcessManageNo);
        // 공정별 관리요령 그림문자 목록
        List<String> picGraphs = this.chemProceessManageMapper.getProcessManagePicGraphsNm(chmProcessManageNo);
        List<Map<String, String>> picGraphsMap = new ArrayList<Map<String, String>>();
        for (int i = 0; i < picGraphs.size(); i++) {
            Map<String, String> pic = new HashMap<String, String>();
            pic.put("picGraphNm", picGraphs.get(i));
            picGraphsMap.add(pic);
        }

        JRDataSource chemProcessManageList = new JRBeanCollectionDataSource(chemProcessManages);
        JRDataSource processManagePicList = new JRBeanCollectionDataSource(picGraphsMap);
        JRDataSource processManageChemList = new JRBeanCollectionDataSource(chemprodVals);

        // 파일경로 setting
        String reportPath = globalSettings.getReportChmProcessMng();
        String subReportDir = globalSettings.getReportChmProcessMngDir();
        String imageFilePath = globalSettings.getReportChmMsdsImages();

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
        parameters.put("processNm", chemProcessManage.getProcessNm());
        parameters.put("processManagePicList", processManagePicList);
        parameters.put("processManageChemList", processManageChemList);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, chemProcessManageList);
        // Export the report to a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

        return new File(outputFileName);
    }
}
