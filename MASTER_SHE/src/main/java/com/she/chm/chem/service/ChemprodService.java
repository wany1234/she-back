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
package com.she.chm.chem.service;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.mapper.ChemMapper;
import com.she.chm.chem.mapper.ChemprodMapper;
import com.she.chm.model.ChemicalRegulItem;
import com.she.chm.model.ChemicalRegulItmChemprodVal;
import com.she.chm.model.Chemprod;
import com.she.chm.model.ChemprodChem;
import com.she.chm.model.ChemprodChemRegul;

@Service
public class ChemprodService {
    @Autowired
    private ChemprodMapper chemprodMapper;

    @Autowired
    private ChemMapper chemMapper;

    /**
     * 취급물질 조회
     *
     * @param search
     *            검색어 (취급물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @return 취급물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemprods(String search, int[] regulItmNos, String useYn, String plantCd, String airPolYn, String wtrPolYn, String licensingYn, String dgrYn, int chemprodRegulItmNo, DefaultParam defaultParam) throws Exception {
        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        String regulItmNoStr = String.join(",", regulItmNo);

        return chemprodMapper.getChemprods(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, regulItmNoStr, defaultParam);
    }

    /**
     * 페이징처리 조회
     *
     * @param search
     * @param regulItmNos
     * @param useYn
     * @param plantCd
     * @param dgrYn
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemprodsPage(String search, int[] regulItmNos, String useYn, String plantCd, String deptCd, String safFacilityCd, String airPolYn, String wtrPolYn, String licensingYn, String dgrYn, int chemprodRegulItmNo, Integer pageNumber, Integer pageSize, String orderByExpression, DefaultParam defaultParam)
            throws Exception {
        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        String regulItmNoStr = Arrays.toString(regulItmNos).replaceAll("[^0-9^, ]", "");

        return chemprodMapper.getChemprodsPage(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn, plantCd, deptCd, safFacilityCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, pageNumber, pageSize, orderByExpression, regulItmNoStr, defaultParam);
    }

    /**
     * 조회
     *
     * @param search
     * @param regulItmNos
     * @param useYn
     * @param plantCd
     * @param dgrYn
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemprodsPageTotal(String search, int[] regulItmNos, String useYn, String plantCd, String deptCd, String safFacilityCd, String airPolYn, String wtrPolYn, String licensingYn, String dgrYn, int chemprodRegulItmNo, String orderByExpression) throws Exception {
        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        return chemprodMapper.getChemprodsPageTotal(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn, plantCd, deptCd, safFacilityCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, orderByExpression);
    }

    /**
     * 페이징 처리시 총 갯수 조회
     *
     * @param search
     * @param regulItmNos
     * @param useYn
     * @param plantCd
     * @param dgrYn
     * @return
     * @throws Exception
     */
    public int getChemprodsTotalSize(String search, int[] regulItmNos, String useYn, String plantCd, String airPolYn, String wtrPolYn, String licensingYn, String dgrYn, int chemprodRegulItmNo) throws Exception {
        return chemprodMapper.getChemprodsTotalSize(search, regulItmNos, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo);
    }

    /**
     * 취급물질 조회 (법규 제외)
     *
     * @param chemprodNm
     *            취급물질명
     * @param useYn
     *            사용여부
     * @return 취급물질 목록 (법규 제외)
     * @throws Exception
     */
    public List<Chemprod> getChemprodNoReguls(String search, String useYn, String plantCd, String airPolYn, String wtrPolYn, String licensingYn, String dgrYn, int chemprodRegulItmNo, DefaultParam defaultParam) throws Exception {
        return chemprodMapper.getChemprodNoReguls(search, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, defaultParam);
    }

    /**
     * 취급물질 조회 (법규 제외) 페이징
     *
     * @param chemprodNm
     * @param useYn
     * @param plantCd
     * @param dgrYn
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    public List<Chemprod> getChemprodNoRegulsPage(String search, String useYn, String plantCd, String airPolYn, String wtrPolYn, String licensingYn, String dgrYn, int chemprodRegulItmNo, Integer pageNumber, Integer pageSize, String orderByExpression, DefaultParam defaultParam) throws Exception {
        return chemprodMapper.getChemprodNoRegulsPage(search, useYn, plantCd, airPolYn, wtrPolYn, licensingYn, dgrYn, chemprodRegulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);
    }

    /**
     * 취급물질 상세 조회
     *
     * @param chemprodNo
     *            취급물질 번호
     * @return 취급물질
     * @throws Exception
     */
    public Chemprod getChemprod(int chemprodNo, DefaultParam defaultParam) throws Exception {
        Chemprod chemprod = this.chemprodMapper.getChemprod(chemprodNo, defaultParam);
        return chemprod;
    }

    /**
     * 중복검사
     *
     * @param chemProdNmKr
     * @param chemProdNmEn
     * @param chemProdNo
     * @return 중복 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkChemprod(String sapMatCd, String vendorCd, String makerCd, String plantCd, int chemProdNo) throws Exception {
        return this.chemprodMapper.checkChemprod(sapMatCd, vendorCd, makerCd, plantCd, chemProdNo);
    }

    /**
     * 취급물질 신규등록
     *
     * @param chemprod
     *            취급물질
     * @return 취급물질 번호
     * @throws Exception
     */
    @Transactional
    public int createChemprod(Chemprod chemprod) throws Exception {
        // 취급물질 등록
        // this.chemprodMapper.createChemprod(chemprod);
        // return chemprod.getChemProdNo();

        int resultNo = 0;
        resultNo += chemprodMapper.createChemprod(chemprod);
        chemprod.getChemprodChemRegul().setChemProdNo(chemprod.getChemProdNo());
        resultNo += createChemprodChem(chemprod.getChemprodChemRegul());
        return resultNo > 0 ? chemprod.getChemProdNo() : 0;
    }

    /**
     * 취급물질 수정
     *
     * @param chemprod
     *            취급물질
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemprod(Chemprod chemprod) throws Exception {
        this.chemprodMapper.updateChemprod(chemprod);
        createChemprodChem(chemprod.getChemprodChemRegul());
        return chemprod.getChemProdNo();
    }

    /**
     * 취급물질 규제법규별 항목값 상세 조회
     *
     * @param regulItmChemprodValNo
     *            항목값 번호
     * @return 취급물질 규제법규별 항목값
     * @throws Exception
     */
    public ChemicalRegulItmChemprodVal getChemicalRegulItmChemprodVal(int regulItmChemprodValNo, DefaultParam defaultParam) throws Exception {
        return this.chemprodMapper.getChemicalRegulItmChemprodVal(regulItmChemprodValNo, defaultParam);
    }

    /**
     * 취급물질 구성성분 조회
     *
     * @param chemProdNo
     * @return 취급물질 구성성분 목록
     * @throws Exception
     */
    public List<ChemprodChem> getChemprodChems(int chemProdNo, String searchFlag) throws Exception {
        return this.chemprodMapper.getChemprodChems(chemProdNo, searchFlag);
    }

    /**
     * 취급물질 신규등록
     *
     * @param chemprod
     *            취급물질
     * @return 취급물질 번호
     * @throws Exception
     */
    @Transactional
    public int createChemprodChem(ChemprodChemRegul chemprodChemRegul) throws Exception {
        int resultNo = 0;
        this.chemprodMapper.deleteChemprodRegul(chemprodChemRegul.getChemProdNo());
        if (chemprodChemRegul.getRegulItmNos() != null && chemprodChemRegul.getRegulItmNos().size() != 0) {
            for (int regulItmNo : chemprodChemRegul.getRegulItmNos()) {
                ChemicalRegulItmChemprodVal chemicalRegulItmChemprodVal = new ChemicalRegulItmChemprodVal();
                // 취급물질 규제 등록
                chemicalRegulItmChemprodVal.setRegulItmNo(regulItmNo);
                chemicalRegulItmChemprodVal.setChemProdNo(chemprodChemRegul.getChemProdNo());
                chemicalRegulItmChemprodVal.setCreateUserId(chemprodChemRegul.getCreateUserId());
                resultNo += this.chemprodMapper.createChemprodRegul(chemicalRegulItmChemprodVal);
            }
        }

        this.chemprodMapper.deleteChemprodChem(chemprodChemRegul.getChemProdNo());
        if (chemprodChemRegul.getChemprodChems() != null && chemprodChemRegul.getChemprodChems().size() != 0) {
            for (ChemprodChem chemprodChem : chemprodChemRegul.getChemprodChems()) {
                // 구성성분 등록
                chemprodChem.setCreateUserId(chemprodChemRegul.getCreateUserId());
                chemprodChem.setChemProdNo(chemprodChemRegul.getChemProdNo());
                resultNo += this.chemprodMapper.createChemprodChem(chemprodChem);
            }
        }

        return resultNo;
    }

    /**
     * 취급물질 규제항목 조회
     *
     * @param chemProdNo
     * @return 취급물질 규제항목 목록
     * @throws Exception
     */
    public List<ChemicalRegulItem> getChemprodReguls(int chemprodNo, DefaultParam defaultParam) throws Exception {
        return this.chemprodMapper.getChemprodReguls(chemprodNo, defaultParam);
    }

}
