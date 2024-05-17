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

import com.she.chm.model.ChemicalUnit;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalUnitMapper")
public interface ChemicalUnitMapper {

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
    public List<ChemicalUnit> getChemicalUnits(@Param("unitNm") String unitNm, @Param("useYn") String useYn)
            throws Exception;

    /**
     * 물질단위 상세 조회
     * 
     * @param unitNo
     *            물질단위번호
     * @return 물질단위
     * @throws Exception
     */
    public ChemicalUnit getChemicalUnit(@Param("unitNo") int unitNo) throws Exception;

    /**
     * 물질단위 신규등록
     * 
     * @param chemicalBranch
     *            물질단위
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalUnit(ChemicalUnit chemicalUnit) throws Exception;

    /**
     * 물질단위 수정
     * 
     * @param chemicalBranch
     *            물질단위
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalUnit(ChemicalUnit chemicalUnit) throws Exception;

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
    public List<HashMap<String, Object>> getCheckChemicalUnit(@Param("unitNm") String unitNm,
            @Param("unitNo") int unitNo) throws Exception;
}
