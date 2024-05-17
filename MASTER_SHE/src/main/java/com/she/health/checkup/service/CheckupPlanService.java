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

package com.she.health.checkup.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.health.baseInfo.mapper.CheckupOrgMapper;
import com.she.health.checkup.mapper.CheckupPlanMapper;
import com.she.health.checkup.mapper.CheckupResultMapper;
import com.she.health.model.CheckupPlan;
import com.she.health.model.CheckupPlanOrg;
import com.she.health.model.CheckupUser;

/**
 * 건강검진계획 기능정의
 *
 */
@Service
public class CheckupPlanService {

    @Autowired
    private CheckupResultMapper checkupResultMapper;

    @Autowired
    private CheckupPlanMapper checkupPlanMapper;

    @Autowired
    private CheckupOrgMapper checkupOrgMapper;

    /**
     * 건강검진계획 조회
     * 
     * @param plantCd
     *            사업장 코드
     * @param startDate
     *            검진기간시작일
     * @param endDate
     *            검진기간종료일
     * @param heaCheckupClassCd
     *            검진종류
     * @return 건강검진계획 목록
     * @throws Exception
     */
    public List<CheckupPlan> getCheckupPlans(String startDate, String endDate, String plantCd, String heaCheckupClassCd, String heaCheckupPlanNm) throws Exception {
        return this.checkupPlanMapper.getCheckupPlans(startDate, endDate, plantCd, heaCheckupClassCd, heaCheckupPlanNm);
    }

    /**
     * 건강검진계획 상세 조회
     * 
     * @param heaCheckupPlanNo
     * @return 건강검진계획
     * @throws Exception
     */
    public CheckupPlan getCheckupPlan(int heaCheckupPlanNo) throws Exception {
        CheckupPlan checkupPlan = this.checkupPlanMapper.getCheckupPlan(heaCheckupPlanNo);
        checkupPlan.setCheckupPlanOrgs(this.checkupPlanMapper.getCheckupPlanOrgs(heaCheckupPlanNo, 0, null));
        return checkupPlan;
    }

    /**
     * 건강검진계획 생성
     * 
     * @param checkupPlan
     *            건강검진계획
     * @return 건강검진계획번호
     * @throws Exception
     */
    @Transactional
    public int createCheckupPlan(CheckupPlan checkupPlan) throws Exception {
        int resultNo = 0;
        resultNo = this.checkupPlanMapper.createCheckupPlan(checkupPlan) > 0 ? checkupPlan.getHeaCheckupPlanNo() : 0;

        if (resultNo > 0) {
            for (CheckupPlanOrg checkupPlanOrg : checkupPlan.getCheckupPlanOrgs()) {
                checkupPlanOrg.setHeaCheckupPlanNo(checkupPlan.getHeaCheckupPlanNo());
                checkupPlanOrg.setCreateUserId(checkupPlan.getCreateUserId());
                this.checkupPlanMapper.createCheckupPlanOrg(checkupPlanOrg);
            }
        }

        return resultNo;
    }

    @Transactional
    public int updateCheckupPlan(CheckupPlan checkupPlan) throws Exception {
        int resultNo = 0;
        resultNo += this.checkupPlanMapper.updateCheckupPlan(checkupPlan);

        if (resultNo > 0) {
            this.checkupPlanMapper.deleteCheckupPlanOrg(checkupPlan.getHeaCheckupPlanNo(), 0);
            for (CheckupPlanOrg checkupPlanOrg : checkupPlan.getCheckupPlanOrgs()) {
                checkupPlanOrg.setCreateUserId(checkupPlan.getCreateUserId());
                this.checkupPlanMapper.createCheckupPlanOrg(checkupPlanOrg);
            }
        }

        return resultNo;
    }

    /**
     * 건강검진계획 삭제
     * 
     * @param checkupPlan
     *            건강검진계획 삭제 리스트
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteCheckupPlan(CheckupPlan checkupPlan) throws Exception {
        int resultNo = 0;

        resultNo += this.checkupPlanMapper.deleteCheckupPlanOrg(checkupPlan.getHeaCheckupPlanNo(), 0);
        resultNo += this.checkupPlanMapper.deleteCheckupPlan(checkupPlan);

        return resultNo;
    }

    @Transactional
    public void deleteCheckupPlanOrg(List<CheckupPlanOrg> heaCheckupPlanOrgs) throws Exception {
        int resultNo = 0;
        for (CheckupPlanOrg checkupPlanOrg : heaCheckupPlanOrgs) {
            resultNo += this.checkupPlanMapper.deleteCheckupPlanOrg(checkupPlanOrg.getHeaCheckupPlanNo(), 0);
        }
    }

    @Transactional
    public void createCheckupPlanOrg(List<CheckupPlanOrg> heaCheckupPlanOrgs) throws Exception {
        for (CheckupPlanOrg checkupPlanOrg : heaCheckupPlanOrgs) {
            this.checkupPlanMapper.createCheckupPlanOrg(checkupPlanOrg);
        }
    }

    /**
     * 건강검진계획별 대상자 지정
     * 
     * @param checkupUsers
     *            건강검진대상자 목록
     * @return 생성행수
     * @throws Exception
     */
    @Transactional
    public int createCheckupUsers(List<CheckupUser> checkupUsers) throws Exception {
        int count = 0;
        for (CheckupUser checkupUser : checkupUsers) {
            count += this.checkupPlanMapper.createCheckupUser(checkupUser);
        }

        return count;
    }

    /**
     * 건강검진계획별 대상자 해제
     * 
     * @param checkupUsers
     *            건강검진대상자 목록
     * @return 삭제행수
     * @throws Exception
     */
    @Transactional
    public int deleteCheckupUsers(List<CheckupUser> checkupUsers) throws Exception {
        int count = 0;
        for (CheckupUser checkupUser : checkupUsers) {
            count += this.checkupPlanMapper.deleteCheckupUser(checkupUser);
        }

        return count;
    }

    /**
     * 검색조건 해당 대상자 조회
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param processNo
     *            공정번호
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자아이디
     * @param userNm
     *            사용자명
     * @param heaCheckedOrgNos
     *            예약검진기관
     * @param statusYn
     *            수검여부
     * @param age
     *            나이
     * @param gender
     *            성별
     * @return 대상자목록
     * @throws Exception
     */
    public List<CheckupUser> getCheckupUsersNoPage(int heaCheckupPlanNo, int processNo, String deptCd, String userId, String userNm, int[] heaCheckedOrgNos, int[] heaCheckupOrgNos, String statusYn, String age, String gender) throws Exception {
        return this.checkupPlanMapper.getCheckupUsersNoPage(heaCheckupPlanNo, processNo, deptCd, userId, userNm, heaCheckedOrgNos, heaCheckupOrgNos, statusYn, age, gender);
    }

    /**
     * 검색조건 해당 대상자 조회
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param processNo
     *            공정번호
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자아이디
     * @param userNm
     *            사용자명
     * @param heaCheckedOrgNos
     *            예약검진기관
     * @param statusYn
     *            수검여부
     * @param age
     *            나이
     * @param gender
     *            성별
     * @return 대상자목록
     * @throws Exception
     */
    public List<CheckupUser> getCheckupUsers(int heaCheckupPlanNo, int processNo, String deptCd, String userId, String userNm, int[] heaCheckedOrgNos, int[] heaCheckupOrgNos, String statusYn, String age, String gender, String checkUpResult, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        orderByExpression = orderByExpression.toLowerCase();
        return this.checkupPlanMapper.getCheckupUsers(heaCheckupPlanNo, processNo, deptCd, userId, userNm, heaCheckedOrgNos, heaCheckupOrgNos, statusYn, age, gender, checkUpResult, pageNumber, pageSize, orderByExpression);
    }

    public List<CheckupUser> getCheckupUsersNoPaging(int heaCheckupPlanNo, int processNo, String deptCd, String userId, String userNm, int[] heaCheckedOrgNos, int[] heaCheckupOrgNos, String statusYn, String age, String gender, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        return this.checkupPlanMapper.getCheckupUsersNoPaging(heaCheckupPlanNo, processNo, deptCd, userId, userNm, heaCheckedOrgNos, heaCheckupOrgNos, statusYn, age, gender, pageNumber, pageSize, orderByExpression);
    }

    /**
     * 검색조건 해당 대상자 조회
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param processNo
     *            공정번호
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자아이디
     * @param userNm
     *            사용자명
     * @param heaCheckedOrgNos
     *            예약검진기관
     * @param statusYn
     *            수검여부
     * @param age
     *            나이
     * @param gender
     *            성별
     * @return 대상자목록
     * @throws Exception
     */
    public int getCheckupUsersTotalSize(int heaCheckupPlanNo, int processNo, String deptCd, String userId, String userNm, int[] heaCheckedOrgNos, int[] heaCheckupOrgNos, String statusYn, String age, String gender) throws Exception {
        return this.checkupPlanMapper.getCheckupUsersTotalSize(heaCheckupPlanNo, processNo, deptCd, userId, userNm, heaCheckedOrgNos, heaCheckupOrgNos, statusYn, age, gender);
    }

    /**
     * 검색조건 해당 가능한 대상자 조회
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param processCd
     *            공정코드
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자아이디
     * @param userNm
     *            사용자명
     * @param prevYearCheckupUserYn
     *            작년수검자제외여부
     * @param age
     *            나이대
     * @param gender
     *            성별
     * @param specialTargetChemYn
     *            특검대상(취급자재)
     * @param specialTargetPhyYn
     *            특검대상(물리적인자)
     * @param plantCd
     *            사업장
     * @return 지정가능 대상자목록
     * @throws Exception
     */
    public List<CheckupUser> getCheckupUsersNoTarget(int heaCheckupPlanNo, String processCd, String deptCd, String userId, String userNm, String prevYearCheckupUserYn, int age, String gender, String specialTargetChemYn, String specialTargetPhyYn, String plantCd, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        orderByExpression = orderByExpression.toLowerCase();
        return this.checkupPlanMapper.getCheckupUsersNoTarget(heaCheckupPlanNo, processCd, deptCd, userId, userNm, prevYearCheckupUserYn, age, gender, specialTargetChemYn, specialTargetPhyYn, plantCd, pageNumber, pageSize, orderByExpression);
    }

    public List<CheckupUser> getCheckupUsersNoTargetNoPage(int heaCheckupPlanNo, String processCd, String deptCd, String userId, String userNm, String prevYearCheckupUserYn, int age, String gender, String specialTargetChemYn, String specialTargetPhyYn, String plantCd, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        return this.checkupPlanMapper.getCheckupUsersNoTargetNoPage(heaCheckupPlanNo, processCd, deptCd, userId, userNm, prevYearCheckupUserYn, age, gender, specialTargetChemYn, specialTargetPhyYn, plantCd, pageNumber, pageSize, orderByExpression);
    }

    /**
     * 건강검진계획별 전체 대상자 지정
     * 
     * @param heaCheckupPlanNo
     * @param processCd
     * @param deptCd
     * @param userId
     * @param userNm
     * @param prevYearCheckupUserYn
     * @param age
     * @param gender
     * @param specialTargetChemYn
     * @param specialTargetPhyYn
     * @param plantCd
     * @param pageNumber
     * @param pageSize
     * @param orderByExpression
     * @return
     * @throws Exception
     */
    @Transactional
    public int addCheckupAllUsers(int heaCheckupPlanNo, String processCd, String deptCd, String userId, String userNm, String prevYearCheckupUserYn, int age, String gender, String specialTargetChemYn, String specialTargetPhyYn, String plantCd, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        int result = 0;

        // 대상자 전체 조회
        List<CheckupUser> allUsers = this.checkupPlanMapper.getCheckupUsersNoTarget(heaCheckupPlanNo, processCd, deptCd, userId, userNm, prevYearCheckupUserYn, age, gender, specialTargetChemYn, specialTargetPhyYn, plantCd, pageNumber, pageSize, orderByExpression);

        // 대상자 지정
        if (CollectionUtils.isNotEmpty(allUsers)) {
            for (CheckupUser checkupUser : allUsers) {
                result += this.checkupPlanMapper.createCheckupUser(checkupUser);
            }
        }

        return result;
    }

    /**
     * 건강검진계획별 기관 조회
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param standardYmd
     *            기준날짜
     * @return 건강검진계획별 기관 목록
     * @throws Exception
     */
    public List<CheckupPlanOrg> getCheckupPlanOrgs(int heaCheckupPlanNo, int heaCheckupOrgNo, String standardYmd) throws Exception {
        return this.checkupPlanMapper.getCheckupPlanOrgs(heaCheckupPlanNo, heaCheckupOrgNo, standardYmd);
    }
}
