package com.she.env.gas.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.DischargeFacility;
import com.she.env.gas.model.DischargeFacilityData;
import com.she.env.gas.model.DischargeFacilityHistory;

@Mapper
@Repository("com.she.env.gas.baseInfo.mapper.DischargeFacilityMapper")
public interface DischargeFacilityMapper {

    public List<DischargeFacility> getdisFacMasters(@Param("plantCd") String plantCd, @Param("ghgFcltCd") String ghgFcltCd, @Param("fcltNm") String fcltNm, @Param("useYn") String useYn, @Param("filterSave") String filterSave, @Param("year") String year) throws Exception;

    public DischargeFacility getdisFacMaster(@Param("ghgFcltNo") int ghgFcltNo) throws Exception;

    public List<DischargeFacilityHistory> getHistorys(@Param("ghgFcltNo") int ghgFcltNo) throws Exception;

    public List<DischargeFacilityHistory> getHistory(@Param("historyNo") int historyNo) throws Exception;

    public List<DischargeFacilityData> getdisFacDatas(@Param("ghgFcltNo") int ghgFcltNo) throws Exception;

    public int insertdisFacMaster(DischargeFacility disFacility) throws Exception;

    public int updatedisFacMaster(DischargeFacility disFacility) throws Exception;

    public int insertdisFacilityList(DischargeFacilityData dataList) throws Exception;

    public int insertdisFacHistory(DischargeFacilityHistory dataList) throws Exception;

    public int getFacCount(@Param("historyNo") int historyNo) throws Exception;

    public int deletedisFacilityList(@Param("ghgFcltNo") int ghgFcltNo) throws Exception;

    public int deletedisFacilityHistoryList(@Param("ghgFcltNo") int ghgFcltNo) throws Exception;

    public Integer getSequenceNumber() throws Exception;

    public Integer getHisSequenceNumber() throws Exception;

    public int getDisFacMasterCheck(@Param("fcltNm") String fcltNm, @Param("ghgFcltNo") int ghgFcltNo) throws Exception;

    public int deletedisFacMasterHisotry(DischargeFacilityHistory dischargeFacilityHistory) throws Exception;
}
