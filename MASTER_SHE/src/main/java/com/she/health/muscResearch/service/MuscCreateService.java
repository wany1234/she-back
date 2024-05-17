package com.she.health.muscResearch.service;

import com.she.health.model.*;
import com.she.health.muscResearch.mapper.MuscResearchMapper;
import com.she.utils.ConstVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MuscCreateService {

    @Autowired MuscResearchMapper muscResearchMapper;

    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void execDbWork(String uploadUserId, String muscResearchNo, List<MuscResearchUnit> muscResearchUnitList, List<MuscResearchRslt> muscResearchRsltList, List<MuscResearchDept> muscResearchDeptList, List<HashMap<String, String>> insertInfos) throws Exception {
        int i = 0;
        try {
            for (i = 0; i < muscResearchRsltList.size(); i++) {

                MuscResearchUnit unit = muscResearchUnitList.get(i);
                Map<String, String> infos = insertInfos.get(i);
                MuscResearchDept researchDept = muscResearchDeptList.get(i);
                MuscResearchRslt muscResearchRslt = muscResearchRsltList.get(i);
                String insertDeptCd = infos.get("deptCd");
                String insertUnitWorkNm = infos.get("unitWorkNm");

                int checkUnitWorks = 0;

                checkUnitWorks = muscResearchMapper.getCheckUnitWorkCnt(0, Integer.parseInt(muscResearchNo), insertDeptCd, insertUnitWorkNm);
                if (checkUnitWorks == 0) {
                    int unitWork = muscResearchMapper.createUnitWork(unit);
                } else {
                    int muscResearchUnitNo = muscResearchMapper.getUnitWorkNo(Integer.parseInt(muscResearchNo), insertDeptCd, insertUnitWorkNm);
                    unit.setMuscResearchUnitNo(muscResearchUnitNo);
                }

                muscResearchRslt.setMuscResearchUnitNo(unit.getMuscResearchUnitNo());

                researchDept.setSendYn(null);
                muscResearchMapper.updateMuscResearchDept(researchDept);

                muscResearchMapper.createResearchUnitRslt(muscResearchRslt);
            }
            MuscResearch muscResearch = null;
            muscResearch = muscResearchMapper.getMuscResearch(Integer.parseInt(muscResearchNo));
            muscResearch.setUpdateUserId(uploadUserId);
            muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_RESULT_STATE_WRITE);
            muscResearchMapper.completeMuscResearch(muscResearch);
        } catch (Exception e) {
            throw new Exception(String.valueOf(i));
        }
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
    public void execHarmfulDbWork(String uploadUserId, String muscResearchNo, List<MuscHarmful> muscResearchHarmfulList, List<MuscHarmfulEval> muscResearchEvalList) throws Exception {
        int i = 0;
        try {
            for (i = 0; i < muscResearchEvalList.size(); i++) {

                MuscHarmful harmful = muscResearchHarmfulList.get(i);
                MuscHarmfulEval harmfulEval = muscResearchEvalList.get(i);

                muscResearchMapper.updateHarmful(harmful);

                MuscHarmfulEval found = muscResearchMapper.getSingleHarmfulSurvey(harmful.getMuscHarmfulNo(), harmfulEval.getMuscResearchChklistNo());
                if (found == null) {
                    muscResearchMapper.createHarmfulSurvey(harmfulEval);
                } else {
                    harmfulEval.setMuscHarmfulEvalNo(found.getMuscHarmfulEvalNo());
                    muscResearchMapper.updateHarmfulSurvey(harmfulEval);
                }
            }
//            MuscResearch muscResearch = muscResearchMapper.getMuscResearch(Integer.parseInt(muscResearchNo));
//            muscResearch.setUpdateUserId(uploadUserId);
//            muscResearch.setMuscResearchStateCd(ConstVal.MUSC_RESEARCH_HARMFUL_SUR_COMPLETE);
//            muscResearchMapper.completeMuscResearch(muscResearch);
        } catch (Exception e) {
            throw new Exception(String.valueOf(i));
        }
    }

}
