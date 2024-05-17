package com.she.common.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 첨부파일 그룹
 */
public class AttachFileGrp {
    public String taskClassNm;
    public String taskKey;
    public String taskFlag;
    public String createUserId;
    public String createDt;
    public String pictureExplan;
    public String attachFileId;
    public String encryptYn;
    public List<MultipartFile> files;

    public AttachFileGrp() {
        this.files = new ArrayList<>();
    }

    public AttachFileGrp(String taskClassNm, String taskKey, String taskFlag, String createUserId, String pictureExplan, List<MultipartFile> files) {
        this.taskClassNm = taskClassNm;
        this.taskKey = taskKey;
        this.taskFlag = taskFlag;
        this.createUserId = createUserId;
        this.pictureExplan = pictureExplan;
        this.files = files;
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

    public String getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(String taskFlag) {
        this.taskFlag = taskFlag;
    }

    public String getPictureExplan() {
        return pictureExplan;
    }

    public void setPictureExplan(String pictureExplan) {
        this.pictureExplan = pictureExplan;
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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getAttachFileId() {
        return attachFileId;
    }

    public void setAttachFileId(String attachFileId) {
        this.attachFileId = attachFileId;
    }

    public String getEncryptYn() {
        return encryptYn;
    }

    public void setEncryptYn(String encryptYn) {
        this.encryptYn = encryptYn;
    }
}
