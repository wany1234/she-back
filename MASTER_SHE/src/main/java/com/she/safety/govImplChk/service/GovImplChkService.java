package com.she.safety.govImplChk.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.safety.govImplChk.mapper.GovImplChkMapper;
import com.she.safety.model.GovImplChkExRater;
import com.she.safety.model.GovImplChkInRater;
import com.she.safety.model.GovImplChkPlan;
import com.she.utils.ConstVal;

@Service
public class GovImplChkService {
    @Autowired
    private GovImplChkMapper govImplChkMapper;

    @Autowired
    private ImprService imprService;

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
    public List<GovImplChkPlan> getGovImplChkResults(String plantCd, String year, String regRegdem, String procStepCd, String stateCd, String chkNm, String mainDeptCd, String mainDeptSubYn, String targetDeptCd, String targetDeptSubYn, String refGovNm, String overdueYn, int month, String tgtDeptYn, String statusYn, DefaultParam defaultParam)
            throws Exception {
        return govImplChkMapper.getGovImplChkResults(plantCd, year, regRegdem, procStepCd, stateCd, chkNm, mainDeptCd, mainDeptSubYn, targetDeptCd, targetDeptSubYn, refGovNm, overdueYn, month, tgtDeptYn, statusYn, defaultParam);
    }

    /**
     * 정부지자체 시정조치 이행점검 관리 상세 조회
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    public GovImplChkPlan getGovImplChkResult(int implChkPlanNo) throws Exception {
        GovImplChkPlan govImplChkPlan = govImplChkMapper.getGovImplChkPlan(implChkPlanNo);
        govImplChkPlan.setGovImplChkInRaters(govImplChkMapper.getGovImplChkInRaters(implChkPlanNo));
        govImplChkPlan.setGovImplChkExRaters(govImplChkMapper.getGovImplChkExRaters(implChkPlanNo));
        govImplChkPlan.setGovImplChkRslt(govImplChkMapper.getGovImplChkRslt(implChkPlanNo));

        return govImplChkPlan;
    }

    /**
     * 정부지자체 시정조치 이행점검 관리 저장
     * 
     * @param govImplChkPlan
     *            정부지자체 시정조치 이행점검
     * @return
     * @throws Exception
     */
    @Transactional
    public GovImplChkPlan saveGovImplChkResult(GovImplChkPlan govImplChkPlan) throws Exception {
        if (govImplChkPlan.getImplChkPlanNo() == 0) {

            govImplChkMapper.createGovImplChkPlan(govImplChkPlan);
        } else {
            govImplChkMapper.updateGovImplChkPlan(govImplChkPlan);
        }

        if (CollectionUtils.isNotEmpty(govImplChkPlan.getGovImplChkInRaters())) {
            for (GovImplChkInRater govImplChkInRater : govImplChkPlan.getGovImplChkInRaters()) {
                govImplChkInRater.setImplChkPlanNo(govImplChkPlan.getImplChkPlanNo());
                govImplChkMapper.saveGovImplChkInRater(govImplChkInRater);
            }
        }

        if (CollectionUtils.isNotEmpty(govImplChkPlan.getGovImplChkExRaters())) {
            for (GovImplChkExRater govImplChkExRater : govImplChkPlan.getGovImplChkExRaters()) {
                govImplChkExRater.setImplChkPlanNo(govImplChkPlan.getImplChkPlanNo());
                govImplChkMapper.saveGovImplChkExRater(govImplChkExRater);
            }
        }

        govImplChkPlan.getGovImplChkRslt().setImplChkPlanNo(govImplChkPlan.getImplChkPlanNo());
        govImplChkMapper.saveGovImplChkRslt(govImplChkPlan.getGovImplChkRslt());

        return govImplChkPlan;
    }

    /**
     * 정부지자체 시정조치 이행점검 관리 삭제
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    @Transactional
    public int deleteGovImplChkResult(int implChkPlanNo) throws Exception {
        int resultNo = 0;

        resultNo += govImplChkMapper.deleteGovImplChkPlan(implChkPlanNo);
        resultNo += govImplChkMapper.deleteGovImplChkInRater(implChkPlanNo);
        resultNo += govImplChkMapper.deleteGovImplChkExRater(implChkPlanNo);
        resultNo += govImplChkMapper.deleteGovImplChkRslt(implChkPlanNo);

        return resultNo;
    }

    /**
     * 정부지자체 시정조치 이행점검 결재상태 업데이트
     * 
     * @param implChkPlanNo
     *            정부지자체 시정조치 이행점검 번호
     * @return
     * @throws Exception
     */
    public int updateAppr(int implChkPlanNo, String bizApprStepCd, int apprRqstNo, String apprUserId) throws Exception {
        GovImplChkPlan govImplChkPlan = this.getGovImplChkResult(implChkPlanNo);
        String stateCd = "";

        if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            // 결재완료일 경우
            stateCd = ConstVal.COM_STATE_COMPLETE;
            imprService.updateImprStepCd("ICL39", implChkPlanNo, apprUserId);
        } else if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
            // 반려일 경우
            stateCd = ConstVal.COM_STATE_ING;
        } else {
            stateCd = ConstVal.COM_STATE_APPR;
        }

        govImplChkPlan.setStateCd(stateCd);
        govImplChkPlan.setStepCd(bizApprStepCd);
        govImplChkPlan.setApprRqstNo(apprRqstNo);

        return govImplChkMapper.updateAppr(govImplChkPlan);
    }

    /**
     * 정부지자체 시정조치 이행점검결과 현황
     * 
     * @param plantCd
     *            사업장
     * @param year
     *            대상년도
     * @param regRedgdem
     *            구분
     * @return
     * @throws Exception
     */
    public List<HashMap<String, Object>> getGovImplChkStatus(String plantCd, String year, String regRegdem, String totalFlag, DefaultParam defaultParam) throws Exception {
        return govImplChkMapper.getGovImplChkStatus(plantCd, year, regRegdem, totalFlag, defaultParam);
    }
}
