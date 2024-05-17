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
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.mgt.baseInfo.mapper.SafetyActionBizFieldMapper;
import com.she.utils.ConstVal;

/**
 * 경영 기준정보 KPI분야관리 기능정의
 *
 */
@Service
public class SafetyActionBizFieldService {

    @Autowired
    private SafetyActionBizFieldMapper safetyActionBizFieldMapper;

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
    public List<CodeMaster> getSafetyActionBizFields(String codeNm, String useYn) throws Exception {
        return codeMasterService.getCodeMasters("Y", ConstVal.COM_CODE_DOMAIN_MGT, ConstVal.COM_CODE_GROUP_KPI_BIZFIELD, codeNm, useYn);
    }

    /**
     * 경영 기준정보 KPI분야관리 상세조회
     * 
     * @param code
     *            코드
     * @return 경영 기준정보 KPI분야관리
     * @throws Exception
     */
    public CodeMaster getSafetyActionBizField(String code) throws Exception {
        return codeMasterService.getCodeMaster(ConstVal.COM_CODE_GROUP_KPI_BIZFIELD, code, "Y");
    }

    /**
     * 경영 기준정보 KPI분야 등록
     * 
     * @param codeMaster
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createSafetyActionBizField(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_KPI_BIZFIELD);
        return codeMasterService.createCodeMaster(codeMaster);
    }

    /**
     * 경영 기준정보 KPI분야 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateSafetyActionBizField(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_KPI_BIZFIELD);
        return codeMasterService.updateCodeMaster(codeMaster);
    }

    /**
     * 경영 기준정보 KPI분야관리 중복 검사
     * 
     * @param parameter
     * @return 경영 기준정보 KPI분야 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkSafetyActionBizField(String code, String codeNm) throws Exception {
        return safetyActionBizFieldMapper.checkSafetyActionBizField(ConstVal.COM_CODE_GROUP_KPI_BIZFIELD, code, codeNm);
    }

}
