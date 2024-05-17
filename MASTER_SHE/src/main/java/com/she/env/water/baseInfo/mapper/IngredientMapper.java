package com.she.env.water.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.Ingredient;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.IngredientMapper")
public interface IngredientMapper {

    /**
     * 원료 조회
     * 
     * @param useYn
     *            사용여부
     * @return 원료 목록
     * @throws Exception
     *             예외
     */
    public List<Ingredient> getIngredients(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("title") String title, @Param("eairIngrNm") String eairIngrNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 원료 상세조회
     * 
     * @param EwtrIngrNo
     *            원료번호
     * @return Ingredient 원료
     * @throws Exception
     *             예외
     */
    public Ingredient getIngredient(@Param("ewtrIngrNo") int ewtrIngrNo) throws Exception;

    /**
     * 원료 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getIngreCheck(@Param("plantCd") String plantCd, @Param("ewtrIngrNm") String ewtrIngrNm, @Param("ewtrIngrNo") int ewtrIngrNo) throws Exception;

    /**
     * 원료 신규등록
     * 
     * @param Ingredient
     *            원료
     * @return EwtrIngrNo 원료번호
     * @throws Exception
     *             예외
     */
    public int createIngredient(Ingredient ingredient) throws Exception;

    /**
     * 원료 수정
     * 
     * @param Ingredient
     *            원료
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateIngredient(Ingredient ingredient) throws Exception;
}
