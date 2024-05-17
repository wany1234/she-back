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
package com.she.health.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.health.baseInfo.mapper.DiseaseMapper;
import com.she.health.model.Disease;

/**
 * 질환 기능정의
 *
 */
@Service
public class DiseaseService {
    @Autowired
    DiseaseMapper diseaseMapper;

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
    public List<Disease> getDiseases(String heaDiseaseClassCd, String heaDiseaseNm) throws Exception {
        return diseaseMapper.getDiseases(heaDiseaseClassCd, heaDiseaseNm);
    }

    /**
     * 질환 상세 조회
     * 
     * @param heaDiseaseCd
     *            질환코드
     * @return 질환
     * @throws Exception
     */
    public Disease getDisease(String heaDiseaseCd) throws Exception {
        return diseaseMapper.getDisease(heaDiseaseCd);
    }

    /**
     * 질환 생성
     * 
     * @param disease
     *            질환
     * @return 변경 행 수
     * @throws Exception
     */
    public int createDisease(Disease disease) throws Exception {
        return diseaseMapper.createDisease(disease);
    }

    /**
     * 질환 수정
     * 
     * @param disease
     *            질환
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateDisease(Disease disease) throws Exception {
        return diseaseMapper.updateDisease(disease);
    }

}
