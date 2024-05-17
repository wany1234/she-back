package com.she.env.gas.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.mapper.EmstrHisMapper;
import com.she.env.gas.model.EmstrHis;

@Service
public class EmstrHisService {

    @Autowired
    private EmstrHisMapper emstrHisMapper;

    /**
     * 거래이력 신규등록
     * 
     * @param emstrHis
     * @return
     * @throws Exception
     */
    public int createEmstrHis(EmstrHis emstrHis) throws Exception {
        emstrHisMapper.createEmstrHis(emstrHis);
        return emstrHis.getEmstrHisNo();
    }

    /**
     * 거래이력 수정
     * 
     * @param emstrHis
     * @return
     * @throws Exception
     */
    public int updateEmstrHis(EmstrHis emstrHis) throws Exception {
        emstrHisMapper.updateEmstrHis(emstrHis);
        return emstrHis.getEmstrHisNo();
    }

    /**
     * 거래이력 조회
     * 
     * @param plantCd
     * @param fromDate
     * @param toDate
     * @param trVendorCd
     * @param gubun
     * @param trGubun
     * @param trPlantCd
     * @return
     * @throws Exception
     */
    public List<EmstrHis> getEmstrHisList(@Param("plantCd") String plantCd, @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("trVendorCd") String trVendorCd, @Param("gubun") String gubun, @Param("trGubun") String trGubun, @Param("trPlantCd") String trPlantCd) throws Exception {
        return emstrHisMapper.getEmstrHisList(plantCd, fromDate, toDate, trVendorCd, gubun, trGubun, trPlantCd);
    }

    /**
     * 거래이력 상세
     * 
     * @param emstrHisNo
     * @return
     * @throws Exception
     */
    public EmstrHis getEmstHis(@Param("emstrHisNo") int emstrHisNo) throws Exception {
        return emstrHisMapper.getEmstHis(emstrHisNo);
    }

    /**
     * 거래이력 중복체크
     * 
     * @param plantCd
     * @param trYmd
     * @param trVendorCd
     * @param gubun
     * @return
     * @throws Exception
     */
    public int dupleCheck(@Param("plantCd") String plantCd, @Param("trYmd") String trYmd, @Param("trGubun") String trGubun, @Param("gubun") String gubun) throws Exception {
        return emstrHisMapper.dupleCheck(plantCd, trYmd, trGubun, gubun);
    }

}
