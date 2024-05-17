/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.chm.chem.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.MSDS;
import com.she.chm.model.MSDSPicGraph;
import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.mgt.model.LicensingStatus;

@Mapper
@Repository("com.she.chm.chem.mapper.MSDSMapper")
public interface MSDSMapper {

    /**
     * MSDS 조회
     * 
     * @param search
     *            검색어 (취급물질명 및 업체)
     * @param delYn
     *            삭제여부
     * @param newYn
     *            최신본여부
     * @param chemProdNo
     *            취급물질번호
     * @param msdsRqstNo
     *            MSDS 그룹번호
     * @return MSDS 목록
     * @throws Exception
     */
    public List<MSDS> getMsdss(@Param("search") String search, @Param("delYn") String delYn, @Param("newYn") String newYn, @Param("plantCd") String plantCd, @Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("chemProdNo") int chemProdNo, @Param("msdsGrpNo") int msdsGrpNo, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * MSDS 상세 조회
     * 
     * @param msdsRqstNo
     *            MSDS 번호
     * @return MSDS
     * @throws Exception
     */
    public MSDS getMsds(@Param("msdsRqstNo") int msdsRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * MSDS 요약정보 - 그림문자 상세 조회
     * 
     * @param msdsRqstNo
     *            MSDS 번호
     * @return msdsPicGraphs
     * @throws Exception
     */
    public List<String> getMsdsPicGraphs(@Param("msdsRqstNo") int msdsRqstNo) throws Exception;

    /**
     * MSDS 요약정보 - 그림문자명 상세 조회
     * 
     * @param msdsRqstNo
     *            MSDS 번호
     * @return msdsPicGraphs
     * @throws Exception
     */
    public List<String> getMsdsPicGraphNms(@Param("msdsRqstNo") int msdsRqstNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 중복검사
     * 
     * @param makecpCd
     * @param vendorCd
     * @param chemProdNmKr
     * @param chemProdNmEn
     * @param msdsRqstNo
     * @param revNum
     * @param msdsGrpNo
     * @return 중복 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkMsds(@Param("makecpCd") String makecpCd, @Param("vendorCd") String vendorCd, @Param("chemProdNmKr") String chemProdNmKr, @Param("chemProdNmEn") String chemProdNmEn, @Param("msdsRqstNo") int msdsRqstNo, @Param("revNum") int revNum, @Param("msdsGrpNo") int msdsGrpNo) throws Exception;

    /**
     * MSDS 신규등록
     * 
     * @param msds
     * @return 등록 행 수
     * @throws Exception
     */
    public int createMsds(MSDS msds) throws Exception;

    /**
     * 개정을 위한 용도로 이전 버전의 경우 최신본여부를 N으로 바꾸어 준다.
     * 
     * @param msdsGrpNo
     * @return 등록 행 수
     * @throws Exception
     */
    public int updateNewMsds(@Param("msdsGrpNo") int msdsGrpNo) throws Exception;

    /**
     * MSDS 수정
     * 
     * @param msds
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMsds(MSDS msds) throws Exception;

    /**
     * MSDS 요약정보 - 그림문자 등록
     * 
     * @param MSDSPicGraph
     * @return 수정 행 수
     * @throws Exception
     */
    public int createMsdsPicGraphs(MSDSPicGraph msdsPicGraph) throws Exception;

    /**
     * MSDS 요약정보 - 그림문자 삭제
     * 
     * @param msdsRqstNo
     * @return 수정 행 수
     * @throws Exception
     */
    public int deleteMsdsPicGraphs(@Param("msdsRqstNo") int msdsRqstNo) throws Exception;

    public AttachFile searchFile(String fileNo) throws Exception;

    public int msdsDeleteLicensingStatus(MSDS msds) throws Exception;

    public int deleteLicensingStatus(@Param("msdsRqstNo") int msdsRqstNo);
}
