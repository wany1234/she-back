package com.she.env.gas.baseInfo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.baseInfo.mapper.ActDataMasterMapper;
import com.she.env.gas.model.ActDataMaster;

@Service
public class ActDataMasterService {
    @Autowired
    private ActDataMasterMapper mapper;

    public List<ActDataMaster> getActDataMasters(String actClsCd, String actDataCd, String dataLvlCd, String pActDataCd, String useYn) throws Exception {
        return mapper.getActDataMasters(actClsCd, actDataCd, dataLvlCd, pActDataCd, useYn);

    }

    public List<ActDataMaster> getDataCategoryParents(String actDataNm, String actClsCd) throws Exception {
        return mapper.getDataCategoryParents(actDataNm, actClsCd);

    }

    @Transactional
    public String insertActDataMaster(ActDataMaster actDataMaster) throws Exception {
        int result = mapper.insertActDataMaster(actDataMaster);
        return actDataMaster.getActDataCd();
    }

    @Transactional
    public String updateActDataMaster(ActDataMaster actDataMaster) throws Exception {
        int result = mapper.updateActDataMaster(actDataMaster);
        List<ActDataMaster> list = mapper.getLowDataCategories(actDataMaster.getActDataCd());
        if (actDataMaster.getUseYn().equals("Y")) {
            // to do..
        } else {
            for (ActDataMaster lists : list) {
                lists.setUseYn("N");
            }
        }
        for (ActDataMaster lists : list) {
            mapper.updateActDataMaster(lists);
        }
        return actDataMaster.getActDataCd();
    }

    public List<ActDataMaster> getActDataMaster(String actDataCd) throws Exception {
        return mapper.getActDataMaster(actDataCd);
    }

    public int countDataCategory(String actDataCd) throws Exception {
        return mapper.countDataCategory(actDataCd);
    }

    public List<ActDataMaster> getDataCategoryItems(String actDataCd, String pActDataCd, String useYn) throws Exception {
        return mapper.getDataCategoryItems(actDataCd, pActDataCd, useYn);
    }

}
