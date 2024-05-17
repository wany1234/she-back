package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.FacilityInsType;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.FacilityInsTypeMapper")
public interface FacilityInsTypeMapper {

    /**
     * 시설유형 조회
     *
     * @param facilityTypeNm
     *            시설유형명
     * @return 시설유형 목록
     * @throws Exception
     */
    public List<FacilityInsType> getFacilityTypeList(@Param("facilityTypeNm") String facilityTypeNm) throws Exception;

    /**
     * 시설유형 상세 조회
     *
     * @param facilityTypeCd
     *            시설유형코드
     * @return 시설유형
     * @throws Exception
     */
    public FacilityInsType getFacilityType(@Param("facilityTypeCd") String facilityTypeCd) throws Exception;

    /**
     * 시설유형 등록
     *
     * @param FacilityInsType
     *            시설유형
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilityInsType(FacilityInsType facilityInsType) throws Exception;

    /**
     * 시설유형 수정
     *
     * @param FacilityInsType
     *            시설유형
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityInsType(FacilityInsType facilityInsType) throws Exception;

    /**
     * 시설유형코드 체크
     *
     * @param facilityTypeCd
     *            시설유형코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityInsType(@Param("facilityTypeCd") String facilityTypeCd)
            throws Exception;

}
