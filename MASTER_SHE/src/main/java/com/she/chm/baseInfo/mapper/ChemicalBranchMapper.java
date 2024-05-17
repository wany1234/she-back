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
package com.she.chm.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalBranch;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalBranchMapper")
public interface ChemicalBranchMapper {

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
    public List<ChemicalBranch> getChemicalBranchs(@Param("branchNmKr") String branchNmKr, @Param("useYn") String useYn)
            throws Exception;

    /**
     * 법인 상세 조회
     * 
     * @param branchCd
     *            법인코드
     * @return 법인
     * @throws Exception
     */
    public ChemicalBranch getChemicalBranch(@Param("branchCd") String branchCd) throws Exception;

    /**
     * 법인 신규등록
     * 
     * @param chemicalBranch
     *            법인
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalBranch(ChemicalBranch chemicalBranch) throws Exception;

    /**
     * 법인 수정
     * 
     * @param chemicalBranch
     *            법인
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalBranch(ChemicalBranch chemicalBranch) throws Exception;

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
    public List<HashMap<String, Object>> getCheckChemicalBranch(@Param("branchNmKr") String branchNmKr,
            @Param("branchCdOrgin") String branchCdOrgin, @Param("branchCd") String branchCd) throws Exception;
}
