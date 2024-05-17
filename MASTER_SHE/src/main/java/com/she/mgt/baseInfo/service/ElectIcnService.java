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
package com.she.mgt.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.mgt.baseInfo.mapper.ElectIcnMapper;
import com.she.mgt.model.InvestItem;
import com.she.mgt.model.MgtMgInfoItm;
import com.she.mgt.model.MgtMgInfoItmDeptAtt;

@Service
public class ElectIcnService {
    @Autowired
    private ElectIcnMapper electIcnMapper;

    /**
     * 경영정보부서 구분명 조회
     *
     * @param parameter
     *            검색조건
     * @return
     * @throws Exceptio
     */
    public List<MgtMgInfoItmDeptAtt> getMgtInfoItem(String plantCd, String infoItmAttCd) throws Exception {
        return electIcnMapper.getMgtInfoItem(plantCd, infoItmAttCd);
    }

    /**
     * 경영정보항목 상세 조회
     *
     * @param mgtMgInfoDeptAttNo
     *            경영정보부서 번호
     * @return mgtMgInfoDeptAttNo 경영정보부서
     * @throws Exception
     *             예외
     */
    public MgtMgInfoItmDeptAtt getMgtInfoItemDetail(String mgtMgInfoDeptAttNo) throws Exception {
        return electIcnMapper.getMgtInfoItemDetail(mgtMgInfoDeptAttNo);
    }

    /**
     * 경영정보부서 항목 조회
     *
     * @param parameter
     *            검색조건
     * @return
     * @throws Exceptio
     */
    public List<MgtMgInfoItm> getMgtMgInfoItemList() throws Exception {
        return electIcnMapper.getMgtMgInfoItemList();
    }

    /**
     * 등록된 경영정보항목 확인
     *
     * @param plantCd
     * @param mgtMgInfoItmNo
     * @param deptAttNm
     * @return
     * @throws Exception
     */
    public int checkMgtinfoitem(String plantCd, int mgtMgInfoItmNo, String deptAttNm) throws Exception {
        return electIcnMapper.checkMgtinfoitem(plantCd, mgtMgInfoItmNo, deptAttNm);
    }

    /**
     * 경영정보부서 항목 신규등록
     *
     * @param electIcn
     *
     * @return
     * @throws Exception
     */
    @Transactional
    public int createMgtinfoitem(MgtMgInfoItmDeptAtt mgtMgInfoItmDeptAtt) throws Exception {
        int result = this.electIcnMapper.createMgtinfoitem(mgtMgInfoItmDeptAtt);
        if (result <= 0) {
            return 0;
        } else {
            return mgtMgInfoItmDeptAtt.getMgtMgInfoDeptAttNo();
        }
    }

    /**
     * 경영정보부서 항목 수정
     *
     * @param electIcn
     *
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateMgtinfoitem(MgtMgInfoItmDeptAtt mgtMgInfoItmDeptAtt) throws Exception {
        if (mgtMgInfoItmDeptAtt.getMgtMgInfoDeptAttNo() <= 0) {
            return 0;
        } else {
            return electIcnMapper.updateMgtinfoitem(mgtMgInfoItmDeptAtt);
        }
    }

    /**
     * 투자항목관리 조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    public List<InvestItem> getInvestItems(String codeNm, int mgtMgInfoItmNo, String useYn) throws Exception {
        return this.electIcnMapper.getInvestItems(codeNm, mgtMgInfoItmNo, useYn);
    }

    /**
     * 투자항목(중분류) 조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */

    public List<InvestItem> getInvestItemsMiddle(String infoItmAttCd, int mgtMgInfoItmNo, String useYn) throws Exception {
        return this.electIcnMapper.getInvestItemsMiddle(infoItmAttCd, mgtMgInfoItmNo, useYn);
    }

    /**
     * 투자항목관리 상세조회
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    public InvestItem getInvestItem(String infoItmAttCd, int mgtMgInfoItmNo) throws Exception {
        return this.electIcnMapper.getInvestItem(infoItmAttCd, mgtMgInfoItmNo);
    }

    /**
     * 투자항목 등록
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @Transactional
    public int createInvestItemAdd(InvestItem investItem) throws Exception {
        return this.electIcnMapper.createInvestItemAdd(investItem);
    }

    /**
     * 투자항목 수정
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    @Transactional
    public int updateInvestItemAdd(InvestItem investItem) throws Exception {
        return this.electIcnMapper.updateInvestItemAdd(investItem);
    }
}
