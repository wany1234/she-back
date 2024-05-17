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
package com.she.chm.chem.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.chm.chem.service.MSDSService;
import com.she.chm.model.MSDS;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.file.service.FileStorageService;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/chm/chem")
public class MSDSController {

    private final Logger logger = LoggerFactory.getLogger(MSDSController.class);

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private MSDSService msdsService;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * MSDS 조회
     *
     * @param parameter
     *            검색조건
     * @return MSDS 목록
     * @throws Exception
     */
    @GetMapping("/msdses")
    public ResponseEntity<List<MSDS>> getMsdss(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String search = map.containsKey("search") ? map.get("search").toString() : "";
        String delYn = map.containsKey("delYn") ? map.get("delYn").toString() : "";
        String newYn = map.containsKey("newYn") ? map.get("newYn").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String fromYmd = map.containsKey("fromYmd") ? map.get("fromYmd").toString() : "";
        String toYmd = map.containsKey("toYmd") ? map.get("toYmd").toString() : "";
        int chemProdNo = map.containsKey("chemProdNo") ? Integer.parseInt("".equals(map.get("chemProdNo").toString()) ? "0" : map.get("chemProdNo").toString()) : 0;
        int msdsGrpNo = map.containsKey("msdsGrpNo") ? Integer.parseInt("".equals(map.get("msdsGrpNo").toString()) ? "0" : map.get("msdsGrpNo").toString()) : 0;

        return ResponseEntity.ok().body(msdsService.getMsdss(search, delYn, newYn, plantCd, fromYmd, toYmd, chemProdNo, msdsGrpNo, defaultParam));
    }

    /**
     * MSDS 상세 조회
     *
     * @param msdsRqstNo
     *            MSDS 번호
     * @return MSDS
     * @throws Exception
     */
    @GetMapping("/msds/{msdsRqstNo}")
    public ResponseEntity<MSDS> getMsds(@PathVariable(name = "msdsRqstNo") int msdsRqstNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        return ResponseEntity.ok().body(this.msdsService.getMsds(msdsRqstNo, defaultParam));
    }

    /**
     * 중복검사
     *
     * @return 중복 행 수
     * @throws Exception
     */
    @GetMapping("/checkmsds")
    public ResponseEntity<List<HashMap<String, Object>>> checkMsds(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 제조업체
        String makecpCd = map.containsKey("makecpCd") ? map.get("makecpCd").toString() : "";
        // 공급업체
        String vendorCd = map.containsKey("vendorCd") ? map.get("vendorCd").toString() : "";
        // MSDS명(KOR)
        String chemProdNmKr = map.containsKey("chemProdNmKr") ? map.get("chemProdNmKr").toString() : "";
        // MSDS명(ENG)
        String chemProdNmEn = map.containsKey("chemProdNmEn") ? map.get("chemProdNmEn").toString() : "";
        // MSDS no
        int msdsRqstNo = map.containsKey("msdsRqstNo") ? Integer.parseInt("".equals(map.get("msdsRqstNo").toString()) ? "0" : map.get("msdsRqstNo").toString()) : 0;
        // revNum
        int revNum = map.containsKey("revNum") ? Integer.parseInt("".equals(map.get("revNum").toString()) ? "0" : map.get("revNum").toString()) : 0;
        // MSDS 그룹번호
        int msdsGrpNo = map.containsKey("msdsGrpNo") ? Integer.parseInt("".equals(map.get("msdsGrpNo").toString()) ? "0" : map.get("msdsGrpNo").toString()) : 0;

        return ResponseEntity.ok().body(this.msdsService.checkMsds(makecpCd, vendorCd, chemProdNmKr, chemProdNmEn, msdsRqstNo, revNum, msdsGrpNo));
    }

    /**
     * MSDS 신규등록
     *
     * @param chem
     *            MSDS
     * @return MSDS 번호
     * @throws Exception
     */
    @PostMapping("/msds")
    public ResponseEntity<MSDS> createMsds(@RequestBody MSDS msds) throws Exception {
        return ResponseEntity.ok().body(this.msdsService.createMsds(msds));
    }

    /**
     * MSDS 수정
     *
     * @param chem
     *            MSDS
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/msds")
    public ResponseEntity<MSDS> updateMsds(@RequestBody MSDS msds) throws Exception {
        return ResponseEntity.ok().body(this.msdsService.updateMsds(msds));
    }

    //
    // /**
    // * 라벨 및 보고서 출력
    // *
    // * @param reportType
    // * : [warnsign:경고표지] [saftymng:공정관리요령] [msdssumm:MSDS요약본]
    // * @param msdsRqstNo
    // * @return
    // * @throws Exception
    // */
    // @GetMapping("/msds/report/{msdsRqstNo}")
    // public ResponseEntity<InputStreamResource>
    // createMsdsReport(@PathVariable(name = "msdsRqstNo") int msdsRqstNo,
    // @RequestParam HashMap<String, Object> parameter) throws Exception {
    //
    // File file = null;
    // HashMap<String, Object> map =
    // this.requestMapper.convertAsParameter(parameter);
    // String reportType = map.containsKey("reportType") ?
    // map.get("reportType").toString() : "";
    //
    // if ("warnsign".equalsIgnoreCase(reportType)) {
    // file = this.msdsService.getMsdsWarnSignReport(msdsRqstNo);
    // } else if ("saftymng".equalsIgnoreCase(reportType)) {
    // file = this.msdsService.getMsdsSaftyMngReport(msdsRqstNo);
    // } else if ("msdssumm".equalsIgnoreCase(reportType)) {
    // file = this.msdsService.getMsdsReport(msdsRqstNo);
    // }
    //
    // if (file != null) {
    // FileInputStream inputStream = new FileInputStream(file);
    //
    // String downloadfileName = new String(file.getName().getBytes("UTF-8"),
    // "ISO-8859-1");
    //
    // HttpHeaders header = new HttpHeaders();
    // header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
    // downloadfileName + "\"");
    // header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    // header.add("Pragma", "no-cache");
    // header.add("Expires", "0");
    //
    // return ResponseEntity.ok().headers(header).contentLength(file.length())
    // .contentType(MediaType.parseMediaType("application/pdf"))
    // .body(new InputStreamResource(inputStream));
    // } else {
    // HttpHeaders header = new HttpHeaders();
    // header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    //
    // return ResponseEntity.notFound().headers(header).build();
    // }
    //
    // }
    /**
     * 라벨 및 보고서 출력
     *
     * @param reportType
     *            : [warnsign:경고표지] [saftymng:공정관리요령] [msdssumm:MSDS요약본]
     * @param msdsRqstNo
     * @return
     * @throws Exception
     */
    @GetMapping("/msds/report/{msdsRqstNo}")
    public @ResponseBody byte[] createMsdsReport(@PathVariable("msdsRqstNo") int msdsRqstNo) throws Exception {
        File file = this.msdsService.getMsdsWarnSignReport(msdsRqstNo);
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] encoded = Base64.encodeBase64(IOUtils.toByteArray(inputStream));
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException e) {
            return null;
        } finally {
            inputStream.close();
        }
    }

    // MSDS 일괄다운로드
    @GetMapping(value = "/msds/downloadZip")
    public @ResponseBody byte[] CompressZIP(HttpServletRequest request, HttpServletResponse response, Object handler, @RequestParam HashMap<String, Object> param) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(param);
        String[] fileNo = this.requestMapper.convertObjectListAsStringArray(map.get("fileNo"));

        String separator = File.separator;
        String savepath = separator + "zips" + separator + "MSDS" + separator + "zipFile";
        String saveName = "MSDSFile.zip";
        String zipFileName = savepath + separator + saveName;

        File chkZipFile = new File(zipFileName);
        if (chkZipFile.isFile()) {
            chkZipFile.delete();
        }

        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < fileNo.length; i++) {
            String fileInfo = fileNo[i];
            String[] fileInfoArr = fileInfo.split("\\|");
            AttachFile af = msdsService.searchFile(fileInfoArr[2]);

            Map<String, String> tmp = new HashMap<String, String>();
            tmp.put("filePath", af.getFilePath());
            tmp.put("fileName", fileInfoArr[0] + "-" + fileInfoArr[1] + "-" + af.getFileOrgNm());
            fileList.add(tmp);
        }

        File savedir = new File(savepath);
        // 없으면 생성
        if (!savedir.exists())
            savedir.mkdirs();

        byte[] bytes = new byte[4096];
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));

        for (int j = 0; j < fileList.size(); j++) {
            Map<String, String> fileMap = fileList.get(j);
            String fileName = fileMap.get("fileName");
            String filePath = fileMap.get("filePath");

            File chkFile = new File(filePath);

            if (chkFile.isFile()) {
                FileInputStream fis = new FileInputStream(filePath);
                zos.putNextEntry(new ZipEntry(fileName));

                int len = 0;
                while ((len = fis.read(bytes)) > 0) {
                    zos.write(bytes, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }

        }
        zos.close();

        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(zipFileName));

            byte[] buffer = IOUtils.toByteArray(inputStream);

            byte[] encoded = Base64.encodeBase64(buffer);
            String encodedString = new String(encoded);
            return encodedString.getBytes("UTF-8");
        } catch (FileNotFoundException fe) {
            logger.error(fe.getMessage());
            return null;
        } catch (IllegalArgumentException iae) {
            logger.error(iae.getMessage());
            return null;
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    // 개정이력 삭제
    @DeleteMapping("/revMsdsDelete")
    public ResponseEntity<Integer> msdsDeleteLicensingStatus(@RequestBody List<MSDS> licensingStatus) throws Exception {
        return ResponseEntity.ok().body(this.msdsService.msdsDeleteLicensingStatus(licensingStatus));
    }

    // 개정 삭제
    @DeleteMapping("/delete/{msdsRqstNo}")
    public ResponseEntity<Integer> deleteLicensingStatus(@PathVariable("msdsRqstNo") int dataBoardNo) throws Exception {
        return ResponseEntity.ok().body(this.msdsService.deleteLicensingStatus(dataBoardNo));
    }
}
