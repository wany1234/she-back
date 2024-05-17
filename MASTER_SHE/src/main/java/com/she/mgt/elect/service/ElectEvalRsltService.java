package com.she.mgt.elect.service;

import com.she.common.model.DefaultParam;
import com.she.mgt.elect.mapper.ElectEvalRsltMapper;
import com.she.mgt.model.ElectEvalRslt;
import com.she.mgt.model.ElectEvalRsltItem;
import com.she.mgt.model.ElectEvalRsltStatus;
import com.she.utils.ConstVal;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ElectEvalRsltService {
    @Autowired
    private ElectEvalRsltMapper electEvalRsltMapper;

    /**
     * 법정선임자 본인평가결과 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param halfTypeCd
     *          구분코드
     * @param plantCd
     *          사업장
     * @param safElectTitlNo
     *          법정선임자 번호
     * @param evalNm
     *          평가명
     * @param deptCd
     *          주관부서 코드
     * @param deptSubYn
     *          하위부서 조회 여부
     * @param userId
     *          대상자
     * @param meUserId
     *          본인평가자
     * @param procStepCd
     *          단계코드
     * @param stateCd
     *          상태코드
     * @param evalIncompleteYn
     *          평가 미완료 여부
     * @return 법정선임자 본인평가 목록
     * @throws Exception
     */
    public List<ElectEvalRslt> getElectEvalMeRslts(String startYear, String endYear, String halfTypeCd, String plantCd, int safElectTitlNo, String evalNm, String deptCd, String deptSubYn, String userId, String meUserId, String procStepCd, String stateCd, String evalIncompleteYn, int evalPlanNo, DefaultParam defaultParam) throws Exception {
        return electEvalRsltMapper.getElectEvalMeRslts(startYear, endYear, halfTypeCd, plantCd, safElectTitlNo, evalNm, deptCd, deptSubYn, userId, meUserId, procStepCd, stateCd, evalIncompleteYn, evalPlanNo, defaultParam);
    }

    /**
     * 법정선임자 본인평가결과 상세 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 법정선임자 본인평가결과 상세
     * @throws Exception
     */
    public ElectEvalRslt getElectEvalMeRslt(int evalUserNo, DefaultParam defaultParam) throws Exception {
        ElectEvalRslt electEvalRslt = electEvalRsltMapper.getElectEvalRslt(evalUserNo, defaultParam);
        electEvalRslt.setElectEvalRsltItems(electEvalRsltMapper.getElectEvalMeRsltItems(evalUserNo));
        return electEvalRslt;
    }

    /**
     * 법정선임자 본인평가결과 저장
     * @param electEvalRslt
     *          평가결과
     * @return 저장
     * @throws Exception
     */
    @Transactional
    public ElectEvalRslt saveElectEvalMeRslt(ElectEvalRslt electEvalRslt) throws Exception {
        // 확정일 경우 상위평가의 진행상태를 미작성으로 업데이트
        if (electEvalRslt.getMeProcStep().equals(ConstVal.SAF_PROCESS_STEP2_END)) {
            electEvalRslt.setUpProcStep(ConstVal.SAF_PROCESS_STEP2_UNWRITTEN);
        }

        electEvalRsltMapper.updateElectEvalMeRslt(electEvalRslt);

        // 확정일 경우 평가계획 테이블의 진행상태를 상위평가 상태로 업데이트
        if (electEvalRslt.getMeProcStep().equals(ConstVal.SAF_PROCESS_STEP2_END)) {
            electEvalRsltMapper.updateProcess(electEvalRslt);
        }

        if (CollectionUtils.isNotEmpty(electEvalRslt.getElectEvalRsltItems())) {
            for (ElectEvalRsltItem electEvalRsltItem : electEvalRslt.getElectEvalRsltItems()) {
                electEvalRsltMapper.saveElectEvalMeRsltItem(electEvalRsltItem);
            }
        }

        return electEvalRslt;
    }

    /**
     * 본인평가결과 확정취소
     * @param electEvalRslt
     *          평가결과
     * @return 결과
     * @throws Exception
     */
    @Transactional
    public ElectEvalRslt cancelElectEvalMeRslt(ElectEvalRslt electEvalRslt) throws Exception {
        electEvalRsltMapper.cancelElectEvalMeRslt(electEvalRslt);
        return electEvalRslt;
    }

    /*********************************************************** 상위평가결과  ************************************************************/

    /**
     * 법정선임자 상위평가결과 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param halfTypeCd
     *          구분코드
     * @param plantCd
     *          사업장
     * @param safElectTitlNo
     *          법정선임자 번호
     * @param evalNm
     *          평가명
     * @param deptCd
     *          주관부서 코드
     * @param deptSubYn
     *          하위부서 조회 여부
     * @param userId
     *          대상자
     * @param meUserId
     *          본인평가자
     * @param procStepCd
     *          단계코드
     * @param stateCd
     *          상태코드
     * @param evalIncompleteYn
     *          평가 미완료 여부
     * @return 법정선임자 본인평가 목록
     * @throws Exception
     */
    public List<ElectEvalRslt> getElectEvalUpRslts(String startYear, String endYear, String halfTypeCd, String plantCd, int safElectTitlNo, String evalNm, String deptCd, String deptSubYn, String userId, String upUserId, String procStepCd, String stateCd, String evalIncompleteYn, int evalPlanNo, DefaultParam defaultParam) throws Exception {
        return electEvalRsltMapper.getElectEvalUpRslts(startYear, endYear, halfTypeCd, plantCd, safElectTitlNo, evalNm, deptCd, deptSubYn, userId, upUserId, procStepCd, stateCd, evalIncompleteYn, evalPlanNo, defaultParam);
    }

    /**
     * 법정선임자 상위평가결과 상세 조회
     * @param evalUserNo
     *          평가대상자 번호
     * @return 법정선임자 상위평가결과 상세
     * @throws Exception
     */
    public ElectEvalRslt getElectEvalUpRslt(int evalUserNo, DefaultParam defaultParam) throws Exception {
        ElectEvalRslt electEvalRslt = electEvalRsltMapper.getElectEvalRslt(evalUserNo, defaultParam);
        electEvalRslt.setElectEvalRsltItems(electEvalRsltMapper.getElectEvalUpRsltItems(evalUserNo));
        return electEvalRslt;
    }

    /**
     * 법정선임자 상위평가결과 저장/확정/확정취소
     * @param electEvalRslt
     *          평가결과
     * @return 저장
     * @throws Exception
     */
    @Transactional
    public ElectEvalRslt saveElectEvalUpRslt(ElectEvalRslt electEvalRslt) throws Exception {
        electEvalRsltMapper.updateElectEvalUpRslt(electEvalRslt);

        if (CollectionUtils.isNotEmpty(electEvalRslt.getElectEvalRsltItems())) {
            for (ElectEvalRsltItem electEvalRsltItem : electEvalRslt.getElectEvalRsltItems()) {
                electEvalRsltMapper.saveElectEvalUpRsltItem(electEvalRsltItem);
            }
        }

        return electEvalRslt;
    }

    /**
     * 평가결과 현황 목록 조회
     * @param startYear
     *          시작년도
     * @param endYear
     *          종료년도
     * @param plantCd
     *          사업장
     * @param halfTypeCd
     *          구분
     * @param safElectTitlNo
     *          법정선임자 번호
     * @return 평가결과 현황 목록 조회
     * @throws Exception
     */
    public List<ElectEvalRsltStatus> getElectEvalRsltStatus(String startYear, String endYear, String plantCd, String halfTypeCd, int safElectTitlNo) throws Exception {
        return electEvalRsltMapper.getElectEvalRsltStatus(startYear, endYear, plantCd, halfTypeCd, safElectTitlNo);
    }
}
