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
package com.she.env.waste.baseInfo.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.waste.model.WasteClass;

/**
 * 폐기물 분류 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.baseInfo.mapper.WasteClassMapper")
public interface WasteClassMapper {

    /**
     * 폐기물 분류 전체 조회
     * 
     * @param useYn
     *            사용여부
     * @return 폐기물 분류 목록
     * @throws Exception
     */
    public List<WasteClass> getWasteClasses(@Param("ewstClassNm") String ewstClassNm,@Param("ewstDivCd") String ewstDivCd, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 폐기물 분류 상세 조회
     * 
     * @param ewstClassCd
     *            폐기물 분류코드
     * @return 폐기물 분류
     * @throws Exception
     */
    public WasteClass getWasteClass(@Param("ewstClassCd") String ewstClassCd) throws Exception;

    /**
     * 폐기물 분류 신규등록
     * 
     * @param wasteClass
     *            폐기물 분류
     * @return 등록행수
     * @throws Exception
     */
    public int createWasteClass(WasteClass wasteClass) throws Exception;

    /**
     * 선택된 폐기물 분류 수정
     * 
     * @param wasteClass
     *            폐기물 분류
     * @return 수정행수
     * @throws Exception
     */
    public int updateWasteClass(WasteClass wasteClass) throws Exception;
    
    /**
     * 폐기물 분류 중복 체크
     * @param ewstClassCd
     * @return
     * @throws Exception
     */
    public int dupleCheck(@Param("ewstClassCd") String ewstClassCd) throws Exception;
}
