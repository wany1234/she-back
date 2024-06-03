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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.AlarmMapper;
import com.she.manage.model.Alarm;

@Service
public class AlarmService {

	@Autowired
	private AlarmMapper alarmMapper;

	/**
	 * 알람리스트 조회
	 *
	 * @param alarmType
	 * @param alarmNm
	 * @return
	 * @throws Exception
	 */
	public List<Alarm> getAlarms(String alarmType, String alarmNm, String useYn) throws Exception {
		return alarmMapper.getAlarms(alarmType, alarmNm, useYn);
	}

	/**
	 * 알람상세 조회
	 *
	 * @param alarmCd
	 * @return
	 * @throws Exception
	 */
	public Alarm getAlarm(int alarmNo) throws Exception {
		return alarmMapper.getAlarm(alarmNo);
	}

	/**
	 * 알람명 중복체크
	 *
	 * @param alarmNo
	 * @param alarmNm
	 * @return
	 * @throws Exception
	 */
	public int getAlarmCheck(int alarmNo, String alarmNm) throws Exception {
		return alarmMapper.getAlarmCheck(alarmNo, alarmNm);
	}

	/**
	 * 알람등록
	 *
	 * @param alarm
	 * @return
	 * @throws Exception
	 */
	public int insertAlarm(Alarm alarm) throws Exception {
		alarmMapper.insertAlarm(alarm);
		return alarm.getAlarmNo();
	}

	/**
	 * 알람수정
	 *
	 * @param alarm
	 * @return
	 * @throws Exception
	 */
	public int updateAlarm(Alarm alarm) throws Exception {
		return alarmMapper.updateAlarm(alarm);
	}
	
	/**
	 * 알람코드로 알람정보 조회
	 * @param alarmCd
	 *          알람코드
	 * @return  알람목록
	 * @throws Exception
	 */
	public List<Alarm> getAlarmByAlarmCd(String alarmCd) throws Exception {
		return alarmMapper.getAlarmByAlarmCd(alarmCd);
	}

}
