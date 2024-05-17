package com.she.psm.buildingFacilitiesLayout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.buildingFacilitiesLayout.mapper.FireProofsMapper;
import com.she.psm.model.FireProofs;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 내화구조명세 기능정의
 */
@Service
public class FireProofsService {

    @Autowired
    private FireProofsMapper fireProofsMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 내화구조명세 조회
     * 
     * @param plantCd
     *            사업장
     * @param category
     *            구분
     * @param fireProofsNum
     *            지역번호
     * @param fireProofsNm
     *            지역명
     * @return 내화구조명세 목록
     * @throws Exception
     */
    public List<FireProofs> getFireProofsLists(String plantCd, String category, String fireProofsNum, String fireProofsNm) throws Exception {
        return fireProofsMapper.getFireProofsLists(plantCd, category, fireProofsNum, fireProofsNm);
    }

    /**
     * 내화구조명세조회
     * 
     * @param fireProofsNo
     *            운전상태 No
     * @return 내화구조명세 상세정보
     * @throws Exception
     */
    public FireProofs getFireProofs(int fireProofsNo) throws Exception {
        return fireProofsMapper.getFireProofs(fireProofsNo);
    }

    /**
     * 내화구조명세 항목 생성
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 내화구조명세 항목 Key값
     * @throws Exception
     */
    public int createFireProofs(FireProofs fireProofs) throws Exception {
        fireProofsMapper.createFireProofs(fireProofs);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(fireProofs.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(fireProofs.getFireProofsNo()));
        changeRefWork.setRefTableNm("saf_fire_proofs");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(fireProofs.getCreaterId());
        changeService.taskChange(changeRefWork);

        return fireProofs.getFireProofsNo();
    }

    /**
     * 내화구조명세 항목 수정
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 내화구조명세 항목 Key값
     * @throws Exception
     */
    public int updateFireProofs(FireProofs fireProofs) throws Exception {
        fireProofsMapper.updateFireProofs(fireProofs);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(fireProofs.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(fireProofs.getFireProofsNo()));
        changeRefWork.setRefTableNm("saf_fire_proofs");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(fireProofs.getUpdaterId());
        changeService.taskChange(changeRefWork);

        return fireProofs.getFireProofsNo();
    }

    /**
     * 내화구조명세 항목 삭제
     * 
     * @param fireProofs
     *            내화구조명세 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteFireProofs(FireProofs fireProofs) throws Exception {
        return fireProofsMapper.deleteFireProofs(fireProofs);
    }
}
