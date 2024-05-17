package com.she.env.water.facility.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.water.model.PreventFac;

@Mapper
@Repository("com.she.env.water.facility.mapper.PreventFacMapper")
public interface PreventFacMapper {
    /**
     * 방지시설 조회
     * 
     * @param useYn
     *            사용여부
     * @return 방지시설 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFac> getPreventFacs(@Param("useYn") String useYn, @Param("deptCd") String deptCd,
            @Param("plantCd") String plantCd, @Param("ewtrPreventFacNm") String ewtrPreventFacNm, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 방지시설 상세조회
     * 
     * @param ewtrPreventFacNo
     *            방지시설번호
     * @return PreventFac 방지시설
     * @throws Exception
     *             예외
     */
    public PreventFac getPreventFac(@Param("ewtrPreventFacNo") int ewtrPreventFacNo) throws Exception;

    /**
     * 방지시설 신규등록
     * 
     * @param PreventFac
     *            방지시설
     * @return ewtrPreventFacNo 방지시설번호
     * @throws Exception
     *             예외
     */
    public int createPreventFac(PreventFac preventFac) throws Exception;

    /**
     * 방지시설 수정
     * 
     * @param PreventFac
     *            방지시설
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePreventFac(PreventFac preventFac) throws Exception;
}
