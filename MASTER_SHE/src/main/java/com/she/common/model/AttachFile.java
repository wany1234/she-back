package com.she.common.model;

/**
 * 첨부 파일 정보
 */
public class AttachFile {
    private String attachFileId;
    private int fileNo;
    private String taskClassNm;
    private String taskKey;
    private String fileOrgNm;
    private String fileSaveNm;
    private String fileExt;
    private Long fileSize;
    private String filePath;
    private String fileDownPath;
    private String name;
    private String contentType;
    private String pictureExplan;
    private String encryptYn;
    private String createUserId;
    private String createDt;
    private String previewImage;

    public AttachFile() {
    }

    public AttachFile(String fileOrgNm, String fileSaveNm, String fileExt, Long fileSize, String filePath, String contentType, String pictureExplan) {
        this.fileOrgNm = fileOrgNm;
        this.fileSaveNm = fileSaveNm;
        this.fileExt = fileExt;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.contentType = contentType;
        this.pictureExplan = pictureExplan;
    }

    public String getAttachFileId() {
        return attachFileId;
    }

    public void setAttachFileId(String attachFileId) {
        this.attachFileId = attachFileId;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public String getTaskClassNm() {
        return taskClassNm;
    }

    public void setTaskClassNm(String taskClassNm) {
        this.taskClassNm = taskClassNm;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getFileOrgNm() {
        return fileOrgNm;
    }

    public void setFileOrgNm(String fileOrgNm) {
        this.fileOrgNm = fileOrgNm;
    }

    public String getFileSaveNm() {
        return fileSaveNm;
    }

    public void setFileSaveNm(String fileSaveNm) {
        this.fileSaveNm = fileSaveNm;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDownPath() {
        return fileDownPath;
    }

    public void setFileDownPath(String fileDownPath) {
        this.fileDownPath = fileDownPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getPictureExplan() {
        return pictureExplan;
    }

    public void setPictureExplan(String pictureExplan) {
        this.pictureExplan = pictureExplan;
    }

    public String getEncryptYn() {
        return encryptYn;
    }

    public void setEncryptYn(String encryptYn) {
        this.encryptYn = encryptYn;
    }

    public String getPreviewImage() {
        return previewImage;
    }

    public void setPreviewImage(String previewImage) {
        this.previewImage = previewImage;
    }
}
