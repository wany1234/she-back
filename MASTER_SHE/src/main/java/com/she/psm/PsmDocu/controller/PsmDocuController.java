package com.she.psm.PsmDocu.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.common.service.AttachFileService;
import com.she.manage.model.CodeMaster;
import com.she.psm.PsmDocu.service.PsmDocuService;
import com.she.psm.model.DocuGrpNo;
import com.she.psm.model.PsmDocu;
import com.she.utils.RequestMapper;

/**
 * 공정안전문서
 */
@RestController
@RequestMapping("api/psm/psmdocu")
public class PsmDocuController {

    private final Logger logger = LoggerFactory.getLogger(PsmDocuController.class);

    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private PsmDocuService psmDocuService;

    @Autowired
    private AttachFileService attachFileService;

    /**
     * 임시 압축파일 업로드 경로
     */
    @Value("${file.zip-dir}")
    private String fileZipPath;

    /**
     * 공정안전문서 목록 조회
     */
    @GetMapping("/getpsmdocuments")
    public ResponseEntity<List<PsmDocu>> getPsmDocuments(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        // 작성기간
        String[] period = this.requestMapper.convertObjectListAsStringArray(map.get("period"));
        String startYmd = "";
        String endYmd = "";
        if (period != null && period.length == 2) {
            startYmd = period[0];
            endYmd = period[1];
        }

        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String categoryCd = map.containsKey("categoryCd") ? map.get("categoryCd").toString() : "";
        String subCategoryCd = map.containsKey("subCategoryCd") ? map.get("subCategoryCd").toString() : "";
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        String docuTitle = map.containsKey("docuTitle") ? map.get("docuTitle").toString() : "";

        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        // 유효성여부
        String checkEffectYn = map.containsKey("checkEffectYn") ? map.get("checkEffectYn").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        List<PsmDocu> psmDocu = psmDocuService.getPsmDocuments(plantCd, categoryCd, deptCd, docuTitle, startYmd, endYmd, subCategoryCd, useYn, checkEffectYn, deptSubYn, defaultParam);
        return ResponseEntity.ok().body(psmDocu);
    }

    /**
     * PSM Portlet 목록 조회
     */
    @GetMapping("/getpsmportletlist")
    public ResponseEntity<List<PsmDocu>> getPsmPortletList(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        List<PsmDocu> psmDocu = psmDocuService.getPsmPortletList(defaultParam);
        return ResponseEntity.ok().body(psmDocu);
    }

    /**
     * sub Category 목록 조회
     */
    @GetMapping("/getsubcategory")
    public ResponseEntity<List<CodeMaster>> getSubCategory(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String code = map.containsKey("code") ? map.get("code").toString() : "";

        List<CodeMaster> subCategory = psmDocuService.getSubCategory(code, defaultParam);
        return ResponseEntity.ok().body(subCategory);
    }

    /**
     * sub Category 문서그룹번호 조회
     */
    @GetMapping("/getdocugrpnolist")
    public ResponseEntity<List<DocuGrpNo>> getDocuGrpNoList() throws Exception {
        List<DocuGrpNo> docuGrpNoList = psmDocuService.getDocuGrpNoList();
        return ResponseEntity.ok().body(docuGrpNoList);
    }

    /**
     * 공정안전문서 상세 조회
     */
    @GetMapping("/getpsmdocument/{psmDocuNo}")
    public ResponseEntity<PsmDocu> getPsmDocument(@PathVariable(name = "psmDocuNo") int psmDocuNo, @ModelAttribute DefaultParam defaultParam) throws Exception {
        PsmDocu psmDocu = psmDocuService.getPsmDocument(psmDocuNo, defaultParam);
        return ResponseEntity.ok().body(psmDocu);
    }

    /**
     * 공정안전문서 개정이력 조회
     */
    @GetMapping("/getrevisionpsmdocument/{docuId}")
    public ResponseEntity<List<PsmDocu>> getRevisionPsmDocument(@PathVariable(name = "docuId") String docuId, @ModelAttribute DefaultParam defaultParam) throws Exception {
        List<PsmDocu> psmDocu = psmDocuService.getRevisionPsmDocument(docuId, defaultParam);
        return ResponseEntity.ok().body(psmDocu);
    }

    /**
     * 공정안전문서 생성
     */
    @PostMapping("/createpsmdocument")
    public ResponseEntity<Integer> createPsmDocument(@RequestBody PsmDocu psmDocu) throws Exception {
        return ResponseEntity.ok().body(psmDocuService.createPsmDocument(psmDocu));
    }

    /**
     * 공정안전문서 수정
     */
    @PutMapping("/updatepsmdocument")
    public ResponseEntity<Integer> updatePsmDocument(@RequestBody PsmDocu psmDocu) throws Exception {
        return ResponseEntity.ok().body(psmDocuService.updatePsmDocument(psmDocu));
    }

    /**
     * 공정안전문서 삭제
     */
    @PutMapping("/deletepsmdocument")
    public ResponseEntity<Integer> deletePsmDocument(@RequestBody PsmDocu psmDocu) throws Exception {
        int psmDocuNo = psmDocu.getPsmDocuNo();
        String delYn = psmDocu.getDelYn();

        return ResponseEntity.ok().body(psmDocuService.deletePsmDocument(psmDocuNo, delYn));
    }

    /**
     * 공정안전문서 개정
     */
    @PutMapping("/renewalpsmdocument")
    public ResponseEntity<Integer> renewalPsmDocument(@RequestBody PsmDocu psmDocu) throws Exception {
        return ResponseEntity.ok().body(psmDocuService.renewalPsmDocument(psmDocu));
    }

    /**
     * 부서코드로 상위,하위 부서코드 구하기
     *
     * @param depdCd
     *            부서코드
     *
     * @return 직계부서코드 목록
     * @throws Exception
     */
    @GetMapping("getdepthierarchylist/{deptCd}")
    public ResponseEntity<List<HashMap<String, Object>>> getgetDeptHierarchyList(@PathVariable(name = "deptCd") String deptCd, @ModelAttribute DefaultParam defaultParam) throws Exception {

        return ResponseEntity.ok().body(psmDocuService.getDeptHierarchyList(deptCd));

    }

    /**
     * 파일다운로드 - 압축
     *
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    // MSDS 일괄다운로드
    @PutMapping(value = "/downloadZip")
    public @ResponseBody byte[] downloadZip(HttpServletRequest request, HttpServletResponse response, Object handler, @RequestBody HashMap<String, Object> param) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(param);

        String fileStr = map.get("fileNo").toString().replace("[", "").replace("]", "");
        String[] fileNo = fileStr.split(",");

        String separator = File.separator;
        String savepath = separator + "zips" + separator + "PSM" + separator + "zipFile";
        String saveName = "PSMFile.zip";
        String zipFileName = savepath + separator + saveName;

        File chkZipFile = new File(zipFileName);
        if (chkZipFile.isFile()) {
            chkZipFile.delete();
        }

        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < fileNo.length; i++) {
            String fileInfo = fileNo[i];
            AttachFile af = attachFileService.getUploadFile(fileInfo, "COUNTRY_SITE1"); // 첨부파일
                                                                                        // 가져오기

            Map<String, String> tmp = new HashMap<String, String>();
            tmp.put("filePath", af.getFilePath());
            tmp.put("fileName", af.getFileOrgNm());
            fileList.add(tmp);
        }

        File savedir = new File(savepath);
        // 없으면 생성
        if (!savedir.exists())
            savedir.mkdirs();

        byte[] bytes = new byte[4096];
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));

        // 기존 소스의 경우 같은 파일명이 존재할시 에러가 발생 => 아래 코드로 수정
        // for (int j = 0; j < fileList.size(); j++) {
        // Map<String, String> fileMap = fileList.get(j);
        // String fileName = fileMap.get("fileName");
        // String filePath = fileMap.get("filePath");
        //
        // File chkFile = new File(filePath);
        //
        // if (chkFile.isFile()) {
        // FileInputStream fis = new FileInputStream(filePath);
        // zos.putNextEntry(new ZipEntry(fileName));
        //
        // int len = 0;
        // while ((len = fis.read(bytes)) > 0) {
        // zos.write(bytes, 0, len);
        // }
        // zos.closeEntry();
        // fis.close();
        // }
        //
        // }
        // zos.close();

        Set<String> usedFilenames = new HashSet<>();

        for (int j = 0; j < fileList.size(); j++) {
            Map<String, String> fileMap = fileList.get(j);
            String fileName = fileMap.get("fileName");
            String filePath = fileMap.get("filePath");

            File chkFile = new File(filePath);

            if (chkFile.isFile()) {
                String uniqueFileName = fileName;

                // 중복된 파일명이 있는지 확인하고 유니크한 파일명 생성
                int index = 1;
                while (usedFilenames.contains(uniqueFileName)) {
                    int lastDotIndex = fileName.lastIndexOf(".");
                    String nameWithoutExtension = lastDotIndex != -1 ? fileName.substring(0, lastDotIndex) : fileName;
                    String fileExtension = lastDotIndex != -1 ? fileName.substring(lastDotIndex) : "";

                    uniqueFileName = nameWithoutExtension + "_" + index + fileExtension;
                    index++;
                }

                usedFilenames.add(uniqueFileName);

                FileInputStream fis = new FileInputStream(filePath);
                zos.putNextEntry(new ZipEntry(uniqueFileName));

                int len;
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
}
