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
package com.she.chm.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalPermitCls;
import com.she.chm.model.ChemicalRegulItem;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalPermitClsMapper")
public interface ChemicalPermitClsMapper {

    /**
     * 인허가신고서 조회
     * 
     * @param permitKindCd
     *            인허가구분
     * @param permitClsNm
     *            인허가신고명
     * @param useYn
     *            사용여부
     * @return 인허가신고서 목록
     * @throws Exception
     */
    public List<ChemicalPermitCls> getChemicalPermitClses(@Param("permitKindCd") String permitKindCd,
            @Param("permitClsNm") String permitClsNm, @Param("useYn") String useYn, @Param("plantCd") String plantCd)
            throws Exception;

    /**
     * 인허가신고서 규제정보제외
     * 
     * @param permitKindCd
     * @param permitClsNm
     * @param useYn
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<ChemicalPermitCls> getChemicalPermitClsList(@Param("permitKindCd") String permitKindCd,
            @Param("permitClsNm") String permitClsNm, @Param("useYn") String useYn, @Param("plantCd") String plantCd)
            throws Exception;

    /**
     * 인허가신고서 상세 조회
     * 
     * @param regulItmNo
     *            인허가신고서번호
     * @return 인허가신고서
     * @throws Exception
     */
    public ChemicalPermitCls getChemicalPermitCls(@Param("permitClsNo") int permitClsNo) throws Exception;

    /**
     * 인허가신고서 신규등록
     * 
     * @param chemicalPermitCls
     *            인허가신고서
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalPermitCls(ChemicalPermitCls chemicalPermitCls) throws Exception;

    /**
     * 인허가신고서 수정
     * 
     * @param chemicalPermitCls
     *            인허가신고서
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalPermitCls(ChemicalPermitCls chemicalPermitCls) throws Exception;

    /**
     * 인허가신고서명 체크
     * 
     * @param permitClsNm
     *            인허가신고서명
     * @param permitClsCd
     *            인허가신고서코드
     * @param permitClsNo
     *            인허가신고서번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalPermitCls(@Param("permitClsNm") String permitClsNm,
            @Param("permitClsCd") String permitClsCd, @Param("permitClsNo") int permitClsNo) throws Exception;

    /**
     * 인허가신고 규제사항 체크
     * 
     * @param permitClsNo
     *            인허가신고서번호
     * @return 인허가신고 규제사항 목록
     * @throws Exception
     */
    public List<ChemicalRegulItem> getChemicalPermitReguls(@Param("permitClsNo") int permitClsNo) throws Exception;

    /**
     * 인허가신고 규제사항 신규등록
     * 
     * @param permitClsNo
     *            인허가신고서
     * @param regulItmNo
     *            규제번호
     * @param createUserId
     *            생성자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalPermitReguls(@Param("permitClsNo") int permitClsNo, @Param("regulItmNo") int regulItmNo,
            @Param("createUserId") String createUserId) throws Exception;

    /**
     * 인허가신고 규제사항 삭제
     * 
     * @param chemicalPermitCls
     *            인허가신고서
     * @return 등록 행 수
     * @throws Exception
     */
    public int deleteChemicalPermitReguls(@Param("permitClsNo") int permitClsNo) throws Exception;
}
