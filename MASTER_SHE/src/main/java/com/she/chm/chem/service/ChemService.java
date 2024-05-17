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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.mapper.ChemMapper;
import com.she.chm.model.Chem;
import com.she.chm.model.ChemicalRegulItmChemVal;
import com.she.security.auth.IAuthenticationFacade;

@Service
public class ChemService {
    @Autowired
    private ChemMapper chemMapper;

    @Autowired
    private IAuthenticationFacade iAuthenticationFacade;

    /**
     * 화학물질 조회
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChems(String search, int[] regulItmNos, String useYn, int chemprodNo)
            throws Exception {
        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        String regulItmNoStr = Arrays.toString(regulItmNos).replaceAll("[^0-9^, ]", "");

        return chemMapper.getChems(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn, chemprodNo, regulItmNoStr);
    }

    /**
     * 화학물질 조회
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemsPage(String search, int[] regulItmNos, String useYn, int chemprodNo,
            Integer pageNumber, Integer pageSize, String orderByExpression) throws Exception {
        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        String regulItmNoStr = Arrays.toString(regulItmNos).replaceAll("[^0-9^, ]", "");

        return chemMapper.getChemsPage(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn, chemprodNo,
                pageNumber, pageSize, orderByExpression, regulItmNoStr);
    }

    /**
     * 화학물질 조회
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @return 화학물질 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getChemsPageTotal(String search, int[] regulItmNos, String useYn,
            int chemprodNo, String orderByExpression) throws Exception {
        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        String regulItmNoStr = Arrays.toString(regulItmNos).replaceAll("[^0-9^, ]", "");

        return chemMapper.getChemsPageTotal(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn,
                chemprodNo, orderByExpression, regulItmNoStr);
    }

    /**
     * 화학물질 조회 페이징처리를 위한 총 건수 조회
     *
     * @param search
     *            검색어 (화학물질명 및 CAS NO.)
     * @param regulItmNos
     *            규제항목
     * @param useYn
     *            사용여부
     * @return 화학물질 목록
     * @throws Exception
     */
    public int getChemsTotalSize(String search, int[] regulItmNos, String useYn, int chemprodNo) throws Exception {

        // 동적으로 규제항목을 집어넣기 위해 사용가능한 규제항목들을 가지고 온다
        List<String> regulItmNo = chemMapper.getRegulItmNos();

        String allRegulItmNos = regulItmNo.get(0);
        // select 할 규제항목을 동적으로 isnull 된 값들을 가져온다.
        String allSelectRegulItmNos = regulItmNo.get(1);

        String regulItmNoStr = Arrays.toString(regulItmNos).replaceAll("[^0-9^, ]", "");

        return chemMapper.getChemsTotalSize(search, regulItmNos, allRegulItmNos, allSelectRegulItmNos, useYn,
                chemprodNo, regulItmNoStr);
    }

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
    public List<Chem> getChemNoReguls(String casNo, String chemNmKr, String chemNmEn) throws Exception {
        return chemMapper.getChemNoReguls(casNo, chemNmKr, chemNmEn);
    }

    /**
     * 화학물질 조회 (법규 제외) 페이징
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
    public List<Chem> getChemNoRegulsPage(String casNo, String chemNmKr, String chemNmEn, Integer pageNumber,
            Integer pageSize, String orderByExpression) throws Exception {
        return chemMapper.getChemNoRegulsPage(casNo, chemNmKr, chemNmEn, pageNumber, pageSize, orderByExpression);
    }

    /**
     * 화학물질 조회 (법규 제외) 페이징 총건수 조회
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
    public int getChemNoRegulsTotalSize(String casNo, String chemNmKr, String chemNmEn) throws Exception {
        return chemMapper.getChemNoRegulsTotalSize(casNo, chemNmKr, chemNmEn);
    }

    /**
     * 화학물질 상세 조회
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질
     * @throws Exception
     */
    public Chem getChem(int chemNo, DefaultParam defaultParam) throws Exception {
        Chem chem = this.chemMapper.getChem(chemNo);
        chem.setChemicalRegulItmChemVals(this.chemMapper.getChemicalRegulItmChemVals(chemNo, defaultParam));
        return chem;
    }

    /**
     * 화학물질 상세 조회 모든 규제를 포함한 정보조회 처리
     *
     * @param chemNo
     *            화학물질 번호
     * @return 화학물질
     * @throws Exception
     */
    public Chem getChemUnion(int chemNo, DefaultParam defaultParam) throws Exception {
        if (chemNo == 0) {
            Chem chem = new Chem();
            chem.setChemicalRegulItmChemVals(this.chemMapper.getChemicalRegulItmChemValsUnion(chemNo, defaultParam));
            return chem;
        } else {
            Chem chem = this.chemMapper.getChem(chemNo);
            chem.setChemicalRegulItmChemVals(this.chemMapper.getChemicalRegulItmChemValsUnion(chemNo, defaultParam));
            return chem;
        }
    }

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
    public List<HashMap<String, Object>> checkChem(String casNo, String chemNmKr, String chemNmEn, int chemNo)
            throws Exception {
        return this.chemMapper.checkChem(casNo, chemNmKr, chemNmEn, chemNo);
    }

    /**
     * 화학물질 신규등록
     *
     * @param chem
     *            화학물질
     * @return 화학물질 번호
     * @throws Exception
     */
    @Transactional
    public int createChem(Chem chem) throws Exception {
        chem.setCreateUserId(iAuthenticationFacade.getUserName(""));
        chem.setCreateUserNm(iAuthenticationFacade.getDispName());

        for (ChemicalRegulItmChemVal o : chem.getChemicalRegulItmChemVals()) {
            o.setCreateUserId(iAuthenticationFacade.getUserName(""));
            o.setCreateUserNm(iAuthenticationFacade.getDispName());
        }

        // 화학물질 등록
        this.chemMapper.createChem(chem);

        saveChemicalRegulItmChemVal(chem);

        return chem.getChemNo();
    }

    /**
     * 화학물질 수정
     *
     * @param chem
     *            화학물질
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChem(Chem chem) throws Exception {

        chem.setUpdateUserId(iAuthenticationFacade.getUserName(""));
        chem.setUpdateUserNm(iAuthenticationFacade.getDispName());

        for (ChemicalRegulItmChemVal o : chem.getChemicalRegulItmChemVals()) {
            o.setCreateUserId(iAuthenticationFacade.getUserName(""));
            o.setCreateUserNm(iAuthenticationFacade.getDispName());
            o.setUpdateUserId(iAuthenticationFacade.getUserName(""));
            o.setUpdateUserNm(iAuthenticationFacade.getDispName());
        }

        int resultNo = 0;
        resultNo += this.chemMapper.updateChem(chem);

        saveChemicalRegulItmChemVal(chem);

        return resultNo;
    }

    public void saveChemicalRegulItmChemVal(Chem chem) throws Exception {
        // 화학물질no에 따른 규제법규별 항목값 삭제
        this.chemMapper.deleteChemicalRegulItmChemVal(chem.getChemNo());

        // 화학물질no에 따른 규제법규별 항목값 신규등록
        if (chem.getChemicalRegulItmChemVals() != null && chem.getChemicalRegulItmChemVals().size() != 0) {
            for (ChemicalRegulItmChemVal chemicalRegulItmChemVal : chem.getChemicalRegulItmChemVals()) {
                chemicalRegulItmChemVal.setChemNo(chem.getChemNo());
                this.chemMapper.createChemicalRegulItmChemVal(chemicalRegulItmChemVal);
            }
        }
    }

    /**
     * 화학물질 규제법규별 항목값 상세 조회
     *
     * @param regulItmChemValNo
     *            항목값 번호
     * @return 화학물질 규제법규별 항목값
     * @throws Exception
     */
    public ChemicalRegulItmChemVal getChemicalRegulItmChemVal(int regulItmChemValNo, DefaultParam defaultParam) throws Exception {
        return this.chemMapper.getChemicalRegulItmChemVal(regulItmChemValNo, defaultParam);
    }

}
