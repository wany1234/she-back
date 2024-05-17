package com.she.chm.chem.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalByRegulItmRes;
import com.she.chm.model.ChemicalProdRegulRes;
import com.she.chm.model.ChemprodByRegulItmRes;

@Mapper
@Repository("com.she.chm.chem.mapper.ChemStatMapper")
public interface ChemStatMapper {
    /**
     * 취급자재 규제현황
     * 
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    public List<ChemicalProdRegulRes> getChemicalProdRegul(@Param("regulItmNos") int[] regulItmNos, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급자재 규제현황 : 규제항목별 화학물질 개수
     * 
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    public List<ChemicalByRegulItmRes> getChemicalsByRegulItmNo(@Param("regulItmNo") int regulItmNo,
            @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize,
            @Param("orderByExpression") String orderByExpression, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 취급자재 규제현황 : 규제항목별 화학물질이 포함된 자재 개수
     * 
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    public List<ChemprodByRegulItmRes> getChemprodByRegulItmNo(@Param("regulItmNo") int regulItmNo,
            @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize,
            @Param("orderByExpression") String orderByExpression, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
