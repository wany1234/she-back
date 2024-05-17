package com.she.rsa.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.Job;
import com.she.rsa.model.JobStep;
import com.she.rsa.model.RiskHazard;

@Mapper
@Repository("com.she.rsa.baseInfo.mapper.JobMapper")
public interface JobMapper {

    /**
     * 직무 조회
     * 
     * @param deptCd
     *            부서코드
     * @param processCd
     *            공정코드
     * @param jobCd
     *            직무코드
     * @param jobNm
     *            직무명
     * @param sopNo
     *            SOP번호
     * @param sopNm
     *            SOP명
     * @param useYn
     *            사용여부
     * @return 직무 목록
     * @throws Exception
     */
    public List<Job> getJobs(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("processCd") String processCd, @Param("jobCd") String jobCd, @Param("jobNm") String jobNm, @Param("sopNo") String sopNo, @Param("sopNm") String sopNm, @Param("useYn") String useYn,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 직무 상세 조회
     * 
     * @param jobId
     *            직무Id
     * @return 직무
     * @throws Exception
     */
    public Job getJob(@Param("jobId") int jobId) throws Exception;

    /**
     * Rev No 중복검사
     * 
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param processCd
     *            공정코드
     * @param jobCd
     *            직무코드
     * @param jobRevno
     *            Rev No
     * @return Rev No 카운트
     * @throws Exception
     */
    public List<HashMap<String, Object>> countRevno(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("jobCd") String jobCd, @Param("selectedJobId") int selectedJobId, @Param("jobNm") String jobNm, @Param("jobRevno") int jobRevno) throws Exception;

    /**
     * 직무 신규등록
     * 
     * @param job
     *            직무
     * @return 등록 행 수
     * @throws Exception
     */
    public int createJob(Job job) throws Exception;

    /**
     * 직무 수정
     * 
     * @param job
     *            직무
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateJob(Job job) throws Exception;

    /**
     * 작업절차 조회
     * 
     * @param jobId
     *            직무Id
     * @param useYn
     *            사용여부
     * @return 작업절차 목록
     * @throws Exception
     */
    public List<JobStep> getJobSteps(@Param("jobId") int jobId, @Param("useYn") String useYn) throws Exception;

    /**
     * 작업절차 이력 조회
     * 
     * @param jobId
     *            직무Id
     * @param jobStepNo
     *            작업순번
     * @return 작업절차 이력 목록
     * @throws Exception
     */
    public List<JobStep> getJobStepHistorys(@Param("jobId") int jobId, @Param("jobStepNo") int jobStepNo) throws Exception;

    /**
     * 작업절차 상세 조회
     * 
     * @param jobId
     *            직무Id
     * @param jobStepNo
     *            작업절차순번
     * @return 작업절차
     * @throws Exception
     */
    public JobStep getJobStep(@Param("jobStepId") int jobStepId) throws Exception;

    /**
     * 작업절차 개정번호 체크
     * 
     * @return 작업절차 개정번호 체크
     * @throws Exception
     */
    public HashMap<String, Object> getJobStepCheckRev(@Param("jobId") int jobId, @Param("jobStepNo") int jobStepNo, @Param("revNo") int revNo) throws Exception;

    /**
     * 작업절차 신규등록
     * 
     * @param jobStep
     *            작업절차
     * @return 등록 행 수
     * @throws Exception
     */
    public int createJobStep(JobStep jobStep) throws Exception;

    /**
     * 작업절차 수정
     * 
     * @param jobStep
     *            작업절차
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateJobStep(JobStep jobStep) throws Exception;

    /**
     * 직무단계별 유해위험요인 조회
     * 
     * @param jobId
     *            직무Id
     * @return 직무단계별 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getJobStepRiskHazards(@Param("jobId") int jobId) throws Exception;

    /**
     * 직무별 유해위험요인 조회
     * 
     * @param jobStepId
     *            직무단계Id
     * @return 직무별 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getJobRiskHazards(@Param("jobStepId") int jobStepId) throws Exception;

    /**
     * 직무별 유해위험요인 조회
     * 
     * @param jobId
     *            직무Id
     * @param jobStepNo
     *            직무단계번호
     * @return 직무별 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getAllRiskHazards(@Param("jobStepId") int jobStepId) throws Exception;

    /**
     * 직무별 유해위험요인 신규등록
     * 
     * @param jobRiskHazard
     *            직무별 유해위험요인
     * @return 등록 행 수
     * @throws Exception
     */
    public int createJobRiskHazard(RiskHazard jobRiskHazard) throws Exception;

    /**
     * 직무별 유해위험요인 삭제
     * 
     * @param jobStepId
     * @param riskHazardNo
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteJobRiskHazard(@Param("jobStepId") int jobStepId, @Param("riskHazardNo") int riskHazardNo) throws Exception;

    /**
     * 직무 이력정보 조회
     * 
     * @param jobCd
     *            직무코드
     * @return 직무 이력정보 목록
     * @throws Exception
     */
    public List<Job> getJobHistories(@Param("jobCd") String jobCd) throws Exception;

    public int deleteRevJobHistory(Job job) throws Exception;

}