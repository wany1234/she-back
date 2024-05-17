package com.she.safety.patrol.service;

import java.util.HashMap;
import java.util.List;

import com.she.impr.service.ImprService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.safety.model.Patrol;
import com.she.safety.model.PatrolInspector;
import com.she.safety.model.PatrolItemResult;
import com.she.safety.patrol.mapper.PatrolMapper;
import com.she.safety.patrol.mapper.PatrolMstMapper;
import com.she.utils.ConstVal;

/**
 * 순회 기능정의
 *
 */
@Service
public class PatrolService {
    @Autowired
    private PatrolMapper patrolMapper;
    @Autowired
    private PatrolMstMapper mapper;
    @Autowired
    private ImprService imprService;

    /**
     * 순회 조회
     * 
     * @param plantCd
     *            사업장
     * @param startDate
     *            from
     * @param endDate
     *            to
     * @param deptCd
     *            순회주관부서
     * @param safCheckKindNo
     *            순회종류
     * @param checkStepCd
     *            진행단계
     * @param tgtClsCd
     *            순회대상
     * @param tgtDeptCd
     *            순회대상부서
     * @param tgtVendorCd
     *            순회대상업체
     * @param bizApprStepCd
     *            결재진행상태
     * @return 순회 목록
     * @throws Exception
     */
    public List<Patrol> getPatrols(String tgtVendorNm, String checkResultCd, String plantCd, String startDate, String endDate, String startDateRes, String endDateRes, String deptCd, String deptSubYn, int safCheckKindNo, String checkStepCd, String tgtClsCd, String tgtDeptCd, String tgtDeptSubYn, String pgpDeptCd, String pgpDeptSubYn,String tgtVendorCd, String bizApprStepCd,
            String userNm, String checkTitle) throws Exception {
        return patrolMapper.getPatrols(tgtVendorNm, checkResultCd, plantCd, startDate, endDate, startDateRes, endDateRes, deptCd, deptSubYn, safCheckKindNo, checkStepCd, tgtClsCd, tgtDeptCd, tgtDeptSubYn, pgpDeptCd, pgpDeptSubYn,tgtVendorCd, bizApprStepCd, userNm, checkTitle);
    }

    /**
     * 순회 상세조회
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 순회
     * @throws Exception
     */
    
    public Patrol getPatrol(int safCheckRsltNo) throws Exception {
       Patrol patrol = patrolMapper.getPatrol(safCheckRsltNo);
 
      // 점검자
       patrol.setPatrolInspectors(this.getCheckInspectors(safCheckRsltNo, null));
 
       // 계획상태인 경우에는 점검결과를 조회하지 않음
       if (!ConstVal.SAF_CHK_STEP_PLAN_CD.equals(patrol.getCheckStepCd())) {
           // 점검결과
           patrol.setPatrolItems(this.getPatrolResultItems(safCheckRsltNo, patrol.getSafCheckKindNo()));
       }
       return patrol;
   }
    

    /**
     * 순회 등록
     * 
     * @param patrol
     *            순회
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createPatrol(Patrol patrol) throws Exception {
        // 순회 등록
        patrolMapper.createPatrol(patrol);
        // 순회점검자 등록
        if (CollectionUtils.isNotEmpty(patrol.getPatrolInspectors())) {
            for (PatrolInspector inspector : patrol.getPatrolInspectors()) {
                inspector.setSafCheckRsltNo(patrol.getSafCheckRsltNo());
                patrolMapper.createPlanPsn(inspector);
            }
        }
        // 순회결과 등록
        if (CollectionUtils.isNotEmpty(patrol.getPatrolItems())) {
            for (PatrolItemResult patrolItemResult : patrol.getPatrolItems()) {
                patrolItemResult.setSafCheckRsltNo(patrol.getSafCheckRsltNo());
                patrolMapper.createPatrolResultItem(patrolItemResult);
            }
        }
        return patrol.getSafCheckRsltNo();
    }

    /**
     * 순회 수정
     * 
     * @param patrol
     *            순회
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updatePatrol(Patrol patrol) throws Exception {
        int resultNo = 0;
        // 순회 수정
        resultNo += patrolMapper.updatePatrol(patrol);

        if (patrol.getCheckStepCd().equals("CHS04")) {
            int requestImprovementCount = imprService.updateImprStepCd(ConstVal.SAF_IMPR_PATROL_RESULT, patrol.getSafCheckRsltNo(), patrol.getUpdateUserId());
        }

        // 순회점검자 등록
        if (patrol.getPatrolInspectors() != null) {
            patrolMapper.deletePlanPsn(patrol.getSafCheckRsltNo());
            if (patrol.getPatrolInspectors().size() > 0) {
                for (PatrolInspector inspector : patrol.getPatrolInspectors()) {
                    inspector.setSafCheckRsltNo(patrol.getSafCheckRsltNo());
                    resultNo += patrolMapper.createPlanPsn(inspector);
                }
            }
        }
        // 순회결과 등록
        if (patrol.getPatrolItems() != null) {
            patrolMapper.deletePatrolResultItem(patrol.getSafCheckRsltNo());
            if (patrol.getPatrolItems().size() > 0) {
                for (PatrolItemResult patrolItemResult : patrol.getPatrolItems()) {
                    patrolItemResult.setSafCheckRsltNo(patrol.getSafCheckRsltNo());
                    resultNo += patrolMapper.createPatrolResultItem(patrolItemResult);
                }
            }
        }
        return patrol.getSafCheckRsltNo();
    }

    /**
     * 순회 삭제
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deletePatrol(int safCheckRsltNo) throws Exception {
        int resultNo = patrolMapper.deletePatrolResultItem(safCheckRsltNo);
        resultNo += patrolMapper.deletePlanPsn(safCheckRsltNo);
        resultNo += patrolMapper.deletePatrol(safCheckRsltNo);
        return resultNo;
    }

    /**
     * 순회 조회
     * 
     * @param safCheckRsltNo
     *            순회번호
     * @param safCheckKindNo
     *            순회종류
     * @return 순회 결과 목록
     * @throws Exception
     */
    public List<PatrolItemResult> getPatrolResultItems(int safCheckRsltNo, int safCheckKindNo) throws Exception {
        List<PatrolItemResult> patrolItemResults = patrolMapper.getPatrolResultItems(safCheckRsltNo);
        // 저장된 데이터 값이 있는 경우
        if (patrolItemResults != null && patrolItemResults.size() > 0) {
            // 저장된 데이터값에 순회종류의 값이 현재 순회의 순회종류값과 동일한지 체크
            for (PatrolItemResult patrolItemResult : patrolItemResults) {
                // 동일한 값이 들어 있다면 저장된 데이터 값을 그대로 가져가 사용
                if (safCheckKindNo == patrolItemResult.getSafCheckKindNo()) {
                    return patrolItemResults;
                }
            }
            // 동일한 값이 없어 반환 되지 않은 경우에는 현재 순회의 순회:종류값에 따른 상세항목들을 저장된 값에 붙인다.
            patrolItemResults.addAll(patrolMapper.getNewPatrolResultItems(safCheckKindNo));
        } else {
            // 저장된 값이 없는 경우에는 현재 순회의 순회종류값에 따른 상세항목들을 가지고 온다. (사용하는 것들만)
            patrolItemResults = patrolMapper.getNewPatrolResultItems(safCheckKindNo);
        }
        return patrolItemResults;
    }

    public List<HashMap<String, Object>> getPatrolStatus(String plantCd, String year) throws Exception {
        return patrolMapper.getPatrolStatus(plantCd, year);
    }

    public int completePatrolResultPlan(int safCheckRsltNo, int patrolMstNo) throws Exception {

        patrolMapper.completePatrolResultPlan(safCheckRsltNo);

        int count = patrolMapper.countPatrolList(patrolMstNo, "CHS04");
        int count2 = patrolMapper.countPatrolList(patrolMstNo, "9999");

        if (count >= count2) {
            mapper.completeResultPatrolMst(patrolMstNo);
        }
        return 1;
    }

    public List<HashMap<String, Object>> getPatrolStatusSub(String plantCd, String year, String deptCd, String tgtClsCd, String tgtDeptCd, String tgtVendorCd, String checkResultCd) throws Exception {
        return patrolMapper.getPatrolStatusSub(plantCd, year, deptCd, tgtClsCd, tgtDeptCd, tgtVendorCd, checkResultCd);
    }

    
    
    // 훙냐리훙냐리
	public List<PatrolInspector> getCheckInspectors(int safCheckRsltNo, String inspectorClassCd) throws Exception {
		return patrolMapper.getCheckInspectors(safCheckRsltNo, inspectorClassCd);
	}

}
