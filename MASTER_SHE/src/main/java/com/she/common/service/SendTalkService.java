package com.she.common.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.SendTalkMapper;

@Service
public class SendTalkService {

    @Autowired
    private SendTalkMapper sendTalkMapper;

    /**
     * 알림톡 전송 프로시져
     * 
     * @param phoneNo
     * @param message
     * @param templateCd
     * @return
     * @throws Exception
     */
    public int sendTalkProcedure(String phoneNo, String message, String templateCd) throws Exception {
        // default: 단건발송
        return sendTalkProcedure(phoneNo, message, templateCd, "R00");
    }

    /**
     * 알림톡 전송 프로시져
     * 
     * @param phoneNo
     * @param message
     * @param templateCd
     * @param jobType
     * @return
     * @throws Exception
     */
    public int sendTalkProcedure(String phoneNo, String message, String templateCd, String jobType) throws Exception {
        // default: 즉시발송
        return sendTalkProcedure(phoneNo, message, templateCd, jobType, "R");
    }

    /**
     * 알림톡 전송 프로시져
     * 
     * @param phoneNo
     * @param message
     * @param templateCd
     * @param jobType
     * @param timeType
     * @return
     * @throws Exception
     */
    public int sendTalkProcedure(String phoneNo, String message, String templateCd, String jobType, String timeType)
            throws Exception {
        if (StringUtils.isBlank(phoneNo) || StringUtils.isBlank(message) || StringUtils.isBlank(templateCd)
                || StringUtils.isBlank(jobType) || StringUtils.isBlank(timeType)) {
            return 0;
        } else {
            phoneNo = phoneNo.replaceAll("-", "");
            return sendTalkMapper.sendTalkProcedure(phoneNo, message, templateCd, jobType, timeType);
        }
    }
}
