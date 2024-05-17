package com.she.env.water.operationLog.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.water.model.ChemChkResult;
import com.she.env.water.model.DischChkResult;
import com.she.env.water.model.IngrChkResult;
import com.she.env.water.model.OpLogResult;
import com.she.env.water.model.PreventFacBreakHist;
import com.she.env.water.model.PreventFacRunTm;
import com.she.env.water.model.PwrcChkResult;
import com.she.env.water.model.SuplChkResult;
import com.she.env.water.model.TestItemResult;
import com.she.env.water.model.WasteDischFac;
import com.she.env.water.model.WasteDischFacRunTm;

@Mapper
@Repository("com.she.env.water.operationLog.mapper.OperationMapper")
public interface OperationMapper {

    /**
     * 운영일지 조회
     *
     * @param yearMonth
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */
    public List<OpLogResult> getDailyReports(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("deptCd") String deptCd, @Param("mgDeptCd") String mgDeptCd, @Param("plantCd") String plantCd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo, @Param("envOpLogStCd") String envOpLogStCd,
            @Param("defaultParam") DefaultParam defaultParam, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 방지시설 고장유무 및 특기사항
     *
     * @param measureYmd
     * @return 방지시설 고장유무 및 특기사항 목록
     * @throws Exception
     *             예외
     */

    public List<PreventFacBreakHist> getPreventBreakHists(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 운영일지 중복 조회
     *
     * @param measureYmd
     * @param ewtrCleanFacNo
     * @param plantCd
     *
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */

    public int createOperationLogChk(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지 삭제
     *
     * @param measureYmd
     * @param ewtrCleanFacNo
     * @return 운영일지 목록
     * @throws Exception
     *             예외
     */

    public int deleteOperationLog(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo) throws Exception;

    /**
     * 운영일지 상세조회
     *
     * @param measureYmd
     *            운영일지측정일
     * @return OpLogResult 운영일지
     * @throws Exception
     *             예외
     */
    public OpLogResult getDailyReport(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 운영일지 수정
     *
     * @param OpLogResult
     *            운영일지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateDailyReport(OpLogResult opLogResult) throws Exception;

    /**
     * 운영일지 슬러지 수정
     *
     * @param OpLogResult
     *            운영일지 슬러지
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSludge(OpLogResult opLogResult) throws Exception;

    /**
     * 수질 운영일지 신규등록
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    public int createOperationLog(OpLogResult opLogResult) throws Exception;

    /**
     * 공급수 사용량 조회
     *
     * @param measureYmd
     * @return 공급수 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<SuplChkResult> getSuplChkResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 공급수 사용량 수정
     *
     * @param SuplChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateSuplChkResult(SuplChkResult suplChkResult) throws Exception;

    /**
     * 배출수 사용량 조회
     *
     * @param measureYmd
     * @return 배출수 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<DischChkResult> getDischChkResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 배출수 사용량 수정
     *
     * @param DischChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateDischChkResult(DischChkResult dischChkResult) throws Exception;

    /**
     * 약품 사용량 조회
     *
     * @param measureYmd
     * @return 약품 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<ChemChkResult> getChemChkResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String dpetCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 약품 사용량 수정
     *
     * @param ChemChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateChemChkResult(ChemChkResult chemChkResult) throws Exception;

    /**
     * 약품 현재재고량 플러스
     *
     * @param chemChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int plusAmountCurr(ChemChkResult chemChkResult) throws Exception;

    /**
     * 약품 현재재고량 마이너스
     *
     * @param chemChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int minusAmountCurr(ChemChkResult chemChkResult) throws Exception;

    /**
     * 전력 사용량 조회
     *
     * @param measureYmd
     * @return 전력 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<PwrcChkResult> getPwrcChkResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String dpetCd) throws Exception;

    /**
     * 전력 사용량 수정
     *
     * @param PwrcChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePwrcChkResult(PwrcChkResult pwrcChkResult) throws Exception;

    /**
     * 약품/첨가제 사용량 조회
     *
     * @param measureYmd
     * @return 약품/첨가제 사용량 목록
     * @throws Exception
     *             예외
     */
    public List<IngrChkResult> getIngrChkResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 약품/첨가제 사용량 수정
     *
     * @param IngrChkResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateIngrChkResult(IngrChkResult ingrChkResult) throws Exception;

    /**
     * 자가측정 검사결과 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<HashMap<String, Object>> getTestItemResultsNew(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd, @Param("testItemResult") List<TestItemResult> testItemResult, @Param("testItemResultStr") String testItemResultStr) throws Exception;

    /**
     * 자가측정 검사결과 신규 항목 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<TestItemResult> getTestItemResultsNewItems(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 자가측정 검사현황 신규 항목 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<TestItemResult> getTestItemResultStatusNewItems(@Param("plantCd") String plantCd) throws Exception;

    /**
     * 자가측정 검사결과 조회
     *
     * @param measureYmd
     * @return 자가측정 검사결과 목록
     * @throws Exception
     *             예외
     */
    public List<HashMap<String, Object>> getTestItemResults(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String deptCd, @Param("testItemResult") List<TestItemResult> testItemResult, @Param("testItemResultStr") String testItemResultStr) throws Exception;

    /**
     * 자가측정 검사결과 수정
     *
     * @param TestItemResult
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateTestItemResult(TestItemResult testItemResult) throws Exception;

    /**
     * 배출시설 가동시간 조회
     *
     * @param measureYmd
     * @return 배출시설 가동시간 목록
     * @throws Exception
     *             예외
     */
    public List<WasteDischFacRunTm> getDischRunTms(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 배출시설 가동시간 수정
     *
     * @param WasteDischFacRunTm
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateDischRunTm(WasteDischFacRunTm wasteDischFacRunTm) throws Exception;

    /**
     * 방지시설 가동시간 조회
     *
     * @param measureYmd
     * @return 방지시설 가동시간 목록
     * @throws Exception
     *             예외
     */
    public List<PreventFacRunTm> getPreventRunTms(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 방지시설 가동시간 수정
     *
     * @param PreventFacRunTm
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePreventRunTm(PreventFacRunTm preventFacRunTm) throws Exception;

    /**
     * 금일 폐수 (단위 부피당) 소모전력량 수정
     *
     * @param measureYmd
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updatePwrcPerDay(String measureYmd, int ewtrPwrMeterNo) throws Exception;

    /**
     * 수질 고장시간에 따라 방지시설 가동시간을 해제시키기 전에 관련 데이터 조회
     *
     * @param PreventFacRunTm
     * @return List<HashMap<String, Object>>
     * @throws Exception
     */
    public List<HashMap<String, Object>> getDownTime(PreventFacRunTm preventFacRunTm) throws Exception;

    /**
     * 수질 고장시간에 따라 방지시설 가동시간을 해제시키기 위한 쿼리
     *
     * @param measureYmd
     *            기준일
     * @return 수정행수
     * @throws Exception
     */
    public int updateDownTimeYn(@Param("measureYmd") String measureYmd) throws Exception;

    /**
     * 수질 운영일지 결제
     *
     * @param parameter
     *            검색조건
     * @return 수질 운영현황
     * @throws Exception
     */
    public int updateAppr(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("envOpLogStCd") String envOpLogStCd, @Param("apprRqstNo") int apprRqstNo, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 수질 운영현황 조회
     *
     * @param parameter
     *            검색조건
     * @return 수질 운영현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getOperationStatus(@Param("fromYmd") String fromYmd, @Param("toYmd") String toYmd, @Param("ymCols") List<String> ymCols, @Param("plantCd") String plantCd, @Param("totalTypeCd") String totalTypeCd, @Param("ewtrCleanFacNo") String ewtrCleanFacNo, @Param("search") String search,
            @Param("testItem") List<TestItemResult> testItem, @Param("deptCd") String deptCd, @Param("ymColStr") String ymColStr, @Param("testItemStr") String testItemStr, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 클린센터 조회
     *
     * @param plantCd
     *            사업장코드
     * @return 클린센터 목록
     * @throws Exception
     */
    public List<WasteDischFac> getWasteDischFacs(@Param("plantCd") String plantCd) throws Exception;

    /**
     * 수질 운영일지 신규등록 - 수질 배출시설 가동시간 초기데이터
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    public int createDischRunTms(OpLogResult opLogResult) throws Exception;

    /**
     * 수질 운영일지 신규등록 - 수질 방지시설 가동시간 초기데이터
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    public int createPreventRunTms(OpLogResult opLogResult) throws Exception;

    /**
     * 수질 운영일지 신규등록 - 수질 방지시설 가동시간 초기데이터
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    public List<OpLogResult> getPreventTms(@Param("plantCd") String plantCd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo) throws Exception;

    /**
     * 수질 운영일지 신규등록 - 약품 사용량 초기데이터
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    public int createChems(OpLogResult opLogResult) throws Exception;

    /**
     * 수질 운영일지 신규등록 - 자가 측정 초기데이터
     *
     * @param opLogResult
     *            운영일지
     * @return 등록행수
     * @throws Exception
     */
    public int createTestItems(OpLogResult opLogResult) throws Exception;

    public List<OpLogResult> DailyReportList(@Param("measureYmd") String measureYmd, @Param("ewtrCleanFacNo") int ewtrCleanFacNo, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 수질 슬러지,지도점검사항 리스트
     *
     * @param
     * @return 수질 슬러지,지도점검사항 리스트
     * @throws Exception
     *             예외
     */

}
