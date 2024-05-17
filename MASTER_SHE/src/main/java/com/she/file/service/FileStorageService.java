package com.she.file.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.CaseFormat;
import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.config.GlobalSettings;
import com.she.file.exception.FileStorageException;
import com.she.file.exception.MyFileNotFoundException;
import com.she.file.model.FileStorageProperties;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.security.util.DateUtil;
import com.she.utils.AES256Util;

@Service
public class FileStorageService {
    @Autowired
    AttachFileMapper attachFileMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private final Path fileStorageLocation;

    private final Path zipStorageLocation;

    public Path getFileStorageLocation() {
        return fileStorageLocation;
    }

    public Path getZipStorageLocation() {
        return zipStorageLocation;
    }

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties, GlobalSettings globalSettings) {
        this.fileStorageLocation = Paths.get(globalSettings.getFileStorePath()).toAbsolutePath().normalize();

        this.zipStorageLocation = Paths.get(fileStorageProperties.getZipDir()).toAbsolutePath().normalize();

        try {
            // TODO: 업로드용 폴더 생성
            Files.createDirectories(this.fileStorageLocation);
            // TODO: Zip 파일 업로드용 폴더 생성
            Files.createDirectories(this.zipStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /**
     * 업로드 된 파일을 로컬에 저장
     * 
     * @param file
     * @return
     */
    public AttachFile storeFile(MultipartFile file, AttachFileGrp attachFileGrp) throws Exception {
        AttachFile attachFile = null;

        // 원본 파일명 : example.jpg
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // 확장자 : jpg
        String extension = FilenameUtils.getExtension(fileName);

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // 파일명 암호화
            String uploadFileNm = UUID.randomUUID().toString();

            // URLCodec urlCodec = new URLCodec();
            // String key = urlCodec.encode(fileName);
            // AES256Util aes256Util = new AES256Util(key);
            // String targetFileName =
            // aes256Util.fullEncode(FilenameUtils.getBaseName(fileName)) + '.'
            // + extension;

            String targetFileName = uploadFileNm + '.' + extension;
            String addFilePath = attachFileMapper.getUploadFilePath(attachFileGrp.getTaskClassNm());

            // 없을시에 자동으로 생성
            // 생성 디렉토리 path 패턴 ex. taskClassNm = 'TASK_CLASS' >> taskClass\\
            if ("".equals(addFilePath) || addFilePath == null) {
                String taskClassNm = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, attachFileGrp.getTaskClassNm()).toLowerCase();
                CodeMaster codeMaster = new CodeMaster();
                codeMaster.setCodeGroupCd("COM_UPLOAD_FILE_PATH");
                codeMaster.setCode(attachFileGrp.getTaskClassNm());
                codeMaster.setCodeNm(taskClassNm);
                codeMaster.setUseYn("Y");
                codeMaster.setCreateUserId(attachFileGrp.getCreateUserId());
                this.codeMasterMapper.createCodeMaster(codeMaster);
                addFilePath = taskClassNm + File.separator;
            }
            // Copy file to the target location (Replacing existing file with
            // the same name)
            // 날짜폴더 구분 추가 yyyymmdd\\
            addFilePath += DateUtil.currentDateYYYYMMDD() + File.separator;
            addFilePath = addFilePath.replaceAll("\\\\", Matcher.quoteReplacement(File.separator));

            Path targetLocation = this.fileStorageLocation.resolve(addFilePath + targetFileName);
            Path checkUploadDir = this.fileStorageLocation.resolve(addFilePath);

            File dirCheck = new File(checkUploadDir.toString());
            dirCheck.setExecutable(false, true);
            dirCheck.setReadable(true);
            dirCheck.setWritable(false, true);
            if (!dirCheck.isDirectory()) {
                dirCheck.mkdirs();
            }

            if (attachFileGrp.getEncryptYn() != null && attachFileGrp.getEncryptYn().equals("Y")) {
                byte[] buffer = IOUtils.toByteArray(file.getInputStream());
                byte[] bytes = this.encryptFileData(buffer);

                Files.write(targetLocation, bytes, StandardOpenOption.CREATE_NEW);
            } else {
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }

            attachFile = new AttachFile(fileName, // TODO: '파일명.txt'에서 파일명 추출
                    targetFileName, // TODO: 저장 파일명(암호화)
                    extension, // TODO: '파일명.txt'에서 확장자 추출
                    file.getSize(), targetLocation.toString(), file.getContentType(), attachFileGrp.getPictureExplan());

            // attachFile.setFileDownPath(addFilePath.replaceAll("\\\\+",
            // "\\\\") + targetFileName);
            String fileDownPath = addFilePath + targetFileName;
            attachFile.setFileDownPath(fileDownPath);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
        return attachFile;
    }

    /**
     * 파일 데이터 암호화
     * 
     * @param bytes
     * @return
     */
    public byte[] encryptFileData(byte[] bytes) throws Exception {
        byte[] _bytes = bytes.clone();
        AES256Util aes = new AES256Util("she.file.enc");
        return aes.aesEncode(_bytes);
    }

    /**
     * 파일 데이터 복호화
     * 
     * @param bytes
     * @return
     * @throws Exception
     */
    public byte[] decryptFileData(byte[] bytes) throws Exception {
        byte[] _bytes = bytes.clone();
        AES256Util aes = new AES256Util("she.file.enc");
        return aes.aesDecode(_bytes);
    }

    /**
     * taskClassNm, taskKey로 파일을 읽어온다.
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     * @throws Exception
     */
    public AttachFile copyFile(String taskClassNm, String taskKey, AttachFile oldAttachFile) throws Exception {
        // 파일을 복사하고 해당 파일을 반환해 준다.
        File srcFile = new File(oldAttachFile.getFilePath());

        // 파일명 암호화
        String uploadFileNm = UUID.randomUUID().toString();
        String targetFileName = uploadFileNm + '.' + oldAttachFile.getFileExt();
        String addFilePath = attachFileMapper.getUploadFilePath(taskClassNm);
        addFilePath += DateUtil.currentDateYYYYMMDD() + "\\";

        Path targetLocation = this.fileStorageLocation.resolve(addFilePath + targetFileName);
        Path checkUploadDir = this.fileStorageLocation.resolve(addFilePath);

        File dirCheck = new File(checkUploadDir.toString());
        dirCheck.setExecutable(false, true);
        dirCheck.setReadable(true);
        dirCheck.setWritable(false, true);
        if (!dirCheck.isDirectory()) {
            dirCheck.mkdirs();
        }
        File destFile = new File(targetLocation.toString());
        FileUtils.copyFile(srcFile, destFile);

        AttachFile returnAttachFile = new AttachFile(oldAttachFile.getFileOrgNm(), targetFileName, oldAttachFile.getFileExt(), oldAttachFile.getFileSize(), targetLocation.toString(), oldAttachFile.getContentType(), "");

        returnAttachFile.setFileDownPath(addFilePath.replaceAll("\\\\+", "\\\\") + targetFileName);
        return returnAttachFile;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public Resource loadZipFileAsResource(String fileName) {
        try {
            Path filePath = this.zipStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    /**
     * 파일 삭제
     * 
     * @param path
     *            : 파일 경로
     * @return 파일 삭제 여부
     */
    public boolean deleteFile(String path) {
        boolean isDelete = false;
        File file = new File(path);
        if (file.exists()) {
            isDelete = FileDelete(file);
        }
        return isDelete;
    }

    public synchronized boolean FileDelete(File file) {
        boolean isDelete = false;
        isDelete = file.delete();
        return isDelete;
    }

    public Path getFilePath(String fileName) {
        if (FilenameUtils.getExtension(fileName.toLowerCase()).equals("zip")) {
            return this.zipStorageLocation.resolve(fileName).normalize();
        } else {
            return this.fileStorageLocation.resolve(fileName).normalize();
        }
    }
}