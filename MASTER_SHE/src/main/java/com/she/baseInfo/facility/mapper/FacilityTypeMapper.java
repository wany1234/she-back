package com.she.baseInfo.facility.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.FacilityType;

@Mapper
@Repository("com.she.baseInfo.facility.mapper.FacilityTypeMapper")
public interface FacilityTypeMapper {
    /**
     * 설비 유형 List 조회
     * 
     * @param safFacilityTypeNm
     *            설비유형명
     * @param psafFacilityTypeCd
     *            상위설비유형코드
     * @param useYn
     *            사용여부
     * @return 설비유형 목록
     * @throws Exception
     */
    public List<FacilityType> getFacilityTypes(@Param("safFacilityTypeNm") String safFacilityTypeNm,
            @Param("psafFacilityTypeCd") String psafFacilityTypeCd, @Param("useYn") String useYn) throws Exception;

    /**
     * 설비유형 상세 조회
     * 
     * @param safFacilityTypeCd
     *            설비유형코드
     * @return 설비유형
     * @throws Exception
     */
    public FacilityType getFacilityType(@Param("safFacilityTypeCd") String safFacilityTypeCd) throws Exception;

    /**
     * 설비유형 신규
     * 
     * @param facilityType
     *            설비유형
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilityType(FacilityType facilityType) throws Exception;

    /**
     * 설비유형 수정
     * 
     * @param facilityType
     *            설비유형
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityType(FacilityType facilityType) throws Exception;

    /**
     * 설비유형 중복검사
     * 
     * @param safFacilityTypeNm
     *            설비유형명
     * @param safFacilityTypeCd
     *            설비유형코드
     * @return 설비유형 중복검사
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityType(@Param("safFacilityTypeNm") String safFacilityTypeNm,
            @Param("safFacilityTypeCd") String safFacilityTypeCd) throws Exception;

}
