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
package com.she.psm.processFacility.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.Map;

import com.she.config.GlobalSettings;
import com.she.psm.model.ChemprodPrint;
import com.she.utils.FileUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.model.Chemprod;
import com.she.psm.processFacility.mapper.FacilityChemprodMapper;
import com.she.safety.model.FacilityChemprod;

/**
 * 설비별 취급물질
 */
@Service
public class FacilityChemprodService {
	@Autowired
	private FacilityChemprodMapper facilityChemprodMapper;

	@Autowired
	private GlobalSettings globalSettings;

	/**
	 * 유해위험요인 조회
	 *
	 * @param plantCd      사업장
	 * @param chemProdNmKr 취급물질명
	 * @return 설비별 취급물질 목록
	 * @throws Exception
	 */
	public List<Chemprod> getRiskChemprodchems(String plantCd, String chemProdNmKr, String casNo, int[] saveChemprodNos)
			throws Exception {
		return this.facilityChemprodMapper.getRiskChemprodchems(plantCd, chemProdNmKr, casNo, saveChemprodNos, 0);
	}

	/**
	 * 설비별 취급물질 조회
	 *
	 * @param plantCd       사업장
	 * @param chemProdNmKr  취급물질명
	 * @param safFacilityCd 설비 코드
	 * @param equipmentNo   설비별 장치번호
	 * @return 설비별 취급물질 목록
	 * @throws Exception
	 */
	public List<FacilityChemprod> getFacilityChemprods(String plantCd, String chemProdNmKr, String safFacilityCd,
			int equipmentNo) throws Exception {
		return facilityChemprodMapper.getFacilityChemprods(plantCd, chemProdNmKr, safFacilityCd, equipmentNo);
	}

	/**
	 * 유해위험물질 출력
	 *
	 * @param plantCd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public File getRiskHazardPrint(String plantCd, String plantNm) throws Exception {
		List<ChemprodPrint> chemprodPrintList = facilityChemprodMapper.geChemProdNms(plantCd);
		// 화학자재에 대한 화학물질 리스트
		for (ChemprodPrint chemprodPrint : chemprodPrintList) {
			List<Chemprod> chemprodList = facilityChemprodMapper.getRiskChemprodchems(plantCd, null, null, null,
					chemprodPrint.getChemProdNo());
			if (chemprodList != null && chemprodList.size() > 0) {
				chemprodPrint.setChemProdList(chemprodList);
			}
			JRDataSource chemDatasource = new JRBeanCollectionDataSource(chemprodList);
			chemprodPrint.setChemList(chemDatasource);
		}

		if (chemprodPrintList == null || chemprodPrintList.size() == 0) {
			ChemprodPrint blankChemprod = new ChemprodPrint();
			blankChemprod.setChemProdNmEn("");
			if (chemprodPrintList == null && chemprodPrintList.size() == 0) {
				chemprodPrintList.add(blankChemprod);

			}

		}
		JRDataSource chemprodList = new JRBeanCollectionDataSource(chemprodPrintList);

		// 파일경로 setting
		String reportPath = globalSettings.getReportRiskHazard();
		String subReportDir = globalSettings.getReportRiskHazardDir();

		String fileName = FilenameUtils.getBaseName(reportPath);
		String fileExt = "." + FilenameUtils.getExtension(reportPath);

		File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
		String reportFileName = (file != null ? file.getAbsolutePath() : null);
		String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".pdf";
		String logoPath = globalSettings.getLogoImageFilePath();

		// Compile the Jasper report from .jrxml to .japser
		JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

		List<ChemprodPrint> data = new ArrayList<ChemprodPrint>();
		if (data != null || data.size() > 0) {
			data.add(chemprodPrintList.get(0));
		}
		JRDataSource datasource = new JRBeanCollectionDataSource(data);

		// 파라미터값
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("subreport_dir", subReportDir);
		parameters.put("plantNm", plantNm);
		parameters.put("chemprodDtoList", chemprodList);
		parameters.put("logo_dir", logoPath);

		// Fill the report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		// Export the report to a PDF
		JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

		return new File(outputFileName);
	}

	/**
	 * 유해위험물질 Excel 출력
	 *
	 * @param plantCd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public File getRiskHazardExcelPrint(String plantCd, String plantNm) throws Exception {
		List<ChemprodPrint> chemprodPrintList = facilityChemprodMapper.geChemProdNms(plantCd);
		// 화학자재에 대한 화학물질 리스트
		for (ChemprodPrint chemprodPrint : chemprodPrintList) {
			List<Chemprod> chemprodList = facilityChemprodMapper.getRiskChemprodchems(plantCd, null, null, null,
					chemprodPrint.getChemProdNo());
			if (chemprodList != null && chemprodList.size() > 0) {
				chemprodPrint.setChemProdList(chemprodList);
			}
			JRDataSource chemDatasource = new JRBeanCollectionDataSource(chemprodList);
			chemprodPrint.setChemList(chemDatasource);
		}

		if (chemprodPrintList == null || chemprodPrintList.size() == 0) {
			ChemprodPrint blankChemprod = new ChemprodPrint();
			blankChemprod.setChemProdNmEn("");
			if (chemprodPrintList == null && chemprodPrintList.size() == 0) {
				chemprodPrintList.add(blankChemprod);
			}

		}
		JRDataSource chemprodList = new JRBeanCollectionDataSource(chemprodPrintList);

		// 파일경로 setting
		String reportPath = globalSettings.getReportRiskHazard();
		String subReportDir = globalSettings.getReportRiskHazardDir();

		String fileName = FilenameUtils.getBaseName(reportPath);
		String fileExt = "." + FilenameUtils.getExtension(reportPath);

		File file = FileUtil.getFileFixedBase64(reportPath, fileName, fileExt);
		String reportFileName = (file != null ? file.getAbsolutePath() : null);
		String outputFileName = FileUtil.getStoreFilePath() + File.separator + fileName + "_"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
		String logoPath = globalSettings.getLogoImageFilePath();

		// Compile the Jasper report from .jrxml to .japser
		JasperReport jasperReport = JasperCompileManager.compileReport(reportFileName);

		List<ChemprodPrint> data = new ArrayList<ChemprodPrint>();
		if (data != null || data.size() > 0) {
			data.add(chemprodPrintList.get(0));
		}
		JRDataSource datasource = new JRBeanCollectionDataSource(data);

		// 파라미터값
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("subreport_dir", subReportDir);
		parameters.put("plantNm", plantNm);
		parameters.put("chemprodDtoList", chemprodList);
		parameters.put("logo_dir", logoPath);
		// Fill the report
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		// Export the report to a PDF
		JasperExportManager.exportReportToPdfFile(jasperPrint, outputFileName);

		// Export the report to a excel
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		File outputFile = new File(outputFileName);
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
		SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
		configuration.setOnePagePerSheet(true);

		exporter.setConfiguration(configuration);
		exporter.exportReport();

		return new File(outputFileName);
	}
}
