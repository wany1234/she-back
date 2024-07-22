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
package com.she.mgt.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.mgt.baseInfo.model.DisasterIndex;

@Mapper
@Repository("com.she.mgt.baseInfo.mapper.DisasterIndexMapper")
public interface DisasterIndexMapper {
	
	/**
	 * 종합재해지수 목록
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public List<DisasterIndex> getDisasterIndexList(@Param("year") String year) throws Exception;

	/**
	 * 종합재해지수 상세
	 * @param year
	 * @param plantCd
	 * @return
	 * @throws Exception
	 */
	public DisasterIndex getDisasterIndexDetail(@Param("year") String year, @Param("plantCd") String plantCd) throws Exception;
	
	/**
	 * 종합재해지수 등록
	 * @param disasterIndex
	 * @return
	 * @throws Exception
	 */
	public int insertDisasterIndex(DisasterIndex disasterIndex) throws Exception;
	
	/**
	 * 종합재해지수 수정
	 * @param disasterIndex
	 * @return
	 * @throws Exception
	 */
	public int updateDisasterIndex(DisasterIndex disasterIndex) throws Exception;
}
