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

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.chm.baseInfo.mapper.ChemicalUnitMapper;
import com.she.chm.model.ChemicalUnit;

@Service
public class ChemicalUnitService {
    @Autowired
    private ChemicalUnitMapper chemicalUnitMapper;

    /**
     * 물질단위 조회
     *
     * @param unitNm
     *            물질단위명
     * @param useYn
     *            사용여부
     * @return 물질단위 목록
     * @throws Exception
     */
    public List<ChemicalUnit> getChemicalUnits(String unitNm, String useYn) throws Exception {
        return chemicalUnitMapper.getChemicalUnits(unitNm, useYn);
    }

    /**
     * 물질단위 상세 조회
     *
     * @param unitNo
     *            물질단위번호
     * @return 물질단위
     * @throws Exception
     */
    public ChemicalUnit getChemicalUnit(int unitNo) throws Exception {
        return this.chemicalUnitMapper.getChemicalUnit(unitNo);
    }

    /**
     * 물질단위 신규등록
     *
     * @param chemicalUnit
     *            물질단위
     * @return 물질단위코드
     * @throws Exception
     */
    @Transactional
    public int createChemicalUnit(ChemicalUnit chemicalUnit) throws Exception {
        this.chemicalUnitMapper.createChemicalUnit(chemicalUnit);

        return chemicalUnit.getUnitNo();
    }

    /**
     * 물질단위 수정
     *
     * @param chemicalUnit
     *            물질단위
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalUnit(ChemicalUnit chemicalUnit) throws Exception {
        return this.chemicalUnitMapper.updateChemicalUnit(chemicalUnit);
    }

    /**
     * 물질단위명 체크
     *
     * @param unitNm
     *            물질단위명
     * @param unitNo
     *            물질단위번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalUnit(String unitNm, int unitNo) throws Exception {
        return chemicalUnitMapper.getCheckChemicalUnit(unitNm, unitNo);
    }

}
