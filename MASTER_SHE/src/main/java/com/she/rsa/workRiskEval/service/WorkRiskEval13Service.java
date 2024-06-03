package com.she.rsa.workRiskEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval13DtlUnitWork;
import com.she.rsa.model.WorkRiskEval13UnitWork;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval13Mapper;

/**
 * 작업위험성평가 > 기준정보 > 부서공정별 단위작업
 */
@Service
public class WorkRiskEval13Service {

    @Autowired
    private WorkRiskEval13Mapper workRiskEval13Mapper;

    /**
     * 작업위험성평가 부서공정별 단위작업 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 부서공정별 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13Lists(String plantCd, String deptCd, String processCd, String unitWorkNm, String useYn, DefaultParam defaultParam) throws Exception {
        return workRiskEval13Mapper.getworkRiskEval13Lists(plantCd, deptCd, processCd, unitWorkNm, useYn, defaultParam);
    }

    /**
     * 작업위험성평가 공정검색팝업 공정 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정검색팝업 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13ProcessLists(String plantCd, String prcsLvlCd, String processNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval13Mapper.getworkRiskEval13ProcessLists(plantCd, prcsLvlCd, processNm, defaultParam);
    }

    /**
     * 작업위험성평가 단위작업검색팝업 단위작업 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업검색팝업 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13UnitLists(String plantCd, String unitWorkNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval13Mapper.getworkRiskEval13UnitLists(plantCd, unitWorkNm, defaultParam);
    }

    /**
     * 작업위험성평가 부서공정별 상세
     * 
     * @param plantCd,
     *            deptCd, processCd, unitWorkCd
     * @return 작업위험성평가 부서공정별 상세조회
     * @throws Exception
     */
    public WorkRiskEval13UnitWork getWorkRiskEval13Info(String plantCd, String deptCd, String processCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval13Mapper.getWorkRiskEval13Info(plantCd, deptCd, processCd, unitWorkCd, defaultParam);
    }

    /**
     * 작업위험성평가 부서공정 등록
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public String createWorkRiskEval13(WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        workRiskEval13Mapper.createWorkRiskEval13(workRiskEval13UnitWork);

        if (workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork() != null && workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size() > 0) {
            for (int i = 0; i < workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size(); i++) {

                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setPlantCd(workRiskEval13UnitWork.getPlantCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setDeptCd(workRiskEval13UnitWork.getDeptCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setProcessCd(workRiskEval13UnitWork.getProcessCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUnitWorkCd(workRiskEval13UnitWork.getUnitWorkCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setCreateUserId(workRiskEval13UnitWork.getCreateUserId());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUpdateUserId(workRiskEval13UnitWork.getUpdateUserId());

                workRiskEval13Mapper.createWorkRiskEval13DtlUnitWork(workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i));
            }
        }

        return workRiskEval13UnitWork.getUnitWorkCd();

    }

    /**
     * 작업위험성평가 부서공정 수정
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval13(WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        workRiskEval13Mapper.updateWorkRiskEval13(workRiskEval13UnitWork);

        if (workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork() != null && workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size() > 0) {
            for (int i = 0; i < workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size(); i++) {

                if (workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).getDtlWkSeq() > 0) {
                    workRiskEval13Mapper.updateWorkRiskEval13DtlUnitWork(workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i));
                } else {
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setPlantCd(workRiskEval13UnitWork.getPlantCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setDeptCd(workRiskEval13UnitWork.getDeptCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setProcessCd(workRiskEval13UnitWork.getProcessCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUnitWorkCd(workRiskEval13UnitWork.getUnitWorkCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setCreateUserId(workRiskEval13UnitWork.getCreateUserId());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUpdateUserId(workRiskEval13UnitWork.getUpdateUserId());

                    workRiskEval13Mapper.createWorkRiskEval13DtlUnitWork(workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i));
                }

            }
        }

        return workRiskEval13UnitWork.getUnitWorkCd();
    }

    /**
     * 작업위험성평가 부서공정별 세부작업 조회
     * 
     * @param plantCd,
     *            deptCd, processCd, unitWorkCd
     * @return 작업위험성평가 부서공정별 세부작업 목록 조회
     * @throws Exception
     */
    public List<WorkRiskEval13DtlUnitWork> getWorkRiskEval13DtlUnitWorkList(String plantCd, String deptCd, String processCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval13Mapper.getWorkRiskEval13DtlUnitWorkList(plantCd, deptCd, processCd, unitWorkCd, defaultParam);
    }

}
