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

import com.she.env.waste.model.Waste;

/**
 * 폐기물 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.baseInfo.mapper.WasteMapper")
public interface WasteMapper {

    /**
     * 폐기물 전체 조회
     * 
     * @param useYn
     *            사용여부
     * @param ewstClassCd
     *            폐기물분류
     * @return 폐기물 목록
     * @throws Exception
     */
    public List<Waste> getWastes(@Param("ewstDispoMtdCd") String ewstDispoMtdCd,@Param("ewstWasteNm") String ewstWasteNm, @Param("useYn") String useYn, @Param("ewstClassCd") String ewstClassCd, @Param("plantCd")String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 폐기물 상세 조회
     * 
     * @param ewstWasteNo
     *            폐기물번호
     * @return 폐기물
     * @throws Exception
     */
    public Waste getWaste(@Param("ewstWasteNo") int ewstWasteNo) throws Exception;

    /**
     * 폐기물 신규등록
     * 
     * @param waste
     *            폐기물
     * @return 등록행수
     * @throws Exception
     */
    public int createWaste(Waste waste) throws Exception;

    /**
     * 선택된 폐기물 수정
     * 
     * @param waste
     *            폐기물
     * @return 수정행수
     * @throws Exception
     */
    public int updateWaste(Waste waste) throws Exception;
    
    /**
     * 중복체크
     * @param ewstWasteNm
     * @param plantCd
     * @return
     * @throws Exception
     */
    public int dupleCheck(@Param("ewstWasteNm") String ewstWasteNm,@Param("plantCd") String plantCd) throws Exception;
}
