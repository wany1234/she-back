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
package com.she.env.air.selfMeasurement.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import com.she.env.air.model.OutletItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.SelfMeasurement;
import com.she.env.air.model.SelfMeasurementResult;

/**
 * 대기 운영일지 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.selfMeasurement.mapper.SelfMeasurementMapper")
public interface SelfMeasurementMapper {

    /**
     * 대기 자가측정 조회
     *
     * @param fromYmd
     *            시작일자
     * @param toYmd
     *            종료일자
     * @param deptCd
     *            부서코드
     * @param selfManageStatus
     *            상태
     * @param searchEairOutletNm
     *            배출구명
     * @param plantCd
     *            사업장코드
     * 
     * @return 대기 자가측정 목록
     * @throws Exception
     *             예외
     */

    public List<SelfMeasurement> getSelfMeasurements(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("deptCd") String deptCd, @Param("selfManageStatus") String selfManageStatus, @Param("searchEairOutletNm") String searchEairOutletNm, @Param("plantCd") String plantCd, @Param("mgDeptCd") String mgDeptCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대기 자가측정 단건 조회
     *
     * @param eairOpMeasNo
     *            자가측정 번호
     * 
     * @return 대기 자가측정 목록
     * @throws Exception
     *             예외
     */
    public SelfMeasurement getSelfMeasurement(@Param("eairOpMeasNo") int eairOpMeasNo) throws Exception;

    /**
     * 대기 자가측정 배출구 결과 조회
     *
     * @param eairOpMeasNo
     *            자가측정 번호
     * @param eairOpMeasNo
     *            배출구 번호
     * @return 대기 자가측정 배출구 목록
     * @throws Exception
     *             예외
     */
    public List<SelfMeasurementResult> getSelfMeasurementResult(@Param("eairOpMeasNo") int eairOpMeasNo, @Param("eairOutletNo") int eairOutletNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대기 자가측정 생성
     *
     * @param selfMeasurement
     *            자가측정
     * 
     * @return 대기 자가측정 생성
     * @throws Exception
     *             예외
     */

    public int createSelfMeasurement(SelfMeasurement selfMeasurement) throws Exception;

    /**
     * 대기 자가측정 배출구결과 생성
     *
     * @param selfMeasurementResult
     *            자가측정 배출구결과
     * 
     * @return 대기 자가측정 배출구결과 생성
     * @throws Exception
     *             예외
     */
    public int createSelfMeasurementResult(SelfMeasurementResult selfMeasurementResult) throws Exception;

    /**
     * 대기 자가측정 배출구결과 수정
     *
     * @param selfMeasurementResult
     *            자가측정 배출구결과
     *
     * @return 수정 행 수
     * @throws Exception
     *             예외
     */
    public int updateSelfMeasurementResult(SelfMeasurementResult selfMeasurementResult) throws Exception;

    /**
     * 대기 자가측정 삭제
     *
     * @param eairOpMeasNo
     *            자가측정 번호
     * 
     * @return 대기 자가측정 삭제
     * @throws Exception
     *             예외
     */
    public int deleteSelfMeasurement(@Param("eairOpMeasNo") int eairOpMeasNo) throws Exception;

    /**
     * 대기 자가측정 수정
     *
     * @param selfMeasurement
     *            자가측정
     * 
     * @return 대기 자가측정 수정
     * @throws Exception
     *             예외
     */
    public int updateSelfMeasurement(SelfMeasurement selfMeasurement) throws Exception;

    /**
     * 대기 자가측정 배출구결과 삭제
     *
     * @param eairOpMeasNo
     *            자가측정 번호
     * 
     * @return 대기 자가측정 삭제
     * @throws Exception
     *             예외
     */
    public int deleteSelfMeasurementResult(int eairOpMeasNo) throws Exception;

    public int updateAppr(@Param("eairOpMeasNo") int ewtrMeasNo, @Param("stepCd") String apprStepCd, @Param("apprRqstNo") int apprRqstNo);

    /**
     * 자가측정 중복체크
     *
     * @param eairOpMeasNo
     *            자가측정번호
     * @param eairOutletNo
     *            배출구번호
     * @param plantCd
     *            사업장코드
     * @param measureYmd
     *            측정일자
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkSelfMeasurement(@Param("eairOpMeasNo") int eairOpMeasNo, @Param("eairOutletNo") int eairOutletNo, @Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 배출구 유효성 검사 (대기-자가측정 엑셀업로드용 SK E&S)
     *
     * @param eairOutletNm
     *            배출구일련번호
     * @param eairOutletPermitNo
     *            배출구허가증상배출구번호
     * @param mainDischFacNm
     *            배출구명
     * @return 배출구
     * @throws Exception
     */
    public int getOutletSelfMeasurement(@Param("eairOutletNm") String eairOutletNm, @Param("eairOutletPermitNo") String eairOutletPermitNo, @Param("mainDischFacNm") String mainDischFacNm, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 자가측정결과 엑셀 업로드 - 코드 마스터 유효성 검사
     *
     * @param codeNm
     *            코드명칭
     * @param codeGroupCd
     *            코드그룹코드
     * @return code
     * @throws Exception
     */
    public String checkCodeMaster(@Param("codeNm") String codeNm, @Param("codeGroupCd") String codeGroupCd) throws Exception;

    /**
     * 자가측정결과 엑셀 업로드 - 검사항목(오염물질) 유효성 검사
     *
     * @param plantCd
     *            사업장코드
     * @param eairTestItemNm
     *            검사항목명
     * @return eairTestItemCd(검사항목코드)
     * @throws Exception
     */
    public String checkTestItem(@Param("plantCd") String plantCd, @Param("eairTestItemNm") String eairTestItemNm) throws Exception;

    /**
     * 자가측정결과 엑셀 업로드 - 검사항목(오염물질)이 배출구에 등록된 검사항목인지 유효성 검사
     *
     * @param eairOutletNo
     *            배출구번호
     * @param eairTestItemCd
     *            검사항목코드
     * @return 등록 된 행 수
     * @throws Exception
     */
    public int checkOutletTestItem(@Param("eairOutletNo") int eairOutletNo, @Param("eairTestItemCd") String eairTestItemCd) throws Exception;

    /**
     * 자가측정결과 엑셀 업로드 - 등록된 자가측정인지 확인
     *
     * @param plantCd
     *            사업장코드
     * @param eairOutletNo
     *            배출구번호
     * @param measureYmd
     *            측정일자
     * @return 등록 된 행 수
     * @throws Exception
     */
    public int checkExcelSelfMeasurement(@Param("plantCd") String plantCd, @Param("eairOutletNo") int eairOutletNo, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 자가측정결과 엑셀 업로드 - 배출구에 등록된 검사항목 조회
     *
     * @param eairOutletNo
     *            배출구번호
     * @return 등록 된 행 수
     * @throws Exception
     */
    public List<OutletItem> getOutletTestItems(@Param("eairOutletNo") int eairOutletNo) throws Exception;

}
