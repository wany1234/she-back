package com.she.env.gas.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.DischargeFacilityMapper;
import com.she.env.gas.model.DischargeFacility;
import com.she.env.gas.model.DischargeFacilityData;
import com.she.env.gas.model.DischargeFacilityHistory;

@Service
public class DischargeFacilityService {

    @Autowired
    private DischargeFacilityMapper mapper;

    public List<DischargeFacility> getdisFacMasters(String plantCd, String ghgFcltCd, String fcltNm, String useYn, String filterSave, String year) throws Exception {
        return mapper.getdisFacMasters(plantCd, ghgFcltCd, fcltNm, useYn, filterSave, year);

    }

    public DischargeFacility getdisFacMaster(int ghgFcltNo) throws Exception {
        return mapper.getdisFacMaster(ghgFcltNo);
    }

    public List<DischargeFacilityHistory> getHistorys(int ghgFcltNo) throws Exception {
        return mapper.getHistorys(ghgFcltNo);
    }

    public List<DischargeFacilityData> getdisFacDatas(int ghgFcltNo) throws Exception {
        return mapper.getdisFacDatas(ghgFcltNo);
    }

    @Transactional
    public int insertdisFacMaster(DischargeFacility disFacility) throws Exception {
        disFacility.setGhgFcltNo(this.getSequenceNumber());
        mapper.insertdisFacMaster(disFacility);
        this.saveDataMetaInfo(disFacility, "insert");
        return disFacility.getGhgFcltNo();
    }

    public void saveDataMetaInfo(DischargeFacility disFacility, String inflowPath) throws Exception {
        this.deletedisFacilityList(disFacility.getGhgFcltNo());
        if (disFacility.getDisActDataList() != null && disFacility.getDisActDataList().size() > 0) {
            for (DischargeFacilityData disFacData : disFacility.getDisActDataList()) {
                disFacData.setGhgFcltNo(disFacility.getGhgFcltNo());
                disFacData.setCreateUserId(disFacility.getCreateUserId());
                this.insertdisFacilityList(disFacData);
            }
        }
        if (inflowPath == "insert") {
            mapper.deletedisFacilityHistoryList(disFacility.getGhgFcltNo());
        }

        if (inflowPath != "update") {
            DischargeFacilityHistory disFacData = new DischargeFacilityHistory();
            disFacData.setHistoryNo(this.getHisSequenceNumber());
            disFacData.setGhgFcltNo(disFacility.getGhgFcltNo());
            disFacData.setCreateUserId(disFacility.getCreateUserId());
            disFacData.setChgReason(disFacility.getChgReason());
            disFacData.setRevisionNo(disFacility.getRevisionNo());
            mapper.insertdisFacHistory(disFacData);
        }
    }

    public Integer getSequenceNumber() throws Exception {
        return this.mapper.getSequenceNumber();
    }

    public Integer getHisSequenceNumber() throws Exception {
        return this.mapper.getHisSequenceNumber();
    }

    @Transactional
    public int updatedisFacMaster(DischargeFacility disFacility) throws Exception {
        int result = mapper.updatedisFacMaster(disFacility);
        this.saveDataMetaInfo(disFacility, "update");
        return disFacility.getGhgFcltNo();
    }

    @Transactional
    public int revInsertisFacMaster(DischargeFacility disFacility) throws Exception {
        int result = mapper.updatedisFacMaster(disFacility);
        this.saveDataMetaInfo(disFacility, "revInsert");
        return disFacility.getGhgFcltNo();
    }

    @Transactional
    public int insertdisFacilityList(DischargeFacilityData dataList) throws Exception {
        int result = mapper.insertdisFacilityList(dataList);
        return result;
    }

    @Transactional
    public int deletedisFacilityList(int ghgFcltNo) throws Exception {
        int result = mapper.deletedisFacilityList(ghgFcltNo);
        return result;
    }

    @Transactional
    public int deletedisFacilityHistoryList(int ghgFcltNo) throws Exception {
        int result = mapper.deletedisFacilityHistoryList(ghgFcltNo);
        return result;
    }

    public int getDisFacMasterCheck(String fcltNm, int ghgFcltNo) throws Exception {
        return mapper.getDisFacMasterCheck(fcltNm, ghgFcltNo);
    }

    public int deletedisFacMasterHisotry(List<DischargeFacilityHistory> dischargeFacilityHistorys) throws Exception {
        System.out.println("서비스");
        int count = 0;
        for (DischargeFacilityHistory dischargeFacilityHistory : dischargeFacilityHistorys) {
            count += mapper.deletedisFacMasterHisotry(dischargeFacilityHistory);
        }
        return count;
    }
}
