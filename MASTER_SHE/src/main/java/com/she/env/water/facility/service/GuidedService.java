package com.she.env.water.facility.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.facility.mapper.GuidedMapper;
import com.she.env.water.model.GuidedHist;

@Service
public class GuidedService {
    @Autowired
    private GuidedMapper guidedMapper;

    /**
     * 지도점검 조회
     *
     * @param
     * @return 지도점검 목록
     * @throws Exception
     *             예외
     */
    public List<GuidedHist> getGuideds() throws Exception {
        return guidedMapper.getGuideds();
    }

    /**
     * 지도점검 상세조회
     *
     * @param ewtrGuidedHistNo
     *            지도점검번호
     * @return GuidedHist 지도점검
     * @throws Exception
     *             예외
     */
    public GuidedHist getGuided(String measureYmd, int ewtrCleanFacNo, String plantCd, String deptCd) throws Exception {
        return guidedMapper.getGuided(measureYmd, ewtrCleanFacNo, plantCd, deptCd);
    }

    /**
     * 지도점검 수정
     *
     * @param GuidedHist
     *            지도점검
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateGuided(GuidedHist guidedHist) throws Exception {

        return guidedMapper.updateGuided(guidedHist);
    }
}
