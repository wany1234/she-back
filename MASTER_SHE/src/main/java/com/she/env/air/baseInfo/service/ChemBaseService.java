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
package com.she.env.air.baseInfo.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.air.baseInfo.mapper.ChemBaseMapper;
import com.she.env.air.model.ChemBase;

/**
 * 대기 약품
 *
 */
@Service
public class ChemBaseService {
    @Autowired
    private ChemBaseMapper chemBaseMapper;

    /**
     * 대기약품 조회
     * 
     * @param useYn
     *            사용여부
     * @param plantCd
     *            사업장코드
     * @return 대기약품 목록
     * @throws Exception
     */
    public List<ChemBase> getChemBases(String useYn, String plantCd, String eairChemNm, DefaultParam defaultParam) throws Exception {
        return chemBaseMapper.getChemBases(useYn, plantCd, eairChemNm, defaultParam);
    }

    /**
     * 대기약품 상세조회
     * 
     * @param eairChemCd
     *            약품코드
     * @return ChemBase
     * @throws Exception
     */
    public ChemBase getChemBase(String eairChemCd) throws Exception {
        return chemBaseMapper.getChemBase(eairChemCd);
    }

    /**
     * 대기약품 신규등록
     * 
     * @param chemBase
     *            약품
     * @return eairChemCd
     * @throws Exception
     */
    public String createChemBase(ChemBase chemBase) throws Exception {
        int result = 0;
        result += this.chemBaseMapper.createChemBase(chemBase);
        return result > 0 ? chemBase.getEairChemCd() : "";
    }

    /**
     * 대기약품 수정
     * 
     * @param chemBase
     *            약품
     * @return 수정 행 수
     * @throws Exception
     */
    public String updateChemBase(ChemBase chemBase) throws Exception {
        int result = 0;
        result += this.chemBaseMapper.updateChemBase(chemBase);
        return result > 0 ? chemBase.getEairChemCd() : "";
    }

    /**
     * 대기약품 중복체크
     * 
     * @param eairChemCd
     *            약품코드
     * @param eairChemNm
     *            약품명
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkChemBase(String eairChemCd, String eairChemNm, String plantCd) throws Exception {
        return chemBaseMapper.checkChemBase(eairChemCd, eairChemNm, plantCd);
    }
}
