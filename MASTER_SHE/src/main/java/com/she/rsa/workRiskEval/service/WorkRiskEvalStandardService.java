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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval09Line;
import com.she.rsa.model.WorkRiskEval09Process;
import com.she.rsa.model.WorkRiskEval11UnitWork;
import com.she.rsa.model.WorkRiskEval12RefInd;
import com.she.rsa.model.WorkRiskEval13DtlUnitWork;
import com.she.rsa.model.WorkRiskEval13UnitWork;
import com.she.rsa.workRiskEval.mapper.WorkRiskEvalStandardMapper;

/**
 * 작업위험성평가 > 기준정보 
 */
@Service
public class WorkRiskEvalStandardService {

    @Autowired
    private WorkRiskEvalStandardMapper workRiskEvalStandardMapper;

    // 09 공정
    /**
     * 작업위험성평가 공정 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getworkRiskEval09Lists(String plantCd, String prcsLvlCd, String processNm, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getworkRiskEval09Lists(plantCd, prcsLvlCd, processNm, defaultParam);
    }

    /**
     * 작업위험성평가 공정 등록
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public String createWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception {
        workRiskEvalStandardMapper.createWorkRiskEval09(workRiskEval09Process);

        return workRiskEval09Process.getProcessCd();
    }

    /**
     * 작업위험성평가 공정 수정
     * 
     * @param WorkRiskEval09Process
     * @return 작업위험성평가 공정 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval09(WorkRiskEval09Process workRiskEval09Process) throws Exception {
        workRiskEvalStandardMapper.updateWorkRiskEval09(workRiskEval09Process);

        return workRiskEval09Process.getProcessCd();
    }

    /**
     * 작업위험성평가 공정 상세
     * 
     * @param plantCd,
     *            processCd 공정코드
     * @return 작업위험성평가 공정 상세조회
     * @throws Exception
     */
    public WorkRiskEval09Process getWorkRiskEval09Info(String plantCd, String processCd, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getWorkRiskEval09Info(plantCd, processCd, defaultParam);
    }

    /**
     * 작업위험성평가 공정 라인 조회
     * 
     * @return 작업위험성평가 공정 라인 목록
     * @throws Exception
     */
    public List<WorkRiskEval09Line> getworkRiskEval09LineLists(String plantCd, String processClsCd, String processNm, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getworkRiskEval09LineLists(plantCd, processClsCd, processNm, defaultParam);
    }

    /**
     * 작업위험성평가 상위공정 코드조회
     * 
     * @param plantCd,
     *            prcsLvlCd 레벨
     * @return 작업위험성평가 상위공정 코드조회
     * @throws Exception
     */
    public List<WorkRiskEval09Process> getWorkRiskEval09UpProcess(String plantCd, String prcsLvlCd, DefaultParam defaultParam) throws Exception {

        List<WorkRiskEval09Process> getWorkRiskEval09UpProcessList = null;

        if ("2".equals(prcsLvlCd)) {
            getWorkRiskEval09UpProcessList = workRiskEvalStandardMapper.getWorkRiskEval09UpProcessLvl2(plantCd, prcsLvlCd, defaultParam);
        } else if ("3".equals(prcsLvlCd)) {
            getWorkRiskEval09UpProcessList = workRiskEvalStandardMapper.getWorkRiskEval09UpProcessLvl3(plantCd, prcsLvlCd, defaultParam);
        }

        return getWorkRiskEval09UpProcessList;
    }
    
    // 11 단위작업
    /**
     * 작업위험성평가 단위작업 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval11UnitWork> getworkRiskEval11Lists(String plantCd, String unitWorkNm, String useYn, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getworkRiskEval11Lists(plantCd, unitWorkNm, useYn, defaultParam);
    }

    /**
     * 작업위험성평가 단위작업 등록
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public String createWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        workRiskEvalStandardMapper.createWorkRiskEval11(workRiskEval11UnitWork);

        return workRiskEval11UnitWork.getUnitWorkCd();
    }

    /**
     * 작업위험성평가 단위작업 수정
     * 
     * @param WorkRiskEval11UnitWork
     * @return 작업위험성평가 단위작업 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval11(WorkRiskEval11UnitWork workRiskEval11UnitWork) throws Exception {
        workRiskEvalStandardMapper.updateWorkRiskEval11(workRiskEval11UnitWork);

        return workRiskEval11UnitWork.getUnitWorkCd();
    }

    /**
     * 작업위험성평가 단위작업 상세
     * 
     * @param plantCd,
     *            unitWorkCd 평가번호
     * @return 작업위험성평가 단위작업 상세조회
     * @throws Exception
     */
    public WorkRiskEval11UnitWork getWorkRiskEval11Info(String plantCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getWorkRiskEval11Info(plantCd, unitWorkCd, defaultParam);
    }
    
    
    // 13 세부작업
    /**
     * 작업위험성평가 부서공정별 단위작업 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 부서공정별 단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval13UnitWork> getworkRiskEval13Lists(String plantCd, String deptCd, String processCd, String unitWorkNm, String useYn, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getworkRiskEval13Lists(plantCd, deptCd, processCd, unitWorkNm, useYn, defaultParam);
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
        return workRiskEvalStandardMapper.getworkRiskEval13ProcessLists(plantCd, prcsLvlCd, processNm, defaultParam);
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
        return workRiskEvalStandardMapper.getworkRiskEval13UnitLists(plantCd, unitWorkNm, defaultParam);
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
        return workRiskEvalStandardMapper.getWorkRiskEval13Info(plantCd, deptCd, processCd, unitWorkCd, defaultParam);
    }

    /**
     * 작업위험성평가 부서공정 등록
     * 
     * @param WorkRiskEval13UnitWork
     * @return 작업위험성평가 부서공정 Key값
     * @throws Exception
     */
    public String createWorkRiskEval13(WorkRiskEval13UnitWork workRiskEval13UnitWork) throws Exception {
        workRiskEvalStandardMapper.createWorkRiskEval13(workRiskEval13UnitWork);

        if (workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork() != null && workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size() > 0) {
            for (int i = 0; i < workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size(); i++) {

                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setPlantCd(workRiskEval13UnitWork.getPlantCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setDeptCd(workRiskEval13UnitWork.getDeptCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setProcessCd(workRiskEval13UnitWork.getProcessCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUnitWorkCd(workRiskEval13UnitWork.getUnitWorkCd());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setCreateUserId(workRiskEval13UnitWork.getCreateUserId());
                workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUpdateUserId(workRiskEval13UnitWork.getUpdateUserId());

                workRiskEvalStandardMapper.createWorkRiskEval13DtlUnitWork(workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i));
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
        workRiskEvalStandardMapper.updateWorkRiskEval13(workRiskEval13UnitWork);

        if (workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork() != null && workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size() > 0) {
            for (int i = 0; i < workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().size(); i++) {

                if (workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).getDtlWkSeq() > 0) {
                    workRiskEvalStandardMapper.updateWorkRiskEval13DtlUnitWork(workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i));
                } else {
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setPlantCd(workRiskEval13UnitWork.getPlantCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setDeptCd(workRiskEval13UnitWork.getDeptCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setProcessCd(workRiskEval13UnitWork.getProcessCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUnitWorkCd(workRiskEval13UnitWork.getUnitWorkCd());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setCreateUserId(workRiskEval13UnitWork.getCreateUserId());
                    workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i).setUpdateUserId(workRiskEval13UnitWork.getUpdateUserId());

                    workRiskEvalStandardMapper.createWorkRiskEval13DtlUnitWork(workRiskEval13UnitWork.getWorkRiskEval13DtlUnitWork().get(i));
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
        return workRiskEvalStandardMapper.getWorkRiskEval13DtlUnitWorkList(plantCd, deptCd, processCd, unitWorkCd, defaultParam);
    }

    
    // 12 평가지표
    /**
     * 작업위험성평가 관련지표 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관련지표 목록
     * @throws Exception
     */
    public List<WorkRiskEval12RefInd> getworkRiskEval12Lists(String plantCd, String indTypeCd, String setNm, String useYn, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getworkRiskEval12Lists(plantCd, indTypeCd, setNm, useYn, defaultParam);
    }

    /**
     * 작업위험성평가 관련지표 등록
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 관련지표 Key값
     * @throws Exception
     */
    public String createWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        workRiskEvalStandardMapper.createWorkRiskEval12(workRiskEval12RefInd);

        return workRiskEval12RefInd.getSetVal();
    }

    /**
     * 작업위험성평가 관련지표 수정
     * 
     * @param WorkRiskEval12RefInd
     * @return 작업위험성평가 관련지표 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval12(WorkRiskEval12RefInd workRiskEval12RefInd) throws Exception {
        workRiskEvalStandardMapper.updateWorkRiskEval12(workRiskEval12RefInd);

        return workRiskEval12RefInd.getSetVal();
    }

    /**
     * 작업위험성평가 관련지표 상세
     * 
     * @param plantCd,
     *            indTypeCd 평가번호, setVal
     * @return 작업위험성평가 관련지표 상세조회
     * @throws Exception
     */
    public WorkRiskEval12RefInd getWorkRiskEval12Info(String plantCd, String indTypeCd, String setVal, DefaultParam defaultParam) throws Exception {
        return workRiskEvalStandardMapper.getWorkRiskEval12Info(plantCd, indTypeCd, setVal, defaultParam);
    }
}
