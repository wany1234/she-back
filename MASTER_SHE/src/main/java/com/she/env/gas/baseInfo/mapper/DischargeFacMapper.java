package com.she.env.gas.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.DischargeFac;

@Mapper
@Repository("com.she.env.gas.baseInfo.mapper.DischargeFacMapper")
public interface DischargeFacMapper {

    /**
     * 배출시설코드 조회
     * 
     * @param disClsCd
     *            배츨시설구분
     * @param disFacNm
     *            배출시설코드명
     * @return 배출시설 목록
     * @throws Exception
     */
    public List<DischargeFac> getDischargeFacs(@Param("disClsCd") String disClsCd, @Param("disFacNm") String disFacNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 배출시설 체크
     * 
     * @param disFacCd
     *            배출시설코드
     * @return 배출시설 목록
     * @throws Exception
     */
    public int countDischargeFac(@Param("disFacCd") String disFacCd);

    /**
     * 배출시설코드 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드
     * @throws Exception
     */
    public void createDischargeFac(DischargeFac dischargeFac);

    /**
     * 배출시설코드 상세조회
     * 
     * @param disFacCd
     *            배출시설코드
     * @return 배출시설코드 상세내역
     * @throws Exception
     */
    public DischargeFac getDischargeFac(@Param("disFacCd") String disFacCd);

    /**
     * 배출시설코드 수정
     * 
     * @param parameter
     *            검색조건
     * @return 배출시설코드
     * @throws Exception
     */
    public void updateDischargeFac(DischargeFac dischargeFac);

}
