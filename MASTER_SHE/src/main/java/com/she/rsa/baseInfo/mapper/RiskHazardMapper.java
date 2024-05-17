package com.she.rsa.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.rsa.model.RiskHazard;

@Mapper
@Repository("com.she.rsa.baseInfo.mapper.RiskHazardMapper")
public interface RiskHazardMapper {

    /**
     * 유해위험요인 조회
     * 
     * @param priskHazardNo
     *            유해위험요인 Lv1
     * @param riskHazardNm
     *            유해위험요인 Lv2
     * @param riskHazardLevel
     *            유해위험요소 레벨
     * @return 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getRiskHazards(@Param("priskHazardNo") int priskHazardNo,
            @Param("riskHazardNm") String riskHazardNm, @Param("riskHazardLevel") int riskHazardLevel,
            @Param("useYn") String useYn) throws Exception;

    /**
     * 유해위험요인 상세 조회
     * 
     * @param riskHazardNo
     *            유해위험요인 번호
     * @return 유해위험요인
     * @throws Exception
     */
    public RiskHazard getRiskHazard(@Param("riskHazardNo") int riskHazardNo) throws Exception;

    /**
     * 유해위험요인 신규등록
     * 
     * @param riskHazard
     *            유해위험요인
     * @return 등록 행 수
     * @throws Exception
     */
    public int createRiskHazard(RiskHazard riskHazard) throws Exception;

    /**
     * 유해위험요인 명칭 체크
     *
     * @param priskHazardNo
     *            상위 번호
     * @param riskHazardNo
     *            유해위험요인 번호
     * @param riskHazardNm
     *            유해위험요인명
     * @return 수정 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> getRiskHazardCheck(@Param("priskHazardNo") int priskHazardNo,
            @Param("riskHazardNo") int riskHazardNo, @Param("riskHazardNm") String riskHazardNm) throws Exception;

    /**
     * 유해위험요인 수정
     * 
     * @param riskHazard
     *            유해위험요인
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateRiskHazard(RiskHazard riskHazard) throws Exception;

}
