package com.she.safety.baseinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.safety.model.Map;

@Mapper
@Repository("com.she.safety.baseinfo.mapper.MapMapper")
public interface MapMapper {
    /**
     * 안전 기준정보 지도 조회
     *
     * @param
     * 
     * @return 지도 리스트
     * @throws Exception
     */
    public List<Map> getMaps(@Param("plantCd") String plantCd, @Param("mapName") String mapName, @Param("useYn") String useYn) throws Exception;

    /**
     * 사업부 기준 메인 표출될 MAP ID 조회
     *
     * @param plantCd
     *            사업부코드
     * @return 지도
     * @throws Exception
     */
    public Map getMapIdByPlantCd(@Param("plantCd") String plantCd) throws Exception;

    /**
     * 안전 기준정보 지도 등록
     *
     * @param map
     *            지도마스터
     * @return 등록 행 수
     * @throws Exception
     */
    public int createMap(Map map) throws Exception;

    /**
     * 안전 기준정보 지도 수정
     *
     * @param map
     *            지도마스터
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMap(Map map) throws Exception;

    /**
     * 메인노출여부 일괄 비노출로 변경
     *
     * @param map
     *            지도마스터
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMapForMainViewYn(Map map) throws Exception;

}
