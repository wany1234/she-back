package com.she.env.water.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.WasteDischFac;

@Mapper
@Repository("com.she.env.water.facility.mapper.WasteDischFacMapper")
public interface WasteDischFacMapper {
    /**
     * 폐수배출시설 조회
     * 
     * @param useYn
     *            사용여부
     * @return 폐수배출시설 목록
     * @throws Exception
     *             예외
     */
    public List<WasteDischFac> getWasteDischFacs(@Param("useYn") String useYn, @Param("deptCd") String deptCd, @Param("plantCd") String plantCd, @Param("ewtrWasteDischFacNm") String ewtrWasteDischFacNm, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 폐수배출시설 상세조회
     * 
     * @param ewtrWasteDischFacNo
     *            폐수배출시설번호
     * @return WasteDischFac 폐수배출시설
     * @throws Exception
     *             예외
     */
    public WasteDischFac getWasteDischFac(@Param("ewtrWasteDischFacNo") int ewtrWasteDischFacNo) throws Exception;

    /**
     * 폐수배출시설 체크
     * 
     * @return 배출수 체크 값
     * @throws Exception
     *             예외
     */
    public int getWasteFacsCheck(@Param("plantCd") String plantCd, @Param("ewtrWasteDischFacNm") String ewtrWasteDischFacNm, @Param("ewtrWasteDischFacNo") int ewtrWasteDischFacNo) throws Exception;

    /**
     * 폐수배출시설 신규등록
     * 
     * @param WasteDischFac
     *            폐수배출시설
     * @return ewtrWasteDischFacNo 폐수배출시설번호
     * @throws Exception
     *             예외
     */
    public int createWasteDischFac(WasteDischFac wasteDischFac) throws Exception;

    /**
     * 폐수배출시설 수정
     * 
     * @param WasteDischFac
     *            폐수배출시설
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateWasteDischFac(WasteDischFac wasteDischFac) throws Exception;
}
