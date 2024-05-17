package com.she.env.water.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.mapper.ChemicalMapper;
import com.she.env.water.model.Chemical;

@Service
public class ChemicalService {
    @Autowired
    private ChemicalMapper chemicalMapper;

    /**
     * 수질약품 조회
     * 
     * @param useYn
     *            사용여부
     * @return 수질약품 목록
     * @throws Exception
     *             예외
     */
    public List<Chemical> getChemicals(String useYn, String plantCd, String title, DefaultParam defaultParam) throws Exception {
        return chemicalMapper.getChemicals(useYn, plantCd, title, defaultParam);
    }

    /**
     * 전력량계 상세조회
     * 
     * @param ewtrChemNo
     *            전력량계번호
     * @return Chemical 전력량계
     * @throws Exception
     *             예외
     */
    public Chemical getChemical(int ewtrChemNo) throws Exception {
        return chemicalMapper.getChemical(ewtrChemNo);
    }

    /**
     * 수질약품 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getChemicalCheck(String plantCd, String ewtrChemNm, int ewtrChemNo) throws Exception {
        return chemicalMapper.getChemicalCheck(plantCd, ewtrChemNm, ewtrChemNo);
    }

    /**
     * 수질약품 신규등록
     * 
     * @param Chemical
     *            전력량계
     * @return ewtrChemNo 전력량계번호
     * @throws Exception
     *             예외
     */
    public int createChemical(Chemical chemical) throws Exception {
        this.chemicalMapper.createChemical(chemical);
        return chemical.getEwtrChemNo();
    }

    /**
     * 수질약품 수정
     * 
     * @param Chemical
     *            전력량계
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateChemical(Chemical chemical) throws Exception {
        return chemicalMapper.updateChemical(chemical);
    }

}
