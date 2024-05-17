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
package com.she.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.PositionMapper;
import com.she.manage.model.Position;

@Service
public class PositionService {

    @Autowired
    private PositionMapper positionMapper;

    /**
     * 직위 조회
     * 
     * @param useYn
     *            사용여부
     * @return 직위 목록
     * @throws Exception
     */
    public List<Position> getPositions(String useYn) throws Exception {
        return positionMapper.getPositions(useYn);
    }

}
