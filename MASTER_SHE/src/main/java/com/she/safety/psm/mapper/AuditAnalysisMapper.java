package com.she.safety.psm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;

@Mapper
@Repository("com.she.safety.psm.mapper.AuditAnalysisMapper")
public interface AuditAnalysisMapper {

    /**
     * PSM 분석현황 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 분석현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAnalysisStatus(@Param("plantCd") String plantCd, @Param("year") String year, @Param("auditRsltNo") String auditRsltNo, @Param("deptCds") String[] deptCds, @Param("deptCdStr") String deptCdStr) throws Exception;

    /**
     * PSM 분석현황 부서 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 분석현황 부서 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getAnalysisstatusDept(@Param("plantCd") String plantCd, @Param("year") String year, @Param("auditRsltNo") String auditRsltNo) throws Exception;

    /**
     * PSM 감사결과 조회
     *
     * @return PSM 감사결과 목록
     * @throws Exception
     */
    public List<SafPsmAuditRslt> getAuditResults(@Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
