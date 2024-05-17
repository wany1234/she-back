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
package com.she.mgt.sapaDashboard.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.SapaDashboard;

@Mapper
@Repository("com.she.mgt.sapaDashboard.mapper.SapaDashboardMapper")
public interface SapaDashboardMapper {
    /**
     * 대시보드 목표관리 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 목표관리 정보
     * @throws Exception
     */
    public List<HashMap<String, Object>> getMgtTargetItems(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 교육이수 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 교육이수 정보
     * @throws Exception
     */
    public SapaDashboard getResultEdu(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 안전보건법령 이행 개선정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 안전보건법령 이행 개선정보
     * @throws Exception
     */
    public SapaDashboard getSafetyhealthImprCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 정부지자체 시정조치 이행 개선정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 정부지자체 시정조치 이행 개선정보
     * @throws Exception
     */
    public SapaDashboard getSafGovImprCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 중대시민재해점검 개선정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 중대시민재해점검 개선정보
     * @throws Exception
     */
    public SapaDashboard getSafDisaInspCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 작업위험성평가 개선정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 작업위험성평가 개선정보
     * @throws Exception
     */
    public SapaDashboard getRsaWorkImprCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
    /**
     * 대시보드 비상대응훈련 개선정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 비상대응훈련 개선정보
     * @throws Exception
     */
    public SapaDashboard getSafTrainingImprCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 사고관리 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 사고관리 정보
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSafAccidentTypeCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 사고관리 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 사고관리 정보
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSafAccidentYearCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 종사자 의견 관리 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 종사자 의견 관리 정보
     * @throws Exception
     */
    public List<HashMap<String, Object>> getMgtListenImprCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 비용예산 집행율 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 비용예산 집행율 정보
     * @throws Exception
     */
    public List<SapaDashboard> getBudgetCost(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 투자예산 집행율 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 투자예산 집행율 정보
     * @throws Exception
     */
    public List<SapaDashboard> getBudgetInvestMent(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 법정선임자평가 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 법정선임자평가 정보
     * @throws Exception
     */
    public SapaDashboard getElectEval(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 법정선임자 선해임 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 법정선임자 선해임 정보
     * @throws Exception
     */
    public SapaDashboard getElectHis(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 평가및안전보건 비용 정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 평가및안전보건 비용 정보
     * @throws Exception
     */
    public SapaDashboard getResultmgmt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대시보드 작업위험성평가 개선정보 조회
     *
     * @param plantCd
     *            사업장코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            부서코드
     * @param deptSubYn
     *            하위부서 포함 여부
     * @return 대시보드 작업위험성평가 개선정보
     * @throws Exception
     */
    public SapaDashboard getRsaProcImprCnt(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptCd") String deptCd, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
