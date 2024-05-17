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
import org.springframework.transaction.annotation.Transactional;

import com.she.manage.mapper.CodeGroupMapper;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeGroup;
import com.she.manage.model.CodeMaster;
import com.she.safety.baseinfo.mapper.OccurrFormMapper;
import com.she.utils.ConstVal;

/**
 * 안전 기준정보 발생형태(대분류/중분류) 기능정의
 */
@Service
public class OccurrFormService {

    @Autowired
    private OccurrFormMapper occurrFormMapper;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    @Autowired
    private CodeGroupMapper codeGroupMapper;

    /**
     * 안전 기준정보 발생형태(대분류) 조회
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(대분류) 목록
     * @throws Exception
     */
    public List<CodeMaster> getOccurrForms(String codeNm, String useYn) throws Exception {
        return codeMasterMapper.getCodeMasters("Y", ConstVal.COM_CODE_DOMAIN_SAF, ConstVal.COM_CODE_GROUP_OCC_PTN, codeNm, useYn);
    }

    /**
     * 안전 기준정보 발생형태(대분류) 상세조회
     * 
     * @param code
     *            코드
     * @return 안전 기준정보 발생형태(대분류)
     * @throws Exception
     */
    public CodeMaster getOccurrForm(String code) throws Exception {
        return codeMasterMapper.getCodeMaster(ConstVal.COM_CODE_GROUP_OCC_PTN, code, "N");
    }

    /**
     * 안전 기준정보 발생형태(대분류) 등록
     * 
     * @param codeMaster
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createOccurrForm(CodeMaster codeMaster) throws Exception {

        int resultNo = 0;
        // 대분류 등록
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_OCC_PTN);
        resultNo += codeMasterMapper.createCodeMaster(codeMaster);

        if (codeMaster.getMiddleItems() != null && codeMaster.getMiddleItems().size() > 0) {
            for (CodeMaster middleItem : codeMaster.getMiddleItems()) {
                middleItem.setCodeGroupCd(codeMaster.getCode());
                int middleItemResult = codeMasterMapper.selectMiddleItem(codeMaster.getCode(), middleItem.getCode());
                if (middleItemResult > 0) {
                    codeMasterMapper.updateCodeMaster(middleItem);
                } else {
                    codeMasterMapper.createCodeMaster(middleItem);
                }
            }
        }

        if (codeMaster.getDeleteMiddleItems() != null && codeMaster.getDeleteMiddleItems().size() > 0) {
            for (CodeMaster middleItem : codeMaster.getDeleteMiddleItems()) {
                if (middleItem.getCode() != "") {
                    middleItem.setCodeGroupCd(codeMaster.getCode());
                    codeMasterMapper.deleteCodeMaster(codeMaster.getCode(), middleItem.getCode());
                }
            }
        }

        // 등록된 대분류 코드값에 따른 코드그룹 생성
        CodeGroup codeGroup = new CodeGroup();
        codeGroup.setCodeGroupCd(codeMaster.getCode());
        codeGroup.setCodeDomainCd(ConstVal.COM_CODE_DOMAIN_SAF);
        codeGroup.setCodeGroupNm("발생형태중분류-" + codeMaster.getCodeNm());
        codeGroup.setCodeLength(5);
        codeGroup.setForSystemYn("N");
        codeGroup.setSettableYn("Y");
        codeGroup.setUseYn("Y");
        codeGroup.setCreateUserId(codeMaster.getCreateUserId());

        resultNo += codeGroupMapper.createCodeGroup(codeGroup);
        return resultNo;
    }

    /**
     * 안전 기준정보 발생형태(대분류) 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateOccurrForm(CodeMaster codeMaster) throws Exception {
        codeMaster.setCodeGroupCd(ConstVal.COM_CODE_GROUP_OCC_PTN);

        int result = codeMasterMapper.updateCodeMaster(codeMaster);

        if (codeMaster.getMiddleItems() != null && codeMaster.getMiddleItems().size() > 0) {
            for (CodeMaster middleItem : codeMaster.getMiddleItems()) {
                middleItem.setCodeGroupCd(codeMaster.getCode());
                int middleItemResult = codeMasterMapper.selectMiddleItem(codeMaster.getCode(), middleItem.getCode());
                if (middleItemResult > 0) {
                    codeMasterMapper.updateCodeMaster(middleItem);
                } else {
                    codeMasterMapper.createCodeMaster(middleItem);
                }
            }
        }

        if (codeMaster.getDeleteMiddleItems() != null && codeMaster.getDeleteMiddleItems().size() > 0) {
            for (CodeMaster middleItem : codeMaster.getDeleteMiddleItems()) {
                if (middleItem.getCode() != "") {
                    middleItem.setCodeGroupCd(codeMaster.getCode());
                    codeMasterMapper.deleteCodeMaster(codeMaster.getCode(), middleItem.getCode());
                }
            }
        }
        return result;
    }

    /**
     * 안전 기준정보 발생형태(대분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(대분류) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkOccurrForm(String code, String codeNm) throws Exception {
        return occurrFormMapper.checkOccurrForm(ConstVal.COM_CODE_GROUP_OCC_PTN, code, codeNm);
    }

    /**
     * 안전 기준정보 발생형태(중분류) 조회
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 목록
     * @throws Exception
     */
    public List<CodeMaster> getOccurrAtts(String codeGroupCd) throws Exception {
        return codeMasterMapper.getCodeMasters("Y", ConstVal.COM_CODE_DOMAIN_SAF, codeGroupCd, "", "");
    }

    /**
     * 안전 기준정보 발생형태(중분류) 상세조회
     * 
     * @param codeGroupCd
     *            코드그룹코드
     * @param code
     *            코드
     * @return 안전 기준정보 발생형태(중분류)
     * @throws Exception
     */
    public CodeMaster getOccurrAtt(String codeGroupCd, String code) throws Exception {
        return codeMasterMapper.getCodeMaster(codeGroupCd, code, "N");
    }

    /**
     * 안전 기준정보 발생형태(중분류) 등록
     * 
     * @param codeMaster
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createOccurrAtt(CodeMaster codeMaster) throws Exception {
        int resultNo = 0;
        // 중분류 등록
        resultNo += codeMasterMapper.createCodeMaster(codeMaster);
        return resultNo;
    }

    /**
     * 안전 기준정보 발생형태(중분류) 수정
     * 
     * @param codeMaster
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateOccurrAtt(CodeMaster codeMaster) throws Exception {
        return codeMasterMapper.updateCodeMaster(codeMaster);
    }

    /**
     * 안전 기준정보 발생형태(중분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkOccurrAtt(String codeGroupCd, String code, String codeNm) throws Exception {
        return occurrFormMapper.checkOccurrAtt(codeGroupCd, code, codeNm);
    }

    /**
     * 안전 기준정보 발생형태(중분류) 중복 검사
     * 
     * @param parameter
     * @return 안전 기준정보 발생형태(중분류) 중복 검사 List
     * @throws Exception
     */
    public List<HashMap<String, Object>> checkOccurrAtts(List<String> code) throws Exception {
        return occurrFormMapper.checkOccurrAtts(ConstVal.COM_CODE_DOMAIN_SAF, ConstVal.COM_CODE_GROUP_OCC_PTN, code);
    }

}
