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
package com.she.rsa.jobRiskBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.impr.mapper.ImprMapper;
import com.she.rsa.jobRiskBook.mapper.JobRiskBookMapper;
import com.she.rsa.model.JobRiskBook;

/**
 * 위험등록부 기능정의
 */
@Service
public class JobRiskBookService {

    @Autowired
    private JobRiskBookMapper jobRiskBookMapper;

    @Autowired
    private ImprMapper imprMapper;

    /**
     * 위험등록부 JSA 조회
     * 
     * @param assessTypeNo
     *            평가기법 번호
     * @param assessNm
     *            평가명
     * @param deptCd
     *            부서
     * @param processNo
     *            공정
     * @param assessYear
     *            평가년도
     * @param jobNm
     *            직무명
     * @param jobStepNm
     *            직무절차명
     * @return 위험등록부 JSA 목록
     * @throws Exception
     */
    public List<JobRiskBook> getJobRiskBookJSAs(String plantCd, String userId, int assessTypeNo, String assessNm, String deptCd, String deptSubYn, String processCd, String assessYear, String jobNm, String jobStepNm, DefaultParam defaultParam) throws Exception {
        return jobRiskBookMapper.getJobRiskBookJSAs(plantCd, userId, assessTypeNo, assessNm, deptCd, deptSubYn, processCd, assessYear, jobNm, jobStepNm, defaultParam);
    }

    /**
     * 위험등록부 삭제
     * 
     * @param JobRiskBooks
     *            위험등록부 리스트
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteJobRiskBook(List<JobRiskBook> jobRiskBooks) throws Exception {
        int resultNo = 0;
        for (JobRiskBook jobRiskBook : jobRiskBooks) {
            resultNo += jobRiskBookMapper.deleteJobRiskBook(jobRiskBook.getRiskBookNo());
            imprMapper.deleteImprAct(String.valueOf(jobRiskBook.getSafImprActNo()));
        }
        return resultNo;
    }

}
