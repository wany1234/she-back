package com.she.env.air.operationLog.service;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.she.env.air.model.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.she.common.model.DefaultParam;
import com.she.config.GlobalSettings;

import com.she.env.air.operationLog.mapper.OpLogMapper;
import com.she.env.water.model.OpLogResult;
import com.she.utils.ConstVal;
import com.she.utils.FileUtil;
import com.she.utils.Methods;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class OpLogService {

    @Autowired
    private OpLogMapper oplogMapper;

    @Autowired
    private GlobalSettings globalSettings;

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
    public List<OpLogRslt> getOplogs(String plantCd, String deptCd, String mgDeptCd, String fromDate, String toDate, String envOpLogStCd) throws Exception {

        return this.oplogMapper.getOplogs(plantCd, deptCd, mgDeptCd, fromDate, toDate, envOpLogStCd);
    }

    /**
     * 운영정보-기본정보 상세
     *
     * @param deptCd
     * @param measureYmd
     * @throws Exception
     */
    public OpLogRslt getOplog(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception {

        OpLogRslt opLogRslt = this.oplogMapper.getOplog(deptCd, measureYmd);
        if (opLogRslt == null) {
            opLogRslt = new OpLogRslt();
            opLogRslt.setPlantCd(plantCd);
            opLogRslt.setDeptCd(deptCd);
            opLogRslt.setMeasureYmd(measureYmd);
        }
        return opLogRslt;
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
    public OpLogRslt getAllOplog(String plantCd, String deptCd, String measureYmd) throws Exception {

        OpLogRslt opLogRslt = this.oplogMapper.getOplog(deptCd, measureYmd);
        if (opLogRslt == null) {
            opLogRslt = new OpLogRslt();
            opLogRslt.setPlantCd(plantCd);
            opLogRslt.setDeptCd(deptCd);
            opLogRslt.setMeasureYmd(measureYmd);
        }
        int logCount = this.oplogMapper.getOpLogCheck(measureYmd, deptCd);
        if (logCount > 0) {
            opLogRslt.setDischChkResult(this.oplogMapper.getOpLogOutletDischChkResult(plantCd, deptCd, measureYmd)); // 로그-배출구별_가동시간_조회
            opLogRslt.setPreventChkResult(this.oplogMapper.getOpLogOutletPreventChkResult(plantCd, deptCd, measureYmd)); // 로그-방지시설-운전사항
            opLogRslt.setPreventChemResults(this.oplogMapper.getOpLogPreventChemResult(deptCd, measureYmd)); // 로그-방지시설-운전사항-약품사용량
            opLogRslt.setFuelCheckResult(this.oplogMapper.getOpLogDischFuelResult(deptCd, measureYmd)); // 로그-배출시설-연료
            opLogRslt.setIngrCheckResult(this.oplogMapper.getOpLogIngrChkResult(deptCd, measureYmd, plantCd)); // 로그-운영일지-원료
        } else {
            opLogRslt.setDischChkResult(this.oplogMapper.getOutletDischChkResult(plantCd, deptCd, measureYmd)); // 배출구별_가동시간_조회
            opLogRslt.setPreventChkResult(this.oplogMapper.getOutletPreventChkResult(plantCd, deptCd, measureYmd)); // 방지시설-운전사항
            opLogRslt.setPreventChemResults(this.oplogMapper.getPreventChemResult(deptCd, measureYmd)); // 방지시설-운전사항-약품사용량
            opLogRslt.setFuelCheckResult(this.oplogMapper.getDischFuelResult(deptCd, measureYmd)); // 배출시설-연료
            opLogRslt.setIngrCheckResult(this.oplogMapper.getIngrChkResult(deptCd, measureYmd, plantCd)); // 운영일지-원료
        }
        return opLogRslt;
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
    public List<OutletDischChkResult> getOutletDischChkResult(String plantCd, String deptCd, String measureYmd) throws Exception {

        return this.oplogMapper.getOutletDischChkResult(plantCd, deptCd, measureYmd);
    }

    /**
     * 방지시설-운전사항
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletPreventChkResult> getOutletPreventChkResult(String plantCd, String deptCd, String measureYmd) throws Exception {

        return this.oplogMapper.getOutletPreventChkResult(plantCd, deptCd, measureYmd);

    }

    /**
     * 방지시설-운전사항-약품사용량
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public List<PreventChemResult> getPreventChemResult(String deptCd, String measureYmd) throws Exception {

        return this.oplogMapper.getPreventChemResult(deptCd, measureYmd);
    }

    /**
     * 방지시설-보수내역
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<PreventFacMaintResults> getPreventMaintHist(String deptCd, String measureYmd) throws Exception {
        int logCount = this.oplogMapper.getOpLogCheck(measureYmd, deptCd);
        if (logCount > 0) {
            return this.oplogMapper.getOpLogPreventMaintHist(deptCd, measureYmd);
        } else {
            return this.oplogMapper.getPreventMaintHist(deptCd, measureYmd);
        }
    }

    /**
     * 배출시설-연료
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<FuelCheckResult> getDischFuelResult(String deptCd, String measureYmd) throws Exception {

        return this.oplogMapper.getDischFuelResult(deptCd, measureYmd);
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
    public List<IngrCheckResult> getIngrChkResult(String deptCd, String measureYmd, String plantCd) throws Exception {

        return this.oplogMapper.getIngrChkResult(deptCd, measureYmd, plantCd);
    }

    /**
     * 운영일지 생성
     *
     * @param opLogRslt
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateOplogResult(OpLogRslt opLogRslt) throws Exception {
        OpLogRslt chkOpLogRslt = this.oplogMapper.getOplog(opLogRslt.getDeptCd(), opLogRslt.getMeasureYmd());
        if (chkOpLogRslt == null) {
            this.oplogMapper.createOpLogOplogBase(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogLogOutlet(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogDischFac(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogDischFacFuel(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogPreventFac(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogPreventFacElecMeter(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogIngr(opLogRslt.getMeasureYmd(), opLogRslt.getPlantCd(), opLogRslt.getDeptCd());
            this.oplogMapper.createOpLogCheck(opLogRslt.getMeasureYmd(), opLogRslt.getPlantCd(), opLogRslt.getDeptCd());
        }
        int result = this.oplogMapper.updateOplogResult(opLogRslt);
        if (opLogRslt.getDischChkResult() != null && opLogRslt.getDischChkResult().size() > 0) {
            this.oplogMapper.deleteOutletDischChkResult(opLogRslt.getDeptCd(), opLogRslt.getMeasureYmd());
            for (OutletDischChkResult item : opLogRslt.getDischChkResult()) {
                item.setDeptCd(opLogRslt.getDeptCd());
                item.setMeasureYmd(opLogRslt.getMeasureYmd());
                item.setCreateUserId(opLogRslt.getCreateUserId());
                item.setUpdateUserId(opLogRslt.getUpdateUserId());
                this.oplogMapper.createOutletDischChkResult(item);
            }

        }
        if (opLogRslt.getPreventChkResult() != null && opLogRslt.getPreventChkResult().size() > 0) {
            this.oplogMapper.deleteOutletPreventChkResult(opLogRslt.getDeptCd(), opLogRslt.getMeasureYmd());
            for (OutletPreventChkResult item : opLogRslt.getPreventChkResult()) {
                item.setDeptCd(opLogRslt.getDeptCd());
                item.setMeasureYmd(opLogRslt.getMeasureYmd());
                item.setCreateUserId(opLogRslt.getCreateUserId());
                item.setUpdateUserId(opLogRslt.getUpdateUserId());
                this.oplogMapper.createOutletPreventChkResult(item);
            }
        }
        if (opLogRslt.getPreventChemResults() != null && opLogRslt.getPreventChemResults().size() > 0) {
            this.oplogMapper.deletePreventChemResult(opLogRslt.getDeptCd(), opLogRslt.getMeasureYmd());
            for (PreventChemResult item : opLogRslt.getPreventChemResults()) {
                item.setDeptCd(opLogRslt.getDeptCd());
                item.setMeasureYmd(opLogRslt.getMeasureYmd());
                this.oplogMapper.createPreventChemResult(item);
            }
        }
        if (opLogRslt.getFuelCheckResult() != null && opLogRslt.getFuelCheckResult().size() > 0) {
            this.oplogMapper.deleteDischFuelResult(opLogRslt.getMeasureYmd(), opLogRslt.getDeptCd());
            for (FuelCheckResult item : opLogRslt.getFuelCheckResult()) {
                item.setDeptCd(opLogRslt.getDeptCd());
                item.setMeasureYmd(opLogRslt.getMeasureYmd());
                item.setCreateUserId(opLogRslt.getCreateUserId());
                item.setUpdateUserId(opLogRslt.getUpdateUserId());
                this.oplogMapper.createDischFuelResult(item);
            }
        }
        if (opLogRslt.getIngrCheckResult() != null && opLogRslt.getIngrCheckResult().size() > 0) {
            this.oplogMapper.deleteIngrChkResult(opLogRslt.getDeptCd(), opLogRslt.getMeasureYmd());
            for (IngrCheckResult item : opLogRslt.getIngrCheckResult()) {
                item.setDeptCd(opLogRslt.getDeptCd());
                item.setMeasureYmd(opLogRslt.getMeasureYmd());
                item.setCreateUserId(opLogRslt.getCreateUserId());
                item.setUpdateUserId(opLogRslt.getUpdateUserId());
                this.oplogMapper.createIngrChkResult(item);
            }
        }

        return result;
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
    public List<HashMap<String, Object>> getOperationStatus(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("totalTypeCd") String totalTypeCd, @Param("plantCd") String plantCd, @Param("search") String search, @Param("deptCd") String deptCd, @Param("mainDischFacNm") String mainDischFacNm,
            @Param("DefaultParam") DefaultParam defaultParam) throws Exception {
        List<String> ymCols = new ArrayList<String>();
        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();

        fromDate.setTime(Methods.convertStringToDate(fromYmd));
        toDate.setTime(Methods.convertStringToDate(toYmd));
        for (; fromDate.compareTo(toDate) < 1; fromDate.add(Calendar.MONTH, 1)) {
            String year = fromDate.get(Calendar.YEAR) + "";
            String month = Methods.leftPad(String.valueOf(fromDate.get(Calendar.MONTH) + 1), 2, '0');
            ymCols.add(year + month);
        }

        String ymColStr = String.join(",", ymCols);

        return this.oplogMapper.getOperationStatus(fromYmd, toYmd, ymCols, totalTypeCd, plantCd, search, deptCd, ymColStr, mainDischFacNm, defaultParam);
    }

    /**
     * 대기운영일지 출력
     *
     * @param measureYmd
     *            대기운영일자
     * @param deptCd
     *            부서코드
     * @return 대기운영일지 파일
     * @throws Exception
     */
    public File getEirOpLogResultPrint(String plantCd, String measureYmd, String deptCd, DefaultParam defaultParam) throws Exception {
        // 대기운영일지 기본정보
        OpLogRslt opLogRslt = this.oplogMapper.getOplog(deptCd, measureYmd);
        String[] ymdArr = measureYmd.split("-");
        String year = ymdArr[0];
        String month = ymdArr[1];
        String date = ymdArr[2];

        // 운영일지 시설정보 로그 체크
        int logCount = this.oplogMapper.getOpLogCheck(measureYmd, deptCd);
        // 배출구별 주요배출시설 및 방지시설 가동(조업) 시간 (데이터없을경우 공데이터 1개 기입)
        List<OutletDischChkResult> outletDischChkResults = new ArrayList<>();
        if (logCount > 0) {
            outletDischChkResults = this.oplogMapper.getOpLogOutletDischChkResult(plantCd, deptCd, measureYmd);
        } else {
            outletDischChkResults = this.oplogMapper.getOutletDischChkResult(plantCd, deptCd, measureYmd);
        }

        int addOutletCount = 0;
        if (outletDischChkResults.size() <= 56) {
            addOutletCount = 56 - outletDischChkResults.size();
        } else {
            addOutletCount = 126 - outletDischChkResults.size();
        }

        for (int i = 0; i < addOutletCount; i++) {
            OutletDischChkResult outletChkResult = new OutletDischChkResult();
            outletChkResult.setEairOutletNm("");
            outletChkResult.setEairOutletNum("");
            outletChkResult.setEairDischFacNm("");
            outletChkResult.setRunTm(0.0f);
            outletChkResult.setRunTmt("");
            outletChkResult.setRemark("");
            outletDischChkResults.add(outletChkResult);
        }
        JRDataSource outletChkResultList = new JRBeanCollectionDataSource(outletDischChkResults);
        // 방지시설 운전사항 (데이터없을경우 공데이터 1개 기입)
        List<OutletPreventChkResult> preventChemResults = new ArrayList<>();
        if (logCount > 0) {
            preventChemResults = this.oplogMapper.getOpLogOutletPreventChkResult(plantCd, deptCd, measureYmd);
        } else {
            preventChemResults = this.oplogMapper.getOutletPreventChkResult(plantCd, deptCd, measureYmd);
        }
        int addPreventCount = 40 - preventChemResults.size();
        for (int i = 0; i < addPreventCount; i++) {
            OutletPreventChkResult outletPreventChkResult = new OutletPreventChkResult();
            outletPreventChkResult.setEairPreventFacNm(" ");
            outletPreventChkResult.setEairOutletNm(" ");
            outletPreventChkResult.setInstallPos(" ");
            preventChemResults.add(outletPreventChkResult);
        }

        String xmlFilePath = FileUtil.getStoreFilePath() + File.separator + "xmldata_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xml";

        // data setting
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root element
        Element root = document.createElement("preventFacPwrcChkResultList");
        document.appendChild(root);
        for (int i = 0; i < preventChemResults.size(); i++) {
            OutletPreventChkResult result = preventChemResults.get(i);
            Element preventFacPwrcChkResult = document.createElement("preventFacPwrcChkResult");

            Element eairPreventFacNm = document.createElement("eairPreventFacNm");
            Element eairOutletNo = document.createElement("eairOutletNm");
            Element installPos = document.createElement("installPos");
            Element chemNmpwrConsumAmt = document.createElement("pwrConsumAmt");
            Element dispoCap = document.createElement("dispoCap");
            Element eairPolluNm = document.createElement("eairPolluNm");
            Element dispoConc = document.createElement("dispoConc");
            Element dispoEff = document.createElement("dispoEff");
            Element chems = document.createElement("chems");

            eairPreventFacNm.appendChild(document.createTextNode(result.getEairPreventFacNm() != null ? result.getEairPreventFacNm() : " "));
            eairOutletNo.appendChild(document.createTextNode(result.getEairOutletNm() != null ? result.getEairOutletNm() : " "));
            installPos.appendChild(document.createTextNode(result.getInstallPos() != null ? result.getInstallPos() : " "));
            chemNmpwrConsumAmt.appendChild(document.createTextNode(result.getPwrConsumAmt() != null ? Float.toString(result.getPwrConsumAmt()) : " "));
            dispoCap.appendChild(document.createTextNode(result.getDispoCap() != null ? Float.toString(result.getDispoCap()) : " "));
            eairPolluNm.appendChild(document.createTextNode(result.getEairPolluNm() != null ? result.getEairPolluNm() : " "));
            dispoConc.appendChild(document.createTextNode(result.getDispoConc() != null ? Float.toString(result.getDispoConc()) : " "));
            dispoEff.appendChild(document.createTextNode(result.getDispoEff() != null ? Float.toString(result.getDispoEff()) : " "));

            int countAmt = 0;
            List<PreventChemResult> chemResult = new ArrayList<>();
            if (logCount > 0) {
                chemResult = this.oplogMapper.getOpLogPreventChemResult(deptCd, measureYmd);
            } else {
                chemResult = this.oplogMapper.getPreventChemResult(deptCd, measureYmd);
            }
            for (PreventChemResult list : chemResult) {
                if (result.getEairPreventFacNo() == list.getEairPreventFacNo()) {
                    Element chem = document.createElement("chem");
                    Element chemNm = document.createElement("chemNm");
                    Element consumAmt = document.createElement("consumAmt");
                    chemNm.appendChild(document.createTextNode(list.getEairChemNm()));
                    consumAmt.appendChild(document.createTextNode(Float.toString(list.getConsumAmt()) != null ? String.valueOf(NumberFormat.getInstance().format(list.getConsumAmt()) + "" + list.getEnvUnitNm()) : "0"));

                    chem.appendChild(chemNm);
                    chem.appendChild(consumAmt);
                    chems.appendChild(chem);
                    countAmt++;
                }
            }

            if (result.getChemCd1() != null) {
                Element chem = document.createElement("chem");
                Element chemNm = document.createElement("chemNm");
                Element consumAmt = document.createElement("consumAmt");

                chemNm.appendChild(document.createTextNode(result.getChemNm1() != null ? result.getChemNm1() : " "));
                consumAmt.appendChild(document.createTextNode(Float.toString(result.getConsumAmt1()) != null ? String.valueOf(NumberFormat.getInstance().format(result.getConsumAmt1()) + "" + result.getUnitNm1())

                        : "0"));

                chem.appendChild(chemNm);
                chem.appendChild(consumAmt);
                chems.appendChild(chem);
                countAmt++;
            }
            if (result.getChemCd2() != null) {
                Element chem = document.createElement("chem");
                Element chemNm = document.createElement("chemNm");
                Element consumAmt = document.createElement("consumAmt");

                chemNm.appendChild(document.createTextNode(result.getChemNm2() != null ? result.getChemNm2() : " "));
                consumAmt.appendChild(document.createTextNode(Float.toString(result.getConsumAmt2()) != null ? Float.toString(result.getConsumAmt2()) : "0"));

                chem.appendChild(chemNm);
                chem.appendChild(consumAmt);
                chems.appendChild(chem);
                countAmt++;
            }
            if (result.getChemCd3() != null) {
                Element chem = document.createElement("chem");
                Element chemNm = document.createElement("chemNm");
                Element consumAmt = document.createElement("consumAmt");

                chemNm.appendChild(document.createTextNode(result.getChemNm3() != null ? result.getChemNm3() : " "));
                consumAmt.appendChild(document.createTextNode(Float.toString(result.getConsumAmt3()) != null ? Float.toString(result.getConsumAmt3()) : "0"));

                chem.appendChild(chemNm);
                chem.appendChild(consumAmt);
                chems.appendChild(chem);
                countAmt++;
            }
            if (countAmt == 0) {
                Element chem = document.createElement("chem");
                Element chemNm = document.createElement("chemNm");
                Element consumAmt = document.createElement("consumAmt");

                chemNm.appendChild(document.createTextNode(" "));
                consumAmt.appendChild(document.createTextNode(" "));

                chem.appendChild(chemNm);
                chem.appendChild(consumAmt);
                chems.appendChild(chem);
                countAmt++;
            }
            preventFacPwrcChkResult.appendChild(eairPreventFacNm);
            preventFacPwrcChkResult.appendChild(eairOutletNo);
            preventFacPwrcChkResult.appendChild(installPos);
            preventFacPwrcChkResult.appendChild(chemNmpwrConsumAmt);
            preventFacPwrcChkResult.appendChild(dispoCap);
            preventFacPwrcChkResult.appendChild(eairPolluNm);
            preventFacPwrcChkResult.appendChild(dispoConc);
            preventFacPwrcChkResult.appendChild(dispoEff);
            preventFacPwrcChkResult.appendChild(chems);

            root.appendChild(preventFacPwrcChkResult);
        }
        // create the xml file
        // transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));

        // 출력 시 문자코드: UTF-8
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        // 들여 쓰기 있음
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, streamResult);

        File xmlData = new File(xmlFilePath);
        JRXmlDataSource jrdatasource = new JRXmlDataSource(xmlData, "/preventFacPwrcChkResultList/preventFacPwrcChkResult");
        JRDataSource preventFacPwrcChkResultList = jrdatasource;

        // 안전조치항목
        List<PreventFacMaintResults> preventFacMaintResults = new ArrayList<>();
        if (logCount > 0) {
            preventFacMaintResults = this.oplogMapper.getOpLogPreventMaintHist(deptCd, measureYmd);
        } else {
            preventFacMaintResults = this.oplogMapper.getPreventMaintHist(deptCd, measureYmd);
        }
        if (preventFacMaintResults.size() == 0) {
            PreventFacMaintResults facMaintResults = new PreventFacMaintResults();
            preventFacMaintResults.add(facMaintResults);
        }
        JRDataSource preventFacMaintResultList = new JRBeanCollectionDataSource(preventFacMaintResults);
        // 파일경로 setting
        String reportPath = globalSettings.getReportEnvEair();
        String subReportDir = globalSettings.getReportEnvEairDir();
        String fileName = FilenameUtils.getBaseName(reportPath);
        String fileExt = "." + FilenameUtils.getExtension(reportPath);

        File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
        String reportFileName = (file != null ? file.getAbsolutePath() : null);
        String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        String outputFileNamepdf = FileUtil.getStoreFilePath() + File.separator + fileName + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";

        // Compile the Jasper report from .jrxml to .japser
        JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

        // 필수값 데이터 List
        List<OpLogRslt> opLogRslts = new ArrayList<OpLogRslt>();
        opLogRslts.add(opLogRslt);
        JRDataSource dataSource = new JRBeanCollectionDataSource(opLogRslts);
        // 파라미터값
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("report_dir", subReportDir);
        parameters.put("year", year);
        parameters.put("month", month.lastIndexOf("0") == 0 ? month.substring(1, month.length()) : month.toString());
        parameters.put("date", date.lastIndexOf("0") == 0 ? date.substring(1, date.length()) : date.toString());
        parameters.put("outletChkResultList", outletChkResultList);
        parameters.put("preventFacPwrcChkResultList", preventFacPwrcChkResultList);
        parameters.put("preventFacMaintResultList", preventFacMaintResultList);

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        // Export the report to a PDF
        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // outputFileNamepdf);
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
        exporter.exportReport();

        return new File(outputFileName);
    }

    /**
     * 대기 운영일지 배출구 기본 데이터
     *
     * @param measureYmd
     *            측정일자
     * @param deptCd
     *            부서코드
     * @param plantCd
     *            사업장코드
     * @return 등록행수
     * @throws Exception
     */
    public int createOperationLogChk(String measureYmd, String deptCd, String plantCd) throws Exception {
        return this.oplogMapper.createOperationLogChk(measureYmd, deptCd, plantCd);
    }

    /**
     * 운영일지 결재
     *
     * @param measureYmd
     * @param deptCd
     * @param apprStepCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int updateAppr(String measureYmd, String deptCd, String apprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String stepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재완료
            OpLogRslt opLogRslt = this.oplogMapper.getOplog(deptCd, measureYmd);

            if (opLogRslt.getEnvOpLogStCd().equals(ConstVal.SAF_PROCESS_STEP_CREATE)) {
                stepCd = ConstVal.SAF_PROCESS_STEP_COMPLTE;
            }
        }
        resultNo += this.oplogMapper.updateAppr(measureYmd, deptCd, stepCd, apprRqstNo);

        return resultNo;
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
    public List<OpLogAdminRslt> getOplogAdmins(String plantCd, String startYmd, String endYmd, String stepCd) throws Exception {
        return this.oplogMapper.getOplogAdmins(plantCd, startYmd, endYmd, stepCd);
    }

    /**
     * 운영일지(관리자) 상세 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return OpLogAdminRslt
     * @throws Exception
     */
    public OpLogAdminRslt getOplogAdminAll(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception {
        OpLogAdminRslt opLogAdminRslt = this.oplogMapper.getOplogAdmin(plantCd, measureYmd);
        if (opLogAdminRslt == null) {
            opLogAdminRslt = new OpLogAdminRslt();
            opLogAdminRslt.setPlantCd(plantCd);
            opLogAdminRslt.setMeasureYmd(measureYmd);
        }

        opLogAdminRslt.setDischChkResult(oplogMapper.getOutletDischChkResultAdmin(plantCd, measureYmd));
        opLogAdminRslt.setPreventChkResult(oplogMapper.getOutletPreventChkResultAdmin(plantCd, measureYmd));
        opLogAdminRslt.setPreventChemResults(this.oplogMapper.getPreventChemResultAdmin(plantCd, measureYmd));
        opLogAdminRslt.setFuelCheckResult(this.oplogMapper.getDischFuelResultAdmin(plantCd, measureYmd));
        opLogAdminRslt.setIngrCheckResult(this.oplogMapper.getIngrChkResultAdmin(measureYmd, plantCd));

        return opLogAdminRslt;
    }

    /**
     * 운영일지(관리자) 상세 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return OpLogAdminRslt
     * @throws Exception
     */
    public OpLogAdminRslt getOplogAdmin(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception {
        return this.oplogMapper.getOplogAdmin(plantCd, measureYmd);
    }

    /**
     * 운영일지(관리자) 작성부서 정보 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return List<HashMap<String, Object>>
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOplogAdminDepts(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception {
        return this.oplogMapper.getOplogAdminDepts(plantCd, measureYmd);
    }

    /**
     * 운영일지(관리자) 생성
     *
     * @param OpLogAdminRslt
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateOplogResultAdmin(OpLogAdminRslt opLogAdminRslt) throws Exception {
        int result = this.oplogMapper.updateOplogResultAdmin(opLogAdminRslt);
        if (opLogAdminRslt.getDischChkResult() != null && opLogAdminRslt.getDischChkResult().size() > 0) {
            for (OutletDischChkResult item : opLogAdminRslt.getDischChkResult()) {
                item.setMeasureYmd(opLogAdminRslt.getMeasureYmd());
                this.oplogMapper.updateOutletDischChkResult(item);
            }

        }
        if (opLogAdminRslt.getPreventChkResult() != null && opLogAdminRslt.getPreventChkResult().size() > 0) {
            for (OutletPreventChkResult item : opLogAdminRslt.getPreventChkResult()) {
                item.setMeasureYmd(opLogAdminRslt.getMeasureYmd());
                this.oplogMapper.updateOutletPreventChkResult(item);
            }
        }
        if (opLogAdminRslt.getPreventChemResults() != null && opLogAdminRslt.getPreventChemResults().size() > 0) {
            for (PreventChemResult item : opLogAdminRslt.getPreventChemResults()) {
                item.setMeasureYmd(opLogAdminRslt.getMeasureYmd());
                this.oplogMapper.updatePreventChemResult(item);
            }
        }
        if (opLogAdminRslt.getFuelCheckResult() != null && opLogAdminRslt.getFuelCheckResult().size() > 0) {
            for (FuelCheckResult item : opLogAdminRslt.getFuelCheckResult()) {
                item.setMeasureYmd(opLogAdminRslt.getMeasureYmd());
                this.oplogMapper.updateDischFuelResult(item);
            }
        }
        if (opLogAdminRslt.getIngrCheckResult() != null && opLogAdminRslt.getIngrCheckResult().size() > 0) {
            for (IngrCheckResult item : opLogAdminRslt.getIngrCheckResult()) {
                item.setMeasureYmd(opLogAdminRslt.getMeasureYmd());
                this.oplogMapper.updateIngrChkResult(item);
            }
        }

        return result;
    }

    /**
     * 운영일지(관리자) 결재
     *
     * @param measureYmd
     * @param plantCd
     * @param apprStepCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int updateAdminAppr(String measureYmd, String plantCd, String apprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String stepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(apprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(apprStepCd)) {
            // 결재완료
            OpLogAdminRslt OpLogAdminRslt = this.oplogMapper.getOplogAdmin(plantCd, measureYmd);

            if (OpLogAdminRslt.getStepCd().equals(ConstVal.SAF_PROCESS_STEP_CREATE)) {
                stepCd = ConstVal.SAF_PROCESS_STEP_COMPLTE;
            }
        }
        resultNo += this.oplogMapper.updateAdminAppr(measureYmd, plantCd, stepCd, apprRqstNo);

        return resultNo;
    }

}
