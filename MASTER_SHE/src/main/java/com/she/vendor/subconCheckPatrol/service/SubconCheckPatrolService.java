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
package com.she.vendor.subconCheckPatrol.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.impr.service.ImprService;
import com.she.manage.model.CodeMaster;
import com.she.safety.model.CheckItem;
import com.she.utils.ConstVal;
import com.she.vendor.model.SubconCheckInspector;
import com.she.vendor.model.SubconCheckItemResult;
import com.she.vendor.model.SubconCheckResult;
import com.she.vendor.subconCheckPatrol.mapper.SubconCheckPatrolMapper;

@Service
public class SubconCheckPatrolService {
    @Autowired
    private SubconCheckPatrolMapper subconCheckPatrolMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 협력업체자체점검 목록 조회
     * 
     * @param plantCd
     *            사업장
     * @param checkKindCd
     *            점검종류코드
     * @param fromDt
     *            시작일
     * @param toDt
     *            종료일
     * @param vendorCd
     *            업체코드
     * @param vendorNm
     *            업체명
     * @return 협력업체평가 목록
     * @throws Exception
     */
    public List<SubconCheckResult> getSubconCheckResults(String plantCd, String checkKindCd, String fromDt, String toDt, String checkStepCd, String vendorCd, String vendorNm, String checkTitle) throws Exception {
        return subconCheckPatrolMapper.getSubconCheckResults(plantCd, checkKindCd, fromDt, toDt, checkStepCd, vendorCd, vendorNm, checkTitle);
    }

    /**
     * 협력업체자체점검종류 조회
     * 
     * @param plantCd
     *            사업장
     * @return 협력업체자체점검종류
     * @throws Exception
     */
    public List<CodeMaster> getSubconCheckKinds() throws Exception {
        return subconCheckPatrolMapper.getSubconCheckKinds();
    }

    /**
     * 협력업체자체점검 결과 상세 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체자체점검결과
     * @throws Exception
     */
    public SubconCheckResult getSubconCheckResult(int vendorCheckRsltNo) throws Exception {
        SubconCheckResult result = subconCheckPatrolMapper.getSubconCheckResult(vendorCheckRsltNo);
        result.setVendorCheckInspectors(subconCheckPatrolMapper.getSubconCheckInspectors(vendorCheckRsltNo));
        return result;
    }

    /**
     * 협력업체자체점검 결과 항목 리스트 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체자체점검결과 항목 리스트
     * @throws Exception
     */
    public List<SubconCheckItemResult> getSubconCheckItemResult(int safCheckKindNo, int vendorCheckRsltNo) throws Exception {
        return subconCheckPatrolMapper.getSubconCheckItemResults(safCheckKindNo, vendorCheckRsltNo);
    }

    /**
     * 협력업체자체점검 결과 외부 점검자 리스트 조회
     *
     * @param vendorCheckRsltNo
     *            협력업체자체점검결과No
     * @return 협력업체자체점검결과 외부 점검자 리스트
     * @throws Exception
     */
    public List<SubconCheckInspector> getSubconCheckInspector(int vendorCheckRsltNo) throws Exception {
        return subconCheckPatrolMapper.getSubconCheckInspectors(vendorCheckRsltNo);
    }

    /**
     * 점검항목 목록 조회
     * 
     * @param safCheckKindNo
     * @return
     * @throws Exception
     */
    public List<CheckItem> getCheckItemList(String safCheckKindNo) throws Exception {
        return subconCheckPatrolMapper.getCheckItemList(safCheckKindNo);
    }

    /**
     * 업체자체점검결과 등록
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    @Transactional
    public int createCheckResult(SubconCheckResult subconCheckResult) throws Exception {
        // 업체자체점검결과 등록
        subconCheckResult.setCheckStepCd(ConstVal.SAF_CHECK_STEP_RESULT_CD); // 점검진행상태

        subconCheckPatrolMapper.createCheckResult(subconCheckResult);

        // 항목별점검결과 등록
        if (CollectionUtils.isNotEmpty(subconCheckResult.getVendorCheckItemResults())) {
            for (SubconCheckItemResult subconCheckItemResult : subconCheckResult.getVendorCheckItemResults()) {
                subconCheckItemResult.setVendorCheckRsltNo(subconCheckResult.getVendorCheckRsltNo());
                subconCheckPatrolMapper.createCheckItemResult(subconCheckItemResult);
            }
        }

        // 업체자체점검 점검자 등록
        if (CollectionUtils.isNotEmpty(subconCheckResult.getVendorCheckInspectors())) {
            for (SubconCheckInspector subconCheckInspector : subconCheckResult.getVendorCheckInspectors()) {
                subconCheckInspector.setVendorCheckRsltNo(subconCheckResult.getVendorCheckRsltNo());
                subconCheckPatrolMapper.createCheckInspector(subconCheckInspector);
            }
        }

        return subconCheckResult.getVendorCheckRsltNo();
    }

    /**
     * 업체자체점검결과 수정
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateCheckResult(SubconCheckResult subconCheckResult) throws Exception {

        // 업체자체점검결과 수정
        subconCheckResult.setCheckStepCd(ConstVal.SAF_CHECK_STEP_RESULT_CD); // 점검진행상태
        int result = subconCheckPatrolMapper.updateCheckResult(subconCheckResult);

        // 항목별점검결과 처리
        if (subconCheckResult.getVendorCheckItemResults() != null) {
            // 항목별점검결과 삭제
            // 항목별점검결과 등록
            subconCheckPatrolMapper.deleteCheckItemRslt(subconCheckResult.getVendorCheckRsltNo());
            for (SubconCheckItemResult subconCheckItemResult : subconCheckResult.getVendorCheckItemResults()) {
                subconCheckItemResult.setVendorCheckRsltNo(subconCheckResult.getVendorCheckRsltNo());
                result += subconCheckPatrolMapper.createCheckItemResult(subconCheckItemResult);
            }
        }

        // 업체자체점검 점검자 처리
        if (subconCheckResult.getVendorCheckInspectors() != null) {
            // 업체자체점검 점검자 삭제
            subconCheckPatrolMapper.deleteCheckInspectPsn(subconCheckResult.getVendorCheckRsltNo());
            // 업체자체점검 점검자 등록
            for (SubconCheckInspector subconCheckInspector : subconCheckResult.getVendorCheckInspectors()) {
                subconCheckInspector.setVendorCheckRsltNo(subconCheckResult.getVendorCheckRsltNo());
                result += subconCheckPatrolMapper.createCheckInspector(subconCheckInspector);
            }
        }
        return subconCheckResult.getVendorCheckRsltNo();
    }

    /**
     * 업체자체점검결과 완료
     * 
     * @param subconCheckResult
     * @return
     * @throws Exception
     */
    @Transactional
    public int completeCheckResult(SubconCheckResult subconCheckResult) throws Exception {

        // 업체자체점검결과 수정
        subconCheckResult.setCheckStepCd(ConstVal.SAF_CHECK_STEP_IMPROVED_CD); // 점검진행상태
        subconCheckPatrolMapper.updateCheckResult(subconCheckResult);

        // 항목별점검결과 처리
        if (subconCheckResult.getVendorCheckItemResults() != null) {
            // 항목별점검결과 삭제
            // 항목별점검결과 등록
            subconCheckPatrolMapper.deleteCheckItemRslt(subconCheckResult.getVendorCheckRsltNo());
            for (SubconCheckItemResult subconCheckItemResult : subconCheckResult.getVendorCheckItemResults()) {
                subconCheckItemResult.setVendorCheckRsltNo(subconCheckResult.getVendorCheckRsltNo());
                subconCheckPatrolMapper.createCheckItemResult(subconCheckItemResult);
            }
        }

        // 업체자체점검 점검자 처리
        if (subconCheckResult.getVendorCheckInspectors() != null) {
            // 업체자체점검 점검자 삭제
            subconCheckPatrolMapper.deleteCheckInspectPsn(subconCheckResult.getVendorCheckRsltNo());
            // 업체자체점검 점검자 등록
            for (SubconCheckInspector subconCheckInspector : subconCheckResult.getVendorCheckInspectors()) {
                subconCheckInspector.setVendorCheckRsltNo(subconCheckResult.getVendorCheckRsltNo());
                subconCheckPatrolMapper.createCheckInspector(subconCheckInspector);
            }
        }

        imprService.updateImprStepCd(ConstVal.SAF_IMPR_CLASS_VENDOR, subconCheckResult.getVendorCheckRsltNo(), subconCheckResult.getUpdatePortalId());

        return subconCheckResult.getVendorCheckRsltNo();
    }

    /**
     * 업체자체점검결과 삭제
     * 
     * @param vendorCheckRsltNo
     * @return
     * @throws Exception
     */
    @Transactional
    public int deleteCheckResult(int vendorCheckRsltNo) throws Exception {
        if (vendorCheckRsltNo <= 0) {
            return 0;
        } else {
            // 항목별점검결과 삭제
            subconCheckPatrolMapper.deleteCheckItemRslt(vendorCheckRsltNo);
            // 업체자체점검 점검자 삭제
            subconCheckPatrolMapper.deleteCheckInspectPsn(vendorCheckRsltNo);
            // 개선조치 삭제
            imprService.deleteImprActRefTableId(vendorCheckRsltNo);
            // 업체자체점검결과 삭제
            subconCheckPatrolMapper.deleteCheckResult(vendorCheckRsltNo);
            return 1;
        }
    }
}
