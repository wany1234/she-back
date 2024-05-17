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
package com.she.manage.mapper;

import com.she.common.model.DefaultParam;
import com.she.manage.model.Batch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("com.she.manage.mapper.BatchMapper")
public interface BatchMapper {

    public List<Batch> getBatchs(@Param("batchCd") String batchCd, @Param("batchNm") String batchNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public Batch getBatch(@Param("batchCd") String batchCd) throws Exception;

    public int getBatchCheck(@Param("batchCd") String batchCd, @Param("batchCdSave") String batchCdSave) throws Exception;

    public int insertBatch(Batch batch) throws Exception;

    public int updateBatch(Batch batch) throws Exception;

}