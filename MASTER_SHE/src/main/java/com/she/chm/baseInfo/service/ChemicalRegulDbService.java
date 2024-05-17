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
package com.she.chm.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.baseInfo.mapper.ChemicalRegulDbMapper;
import com.she.chm.model.ChemicalRegulDb;
import com.she.chm.model.ChemicalRegulDbHist;

@Service
public class ChemicalRegulDbService {
    @Autowired
    private ChemicalRegulDbMapper chemicalRegulDbMapper;

    @Autowired
    private ChemicalRegulDbHistService chemicalRegulDbHistService;

    /**
     * 규제DB업로드건별 규제정보 조회
     *
     * @param chmRegulDbCd
     *            : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDb> getChemicalRegulDbs(String chmRegulDbCd) throws Exception {
        return chemicalRegulDbMapper.getChemicalRegulDbs(chmRegulDbCd);
    }
    
    /**
     * 규제DB업로드건별 규제정보 페이징처리조회
     *
     * @param chmRegulDbCd
     *            : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public List<ChemicalRegulDb> getChemicalRegulDbsPage(String chmRegulDbCd, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        return chemicalRegulDbMapper.getChemicalRegulDbsPage(chmRegulDbCd, pageNumber, pageSize, orderByExpression);
    }
    
    /**
     * 규제DB업로드건별 규제정보 페이징 처리시 총 갯수 조회
     *
     * @param chmRegulDbCd
     *            : 규제DB업로드코드
     * @return
     * @throws Exception
     */
    public int getChemicalRegulDbsTotalSize(String chmRegulDbCd) throws Exception {
        return chemicalRegulDbMapper.getChemicalRegulDbsTotalSize(chmRegulDbCd);
    }

    /**
     * 규제정보등록
     *
     * @param chemicalRegulDb
     * @return
     * @throws Exception
     */
    public int createChemicalRegulDb(ChemicalRegulDb chemicalRegulDb) throws Exception {
        return chemicalRegulDbMapper.createChemicalRegulDb(chemicalRegulDb);
    }

    /**
     * 적용프로시저 실행
     *
     * @param chmRegulDbCd
     * @return
     * @throws Exception
     */
    @Transactional
    public ChemicalRegulDbHist executeIfRegulDbToChemUpdate(String chmRegulDbCd) throws Exception {
        ChemicalRegulDbHist chemicalRegulDbHist = chemicalRegulDbHistService.getChemicalRegulDbHist(chmRegulDbCd);
        return chemicalRegulDbHistService.applyChemicalRegulDbHist(chemicalRegulDbHist);
    }

}
