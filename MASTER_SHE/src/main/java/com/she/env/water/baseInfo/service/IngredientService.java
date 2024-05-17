package com.she.env.water.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.mapper.IngredientMapper;
import com.she.env.water.model.Ingredient;

@Service
public class IngredientService {
    @Autowired
    private IngredientMapper ingredientMapper;

    /**
     * 원료 조회
     *
     * @param useYn
     *            사용여부
     * @return 원료 목록
     * @throws Exception
     *             예외
     */
    public List<Ingredient> getIngredients(String useYn, String plantCd, String title, String eairIngrNm, DefaultParam defaultParam) throws Exception {
        return ingredientMapper.getIngredients(useYn, plantCd, title, eairIngrNm, defaultParam);
    }

    /**
     * 원료 상세조회
     *
     * @param ewtrIngrNo
     *            원료번호
     * @return Ingredient 원료
     * @throws Exception
     *             예외
     */
    public Ingredient getIngredient(int ewtrIngrNo) throws Exception {

        return ingredientMapper.getIngredient(ewtrIngrNo);
    }

    /**
     * 원료 체크
     *
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getIngreCheck(String plantCd, String ewtrIngrNm, int ewtrIngrNo) throws Exception {
        return ingredientMapper.getIngreCheck(plantCd, ewtrIngrNm, ewtrIngrNo);
    }

    /**
     * 원료 신규등록
     *
     * @param Ingredient
     *            원료
     * @return ewtrIngrNo 원료번호
     * @throws Exception
     *             예외
     */
    public int createIngredient(Ingredient ingredient) throws Exception {
        this.ingredientMapper.createIngredient(ingredient);
        return ingredient.getEwtrIngrNo();
    }

    /**
     * 원료 수정
     *
     * @param Ingredient
     *            원료
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateIngredient(Ingredient ingredient) throws Exception {
        return ingredientMapper.updateIngredient(ingredient);
    }
}
