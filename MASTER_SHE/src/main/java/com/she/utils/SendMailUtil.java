package com.she.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.she.utils.model.MailResult;
import com.she.utils.model.MailVo;

public class SendMailUtil {

    private static final Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SMTP_HOST_ID = "bosswany@gmail.com";
    private static final String SMTP_HOST_PASSWORD = "iyrqgrjwhceeryjn";
    private static final String SMTP_ENCODING = "UTF-8";
    private final static String systemName = "[안전보건 관리시스템]";

    /**
     * 파일내용가져오기
     *
     * @param fileName
     * @return
     */
    private static String getFileContent(String fileName) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("mail/" + fileName);
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            reader.close();

            return sb.toString();
        } catch (Exception e) {
            return "";
        }
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
    public static String makeMailContent(String mailContent) throws Exception {
        String content = getFileContent("common.html");
        logger.error("## html content : " + content);

        if (content != null && !"".equals(content)) {
            content = content.replace("[$MAIL_CONTENT$]", mailContent);
            return content;
        } else {
            return mailContent;
        }

    }

    /**
     * 메일 전송
     *
     * @param mailVo
     * @return
     * @throws MessagingException
     */
    public static MailResult sendMail(MailVo mailVo) {
        MailResult result = new MailResult();

        try {
            // 서버 확인
            Properties properties = new Properties();
            String resource = "application.properties";
            Reader reader = Resources.getResourceAsReader(resource);
            properties.load(reader);
            String active = properties.getProperty("spring.profiles.active");
            System.out.println("===active===" + active);

            // if (!"prd".equals(active)) {
            if ("dev".equals(active)) {
                result.setResultCd("SUCCESS"); // 결과코드
                result.setResultMsg("개발은 메일 서비스 이용에 제한됩니다."); // 결과메세지
            } else {
                try {
                    Properties props = new Properties();
                    props.put("mail.use", "true");
                    props.put("mail.smtp.host", SMTP_HOST_NAME);
                    props.put("mail.smtp.port", SMTP_PORT);
                    props.put("mail.host.auth.flag", "true");
                    props.put("mail.smtp.encoding", SMTP_ENCODING);
                    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(SMTP_HOST_ID, SMTP_HOST_PASSWORD);
                        }
                    });

                    MimeMessage msg = new MimeMessage(session);
                    // msg.setFrom(new
                    // InternetAddress("pe911642@poscoict.com"));
                    msg.setFrom(new InternetAddress(mailVo.getSenderEmail()));

                    String[] recipients = mailVo.getRecipientsEmailAddress();
                    InternetAddress[] addressTo = new InternetAddress[recipients.length];
                    int i = 0;
                    for (String recipient : recipients) {
                        addressTo[i] = new InternetAddress(recipient);
                        i++;
                    }
                    msg.setRecipients(Message.RecipientType.TO, addressTo);
                    msg.setSubject(systemName + " " + mailVo.getTitle(), "utf-8");
                    msg.setSubject(mailVo.getTitle(), "utf-8");
                    msg.setContent(makeMailContent(mailVo.getContents()), "text/html;charset=euc-kr");
                    msg.setHeader("Content-Transfer-Encoding", "base64");

                    Transport transport = session.getTransport();

                    try {
                        Transport.send(msg);

                        result.setResultCd("SUCCESS"); // 결과코드
                        result.setResultMsg("메일이 성공적으로 발송되었습니다."); // 결과메세지
                    } catch (Exception e) {
                        result.setResultCd("FAILURE"); // 결과코드
                        result.setResultMsg("메일이 발송중 오류가 발생했습니다. message: " + e.getMessage()); // 결과메세지
                    } finally {
                        // Close and terminate the connection.
                        transport.close();
                    }
                } catch (Exception e) {
                    result.setResultCd("FAILURE"); // 결과코드
                    result.setResultMsg("메일이 발송중 오류가 발생했습니다. message: " + e.getMessage()); // 결과메세지
                }
            }
        } catch (Exception e) {
            result.setResultCd("FAILURE"); // 결과코드
            result.setResultMsg("메일이 발송중 오류가 발생했습니다. message: " + e.getMessage()); // 결과메세지
        }
        return result;
    }
}
