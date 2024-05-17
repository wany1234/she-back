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
package com.she.vendor.subconEval.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.utils.ConstVal;
import com.she.vendor.model.SubconEvalItemResult;
import com.she.vendor.model.SubconEvalResult;
import com.she.vendor.subconEval.mapper.SubconEvalResultMapper;

@Service
public class SubconEvalResultService {
    @Autowired
    private SubconEvalResultMapper subconEvalResultMapper;

    /**
     * 협력업체평가 상세 조회
     * 
     * @param safSubconEvalResultNo
     *            협력업체평가번호
     * @return 협력업체평가
     * @throws Exception
     */
    public SubconEvalResult getSubconEvalResult(int safSubconEvalResultNo) throws Exception {
        return subconEvalResultMapper.getSubconEvalResult(safSubconEvalResultNo);
    }

    /**
     * 협력업체평가 신규등록
     * 
     * @param subconEvalResult
     *            협력업체평가
     * @return 협력업체평가 코드
     * @throws Exception
     */
    @Transactional
    public int createSubconEvalResult(SubconEvalResult subconEvalResult) throws Exception {
        int resultNo = subconEvalResultMapper.createSubconEvalResult(subconEvalResult);
        saveSubconEvalItemResult(subconEvalResult);
        return resultNo > 0 ? subconEvalResult.getSafSubconEvalResultNo() : 0;
    }

    /**
     * 협력업체평가 수정
     * 
     * @param subconEvalResult
     *            협력업체평가
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateSubconEvalResult(SubconEvalResult subconEvalResult) throws Exception {
        int resultNo = subconEvalResultMapper.updateSubconEvalResult(subconEvalResult);
        resultNo += saveSubconEvalItemResult(subconEvalResult);
        return resultNo;
    }

    /**
     * 협력업체평가항목결과 등록
     * 
     * @param subconEvalResult
     *            협력업체평가
     * @return 등록 행 수
     * @throws Exception
     */
    public int saveSubconEvalItemResult(SubconEvalResult subconEvalResult) throws Exception {
        int resultNo = 0;
        List<SubconEvalItemResult> subconEvalItemResults = subconEvalResult.getSubconEvalItemResults();
        if (subconEvalItemResults != null) {
            // 협력업체 평가결과 항목 삭제
            resultNo += subconEvalResultMapper.deleteSubconEvalItemResult(subconEvalResult.getSafSubconEvalResultNo());

            if (subconEvalItemResults.size() > 0) {
                for (SubconEvalItemResult saveSubconEvalItemResult : subconEvalItemResults) {
                    saveSubconEvalItemResult.setSafSubconEvalResultNo(subconEvalResult.getSafSubconEvalResultNo());
                    resultNo += subconEvalResultMapper.createSubconEvalItemResult(saveSubconEvalItemResult);
                }
            }
        }
        return resultNo;
    }

    /**
     * 협력업체평가 삭제
     * 
     * @param subconEvalResult
     *            협력업체평가
     * @return 삭제 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteSubconEvalResult(SubconEvalResult subconEvalResult) throws Exception {
        int resultNo = subconEvalResultMapper.deleteSubconEvalItemResult(subconEvalResult.getSafSubconEvalResultNo());
        resultNo += subconEvalResultMapper.deleteSubconEvalResult(subconEvalResult.getSafSubconEvalResultNo());
        return resultNo;
    }

    /**
     * 평가항목결과 진행단계 변경
     *
     * @param safSubconEvalResultNo
     *            평가항목결과 ID
     * @param bizApprStepCd
     *            결재진행단계
     * @return 변경 행 수
     * @throws Exception
     */
    public int apprProcessSubconEvalResult(int safSubconEvalResultNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        int resultNo = 0;
        String status = "";
        // 결재완료
        SubconEvalResult subconEvalResult = subconEvalResultMapper.getSubconEvalResult(safSubconEvalResultNo);
        status = subconEvalResult.getStatus();
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 작성중의 경우
            if (status.equals(ConstVal.COM_PROCESS_STEP_CREATE)) {
                status = ConstVal.COM_PROCESS_STEP_COMPLTE;
            }
        }
        subconEvalResult.setApprRqstNo(apprRqstNo);
        subconEvalResult.setStatus(status);
        // 평가항목결과 진행단계 수정
        resultNo += subconEvalResultMapper.updateSubconEvalResult(subconEvalResult);
        return resultNo;
    }
}
