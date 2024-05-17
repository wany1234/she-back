package com.she.mgt.industrialSafetyHealthCommittee.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.mgt.model.IndustrialSafetyHealthCommittee;
import com.she.mgt.model.MgtCommitteeAgenda;
import com.she.mgt.model.MgtCommitteeAgendaImpr;
import com.she.mgt.model.MgtCommitteePsn;

@Mapper
@Repository("com.she.mgt.industrialSafetyHealthCommittee.mapper.IndustrialSafetyHealthCommitteeMapper")
public interface IndustrialSafetyHealthCommitteeMapper {

    /**
     * 산업안전보건위원회 회의록 조회
     *
     * @param plantCd
     *            사업장명
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param cmiClsCd
     *            구분
     * @param confNm
     *            제목
     * @return 산업안전보건위원회 회의록 목록
     * @throws Exception
     */
    public List<IndustrialSafetyHealthCommittee> getIndustrialSafetyHealthCommittees(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("cmiClsCd") String cmiClsCd, @Param("confNm") String confNm, @Param("progressStepCd") String progressStepCd, @Param("mainDeptCd") String mainDeptCd,
            @Param("deptSubYn") String deptSubYn, @Param("stateCd") String apprStatusCd, @Param("halfTypeCd") String halfTypeCd, @Param("imprChk") String imprChk, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 산업안전보건위원회 회의록 등록
     * 
     * @param industrialSafetyHealthCommittee
     *            산업안전보건위원회 회의록
     * @return 회의록 키
     * @throws Exception
     */
    public int createIndustrialSafetyHealthCommittee(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception;

    /**
     * 참석자 등록
     *
     * @param mgtCommitteePsn
     *            참석자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createMgtCommitteePsn(MgtCommitteePsn mgtCommitteePsn) throws Exception;

    /**
     * 회의안건 등록
     * 
     * @param mgtCommitteeAgenda
     *            회의안건
     * @return 회의안건 키
     * @throws Exception
     */
    public int createMgtCommitteeAgenda(MgtCommitteeAgenda mgtCommitteeAgenda) throws Exception;

    /**
     * 산업안전보건위원회 회의록 상세 조회
     * 
     * @param committeeConfNo
     *            회의록번호
     * @return 회의록
     * @throws Exception
     */
    public IndustrialSafetyHealthCommittee getIndustrialSafetyHealthCommittee(@Param("committeeConfNo") int committeeConfNo) throws Exception;

    /**
     * 회의록 안건 조회
     * 
     * @param committeeConfNo
     *            회의록번호
     * @return 회의안건
     * @throws Exception
     */
    public List<MgtCommitteeAgenda> getMgtCommitteeAgenda(@Param("committeeConfNo") int committeeConfNo) throws Exception;

    /**
     * 참석자 조회
     *
     * @param committeeConfNo
     *            회의록번호
     * @return 참석자
     * @throws Exception
     */
    public List<MgtCommitteePsn> getMgtCommitteePsn(@Param("committeeConfNo") int committeeConfNo) throws Exception;

    /**
     * 산업안전보건위원회 회의록 수정
     * 
     * @param industrialSafetyHealthCommittee
     *            산업안전보건위원회 회의록
     * @return 회의록 키
     * @throws Exception
     */
    public int updateIndustrialSafetyHealthCommittee(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception;

    /**
     * 회의안건 수정
     * 
     * @param mgtCommitteeAgenda
     *            회의안건
     * @return 회의안건 키
     * @throws Exception
     */
    public int updateMgtCommitteeAgenda(MgtCommitteeAgenda mgtCommitteeAgenda) throws Exception;

    /**
     * 회의안건 삭제
     * 
     * @param mgtCommitteeAgenda
     *            회의안건
     * @return 회의안건 키
     * @throws Exception
     */
    public int deleteMgtCommitteeAgenda(MgtCommitteeAgenda mgtCommitteeAgenda) throws Exception;

    /**
     * 참석자 삭제
     *
     * @param committeeConfNo
     *            회의록번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteMgtCommitteePsn(@Param("committeeConfNo") int committeeConfNo) throws Exception;

    /**
     * 개선 & 즉시조치 삭제
     * 
     * @param mgtCommitteeAgenda
     *            회의안건
     * @return 개선 & 즉시조치 키
     * @throws Exception
     */
    public int deleteSafImprAct(MgtCommitteeAgenda mgtCommitteeAgenda) throws Exception;

    /**
     * 회의록에 해당 되는 개선 & 즉시조치 전체 삭제
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 개선 & 즉시조치 키
     * @throws Exception
     */
    public int deleteSafImprActAll(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception;

    /**
     * 회의록에 해당 되는 회의안건 삭제
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 회의안건 키
     * @throws Exception
     */
    public int deleteMgtCommitteeAgendaAll(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception;

    /**
     * 회의록 삭제
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 회의록 키
     * @throws Exception
     */
    public int deleteIndustrialSafetyHealthCommittee(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception;

    public List<MgtCommitteeAgendaImpr> getMeetingAgendaImpr(@Param("imprClassCd") String imprClassCd, @Param("committeeConfNo") int committeeConfNo, @Param("apprFlag") String apprFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<MgtCommitteeAgendaImpr> getMeetingAgendaImprPopup(@Param("imprClassCd") String imprClassCd, @Param("gubunType") String gubunType, @Param("imprFlag") String imprFlag, @Param("plantCd") String plantCd, @Param("year") String year, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<IndustrialSafetyHealthCommittee> getListenResultMgmtPopup(@Param("halfTypeCd") String halfTypeCd, @Param("plantCd") String plantCd, @Param("year") String year, @Param("progressStepCd") String progressStepCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public int deleteImprAct(int safImprActNo) throws Exception;

    public int updateMgtCommitteeAgendaImpr(MgtCommitteeAgendaImpr mgtCommitteeAgendaImpr) throws Exception;

    public int completeImpr(int committeeConfNo, int apprRqstNo);

    /**
     * 산업안전보건위원회_회의록 결재
     *
     * @param committeeConfNo
     *            회의록 번호
     * @param apprRqstNo
     *            결재번호
     * @param progressStepCd
     *            진행상태
     * @return 수정 행 수
     * @throws Exception
     */
    public int apprProcessCommittee(@Param("committeeConfNo") int committeeConfNo, @Param("apprRqstNo") int apprRqstNo, @Param("stateCd") String stateCd);

    /**
     * 회의록 조회(협력업체)
     *
     * @param plantCd
     *            사업장명
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param vendorCd
     *            업체코드
     * @param confNm
     *            제목
     * @return 산업안전보건위원회 회의록 목록
     * @throws Exception
     */
    public List<IndustrialSafetyHealthCommittee> getVendorCommittees(@Param("plantCd") String plantCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("vendorCd") String vendorCd, @Param("confNm") String confNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 참석자 조회(모바일)
     *
     * @param plantCd
     *            사업장명
     * @param psnClsCd
     *            참석자구분코드
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param userId
     *            사용자ID
     * @param vendorCd
     *            업체코드
     * @param confNm
     *            제목
     * @return 산업안전보건위원회 회의록 참석자 목록
     * @throws Exception
     */
    public List<MgtCommitteePsn> getMgtCommitteePsnSigns(@Param("plantCd") String plantCd, @Param("psnClsCd") String psnClsCd, @Param("startDt") String startDt, @Param("endDt") String endDt, @Param("userId") String userId, @Param("vendorCd") String vendorCd, @Param("confNm") String confNm, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 참석자 상세 조회(모바일)
     *
     * @param mgtCommitteePsnNo
     *            참석자번호
     * @return 산업안전보건위원회 회의록 참석자 상세
     * @throws Exception
     */
    public MgtCommitteePsn getMgtCommitteePsnSign(@Param("mgtCommitteePsnNo") int mgtCommitteePsnNo) throws Exception;

    /**
     * 참석자 사인이미지, 사인완료여부, 사인등록일시 수정
     *
     * @param mgtCommitteePsn
     *            산업안전보건위원회_회의록_참석자
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMgtCommitteePsnSign(MgtCommitteePsn mgtCommitteePsn) throws Exception;

    /**
     * 평가결과 현황 목록
     * 
     * @param parameter
     *            검색조건
     * @return 평가결과 현황 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getListenStatus(@Param("plantCd") String plantCd, @Param("year") String year, @Param("halfTypeCd") String halfTypeCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 개선요청 삭제
     *
     * @param parameter
     * @return 개선요청 삭제
     * @throws Exception
     */
    public int deleteImpr(@Param("refTableId") int refTableId, @Param("imprClassCd") String imprClassCd) throws Exception;
}
