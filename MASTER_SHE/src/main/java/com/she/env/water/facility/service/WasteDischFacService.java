package com.she.env.water.facility.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.water.facility.mapper.WasteDischFacMapper;
import com.she.env.water.model.WasteDischFac;

@Service
public class WasteDischFacService {
    @Autowired
    private WasteDischFacMapper wasteDischFacMapper;

    /**
     * 폐수배출시설 조회
     * 
     * @param useYn
     *            사용여부
     * @return 폐수배출시설 목록
     * @throws Exception
     *             예외
     */
    public List<WasteDischFac> getWasteDischFacs(String useYn, String deptCd, String plantCd,
            String ewtrWasteDischFacNm, int ewtrCleanFacNo, DefaultParam defaultParam) throws Exception {
        return wasteDischFacMapper.getWasteDischFacs(useYn, deptCd, plantCd, ewtrWasteDischFacNm, ewtrCleanFacNo, defaultParam);
    }

    /**
     * 폐수배출시설 상세조회
     * 
     * @param ewtrWasteDischFacNo
     *            폐수배출시설번호
     * @return WasteDischFac 폐수배출시설
     * @throws Exception
     *             예외
     */
    public WasteDischFac getWasteDischFac(int ewtrWasteDischFacNo) throws Exception {
        return wasteDischFacMapper.getWasteDischFac(ewtrWasteDischFacNo);
    }
    
    /**
     * 폐수배출시설 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getWasteFacsCheck(String plantCd, String ewtrWasteDischFacNm, int ewtrWasteDischFacNo) throws Exception {
        return wasteDischFacMapper.getWasteFacsCheck(plantCd, ewtrWasteDischFacNm, ewtrWasteDischFacNo);
    }
    
    /**
     * 폐수배출시설 신규등록
     * 
     * @param WasteDischFac
     *            폐수배출시설
     * @return ewtrWasteDischFacNo 폐수배출시설번호
     * @throws Exception
     *             예외
     */
    public int createWasteDischFac(WasteDischFac wasteDischFac) throws Exception {
        this.wasteDischFacMapper.createWasteDischFac(wasteDischFac);
        return wasteDischFac.getEwtrWasteDischFacNo();
    }

    /**
     * 폐수배출시설 수정
     * 
     * @param WasteDischFac
     *            폐수배출시설
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateWasteDischFac(WasteDischFac wasteDischFac) throws Exception {
        return wasteDischFacMapper.updateWasteDischFac(wasteDischFac);
    }
}
