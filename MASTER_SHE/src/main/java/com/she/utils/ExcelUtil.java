package com.she.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;

import com.she.common.model.AttachFile;
import com.she.utils.model.TableHeader;

public class ExcelUtil {

    /**
     * [List<Model> convert to List<Map<String, Object>>]
     */
    public static <T> List<Map<String, Object>> convertListToMap(Collection<T> target) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        if (target != null && target.size() > 0) {
            boolean isMap = false;
            for (T element : target) {
                if ("java.util.HashMap".equals(element.getClass().getName()) || "java.util.Map".equals(element.getClass().getName())) {
                    isMap = true;
                    break;
                }
                Map<String, Object> resultMap = new HashMap<String, Object>();
                Field[] fieldList = element.getClass().getDeclaredFields();
                if (fieldList != null && fieldList.length > 0) {
                    for (int i = 0; i < fieldList.length; i++) {
                        String curInsName = fieldList[i].getName();
                        Field field = element.getClass().getDeclaredField(curInsName);
                        field.setAccessible(true);
                        Object targetValue = field.get(element);
                        resultMap.put(curInsName, targetValue);
                    }
                    resultList.add(resultMap);
                }
            }
            if (isMap) {
                return (List<Map<String, Object>>) target;
            }
        }
        return resultList;
    }

    public static <T> File createExcelFile(List<TableHeader> tableHeaders, Collection<T> target, String title, String path) throws Exception {
        File resultFile = null;
        List<Map<String, Object>> excelData = convertListToMap(target);

        HSSFWorkbook writebook = new HSSFWorkbook();// 새 엑셀파일만들기
        createSheet(writebook, title + " 시트", tableHeaders, excelData, title);

        path += File.separator + "excelDown";

        // 경로
        File folder = new File(path);
        folder.setExecutable(false, true);
        folder.setReadable(true);
        folder.setWritable(false, true);
        if (!folder.exists()) {
            folder.mkdir(); // 폴더 생성합니다.
        }
        String uuid = UUID.randomUUID().toString();
        FileOutputStream output = null;
        try {
            output = new FileOutputStream(path + "\\" + uuid + ".xls");
            writebook.write(output);// 파일 생성
            output.close();
            resultFile = new File(path + "\\" + uuid + ".xls");

            return resultFile;
        } catch (Exception e) {
            throw e;
        } finally {
            output.close();
        }
    }

    public static void createSheet(HSSFWorkbook writebook, String sheetName, List<TableHeader> tableHeaders, List<Map<String, Object>> list, String title) throws Exception {
        HSSFSheet sheet = writebook.createSheet(sheetName);// 새 시트 만들기//눈금선 없애기
        sheet.setDisplayGridlines(false);

        HSSFRow row;
        int rowIndex = 0;

        CellStyle titleStyle = getCellStyle(writebook, 1);
        CellStyle subTitleStyle = getCellStyle(writebook, 2);
        CellStyle headerStyle = getCellStyle(writebook, 10);
        // CellStyle nomalUpStyle = getCellStyle(writebook, 6);
        // CellStyle nomalDownStyle = getCellStyle(writebook, 7);
        // CellStyle nomalDotStyle = getCellStyle(writebook, 8);
        CellStyle nomalAllStyle = getCellStyle(writebook, 4);

        /**
         * 제목 setting
         **/
        row = sheet.createRow(rowIndex);// 행 생성
        HSSFCell cell = row.createCell(0);// 해당 행의 1열
        cell.setCellValue(title);// 값넣기
        cell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19)); // 제목
        ++rowIndex; // 개행

        /**
         * 소제목 setting
         **/
        row = sheet.createRow(++rowIndex);// 행 생성
        cell = row.createCell(0);// 해당 행의 1열
        cell.setCellValue(title + " 목록");// 값넣기
        cell.setCellStyle(subTitleStyle);

        boolean haveChild = false;
        for (int i = 0; i < tableHeaders.size(); i++) {
            if (tableHeaders.get(i).getChild() != null && tableHeaders.get(i).getChild().size() > 0) {
                haveChild = true;
                break;
            }
        }
        String[] headerText = new String[tableHeaders.size()];

        if (!haveChild) {
            for (int i = 0; i < tableHeaders.size(); i++) {
                headerText[i] = tableHeaders.get(i).getText();
            }
            /**
             * 헤더 setting
             **/
            createRow(sheet, rowIndex, headerStyle, headerText, 0, 0);
            ++rowIndex;
        } else {
            List<List<Map<String, Object>>> allHeader = createHeaderList(tableHeaders);

            if (allHeader != null && allHeader.size() > 0) {
                for (int i = 0; i < allHeader.size(); i++) {
                    List<Map<String, Object>> drawHaeder = allHeader.get(i);

                    int colIndex = 0;
                    List<String> headerTextList = new ArrayList<String>();
                    for (int j = 0; j < drawHaeder.size(); j++) {

                        headerTextList.add(String.valueOf(drawHaeder.get(j).get("data")));
                        String colspanString = String.valueOf(drawHaeder.get(j).get("colspan"));
                        String rowspanString = String.valueOf(drawHaeder.get(j).get("rowspan"));
                        int colspan = Integer.parseInt(colspanString);
                        int rowspan = Integer.parseInt(rowspanString);
                        if (colspan > 0 || rowspan > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(rowIndex + 1, rowIndex + 1 + (rowspan - 1 > 0 ? (rowspan - 1) : 0), colIndex, colIndex + (colspan - 1 > 0 ? (colspan - 1) : 0)));
                        }
                        if (colspan > 0) {
                            for (int x = 1; x < colspan; x++) {
                                headerTextList.add(String.valueOf(drawHaeder.get(j).get("data")));
                            }
                            colIndex += colspan;
                        } else {
                            colIndex++;
                        }
                    }

                    headerText = new String[headerTextList.size()];
                    for (int j = 0; j < headerTextList.size(); j++) {
                        headerText[j] = headerTextList.get(j);
                    }
                    createRow(sheet, rowIndex, headerStyle, headerText, 0, 0);
                    ++rowIndex;
                }

                /**
                 * tableHeaders setting
                 * 
                 * 맨 아래 헤더 정보가 tableHeaders가 되도로 설정
                 */
                List<Map<String, Object>> lastHaeder = allHeader.get(allHeader.size() - 1);
                tableHeaders = new ArrayList<TableHeader>();
                for (Map<String, Object> map : lastHaeder) {
                    TableHeader th = new TableHeader();
                    th.setName(String.valueOf(map.get("name")));
                    th.setText(String.valueOf(map.get("text")));
                    th.setType(String.valueOf(map.get("type")));

                    tableHeaders.add(th);
                }
            } else {
                //
            }
            System.out.println(allHeader);
        }

        /**
         * data setting
         **/
        if (list != null && list.size() > 0) {
            try {
                int maxPictureLength = 0;
                int pictureIndex = 0;
                Map<String, Integer> resultMap = getPictureInfo(tableHeaders, list);
                maxPictureLength = resultMap.get("maxPictureLength");
                pictureIndex = resultMap.get("pictureIndex");
                for (Map<String, Object> map : list) {
                    // 헤더 순서대로 값을 셋팅
                    String[] dataValue = new String[tableHeaders.size()];
                    boolean isPicture = false;
                    for (int i = 0; i < tableHeaders.size(); i++) {
                        if ("1".equals(tableHeaders.get(i).getType())) {
                            // 현재 로직은 사진이 나오는 col은 한 곳이라 가정하고 개발됨
                            // 그리는 excel col에 이미지가 들어가는 경우
                            List<AttachFile> files = (List<AttachFile>) map.get(tableHeaders.get(i).getName());
                            if (files != null && files.size() > 0) {
                                isPicture = true;
                                for (int f = 0; f < files.size(); f++) {
                                    sheet.setColumnWidth(i + f, 1600 * 3);
                                    InputStream inputStream = null;
                                    try {
                                        inputStream = new FileInputStream(files.get(f).getFilePath());
                                        byte[] bytes = IOUtils.toByteArray(inputStream);
                                        int pictureIdx = writebook.addPicture(bytes, HSSFWorkbook.PICTURE_TYPE_JPEG);

                                        HSSFCreationHelper helper = writebook.getCreationHelper();
                                        HSSFPatriarch drawing = sheet.createDrawingPatriarch();
                                        HSSFClientAnchor anchor = helper.createClientAnchor();

                                        // 이미지를 출력할 CELL 위치 선정
                                        anchor.setCol1(i + f);
                                        anchor.setRow1(rowIndex + 1);
                                        anchor.setCol2(i + f);
                                        anchor.setRow2(rowIndex + 1);
                                        anchor.setDx2(255 * 25);
                                        anchor.setDy2(255 * 25);

                                        // 이미지 그리기
                                        drawing.createPicture(anchor, pictureIdx);

                                    } catch (Exception e) {
                                        dataValue[i] = "파일을 읽을수 없습니다.";
                                    } finally {
                                        inputStream.close();
                                    }
                                }
                            } else {
                                dataValue[i] = "";
                            }
                        } else {
                            String cellData = String.valueOf(map.get(tableHeaders.get(i).getName()));
                            if ("null".equals(cellData)) {
                                cellData = "";
                            }
                            dataValue[i] = cellData;
                        }
                    }

                    int height = 0;
                    if (isPicture) {
                        height = 1600;
                    }
                    if ("1".equals(tableHeaders.get(pictureIndex).getType())) {
                        sheet.addMergedRegion(new CellRangeAddress(rowIndex + 1, rowIndex + 1, pictureIndex, pictureIndex + maxPictureLength - 1));
                    }

                    createRow(sheet, rowIndex, nomalAllStyle, dataValue, height, maxPictureLength == 0 ? 0 : maxPictureLength - 1);
                    ++rowIndex;
                }

                HSSFRow colAutoWidthRow = writebook.getSheetAt(0).getRow(3);
                for (int i = 0; i < colAutoWidthRow.getLastCellNum(); i++) {
                    TableHeader header = tableHeaders.get(i);
                    if (header == null || !"1".equals(header.getType())) {
                        sheet.autoSizeColumn(i);
                        if (i == 1 || i == 2 || i == 3 || i == 4) {
                            sheet.setColumnWidth(i, Math.min(255 * 256, (sheet.getColumnWidth(i)) + 512 * 8));
                        } else {
                            sheet.setColumnWidth(i, Math.min(255 * 256, (sheet.getColumnWidth(i)) + 512));
                        }
                    } else {
                        // 사진이 있었던 경우 헤더도 merge 작업을 해주어야 함
                        sheet.addMergedRegion(new CellRangeAddress(3, 3, pictureIndex, pictureIndex + maxPictureLength - 1));
                        // sheet.autoSizeColumn(i);
                        // sheet.setColumnWidth(i, Math.min(255 * 256, ((int)
                        // Math.round(((Math.round(10 * 96 / 2.54D) - 5.0D) / 8
                        // * 7
                        // + 5)
                        // / 7 * 256)) + 512));
                    }
                }
                if ("1".equals(tableHeaders.get(pictureIndex).getType())) {
                    CellStyle style = headerStyle;
                    for (int k = pictureIndex + 1; k < pictureIndex + maxPictureLength; k++) {
                        // sheet.getRow(3).getCell(pictureIndex).setCellStyle(headerStyle);
                        HSSFCell cellHeader = sheet.getRow(3).createCell(k);

                        if (k == 0) {
                            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                        } else if (k == maxPictureLength - 1) {
                            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        } else {
                            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                        }
                        cellHeader.setCellStyle(style);
                    }
                }

            } catch (Exception e) {
                throw e;
            }
        }
    }

    public static void createRow(HSSFSheet sheet, int rowIndex, CellStyle style, String[] values, int height, int maxPictureLength) throws Exception {
        if (values != null && values.length > 0) {
            HSSFRow row = sheet.createRow(++rowIndex);// 행 생성
            if (height > 0) {
                row.setHeight((short) height);
            }
            for (int i = 0; i < values.length + maxPictureLength; i++) {
                HSSFCell cell = row.createCell(i);// 해당 행의 1열
                if (i < values.length) {
                    cell.setCellValue(values[i]);// 값넣기
                }
                if (i == 0) {
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                } else if (i == values.length - 1) {
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                } else {
                    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                }
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 목적 : 자식 헤더를 가지고 있는 헤더를 그릴수 있는 형태로 변환하여 반환
     */
    public static List<List<Map<String, Object>>> createHeaderList(List<TableHeader> tableHeaders) {
        List<List<Map<String, Object>>> allHeader = new ArrayList<List<Map<String, Object>>>();

        /**
         * child depth check
         */
        int depth = getHeaderDepth(tableHeaders);
        for (int i = 0; i < depth; i++) {
            allHeader.add(createHeaderChildList(tableHeaders, i, depth));
        }

        /**
         * colspan setting
         * 
         * createHeaderChildList function에서 setting 된 colspan이 있음
         * 
         * 해당 colspan 값은 당장 자신의 child size 값을 표시한 것임
         * 
         * 따라서 setting 된 colspan 값은 맨 아래, 그 위까지는 맞지만
         * 
         * 그 위의 header의 colspan은 아래의 colspan값을 더하면서 진행
         */
        if (depth > 2) {
            // 맨 아래와 그 위의 헤더 정보는 colspan값이 맞음 따라서 depth가 3이상인 것들에 한해서 진행
            for (int i = depth - 3; i >= 0; i--) {
                List<Map<String, Object>> settingHaeder = allHeader.get(i);
                List<Map<String, Object>> underHaeder = allHeader.get(i + 1);

                for (int j = 0; j < settingHaeder.size(); j++) {
                    List<Map<String, Object>> childHeader = (List<Map<String, Object>>) settingHaeder.get(j).get("child");
                    if (childHeader != null && childHeader.size() > 0) {
                        int colspan = 0;
                        for (int x = 0; x < childHeader.size(); x++) {
                            String data = String.valueOf(childHeader.get(x).get("data"));
                            List<Map<String, Object>> findChild = underHaeder.stream().filter(d -> d.get("data").equals(data)).collect(Collectors.toList());

                            if (findChild != null && findChild.size() > 0) {
                                String colspanString = String.valueOf(findChild.get(0).get("colspan"));
                                colspanString = isNumeric(colspanString) ? colspanString.substring(0, colspanString.indexOf('.')) : "0";

                                colspan += Integer.parseInt(colspanString);
                            }
                        }

                        settingHaeder.get(j).put("colspan", colspan);
                    }
                }
            }
        }

        return allHeader;
    }

    public static int getHeaderDepth(List<TableHeader> tableHeaders) {
        List<Integer> tempDepths = new ArrayList<Integer>();
        for (int i = 0; i < tableHeaders.size(); i++) {
            int tempDepth = 1;
            List<TableHeader> childHeaders = tableHeaders.get(i).getChild();
            if (childHeaders != null && childHeaders.size() > 0) {
                // 자식 헤더가 있는 경우
                tempDepth += getHeaderDepth(childHeaders);
            }

            tempDepths.add(tempDepth);
        }

        int maxDept = tempDepths.get(0);
        for (int i = 1; i < tempDepths.size(); i++) {
            if (maxDept < tempDepths.get(i)) {
                maxDept = tempDepths.get(i);
            }
        }

        return maxDept;
    }

    public static List<Map<String, Object>> createHeaderChildList(List<TableHeader> tableHeaders, int depthRange, int depth) {
        if (depthRange < 0) {
            return null;
        } else {
            List<Map<String, Object>> header = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < tableHeaders.size(); i++) {
                // 자식 헤더가 있는지 체크
                List<TableHeader> childHeaders = tableHeaders.get(i).getChild();
                if (childHeaders != null && childHeaders.size() > 0) {
                    // 자식 헤더가 있는 경우
                    List<Map<String, Object>> childHeader = createHeaderChildList(childHeaders, depthRange - 1, depth - 1);

                    // depthRange가 -1이 였을 경우 null 반환 됨
                    if (childHeader != null) {
                        for (int j = 0; j < childHeader.size(); j++) {
                            Map<String, Object> map = new HashMap<String, Object>();

                            map.put("data", childHeader.get(j).get("data"));
                            map.put("name", childHeader.get(j).get("name"));
                            map.put("colspan", childHeader.get(j).get("colspan"));
                            map.put("rowspan", childHeader.get(j).get("rowspan"));
                            map.put("child", childHeader.get(j).get("child"));

                            header.add(map);
                        }
                    } else {
                        Map<String, Object> map = new HashMap<String, Object>();

                        map.put("data", tableHeaders.get(i).getText());
                        map.put("name", tableHeaders.get(i).getName());
                        map.put("colspan", childHeaders.size());
                        map.put("rowspan", 1);
                        map.put("child", childHeaders);

                        header.add(map);
                    }
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("data", tableHeaders.get(i).getText());
                    map.put("name", tableHeaders.get(i).getName());
                    map.put("colspan", 0);
                    map.put("rowspan", depth);

                    header.add(map);
                }
            }

            return header;
        }
    }

    public static Map<String, Integer> getPictureInfo(List<TableHeader> tableHeaders, List<Map<String, Object>> list) throws Exception {
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        int maxPictureLength = 0;
        int pictureIndex = 0;

        // list는 한개라도 있는 상황에서 호출된 상황

        for (int i = 0; i < tableHeaders.size(); i++) {
            if ("1".equals(tableHeaders.get(i).getType())) {
                pictureIndex = i;
            }
        }

        if (pictureIndex > 0) {
            for (Map<String, Object> map : list) {
                List<AttachFile> files = (List<AttachFile>) map.get(tableHeaders.get(pictureIndex).getName());
                if (files != null && files.size() > 0) {
                    if (maxPictureLength < files.size()) {
                        maxPictureLength = files.size();
                    }
                }
            }
        }
        resultMap.put("maxPictureLength", maxPictureLength);
        resultMap.put("pictureIndex", pictureIndex);
        return resultMap;
    }

    public static CellStyle getCellStyle(HSSFWorkbook writebook, int type) throws Exception {

        // 셀 스타일 및 폰트 설정
        CellStyle style = writebook.createCellStyle();

        if (type == 1) { // 제목 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (15 * 20)); // 사이즈
            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드
            // (굵게)
            style.setFont(font);
        } else if (type == 2) { // 소제목 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (12 * 20)); // 사이즈
            font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 볼드
            // (굵게)
            style.setFont(font);
        } else if (type == 3) { // 헤더 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 4) { // 일반 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 5) { // 소계 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 배경색
            style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 6) { // 위에선이 두꺼운 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 7) { // 아래선이 두꺼운 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
            style.setBorderBottom(HSSFCellStyle.BORDER_THICK);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 8) { // 위아래선이 점선인 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOTTED);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 9) { // 위아래선이 두꺼운 style
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_THICK);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        } else if (type == 10) { // 헤더 style2
            // 정렬
            style.setAlignment(CellStyle.ALIGN_CENTER); // 가운데 정렬
            style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 높이 가운데 정렬
            // 배경색
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            // 테두리 선 (우,좌,위,아래)
            style.setBorderRight(HSSFCellStyle.BORDER_THICK);
            style.setBorderLeft(HSSFCellStyle.BORDER_THICK);
            style.setBorderTop(HSSFCellStyle.BORDER_THICK);
            style.setBorderBottom(HSSFCellStyle.BORDER_DOUBLE);

            // 폰트 설정
            Font font = writebook.createFont();
            font.setFontName("나눔고딕"); // 글씨체
            font.setFontHeight((short) (11 * 20)); // 사이즈
            // (굵게)
            style.setFont(font);
        }
        return style;
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String createThumbnail(String fileName, int maxSize) throws Exception {
        int thumbnailWidth = maxSize;
        int thumbnailHeight = maxSize;
        File originFileName = new File(fileName);

        String ext = getFileExt(fileName);
        String newFileName = fileName.replace("." + ext, ".thumbnail." + ext);

        BufferedImage bufferOriginalImage = ImageIO.read(originFileName);

        double imgWidth = bufferOriginalImage != null ? bufferOriginalImage.getWidth() : 0;
        double imgHeight = bufferOriginalImage != null ? bufferOriginalImage.getHeight() : 0;

        if (imgWidth < imgHeight) {
            thumbnailWidth = (int) ((imgWidth / imgHeight) * maxSize);
        } else {
            thumbnailHeight = (int) ((imgHeight / imgWidth) * maxSize);
        }

        int imgType = (bufferOriginalImage.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage bufferThumbnailImage = new BufferedImage(thumbnailWidth, thumbnailHeight, imgType);
        Graphics2D graphic = bufferThumbnailImage.createGraphics();

        graphic.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphic.drawImage(bufferOriginalImage, 0, 0, thumbnailWidth, thumbnailHeight, null);

        if (ext.equalsIgnoreCase("jpg")) {
            writeJpeg(bufferThumbnailImage, newFileName, 1.0f);
        } else {
            File thumbFileName = new File(newFileName);
            ImageIO.write(bufferThumbnailImage, ext.toLowerCase(), thumbFileName);
        }

        graphic.dispose();

        return newFileName;
    }

    private static String getFileExt(String fileName) { // "abc.txt" -> "txt",
                                                        // not ".txt"
        int i = fileName.lastIndexOf('.');
        int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
        if (i > p) {
            return fileName.substring(i + 1);
        }
        return null;
    }

    private static void writeJpeg(BufferedImage image, String destFile, float quality) throws IOException {
        ImageWriter writer = null;
        FileImageOutputStream output = null;

        try {
            writer = ImageIO.getImageWritersByFormatName("jpeg").next();

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);

            output = new FileImageOutputStream(new File(destFile));
            writer.setOutput(output);

            IIOImage iioImage = new IIOImage(image, null, null);
            writer.write(null, iioImage, param);
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (writer != null) {
                writer.dispose();
            }

            if (output != null) {
                output.close();
            }
        }
    }

}
