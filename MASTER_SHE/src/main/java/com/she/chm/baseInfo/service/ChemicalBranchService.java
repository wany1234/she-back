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

import com.she.chm.baseInfo.mapper.ChemicalBranchMapper;
import com.she.chm.model.ChemicalBranch;

@Service
public class ChemicalBranchService {
    @Autowired
    private ChemicalBranchMapper chemicalBranchMapper;

    /**
     * 법인 조회
     * 
     * @param branchNmKr
     *            법인명 (KOR)
     * @param useYn
     *            사용여부
     * @return 법인 목록
     * @throws Exception
     */
    public List<ChemicalBranch> getChemicalBranchs(String branchNmKr, String useYn) throws Exception {
        return chemicalBranchMapper.getChemicalBranchs(branchNmKr, useYn);
    }

    /**
     * 법인 상세 조회
     * 
     * @param branchCd
     *            법인코드
     * @return 법인
     * @throws Exception
     */
    public ChemicalBranch getChemicalBranch(String branchCd) throws Exception {
        return this.chemicalBranchMapper.getChemicalBranch(branchCd);
    }

    /**
     * 법인 신규등록
     * 
     * @param chemicalBranch
     *            법인
     * @return 법인코드
     * @throws Exception
     */
    @Transactional
    public String createChemicalBranch(ChemicalBranch chemicalBranch) throws Exception {
        this.chemicalBranchMapper.createChemicalBranch(chemicalBranch);

        return chemicalBranch.getBranchCd();
    }

    /**
     * 법인 수정
     * 
     * @param chemicalBranch
     *            법인
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalBranch(ChemicalBranch chemicalBranch) throws Exception {
        return this.chemicalBranchMapper.updateChemicalBranch(chemicalBranch);
    }

    /**
     * 법인명 체크
     * 
     * @param branchNmKr
     *            법인명 (KOR)
     * @param branchCdOrgin
     *            법인코드 자기자신거
     * @param branchCd
     *            법인코드 바꿔 볼려고 하는거
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalBranch(String branchNmKr, String branchCdOrgin,
            String branchCd) throws Exception {
        return chemicalBranchMapper.getCheckChemicalBranch(branchNmKr, branchCdOrgin, branchCd);
    }

}
