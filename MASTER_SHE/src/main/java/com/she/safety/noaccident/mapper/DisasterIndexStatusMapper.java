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
package com.she.safety.noaccident.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.DisasterIndexAccident;
import com.she.safety.model.DisasterIndexStatus;


@Mapper
@Repository("com.she.safety.accident.mapper.DisasterIndexMapper")
public interface DisasterIndexStatusMapper {
	
	/**
	 * 종합재해지수 현황 목록 조회
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public List<DisasterIndexStatus> getDisasterIndexStatusList(@Param("year") String year, @Param("month") String month) throws Exception;
	
	/**
	 * 사고목록
	 * @param plantCd
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public List<DisasterIndexAccident> getAccidentList(@Param("plantCd") String plantCd, @Param("year") String year, @Param("month") String month) throws Exception;
}
