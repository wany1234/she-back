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

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.Chem;
import com.she.chm.model.ChemicalRegulItmChemVal;

@Mapper
@Repository("com.she.chm.chem.mapper.ChemMapper")
public interface ChemMapper {

    /**
     * 화학물질 조회 페이징
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @param chemprodNo
     *            취급물질no
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemsPage(@Param("search") String search,
            @Param("regulItmNos") int[] regulItmNos, @Param("allRegulItmNos") String allRegulItmNos,
            @Param("allSelectRegulItmNos") String allSelectRegulItmNos, @Param("useYn") String useYn,
            @Param("chemprodNo") int chemprodNo, @Param("pageNumber") Integer pageNumber,
            @Param("pageSize") Integer pageSize, @Param("orderByExpression") String orderByExpression, @Param("regulItmNoStr") String regulItmNoStr) throws Exception;

    /**
     * 화학물질 조회 페이징
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @param chemprodNo
     *            취급물질no
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemsPageTotal(@Param("search") String search,
            @Param("regulItmNos") int[] regulItmNos, @Param("allRegulItmNos") String allRegulItmNos,
            @Param("allSelectRegulItmNos") String allSelectRegulItmNos, @Param("useYn") String useYn,
            @Param("chemprodNo") int chemprodNo, @Param("orderByExpression") String orderByExpression, @Param("regulItmNoStr") String regulItmNoStr) throws Exception;

    /**
     * 화학물질 조회
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @param chemprodNo
     *            취급물질no
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChems(@Param("search") String search,
            @Param("regulItmNos") int[] regulItmNos, @Param("allRegulItmNos") String allRegulItmNos,
            @Param("allSelectRegulItmNos") String allSelectRegulItmNos, @Param("useYn") String useYn,
            @Param("chemprodNo") int chemprodNo, @Param("regulItmNoStr") String regulItmNoStr) throws Exception;

    /**
     * 화학물질 조회 페이징 처리를 위한 총 건수 조회
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @param chemprodNo
     *            취급물질no
     * @return 화학물질 목록
     * @throws Exception
     */
    public int getChemsTotalSize(@Param("search") String search, @Param("regulItmNos") int[] regulItmNos,
            @Param("allRegulItmNos") String allRegulItmNos, @Param("allSelectRegulItmNos") String allSelectRegulItmNos,
            @Param("useYn") String useYn, @Param("chemprodNo") int chemprodNo, @Param("regulItmNoStr") String regulItmNoStr) throws Exception;

    /**
     * 화학물질 조회 (법규 제외)
     *
     * @param casNo
     *            CAS NO.
     * @param chemNmKr
     *            화학물질명
     * @param chemNmEn
     *            화학물질명
     * @return 화학물질 목록 (법규 제외)
     * @throws Exception
     */
    public List<Chem> getChemNoReguls(@Param("casNo") String casNo, @Param("chemNmKr") String chemNmKr,
            @Param("chemNmEn") String chemNmEn) throws Exception;

    /**
     * 화학물질 조회 (법규 제외) 페이지
     *
     * @param casNo
     *            CAS NO.
     * @param chemNmKr
     *            화학물질명
     * @param chemNmEn
     *            화학물질명
     * @return 화학물질 목록 (법규 제외)
     * @throws Exception
     */
    public List<Chem> getChemNoRegulsPage(@Param("casNo") String casNo, @Param("chemNmKr") String chemNmKr,
            @Param("chemNmEn") String chemNmEn, @Param("pageNumber") Integer pageNumber,
            @Param("pageSize") Integer pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;

    /**
     * 화학물질 조회 (법규 제외) 페이지 총 건수 조회
     *
     * @param casNo
     *            CAS NO.
     * @param chemNmKr
     *            화학물질명
     * @param chemNmEn
     *            화학물질명
     * @return 화학물질 목록 (법규 제외)
     * @throws Exception
     */
    public int getChemNoRegulsTotalSize(@Param("casNo") String casNo, @Param("chemNmKr") String chemNmKr,
            @Param("chemNmEn") String chemNmEn) throws Exception;

    /**
     * 화학물질 상세 조회
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질
     * @throws Exception
     */
    public Chem getChem(@Param("chemNo") int chemNo) throws Exception;

    /**
     * 화학물질 규제법규별 항목값 상세 조회
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질 규제법규별 항목값
     * @throws Exception
     */
    public List<ChemicalRegulItmChemVal> getChemicalRegulItmChemVals(@Param("chemNo") int chemNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 화학물질 규제법규별 항목값 상세 조회 모든 규제를 포함하여
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질 규제법규별 항목값
     * @throws Exception
     */
    public List<ChemicalRegulItmChemVal> getChemicalRegulItmChemValsUnion(@Param("chemNo") int chemNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
     *
     * @return 규제항목 목록
     * @throws Exception
     */
    public List<String> getRegulItmNos() throws Exception;

    /**
     * casNo 중복검사
     *
     * @param casNo
     * @param chemNmKr
     * @param chemNmEn
     * @param chemNo
     * @return 중복 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkChem(@Param("casNo") String casNo, @Param("chemNmKr") String chemNmKr,
            @Param("chemNmEn") String chemNmEn, @Param("chemNo") int chemNo) throws Exception;

    /**
     * 화학물질 신규등록
     *
     * @param chem
     *            화학물질
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChem(Chem chem) throws Exception;

    /**
     * 화학물질 수정
     *
     * @param chem
     *            화학물질
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChem(Chem chem) throws Exception;

    /**
     * 화학물질 규제법규별 항목값 신규등록
     *
     * @param chemicalRegulItmChemVal
     *            화학물질 규제법규별 항목값
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalRegulItmChemVal(ChemicalRegulItmChemVal chemicalRegulItmChemVal) throws Exception;

    /**
     * 화학물질 규제법규별 항목값 삭제
     *
     * @param chemNo
     * @return 등록 행 수
     * @throws Exception
     */
    public int deleteChemicalRegulItmChemVal(@Param("chemNo") int chemNo) throws Exception;

    /**
     * 화학물질 규제법규별 항목값 상세 조회
     *
     * @param regulItmChemValNo
     *            항목값 번호
     * @return 화학물질 규제법규별 항목값
     * @throws Exception
     */
    public ChemicalRegulItmChemVal getChemicalRegulItmChemVal(@Param("regulItmChemValNo") int regulItmChemValNo, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 화학물질 조회
     *
     * @param casNo
     *            CAS No
     * @param chemNmKr
     *            화학물질명(KOR)
     * @param chemNmEn
     *            화학물질명(ENG)
     * @param chemProdNo
     *            취급물질 번호
     * @param useYn
     *            사용여부
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<Chem> getChemicals(@Param("casNo") String casNo, @Param("chemNmKr") String chemNmKr,
            @Param("chemNmEn") String chemNmEn, @Param("useYn") String useYn, @Param("chemProdNo") int chemProdNo)
            throws Exception;

}
