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
package com.she.rsa.jobRiskBook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.JobRiskBook;

@Mapper
@Repository("com.she.rsa.jobRiskBook.mapper.JobRiskBookMapper")
public interface JobRiskBookMapper {

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
    public List<JobRiskBook> getJobRiskBookJSAs(@Param("plantCd") String plantCd, @Param("userId") String userId, @Param("assessTypeNo") int assessTypeNo, @Param("assessNm") String assessNm, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("processCd") String processCd, @Param("assessYear") String assessYear,
            @Param("jobNm") String jobNm, @Param("jobStepNm") String jobStepNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 위험등록부 삭제
     * 
     * @param riskBookNo
     *            위험등록부 no
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteJobRiskBook(@Param("riskBookNo") int riskBookNo) throws Exception;

}
