package com.she.health.infirmary.mapper;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.health.model.CheckupResult;
import com.she.health.model.Consult;
import com.she.health.model.DrugSuply;
import com.she.health.model.HearingMgr;
import com.she.health.model.InfirmaryUsage;
import com.she.health.model.Suspect;
import com.she.health.model.SuspectHist;

@Mapper
@Repository("com.she.health.infirmary.mapper.InfirmaryMapper")
public interface InfirmaryMapper {

    /**
     * 건강관리실 방문이력 조회
     *
     * @param plantCd
     *            사업장코드
     * @param counselTypeCd
     *            상담구분코드
     * @param userNm
     *            사용자명
     * @param deptCd
     *            부서코드
     * @param startDt
     *            기간검색 시작일
     * @param endDt
     *            기간검색 종료일
     * @return 건강관리실 방문이력 목록
     * @throws Exception
     */
    public List<InfirmaryUsage> getInfirmaryUsageHistorys(@Param("plantCd") String plantCd, @Param("counselTypeCd") String counselTypeCd, @Param("userNm") String userNm, @Param("deptCd") String deptCd, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;

    /**
     * 건강검진이력 조회
     *
     * @param userNm
     *            사용자명
     * @param deptCd
     *            부서코드
     * @return 건강검진이력목록
     * @throws Exception
     */
    public List<CheckupResult> getCheckupHistorys(@Param("userNm") String userNm, @Param("deptCd") String deptCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("heaCheckupClassCd") String heaCheckupClassCd, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 관리대상 유소견자 조회
     *
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param userId
     *            사용자ID
     * @param suspectYn
     *            유소견자 지정여부
     * @param startYear
     *            검색년도(시작)
     * @param endYear
     *            검색년도(종료)
     * @return 관리대상 유소견자 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectUsers(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("userId") String userId, @Param("suspectYn") String suspectYn, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("deptSubYn") String deptSubYn) throws Exception;

    /**
     * 관리대상 유소견자 상세조회
     *
     * @param userId
     *            사용자명
     * @return 유소견자 상세조회
     * @throws Exception
     */
    public Suspect getSuspectUser(@Param("userId") String userId) throws Exception;

    /**
     * 관리대상 유소견자 요청 생성
     *
     * @param suspect
     * @return 추가 행 수
     * @throws Exception
     */
    public Integer createSuspectRequest(Suspect suspect) throws Exception;

    /**
     * 관리대상 유소견자 요청 수정
     *
     * @param suspect
     * @return 수정 행 수
     * @throws Exception
     */
    public Integer updateSuspectRequest(Suspect suspect) throws Exception;

    /**
     * 관리대상 유소견자 지정
     *
     * @param suspect
     * @return 추가 행 수
     * @throws Exception
     */
    public Integer createSuspectUser(Suspect suspect) throws Exception;

    /**
     * 관리대상 유소견자 해제
     *
     * @param suspect
     * @return 수정 행 수
     * @throws Exception
     */
    public Integer releaseSuspectUser(Suspect suspect) throws Exception;

    /**
     * 관리대상 유소견자 수정
     *
     * @param suspect
     * @return 수정행수
     * @throws Exception
     */
    public Integer updateSuspectUser(Suspect suspect) throws Exception;

    /**
     * 관리대상 유소견자지정팝업 조회
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param deptCd
     *            부서코드
     * @param userNm
     *            사용자명
     * @param suspectYn
     *            유소견자여부
     * @param heaCheckedYear
     *            검진년도
     * @param plantCd
     *            사업장
     *
     * @return 관리대상 유소견자지정팝업 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectAppointUsers(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("deptCd") String deptCd, @Param("userNm") String userNm, @Param("suspectYn") String suspectYn, @Param("heaCheckedYear") String heaCheckedYear, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 관리대상 유소견자해제팝업 조회
     *
     * @param heaCheckupPlanNo
     *            검진계획번호
     * @param deptCd
     *            부서코드
     * @param userNm
     *            사용자명
     * @param heaCheckedYear
     *            검진년도
     * @param plantCd
     *            사업장
     *
     * @return 관리대상 유소견자해제팝업 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectReleaseUsers(@Param("heaCheckupPlanNo") int heaCheckupPlanNo, @Param("deptCd") String deptCd, @Param("userNm") String userNm, @Param("heaCheckedYear") String heaCheckedYear, @Param("plantCd") String plantCd) throws Exception;

    /**
     * 관리대상 유소견자팝업 조회(검진결과 목록)
     *
     * @param plantCd
     *            사업장코드
     * @param userNm
     *            등록자명
     * @param startDt
     *            기간(시작)
     * @param endDt
     *            기간(종료)
     * @return 관리대상 유소견자팝업 목록(결재건별 지정, 해제 인원)
     * @throws Exception
     */
    public List<Suspect> getSuspectUsersOfAll(@Param("plantCd") String plantCd, @Param("userNm") String userNm, @Param("startDt") String startDt, @Param("endDt") String endDt) throws Exception;

    /**
     * 유소견자 지정 및 해제 요청 조회
     *
     * @param suspectReqNo
     *            요청번호
     * @return 유소견지 지정 및 해제 요청
     * @throws Exception
     */
    public Suspect getSuspectRequest(@Param("suspectReqNo") int suspectReqNo) throws Exception;

    /**
     * 유소견자 결재 팝업 관리대상 지정 목록
     *
     * @param suspectReqNo
     *            요청번호
     * @return 관리대상 지정자 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectUsersOfActTarget(@Param("suspectReqNo") int suspectReqNo) throws Exception;

    /**
     * 유소견자 결재 팝업 관리대상 해제 목록
     *
     * @param suspectReqNo
     *            요청번호
     * @return 관리대상 해제자 목록
     * @throws Exception
     */
    public List<Suspect> getSuspectUsersOfRelTarget(@Param("suspectReqNo") int suspectReqNo) throws Exception;

    /**
     * 유소견자 지정자 추가
     *
     * @param suspect
     *            유소견자 정보
     * @return 저장 행 수
     * @throws Exception
     */
    public int createSuspectActUser(Suspect suspect);

    /**
     * 유소견자 해제자 추가
     *
     * @param suspect
     *            유소견자 정보
     * @return 저장 행 수
     * @throws Exception
     */
    public int createSuspectRelUser(Suspect suspect);

    /**
     * 유소견자 요청 지정자 삭제
     *
     * @param suspectReqNo
     *            요청번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteSuspectActUser(@Param("suspectReqNo") int suspectReqNo);

    /**
     * 유소견자 요청 해제자 삭제
     *
     * @param suspectReqNo
     *            요청번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteSuspectRelUser(@Param("suspectReqNo") int suspectReqNo);

    /**
     * 관리대상 유소견자 지정/해제이력
     *
     * @param userId
     *            사용자아이디
     * @return 이력목록
     * @throws Exception
     */
    public List<SuspectHist> getSuspectUserHists(@Param("userId") String userId) throws Exception;

    /**
     * 관리대상 유소견자 이력 추가
     *
     * @param SuspectHist
     * @return 추가행수
     * @throws Exception
     */
    public Integer createSuspectUserHist(SuspectHist suspectHist) throws Exception;

    /**
     * 유소견자 건강상담이력 조회
     *
     * @param userId
     *            사용자명
     * @param startYmd
     *            기간
     * @param endYmd
     *            기간
     * @param plantCd
     *            사업장코드
     * @param counselTypeCd
     *            상담구분코드
     * @param userNm
     *            사원명
     * @param deptCd
     *            부서명
     *
     * @return 유소견자 건강상담이력 목록
     * @throws Exception
     */
    public List<Consult> getConsults(@Param("userId") String userId, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("plantCd") String plantCd, @Param("counselTypeCd") String counselTypeCd, @Param("userNm") String userNm, @Param("deptCd") String deptCd) throws Exception;

    /**
     * 유소견자 건강상담이력 상세 조회
     *
     * @param heaConsultNo
     *            건강상담번호
     * @return Consult
     * @throws Exception
     */
    public Consult getConsult(@Param("heaConsultNo") int heaConsultNo) throws Exception;

    /**
     * 건강상담이력 별 약품 지급 내역 목록
     *
     * @param heaConsultNo
     *            건강상담번호
     * @return Consult
     * @throws Exception
     */
    public List<DrugSuply> getDrugSuplies(@Param("heaConsultNo") int heaConsultNo) throws Exception;

    /**
     * 유소견자 건강상담이력 신규 등록
     *
     * @param consult
     *            (유소견자)건강 상담
     * @return heaConsultNo 건강상담번호
     * @throws Exception
     *             예외
     */
    public int createConsult(Consult consult) throws Exception;

    /**
     * 건강상담이력 별 약품 지급 내역 신규 등록
     *
     * @param drugSuply
     *            건강상담이력 별 약품 지급 내역
     * @return 등록 건수
     * @throws Exception
     *             예외
     */
    public int createDrugSuply(DrugSuply drugSuply) throws Exception;

    /**
     * 건강상담이력 별 약품 지급 내역 수정
     *
     * @param drugSuply
     *            건강상담이력 별 약품 지급 내역
     * @return 수정 건수
     * @throws Exception
     *             예외
     */
    public int updateDrugSuply(DrugSuply drugSuply) throws Exception;

    /**
     * 건강상담이력 별 약품 지급 내역 삭제
     *
     * @param heaConsultNo
     *            건강상담번호 heaDrugNo 약품번호
     * @return 삭제 건수
     * @throws Exception
     *             예외
     */
    public int deleteDrugSuply(@Param("heaConsultNo") int heaConsultNo, @Param("heaDrugNo") int heaDrugNo) throws Exception;

    /**
     * 유소견자 건강상담이력 수정
     *
     * @param consult
     *            (유소견자)건강 상담
     * @return 수정행수
     * @throws Exception
     *             예외
     */
    public int updateConsult(Consult consult) throws Exception;

    /**
     * 유소견자 건강상담 이력 삭제
     *
     * @param List<Consult>
     * @return 삭제행수
     * @throws Exception
     *             예외
     */
    public int deleteConsult(Consult consult) throws Exception;

    /**
     * 청력관리대상자 조회
     *
     * @param plantCd
     *            사업장코드
     * @param userNm
     *            성명
     * @param deptCd
     *            부서코드
     * @param startDt
     *            검사 시작일
     * @param endDt
     *            검사 종료일
     *
     * @return 청력관리대상자 목록
     * @throws Exception
     *             예외
     */
    public List<HearingMgr> getHearingMgrs(@Param("plantCd") String plantCd, @Param("userNm") String userNm, @Param("deptCd") String deptCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("deptSubYn") String deptSubYn);

    /**
     * 청력관리대상자 엑셀다운로드 조회
     *
     * @param plantCd
     * @param userNm
     * @param deptCd
     * @param startDt
     * @param endDt
     * @return
     */
    public List<LinkedHashMap<String, Object>> getHearingMgrsForExcel(@Param("plantCd") String plantCd, @Param("userNm") String userNm, @Param("deptCd") String deptCd, @Param("startDt") String startDt, @Param("endDt") String endDt);

    /**
     * 청력대상자 엑셀업로드
     *
     * @param hearingMgr
     *            청력대상자 검사결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int uploadExcelHearingMgr(HearingMgr hearingMgr);

    /**
     * 유소견자 지정 및 해제 결제완료시 진행상태 변경
     *
     * @param suspectReqNo
     *            요청번호
     * @param suspStepCd
     *            진행단계
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateSuspStepCd(@Param("suspectReqNo") int suspectReqNo, @Param("suspStepCd") String suspStepCd);

    /**
     * 유소견자 지정 및 해제 결재요청번호 수정
     *
     * @param suspectReqNo
     *            요청번호
     * @param apprRqstNo
     *            걸재요청번호
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateApprRqstNo(@Param("suspectReqNo") int suspectReqNo, @Param("apprRqstNo") int apprRqstNo);

    /**
     * 청력관리대상자 조회
     *
     * @param heaHearingMgrListNo
     *            청력관리대상자 번호
     * @return 청력관리대상자
     * @throws Exception
     *             예외
     */
    public HearingMgr getHearingMgr(@Param("heaHearingMgrListNo") int heaHearingMgrListNo) throws Exception;

    /**
     * 청력대상자 수정
     *
     * @param hearingMgr
     *            청력대상자 검사결과
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateHearingMgr(HearingMgr hearingMgr) throws Exception;

    /**
     * 청력관리대상자 조회
     *
     * @param heaHearingMgrListNo
     *            청력관리대상자 번호
     * @return 청력관리대상자
     * @throws Exception
     *             예외
     */
    public int deleteHearingMgr(@Param("heaHearingMgrListNo") int heaHearingMgrListNo) throws Exception;
    
    /**
     * 상담이력 등록
     * @param consult
     * @return
     * @throws Exception
     */
    public int insertConsult(Consult consult)throws Exception;
}
