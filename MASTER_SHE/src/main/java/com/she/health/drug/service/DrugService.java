package com.she.health.drug.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.health.drug.mapper.DrugMapper;
import com.she.health.model.Drug;

@Service
public class DrugService {
    @Autowired
    private DrugMapper drugMapper;

    /**
     * 약품관리 조회
     * 
     * @param
     * @return 약품관리 목록
     * @throws Exception
     *             예외
     */
    public List<Drug> getDrugs(String useYn, String heaDrugNm) throws Exception {
        return drugMapper.getDrugs(useYn, heaDrugNm);
    }

    /**
     * 약품관리 상세조회
     * 
     * @param heaDrugNo
     *            약품번호
     * @return Drug 약품
     * @throws Exception
     *             예외
     */
    public Drug getDrug(int heaDrugNo) throws Exception {
        return drugMapper.getDrug(heaDrugNo);
    }

    /**
     * 약품관리 신규등록
     * 
     * @param Drug
     *            약품
     * @return heaDrugNo 약품번호
     * @throws Exception
     *             예외
     */
    public int createDrug(Drug drug) throws Exception {
        this.drugMapper.createDrug(drug);
        return drug.getHeaDrugNo();
    }

    /**
     * 약품관리 수정
     * 
     * @param Drug
     *            약품
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateDrug(Drug drug) throws Exception {
        return drugMapper.updateDrug(drug);
    }

}
