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
package com.she.manage.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.manage.model.CodeMaster;
import com.she.mgt.model.InvestItem;
import com.she.utils.ConstVal;

/**
 * 코드 마스터 기능정의
 *
 */
@Service
public class CodeMasterService {

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 선택박스에서 사용되는 코드목록 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @return 코드목록
     * @throws Exception
     */
    @Cacheable(cacheNames = "ComCodeSelectCashe", key = "'COMCODESEL' + #defaultParam.lang + #codeGroupCd")
    public List<CodeMaster> getSelect(String codeGroupCd, String useYn, DefaultParam defaultParam) throws Exception {
        return codeMasterMapper.getSelect(codeGroupCd, useYn, defaultParam);
    }

    /**
     * 선택박스에서 사용되는 코드목록 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @return 코드목록
     * @throws Exception
     */
    @Cacheable(cacheNames = "ComCodeAttrSelectCashe", key = "'COMCODESELATTR' + #defaultParam.lang + #codeGroupCd + #attr1 + #attr2 + #attr3")
    public List<CodeMaster> getSelectAttr(String codeGroupCd, String attr1, String attr2, String attr3, DefaultParam defaultParam) throws Exception {
        return codeMasterMapper.getSelectAttr(codeGroupCd, attr1, attr2, attr3, defaultParam);
    }

    /**
     * 양식 path + 양식파일명 조회
     *
     * @param templeteFileNameCode
     *            양식 파일명 저장된 코드
     * @return 양식 path + 양식파일명
     * @throws Exception
     */
    public File getTempleteFilePathName(String templeteFileNameCode) throws Exception {
        String fullPath = "";
        CodeMaster path = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_PATH, ConstVal.CODE_FILE_PATH_FORM, "Y");

        String code = "";
        if ("riskHazard".equals(templeteFileNameCode)) {
            code = ConstVal.FILE_NAME_RISK_HAZARD;
        } else if ("workMeasureResultPhysical".equals(templeteFileNameCode)) {
            code = ConstVal.FILE_NAME_WORK_MEASURE_RESULT_PHYSICAL;
        } else if ("workMeasureResultChemical".equals(templeteFileNameCode)) {
            code = ConstVal.FILE_NAME_WORK_MEASURE_RESULT_CHEMICAL;
        } else if ("job".equals(templeteFileNameCode)) {
            code = ConstVal.FILE_NAME_JOB;
        }

        CodeMaster fileName = this.codeMasterMapper.getCodeMaster(ConstVal.CODE_GROUP_FILE_NAME, code, "Y");

        fullPath = path.getCodeNm() + "\\" + fileName.getCodeNm();

        return new File(fullPath);
    }

    /**
     * 공용코드 전체 조회
     *
     * @param settableYn
     *            설정가능여부
     * @param codeDomainCd
     *            코드도메인
     * @param codeGroupCd
     *            코드그룹
     * @param codeNm
     *            코드명
     * @param attr1
     *            추가 속성1
     * @param attr2
     *            추가 속성2
     * @param attr3
     *            추가 속성3
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeMaster> getCodeMasters(String settableYn, String codeDomainCd, String codeGroupCd, String codeNm, String useYn) throws Exception {
        return this.codeMasterMapper.getCodeMasters(settableYn, codeDomainCd, codeGroupCd, codeNm, useYn);
    }

    /**
     * 공용코드 상세 조회
     *
     * @param codeGroupCd
     *            코드그룹
     * @param code
     *            코드
     * @param forSystemYn
     * @return 코드마스터
     * @throws Exception
     */
    public CodeMaster getCodeMaster(String codeGroupCd, String code, String forSystemYn) throws Exception {
        CodeMaster codeMaster = this.codeMasterMapper.getCodeMaster(codeGroupCd, code, forSystemYn);

        if (codeMaster != null) {
            codeMaster.setInvestItems(codeMasterMapper.getInvestItemsInCodeMaster(codeGroupCd, code, forSystemYn));
        }
        return codeMaster;
    }

    /**
     * 공용코드 신규 등록
     *
     * @param codeMaster
     *            코드마스터
     * @return 생성행수
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "ComCodeSelectCashe", "ComCodeAttrSelectCashe" }, allEntries = true)
    public int createCodeMaster(CodeMaster codeMaster) throws Exception {
        int result = this.codeMasterMapper.createCodeMaster(codeMaster);
        if (codeMaster.getInvestItems() != null && codeMaster.getInvestItems().size() > 0) {
            for (InvestItem investItem : codeMaster.getInvestItems()) {
                codeMasterMapper.createInvestItem(investItem);
            }
        }
        return result;
    }

    /**
     * 공용코드 수정
     *
     * @param codeMaster
     *            코드마스터
     * @return 수정행수
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "ComCodeSelectCashe", "ComCodeAttrSelectCashe" }, allEntries = true)
    public int updateCodeMaster(CodeMaster codeMaster) throws Exception {
        int result = codeMasterMapper.updateCodeMaster(codeMaster);

        if (codeMaster.getInvestItems() != null && codeMaster.getInvestItems().size() > 0) {
            for (InvestItem investItem : codeMaster.getInvestItems()) {
                int investResult = codeMasterMapper.selectInvestItem(investItem);
                if (investResult > 0) {
                    codeMasterMapper.updateInvestItem(investItem);
                } else {
                    codeMasterMapper.createInvestItem(investItem);
                }
            }
        }

        if (codeMaster.getDeleteInvestItems() != null && codeMaster.getDeleteInvestItems().size() > 0) {
            for (InvestItem investItem : codeMaster.getDeleteInvestItems()) {
                if (investItem.getMgtMgInfoItmNo() > 0) {
                    codeMasterMapper.deleteInvestItem(investItem);
                }
            }
        }
        return result;
    }

    /**
     * 공용코드 전체 조회
     *
     * @param forSystemYn
     * @param settableYn
     *            설정가능여부
     * @param codeDomainCd
     *            코드도메인
     * @param codeGroupCd
     *            코드그룹
     * @param codeNm
     *            코드명
     * @param attr1
     *            추가 속성1
     * @param attr2
     *            추가 속성2
     * @param attr3
     *            추가 속성3
     * @return 코드목록
     * @throws Exception
     */
    public List<CodeMaster> getCodeAllMasters(String forSystemYn, String settableYn, String codeDomainCd, String codeGroupCd, String codeNm, String attr1, String attr2, String attr3, String codeGroupNm) throws Exception {
        return this.codeMasterMapper.getCodeAllMasters(forSystemYn, settableYn, codeDomainCd, codeGroupCd, codeNm, attr1, attr2, attr3, codeGroupNm);
    }

    public int getDuplicationCheck(String mgtMgInfoItmNo) throws Exception {
        int result = this.codeMasterMapper.getDuplicationCheck(mgtMgInfoItmNo);
        return result;
    }
}
