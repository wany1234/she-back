package com.she.safety.baseinfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.FacilityTypeCheckItem;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.FacilityInsCkItemMapper")
public interface FacilityInsCkItemMapper {

    /**
     * 시설유형별 점검항목 조회
     *
     * @param plantCd
     *            사업장코드
     * @param facilityTypeCd
     *            시설유형코드
     * @param facilityCheckCd
     *            시설점검종류코드
     * @return 시설유형별 점검항목 목록
     * @throws Exception
     */
    public List<FacilityTypeCheckItem> getFacilityTypeCkItemList(@Param("plantCd") String plantCd, @Param("facilityTypeCd") String facilityTypeCd, @Param("facilityCheckCd") String facilityCheckCd, @Param("facilityCheckItemNm") String facilityCheckItemNm) throws Exception;

    /**
     * 시설유형별 점검항목 상세 조회
     *
     * @param facilityCheckItemNo
     *            시설유형별 점검항목No
     * @return 시설유형별 점검항목
     * @throws Exception
     */
    public FacilityTypeCheckItem getFacilityTypeCkItem(@Param("facilityCheckItemNo") int facilityCheckItemNo) throws Exception;

    /**
     * 시설유형별 점검항목 등록
     *
     * @param FacilityTypeCheckItem
     *            시설유형별 점검항목
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilityTypeCkItem(FacilityTypeCheckItem facilityTypeCheckItem) throws Exception;

    /**
     * 시설유형별 점검항목 수정
     *
     * @param FacilityTypeCheckItem
     *            시설유형별 점검항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityTypeCkItem(FacilityTypeCheckItem facilityTypeCheckItem) throws Exception;

    /**
     * 시설유형별 점검항목명 체크
     *
     * @param facilityCheckItemNo
     *            시설유형별점검항목No
     * @param facilityCheckItemNm
     *            시설유형별점검항목명
     * @param facilityTypeCd
     *            시설유형코드
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckFacilityTypeCkItemNm(@Param("facilityCheckItemNo") int facilityCheckItemNo, @Param("facilityCheckItemNm") String facilityCheckItemNm, @Param("facilityTypeCd") String facilityTypeCd) throws Exception;

}
