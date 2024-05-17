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
package com.she.mgt.mgtLaw.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.MgtLawMakingCheck;
import com.she.mgt.model.MgtLawMakingCheckDept;
import com.she.mgt.model.MgtLawMakingChecker;

@Mapper
@Repository("com.she.mgt.mgtLaw.mapper.SheLawMakingCheckMapper")
public interface SheLawMakingCheckMapper {

    public List<MgtLawMakingCheck> getLawMakingChecks(@Param("isNm") String isNm, @Param("stYd") String stYd, @Param("edYd") String edYd, @Param("createUserId") String createUserId, @Param("checkStepCd") String checkStepCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("bizApprStepCd") String bizApprStepCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 입법예고법규 검토 상세조회
     * 
     * @param lmcSeqNo
     *            PK
     * @return 입법예고법규 검토
     * @throws Exception
     */
    public MgtLawMakingCheck getLawMakingCheck(@Param("lmcSeqNo") int lmcSeqNo, @Param("seqNo") int seqNo) throws Exception;

    /**
     * 입법예고법규 검토 신규등록
     * 
     * @param lawMakingCheck
     *            입법예고법규 검토
     * @return 등록 행 수
     * @throws Exception
     */
    public int createLawMakingCheck(MgtLawMakingCheck lawMakingCheck) throws Exception;

    /**
     * 입법예고법규 검토 수정
     * 
     * @param lawMakingCheck
     *            입법예고법규 검토
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateLawMakingCheck(MgtLawMakingCheck lawMakingCheck) throws Exception;

    /**
     * 입법예고법규 검토 부서 조회
     * 
     * @param lmcSeqNo
     *            입법예고법규 검토번호
     * @return 입법예고법규 검토 부서
     * @throws Exception
     */
    public List<MgtLawMakingCheckDept> getLawMakingCheckDepts(@Param("lmcSeqNo") int lmcSeqNo) throws Exception;

    /**
     * 입법예고법규 검토 부서 신규등록
     * 
     * @param mgtLawMakingCheckDept
     *            입법예고법규 검토 부서
     * @return 등록 행 수
     * @throws Exception
     */
    public int createLawMakingCheckDept(MgtLawMakingCheckDept mgtLawMakingCheckDept) throws Exception;

    /**
     * 입법예고법규 검토 부서 전체 삭제
     * 
     * @param lmcSeqNo
     *            입법예고법규 검토번호
     * @return 등록 행 수
     * @throws Exception
     */
    public int deleteLawMakingCheckDept(@Param("lmcSeqNo") int lmcSeqNo) throws Exception;

    public int finishLawMakingCheckDept(MgtLawMakingCheckDept mgtLawMakingCheckDept) throws Exception;

    public Map<String, Object> getAllFinishDept(@Param("lmcSeqNo") int lmcSeqNo) throws Exception;

    public int updateLawMakingCheckStepOpinion(@Param("lmcSeqNo") int lmcSeqNo) throws Exception;

    /***
     * 입법예고법규 검토자 목록 조회
     * 
     * @param lmcSeqNo
     *            입법예고법규 검토번호
     * @return 입법예고법규 검토자 목록
     * @throws Exception
     */
    public List<MgtLawMakingChecker> getLawMakingCheckers(@Param("lmcSeqNo") int lmcSeqNo, @Param("checkDeptCd") String checkDeptCd) throws Exception;

    public int createLawMakingChecker(MgtLawMakingChecker mgtLawMakingChecker) throws Exception;

    public int deleteLawMakingChecker(@Param("lmcSeqNo") int lmcSeqNo, @Param("checkDeptCd") String checkDeptCd) throws Exception;

    public int updateLawMakingCheckOpinion(MgtLawMakingChecker mgtLawMakingChecker) throws Exception;

    public int apprProcessLawMaking(@Param("lmcSeqNo") int lmcSeqNo, @Param("apprRqstNo") int apprRqstNo, @Param("checkStepCd") String checkStepCd) throws Exception;

}
