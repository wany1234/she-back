package com.she.safety.hazardousRiskMechanism.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.manage.model.AlarmCycle;
import com.she.safety.model.HazardousRiskMechanism;
import com.she.safety.model.SafCheckLog;
import com.she.safety.model.SafCheckMachine;
import com.she.safety.model.SafCheckUser;

@Mapper
@Repository("com.she.safety.hazardousRiskMechanism.mapper.HazardousRiskMechanismMapper")
public interface HazardousRiskMechanismMapper {

    /**
     * 유해위험기계기구 조회
     * 
     * @param parameter
     *            검색조건
     * @return 유해위험기계기구 목록
     * @throws Exception
     */
    public List<SafCheckMachine> getSafCheckMachines(@Param("parameter") HazardousRiskMechanism parameter, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 검사이력 조회
     * 
     * @param parameter
     *            검색조건
     * @return 검사이력 목록
     * @throws Exception
     */
    public List<SafCheckLog> getSearchSafCheckLogs(@Param("parameter") HazardousRiskMechanism parameter) throws Exception;

    /**
     * 검사이력 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 검사이력 목록
     * @throws Exception
     */
    public List<SafCheckLog> getSafCheckLogs(@Param("checkMachineNo") int checkMachineNo) throws Exception;

    /**
     * 알람담당자 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 알람담당자 목록
     * @throws Exception
     */
    public List<SafCheckUser> getSafCheckUsers(@Param("checkMachineNo") int checkMachineNo) throws Exception;

    /**
     * 알람 주기 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 알람 주기 목록
     * @throws Exception
     */
    public List<AlarmCycle> getAlarmCycles(@Param("checkMachineNo") int checkMachineNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 유해위험기계기구 등록
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 유해위험기계기구 키
     * @throws Exception
     */
    public void createSafCheckMachine(SafCheckMachine safCheckMachine) throws Exception;

    /**
     * 검사이력 등록
     * 
     * @param safCheckMachine
     *            검사이력
     * @return 검사이력 키
     * @throws Exception
     */
    public void createSafCheckLog(SafCheckLog safCheckLog) throws Exception;

    /**
     * 알람담당자 등록
     * 
     * @param safCheckUser
     *            알람담당자
     * @return 알람담당자 키
     * @throws Exception
     */
    public void createSafCheckUser(SafCheckUser safCheckUser) throws Exception;

    /**
     * 알람주기 등록
     * 
     * @param safCheckUser
     *            알람담당자
     * @return 알람담당자 키
     * @throws Exception
     */
    public void createAlarmCycs(AlarmCycle alarmCycle) throws Exception;

    /**
     * 유해위험기계기구 상세 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 유해위험기계기구
     * @throws Exception
     */
    public SafCheckMachine getSafCheckMachine(@Param("checkMachineNo") int checkMachineNo) throws Exception;

    /**
     * 유해위험기계기구 수정
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 유해위험기계기구 키
     * @throws Exception
     */
    public void updateSafCheckMachine(SafCheckMachine safCheckMachine) throws Exception;

    /**
     * 점검이력 수정
     * 
     * @param safCheckLog
     *            점검이력
     * @return 점검이력 키
     * @throws Exception
     */
    public int updateSafCheckLog(SafCheckLog safCheckLog) throws Exception;

    /**
     * 점검이력 삭제
     * 
     * @param safCheckLog
     *            점검이력
     * @return 점검이력 키
     * @throws Exception
     */
    public int deleteSafCheckLog(SafCheckLog safCheckLog) throws Exception;

    /**
     * 알람담당자 삭제
     * 
     * @param safCheckUser
     *            알람담당자
     * @return 알람담당자 키
     * @throws Exception
     */
    public int deleteSafCheckUser(SafCheckUser safCheckUser) throws Exception;

    /**
     * 알람담당자 삭제
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 점검이력 키
     * @throws Exception
     */
    public int deleteSafCheckUserAll(SafCheckMachine safCheckMachine) throws Exception;

    /**
     * 점검이력 삭제
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 점검이력 키
     * @throws Exception
     */
    public int deleteSafCheckLogAll(SafCheckMachine safCheckMachine) throws Exception;

    /**
     * 유해위험기계기구 삭제
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 유해위험기계기구 키
     * @throws Exception
     */
    public int deletesafCheckMachine(SafCheckMachine safCheckMachine) throws Exception;

    /**
     * 알람주기 삭제
     * 
     * @param alarmCycle
     *            알람주기
     * @return 알람주기 키
     * @throws Exception
     */
    public void deleteAlarmCycs(int checkMachineNo) throws Exception;

}
