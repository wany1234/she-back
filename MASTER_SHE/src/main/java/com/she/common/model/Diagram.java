package com.she.common.model;

import java.util.List;

import lombok.Data;

/**
 * 첨부 파일 정보
 */
@Data
public class Diagram extends AttachFileGrp {

    private int diagramSeq;
    private String diagramNo;
    private int diagGrpNo;
    private int diagKindNo;
    private String diagClassCd;
    private String diagKindNm;
    private String plantCd;
    private String plantNm;
    private String deptCd;
    private String processCd;
    private String processNm;
    private String diagTitle;
    private float revNum;
    private String revYmd;
    private String diagContents;
    private String diagSysNum;
    private String diagSysUrl;
    private String revContents;
    private String useYn;
    private String delYn;
    private String createUserId;
    private String createDt;
    private String updateUserId;
    private String updateDt;
    private String fileOrgNm;
    private String fileDownPath;
    private int key1;
    private List<SafAttachFile> selectRow;
    private int safChngNo;
    private String chngNum;
    private String writerUserNm;
    private String writerDt;
}
