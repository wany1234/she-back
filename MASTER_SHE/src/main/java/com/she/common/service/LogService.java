package com.she.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.LogMapper;
import com.she.common.model.Log;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public int createLog(Log log) {
        int logNo = logMapper.createLog(log);
        return log.getLogNo();
    }
}
