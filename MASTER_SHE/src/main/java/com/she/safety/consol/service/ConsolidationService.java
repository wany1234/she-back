package com.she.safety.consol.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.safety.consol.mapper.ConsolidationMapper;
import com.she.safety.consol.mapper.ConsolidationPlanMapper;
import com.she.safety.consol.model.ConsolInspDept;
import com.she.safety.consol.model.Consolidation;
import com.she.utils.ConstVal;

@Service
public class ConsolidationService {

    @Autowired
    private ConsolidationMapper mapper;

    @Autowired
    private ConsolidationPlanMapper planMapper;

    public List<Consolidation> getConsolMsts(String startDate, String endDate, String deptCd, String plantCd, String stepCd, int safCheckKindNo) throws Exception {
        return mapper.getConsolMsts(startDate, endDate, deptCd, plantCd, stepCd, safCheckKindNo);
    }

    public List<Consolidation> getConsolMst(int safCongChkPlanNo) throws Exception {
        List<Consolidation> master = mapper.getConsolMst(safCongChkPlanNo);
        return master;
    }

    public List<ConsolInspDept> getConsolDept(int safCongChkPlanNo) throws Exception {
        List<ConsolInspDept> master = mapper.getConsolDept(safCongChkPlanNo);
        return master;
    }

    /**
     * 합동점검계획 등록
     * 
     * @param patrolMaster
     * @return
     * @throws Exception
     */
    @Transactional
    public int createConsolMst(Consolidation consolidation) throws Exception {
        if (consolidation == null) {
            return 0;
        } else {
            // 합동점검 마스터 등록
            consolidation.setSafCongChkPlanNo(this.getPlanSequenceNumber());
            // 부서및협력업체 등록
            if (CollectionUtils.isNotEmpty(consolidation.getVendorDeptList())) {
                for (int i = 0; i < consolidation.getVendorDeptList().size(); i++) {
                    ConsolInspDept consol = consolidation.getVendorDeptList().get(i);
                    consolidation.setSafCongChkRsltNo(this.getResultSequenceNumber());
                    consolidation.setCheckStepCd(ConstVal.SAF_CHK_STEP_SCHEDULE_CD); // 점검진행상태(일정)
                                                                                     // ->
                                                                                     // 일정수립
                    consolidation.setChkTitle(consol.getChkTitle());
                    consolidation.setCongChkSchYmd(consol.getCongChkSchYmd());
                    mapper.createConsolMst(consolidation);

                    if (CollectionUtils.isNotEmpty(consolidation.getDeptList())) {
                        for (ConsolInspDept list : consolidation.getDeptList()) {
                            list.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                            list.setSafChkInspDeptNo(this.getInspSequenceNumber());
                            mapper.createInspDept(list);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(consolidation.getVendorList())) {
                        for (ConsolInspDept list : consolidation.getVendorList()) {
                            list.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                            list.setSafChkInspDeptNo(this.getInspSequenceNumber());
                            mapper.createInspDept(list);
                        }
                    }
                }

            }
        }
        return consolidation.getSafCongChkPlanNo();
    }

    public int getResultSequenceNumber() throws Exception {
        return mapper.getResultSequenceNumber();
    }

    public int getPlanSequenceNumber() throws Exception {
        return mapper.getPlanSequenceNumber();
    }

    public int getInspSequenceNumber() throws Exception {
        return mapper.getInspSequenceNumber();
    }

    // 계획결재
    public int apprConsolPlanByYear(int safCongChkPlanNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String stepCd = "";
        // 결재완료
        List<Consolidation> consolidation = mapper.getConsolMst(safCongChkPlanNo);

        stepCd = consolidation.get(0).getCheckStepCd();
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) { // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            stepCd = ConstVal.SAF_CHK_STEP_PLAN_CD; // 일정수립 > 계획
        }
        // 결재 진행단계 수정
        resultNo += mapper.apprConsolPlanByYear(safCongChkPlanNo, stepCd, apprRqstNo);
        return resultNo;
    }

    // 결과결재
    public int apprConsolResultByYear(int safCongChkRsltNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String stepCd = "";
        // 결재완료

        Consolidation consolidation = planMapper.getConsolPlan(safCongChkRsltNo);

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) { // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            stepCd = "CHS04"; // 점검완료

        }
        resultNo += mapper.apprConsolResultByYear(safCongChkRsltNo, stepCd, apprRqstNo);

        int count = mapper.countConsol(consolidation.getSafCongChkPlanNo(), "CHS04");
        int count2 = mapper.countConsol(consolidation.getSafCongChkPlanNo(), "9999");

        if (count >= count2) { // 진행중에서 > 완료로
            mapper.completeResult(consolidation.getSafCongChkPlanNo());
        }
        // 결재 진행단계 수정
        return resultNo;
    }

    /**
     * 안전점검일정 수정
     * 
     * @param patrolMaster
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateConsolMst(Consolidation consolidation) throws Exception {
        int result = 0;
        if (consolidation == null) {
            return 0;
        } else {
            // 합동점검 마스터 등록
            for (ConsolInspDept list : consolidation.getVendorDeptList()) {
                this.deleteConsolMst(list.getSafCongChkRsltNo(), 0);
            }
            this.deleteConsolMst(0, consolidation.getSafCongChkPlanNo());
            consolidation.setSafCongChkPlanNo(this.getPlanSequenceNumber());
            // 부서및협력업체 등록
            if (CollectionUtils.isNotEmpty(consolidation.getVendorDeptList())) {
                for (int i = 0; i < consolidation.getVendorDeptList().size(); i++) {
                    ConsolInspDept consol = consolidation.getVendorDeptList().get(i);
                    consolidation.setSafCongChkRsltNo(this.getResultSequenceNumber());
                    consolidation.setCheckStepCd(ConstVal.SAF_CHK_STEP_SCHEDULE_CD); // 점검진행상태(일정)
                                                                                     // ->
                                                                                     // 일정수립
                    consolidation.setCongChkSchYmd(consol.getCongChkSchYmd());
                    consolidation.setChkTitle(consol.getChkTitle());
                    mapper.createConsolMst(consolidation);

                    if (CollectionUtils.isNotEmpty(consolidation.getDeptList())) {
                        for (ConsolInspDept list : consolidation.getDeptList()) {
                            list.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                            list.setSafChkInspDeptNo(this.getInspSequenceNumber());
                            mapper.createInspDept(list);
                        }
                    }
                    if (CollectionUtils.isNotEmpty(consolidation.getVendorList())) {
                        for (ConsolInspDept list : consolidation.getVendorList()) {
                            list.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                            list.setSafChkInspDeptNo(this.getInspSequenceNumber());
                            mapper.createInspDept(list);
                        }
                    }
                }

            }
        }
        return consolidation.getSafCongChkPlanNo();
    }

    public int completeConsolMst(int safCongChkRsltNo) throws Exception {
        return mapper.completeConsolMst(safCongChkRsltNo);
    }

    public int deleteConsolMst(int safCongChkRsltNo, int safCongChkPlanNo) throws Exception {
        return mapper.deleteConsolMst(safCongChkRsltNo, safCongChkPlanNo);
    }
}
