package com.she.psm.processChart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.model.InterLock;
import com.she.psm.processChart.mapper.InterLockMapper;

/**
 * 인터록 작동조건 및 가동중지 범위 기능정의
 */
@Service
public class InterLockService {
    @Autowired
    private InterLockMapper interLockMapper;

    /**
     * 인터록 작동조건 및 가동중지 범위 조회
     * 
     * @param plantCd
     *            사업장
     * @param facilityNm
     *            대상설비
     * @param interLockNo
     *            인터록번호
     * @return 인터록 작동조건 및 가동중지 범위 목록
     * @throws Exception
     */
    public List<InterLock> getInterLockLists(String plantCd, String interLockNo, String facilityNm) throws Exception {
        return interLockMapper.getInterLockLists(plantCd, interLockNo, facilityNm);
    }

    /**
     * 인터록 작동조건 및 가동중지 범위조회
     * 
     * @param interLockSeq
     *            인터록 Seq
     * @return 인터록 작동조건 및 가동중지 범위 상세정보
     * @throws Exception
     */
    public InterLock getInterLock(int interLockSeq) throws Exception {
        return interLockMapper.getInterLock(interLockSeq);
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
     * 인터록 작동조건 및 가동중지 범위 항목 생성
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 인터록 작동조건 및 가동중지 범위 항목 Key값
     * @throws Exception
     */
    public int createInterLock(InterLock interLock) throws Exception {
        interLockMapper.createInterLock(interLock);
        return interLock.getInterLockSeq();
    }

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 수정
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 인터록 작동조건 및 가동중지 범위 항목 Key값
     * @throws Exception
     */
    public int updateInterLock(InterLock interLock) throws Exception {
        interLockMapper.updateInterLock(interLock);
        return interLock.getInterLockSeq();
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
}
