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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval02DeptPrcs;
import com.she.rsa.model.WorkRiskEval02Prcs;
import com.she.rsa.model.WorkRiskEval03RsltDetailWork;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval02Mapper;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval03Mapper;
import com.she.security.util.StringUtil;

/**
 * 작업위험성평가 대상 공정설정 기능정의
 *
 */
@Service
public class WorkRiskEval02Service {

    @Autowired
    private WorkRiskEval02Mapper workRiskEval02Mapper;

    @Autowired
    private WorkRiskEval03Mapper workRiskEval03Mapper;

    /**
     * 작업위험성평가 대상 공정설정 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 대상 공정설정 목록
     * @throws Exception
     */
    public List<WorkRiskEval02Prcs> getworkRiskEval02Lists(String plantCd, String deptCd, String evalTypeCd, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEval02Mapper.getworkRiskEval02Lists(plantCd, deptCd, evalTypeCd, startYear, endYear, defaultParam);
    }

    /**
     * 작업위험성평가 대상 공정설정 평가대상공정 목록
     * 
     * @param plantCd,
     *            deptCd
     * @return 작업위험성평가 대상 공정설정 평가대상공정 목록
     * @throws Exception
     */
    public List<WorkRiskEval02DeptPrcs> getWorkRiskEval02detpPrcsLists(String plantCd, String deptCd, String evalYear, String evalNo, DefaultParam defaultParam) throws Exception {
        return workRiskEval02Mapper.getWorkRiskEval02detpPrcsLists(plantCd, deptCd, evalYear, evalNo, defaultParam);
    }

    /**
     * 작업위험성평가 대상 공정설정 부서별 공정/단위작업 목록
     * 
     * @param plantCd,
     *            deptCd
     * @return 작업위험성평가 대상 공정설정 부서별 공정/단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval02DeptPrcs> getdeptPrcsBaseInfoLists(String plantCd, String deptCd, String processNm, String unitWorkNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval02Mapper.getdeptPrcsBaseInfoLists(plantCd, deptCd, processNm, unitWorkNm, defaultParam);
    }

    /**
     * 작업위험성평가 대상 공정설정 기존 부서별 공정/단위작업 목록
     * 
     * @param plantCd,
     *            deptCd
     * @return 작업위험성평가 대상 공정설정 기존 부서별 공정/단위작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval02DeptPrcs> getdeptPrcsExistBaseInfoLists(String plantCd, String evalNo, String deptCd, String processNm, String unitWorkNm, String evalTypeCd, String startYear, String endYear, DefaultParam defaultParam) throws Exception {
        return workRiskEval02Mapper.getdeptPrcsBaseExistInfoLists(plantCd, evalNo, deptCd, processNm, unitWorkNm, evalTypeCd, startYear, endYear, defaultParam);
    }

    /**
     * 작업위험성평가 대상 공정설정 평가대상공정 상세
     * 
     * @param evalNo(평가번호),
     *            deptCd(대상부서코드)
     * @return 작업위험성평가 대상 공정설정 관리 상세조회
     * @throws Exception
     */
    public WorkRiskEval02Prcs getWorkRiskEval02Info(String plantCd, String evalYear, String evalNo, String deptCd, DefaultParam defaultParam) throws Exception {
        WorkRiskEval02Prcs workRiskEval02Prcs = workRiskEval02Mapper.getWorkRiskEval02Info(plantCd, evalYear, evalNo, deptCd, defaultParam);

        return workRiskEval02Prcs;
    }

    /**
     * 작업위험성평가 공정설정 평가대상공정 등록
     * 
     * @param WorkRiskEval02Prcs
     *            작업위험성평가 공정설정 평가대상공정 관리 List
     * @return 작업위험성평가 공정설정 평가대상공정 관리 Key값
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public String createWorkRiskEval02(WorkRiskEval02Prcs workRiskEval02Prcs) throws Exception {

        workRiskEval02Mapper.updateDeptEvalUserId(workRiskEval02Prcs);
        workRiskEval02Mapper.deleteWorkRiskEval02DeptPrcs(workRiskEval02Prcs.getPlantCd(), workRiskEval02Prcs.getEvalYear(), workRiskEval02Prcs.getEvalNo(), workRiskEval02Prcs.getDeptCd());
        workRiskEval02Mapper.deleteWorkRiskEval02EvalRslt(workRiskEval02Prcs.getPlantCd(), workRiskEval02Prcs.getEvalYear(), workRiskEval02Prcs.getEvalNo(), workRiskEval02Prcs.getDeptCd());

        if (workRiskEval02Prcs.getWorkRiskEval02DeptPrcs() != null && workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().size() > 0) {
            for (int i = 0; i < workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().size(); i++) {
                workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).setPlantCd(workRiskEval02Prcs.getPlantCd());
                workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).setEvalYear(workRiskEval02Prcs.getEvalYear());
                workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).setEvalNo(workRiskEval02Prcs.getEvalNo());
                workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).setCreateUserId(workRiskEval02Prcs.getCreateUserId());
                workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).setUpdateUserId(workRiskEval02Prcs.getUpdateUserId());
                workRiskEval02Mapper.createWorkRiskEval02DeptPrcs(workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i));
                workRiskEval02Mapper.updateUnitWorkCnt(workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i));

                if (StringUtil.isNotEmpty(workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).getUnitWorkAgg())) {
                    String[] unitWorkArr = workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).getUnitWorkAgg().split(",");

                    for (int j = 0; j < unitWorkArr.length; j++) {

                        WorkRiskEval03RsltDetailWork workRiskEval03RsltDetailWork = new WorkRiskEval03RsltDetailWork();
                        String[] unitWorkDataArr = unitWorkArr[j].split("\\|");

                        workRiskEval03RsltDetailWork.setPlantCd(workRiskEval02Prcs.getPlantCd());
                        workRiskEval03RsltDetailWork.setEvalYear(workRiskEval02Prcs.getEvalYear());
                        workRiskEval03RsltDetailWork.setEvalNo(workRiskEval02Prcs.getEvalNo());
                        workRiskEval03RsltDetailWork.setDeptCd(workRiskEval02Prcs.getDeptCd());
                        workRiskEval03RsltDetailWork.setProcessCd(workRiskEval02Prcs.getWorkRiskEval02DeptPrcs().get(i).getProcessCd());
                        workRiskEval03RsltDetailWork.setUnitWorkCd(unitWorkDataArr[0]);
                        workRiskEval03RsltDetailWork.setDtlWkSeq(unitWorkDataArr[1]);
                        workRiskEval03RsltDetailWork.setDtlWkNm(unitWorkDataArr[2]);
                        workRiskEval03RsltDetailWork.setCreateUserId(workRiskEval02Prcs.getCreateUserId());
                        workRiskEval03RsltDetailWork.setUpdateUserId(workRiskEval02Prcs.getUpdateUserId());

                        workRiskEval03Mapper.mergeWorkRiskEval03DetailWork(workRiskEval03RsltDetailWork);

                    }
                }
            }
        }

        return workRiskEval02Prcs.getDeptCd();
    }
}
