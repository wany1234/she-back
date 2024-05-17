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
package com.she.env.waste.baseInfo.service;

import java.util.List;

import com.she.common.model.DefaultParam;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.waste.baseInfo.mapper.WasteMapper;
import com.she.env.waste.model.Waste;

/**
 * 폐기물 기능정의
 *
 */
@Service("EwstWasteService")
public class WasteService {

    @Autowired
    private WasteMapper wasteMapper;

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
    public List<Waste> getWastes(String ewstDispoMtdCd, String ewstWasteNm, String useYn, String ewstClassCd, String plantCd, DefaultParam defaultParam) throws Exception {
        return this.wasteMapper.getWastes(ewstDispoMtdCd, ewstWasteNm, useYn, ewstClassCd, plantCd, defaultParam);
    }

    /**
     * 선택된 폐기물 상세 조회
     * 
     * @param ewstWasteNo
     *            폐기물번호
     * @return 폐기물
     * @throws Exception
     */
    public Waste getWaste(int ewstWasteNo) throws Exception {
        return this.wasteMapper.getWaste(ewstWasteNo);
    }

    /**
     * 폐기물 신규등록
     * 
     * @param waste
     *            폐기물
     * @return 폐기물번호
     * @throws Exception
     */
    public int createWaste(Waste waste) throws Exception {
        this.wasteMapper.createWaste(waste);
        return waste.getEwstWasteNo();
    }

    /**
     * 선택된 폐기물 수정
     * 
     * @param waste
     *            폐기물
     * @return 수정행수
     * @throws Exception
     */
    public int updateWaste(Waste waste) throws Exception {
        return this.wasteMapper.updateWaste(waste);
    }

    /**
     * 중복체크
     * 
     * @param ewstWasteNm
     * @param plantCd
     * @return
     * @throws Exception
     */
    public int dupleCheck(@Param("ewstWasteNm") String ewstWasteNm, @Param("plantCd") String plantCd) throws Exception {
        return this.wasteMapper.dupleCheck(ewstWasteNm, plantCd);
    }
}
