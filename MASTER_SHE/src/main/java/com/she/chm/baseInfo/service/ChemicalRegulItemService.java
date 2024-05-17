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

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.chm.baseInfo.mapper.ChemicalRegulItemMapper;
import com.she.chm.model.ChemicalRegulItem;

@Service
public class ChemicalRegulItemService {
    @Autowired
    private ChemicalRegulItemMapper chemicalRegulItemMapper;

    /**
     * 규제항목 조회
     *
     * @param regulLawCd
     *            규제법규
     * @param regulItmNm
     *            규제항목명
     * @param useYn
     *            사용여부
     * @return 규제항목 목록
     * @throws Exception
     */
    public List<ChemicalRegulItem> getChemicalRegulItems(String regulLawCd, String regulItmNm, String useYn, DefaultParam defaultParam)
            throws Exception {
        return chemicalRegulItemMapper.getChemicalRegulItems(regulLawCd, regulItmNm, useYn, defaultParam);
    }

    /**
     * 규제항목 상세 조회
     *
     * @param regulItmNo
     *            규제항목번호
     * @return 규제항목
     * @throws Exception
     */
    public ChemicalRegulItem getChemicalRegulItem(int regulItmNo, DefaultParam defaultParam) throws Exception {
        return this.chemicalRegulItemMapper.getChemicalRegulItem(regulItmNo, defaultParam);
    }

    /**
     * 규제항목 신규등록
     *
     * @param chemicalRegulItem
     *            규제항목
     * @return 규제항목번호
     * @throws Exception
     */
    @Transactional
    public int createChemicalRegulItem(ChemicalRegulItem chemicalRegulItem) throws Exception {
        this.chemicalRegulItemMapper.createChemicalRegulItem(chemicalRegulItem);

        return chemicalRegulItem.getRegulItmNo();
    }

    /**
     * 규제항목 수정
     *
     * @param chemicalRegulItem
     *            규제항목
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalRegulItem(ChemicalRegulItem chemicalRegulItem) throws Exception {
        return this.chemicalRegulItemMapper.updateChemicalRegulItem(chemicalRegulItem);
    }

    /**
     * 규제항목명 체크
     *
     * @param regulItmNm
     *            규제항목명
     * @param regulItmNo
     *            규제항목번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalRegulItem(String regulItmNm, int regulItmNo) throws Exception {
        return chemicalRegulItemMapper.getCheckChemicalRegulItem(regulItmNm, regulItmNo);
    }

}
