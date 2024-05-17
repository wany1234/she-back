package com.she.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.AttachFile;
import com.she.common.model.AttachFileGrp;
import com.she.common.model.Diagram;
import com.she.common.model.SafAttachFile;
import com.she.config.GlobalSettings;
import com.she.file.service.FileStorageService;
import com.she.security.util.StringUtil;

/**
 * 첨부파일 서비스 클래스
 */
@Service
public class AttachFileService {
    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private GlobalSettings globalSettings;

    /**
     * 신규 첨부파일 아이디 생성
     * 
     * @return
     * @throws Exception
     */
    public String getNewAttachFileId() throws Exception {
        String attachFileId = "";
        HashMap<String, Object> map = this.attachFileMapper.getNewAttachFileId();
        if (map != null && map.containsKey("attach_file_id")) {
            attachFileId = String.valueOf(map.get("attach_file_id"));
        }

        return attachFileId;
    }

    /**
     * 미리업로드된 파일 적용
     * 
     * @param attachFileGrp
     * @return
     * @throws Exception
     */
    public int applyUploadFiles(AttachFileGrp attachFileGrp) throws Exception {

        if (StringUtil.isEmpty(attachFileGrp.getTaskFlag())) {
            this.attachFileMapper.applyUploadFiles(attachFileGrp);
        } else {
            this.attachFileMapper.applyUploadSafFiles(attachFileGrp);
        }
        return 1;
    }

    /**
     * 파일 업로드
     * 
     * @param attachFileGrp
     * @return
     * @throws Exception
     */
    @Transactional
    public List<AttachFile> uploadFiles(AttachFileGrp attachFileGrp) throws Exception {
        // 1. 파일 지정된 경로에 업로드
        List<AttachFile> attachFiles = this.storeFiles(attachFileGrp);

        if (attachFiles == null || attachFiles.size() <= 0) {
            throw new Exception("파일저장 도중 문제가 발생하였습니다. 관리자에게 문의 바랍니다.");
        }

        // 2. com_attach_file table에 업로드 된 파일 정보 저장
        int count = 0;
        for (AttachFile attachFile : attachFiles) {
            attachFile.setAttachFileId(attachFileGrp.getAttachFileId());
            attachFile.setEncryptYn(attachFileGrp.getEncryptYn());
            attachFile.setTaskClassNm(attachFileGrp.getTaskClassNm());
            attachFile.setTaskKey(attachFileGrp.getTaskKey());
            attachFile.setCreateUserId(attachFileGrp.getCreateUserId());
            if (!"".equals(attachFileGrp.getTaskFlag()) && attachFileGrp.getTaskFlag() != null) {
                count += attachFileMapper.createSafAttachFile(attachFile);
            } else {
                count += attachFileMapper.createAttachFile(attachFile);
            }
        }

        return attachFiles;
    }

    /**
     * 다중 파일 저장
     * 
     * @param attachFileGrp
     * @return
     * @throws Exception
     */
    public List<AttachFile> storeFiles(AttachFileGrp attachFileGrp) throws Exception {
        // 1. 파일을 지정된 경로에 업로드
        List<AttachFile> attachFiles = attachFileGrp.getFiles().stream().map(file -> storeFile(file, attachFileGrp)).collect(Collectors.toList());
        // 2. 첨부된 파일과 업로드 개수가 다를 경우 기존 업로드 파일 삭제
        if (attachFileGrp.getFiles().size() != attachFiles.size()) {
            attachFileGrp.getFiles().stream().map(file -> this.fileStorageService.deleteFile(file.toString()));
            attachFiles = null;
        }
        return attachFiles;
    }

    /**
     * 단일 파일 저장
     * 
     * @param file
     * @return
     */
    public AttachFile storeFile(@RequestParam("file") MultipartFile file, AttachFileGrp attachFileGrp) {
        AttachFile attachFile = null;
        try {
            attachFile = fileStorageService.storeFile(file, attachFileGrp);

            /*
             * storeFile에서 fileDownPath를 지정하고 나오도록 변경. String fileDownloadUri =
             * ServletUriComponentsBuilder.fromCurrentContextPath()
             * .path(downloadPath) //.path(attachFile.getFileSaveNm())
             * .toUriString(); attachFile.setFileDownPath(fileDownloadUri);
             */
            return attachFile;
        } catch (Exception e) {
            e.printStackTrace();
            return attachFile;
        }

    }

    /**
     * 파일복사
     * 
     * @param taskClassNm
     * @param taskKey
     * @return
     * @throws Exception
     */
    public AttachFile copyFile(String srcTaskClassNm, String strTaskKey, String destTaskClassNm, String destTaskKey, String userId) throws Exception {
        // 1. 첨부파일을 복사하여 생성
        // 2. 첨부파일을 첨부파일 db에 저장.

        AttachFile attachFile = null;
        try {

            // 복사할 파일 정보를 읽는다.
            List<AttachFile> fileList = this.getUploadFiles(srcTaskClassNm, strTaskKey, "");
            if (fileList != null && fileList.size() > 0) {
                for (AttachFile file : fileList) {
                    attachFile = fileStorageService.copyFile(destTaskClassNm, destTaskKey, file);
                    if (attachFile != null) {
                        attachFile.setTaskClassNm(destTaskClassNm);
                        attachFile.setTaskKey(destTaskKey);
                        attachFile.setCreateUserId(userId);
                        attachFileMapper.createAttachFile(attachFile);
                    }
                }
            }
            return attachFile;
        } catch (Exception e) {
            e.printStackTrace();
            return attachFile;
        }
    }

    /**
     * fileNo로 파일복사
     * 
     * @param fileNo
     * @param destTaskClassNm
     * @param destTaskKey
     * @param userId
     * @return
     * @throws Exception
     */
    public AttachFile copyFile(int fileNo, String destTaskClassNm, String destTaskKey, String userId) throws Exception {
        // 1. 첨부파일을 복사하여 생성
        // 2. 첨부파일을 첨부파일 db에 저장.

        AttachFile attachFile = null;
        try {

            // 복사할 파일 정보를 읽는다.
            AttachFile srcFile = this.getUploadFile(String.valueOf(fileNo), "");
            if (srcFile != null) {
                attachFile = fileStorageService.copyFile(destTaskClassNm, destTaskKey, srcFile);
                if (attachFile != null) {
                    attachFile.setTaskClassNm(destTaskClassNm);
                    attachFile.setTaskKey(destTaskKey);
                    attachFile.setCreateUserId(userId);
                    attachFileMapper.createAttachFile(attachFile);
                }
            }
            return attachFile;
        } catch (Exception e) {
            e.printStackTrace();
            return attachFile;
        }
    }

    /**
     * 업무그룹별 업로드 파일 조회
     * 
     * @param taskClassNm
     *            업무그룹명
     * @param taskKey
     *            업무그룹키
     * @return
     */
    public List<AttachFile> getUploadFiles(String taskClassNm, String taskKey, String taskFlag) throws Exception {
        // taskFlag + "###");
        if (!"".equals(taskFlag) && taskFlag != null) {
            return attachFileMapper.getSafUploadFiles(taskClassNm, taskKey);
        } else {
            return attachFileMapper.getUploadFiles(taskClassNm, taskKey);
        }
    }

    /**
     * 첨부파일아이디를 이용한 파일 조회
     * 
     * @param attachFileId
     * @return
     * @throws Exception
     */
    public List<AttachFile> getUploadFilesById(String attachFileId, String taskFlag) throws Exception {
        if (!"".equals(taskFlag) && taskFlag != null) {
            return attachFileMapper.getSafUploadFilesById(attachFileId);
        } else {
            return attachFileMapper.getUploadFilesById(attachFileId);
        }
    }

    /**
     * PSM 파일 List 조회
     * 
     * @param taskClassNm
     *            업무그룹명
     * @param taskKey
     *            업무그룹키
     * @return
     */
    public List<SafAttachFile> getSafAttachFileList(String taskClassNm, String docuKindCd, String taskKey, String taskFlag, String plantCd, String docuTitle, String refTableId, String mgtEditable, String processCd) throws Exception {
        return attachFileMapper.getSafAttachFileList(taskClassNm, docuKindCd, taskKey, taskFlag, plantCd, docuTitle, refTableId, mgtEditable, processCd);
    }

    /**
     * 도면 파일 List 조회
     * 
     * @param taskClassNm
     *            업무그룹명
     * @param taskKey
     *            업무그룹키
     * @return
     */
    public List<Diagram> getDiagramList(String taskClassNm, String diagClassCd, String taskKey, String taskFlag, String plantCd, String diagTitle, String processCd, String chngNum) throws Exception {
        return attachFileMapper.getDiagramList(taskClassNm, diagClassCd, taskKey, taskFlag, plantCd, diagTitle, processCd, chngNum);
    }

    public AttachFile getUploadFile(String fileNo, String taskFlag) throws Exception {
        if (!"".equals(taskFlag) && taskFlag != null) {
            return attachFileMapper.getSafFile(fileNo);
        } else {
            return attachFileMapper.getUploadFile(fileNo);
        }
    }

    public AttachFile getSafUploadFile(String fileNo, String taskClassNm) throws Exception {
        return attachFileMapper.getSafUploadFile(fileNo, taskClassNm);
    }

    public int deleteFile(String fileNo) throws Exception {
        AttachFile attachFile = attachFileMapper.getUploadFile(fileNo);
        this.fileStorageService.deleteFile(attachFile.getFilePath());
        return attachFileMapper.deleteFile(fileNo);
    }

    public int deleteFilesAll(String taskClassNm, String taskKey) throws Exception {
        List<AttachFile> attachFiles = attachFileMapper.getFileList(taskClassNm, taskKey);
        if (CollectionUtils.isNotEmpty(attachFiles)) {
            for (AttachFile attachFile : attachFiles) {
                this.fileStorageService.deleteFile(attachFile.getFilePath());
            }
        }
        return attachFileMapper.deleteFilesAll(taskClassNm, taskKey);
    }

    public int safDeleteFile(String fileNo) throws Exception {
        // AttachFile attachFile = attachFileMapper.getUploadFile(fileNo);
        // this.fileStorageService.deleteFile(attachFile.getFilePath());
        return attachFileMapper.safDeleteFile(fileNo);
    }

    public int deleteMultiFile(List<Map<String, String>> files) throws Exception {
        int resultNo = 0;
        for (Map<String, String> map : files) {
            AttachFile attachFile = attachFileMapper.getUploadFile(map.get("fileNo"));
            if (attachFile != null) {
                this.fileStorageService.deleteFile(attachFile.getFilePath());
                resultNo += attachFileMapper.deleteFile(map.get("fileNo"));
            }
        }
        return resultNo;
    }

    public int safDeleteMultiFile(List<Map<String, String>> files) throws Exception {
        int resultNo = 0;
        for (Map<String, String> map : files) {
            resultNo += attachFileMapper.safDeleteFile(map.get("fileNo"));
        }
        return resultNo;
    }

    public int deleteFiles(AttachFileGrp attachFileGrp) throws Exception {
        List<AttachFile> attachFiles = this.getUploadFiles(attachFileGrp.getTaskClassNm(), attachFileGrp.getTaskKey(), attachFileGrp.getTaskFlag());
        int count = 0;
        for (AttachFile attachFile : attachFiles) {
            if ("".equals(attachFileGrp.getTaskFlag()) || attachFileGrp.getTaskFlag() == null) {
                count += this.deleteFile(Integer.toString(attachFile.getFileNo()));
            } else {
                count += this.safDeleteFile(Integer.toString(attachFile.getFileNo()));
            }
        }
        return count;
    }

    /**
     * 구버전 파일 리스트 조회
     * 
     * @param taskClassNm
     *            업무그룹명
     * @param taskKey
     *            업무그룹키
     * @return
     */
    public List<AttachFile> getFileList(@Param("taskClassNm") String taskClassNm, @Param("taskKey") String taskKey) throws Exception {
        return attachFileMapper.getFileList(taskClassNm, taskKey);
    }

    /**
     * 일반 파일다운드
     * 
     * @param attachFile
     * @return
     * @throws IOException
     */
    public InputStreamResource fileDownload(AttachFile attachFile) throws IOException {
        String downFileFullPath = attachFile.getFileDownPath();
        if (attachFile == null || StringUtil.isEmpty(attachFile.getFileOrgNm())) {
            throw new FileNotFoundException("File information not found.");
        }

        String downFileName = attachFile.getFileOrgNm();
        File file = new File(globalSettings.getFileStorePath() + File.separator + downFileFullPath);

        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException(downFileName);
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return resource;
    }

    public File getFile(AttachFile attachFile) throws FileNotFoundException {
        String downFileFullPath = attachFile.getFileDownPath();
        if (attachFile == null || StringUtil.isEmpty(attachFile.getFileOrgNm())) {
            throw new FileNotFoundException("File information not found.");
        }

        String downFileName = attachFile.getFileOrgNm();
        return new File(globalSettings.getFileStorePath() + File.separator + downFileFullPath);
    }

    public int editExplain(AttachFile attachFile) throws Exception {
        return attachFileMapper.editExplain(attachFile);
    }

    public String uploadFiles(String taskClassNm, String taskKey, String attachFileId, String taskFlag, String createUserId, String pictureExplan, MultipartFile[] files) throws Exception {
        AttachFileGrp attachFileGrp = new AttachFileGrp(taskClassNm, taskKey, taskFlag, createUserId, pictureExplan, new ArrayList<>(Arrays.asList(files)));

        boolean isUpload = true;
        // 업무 그룹별 첨부 파일 설정 조회

        // 파일 확장자 체크

        if (attachFileId == null || attachFileId.equals("")) {
            // 첨부파일아이디 생성
            attachFileId = this.getNewAttachFileId();
        }
        attachFileGrp.setAttachFileId(attachFileId);

        if (isUpload) {
            this.uploadFiles(attachFileGrp);
        }
        return attachFileId;
    }

    /**
     * 파일 업로드(테이블)
     *
     * @return
     * @throws Exception
     */
    @Transactional
    public void uploadTableFiles(String taskKey, String tempId, String createUserId, String taskClassNm, List<String> tempIds, MultipartFile[] files) throws Exception {
        if (tempIds != null && tempIds.size() > 0) {
            List<Integer> indexList = new ArrayList<Integer>();
            // 1. tempId의 index를 찾는다.
            for (int i = 0; i < tempIds.size(); i++) {
                if (tempId == null) {
                    indexList.add(i);
                } else if (!tempId.equals(tempIds.get(i)) && !"".equals(tempIds.get(i))) {
                    // tempId와 [전체]tempIds 안에 있는 값이 동일 하지 경우 해당 index를 저장해 놓는다.
                    // 지울 index를 저장
                    indexList.add(i);
                }
            }
            // 2. 찾은 index를 통해 저장할 file정보를 추려낸다.
            List<MultipartFile> saveFiles = new ArrayList<>(Arrays.asList(files));
            if (indexList != null && indexList.size() > 0) {
                for (int i = indexList.size() - 1; i >= 0; i--) {
                    saveFiles.remove((int) indexList.get(i));
                }
            }

            if (saveFiles.size() == 0) {
                return;
            }

            // 3. 첨부파일 model에 셋팅
            AttachFileGrp attachFileGrp = new AttachFileGrp(taskClassNm, taskKey, "", createUserId, "", saveFiles);
            // 3-1. attach_file_id 생성
            String attachFileId = "";
            if (attachFileId == null || attachFileId.equals("")) {
                // 첨부파일아이디 생성
                attachFileId = this.getNewAttachFileId();
            }
            attachFileGrp.setAttachFileId(attachFileId);
            // 4. 첨부파일 저장
            this.uploadFiles(attachFileGrp);
        }
    }
}
