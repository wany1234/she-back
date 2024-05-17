package com.she.mgt.baseInfo.service;

import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.mgt.baseInfo.mapper.MgtMgBudgetActMstMapper;
import com.she.mgt.baseInfo.model.MgtMgBudgetAct;
import com.she.mgt.baseInfo.model.MgtMgBudgetActMst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MgtMgBudgetActMstService {
    @Autowired
    private MgtMgBudgetActMstMapper mgtMgBudgetActMstMapper;

    @Autowired
    private CodeMasterService codeMasterService;

    /**
     * 예산계정 관리목록 조회
     *
     * @param codeNm
     *      예산분류명
     * @param useYn
     *      사용여부
     * @return
     * @throws Exception
     */
    public List<CodeMaster> getBudgetActs(String codeNm, String useYn) throws Exception {
        return mgtMgBudgetActMstMapper.getBudgetActs(codeNm, useYn);
    }

    /**
     * 예산계정 상세 조회
     *
     * @param codeGroupCd
     *      코드그룹 코드
     * @param code
     *      예산분류코드
     * @return
     * @throws Exception
     */
    public MgtMgBudgetAct getBudgetAct(String codeGroupCd, String code) throws Exception {
        MgtMgBudgetAct mgtMgBudgetAct = new MgtMgBudgetAct();
        mgtMgBudgetAct.setMgtMgBudgetAct(codeMasterService.getCodeMaster(codeGroupCd, code, ""));
        mgtMgBudgetAct.setMgtMgBudgetActMsts(this.getBudgetActMsts(code));
        return mgtMgBudgetAct;
    }

    /**
     * 예산계정 관리상세목록 조회
     *
     * @param budgetTypeCd
     *      예산분류코드
     * @return
     * @throws Exception
     */
    public List<MgtMgBudgetActMst> getBudgetActMsts(String budgetTypeCd) throws Exception {
        return mgtMgBudgetActMstMapper.getBudgetActMsts(budgetTypeCd);
    }

    /**
     * 예산계정 상세목록 저장
     *
     * @param mgtMgBudgetActMst
     *          예산계정 상세
     * @return
     * @throws Exception
     */
    @Transactional
    public int createBudgetAct(MgtMgBudgetAct mgtMgBudgetAct) throws Exception {
        int resultNo = 0;
        resultNo = codeMasterService.createCodeMaster(mgtMgBudgetAct.getMgtMgBudgetAct());

        if (mgtMgBudgetAct.getMgtMgBudgetActMsts() != null && mgtMgBudgetAct.getMgtMgBudgetActMsts().size() > 0) {
            for (MgtMgBudgetActMst mgtMgBudgetActMst : mgtMgBudgetAct.getMgtMgBudgetActMsts()) {
                resultNo += mgtMgBudgetActMstMapper.saveBudgetActMst(mgtMgBudgetActMst);
            }
        }

        return resultNo;
    }

    /**
     * 예산계정 상세목록 저장
     *
     * @param mgtMgBudgetActMst
     *          예산계정 상세
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateBudgetAct(MgtMgBudgetAct mgtMgBudgetAct) throws Exception {
        int resultNo = 0;
        resultNo = codeMasterService.updateCodeMaster(mgtMgBudgetAct.getMgtMgBudgetAct());

        if (mgtMgBudgetAct.getMgtMgBudgetActMsts() != null && mgtMgBudgetAct.getMgtMgBudgetActMsts().size() > 0) {
            for (MgtMgBudgetActMst mgtMgBudgetActMst : mgtMgBudgetAct.getMgtMgBudgetActMsts()) {
                resultNo += mgtMgBudgetActMstMapper.saveBudgetActMst(mgtMgBudgetActMst);
            }
        }

        if (mgtMgBudgetAct.getDeleteMgtMgBudgetActMsts() != null && mgtMgBudgetAct.getDeleteMgtMgBudgetActMsts().size() > 0) {
            for (MgtMgBudgetActMst mgtMgBudgetActMst : mgtMgBudgetAct.getDeleteMgtMgBudgetActMsts()) {
                resultNo += mgtMgBudgetActMstMapper.deleteBudgetActMst(mgtMgBudgetActMst);
            }
        }

        return resultNo;
    }
}
