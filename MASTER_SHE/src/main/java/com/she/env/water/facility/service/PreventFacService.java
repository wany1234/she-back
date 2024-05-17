package com.she.env.water.facility.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.facility.mapper.PreventFacMapper;
import com.she.env.water.model.PreventFac;

@Service
public class PreventFacService {
    @Autowired
    private PreventFacMapper preventFacMapper;

    /**
     * 방지시설 조회
     * 
     * @param useYn
     *            사용여부
     * @return 방지시설 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFac> getPreventFacs(String useYn, String deptCd, String plantCd, String ewtrPreventFacNm, int ewtrCleanFacNo, DefaultParam defaultParam)
            throws Exception {
        return preventFacMapper.getPreventFacs(useYn, deptCd, plantCd, ewtrPreventFacNm, ewtrCleanFacNo, defaultParam);
    }

    /**
     * 방지시설 상세조회
     * 
     * @param ewtrPreventFacNo
     *            방지시설번호
     * @return PreventFac 방지시설
     * @throws Exception
     *             예외
     */
    public PreventFac getPreventFac(int ewtrPreventFacNo) throws Exception {
        return preventFacMapper.getPreventFac(ewtrPreventFacNo);
    }

    /**
     * 방지시설 신규등록
     * 
     * @param PreventFac
     *            방지시설
     * @return ewtrPreventFacNo 방지시설번호
     * @throws Exception
     *             예외
     */
    public int createPreventFac(PreventFac preventFac) throws Exception {
        this.preventFacMapper.createPreventFac(preventFac);
        return preventFac.getEwtrPreventFacNo();
    }

    /**
     * 방지시설 수정
     * 
     * @param PreventFac
     *            방지시설
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePreventFac(PreventFac preventFac) throws Exception {
        return preventFacMapper.updatePreventFac(preventFac);
    }
}
