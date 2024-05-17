package com.she.mgt.elect.service;

import com.she.common.model.DefaultParam;
import com.she.mgt.elect.mapper.ElectEvalPlanMapper;
import com.she.mgt.model.ElectEvalItem;
import com.she.mgt.model.ElectEvalPlan;
import com.she.mgt.model.ElectEvalUser;
import com.she.utils.ConstVal;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ElectEvalPlanService {
    @Autowired
    private ElectEvalPlanMapper electEvalPlanMapper;

    /**
     * 평가대상 법정선임자 목록 조회
     * 
     * @param safElectTitlNo
     *            법정선임자 번호
     * @return 평가대상 법정선임자 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getSafElectTitlEvalTarget(int safElectTitlNo, String plantCd, DefaultParam defaultParam) throws Exception {
        return electEvalPlanMapper.getSafElectTitlEvalTarget(safElectTitlNo, plantCd, defaultParam);
    }

    /**
     * 법정선임자 평가계획 목록 조회
     * 
     * @param startYear
     *            시작년도
     * @param endYear
     *            종료년도
     * @param halfTypeCd
     *            구분코드
     * @param deptCd
     *            주관부서코드
     * @param deptSubYn
     *            하위부서 조회여부
     * @param safElectTitlNo
     *            법정선임자번호
     * @param evalNm
     *            평가명
     * @param procStepCd
     *            단계코드
     * @param stateCd
     *            상태코드
     * @param evalIncompleteYn
     *            평가미완료여부
     * @param evalStepCd
     *            평가미완료시 조회할 상태코드
     * @param defaultParam
     * @return 법정선임자 평가계획 목록
     * @throws Exception
     */
    public List<ElectEvalPlan> getElectEvalPlans(String startYear, String endYear, String halfTypeCd, String deptCd, String deptSubYn, int safElectTitlNo, String evalNm, String procStepCd, String stateCd, String evalIncompleteYn, String evalStepCd, DefaultParam defaultParam) throws Exception {
        return electEvalPlanMapper.getElectEvalPlans(startYear, endYear, halfTypeCd, deptCd, deptSubYn, safElectTitlNo, evalNm, procStepCd, stateCd, evalIncompleteYn, evalStepCd, defaultParam);
    }

    /**
     * 법정선임자 평가계획 상세 조회
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 법정선임자 평가계획 상세
     * @throws Exception
     */
    public ElectEvalPlan getElectEvalPlan(int evalPlanNo, DefaultParam defaultParam) throws Exception {
        ElectEvalPlan electEvalPlan = electEvalPlanMapper.getElectEvalPlan(evalPlanNo);
        electEvalPlan.setElectEvalUsers(this.getElectEvalUsers(evalPlanNo, defaultParam));
        electEvalPlan.setElectEvalItems(this.getElectEvalItems(evalPlanNo, 0));
        return electEvalPlan;
    }

    /**
     * 법정선임자 평가계획 신규 등록
     * 
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 법정선임자 평가계획
     * @throws Exception
     */
    @Transactional
    public ElectEvalPlan createElectEvalPlan(ElectEvalPlan electEvalPlan) throws Exception {
        electEvalPlanMapper.createElectEvalPlan(electEvalPlan);

        if (CollectionUtils.isNotEmpty(electEvalPlan.getElectEvalUsers())) {
            for (ElectEvalUser electEvalUser : electEvalPlan.getElectEvalUsers()) {
                electEvalUser.setEvalPlanNo(electEvalPlan.getEvalPlanNo());
                electEvalPlanMapper.saveElectEvalUser(electEvalUser);
            }
        }

        if (CollectionUtils.isNotEmpty(electEvalPlan.getElectEvalItems())) {
            for (ElectEvalItem electEvalItem : electEvalPlan.getElectEvalItems()) {
                electEvalItem.setEvalPlanNo(electEvalPlan.getEvalPlanNo());
                electEvalPlanMapper.createElectEvalItem(electEvalItem);
            }
        }

        return electEvalPlan;
    }

    /**
     * 법정선임자 평가계획 수정
     * 
     * @param electEvalPlan
     *            법정선임자 평가계획
     * @return 법정선임자 평가계획
     * @throws Exception
     */
    @Transactional
    public ElectEvalPlan updateElectEvalPlan(ElectEvalPlan electEvalPlan) throws Exception {
        electEvalPlanMapper.updateElectEvalPlan(electEvalPlan);

        if (CollectionUtils.isNotEmpty(electEvalPlan.getElectEvalUsers())) {
            for (ElectEvalUser electEvalUser : electEvalPlan.getElectEvalUsers()) {
                electEvalUser.setEvalPlanNo(electEvalPlan.getEvalPlanNo());
                electEvalPlanMapper.saveElectEvalUser(electEvalUser);
            }
        }

        if (CollectionUtils.isNotEmpty(electEvalPlan.getDeleteElectEvalUsers())) {
            for (ElectEvalUser electEvalUser : electEvalPlan.getDeleteElectEvalUsers()) {
                electEvalUser.setEvalPlanNo(electEvalPlan.getEvalPlanNo());
                electEvalPlanMapper.deleteElectEvalUser(electEvalUser.getEvalPlanNo(), electEvalUser.getEvalUserNo());
            }
        }

        electEvalPlanMapper.deleteElectEvalItem(electEvalPlan.getEvalPlanNo());
        if (CollectionUtils.isNotEmpty(electEvalPlan.getElectEvalItems())) {
            for (ElectEvalItem electEvalItem : electEvalPlan.getElectEvalItems()) {
                electEvalItem.setEvalPlanNo(electEvalPlan.getEvalPlanNo());
                electEvalPlanMapper.createElectEvalItem(electEvalItem);
            }
        }

        return electEvalPlan;
    }

    /**
     * 법정선임자 평가계획 삭제
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public int deleteElectEvalPlan(int evalPlanNo) throws Exception {
        int resultNo = 0;
        resultNo += electEvalPlanMapper.deleteElectEvalUser(evalPlanNo, 0); // 평가대상자
                                                                            // 삭제
        resultNo += electEvalPlanMapper.deleteElectEvalItem(evalPlanNo); // 평가항목
                                                                         // 삭제
        resultNo += electEvalPlanMapper.deleteElectEvalPlan(evalPlanNo); // 평가계획
                                                                         // 삭제
        return resultNo;
    }

    /**
     * 법정선임지 평가계획 평가대상자 삭제
     * 
     * @param evalPlanNo
     * @return
     * @throws Exception
     */
    public int deleteElectEvalUser(int evalPlanNo) throws Exception {
        int resultNo = 0;
        resultNo += electEvalPlanMapper.deleteElectEvalUser(evalPlanNo, 0); // 평가대상자

        return resultNo;
    }

    /**
     * 법정선임자 평가계획 평가대상자 목록 조회
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @return 법정선임자 평가계획 평가대상자 목록
     * @throws Exception
     */
    public List<ElectEvalUser> getElectEvalUsers(int evalPlanNo, DefaultParam defaultParam) throws Exception {
        return electEvalPlanMapper.getElectEvalUsers(evalPlanNo, defaultParam);
    }

    /**
     * 법정선임자 평가계획 평가항목 목록 조회
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @param safElectTitlNo
     *            법정선임자 번호
     * @return 법정선임자 평가계획 평가항목 목록
     * @throws Exception
     */
    public List<ElectEvalItem> getElectEvalItems(int evalPlanNo, int safElectTitlNo) throws Exception {
        List<ElectEvalItem> electEvalItems = null;

        if (evalPlanNo > 0) {
            electEvalItems = electEvalPlanMapper.getElectEvalItems(evalPlanNo);
        } else {
            electEvalItems = electEvalPlanMapper.getElectEvalItemsInit(safElectTitlNo);
        }

        return electEvalItems;
    }

    /**
     * 법정선임자 평가계획 결재정보 업데이트
     * 
     * @param evalPlanNo
     *            법정선임자 평가계획 번호
     * @param bizApprStepCd
     *            결재상태
     * @param apprRqstNo
     *            결재요청번호
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public int updateAppr(String evalPlanNos, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;

        for (String evalPlanNo : evalPlanNos.split(",")) {
            ElectEvalPlan electEvalPlan = this.getElectEvalPlan(Integer.parseInt(evalPlanNo), new DefaultParam("kr"));

            if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_ING)) {
                // 결재중
                electEvalPlan.setStateCd(ConstVal.COM_STATE_APPR);
            } else if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
                // 반려
                electEvalPlan.setStateCd(ConstVal.COM_STATE_ING);
            } else if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
                // 결재완료
                // 상태는 결재완료/단계는 본인평가로 업데이트
                electEvalPlan.setStateCd(ConstVal.COM_STATE_COMPLETE);
                electEvalPlan.setProcStepCd(ConstVal.MGT_ELECT_EVAL_STEP_ME_RSLT);
            }

            electEvalPlan.setApprRqstNo(apprRqstNo); // 결재요청번호
            electEvalPlan.setStepCd(bizApprStepCd); // 결재상태코드

            resultNo += electEvalPlanMapper.updateAppr(electEvalPlan);
        }

        return resultNo;
    }
}
