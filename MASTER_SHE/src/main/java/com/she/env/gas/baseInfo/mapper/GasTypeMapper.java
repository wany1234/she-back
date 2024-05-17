package com.she.env.gas.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.GasType;

@Mapper
@Repository("com.she.env.gas.baseInfo.mapper.GasTypeMapper")
public interface GasTypeMapper {

    /**
     * 배출활동코드 조회
     * 
     * @param gasTypeNm
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public List<GasType> getGasTypes(@Param("gasTypeNm") String gasTypeNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 배출활동 체크
     * 
     * @param gasTypeNm
     *            배출활동명
     * @param gasTypeCd
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public int countGasType(@Param("gasTypeCd") String gasTypeCd);

    /**
     * 배출활동코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public void createGasType(GasType gasType);

    /**
     * 배출활동코드 상세조회
     * 
     * @param gasTypeCd
     *            배출활동코드
     * @return 배출활동코드 상세내역
     * @throws Exception
     */
    public GasType getGasType(@Param("gasTypeCd") String gasTypeCd);

    /**
     * 배출활동코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public void updateGasType(GasType gasType);

}
