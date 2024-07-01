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
import com.she.common.service.TemplateService;
import com.she.manage.model.Alarm;
import com.she.manage.model.MailLog;
import com.she.manage.model.User;
import com.she.manage.service.AlarmService;
import com.she.manage.service.LogListService;
import com.she.manage.service.UserService;
import com.she.rsa.model.WorkRiskEvalPlan;
import com.she.rsa.model.WorkRiskEvalPlanDeptList;
import com.she.rsa.workRiskEval.mapper.WorkRiskEvalPlanMapper;
import com.she.utils.ConstVal;
import com.she.utils.SendMailUtil;
import com.she.utils.model.MailResult;
import com.she.utils.model.MailVo;

import sun.misc.BASE64Encoder;

/**
 * 작업위험성평가 기능정의
 *
 */
@Service
public class WorkRiskEvalPlanService {

    @Autowired
    private WorkRiskEvalPlanMapper workRiskEvalPlanMapper;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private LogListService logListService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private UserService userService;

    /**
     * 작업위험성평가 관리 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관리 목록
     * @throws Exception
     */
    public List<WorkRiskEvalPlan> getworkRiskEvalPlanLists(String plantCd, String evalNm, String evalTypeCd, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEvalPlanMapper.getworkRiskEvalPlanLists(plantCd, evalNm, evalTypeCd, startYear, endYear, defaultParam);
    }

    /**
     * 작업위험성평가 관리 등록
     * 
     * @param WorkRiskEvalPlan
     *            작업위험성평가 관리 List
     * @return 작업위험성평가 관리 Key값
     * @throws Exception
     */
    @Transactional
    public String createWorkRiskEvalPlan(WorkRiskEvalPlan workRiskEvalPlan) throws Exception {
        workRiskEvalPlan.setStepCd("PNREG"); // 계획등록

        String newEvalNo = workRiskEvalPlanMapper.getCreateEvalNo(workRiskEvalPlan.getPlantCd(), workRiskEvalPlan.getEvalYear());
        workRiskEvalPlan.setEvalNo(newEvalNo);

        workRiskEvalPlanMapper.createWorkRiskEvalPlan(workRiskEvalPlan);

        if (workRiskEvalPlan.getWorkRiskEvalPlanDeptList() != null && workRiskEvalPlan.getWorkRiskEvalPlanDeptList().size() > 0) {
            for (int i = 0; i < workRiskEvalPlan.getWorkRiskEvalPlanDeptList().size(); i++) {
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setPlantCd(workRiskEvalPlan.getPlantCd());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setEvalYear(workRiskEvalPlan.getEvalYear());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setEvalNo(workRiskEvalPlan.getEvalNo());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setEvalTypeCd(workRiskEvalPlan.getEvalTypeCd());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setRApprRqstNo(workRiskEvalPlan.getApprRqstNo());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setCreateUserId(workRiskEvalPlan.getCreateUserId());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setUpdateUserId(workRiskEvalPlan.getUpdateUserId());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setRsltStepCd("RREGI"); // 평가결과진행상태코드(등록)

                workRiskEvalPlanMapper.createWorkRiskEvalPlanDept(workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i));
            }
        }

        return workRiskEvalPlan.getEvalNo();
    }

    /**
     * 작업위험성평가 관리 수정
     * 
     * @param WorkRiskEvalPlan
     *            작업위험성평가 관리 List
     * @return 작업위험성평가 관리 Key값
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public String updateWorkRiskEvalPlan(WorkRiskEvalPlan workRiskEvalPlan) throws Exception {
        workRiskEvalPlanMapper.updateWorkRiskEvalPlan(workRiskEvalPlan);
        workRiskEvalPlanMapper.deleteWorkRiskEvalPlandetpLists(workRiskEvalPlan.getPlantCd(), workRiskEvalPlan.getEvalYear(), workRiskEvalPlan.getEvalNo());

        if (workRiskEvalPlan.getWorkRiskEvalPlanDeptList() != null && workRiskEvalPlan.getWorkRiskEvalPlanDeptList().size() > 0) {
            for (int i = 0; i < workRiskEvalPlan.getWorkRiskEvalPlanDeptList().size(); i++) {
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setPlantCd(workRiskEvalPlan.getPlantCd());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setEvalYear(workRiskEvalPlan.getEvalYear());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setEvalNo(workRiskEvalPlan.getEvalNo());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setEvalTypeCd(workRiskEvalPlan.getEvalTypeCd());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setRApprRqstNo(workRiskEvalPlan.getApprRqstNo());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setCreateUserId(workRiskEvalPlan.getCreateUserId());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setUpdateUserId(workRiskEvalPlan.getUpdateUserId());
                workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i).setRsltStepCd("RREGI"); // 평가결과진행상태코드(등록)

                workRiskEvalPlanMapper.createWorkRiskEvalPlanDept(workRiskEvalPlan.getWorkRiskEvalPlanDeptList().get(i));
            }
        }

        return workRiskEvalPlan.getEvalNo();
    }

    /**
     * 작업위험성평가 관리 상세
     * 
     * @param assessPlanNo
     *            평가번호
     * @return 작업위험성평가 관리 상세조회
     * @throws Exception
     */
    public WorkRiskEvalPlan getWorkRiskEvalPlanInfo(String plantCd, String evalYear, String evalNo, DefaultParam defaultParam) throws Exception {
        WorkRiskEvalPlan workRiskEvalPlan = workRiskEvalPlanMapper.getWorkRiskEvalPlanInfo(plantCd, evalYear, evalNo, defaultParam);

        return workRiskEvalPlan;
    }

    /**
     * 작업위험성평가 관리 조회
     * 
     * @param evalNo
     *            평가번호
     * @return 작업위험성평가 관리 조회
     * @throws Exception
     */
    public List<WorkRiskEvalPlanDeptList> getWorkRiskEvalPlandetpLists(String plantCd, String evalYear, String evalNo, DefaultParam defaultParam) throws Exception {
        List<WorkRiskEvalPlanDeptList> workRiskEvalPlanDeptList = workRiskEvalPlanMapper.getWorkRiskEvalPlandetpLists(plantCd, evalYear, evalNo, defaultParam);

        return workRiskEvalPlanDeptList;
    }

    /**
     * 작업위험성평가 삭제
     * 
     * @param Planmgmt
     * 
     * @return 작업위험성평가 관리 삭제
     * @throws Exception
     */
    public int deleteWorkRiskEvalPlan(String plantCd, String evalYear, String evalNo) throws Exception {
        return workRiskEvalPlanMapper.deleteWorkRiskEvalPlan(plantCd, evalYear, evalNo);
    }

    /**
     * 작업위험성평가 계획 결재요청 내용 조회 plantCd(사업장코드) evalYear(평가년도) evalNo(평가번호)
     * apprBizCd(결재문서코드)
     * 
     * @return 결재요청 내용 HTML
     * @throws Exception
     */
    public Map<String, String> createApprContents(String plantCd, String evalYear, String evalNo, String apprRqstNm, String apprBizCd, String linkUrl, DefaultParam defaultParam) throws Exception {
        WorkRiskEvalPlan workRiskEvalPlan = workRiskEvalPlanMapper.getWorkRiskEvalPlanInfo(plantCd, evalYear, evalNo, defaultParam);

        Map<String, Object> param = new HashMap<>();
        param.put("model", workRiskEvalPlan);

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
    public int updateAppr(String plantCd, String evalYear, String evalNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int upAppr = workRiskEvalPlanMapper.updateAppr(plantCd, evalYear, evalNo, apprRqstNo, bizApprStepCd);
        DefaultParam defaultParam = new DefaultParam("KR");

        // 결재완료일 경우 평가대상부서 팀장에게 메일발송
        if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            WorkRiskEvalPlan workRiskEvalPlan = workRiskEvalPlanMapper.getWorkRiskEvalPlanInfo(plantCd, evalYear, evalNo, defaultParam);
            List<WorkRiskEvalPlanDeptList> workRiskEvalPlanDeptList = workRiskEvalPlanMapper.getWorkRiskEvalPlandetpLists(plantCd, evalYear, evalNo, defaultParam);
            workRiskEvalPlan.setWorkRiskEvalPlanDeptList(workRiskEvalPlanDeptList);

            this.workRiskSendEmail(workRiskEvalPlan, ConstVal.ALARM_S10016, workRiskEvalPlan.getMgrId());
        }

        return upAppr;
    }

	
	  public int workRiskSendEmail(WorkRiskEvalPlan workRiskEvalPlan, String  alarmCd, String senderId) throws Exception { 
		  int resultNo = 0;
		  
		  if (Strings.isNullOrEmpty(alarmCd) || Strings.isNullOrEmpty(senderId)) {  
			  return 0; 
		  }
		  
		  List<Alarm> alarms = alarmService.getAlarmByAlarmCd(alarmCd);
		  
		  for (Alarm alarm : alarms) { 
			  String mailUrl = alarm.getMailUrl(); 
			  String templateUrl = alarm.getTemplateUrl();
		  
			  String mailTitle = alarm.getAlarmNm(); 
			  String mailContents = ""; 
			  List<User>  receivers = null; 
			  String receiverNms = ""; 
			  String[] receiverEmails = null;
			  String senderNm = ""; 
			  String senderEmail = ""; 
			  List<MailVo> mailVoList = new ArrayList<>(); 
			  List<MailResult> results = null;
			  
			  for (WorkRiskEvalPlanDeptList workRiskEvalPlanDeptList :  workRiskEvalPlan.getWorkRiskEvalPlanDeptList()) {
				  HashMap<String, Object>  param = new HashMap<>(); 
				  param.put("workRiskEvalPlan", workRiskEvalPlan);
				  mailContents = templateService.createMailContents(param, templateUrl);
				  
				  receivers =  userService.getTeamLeader(workRiskEvalPlanDeptList.getDeptCd());
				  receiverNms = String.join(",",  receivers.stream().map(User::getUserNm).toArray(String[]::new));
				  receiverEmails = receivers.stream().map(User::getEmail).toArray(String[]::new);
					  
				  User sender = userService.getUser(senderId); 
				  if (sender != null) {
					  senderNm = sender.getUserNm(); 
					  senderEmail = sender.getEmail(); 
				  }
			  
				  List<MailLog> mailLogs = new ArrayList<>();
			  
				  if (CollectionUtils.isNotEmpty(receivers)) {
					  for (User receiver : receivers)  {
						  MailLog mailLog = new MailLog(); 
						  mailLog.setSenderId(senderId);
						  mailLog.setSenderNm(senderNm);
						  mailLog.setSenderEmail("tog1124@naver.com");
						  mailLog.setReceiverId(receiver.getUserId());
						  mailLog.setReceiverNm(receiver.getUserNm());
						  mailLog.setReceiverEmail(receiver.getEmail());
						  mailLog.setTitle(mailTitle);
						  mailLog.setContent(mailContents); 
						  mailLog.setSendYn("Y");
						  mailLog.setTryCount(1); 
						  mailLog.setHtmlYn("Y");
						  mailLog.setAlarmNo(alarm.getAlarmNo()); 
						  int cnt = 0; 
						  MailLog paramMailLog =  new MailLog(); 
						  paramMailLog.setSenderId(senderId);
					  
						  paramMailLog.setReceiverId(receiver.getUserId());
						  paramMailLog.setTitle(mailTitle); 
						  paramMailLog.setContent(mailContents);
						  paramMailLog.setAlarmNo(alarm.getAlarmNo()); 
						  cnt = logListService.getMailLogConut(paramMailLog); // 동일한 발신자id,수신자id,제목,내용,알람번호 의
						  //메일발송여부가 없으면 메일발송 
						  if (cnt < 1) {
							  mailLogs.add(mailLog); 
						  } 
					  }
						  
					  MailVo mailVo = new MailVo(); 
					  mailVo.setTitle(mailTitle);
					  mailVo.setMailTitle(mailTitle); 
					  mailVo.setContents(mailContents);
					  mailVo.setReceiver(receiverNms);
					  mailVo.setRecipientsEmailAddress(receiverEmails);
					  mailVo.setSenderEmail("tog1124@naver.com"); 
					  mailVo.setMailLogs(mailLogs);
					  mailVo.setLink(mailUrl); 
					  if (mailLogs.size() > 0) {
						  mailVoList.add(mailVo); 
					  }
				  } 
			  }
			  if (mailVoList.size() > 0) {
				  results = SendMailUtil.sendMails(mailVoList); 
			  }
			  if (results != null && !results.isEmpty()) {
				  List<MailLog> logs = new  ArrayList<>(); 
				  for (MailResult mailResult : results) {
					  if (!mailResult.getMailLogs().isEmpty()) {
						  logs.addAll(mailResult.getMailLogs()); 
					  } 
				  }
				  for (MailLog maillog : logs) {
					  resultNo +=  logListService.createMailLog(maillog); 
				  } 
			  } 
		  }
		  return resultNo; 
	  }
	 
}
