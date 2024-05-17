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
package com.she.env.waste.operationStatus.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.waste.model.WesteOperationStatus;

@Mapper
@Repository("com.she.env.waste.operationStatus.mapper.OperationMapper")
public interface WstGenStatusMapper {

    public List<WesteOperationStatus> getOperationLogAmgGens(@Param("plantCd") String plantCd,
                                                             @Param("measureYear") String measureYear,
                                                             @Param("ewstClassCd") String ewstClassCd,
                                                             @Param("ewstWasteNo") String ewstWasteNo,
                                                             @Param("deptCd") String deptCd,
                                                             @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}