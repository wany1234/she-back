package com.she.env.air.operationLog.mapper;

import java.util.HashMap;
import java.util.List;

import com.she.env.air.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;

@Mapper
@Repository("com.she.env.air.operationLog.mapper.OplogMapper")
public interface OpLogMapper {

    /**
     * 운영일지-기본정보 조회
     *
     * @param plantCd
     * @param deptCd
     * @param mgDeptCd
     * @param fromDate
     * @param toDate
     * @return
     * @throws Exception
     */
    public List<OpLogRslt> getOplogs(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("mgDeptCd") String mgDeptCd, @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("envOpLogStCd") String envOpLogStCd) throws Exception;

    /**
     * 운영정보-기본정보 상세
     *
     * @param deptCd
     * @param measureYmd
     * @throws Exception
     */
    public OpLogRslt getOplog(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 배출구별 가동시간 조회
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletDischChkResult> getOutletDischChkResult(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 방지시설-운전사항
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletPreventChkResult> getOutletPreventChkResult(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 방지시설-운전사항-약품사용량
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<PreventChemResult> getPreventChemResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 방지시설-보수내역
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<PreventFacMaintResults> getPreventMaintHist(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 배출시설-연료
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<FuelCheckResult> getDischFuelResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영일지-원료
     *
     * @param deptCd
     * @param measureYmd
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<IngrCheckResult> getIngrChkResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 운영일지-기본정보 생성
     *
     * @param opLogRslt
     * @return
     * @throws Exception
     */
    public int updateOplogResult(OpLogRslt opLogRslt) throws Exception;

    /**
     * 운영일지-가동시간 생성
     *
     * @param outletDischChkResult
     * @return
     * @throws Exception
     */
    public int createOutletDischChkResult(OutletDischChkResult outletDischChkResult) throws Exception;

    /**
     * 운영일지-가동시간 제거
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public int deleteOutletDischChkResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영일지-운전사항 생성
     *
     * @param outletPreventChkResult
     * @return
     * @throws Exception
     */
    public int createOutletPreventChkResult(OutletPreventChkResult outletPreventChkResult) throws Exception;

    /**
     * 운영일지-운전사항 제거
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public int deleteOutletPreventChkResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영일지-운전사항 약품 생성
     *
     * @param preventChemResult
     * @return
     * @throws Exception
     */
    public int createPreventChemResult(PreventChemResult preventChemResult) throws Exception;

    /**
     * 운영일지-운전사항 약품 제거
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public int deletePreventChemResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영일지-연로 생성
     *
     * @param fuelCheckResult
     * @return
     * @throws Exception
     */
    public int createDischFuelResult(FuelCheckResult fuelCheckResult) throws Exception;

    /**
     * 운영일지-연료 제거
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int deleteDischFuelResult(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지-원료 생성
     *
     * @param ingrCheckResult
     * @return
     * @throws Exception
     */
    public int createIngrChkResult(IngrCheckResult ingrCheckResult) throws Exception;

    /**
     * 운영일지-원료 제거
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public int deleteIngrChkResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영현황 조회
     *
     * @param fromYmd
     * @param toYmd
     * @param totalTypeCd
     * @param plantCd
     * @param search
     * @param deptCd
     * @param defaultParam
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOperationStatus(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("ymCols") List<String> ymCols, @Param("totalTypeCd") String totalTypeCd, @Param("plantCd") String plantCd, @Param("search") String search, @Param("deptCd") String deptCd, @Param("ymColStr") String ymColStr,
            @Param("mainDischFacNm") String mainDischFacNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 대기 운영일지 배출구 기본 중복 데이터
     *
     * @param opLogResult
     *            운영일지 배출구 기본 데이터
     * @return 등록행수
     * @throws Exception
     */

    public int createOperationLogChk(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 운영일지 결재 업데이트
     *
     * @param measureYmd
     * @param deptCd
     * @param envOpLogStCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int updateAppr(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd, @Param("envOpLogStCd") String envOpLogStCd, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    /**
     * 운영일지(관리자) 목록 조회
     *
     * @param plantCd
     * @param startYmd
     * @param endYmd
     * @return List<OpLogAdminRslt>
     * @throws Exception
     */
    public List<OpLogAdminRslt> getOplogAdmins(@Param("plantCd") String plantCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("stepCd") String stepCd) throws Exception;

    /**
     * 운영일지(관리자) 상세 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return OpLogAdminRslt
     * @throws Exception
     */
    public OpLogAdminRslt getOplogAdmin(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영일지(관리자) 작성부서 정보 조회
     *
     * @param plantCd
     * @param measureYmd
     * @return List<HashMap<String, Object>>
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOplogAdminDepts(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 배출구별 가동시간 조회 (관리자)
     *
     * @param plantCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletDischChkResult> getOutletDischChkResultAdmin(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 방지시설-운전사항 (관리자)
     *
     * @param plantCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletPreventChkResult> getOutletPreventChkResultAdmin(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 방지시설-운전사항-약품사용량 (관리자)
     *
     * @param plantCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<PreventChemResult> getPreventChemResultAdmin(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 배출시설-연료 (관리자)
     *
     * @param plantCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<FuelCheckResult> getDischFuelResultAdmin(@Param("plantCd") String plantCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 운영일지-원료 (관리자)
     *
     * @param measureYmd
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<IngrCheckResult> getIngrChkResultAdmin(@Param("measureYmd") String measureYmd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 운영일지(관리자)-기본정보 생성
     *
     * @param opLogRslt
     * @return
     * @throws Exception
     */
    public int updateOplogResultAdmin(OpLogAdminRslt opLogAdminRslt) throws Exception;

    /**
     * 운영일지(관리자)-가동시간 수정
     *
     * @param outletDischChkResult
     * @return
     * @throws Exception
     */
    public int updateOutletDischChkResult(OutletDischChkResult outletDischChkResult) throws Exception;

    /**
     * 운영일지(관리자)-운전사항 수정
     *
     * @param outletPreventChkResult
     * @return
     * @throws Exception
     */
    public int updateOutletPreventChkResult(OutletPreventChkResult outletPreventChkResult) throws Exception;

    /**
     * 운영일지(관리자)-운전사항 약품 생성
     *
     * @param preventChemResult
     * @return
     * @throws Exception
     */
    public int updatePreventChemResult(PreventChemResult preventChemResult) throws Exception;

    /**
     * 운영일지(관리자)-연로 생성
     *
     * @param fuelCheckResult
     * @return
     * @throws Exception
     */
    public int updateDischFuelResult(FuelCheckResult fuelCheckResult) throws Exception;

    /**
     * 운영일지(관리자)-원료 생성
     *
     * @param ingrCheckResult
     * @return
     * @throws Exception
     */
    public int updateIngrChkResult(IngrCheckResult ingrCheckResult) throws Exception;

    /**
     * 운영일지(관리자) 결재 업데이트
     *
     * @param measureYmd
     * @param plantCd
     * @param stepCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int updateAdminAppr(@Param("measureYmd") String measureYmd, @Param("plantCd") String plantCd, @Param("stepCd") String stepCd, @Param("apprRqstNo") int apprRqstNo) throws Exception;

    /**
     * 운영일지- 로그 생성여부 인서트
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogCheck(@Param("measureYmd") String measureYmd, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 운영일지 작성구분 로그 생성
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogOplogBase(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 대기 배출구 로그 생성
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogLogOutlet(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 대기 배출시설 로그 생성
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogDischFac(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 대기 배출 시설별 연료 로그 생성
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogDischFacFuel(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 대기 방지시설 로그 생성
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogPreventFac(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 대기 (오염)방지 시설_전력량계 로그 생성
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogPreventFacElecMeter(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 대기 원료 로그 생성
     *
     * @param measureYmd
     * @param plantCd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int createOpLogIngr(@Param("measureYmd") String measureYmd, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지- 로그 생성 체크
     *
     * @param measureYmd
     * @param deptCd
     * @return
     * @throws Exception
     */
    public int getOpLogCheck(@Param("measureYmd") String measureYmd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 로그 - 배출구별 가동시간 조회
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletDischChkResult> getOpLogOutletDischChkResult(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 로그 - 방지시설-운전사항
     *
     * @param plantCd
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<OutletPreventChkResult> getOpLogOutletPreventChkResult(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 로그 - 운전사항-약품사용량
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<PreventChemResult> getOpLogPreventChemResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 로그 - 방지시설-보수내역
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<PreventFacMaintResults> getOpLogPreventMaintHist(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 로그 - 배출시설-연료
     *
     * @param deptCd
     * @param measureYmd
     * @return
     * @throws Exception
     */
    public List<FuelCheckResult> getOpLogDischFuelResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 로그 - 운영일지-원료
     *
     * @param deptCd
     * @param measureYmd
     * @param plantCd
     * @return
     * @throws Exception
     */
    public List<IngrCheckResult> getOpLogIngrChkResult(@Param("deptCd") String deptCd, @Param("measureYmd") String measureYmd, @Param("plantCd") String plantCd) throws Exception;
}
