package com.she.common.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 첨부 파일 정보
 */
@Data
public class SafAttachFile extends AttachFileGrp {
    private int sheDocuNo;
    private int docuGrpNo;
    private int fileNo;
    private String docuClassCd;
    private String docuKindCd;
    private String docuKindNm;
    private String plantCd;
    private String plantNm;
    private String deptCd;
    private String docuTitle;
    private int revNum;
    private String revYmd;
    private int enfYmd;
    private String docuContents;
    private String revContents;
    private String useYn;
    private String delYn;
    private String updateUserId;
    private String updateDt;
    private int fileCnt;
    private String refTableId;
    private int key1;
    private String processCd;
    private String processNm;
    private List<SafAttachFile> selectRow;
    private String writerUserNm;
    private String writerDt;
}
