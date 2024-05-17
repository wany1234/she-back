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

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.service.CodeMasterService;
import com.she.mgt.baseInfo.mapper.SafetyActionBizFieldItemMapper;
import com.she.mgt.baseInfo.model.BizFieldItem;

/**
 * 경영 기준정보 KPI분야관리 기능정의
 *
 */
@Service
public class SafetyActionBizFieldItemService {

    @Autowired
    private SafetyActionBizFieldItemMapper safetyActionBizFieldItemMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private CodeMasterService codeMasterService;

    /**
     * 경영 기준정보 KPI분야관리 조회
     * 
     * @param parameter
     * @return 경영 기준정보 KPI분야관리 목록
     * @throws Exception
     */
    public List<BizFieldItem> getSafetyActionBizFieldItems(String bizFieldCd, String bizFieldItemNm, String useYn, String from, String to, String createUserNm) throws Exception {
        return safetyActionBizFieldItemMapper.getSafetyActionBizFieldItems(bizFieldCd, bizFieldItemNm, useYn, from, to, createUserNm);
    }

    /**
     * 경영 기준정보 KPI분야관리 상세조회
     * 
     * @param bizFieldItemNo
     *            분야별 항목번호
     * @return 경영 기준정보 KPI분야관리
     * @throws Exception
     */
    public BizFieldItem getSafetyActionBizFieldItem(int bizFieldItemNo) throws Exception {
        return safetyActionBizFieldItemMapper.getSafetyActionBizFieldItem(bizFieldItemNo);
    }

    /**
     * 경영 기준정보 KPI분야별 항목 등록
     * 
     * @param codeMaster
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createSafetyActionBizFieldItem(BizFieldItem bizFieldItem) throws Exception {
        return safetyActionBizFieldItemMapper.createSafetyActionBizFieldItem(bizFieldItem);
    }

    /**
     * 경영 기준정보 KPI분야별 항목 수정
     * 
     * @param bizFieldItem
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateSafetyActionBizFieldItem(BizFieldItem bizFieldItem) throws Exception {
        return safetyActionBizFieldItemMapper.updateSafetyActionBizFieldItem(bizFieldItem);
    }

    /**
     * 경영 기준정보 KPI분야관리 중복 검사
     * 
     * @param parameter
     * @return 경영 기준정보 KPI분야 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkSafetyActionBizFieldItem(String bizFieldCd, String bizFieldItemNm, int bizFieldItemNo) throws Exception {
        return safetyActionBizFieldItemMapper.checkSafetyActionBizFieldItem(bizFieldCd, bizFieldItemNm, bizFieldItemNo);
    }

}
