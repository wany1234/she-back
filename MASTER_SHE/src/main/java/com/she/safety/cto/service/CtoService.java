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
package com.she.safety.cto.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.impr.service.ImprService;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.safety.cto.mapper.CtoMapper;
import com.she.safety.model.Cto;
import com.she.safety.model.CtoAct;
import com.she.safety.model.CtoActCause;
import com.she.safety.model.CtoCheckResult;
import com.she.utils.ConstVal;

@Service
public class CtoService {
    @Autowired
    private CtoMapper ctoMapper;

    @Autowired
    private ImprService imprService;

    @Autowired
    private CodeMasterService codeMasterService;

    /**
     * cto 조회
     *
     * @param plantCd
     *            사업장
     * @param startDt
     *            시작일
     * @param endDt
     *            종료일
     * @param deptCd
     *            관찰부서
     * @param processCd
     *            관찰공정
     * @param jobNm
     *            작업명
     * @return cto 목록
     * @throws Exception
     */
    public List<Cto> getCtos(String plantCd, String startDt, String endDt, String deptCd, String processCd, String jobNm, String stepCd, String deptSubYn) throws Exception {
        return ctoMapper.getCtos(plantCd, startDt, endDt, deptCd, processCd, jobNm, stepCd, deptSubYn);
    }

    /**
     * cto 상세조회
     *
     * @param safCtoNo
     *            cto번호
     * @return cto
     * @throws Exception
     */
    public Cto getCto(int safCtoNo) throws Exception {
        Cto cto = ctoMapper.getCto(safCtoNo);
        // 핵심행동
        cto.setCtoActs(ctoMapper.getCtoActs(safCtoNo));
        // CTO체크리스트
        cto.setCtoCheckResults(ctoMapper.getCtoCheckResults(safCtoNo));
        // 근본원인
        cto.setCtoActCauses(ctoMapper.getCtoActCauses(safCtoNo));
        return cto;
    }

    /**
     * cto 신규등록
     *
     * @param cto
     *            cto
     * @return cto번호
     * @throws Exception
     */
    @Transactional
    public int createCto(Cto cto) throws Exception {
        int resultNo = 0;
        // cto 신규등록
        resultNo += ctoMapper.createCto(cto);
        // 핵심행동 신규등록
        if (cto.getCtoActs() != null && cto.getCtoActs().size() > 0) {
            for (CtoAct ctoAct : cto.getCtoActs()) {
                ctoAct.setSafCtoNo(cto.getSafCtoNo());
                resultNo += ctoMapper.createCtoAct(ctoAct);
            }
        }
        // 체크리스트 신규등록
        if (cto.getCtoCheckResults() != null && cto.getCtoCheckResults().size() > 0) {
            for (CtoCheckResult ctoCheckResult : cto.getCtoCheckResults()) {
                ctoCheckResult.setSafCtoNo(cto.getSafCtoNo());
                resultNo += ctoMapper.createCtoCheckResult(ctoCheckResult);
            }
        }
        // 근본원인 신규등록
        if (cto.getCtoActCauses() != null && cto.getCtoActCauses().size() > 0) {
            for (CtoActCause ctoActCause : cto.getCtoActCauses()) {
                ctoActCause.setSafCtoNo(cto.getSafCtoNo());
                resultNo += ctoMapper.createCtoActCause(ctoActCause);
            }
        }

        return resultNo > 0 ? cto.getSafCtoNo() : 0;
    }

    /**
     * cto 수정
     *
     * @param cto
     *            cto
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateCto(Cto cto) throws Exception {
        int resultNo = 0;
        if ("STEP2".equals(cto.getStepCd())) {
            int requestImprovementCount = imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_CTO, cto.getSafCtoNo(), cto.getUpdateUserId());
        }
        // cto 수정
        resultNo += ctoMapper.updateCto(cto);
        // 핵심행동 저장
        if (cto.getCtoActs() != null) {
            ctoMapper.deleteCtoAct(cto.getSafCtoNo());
            if (cto.getCtoActs().size() > 0) {
                for (CtoAct ctoAct : cto.getCtoActs()) {
                    ctoAct.setSafCtoNo(cto.getSafCtoNo());
                    resultNo += ctoMapper.createCtoAct(ctoAct);
                }
            }
        }
        // 체크리스트 저장
        if (cto.getCtoCheckResults() != null) {
            ctoMapper.deleteCtoCheckResult(cto.getSafCtoNo());
            if (cto.getCtoCheckResults().size() > 0) {
                for (CtoCheckResult ctoCheckResult : cto.getCtoCheckResults()) {
                    ctoCheckResult.setSafCtoNo(cto.getSafCtoNo());
                    resultNo += ctoMapper.createCtoCheckResult(ctoCheckResult);
                }
            }
        }
        // 근본원인 저장
        if (cto.getCtoActCauses() != null) {
            ctoMapper.deleteCtoActCause(cto.getSafCtoNo());
            if (cto.getCtoActCauses().size() > 0) {
                for (CtoActCause ctoActCause : cto.getCtoActCauses()) {
                    ctoActCause.setSafCtoNo(cto.getSafCtoNo());
                    resultNo += ctoMapper.createCtoActCause(ctoActCause);
                }
            }
        }

        return resultNo;
    }

    /**
     * cto 삭제
     *
     * @param safCtoNo
     *            cto번호
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteCto(int safCtoNo) throws Exception {
        int resultNo = 0;
        // 핵심행동 삭제
        resultNo += ctoMapper.deleteCtoAct(safCtoNo);
        // 체크리스트 삭제
        resultNo += ctoMapper.deleteCtoCheckResult(safCtoNo);
        // 근본원인 삭제
        resultNo += ctoMapper.deleteCtoActCause(safCtoNo);
        // cto 삭제
        resultNo += ctoMapper.deleteCto(safCtoNo);

        return resultNo;
    }

    public List<CodeMaster> getCheckList(String codeNm, String useYn) throws Exception {
        return codeMasterService.getCodeMasters("Y", ConstVal.COM_CODE_DOMAIN_SAF, ConstVal.COM_CODE_GROUP_CTO_ACT, codeNm, useYn);
    }

    public CodeMaster getCheckItem(String code) throws Exception {
        return codeMasterService.getCodeMaster(ConstVal.COM_CODE_GROUP_CTO_ACT, code, "Y");
    }

    public List<HashMap<String, Object>> checkCheckListItem(String code, String codeNm) throws Exception {
        return ctoMapper.checkCheckListItem(ConstVal.COM_CODE_GROUP_CTO_ACT, code, codeNm);
    }

    @Transactional
    public int updateCheckList(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_CTO_ACT);
        return codeMasterService.updateCodeMaster(codeMaster);
    }

    @Transactional
    public int createCheckList(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_CTO_ACT);
        return codeMasterService.createCodeMaster(codeMaster);
    }
}
