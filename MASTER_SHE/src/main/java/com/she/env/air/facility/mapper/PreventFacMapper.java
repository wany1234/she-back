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
package com.she.env.air.facility.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.PreventDischFacItem;
import com.she.env.air.model.PreventFac;
import com.she.env.air.model.PreventFacActCarbHist;
import com.she.env.air.model.PreventFacChgHist;
import com.she.env.air.model.PreventFacElecMeter;
import com.she.env.air.model.PreventFacItem;
import com.she.env.air.model.PreventFacMaintHist;

/**
 * 대기 방지시설 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.facility.mapper.PreventFacMapper")
public interface PreventFacMapper {

    /**
     * 방지시설 전체 조회
     *
     * @param useYn
     *            사용여부
     * @param eairPreventFacClassCd
     *            방지시설분류코드
     * @param eairPreventFacNm
     *            방지시설명
     * @param plantCd
     *            사업장코드
     * @param mgDeptCd
     *            관리부서코드
     * @return 방지시설목록
     * @throws Exception
     */
    public List<PreventFac> getPreventionFacilities(@Param("useYn") String useYn, @Param("eairPreventFacClassCd") String eairPreventFacClassCd, @Param("eairPreventFacNm") String eairPreventFacNm, @Param("plantCd") String plantCd, @Param("mgDeptCd") String mgDeptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 방지시설 상세 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    public PreventFac getPreventionFacility(@Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

    /**
     * 선택된 방지시설 전략량계 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    public List<PreventFacElecMeter> getPreventionElecMeter(@Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

    /**
     * 선택된 방지시설 전략량계 등록
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    public int createPreventionElecMeter(PreventFacElecMeter preventFacElecMeter) throws Exception;

    /**
     * 선택된 방지시설 전략량계 삭제
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    public int deletePreventionElecMeter(@Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

    /**
     * 방지시설 신규등록
     *
     * @param preventFac
     *            방지시설
     * @return 등록행수
     * @throws Exception
     */
    public int createPreventionFacility(PreventFac preventFac) throws Exception;

    /**
     * 선택된 방지지설 수정
     *
     * @param preventFac
     *            방지시설
     * @return 수정행수
     * @throws Exception
     */
    public int updatePreventionFacility(PreventFac preventFac) throws Exception;

    // 방지시설 변경이력
    /**
     * 방지시설 변경이력 전체 조회
     *
     * @param eairPreventFacNo
     * @return 방지시설 변경이력 목록
     * @throws Exception
     */
    public List<PreventFacChgHist> getPreventionFacilityChangeHistories(@Param("eairPreventFacNo") int eairPreventFacNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 방지시설 변경이력 상세 조회
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경 이력 번호
     * @return 방지시설 변경이력
     * @throws Exception
     */
    public PreventFacChgHist getPreventionFacilityChangeHistory(@Param("eairPreventFacChgHistNo") int eairPreventFacChgHistNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 방지시설 마지막 변경이력 상세 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설 변경이력
     * @throws Exception
     */
    public PreventFacChgHist getPreventionFacilityLastChangeHistory(@Param("eairPreventFacNo") int eairPreventFacNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 방지시설 변경이력 신규등록
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 등록행수
     * @throws Exception
     */
    public int createPreventionFacilityChangeHistory(PreventFacChgHist preventFacChgHist) throws Exception;

    /**
     * 방지시설 변경이력 처리오염물질 조회
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 등록행수
     * @throws Exception
     */
    public List<PreventFacItem> selectPreventionFacilityChangeHistoryPollu(@Param("eairPreventFacChgHistNo") int eairPreventFacChgHistNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 방지시설 변경이력 처리오염물질
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 등록행수
     * @throws Exception
     */
    public int createPreventionFacilityChangeHistoryPollu(PreventFacItem preventFacItem) throws Exception;

    /**
     * 방지시설 변경이력 처리오염물질 삭제
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 등록행수
     * @throws Exception
     */
    public int deletePreventionFacilityChangeHistoryPollu(@Param("eairPreventFacChgHistNo") int eairPreventFacChgHistNo) throws Exception;

    /**
     * 방지시설 변경이력 사용약품 조회
     *
     * @param eairPreventFacChgHistNo
     * @return 등록행수
     * @throws Exception
     */
    public List<PreventFacItem> selectPreventionFacilityChangeHistoryChem(@Param("eairPreventFacChgHistNo") int eairPreventFacChgHistNo) throws Exception;

    /**
     * 방지시설 변경이력 사용약품
     *
     * @param preventFacChgHist
     * @return 등록행수
     * @throws Exception
     */
    public int createPreventionFacilityChangeHistoryChem(PreventFacItem preventFacItem) throws Exception;

    /**
     * 방지시설 변경이력 사용약품
     *
     * @param preventFacChgHist
     * @return 등록행수
     * @throws Exception
     */
    public int deletePreventionFacilityChangeHistoryChem(@Param("eairPreventFacChgHistNo") int eairPreventFacChgHistNo) throws Exception;

    /**
     * 선택된 방지시설 변경이력 수정
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 수정행수
     * @throws Exception
     */
    public int updatePreventionFacilityChangeHistory(PreventFacChgHist preventFacChgHist) throws Exception;

    public List<PreventFacActCarbHist> getPreventionFacActCarbHists(@Param("eairPreventFacNo") int eairPreventFacNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public PreventFacActCarbHist getPreventionFacActCarbHist(@Param("eairPreventFacActCarbHistNo") int eairPreventFacActCarbHistNo) throws Exception;

    /**
     * 활성탄 교체내역 등록
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */

    public int createPreventionFacActCarbHist(PreventFacActCarbHist preventFacActCarbHist) throws Exception;

    /**
     * 활성탄 교체내역 수정
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */
    public int updatePreventionFacActCarbHist(PreventFacActCarbHist preventFacActCarbHist) throws Exception;

    /**
     * 활성탄 교체내역 삭제
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */
    public int deletePreventionFacActCarbHist(PreventFacActCarbHist preventFacActCarbHist) throws Exception;

    /**
     * 선택된 방지시설 변경이력 삭제
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경 이력 번호
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deletePreventionFacilityChangeHistory(@Param("eairPreventFacChgHistNo") int eairPreventFacChgHistNo, @Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

    // 방지시설 보수이력
    /**
     * 방지시설 보수이력 전체 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설 보수이력 목록
     * @throws Exception
     */
    public List<PreventFacMaintHist> getPreventionFacilityMaintenanceHistories(@Param("eairPreventFacNo") int eairPreventFacNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 방지시설 보수이력 상세 조회
     *
     * @param eairPreventFacMaintHistNo
     *            방지시설 보수이력번호
     * @return 방지시설 보수이력
     * @throws Exception
     */
    public PreventFacMaintHist getPreventionFacilityMaintenanceHistory(@Param("eairPreventFacMaintHistNo") int eairPreventFacMaintHistNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 방지시설 보수이력 신규등록
     *
     * @param preventFacMaintHist
     *            방지시설 보수이력
     * @return 등록행수
     * @throws Exception
     */
    public int createPreventionFacilityMaintenanceHistory(PreventFacMaintHist preventFacMaintHist) throws Exception;

    /**
     * 선택된 방지시설 보수이력 수정
     *
     * @param preventFacMaintHist
     *            방지시설 보수이력
     * @return 수정행수
     * @throws Exception
     */
    public int updatePreventionFacilityMaintenanceHistory(PreventFacMaintHist preventFacMaintHist) throws Exception;

    /**
     * 선택된 방지시설 보수이력 삭제
     *
     * @param eairPreventFacMaintHistNo
     *            방지시설 보수이력번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deletePreventionFacilityMaintenanceHistory(@Param("eairPreventFacMaintHistNo") int eairPreventFacMaintHistNo) throws Exception;

    /**
     * 방지시설명 중복체크
     *
     * @param eairPreventFacNo
     *            대기 오염 방지시설 번호
     * @param eairPreventFacNm
     *            대기 오염 방지시설 명칭
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkPreventionFacility(@Param("eairPreventFacNo") int eairPreventFacNo, @Param("eairPreventFacNm") String eairPreventFacNm, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 고유방지시설번호 중복체크
     *
     * @param eairPreventFacInhNum
     *            고유방지시설번호
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkPreventionInhNum(@Param("eairPreventFacInhNum") String eairPreventFacInhNum, @Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

    /**
     * 방지시설-배출시설 연결 등록
     *
     * @param preventDischFacItem
     * @return
     * @throws Exception
     */
    public int createPreventionDischItem(PreventDischFacItem preventDischFacItem) throws Exception;

    /**
     * 방지시설-배출시설 제거
     *
     * @param eairPreventFacNo
     * @return
     * @throws Exception
     */
    public int deletePreventionDischItem(@Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

    /**
     * 방지시설-배출시설 조회
     *
     * @param selectPreventDisch
     * @return
     * @throws Exception
     */
    public List<PreventDischFacItem> selectPreventDisch(@Param("eairPreventFacNo") int eairPreventFacNo) throws Exception;

}
