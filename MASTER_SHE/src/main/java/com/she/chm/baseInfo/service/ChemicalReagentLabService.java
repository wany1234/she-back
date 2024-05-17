package com.she.chm.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.chm.baseInfo.mapper.ChemicalReagentLabMapper;
import com.she.chm.model.ChemicalReagentLab;

@Service
public class ChemicalReagentLabService {

    @Autowired
    private ChemicalReagentLabMapper chemicalReagentLabMapper;

    /**
     * 시약실험실 조회
     * 
     * @param labNm
     *            시약실험실명
     * @param useYn
     *            사용여부
     * @return 시약실험실 목록
     * @throws Exception
     */
    public List<ChemicalReagentLab> getChemicalReagentLabs(String labNm, String plantCd, String useYn, DefaultParam defaultParam)
            throws Exception {
        return chemicalReagentLabMapper.getChemicalReagentLabs(labNm, plantCd, useYn, defaultParam);
    }

    /**
     * 시약실험실 상세 조회
     * 
     * @param labNo
     *            시약실험실번호
     * @return 시약실험실
     * @throws Exception
     */
    public ChemicalReagentLab getChemicalReagentLab(Integer labNo, DefaultParam defaultParam) throws Exception {
        return this.chemicalReagentLabMapper.getChemicalReagentLab(labNo, defaultParam);
    }

    /**
     * 시약실험실 신규등록
     * 
     * @param chemicalReagentLab
     *            시약실험실
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createChemicalReagentLab(ChemicalReagentLab chemicalReagentLab) throws Exception {
        this.chemicalReagentLabMapper.createChemicalReagentLab(chemicalReagentLab);

        return chemicalReagentLab.getLabNo();
    }

    /**
     * 시약실험실 수정
     * 
     * @param chemicalReagentLab
     *            시약실험실
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalReagentLab(ChemicalReagentLab chemicalReagentLab) throws Exception {
        return this.chemicalReagentLabMapper.updateChemicalReagentLab(chemicalReagentLab);
    }

    /**
     * 시약실험실명 체크 (사업장코드 + 시약실험실명)
     * 
     * @param labNm
     *            바꿀 시약실험실명
     * @param labNo
     *            시약실험실번호
     * @param plantCd
     *            사업장코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalReagentLab(String labNm, Integer labNo, String plantCd)
            throws Exception {
        return chemicalReagentLabMapper.getCheckChemicalReagentLab(labNm, labNo, plantCd);
    }
}
