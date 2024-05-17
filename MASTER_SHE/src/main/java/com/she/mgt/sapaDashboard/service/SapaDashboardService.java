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
package com.she.mgt.sapaDashboard.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.SapaDashboard;
import com.she.mgt.sapaDashboard.mapper.SapaDashboardMapper;

/**
 * 평가계획 기능정의
 *
 */
@Service
public class SapaDashboardService {
    private static Logger logger = LoggerFactory.getLogger(SapaDashboardService.class);

    @Autowired
    private SapaDashboardMapper sapaDashboardMapper;

    /**
     * 대시보드 개선사항 목록 cnt
     *
     * @param parameter
     *            검색조건
     * @return 대시보드 개선사항 목록 cnt
     * @throws Exception
     */
    public Map<String, Object> getImprCnt(String plantCd, String startDt, String endDt, String deptCd, String deptSubYn, DefaultParam defaultParam) throws Exception {
        Map<String, Object> result = new HashMap<>();

        List<SapaDashboard> dashBoardList = new ArrayList<SapaDashboard>();

        dashBoardList.add(sapaDashboardMapper.getRsaWorkImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getRsaProcImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getSafTrainingImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getSafetyhealthImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getSafGovImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        DecimalFormat decFormat = new DecimalFormat("###,###");
        List<SapaDashboard> budGetCoseList = sapaDashboardMapper.getBudgetCost(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam);

        for (SapaDashboard budGetCose : budGetCoseList) {
            budGetCose.setTotal(budGetCose.getExecAmt());
            budGetCose.setCompleteCnt(budGetCose.getOrgAmt());
            double a = Double.parseDouble(budGetCose.getExecAmt());
            double b = Double.parseDouble(budGetCose.getOrgAmt());
            double average = 0.0;
            if (a != 0.0 && b != 0.0) {
                average = a / b * 100;
            }
            budGetCose.setAverage(String.format("%.2f", average) + "%");
            budGetCose.setOrgAmt(decFormat.format(Double.parseDouble(budGetCose.getOrgAmt())));
            budGetCose.setExecAmt(decFormat.format(Double.parseDouble(budGetCose.getExecAmt())));
        }

        List<SapaDashboard> budgetInvestMentList = sapaDashboardMapper.getBudgetInvestMent(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam);
        for (SapaDashboard budgetInvestMent : budgetInvestMentList) {
            budgetInvestMent.setTotal(budgetInvestMent.getExecAmt());
            budgetInvestMent.setCompleteCnt(budgetInvestMent.getOrgAmt());
            double c = Double.parseDouble(budgetInvestMent.getExecAmt());
            double d = Double.parseDouble(budgetInvestMent.getOrgAmt());
            double average2 = 0.0;
            if (c != 0.0 && d != 0.0) {
                average2 = c / d * 100;
            }

            budgetInvestMent.setAverage(String.format("%.2f", average2) + "%");
            budgetInvestMent.setOrgAmt(decFormat.format(Double.parseDouble(budgetInvestMent.getOrgAmt())));
            budgetInvestMent.setExecAmt(decFormat.format(Double.parseDouble(budgetInvestMent.getExecAmt())));
        }

        dashBoardList.add(sapaDashboardMapper.getElectEval(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getElectHis(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getResultmgmt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getResultEdu(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));
        dashBoardList.add(sapaDashboardMapper.getSafDisaInspCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam));

        result.put("dashBoardList", dashBoardList);
        result.put("mgtTargetItems", sapaDashboardMapper.getMgtTargetItems(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam)); // 목표관리
        result.put("mgtListenImprs", sapaDashboardMapper.getMgtListenImprCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam)); // 종사자 의견 채널별
        result.put("safAccidentType", sapaDashboardMapper.getSafAccidentTypeCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam)); // 사고관리 유형별
        result.put("safAccidentYear", sapaDashboardMapper.getSafAccidentYearCnt(plantCd, startDt, endDt, deptCd, deptSubYn, defaultParam)); // 사고관리 년도별
        result.put("budGetCoseList", budGetCoseList); // 비용예산
        result.put("budgetInvestMentList", budgetInvestMentList); // 투자예산

        return result;
    }
}
