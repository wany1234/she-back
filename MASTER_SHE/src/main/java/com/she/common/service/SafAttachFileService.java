package com.she.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.SafAttachFileMapper;
import com.she.common.model.SafAttachFile;

/**
 * 첨부파일 서비스 클래스
 */
@Service
public class SafAttachFileService {
    @Autowired
    private SafAttachFileMapper safAttachFileMapper;

    /**
     * she 문서 생성
     * 
     * @param safAttachFile
     *            she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    public SafAttachFile createSafSheDocu(SafAttachFile safAttachFile, boolean revision) throws Exception {
        if (revision) {
            safAttachFile.setRevNum(safAttachFile.getRevNum() + 1);
        }

        safAttachFileMapper.createSafSheDocu(safAttachFile);
        return safAttachFile;
    }

    /**
     * she 문서 수정
     * 
     * @param safAttachFile
     *            she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    public int updateSheDocu(SafAttachFile safAttachFile) throws Exception {
        safAttachFileMapper.updateSheDocu(safAttachFile);
        return safAttachFile.getSheDocuNo();
    }

    /**
     * she 문서 생성
     * 
     * @param safAttachFile
     *            she 문서정보
     * @return she 문서번호
     * @throws Exception
     */
    public int createBeforeFile(int newTaskKey, List<SafAttachFile> safAttachFiles) throws Exception {
        for (SafAttachFile safAttachFile : safAttachFiles) {
            safAttachFileMapper.createBeforeFile(newTaskKey, safAttachFile.getFileNo());
        }

        return 0;
    }
}
