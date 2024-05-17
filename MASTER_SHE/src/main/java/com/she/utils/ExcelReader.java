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

package com.she.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 엑셀파일 리더 -> 엑셀을 이쁘게 만들려면 아래 내용을 복사하여 각 업무별로 작성하세요.
 *
 */
public class ExcelReader {

    /**
     * 엑셀 파일 읽기
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);

    public List<String[][]> read(String filePath) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        try {
            int startIndex = filePath.lastIndexOf('.');
            String ext = filePath.substring(startIndex + 1, filePath.length());
            if (ext.equals("xls")) {
                list = this.readOfXls(filePath);
            } else if (ext.equals("xlsx")) {
                list = this.readOfXlsx(filePath);
            }
        } catch (NullPointerException ne) {
            logger.error(ne.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    /**
     * 엑셀 파일 읽기
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> read(File file) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        try {
            int startIndex = file.getPath().lastIndexOf('.');
            String ext = file.getPath().substring(startIndex + 1, file.getPath().length());
            if (ext.equals("xls")) {
                list = this.readOfXls(file);
            } else if (ext.equals("xlsx")) {
                list = this.readOfXlsx(file);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    /**
     * 엑셀 파일 읽기 특정 sheet
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> read(File file, int sheetIndex) {
        List<String[][]> list = new ArrayList<String[][]>();

        try {
            int startIndex = file.getPath().lastIndexOf('.');
            String ext = file.getPath().substring(startIndex + 1, file.getPath().length());
            if (ext.equals("xls")) {
                list = this.readOfXls(file, sheetIndex);
            } else if (ext.equals("xlsx")) {
                list = this.readOfXlsx(file, sheetIndex);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return list;
    }

    /**
     * 엑셀 97-2003 통합 파일 읽기(.xls) 특정 시트
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> readOfXls(File file, int sheetIndex) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        HSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new HSSFWorkbook(fis);

            HSSFSheet curSheet;
            HSSFRow curRow;
            HSSFCell curCell;

            curSheet = workbook.getSheetAt(sheetIndex);

            int maxRow = curSheet.getPhysicalNumberOfRows();
            int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
            String[][] sheetData = new String[maxRow][maxCell];

            for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                curRow = curSheet.getRow(rowIndex);
                if (curRow == null) {
                    break;
                }
                for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
                    curCell = curRow.getCell(cellIndex);
                    String value = "";
                    switch (curCell.getCellType()) {
                    case HSSFCell.CELL_TYPE_FORMULA:
                        value = curCell.getCellFormula();
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(curCell)) {
                            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                            value = f.format(curCell.getDateCellValue()) + "";
                        } else {
                            value = curCell.getNumericCellValue() + "";
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        value = curCell.getStringCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        value = curCell.getBooleanCellValue() + "";
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        value = curCell.getErrorCellValue() + "";
                        break;
                    default:
                        value = "";
                        break;
                    }

                    sheetData[rowIndex][cellIndex] = value;
                }
            }

            list.add(sheetData);
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (FileNotFoundException fe) {
                logger.error(fe.getMessage());
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return list;
    }

    /**
     * 통합 엑셀 파일 읽기(.xlsx) 특정 시트
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> readOfXlsx(File file, int sheetIndex) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet curSheet;
            XSSFRow curRow;
            XSSFCell curCell;

            curSheet = workbook.getSheetAt(sheetIndex);

            int maxRow = curSheet.getPhysicalNumberOfRows();
            int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
            int fixCells = curSheet.getRow(0).getPhysicalNumberOfCells();
            String[][] sheetData = new String[maxRow][maxCell];

            for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                curRow = curSheet.getRow(rowIndex);
                if (curRow == null) {
                    break;
                }
                for (int cellIndex = 0; cellIndex < fixCells; cellIndex++) {
                    curCell = curRow.getCell(cellIndex);
                    String value = "";
                    if (curCell != null) {
                        switch (curCell.getCellType()) {
                        case XSSFCell.CELL_TYPE_FORMULA:
                            value = curCell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(curCell)) {
                                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                value = f.format(curCell.getDateCellValue()) + "";
                            } else {
                                boolean isInteger = false;
                                int index = String.valueOf(curCell).indexOf('.');
                                if (index > -1) {
                                    String[] cellSplit = String.valueOf(curCell).split(".");
                                    if (String.valueOf(curCell).substring(index).equals(".0")) {
                                        isInteger = true;
                                        value = String.valueOf(curCell).substring(0, index);
                                    }
                                }
                                if (!isInteger) {
                                    BigDecimal number = new BigDecimal(curCell.getNumericCellValue() + "");
                                    value = number.toPlainString();
                                }
                            }
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            value = curCell.getStringCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = curCell.getBooleanCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = curCell.getErrorCellValue() + "";
                            break;
                        default:
                            value = "";
                            break;
                        }
                    }

                    sheetData[rowIndex][cellIndex] = value;
                }
            }

            list.add(sheetData);
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (FileNotFoundException fe) {
                logger.error(fe.getMessage());
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return list;
    }

    /**
     * 엑셀 97-2003 통합 파일 읽기(.xls)
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> readOfXls(String filePath) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        HSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(filePath);
            workbook = new HSSFWorkbook(fis);

            HSSFSheet curSheet;
            HSSFRow curRow;
            HSSFCell curCell;

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                curSheet = workbook.getSheetAt(sheetIndex);

                int maxRow = curSheet.getPhysicalNumberOfRows();
                int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
                String[][] sheetData = new String[maxRow][maxCell];

                for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                    curRow = curSheet.getRow(rowIndex);
                    for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
                        curCell = curRow.getCell(cellIndex);
                        String value = "";

                        switch (curCell.getCellType()) {
                        case HSSFCell.CELL_TYPE_FORMULA:
                            // value = curCell.getCellFormula();
                            System.out.println("Formula is " + curCell.getCellFormula());
                            switch (curCell.getCachedFormulaResultType()) {
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                System.out.println("Last evaluated as: " + curCell.getNumericCellValue());
                                value = String.valueOf(curCell.getNumericCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                System.out.println("Last evaluated as \"" + curCell.getRichStringCellValue() + "\"");
                                value = curCell.getRichStringCellValue().getString();
                                break;
                            default:
                                value = "";
                                break;
                            }
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(curCell)) {
                                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                value = f.format(curCell.getDateCellValue()) + "";
                            } else {
                                value = curCell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            value = curCell.getStringCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = curCell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = curCell.getErrorCellValue() + "";
                            break;
                        default:
                            value = "";
                            break;
                        }

                        sheetData[rowIndex][cellIndex] = value;
                    }
                }

                list.add(sheetData);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (FileNotFoundException fe) {
                logger.error(fe.getMessage());
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return list;
    }

    /**
     * 엑셀 97-2003 통합 파일 읽기(.xls)
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> readOfXls(File file) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        HSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new HSSFWorkbook(fis);

            HSSFSheet curSheet;
            HSSFRow curRow;
            HSSFCell curCell;
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                curSheet = workbook.getSheetAt(sheetIndex);

                int maxRow = curSheet.getPhysicalNumberOfRows();
                int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
                String[][] sheetData = new String[maxRow][maxCell];

                for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                    curRow = curSheet.getRow(rowIndex);
                    for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
                        curCell = curRow.getCell(cellIndex);
                        String value = "";
                        switch (curCell.getCellType()) {
                        case HSSFCell.CELL_TYPE_FORMULA:
                            value = curCell.getCellFormula();
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(curCell)) {
                                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                value = f.format(curCell.getDateCellValue()) + "";
                            } else {
                                value = curCell.getNumericCellValue() + "";
                            }
                            break;
                        case HSSFCell.CELL_TYPE_STRING:
                            value = curCell.getStringCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            value = curCell.getBooleanCellValue() + "";
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            value = curCell.getErrorCellValue() + "";
                            break;
                        default:
                            value = "";
                            break;
                        }

                        sheetData[rowIndex][cellIndex] = value;
                    }
                }

                list.add(sheetData);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (FileNotFoundException fe) {
                logger.error(fe.getMessage());
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return list;
    }

    /**
     * 통합 엑셀 파일 읽기(.xlsx)
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> readOfXlsx(String filePath) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet curSheet;
            XSSFRow curRow;
            XSSFCell curCell;
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                curSheet = workbook.getSheetAt(sheetIndex);

                int maxRow = curSheet.getPhysicalNumberOfRows();
                int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
                String[][] sheetData = new String[maxRow][maxCell];

                for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                    curRow = curSheet.getRow(rowIndex);
                    for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
                        curCell = curRow.getCell(cellIndex);
                        String value = "";
                        switch (curCell.getCellType()) {
                        case XSSFCell.CELL_TYPE_FORMULA:
                            value = curCell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(curCell)) {
                                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                value = f.format(curCell.getDateCellValue()) + "";
                            } else {
                                value = curCell.getNumericCellValue() + "";
                            }
                            break;
                        case XSSFCell.CELL_TYPE_STRING:
                            value = curCell.getStringCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = curCell.getBooleanCellValue() + "";
                            break;
                        case XSSFCell.CELL_TYPE_ERROR:
                            value = curCell.getErrorCellValue() + "";
                            break;
                        default:
                            value = "";
                            break;
                        }

                        sheetData[rowIndex][cellIndex] = value;
                    }
                }

                list.add(sheetData);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (FileNotFoundException fe) {
                logger.error(fe.getMessage());
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return list;
    }

    /**
     * 통합 엑셀 파일 읽기(.xlsx)
     *
     * @param filePath
     *            파일경로
     * @return 엑셀파일 시트목록
     */
    public List<String[][]> readOfXlsx(File file) throws Exception {
        List<String[][]> list = new ArrayList<String[][]>();

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet curSheet;
            XSSFRow curRow;
            XSSFCell curCell;
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                curSheet = workbook.getSheetAt(sheetIndex);

                int maxRow = curSheet.getPhysicalNumberOfRows();
                int maxCell = (maxRow > 0) ? curSheet.getRow(0).getPhysicalNumberOfCells() : 0;
                int fixCells = curSheet.getRow(0).getPhysicalNumberOfCells();
                String[][] sheetData = new String[maxRow][maxCell];

                for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
                    curRow = curSheet.getRow(rowIndex);
                    for (int cellIndex = 0; cellIndex < fixCells; cellIndex++) {
                        curCell = curRow.getCell(cellIndex);
                        String value = "";
                        if (curCell != null) {
                            switch (curCell.getCellType()) {
                            case XSSFCell.CELL_TYPE_FORMULA:
                                value = curCell.getCellFormula();
                                break;
                            case XSSFCell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(curCell)) {
                                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                    value = f.format(curCell.getDateCellValue()) + "";
                                } else {
                                    BigDecimal number = new BigDecimal(curCell.getNumericCellValue() + "");
                                    value = number.toPlainString();
                                }
                                break;
                            case XSSFCell.CELL_TYPE_STRING:
                                value = curCell.getStringCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_BOOLEAN:
                                value = curCell.getBooleanCellValue() + "";
                                break;
                            case XSSFCell.CELL_TYPE_ERROR:
                                value = curCell.getErrorCellValue() + "";
                                break;
                            default:
                                value = "";
                                break;
                            }
                        }

                        sheetData[rowIndex][cellIndex] = value;
                    }
                }

                list.add(sheetData);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (FileNotFoundException fe) {
                logger.error(fe.getMessage());
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return list;
    }

    /**
     * 엑셀 파일에 항목 추가
     *
     * @param file
     *            엑셀파일
     * @param sheetIndex
     *            시트인덱스
     * @param appendRows
     *            추가행
     * @return 결과
     */
    public boolean appendExcelRows(File file, int sheetIndex, List<List<String>> appendRows) throws Exception {
        boolean success = false;
        try {
            int startIndex = file.getPath().lastIndexOf('.');
            String ext = file.getPath().substring(startIndex + 1, file.getPath().length());
            if (ext.equals("xls")) {
                success = this.appendExcelRowsOfXls(file, sheetIndex, appendRows);
            } else if (ext.equals("xlsx")) {
                success = this.appendExcelRowsOfXlsx(file, sheetIndex, appendRows);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return success;
    }

    /**
     * 엑셀 파일에 항목 추가(.xls)
     *
     * @param file
     *            엑셀파일
     * @param sheetIndex
     *            시트인덱스
     * @param appendRows
     *            추가행
     * @return 결과
     */
    public boolean appendExcelRowsOfXls(File file, int sheetIndex, List<List<String>> appendRows) throws Exception {
        boolean success = false;
        FileInputStream fis = null;
        HSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new HSSFWorkbook(fis);

            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            int maxRow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < appendRows.size(); i++) {
                HSSFRow row = sheet.createRow(i + maxRow);
                List<String> appendRow = appendRows.get(i);
                for (int j = 0; j < appendRow.size(); j++) {
                    CellStyle style = workbook.createCellStyle();
                    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(appendRow.get(j));
                    cell.setCellStyle(style);
                }
            }

            success = true;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return success;
    }

    /**
     * 엑셀 파일에 항목 추가(.xlsx)
     *
     * @param file
     *            엑셀파일
     * @param sheetIndex
     *            시트인덱스
     * @param appendRows
     *            추가행
     * @return 결과
     */
    public boolean appendExcelRowsOfXlsx(File file, int sheetIndex, List<List<String>> appendRows) throws Exception {
        boolean success = false;
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        FileOutputStream outputStream = null;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            int maxRow = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < appendRows.size(); i++) {
                XSSFRow row = sheet.createRow(i + maxRow);
                List<String> appendRow = appendRows.get(i);
                for (int j = 0; j < appendRow.size(); j++) {
                    CellStyle style = workbook.createCellStyle();
                    style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
                    style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
                    style.setBorderRight(XSSFCellStyle.BORDER_THIN);
                    style.setBorderTop(XSSFCellStyle.BORDER_THIN);

                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(appendRow.get(j));
                    cell.setCellStyle(style);
                }
            }

            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);

            success = true;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ie) {
                logger.error(ie.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        return success;
    }

    /**
     * 엑셀헤더양식 확인
     *
     * @param sheetHeaderNames
     *            시트헤더배열
     * @param headerNames
     *            헤더명
     * @return 결과
     * @throws Exception
     */
    public boolean excelHeaderCheck(String[] sheetHeaderNames, String[] headerNames) throws Exception {
        boolean success = true;
        if (sheetHeaderNames.length != headerNames.length) {
            return false;
        }
        for (int i = 0; i < headerNames.length; i++) {
            if (!headerNames[i].equals(sheetHeaderNames[i])) {
                success = false;
                break;
            }
        }

        return success;
    }
}
