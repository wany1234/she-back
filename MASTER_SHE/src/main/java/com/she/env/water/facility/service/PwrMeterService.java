package com.she.env.water.facility.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.facility.mapper.PwrMeterMapper;
import com.she.env.water.model.PowerMeter;

@Service
public class PwrMeterService {
    @Autowired
    private PwrMeterMapper pwrMeterMapper;

    /**
     * 전력량계 조회
     * 
     * @param useYn
     *            사용여부
     * @return 전력량계 목록
     * @throws Exception
     *             예외
     */
    public List<PowerMeter> getPwrMeters(String useYn, String plantCd, String ewtrPwrMeterNm, DefaultParam defaultParam) throws Exception {
        return pwrMeterMapper.getPwrMeters(useYn, plantCd, ewtrPwrMeterNm, defaultParam);
    }

    /**
     * 전력량계 상세조회
     * 
     * @param ewtrPwrMeterNo
     *            전력량계번호
     * @return PwrMeter 전력량계
     * @throws Exception
     *             예외
     */
    public PowerMeter getPwrMeter(int ewtrPwrMeterNo) throws Exception {
        return pwrMeterMapper.getPwrMeter(ewtrPwrMeterNo);
    }
    
    /**
     * 전력량계 중복체크
     * 
     * @param ewtrPwrMeterNo
     *            전력량계번호
     * @return PwrMeter 전력량계
     * @throws Exception
     *             예외
     */
    public int getCheck(String plantCd, String ewtrPwrMeterNm, int ewtrPwrMeterNo) throws Exception {
        return pwrMeterMapper.getCheck(plantCd, ewtrPwrMeterNm, ewtrPwrMeterNo);
    }
    
    /**
     * 전력량계 신규등록
     * 
     * @param PowerMeter
     *            전력량계
     * @return ewtrPwrMeterNo 전력량계번호
     * @throws Exception
     *             예외
     */
    public int createPwrMeter(PowerMeter pwrMeter) throws Exception {
        this.pwrMeterMapper.createPwrMeter(pwrMeter);
        return pwrMeter.getEwtrPwrMeterNo();
    }

    /**
     * 전력량계 수정
     * 
     * @param PowerMeter
     *            전력량계
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePwrMeter(PowerMeter pwrMeter) throws Exception {
        return pwrMeterMapper.updatePwrMeter(pwrMeter);
    }
}
