package com.she.mgt.elect.mapper;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ElectEvalRsltMapper {
    /**
     * 법정선임자 본인평가결과 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param halfTypeCd
     *          구분코드
     * @param plantCd
     *          사업장
     * @param safElectTitlNo
     *          법정선임자 번호
     * @param evalNm
     *          평가명
     * @param deptCd
     *          주관부서 코드
     * @param deptSubYn
     *          하위부서 조회 여부
     * @param userId
     *          대상자
     * @param meUserId
     *          본인평가자
     * @param procStepCd
     *          단계코드
     * @param stateCd
     *          상태코드
     * @param evalIncompleteYn
     *          평가 미완료 여부
     * @return 법정선임자 본인평가 목록
     * @throws Exception
     */
    public List<ElectEvalRslt> getElectEvalMeRslts(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("halfTypeCd") String halfTypeCd, @Param("plantCd") String plantCd, @Param("safElectTitlNo") int safElectTitlNo
            , @Param("evalNm") String evalNm, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("userId") String userId, @Param("meUserId") String meUserId, @Param("procStepCd") String procStepCd
            , @Param("stateCd") String stateCd, @Param("evalIncompleteYn") String evalIncompleteYn, @Param("evalPlanNo") int evalPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 법정선임자 본인평가결과/상위평가결과 상세 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 법정선임자 본인평가결과 상세
     * @throws Exception
     */
    public ElectEvalRslt getElectEvalRslt(@Param("evalUserNo") int evalUserNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 본인평가결과 항목 목록 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 평가결과 목록
     * @throws Exception
     */
    public List<ElectEvalRsltItem> getElectEvalMeRsltItems(@Param("evalUserNo") int evalUserNo) throws Exception;

    /**
     * 본인평가결과 상태 업데이트 (저장/확정)
     * @param electEvalRslt
     *          평가결과
     * @return 결과
     * @throws Exception
     */
    public int updateElectEvalMeRslt(ElectEvalRslt electEvalRslt) throws Exception;

    /**
     * 본인평가결과 항목 저장
     * @param electEvalRsltItem
     *          평가결과 항목
     * @return 결과
     * @throws Exception
     */
    public int saveElectEvalMeRsltItem(ElectEvalRsltItem electEvalRsltItem) throws Exception;

    /**
     * 본인평가결과 확정시 본인평가결과 상태 업데이트
     * @param electEvalRslt
     *          평가결과
     * @return 결과
     * @throws Exception
     */
    public int updateProcess(ElectEvalRslt electEvalRslt) throws Exception;

    /**
     * 본인평가결과 확정취소
     * @param electEvalRslt
     *          평가결과
     * @return 결과
     * @throws Exception
     */
    public int cancelElectEvalMeRslt(ElectEvalRslt electEvalRslt) throws Exception;

    /*********************************************************** 상위평가결과  ************************************************************/

    /**
     * 법정선임자 상위평가결과 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param halfTypeCd
     *          구분코드
     * @param plantCd
     *          사업장
     * @param safElectTitlNo
     *          법정선임자 번호
     * @param evalNm
     *          평가명
     * @param deptCd
     *          주관부서 코드
     * @param deptSubYn
     *          하위부서 조회 여부
     * @param userId
     *          대상자
     * @param meUserId
     *          본인평가자
     * @param procStepCd
     *          단계코드
     * @param stateCd
     *          상태코드
     * @param evalIncompleteYn
     *          평가 미완료 여부
     * @return 법정선임자 본인평가 목록
     * @throws Exception
     */
    public List<ElectEvalRslt> getElectEvalUpRslts(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("halfTypeCd") String halfTypeCd, @Param("plantCd") String plantCd, @Param("safElectTitlNo") int safElectTitlNo
            , @Param("evalNm") String evalNm, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("userId") String userId, @Param("upUserId") String upUserId, @Param("procStepCd") String procStepCd
            , @Param("stateCd") String stateCd, @Param("evalIncompleteYn") String evalIncompleteYn, @Param("evalPlanNo") int evalPlanNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 상위평가결과 항목 목록 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 평가결과 목록
     * @throws Exception
     */
    public List<ElectEvalRsltItem> getElectEvalUpRsltItems(@Param("evalUserNo") int evalUserNo) throws Exception;

    /**
     * 상위평가결과 상태 업데이트 (저장/확정/확정취소)
     * @param electEvalRslt
     *          평가결과
     * @return 결과
     * @throws Exception
     */
    public int updateElectEvalUpRslt(ElectEvalRslt electEvalRslt) throws Exception;

    /**
     * 상위평가결과 항목 저장
     * @param electEvalRsltItem
     *          평가결과 항목
     * @return 결과
     * @throws Exception
     */
    public int saveElectEvalUpRsltItem(ElectEvalRsltItem electEvalRsltItem) throws Exception;

    /**
     * 평가결과 현황 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param plantCd
     *          사업장
     * @param halfTypeCd
     *          구분
     * @param safElectTitlNo
     *          법정선임자 번호
     * @return 평가결과 현황 목록 조회
     * @throws Exception
     */
    public List<ElectEvalRsltStatus> getElectEvalRsltStatus(@Param("startYear") String startYear, @Param("endYear") String endYear, @Param("plantCd") String plantCd, @Param("halfTypeCd") String halfTypeCd, @Param("safElectTitlNo") int safElectTitlNo) throws Exception;
}
