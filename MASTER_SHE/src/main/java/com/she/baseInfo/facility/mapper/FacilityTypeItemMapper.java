package com.she.baseInfo.facility.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.FacilityTypeItem;

@Mapper
@Repository("com.she.baseInfo.facility.mapper.FacilityTypeItemMapper")
public interface FacilityTypeItemMapper {

    /**
     * 설비 유형별 관리 항목 List 조회
     * 
     * @param parameter
     *            (안전설비유형코드, 안전설비유형항목명,사용여부 )
     * @return 안전점검항목 List
     * @throws Exception
     */
    public List<FacilityTypeItem> getFacilityTypeItems(@Param("safFacilityTypeCd") String safFacilityTypeCd,
            @Param("safFacilityTypeItemNm") String safFacilityTypeItemNm, @Param("useYn") String useYn)
            throws Exception;

    /**
     * 설비 유형별 관리 항목 상세 조회
     * 
     * @param parameter
     *            (안전설비유형항목번호)
     * @return 안전점검항목
     * @throws Exception
     */
    public FacilityTypeItem getFacilityTypeItem(@Param("safFacilityTypeItemNo") int safFacilityTypeItemNo)
            throws Exception;

    /**
     * 설비 유형별 관리 항목 신규
     * 
     * @param FacilityTypeItem
     *            설비 유형별 관리 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityTypeItem(FacilityTypeItem facilityTypeItem) throws Exception;

    /**
     * 설비 유형별 관리 항목 수정
     * 
     * @param FacilityTypeItem
     *            설비 유형별 관리 항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityTypeItem(FacilityTypeItem facilityTypeItem) throws Exception;

    /**
     * 설비유형별 상세항목 중복검사
     * 
     * @param parameter
     *            (설비유형관리항목번호, 안전설비유형항목명 )
     * @return 설비유형별 상세항목 중복검사
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityTypeItem(
            @Param("safFacilityTypeItemNo") int safFacilityTypeItemNo,
            @Param("safFacilityTypeItemNm") String safFacilityTypeItemNm,
            @Param("safFacilityTypeCd") String safFacilityTypeCd) throws Exception;

}
