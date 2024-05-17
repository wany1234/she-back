package com.she.env.water.facility.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.water.facility.mapper.PreventFacBreakHistMapper;
import com.she.env.water.model.PreventFacBreakHist;

@Service
public class PreventFacBreakHistService {
    @Autowired
    private PreventFacBreakHistMapper preventFacBreakHistMapper;

    /**
     * 고장이력정보 조회
     * 
     * @param ewtrPreventFacNo
     *            오염방지시설번호
     * @param measureYmd
     *            기준일자
     * @return 고장이력정보 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFacBreakHist> getPreventFacBreakHists(int ewtrPreventFacNo, String measureYmd) throws Exception {
        return preventFacBreakHistMapper.getPreventFacBreakHists(ewtrPreventFacNo, measureYmd);
    }

    /**
     * 고장이력정보 상세조회
     * 
     * @param ewtrPreventFacBreakHistNo
     *            고장이력정보번호
     * @return PreventFacBreakHist 고장이력정보
     * @throws Exception
     *             예외
     */
    public PreventFacBreakHist getPreventFacBreakHist(int ewtrPreventFacBreakHistNo) throws Exception {
        return preventFacBreakHistMapper.getPreventFacBreakHist(ewtrPreventFacBreakHistNo);
    }

    /**
     * 고장이력정보 신규등록
     * 
     * @param PreventFacBreakHist
     *            고장이력정보
     * @return ewtrPreventFacBreakHistNo 고장이력정보번호
     * @throws Exception
     *             예외
     */
    public int createPreventFacBreakHist(PreventFacBreakHist preventFacBreakHist) throws Exception {
        this.preventFacBreakHistMapper.createPreventFacBreakHist(preventFacBreakHist);
        return preventFacBreakHist.getEwtrPreventFacBreakHistNo();
    }

    /**
     * 고장이력정보 수정
     * 
     * @param PreventFacBreakHist
     *            고장이력정보
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePreventFacBreakHist(PreventFacBreakHist preventFacBreakHist) throws Exception {
        return preventFacBreakHistMapper.updatePreventFacBreakHist(preventFacBreakHist);
    }

    /**
     * 선택된 고장이력정보 삭제
     * 
     * @param List<PreventFacBreakHist>
     * @return 삭제행수
     * @throws Exception
     */
    @Transactional
    public int deletePreventFacBreakHist(List<PreventFacBreakHist> preventFacBreakHists) throws Exception {
        int count = 0;
        for (PreventFacBreakHist preventFacBreakHist : preventFacBreakHists) {
            count += this.preventFacBreakHistMapper
                    .deletePreventFacBreakHist(preventFacBreakHist.getEwtrPreventFacBreakHistNo());
        }

        return count;
    }
}
