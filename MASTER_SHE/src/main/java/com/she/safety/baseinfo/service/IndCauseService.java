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
import com.she.safety.baseinfo.mapper.IndCauseMapper;
import com.she.utils.ConstVal;

/**
 * 안전 기준정보 원인(간접) 기능정의
 */
@Service
public class IndCauseService {
    @Autowired
    private IndCauseMapper imdCauseMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 안전 기준정보 원인(간접) 조회
     * 
     * @param parameter
     * @return 안전 기준정보 원인(간접) 목록
     * @throws Exception
     */
    public List<CodeMaster> getIndCauses(String codeNm, String useYn) throws Exception {
        return codeMasterMapper.getCodeMasters("Y", ConstVal.COM_CODE_DOMAIN_SAF, ConstVal.COM_CODE_GROUP_IND_CAUSE, codeNm, useYn);
    }

    /**
     * 안전 기준정보 원인(간접) 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 원인(간접)
     * @throws Exception
     */
    public CodeMaster getIndCause(String code) throws Exception {
        return codeMasterMapper.getCodeMaster(ConstVal.COM_CODE_GROUP_IND_CAUSE, code, "N");
    }

    /**
     * 안전 기준정보 원인(간접) 등록
     * 
     * @param codeMaster
     * @return 등록 행 수
     * @throws Exception
     */
    public int createIndCause(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_IND_CAUSE);
        return codeMasterMapper.createCodeMaster(codeMaster);
    }

    /**
     * 안전 기준정보 원인(간접) 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateIndCause(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_IND_CAUSE);
        return codeMasterMapper.updateCodeMaster(codeMaster);
    }

    /**
     * 안전 기준정보 원인(간접) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 원인(간접) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkIndCause(String code, String codeNm) throws Exception {
        return imdCauseMapper.checkIndCause(ConstVal.COM_CODE_GROUP_IND_CAUSE, code, codeNm);
    }

}
