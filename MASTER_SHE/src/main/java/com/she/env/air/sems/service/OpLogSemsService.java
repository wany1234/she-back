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
package com.she.env.air.sems.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import com.she.common.model.DefaultParam;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.she.env.air.sems.mapper.OpLogSemsMapper;

import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.FileUtil;
import com.she.utils.Methods;

/**
 * SEMS 기능정의
 *
 */
@Service("OpLogSemsService")
public class OpLogSemsService {
    private static Logger logger = LoggerFactory.getLogger(OpLogSemsService.class);

    @Autowired
    private OpLogSemsMapper opLogSemsMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * SEMS 조건별 조회
     *
     * @param fromYmd,
     *            toYmd, plantCd, eairSemsRptTypeCd, deptCd 시작일자, 종료일자, 사업장코드,
     *            검색 조건, 부서코드
     * @return SEMS 목록
     * @throws Exception
     */
    public List<LinkedHashMap<String, Object>> getSems(String fromYmd, String toYmd, String plantCd, String eairSemsRptTypeCd, String deptCd, String mainDischFacNm, DefaultParam defaultParam) throws Exception {

        List<String> ymCols = new ArrayList<String>();
        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();
        String ymColStr = "";

        fromDate.setTime(Methods.convertStringToDate(fromYmd));
        toDate.setTime(Methods.convertStringToDate(toYmd));
        for (; fromDate.compareTo(toDate) < 1; fromDate.add(Calendar.MONTH, 1)) {
            String year = fromDate.get(Calendar.YEAR) + "";
            String month = Methods.leftPad(String.valueOf(fromDate.get(Calendar.MONTH) + 1), 2, '0');
            ymCols.add(year + month);
        }

        ymColStr = String.join(", ", ymCols);
        // toYmd 해당월의 마지막일로 설정
        toYmd = toYmd.substring(0, 8) + Methods.leftPad(String.valueOf(toDate.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, '0');

        return this.opLogSemsMapper.getSems(fromYmd, toYmd, ymCols, plantCd, eairSemsRptTypeCd, deptCd, ymColStr, mainDischFacNm, defaultParam);

    }

    /**
     * SEMS ESRT1(가동시간) 엑셀 다운로드
     *
     * @param fromYmd,
     *            toYmd, plantCd, eairSemsRptTypeCd, deptCd 시작일자, 종료일자, 사업장코드,
     *            검색 조건, 부서코드
     * @return SEMS 목록
     * @throws Exception
     */
    public byte[] getSemsExcelDownload(String fromYmd, String toYmd, String plantCd, String eairSemsRptTypeCd, String deptCd, String mainDischFacNm, DefaultParam defaultParam) throws Exception {
        File resultFile = null;

        List<String> ymCols = new ArrayList<String>();
        Calendar fromDate = Calendar.getInstance();
        Calendar toDate = Calendar.getInstance();
        String ymColStr = "";

        fromDate.setTime(Methods.convertStringToDate(fromYmd));
        toDate.setTime(Methods.convertStringToDate(toYmd));
        for (; fromDate.compareTo(toDate) < 1; fromDate.add(Calendar.MONTH, 1)) {
            String year = fromDate.get(Calendar.YEAR) + "";
            String month = Methods.leftPad(String.valueOf(fromDate.get(Calendar.MONTH) + 1), 2, '0');
            ymCols.add(year + month);
        }

        ymColStr = String.join(", ", ymCols);
        // toYmd 해당월의 마지막일로 설정
        toYmd = toYmd.substring(0, 8) + Methods.leftPad(String.valueOf(toDate.getActualMaximum(Calendar.DAY_OF_MONTH)), 2, '0');

        List<LinkedHashMap<String, Object>> getSemsData = this.opLogSemsMapper.getSems(fromYmd, toYmd, ymCols, plantCd, eairSemsRptTypeCd, deptCd, ymColStr, mainDischFacNm, defaultParam);

        if ("ESRT1".equals(eairSemsRptTypeCd)) {
            // 가동시간
            resultFile = this.getExcelFile("가동시간-엑셀샘플.xls", eairSemsRptTypeCd, getSemsData, new String[] { "measure_ymd", "eair_outlet_permit_no", "eair_outlet_nm", "main_disch_fac_nm", "run_tm", "run_min", "remark" });
        } else if ("ESRT2".equals(eairSemsRptTypeCd)) {
            // 시설운전사항
            resultFile = this.getExcelFile("시설운전사항.xls", eairSemsRptTypeCd, getSemsData, new String[] { "measure_ymd", "eair_outlet_nm", "eair_outlet_permit_no", "main_disch_fac_nm", "eair_prevent_fac_num", "eair_prevent_fac_inh_num", "eair_prevent_fac_nm", "elec_yn", "eair_prevent_fac_elec_meter_no", "pwr_meter_magn", "pwr_meter_cnt",
                    "eair_chem_nm", "consum_amt1", "unit_cd1", "eair_chem_nm2", "consum_amt2", "unit_cd2", "eair_chem_nm3", "consum_amt3", "unit_cd3" });
        } else if ("ESRT3".equals(eairSemsRptTypeCd)) {
            // 보수사항
            resultFile = this.getExcelFile("보수사항-엑셀셈플.xls", eairSemsRptTypeCd, getSemsData, new String[] { "reg_dt", "eair_outlet_nm", "eair_outlet_permit_no", "main_disch_fac_nm", "eair_prevent_fac_num", "eair_prevent_fac_inh_num", "eair_prevent_fac_nm", "start_ymd", "end_ymd", "worker", "remark" });
        } else if ("ESRT4".equals(eairSemsRptTypeCd)) {
            // 자가측정사항
            resultFile = this.getExcelFile("자가측정-엑셀샘플.xls", eairSemsRptTypeCd, getSemsData, new String[] { "eair_outlet_nm", "eair_outlet_permit_no", "main_disch_fac_nm", "weather", "temp", "hum", "air_press", "wind_dir", "wind_speed", "measure_ymd", "method_cd", "gas_speed", "gas_temp", "wtr_per", "real_o2_val", "stnd_o2_val", "flow_day",
                    "test_item_cd", "num_result", "legal_limit", "legal_limit_chk", "fuel_nm_result", "ingr_nm_result", "env_engr_nm", "env_engr_opn", "eair_inst_nm", "eair_test_mtd_nm" });
        } else if ("ESRT5".equals(eairSemsRptTypeCd)) {
            String year = "";
            if (fromYmd != null) {
                year = fromYmd.substring(0, 4);
            }
            // 연료사용량
            resultFile = this.getExcelFile("연료사용량-엑셀셈플.xls", eairSemsRptTypeCd, getSemsData, new String[] { "eair_outlet_num", "main_disch_fac_num", "eair_prevent_fac_num", "eair_prevent_fac_nm", "eair_disch_fac_num", "eair_disch_fac_nm", "eair_fuel_cd", "fuel_etc", "env_unit_cd", "cal_val", "cal_val_unit_cd", "sulfur_content", "ym" + year + "01",
                    "ym" + year + "02", "ym" + year + "03", "ym" + year + "04", "ym" + year + "05", "ym" + year + "06", "ym" + year + "07", "ym" + year + "08", "ym" + year + "09", "ym" + year + "10", "ym" + year + "11", "ym" + year + "12" });
        } else if ("ESRT6".equals(eairSemsRptTypeCd)) {
            String year = "";
            if (fromYmd != null) {
                year = fromYmd.substring(0, 4);
            }
            // 원료사용량
            resultFile = this.getExcelFile("원료사용량-엑셀셈플.xls", eairSemsRptTypeCd, getSemsData,
                    new String[] { "eair_ingr_nm", "env_unit_cd", "ym" + year + "01", "ym" + year + "02", "ym" + year + "03", "ym" + year + "04", "ym" + year + "05", "ym" + year + "06", "ym" + year + "07", "ym" + year + "08", "ym" + year + "09", "ym" + year + "10", "ym" + year + "11", "ym" + year + "12" });
        }
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(resultFile));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            inputStream.close();
        }
    }

    public File getExcelFile(String excelFileName, String eairSemsRptTypeCd, List<LinkedHashMap<String, Object>> getSemsData, String[] cols) throws Exception {
        File resultFile = null;
        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        FileInputStream file = null;
        HSSFWorkbook workbook = null;
        FileOutputStream output = null;
        try {
            File filePath = new ClassPathResource("templates" + path.getCodeNm() + excelFileName).getFile();
            file = new FileInputStream(filePath);
            // file = new FileInputStream("C:\\SHE_FILE\\FORM\\" +
            // excelFileName);

            workbook = new HSSFWorkbook(file);
            // 탐색에 사용할 Sheet, Row, Cell 객체
            HSSFSheet curSheet;
            HSSFRow curRow;

            // 현재 Sheet 반환
            curSheet = workbook.getSheetAt(0);
            int rowIndex = 0;
            CellStyle style = workbook.createCellStyle();
            // 폰트 설정
            Font font = workbook.createFont();
            font.setFontName("굴림체"); // 글씨체
            font.setFontHeight((short) (9 * 20)); // 사이즈
            style.setFont(font);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            for (LinkedHashMap<String, Object> data : getSemsData) {
                curRow = curSheet.createRow(++rowIndex);// 행 생성
                int colIndex = 0;
                for (String col : cols) {
                    HSSFCell cell = curRow.createCell(colIndex);// 해당 행의 1열
                    cell.setCellValue(String.valueOf(data.get(col)));// 값넣기
                    cell.setCellStyle(style);
                    colIndex++;
                }

            }
            String createpath = FileUtil.getStoreFilePath() + File.separator + eairSemsRptTypeCd; // 폴더
            // 경로
            File folder = new File(createpath);
            folder.setExecutable(false, true);
            folder.setReadable(true);
            folder.setWritable(false, true);
            if (!folder.exists()) {
                try {
                    folder.mkdir(); // 폴더 생성합니다.
                } catch (NullPointerException e) {
                    logger.error(e.getMessage());
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
            String uuid = UUID.randomUUID().toString();
            output = new FileOutputStream(createpath + File.separator + uuid + ".xls");
            workbook.write(output);// 파일 생성

            resultFile = new File(createpath + File.separator + uuid + ".xls");
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                // 사용한 자원은 finally에서 해제
                if (output != null) {
                    output.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
        return resultFile;
    }

}
