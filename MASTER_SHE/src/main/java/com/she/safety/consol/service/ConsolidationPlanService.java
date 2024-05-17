package com.she.safety.consol.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.safety.consol.mapper.ConsolidationMapper;
import com.she.safety.consol.mapper.ConsolidationPlanMapper;
import com.she.safety.consol.model.ConsolInspDept;
import com.she.safety.consol.model.ConsolPerson;
import com.she.safety.consol.model.Consolidation;
import com.she.safety.model.PatrolItemResult;
import com.she.utils.ConstVal;

@Service
public class ConsolidationPlanService {

    @Autowired
    private ConsolidationPlanMapper mapper;

    @Autowired
    private ConsolidationService mstService;

    @Autowired
    private ConsolidationMapper mstMapper;

    public List<Consolidation> getConsolPlans(String startDate, String endDate, String deptCd, String plantCd, String checkStepCd, int safCheckKindNo) throws Exception {
        return mapper.getConsolPlans(startDate, endDate, deptCd, plantCd, checkStepCd, safCheckKindNo);
    }

    public List<Consolidation> getConsolSigs(String startDate, String endDate, String plantCd, String deptCd, String userId) throws Exception {
        return mapper.getConsolSigs(startDate, endDate, plantCd, deptCd, userId);
    }

    public Consolidation getConsolPlan(int safCongChkRsltNo) throws Exception {
        return mapper.getConsolPlan(safCongChkRsltNo);
    }

    public Consolidation getConsolSig(int safCongChkRsltNo, int safChkInspPsnNo, int safChkInspDeptNo) throws Exception {
        return mapper.getConsolSig(safCongChkRsltNo, safChkInspPsnNo, safChkInspDeptNo);
    }

    public Integer updateUserSignature(int safChkInspPsnNo, String signImg) throws Exception {
        return mapper.updateUserSignature(safChkInspPsnNo, signImg);
    }

    public int completeSign(int safChkInspPsnNo) throws Exception {
        return mapper.completeSign(safChkInspPsnNo);
    }

    public List<ConsolInspDept> getConsolInspectors(int safCongChkRsltNo) throws Exception {
        List<ConsolInspDept> list = mapper.getConsolInspectors(safCongChkRsltNo);

        if (CollectionUtils.isNotEmpty(list)) {
            for (ConsolInspDept insp : list) {
                insp.setPersonList(mapper.getConsolPersons(insp.getSafChkInspDeptNo()));
            }
        }
        // String[] array = null;
        // String[] array2 = null;
        // for(ConsolInspDept insp : list) {
        // if (insp.getUserNms() != null)
        // array = insp.getUserNms().split(",");
        //
        // if(array != null && array.length == 2) {
        // insp.setUserNmF(array[0]);
        // insp.setUserNmS(array[1]);
        // } else if (array != null && array.length == 1){
        // insp.setUserNmF(insp.getUserNms());
        // }
        //
        // if (insp.getUserIds() != null)
        // array2 = insp.getUserIds().split(",");
        //
        // if(array2 != null && array2.length == 2) {
        // insp.setUserIdF(array2[0]);
        // insp.setUserIdS(array2[1]);
        // } else if (array2 != null && array2.length == 1){
        // insp.setUserIdF(insp.getUserIds());
        // }
        // }
        return list;
    }

    @Transactional
    public int createConsolPlan(Consolidation consolidation) throws Exception {
        if (consolidation == null) {
            return 0;
        } else {

            consolidation.setSafCongChkPlanNo(mstService.getPlanSequenceNumber());

            consolidation.setSafCongChkRsltNo(mstService.getResultSequenceNumber());

            // 합동점검 마스터 등록
            mstMapper.createConsolMst(consolidation);

            // 합동점검 검사항목 등록
            if (CollectionUtils.isNotEmpty(consolidation.getConsolItems())) {
                for (PatrolItemResult result : consolidation.getConsolItems()) {
                    result.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                    mapper.createConsolResultItem(result);
                }
            }

            // 합동점검 참여부서/업체 점검자 등록
            if (CollectionUtils.isNotEmpty(consolidation.getConsolInspectors())) {
                for (ConsolInspDept list : consolidation.getConsolInspectors()) {
                    list.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                    list.setSafChkInspDeptNo(mstService.getInspSequenceNumber());
                    mstMapper.createInspDept(list);

                    if (list.getUserNmF() != null && !list.getUserNmF().equals("")) {
                        ConsolPerson person = new ConsolPerson();
                        person.setSafChkInspPsnNo(mapper.getPsnSequenceNumber());
                        person.setUserId(list.getUserIdF());
                        person.setUserNm(list.getUserNmF());
                        person.setSafChkInspDeptNo(list.getSafChkInspDeptNo());
                        person.setInspClsCd(list.getInspClsCd());
                        mapper.createInspPsnNoImg(person);

                    }
                    if (list.getUserNmS() != null && !list.getUserNmS().equals("")) {
                        ConsolPerson person = new ConsolPerson();
                        person.setSafChkInspPsnNo(mapper.getPsnSequenceNumber());
                        person.setUserId(list.getUserIdS());
                        person.setUserNm(list.getUserNmS());
                        person.setSafChkInspDeptNo(list.getSafChkInspDeptNo());
                        person.setInspClsCd(list.getInspClsCd());
                        mapper.createInspPsnNoImg(person);
                    }
                }
            }
        }

        return consolidation.getSafCongChkRsltNo();
    }

    @Transactional
    public int updateConsolPlan(Consolidation consolidation) throws Exception {
        if (consolidation == null) {
            return 0;
        } else {
            if (CollectionUtils.isNotEmpty(consolidation.getConsolItems())) {
                mapper.deleteConsolResultItem(consolidation.getSafCongChkRsltNo());
                for (PatrolItemResult result : consolidation.getConsolItems()) {
                    result.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                    mapper.createConsolResultItem(result);
                }
            }

            mstMapper.updateConsolMst(consolidation);
            mstMapper.deleteConsolMst(consolidation.getSafCongChkRsltNo(), 0);
            if (CollectionUtils.isNotEmpty(consolidation.getConsolInspectors())) {
                for (ConsolInspDept list : consolidation.getConsolInspectors()) {
                    mapper.deleteInspPsn(list.getSafChkInspDeptNo());
                    list.setSafCongChkRsltNo(consolidation.getSafCongChkRsltNo());
                    list.setSafChkInspDeptNo(mstService.getInspSequenceNumber());
                    mstMapper.createInspDept(list);

                    // 참여부서/참여업체 점검자 등록
                    if (list.getUserNmF() != null && !list.getUserNmF().equals("")) {
                        ConsolPerson person = new ConsolPerson();
                        person.setSafChkInspPsnNo(mapper.getPsnSequenceNumber());
                        person.setUserId(list.getUserIdF());
                        person.setUserNm(list.getUserNmF());
                        person.setSafChkInspDeptNo(list.getSafChkInspDeptNo());
                        // 사인여부 확인
                        if ((list.getSignCfmYnF() != null && !list.getSignCfmYnF().equals(""))) {
                            person.setSignCfmYn("Y");
                        }
                        // 사인이미지 확인
                        if (list.getSignImgF() != null && !list.getSignImgF().equals("")) {
                            person.setSignImg(list.getSignImgF());
                            mapper.createInspPsn(person);
                        } else {
                            mapper.createInspPsnNoImg(person);
                        }
                    }
                    if (list.getUserNmS() != null && !list.getUserNmS().equals("")) {
                        ConsolPerson person = new ConsolPerson();
                        person.setSafChkInspPsnNo(mapper.getPsnSequenceNumber());
                        person.setUserId(list.getUserIdS());
                        person.setUserNm(list.getUserNmS());
                        person.setSafChkInspDeptNo(list.getSafChkInspDeptNo());

                        if ((list.getSignCfmYnS() != null && !list.getSignCfmYnS().equals(""))) {
                            person.setSignCfmYn("Y");
                        }
                        if (list.getSignImgS() != null && !list.getSignImgS().equals("")) {
                            person.setSignImg(list.getSignImgS());
                            mapper.createInspPsn(person);
                        } else {
                            mapper.createInspPsnNoImg(person);
                        }

                    }

                }
            }
        }
        return consolidation.getSafCongChkRsltNo();
    }

    public List<PatrolItemResult> getConsolResultItems(int safCongChkRsltNo, int safCheckKindNo) throws Exception {
        List<PatrolItemResult> patrolItemResults = mapper.getConsolResultItems(safCongChkRsltNo);
        // 저장된 데이터 값이 있는 경우
        if (patrolItemResults != null && patrolItemResults.size() > 0) {
            // 저장된 데이터값에 합동종류의 값이 현재 합동의 합동종류값과 동일한지 체크
            for (PatrolItemResult patrolItemResult : patrolItemResults) {
                // 동일한 값이 들어 있다면 저장된 데이터 값을 그대로 가져가 사용
                if (safCheckKindNo == patrolItemResult.getSafCheckKindNo()) {
                    return patrolItemResults;
                }
            }
            // 동일한 값이 없어 반환 되지 않은 경우에는 현재 합동의 합동종류값에 따른 상세항목들을 저장된 값에 붙인다.
            patrolItemResults.addAll(mapper.getNewConsolResultItems(safCheckKindNo));
        } else {
            // 저장된 값이 없는 경우에는 현재 합동의 합동종류값에 따른 상세항목들을 가지고 온다. (사용하는 것들만)
            patrolItemResults = mapper.getNewConsolResultItems(safCheckKindNo);
        }
        return patrolItemResults;
    }

    public int completeConsolPlan(int safCongChkRsltNo) throws Exception {
        return mapper.completeConsolPlan(safCongChkRsltNo);
    }

    public int deleteConsolPlan(int safCongChkRsltNo) throws Exception {
        return mapper.deleteConsolPlan(safCongChkRsltNo);
    }
}
