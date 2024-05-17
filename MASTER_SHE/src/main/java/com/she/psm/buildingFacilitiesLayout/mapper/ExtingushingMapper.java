package com.she.psm.buildingFacilitiesLayout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.Extingushing;

@Mapper
@Repository("com.she.psm.buildingFacilitiesLayout.mapper.ExtingushingMapper")
public interface ExtingushingMapper {
    /**
     * 소화설비 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param category
     *            구분
     * @return 소화설비 설치계획 목록
     * @throws Exception
     * @throws Exception
     */
    public List<Extingushing> getExtingushingLists(@Param("plantCd") String plantCd, @Param("location") String location)
            throws Exception;

    /**
     * 소화설비 설치계획조회
     * 
     * @param extingushingNo
     *            운전상태 No
     * @return 소화설비 설치계획 상세정보
     * @throws Exception
     */
    public Extingushing getExtingushing(@Param("extingushingNo") int extingushingNo) throws Exception;

    /**
     * 소화설비 설치계획 항목 생성
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 소화설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int createExtingushing(Extingushing extingushing) throws Exception;

    /**
     * 소화설비 설치계획 항목 수정
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 소화설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateExtingushing(Extingushing extingushing) throws Exception;

    /**
     * 소화설비 설치계획 항목 삭제
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteExtingushing(Extingushing extingushing) throws Exception;
}
