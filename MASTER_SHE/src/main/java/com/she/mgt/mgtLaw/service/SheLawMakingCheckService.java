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
package com.she.mgt.mgtLaw.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.mgt.mgtLaw.mapper.SheLawMakingCheckMapper;
import com.she.mgt.model.MgtLawMakingCheck;
import com.she.mgt.model.MgtLawMakingCheckDept;
import com.she.mgt.model.MgtLawMakingChecker;
import com.she.utils.ConstVal;

@Service
public class SheLawMakingCheckService {
    @Autowired
    private SheLawMakingCheckMapper sheLawMakingCheckMapper;

    public List<MgtLawMakingCheck> getLawMakingChecks(String isNm, String stYd, String edYd, String createUserId, String checkStepCd, String deptCd, String deptSubYn, String bizApprStepCd, DefaultParam defaultParam) throws Exception {
        return sheLawMakingCheckMapper.getLawMakingChecks(isNm, stYd, edYd, createUserId, checkStepCd, deptCd, deptSubYn, bizApprStepCd, defaultParam);
    }

    public MgtLawMakingCheck getLawMakingCheck(int lmcSeqNo, int seqNo, String createUserId) throws Exception {
        MgtLawMakingCheck mgtLawMakingCheck = sheLawMakingCheckMapper.getLawMakingCheck(lmcSeqNo, seqNo);

        // 검토부서 조회
        if (lmcSeqNo > 0) {
            mgtLawMakingCheck.setLawMakingCheckDepts(sheLawMakingCheckMapper.getLawMakingCheckDepts(lmcSeqNo));

            if (CollectionUtils.isNotEmpty(mgtLawMakingCheck.getLawMakingCheckDepts())) {
                for (MgtLawMakingCheckDept mgtLawMakingCheckDept : mgtLawMakingCheck.getLawMakingCheckDepts()) {
                    mgtLawMakingCheckDept.setLawMakingCheckers(sheLawMakingCheckMapper.getLawMakingCheckers(lmcSeqNo, mgtLawMakingCheckDept.getCheckDeptCd()));
                }
            }
        }
        return mgtLawMakingCheck;
    }

    /**
     * 입법예고법규 검토 신규등록
     * 
     * @param lawMakingCheck
     *            입법예고법규 검토
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createLawMakingCheck(MgtLawMakingCheck lawMakingCheck) throws Exception {
        sheLawMakingCheckMapper.createLawMakingCheck(lawMakingCheck);
        if (CollectionUtils.isNotEmpty(lawMakingCheck.getLawMakingCheckDepts())) {
            for (MgtLawMakingCheckDept mgtLawMakingCheckDept : lawMakingCheck.getLawMakingCheckDepts()) {
                mgtLawMakingCheckDept.setLmcSeqNo(lawMakingCheck.getLmcSeqNo());
                sheLawMakingCheckMapper.createLawMakingCheckDept(mgtLawMakingCheckDept);
            }
        }

        return lawMakingCheck.getLmcSeqNo();
    }

    /**
     * 입법예고법규 검토 수정
     * 
     * @param lawMakingCheck
     *            입법예고법규 검토
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateLawMakingCheck(MgtLawMakingCheck lawMakingCheck) throws Exception {
        int resultNo = sheLawMakingCheckMapper.updateLawMakingCheck(lawMakingCheck);

        sheLawMakingCheckMapper.deleteLawMakingCheckDept(lawMakingCheck.getLmcSeqNo());
        if (CollectionUtils.isNotEmpty(lawMakingCheck.getLawMakingCheckDepts())) {
            for (MgtLawMakingCheckDept mgtLawMakingCheckDept : lawMakingCheck.getLawMakingCheckDepts()) {
                mgtLawMakingCheckDept.setLmcSeqNo(lawMakingCheck.getLmcSeqNo());
                resultNo += sheLawMakingCheckMapper.createLawMakingCheckDept(mgtLawMakingCheckDept);
            }
        }
        return resultNo;
    }

    /**
     * 입법예고법규 검토부서 사용자 저장
     * 
     * @param mgtLawMakingCheckDept
     *            입법예고법규 검토부서
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int createLawMakingCheckUser(MgtLawMakingCheckDept mgtLawMakingCheckDept) throws Exception {
        int resultNo = 0;

        sheLawMakingCheckMapper.deleteLawMakingChecker(mgtLawMakingCheckDept.getLmcSeqNo(), mgtLawMakingCheckDept.getCheckDeptCd());
        if (CollectionUtils.isNotEmpty(mgtLawMakingCheckDept.getLawMakingCheckers())) {
            for (MgtLawMakingChecker mgtLawMakingChecker : mgtLawMakingCheckDept.getLawMakingCheckers()) {
                mgtLawMakingChecker.setLmcSeqNo(mgtLawMakingCheckDept.getLmcSeqNo());
                resultNo += sheLawMakingCheckMapper.createLawMakingChecker(mgtLawMakingChecker);
            }
        }

        return resultNo;
    }

    /**
     * 입법예고법규 검토부서 사용자 지정 완료
     * 
     * @param mgtLawMakingCheckDept
     *            입법예고법규 검토부서
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateLawMakingCheckConfirmUser(MgtLawMakingCheckDept mgtLawMakingCheckDept) throws Exception {
        // 검토부서 완료 여부 업데이트
        int resultNo = sheLawMakingCheckMapper.finishLawMakingCheckDept(mgtLawMakingCheckDept);

        // 모든 검토부서 완료 여부 조회
        Map<String, Object> map = sheLawMakingCheckMapper.getAllFinishDept(mgtLawMakingCheckDept.getLmcSeqNo());
        if ("1".equals(String.valueOf(map.get("check_val")))) {
            // 반환 값이 1인 경우 검토부서 전체가 검토자 지정이 끝났다는 의미
            // 검토의 진행상태를 검토자지정 >> 의견작성중 상태로 변경
            sheLawMakingCheckMapper.updateLawMakingCheckStepOpinion(mgtLawMakingCheckDept.getLmcSeqNo());
        }

        sheLawMakingCheckMapper.deleteLawMakingChecker(mgtLawMakingCheckDept.getLmcSeqNo(), mgtLawMakingCheckDept.getCheckDeptCd());
        if (CollectionUtils.isNotEmpty(mgtLawMakingCheckDept.getLawMakingCheckers())) {
            for (MgtLawMakingChecker mgtLawMakingChecker : mgtLawMakingCheckDept.getLawMakingCheckers()) {
                mgtLawMakingChecker.setLmcSeqNo(mgtLawMakingCheckDept.getLmcSeqNo());
                sheLawMakingCheckMapper.createLawMakingChecker(mgtLawMakingChecker);
            }
        }

        return resultNo;
    }

    public int updateLawMakingCheckOpinion(MgtLawMakingChecker mgtLawMakingChecker) throws Exception {
        return sheLawMakingCheckMapper.updateLawMakingCheckOpinion(mgtLawMakingChecker);
    }

    @Transactional
    public int updateLawMakingCheckConfirmOpinion(MgtLawMakingChecker mgtLawMakingChecker) throws Exception {
        mgtLawMakingChecker.setFinishYn("Y");
        return sheLawMakingCheckMapper.updateLawMakingCheckOpinion(mgtLawMakingChecker);
    }

    public int apprProcessLawMaking(int lmcSeqNo, int seqNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String checkStepCd = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            checkStepCd = ConstVal.LAW_MAKING_COMPLETE;
        }

        return sheLawMakingCheckMapper.apprProcessLawMaking(lmcSeqNo, apprRqstNo, checkStepCd);
    }
}
