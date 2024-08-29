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
package com.she.safety.emergency.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.impr.service.ImprService;
import com.she.safety.emergency.mapper.EmergencyMapper;
import com.she.safety.model.Emergency;
import com.she.safety.model.EmergencyDept;
import com.she.safety.model.EmergencyOutsidePsn;
import com.she.safety.model.EmergencyPsn;
import com.she.utils.ConstVal;

@Service
public class EmergencyService {
    @Autowired
    private EmergencyMapper emergencyMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 훈련계획 관리 목록 조회
     *
     * @param parameter
     * @return 훈련계획 관리 목록 조회
     * @throws Exception
     */
    public List<Emergency> getEmergencyLists(String plantCd, String startDt, String endDt, String trainTypeCd, String trainNm, String trainPlace, String deptCd, String deptSubYn, String trainPlanState, String reTrainYn, String trainMethodCd, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyLists(plantCd, startDt, endDt, trainTypeCd, trainNm, trainPlace, deptCd, deptSubYn, trainPlanState, reTrainYn, trainMethodCd, defaultParam);
    }

    /**
     * 훈련계획 관리 계획 조회
     *
     * @param parameter
     * @return 훈련계획 관리 계획 조회
     * @throws Exception
     */
    public Emergency getEmergencyInfo(int safTrainPlanNo, DefaultParam defaultParam) throws Exception {
        Emergency emergency = new Emergency();
        emergency = emergencyMapper.getEmergencyInfo(safTrainPlanNo, defaultParam);
        emergency.setEmergencyPlantList(emergencyMapper.getEmergencyPlantList(safTrainPlanNo));
        emergency.setEmergencyUserList(emergencyMapper.getEmergencyUserList(safTrainPlanNo, null, defaultParam));
        emergency.setEmergencyScenarioList(emergencyMapper.getEmergencyScenarioLists(safTrainPlanNo, defaultParam));
        if (ConstVal.TRAIN_METHOD_VIDEO.equals(emergency.getTrainMethodCd())) {
            emergency.setEmergencyVideo(emergencyMapper.getEmergencyVideo(safTrainPlanNo));
        }
        return emergency;
    }

    /**
     * 훈련계획 대상자 목록 조회
     *
     * @param parameter
     * @return 훈련계획 대상자 목록 조회
     * @throws Exception
     */
    public List<EmergencyPsn> getEmergencyUserList(int safTrainPlanNo, String apprFlag, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyUserList(safTrainPlanNo, apprFlag, defaultParam);
    }

    /**
     * 훈련계획 관리 계획 등록
     *
     * @param parameter
     * @return 훈련계획 관리 계획 조회
     * @throws Exception
     */
    @Transactional
    public int createEmergency(Emergency emergency) throws Exception {

        emergency.setTrainSTime(emergency.getTrainSHour() + ":" + emergency.getTrainSMin());
        emergency.setTrainETime(emergency.getTrainEHour() + ":" + emergency.getTrainEMin());
        emergency.setTrainPlanState(ConstVal.TRAIN_PLAN_STATE_ING); // 계획(작성중)
        emergencyMapper.createEmergency(emergency);
        int primaryKey = emergency.getSafTrainPlanNo();

        if (emergency.getPlantCdList() != null && emergency.getPlantCdList().size() > 0) {
            for (int i = 0; i < emergency.getPlantCdList().size(); i++) {
                String plantCd = emergency.getPlantCdList().get(i);
                emergencyMapper.createEmergencyPlant(primaryKey, plantCd);
            }
        }
        if (emergency.getEmergencyUserList() != null && emergency.getEmergencyUserList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyUserList().size(); i++) {
                emergency.getEmergencyUserList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyUser(emergency.getEmergencyUserList().get(i));
            }
        }
        emergencyMapper.deleteEmergencyScenario(primaryKey);
        if (emergency.getEmergencyScenarioList() != null & emergency.getEmergencyScenarioList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyScenarioList().size(); i++) {
                emergency.getEmergencyScenarioList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyScenario(emergency.getEmergencyScenarioList().get(i));
            }
        }

        if (ConstVal.TRAIN_METHOD_VIDEO.equals(emergency.getTrainMethodCd())) {
            emergency.getEmergencyVideo().setSafTrainPlanNo(primaryKey);
            emergency.getEmergencyVideo().setCreateUserId(emergency.getCreateUserId());
            emergencyMapper.createEmergencyVideo(emergency.getEmergencyVideo());
        }

        return emergency.getSafTrainPlanNo();
    }

    /**
     * 훈련계획 관리 계획 수정
     *
     * @param parameter
     * @return 훈련계획 관리 계획 수정
     * @throws Exception
     */
    @Transactional
    public int updateEmergency(Emergency emergency) throws Exception {
        emergency.setTrainSTime(emergency.getTrainSHour() + ":" + emergency.getTrainSMin());
        emergency.setTrainETime(emergency.getTrainEHour() + ":" + emergency.getTrainEMin());
        emergencyMapper.updateEmergency(emergency);
        int primaryKey = emergency.getSafTrainPlanNo();

        emergencyMapper.deleteEmergencyPlant(primaryKey);
        if (emergency.getPlantCdList() != null && emergency.getPlantCdList().size() > 0) {
            for (int i = 0; i < emergency.getPlantCdList().size(); i++) {
                String plantCd = emergency.getPlantCdList().get(i);
                emergencyMapper.createEmergencyPlant(primaryKey, plantCd);
            }
        }
        emergencyMapper.deleteEmergencyUser(primaryKey);
        if (emergency.getEmergencyUserList() != null && emergency.getEmergencyUserList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyUserList().size(); i++) {
                emergency.getEmergencyUserList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyUser(emergency.getEmergencyUserList().get(i));
            }
        }
        emergencyMapper.deleteEmergencyScenario(primaryKey);
        if (emergency.getEmergencyScenarioList() != null & emergency.getEmergencyScenarioList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyScenarioList().size(); i++) {
                emergency.getEmergencyScenarioList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyScenario(emergency.getEmergencyScenarioList().get(i));
            }
        }

        if (ConstVal.TRAIN_METHOD_VIDEO.equals(emergency.getTrainMethodCd())) {
            if (emergency.getEmergencyVideo().getSafTrainPlanNo() == 0) {
                emergency.getEmergencyVideo().setSafTrainPlanNo(primaryKey);
                emergency.getEmergencyVideo().setCreateUserId(emergency.getCreateUserId());
                emergencyMapper.createEmergencyVideo(emergency.getEmergencyVideo());
            } else {
                emergency.getEmergencyVideo().setUpdateUserId(emergency.getUpdateUserId());
                emergencyMapper.updateEmergencyVideo(emergency.getEmergencyVideo());
            }
        } else {
            emergencyMapper.deleteEmergencyVideo(primaryKey);
        }

        return emergency.getSafTrainPlanNo();
    }

    /**
     * 훈련계획 관리 계획 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 계획 삭제
     * @throws Exception
     */
    public int deleteEmergency(int safTrainPlanNo) throws Exception {
        return emergencyMapper.deleteEmergency(safTrainPlanNo);
    }

    /**
     * 훈련계획 관리 대상부서 삭제
     *
     * @param parameter
     * @return 훈련계획 관리 대상부서 삭제
     * @throws Exception
     */
    public int deleteEmergencyDept(List<EmergencyDept> emergencyDept) throws Exception {
        int deleteCnt = 0;
        if (emergencyDept != null && emergencyDept.size() > 0) {
            for (int i = 0; i < emergencyDept.size(); i++) {
                deleteCnt += emergencyMapper.deleteEachEmergencyDept(emergencyDept.get(i).getSafTrainDeptRsltNo());
            }
        }
        return deleteCnt;
    }

    /**
     * 훈련계획 관리 계획 완료처리
     *
     * @param parameter
     * @return 훈련계획 관리 계획 완료처리
     * @throws Exception
     */
    public int updateEmergencyComplete(int safTrainPlanNo) throws Exception {
        return emergencyMapper.updateEmergencyComplete(safTrainPlanNo);
    }

    /**
     * 훈련결과 관리 목록 조회
     *
     * @param parameter
     * @return 훈련결과 관리 목록 조회
     * @throws Exception
     */
    public List<EmergencyDept> getEmergencyResultLists(String plantCd, String startDt, String endDt, String trainTypeCd, String trainNm, String trainPlace, String deptCd, String deptSubYn, String trainPlanState, int monFlag, String year, String gubun, String imprChk, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyResultLists(plantCd, startDt, endDt, trainTypeCd, trainNm, trainPlace, deptCd, deptSubYn, trainPlanState, monFlag, year, gubun, imprChk, defaultParam);
    }

    /**
     * 훈련결과 관리 계획 조회
     *
     * @param parameter
     * @return 훈련결과 관리 계획 조회
     * @throws Exception
     */
    public Emergency getEmergencyResultInfo(int safTrainPlanNo, DefaultParam defaultParam) throws Exception {
        Emergency emergency = new Emergency();
        emergency = emergencyMapper.getEmergencyResultInfo(safTrainPlanNo, defaultParam);
        emergency.setEmergencyPlantList(emergencyMapper.getEmergencyPlantList(safTrainPlanNo));
        emergency.setEmergencyUserList(emergencyMapper.getEmergencyUserList(safTrainPlanNo, null, defaultParam));
        emergency.setEmergencyScenarioList(emergencyMapper.getEmergencyScenarioLists(safTrainPlanNo, defaultParam));
        emergency.setEmergencyOutSideUserList(emergencyMapper.getEmergencyOutSideUsers(safTrainPlanNo, defaultParam));
        if (ConstVal.TRAIN_METHOD_VIDEO.equals(emergency.getTrainMethodCd())) {
            emergency.setEmergencyVideo(emergencyMapper.getEmergencyVideo(safTrainPlanNo));
        }
        return emergency;
    }

    /**
     * 무계획훈련결과 등록
     *
     * @param parameter
     * @return 무계획훈련결과 등록
     * @throws Exception
     */
    @Transactional
    public int createEmergencyResult(Emergency emergency) throws Exception {

        emergency.setTrainSTime(emergency.getTrainSHour() + ":" + emergency.getTrainSMin());
        emergency.setTrainETime(emergency.getTrainEHour() + ":" + emergency.getTrainEMin());
        emergency.setTrainPlanState(ConstVal.TRAIN_PLAN_STATE_PLAN); // 계획확정
        emergencyMapper.createEmergency(emergency);
        int primaryKey = emergency.getSafTrainPlanNo();

        if (emergency.getPlantCdList() != null && emergency.getPlantCdList().size() > 0) {
            for (int i = 0; i < emergency.getPlantCdList().size(); i++) {
                String plantCd = emergency.getPlantCdList().get(i);
                emergencyMapper.createEmergencyPlant(primaryKey, plantCd);
            }
        }

        if (emergency.getEmergencyUserList() != null && emergency.getEmergencyUserList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyUserList().size(); i++) {
                emergency.getEmergencyUserList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyResultUser(emergency.getEmergencyUserList().get(i));
            }
        }

        if (emergency.getEmergencyScenarioList() != null & emergency.getEmergencyScenarioList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyScenarioList().size(); i++) {
                emergency.getEmergencyScenarioList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyScenario(emergency.getEmergencyScenarioList().get(i));
            }
        }

        if (ConstVal.TRAIN_METHOD_VIDEO.equals(emergency.getTrainMethodCd())) {
            emergency.getEmergencyVideo().setSafTrainPlanNo(primaryKey);
            emergency.getEmergencyVideo().setCreateUserId(emergency.getCreateUserId());
            emergencyMapper.createEmergencyVideo(emergency.getEmergencyVideo());
        }

        emergencyMapper.createEmergencyResult(emergency);

        return emergency.getSafTrainPlanNo();
    }

    /**
     * 훈련결과 수정
     *
     * @param parameter
     * @return 훈련결과 수정
     * @throws Exception
     */
    @Transactional
    public int updateEmergencyResult(Emergency emergency) throws Exception {

        int primaryKey = emergency.getSafTrainPlanNo();

        emergencyMapper.deleteEmergencyUser(primaryKey);
        if (emergency.getEmergencyUserList() != null && emergency.getEmergencyUserList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyUserList().size(); i++) {
                emergency.getEmergencyUserList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyResultUser(emergency.getEmergencyUserList().get(i));
            }
        }

        emergencyMapper.deleteEmergencyOutSideUser(primaryKey);
        if (emergency.getEmergencyOutSideUserList() != null && emergency.getEmergencyOutSideUserList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyOutSideUserList().size(); i++) {
                emergency.getEmergencyOutSideUserList().get(i).setSafTrainPlanNo(primaryKey);
                emergencyMapper.createEmergencyOutSideUser(emergency.getEmergencyOutSideUserList().get(i));
            }
        }
        int cnt = emergencyMapper.checkEmergencyResult(primaryKey);
        if (cnt <= 0) {
            emergencyMapper.createEmergencyResult(emergency);
        } else {
            emergencyMapper.updateEmergencyResult(emergency);
        }
        return emergency.getSafTrainPlanNo();
    }

    /**
     * 훈련결과 관리 완료처리
     *
     * @param parameter
     * @return 훈련결과 관리 완료처리
     * @throws Exception
     */
    public int updateEmergencyResultComplete(int safTrainPlanNo) throws Exception {
        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_EMERGENCY, safTrainPlanNo, "");
        return emergencyMapper.updateEmergencyResultComplete(safTrainPlanNo);
    }

    /**
     * 훈련결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 훈련결과 현황 목록현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getResultstatusList(String plantCd, String year, String trainTypeCd, String totalFlag) throws Exception {
        List<HashMap<String, Object>> statusList = emergencyMapper.getResultstatusList(plantCd, year, trainTypeCd, totalFlag);
        return statusList;
    }

    public List<ImprAct> getEmergencyImprList(String plantCd, int monFlag, String trainTypeCd, String apprFlag, DefaultParam defaultParam) throws Exception {

        return emergencyMapper.getEmergencyImprList(plantCd, monFlag, trainTypeCd, apprFlag, defaultParam);
    }

    /**
     * 훈련이수자 조회 (외부)
     *
     * @param safTrainPlanNo
     *            훈련계획번호
     * @return 훈련이수자 목록
     * @throws Exception
     *             예외
     */
    public List<EmergencyOutsidePsn> getEmergencyOutSideUsers(int safTrainPlanNo, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyOutSideUsers(safTrainPlanNo, defaultParam);
    }

    /**
     * 훈련동영상 시청 대상 이수자 목록
     * 
     * @param parameter
     *            검색조건
     * @return 훈련동영상 시청 대상 이수자 목록
     * @throws Exception
     */
    public List<Emergency> getEmergencyVideoUserList(String plantCd, String startDt, String endDt, String trainTypeCd, String trainNm, String deptCd, String deptSubYn, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyVideoUserList(plantCd, startDt, endDt, trainTypeCd, trainNm, deptCd, deptSubYn, defaultParam);
    }

    /**
     * 훈련이수자 교육동영상 시청 등록/수정
     * 
     * @param EmergencyPsn
     *            훈련이수자
     *
     * @return 결과
     * @throws Exception
     *             예외
     */
    public int updateEmergencyViewUserVideo(EmergencyPsn emergencyPsn, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.updateEmergencyViewUserVideo(emergencyPsn);
    }
}
