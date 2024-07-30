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

import com.she.common.model.DefaultParam;
import com.she.health.model.CheckupResult;
import com.she.health.model.CheckupResultDiag;
import com.she.health.model.CheckupSituation;
import com.she.health.model.CheckupUser;
import com.she.health.model.TestItemResult;

/**
 * 건강검진 결과 검색 맵퍼
 *
 */
@Mapper
@Repository("com.she.health.checkup.mapper.CheckupResultMapper")
public interface CheckupResultMapper {

    /**
     *
     * 건강검진결과 조회
     *
     * @param userId
     *            건강검진대상자
     * @param checkupYear
     *            검진연도
     * @param heaCheckupPlanNo
     *            검진계획
     * @param retirementYn
     *            재직/퇴직
     * @param heaDiagnoseCds
     *            판정(복수선택)
     * @param heaDiseaseClassCd
     *            질환종류
     * @param heaDiseaseCd
     *            질환
     * @param heaCheckedOrgNos
     *            검진병원(복수선택)
     * @param userNm
     *            사원명
     * @param plantCd
     *            사업장코드
     * @return 건강검진결과목록
     * @throws Exception
     */
    public List<CheckupResult> getCheckupResults(@Param("userId") String userId, @Param("checkupYear") String checkupYear, @Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("retirementYn") String retirementYn, @Param("heaDiagnoseCds") String[] heaDiagnoseCds, @Param("heaDiseaseClassCd") String heaDiseaseClassCd,
            @Param("heaDiseaseCd") String heaDiseaseCd, @Param("heaCheckedOrgNos") int[] heaCheckedOrgNos, @Param("userNm") String userNm, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 건강검진결과 상세 조회
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @return 검진정보
     * @throws Exception
     */
    public CheckupResult getCheckupResult(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId) throws Exception;

    /**
     * 건강검진진단결과 조회
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @return 소견및판정정보
     * @throws Exception
     */
    public List<CheckupResultDiag> getCheckupResultDiags(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId) throws Exception;

    /**
     * 건강검진항목별 결과 조회
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @param optionalYn
     *            선택항목여부
     * @return 검진항목 목록
     * @throws Exception
     */
    public List<TestItemResult> getTestItemResults(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId, @Param("optionalYn") String optionalYn) throws Exception;

    /**
     * 건강검진결과 등록 전 유효성 체크
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            검진대상자ID
     * @return 중복 행 수
     * @throws Exception
     */
    public int validCheckupResult(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId) throws Exception;

    /**
     * 건강검진결과 등록
     *
     * @param checkupResult
     *            건강검진결과
     * @return 등록 행 수
     * @throws Exception
     */
    public int createCheckupResult(CheckupResult checkupResult) throws Exception;

    /**
     * 건강검진결과 수정
     *
     * @param checkupResult
     *            건강검진결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateCheckupResult(CheckupResult checkupResult) throws Exception;

    /**
     * 건강검진진단결과 등록
     *
     * @param checkupResultDiag
     *            건강검진진단결과
     * @return 등록행수
     * @throws Exception
     */
    public int createCheckupResultDiag(CheckupResultDiag checkupResultDiag) throws Exception;

    /**
     * 건강검진진단결과 삭제
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @param heaDiseaseCd
     *            질환코드
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteCheckupResultDiag(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId, @Param("heaDiseaseCd") String heaDiseaseCd) throws Exception;

    /**
     * 건강검진항목별 결과 등록
     *
     * @param testItemResult
     *            건강검진항목별 결과
     * @return 등록행수
     * @throws Exception
     */
    public int createTestItemResult(TestItemResult testItemResult) throws Exception;

    /**
     * 건강검진항목별 결과 삭제
     *
     * @param heaCheckupPlanNo
     *            건강검진계획번호
     * @param userId
     *            사용자아이디
     * @param heaTestItemCd
     *            검사항목코드
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteTestItemResult(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("userId") String userId, @Param("heaTestItemCd") String heaTestItemCd) throws Exception;

    /**
     *
     * 과거판정데이터 > 검진 결과 이력 목록 조회
     *
     * @param userId
     *            건강검진대상자
     * @param heaCheckupPlanNo
     *            검진계획
     * @return 건강검진결과이력
     * @throws Exception
     */
    public List<CheckupResult> getCheckupPastResults(@Param("userId") String userId, @Param("heaCheckupPlanNo") int heaCheckupPlanNo) throws Exception;

    /**
     * 검진대상자 엑셀업로드
     * @param checkupUser
     * @return
     * @throws Exception
     */
    public int excelUploadCheckupUser(CheckupUser checkupUser) throws Exception; 
    public int getExistsUser(@Param("userId") String userId)throws Exception; 
    
    /**
     * 검진현황 목록
     * @param year
     * @param plantCd
     * @param deptCd
     * @param heaCheckupClassCd
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<CheckupSituation> getCheckupSituation(@Param("year") String year, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd,
    		@Param("heaCheckupClassCd") String heaCheckupClassCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
