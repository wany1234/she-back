/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.safety.baseinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.safety.baseinfo.mapper.MapMapper;
import com.she.safety.model.Map;

/**
 * 안전 기준정보 지도 기능정의
 */
@Service
public class MapService {
    @Autowired
    private MapMapper mapMapper;

    /**
     * 안전 기준정보 지도 조회
     * 
     * @param
     * @return 안전 기준정보 지도 목록
     * @throws Exception
     */
    public List<Map> getMaps(String plantCd, String mapName, String useYn) throws Exception {
        return mapMapper.getMaps(plantCd, mapName, useYn);
    }

    /**
     * 사업부 기준 메인 표출될 MAP ID 조회
     *
     * @param plantCd
     *            사업부코드
     * @return 지도ID
     * @throws Exception
     */
    public Map getMapIdByPlantCd(String plantCd) throws Exception {
        Map map = this.mapMapper.getMapIdByPlantCd(plantCd);
        return map;
    }

    /**
     * 안전 기준정보 지도 등록
     * 
     * @param map
     * @return 등록 행 수
     * @throws Exception
     */
    public int createMap(Map map) throws Exception {

        // 해당 지도가 메인 노출지도일 경우 기존 사업장의 메인 노출되는 지도를 비노출로 변경
        if (map.getMainViewYn().equals("Y")) {
            mapMapper.updateMapForMainViewYn(map);
        }

        // 지도 암호화 필요함
        return mapMapper.createMap(map);
    }

    /**
     * 안전 기준정보 지도 수정
     * 
     * @param map
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateMap(Map map) throws Exception {

        // 해당 지도가 메인 노출지도일 경우 기존 사업장의 메인 노출되는 지도를 비노출로 변경
        if (map.getMainViewYn().equals("Y")) {
            mapMapper.updateMapForMainViewYn(map);
        }
        // 지도 암호화 필요함
        return mapMapper.updateMap(map);
    }
}
