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
package com.she.manage.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.manage.mapper.LogListMapper;
import com.she.manage.model.AlarmLog;
import com.she.manage.model.BatchLog;
import com.she.manage.model.CrudLog;
import com.she.manage.model.ErrorLog;
import com.she.manage.model.ImpoAction;
import com.she.manage.model.MailLog;
import com.she.manage.model.MenuLog;
import com.she.security.model.LoginLog;
import com.she.utils.SendMailUtil;
import com.she.utils.SendSmsUtil;
import com.she.utils.model.MailResult;
import com.she.utils.model.MailVo;
import com.she.utils.model.SmsResult;
import com.she.utils.model.SmsVo;

@Service
public class LogListService {
    @Autowired
    private LogListMapper logListMapper;

    private static final Logger logger = LoggerFactory.getLogger(LogListService.class);

    /**
     * 파일내용가져오기
     *
     * @param fileName
     * @return
     */
    private String getFileContent(String fileName) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("mail/" + fileName);
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "euc-kr"));

            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();

            return sb.toString();
        } catch (IOException ie) {
            logger.error(ie.getMessage());
            return "";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "";
        }
    }

    /**
     * 개발 메일HTML 내용만들기
     *
     * @param fileName
     * @param keyValue
     * @return
     * @throws Exception
     */
    public String makeContent(String fileName, HashMap<String, String> keyValue) throws Exception {
        String content = this.getFileContent(fileName);

        for (String key : keyValue.keySet()) {
            content = content.replace(key, keyValue.get(key));
        }

        return content;
    }

    /**
     * 메일HTML 내용만들기
     *
     * @param subTitle
     * @param mailDesc
     * @param mailContent
     * @param linkVisible
     * @return
     * @throws Exception
     */
    public String makeMailContent(String subTitle, String mailDesc, String mailContent, boolean linkVisible) throws Exception {
        String content = this.getFileContent("common.html");
        content = content.replace("[$SUB_TITLE$]", subTitle);
        content = content.replace("[$MAIL_DESC$]", mailDesc);
        content = content.replace("[$MAIL_CONTENT$]", mailContent);
        content = content.replace("[$LINK_VISIBLE$]", linkVisible ? "" : "display:none;");

        return content;
    }

    public List<LoginLog> getLoginLogList(String fromDt, String toDt, String deptCd, String plantCd) throws Exception {
        return logListMapper.getLoginLogList(fromDt, toDt, deptCd, plantCd);
    }

    public List<ErrorLog> getErrorLogList(String fromDt, String toDt) throws Exception {
        return logListMapper.getErrorLogList(fromDt, toDt);
    }

    public ErrorLog getErrorLogDetail(int logNo) throws Exception {
        return logListMapper.getErrorLogDetail(logNo);
    }

    public List<MailLog> getMailLogs(String fromDt, String toDt, String keyword) throws Exception {
        List<MailLog> result = logListMapper.getMailLogs(fromDt, toDt, keyword);
        if (CollectionUtils.isNotEmpty(result)) {
            for (MailLog mailLog : result) {
                mailLog.setContentHtml(SendMailUtil.makeMailContent(mailLog.getContents()));
            }
        }
        return result;
    }

    @Transactional
    public HashMap<String, Integer> resendMailLog(List<MailLog> mailLogs) throws Exception {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        int successCount = 0;
        int failCount = 0;
        if (CollectionUtils.isNotEmpty(mailLogs)) {
            for (MailLog mailLog : mailLogs) {
                MailVo mailVo = new MailVo();

                mailVo.setTitle(mailLog.getTitle());
                mailVo.setContents(mailLog.getContents());
                mailVo.setRecipientsEmailAddress(new String[] { mailLog.getReceiverEmail() });
                mailVo.setSender(mailLog.getSenderId());
                mailVo.setSenderEmail(mailLog.getSenderEmail());

                MailResult mailResult = SendMailUtil.sendMail(mailVo);
                if ("SUCCESS".equals(mailResult.getResultCd())) {
                    mailLog.setSendYn("Y");
                    successCount++;
                } else {
                    mailLog.setSendYn("N");
                    mailLog.setTryCount(mailLog.getTryCount() + 1);
                    mailLog.setFailDesc(mailResult.getResultMsg());
                    failCount++;
                }
                this.logListMapper.resendMailLog(mailLog);
            }
        }
        result.put("successCount", successCount);
        result.put("failCount", failCount);
        return result;
    }

    public List<AlarmLog> getSmsLogs(String fromDt, String toDt, String word) throws Exception {
        return logListMapper.getSmsLogs(fromDt, toDt, word);
    }

    @Transactional
    public HashMap<String, Integer> resendSmsLog(List<AlarmLog> alarmLogs) throws Exception {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        int successCount = 0;
        int failCount = 0;
        if (CollectionUtils.isNotEmpty(alarmLogs)) {
            for (AlarmLog alarmLog : alarmLogs) {
                SmsVo smsVo = new SmsVo();

                smsVo.setMsg(alarmLog.getContents());
                smsVo.setSndr(alarmLog.getSenderId());
                smsVo.setCallback(alarmLog.getSenderPhoneNo());
                smsVo.setRcvr(alarmLog.getReceiverId());
                smsVo.setRcvrnum(alarmLog.getReceiverPhoneNo());

                SmsResult smsResult = SendSmsUtil.sendSms(smsVo);
                if ("SUCCESS".equals(smsResult.getResultCd())) {
                    alarmLog.setSendYn("Y");
                    successCount++;
                } else {
                    alarmLog.setSendYn("N");
                    alarmLog.setTryCount(alarmLog.getTryCount() + 1);
                    alarmLog.setFailDesc(smsResult.getResultMsg());
                    failCount++;
                }
                this.logListMapper.resendSmsLog(alarmLog);
            }
        }
        result.put("successCount", successCount);
        result.put("failCount", failCount);
        return result;
    }

    public List<BatchLog> getBatchLogs(String fromDt, String toDt) {
        return logListMapper.getBatchLogs(fromDt, toDt);
    }

    public void insertMenuLog(String menuId, String menuNm, String menuPath, String url, String createUserId) throws Exception {
        logListMapper.insertMenuLog(menuId, menuNm, menuPath, url, createUserId);
    }

    public List<MenuLog> getMenuLog(String menuNm, String fromDt, String toDt) throws Exception {
        return logListMapper.getMenuLog(menuNm, fromDt, toDt);
    }

    public ImpoAction getImpoConform(String servletPath, String crudCd) throws Exception {
        return logListMapper.getImpoConform(servletPath, crudCd);
    }

    public void insertCrudLog(String crudCd, String crudNm, String crudDesc, String userId, String url, String option) throws Exception {
        logListMapper.insertCrudLog(crudCd, crudNm, crudDesc, userId, url, option);
    }

    public List<CrudLog> getCrudLog(String crudNm, String fromDt, String toDt) throws Exception {
        return logListMapper.getCrudLog(crudNm, fromDt, toDt);
    }

    public List<ImpoAction> getImpoActionList() throws Exception {
        return logListMapper.getImpoActionList();
    }

    public void insertImpoAction(String servletPath, String crudCd, String crudNm, String crudDesc, String useYn) throws Exception {
        logListMapper.insertImpoAction(servletPath, crudCd, crudNm, crudDesc, useYn);
    }

    public void updateImpoAction(String servletPath, String crudCd, String crudNm, String crudDesc, String useYn) throws Exception {
        logListMapper.updateImpoAction(servletPath, crudCd, crudNm, crudDesc, useYn);
    }
    
    public int createMailLog(MailLog mailLog) throws Exception {
        return logListMapper.createMailLog(mailLog);
    }
    
    /**
     * 메일발송여부 조회
     *
     * @param MailLog
     * @return int
     * @throws Exception
     */
    public int getMailLogConut(MailLog mailLog) throws Exception {
        return logListMapper.getMailLogConut(mailLog);
    }
}
