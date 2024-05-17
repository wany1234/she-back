/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.rsa.assess.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.rsa.assess.mapper.DeptAssessJobMapper;
import com.she.rsa.model.DeptAssessJob;
import com.she.rsa.model.Job;

/**
 * 평가대상직무 기능정의
 *
 */
@Service
public class DeptAssessJobService {
    @Autowired
    private DeptAssessJobMapper deptAssessJobMapper;

    // @Autowired
    // private AssessPlanMapper assessPlanMapper;
    //
    // @Autowired
    // private JobMapper jobMapper;

    /**
     * 평가대상직무 조회
     * 
     * @param assessPlanNo
     *            평가계획 번호
     * @param deptCd
     *            부서 코드
     * @return 평가대상직무 목록
     * @throws Exception
     */
    public List<DeptAssessJob> getDeptAssessJobs(int assessPlanNo, String processCd, int assessTypeNo)
            throws Exception {
        List<DeptAssessJob> deptAssessJobs = new ArrayList<DeptAssessJob>();
        // if (assessTypeNo == 1) deptAssessJobs =
        // deptAssessJobMapper.getDeptAssessJobKRASs(assessPlanNo, deptCd,
        // assessTypeNo);
        // else if (assessTypeNo == 2) deptAssessJobs =
        // deptAssessJobMapper.getDeptAssessJobJSAs(assessPlanNo, deptCd,
        // assessTypeNo);
        /*
         * 2019-06-19 kdh 위험성평가 함에 있어 kras와 jsa로 평가직무를 테이블을 구분지을필요가 없기에 하나로 통일
         */
        deptAssessJobs = deptAssessJobMapper.getDeptAssessJobKRASs(assessPlanNo, processCd, assessTypeNo);

        return deptAssessJobs;
    }

    /**
     * 평가대상직무 등록
     * 
     * @param AssessPlan
     *            평가대상직무
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createDeptAssessJob(DeptAssessJob deptAssessJob) throws Exception {
        int resultNo = 0;

        // AssessPlan assessPlan =
        // assessPlanMapper.getAssessPlan(deptAssessJob.getAssessPlanNo());
        //
        // if ("KRAS".equals(assessPlan.getAssessTypeNm()))
        // {
        // deptAssessJobMapper.deleteDeptAssessJob(deptAssessJob.getAssessPlanNo(),
        // "", 0);
        //
        // if (deptAssessJob != null && deptAssessJob.getJobs() != null &&
        // deptAssessJob.getJobs().size() > 0)
        // {
        // for (Job job: deptAssessJob.getJobs())
        // {
        // deptAssessJob.setCreateUserId(job.getCreateUserId());
        // deptAssessJob.setJobId(job.getJobId());
        // deptAssessJob.setDeptCd(job.getDeptCd());
        // resultNo += deptAssessJobMapper.createDeptAssessJob(deptAssessJob);
        // }
        // }
        // }
        // else if ("JSA".equals(assessPlan.getAssessTypeNm()))
        // {
        // deptAssessJobMapper.deleteDeptAssessStep(deptAssessJob.getAssessPlanNo(),
        // "", 0);
        //
        // if (deptAssessJob != null && deptAssessJob.getJobs() != null &&
        // deptAssessJob.getJobs().size() > 0)
        // {
        // for (Job job: deptAssessJob.getJobs())
        // {
        // // 작업절차 테이블에서 job_id를 가지고 작업절차번호리스트를 가지고온다.
        // // 가지고 온 리스트를 통해 for문 돌려 부서별_평가대상직무단계 테이블에 집어넣는다.
        // List<JobStep> jobSteps = jobMapper.getJobSteps(job.getJobId(), "Y");
        //
        // for (JobStep jobStep : jobSteps)
        // {
        // DeptAssessStep deptAssessStep = new DeptAssessStep();
        // deptAssessStep.setCreateUserId(job.getCreateUserId());
        // deptAssessStep.setAssessPlanNo(deptAssessJob.getAssessPlanNo());
        //// deptAssessStep.setJobId(job.getJobId());
        // deptAssessStep.setDeptCd(job.getDeptCd());
        // deptAssessStep.setJobStepId(jobStep.getJobStepId());
        //
        // resultNo += deptAssessJobMapper.createDeptAssessStep(deptAssessStep);
        // }
        // }
        // }
        // }

        /*
         * 2019-06-19 kdh 위험성평가 함에 있어 kras와 jsa로 평가직무를 테이블을 구분지을필요가 없기에 하나로 통일
         */
        deptAssessJobMapper.deleteDeptAssessJob(deptAssessJob.getAssessPlanNo(), "", 0);

        if (deptAssessJob != null && deptAssessJob.getJobs() != null && deptAssessJob.getJobs().size() > 0) {
            for (Job job : deptAssessJob.getJobs()) {
                deptAssessJob.setCreateUserId(job.getCreateUserId());
                deptAssessJob.setJobId(job.getJobId());
                // deptAssessJob.setDeptCd(job.getDeptCd());
                deptAssessJob.setProcessCd(job.getProcessCd());
                resultNo += deptAssessJobMapper.createDeptAssessJob(deptAssessJob);
            }
        }

        return resultNo;
    }

}
