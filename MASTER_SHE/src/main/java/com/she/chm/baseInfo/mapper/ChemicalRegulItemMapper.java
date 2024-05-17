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

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalRegulItem;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalRegulItemMapper")
public interface ChemicalRegulItemMapper {

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
    public List<ChemicalRegulItem> getChemicalRegulItems(@Param("regulLawCd") String regulLawCd,
            @Param("regulItmNm") String regulItmNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 규제항목 상세 조회
     * 
     * @param regulItmNo
     *            규제항목번호
     * @return 규제항목
     * @throws Exception
     */
    public ChemicalRegulItem getChemicalRegulItem(@Param("regulItmNo") int regulItmNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 규제항목 신규등록
     * 
     * @param chemicalRegulItem
     *            규제항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalRegulItem(ChemicalRegulItem chemicalRegulItem) throws Exception;

    /**
     * 규제항목 수정
     * 
     * @param chemicalRegulItem
     *            규제항목
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalRegulItem(ChemicalRegulItem chemicalRegulItem) throws Exception;

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
    public List<HashMap<String, Object>> getCheckChemicalRegulItem(@Param("regulItmNm") String regulItmNm,
            @Param("regulItmNo") int regulItmNo) throws Exception;
}
