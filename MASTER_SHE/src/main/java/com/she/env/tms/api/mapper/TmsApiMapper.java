package com.she.env.tms.api.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.env.tms.api.mapper.TmsApiMapper")
public interface TmsApiMapper {

    /**
     * [TMS 5분 데이터 저장]
     * 
     * 측정소, 측정항목, 측정일시마다 값이 들어가게 되며 혹 동일한 데이터가 들어오는 경우 update
     */
    public int saveTms5(HashMap<String, Object> map) throws Exception;

    /**
     * [TMS 5분 최근 데이터 저장]
     * 
     * 측정소, 측정항목별로 데이터가 들어가며 해당 값이 존재할 경우 update
     */
    public int saveTms5Recent(HashMap<String, Object> map) throws Exception;

    /**
     * [TMS 30분 데이터 저장]
     * 
     * 측정소, 측정항목, 측정일시마다 값이 들어가게 되며 혹 동일한 데이터가 들어오는 경우 update
     */
    public int saveTms30(HashMap<String, Object> map) throws Exception;

    /**
     * [TMS 30분 최근 데이터 저장]
     * 
     * 측정소, 측정항목별로 데이터가 들어가며 해당 값이 존재할 경우 update
     */
    public int saveTms30Recent(HashMap<String, Object> map) throws Exception;

}
