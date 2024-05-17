package com.she.env.envEffectEval.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.envEffectEval.model.EnvEffectEvalPlan;
import com.she.env.envEffectEval.model.EnvEffectEvalRslt;

@Mapper
@Repository("com.she.env.envEffectEval.mapper.envEffectEvalPlanMapper")
public interface EnvEffectEvalPlanMapper {

    /**
     * 환경영향평가 계획 목록
     * 
     * @param plantCd
     * @param deptCd
     * @param tgtDeptCd
     * @param envEffEvalPlanStepCd
     * @param envEffEvalDivCd
     * @param evalTitle
     * @param evalYearFrom
     * @param evalYearTo
     * @param apprStepCd
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<EnvEffectEvalPlan> getEnvEffectEvalPlans(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("envEffEvalPlanStepCd") String envEffEvalPlanStepCd, @Param("envEffEvalDivCd") String envEffEvalDivCd, @Param("evalTitle") String evalTitle,
            @Param("evalYearFrom") String evalYearFrom, @Param("evalYearTo") String evalYearTo, @Param("apprStepCd") String apprStepCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 환경영향평가 계획 상세
     * 
     * @param evalPlanNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public EnvEffectEvalPlan getEnvEffectEvalPlan(@Param("evalPlanNo") int evalPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 환경영향평가 계획별 결과 목록
     * 
     * @param evalPlanNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<EnvEffectEvalRslt> getEnvEffectEvalResults(@Param("evalPlanNo") int evalPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 환경영향평가 결과 상세
     * 
     * @param evalRsltNo
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public EnvEffectEvalRslt getEnvEffectEvalResult(@Param("evalRsltNo") int evalRsltNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 환경영형평가 등록부 목록
     * 
     * @param plantCd
     * @param deptCd
     * @param tgtDeptCd
     * @param tgtProcessCd
     * @param evalTitle
     * @param evalYearFrom
     * @param evalYearTo
     * @param safFacilityCd
     * @param impoRate
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<EnvEffectEvalRslt> getEnvEffectEvalImpoResults(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("tgtDeptCd") String tgtDeptCd, @Param("tgtProcessCd") String tgtProcessCd, @Param("evalTitle") String evalTitle, @Param("evalYearFrom") String evalYearFrom, @Param("evalYearTo") String evalYearTo,
            @Param("safFacilityCd") String safFacilityCd, @Param("impoRate") String impoRate, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 환경영퍙평가 계획 등록
     * 
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    public int createEnvEffectEvalPlan(EnvEffectEvalPlan envEffectEvalPlan) throws Exception;

    /**
     * 환경영향평가 계획 수정
     * 
     * @param envEffectEvalPlan
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalPlan(EnvEffectEvalPlan envEffectEvalPlan) throws Exception;

    /**
     * 환경영향평가 계획 삭제
     * 
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public int deleteEnvEffectEvalPlan(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 환경영향평가 결과 등록
     * 
     * @param envEffectEvalRslt
     * @return
     * @throws Exception
     */
    public int createEnvEffectEvalRslt(EnvEffectEvalRslt envEffectEvalRslt) throws Exception;

    /**
     * 환경영향평가 결과 수정
     * 
     * @param envEffectEvalRslt
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalRslt(EnvEffectEvalRslt envEffectEvalRslt) throws Exception;

    /**
     * 환경영향평가 결과 삭제
     * 
     * @param evalRsltNo
     * @return
     * @throws Exception
     */
    public int deleteEnvEffectEvalRslt(@Param("evalRsltNo") int evalRsltNo) throws Exception;

    /**
     * 환경영향평가 결과 전체삭제
     * 
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public int deleteEnvEffectEvalRslts(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 환경영향평가 환경담당자 의견 등록
     * 
     * @param evalRsltNo
     * @param managerId
     * @param managerComment
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalRsltManagerComment(@Param("evalRsltNo") int evalRsltNo, @Param("managerId") String managerId, @Param("managerComment") String managerComment) throws Exception;

    /**
     * 환경영향평가 상태변경
     * 
     * @param evalPlanNo
     * @param envEffEvalPlanStepCd
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalPlanStatus(@Param("evalPlanNo") int evalPlanNo, @Param("envEffEvalPlanStepCd") String envEffEvalPlanStepCd) throws Exception;

    /**
     * 환경영향평가 개선상태변경
     * 
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalImprStatus(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 환경영향평가 계획 결재번호 업데이트
     * 
     * @param evalPlanNo
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalPlanAppr(@Param("evalPlanNo") int evalPlanNo, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    /**
     * 환경영향평가 결과 결재번호 업데이트
     * 
     * @param evalPlanNo
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int updateEnvEffectEvalRsltAppr(@Param("evalPlanNo") int evalPlanNo, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    /**
     * 엑셀업로드 설비 체크
     * 
     * @param facilityNm
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public String checkExcelFacilityNm(@Param("facilityNm") String facilityNm, @Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 엑셀업로드 문구 체크
     * 
     * @param lang
     * @param grpCd
     * @param val
     * @return
     * @throws Exception
     */
    public String checkExcelLbl(@Param("lang") String lang, @Param("grpCd") String grpCd, @Param("val") String val) throws Exception;

    /**
     * 엑셀업로드 메시지 조회
     * 
     * @param lang
     * @return
     * @throws Exception
     */
    public HashMap<String, String> getExcelMessageLbl(@Param("lang") String lang) throws Exception;
}
