package com.she.chm.chem.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.chem.mapper.ChemPermitProdHisMapper;
import com.she.chm.model.ChemPermitProdHis;
import com.she.chm.model.Chemprod;

@Service
public class ChemPermitProdHisService {

    @Autowired
    private ChemPermitProdHisMapper chemPermitProdHisMapper;

    /**
     * 인허가신고 조회
     *
     * @param useYn
     * @param plantCd
     * @param permitClsNo
     * @param fromYmd
     * @param toYmd
     * @return
     * @throws Exception
     */
    public List<ChemPermitProdHis> getChemPermitProdHises(String useYn, String plantCd, Integer permitClsNo,
            String fromYmd, String toYmd) throws Exception {
        return chemPermitProdHisMapper.getChemPermitProdHises(useYn, plantCd, permitClsNo, fromYmd, toYmd);
    }

    /**
     * 인허가신고 상세조회
     *
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    public ChemPermitProdHis getChemPermitProdHis(@Param("permitProdHisNo") int permitProdHisNo) throws Exception {
        ChemPermitProdHis chemPermitProdHis = chemPermitProdHisMapper.getChemPermitProdHis(permitProdHisNo);
        chemPermitProdHis.setChmPermitRefProd(chemPermitProdHisMapper.getChemPermitProdHisProd(permitProdHisNo));
        return chemPermitProdHis;
    }

    /**
     * 인허가신고 중복검사
     *
     * @param plantCd
     * @param permitClsNo
     * @param permitDt
     * @param permitProdHisNo
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkChemPermitProdHis(String plantCd, int permitClsNo, String permitDt,
            Integer permitProdHisNo) throws Exception {
        return chemPermitProdHisMapper.checkChemPermitProdHis(plantCd, permitClsNo, permitDt, permitProdHisNo);
    }

    /**
     * 인허가신고 등록
     *
     * @param chemPermitProdHis
     * @return
     * @throws Exception
     */
    @Transactional
    public ChemPermitProdHis createChemPermitProdHis(ChemPermitProdHis chemPermitProdHis) throws Exception {
        // 인허가신고 등록
        chemPermitProdHisMapper.createChemPermitProdHis(chemPermitProdHis);
        // 인허가신고 취급자재 등록
        this.saveChemPermitProdHisProd(chemPermitProdHis);
        return chemPermitProdHis;
    }

    /**
     * 인허가신고 수정
     *
     * @param chemPermitProdHis
     * @return
     * @throws Exception
     */
    @Transactional
    public ChemPermitProdHis updateChemPermitProdHis(ChemPermitProdHis chemPermitProdHis) throws Exception {
        chemPermitProdHisMapper.updateChemPermitProdHis(chemPermitProdHis);
        // 인허가신고 취급자재 등록
        this.saveChemPermitProdHisProd(chemPermitProdHis);
        return chemPermitProdHis;
    }

    public int saveChemPermitProdHisProd(ChemPermitProdHis chemPermitProdHis) throws Exception {
        int returnVal = 0;

        if (chemPermitProdHis.getChmPermitRefProd() != null) {
            returnVal += chemPermitProdHisMapper.deleteChemPermitProdHisProd(chemPermitProdHis.getPermitProdHisNo());
            if (chemPermitProdHis.getChmPermitRefProd().size() > 0) {
                for (Chemprod chemprod : chemPermitProdHis.getChmPermitRefProd()) {
                    returnVal += chemPermitProdHisMapper.createChemPermitProdHisProd(chemprod.getChemProdNo(),
                            chemPermitProdHis.getPermitProdHisNo());
                }
            }

        }
        return returnVal;
    }

    /**
     * 인허가현황 개정이력 삭제
     *
     * @param ChemPermitProdHisList
     * @return 생성 행 수
     * @throws Exception
     */
    @org.springframework.transaction.annotation.Transactional
    public int revDeleteChemPermitProdHisStatus(List<ChemPermitProdHis> ChemPermitProdHisList) throws Exception {
        int count = 0;
        for (ChemPermitProdHis ChemPermitProdHis : ChemPermitProdHisList) {
            count += this.chemPermitProdHisMapper.revDeleteChemPermitProdHisStatus(ChemPermitProdHis);
        }

        return count;
    }

    /**
     * 인허가현황 삭제
     *
     * @param permitProdHisNo
     * @return 삭제 행 수
     * @throws Exception
     */
    @org.springframework.transaction.annotation.Transactional
    public int deleteChemPermitProdHisStatus(@Param("permitProdHisNo") int permitProdHisNo) throws Exception {
        return this.chemPermitProdHisMapper.deleteChemPermitProdHisStatus(permitProdHisNo);
    }

    /**
     * 인허가현황 개정 조회
     *
     * @param permitProdHisNo
     * @return 인허가현황
     * @throws Exception
     */

    public List<ChemPermitProdHis> getChemPermitProdHisRevisionList(String permitProdHisNo, DefaultParam defaultParam) throws Exception {
        return chemPermitProdHisMapper.getChemPermitProdHisRevisionList(permitProdHisNo, defaultParam);
    }

}
