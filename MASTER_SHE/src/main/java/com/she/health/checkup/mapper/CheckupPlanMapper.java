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

package com.she.health.checkup.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.health.model.CheckupPlan;
import com.she.health.model.CheckupPlanOrg;
import com.she.health.model.CheckupUser;

/**
 * 건강검진 계획 맵퍼
 *
 */
@Mapper
@Repository("com.she.health.checkup.mapper.CheckupPlanMapper")
public interface CheckupPlanMapper {

    /**
     * 건강검진계획 조회
     * 
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param plantCd
     *            사업장코드
     * @param heaCheckupClassCd
     *            검진 종류 코드
     * @return 건강검진계획 목록
     * @throws Exception
     */
    public List<CheckupPlan> getCheckupPlans(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("plantCd") String plantCd, @Param("heaCheckupClassCd") String heaCheckupClassCd, @Param("heaCheckupPlanNm") String heaCheckupPlanNm) throws Exception;

    /**
     * 건강검진계획 상세 조회
     * 
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @return 건강검진계획
     * @throws Exception
     */
    public CheckupPlan getCheckupPlan(@Param("heaCheckupPlanNo") int heaCheckupPlanNo) throws Exception;

    /**
     * 건강검진계획 생성
     * 
     * @param checkupPlan
     *            건강검진계획
     * @return 생성 행 수
     * @throws Exception
     */
    public int createCheckupPlan(CheckupPlan checkupPlan) throws Exception;

    /**
     * 건강검진계획별 기관 생성
     * 
     * @param checkupPlanOrg
     *            건강검진계획별 기관
     * @return 생성 행 수
     * @throws Exception
     */
    public int createCheckupPlanOrg(CheckupPlanOrg checkupPlanOrg) throws Exception;

    /**
     * 건강검진계획 수정
     * 
     * @param checkupPlan
     *            건강검진계획
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateCheckupPlan(CheckupPlan checkupPlan) throws Exception;

    /**
     * 건강검진계획 삭제
     * 
     * @param checkupPlan
     *            건강검진계획 삭제 리스트
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCheckupPlan(CheckupPlan checkupPlan) throws Exception;

    // public void deleteCheckupPlanOrg(int checkupPlan) throws Exception;

    /**
     * 건강검진계획별 기관 삭제
     * 
     * @param heaCheckupPlanNo
     * @param heaCheckupOrgNo
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCheckupPlanOrg(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("heaCheckupOrgNo") int heaCheckupOrgNo) throws Exception;

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
     * 
     * @return 대상자목록
     * @throws Exception
     */
    public List<CheckupUser> getCheckupUsersNoPage(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("processNo") int processNo, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("heaCheckedOrgNos") int[] heaCheckedOrgNos, @Param("heaCheckupOrgNos") int[] heaCheckupOrgNos,
            @Param("statusYn") String statusYn, @Param("age") String age, @Param("gender") String gender) throws Exception;

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
     * 
     * @return 대상자목록
     * @throws Exception
     */
    public List<CheckupUser> getCheckupUsers(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("processNo") int processNo, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("heaCheckedOrgNos") int[] heaCheckedOrgNos, @Param("heaCheckupOrgNos") int[] heaCheckupOrgNos,
            @Param("statusYn") String statusYn, @Param("age") String age, @Param("gender") String gender, @Param("checkUpResult") String checkUpResult, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;

    public List<CheckupUser> getCheckupUsersNoPaging(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("processNo") int processNo, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("heaCheckedOrgNos") int[] heaCheckedOrgNos, @Param("heaCheckupOrgNos") int[] heaCheckupOrgNos,
            @Param("statusYn") String statusYn, @Param("age") String age, @Param("gender") String gender, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;

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
     * 
     * @return 대상자목록
     * @throws Exception
     */
    public int getCheckupUsersTotalSize(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("processNo") int processNo, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("heaCheckedOrgNos") int[] heaCheckedOrgNos, @Param("heaCheckupOrgNos") int[] heaCheckupOrgNos,
            @Param("statusYn") String statusYn, @Param("age") String age, @Param("gender") String gender) throws Exception;

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
    public List<CheckupUser> getCheckupUsersNoTarget(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("processCd") String processCd, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("prevYearCheckupUserYn") String prevYearCheckupUserYn, @Param("age") int age,
            @Param("gender") String gender, @Param("specialTargetChemYn") String specialTargetChemYn, @Param("specialTargetPhyYn") String specialTargetPhyYn, @Param("plantCd") String plantCd, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;

    /**
     * 검색조건 해당 가능한 대상자 조회(페이징 X)
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
    public List<CheckupUser> getCheckupUsersNoTargetNoPage(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("processCd") String processCd, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("userNm") String userNm, @Param("prevYearCheckupUserYn") String prevYearCheckupUserYn, @Param("age") int age,
            @Param("gender") String gender, @Param("specialTargetChemYn") String specialTargetChemYn, @Param("specialTargetPhyYn") String specialTargetPhyYn, @Param("plantCd") String plantCd, @Param("pageNumber") int pageNumber, @Param("pageSize") int pageSize, @Param("orderByExpression") String orderByExpression) throws Exception;

    /**
     * 건강검진계획별 대상자 지정
     * 
     * @param checkupUser
     *            건강검진대상자
     * @return 생성 행 수
     * @throws Exception
     */
    public int createCheckupUser(CheckupUser checkupUser) throws Exception;

    /**
     * 건강검진계획별 대상자 해제
     * 
     * @param checkupUser
     *            건강검진대상자
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteCheckupUser(CheckupUser checkupUser) throws Exception;

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
    public List<CheckupPlanOrg> getCheckupPlanOrgs(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("heaCheckupOrgNo") int heaCheckupOrgNo, @Param("standardYmd") String standardYmd) throws Exception;
}
