package com.she.mgt.budgetManagement.service;

import com.she.common.model.DefaultParam;
import com.she.mgt.baseInfo.model.MgtMgBudgetAct;
import com.she.mgt.budgetManagement.mapper.MgtBudgetingMapper;
import com.she.mgt.model.MgtBudgetAct;
import com.she.mgt.model.MgtBudgetActDept;
import com.she.mgt.model.MgtBudgetActDeptItem;
import com.she.utils.ConstVal;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MgtBudgetingService {
    @Autowired
    private MgtBudgetingMapper mgtBudgetingMapper;

    /**
     * 예산편성 관리 목록 조회
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
     * @param unOrganizeYn
     *          미편성 대상부서 여부
     * @param procStepCd
     *          단계(편성/집행)
     * @param stateCd
     *          상태(미작성/작성중/결재중/결재완료)
     * @return 예산편성 관리 목록
     * @throws Exception
     */
    public List<MgtBudgetAct> getMgtBudgetActs(String startYear, String endYear, String plantCd, String deptCd, String deptSubYn, String budgetTypeCd, String budgetClsCd, String budgetActNm
            , String unOrganizeYn, String procStepCd, String stateCd, String statusYn, int budgetActMstNo, DefaultParam defaultParam) throws Exception {
        return mgtBudgetingMapper.getMgtBudgetActs(startYear, endYear, plantCd, deptCd, deptSubYn, budgetTypeCd, budgetClsCd, budgetActNm, unOrganizeYn, procStepCd, stateCd, statusYn, budgetActMstNo, defaultParam);
    }

    /**
     * 예산편성 상세 조회
     * @param budgetActNo
     *          예산편성관리 번호
     * @param year
     *          연도
     * @param plantCd
     *          사업장코드
     * @param flag
     *          신규등록 조회 여부
     *          신규등록 조회일 경우 사업장코드/연도로 조회
     * @return 예산편성 상세
     * @throws Exception
     */
    public MgtBudgetAct getMgtBudgetAct(int budgetActNo, String year, String plantCd, String flag, DefaultParam defaultParam) throws Exception {
        MgtBudgetAct mgtBudgetAct = mgtBudgetingMapper.getMgtBudgetAct(budgetActNo, year, plantCd, flag);

        if (mgtBudgetAct != null && mgtBudgetAct.getBudgetActNo() > 0) {
            mgtBudgetAct.setMgtBudgetActDepts(mgtBudgetingMapper.getMgtBudgetActDepts(0, mgtBudgetAct.getBudgetActNo(), defaultParam));
        }

        return mgtBudgetAct;
    }

    /**
     * 예산편성 저장
     * @param mgtBudgetAct
     *          예산편성 관리
     * @return 예산편성 관리
     * @throws Exception
     */
    @Transactional
    public MgtBudgetAct createMgtBudgetAct(MgtBudgetAct mgtBudgetAct) throws Exception {
        mgtBudgetingMapper.createMgtBudgetAct(mgtBudgetAct);

        if (CollectionUtils.isNotEmpty(mgtBudgetAct.getMgtBudgetActDepts())) {
            for (MgtBudgetActDept mgtBudgetActDept : mgtBudgetAct.getMgtBudgetActDepts()) {
                mgtBudgetActDept.setBudgetActNo(mgtBudgetAct.getBudgetActNo());
                mgtBudgetingMapper.saveMgtBudgetActDept(mgtBudgetActDept);
            }
        }

        return mgtBudgetAct;
    }

    /**
     * 예산편성 저장
     * @param mgtBudgetAct
     *          예산편성 관리
     * @return 예산편성 관리
     * @throws Exception
     */
    @Transactional
    public MgtBudgetAct updateMgtBudgetAct(MgtBudgetAct mgtBudgetAct) throws Exception {
        mgtBudgetingMapper.updateMgtBudgetAct(mgtBudgetAct);

        if (CollectionUtils.isNotEmpty(mgtBudgetAct.getMgtBudgetActDepts())) {
            for (MgtBudgetActDept mgtBudgetActDept : mgtBudgetAct.getMgtBudgetActDepts()) {
                mgtBudgetActDept.setBudgetActNo(mgtBudgetAct.getBudgetActNo());
                mgtBudgetingMapper.saveMgtBudgetActDept(mgtBudgetActDept);
            }
        }

        if (CollectionUtils.isNotEmpty(mgtBudgetAct.getDeleteMgtBudgetActDepts())) {
            for (MgtBudgetActDept mgtBudgetActDept : mgtBudgetAct.getDeleteMgtBudgetActDepts()) {
                mgtBudgetingMapper.deleteMgtBudgetActDept(mgtBudgetActDept.getBudgetActDeptNo(), mgtBudgetActDept.getBudgetActNo());
            }
        }

        return mgtBudgetAct;
    }

    /**
     * 예산편성 대상부서별 예산계정 상세목록 조회
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public int deleteMgtBudgetAct(int budgetActNo) throws Exception {
        int resultNo = 0;

        resultNo += mgtBudgetingMapper.deleteMgtBudgetAct(budgetActNo);

        resultNo += mgtBudgetingMapper.deleteMgtBudgetActDept(0, budgetActNo);

        return resultNo;
    }

    /**
     * 예산편성 대상부서 상세 조회
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @return 결과
     * @throws Exception
     */
    public MgtBudgetActDept getMgtBudgetActDept(int budgetActDeptNo, int budgetActNo, DefaultParam defaultParam) throws Exception {
        MgtBudgetActDept mgtBudgetActDept = mgtBudgetingMapper.getMgtBudgetActDepts(budgetActDeptNo, budgetActNo, defaultParam).get(0);

        if(mgtBudgetActDept != null && mgtBudgetActDept.getBudgetActDeptNo() > 0) {
            mgtBudgetActDept.setMgtBudgetActDeptItems(mgtBudgetingMapper.getMgtBudgetActDeptItems(budgetActDeptNo));
        }

        return mgtBudgetActDept;
    }

    /**
     * 예산편성 대상부서별 예산계정 저장
     * @param mgtBudgetActDeptItem
     *          예산편성 대상부서 예산계정
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public MgtBudgetActDept saveMgtBudgetActDept(MgtBudgetActDept mgtBudgetActDept) throws Exception {
        int resultNo = 0;
        resultNo += mgtBudgetingMapper.saveMgtBudgetActDept(mgtBudgetActDept);

        if (mgtBudgetActDept.getMgtBudgetActDeptItems() != null && mgtBudgetActDept.getMgtBudgetActDeptItems().size() > 0) {
            for (MgtBudgetActDeptItem mgtBudgetActDeptItem : mgtBudgetActDept.getMgtBudgetActDeptItems()) {
                mgtBudgetActDeptItem.setBudgetActDeptNo(mgtBudgetActDept.getBudgetActDeptNo());
                resultNo += mgtBudgetingMapper.saveMgtBudgetActDeptItem(mgtBudgetActDeptItem);
            }
        }

        if (mgtBudgetActDept.getDeleteMgtBudgetActDeptItems() != null && mgtBudgetActDept.getDeleteMgtBudgetActDeptItems().size() > 0) {
            for (MgtBudgetActDeptItem mgtBudgetActDeptItem : mgtBudgetActDept.getDeleteMgtBudgetActDeptItems()) {
                resultNo += mgtBudgetingMapper.deleteMgtBudgetActDeptItem(mgtBudgetActDeptItem.getBudgetActDeptItemNo(), mgtBudgetActDeptItem.getBudgetActDeptNo());
            }
        }

        return mgtBudgetActDept;
    }

    /**
     * 예산편성 대상부서별 예산계정 상세목록 조회
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public int deleteMgtBudgetActDept(int budgetActDeptNo, int budgetActNo) throws Exception {
        int resultNo = 0;
        resultNo += mgtBudgetingMapper.deleteMgtBudgetActDept(budgetActDeptNo, budgetActNo);

        resultNo += mgtBudgetingMapper.deleteMgtBudgetActDeptItem(0, budgetActDeptNo);

        return resultNo;
    }

    /**
     * 예산편성관리 대상부서 결재정보 업데이트
     * @param budgetActDeptNo
     *          예산편성 대상부서 번호
     * @param budgetActNo
     *          예산편성 관리 번호
     * @param bizApprStepCd
     *          결재상태코드
     * @param apprRqstNo
     *          결재요청번호
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public int updateAppr(String budgetActDeptNos, int budgetActNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        for (String budgetActDeptNo : budgetActDeptNos.split(",")) {
            MgtBudgetActDept mgtBudgetActDept = this.getMgtBudgetActDept(Integer.parseInt(budgetActDeptNo), budgetActNo, new DefaultParam("kr"));

            if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
                // 결재완료일 경우
                mgtBudgetActDept.setStateCd(ConstVal.COM_STATE_COMPLETE); // 결재완료
            } else if (bizApprStepCd.equals(ConstVal.COM_BIZ_APPR_STEP_ING)) {
                // 결재중일 경우
                mgtBudgetActDept.setStateCd(ConstVal.COM_STATE_APPR); // 결재중
            } else {
                // 반려일 경우
                mgtBudgetActDept.setStateCd(ConstVal.COM_STATE_ING); // 작성중
            }

            mgtBudgetActDept.setApprRqstNo(apprRqstNo); // 결재요청번호
            mgtBudgetActDept.setStepCd(bizApprStepCd); // 결재상태

            resultNo += mgtBudgetingMapper.updateAppr(mgtBudgetActDept);
        }

        return resultNo;
    }

    /**
     * 예산편성 대상부서 상세 조회(현황 팝업시 조회)
     * @param year
     *          년도
     * @param plantCd
     *          사업장코드
     * @param deptCd
     *          대상부서코드
     * @return 결과
     * @throws Exception
     */
    public MgtBudgetActDept getMgtBudgetActDeptByStatInfo(String year, String plantCd, String deptCd, DefaultParam defaultParam) throws Exception {
        MgtBudgetActDept mgtBudgetActDept = mgtBudgetingMapper.getMgtBudgetActDeptByStatInfo(year, plantCd, deptCd, defaultParam);

        if(mgtBudgetActDept != null && mgtBudgetActDept.getBudgetActDeptNo() > 0) {
            mgtBudgetActDept.setMgtBudgetActDeptItems(mgtBudgetingMapper.getMgtBudgetActDeptItems(mgtBudgetActDept.getBudgetActDeptNo()));
        }

        return mgtBudgetActDept;
    }
}
