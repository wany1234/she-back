package com.she.security.mapper;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.security.model.LoginLog;
import com.she.security.model.SearchCond;

@Mapper
@Repository("com.she.security.mapper.LoginLogMapper")
public interface LoginLogMapper {
    public List<LoginLog> getLoginLogs(@Param("cond") SearchCond cond);

    public Integer createLoginLog(LoginLog loginLog) throws ValidationException;

    public void updateLoginLog(String userId) throws Exception;
}
