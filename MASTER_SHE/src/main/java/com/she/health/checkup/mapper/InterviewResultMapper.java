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

import com.she.health.model.InterviewResult;

/**
 * 문진결과 맵퍼 - 사용하지 않음. 문진항목 마스터 개발완료, 결과 업로드 및 조회의 경우 구현하지 않기로함.
 *
 */
@Mapper
@Repository("com.she.health.checkup.mapper.InterviewResultMapper")
public interface InterviewResultMapper {

    /**
     * 문진결과 조회
     * 
     * @param checkupYear
     *            검진년도
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param retirementYn
     *            재직/퇴직여부
     * @param userNm
     *            사용자명
     * @param heaCheckedOrgNos
     *            검진기관
     * @param smokingCd
     *            흡연여부
     * @param drinkingCd
     *            음주여부
     * @return 문진결과 목록
     * @throws Exception
     */
    public List<InterviewResult> getInterviewResults(@Param("checkupYear") String checkupYear,
            @Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("retirementYn") String retirementYn,
            @Param("userNm") String userNm, @Param("heaCheckedOrgNos") int[] heaCheckedOrgNos,
            @Param("smokingCd") String smokingCd, @Param("drinkingCd") String drinkingCd) throws Exception;

    /**
     * 문진결과 상세 조회
     * 
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param userId
     *            사용자아이디
     * @return 문진결과
     * @throws Exception
     */
    public InterviewResult getInterviewResult(@Param("heaCheckupPlanNo") int heaCheckupPlanNo,
            @Param("userId") String userId) throws Exception;

    /**
     * 문진결과 신규 생성
     * 
     * @param interviewResult
     *            문진결과
     * @return 생성행수
     * @throws Exception
     */
    public int createInterviewResult(InterviewResult interviewResult) throws Exception;

    /**
     * updateInterviewResult
     * 
     * @param interviewResult
     *            문진결과
     * @return 수정행수
     * @throws Exception
     */
    public int updateInterviewResult(InterviewResult interviewResult) throws Exception;

    /**
     * 문진결과 삭제
     * 
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param userId
     *            사용자아이디
     * @param heaInteItemCd
     *            문진항목코드
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteInterviewResult(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId,
            @Param("heaInteItemCd") String heaInteItemCd) throws Exception;
}
