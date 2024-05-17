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
package com.she.chm.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.chm.baseInfo.mapper.ChemicalOriginMapper;
import com.she.chm.model.ChemicalOrigin;

@Service
public class ChemicalOriginService {

    @Autowired
    private ChemicalOriginMapper chemicalOriginMapper;

    /**
     * 원산지 조회
     *
     * @param originNmKr
     *            원산지명 (KOR)
     * @param useYn
     *            사용여부
     * @return 원산지 목록
     * @throws Exception
     */
    public List<ChemicalOrigin> getChemicalOrigins(String originNmKr, String useYn) throws Exception {
        return chemicalOriginMapper.getChemicalOrigins(originNmKr, useYn);
    }

}
