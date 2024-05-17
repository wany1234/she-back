package com.she.safety.wkod.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.RiskHazard;
import com.she.safety.model.ConstLegalLcn;
import com.she.safety.model.ConstLegalLcnMaster;
import com.she.safety.model.FacilityUsed;
import com.she.safety.model.LOTO;
import com.she.safety.model.Signature;
import com.she.safety.model.WkodChkItem;
import com.she.safety.model.WkodChkResult;
import com.she.safety.model.WkodGasMeas;
import com.she.safety.model.WkodKind;
import com.she.safety.model.WkodMaster;
import com.she.safety.model.WkodSpeKind;
import com.she.safety.model.WkodSubconnWorker;

@Mapper
@Repository("com.she.safety.wkod.mapper.WkodMasterMapper")
public interface WkodMasterMapper {

    /**
     * 작업허가서 조회
     *
     * @param plantCd
     *            사업장
     * @param constTitle
     *            공사명
     * @param wkodClsCd
     *            허가서구분
     * @param vendorNm
     *            공사업체
     * @param startYmd
     *            작업기간 시작일자
     * @param endYmd
     *            작업기간 종료일자
     * @param reqDeptCd
     *            신청부서
     * @param wkodKindCds
     *            작업종류
     * @param pubDeptCd
     *            발행부서
     * @param wkodStepCd
     *            진행단계
     * @param wkodNum
     *            작업NO
     * @param workTitle
     *            작업명
     * @param searchFlag
     *            조회구분자
     * @param constNo
     *            공사번호
     * @return 작업허가서 목록
     * @throws Exception
     */
    public List<WkodMaster> getWkodMasters(@Param("plantCd") String plantCd, @Param("wkodClsCd") String wkodClsCd, @Param("vendorCd") String vendorCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("reqDeptCd") String reqDeptCd, @Param("reqDeptSubYn") String reqDeptSubYn, @Param("wkodKindCds") String[] wkodKindCds,
            @Param("pubDeptCd") String pubDeptCd, @Param("pubDeptSubYn") String pubDeptSubYn, @Param("wkodStepCd") String wkodStepCd, @Param("wkodNum") String wkodNum, @Param("keyword") String keyword, @Param("searchFlag") String searchFlag, @Param("constNo") String constNo, @Param("psmYn") String psmYn, @Param("subconnNm") String subconnNm,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    // /**
    // * 작업종류 조회 (공통으로 작업된 기능과 다르게 배열로 값을 받아 처리해야 하기에 따로 개발)
    // *
    // * @param code_group_cd
    // * @param attr1[]
    // * @return 작업종류 목록
    // * @throws Exception
    // */
    // public List<CodeMaster> getWkodKinds(@Param("codeGroupCd") String
    // codeGroupCd, @Param("attr1") String attr1) throws Exception;

    /**
     * 작업허가서 상세 조회
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 작업허가서 상세
     * @throws Exception
     */
    public WkodMaster getWkodMaster(@Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 허가서구분별 작업구분 조회
     *
     * @return 허가서구분별 작업구분 목록
     * @throws Exception
     */
    public List<String> getWkodClsKinds() throws Exception;

    /**
     * 작업허가서 현황 조회
     *
     * @param plantCd
     *            사업장
     * @param reqDeptCd
     *            신청부서
     * @param subconnNm
     *            공사업체
     * @param startYmd
     *            작업시작일
     * @param wkodNo
     *            작업종료일
     * @param wkodKinds
     *            허가구분별 작업구분
     * @return 작업허가서 현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getWkodMasterStatus(@Param("plantCd") String plantCd, @Param("reqDeptCd") String reqDeptCd, @Param("subconnNm") String subconnNm, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("wkodKinds") String wkodKinds, @Param("wkodKindSearchs") String wkodKindSearchs,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업허가서 현황 조회 (사업장에 따른 신청부서 별 주관부서별 공사업체)
     *
     * @param plantCd
     *            사업장
     * @param reqDeptCd
     *            신청부서
     * @param subconnNm
     *            공사업체
     * @param startYmd
     *            작업시작일
     * @param wkodNo
     *            작업종료일
     * @param wkodKinds
     *            허가구분별 작업구분
     * @return 작업허가서 현황 (사업장에 따른 신청부서 별 주관부서별 공사업체)
     * @throws Exception
     */
    public List<HashMap<String, Object>> getWkodMasterStatusSub(@Param("plantCd") String plantCd, @Param("reqDeptCd") String reqDeptCd, @Param("pubDeptCd") String pubDeptCd, @Param("subconnNm") String subconnNm, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("wkodKinds") String wkodKinds,
            @Param("wkodKindSearchs") String wkodKindSearchs, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전조치항목 조회(report용)
     * 
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업구분 목록
     * @throws Exception
     */
    public List<WkodChkItem> getWkodChkItemPrint(@Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전보호구 조회
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 안전보호구 목록
     * @throws Exception
     */
    public List<String> getSafWkodUseSpe(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 취급물질 조회
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 취급물질 목록
     * @throws Exception
     */
    public List<RiskHazard> getWkodHazardSelectList(@Param("wkodNo") int wkodNo) throws Exception;

    // /**
    // * 안전점검확인 결과 조회
    // * @param wkodNo 작업허가서 번호
    // * @param searchFlag 조회구분
    // * @return 취급물질 목록
    // * @throws Exception
    // */
    // public List<WkodChkItem> getWkodDepts(@Param("wkodNo") int
    // wkodNo,@Param("searchFlag") String searchFlag) throws Exception;
    //
    // /**
    // * 안전점검확인 결과(저장된 데이터) 조회
    // * @param wkodNo 작업허가서 번호
    // * @param searchFlag 조회구분
    // * @return 취급물질 목록
    // * @throws Exception
    // */
    // public List<WkodChkItem> getSelectedWkodDepts(@Param("wkodNo") int
    // wkodNo,@Param("searchFlag") String searchFlag) throws Exception;

    /**
     * 작업허가서 항목 생성
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createWkodMaster(WkodMaster wkodMaster) throws Exception;

    /**
     * 작업허가서 항목 수정
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateWkodMaster(WkodMaster wkodMaster) throws Exception;

    /**
     * 안전작업 취급물질 등록
     *
     * @param wkodNo
     *            작업허가서 번호
     * @param hazardCd
     *            유해인자 번호
     * @return 취급물질 목록
     * @throws Exception
     */
    public int createWkodHazard(@Param("wkodNo") int wkodNo, @Param("riskHazardNo") int riskHazardNo) throws Exception;

    /**
     * 특별작업구분 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWkodSpeKind(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 특별작업구분 등록
     *
     * @param wkodSpeKind
     *            특별작업구분
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWkodSpeKind(WkodSpeKind wkodSpeKind) throws Exception;

    /**
     * 작업구분 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWkodKind(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업구분 등록
     *
     * @param wkodKind
     *            작업구분
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWkodKind(WkodKind wkodKind) throws Exception;

    /**
     * 가스농도측정 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWkodGasMeas(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 가스농도측정 등록
     *
     * @param wkodGasMeas
     *            가스농도측정
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWkodGasMeas(WkodGasMeas wkodGasMeas) throws Exception;

    /**
     * 업체 작업자 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteWkodSubconnWorker(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 업체 작업자 등록
     *
     * @param wkodSubconnWorker
     *            업체 작업자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWkodSubconnWorker(WkodSubconnWorker wkodSubconnWorker) throws Exception;

    /**
     * 안전작업 취급물질 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 안전작업 취급물질 삭제 여부
     * @throws Exception
     */
    public int deleteWkodHazard(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 안전작업 사용보호구 등록
     *
     * @param wkodNo
     *            작업허가서 번호
     * @param wkodSpeCd
     *            보호구 코드
     * @return 취급물질 목록
     * @throws Exception
     */
    public int createWkodUseSpe(@Param("wkodNo") int wkodNo, @Param("wkodSpeCd") String wkodSpeCd) throws Exception;

    /**
     * 안전작업 사용보호구 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 안전작업 사용보호구 삭제 여부
     * @throws Exception
     */
    public int deleteWkodUseSpe(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 안전작업 점검확인결과 등록
     *
     * @param wkodChkResult
     *            안전작업 점검확인결과
     * @return 등록 행 수
     * @throws Exception
     */
    public int createWkodChkResult(WkodChkResult wkodChkResult) throws Exception;

    /**
     * 안전작업 사용보호구 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 안전작업 사용보호구 삭제 여부
     * @throws Exception
     */
    public int deleteWkodChkResult(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업허가서 삭제
     *
     * @param wkodMaster
     *            작업허가서 정보
     * @throws Exception
     */
    public int deleteWkodMaster(WkodMaster wkodMaster) throws Exception;

    /**
     * 작업허가서별 설비 조회
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 작업허가서별 설비 목록
     * @throws Exception
     */
    public List<FacilityUsed> getWkodMasterFacility(@Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 작업허가서별 설비 생성
     *
     * @param wkodChkItem
     *            작업허가서 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createWkodUseFacility(FacilityUsed facilityUsed) throws Exception;

    /**
     * 작업허가서별 설비 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @param safFacilityCd
     *            설비코드
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteWkodMasterFacility(@Param("wkodNo") int wkodNo, @Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 법적인허가대상 조회
     *
     * @param wkodNo
     *            작업허가서번호
     * @return 법적인허가대상
     * @throws Exception
     */
    public List<ConstLegalLcnMaster> getWkodLegalLcnGrps() throws Exception;

    /**
     * 작업허가서별 법적인허가대상 항목 조회
     *
     * @param wkodNo
     *            작업허가서번호
     * @param grpCd
     *            분류코드
     * @return 작업허가서별 법적인허가대상 항목
     * @throws Exception
     */
    public List<ConstLegalLcn> getWkodLegalLcns(@Param("constNo") String constNo, @Param("grpCd") String grpCd) throws Exception;

    /**
     * 작업허가서별 법적인허가대상 항목 조회
     *
     * @param wkodNo
     *            작업허가서번호
     * @param grpCd
     *            분류코드
     * @return 작업허가서별 법적인허가대상 항목
     * @throws Exception
     */
    public List<ConstLegalLcn> getWkodNoLegalLcns(@Param("wkodNo") int wkodNo, @Param("grpCd") String grpCd) throws Exception;

    public List<LOTO> getWkodLotos(@Param("wkodNo") int wkodNo) throws Exception;

    public int createWkodLoto(LOTO loto) throws Exception;

    public int deleteWkodLoto(@Param("wkodNo") int wkodNo) throws Exception;

    public int constCompleteWkodMaster(WkodMaster wkodMaster) throws Exception;

    public int overWkodMaster(WkodMaster wkodMaster) throws Exception;

    public int cancelWkodMaster(WkodMaster wkodMaster) throws Exception;

    /**
     * 작업허가별 법적인허가대상 항목 신규등록
     *
     * @param wkodLegalLcn
     *            법적인허가대상 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createWkodLegalLcn(ConstLegalLcn wkodLegalLcn) throws Exception;

    /**
     * 작업허가별 법적인허가대상 항목 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteWkodLegalLcn(@Param("constNo") String constNo) throws Exception;

    /**
     * 작업허가별 법적인허가대상 항목 신규등록
     *
     * @param wkodLegalLcn
     *            법적인허가대상 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createWkodNoLegalLcn(ConstLegalLcn wkodLegalLcn) throws Exception;

    /**
     * 작업허가별 법적인허가대상 항목 삭제
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteWkodNoLegalLcn(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 특별작업구분 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 특별작업구분 목록
     * @throws Exception
     */
    public List<WkodSpeKind> getWkodSpeKind(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업구분 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업구분 목록
     * @throws Exception
     */
    public List<WkodKind> getWkodKind(@Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 가스농도측정 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 가스농도측정 목록
     * @throws Exception
     */
    public List<WkodGasMeas> getWkodGasMeas(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업허가서별 작업자 조회
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업허가서별 작업자 목록
     * @throws Exception
     */
    public List<WkodSubconnWorker> getWkodSubconnWorkers(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업허가서 진행단계 변경
     *
     * @param wkodNo
     *            작업허가서 ID
     * @param apprRqstNo
     *            결재진행no
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeWkodMaster(@Param("wkodNo") int wkodNo, @Param("apprRqstNo") int apprRqstNo, @Param("wkodStpeCd") String wkodStpeCd) throws Exception;

    /**
     * 특별작업구분 조회(report용)
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 특별작업구분 목록
     * @throws Exception
     */
    public List<WkodSpeKind> getWkodSpeKindPrint(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업구분 조회(report용)
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 작업구분 목록
     * @throws Exception
     */
    public List<WkodKind> getWkodKindPrint(@Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 보호구 조회(report용)
     *
     * @param wkodNo
     *            작업허가서 ID
     * @return 보호구 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSafWkodUseSpeAllInfo(@Param("wkodNo") int wkodNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int updateWkodPrt(@Param("wkodNo") int wkodNo) throws Exception;

    /**
     * 작업허가서 서명 확인 등록(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    public int createConfirmSign(Signature signature) throws Exception;

    /**
     * 작업허가서 서명 확인 수정(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    public int updateConfirmSign(Signature signature) throws Exception;

    /**
     * 작업허가서 서명 확인 삭제(모바일)
     *
     * @param parameter
     * @return
     * @throws Exception
     */
    public int deleteConfirmSign(@Param("signNo") int signNo) throws Exception;

    /**
     * 작업허가서 서명 확인 조회 (모바일)
     *
     * @param wkodNo
     *            작업허가서 번호
     * @return 서명 확인 목록
     * @throws Exception
     */
    public List<Signature> getConfirmSigns(@Param("wkodNo") int wkodNo) throws Exception;

    public List<WkodChkResult> getSafWkodChkResult(@Param("wkodNo") int wkodNo) throws Exception;

    public List<WkodChkResult> getSafWkodChkResultBase(@Param("wkodNo") int wkodNo, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public com.she.safety.model.Map getSafWkodMap(@Param("mapNo") int mapNo, @Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
