package com.she.env.gas.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.DischargeAct;

@Mapper
@Repository("com.she.env.gas.baseInfo.mapper.DischargeActMapper")
public interface DischargeActMapper {

    /**
     * 배출활동코드 조회
     * 
     * @param disClsCd
     *            배츨활동구분
     * @param disActNm
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public List<DischargeAct> getDischargeActs(@Param("disClsCd") String disClsCd, @Param("disActNm") String disActNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 배출활동 체크
     * 
     * @param disActNm
     *            배출활동명
     * @param disActCd
     *            배출활동명
     * @return 배출활동 목록
     * @throws Exception
     */
    public int countDischargeAct(@Param("disActCd") String disActCd);

    /**
     * 배출활동코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public void createDischargeAct(DischargeAct dischargeAct);

    /**
     * 배출활동코드 상세조회
     * 
     * @param disActCd
     *            배출활동코드
     * @return 배출활동코드 상세내역
     * @throws Exception
     */
    public DischargeAct getDischargeAct(@Param("disActCd") String dischargeAct);

    /**
     * 배출활동코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출활동코드
     * @throws Exception
     */
    public void updateDischargeAct(DischargeAct dischargeAct);

}
