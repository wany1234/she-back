package com.she.chm.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.chm.baseInfo.mapper.ChemicalReagentInoutMapper;
import com.she.chm.model.ChemicalReagentInout;

@Service
public class ChemicalReagentInoutService {

    @Autowired
    private ChemicalReagentInoutMapper chemicalReagentInoutMapper;

    /**
     * 시약입출고 조회
     *
     * @param chemProdNm
     *            시약명(취급자재명)
     * @param inoutFlag
     *            입출고구분
     * @param labNo
     *            실험실번호
     * @param fromYmd
     *            입출고일 검색조건 시작일
     * @param toYmd
     *            입출고일 검색조건 마침일
     * @return 시약입출고 목록
     * @throws Exception
     */
    public List<ChemicalReagentInout> getChemicalReagentInouts(String chemProdNm, String inoutFlag, String plantCd,
            Integer labNo, String fromYmd, String toYmd, DefaultParam defaultParam) throws Exception {
        return chemicalReagentInoutMapper.getChemicalReagentInouts(chemProdNm, inoutFlag, plantCd, labNo, fromYmd,
                toYmd, defaultParam);
    }

    /**
     * 시약입출고 상세 조회
     *
     * @param reagentInoutNo
     *            시약입출고번호
     * @return 시약입출고
     * @throws Exception
     */
    public ChemicalReagentInout getChemicalReagentInout(Integer reagentInoutNo, DefaultParam defaultParam) throws Exception {
        return this.chemicalReagentInoutMapper.getChemicalReagentInout(reagentInoutNo, defaultParam);
    }

    /**
     * 시약입출고 신규등록
     *
     * @param chemicalReagentInout
     *            시약입출고
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createChemicalReagentInout(ChemicalReagentInout chemicalReagentInout) throws Exception {
        this.chemicalReagentInoutMapper.createChemicalReagentInout(chemicalReagentInout);

        return chemicalReagentInout.getReagentInoutNo();
    }

    /**
     * 시약입출고 수정
     *
     * @param chemicalReagentInout
     *            시약입출고
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateChemicalReagentInout(ChemicalReagentInout chemicalReagentInout) throws Exception {
        return this.chemicalReagentInoutMapper.updateChemicalReagentInout(chemicalReagentInout);
    }

    /**
     * 시약입출고 유니크 체크 (입출고일 + 실험실번호 + 입출고구분 + 시약번호)
     *
     * @param inoutDt
     *            입출고일
     * @param labNo
     *            실험실번호
     * @param inoutFlag
     *            입출고구분
     * @param chemProdNo
     *            시약번호(취급자재)
     * @param reagentInoutNo
     *            입출고번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalReagentInout(String inoutDt, Integer labNo, String inoutFlag,
            Integer chemProdNo, Integer reagentInoutNo) throws Exception {
        return chemicalReagentInoutMapper.getCheckChemicalReagentInout(inoutDt, labNo, inoutFlag, chemProdNo,
                reagentInoutNo);
    }

    /**
     * 시약입출고 삭제
     *
     * @param reagentInoutNo
     *            입출고번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteChemicalReagentInout(int reagentInoutNo) throws Exception {
        return chemicalReagentInoutMapper.deleteChemicalReagentInout(reagentInoutNo);
    }
}
