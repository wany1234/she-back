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
package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.Duty;

@Mapper
@Repository("com.she.manage.mapper.DutyMapper")
public interface DutyMapper {

    /**
     * 직책 조회
     * @param useYn 사용여부
     *              dutyNm 직책명
     * @return 직책 목록
     * @throws Exception
     */
    public List<Duty> getDutys(@Param("useYn") String useYn, @Param("dutyNm") String dutyNm) throws Exception;

}
