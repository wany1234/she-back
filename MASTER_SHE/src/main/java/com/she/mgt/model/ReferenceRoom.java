package com.she.mgt.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReferenceRoom {

    private int dataBoardNo; // 자료실번호

    private int dataBoardGrpNo; // 자료실그룹번호

    private String kindCd; // 분류코드

    private String roomTp;

    private String title; // 제목

    private String revNum; // 제개정차수

    private String revYmd; // 제개정일자

    private String contents; // 내용

    private String revContents; // 개정내용

    private String useYn; // 사용여부

    private String delYn; // 삭제여부

    private String createUserId; // 등록일

    private String createUserNm;

    private String createDt; // 등록자ID

    private String updateUserId; // 최종수정일

    private String updateUserNm;

    private String updateDt; // 최종수정자ID

    private String plantCd; // 사업장코드

    private String plantNm; // 사업장 명

    private String deptCd;

    private String deptNm;

    private String plantNms;

    private String plantCds;

    private List<String> plantType;

    private String writerUserNm;

    private String writerDt;

    private String writerDeptNm;

}
