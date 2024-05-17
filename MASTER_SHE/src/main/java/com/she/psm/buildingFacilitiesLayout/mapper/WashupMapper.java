package com.she.psm.buildingFacilitiesLayout.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.Washup;

@Mapper
@Repository("com.she.psm.buildingFacilitiesLayout.mapper.WashupMapper")
public interface WashupMapper {
    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param usage
     *            용도
     * @param location
     *            설치위치
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 목록
     * @throws Exception
     */
    public List<Washup> getWashupLists(@Param("plantCd") String plantCd, @Param("usage") String usage,
            @Param("location") String location) throws Exception;

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획조회
     * 
     * @param washupNo
     *            운전상태 No
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 상세정보
     * @throws Exception
     */
    public Washup getWashup(@Param("washupNo") int washupNo) throws Exception;

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 생성
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 Key값
     * @throws Exception
     */
    public int createWashup(Washup washup) throws Exception;

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 수정
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateWashup(Washup washup) throws Exception;

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 삭제
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteWashup(Washup washup) throws Exception;
}
