package com.she.psm.buildingFacilitiesLayout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.LocalVentilation;

@Mapper
@Repository("com.she.psm.buildingFacilitiesLayout.mapper.LocalVentilationMapper")
public interface LocalVentilationMapper {
    /**
     * 국소배기장치 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param source
     *            발생원
     * @param processNm
     *            공정또는작업장명
     * @param inoutFlag
     *            실내외구분
     * @param harmfulType
     *            유해물질종류
     * @return 국소배기장치 설치계획 목록
     * @throws Exception
     */
    public List<LocalVentilation> getLocalVentilationLists(@Param("plantCd") String plantCd,
            @Param("source") String source, @Param("processNm") String processNm, @Param("inoutFlag") String inoutFlag,
            @Param("harmfulType") String harmfulType) throws Exception;

    /**
     * 국소배기장치 설치계획조회
     * 
     * @param localVentilationNo
     *            운전상태 No
     * @return 국소배기장치 설치계획 상세정보
     * @throws Exception
     */
    public LocalVentilation getLocalVentilation(@Param("localVentilationNo") int localVentilationNo) throws Exception;

    /**
     * 국소배기장치 설치계획 항목 생성
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 국소배기장치 설치계획 항목 Key값
     * @throws Exception
     */
    public int createLocalVentilation(LocalVentilation localVentilation) throws Exception;

    /**
     * 국소배기장치 설치계획 항목 수정
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 국소배기장치 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateLocalVentilation(LocalVentilation localVentilation) throws Exception;

    /**
     * 국소배기장치 설치계획 항목 삭제
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteLocalVentilation(LocalVentilation localVentilation) throws Exception;
}
