/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.safety.change.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.safety.change.mapper.ChangeMapper;
import com.she.safety.model.Change;
import com.she.safety.model.ChangeCheckItemResult;
import com.she.safety.model.ChangeCommi;
import com.she.safety.model.ChangeCommiPsn;
import com.she.safety.model.ChangeDashboard;
import com.she.safety.model.ChangeDashboardBase;
import com.she.safety.model.ChangeDashboardStep1;
import com.she.safety.model.ChangeDashboardStep2;
import com.she.safety.model.ChangeDashboardStep3;
import com.she.safety.model.ChangeDashboardStepA;
import com.she.safety.model.ChangeEffect;
import com.she.safety.model.ChangeElectLaw;
import com.she.safety.model.ChangeElectResult;
import com.she.safety.model.ChangeOperation;
import com.she.safety.model.ChangeRefWork;
import com.she.safety.model.ChangeRefWorkView;
import com.she.safety.model.ChangeType;
import com.she.utils.ConstVal;

@Service
public class ChangeService {
    @Autowired
    private ChangeMapper changeMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 변경관리 조회
     *
     * @return 변경관리 목록
     * @throws Exception
     */
    public List<Change> getChanges(String plantCd, String rqstStartDt, String rqstEndDt, String rqstDeptCd, String rqstDeptSubYn, String chngStepCd, String chngAttCd, String bizNm, String chngRefWorkCd, DefaultParam defaultParam) throws Exception {
        return changeMapper.getChanges(plantCd, rqstStartDt, rqstEndDt, rqstDeptCd, rqstDeptSubYn, chngStepCd, chngAttCd, bizNm, chngRefWorkCd, defaultParam);
    }

    /**
     * 변경관리 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리
     * @throws Exception
     */
    public Change getChange(int safChngNo, DefaultParam defaultParam) throws Exception {
        Change change = this.changeMapper.getChange(safChngNo); // 변경관리
        change.setChangeTypes(this.changeMapper.getChangeTypes(safChngNo, defaultParam)); // 분류
        change.setChangeOperations(this.changeMapper.getChangeOperations(safChngNo, defaultParam)); // 분류
        change.setChangeEffects(this.changeMapper.getChangeEffects(safChngNo, defaultParam)); // 기대효과
        change.setChangeCheckItemResults(this.changeMapper.getChangeCheckItemResult(safChngNo, "default", defaultParam)); // 관련업무
        change.setChangeRefWorks(this.getChangeRefWorks(safChngNo, null, defaultParam)); // 진행관리
        change.setChangeElectLaws(this.getChangeElectResults(safChngNo, defaultParam)); // 인허가검토
        return change;
    }

    /**
     * 변경관리 default 조회
     *
     * @return 변경관리 default
     * @throws Exception
     */
    public Change getDefaultChange(DefaultParam defaultParam) throws Exception {
        Change change = new Change();
        change.setChangeElectLaws(this.getChangeElectResults(0, defaultParam)); // 인허가검토
        return change;
    }

    /**
     * 변경관리 신규등록
     *
     * @param change
     *            변경관리
     * @return 변경관리번호
     * @throws Exception
     */
    @Transactional
    public HashMap<String, Object> createChange(Change change) throws Exception {
        // 변경관리 신규등록
        change.setChngStepCd(ConstVal.SAF_CHNG_STEP_CGSP1);
        this.changeMapper.createChange(change);
        // 변경관리구분내역 신규등록
        if (change.getChangeTypes() != null && change.getChangeTypes().size() > 0) {
            for (ChangeType changeType : change.getChangeTypes()) {
                changeType.setSafChngNo(change.getSafChngNo());
                this.changeMapper.createChangeType(changeType);
            }
        }
        // 변경관리 정비운전 신규등록
        if (change.getChangeOperations() != null && change.getChangeOperations().size() > 0) {
            for (ChangeOperation changeOperation : change.getChangeOperations()) {
                changeOperation.setSafChngNo(change.getSafChngNo());
                this.changeMapper.createChangeOperation(changeOperation);
            }
        }
        // 변경 후 기대효과 신규등록
        if (change.getChangeEffects() != null && change.getChangeEffects().size() > 0) {
            for (ChangeEffect changeEffect : change.getChangeEffects()) {
                changeEffect.setSafChngNo(change.getSafChngNo());
                this.changeMapper.createChangeEffect(changeEffect);
            }
        }

        // 관련업무 저장
        List<ChangeCheckItemResult> changeCheckItemResults = change.getChangeCheckItemResults();
        if (changeCheckItemResults != null) {
            if (changeCheckItemResults.size() > 0) {
                for (ChangeCheckItemResult changeCheckItemResult : changeCheckItemResults) {
                    changeCheckItemResult.setSafChngNo(change.getSafChngNo());
                    changeCheckItemResult.setCreateUserId(change.getCreateUserId());
                    this.changeMapper.createChangeCheckItemResult(changeCheckItemResult);
                }

            }
        }

        // 진행관리 저장
        List<ChangeRefWork> changeRefWorks = change.getChangeRefWorks();
        if (changeRefWorks != null) {
            if (changeRefWorks.size() > 0) {
                // 진행관리 등록
                for (ChangeRefWork changeRefWork : changeRefWorks) {
                    changeRefWork.setSafChngNo(change.getSafChngNo());
                    changeRefWork.setCreateUserId(change.getCreateUserId());
                    // this.changeMapper.createChangeRefWork(changeRefWork);
                    this.taskChange(changeRefWork);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(change.getChangeElectLaws())) {
            for (ChangeElectLaw changeElectLaw : change.getChangeElectLaws()) {
                changeElectLaw.setSafChngNo(change.getSafChngNo());
                changeElectLaw.setCreateUserId(change.getCreateUserId());
                this.changeMapper.createChangeElectLaw(changeElectLaw);

                if (CollectionUtils.isNotEmpty(changeElectLaw.getChangeElectResults())) {
                    for (int safElectTitlNo : changeElectLaw.getCheckChangeElectResults()) {
                        ChangeElectResult changeElectResult = new ChangeElectResult();
                        changeElectResult.setSafElectTitlNo(safElectTitlNo);
                        changeElectResult.setSafChngElectLawResultNo(changeElectLaw.getSafChngElectLawResultNo());
                        this.changeMapper.createChangeElectResult(changeElectResult);
                    }
                }
            }
        }

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("safChngNo", change.getSafChngNo());
        data.put("chngNum", change.getChngNum());
        data.put("chngStepCd", "CGSP1");

        return data;
    }

    /**
     * 변경관리 수정
     *
     * @param change
     *            변경관리
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public HashMap<String, Object> updateChange(Change change) throws Exception {
        HashMap<String, Object> data = new HashMap<String, Object>();
        // 승인을 하는 경우 ** 해당 경우 사용하지 않음, 진행상태가 변경되는 경우는 결재를 받음
        // if ("Y".equals(change.getIsComplete())) {
        // if (ConstVal.SAF_CHNG_STEP_CGSP1.equals(change.getChngStepCd())) {
        // change.setChngStepCd(ConstVal.SAF_CHNG_STEP_CGSPA);
        // } else if
        // (ConstVal.SAF_CHNG_STEP_CGSPA.equals(change.getChngStepCd())) {
        // change.setChngStepCd(ConstVal.SAF_CHNG_STEP_CGSP2);
        // } else if
        // (ConstVal.SAF_CHNG_STEP_CGSP2.equals(change.getChngStepCd())) {
        // change.setChngStepCd(ConstVal.SAF_CHNG_STEP_CGSP3);
        // } else if
        // (ConstVal.SAF_CHNG_STEP_CGSP3.equals(change.getChngStepCd())) {
        // change.setChngStepCd(ConstVal.SAF_CHNG_STEP_CGSP4);
        // }
        // }

        this.changeMapper.updateChange(change);

        // 변경관리구분내역 저장
        if (change.getChangeTypes() != null) {
            this.changeMapper.deleteChangeType(change.getSafChngNo());
            if (change.getChangeTypes().size() > 0) {
                for (ChangeType changeType : change.getChangeTypes()) {
                    changeType.setSafChngNo(change.getSafChngNo());
                    this.changeMapper.createChangeType(changeType);
                }
            }
        }
        // 변경관리 정비운전 저장
        if (change.getChangeOperations() != null) {
            this.changeMapper.deleteChangeOperation(change.getSafChngNo());
            if (change.getChangeOperations().size() > 0) {
                for (ChangeOperation changeOperation : change.getChangeOperations()) {
                    changeOperation.setSafChngNo(change.getSafChngNo());
                    this.changeMapper.createChangeOperation(changeOperation);
                }
            }
        }
        // 변경 후 기대효과 저장
        if (change.getChangeEffects() != null) {
            this.changeMapper.deleteChangeEffect(change.getSafChngNo());
            if (change.getChangeEffects().size() > 0) {
                for (ChangeEffect changeEffect : change.getChangeEffects()) {
                    changeEffect.setSafChngNo(change.getSafChngNo());
                    this.changeMapper.createChangeEffect(changeEffect);
                }
            }
        }

        // 변경관리위원회 저장
        ChangeCommi changeCommi = change.getChangeCommi();
        if (changeCommi != null && changeCommi.getCommiDt() != null && !"".equals(changeCommi.getCommiDt())) {
            // 신규
            if (changeCommi.getSafChngCommiNo() == 0) {
                changeCommi.setSafChngNo(change.getSafChngNo());
                changeCommi.setCreateUserId(change.getCreateUserId());
                this.changeMapper.createChangeCommi(changeCommi);
            } else {
                // 수정
                changeCommi.setUpdateUserId(change.getUpdateUserId());
                this.changeMapper.updateChangeCommi(changeCommi);

            }

            this.changeMapper.deleteChangeCommiPsn(changeCommi.getSafChngCommiNo(), "");
            // 변경관리위원회 참석자 등록
            if (changeCommi.getChangeCommiPsns() != null && changeCommi.getChangeCommiPsns().size() > 0) {
                for (ChangeCommiPsn changeCommiPsn : changeCommi.getChangeCommiPsns()) {
                    changeCommiPsn.setSafChngCommiNo(changeCommi.getSafChngCommiNo());
                    this.changeMapper.createChangeCommiPsn(changeCommiPsn);
                }
            }
        }

        // 관련업무 저장
        List<ChangeCheckItemResult> changeCheckItemResults = change.getChangeCheckItemResults();
        if (changeCheckItemResults != null) {
            // this.changeMapper.deleteChangeCheckItemResult(change.getSafChngNo());
            if (changeCheckItemResults.size() > 0) {
                for (ChangeCheckItemResult changeCheckItemResult : changeCheckItemResults) {
                    changeCheckItemResult.setSafChngNo(change.getSafChngNo());
                    // 관련업무 수정
                    if (changeCheckItemResult.getSafChngChkItmRsltNo() > 0) {
                        changeCheckItemResult.setUpdateUserId(change.getUpdateUserId());
                        this.changeMapper.updateChangeCheckItemResult(changeCheckItemResult);
                    } else {
                        changeCheckItemResult.setCreateUserId(change.getCreateUserId());
                        this.changeMapper.createChangeCheckItemResult(changeCheckItemResult);
                    }
                }

            }
        }

        // 진행관리 저장
        List<ChangeRefWork> changeRefWorks = change.getChangeRefWorks();
        if (changeRefWorks != null) {
            this.changeMapper.deleteChangeRefWork(change.getSafChngNo());
            if (changeRefWorks.size() > 0) {
                // 진행관리 등록
                for (ChangeRefWork changeRefWork : changeRefWorks) {
                    changeRefWork.setSafChngNo(change.getSafChngNo());
                    changeRefWork.setCreateUserId(change.getCreateUserId());
                    // // 신규등록 ** 구조상 업데이트가 이루어질 수가 없음
                    // this.changeMapper.createChangeRefWork(changeRefWork);
                    this.taskChange(changeRefWork);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(change.getChangeElectLaws())) {
            for (ChangeElectLaw changeElectLaw : change.getChangeElectLaws()) {
                changeElectLaw.setSafChngNo(change.getSafChngNo());
                if (changeElectLaw.getSafChngElectLawResultNo() > 0) {
                    changeElectLaw.setUpdateUserId(change.getUpdateUserId());
                    this.changeMapper.updateChangeElectLaw(changeElectLaw);
                } else {
                    changeElectLaw.setCreateUserId(change.getCreateUserId());
                    this.changeMapper.createChangeElectLaw(changeElectLaw);
                }
                this.changeMapper.deleteChangeElectResult(changeElectLaw.getSafChngElectLawResultNo());

                if (CollectionUtils.isNotEmpty(changeElectLaw.getChangeElectResults())) {
                    for (int safElectTitlNo : changeElectLaw.getCheckChangeElectResults()) {
                        ChangeElectResult changeElectResult = new ChangeElectResult();
                        changeElectResult.setSafElectTitlNo(safElectTitlNo);
                        changeElectResult.setSafChngElectLawResultNo(changeElectLaw.getSafChngElectLawResultNo());
                        this.changeMapper.createChangeElectResult(changeElectResult);
                    }
                }
            }
        }
        data.put("safChngNo", change.getSafChngNo());
        data.put("chngNum", change.getChngNum());
        data.put("chngStepCd", change.getChngStepCd());
        data.put("safChngCommiNo", changeCommi != null ? changeCommi.getSafChngCommiNo() : 0);

        return data;
    }

    /**
     * 변경관리 삭제
     *
     * @param regulItmNo
     *            변경관리번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteChange(int safChngNo) throws Exception {
        this.changeMapper.deleteChangeRefWork(safChngNo);
        this.changeMapper.deleteChangeCheckItemResult(safChngNo);
        this.changeMapper.deleteChangeOperation(safChngNo);
        this.changeMapper.deleteChangeType(safChngNo);
        this.changeMapper.deleteChangeEffect(safChngNo);
        this.changeMapper.deleteAllChangeElectResult(safChngNo);
        this.changeMapper.deleteChangeElectLaw(safChngNo);
        return this.changeMapper.deleteChange(safChngNo);
    }

    /**
     * 변경관리위원회 상세 조회
     *
     * @param safChngNo
     *            변경관리번호
     * @return 변경관리위원회
     * @throws Exception
     */
    public ChangeCommi getChangeCommi(int safChngNo) throws Exception {
        ChangeCommi changeCommi = this.changeMapper.getChangeCommi(safChngNo);
        if (changeCommi != null && changeCommi.getSafChngCommiNo() > 0) {
            changeCommi.setChangeCommiPsns(this.changeMapper.getChangeCommiPsn(changeCommi.getSafChngCommiNo()));
        }
        return changeCommi;
    }

    /**
     * 진행관리 조회
     *
     * @param safChngNo
     *            변경관리 key
     * @param chngRefWorkCd
     *            진행관리
     * @return 진행관리 뷰
     * @throws Exception
     */
    public List<ChangeRefWork> getChangeRefWorks(int safChngNo, String chngRefWorkCd, DefaultParam defaultParam) throws Exception {
        List<ChangeRefWork> changeRefWorks = this.changeMapper.getChangeRefWork(safChngNo, null, null, null, defaultParam);
        if (changeRefWorks != null && changeRefWorks.size() > 0) {
            List<ChangeRefWork> deleteChangeRefWorks = new ArrayList<ChangeRefWork>();
            for (ChangeRefWork changeRefWork : changeRefWorks) {
                ChangeRefWorkView refWorkDetailInfos = this.changeMapper.getRefWorkDetailInfo(changeRefWork.getRefTableId(), changeRefWork.getRefTableNm(), defaultParam);
                if (refWorkDetailInfos != null) {
                    changeRefWork.setRefWorkNm(refWorkDetailInfos.getRefWorkNm()); // 제목
                    changeRefWork.setStepNm(refWorkDetailInfos.getStepNm()); // 단계
                    changeRefWork.setTypeCd(refWorkDetailInfos.getTypeCd()); // 분류
                    changeRefWork.setRefWorkDt(refWorkDetailInfos.getRefWorkDt()); // 관련등록수정일
                } else {
                    // null 인경우 해당 데이터가 날아간 경우임으로 없는것으로 취급
                    // 삭제 플래그를 따로 사용하고 있지 않음으로 인해 다음과 같이 없는것으로 설정
                    deleteChangeRefWorks.add(changeRefWork);
                }
            }
            if (deleteChangeRefWorks.size() > 0) {
                for (ChangeRefWork changeRefWork : deleteChangeRefWorks) {
                    changeRefWorks.remove(changeRefWork);
                }
            }
            // change.setChangeRefWorks(changeRefWorks);
        }
        return changeRefWorks;
    }

    /**
     * 인허가검토 조회
     *
     * @param safChngNo
     *            변경관리 key
     * @param defaultParam
     *            defaultParam
     * @return 인허가검토
     * @throws Exception
     */
    public List<ChangeElectLaw> getChangeElectResults(int safChngNo, DefaultParam defaultParam) throws Exception {
        List<ChangeElectLaw> changeElectLaws = this.changeMapper.getChangeElectLaws(safChngNo, defaultParam);

        if (CollectionUtils.isNotEmpty(changeElectLaws)) {
            for (ChangeElectLaw changeElectLaw : changeElectLaws) {
                changeElectLaw.setChangeElectResults(this.changeMapper.getChangeElectResults(changeElectLaw.getSafChngElectLawResultNo(), changeElectLaw.getRefLawCd(), defaultParam));
                if (CollectionUtils.isNotEmpty(changeElectLaw.getChangeElectResults())) {
                    List<Integer> pushData = new ArrayList<Integer>();
                    for (ChangeElectResult changeElectResult : changeElectLaw.getChangeElectResults()) {
                        if (changeElectResult.getSafChngElectItemResultNo() > 0) {
                            pushData.add(changeElectResult.getSafElectTitlNo());
                        }
                    }
                    changeElectLaw.setCheckChangeElectResults(pushData);
                }
            }
        }

        return changeElectLaws;
    }

    /**
     * 변경관리 진행단계 변경
     *
     * @param safChngNo
     *            변경관리 번호
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessChange(int safChngNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String chngStepCd = "";
        String confirmDt = "";

        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려

        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료
            Change change = this.changeMapper.getChange(safChngNo);
            if (change != null) {
                if (change.getChngStepCd().equals(ConstVal.SAF_CHNG_STEP_CGSP1)) {
                    if ("Y".equals(change.getCommiYn())) {
                        chngStepCd = ConstVal.SAF_CHNG_STEP_CGSPA;
                    } else {
                        // 미개최인 경우에는 검토단계를 넘어가도록 처리
                        chngStepCd = ConstVal.SAF_CHNG_STEP_CGSP2;
                    }
                } else if (change.getChngStepCd().equals(ConstVal.SAF_CHNG_STEP_CGSPA)) {
                    chngStepCd = ConstVal.SAF_CHNG_STEP_CGSP2;
                } else if (change.getChngStepCd().equals(ConstVal.SAF_CHNG_STEP_CGSP2)) {
                    chngStepCd = ConstVal.SAF_CHNG_STEP_CGSP3;
                    /**
                     * 2021.04.30 kdh
                     * 
                     * 변경관리 사후관리라는 진행단계가 생겼으며 해당 상태에서 완료로 넘어갈 시에 결재를 받지 않음으로 해당
                     * 라인 주석처리
                     */
                    // confirmDt = "완료";
                }
            }
        }

        // 변경관리 결재 반영
        resultNo += changeMapper.apprProcessChange(safChngNo, apprRqstNo, chngStepCd, confirmDt);
        return resultNo;
    }

    /**
     * 변경관리 완료
     *
     * @param change
     *            변경관리
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int completeChange(Change change) throws Exception {
        int result = changeMapper.completeChange(change);
        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_CHANGE, change.getSafChngNo(), change.getUpdateUserId());
        return result;
    }

    @Transactional
    public void taskChange(ChangeRefWork changeRefWork) throws Exception {
        List<ChangeRefWork> changeRefWorkSearchs = this.changeMapper.getChangeRefWork(0, "", changeRefWork.getRefTableId(), changeRefWork.getRefTableNm(), new DefaultParam("kr"));

        if (CollectionUtils.isNotEmpty(changeRefWorkSearchs)) {
            /**
             * 있는 경우 해당 데이터의 saf_chng_no가 가지고 온 데이터와 동일한지 판단
             * 
             * 동일하다면 아무것도 하지 않음
             * 
             * 동일하지 않다면 기존 데이터는 삭제하고 현재 들어온 값을 insert
             * 
             * ## 두 개 이상의 데이터는 존재할 수 없음
             */
            for (ChangeRefWork changeRefWorkSearch : changeRefWorkSearchs) {
                if (changeRefWorkSearch.getSafChngNo() != changeRefWork.getSafChngNo()) {
                    this.changeMapper.deleteChangeRefWork(changeRefWorkSearch.getSafChngNo());
                    if (changeRefWork.getSafChngNo() > 0) {
                        this.changeMapper.createChangeRefWork(changeRefWork);
                    }
                }
            }
        } else {
            /**
             * 없는 경우 saf_chng_no의 값이 존재하는지 확인 후 insert
             */
            if (changeRefWork.getSafChngNo() > 0) {
                this.changeMapper.createChangeRefWork(changeRefWork);
            }
        }
    }

    /**
     * 변경관리 조회
     *
     * @return 변경관리 목록
     * @throws Exception
     */
    public List<ChangeDashboard> getChangeDashboad(String plantCd, String rqstStartDt, String rqstEndDt, String rqstDeptCd, String chngStepCd, String lvlCd, String bizNm, DefaultParam defaultParam) throws Exception {
        List<ChangeDashboard> changeDashboards = new ArrayList<ChangeDashboard>();
        List<ChangeDashboardBase> changes = changeMapper.getChangeDashboadBase(plantCd, rqstStartDt, rqstEndDt, rqstDeptCd, chngStepCd, lvlCd, bizNm, defaultParam);

        if (CollectionUtils.isNotEmpty(changes)) {
            for (ChangeDashboardBase change : changes) {
                ChangeDashboard data = new ChangeDashboard();
                ChangeDashboardStep1 step1 = new ChangeDashboardStep1();
                ChangeDashboardStepA stepa = new ChangeDashboardStepA();
                ChangeDashboardStep2 step2 = new ChangeDashboardStep2();
                ChangeDashboardStep3 step3 = new ChangeDashboardStep3();

                step1.setSafChngNo(change.getSafChngNo());
                step1.setChngStepCd(change.getChngStepCd());
                step1.setChngStepNm(change.getChngStepNm());
                step1.setChngNum(change.getChngNum());
                step1.setBizNm(change.getBizNm());
                step1.setRqstDeptNm(change.getRqstDeptNm());
                step1.setPrevMocLvlNm(change.getPrevMocLvlNm());
                step1.setMocLvlNm(change.getMocLvlNm());

                stepa.setSafChngNo(change.getSafChngNo());
                stepa.setSafChngCommiNo(change.getSafChngCommiNo());
                stepa.setCommiDt(change.getCommiDt());
                stepa.setCommiContents(change.getCommiContents());
                stepa.setCommiYn(change.getCommiYn());
                stepa.setChangeCommiPsns(this.changeMapper.getChangeCommiPsn(change.getSafChngCommiNo()));

                step2.setSafChngNo(change.getSafChngNo());
                step2.setChangeCheckItemResults(this.changeMapper.getChangeCheckItemResult(change.getSafChngNo(), "order", defaultParam));

                step3.setImprs(imprService.getImprActDashboards(change.getSafChngNo(), ConstVal.SAF_IMPR_CLASS_CHANGE, defaultParam));

                data.setCGSP1(step1);
                data.setCGSPA(stepa);
                data.setCGSP2(step2);
                data.setCGSP3(step3);
                data.setChngStepCd(change.getChngStepCd());

                changeDashboards.add(data);
            }
        }

        return changeDashboards;
    }

}
