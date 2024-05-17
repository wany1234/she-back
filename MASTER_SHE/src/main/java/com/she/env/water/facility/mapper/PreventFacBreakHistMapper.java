package com.she.env.water.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.PreventFacBreakHist;

@Mapper
@Repository("com.she.env.water.facility.mapper.PreventFacBreakHistMapper")
public interface PreventFacBreakHistMapper {
    /**
     * 고장이력정보 조회
     * 
     * @param ewtrPreventFacNo
     *            오염방지시설번호
     * @param measureYmd
     *            기준일자
     * @return 고장이력정보 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFacBreakHist> getPreventFacBreakHists(@Param("ewtrPreventFacNo") int ewtrPreventFacNo,
            @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 고장이력정보 상세조회
     * 
     * @param ewtrPreventFacBreakHistNo
     *            고장이력정보번호
     * @return PreventFacBreakHist 고장이력정보
     * @throws Exception
     *             예외
     */
    public PreventFacBreakHist getPreventFacBreakHist(@Param("ewtrPreventFacBreakHistNo") int ewtrPreventFacBreakHistNo)
            throws Exception;

    /**
     * 고장이력정보 신규등록
     * 
     * @param PreventFacBreakHist
     *            고장이력정보
     * @return ewtrPreventFacBreakHistNo 고장이력정보번호
     * @throws Exception
     *             예외
     */
    public int createPreventFacBreakHist(PreventFacBreakHist preventFacBreakHist) throws Exception;

    /**
     * 고장이력정보 수정
     * 
     * @param PreventFacBreakHist
     *            고장이력정보
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePreventFacBreakHist(PreventFacBreakHist preventFacBreakHist) throws Exception;

    /**
     * 고장이력정보 삭제
     * 
     * @param ewtrPreventFacBreakHistNo
     *            고장이력정보번호
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    public int deletePreventFacBreakHist(@Param("ewtrPreventFacBreakHistNo") int ewtrPreventFacBreakHistNo)
            throws Exception;
}
