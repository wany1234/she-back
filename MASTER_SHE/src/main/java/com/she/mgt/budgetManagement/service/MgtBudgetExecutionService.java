package com.she.mgt.budgetManagement.service;

import com.google.common.base.Strings;
import com.she.common.model.DefaultParam;
import com.she.mgt.budgetManagement.mapper.MgtBudgetExecutionMapper;
import com.she.mgt.model.MgtBudgetExec;
import com.she.mgt.model.MgtBudgetStat;
import com.she.utils.ConstVal;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class MgtBudgetExecutionService {
    @Autowired
    private MgtBudgetExecutionMapper mgtBudgetExecutionMapper;

    /**
     * 예산집행 관리 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param plantCd
     *          사업장코드
     * @param deptCd
     *          대상부서코드
     * @param deptSubYn
     *          하위부서 조회여부
     * @param budgetTypeCd
     *          예산분류코드
     * @param budgetClsCd
     *          예산구분코드
     * @param budgetActNm
     *          예산계정
     * @param procStepCd
     *          단계(편성/집행)
     * @param stateCd
     *          상태(미작성/작성중/결재중/결재완료)
     * @return 예산집행 관리 목록
     * @throws Exception
     */
    public List<MgtBudgetExec> getMgtBudgetExecs(String startYear, String endYear, String plantCd, String deptCd, String deptSubYn, String budgetTypeCd, String budgetClsCd, String budgetActNm
            , String procStepCd, String stateCd, String statusYn, int budgetActMstNo, String month, DefaultParam defaultParam) throws Exception {
        return mgtBudgetExecutionMapper.getMgtBudgetExecs(startYear, endYear, plantCd, deptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, procStepCd, stateCd, statusYn, budgetActMstNo, month, defaultParam);
    }

    /**
     * 예산집행 관리 상세 조회
     * @param budgetExecNo
     *          예산집행 번호
     * @return 예산집행 상세
     * @throws Exception
     */
    public MgtBudgetExec getMgtBudgetExec(int budgetExecNo, DefaultParam defaultParam) throws Exception {
        MgtBudgetExec mgtBudgetExec = mgtBudgetExecutionMapper.getMgtBudgetExec(budgetExecNo, defaultParam);
        mgtBudgetExec.setExecPsblAmt(this.getExecPsblAmt(mgtBudgetExec.getDeptCd(), mgtBudgetExec.getBudgetTypeCd(), mgtBudgetExec.getBudgetClsCd(), mgtBudgetExec.getBudgetActMstNo()
                , (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(mgtBudgetExec.getStepCd()) || ConstVal.COM_BIZ_APPR_STEP_ING.equals(mgtBudgetExec.getStepCd())) ? 0 : mgtBudgetExec.getBudgetExecNo())); // 집행가능금액 따로 조회
        return mgtBudgetExec;
    }

    /**
     * 예산계정별 집행가능 금액 조회
     * @param budgetActMstNo
     *          예산계정 번호
     * @return 집행가능 금액
     * @throws Exception
     */
    public String getExecPsblAmt(String deptCd, String budgetTypeCd, String budgetClsCd, int budgetActMstNo, int budgetExecNo) throws Exception {
        return mgtBudgetExecutionMapper.getExecPsblAmt(deptCd, budgetTypeCd, budgetClsCd, budgetActMstNo, budgetExecNo);
    }

    /**
     * 예산편성된 예산계정 목록 조회
     * @param year
     *          년도
     * @param deptCd
     *          대상부서
     * @param pdeptCd
     *          상위대상부서
     * @param deptSubYn
     *          하위부서 조회여부
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActNm
     *          예산계정
     * @return 예산편성된 예산계정 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getBudgetingActMst(String year, String plantCd, String deptCd, String pdeptCd, String deptSubYn, String budgetTypeCd, String budgetClsCd, String budgetActNm, DefaultParam defaultParam) throws Exception {
        List<HashMap<String, Object>> budgetingActMsts = mgtBudgetExecutionMapper.getBudgetingActMst(year, plantCd, deptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, defaultParam);
        if (CollectionUtils.isEmpty(budgetingActMsts) && !Strings.isNullOrEmpty(pdeptCd)) {
            // 현재 부서의 예산계정정보가 없고 상위부서코드가 있을 경우 상위부서의 예산계정을 조회
            budgetingActMsts = mgtBudgetExecutionMapper.getBudgetingActMst(year, plantCd, pdeptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, defaultParam);
        }
        return budgetingActMsts;
    }

    /**
     * 예산집행 신규등록
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    public MgtBudgetExec insertMgtBudgetExec(MgtBudgetExec mgtBudgetExec) throws Exception {
        mgtBudgetExecutionMapper.insertMgtBudgetExec(mgtBudgetExec);
        return mgtBudgetExec;
    }

    /**
     * 예산집행 수정
     * @param mgtBudgetExec
     *          예산집행
     * @return 결과
     * @throws Exception
     */
    public MgtBudgetExec updateMgtBudgetExec(MgtBudgetExec mgtBudgetExec) throws Exception {
        mgtBudgetExecutionMapper.updateMgtBudgetExec(mgtBudgetExec);
        return mgtBudgetExec;
    }

    /**
     * 예산집행 삭제
     * @param budgetExecNo
     *          예산집행 번호
     * @return 결과
     * @throws Exception
     */
    public int deleteMgtBudgetExec(int budgetExecNo) throws Exception {
        return mgtBudgetExecutionMapper.deleteMgtBudgetExec(budgetExecNo);
    }

    /**
     * 예산집행 결재정보 업데이트
     * @param budgetExecNo
     *          예산집행 번호
     * @param bizApprStepCd
     *          결재상태코드
     * @param apprRqstNo
     *          결재요청번호
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public int updateAppr(String budgetExecNos, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        for (String budgetExecNo : budgetExecNos.split(",")) {
            MgtBudgetExec mgtBudgetExec = this.getMgtBudgetExec(Integer.parseInt(budgetExecNo), new DefaultParam("kr"));

            if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
                // 결재완료일 경우
                mgtBudgetExec.setStateCd(ConstVal.COM_STATE_COMPLETE); // 결재완료
            } else if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_ING)) {
                // 결재중일 경우
                mgtBudgetExec.setStateCd(ConstVal.COM_STATE_APPR); // 결재중
            } else {
                // 반려일 경우
                mgtBudgetExec.setStateCd(ConstVal.COM_STATE_ING); // 작성중
            }

            mgtBudgetExec.setApprRqstNo(apprRqstNo); // 결재요청번호
            mgtBudgetExec.setStepCd(bizApprStepCd); // 결재상태

            resultNo += mgtBudgetExecutionMapper.updateAppr(mgtBudgetExec);
        }


        return resultNo;
    }

    /**
     * 예산 편성관리 현황 조회
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActMstNo
     *          예산계정
     * @return 결과
     * @throws Exception
     */
    public List<HashMap<String, Object>> getBudgetingStatus(String year, String plantCd, String budgetTypeCd, String budgetClsCd, int budgetActMstNo, String deptCd, DefaultParam defaultParam) throws Exception {
        return mgtBudgetExecutionMapper.getBudgetingStatus(year, plantCd, budgetTypeCd, budgetClsCd, budgetActMstNo, deptCd, defaultParam);
    }

    /**
     * 예산 편성및집행 현황 조회
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param budgetTypeCd
     *          예산분류
     * @param budgetClsCd
     *          예산구분
     * @param budgetActMstNo
     *          예산계정
     * @return 결과
     * @throws Exception
     */
    public List<MgtBudgetStat> getBudgetStatus(String year, String plantCd, String budgetTypeCd, String budgetClsCd, int budgetActMstNo, DefaultParam defaultParam) throws Exception {
        return mgtBudgetExecutionMapper.getBudgetStatus(year, plantCd, budgetTypeCd, budgetClsCd, budgetActMstNo, defaultParam);
    }
}
