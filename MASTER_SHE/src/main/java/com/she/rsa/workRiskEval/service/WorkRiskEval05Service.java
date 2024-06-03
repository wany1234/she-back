package com.she.rsa.workRiskEval.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
//import com.she.common.service.TemplateService;
import com.she.impr.service.ImprService;
import com.she.manage.model.Alarm;
import com.she.manage.model.MailLog;
import com.she.manage.model.User;
import com.she.manage.service.AlarmService;
import com.she.manage.service.LogListService;
import com.she.manage.service.UserService;
import com.she.rsa.model.WorkRiskEval02Prcs;
import com.she.rsa.model.WorkRiskEval04Appr;
import com.she.rsa.model.WorkRiskEval05Exam;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval02Mapper;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval05Mapper;
import com.she.utils.ConstVal;
import com.she.utils.SendMailUtil;
import com.she.utils.model.MailResult;
import com.she.utils.model.MailVo;

import sun.misc.BASE64Encoder;

/**
 * 작업위험성평가 결재 기능정의
 *
 */
@Service
public class WorkRiskEval05Service {

    @Autowired
    private WorkRiskEval05Mapper workRiskEval05Mapper;

    @Autowired
    private WorkRiskEval02Mapper workRiskEval02Mapper;

//    @Autowired
//    private TemplateService templateService;

    @Autowired
    private ImprService imprService;

    @Autowired
    private LogListService logListService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private UserService userService;

    /**
     * 작업위험성평가 검토 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 검토 목록
     * @throws Exception
     */
    public List<WorkRiskEval04Appr> getworkRiskEval05List(String plantCd, String deptCd, String evalTypeCd, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEval05Mapper.getworkRiskEval05List(plantCd, deptCd, evalTypeCd, startYear, endYear, defaultParam);
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval05CheckRslt(WorkRiskEval05Exam workRiskEval05Exam) throws Exception {

        // 검토결과가 부적합일 경우 대표평가자에게 메일 발송 및 평가결과진행상태를 공정확정으로 변경처리
        if ("FCRCN".equals(workRiskEval05Exam.getChkRsltCd())) {
            DefaultParam defaultParam = new DefaultParam("KR");
            WorkRiskEval02Prcs workRiskEval02Prcs = workRiskEval02Mapper.getWorkRiskEval02Info(workRiskEval05Exam.getPlantCd(), workRiskEval05Exam.getEvalYear(), workRiskEval05Exam.getEvalNo(), workRiskEval05Exam.getDeptCd(), defaultParam);

            // 메일 발송처리(검토자가 대표평가자에게 재평가등록 알림메일)
            String senderId = workRiskEval02Prcs.getChkUserId(); // 검토자
            String receiverId = workRiskEval02Prcs.getDeptEvalUserId(); // 대표평가자

//            this.workRiskSendEmail(workRiskEval02Prcs, ConstVal.ALARM_S10044, senderId, receiverId);

            // 대상공정 초기화 처리
            workRiskEval05Mapper.updateWorkRiskEval05DeptPrcsInit(workRiskEval05Exam);
        }

        workRiskEval05Mapper.updateWorkRiskEval05CheckRslt(workRiskEval05Exam);

        return workRiskEval05Exam.getDeptCd();
    }

	/*
	 * public int workRiskSendEmail(WorkRiskEval02Prcs workRiskEval02Prcs, String
	 * alarmCd, String senderId, String receiverId) throws Exception { int resultNo
	 * = 0;
	 * 
	 * if (Strings.isNullOrEmpty(alarmCd) || Strings.isNullOrEmpty(senderId)) {
	 * return 0; }
	 * 
	 * List<Alarm> alarms = alarmService.getAlarmByAlarmCd(alarmCd);
	 * 
	 * for (Alarm alarm : alarms) { String mailUrl = alarm.getMailUrl(); String
	 * templateUrl = alarm.getTemplateUrl();
	 * 
	 * String alarmTitle = "작업 위험성평가결과(" + workRiskEval02Prcs.getDeptNm() +
	 * ") 검토 반려 알림";
	 * 
	 * String mailTitle = alarmTitle; String mailContents = ""; List<User> receivers
	 * = null; String receiverNms = ""; String[] receiverEmails = null; String
	 * senderNm = ""; String senderEmail = ""; List<MailVo> mailVoList = new
	 * ArrayList<>(); List<MailResult> results = null;
	 * 
	 * HashMap<String, Object> param = new HashMap<>();
	 * param.put("workRiskEval02Prcs", workRiskEval02Prcs); mailContents =
	 * templateService.createMailContents(param, templateUrl); User actUser =
	 * userService.getUser(receiverId); receivers = new ArrayList<>();
	 * receivers.add(actUser); receiverNms = String.join(",",
	 * receivers.stream().map(User::getUserNm).toArray(String[]::new));
	 * receiverEmails =
	 * receivers.stream().map(User::getEmail).toArray(String[]::new);
	 * 
	 * User sender = userService.getUser(senderId); if (sender != null) { senderNm =
	 * sender.getUserNm(); senderEmail = sender.getEmail(); }
	 * 
	 * List<MailLog> mailLogs = new ArrayList<>();
	 * 
	 * if (CollectionUtils.isNotEmpty(receivers)) { for (User receiver : receivers)
	 * { MailLog mailLog = new MailLog(); mailLog.setSenderId(senderId);
	 * mailLog.setSenderNm(senderNm); mailLog.setSenderEmail(senderEmail);
	 * mailLog.setReceiverId(receiver.getUserId());
	 * mailLog.setReceiverNm(receiver.getUserNm());
	 * mailLog.setReceiverEmail(receiver.getEmail()); mailLog.setTitle(mailTitle);
	 * mailLog.setContent(mailContents); mailLog.setSendYn("Y");
	 * mailLog.setTryCount(1); mailLog.setHtmlYn("Y");
	 * mailLog.setAlarmNo(alarm.getAlarmNo()); int cnt = 0; MailLog paramMailLog =
	 * new MailLog(); paramMailLog.setSenderId(senderId);
	 * paramMailLog.setReceiverId(receiver.getUserId());
	 * paramMailLog.setTitle(mailTitle); paramMailLog.setContent(mailContents);
	 * paramMailLog.setAlarmNo(alarm.getAlarmNo()); cnt =
	 * logListService.getMailLogConut(paramMailLog); // 동일한 발신자id,수신자id,제목,내용,알람번호 의
	 * 메일발송여부가 없으면 메일발송 if (cnt < 1) { mailLogs.add(mailLog); } }
	 * 
	 * MailVo mailVo = new MailVo(); mailVo.setTitle(mailTitle);
	 * mailVo.setMailTitle(mailTitle); mailVo.setContents(mailContents);
	 * mailVo.setReceiver(receiverNms);
	 * mailVo.setRecipientsEmailAddress(receiverEmails);
	 * mailVo.setSenderEmail(senderEmail); mailVo.setMailLogs(mailLogs);
	 * mailVo.setLink(mailUrl); if (mailLogs.size() > 0) { mailVoList.add(mailVo); }
	 * }
	 * 
	 * if (mailVoList.size() > 0) { results = SendMailUtil.sendMails(mailVoList); }
	 * if (results != null && !results.isEmpty()) { List<MailLog> logs = new
	 * ArrayList<>(); for (MailResult mailResult : results) { if
	 * (!mailResult.getMailLogs().isEmpty()) {
	 * logs.addAll(mailResult.getMailLogs()); } }
	 * 
	 * for (MailLog maillog : logs) { resultNo +=
	 * logListService.createMailLog(maillog); } } }
	 * 
	 * return resultNo; }
	 */

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval05CheckRsltPrcs(WorkRiskEval05Exam workRiskEval05Exam) throws Exception {

        workRiskEval05Mapper.updateWorkRiskEval05CheckRsltPrcs(workRiskEval05Exam);

        return workRiskEval05Exam.getDeptCd();
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval05CheckRsltPrcsAll(WorkRiskEval05Exam workRiskEval05Exam) throws Exception {
        workRiskEval05Mapper.updateWorkRiskEval05CheckRsltPrcsAll(workRiskEval05Exam);

        return workRiskEval05Exam.getDeptCd();
    }

    /**
     * 작업위험성평가 결과검토 결재요청 내용 조회 plantCd(사업장코드) evalYear(평가년도) evalNo(평가번호)
     * apprBizCd(결재문서코드)
     * 
     * @return 결재요청 내용 HTML
     * @throws Exception
     */
    public Map<String, String> createApprContents(String plantCd, String evalYear, String evalNo, String deptCd, String apprRqstNm, String apprBizCd, String linkUrl, DefaultParam defaultParam) throws Exception {
        WorkRiskEval02Prcs workRiskEval02Prcs = workRiskEval02Mapper.getWorkRiskEval02Info(plantCd, evalYear, evalNo, deptCd, defaultParam);

        Map<String, Object> param = new HashMap<>();
        param.put("model", workRiskEval02Prcs);

//        String html = templateService.createApprHtml(param, apprRqstNm, apprBizCd, linkUrl);
        String html = "";

        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encodeHtml = base64Encoder.encode(html.getBytes(StandardCharsets.UTF_8));

        Map<String, String> returnVal = new HashMap<>();
        returnVal.put("html", html);
        returnVal.put("encodeHtml", encodeHtml);

        return returnVal;
    }

    @Transactional
    public int updateAppr(String plantCd, String evalYear, String evalNo, String deptCd, String bizApprStepCd, int apprRqstNo) throws Exception {

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) { // 반려
            // 1. 대상공정 초기화 처리
            // WorkRiskEval05Exam workRiskEval05Exam = new WorkRiskEval05Exam();
            // workRiskEval05Exam.setPlantCd(plantCd);
            // workRiskEval05Exam.setEvalYear(evalYear);
            // workRiskEval05Exam.setEvalNo(evalNo);
            // workRiskEval05Exam.setDeptCd(deptCd);
            //
            // workRiskEval05Mapper.updateWorkRiskEval05DeptPrcsInit(workRiskEval05Exam);
            //
            // DefaultParam defaultParam = new DefaultParam("KR");
            // WorkRiskEval02Prcs workRiskEval02Prcs =
            // workRiskEval02Mapper.getWorkRiskEval02Info(plantCd, evalYear,
            // evalNo, deptCd, defaultParam);
            //
            // // 메일 발송처리(검토자가 대표평가자에게 재평가등록 알림메일)
            // String senderId = workRiskEval02Prcs.getChkUserId(); // 검토자
            // String receiverId = workRiskEval02Prcs.getDeptEvalUserId(); //
            // 대표평가자
            //
            // this.workRiskSendEmail(workRiskEval02Prcs, ConstVal.ALARM_S10044,
            // senderId, receiverId);

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            String userId = workRiskEval05Mapper.imprActMailSender(plantCd, evalYear, evalNo, deptCd);

            // 1.개선사항은 접수단계로 변경
            String imprActNoList = workRiskEval05Mapper.imprActNoList(plantCd, evalYear, evalNo, deptCd);
            String[] imprActNoArray = imprActNoList.split(",");

            for (int i = 0; i < imprActNoArray.length; i++) {
//                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_JSA, Integer.parseInt(imprActNoArray[i]), userId, ConstVal.ALARM_S10018, userId);
                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_JSA, Integer.parseInt(imprActNoArray[i]), userId);
            }
        }

        return workRiskEval05Mapper.updateAppr(plantCd, evalYear, evalNo, deptCd, apprRqstNo, bizApprStepCd);
    }

}
