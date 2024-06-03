package com.she.rsa.workRiskEval.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.WorkRiskEval03Rslt;
import com.she.rsa.model.WorkRiskEval03RsltDetailUnitWork;
import com.she.rsa.model.WorkRiskEval03RsltDetailWork;
import com.she.rsa.model.WorkRiskEval03RsltEvalParty;
import com.she.rsa.model.WorkRiskEval03RsltRefInd;
import com.she.rsa.workRiskEval.mapper.WorkRiskEval03Mapper;
import com.she.security.util.StringUtil;

/**
 * 작업위험성평가 결과 기능정의
 *
 */
@Service
public class WorkRiskEval03Service {

    @Autowired
    private WorkRiskEval03Mapper workRiskEval03Mapper;

    /**
     * 작업위험성평가 결과 목록
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 결과 목록
     * @throws Exception
     */
    public List<WorkRiskEval03Rslt> getworkRiskEval03List(String plantCd, String deptCd, String evalTypeCd, String startYear, String endYear, String prcsNm01, String prcsNm02, String prcsNm03, String confirmYn, DefaultParam defaultParam) throws Exception {
        return workRiskEval03Mapper.getworkRiskEval03List(plantCd, deptCd, evalTypeCd, startYear, endYear, prcsNm01, prcsNm02, prcsNm03, confirmYn, defaultParam);
    }

    /**
     * 작업위험성평가 결과 상세
     * 
     * @param evalNo(평가번호),
     *            deptCd(대상부서코드), processCd(주요설비코드), unitWorkCd(작업명)
     * @return 작업위험성평가 결과 상세조회
     * @throws Exception
     */
    public WorkRiskEval03Rslt getWorkRiskEval03Info(String plantCd, String evalYear, String evalNo, String deptCd, String processCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        WorkRiskEval03Rslt workRiskEval03Rslt = workRiskEval03Mapper.getWorkRiskEval03Info(plantCd, evalYear, evalNo, deptCd, processCd, unitWorkCd, defaultParam);

        return workRiskEval03Rslt;
    }

    /**
     * 작업위험성평가 결과 세부작업 조회
     * 
     * @return 작업위험성평가 결과 세부작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval03RsltDetailWork> getDetailWorkList(String plantCd, String deptCd, String evalYear, String evalNo, String processCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval03Mapper.getDetailWorkList(plantCd, deptCd, evalYear, evalNo, processCd, unitWorkCd, defaultParam);
    }

    /**
     * 작업위험성평가 결과 평가참여자 조회
     * 
     * @return 작업위험성평가 결과 평가참여자 목록
     * @throws Exception
     */
    public List<WorkRiskEval03RsltEvalParty> getEvalPartyList(String plantCd, String deptCd, String evalYear, String evalNo, String processCd, String unitWorkCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval03Mapper.getEvalPartyList(plantCd, deptCd, evalYear, evalNo, processCd, unitWorkCd, defaultParam);
    }

    /**
     * 작업위험성평가 결과 등록
     * 
     * @param WorkRiskEval03Rslt
     *            작업위험성평가 결과 관리 List
     * @return 작업위험성평가 결과 관리 Key값
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public String createWorkRiskEval03(WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception {

        workRiskEval03Mapper.updateEvalDeptPrcs(workRiskEval03Rslt);
        // 관련변경관리 처리
        if (workRiskEval03Rslt.getOldRefBizNo() > 0 && workRiskEval03Rslt.getRefBizNo() == 0) {
            // 기존 데이터 삭제처리
            workRiskEval03Mapper.deleteSafChngRefBiz(workRiskEval03Rslt);

            if (StringUtil.isNotEmpty(workRiskEval03Rslt.getSafChngNo())) {
                // 신규 데이터 저장
                workRiskEval03Mapper.insertSafChngRefBiz(workRiskEval03Rslt);
            }
        } else if (workRiskEval03Rslt.getOldRefBizNo() == 0 && workRiskEval03Rslt.getRefBizNo() == 0) {
            if (StringUtil.isNotEmpty(workRiskEval03Rslt.getSafChngNo())) {
                // 신규 데이터 저장
                workRiskEval03Mapper.insertSafChngRefBiz(workRiskEval03Rslt);
            }
        }

        // 평가참여자 처리
        workRiskEval03Mapper.deleteWorkRiskEval03EvalParty(workRiskEval03Rslt);
        if (workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty() != null && workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().size() > 0) {
            for (int i = 0; i < workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().size(); i++) {
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setPlantCd(workRiskEval03Rslt.getPlantCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setEvalYear(workRiskEval03Rslt.getEvalYear());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setEvalNo(workRiskEval03Rslt.getEvalNo());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setDeptCd(workRiskEval03Rslt.getDeptCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setProcessCd(workRiskEval03Rslt.getProcessCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setUnitWorkCd(workRiskEval03Rslt.getUnitWorkCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setCreateUserId(workRiskEval03Rslt.getCreateUserId());
                workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i).setUpdateUserId(workRiskEval03Rslt.getUpdateUserId());

                workRiskEval03Mapper.createWorkRiskEval03EvalParty(workRiskEval03Rslt.getWorkRiskEval03RsltEvalParty().get(i));
            }
        }

        // 단위작업 처리
        if (workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork() != null && workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().size() > 0) {
            // 삭제대상 항목 키값
            Map<String, Object> param = new HashMap<String, Object>();
            List<String> delEmptyList = new ArrayList<String>();

            param.put("plantCd", workRiskEval03Rslt.getPlantCd());
            param.put("evalYear", workRiskEval03Rslt.getEvalYear());
            param.put("evalNo", workRiskEval03Rslt.getEvalNo());
            param.put("deptCd", workRiskEval03Rslt.getDeptCd());
            param.put("processCd", workRiskEval03Rslt.getProcessCd());
            param.put("unitWorkCd", workRiskEval03Rslt.getUnitWorkCd());

            for (int i = 0; i < workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().size(); i++) {
                delEmptyList.add(workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).getDtlWkSeq());
            }
            param.put("delEmptyList", delEmptyList);

            workRiskEval03Mapper.deleteWorkRiskEval03safImprAct(param); // 개선요청항목
                                                                        // 삭제
            workRiskEval03Mapper.deleteWorkRiskEval03DetailWork(param); // 작업위험성평가
                                                                        // 결과 삭제

            for (int i = 0; i < workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().size(); i++) {
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setPlantCd(workRiskEval03Rslt.getPlantCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setEvalYear(workRiskEval03Rslt.getEvalYear());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setEvalNo(workRiskEval03Rslt.getEvalNo());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setDeptCd(workRiskEval03Rslt.getDeptCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setProcessCd(workRiskEval03Rslt.getProcessCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setUnitWorkCd(workRiskEval03Rslt.getUnitWorkCd());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setCreateUserId(workRiskEval03Rslt.getCreateUserId());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setUpdateUserId(workRiskEval03Rslt.getUpdateUserId());
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i).setRsltConfYn(workRiskEval03Rslt.getRsltCfmYn());

                workRiskEval03Mapper.mergeWorkRiskEval03DetailWork(workRiskEval03Rslt.getWorkRiskEval03RsltDetailWork().get(i));
            }
        }

        return workRiskEval03Rslt.getDeptCd();
    }

    /**
     * 작업위험성평가 결과 세부작업 팝업
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 결과 세부작업 팝업 상세조회
     * @throws Exception
     */
    public WorkRiskEval03RsltDetailUnitWork getWorkRiskEval03DtlWorkInfo(String plantCd, String evalYear, String evalNo, String deptCd, String processCd, String unitWorkCd, String dtlWkSeq, DefaultParam defaultParam) throws Exception {
        WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork = workRiskEval03Mapper.getWorkRiskEval03DtlWorkInfo(plantCd, evalYear, evalNo, deptCd, processCd, unitWorkCd, defaultParam);

        if (StringUtil.isNotEmpty(dtlWkSeq)) {
            WorkRiskEval03RsltDetailUnitWork unitWorkAdd = workRiskEval03Mapper.getWorkRiskEval03DtlWorkInfoAdd(plantCd, deptCd, processCd, unitWorkCd, dtlWkSeq, defaultParam);
            workRiskEval03RsltDetailUnitWork.setDtlWkSeq(unitWorkAdd.getDtlWkSeq());
            workRiskEval03RsltDetailUnitWork.setDtlWkNm(unitWorkAdd.getDtlWkNm());
            workRiskEval03RsltDetailUnitWork.setRemark(unitWorkAdd.getRemark());
            workRiskEval03RsltDetailUnitWork.setSortOrder(unitWorkAdd.getSortOrder());
            workRiskEval03RsltDetailUnitWork.setUseYn(unitWorkAdd.getUseYn());
        } else {
            workRiskEval03RsltDetailUnitWork.setUseYn("Y");
        }

        return workRiskEval03RsltDetailUnitWork;
    }

    /**
     * 작업위험성평가 결과 관련지표 콤보
     * 
     * @param parameter
     *            검색조건
     * @return 작업위험성평가 관련지표 콤보 조회
     * @throws Exception
     */
    public List<WorkRiskEval03RsltRefInd> getWorkRiskEval03RefIndItemList(String plantCd, String indTypeCd, DefaultParam defaultParam) throws Exception {
        return workRiskEval03Mapper.getWorkRiskEval03RefIndItemList(plantCd, indTypeCd, defaultParam);
    }

    /**
     * 작업위험성평가 결과 세부작업 등록
     * 
     * @param workRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 관리 Key값
     * @throws Exception
     */
    public String createWorkRiskEval03WorkPop(WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork) throws Exception {

        workRiskEval03Mapper.createWorkRiskEval03WorkPop(workRiskEval03RsltDetailUnitWork);

        return workRiskEval03RsltDetailUnitWork.getDtlWkSeq();
    }

    /**
     * 작업위험성평가 결과 세부작업 수정
     * 
     * @param workRiskEval03RsltDetailUnitWork
     * @return 작업위험성평가 결과 세부작업 관리 Key값
     * @throws Exception
     */
    public String updateWorkRiskEval03WorkPop(WorkRiskEval03RsltDetailUnitWork workRiskEval03RsltDetailUnitWork) throws Exception {

        workRiskEval03Mapper.updateWorkRiskEval03WorkPop(workRiskEval03RsltDetailUnitWork);

        return workRiskEval03RsltDetailUnitWork.getDtlWkSeq();
    }

    /**
     * 작업위험성평가 결과 세부작업 조회
     * 
     * @return 작업위험성평가 결과 세부작업 목록
     * @throws Exception
     */
    public List<WorkRiskEval03RsltDetailUnitWork> getWorkRiskEval03RefIndList(String plantCd, String evalYear, String evalNo, String deptCd, String processCd, String unitWorkCd, String dtlWkNm, DefaultParam defaultParam) throws Exception {
        return workRiskEval03Mapper.getWorkRiskEval03RefIndList(plantCd, evalYear, evalNo, deptCd, processCd, unitWorkCd, dtlWkNm, defaultParam);
    }

    /**
     * 작업위험성평가 공정설정 세부작업 정렬 수정
     * 
     * @param WorkRiskEval03RsltDetailUnitWork
     *            세부작업 List
     * @return 작업위험성평가 세부작업 관리 Key값
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public String updateDetailWorkSortOrder(WorkRiskEval03Rslt workRiskEval03Rslt) throws Exception {
        // 단위작업 처리
        if (workRiskEval03Rslt.getWorkRiskEval03RsltDetailUnitWork() != null && workRiskEval03Rslt.getWorkRiskEval03RsltDetailUnitWork().size() > 0) {
            for (int i = 0; i < workRiskEval03Rslt.getWorkRiskEval03RsltDetailUnitWork().size(); i++) {
                workRiskEval03Rslt.getWorkRiskEval03RsltDetailUnitWork().get(i).setUpdateUserId(workRiskEval03Rslt.getUpdateUserId());

                workRiskEval03Mapper.updateWorkRiskEval03WorkPop(workRiskEval03Rslt.getWorkRiskEval03RsltDetailUnitWork().get(i));
            }
        }

        return workRiskEval03Rslt.getUpdateUserId();
    }
}
