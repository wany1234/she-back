package com.she.chm.chem.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.mapper.ChemStatMapper;
import com.she.chm.model.ChemicalByRegulItmRes;
import com.she.chm.model.ChemicalProdRegulRes;
import com.she.chm.model.ChemprodByRegulItmRes;

@Service
public class ChemStatService {

    @Autowired
    private ChemStatMapper chemStatMapper;

    /**
     * 취급자재 규제현황
     *
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    public List<ChemicalProdRegulRes> getChemicalProdRegul(int[] regulItmNos, DefaultParam defaultParam) throws Exception {
        return this.chemStatMapper.getChemicalProdRegul(regulItmNos, defaultParam);
    }

    /**
     * 취급자재 규제현황 : 규제항목별 화학물질 개수
     *
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    public List<ChemicalByRegulItmRes> getChemicalsByRegulItmNo(int regulItmNo, Integer pageNumber, Integer pageSize,
            String orderByExpression, DefaultParam defaultParam) throws Exception {
        return this.chemStatMapper.getChemicalsByRegulItmNo(regulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);
    }

    /**
     * 취급자재 규제현황 : 규제항목별 화학물질이 포함된 자재 개수
     *
     * @param regulItmNo
     *            : 규제항목번호
     * @return
     * @throws Exception
     */
    public List<ChemprodByRegulItmRes> getChemprodByRegulItmNo(int regulItmNo, Integer pageNumber, Integer pageSize,
            String orderByExpression, DefaultParam defaultParam) throws Exception {
        return this.chemStatMapper.getChemprodByRegulItmNo(regulItmNo, pageNumber, pageSize, orderByExpression, defaultParam);
    }

}
