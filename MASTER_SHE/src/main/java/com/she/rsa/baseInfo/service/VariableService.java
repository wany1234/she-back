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
package com.she.rsa.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.rsa.baseInfo.mapper.VariableMapper;
import com.she.rsa.model.Variable;
import com.she.rsa.model.VariableUseGuideword;
import com.she.rsa.model.VariableUseGuidewordPosibleCause;

@Service
public class VariableService {

    @Autowired
    private VariableMapper variableMapper;

    /**
     * 변수 조회
     *
     * @return 변수 목록
     * @throws Exception
     */
    public List<Variable> getVariables(String useYn) throws Exception {
        return variableMapper.getVariables(useYn);
    }

    /**
     * 변수 상세 조회
     *
     * @param varId
     *            변수ID
     * @return 변수
     * @throws Exception
     */
    public Variable getVariable(int varId, DefaultParam defaultParam) throws Exception {
        // 변수 조회
        Variable variable = this.variableMapper.getVariable(varId);
        // 변수에서 사용가능한 가이드워드 조회
        variable.setVariableUseGuideword(this.variableMapper.getVariableUseGuidewords(varId, defaultParam));
        if (CollectionUtils.isNotEmpty(variable.getVariableUseGuideword())) {
            for (VariableUseGuideword variableUseGuideword : variable.getVariableUseGuideword()) {
                variableUseGuideword.setPosibleCauses(this.variableMapper.getVariableUseGuidewordPosibleCauses(varId, variableUseGuideword.getGuidewordCd(), defaultParam));
            }
        }
        return variable;
    }

    /**
     * 변수 신규등록
     *
     * @param Variable
     *            변수
     * @return 변수 번호
     * @throws Exception
     */
    @Transactional
    public int createVariable(Variable variable) throws Exception {
        // 변수 등록
        this.variableMapper.createVariable(variable);

        // 변수에서 사용가능한 가이드워드 등록
        if (CollectionUtils.isNotEmpty(variable.getVariableUseGuideword())) {
            for (VariableUseGuideword variableUseGuideword : variable.getVariableUseGuideword()) {
                variableUseGuideword.setVarId(variable.getVarId());
                this.variableMapper.createVariableUseGuideword(variableUseGuideword);

                // 가이드워드 별 가능한 원인 등록
                if (CollectionUtils.isNotEmpty(variableUseGuideword.getPosibleCauses())) {
                    for (VariableUseGuidewordPosibleCause variableUseGuidewordPosibleCause : variableUseGuideword.getPosibleCauses()) {
                        variableUseGuidewordPosibleCause.setVarId(variable.getVarId());
                        variableUseGuidewordPosibleCause.setGuidewordCd(variableUseGuideword.getGuidewordCd());
                        this.variableMapper.createVariableUseGuidewordPosibleCause(variableUseGuidewordPosibleCause);
                    }
                }
            }
        }

        return variable.getVarId();
    }

    /**
     * 변수 수정
     *
     * @param Variable
     *            변수
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateVariable(Variable variable) throws Exception {
        // 변수 수정
        this.variableMapper.updateVariable(variable);

        // 변수에서 사용가능한 가이드워드 저장
        if (CollectionUtils.isNotEmpty(variable.getVariableUseGuideword())) {
            for (VariableUseGuideword variableUseGuideword : variable.getVariableUseGuideword()) {
                if (variableUseGuideword.getVarId() > 0) {
                    // 수정
                    this.variableMapper.updateVariableUseGuideword(variableUseGuideword);
                } else {
                    // 등록
                    variableUseGuideword.setVarId(variable.getVarId());
                    this.variableMapper.createVariableUseGuideword(variableUseGuideword);
                }

                // 가이드워드 별 가능한 원인 저장
                this.variableMapper.deleteVariableUseGuidewordPosibleCause(variable.getVarId(), variableUseGuideword.getGuidewordCd());
                if (CollectionUtils.isNotEmpty(variableUseGuideword.getPosibleCauses())) {
                    for (VariableUseGuidewordPosibleCause variableUseGuidewordPosibleCause : variableUseGuideword.getPosibleCauses()) {
                        variableUseGuidewordPosibleCause.setVarId(variable.getVarId());
                        variableUseGuidewordPosibleCause.setGuidewordCd(variableUseGuideword.getGuidewordCd());
                        this.variableMapper.createVariableUseGuidewordPosibleCause(variableUseGuidewordPosibleCause);
                    }
                }
            }
        }
        return variable.getVarId();
    }

    /**
     * 변수명 체크
     *
     * @param varNmKr
     *            변수명(KOR)
     * @param varNmEn
     *            변수명 (ENG)
     * @param varId
     *            변수ID
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckVariables(String varNmKr, String varNmEn, int varId) throws Exception {
        return variableMapper.getCheckVariables(varNmKr, varNmEn, varId);
    }

}
