package com.she.psm.PsmDocu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.AttachFile;
import com.she.common.model.DefaultParam;
import com.she.manage.model.CodeMaster;
import com.she.psm.PsmDocu.mapper.PsmDocuMapper;
import com.she.psm.model.DocuGrpNo;
import com.she.psm.model.InterLock;
import com.she.psm.model.PsmDocu;
import com.she.psm.processChart.mapper.InterLockMapper;
import com.she.utils.ConstVal;

/**
 * 인터록 작동조건 및 가동중지 범위 기능정의
 */
@Service
public class PsmDocuService {
    @Autowired
    private InterLockMapper interLockMapper;

    @Autowired
    private PsmDocuMapper psmDocuMapper;

    /**
     * 공정안전문서 목록 조회
     */
    public List<PsmDocu> getPsmDocuments(String plantCd, String categoryCd, String deptCd, String docuTitle, String startYmd, String endYmd, String subCategoryCd, String useYn, String checkEffectYn, String deptSubYn, DefaultParam defaultParam) throws Exception {
        List<PsmDocu> resultList = psmDocuMapper.getPsmDocuments(plantCd, categoryCd, deptCd, docuTitle, startYmd, endYmd, subCategoryCd, useYn, checkEffectYn, deptSubYn, defaultParam);
        if (!resultList.isEmpty()) {
            for (PsmDocu item : resultList) {
                // 첨부파일 추가
                item.setFiles(new ArrayList<AttachFile>());
            }
        }
        return resultList;
    }

    /**
     * PSM Portlet 목록 조회
     */
    public List<PsmDocu> getPsmPortletList(DefaultParam defaultParam) throws Exception {

        List<PsmDocu> resultList = psmDocuMapper.getPsmPortletList(defaultParam);
        return resultList;
    }

    /**
     * sub Category 목록 조회
     */
    public List<CodeMaster> getSubCategory(String code, DefaultParam defaultParam) throws Exception {
        return psmDocuMapper.getSubCategory(code, defaultParam);
    }

    /**
     * 공정안전문서 상세 조회
     */
    public PsmDocu getPsmDocument(int psmDocuNo, DefaultParam defaultParam) throws Exception {
        return psmDocuMapper.getPsmDocument(psmDocuNo, defaultParam);
    }

    /**
     * 공정안전문서 개정이력 조회
     */
    public List<PsmDocu> getRevisionPsmDocument(String docuId, DefaultParam defaultParam) throws Exception {
        return psmDocuMapper.getRevisionPsmDocument(docuId, defaultParam);
    }

    /**
     * 공정안전문서 문서그룹번호 조회
     */
    public List<DocuGrpNo> getDocuGrpNoList() throws Exception {
        return psmDocuMapper.getDocuGrpNoList();
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 중복체크
     *
     * @param interLockSeq
     *            인터록 Seq
     * @return 동일 인터록 수량
     * @throws Exception
     */
    public int checkInterLock(int interLockSeq, String interLockNo) throws Exception {
        return interLockMapper.checkInterLock(interLockSeq, interLockNo);
    }

    /**
     * 공정안전문서 생성
     */
    public int createPsmDocument(PsmDocu psmDocu) throws Exception {
        psmDocuMapper.createPsmDocument(psmDocu);
        return psmDocu.getPsmDocuNo();
    }

    /**
     * 공정안전문서 수정
     */
    public int updatePsmDocument(PsmDocu psmDocu) throws Exception {
        psmDocuMapper.updatePsmDocument(psmDocu);
        return psmDocu.getPsmDocuNo();
    }

    /**
     * 공정안전문서 개정
     */
    public int renewalPsmDocument(PsmDocu psmDocu) throws Exception {
        psmDocuMapper.revisionPsmDocument(psmDocu); // 개정이력 생성

        return psmDocu.getPsmDocuNo();
    }

    /**
     * 공정안전문서 결재
     */
    @Transactional
    public int apprPsmDocument(int psmDocuNo, int apprRqstNo, String bizApprStepCd) throws Exception {
        int result = 0;
        if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_ING)) {
            // 결재중
            String stepCd = ConstVal.COM_PROCESS_STEP_CREATE; // 작성중
            result = psmDocuMapper.updatePsmDocumentForAppr(psmDocuNo, apprRqstNo, stepCd);
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            // 결재완료
            String stepCd = ConstVal.COM_PROCESS_STEP_COMPLTE; // 완료
            result = psmDocuMapper.updatePsmDocumentForAppr(psmDocuNo, apprRqstNo, stepCd);
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
            // 반려는 처리할게 없다..
            result = 1;
        }
        return result;
    }

    /**
     * 공정안전문서 삭제
     */
    public int deletePsmDocument(int psmDocuNo, String delYn) throws Exception {
        psmDocuMapper.deletePsmDocument(psmDocuNo, delYn);
        return psmDocuNo;
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 삭제
     *
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteInterLock(InterLock interLock) throws Exception {
        return interLockMapper.deleteInterLock(interLock);
    }

    /**
     * 부서코드로 상위,하위 부서코드 구하기
     *
     * @param depdCd
     *            부서코드
     *
     * @return 직계부서코드 목록
     * @throws Exception
     */

    public List<HashMap<String, Object>> getDeptHierarchyList(String deptCd) throws Exception {

        // 직계부서코드 조회
        List<HashMap<String, Object>> resultList = psmDocuMapper.getDeptHierarchyList(deptCd);

        return resultList;
    }

    /**
     * psm 이행점검 > 공정안전문서 > 환경안전(사업장공통) 결재요청
     *
     */
    public int apprDeptPlanResult(int psmDocuNo, String bizApprStepCd, int apprRqstNo) throws Exception {
        String stepCd = "";
        if (ConstVal.COM_BIZ_APPR_STEP_REJECT.equals(bizApprStepCd)) {
            // 반려시 상태를 등록으로 변경
            stepCd = ConstVal.MGT_TGT_D_STEP_REG;
            psmDocuMapper.updatePsmDocumentForAppr(psmDocuNo, apprRqstNo, stepCd);
        } else if (ConstVal.COM_BIZ_APPR_STEP_COMPLETE.equals(bizApprStepCd)) {
            // 결재완료시 확정으로 상태 변경
            stepCd = ConstVal.COM_PROCESS_STEP_COMPLTE;
            psmDocuMapper.updatePsmDocumentForAppr(psmDocuNo, apprRqstNo, stepCd);
        } else if (ConstVal.COM_BIZ_APPR_STEP_ING.equals(bizApprStepCd)) {
            // 결재요청
            stepCd = ConstVal.CODE_MASTER_PSM_PROG_STATE_RESULT_APPR_REQUEST;
        }
        return psmDocuMapper.updatePsmDocumentForAppr(psmDocuNo, apprRqstNo, stepCd);
    }

}
