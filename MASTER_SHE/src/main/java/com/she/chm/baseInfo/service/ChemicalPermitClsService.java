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

import com.she.chm.baseInfo.mapper.ChemicalPermitClsMapper;
import com.she.chm.model.ChemicalPermitCls;
import com.she.chm.model.ChemicalRegulItem;

@Service
public class ChemicalPermitClsService {
    @Autowired
    private ChemicalPermitClsMapper chemicalPermitClsMapper;

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
    public List<ChemicalPermitCls> getChemicalPermitClses(String permitKindCd, String permitClsNm, String useYn, String plantCd) throws Exception {
        return chemicalPermitClsMapper.getChemicalPermitClses(permitKindCd, permitClsNm, useYn, plantCd);
    }

    /**
     * 인허가신고서 규제정보 제외 조회
     * 
     * @param permitKindCd
     * @param permitClsNm
     * @param useYn
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<ChemicalPermitCls> getChemicalPermitClsList(String permitKindCd, String permitClsNm, String useYn, String plantCd) throws Exception {
        return chemicalPermitClsMapper.getChemicalPermitClsList(permitKindCd, permitClsNm, useYn, plantCd);
    }

    /**
     * 인허가신고서 상세 조회
     * 
     * @param permitClsNo
     *            인허가신고서번호
     * @return 인허가신고서
     * @throws Exception
     */
    public ChemicalPermitCls getChemicalPermitCls(int permitClsNo) throws Exception {
        return this.chemicalPermitClsMapper.getChemicalPermitCls(permitClsNo);
    }

    /**
     * 인허가신고서 신규등록
     * 
     * @param chemicalPermitCls
     *            인허가신고서
     * @return 인허가신고서번호
     * @throws Exception
     */
    @Transactional
    public int createChemicalPermitCls(ChemicalPermitCls chemicalPermitCls) throws Exception {
        this.chemicalPermitClsMapper.createChemicalPermitCls(chemicalPermitCls);

        // 인허가신고 규제사항 삭제
        this.chemicalPermitClsMapper.deleteChemicalPermitReguls(chemicalPermitCls.getPermitClsNo());
        // 인허가신고 규제사항 등록
        for (int regulItmNo : chemicalPermitCls.getRegulItmNos()) {
            this.chemicalPermitClsMapper.createChemicalPermitReguls(chemicalPermitCls.getPermitClsNo(), regulItmNo, chemicalPermitCls.getCreateUserId());
        }

        return chemicalPermitCls.getPermitClsNo();
    }

    /**
     * 인허가신고서 수정
     * 
     * @param chemicalPermitCls
     *            인허가신고서
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalPermitCls(ChemicalPermitCls chemicalPermitCls) throws Exception {
        int resultNo = 0;
        resultNo += this.chemicalPermitClsMapper.updateChemicalPermitCls(chemicalPermitCls);

        // 인허가신고 규제사항 삭제
        this.chemicalPermitClsMapper.deleteChemicalPermitReguls(chemicalPermitCls.getPermitClsNo());
        // 인허가신고 규제사항 등록
        for (int regulItmNo : chemicalPermitCls.getRegulItmNos()) {
            if (regulItmNo != 0) {
                this.chemicalPermitClsMapper.createChemicalPermitReguls(chemicalPermitCls.getPermitClsNo(), regulItmNo, chemicalPermitCls.getCreateUserId());
            }
        }
        return resultNo;
    }

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
    public List<HashMap<String, Object>> getCheckChemicalPermitCls(String permitClsNm, String permitClsCd, int permitClsNo) throws Exception {
        return chemicalPermitClsMapper.getCheckChemicalPermitCls(permitClsNm, permitClsCd, permitClsNo);
    }

    /**
     * 인허가신고 규제사항 체크
     * 
     * @param permitClsNo
     *            인허가신고서번호
     * @return 인허가신고 규제사항 목록
     * @throws Exception
     */
    public List<ChemicalRegulItem> getChemicalPermitReguls(int permitClsNo) throws Exception {
        return chemicalPermitClsMapper.getChemicalPermitReguls(permitClsNo);
    }

}
