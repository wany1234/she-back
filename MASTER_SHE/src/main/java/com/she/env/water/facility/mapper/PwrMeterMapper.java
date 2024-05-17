package com.she.env.water.facility.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.PowerMeter;

@Mapper
@Repository("com.she.env.water.facility.mapper.PwrMeterMapper")
public interface PwrMeterMapper {
    /**
     * 전력량계 조회
     * 
     * @param useYn
     *            사용여부
     * @return 전력량계 목록
     * @throws Exception
     *             예외
     */
    public List<PowerMeter> getPwrMeters(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("ewtrPwrMeterNm") String ewtrPwrMeterNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 전력량계 상세조회
     * 
     * @param ewtrPwrMeterNo
     *            전력량계번호
     * @return PwrMeter 전력량계
     * @throws Exception
     *             예외
     */
    public PowerMeter getPwrMeter(@Param("ewtrPwrMeterNo") int ewtrPwrMeterNo) throws Exception;

    /**
     * 전력량계 중복체크
     * 
     * @param ewtrPwrMeterNo
     *            전력량계번호
     * @return PwrMeter 전력량계
     * @throws Exception
     *             예외
     */
    public int getCheck(@Param("plantCd") String plantCd, @Param("ewtrPwrMeterNm") String ewtrPwrMeterNm, @Param("ewtrPwrMeterNo") int ewtrPwrMeterNo) throws Exception;

    /**
     * 전력량계 신규등록
     * 
     * @param PowerMeter
     *            전력량계
     * @return ewtrPwrMeterNo 전력량계번호
     * @throws Exception
     *             예외
     */
    public int createPwrMeter(PowerMeter pwrMeter) throws Exception;

    /**
     * 전력량계 수정
     * 
     * @param PowerMeter
     *            전력량계
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePwrMeter(PowerMeter pwrMeter) throws Exception;
}
