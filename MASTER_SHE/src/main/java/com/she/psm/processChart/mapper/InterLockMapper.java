package com.she.psm.processChart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.InterLock;

@Mapper
@Repository("com.she.psm.processChart.mapper.InterLockMapper")
public interface InterLockMapper {
    /**
     * 인터록 작동조건 및 가동중지 범위조회
     * 
     * @param plantCd
     *            사업장
     * @param facilityNm
     *            대상설비
     * @param mgrVar
     *            관리변수
     * @param loopNum
     *            루프번호
     * @param detectorNum
     *            감지기번호
     * @param useYn
     *            사용여부
     * @return 인터록 작동조건 및 가동중지 범위 목록
     * @throws Exception
     */
    public List<InterLock> getInterLockLists(@Param("plantCd") String plantCd, @Param("interLockNo") String interLockNo,
            @Param("facilityNm") String facilityNm) throws Exception;

    /**
     * 인터록 작동조건 및 가동중지 범위조회
     * 
     * @param interLockSeq
     *            운전상태 No
     * @return 인터록 작동조건 및 가동중지 범위 상세정보
     * @throws Exception
     */
    public InterLock getInterLock(@Param("interLockSeq") int interLockSeq) throws Exception;

    /**
     * 인터록 작동조건 및 가동중지 범위 중복체크
     * 
     * @param interLockSeq
     *            운전상태 No
     * @return 동일 인터록 수량
     * @throws Exception
     */
    public int checkInterLock(@Param("interLockSeq") int interLockSeq, @Param("interLockNo") String interLockNo)
            throws Exception;

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 생성
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 인터록 작동조건 및 가동중지 범위 항목 Key값
     * @throws Exception
     */
    public int createInterLock(InterLock interLock) throws Exception;

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 수정
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 인터록 작동조건 및 가동중지 범위 항목 Key값
     * @throws Exception
     */
    public int updateInterLock(InterLock interLock) throws Exception;

    /**
     * 인터록 작동조건 및 가동중지 범위 항목 삭제
     * 
     * @param interLock
     *            인터록 작동조건 및 가동중지 범위 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteInterLock(InterLock interLock) throws Exception;
}
