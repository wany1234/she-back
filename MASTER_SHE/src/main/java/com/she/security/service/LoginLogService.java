package com.she.security.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.she.security.model.LoginLog;
import com.she.security.model.SearchCond;

public interface LoginLogService {

    public List<LoginLog> getLoginLogs(SearchCond cond);

    Integer createLoginLog(LoginLog loginLog) throws ValidationException;

}
