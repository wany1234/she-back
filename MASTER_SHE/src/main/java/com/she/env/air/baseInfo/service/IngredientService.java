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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.air.baseInfo.mapper.IngredientMapper;
import com.she.env.air.model.Ingredient;

/**
 * 대기 원료 기능정의
 *
 */
@Service("EairIngredientService")
public class IngredientService {

    @Autowired
    private IngredientMapper ingredientMapper;

    /**
     * 원료 전체 조회
     * 
     * @param useYn
     *            사용여부
     * @return 원료목록
     * @throws Exception
     */
    public List<Ingredient> getIngredients(String useYn, String plantCd, String eairIngrNm, DefaultParam defaultParam) throws Exception {
        return this.ingredientMapper.getIngredients(useYn, plantCd, eairIngrNm, defaultParam);
    }

    /**
     * 원료 상세 조회
     * 
     * @param eairIngrCd
     *            원료코드
     * @return 원료
     * @throws Exception
     */
    public Ingredient getIngredient(String eairIngrCd) throws Exception {
        return this.ingredientMapper.getIngredient(eairIngrCd);
    }

    /**
     * 원료 신규등록
     * 
     * @param ingredient
     *            원료
     * @return 원료코드
     * @throws Exception
     */
    public String createIngredient(Ingredient ingredient) throws Exception {
        int result = 0;
        result += this.ingredientMapper.createIngredient(ingredient);
        return result > 0 ? ingredient.getEairIngrCd() : "";
    }

    /**
     * 원료 수정
     * 
     * @param ingredient
     *            원료
     * @return 수정행수
     * @throws Exception
     */
    public String updateIngredient(Ingredient ingredient) throws Exception {
        int result = 0;
        result += this.ingredientMapper.updateIngredient(ingredient);
        return result > 0 ? ingredient.getEairIngrCd() : "";
    }

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
    public int checkIngredient(String eairIngrCd, String eairIngrNm, String plantCd) throws Exception {
        return this.ingredientMapper.checkIngredient(eairIngrCd, eairIngrNm, plantCd);
    }
}
