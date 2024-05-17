package com.she.env.gas.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.GasUnit;

@Mapper
@Repository("com.she.env.gas.baseInfo.mapper.GasUnitMapper")
public interface GasUnitMapper {

    /**
     * 배출활동코드 조회
     * 
     * @param unitNm
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public List<GasUnit> getGasUnits(@Param("unitClsCd") String unitClsCd, @Param("unitNm") String unitNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 배출활동 체크
     * 
     * @param unitCd
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public int countGasUnit(@Param("unitCd") String unitCd);

    /**
     * 배출활동코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public void createGasUnit(GasUnit gasUnit);

    /**
     * 배출활동코드 상세조회
     * 
     * @param unitCd
     *            배출활동코드
     * @return 배출활동코드 상세내역
     * @throws Exception
     */
    public GasUnit getGasUnit(@Param("unitCd") String unitCd);

    /**
     * 배출활동코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public void updateGasUnit(GasUnit gasUnit);

    /**
     * 배출활동코드 조회
     * 
     * @param unitNm
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public List<GasUnit> getGasUnitCdNms() throws Exception;

}
