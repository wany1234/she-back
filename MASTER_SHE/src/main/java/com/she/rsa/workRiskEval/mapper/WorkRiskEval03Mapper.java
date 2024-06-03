package com.she.rsa.workRiskEval.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval03Rslt;
import com.she.rsa.model.WorkRiskEval03RsltDetailUnitWork;
import com.she.rsa.model.WorkRiskEval03RsltDetailWork;
import com.she.rsa.model.WorkRiskEval03RsltEvalParty;
import com.she.rsa.model.WorkRiskEval03RsltRefInd;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval03Mapper")
public interface WorkRiskEval03Mapper {

    /**
     * 작업위험성평가 결과 목록
     * 
     * @return 작업위험성평가 결과 목록
     * @throws Exception
     */
    public List<WorkRiskEval03Rslt> getworkRiskEval03List(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("prcsNm01") String prcsNm01, @Param("prcsNm02") String prcsNm02, @Param("prcsNm03") String prcsNm03,
            @Param("confirmYn") String confirmYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 결과 상세
     * 
     * @param evalNo(평가번호),
     *            deptCd(대상부서코드), processCd(주요설비코드), unitWorkCd(작업명)
     * @return 작업위험성평가 결과 상세조회
     * @throws Exception
     */
    public WorkRiskEval03Rslt getWorkRiskEval03Info(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 결과 평가참여자 조회
     * 
     * @return 작업위험성평가 결과 평가참여자 목록
     * @throws Exception
     */
    public List<WorkRiskEval03RsltDetailWork> getDetailWorkList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 결과 평가참여자 조회
     * 
     * @return 작업위험성평가 결과 평가참여자 목록
     * @throws Exception
     */
    public List<WorkRiskEval03RsltEvalParty> getEvalPartyList(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가결과 공정설정 평가대상공정 평가일 수정
     * 
     * @param WorkRiskEval03Rslt
     * @return WorkRiskEval03Rslt
     * @throws Exception
     */
    public int updateEvalDeptPrcs(WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception;

    /**
     * 작업위험성평가결과 공정설정 관련변경관리 정보 삭제
     * 
     * @param WorkRiskEval03Rslt
     * @return WorkRiskEval03Rslt
     * @throws Exception
     */
    public int deleteSafChngRefBiz(WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception;

    /**
     * 작업위험성평가결과 공정설정 관련변경관리 정보 등록
     * 
     * @param WorkRiskEval03Rslt
     * @return WorkRiskEval03Rslt
     * @throws Exception
     */
    public int insertSafChngRefBiz(WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception;

    /**
     * 작업위험성평가 결과 평가참여자 삭제
     * 
     * @param WorkRiskEval03Rslt
     * @return WorkRiskEval03Rslt
     * @throws Exception
     */
    public int deleteWorkRiskEval03EvalParty(WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception;

    /**
     * 작업위험성평가 결과 평가참여자 등록
     * 
     * @param WorkRiskEval03Rslt
     * @return WorkRiskEval03Rslt
     * @throws Exception
     */
    public int createWorkRiskEval03EvalParty(WorkRiskEval03RsltEvalParty workRiskEval03RsltEvalParty) throws Exception;

    /**
     * 작업위험성평가 결과 세부작업 팝업
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 결과 세부작업 팝업 상세조회
     * @throws Exception
     */
    public WorkRiskEval03RsltDetailUnitWork getWorkRiskEval03DtlWorkInfo(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 작업위험성평가 결과 세부작업 팝업
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 결과 세부작업 팝업 상세조회
     * @throws Exception
     */
    public WorkRiskEval03RsltDetailUnitWork getWorkRiskEval03DtlWorkInfoAdd(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, @Param("dtlWkSeq") String dtlWkSeq, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 결과 관련지표 콤보
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관련지표 콤보 조회
     * @throws Exception
     */
    public List<WorkRiskEval03RsltRefInd> getWorkRiskEval03RefIndItemList(@Param("plantCd") String plantCd, @Param("indTypeCd") String indTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 결과 세부작업 등록
     * 
     * @param workRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 관리 Key값
     * @throws Exception
     */
    public int createWorkRiskEval03WorkPop(WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork) throws Exception;

    /**
     * 작업위험성평가 결과 세부작업 수정
     * 
     * @param workRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 관리 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval03WorkPop(WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork) throws Exception;

    /**
     * 작업위험성평가 결과 세부작업 조회
     * 
     * @param WorkRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval03RsltDetailUnitWork> getWorkRiskEval03RefIndList(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("unitWorkCd") String unitWorkCd, @Param("dtlWkNm") String dtlWkNm,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가결과 삭제
     * 
     * @param Map
     * @return 1
     * @throws Exception
     */
    public int deleteWorkRiskEval03safImprAct(Map<String, Object> param) throws Exception;

    /**
     * 작업위험성평가결과 삭제
     * 
     * @param Map
     * @return 1
     * @throws Exception
     */
    public int deleteWorkRiskEval03DetailWork(Map<String, Object> param) throws Exception;

    /**
     * 작업위험성평가결과 등록/수정
     * 
     * @param WorkRiskEval03RsltDetailWork
     * @return WorkRiskEval03RsltDetailWork
     * @throws Exception
     */
    public int mergeWorkRiskEval03DetailWork(WorkRiskEval03RsltDetailWork workRiskEval03RsltDetailWork) throws Exception;

}
