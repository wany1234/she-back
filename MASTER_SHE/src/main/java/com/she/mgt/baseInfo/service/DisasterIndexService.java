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
package com.she.mgt.baseInfo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.mgt.baseInfo.mapper.DisasterIndexMapper;
import com.she.mgt.baseInfo.model.DisasterIndex;

@Service
public class DisasterIndexService {
    @Autowired
    private DisasterIndexMapper disasterIndexMapper;
    
    /**
     * 종합재해지수 목록
     * @param year
     * @return
     * @throws Exception
     */
    public List<DisasterIndex> getDisasterIndexList(String year) throws Exception{
    	return disasterIndexMapper.getDisasterIndexList(year);
    }

    /**
     * 종합재해지수 상세
     * @param year
     * @param plantCd
     * @return
     * @throws Exception
     */
    public DisasterIndex getDisasterIndexDetail(String year, String plantCd) throws Exception {
    	return disasterIndexMapper.getDisasterIndexDetail(year, plantCd);
    }
    
    /**
	 * 종합재해지수 등록
	 * @param disasterIndex
	 * @return
	 * @throws Exception
	 */
	public DisasterIndex insertDisasterIndex(DisasterIndex disasterIndex) throws Exception {
		disasterIndexMapper.insertDisasterIndex(disasterIndex);
		return disasterIndex;
	}
	
	/**
	 * 종합재해지수 수정
	 * @param disasterIndex
	 * @return
	 * @throws Exception
	 */
	public DisasterIndex updateDisasterIndex(DisasterIndex disasterIndex) throws Exception {
		disasterIndexMapper.updateDisasterIndex(disasterIndex);
		return disasterIndex;
	}
}
