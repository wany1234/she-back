package com.she.safety.hazardousRiskMechanism.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.manage.model.AlarmCycle;
import com.she.safety.hazardousRiskMechanism.mapper.HazardousRiskMechanismMapper;
import com.she.safety.model.HazardousRiskMechanism;
import com.she.safety.model.SafCheckLog;
import com.she.safety.model.SafCheckMachine;
import com.she.safety.model.SafCheckUser;

/**
 * 유해위험기계기구 기능정의
 *
 */
@Service
public class HazardousRiskMechanismService {

    @Autowired
    private HazardousRiskMechanismMapper hazardousRiskMechanismMapper;

    /**
     * 유해위험기계기구 조회
     * 
     * @param parameter
     *            검색조건
     * @return 유해위험기계기구 목록
     * @throws Exception
     */
    public HazardousRiskMechanism getHazardousRiskMechanisms(HazardousRiskMechanism parameter, DefaultParam defaultParam) throws Exception {

        parameter.setSafCheckMachines(hazardousRiskMechanismMapper.getSafCheckMachines(parameter, defaultParam));
        // parameter.setSafCheckLogs(hazardousRiskMechanismMapper.getSearchSafCheckLogs(parameter));

        return parameter;
    }

    /**
     * 유해위험기계기구 등록
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 유해위험기계기구 키
     */
    @Transactional
    public int createHazardousRiskMechanism(SafCheckMachine safCheckMachine) throws Exception {

        // 유해위험기계기구 등록
        hazardousRiskMechanismMapper.createSafCheckMachine(safCheckMachine);

        // 점검이력
        if (safCheckMachine.getSafCheckLogs() != null && safCheckMachine.getSafCheckLogs().size() > 0) {
            for (SafCheckLog safCheckLog : safCheckMachine.getSafCheckLogs()) {
                safCheckLog.setCheckMachineNo(safCheckMachine.getCheckMachineNo());
                safCheckLog.setCreateUserId(safCheckMachine.getCreateUserId());
                hazardousRiskMechanismMapper.createSafCheckLog(safCheckLog);
            }
        }
        // 알람담당자
        if (safCheckMachine.getSafCheckUsers() != null && safCheckMachine.getSafCheckUsers().size() > 0) {
            for (SafCheckUser safCheckUser : safCheckMachine.getSafCheckUsers()) {
                safCheckUser.setCheckMachineNo(safCheckMachine.getCheckMachineNo());
                hazardousRiskMechanismMapper.createSafCheckUser(safCheckUser);
            }
        }
        // 알람 주기
        if (safCheckMachine.getAlarmCycs() != null && safCheckMachine.getAlarmCycs().size() > 0) {
            for (AlarmCycle alarmCycle : safCheckMachine.getAlarmCycs()) {
                alarmCycle.setCheckMachineNo(safCheckMachine.getCheckMachineNo());
                hazardousRiskMechanismMapper.createAlarmCycs(alarmCycle);
            }
        }
        return safCheckMachine.getCheckMachineNo();
    }

    /**
     * 유해위험기계기구 상세 조회
     * 
     * @param committeeConfNo
     *            유해위험기계기구 번호
     * @return 유해위험기계기구
     * @throws Exception
     */
    public SafCheckMachine getHazardousRiskMechanism(int checkMachineNo, DefaultParam defaultParam) throws Exception {
        SafCheckMachine safCheckMachine = hazardousRiskMechanismMapper.getSafCheckMachine(checkMachineNo);

        if (safCheckMachine != null) {
            // 점검이력
            safCheckMachine.setSafCheckLogs(hazardousRiskMechanismMapper.getSafCheckLogs(checkMachineNo));
            // 알람담당자
            safCheckMachine.setSafCheckUsers(hazardousRiskMechanismMapper.getSafCheckUsers(checkMachineNo));
            // 알람주기
            safCheckMachine.setAlarmCycs(hazardousRiskMechanismMapper.getAlarmCycles(checkMachineNo, defaultParam));
        }

        return safCheckMachine;
    }

    /**
     * 유해위험기계기구 수정
     * 
     * @param safCheckMachine
     *            유해위험기계기구
     * @return 유해위험기계기구 키
     */
    @Transactional
    public int updateHazardousRiskMechanism(SafCheckMachine safCheckMachine) throws Exception {

        // 유해위험기계기구 등록
        // 테스트를 위해서 추가 추후 개발 시 삭제

        hazardousRiskMechanismMapper.updateSafCheckMachine(safCheckMachine);

        // 점검이력
        if (safCheckMachine.getSafCheckLogs() != null && safCheckMachine.getSafCheckLogs().size() > 0) {
            for (SafCheckLog safCheckLog : safCheckMachine.getSafCheckLogs()) {
                if (safCheckLog.getCheckLogNo() > 0) {
                    safCheckLog.setUpdateUserId(safCheckMachine.getUpdateUserId());
                    hazardousRiskMechanismMapper.updateSafCheckLog(safCheckLog);
                } else {
                    safCheckLog.setCreateUserId(safCheckMachine.getUpdateUserId());
                    safCheckLog.setCheckMachineNo(safCheckMachine.getCheckMachineNo());
                    hazardousRiskMechanismMapper.createSafCheckLog(safCheckLog);
                }
            }
        }
        // 알람담당자
        if (safCheckMachine.getSafCheckUsers() != null && safCheckMachine.getSafCheckUsers().size() > 0) {
            for (SafCheckUser safCheckUser : safCheckMachine.getSafCheckUsers()) {
                if (safCheckUser.getCheckUserNo() < 1) {
                    safCheckUser.setCheckMachineNo(safCheckMachine.getCheckMachineNo());
                    hazardousRiskMechanismMapper.createSafCheckUser(safCheckUser);
                }
            }
        }

        // 점검이력 삭제
        if (safCheckMachine.getDeleteSafCheckLogs() != null && safCheckMachine.getDeleteSafCheckLogs().size() > 0) {
            for (SafCheckLog safCheckLog : safCheckMachine.getDeleteSafCheckLogs()) {
                if (safCheckLog.getCheckLogNo() > 0) {
                    hazardousRiskMechanismMapper.deleteSafCheckLog(safCheckLog);
                }
            }
        }
        // 알람담당자 삭제
        if (safCheckMachine.getDeleteSafCheckUsers() != null && safCheckMachine.getDeleteSafCheckUsers().size() > 0) {
            for (SafCheckUser safCheckUser : safCheckMachine.getDeleteSafCheckUsers()) {
                if (safCheckUser.getCheckUserNo() > 0) {
                    hazardousRiskMechanismMapper.deleteSafCheckUser(safCheckUser);
                }
            }
        }
        // 알람주기
        if (safCheckMachine.getAlarmCycs() != null && safCheckMachine.getAlarmCycs().size() > 0) {
            hazardousRiskMechanismMapper.deleteAlarmCycs(safCheckMachine.getCheckMachineNo());
            for (AlarmCycle alarmCycle : safCheckMachine.getAlarmCycs()) {
                alarmCycle.setCheckMachineNo(safCheckMachine.getCheckMachineNo());
                hazardousRiskMechanismMapper.createAlarmCycs(alarmCycle);
            }
        }
        return safCheckMachine.getCheckMachineNo();
    }

    /**
     * 유해위험기계기구 삭제
     * 
     * @param safCheckMachines
     *            유해위험기계기구
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteHazardousRiskMechanism(List<SafCheckMachine> safCheckMachines) throws Exception {
        int resultNo = 0;
        if (safCheckMachines != null && safCheckMachines.size() > 0) {
            for (SafCheckMachine safCheckMachine : safCheckMachines) {
                // 알람주기 삭제
                hazardousRiskMechanismMapper.deleteAlarmCycs(safCheckMachine.getCheckMachineNo());
                // 알람담당자 삭제
                hazardousRiskMechanismMapper.deleteSafCheckUserAll(safCheckMachine);

                // 점검이력 삭제
                hazardousRiskMechanismMapper.deleteSafCheckLogAll(safCheckMachine);

                // 유해위험기계기구 삭제
                resultNo += hazardousRiskMechanismMapper.deletesafCheckMachine(safCheckMachine);
            }
        }
        return resultNo;
    }

    /**
     * 검사 이력 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 점검 이력
     * @throws Exception
     */
    public List<SafCheckLog> getSafCheckLog(int checkMachineNo) throws Exception {
        return hazardousRiskMechanismMapper.getSafCheckLogs(checkMachineNo);
    }

    /**
     * 알람 담당자 조회
     * 
     * @param checkMachineNo
     *            유해위험기계기구 번호
     * @return 알람 담당자
     * @throws Exception
     */
    public List<SafCheckUser> getSafCheckUsers(int checkMachineNo) throws Exception {
        return hazardousRiskMechanismMapper.getSafCheckUsers(checkMachineNo);
    }
}
