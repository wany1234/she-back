package com.she.rsa.workRiskEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval04Appr;
import com.she.rsa.model.WorkRiskEval05Exam;

@Mapper
@Repository("com.she.rsa.workRiskEval.mapper.WorkRiskEval05Mapper")
public interface WorkRiskEval05Mapper {

    /**
     * 작업위험성평가 검토 목록
     * 
     * @return 작업위험성평가 검토 목록
     * @throws Exception
     */
    public List<WorkRiskEval04Appr> getworkRiskEval05List(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalTypeCd") String evalTypeCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval05CheckRslt(WorkRiskEval05Exam workRiskEval05Exam) throws Exception;

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval05CheckRsltPrcs(WorkRiskEval05Exam workRiskEval05Exam) throws Exception;

    /**
     * 작업위험성평가 공정설정 평가대상공정 확정
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과 일괄적용
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval05CheckRsltPrcsAll(WorkRiskEval05Exam workRiskEval05Exam) throws Exception;

    /**
     * 작업위험성평가 공정설정 평가대상공정 초기화
     * 
     * @param WorkRiskEval05Exam
     *            작업위험성평가 검토결과
     * @return 작업위험성평가 검토결과 관리 Key값
     * @throws Exception
     */
    public int updateWorkRiskEval05DeptPrcsInit(WorkRiskEval05Exam workRiskEval05Exam) throws Exception;

    public int updateAppr(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd, @Param("apprRqstNo") int apprRqstNo, @Param("bizApprStepCd") String bizApprStepCd);

    public String imprActNoList(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd);

    public String imprActMailSender(@Param("plantCd") String plantCd, @Param("evalYear") String evalYear, @Param("evalNo") String evalNo, @Param("deptCd") String deptCd);
}
