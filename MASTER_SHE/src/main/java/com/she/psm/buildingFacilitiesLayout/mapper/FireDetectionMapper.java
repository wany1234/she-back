package com.she.psm.buildingFacilitiesLayout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.FireDetection;

@Mapper
@Repository("com.she.psm.buildingFacilitiesLayout.mapper.FireDetectionMapper")
public interface FireDetectionMapper {
    /**
     * 화재탐지 및 경보설비 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param location
     *            작업장소
     * @return 화재탐지 및 경보설비 설치계획 목록
     * @throws Exception
     */
    public List<FireDetection> getFireDetectionLists(@Param("plantCd") String plantCd,
            @Param("location") String location) throws Exception;

    /**
     * 화재탐지 및 경보설비 설치계획조회
     * 
     * @param fireDetectionNo
     *            운전상태 No
     * @return 화재탐지 및 경보설비 설치계획 상세정보
     * @throws Exception
     */
    public FireDetection getFireDetection(@Param("fireDetectionNo") int fireDetectionNo) throws Exception;

    /**
     * 화재탐지 및 경보설비 설치계획 항목 생성
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 화재탐지 및 경보설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int createFireDetection(FireDetection fireDetection) throws Exception;

    /**
     * 화재탐지 및 경보설비 설치계획 항목 수정
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 화재탐지 및 경보설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateFireDetection(FireDetection fireDetection) throws Exception;

    /**
     * 화재탐지 및 경보설비 설치계획 항목 삭제
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteFireDetection(FireDetection fireDetection) throws Exception;
}
