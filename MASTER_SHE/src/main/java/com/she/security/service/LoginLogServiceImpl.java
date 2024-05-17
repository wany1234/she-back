package com.she.security.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.security.mapper.LoginLogMapper;
import com.she.security.model.LoginLog;
import com.she.security.model.SearchCond;

@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLog> getLoginLogs(SearchCond cond) {
        return loginLogMapper.getLoginLogs(cond);
    }

    @Override
    @Transactional(rollbackFor = ValidationException.class)
    public Integer createLoginLog(LoginLog loginLog) throws ValidationException {
        return loginLogMapper.createLoginLog(loginLog);
    }

    public void updateLoginLog(String userId) throws Exception {
        loginLogMapper.updateLoginLog(userId);
    }
}
