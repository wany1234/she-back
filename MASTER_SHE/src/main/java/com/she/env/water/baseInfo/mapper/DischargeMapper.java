package com.she.env.water.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.Discharge;

@Mapper
@Repository("com.she.env.water.baseInfo.mapper.DischargeMapper")
public interface DischargeMapper {

    /**
     * 배출수 조회
     * 
     * @param useYn
     *            사용여부
     * @return 배출수 목록
     * @throws Exception
     *             예외
     */
    public List<Discharge> getDischarges(@Param("useYn") String useYn, @Param("plantCd") String plantCd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo, @Param("deptCd") String deptCd, @Param("title") String title, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 배출수 상세조회
     * 
     * @param ewtrDischNo
     *            배출수번호
     * @return Discharge 배출수
     * @throws Exception
     *             예외
     */
    public Discharge getDischarge(@Param("ewtrDischNo") int ewtrDischNo) throws Exception;

    /**
     * 배출수 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public HashMap<String, Object> getDischargeCheck(@Param("plantCd") String plantCd, @Param("ewtrDischClassCd") String ewtrDischClassCd, @Param("ewtrDischNm") String ewtrDischNm, @Param("ewtrDischNo") int ewtrDischNo) throws Exception;

    /**
     * 배출수 신규등록
     * 
     * @param Discharge
     *            배출수
     * @return ewtrDischNo 배출수번호
     * @throws Exception
     *             예외
     */
    public int createDischarge(Discharge discharge) throws Exception;

    /**
     * 배출수 수정
     * 
     * @param Discharge
     *            배출수
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateDischarge(Discharge discharge) throws Exception;
}
