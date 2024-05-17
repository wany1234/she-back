package com.she.rsa.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.AttachFileMapper;
import com.she.common.model.DefaultParam;
import com.she.file.service.FileStorageService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.mapper.DeptProcessMapper;
import com.she.rsa.baseInfo.mapper.JobMapper;
import com.she.rsa.model.Job;
import com.she.rsa.model.JobStep;
import com.she.rsa.model.RiskHazard;

@Service
public class JobService {

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private DeptProcessMapper deptProcessMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 직무 조회
     *
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
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
    public List<Job> getJobs(String plantCd, String deptCd, String deptSubYn, String processCd, String jobCd, String jobNm, String sopNo, String sopNm, String useYn, DefaultParam defaultParam) throws Exception {
        return jobMapper.getJobs(plantCd, deptCd, deptSubYn, processCd, jobCd, jobNm, sopNo, sopNm, useYn, defaultParam);
    }

    /**
     * 직무 상세 조회
     *
     * @param jobId
     *            직무Id
     * @return 직무
     * @throws Exception
     */
    public Job getJob(int jobId) throws Exception {
        return this.jobMapper.getJob(jobId);
    }

    /**
     * Rev No 중복검사
     *
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정번호
     * @param jobCd
     *            직무코드
     * @param jobRevno
     *            Rev No
     * @return Rev No 카운트
     * @throws Exception
     */
    public List<HashMap<String, Object>> countRevno(String plantCd, String deptCd, String jobCd, int selectedJobId, String jobNm, int jobRevno) throws Exception {
        return jobMapper.countRevno(plantCd, deptCd, jobCd, selectedJobId, jobNm, jobRevno);
    }

    /**
     * 직무 신규등록
     *
     * @param Job
     *            직무
     * @return 직무Id
     * @throws Exception
     */
    @Transactional
    public int createJob(Job job) throws Exception {
        this.jobMapper.createJob(job);

        return job.getJobId();
    }

    /**
     * 직무 수정
     *
     * @param Job
     *            직무
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateJob(Job job) throws Exception {
        return this.jobMapper.updateJob(job) > 0 ? job.getJobId() : 0;
    }

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
    public List<JobStep> getJobSteps(int jobId, String useYn) throws Exception {
        return jobMapper.getJobSteps(jobId, useYn);
    }

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
    public List<JobStep> getJobStepHistorys(int jobId, int jobStepNo) throws Exception {
        return jobMapper.getJobStepHistorys(jobId, jobStepNo);
    }

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
    public JobStep getJobStep(int jobStepId) throws Exception {
        return this.jobMapper.getJobStep(jobStepId);
    }

    /**
     * 작업절차 개정번호 체크
     *
     * @return 작업절차 개정번호 체크
     * @throws Exception
     */
    public HashMap<String, Object> getJobStepCheckRev(int jobId, int jobStepNo, int revNo) throws Exception {
        HashMap<String, Object> returnData = new HashMap<String, Object>();
        returnData.put("rev", this.jobMapper.getJobStepCheckRev(jobId, jobStepNo, revNo));
        returnData.put("datas", jobMapper.getJobSteps(jobId, ""));
        return returnData;
    }

    /**
     * 작업절차 신규등록
     *
     * @param jobStep
     *            작업절차
     * @return 작업절차순번
     * @throws Exception
     */
    @Transactional
    public HashMap<String, Object> createJobStep(JobStep jobStep) throws Exception {
        this.jobMapper.createJobStep(jobStep);

        // 작업절차순번 중복검사
        // if (this.jobMapper.countJobStep(jobStep.getJobId(),
        // jobStep.getJobStepNo()) > 0)
        // {
        // jobStepNo = -1;
        // }
        // else
        // {
        // jobStepNo += this.jobMapper.createJobStep(jobStep);
        // }

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("jobId", jobStep.getJobId());
        map.put("jobStepNo", jobStep.getJobStepNo());
        map.put("jobStepId", jobStep.getJobStepId());

        return map;
    }

    /**
     * 작업절차 수정
     *
     * @param jobStep
     *            작업절차
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateJobStep(JobStep jobStep) throws Exception {
        return this.jobMapper.updateJobStep(jobStep);
    }

    /**
     * 직무단계별 유해위험요인 조회
     *
     * @param jobId
     *            직무Id
     * @return 직무단계별 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getJobStepRiskHazards(int jobId) throws Exception {
        return jobMapper.getJobStepRiskHazards(jobId);
    }

    /**
     * 직무별 유해위험요인 조회
     *
     * @param jobStepId
     *            직무단계Id
     * @return 직무별 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getJobRiskHazards(int jobStepId) throws Exception {
        return jobMapper.getJobRiskHazards(jobStepId);
    }

    /**
     * 전체 유해위험요인 트리
     *
     * @param jobId
     *            직무Id
     * @return 전체 유해위험요인 트리 목록
     * @throws Exception
     */
    public List<RiskHazard> getAllRiskHazards(int jobStepId) throws Exception {
        return jobMapper.getAllRiskHazards(jobStepId);
    }

    /**
     * 직무별 유해위험요인 신규등록
     *
     * @param List<RiskHazard>
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createJobRiskHazard(List<RiskHazard> jobRiskHazards) throws Exception {
        int resultNo = 0;

        resultNo = this.jobMapper.deleteJobRiskHazard(jobRiskHazards.get(0).getJobStepId(), 0);

        for (RiskHazard jobRiskHazard : jobRiskHazards) {
            resultNo += this.jobMapper.createJobRiskHazard(jobRiskHazard);
        }
        return resultNo;
    }

    /**
     * 건강검진계획 삭제
     *
     * @param List<RiskHazard>
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteJobRiskHazard(List<RiskHazard> jobRiskHazards) throws Exception {
        int resultNo = 0;
        for (RiskHazard jobRiskHazard : jobRiskHazards) {
            resultNo += this.jobMapper.deleteJobRiskHazard(jobRiskHazard.getJobStepId(), jobRiskHazard.getRiskHazardNo());
        }
        return resultNo;
    }

    /**
     * 직무 이력정보 조회
     *
     * @param jobCd
     *            직무코드
     * @return 직무 이력정보 목록
     * @throws Exception
     */
    public List<Job> getJobHistories(String jobCd) throws Exception {
        return jobMapper.getJobHistories(jobCd);
    }

    public int deleteRevJobHistory(List<Job> jobs) throws Exception {
        int count = 0;
        for (Job job : jobs) {
            count += this.jobMapper.deleteRevJobHistory(job);
        }

        return count;
    }

}