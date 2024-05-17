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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.impr.service.ImprService;
import com.she.safety.emergency.mapper.EmergencyMapper;
import com.she.safety.model.Emergency;
import com.she.safety.model.EmergencyDept;
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
    public List<Emergency> getEmergencyLists(String plantCd, String startDt, String endDt, String trainTypeCd, String trainNm, String trainPlace, String deptCd, String deptSubYn, String procStepCd, String stateCd, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyLists(plantCd, startDt, endDt, trainTypeCd, trainNm, trainPlace, deptCd, deptSubYn, procStepCd, stateCd, defaultParam);
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
        emergency.setEmergencyDeptList(emergencyMapper.getEmergencyDeptLists(safTrainPlanNo, defaultParam));
        emergency.setEmergencyScenarioList(emergencyMapper.getEmergencyScenarioLists(safTrainPlanNo, defaultParam));
        return emergency;
    }

    /**
     * 훈련계획 대상부서 목록
     *
     * @param parameter
     * @return 훈련계획 대상부서 목록
     * @throws Exception
     */
    public List<EmergencyDept> getEmergencyDeptList(int safTrainPlanNo, String apprFlag, DefaultParam defaultParam) throws Exception {
        List<EmergencyDept> emergencyDeptList = new ArrayList<EmergencyDept>();
        emergencyDeptList = emergencyMapper.getEmergencyDeptList(safTrainPlanNo, apprFlag, defaultParam);
        return emergencyDeptList;
    }

    /**
     * 훈련계획 관리 계획 등록
     *
     * @param parameter
     * @return 훈련계획 관리 계획 조회
     * @throws Exception
     */
    public int createEmergency(Emergency emergency) throws Exception {
        String sTime[] = emergency.getTrainSTime().split(":");
        String eTime[] = emergency.getTrainETime().split(":");
        emergency.setTrainSHour(sTime[0]);
        emergency.setTrainSMin(sTime[1]);
        emergency.setTrainEHour(eTime[0]);
        emergency.setTrainEMin(eTime[1]);
        emergency.setStateCd("PSREG");
        emergencyMapper.createEmergency(emergency);
        int primaryKet = emergency.getSafTrainPlanNo();
        emergencyMapper.deleteEmergencyDept(primaryKet);
        if (emergency.getEmergencyDeptList() != null && emergency.getEmergencyDeptList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyDeptList().size(); i++) {
                emergency.getEmergencyDeptList().get(i).setSafTrainPlanNo(primaryKet);
                emergencyMapper.createEmergencyDept(emergency.getEmergencyDeptList().get(i));
            }
        }
        emergencyMapper.deleteEmergencyScenario(primaryKet);
        if (emergency.getEmergencyScenarioList() != null & emergency.getEmergencyScenarioList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyScenarioList().size(); i++) {
                emergency.getEmergencyScenarioList().get(i).setSafTrainMstNo(primaryKet);
                emergencyMapper.createEmergencyScenario(emergency.getEmergencyScenarioList().get(i));
            }
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
    public int updateEmergency(Emergency emergency) throws Exception {
        String sTime[] = emergency.getTrainSTime().split(":");
        String eTime[] = emergency.getTrainETime().split(":");
        emergency.setTrainSHour(sTime[0]);
        emergency.setTrainSMin(sTime[1]);
        emergency.setTrainEHour(eTime[0]);
        emergency.setTrainEMin(eTime[1]);
        emergencyMapper.updateEmergency(emergency);
        int primaryKet = emergency.getSafTrainPlanNo();
        emergencyMapper.deleteEmergencyDept(primaryKet);
        if (emergency.getEmergencyDeptList() != null && emergency.getEmergencyDeptList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyDeptList().size(); i++) {
                emergency.getEmergencyDeptList().get(i).setSafTrainPlanNo(primaryKet);
                emergencyMapper.createEmergencyDept(emergency.getEmergencyDeptList().get(i));
            }
        }
        emergencyMapper.deleteEmergencyScenario(primaryKet);
        if (emergency.getEmergencyScenarioList() != null & emergency.getEmergencyScenarioList().size() > 0) {

            for (int i = 0; i < emergency.getEmergencyScenarioList().size(); i++) {
                emergency.getEmergencyScenarioList().get(i).setSafTrainMstNo(primaryKet);
                emergencyMapper.createEmergencyScenario(emergency.getEmergencyScenarioList().get(i));
            }
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
    public List<EmergencyDept> getEmergencyResultLists(String plantCd, String startDt, String endDt, String trainTypeCd, String trainNm, String trainPlace, String deptCd, String deptSubYn, String procStepCd, String stateCd, int monFlag, String year, String gubun, String imprChk, DefaultParam defaultParam) throws Exception {
        return emergencyMapper.getEmergencyResultLists(plantCd, startDt, endDt, trainTypeCd, trainNm, trainPlace, deptCd, deptSubYn, procStepCd, stateCd, monFlag, year, gubun, imprChk, defaultParam);
    }

    /**
     * 훈련결과 관리 계획 조회
     *
     * @param parameter
     * @return 훈련결과 관리 계획 조회
     * @throws Exception
     */
    public EmergencyDept getEmergencyResultInfo(int safTrainDeptRsltNo, DefaultParam defaultParam) throws Exception {
        EmergencyDept emergencyDept = new EmergencyDept();
        emergencyDept = emergencyMapper.getEmergencyResultInfo(safTrainDeptRsltNo, defaultParam);
        emergencyDept.setEmergencyScenarioList(emergencyMapper.getEmergencyScenarioLists(emergencyDept.getSafTrainPlanNo(), defaultParam));
        return emergencyDept;
    }

    /**
     * 무계획훈련결과 등록
     *
     * @param parameter
     * @return 무계획훈련결과 등록
     * @throws Exception
     */
    public int createEmergencyResult(EmergencyDept emergencyDept) throws Exception {
        Emergency emergency = new Emergency();
        String sTime[] = emergencyDept.getTrainSTime().split(":");
        String eTime[] = emergencyDept.getTrainETime().split(":");
        emergency.setPlantCd(emergencyDept.getPlantCd());
        emergency.setDeptCd(emergencyDept.getDeptCd());
        emergency.setTrainTypeCd(emergencyDept.getTrainTypeCd());
        emergency.setTrainUserId(emergencyDept.getTrainUserId());
        emergency.setTrainDeptCd(emergencyDept.getTrainDeptCd());
        emergency.setTrainNm(emergencyDept.getTrainNm());
        emergency.setTrainYmd(emergencyDept.getTrainYmd());
        emergency.setTrainSTime(emergencyDept.getTrainSTime());
        emergency.setTrainETime(emergencyDept.getTrainETime());
        emergency.setTrainSHour(sTime[0]);
        emergency.setTrainSMin(sTime[1]);
        emergency.setTrainEHour(eTime[0]);
        emergency.setTrainEMin(eTime[1]);
        emergency.setProcStepCd("STS01");
        emergency.setTrainPlace(emergencyDept.getTrainPlace());
        emergency.setTrainContent(emergencyDept.getTrainContent());
        emergency.setCreateUserId(emergencyDept.getCreateUserId());
        emergency.setCreateDeptCd(emergencyDept.getCreateDeptCd());
        emergency.setCreateDeptNm(emergencyDept.getCreateDeptNm());
        emergency.setStateCd("PSEND");
        emergency.setCreatePositionCd(emergencyDept.getCreatePositionCd());
        emergency.setCreatePositionNm(emergencyDept.getCreatePositionNm());
        emergency.setChngNum(emergencyDept.getChngNum());
        emergencyMapper.createEmergency(emergency);
        // 대상부서
        emergencyDept.setSafTrainPlanNo(emergency.getSafTrainPlanNo());
        emergencyDept.setStateCd("PSREG");
        emergencyDept.setDeptCd(emergencyDept.getSubDeptCd());
        emergencyDept.setTrainSTime(emergencyDept.getSubTrainSTime());
        emergencyDept.setTrainETime(emergencyDept.getSubTrainETime());
        emergencyDept.setTrainYmd(emergencyDept.getSubTrainYmd());
        emergencyMapper.createEmergencyDept(emergencyDept);
        emergencyMapper.deleteEmergencyScenario(emergencyDept.getSafTrainPlanNo());
        if (emergencyDept.getEmergencyScenarioList() != null & emergencyDept.getEmergencyScenarioList().size() > 0) {

            for (int i = 0; i < emergencyDept.getEmergencyScenarioList().size(); i++) {
                emergencyDept.getEmergencyScenarioList().get(i).setSafTrainMstNo(emergency.getSafTrainPlanNo());
                emergencyMapper.createEmergencyScenario(emergencyDept.getEmergencyScenarioList().get(i));
            }
        }
        return emergencyDept.getSafTrainDeptRsltNo();
    }

    /**
     * 훈련결과 수정
     *
     * @param parameter
     * @return 훈련결과 수정
     * @throws Exception
     */
    public int updateEmergencyResult(EmergencyDept emergencyDept) throws Exception {

        emergencyMapper.updateEmergencyResult(emergencyDept);
        return emergencyDept.getSafTrainDeptRsltNo();
    }

    /**
     * 훈련결과 관리 완료처리
     *
     * @param parameter
     * @return 훈련결과 관리 완료처리
     * @throws Exception
     */
    public int updateEmergencyResultComplete(int safTrainDeptRsltNo) throws Exception {
        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_EMERGENCY, safTrainDeptRsltNo, "");
        return emergencyMapper.updateEmergencyResultComplete(safTrainDeptRsltNo);
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
}
