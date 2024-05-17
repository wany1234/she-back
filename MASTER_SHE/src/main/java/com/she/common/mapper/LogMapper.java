package com.she.common.mapper;

import com.she.common.model.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("com.she.common.mapper.LogMapper")
public interface LogMapper {

    int createLog(Log log);
}
