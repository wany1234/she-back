package com.she.env.water.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.water.baseInfo.mapper.SupplyMapper;
import com.she.env.water.model.Supply;

@Service
public class SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;

    /**
     * 공급수 조회
     *
     * @param useYn
     *            사용여부
     * @param ewtrSuplClassCd
     *            공급수분류코드
     * @return 공급수 목록
     * @throws Exception
     *             예외
     */
    public List<Supply> getSupplys(String useYn, String plantCd, String ewtrCleanFacNo, String title, DefaultParam defaultParam) throws Exception {
        return supplyMapper.getSupplys(useYn, plantCd, ewtrCleanFacNo, title, defaultParam);
    }

    /**
     * 공급수 상세조회
     *
     * @param ewtrSuplNo
     *            공급수번호
     * @return Supply 공급수
     * @throws Exception
     *             예외
     */
    public Supply getSupply(int ewtrSuplNo) throws Exception {
        return supplyMapper.getSupply(ewtrSuplNo);
    }

    /**
     * 공급수 체크
     *
     * @return 체크 값
     * @throws Exception
     *             예외
     */
    public HashMap<String, Object> getSupplyCheck(String plantCd, String ewtrSuplClassCd, String ewtrSuplNm, int ewtrSuplNo) throws Exception {
        return supplyMapper.getSupplyCheck(plantCd, ewtrSuplClassCd, ewtrSuplNm, ewtrSuplNo);
    }

    /**
     * 공급수 신규등록
     *
     * @param Supply
     *            공급수
     * @return ewtrSuplNo 공급수번호
     * @throws Exception
     *             예외
     */
    public int createSupply(Supply supply) throws Exception {
        this.supplyMapper.createSupply(supply);
        return supply.getEwtrSuplNo();
    }

    /**
     * 공급수 수정
     *
     * @param Supply
     *            공급수
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSupply(Supply supply) throws Exception {
        return supplyMapper.updateSupply(supply);
    }
}
