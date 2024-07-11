package com.she.mgt.mgtTarget.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.mgt.mgtTarget.mapper.MgtTargetMapper;
import com.she.mgt.model.MgtTgt;
import com.she.mgt.model.MgtTgtItemEvalRslt;
import com.she.mgt.model.MgtTgtItemPlanRslt;
import com.she.mgt.model.MgtTgtItemRslt;
import com.she.mgt.model.MgtTgtStatus;
import com.she.mgt.model.MgtTgtStatusGraph;
import com.she.utils.ConstVal;

@Service
public class MgtTargetService {
    @Autowired
    private MgtTargetMapper mgtTargetMapper;

    /**
     * 목표/실적/평가 관리 목록
     * 
     * @param from
     *            대상연월 시작 to 대상연월 종료 plantCd 사업장코드 processCd 공정코드 bizFieldCd 분야코드
     *            bizFieldItemNm 항목명 unregistered 미등록건 areaType 전사/사업장/부서 구분
     * @return MgtTgtItemEvalRslt 목표/실적/평가 목폭
     * @throws Exception
     *             예외
     */
    public List<MgtTgtItemEvalRslt> getMgtTargets(String from, String to, String plantCd, String bizFieldCd, String bizFieldItemNm, String unregistered, String areaType, String plantRoleYn, String deptRoleYn, String userId, String processCd, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTargets(from, to, plantCd, bizFieldCd, bizFieldItemNm, unregistered, areaType, plantRoleYn, deptRoleYn, userId, processCd, defaultParam);
    }

    /**
     * 목표 등록/수정
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return MgtTgt 목표 상세정보
     * @throws Exception
     *             예외
     */
    public MgtTgt getMgtTarget(String mgtTargetGrpNo, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTarget(mgtTargetGrpNo, defaultParam);
    }

    /**
     * 목표 계획 목록
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return MgtTgtItemPlanRslt 목표 계획 목록
     * @throws Exception
     *             예외
     */
    public List<MgtTgtItemPlanRslt> getMgtTargetItems(int mgtTargetGrpNo, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTargetItems(mgtTargetGrpNo, defaultParam);
    }

    /**
     * 목표 중복 체크
     * 
     * @param year
     *            대상연도 plantCd 사업장코드 processCd 공정코드
     * @return HashMap 중복데이터 목록
     * @throws Exception
     *             예외
     */
    public List<HashMap<String, Object>> checkMgtTarget(String year, String plantCd, String processCd, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.checkMgtTarget(year, plantCd, processCd, defaultParam);
    }

    public Integer getMgtTargetGrpSeq() throws Exception {
        return this.mgtTargetMapper.getMgtTargetGrpSeq();
    }

    /**
     * 목표상세, 목표계획 데이터 등록/수정
     * 
     * @param MgtTgt
     *            목표데이터 모델
     * @return Integer 목표그룹번호
     * @throws Exception
     *             예외
     */
    @Transactional
    public int createMgtTargetData(MgtTgt mgtTgt) throws Exception {
        int mgtTargetGrpNo = 0;

        if (mgtTgt.getMgtTargetGrpNo() > 0) {
            mgtTargetMapper.updateMgtTargetData(mgtTgt);
            mgtTargetGrpNo = mgtTgt.getMgtTargetGrpNo();

            if (mgtTgt.getPlanDataList() != null && mgtTgt.getPlanDataList().size() > 0) {
                for (MgtTgtItemPlanRslt mtipr : mgtTgt.getPlanDataList()) {
                    MgtTgtItemRslt mtir = new MgtTgtItemRslt();
                    for (int i = 1; i < 13; i++) {
                        mtir.setMgtTargetGrpNo(mgtTargetGrpNo);
                        if (i < 10) {
                            mtir.setMonth('0' + Integer.toString(i));
                        } else {
                            mtir.setMonth(Integer.toString(i));
                        }
                        mtir.setBizFieldCd(mtipr.getBizFieldCd());
                        mtir.setBizFieldNm(mtipr.getBizFieldNm());
                        mtir.setBizFieldItemNo(mtipr.getBizFieldItemNo());
                        mtir.setBizFieldItemNm(mtipr.getBizFieldItemNm());
                        mtir.setDecPlace(mtipr.getDecPlace());
                        mtir.setSortOrder(mtipr.getSortOrder());

                        switch (i) {
                        case 1:
                            mtir.setTargetVal(mtipr.getM1Val());
                            break;
                        case 2:
                            mtir.setTargetVal(mtipr.getM2Val());
                            break;
                        case 3:
                            mtir.setTargetVal(mtipr.getM3Val());
                            break;
                        case 4:
                            mtir.setTargetVal(mtipr.getM4Val());
                            break;
                        case 5:
                            mtir.setTargetVal(mtipr.getM5Val());
                            break;
                        case 6:
                            mtir.setTargetVal(mtipr.getM6Val());
                            break;
                        case 7:
                            mtir.setTargetVal(mtipr.getM7Val());
                            break;
                        case 8:
                            mtir.setTargetVal(mtipr.getM8Val());
                            break;
                        case 9:
                            mtir.setTargetVal(mtipr.getM9Val());
                            break;
                        case 10:
                            mtir.setTargetVal(mtipr.getM10Val());
                            break;
                        case 11:
                            mtir.setTargetVal(mtipr.getM11Val());
                            break;
                        case 12:
                            mtir.setTargetVal(mtipr.getM12Val());
                            break;
                        }

                        this.updateMgtTgtItemTarget(mtir);
                    }
                }
            }
        } else {
            mgtTgt.setMgtTargetGrpNo(this.getMgtTargetGrpSeq());
            mgtTargetMapper.createMgtTargetData(mgtTgt);
            mgtTargetGrpNo = mgtTgt.getMgtTargetGrpNo();

            if (mgtTgt.getPlanDataList() != null && mgtTgt.getPlanDataList().size() > 0) {
                for (MgtTgtItemPlanRslt mtipr : mgtTgt.getPlanDataList()) {
                    MgtTgtItemRslt mtir = new MgtTgtItemRslt();
                    for (int i = 1; i < 13; i++) {
                        mtir.setMgtTargetGrpNo(mgtTargetGrpNo);
                        if (i < 10) {
                            mtir.setMonth('0' + Integer.toString(i));
                        } else {
                            mtir.setMonth(Integer.toString(i));
                        }
                        mtir.setBizFieldCd(mtipr.getBizFieldCd());
                        mtir.setBizFieldNm(mtipr.getBizFieldNm());
                        mtir.setBizFieldItemNo(mtipr.getBizFieldItemNo());
                        mtir.setBizFieldItemNm(mtipr.getBizFieldItemNm());
                        mtir.setDecPlace(mtipr.getDecPlace());
                        mtir.setSortOrder(mtipr.getSortOrder());

                        switch (i) {
                        case 1:
                            mtir.setTargetVal(mtipr.getM1Val());
                            break;
                        case 2:
                            mtir.setTargetVal(mtipr.getM2Val());
                            break;
                        case 3:
                            mtir.setTargetVal(mtipr.getM3Val());
                            break;
                        case 4:
                            mtir.setTargetVal(mtipr.getM4Val());
                            break;
                        case 5:
                            mtir.setTargetVal(mtipr.getM5Val());
                            break;
                        case 6:
                            mtir.setTargetVal(mtipr.getM6Val());
                            break;
                        case 7:
                            mtir.setTargetVal(mtipr.getM7Val());
                            break;
                        case 8:
                            mtir.setTargetVal(mtipr.getM8Val());
                            break;
                        case 9:
                            mtir.setTargetVal(mtipr.getM9Val());
                            break;
                        case 10:
                            mtir.setTargetVal(mtipr.getM10Val());
                            break;
                        case 11:
                            mtir.setTargetVal(mtipr.getM11Val());
                            break;
                        case 12:
                            mtir.setTargetVal(mtipr.getM12Val());
                            break;
                        }

                        this.createMgtTgtItemTarget(mtir);
                    }
                }
            }
        }
        return mgtTargetGrpNo;
    }

    /**
     * 실적/평가 데이터 등록/수정
     * 
     * @param MgtTgt
     *            목표데이터 모델
     * @return Integer 목표월번호
     * @throws Exception
     */
    @Transactional
    public int createMgtTargetRsltData(MgtTgt mgtTgt) throws Exception {
        int mgtTargetMonthNo = 0;

        mgtTargetMonthNo = mgtTgt.getMgtTargetMonthNo();

        if (mgtTgt.getEvalDataList() != null && mgtTgt.getEvalDataList().size() > 0) {
            for (MgtTgtItemEvalRslt mtier : mgtTgt.getEvalDataList()) {
                MgtTgtItemRslt mtir = new MgtTgtItemRslt();

                mtir.setMgtTargetMonthNo(mgtTargetMonthNo);
                mtir.setBizFieldItemNo(mtier.getBizFieldItemNo());
                mtir.setRsltVal(mtier.getRsltVal());
                mtir.setEvalVal(mtier.getEvalVal());
                mtir.setCreateUserId(mgtTgt.getCreateUserId());

                if (mgtTgt.getRstepCd() != null && mgtTgt.getRstepCd().equals("BAPSG")) {
                    this.updateMgtTgtItemEval(mtir);
                } else {
                    this.updateMgtTgtItemRslt(mtir);
                }
            }
        }

        return mgtTargetMonthNo;
    }

    @Transactional
    public void createMgtTgtItemTarget(MgtTgtItemRslt mgtTgtItemRslt) throws Exception {
        this.mgtTargetMapper.createMgtTgtItemTarget(mgtTgtItemRslt);
    }

    @Transactional
    public void updateMgtTgtItemTarget(MgtTgtItemRslt mgtTgtItemRslt) throws Exception {
        this.mgtTargetMapper.updateMgtTgtItemTarget(mgtTgtItemRslt);
    }

    @Transactional
    public void updateMgtTgtItemRslt(MgtTgtItemRslt mgtTgtItemRslt) throws Exception {
        this.mgtTargetMapper.updateMgtTgtItemRslt(mgtTgtItemRslt);
    }

    @Transactional
    public void updateMgtTgtItemEval(MgtTgtItemRslt mgtTgtItemRslt) throws Exception {
        this.mgtTargetMapper.updateMgtTgtItemEval(mgtTgtItemRslt);
    }

    /**
     * 목표데이터 삭제
     * 
     * @param mgtTargetGrpNo
     *            목표그룹번호
     * @return Integer 0
     * @throws Exception
     *             예외
     */
    public int deleteMgtTargetData(String mgtTargetGrpNo) {
        this.mgtTargetMapper.deleteMgtTargetData(mgtTargetGrpNo);
        return 0;
    }

    /**
     * 실적/평가 데이터 삭제
     * 
     * @param mgtTargetMonthNo
     *            목표월번호 mgtTargetType 실적/평가 구분
     * @return Integer 0
     * @throws Exception
     *             예외
     */
    public int deleteMgtTargetMonthData(String mgtTargetMonthNo, String mgtTargetType) {
        this.mgtTargetMapper.deleteMgtTargetMonthData(mgtTargetMonthNo, mgtTargetType);
        return 0;
    }

    /**
     * 실적/평가 상세 데이터
     * 
     * @param mgtTargetMonthNo
     *            목표월번호
     * @return MgtTgt 목표데이터 모델
     * @throws Exception
     *             예외
     */
    public MgtTgt getMgtTargetRslt(String mgtTargetMonthNo, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTargetRslt(mgtTargetMonthNo, defaultParam);
    }

    /**
     * 실적/평가 목록
     * 
     * @param mgtTargetMonthNo
     *            목표월번호
     * @return MgtTgtItemEvalRslt 실적/평가 목록 모델
     * @throws Exception
     *             예외
     */
    public List<MgtTgtItemEvalRslt> getMgtTargetRslts(int mgtTargetMonthNo, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTargetRslts(mgtTargetMonthNo, defaultParam);
    }

    public int apprProcessMgtTarget(String mgtTargetNo, String apprBizCd, String bizApprStepCd, int apprRqstNo) throws Exception {
        int result = 0;
        if (apprBizCd.equals("MG-MI-06")) {
            // SHE 목표
            result += this.mgtTargetMapper.updateMgtTargetTargetAppr(mgtTargetNo, apprRqstNo);
            if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
                // 반려시 상태 변경-계획 상태
                result += this.mgtTargetMapper.updateMgtTargetTargetStatus(mgtTargetNo, bizApprStepCd);
            } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
                // 승인시 상태 변경-결과 상태
                result += this.mgtTargetMapper.updateMgtTargetTargetStatus(mgtTargetNo, bizApprStepCd);
            }
        } else if (apprBizCd.equals("MG-MI-07")) {
            // SHE 실적
            result += this.mgtTargetMapper.updateMgtTargetRsltAppr(mgtTargetNo, apprRqstNo);
            if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
                // 반려시 상태 변경-결과 상태
                result += this.mgtTargetMapper.updateMgtTargetRsltStatus(mgtTargetNo, bizApprStepCd);
            } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
                // 등록된 개선 건 상태 변경
                result += this.mgtTargetMapper.updateMgtTargetRsltStatus(mgtTargetNo, bizApprStepCd);
            }
        } else if (apprBizCd.equals("MG-MI-08")) {
            // SHE 평가
            result += this.mgtTargetMapper.updateMgtTargetEvalAppr(mgtTargetNo, apprRqstNo);
            if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
                // 반려시 상태 변경-결과 상태
                result += this.mgtTargetMapper.updateMgtTargetEvalStatus(mgtTargetNo, bizApprStepCd);
            } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
                // 등록된 개선 건 상태 변경
                result += this.mgtTargetMapper.updateMgtTargetEvalStatus(mgtTargetNo, bizApprStepCd);
            }
        }

        return result;
    }

    /**
     * SHE목표달성 현황 목록
     * 
     * @param from
     *            대상연월 시작 to 대상연월 종료 plantCd 사업장코드 processCd 공정코드 bizFieldCd 분야코드
     *            bizFieldItemNm 항목명 areaType 전사/사업장/부서 구분
     * @return MgtTgtStatus SHE목표달성 현황 목폭
     * @throws Exception
     *             예외
     */
    public List<MgtTgtStatus> getMgtTargetStatus(String from, String to, String plantCd, String processCd, String bizFieldCd, String bizFieldItemNm, String areaType, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTargetStatus(from, to, plantCd, processCd, bizFieldCd, bizFieldItemNm, areaType, defaultParam);
    }
    
    public List<MgtTgtStatusGraph> getMgtTargetStatusGraph(String from, String to, String plantCd, String processCd, String bizFieldCd, String bizFieldItemNo, DefaultParam defaultParam) throws Exception {
        return mgtTargetMapper.getMgtTargetStatusGraph(from, to, plantCd, processCd, bizFieldCd, bizFieldItemNo, defaultParam);
    }
}
