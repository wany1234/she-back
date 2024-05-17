package com.she.safety.psm.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.SafPsmAuditRslt;
import com.she.safety.model.SafPsmAuditRsltDept;
import com.she.safety.model.SafPsmAuditRsltItem;
import com.she.safety.model.SafPsmAuditRsltStd;
import com.she.safety.model.SafPsmAuditRsltUser;

@Mapper
@Repository("com.she.safety.psm.mapper.AuditPlanMapper")
public interface AuditPlanMapper {

    /**
     * PSM 계획 조회
     *
     * @param parameter
     *            검색조건
     * @return PSM 계획 목록
     * @throws Exception
     */
    public List<SafPsmAuditRslt> getAuditPlans(@Param("parameter") HashMap<String, Object> parameter, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 계획 등록
     *
     * @param safPsmAuditRslt
     *            PSM 계획
     * @throws Exception
     */
    public void createSafPsmAuditRslt(SafPsmAuditRslt safPsmAuditRslt) throws Exception;

    /**
     * PSM 계획 대상부서 등록
     *
     * @param safPsmAuditRsltDept
     *            PSM 계획 대상부서
     * @throws Exception
     */
    public void createSafPsmAuditRsltDept(SafPsmAuditRsltDept safPsmAuditRsltDept) throws Exception;

    /**
     * PSM 계획 감사원 등록
     *
     * @param safPsmAuditRsltUser
     *            PSM 계획 감사원
     * @throws Exception
     */
    public void createSafPsmAuditRsltUser(SafPsmAuditRsltUser safPsmAuditRsltUser) throws Exception;

    /**
     * PSM 계획 감사기준 등록
     *
     * @param safPsmAuditRsltStd
     *            PSM 계획 감사기준
     * @throws Exception
     */
    public void createSafPsmAuditRsltStd(SafPsmAuditRsltStd safPsmAuditRsltStd) throws Exception;

    /**
     * PSM 계획 감사항목 등록
     *
     * @param safPsmAuditRsltStd
     *            PSM 계획 감사 기준
     * @throws Exception
     */
    public void createSafPsmAuditRsltItem(SafPsmAuditRsltStd safPsmAuditRsltStd) throws Exception;

    /**
     * PSM 계획 상세 조회
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return PSM 계획
     * @throws Exception
     */
    public SafPsmAuditRslt getSafPsmAuditRslt(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 계획 대상부서 조회
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return 계획 대상부서
     * @throws Exception
     */
    public List<SafPsmAuditRsltDept> getSafPsmAuditRsltDepts(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 계획 감사원 조회
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return 감사원
     * @throws Exception
     */
    public List<SafPsmAuditRsltUser> getSafPsmAuditRsltUsers(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 계획 감사결과_기준 조회
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @return 기준
     * @throws Exception
     */
    public List<SafPsmAuditRsltStd> getSafPsmAuditRsltStds(@Param("auditRsltNo") int auditRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 계획 감사결과_항목 조회
     *
     * @param auditStdNo
     *            PSM 기준 번호
     * @return 계획 감사결과_항목
     * @throws Exception
     */
    public List<SafPsmAuditRsltItem> getSafPsmAuditRsltItems(@Param("auditStdNo") int auditStdNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * PSM 계획 수정
     *
     * @param safPsmAuditRslt
     *            PSM 계획
     * @throws Exception
     */
    public void updateSafPsmAuditRslt(SafPsmAuditRslt safPsmAuditRslt) throws Exception;

    /**
     * PSM 계획 감사원 수정
     *
     * @param safPsmAuditRsltUser
     *            PSM 계획 감사원
     * @throws Exception
     */
    public void updateSafPsmAuditRsltUser(SafPsmAuditRsltUser safPsmAuditRsltUser) throws Exception;

    /**
     * PSM 계획 감사기준 수정
     *
     * @param safPsmAuditRsltStd
     *            PSM 계획 감사기준
     * @throws Exception
     */
    public void updateSafPsmAuditRsltStd(SafPsmAuditRsltStd safPsmAuditRsltStd) throws Exception;

    /**
     * PSM 계획 대상 부서 삭제
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltDept(@Param("auditRsltNo") int auditRsltNo) throws Exception;

    /**
     * PSM 계획 감사원 삭제
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltUser(SafPsmAuditRsltUser safPsmAuditRsltUser) throws Exception;

    /**
     * PSM 계획 기준 삭제
     *
     * @param safPsmAuditRsltStd
     *            PSM 계획
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltStd(SafPsmAuditRsltStd safPsmAuditRsltStd) throws Exception;

    /**
     * PSM 계획 항목 삭제
     *
     * @param safPsmAuditRsltStd
     *            PSM 계획
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltItem(SafPsmAuditRsltStd safPsmAuditRsltStd) throws Exception;

    /**
     * PSM 계획 항목 전체 삭제
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltItemAll(@Param("auditRsltNo") int auditRsltNo) throws Exception;

    /**
     * PSM 계획 기준 전체 삭제
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltStdAll(@Param("auditRsltNo") int auditRsltNo) throws Exception;

    /**
     * PSM 계획 감사원 전체 삭제
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @throws Exception
     */
    public int deleteSafPsmAuditRsltUserAll(@Param("auditRsltNo") int auditRsltNo) throws Exception;

    /**
     * PSM 계획 삭제
     *
     * @param auditRsltNo
     *            PSM 계획 번호
     * @throws Exception
     */
    public int deleteSafPsmAuditRslt(@Param("auditRsltNo") int auditRsltNo) throws Exception;

    /**
     * PSM 결재 완료
     *
     * @param auditRsltNo
     *            PSM 결재상태변경
     * @throws Exception
     */
    public int completeImpr(@Param("auditRsltNo") int auditRsltNo, @Param("apprRqstNo") int apprRqstNo, @Param("imprStepCd") String imprStepCd) throws Exception;

    /**
     * PSM 계획 중복 조회
     *
     * @param plantCd
     *            사업장코드
     * @param auditYear
     *            감사년도
     * @return 카운트
     */
    public int auditplanCheck(String plantCd, String auditYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}