package com.she.safety.psm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.model.SafPsmAuditRsltDept;
import com.she.safety.model.SafPsmAuditRsltImpr;
import com.she.safety.model.SafPsmAuditRsltItem;
import com.she.safety.model.SafPsmAuditRsltStd;
import com.she.safety.model.SafPsmAuditRsltUser;

@Mapper
@Repository("com.she.safety.psm.mapper.AuditResultMapper")
public interface AuditResultMapper {

    /**
     * PSM 결과 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 결과 목록
     * @throws Exception
     */
    public List<SafPsmAuditRslt> getAuditResults(@Param("parameter") HashMap<String, Object> parameter, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 결과 상세 조회
     *
     * @param auditRsltNo
     *            PSM 결과 번호
     * @return PSM 결과
     * @throws Exception
     */
    public SafPsmAuditRslt getSafPsmAuditRslt(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결과 대상부서 조회
     *
     * @param auditRsltNo
     *            PSM 결과 번호
     * @return 결과 대상부서
     * @throws Exception
     */
    public List<SafPsmAuditRsltDept> getSafPsmAuditRsltDepts(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결과 감사원 조회
     *
     * @param auditRsltNo
     *            PSM 결과 번호
     * @return 감사원
     * @throws Exception
     */
    public List<SafPsmAuditRsltUser> getSafPsmAuditRsltUsers(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결과 감사결과_기준 조회
     *
     * @param auditRsltNo
     *            PSM 결과 번호
     * @return 기준
     * @throws Exception
     */
    public List<SafPsmAuditRsltStd> getSafPsmAuditRsltStds(@Param("auditRsltNo") int auditRsltNo, @Param("targetDeptCd") String targetDeptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 결과 감사결과_항목 조회
     *
     * @param safPsmAuditRsltStd
     *            PSM 기준 번호
     * @return 결과 감사결과_항목
     * @throws Exception
     */
    public List<SafPsmAuditRsltItem> getSafPsmAuditRsltItems(@Param("safPsmAuditRsltStd") SafPsmAuditRsltStd safPsmAuditRsltStd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선조치사항 항목 조회
     *
     * @param imprClassCd
     *            개선분류코드 코드
     * @param auditRsltNo
     *            감사결과번호
     * @return PSM 감사결과_개선사항 목록
     * @throws Exception
     */
    public List<SafPsmAuditRsltImpr> getAuditResultImpr(@Param("imprClassCd") String imprClassCd, @Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 결과 수정
     *
     * @param safPsmAuditRslt
     *            PSM 결과
     * @throws Exception
     */
    public void updateSafPsmAuditRslt(SafPsmAuditRslt safPsmAuditRslt) throws Exception;

    /**
     * PSM 결과 감사기준 수정
     *
     * @param safPsmAuditRsltStd
     *            PSM 결과 감사기준
     * @throws Exception
     */
    public void updateSafPsmAuditRsltStd(SafPsmAuditRsltStd safPsmAuditRsltStd) throws Exception;

    /**
     * PSM 결과 감사기준 수정
     *
     * @param safPsmAuditRsltItem
     *            PSM 결과 감사항목
     * @throws Exception
     */
    public void updateSafPsmAuditRsltItem(SafPsmAuditRsltItem safPsmAuditRsltItem) throws Exception;

    /**
     * PSM 결과 개선사항 수정
     *
     * @param safPsmAuditRsltImpr
     *            PSM 결과 개선사항
     * @throws Exception
     */
    public void updateSafPsmAuditRsltImpr(SafPsmAuditRsltImpr safPsmAuditRsltImpr) throws Exception;

    /**
     * PSM 결과결재요청
     *
     * @param auditRsltNo
     *            PSM 결과
     * @return PSM 결과 키
     */
    public int apprProcessAuditResult(@Param("auditRsltNo") int auditRsltNo, @Param("psmProgState") String psmProgState, @Param("apprRqstNo") int apprRqstNo) throws Exception;
}
