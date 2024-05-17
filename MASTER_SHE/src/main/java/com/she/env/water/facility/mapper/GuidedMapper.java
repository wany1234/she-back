package com.she.env.water.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.GuidedHist;

@Mapper
@Repository("com.she.env.water.facility.mapper.GuidedMapper")
public interface GuidedMapper {
    /**
     * 지도점검 조회
     *
     * @param
     * @return 지도점검 목록
     * @throws Exception
     *             예외
     */
    public List<GuidedHist> getGuideds() throws Exception;

    /**
     * 지도점검 상세조회
     *
     * @param ewtrGuidedHistNo
     *            지도점검번호
     * @return GuidedHist 지도점검
     * @throws Exception
     *             예외
     */
    public GuidedHist getGuided(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 지도점검 수정
     *
     * @param GuidedHist
     *            지도점검
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateGuided(GuidedHist guidedHist) throws Exception;
}
