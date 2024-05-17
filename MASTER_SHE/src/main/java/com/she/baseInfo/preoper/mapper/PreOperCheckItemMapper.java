package com.she.baseInfo.preoper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.FacilityCheckItem;
import com.she.common.model.DefaultParam;

@Mapper
@Repository("com.she.baseInfo.preoper.mapper.PreOperCheckItemMapper")
public interface PreOperCheckItemMapper {

    /**
     * 설비유형별 점검항목 List 조회
     *
     * @param plantCd
     *            사업장코드
     * @param safFacilityTypeCd
     *            설비유형코드
     * @param safCheckTypeCd
     *            설비점검종류코드
     * @param useYn
     *            사용여부
     * @return 설비유형별 점검항목 List
     * @throws Exception
     */
    public List<FacilityCheckItem> getFacilityCheckItems(@Param("plantCd") String plantCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("safFacilityTypeCd") String safFacilityTypeCd, @Param("useYn") String useYn, @Param("dataLvlCd") String dataLvlCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 설비유형별 점검항목 상세 조회
     *
     * @param safFacilityCheckItemNo(설비점검항목번호)
     * @return 설비유형별 점검항목
     * @throws Exception
     */
    public FacilityCheckItem getFacilityCheckItem(@Param("safFacilityCheckItemNo") int safFacilityCheckItemNo) throws Exception;

    /**
     * 설비유형별 점검항목 생성
     *
     * @param facilityCheckItem
     *            설비유형별 점검항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int createFacilityCheckItem(FacilityCheckItem facilityCheckItem) throws Exception;

    /**
     * 설비유형별 점검항목 수정
     *
     * @param facilityCheckItem
     *            설비유형별 점검항목
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateFacilityCheckItem(FacilityCheckItem facilityCheckItem) throws Exception;

    /**
     * 설비유형별 점검항목명 중복 체크
     *
     * @param safFacilityCheckItemNo
     *            설비점검항목번호
     * @param safFacilityCheckNm
     *            설비점검항목명
     * @return 설비유형별 점검항목명 중복 체크값
     * @throws Exception
     */
    public int checkFacilityCheckItem(@Param("safFacilityCheckItemNo") int safFacilityCheckItemNo, @Param("safFacilityCheckNm") String safFacilityCheckNm, @Param("plantCd") String plantCd, @Param("safCheckTypeCd") String safCheckTypeCd) throws Exception;

    public List<FacilityCheckItem> getDataCategoryItems(@Param("dataLvlCd") String dataLvlCd, @Param("safCheckTypeCd") String safCheckTypeCd, @Param("pSafFacilityCheckNm") String pSafFacilityCheckNm, @Param("useYn") String useYn, @Param("plantCd") String plantCd) throws Exception;

}
