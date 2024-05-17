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
package com.she.env.waste.allbaro.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.service.AttachFileService;
import com.she.env.waste.allbaro.mapper.AllbaroMapper;
import com.she.env.waste.model.DisposalResult;
import com.she.file.service.FileStorageService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.utils.ConstVal;
import com.she.utils.ExcelReader;

/**
 * 폐기물 관리대장 기능정의
 *
 */
@Service("allbaroService")
public class AllbaroService {

    private static final String ALLBARO_DATA_TASK_CLASS = "ALLBARO_DATA_TEMPLETE";

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private AllbaroMapper allbaroMapper;

    @Autowired
    private FileStorageService fileStorageService;

    private static final Logger logger = LoggerFactory.getLogger(AllbaroService.class);

    @Transactional
    public int createAllbaroData(List<DisposalResult> allbaros) throws Exception {
        int insertCount = 0;
        for (DisposalResult allbaro : allbaros) {
            DisposalResult disposalResult = new DisposalResult();
            BeanUtils.copyProperties(disposalResult, allbaro);
            if ("update".equals(allbaro.getStatus())) {

                disposalResult.setEwstDispoStCd(ConstVal.ENV_WASTE_DISPOSAL_STATUS_COMPLETE);

                this.allbaroMapper.updateDisposalResult(disposalResult);
            } else {
                disposalResult.setEwstDispoStCd(ConstVal.ENV_WASTE_DISPOSAL_STATUS_PROCESS);

                this.allbaroMapper.createDisposalResult(disposalResult);
            }
        }

        // for (Allbaro allbaro : allbaros) {
        // if (!"delete".equals(allbaro.getStatus())) {
        // insertCount = this.allbaroMapper.createAllbaroData(allbaro);
        // DisposalResult disposalResult = new DisposalResult();
        // BeanUtils.copyProperties(disposalResult, allbaro);
        //
        // disposalResult.setVendorCd(allbaro.getDispoVendorCd());
        // disposalResult.setFreightCd(allbaro.getFreightVendorCd());
        // disposalResult.setEwstDispoStCd(ConstVal.ENV_WASTE_DISPOSAL_STATUS_COMPLETE);
        // disposalResult.setSellYn(allbaro.getSellYn());
        // disposalResult.setCarrier(allbaro.getCarrierNum());
        // disposalResult.setEnvUnitNm(allbaro.getUnitNm());
        // disposalResult.setDispoYmd(allbaro.getDispoYmd());
        // disposalResult.setEwstDispoMtdCd(allbaro.getEwstDispoMtdCd());
        // this.allbaroMapper.createDisposalResult(disposalResult);
        // }
        // }
        return insertCount;
    }

    public List<DisposalResult> getUploadExcelAllbaro(MultipartFile[] files) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String todate = formatter.format(new Date());
        AttachFileGrp attachFileGrp = new AttachFileGrp(ALLBARO_DATA_TASK_CLASS, todate, "", "", "", new ArrayList<>(Arrays.asList(files)));
        List<AttachFile> attachFiles = this.attachFileService.uploadFiles(attachFileGrp);

        // 저장된 엑셀 파일의 정보를 읽는다.
        List<AttachFile> uploaded = attachFileService.getUploadFiles(ALLBARO_DATA_TASK_CLASS, todate, "");

        List<DisposalResult> allbaros = new ArrayList<DisposalResult>();
        // List<AttachFile> uploaded =
        // attachFileMapper.getUploadFiles(taskClassNm, taskKey);

        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        try {

            if (uploaded == null || uploaded.size() == 0) {
                return null;
            }

            File excel = new File(uploaded.get(0).getFilePath());
            ExcelReader reader = new ExcelReader();
            List<String[][]> sheets = reader.read(excel, 0);

            // File templete = new File(path.getCodeNm() +
            // "\\올바로인계서_양식_v1.0.xlsx");
            // File templete = new
            // File("C:\\SHE_FILE\\FORM\\올바로인계서_양식_v1.0.xlsx");
            ClassPathResource classPathResource = new ClassPathResource("templates" + path.getCodeNm() + "올바로인계서_양식_v1.0.xlsx");
            File templete = classPathResource.getFile();
            reader = new ExcelReader();
            List<String[][]> templeteSheets = reader.read(templete);

            if (sheets != null && templeteSheets != null) {
                String[][] templeteData = templeteSheets.get(0);
                String[][] allbarodData = sheets.get(0);

                // 헤더 양식확인
                if (allbarodData.length > 0 && !reader.excelHeaderCheck(allbarodData[0], templeteData[0])) {
                    DisposalResult allbaro = new DisposalResult();
                    allbaro.setErrorMessage("업로드 오류: 시트의 헤더가 다릅니다.");
                    allbaros.add(allbaro);
                } else {
                    if (allbarodData.length == 1) {
                        DisposalResult allbaro = new DisposalResult();
                        allbaro.setErrorMessage("업로드 오류: 데이터가 없습니다.");
                        allbaros.add(allbaro);
                    } else {
                        // 올바로 엑셀업로드로 올라갔었던 정보들만 가져온다.
                        List<DisposalResult> checkAllbaro = allbaroMapper.getAllbaroData();

                        // 엑셀로 읽어들인 데이터를 한줄한줄 읽는다.
                        for (int i = 1; i < allbarodData.length; i++) {
                            DisposalResult allbaro = new DisposalResult();
                            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                            for (int j = 0; j < allbarodData[i].length; j++) {
                                String errorMsg = "[데이터 문제] ";
                                map.put(templeteData[1][j], allbarodData[i][j]);

                                if ("ewstClassNm".equals(templeteData[1][j])) {
                                    // 폐기물종류(성상) 이런식으로 들어오기에 페기물 종류와 성상을 분리 시킨다.
                                    // 폐기물종류는 SHE 시스템에서 관리하고 있는 데이터이지만 유효성 체크는
                                    // 하지 않고
                                    // 텍스트만 찍음
                                    // 폐기물을 선택할 것이고 해당 폐기물은 하나의 폐기물종류를 가짐으로 저장
                                    // 시에 해당
                                    // 폐기물의 폐기물 종류를 저장한다.
                                    // 폐기물종류(성상) 데이터를 가져온다
                                    String data = allbarodData[i][j];
                                    if (data.equals("") || data == null) {
                                        errorMsg += "'인계일자' ";
                                        map.put("status", "delete");
                                    } else {
                                        if (Pattern.compile(".*\\(.*\\)").matcher(data).matches()) {
                                            map.put("ewstClassItem", data.substring(data.lastIndexOf("("), data.length()).replaceAll("\\(|\\)", ""));
                                            map.put("ewstClassNm", data.substring(0, data.lastIndexOf("(")));
                                        } else {
                                            map.put("ewstClassNm", data);
                                        }
                                    }

                                } else if (templeteData[1][j].equals("allbaroTransNum")) {
                                    // 인계번호는 실직적인 키로 빈값이 들어가 있는지 아닌지를 체크한다.
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'인계번호' ";
                                        map.put("status", "delete");
                                    }
                                } else if (templeteData[1][j].equals("dispoYmd")) {
                                    // 인계일자는 빈값이 들어가면 안되며 날자형식(yyyy-MM-dd) 이여야
                                    // 한다.
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'인계일자' ";
                                        map.put("status", "delete");
                                    } else if (!this.dateCheck(allbarodData[i][j], "yyyy-MM-dd")) {
                                        errorMsg += "'인계일자' ";
                                        map.put("status", "delete");
                                    }
                                } else if (templeteData[1][j].equals("amtGent")) {
                                    // 위탁량는 빈값이 들어가면 안되며
                                    // 실수로 소수점 3자리까지 포함한 총 13자리숫자여야 함
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'위탁량' ";
                                        map.put("status", "delete");
                                    } else if (!this.isNumber(allbarodData[i][j])) {
                                        errorMsg += "'위탁량' ";
                                        map.put("status", "delete");
                                    } else if (String.valueOf(Double.parseDouble(String.valueOf(map.get("amtGent")))).length() > 13) {
                                        errorMsg += "'위탁량' ";
                                        map.put("status", "delete");
                                    } else {
                                        NumberFormat nf = NumberFormat.getNumberInstance();
                                        map.put("amtGen", Float.parseFloat(String.valueOf(map.get("amtGent"))));
                                        map.put("amtGent", String.valueOf(nf.format(Double.parseDouble(String.valueOf(map.get("amtGent"))))));
                                    }
                                } else if (templeteData[1][j].equals("envUnitNm")) {
                                    // 단위는 빈값이 들어가면 안되며
                                    // SHE 시스템에서 관리하고 있는 단위 정보를 가지고 code정보를
                                    // 가져온다.
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'단위' ";
                                        map.put("status", "delete");
                                    } else if (!allbarodData[i][j].toUpperCase().equals("KG") && !allbarodData[i][j].toUpperCase().equals("TON")) {
                                        errorMsg += "'단위' ";
                                        map.put("status", "delete");
                                    } else if (allbarodData[i][j].toUpperCase().equals("TON")) {
                                        // ton으로 들어오는 경우에는 위탁량을 1000 곱하고 단위는
                                        // kg으로 한다.
                                        if (!"".equals(map.get("amtGent")) && map.get("amtGent") != null && this.isNumber(String.valueOf(map.get("amtGent")))) {
                                            map.put("amtGen", Float.parseFloat(String.valueOf(map.get("amtGent"))) * 1000);
                                            NumberFormat nf = NumberFormat.getNumberInstance();
                                            map.put("amtGent", String.valueOf(nf.format(Double.parseDouble(String.valueOf(map.get("amtGent"))) * 1000)));
                                            map.put("envUnitNm", "kg");

                                        }

                                    }
                                } else if (templeteData[1][j].equals("carrierNum")) {
                                    // 차량번호는 빈값이 들어가면 안된다
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'챠랑번호' ";
                                        map.put("status", "delete");
                                    }
                                } else if (templeteData[1][j].equals("ewstDispoMtdNm")) {
                                    // 처리방법은 빈값이 들어가면 안된다
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'처리방법' ";
                                        map.put("status", "delete");
                                    }
                                } else if (templeteData[1][j].equals("freightVendorNm")) {
                                    // 운반자명은 빈값이 들어가면 안된다
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'운반자명' ";
                                        map.put("status", "delete");
                                    }
                                } else if (templeteData[1][j].equals("dispoVendorNm")) {
                                    // 처리자명은 빈값이 들어가면 안된다
                                    if (allbarodData[i][j].equals("") || allbarodData[i][j] == null) {
                                        errorMsg += "'처리자명' ";
                                        map.put("status", "delete");
                                    }
                                }

                                if ("delete".equals(map.get("status"))) {
                                    map.put("errorMessage", errorMsg);
                                } else {
                                    for (DisposalResult data : checkAllbaro) {
                                        if (map.containsValue(data.getAllbaroTransNum())) {
                                            map.put("errorMessage", "이미 존재하는 데이터 입니다 저장버튼을 클릭하면 업데이트 됩니다.");

                                            map.put("status", "update");
                                        }
                                    }
                                }
                            }

                            BeanUtils.populate(allbaro, map);
                            allbaros.add(allbaro);
                        }
                    }
                }
            } else {
                DisposalResult allbaro = new DisposalResult();
                allbaro.setErrorMessage("업로드 오류: 파일을 읽을 수 없습니다.");
                allbaros.add(allbaro);
            }
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
        } catch (IOException ie) {
            logger.error(ie.getMessage());
        } catch (Exception e) {
            DisposalResult allbaro = new DisposalResult();
            allbaro.setErrorMessage("업로드 오류: 파일을 읽는 도중 에러가 발생하였습니다.");
            allbaros.add(allbaro);
        } finally {
            // 다 읽어드린 엑셀 파일은 지운다.
            this.fileStorageService.deleteFile(uploaded.get(0).getFilePath());
            if (uploaded != null) {
                attachFileMapper.deleteFile(String.valueOf(uploaded.get(0).getFileNo()));
            }
        }

        return allbaros;

    }

    public boolean dateCheck(String date, String format) {
        SimpleDateFormat dateFormatParser = new SimpleDateFormat(format, Locale.KOREA);
        dateFormatParser.setLenient(false);
        try {
            dateFormatParser.parse(date);
            return true;
        } catch (ParseException pe) {
            logger.error(pe.getMessage());
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ne) {
            logger.error(ne.getMessage());
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

}
