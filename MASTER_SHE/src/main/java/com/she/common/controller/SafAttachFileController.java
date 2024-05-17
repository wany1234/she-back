package com.she.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.SafAttachFile;
import com.she.common.service.SafAttachFileService;

@RestController
public class SafAttachFileController {
    // TODO : 파일 업로드 정보 처리용 서비스
    @Autowired
    private SafAttachFileService safAttachFileService;

    /**
     * she 문서 생성
     * 
     * @param safAttachFile
     *            she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    @PostMapping("/api/attachfile/safUploadfiles")
    public ResponseEntity<SafAttachFile> createSafSheDocu(@RequestBody SafAttachFile safAttachFile) throws Exception {
        boolean revision = false;
        if (safAttachFile.getDocuGrpNo() > 0) {
            revision = true;
        }

        SafAttachFile safAttachFileInfo = safAttachFileService.createSafSheDocu(safAttachFile, revision);

        if (revision && safAttachFile.getSelectRow() != null) {
            safAttachFileService.createBeforeFile(safAttachFileInfo.getSheDocuNo(), safAttachFile.getSelectRow());
        }

        return ResponseEntity.ok().body(safAttachFileInfo);
    }

    /**
     * she 문서 수정
     * 
     * @param safAttachFile
     *            she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    @PutMapping("/api/attachfile/safUploadfiles")
    public ResponseEntity<Integer> updateSheDocu(@RequestBody SafAttachFile safAttachFile) throws Exception {
        int sheDocuNo = safAttachFileService.updateSheDocu(safAttachFile);

        return ResponseEntity.ok().body(sheDocuNo);
    }

}
