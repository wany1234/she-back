package com.she.env.gas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.EmstrHis;

@Mapper
@Repository("com.she.env.gas.mapper.EmstrHisMapper")
public interface EmstrHisMapper {

    /**
     * 거래이력 신규등록
     * 
     * @param emstrHis
     * @return
     * @throws Exception
     */
    public int createEmstrHis(EmstrHis emstrHis) throws Exception;

    /**
     * 거래이력 수정
     * 
     * @param emstrHis
     * @return
     * @throws Exception
     */
    public int updateEmstrHis(EmstrHis emstrHis) throws Exception;

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
    public List<EmstrHis> getEmstrHisList(@Param("plantCd") String plantCd, @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("trVendorCd") String trVendorCd, @Param("gubun") String gubun, @Param("trGubun") String trGubun, @Param("trPlantCd") String trPlantCd) throws Exception;

    /**
     * 거래이력 상세
     * 
     * @param emstrHisNo
     * @return
     * @throws Exception
     */
    public EmstrHis getEmstHis(@Param("emstrHisNo") int emstrHisNo) throws Exception;

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
    public int dupleCheck(@Param("plantCd") String plantCd, @Param("trYmd") String trYmd, @Param("trGubun") String trGubun, @Param("gubun") String gubun) throws Exception;

}
