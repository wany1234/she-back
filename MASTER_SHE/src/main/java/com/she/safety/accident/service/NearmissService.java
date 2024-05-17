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
package com.she.safety.accident.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.impr.service.ImprService;
import com.she.safety.accident.mapper.NearmissMapper;
import com.she.safety.model.Nearmiss;
import com.she.safety.model.NearmissOcctype;
import com.she.safety.model.NearmissVictim;
import com.she.utils.ConstVal;

/**
 * 아차사고 기능정의
 *
 */
@Service
public class NearmissService {
    @Autowired
    private NearmissMapper nearmissMapper;

    @Autowired
    private ImprService imprService;

    /**
     * 아차사고 목록 조회
     * 
     * @param startDate
     *            시작날짜
     * @param endDate
     *            끝날짜
     * @param deptCd
     *            부서코드
     * @param area
     *            장소명
     * @param nearmissTitle
     *            아차사고명
     * @param processStepCd
     *            진행단계코드
     * @param occTypeCd
     *            사고유형코드
     * @return 아차사고 목록
     * @throws Exception
     */
    public List<Nearmiss> getNearmissList(String startDate, String endDate, String deptCd, String deptSubYn, String area, String nearmissTitle, String processStepCd, String occTypeCd, String plantCd, String accidentType, String occKindCd, String occAttCd, String immCauseCd, String indCauseCd, DefaultParam defaultParam) throws Exception {
        return nearmissMapper.getNearmissList(startDate, endDate, deptCd, deptSubYn, area, nearmissTitle, processStepCd, occTypeCd, plantCd, accidentType, occKindCd, occAttCd, immCauseCd, indCauseCd, defaultParam);
    }

    /**
     * 아차사고 상세 조회
     * 
     * @param safNearmissNo
     *            아차사고 번호
     * @return 아차사고
     * @throws Exception
     */
    public Nearmiss getNearmiss(int safNearmissNo, DefaultParam defaultParam) throws Exception {
        // 아차사고 상세 조회
        Nearmiss nearmiss = nearmissMapper.getNearmiss(safNearmissNo);
        // 아차사고 사고자 목록 조회
        nearmiss.setNearmissVictimList(nearmissMapper.getNearmissVictim(safNearmissNo));
        // 아차사고 발생유형 목록 조회
        nearmiss.setNearmissOcctypeList(nearmissMapper.getNearmissOcctype(safNearmissNo, defaultParam));
        return nearmiss;
    }

    /**
     * 아차사고 신규
     * 
     * @param 아차사고
     * @return 아차사고번호
     * @throws Exception
     */
    @Transactional
    public int createNearmiss(Nearmiss nearmiss) throws Exception {
        if (nearmiss != null) {
            // 아치사고 정보 등록
            nearmiss.setProcessStepCd(ConstVal.SAF_NEARMISS_STEP_CREATE); // 진행단계
                                                                          // 작성중
            int result = nearmissMapper.createNearmiss(nearmiss);
            if (result > 0) {
                if (CollectionUtils.isNotEmpty(nearmiss.getNearmissVictimList())) {
                    // 아차사고 사고자 정보 목록 등록
                    for (NearmissVictim nearmissVictim : nearmiss.getNearmissVictimList()) {
                        nearmissVictim.setSafNearmissNo(nearmiss.getSafNearmissNo());
                        nearmissMapper.createNearmissVictim(nearmissVictim);
                    }
                }
                if (CollectionUtils.isNotEmpty(nearmiss.getNearmissOcctypeList())) {
                    // 아차사고 발생유형 목록 등록
                    for (NearmissOcctype nearmissOcctype : nearmiss.getNearmissOcctypeList()) {
                        nearmissOcctype.setSafNearmissNo(nearmiss.getSafNearmissNo());
                        nearmissMapper.createNearmissOcctype(nearmissOcctype);
                    }
                }
                return nearmiss.getSafNearmissNo();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 아차사고 수정
     * 
     * @param 아차사고
     * @return 변경 행 수
     * @throws Exceptions
     */
    @Transactional
    public int updateNearmiss(Nearmiss nearmiss) throws Exception {
        int result = nearmissMapper.updateNearmiss(nearmiss);
        if (result > 0) {
            if (CollectionUtils.isNotEmpty(nearmiss.getNearmissVictimList())) {
                // 아차사고 사고자 목록 삭제
                nearmissMapper.deleteNearmissVictim(nearmiss.getSafNearmissNo());
                // 아차사고 사고자 정보 목록 등록
                for (NearmissVictim nearmissVictim : nearmiss.getNearmissVictimList()) {
                    nearmissVictim.setSafNearmissNo(nearmiss.getSafNearmissNo());
                    nearmissMapper.createNearmissVictim(nearmissVictim);
                }
            }
            if (CollectionUtils.isNotEmpty(nearmiss.getNearmissOcctypeList())) {
                // 아차사고 발생유형 목록 삭제
                nearmissMapper.deleteNearmissOcctype(nearmiss.getSafNearmissNo());
                // 아차사고 발생유형 목록 등록
                for (NearmissOcctype nearmissOcctype : nearmiss.getNearmissOcctypeList()) {
                    nearmissOcctype.setSafNearmissNo(nearmiss.getSafNearmissNo());
                    nearmissMapper.createNearmissOcctype(nearmissOcctype);
                }
            }
        }
        return result;
    }

    /**
     * 아차사고 완료
     * 
     * @param 아차사고
     * @return 변경 행 수
     * @throws Exceptions
     */
    @Transactional
    public int compleNearmiss(Nearmiss nearmiss) throws Exception {
        // 진행단계 완료
        nearmiss.setProcessStepCd(ConstVal.SAF_NEARMISS_STEP_REGISTRATION);
        int result = nearmissMapper.compleNearmiss(nearmiss);
        if (result > 0) {
            if (CollectionUtils.isNotEmpty(nearmiss.getNearmissVictimList())) {
                // 아차사고 사고자 목록 삭제
                nearmissMapper.deleteNearmissVictim(nearmiss.getSafNearmissNo());
                // 아차사고 사고자 정보 목록 등록
                for (NearmissVictim nearmissVictim : nearmiss.getNearmissVictimList()) {
                    nearmissVictim.setSafNearmissNo(nearmiss.getSafNearmissNo());
                    nearmissMapper.createNearmissVictim(nearmissVictim);
                }
            }
            if (CollectionUtils.isNotEmpty(nearmiss.getNearmissOcctypeList())) {
                // 아차사고 발생유형 목록 삭제
                nearmissMapper.deleteNearmissOcctype(nearmiss.getSafNearmissNo());
                // 아차사고 발생유형 목록 등록
                for (NearmissOcctype nearmissOcctype : nearmiss.getNearmissOcctypeList()) {
                    nearmissOcctype.setSafNearmissNo(nearmiss.getSafNearmissNo());
                    nearmissMapper.createNearmissOcctype(nearmissOcctype);
                }
            }
            // 개선사항 완료처리
            imprService.updateImprStepCd("ICL02", nearmiss.getSafNearmissNo(), nearmiss.getUpdateUserId());
        }
        return result;
    }

    /**
     * 아차 사고 삭제
     * 
     * @param safNearmissNo
     *            아차사고번호
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deleteNearmiss(int safNearmissNo) throws Exception {
        // 아차사고 사고자 목록 삭제
        nearmissMapper.deleteNearmissVictim(safNearmissNo);
        // 아차사고 발생유형 목록 삭제
        nearmissMapper.deleteNearmissOcctype(safNearmissNo);
        // 개선사항 정보 삭제
        nearmissMapper.deleteImprAct(safNearmissNo);
        // 아차사고 정보 삭제
        return nearmissMapper.deleteNearmiss(safNearmissNo);
    }

    /**
     * 아차사고 결재
     * 
     * @param safNearmissNo
     * @param apprRqstNo
     * @param bizApprStepCd
     * @return
     * @throws Exception
     */
    @Transactional
    public int apprNearmiss(int safNearmissNo, int apprRqstNo, String bizApprStepCd) throws Exception {
        int result = 0;
        Nearmiss nearmiss = new Nearmiss();
        nearmiss.setSafNearmissNo(safNearmissNo);
        nearmiss.setApprRqstNo(apprRqstNo);
        if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_ING)) {
            // 결재중
            result = nearmissMapper.apprNearmiss(nearmiss);
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_COMPLETE)) {
            // 결재완료
            imprService.updateImprStepCd("ICL02", nearmiss.getSafNearmissNo(), "");
            nearmiss.setProcessStepCd(ConstVal.SAF_NEARMISS_STEP_REGISTRATION); // 진행단계(등록완료)
            result = nearmissMapper.apprNearmiss(nearmiss);
        } else if (StringUtils.equals(bizApprStepCd, ConstVal.COM_BIZ_APPR_STEP_REJECT)) {
            // 반려는 처리할게 없다..
            result = 1;
        }
        return result;
    }

}
