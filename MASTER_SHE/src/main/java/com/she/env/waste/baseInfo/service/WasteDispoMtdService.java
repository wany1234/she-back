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

import com.she.env.waste.baseInfo.mapper.WasteDispoMtdMapper;
import com.she.env.waste.model.WasteDispoMtd;

/**
 * 폐기물 처리방법 기능정의
 *
 */
@Service("EwstWasteDispoMtdService")
public class WasteDispoMtdService {
    @Autowired
    private WasteDispoMtdMapper wasteDispoMtdMapper;

    /**
     * 폐기물 처리방법 전체 조회
     * 
     * @param useYn
     *            사용여부
     * @return 폐기물 처리방법 목록
     * @throws Exception
     */
    public List<WasteDispoMtd> getWasteDisposalMethods(String ewstDispoMtdNm, String ewstDispoClassCd, String useYn, DefaultParam defaultParam) throws Exception {
        return this.wasteDispoMtdMapper.getWasteDisposalMethods(ewstDispoMtdNm, ewstDispoClassCd, useYn, defaultParam);
    }

    /**
     * 선택된 폐기물 처리방법 상세 조회
     * 
     * @param ewstDispoMtdCd
     *            폐기물 처리방법 코드
     * @return 폐기물 처리방법
     * @throws Exception
     */
    public WasteDispoMtd getWasteDisposalMethod(@Param("ewstDispoMtdCd") String ewstDispoMtdCd) throws Exception {
        return this.wasteDispoMtdMapper.getWasteDisposalMethod(ewstDispoMtdCd);
    }

    /**
     * 폐기물 처리방법 신규등록
     * 
     * @param wasteDispoMtd
     *            폐기물 처리방법
     * @return 등록행수
     * @throws Exception
     */
    public int createWasteDisposalMethod(WasteDispoMtd wasteDispoMtd) throws Exception {
        return this.wasteDispoMtdMapper.createWasteDisposalMethod(wasteDispoMtd);
    }

    /**
     * 선택된 폐기물 처리방법 수정
     * 
     * @param wasteDispoMtd
     *            폐기물 처리방법
     * @return 수정행수
     * @throws Exception
     */
    public int updateWasteDisposalMethod(WasteDispoMtd wasteDispoMtd) throws Exception {
        return this.wasteDispoMtdMapper.updateWasteDisposalMethod(wasteDispoMtd);
    }

    /**
     * 폐기물 처리방법 중복체크
     * 
     * @param ewstDispoMtdCd
     * @return
     * @throws Exception
     */
    public int dupleCheck(String ewstDispoMtdCd) throws Exception {
        return this.wasteDispoMtdMapper.dupleCheck(ewstDispoMtdCd);
    }
}
