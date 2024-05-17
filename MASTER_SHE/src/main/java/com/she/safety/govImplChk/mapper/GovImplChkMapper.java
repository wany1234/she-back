package com.she.safety.govImplChk.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.she.common.model.DefaultParam;
import com.she.safety.model.GovImplChkExRater;
import com.she.safety.model.GovImplChkInRater;
import com.she.safety.model.GovImplChkPlan;
import com.she.safety.model.GovImplChkRslt;

@Mapper
public interface GovImplChkMapper {
    /**
     * 정부지자체 시정조치 이행점검 관리 목록 조회
     * 
     * @param plantCd
     *            사업장
     * @param year
     *            대상년도
     * @param regRegdem
     *            구분
     * @param procStepCd
     *            단계
     * @param stateCd
     *            상태
     * @param chkNm
     *            점검명
     * @param mainDeptCd
     *            주관부서
     * @param mainDeptSubYn
     *            주관부서 하위부서 조회여부
     * @param targetDeptCd
     *            대상부서
     * @param targetDeptSubYn
     *            대상부서 하위부서 조회여부
     * @param refGovNm
     *            관련 정부지자체
     * @param overdueYn
     *            조치기한 초과 여부
     * @return 정부지자체 시정조치 이행점검 관리 목록 조회
     * @throws Exception
     */
    public List<GovImplChkPlan> getGovImplChkResults(@Param("plantCd") String plantCd, @Param("year") String year, @Param("regRegdem") String regRegdem, @Param("procStepCd") String procStepCd, @Param("stateCd") String stateCd, @Param("chkNm") String chkNm, @Param("mainDeptCd") String mainDeptCd, @Param("mainDeptSubYn") String mainDeptSubYn,
            @Param("targetDeptCd") String targetDeptCd, @Param("targetDeptSubYn") String targetDeptSubYn, @Param("refGovNm") String refGovNm, @Param("overdueYn") String overdueYn, @Param("month") int month, @Param("tgtDeptYn") String tgtDeptYn, @Param("statusYn") String statusYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 상세 조회
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 계획 번호
     * @return 정부지자체 시정조치 이행점검 상세 조회
     * @throws Exception
     */
    public GovImplChkPlan getGovImplChkPlan(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 내부점검자 목록 조회
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 계획 번호
     * @return 정부지자체 시정조치 이행점검 내부점검자 목록 조회
     * @throws Exception
     */
    public List<GovImplChkInRater> getGovImplChkInRaters(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 내부점검자 목록 조회
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 계획 번호
     * @return 정부지자체 시정조치 이행점검 내부점검자 목록 조회
     * @throws Exception
     */
    public List<GovImplChkExRater> getGovImplChkExRaters(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 점검결과 조회
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 계획 번호
     * @return 정부지자체 시정조치 이행점검 점검결과 조회
     * @throws Exception
     */
    public GovImplChkRslt getGovImplChkRslt(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 계획 신규등록
     * 
     * @param govImplChkPlan
     *            정부지자체 시정조치 이행점검 계획
     * @return
     * @throws Exception
     */
    public int createGovImplChkPlan(GovImplChkPlan govImplChkPlan) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 계획 수정
     * 
     * @param govImplChkPlan
     *            정부지자체 시정조치 이행점검 계획
     * @return
     * @throws Exception
     */
    public int updateGovImplChkPlan(GovImplChkPlan govImplChkPlan) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 계획 삭제
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    public int deleteGovImplChkPlan(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 내부점검자 저장
     * 
     * @param govImplChkInRater
     *            정부지자체 시정조치 이행점검 내부점검자
     * @return
     * @throws Exception
     */
    public int saveGovImplChkInRater(GovImplChkInRater govImplChkInRater) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 내부점검자 저장
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    public int deleteGovImplChkInRater(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 외부점검자 저장
     * 
     * @param govImplChkExRater
     *            정부지자체 시정조치 이행점검 외부점검자
     * @return
     * @throws Exception
     */
    public int saveGovImplChkExRater(GovImplChkExRater govImplChkExRater) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 외부점검자 삭제
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    public int deleteGovImplChkExRater(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 결과 저장
     * 
     * @param govImplChkRslt
     *            정부지자체 시정조치 이행점검 결과
     * @return
     * @throws Exception
     */
    public int saveGovImplChkRslt(GovImplChkRslt govImplChkRslt) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 결과 삭제
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    public int deleteGovImplChkRslt(@Param("implChkPlanNo") int implChkPlanNo) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검 결재상태 업데이트
     * 
     * @param govImplChkPlan
     *            정부지자체 시정조치 이행점검
     * @return
     * @throws Exception
     */
    public int updateAppr(GovImplChkPlan govImplChkPlan) throws Exception;

    /**
     * 정부지자체 시정조치 이행점검결과 현황
     * 
     * @param plantCd
     *            사업장
     * @param year
     *            대상년도
     * @param regRegdem
     *            구분
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getGovImplChkStatus(@Param("plantCd") String plantCd, @Param("year") String year, @Param("regRegdem") String regRegdem, @Param("totalFlag") String totalFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
