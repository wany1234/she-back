package com.she.env.water.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.mapper.DischargeMapper;
import com.she.env.water.model.Discharge;

@Service
public class DischargeService {

    @Autowired
    private DischargeMapper dischargeMapper;

    /**
     * 배출수 조회
     * 
     * @param useYn
     *            사용여부
     * @return 배출수 목록
     * @throws Exception
     *             예외
     */
    public List<Discharge> getDischarges(String useYn, String plantCd, String ewtrCleanFacNo, String deptCd, String title, DefaultParam defaultParam) throws Exception {
        return dischargeMapper.getDischarges(useYn, plantCd, ewtrCleanFacNo, deptCd, title, defaultParam);
    }

    /**
     * 배출수 상세조회
     * 
     * @param ewtrDischNo
     *            배출수번호
     * @return Discharge 배출수
     * @throws Exception
     *             예외
     */
    public Discharge getDischarge(int ewtrDischNo) throws Exception {
        return dischargeMapper.getDischarge(ewtrDischNo);
    }

    /**
     * 배출수 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public HashMap<String, Object> getDischargeCheck(String plantCd, String ewtrDischClassCd, String ewtrDischNm, int ewtrDischNo) throws Exception {
        return dischargeMapper.getDischargeCheck(plantCd, ewtrDischClassCd, ewtrDischNm, ewtrDischNo);
    }

    /**
     * 배출수 신규등록
     * 
     * @param Discharge
     *            배출수
     * @return ewtrDischNo 배출수번호
     * @throws Exception
     *             예외
     */
    public int createDischarge(Discharge discharge) throws Exception {
        int count = this.dischargeMapper.createDischarge(discharge);
        return discharge.getEwtrDischNo();
    }

    /**
     * 배출수 수정
     * 
     * @param Discharge
     *            배출수
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateDischarge(Discharge discharge) throws Exception {
        return dischargeMapper.updateDischarge(discharge);
    }

}
