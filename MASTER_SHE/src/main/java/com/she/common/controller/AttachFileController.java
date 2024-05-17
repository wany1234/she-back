package com.she.common.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.Diagram;
import com.she.common.model.SafAttachFile;
import com.she.common.service.AttachFileService;
import com.she.file.exception.FileStorageException;
import com.she.file.model.ZipFiles;
import com.she.file.service.FileStorageService;
import com.she.manage.model.AttachSetting;
import com.she.manage.service.AttachSettingService;
import com.she.utils.RequestMapper;

@RestController
public class AttachFileController {
    private static Logger logger = LoggerFactory.getLogger(AttachFileController.class);

    // TODO : 파일 저장용 서비스
    @Autowired
    private FileStorageService fileStorageService;

    // TODO : 파일 업로드 정보 처리용 서비스
    @Autowired
    private AttachFileService attachFileService;

    @Autowired
    private AttachSettingService attachSettingService;

    // TODO : queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    /**
     * 미리보기 이미지 조회
     * 
     * @throws Exception
     */
    private void setPreviewImage(AttachFile attachFile) throws Exception {
        if (attachFile.getContentType().indexOf("image") >= 0) {
            File file = attachFileService.getFile(attachFile);
            InputStream inputStream = null;
            InputStream bufferInputStream = null;
            ByteArrayOutputStream stream = null;

            try {
                inputStream = new BufferedInputStream(new FileInputStream(file));

                byte[] buffer = IOUtils.toByteArray(inputStream);
                if (attachFile.getEncryptYn() != null && attachFile.getEncryptYn().equals("Y")) {
                    buffer = this.fileStorageService.decryptFileData(buffer);
                }

                bufferInputStream = new ByteArrayInputStream(buffer);
                BufferedImage inputImage = ImageIO.read(bufferInputStream);

                // 뷰단의 프리뷰가 150*150 으로 됨
                BufferedImage outputImage = new BufferedImage(150, 150, inputImage.getType());

                Graphics2D graphics2D = outputImage.createGraphics();
                graphics2D.drawImage(inputImage, 0, 0, 150, 150, null);
                if (graphics2D != null) {
                    graphics2D.dispose();

                }

                stream = new ByteArrayOutputStream();
                ImageIO.write(outputImage, attachFile.getFileExt(), stream);

                stream.flush();
                byte[] previewData = stream.toByteArray();

                byte[] encoded = Base64.encodeBase64(previewData);
                String encodedString = new String(encoded);

                attachFile.setPreviewImage(encodedString);
            } catch (IOException e) {
                logger.error(e.getMessage());
            } catch (Exception e) {
                logger.error(e.getMessage());
                throw e;
            } finally {
                try {
                    if (stream != null)
                        stream.close();
                    if (bufferInputStream != null)
                        bufferInputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * 파일목록 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    private List<AttachFile> selectUploadFiles(HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String taskFlag = map.containsKey("taskFlag") ? map.get("taskFlag").toString() : "";
        String attachFileId = map.containsKey("attachFileId") ? map.get("attachFileId").toString() : "";
        String preview = map.containsKey("preview") ? map.get("preview").toString() : "false";

        List<AttachFile> attachFiles;
        if (taskKey != null && !taskKey.equals("")) {
            attachFiles = this.attachFileService.getUploadFiles(taskClassNm, taskKey, taskFlag);
        } else {
            attachFiles = this.attachFileService.getUploadFilesById(attachFileId, taskFlag);
        }

        File file = null;
        if (Boolean.valueOf(preview)) {
            for (AttachFile attachFile : attachFiles) {
                file = attachFileService.getFile(attachFile);

                if (file.exists()) {
                    this.setPreviewImage(attachFile);
                }
                // this.setPreviewImage(attachFile);
            }
        }

        for (AttachFile attachFile : attachFiles) {
            attachFile.setCreateDt("");
            attachFile.setFileDownPath("");
            attachFile.setFilePath("");
            attachFile.setFileSaveNm("");
        }

        return attachFiles;
    }

    /**
     * 다중파일 업로드
     * 
     * @return
     */
    @PostMapping("/api/attachfile/uploadfiles")
    public ResponseEntity<String> uploadFiles(@RequestParam("taskClassNm") String taskClassNm, @RequestParam("taskKey") String taskKey, @RequestParam("createUserId") String createUserId, @RequestParam(required = false, value = "attachFileId") String attachFileId, @RequestParam(required = false, value = "pictureExplan") String pictureExplan,
            @RequestParam(required = false, value = "taskFlag") String taskFlag, @RequestParam("files") MultipartFile[] files) throws Exception {
        String fileId = this.attachFileService.uploadFiles(taskClassNm, taskKey, attachFileId, taskFlag, createUserId, pictureExplan, files);

        return ResponseEntity.ok().body(fileId);
    }

    /**
     * 다중파일 업로드
     * 
     * @return
     */
    @PostMapping("/api/attachfile/uploadfiles2")
    public ResponseEntity<String> uploadFiles(@RequestParam("taskClassNm") String taskClassNm, @RequestParam("taskKey") String taskKey, @RequestParam(required = false, value = "taskFlag") String taskFlag, @RequestParam(value = "pictureExplan", required = false) String pictureExplan, @RequestParam("createUserId") String createUserId,
            @RequestParam("files") MultipartFile[] files, @RequestParam("manualSetting") boolean manualSetting, @RequestParam("acceptExt") String acceptExt, @RequestParam("attachFileId") String attachFileId, @RequestParam("encryptYn") String encryptYn) throws Exception {
        AttachFileGrp attachFileGrp = new AttachFileGrp(taskClassNm, taskKey, taskFlag, createUserId, pictureExplan, new ArrayList<>(Arrays.asList(files)));
        boolean success = true;
        String extensions = acceptExt;
        if (!manualSetting) {
            // 자동설정시 attachSetting.acceptExt 를 통하여 확장자 체크
            AttachSetting attachSetting = this.attachSettingService.getAttachSetting(taskClassNm);
            if (attachSetting != null && attachSetting.getAcceptExt() != null) {
                extensions = attachSetting.getAcceptExt();
            }
        }

        if (extensions != null && !extensions.equals("")) {
            String[] checkList = extensions.split(",");
            for (MultipartFile multipartFile : attachFileGrp.getFiles()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                String fileExt = FilenameUtils.getExtension(fileName);
                if (ArrayUtils.indexOf(checkList, fileExt.toLowerCase()) < 0) {
                    success = false;
                    break;
                }
            }
        }

        attachFileGrp.setEncryptYn(encryptYn);
        if (attachFileId == null || attachFileId.equals("")) {
            // 첨부파일아이디 생성
            attachFileId = this.attachFileService.getNewAttachFileId();
            attachFileGrp.setAttachFileId(attachFileId);
        } else {
            attachFileGrp.setAttachFileId(attachFileId);
        }

        if (success) {
            this.attachFileService.uploadFiles(attachFileGrp);
        }

        return ResponseEntity.ok().body(attachFileGrp.getAttachFileId());
    }

    /**
     * 미리업로드된 파일 전체에 taskKey(식별자) 적용
     * 
     * @param attachFileGrp
     * @return
     * @throws Exception
     */
    @PostMapping("/api/attachfile/applyuploadfiles")
    public ResponseEntity<String> applyUploadFiles(@RequestBody AttachFileGrp attachFileGrp) throws Exception {
        this.attachFileService.applyUploadFiles(attachFileGrp);
        return ResponseEntity.ok().body(attachFileGrp.getAttachFileId());
    }

    /**
     * 업무그룹별 업로드 파일 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/api/attachfile/uploadfiles")
    public ResponseEntity<List<AttachFile>> getUploadFiles(@RequestParam HashMap<String, Object> parameter) throws Exception {
        List<AttachFile> attachFiles = this.selectUploadFiles(parameter);
        return ResponseEntity.ok().body(attachFiles);
    }

    /**
     * PSM 파일 List 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/api/attachfile/safFileList")
    public ResponseEntity<List<SafAttachFile>> getSafAttachFileList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String docuKindCd = map.containsKey("docuKindCd") ? map.get("docuKindCd").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String taskFlag = map.containsKey("taskFlag") ? map.get("taskFlag").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String docuTitle = map.containsKey("docuTitle") ? map.get("docuTitle").toString() : "";
        String refTableId = map.containsKey("refTableId") ? map.get("refTableId").toString() : "";
        String mgtEditable = map.containsKey("mgtEditable") ? map.get("mgtEditable").toString() : "false";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        return ResponseEntity.ok().body(this.attachFileService.getSafAttachFileList(taskClassNm, docuKindCd, taskKey, taskFlag, plantCd, docuTitle, refTableId, mgtEditable, processCd));
    }

    /**
     * 도면 파일 List 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/api/attachfile/diagramlist")
    public ResponseEntity<List<Diagram>> getDiagramList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("taskKey") ? map.get("taskKey").toString() : "";
        String diagClassCd = map.containsKey("diagClassCd") ? map.get("diagClassCd").toString() : "";
        String taskFlag = map.containsKey("taskFlag") ? map.get("taskFlag").toString() : "";
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        String diagTitle = map.containsKey("diagTitle") ? map.get("diagTitle").toString() : "";
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";
        String chngNum = map.containsKey("chngNum") ? map.get("chngNum").toString() : "";

        return ResponseEntity.ok().body(this.attachFileService.getDiagramList(taskClassNm, diagClassCd, taskKey, taskFlag, plantCd, diagTitle, processCd, chngNum));
    }

    /**
     * 파일 전체 zip 압축 후 다운로드(실제 사용하지 않음, 사용시 암호화관련 로직 추가 필요)
     * 
     * @param parameter
     * @param request
     * @return
     * @throws IOException
     * @throws FileStorageException
     * @throws Exception
     */
    @GetMapping("/api/attachfile/zipping")
    public ResponseEntity<String> downloadZip(@RequestParam HashMap<String, Object> parameter, HttpServletRequest request) throws IOException, FileStorageException, Exception {
        try {
            List<AttachFile> attachFiles = this.selectUploadFiles(parameter);
            List<String> files = new ArrayList<String>();
            List<String> targetFiles = new ArrayList<String>();

            String zipFileName = "";
            int i = 0;
            for (AttachFile attachFile : attachFiles) {
                if (i <= 0) {
                    zipFileName = FilenameUtils.getBaseName(attachFile.getFileOrgNm());
                }
                files.add(attachFile.getFilePath());
                targetFiles.add(attachFile.getFileOrgNm());
                i++;
            }

            if (i > 1) {
                zipFileName += "+" + Integer.toString(i - 1);
            }

            ZipFiles zipFiles = new ZipFiles(zipFileName, files, targetFiles, fileStorageService.getFileStorageLocation().getParent().toString());
            // String path = zipFiles.filesToZip();

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/attachfile/zip/").path(zipFiles.getZipFileName()).toUriString();

            return ResponseEntity.ok().body(fileDownloadUri);
        } catch (FileStorageException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/attachfile/deletefile/{fileNo}")
    public ResponseEntity<Integer> deleteFile(@PathVariable String fileNo) throws Exception {
        return ResponseEntity.ok().body(attachFileService.deleteFile(fileNo));
    }

    @DeleteMapping("/api/attachfile/deletefile/{taskClassNm}/{taskKey}")
    public ResponseEntity<Integer> deleteFilesAll(@PathVariable String taskClassNm, @PathVariable String taskKey) throws Exception {
        return ResponseEntity.ok().body(attachFileService.deleteFilesAll(taskClassNm, taskKey));
    }

    @DeleteMapping("/api/attachfile/safDeletefile/{fileNo}")
    public ResponseEntity<Integer> safDeleteFile(@PathVariable String fileNo) throws Exception {
        int count = attachFileService.safDeleteFile(fileNo);
        return ResponseEntity.ok().body(count);
    }

    @DeleteMapping("/api/attachfile/deletemultifile")
    public ResponseEntity<Integer> deleteMultiFile(@RequestBody List<Map<String, String>> files) throws Exception {
        return ResponseEntity.ok().body(attachFileService.deleteMultiFile(files));
    }

    @DeleteMapping("/api/attachfile/safdeletemultifile")
    public ResponseEntity<Integer> safDeleteMultiFile(@RequestBody List<Map<String, String>> files) throws Exception {
        return ResponseEntity.ok().body(attachFileService.safDeleteMultiFile(files));
    }

    @PostMapping("/api/attachfile/deletefiles")
    public ResponseEntity<Integer> deleteFiles(@RequestBody AttachFileGrp attachFileGrp) throws Exception {
        int count = attachFileService.deleteFiles(attachFileGrp);
        return ResponseEntity.ok().body(count);
    }

    /**
     * 업무그룹별 업로드 파일 조회
     * 
     * @param parameter
     * @return
     * @throws Exception
     */
    @GetMapping("/api/attachfile/getfilelist")
    public ResponseEntity<List<AttachFile>> getFileList(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);
        String taskClassNm = map.containsKey("taskClassNm") ? map.get("taskClassNm").toString() : "";
        String taskKey = map.containsKey("gridkey") ? map.get("gridkey").toString() : "";

        List<AttachFile> attachFile = this.attachFileService.getFileList(taskClassNm, taskKey);
        return ResponseEntity.ok().body(attachFile);
    }

    @PostMapping("/api/attachfile/editexplain")
    public ResponseEntity<Integer> editExplain(@RequestBody AttachFile attachFile) throws Exception {
        int count = this.attachFileService.editExplain(attachFile);
        return ResponseEntity.ok().body(count);
    }
}
