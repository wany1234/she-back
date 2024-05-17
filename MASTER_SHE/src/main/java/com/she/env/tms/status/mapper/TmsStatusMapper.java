package com.she.env.tms.status.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.tms.model.TmsStatus;

@Mapper
@Repository("com.she.env.tms.status.mapper.TmsStatusMapper")
public interface TmsStatusMapper {

    /**
     * TMS 현황 조회
     * 
     * @param type
     *            구분
     * @param plantCd
     *            사업장
     * @param startDate
     *            시작일
     * @param endDate
     *            종료일
     * @param stationCode
     *            TMS 측정소코드
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 현황 목록
     * @throws Exception
     */
    public List<TmsStatus> getTms5Status(@Param("tmsType") String tmsType, @Param("plantCd") String plantCd, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("stationCode") String stationCode, @Param("itemCode") String itemCode, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize,
            @Param("orderByExpression") String orderByExpression) throws Exception;

}
