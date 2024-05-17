package com.she.mgt.industrialSafetyHealthCommittee.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.mgt.industrialSafetyHealthCommittee.mapper.IndustrialSafetyHealthCommitteeMapper;
import com.she.mgt.model.IndustrialSafetyHealthCommittee;
import com.she.mgt.model.MgtCommitteeAgenda;
import com.she.mgt.model.MgtCommitteeAgendaImpr;
import com.she.mgt.model.MgtCommitteePsn;
import com.she.utils.ConstVal;

/**
 * 산업안전보건위원회 회의록 기능정의
 *
 */
@Service
public class IndustrialSafetyHealthCommitteeService {

    @Autowired
    private IndustrialSafetyHealthCommitteeMapper industrialSafetyHealthCommitteeMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 회의록 조회
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
     * @return 회의록 목록
     * @throws Exception
     */
    public List<IndustrialSafetyHealthCommittee> getIndustrialSafetyHealthCommittees(String plantCd, String startDt, String endDt, String cmiClsCd, String confNm, String progressStepCd, String mainDeptCd, String deptSubYn, String stateCd, String halfTypeCd, String imprChk, DefaultParam defaultParam) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getIndustrialSafetyHealthCommittees(plantCd, startDt, endDt, cmiClsCd, confNm, progressStepCd, mainDeptCd, deptSubYn, stateCd, halfTypeCd, imprChk, defaultParam);
    }

    /**
     * 회의록 등록
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 회의록 키
     */
    @Transactional
    public int createIndustrialSafetyHealthCommittee(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception {

        // 회의록 등록
        int result = 0;
        result += industrialSafetyHealthCommitteeMapper.createIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee);

        if (result > 0) {
            // 참석자 등록
            if (industrialSafetyHealthCommittee.getMgtCommitteePsns() != null && industrialSafetyHealthCommittee.getMgtCommitteePsns().size() > 0) {
                for (MgtCommitteePsn mgtCommitteePsn : industrialSafetyHealthCommittee.getMgtCommitteePsns()) {
                    mgtCommitteePsn.setCommitteeConfNo(industrialSafetyHealthCommittee.getCommitteeConfNo());
                    industrialSafetyHealthCommitteeMapper.createMgtCommitteePsn(mgtCommitteePsn);
                }
            }

            // 회의안건 등록
            if (industrialSafetyHealthCommittee.getMgtCommitteeAgendas() != null && industrialSafetyHealthCommittee.getMgtCommitteeAgendas().size() > 0) {
                for (MgtCommitteeAgenda mgtCommitteeAgenda : industrialSafetyHealthCommittee.getMgtCommitteeAgendas()) {
                    mgtCommitteeAgenda.setCommitteeConfNo(industrialSafetyHealthCommittee.getCommitteeConfNo());
                    mgtCommitteeAgenda.setCreateUserId(industrialSafetyHealthCommittee.getCreateUserId());
                    industrialSafetyHealthCommitteeMapper.createMgtCommitteeAgenda(mgtCommitteeAgenda);
                }
            }
        }

        return result > 0 ? industrialSafetyHealthCommittee.getCommitteeConfNo() : 0;
    }

    /**
     * 회의록 상세 조회
     * 
     * @param committeeConfNo
     *            회의록번호
     * @return 회의록
     * @throws Exception
     */
    public IndustrialSafetyHealthCommittee getIndustrialSafetyHealthCommittee(int committeeConfNo, DefaultParam defaultParam) throws Exception {
        IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee = industrialSafetyHealthCommitteeMapper.getIndustrialSafetyHealthCommittee(committeeConfNo);

        if (industrialSafetyHealthCommittee != null) {
            industrialSafetyHealthCommittee.setMgtCommitteeAgendas(industrialSafetyHealthCommitteeMapper.getMgtCommitteeAgenda(committeeConfNo));
            industrialSafetyHealthCommittee.setMgtCommitteeAgendaImprs(industrialSafetyHealthCommitteeMapper.getMeetingAgendaImpr(ConstVal.SAF_IMPR_CLASS_COMMITTEE, committeeConfNo, "", defaultParam));
            industrialSafetyHealthCommittee.setMgtCommitteePsns(industrialSafetyHealthCommitteeMapper.getMgtCommitteePsn(committeeConfNo));
        }

        return industrialSafetyHealthCommittee;
    }

    /**
     * 회의록 수정
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 회의록 키
     * @throws Exception
     */
    @Transactional
    public int updateIndustrialSafetyHealthCommittee(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception {
        if (industrialSafetyHealthCommittee.getProgressStepCd().equals("CMS02")) { // CMS02:
                                                                                   // 계획완료
                                                                                   // (COM_GEN_STEP)
            industrialSafetyHealthCommittee.setProgressStepCd("CMS03"); // CMS03:
                                                                        // 결과
                                                                        // (COM_GEN_STEP)
        }
        // 회의록 수정
        industrialSafetyHealthCommitteeMapper.updateIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee);

        // 회의안건 수정
        if (industrialSafetyHealthCommittee.getMgtCommitteeAgendas() != null && industrialSafetyHealthCommittee.getMgtCommitteeAgendas().size() > 0) {
            for (MgtCommitteeAgenda mgtCommitteeAgenda : industrialSafetyHealthCommittee.getMgtCommitteeAgendas()) {

                if (mgtCommitteeAgenda.getAgendaNo() > 0) {
                    mgtCommitteeAgenda.setCommitteeConfNo(industrialSafetyHealthCommittee.getCommitteeConfNo());
                    mgtCommitteeAgenda.setUpdateUserId(industrialSafetyHealthCommittee.getUpdateUserId());
                    industrialSafetyHealthCommitteeMapper.updateMgtCommitteeAgenda(mgtCommitteeAgenda);
                } else {
                    mgtCommitteeAgenda.setCommitteeConfNo(industrialSafetyHealthCommittee.getCommitteeConfNo());
                    mgtCommitteeAgenda.setCreateUserId(industrialSafetyHealthCommittee.getUpdateUserId());
                    industrialSafetyHealthCommitteeMapper.createMgtCommitteeAgenda(mgtCommitteeAgenda);
                }
            }
        }

        if (industrialSafetyHealthCommittee.getMgtCommitteeAgendaImprs() != null && industrialSafetyHealthCommittee.getMgtCommitteeAgendaImprs().size() > 0) {
            for (MgtCommitteeAgendaImpr mgtCommitteeAgendaImpr : industrialSafetyHealthCommittee.getMgtCommitteeAgendaImprs()) {
                mgtCommitteeAgendaImpr.setUpdateUserId(industrialSafetyHealthCommittee.getUpdateUserId());
                industrialSafetyHealthCommitteeMapper.updateMgtCommitteeAgendaImpr(mgtCommitteeAgendaImpr);
            }
        }

        if (industrialSafetyHealthCommittee.getDeleteMgtCommitteeAgendas() != null && industrialSafetyHealthCommittee.getDeleteMgtCommitteeAgendas().size() > 0) {
            for (MgtCommitteeAgenda mgtCommitteeAgenda : industrialSafetyHealthCommittee.getDeleteMgtCommitteeAgendas()) {
                if (mgtCommitteeAgenda.getAgendaNo() > 0) {
                    // 회의안건 삭제
                    industrialSafetyHealthCommitteeMapper.deleteMgtCommitteeAgenda(mgtCommitteeAgenda);

                    // 개선 & 즉시조치 삭제
                    industrialSafetyHealthCommitteeMapper.deleteSafImprAct(mgtCommitteeAgenda);
                }
            }
        }
        if (industrialSafetyHealthCommittee.getDeleteMgtCommitteeAgendaImprs() != null && industrialSafetyHealthCommittee.getDeleteMgtCommitteeAgendaImprs().size() > 0) {
            for (MgtCommitteeAgendaImpr mgtCommitteeAgendaImpr : industrialSafetyHealthCommittee.getDeleteMgtCommitteeAgendaImprs()) {
                // 개선 & 즉시조치탭에서 삭제
                industrialSafetyHealthCommitteeMapper.deleteImprAct(mgtCommitteeAgendaImpr.getSafImprActNo());
            }
        }

        // 참석자
        industrialSafetyHealthCommitteeMapper.deleteMgtCommitteePsn(industrialSafetyHealthCommittee.getCommitteeConfNo());
        if (industrialSafetyHealthCommittee.getMgtCommitteePsns() != null && industrialSafetyHealthCommittee.getMgtCommitteePsns().size() > 0) {
            for (MgtCommitteePsn mgtCommitteePsn : industrialSafetyHealthCommittee.getMgtCommitteePsns()) {
                mgtCommitteePsn.setCommitteeConfNo(industrialSafetyHealthCommittee.getCommitteeConfNo());
                industrialSafetyHealthCommitteeMapper.createMgtCommitteePsn(mgtCommitteePsn);
            }
        }

        return industrialSafetyHealthCommittee.getCommitteeConfNo();
    }

    /**
     * 회의록 진행상태 완료
     * 
     * @param industrialSafetyHealthCommittee
     *            회의록
     * @return 회의록 키
     * @throws Exception
     */
    @Transactional
    public int completeIndustrialSafetyHealthCommittee(IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee) throws Exception {
        // 사고 수정
        int resultNo = this.updateIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee);

        return resultNo;
    }

    /**
     * 회의록 삭제
     * 
     * @param industrialSafetyHealthCommittees
     *            회의록
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteIndustrialSafetyHealthCommittee(List<IndustrialSafetyHealthCommittee> industrialSafetyHealthCommittees) throws Exception {
        int resultNo = 0;
        if (industrialSafetyHealthCommittees != null && industrialSafetyHealthCommittees.size() > 0) {
            for (IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee : industrialSafetyHealthCommittees) {
                industrialSafetyHealthCommittee.setImprClassCd(ConstVal.SAF_IMPR_CLASS_COMMITTEE);
                // 개선 & 즉시조치 삭제
                industrialSafetyHealthCommitteeMapper.deleteSafImprActAll(industrialSafetyHealthCommittee);
                // 회의안건 삭제
                industrialSafetyHealthCommitteeMapper.deleteMgtCommitteeAgendaAll(industrialSafetyHealthCommittee);
                // 회의록 삭제
                resultNo += industrialSafetyHealthCommitteeMapper.deleteIndustrialSafetyHealthCommittee(industrialSafetyHealthCommittee);
            }
        }
        return resultNo;
    }

    /**
     * 개선조치사항 항목 조회
     */
    public List<MgtCommitteeAgendaImpr> getMeetingAgendaImpr(String imprClassCd, int committeeConfNo, String apprFlag, DefaultParam defaultParam) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getMeetingAgendaImpr(imprClassCd, committeeConfNo, apprFlag, defaultParam);
    }

    public int apprProcessImpr(int committeeConfNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        return industrialSafetyHealthCommitteeMapper.completeImpr(committeeConfNo, apprRqstNo);
    }

    /**
     * 종사자의견청취 결재 관ㄹ
     * 
     * @param committeeConfNo
     * @param bizApprStepCd
     * @param apprRqstNo
     * @return
     * @throws Exception
     */
    public int apprProcessCommittee(int committeeConfNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String stateCd = "STATE3";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            IndustrialSafetyHealthCommittee industrialSafetyHealthCommittee = getIndustrialSafetyHealthCommittee(committeeConfNo, new DefaultParam("kr"));
            imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_COMMITTEE2, industrialSafetyHealthCommittee.committeeConfNo, "");
            // 결재완료
            stateCd = "STATE4";
            // 개선사항 접수단계로 변경처리
            // 상세의 진행단계가 완료로 바뀔 시에 해당 상세 엮여 있는 개선조치사항건에 대해서 접수 상태로
            // 변경

            for (MgtCommitteeAgenda mgtCommitteeAgenda : industrialSafetyHealthCommittee.getMgtCommitteeAgendas()) {
                imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_COMMITTEE, mgtCommitteeAgenda.getAgendaNo(), "");
            }
        }

        return industrialSafetyHealthCommitteeMapper.apprProcessCommittee(committeeConfNo, apprRqstNo, stateCd);
    }

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
     * @return 회의록 목록
     * @throws Exception
     */
    public List<IndustrialSafetyHealthCommittee> getVendorCommittees(String plantCd, String startDt, String endDt, String vendorCd, String confNm, DefaultParam defaultParam) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getVendorCommittees(plantCd, startDt, endDt, vendorCd, confNm, defaultParam);
    }

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
    public List<MgtCommitteePsn> getMgtCommitteePsnSigns(String plantCd, String psnClsCd, String startDt, String endDt, String userId, String vendorCd, String confNm, DefaultParam defaultParam) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getMgtCommitteePsnSigns(plantCd, psnClsCd, startDt, endDt, userId, vendorCd, confNm, defaultParam);
    }

    /**
     * 참석자 상세 조회(모바일)
     *
     * @param mgtCommitteePsnNo
     *            참석자번호
     * @return 산업안전보건위원회 회의록 참석자 상세
     * @throws Exception
     */
    public MgtCommitteePsn getMgtCommitteePsnSign(int mgtCommitteePsnNo) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getMgtCommitteePsnSign(mgtCommitteePsnNo);
    }

    /**
     * 참석자 사인이미지, 사인완료여부, 사인등록일시 수정
     *
     * @param mgtCommitteePsn
     *            산업안전보건위원회_회의록_참석자
     * @return 참석자 번호
     * @throws Exception
     */
    @Transactional
    public int updateMgtCommitteePsnSign(MgtCommitteePsn mgtCommitteePsn) throws Exception {
        int result = 0;
        result += industrialSafetyHealthCommitteeMapper.updateMgtCommitteePsnSign(mgtCommitteePsn);
        return result > 0 ? mgtCommitteePsn.getMgtCommitteePsnNo() : result;
    }

    /**
     * 종사자의견청취 현황
     * 
     * @param parameter
     *            검색조건
     * @return 종사자의견청취 현황
     * @throws Exception
     */
    public List<HashMap<String, Object>> getListenStatus(String plantCd, String year, String halfTypeCd, DefaultParam defaultParam) throws Exception {
        List<HashMap<String, Object>> resultStatusLists = industrialSafetyHealthCommitteeMapper.getListenStatus(plantCd, year, halfTypeCd, defaultParam);
        return resultStatusLists;
    }

    /**
     * 개선조치사항 팝업항목 조회
     */
    public List<MgtCommitteeAgendaImpr> getMeetingAgendaImprPopup(String imprClassCd, String gubunType, String imprFlag, String plantCd, String year, DefaultParam defaultParam) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getMeetingAgendaImprPopup(imprClassCd, gubunType, imprFlag, plantCd, year, defaultParam);
    }

    /**
     * 청취 상태 목록 팝업
     */
    public List<IndustrialSafetyHealthCommittee> getListenResultMgmtPopup(String halfTypeCd, String plantCd, String year, String progressStepCd, DefaultParam defaultParam) throws Exception {
        return industrialSafetyHealthCommitteeMapper.getListenResultMgmtPopup(halfTypeCd, plantCd, year, progressStepCd, defaultParam);
    }

    /**
     * 개선요청 삭제
     *
     * @param parameter
     * @return 개선요청 삭제
     * @throws Exception
     */
    public int deleteImpr(int refTableId, String imprClassCd) throws Exception {
        return industrialSafetyHealthCommitteeMapper.deleteImpr(refTableId, imprClassCd);
    }
}
