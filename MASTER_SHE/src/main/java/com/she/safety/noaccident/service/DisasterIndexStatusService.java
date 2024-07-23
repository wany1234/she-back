/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.safety.noaccident.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.safety.model.DisasterIndexAccident;
import com.she.safety.model.DisasterIndexStatus;
import com.she.safety.noaccident.mapper.DisasterIndexStatusMapper;

@Service
public class DisasterIndexStatusService {
    @Autowired
    private DisasterIndexStatusMapper disasterIndexStatusMapper;

    /**
     * 종합재해지수 현황 목록 조회
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public List<DisasterIndexStatus> getDisasterIndexStatusList(String year, String month)throws Exception{
    	return disasterIndexStatusMapper.getDisasterIndexStatusList(year, month);
    }
    
    /**
     * 사고목록
     * @param plantCd
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    public List<DisasterIndexAccident> getAccidentList(String plantCd, String year, String month) throws Exception {
    	return disasterIndexStatusMapper.getAccidentList(plantCd, year, month);
    }
}
