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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.air.model.Ingredient;

/**
 * 원료 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.baseInfo.mapper.IngredientMapper")
public interface IngredientMapper {

    /**
     * 원료 전체 조회
     * 
     * @param useYn
     *            사용여부
     * @return 원료목록
     * @throws Exception
     */
    public List<Ingredient> getIngredients(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("eairIngrNm") String eairIngrNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 원료 상세 조회
     * 
     * @param eairIngrCd
     *            원료코드
     * @return 원료
     * @throws Exception
     */
    public Ingredient getIngredient(@Param("eairIngrCd") String eairIngrCd) throws Exception;

    /**
     * 원료 신규등록
     * 
     * @param ingredient
     *            원료
     * @return 등록행수
     * @throws Exception
     */
    public int createIngredient(Ingredient ingredient) throws Exception;

    /**
     * 원료 수정
     * 
     * @param ingredient
     *            원료
     * @return 수정행수
     * @throws Exception
     */
    public int updateIngredient(Ingredient ingredient) throws Exception;

    /**
     * 원료명 중복체크
     *
     * @param eairIngrCd
     *            원료코드
     * @param eairIngrNm
     *            원료명
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkIngredient(@Param("eairIngrCd") String eairIngrCd, @Param("eairIngrNm") String eairIngrNm, @Param("plantCd") String plantCd) throws Exception;
}
