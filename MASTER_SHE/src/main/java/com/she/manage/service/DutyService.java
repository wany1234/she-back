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

import com.she.manage.mapper.DutyMapper;
import com.she.manage.model.Duty;

@Service
public class DutyService {

    @Autowired
    private DutyMapper dutyMapper;

    /**
     * 직책 조회
     * 
     * @param useYn
     *            사용여부
     *              dutyNm
     *            직책명
     * @return 직책 목록
     * @throws Exception
     */
    public List<Duty> getDutys(String useYn, String dutyNm) throws Exception {
        return dutyMapper.getDutys(useYn, dutyNm);
    }

}
