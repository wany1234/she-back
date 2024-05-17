package com.she.mgt.elect.mapper;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.ElectEvalItem;
import com.she.mgt.model.ElectEvalPlan;
import com.she.mgt.model.ElectEvalUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ElectEvalPlanMapper {
    /**
     * 평가대상 법정선임자 목록 조회
     * 
     * @param safElectTitlNo
     *            법정선임자 번호
     * @return 평가대상 법정선임자 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSafElectTitlEvalTarget(@Param("safElectTitlNo") int safElectTitlNo, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 법정선임자 평가계획 목록 조회
     * 
     * @param startYear
     *            시작년도
     * @param endYear
     *            종료년도
     * @param halfTypeCd
     *            구분코드
     * @param deptCd
     *            주관부서코드
     * @param deptSubYn
     *            하위부서 조회여부
     * @param safElectTitlNo
     *            법정선임자번호
     * @param evalNm
     *            평가명
     * @param procStepCd
     *            단계코드
     * @param stateCd
     *            상태코드
     * @param evalIncompleteYn
     *            평가미완료여부
     * @param evalStepCd
     *            평가미완료시 조회할 상태코드
     * @param defaultParam
     * @return 법정선임자 평가계획 목록
     * @throws Exception
     */
    public List<ElectEvalPlan> getElectEvalPlans(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("halfTypeCd") String halfTypeCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("safElectTitlNo") int safElectTitlNo, @Param("evalNm") String evalNm, @Param("procStepCd") String procStepCd,
            @Param("stateCd") String stateCd, @Param("evalIncompleteYn") String evalIncompleteYn, @Param("evalStepCd") String evalStepCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 법정선임자 평가계획 상세 조회
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 법정선임자 평가계획 상세
     * @throws Exception
     */
    public ElectEvalPlan getElectEvalPlan(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 법정선임자 평가계획 신규 등록
     * 
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 결과
     * @throws Exception
     */
    public int createElectEvalPlan(ElectEvalPlan electEvalPlan) throws Exception;

    /**
     * 법정선임자 평가계획 수정
     * 
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 결과
     * @throws Exception
     */
    public int updateElectEvalPlan(ElectEvalPlan electEvalPlan) throws Exception;

    /**
     * 법정선임자 평가계획 삭제
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteElectEvalPlan(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 법정선임자 평가계획 평가대상자 목록 조회
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 법정선임자 평가계획 평가대상자 목록
     * @throws Exception
     */
    public List<ElectEvalUser> getElectEvalUsers(@Param("evalPlanNo") int evalPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 법정선임자 평가계획 평가대상자 저장
     * 
     * @param electEvalUser
     *            법정선임자 평가계획 평가대상자
     * @return 결과
     * @throws Exception
     */
    public int saveElectEvalUser(ElectEvalUser electEvalUser) throws Exception;

    /**
     * 법정선임자 평가계획 평가대상자 삭제
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @param evalUserNo
     *            법정선임자 평가계획 평가대상자 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteElectEvalUser(@Param("evalPlanNo") int evalPlanNo, @Param("evalUserNo") int evalUserNo) throws Exception;

    /**
     * 법정선임자 평가계획 평가항목 목록 조회 (신규등록)
     * 
     * @param safElectTitlNo
     *            법정선임자 번호
     * @return 법정선임자 평가계획 평가항목 목록
     * @throws Exception
     */
    public List<ElectEvalItem> getElectEvalItemsInit(@Param("safElectTitlNo") int safElectTitlNo) throws Exception;

    /**
     * 법정선임자 평가계획 평가항목 목록 조회 (수정)
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 법정선임자 평가계획 평가항목 목록
     * @throws Exception
     */
    public List<ElectEvalItem> getElectEvalItems(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 평가항목 저장
     * 
     * @param electEvalItem
     *            법정선임자 평가항목
     * @return 결과
     * @throws Exception
     */
    public int createElectEvalItem(ElectEvalItem electEvalItem) throws Exception;

    /**
     * 평가항목 삭제
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteElectEvalItem(@Param("evalPlanNo") int evalPlanNo) throws Exception;

    /**
     * 법정선임자 평가계획 결재정보 업데이트
     * 
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 결과
     * @throws Exception
     */
    public int updateAppr(ElectEvalPlan electEvalPlan) throws Exception;
}
