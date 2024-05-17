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
package com.she.health.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.health.model.Disease;

/**
 * 질환 맵퍼
 *
 */
@Mapper
@Repository("com.she.health.baseInfo.mapper.DiseaseMapper")
public interface DiseaseMapper {
    /**
     * 질환 조회
     * 
     * @param heaDiseaseClassCd
     *            질환분류코드
     * @param heaDiseaseNm
     *            질환명
     * @return 질환 목록
     * @throws Exception
     */
    public List<Disease> getDiseases(@Param("heaDiseaseClassCd") String heaDiseaseClassCd,
            @Param("heaDiseaseNm") String heaDiseaseNm) throws Exception;

    /**
     * 질환 상세 조회
     * 
     * @param heaDiseaseCd
     *            질환코드
     * @return 질환
     * @throws Exception
     */
    public Disease getDisease(@Param("heaDiseaseCd") String heaDiseaseCd) throws Exception;

    /**
     * 질환 생성
     * 
     * @param disease
     *            질환
     * @return 변경 행 수
     * @throws Exception
     */
    public int createDisease(Disease disease) throws Exception;

    /**
     * 질환 수정
     * 
     * @param disease
     *            질환
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateDisease(Disease disease) throws Exception;

}
