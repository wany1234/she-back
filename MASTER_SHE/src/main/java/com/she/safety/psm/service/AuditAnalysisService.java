package com.she.safety.psm.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.psm.mapper.AuditAnalysisMapper;

/*
* PSM 분석 기능정의
*
*/
@Service
public class AuditAnalysisService {

    @Autowired
    private AuditAnalysisMapper auditAnalysisMapper;

    public List<HashMap<String, Object>> getAnalysisstatusDept(String plantCd, String year, String auditRsltNo) throws Exception {
        return auditAnalysisMapper.getAnalysisstatusDept(plantCd, year, auditRsltNo);
    }

    /**
     * PSM 분석 분석현황 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 분석 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAnalysisstatus(String plantCd, String year, String auditRsltNo, String[] deptCds) throws Exception {
        String deptCdStr = String.join(", ", deptCds);

        return auditAnalysisMapper.getAnalysisStatus(plantCd, year, auditRsltNo, deptCds, deptCdStr);
    }

    /**
     * PSM 감사결과 조회
     *
     * @return PSM 감사결과 목록
     * @throws Exception
     */
    public List<SafPsmAuditRslt> getAuditResults(DefaultParam defaultParam) throws Exception {
        return auditAnalysisMapper.getAuditResults(defaultParam);
    }

}
