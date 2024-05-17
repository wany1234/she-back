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
package com.she.safety.baseinfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.safety.baseinfo.mapper.HumanDamageMapper;
import com.she.utils.ConstVal;

/**
 * 안전 기준정보 인적피해 기능정의
 *
 */
@Service
public class HumanDamageService {

    @Autowired
    private HumanDamageMapper humanDamageMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 안전 기준정보 인적피해 조회
     * 
     * @param parameter
     * @return 안전 기준정보 인적피해 목록
     * @throws Exception
     */
    public List<CodeMaster> getHumanDamages(String codeNm, String useYn) throws Exception {
        return codeMasterMapper.getCodeMasters("Y", ConstVal.COM_CODE_DOMAIN_SAF, ConstVal.COM_CODE_GROUP_HUMAN_DAMAGE, codeNm, useYn);
    }

    /**
     * 안전 기준정보 인적피해 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 인적피해
     * @throws Exception
     */
    public CodeMaster getHumanDamage(String code) throws Exception {
        return codeMasterMapper.getCodeMaster(ConstVal.COM_CODE_GROUP_HUMAN_DAMAGE, code, "N");
    }

    /**
     * 안전 기준정보 인적피해 등록
     * 
     * @param codeMaster
     * @return 등록 행 수
     * @throws Exception
     */
    public int createHumanDamage(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_HUMAN_DAMAGE);
        return codeMasterMapper.createCodeMaster(codeMaster);
    }

    /**
     * 안전 기준정보 인적피해 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateHumanDamage(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_HUMAN_DAMAGE);
        return codeMasterMapper.updateCodeMaster(codeMaster);
    }

    /**
     * 안전 기준정보 인적피해 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 인적피해 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkHumanDamage(String code, String codeNm) throws Exception {
        return humanDamageMapper.checkHumanDamage(ConstVal.COM_CODE_GROUP_HUMAN_DAMAGE, code, codeNm);
    }

}
