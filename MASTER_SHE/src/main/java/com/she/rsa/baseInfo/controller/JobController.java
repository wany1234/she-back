package com.she.rsa.baseInfo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.she.common.model.DefaultParam;
import com.she.rsa.baseInfo.service.JobService;
import com.she.rsa.model.Job;
import com.she.rsa.model.JobStep;
import com.she.rsa.model.RiskHazard;
import com.she.utils.RequestMapper;

@RestController
@RequestMapping("api/rsa/baseinfo")
public class JobController {

    // queryString 변환을 위한 mapper 선언
    @Autowired
    private RequestMapper requestMapper;

    @Autowired
    private JobService jobService;

    /**
     * 직무 조회
     *
     * @param parameter
     *            검색조건
     * @return 직무 목록
     * @throws Exception
     */
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobs(@RequestParam HashMap<String, Object> parameter, @ModelAttribute DefaultParam defaultParam) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 하위부서 포함여부
        String deptSubYn = map.containsKey("deptSubYn") ? map.get("deptSubYn").toString() : "Y";

        // 공정
        String processCd = map.containsKey("processCd") ? map.get("processCd").toString() : "";

        // 직무코드
        String jobCd = map.containsKey("jobCd") ? map.get("jobCd").toString() : "";
        // 직무명
        String jobNm = map.containsKey("jobNm") ? map.get("jobNm").toString() : "";
        // SOP번호
        String sopNo = map.containsKey("sopNo") ? map.get("sopNo").toString() : "";
        // SOP명
        String sopNm = map.containsKey("sopNm") ? map.get("sopNm").toString() : "";
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<Job> jobs = jobService.getJobs(plantCd, deptCd, deptSubYn, processCd, jobCd, jobNm, sopNo, sopNm, useYn, defaultParam);
        return ResponseEntity.ok().body(jobs);
    }

    /**
     * 직무 상세 조회
     *
     * @param jobId
     *            직무 번호
     * @return 직무
     * @throws Exception
     */
    @GetMapping("/job/{jobId}")
    public ResponseEntity<Job> getJob(@PathVariable(name = "jobId") int jobId) throws Exception {
        return ResponseEntity.ok().body(this.jobService.getJob(jobId));
    }

    /**
     * Rev No 중복검사
     *
     * @param deptCd
     *            부서코드
     * @param processNo
     *            공정코드
     * @param jobCd
     *            직무코드
     * @param jobRevno
     *            Rev No
     * @return Rev No 카운트
     * @throws Exception
     */
    @GetMapping("/countjob")
    public ResponseEntity<List<HashMap<String, Object>>> countRevno(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 사업장
        String plantCd = map.containsKey("plantCd") ? map.get("plantCd").toString() : "";
        // 부서
        String deptCd = map.containsKey("deptCd") ? map.get("deptCd").toString() : "";
        // 직무코드
        String jobCd = map.containsKey("jobCd") ? map.get("jobCd").toString() : "";
        // key
        int selectedJobId = map.containsKey("selectedJobId") ? Integer.parseInt("".equals(map.get("selectedJobId").toString()) ? "0" : map.get("selectedJobId").toString()) : 0;
        // 직무명
        String jobNm = map.containsKey("jobNm") ? map.get("jobNm").toString() : "";
        // Rev no
        int jobRevno = map.containsKey("jobRevno") ? Integer.parseInt("".equals(map.get("jobRevno").toString()) ? "0" : map.get("jobRevno").toString()) : 0;

        return ResponseEntity.ok().body(this.jobService.countRevno(plantCd, deptCd, jobCd, selectedJobId, jobNm, jobRevno));
    }

    /**
     * 직무 신규등록
     *
     * @param Job
     *            직무
     * @return 직무Id
     * @throws Exception
     */
    @PostMapping("/job")
    public ResponseEntity<Integer> createJob(@RequestBody Job job) throws Exception {
        return ResponseEntity.ok().body(this.jobService.createJob(job));
    }

    /**
     * 직무 수정
     *
     * @param Job
     *            직무
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/job")
    public ResponseEntity<Integer> updateJob(@RequestBody Job job) throws Exception {
        return ResponseEntity.ok().body(this.jobService.updateJob(job));
    }

    /**
     * 작업절차 조회
     *
     * @param parameter
     *            직무Id
     * @return 작업절차 목록
     * @throws Exception
     */
    @GetMapping("/jobsteps")
    public ResponseEntity<List<JobStep>> getJobSteps(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무Id
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;
        // 사용여부
        String useYn = map.containsKey("useYn") ? map.get("useYn").toString() : "";

        List<JobStep> jobSteps = jobService.getJobSteps(jobId, useYn);
        return ResponseEntity.ok().body(jobSteps);
    }

    /**
     * 작업절차 이력 조회
     *
     * @param parameter
     *            직무Id
     * @return 작업절차 이력 목록
     * @throws Exception
     */
    @GetMapping("/jobstephistorys")
    public ResponseEntity<List<JobStep>> getJobStepHistorys(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무Id
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;
        // 작업순번
        int jobStepNo = map.containsKey("jobStepNo") ? Integer.parseInt("".equals(map.get("jobStepNo").toString()) ? "0" : map.get("jobStepNo").toString()) : 0;

        return ResponseEntity.ok().body(jobService.getJobStepHistorys(jobId, jobStepNo));
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
    @GetMapping("/jobstep/{jobStepId}")
    public ResponseEntity<JobStep> getJobStep(@PathVariable(name = "jobStepId") int jobStepId) throws Exception {
        return ResponseEntity.ok().body(this.jobService.getJobStep(jobStepId));
    }

    /**
     * 작업절차 이력 조회
     *
     * @param parameter
     *            직무Id
     * @return 작업절차 이력 목록
     * @throws Exception
     */
    @GetMapping("/jobstepcheckrev")
    public ResponseEntity<HashMap<String, Object>> getJobStepCheckRev(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무Id
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;
        // 작업순번
        int jobStepNo = map.containsKey("jobStepNo") ? Integer.parseInt("".equals(map.get("jobStepNo").toString()) ? "0" : map.get("jobStepNo").toString()) : 0;
        // 개정번호
        int revNo = map.containsKey("revNo") ? Integer.parseInt("".equals(map.get("revNo").toString()) ? "0" : map.get("revNo").toString()) : 0;

        return ResponseEntity.ok().body(jobService.getJobStepCheckRev(jobId, jobStepNo, revNo));
    }

    /**
     * 작업절차 신규등록
     *
     * @param jobStep
     *            작업절차
     * @return 작업절차순번
     * @throws Exception
     */
    @PostMapping("/jobstep")
    public ResponseEntity<HashMap<String, Object>> createJobStep(@RequestBody JobStep jobStep) throws Exception {
        return ResponseEntity.ok().body(this.jobService.createJobStep(jobStep));
    }

    /**
     * 작업절차 수정
     *
     * @param jobStep
     *            작업절차
     * @return 수정 행 수
     * @throws Exception
     */
    @PutMapping("/jobstep")
    public ResponseEntity<Integer> updateJobStep(@RequestBody JobStep jobStep) throws Exception {
        return ResponseEntity.ok().body(this.jobService.updateJobStep(jobStep));
    }

    /**
     * 직무단계별 유해위험요인 조회
     *
     * @param parameter
     *            직무Id
     * @return 직무단계별 유해위험요인 목록
     * @throws Exception
     */
    @GetMapping("/jobstepriskhazards")
    public ResponseEntity<List<RiskHazard>> getJobStepRiskHazards(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무Id
        int jobId = map.containsKey("jobId") ? Integer.parseInt("".equals(map.get("jobId").toString()) ? "0" : map.get("jobId").toString()) : 0;

        List<RiskHazard> jobriskHazards = jobService.getJobStepRiskHazards(jobId);
        return ResponseEntity.ok().body(jobriskHazards);
    }

    /**
     * 직무별 유해위험요인 조회
     *
     * @param parameter
     *            직무Id
     * @return 직무별 유해위험요인 목록
     * @throws Exception
     */
    @GetMapping("/jobriskhazards")
    public ResponseEntity<List<RiskHazard>> getJobRiskHazards(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무단계Id
        int jobStepId = map.containsKey("jobStepId") ? Integer.parseInt("".equals(map.get("jobStepId").toString()) ? "0" : map.get("jobStepId").toString()) : 0;

        List<RiskHazard> jobriskHazards = jobService.getJobRiskHazards(jobStepId);
        return ResponseEntity.ok().body(jobriskHazards);
    }

    /**
     * 트리 메뉴를 구성할 기초 메뉴 정보 조회
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/allriskhazards")
    public ResponseEntity<List<RiskHazard>> getAllRiskHazards(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무단계번호
        int jobStepId = map.containsKey("jobStepId") ? Integer.parseInt("".equals(map.get("jobStepId").toString()) ? "0" : map.get("jobStepId").toString()) : 0;

        List<RiskHazard> allRiskHazards = jobService.getAllRiskHazards(jobStepId);
        return ResponseEntity.ok().body(allRiskHazards);
    }

    /**
     * 직무별 유해위험요인 신규등록
     *
     * @param jobRiskHazard
     *            직무별 유해위험요인 등록 리스트
     * @return 등록 행 수
     * @throws Exception
     */
    @PostMapping("/jobriskhazard")
    public ResponseEntity<Integer> createJobRiskHazard(@RequestBody List<RiskHazard> jobRiskHazards) throws Exception {
        return ResponseEntity.ok().body(this.jobService.createJobRiskHazard(jobRiskHazards));
    }

    /**
     * 직무별 유해위험요인 삭제
     *
     * @param jobRiskHazard
     *            직무별 유해위험요인 삭제 리스트
     * @return 삭제 행 수
     * @throws Exception
     */
    @DeleteMapping("/jobriskhazard")
    public ResponseEntity<Integer> deleteJobRiskHazard(@RequestBody List<RiskHazard> jobRiskHazards) throws Exception {
        return ResponseEntity.ok().body(this.jobService.deleteJobRiskHazard(jobRiskHazards));
    }

    @GetMapping("/jobhistories")
    public ResponseEntity<List<Job>> getJobHistories(@RequestParam HashMap<String, Object> parameter) throws Exception {
        HashMap<String, Object> map = this.requestMapper.convertAsParameter(parameter);

        // 직무코드
        String jobCd = map.containsKey("jobCd") ? map.get("jobCd").toString() : "";

        List<Job> jobHistories = jobService.getJobHistories(jobCd);
        return ResponseEntity.ok().body(jobHistories);
    }

    @DeleteMapping("/jobHistory/revdelete")
    public ResponseEntity<Integer> deleteRevJobHistory(@RequestBody List<Job> jobs) throws Exception {
        return ResponseEntity.ok().body(this.jobService.deleteRevJobHistory(jobs));
    }

}