package com.she.impr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.impr.model.ImprAct;
import com.she.impr.model.ImprActDashboard;

@Mapper
@Repository("com.she.impr.mapper.ImprMapper")
public interface ImprMapper {

    /**
     * 개선조치사항 항목 조회
     * 
     * @param imprClassCd
     *            개선분류코드 코드
     * @param imprStepCd
     *            개선진행단계 코드
     * @param actClassCd
     *            조치구분
     * @param imprTitle
     *            제목
     * @param imprRqstDeptCd
     *            개선요청부서 코드
     * @param actDeptCd
     *            조치부서 코드
     * @param startYmd
     *            요청일(from)
     * @param endYmd
     *            요청일(to)
     * @param refTableId
     *            관련테이블 PKID
     * @param notImprStepCd
     *            개선진행단계 코드(입력한 개선진행단계 외의 데이터를 가져온다)
     * @return 개선조치사항 항목
     * @throws Exception
     */
    public List<ImprAct> getImprActs(@Param("year") String year, @Param("plantCd") String plantCd, @Param("imprClassCd") String imprClassCd, @Param("imprStepCd") String imprStepCd, @Param("actClassCd") String actClassCd, @Param("imprTitle") String imprTitle, @Param("imprRqstDeptCd") String imprRqstDeptCd,
            @Param("imprRqstDeptSubYn") String imprRqstDeptSubYn, @Param("actDeptCd") String actDeptCd, @Param("actDeptSubYn") String actDeptSubYn, @Param("actVendorCd") String actVendorCd, @Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("subconnNm") String subconnNm, @Param("refTableId") int refTableId,
            @Param("vendorSearchFlag") String vendorSearchFlag, @Param("notImprStepCd") String notImprStepCd, @Param("apprFlag") String apprFlag, @Param("monFlag") int monFlag, @Param("strParam") String strParam, @Param("subPlantCd") String subPlantCd, @Param("popupMode") String popupMode, @Param("startDt") String startDt,
            @Param("endDt") String endDt, @Param("limitStartYmd") String limitStartYmd, @Param("limitEndYmd") String limitEndYmd, @Param("imprChk") String imprChk, @Param("imprGubun") String imprGubun, @Param("stateCd") String stateCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선조치사항 항목 조회
     * 
     * @param safImprActNo
     *            개선조치사항 번호
     * @return 개선조치사항 항목
     * @throws Exception
     */
    public ImprAct getImprAct(@Param("safImprActNo") int safImprActNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선조치사항 항목 생성
     * 
     * @param imprAct
     *            개선조치사항 항목
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    public int createImprAct(ImprAct imprAct) throws Exception;

    /**
     * 개선조치사항 항목 수정
     * 
     * @param imprAct
     *            개선조치사항 항목
     * @return 개선조치사항 항목 Key값
     * @throws Exception
     */
    public int updateImprAct(ImprAct imprAct) throws Exception;

    /**
     * 개선조치사항 항목 취소
     * 
     * @param safImprActNo
     *            개선조치사항 Key값
     * @return 취소 행 수
     * @throws Exception
     */
    public int cancelImprAct(@Param("safImprActNo") int safImprActNo) throws Exception;

    /**
     * 개선조치사항 항목 삭제
     * 
     * @param safImprActNo
     *            개선조치사항 Key값
     * @return 삭제 여부
     * @throws Exception
     */
    public int deleteImprAct(@Param("safImprActNo") String safImprActNo) throws Exception;

    /**
     * 개선조치사항 항목 연관테이블 key에 따른 삭제
     * 
     * @param refTableId
     *            테이블 key
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteImprActRefTableId(@Param("refTableId") int refTableId) throws Exception;

    /**
     * 개선조치사항 실적 조회
     * 
     * @return 작업허가서 실적 목록
     * @throws Exception
     */
    public List<ImprAct> getImprActStatus(@Param("startYmd") String startYmd, @Param("endYmd") String endYmd, @Param("plantCd") String plantCd, @Param("imprClassCd") String imprClassCd, @Param("actDeptCd") String actDeptCd, @Param("actDeptSubYn") String actDeptSubYn, @Param("vendorCd") String vendorCd, @Param("actClassCd") String actClassCd,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선조치사항 진행상태 변경
     * 
     * @return 변경 행 수
     * @throws Exception
     */
    public int completeImpr(@Param("safImprActNo") int safImprActNo, @Param("apprRqstNo") int apprRqstNo, @Param("imprStepCd") String imprStepCd, @Param("stateCd") String stateCd) throws Exception;

    public List<ImprActDashboard> getImprActDashboards(@Param("refTableId") int refTableId, @Param("imprClassCd") String imprClassCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 코드 마스터 유효성 검사
     *
     * @param codeNm
     *            코드명칭
     * @param codeGroupCd
     *            코드그룹코드
     * @return code
     * @throws Exception
     */
    public String checkCodeMaster(@Param("codeNm") String codeNm, @Param("codeGroupCd") String codeGroupCd) throws Exception;

    public String checkDeptMaster(@Param("deptNm") String deptNm, @Param("plantCd") String plantCd) throws Exception;

    public String checkUserMaster(@Param("actUserNm") String actUserNm, @Param("actUserId") String actUserId, @Param("actDeptCd") String actDeptCd) throws Exception;

    /**
     * 엑셀 업로드 등록
     * 
     * @param hashmap
     *            개선사항 항목
     * @throws Exception
     */
    public int excelUploadCreateImprAct(ImprAct imprAct) throws Exception;

    /**
     * 신규개선조치사항 탭항목 조회
     *
     * @param parameter
     *            imprClassCd, refTableId
     * @return 신규개선조치사항 탭항목 조회
     * @throws Exception
     */
    public List<ImprAct> getNewImprActs(@Param("imprClassCd") String imprClassCd, @Param("refTableId") int refTableId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
