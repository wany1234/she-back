package com.she.env.tms.recent.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.tms.model.TmsRecent;

@Mapper
@Repository("com.she.env.tms.recent.mapper.RecentMapper")
public interface RecentMapper {

    /**
     * TMS 현재값 조회
     *
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 현재값 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getTms5Recents(@Param("laws") String[] laws, @Param("vals") String[] vals, @Param("dates") String[] dates, @Param("lawStr") String lawStr, @Param("valStr") String valStr, @Param("dateStr") String dateStr) throws Exception;

    /**
     * TMS 배출구별 측정치, 법적기준 차트 데이터 조회
     *
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 배출구별 측정치, 법적기준 차트 데이터
     * @throws Exception
     */
    public List<TmsRecent> getTms5RecentChart(@Param("itemCode") String itemCode, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd) throws Exception;

    public List<TmsRecent> getTms5RecentLawChart(@Param("itemCode") String itemCode, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd) throws Exception;

}
