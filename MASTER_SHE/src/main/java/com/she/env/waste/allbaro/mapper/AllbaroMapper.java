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
package com.she.env.waste.allbaro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.waste.model.Allbaro;
import com.she.env.waste.model.DisposalResult;

@Mapper
@Repository("com.she.env.waste.operationLog.mapper.AllbaroMapper")
public interface AllbaroMapper {

    public List<DisposalResult> getAllbaroData() throws Exception;

    public int createAllbaroData(Allbaro allbaro) throws Exception;

    public int createDisposalResult(DisposalResult disposalResult) throws Exception;

    public int updateDisposalResult(DisposalResult disposalResult) throws Exception;

    public int deleteAllbaroData(@Param("allbaroTransNum") String allbaroTransNum) throws Exception;

}