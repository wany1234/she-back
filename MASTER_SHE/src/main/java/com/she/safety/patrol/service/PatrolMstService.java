package com.she.safety.patrol.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.safety.model.Patrol;
import com.she.safety.model.PatrolMaster;
import com.she.safety.patrol.mapper.PatrolMapper;
import com.she.safety.patrol.mapper.PatrolMstMapper;
import com.she.utils.ConstVal;

@Service
public class PatrolMstService {

    @Autowired
    private PatrolMstMapper mapper;

    @Autowired
    private PatrolMapper mstMapper;

    public List<PatrolMaster> getPatrolMsts(String startDate, String endDate, String deptCd, String deptSubYn, String tgtDeptCd, String tgtDeptSubYn, String pgpDeptCd, String pgpDeptSubYn,String tgtVendorCd, String plantCd, String stepCd, int safCheckKindNo, String keyword) throws Exception {
        return mapper.getPatrolMsts(startDate, endDate, deptCd, deptSubYn, tgtDeptCd, tgtDeptSubYn, pgpDeptCd, pgpDeptSubYn,tgtVendorCd, plantCd, stepCd, safCheckKindNo, keyword);
    } 

    public PatrolMaster getPatrolMst(int patrolMstNo) throws Exception {
        PatrolMaster master = mapper.getPatrolMst(patrolMstNo);
        if (CollectionUtils.isNotEmpty(mapper.getPatrolPlans(patrolMstNo))) {
            List<Patrol> list = mapper.getPatrolPlans(patrolMstNo);
            for (Patrol patrol : list) {
                if (patrol.getTgtDeptCd() != null
                        && (patrol.getTgtVendorCd() == null || patrol.getTgtVendorCd().equals(""))) {
                    patrol.setTgtCd(patrol.getTgtDeptCd());
                    patrol.setTgtNm(patrol.getTgtDeptNm());
                    patrol.setIsDept("Y");
                } else if (patrol.getTgtVendorCd() != null
                        && (patrol.getTgtDeptCd() == null || patrol.getTgtDeptCd().equals(""))) {
                    patrol.setTgtCd(patrol.getTgtVendorCd());
                    patrol.setTgtNm(patrol.getTgtVendorNm());
                    patrol.setIsDept("N");
                }
            }
            master.setPatrolList(list);
        }
        return master;
    }

    /**
     * 안전점검일정 등록
     * 
     * @param patrolMaster
     * @return
     * @throws Exception
     */
    @Transactional
    public int createPatrolMst(PatrolMaster patrolMaster) throws Exception {
        int result = 0;

        if (patrolMaster == null) {
            return 0;
        } else {
            // 안전점검 마스터 등록
            patrolMaster.setPatrolMstNo(this.getSequenceNumber());
            result = mapper.createPatrolMst(patrolMaster);
            if (result <= 0) {
                return 0;
            } else {
                // 순회점검 일정 등록
                if (CollectionUtils.isNotEmpty(patrolMaster.getPatrolList())) {
                    for (Patrol patrol : patrolMaster.getPatrolList()) {
                        patrol.setPlantCd(patrolMaster.getPlantCd());
                        patrol.setPatrolMstNo(patrolMaster.getPatrolMstNo()); // 안전점검일련번호
                        patrol.setCheckStepCd(ConstVal.SAF_CHK_STEP_SCHEDULE_CD); // 점검진행상태(일정)
//                        patrol.setPgpDeptCd(patrolMaster.getPgpDeptCd());
                                                                                  // ->
                                                                                  // 일정수립
                        patrol.setCreateUserId(patrolMaster.getCreateUserId()); // 등록자
                        patrol.setSafCheckKindNo(patrolMaster.getSafCheckKindNo()); // 순회점검종류
                        if (patrol.getIsDept().equals("Y")) {
                            patrol.setTgtDeptCd(patrol.getTgtCd());
                            patrol.setTgtDeptNm(patrol.getTgtNm());
//                            patrol.setPgpDeptCd(patrol.getPgpCd());
//                            patrol.setPgpDeptNm(patrol.getPgpNm());
                            patrol.setTgtClsCd("ACTDP");
                            patrol.setDeptCd(patrolMaster.getDeptCd());
                        } else {
                            patrol.setTgtVendorCd(patrol.getTgtCd());
                            patrol.setTgtVendorNm(patrol.getTgtNm());
//                            patrol.setPgpDeptCd(patrol.getPgpCd());
//                            patrol.setPgpDeptNm(patrol.getPgpNm());
                            patrol.setTgtClsCd("ACTSC");
                            patrol.setDeptCd(patrolMaster.getDeptCd());
                        }
                        mstMapper.createPatrol(patrol);
                    }
                }
                return patrolMaster.getPatrolMstNo();
            }
        }
    }

    public int getSequenceNumber() throws Exception {
        return mapper.getSequenceNumber();
    }

    /**
     * 안전점검일정 수정
     * 
     * @param patrolMaster
     * @return
     * @throws Exception
     */
    @Transactional
    public int updatePatrolMst(PatrolMaster patrolMaster) throws Exception {
        int result = 0;
        if (patrolMaster == null) {
            return 0;
        } else {
            // 안전점검 마스터 등록
            result = mapper.updatePatrolMst(patrolMaster);

            if (result <= 0) {
                return 0;
            } else {
                // 순회점검 일정 등록
                if (CollectionUtils.isNotEmpty(patrolMaster.getPatrolList())) {
                    mstMapper.deleteMasterPatrol(patrolMaster.getPatrolMstNo());
                    for (Patrol patrol : patrolMaster.getPatrolList()) {
                        patrol.setPlantCd(patrolMaster.getPlantCd());
                        patrol.setPatrolMstNo(patrolMaster.getPatrolMstNo()); // 순회점검일련번호
                        patrol.setSafCheckKindNo(patrolMaster.getSafCheckKindNo()); // 순회점검종류
                        patrol.setCheckStepCd(ConstVal.SAF_CHK_STEP_SCHEDULE_CD); // 점검진행상태(일정)
//                        patrol.setPgpDeptCd(patrolMaster.getPgpDeptCd());
                                                                                  // ->
                                                                                  // 일정수립
                        patrol.setCreateUserId(patrolMaster.getCreateUserId()); // 등록자
                        if (patrol.getIsDept().equals("Y")) {
                            patrol.setTgtDeptCd(patrol.getTgtCd());
                            patrol.setTgtDeptNm(patrol.getTgtNm());
//                            patrol.setPgpDeptCd(patrol.getPgpCd());
//                            patrol.setPgpDeptNm(patrol.getPgpNm());
                            patrol.setTgtClsCd("ACTDP");
                            patrol.setDeptCd(patrolMaster.getDeptCd());
                        } else {
                            patrol.setTgtVendorCd(patrol.getTgtCd());
                            patrol.setTgtVendorNm(patrol.getTgtNm());
//                            patrol.setPgpDeptCd(patrol.getPgpCd());
//                            patrol.setPgpDeptNm(patrol.getPgpNm());
                            patrol.setTgtClsCd("ACTSC");
                            patrol.setDeptCd(patrolMaster.getDeptCd());
                        }
                        mstMapper.createPatrol(patrol);
                    }
                }
                return patrolMaster.getPatrolMstNo();
            }
        }
    }

    public int completePatrolMst(int patrolMstNo) throws Exception {
        mapper.updatePlanStep(patrolMstNo);
        return mapper.completePatrolMst(patrolMstNo);
    }

    public int deletePatrolMst(int patrolMstNo) throws Exception {
        return mapper.deletePatrolMst(patrolMstNo);
    }
}
