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
package com.she.env.air.baseInfo.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.ChemBase;

@Mapper
@Repository("com.she.env.air.baseInfo.mapper.ChemBaseMapper")
public interface ChemBaseMapper {

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
    public List<ChemBase> getChemBases(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("eairChemNm") String eairChemNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대기약품 상세조회
     *
     * @param eairChemCd
     *            약품코드
     * @return ChemBase
     * @throws Exception
     */
    public ChemBase getChemBase(@Param("eairChemCd") String eairChemCd) throws Exception;

    /**
     * 대기약품 신규등록
     *
     * @param chemBase
     *            약품
     * @return eairChemCd
     * @throws Exception
     */
    public int createChemBase(ChemBase chemBase) throws Exception;

    /**
     * 대기약품 수정
     *
     * @param chemBase
     *            약품
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemBase(ChemBase chemBase) throws Exception;

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
    public int checkChemBase(@Param("eairChemCd") String eairChemCd, @Param("eairChemNm") String eairChemNm, @Param("plantCd") String plantCd) throws Exception;
}
