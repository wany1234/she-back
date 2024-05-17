package com.she.psm.buildingFacilitiesLayout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.GasDetector;

@Mapper
@Repository("com.she.psm.buildingFacilitiesLayout.mapper.GasDetectorMapper")
public interface GasDetectorMapper {
    /**
     * 가스누출감지 경보기 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param mgrNum
     *            관리번호
     * @param sensingObj
     *            감지대상
     * @param location
     *            설치위치
     * @return 가스누출감지 경보기 설치계획 목록
     * @throws Exception
     */
    public List<GasDetector> getGasDetectorLists(@Param("plantCd") String plantCd, @Param("mgrNum") String mgrNum,
            @Param("sensingObj") String sensingObj, @Param("location") String location) throws Exception;

    /**
     * 가스누출감지 경보기 설치계획조회
     * 
     * @param gasDetectorNo
     *            운전상태 No
     * @return 가스누출감지 경보기 설치계획 상세정보
     * @throws Exception
     */
    public GasDetector getGasDetector(@Param("gasDetectorNo") int gasDetectorNo) throws Exception;

    /**
     * 가스누출감지 경보기 설치계획 항목 생성
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 가스누출감지 경보기 설치계획 항목 Key값
     * @throws Exception
     */
    public int createGasDetector(GasDetector gasDetector) throws Exception;

    /**
     * 가스누출감지 경보기 설치계획 항목 수정
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 가스누출감지 경보기 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateGasDetector(GasDetector gasDetector) throws Exception;

    /**
     * 가스누출감지 경보기 설치계획 항목 삭제
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteGasDetector(GasDetector gasDetector) throws Exception;
}
