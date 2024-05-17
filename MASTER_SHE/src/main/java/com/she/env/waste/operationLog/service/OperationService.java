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
package com.she.env.waste.operationLog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.waste.baseInfo.mapper.WasteClassMapper;
import com.she.env.waste.operationLog.mapper.OperationMapper;
import com.she.manage.mapper.CodeMasterMapper;

/**
 * 폐기물 관리대장 기능정의
 *
 */
@Service("EwstOperationService")
public class OperationService {
    @Autowired
    private OperationMapper operationMapper;

    @Autowired
    private WasteClassMapper wasteClassMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    private String operationLogReportFileName = "waste_daily_report.jasper";

    public List<HashMap<String, Object>> getOperationStatus(String ewstWasteNo, String ewstClassCd, String measureYear, String plantCd, String totalTypeCd, DefaultParam defaultParam) throws Exception {
        return this.operationMapper.getOperationStatus(ewstWasteNo, ewstClassCd, measureYear, plantCd, totalTypeCd, defaultParam);
    }

    public List<HashMap<String, Object>> getWaterUnitStatus(String measureYear, String plantCd, DefaultParam defaultParam) throws Exception {
        return this.operationMapper.getWaterUnitStatus(measureYear, plantCd, defaultParam);
    }

    /**
     * 월별 폐기물 발생 및 처리량 조회
     *
     * @param fromDate
     *            검색시작일
     * @param toDate
     *            검색종료일
     * @return 폐기물 발생 및 처리량
     * @throws Exception
     */
    public List<HashMap<String, Object>> getMonthlyDisposalRequestAndResult(String fromDate, String toDate) throws Exception {
        return this.operationMapper.getMonthlyDisposalRequestAndResult(fromDate, toDate);
    }
}
