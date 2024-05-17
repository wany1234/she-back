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
package com.she.chm.baseInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.ChemicalOrigin;

@Mapper
@Repository("com.she.chm.baseInfo.mapper.ChemicalOriginMapper")
public interface ChemicalOriginMapper {

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
    public List<ChemicalOrigin> getChemicalOrigins(@Param("originNmKr") String originNmKr, @Param("useYn") String useYn) throws Exception;

}
