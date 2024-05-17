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

import com.she.baseInfo.model.SAPMAT;
import com.she.chm.model.ChemicalRegulItem;
import com.she.chm.model.ChemicalRegulItmChemprodVal;
import com.she.chm.model.Chemprod;
import com.she.chm.model.ChemprodChem;

@Mapper
@Repository("com.she.chm.chem.mapper.ChemprodMapper")
public interface ChemprodMapper {

    /**
     * 취급물질 조회
     *
     * @param search
     *            검색어 (취급물질명 및 성분명 및 성분 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @return 취급물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemprods(@Param("search") String search, @Param("regulItmNos") int[] regulItmNos, @Param("allRegulItmNos") String allRegulItmNos, @Param("allSelectRegulItmNos") String allSelectRegulItmNos, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("airPolYn") String airPolYn,
            @Param("wtrPolYn") String wtrPolYn, @Param("licensingYn") String licensingYn, @Param("dgrYn") String dgrYn, @Param("chemprodRegulItmNo") int chemprodRegulItmNo, @Param("regulItmNoStr") String regulItmNoStr, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급물질조회 페이징처리
     *
     * @param search
     * @param regulItmNos
     * @param allRegulItmNos
     * @param allSelectRegulItmNos
     * @param useYn
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemprodsPage(@Param("search") String search, @Param("regulItmNos") int[] regulItmNos, @Param("allRegulItmNos") String allRegulItmNos, @Param("allSelectRegulItmNos") String allSelectRegulItmNos, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd,
            @Param("safFacilityCd") String safFacilityCd, @Param("airPolYn") String airPolYn, @Param("wtrPolYn") String wtrPolYn, @Param("licensingYn") String licensingYn, @Param("dgrYn") String dgrYn, @Param("chemprodRegulItmNo") int chemprodRegulItmNo, @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize,
            @Param("orderByExpression") String orderByExpression, @Param("regulItmNoStr") String regulItmNoStr, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급물질조회 페이징처리
     *
     * @param search
     * @param regulItmNos
     * @param allRegulItmNos
     * @param allSelectRegulItmNos
     * @param useYn
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemprodsPageTotal(@Param("search") String search, @Param("regulItmNos") int[] regulItmNos, @Param("allRegulItmNos") String allRegulItmNos, @Param("allSelectRegulItmNos") String allSelectRegulItmNos, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd,
            @Param("safFacilityCd") String safFacilityCd, @Param("airPolYn") String airPolYn, @Param("wtrPolYn") String wtrPolYn, @Param("licensingYn") String licensingYn, @Param("dgrYn") String dgrYn, @Param("chemprodRegulItmNo") int chemprodRegulItmNo, @Param("orderByExpression") String orderByExpression) throws Exception;

    /**
     *
     * @param search
     * @param regulItmNos
     * @param allRegulItmNos
     * @param allSelectRegulItmNos
     * @param useYn
     * @return
     * @throws Exception
     */
    public int getChemprodsTotalSize(@Param("search") String search, @Param("regulItmNos") int[] regulItmNos, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("airPolYn") String airPolYn, @Param("wtrPolYn") String wtrPolYn, @Param("licensingYn") String licensingYn, @Param("dgrYn") String dgrYn,
            @Param("chemprodRegulItmNo") int chemprodRegulItmNo) throws Exception;

    /**
     * 취급물질 조회 (법규 제외)
     *
     * @param search
     *            검색조건
     * @param useYn
     *            사용여부
     * @return 취급물질 목록 (법규 제외)
     * @throws Exception
     */
    public List<Chemprod> getChemprodNoReguls(@Param("search") String search, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("airPolYn") String airPolYn, @Param("wtrPolYn") String wtrPolYn, @Param("licensingYn") String licensingYn, @Param("dgrYn") String dgrYn, @Param("chemprodRegulItmNo") int chemprodRegulItmNo,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급물질 조회 (법규 제외) 페이징처리
     *
     * @param search
     * @param useYn
     * @param plantCd
     * @param dgrYn
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<Chemprod> getChemprodNoRegulsPage(@Param("search") String search, @Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("airPolYn") String airPolYn, @Param("wtrPolYn") String wtrPolYn, @Param("licensingYn") String licensingYn, @Param("dgrYn") String dgrYn, @Param("chemprodRegulItmNo") int chemprodRegulItmNo,
            @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize, @Param("orderByExpression") String orderByExpression, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급물질 상세 조회
     *
     * @param chemprodNo
     *            취급물질 번호
     * @return 취급물질
     * @throws Exception
     */
    public Chemprod getChemprod(@Param("chemprodNo") int chemprodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급물질별 자재 조회
     *
     * @param chemprodNo
     *            취급물질 번호
     * @return 취급물질별 자재
     * @throws Exception
     */
    public List<SAPMAT> getSapMats(@Param("chemprodNo") int chemprodNo) throws Exception;

    /**
     * 중복검사
     *
     * @param chemProdNmKr
     * @param chemProdNmEn
     * @param chemProdNo
     * @return 중복 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkChemprod(@Param("sapMatCd") String sapMatCd, @Param("vendorCd") String vendorCd, @Param("makerCd") String makerCd, @Param("plantCd") String plantCd, @Param("chemProdNo") int chemProdNo) throws Exception;

    /**
     * 취급물질 신규등록
     *
     * @param chemprod
     *            취급물질
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemprod(Chemprod chemprod) throws Exception;

    /**
     * 취급물질 수정
     *
     * @param chemprod
     *            취급물질
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemprod(Chemprod chemprod) throws Exception;

    // /**
    // * 취급물질 자재 신규등록
    // * @param SAPMAT 자재
    // * @return 등록 행 수
    // * @throws Exception
    // */
    // public int createChemicalChemprodMAT(SAPMAT SAPMAT) throws Exception;
    //
    // /**
    // * 취급물질 자재 삭제
    // * @param chemprodNo
    // * @return 등록 행 수
    // * @throws Exception
    // */
    // public int deleteChemicalChemprodMAT(@Param("chemprodNo") int chemprodNo)
    // throws Exception;

    /**
     * 취급물질 규제법규별 항목값 상세 조회
     *
     * @param regulItmChemprodValNo
     *            항목값 번호
     * @return 취급물질 규제법규별 항목값
     * @throws Exception
     */
    public ChemicalRegulItmChemprodVal getChemicalRegulItmChemprodVal(@Param("regulItmChemprodValNo") int regulItmChemprodValNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급물질 구성성분 조회
     *
     * @param chemProdNo
     * @return 취급물질 구성성분 목록
     * @throws Exception
     */
    public List<ChemprodChem> getChemprodChems(@Param("chemProdNo") int chemProdNo, @Param("searchFlag") String searchFlag) throws Exception;

    /**
     * 취급물질 규제 신규등록
     *
     * @param chemicalRegulItmChemVal
     *            취급물질 규제법규별 항목값
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemprodRegul(ChemicalRegulItmChemprodVal chemicalRegulItmChemprodVal) throws Exception;

    /**
     * 취급물질 규제법규별 항목값 삭제
     *
     * @param chemprodNo
     * @return 등록 행 수
     * @throws Exception
     */
    public int deleteChemprodRegul(@Param("chemprodNo") int chemprodNo) throws Exception;

    /**
     * 구성성분 신규등록
     *
     * @param chemprodChem
     *            구성성분
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemprodChem(ChemprodChem chemprodChem) throws Exception;

    /**
     * 구성성분 삭제
     *
     * @param chemprodNo
     * @return 등록 행 수
     * @throws Exception
     */
    public int deleteChemprodChem(@Param("chemprodNo") int chemprodNo) throws Exception;

    /**
     * 취급물질 규제항목 조회
     *
     * @param chemProdNo
     * @return 취급물질 규제항목 목록
     * @throws Exception
     */
    public List<ChemicalRegulItem> getChemprodReguls(@Param("chemprodNo") int chemprodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 부서별 취급물질 구성성분 조회
     *
     * @param chemProdNo
     *            취급물질 번호
     * @param deptCd
     *            부서 코드
     * @param processCd
     *            공정 번호
     * @return 부서별 취급물질 구성성분 목록
     * @throws Exception
     */
    public List<ChemprodChem> getDeptChemprodChems(@Param("chemProdNo") int chemProdNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd) throws Exception;

    /**
     * 조건에 해당되는 취급자재번호조회
     *
     * @param sapMatCd
     * @param vendorCd
     * @param makerCd
     * @return
     * @throws Exception
     */
    public Integer getExistsChemprod(@Param("sapMatCd") String sapMatCd, @Param("vendorCd") String vendorCd, @Param("makerCd") String makerCd) throws Exception;

}
