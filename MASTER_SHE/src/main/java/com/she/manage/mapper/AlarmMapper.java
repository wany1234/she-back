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
package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.Alarm;
import com.she.manage.model.AlarmLog;
import com.she.manage.model.MailLog;

@Mapper
@Repository("com.she.manage.mapper.AlarmMapper")
public interface AlarmMapper {

    /**
     * 알람 리스트 조회
     *
     * @param alarmType
     * @param alarmNm
     * @return
     * @throws Exception
     */
    public List<Alarm> getAlarms(@Param("alarmType") String alarmType, @Param("alarmNm") String alarmNm, @Param("useYn") String useYn) throws Exception;

    /**
     * 알람 리스트 상세조회
     *
     * @param alarmCd
     * @return
     * @throws Exception
     */
    public Alarm getAlarm(@Param("alarmNo") int alarmNo) throws Exception;

    /**
     * 알람명 중복체크
     *
     * @param alarmNo
     * @param alarmNm
     * @return
     * @throws Exception
     */
    public int getAlarmCheck(@Param("alarmNo") int alarmNo, @Param("alarmNm") String alarmNm) throws Exception;

    /**
     * 알람 수정
     *
     * @param alarm
     * @return
     * @throws Exception
     */
    public int updateAlarm(Alarm alarm) throws Exception;

    /**
     * 알람 등록
     *
     * @param alarm
     * @return
     * @throws Exception
     */
    public int insertAlarm(Alarm alarm) throws Exception;

    public int createAlarmLog(AlarmLog alarm) throws Exception;

    public int createMailLog(MailLog mail) throws Exception;

    public List<AlarmLog> getFailAlarms() throws Exception;

    public List<MailLog> getFailMails() throws Exception;

    public int updateAlarmLog(AlarmLog alarm) throws Exception;

    public int updateMailLog(MailLog mail) throws Exception;
    
    /**
     * 알람코드로 알람정보 조회
     * @param alarmCd
     *          알람코드
     * @return  알람목록
     * @throws Exception
     */
    public List<Alarm> getAlarmByAlarmCd(@Param("alarmCd") String alarmCd) throws Exception;

}