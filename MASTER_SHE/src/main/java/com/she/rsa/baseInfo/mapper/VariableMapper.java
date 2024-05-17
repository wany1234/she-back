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
package com.she.rsa.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.rsa.model.Variable;
import com.she.rsa.model.VariableUseGuideword;
import com.she.rsa.model.VariableUseGuidewordPosibleCause;

@Mapper
@Repository("com.she.rsa.baseInfo.mapper.VariableMapper")
public interface VariableMapper {

    /**
     * 변수 조회
     *
     * @param useYn
     *            사용여부
     * @return 변수 목록
     * @throws Exception
     */
    public List<Variable> getVariables(@Param("useYn") String useYn) throws Exception;

    /**
     * 변수 상세 조회
     *
     * @param varId
     *            변수ID
     * @return 변수
     * @throws Exception
     */
    public Variable getVariable(@Param("varId") int varId) throws Exception;

    /**
     * 변수 신규등록
     *
     * @param variable
     *            변수
     * @return 등록 행 수
     * @throws Exception
     */
    public int createVariable(Variable variable) throws Exception;

    /**
     * 변수 수정
     *
     * @param variable
     *            변수
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateVariable(Variable variable) throws Exception;

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
    public List<HashMap<String, Object>> getCheckVariables(@Param("varNmKr") String varNmKr, @Param("varNmEn") String varNmEn, @Param("varId") int varId) throws Exception;

    /**
     * 변수에서 사용하는 가이드워드 조회
     *
     * @return 체크 값
     * @throws Exception
     */
    public List<VariableUseGuideword> getVariableUseGuidewords(@Param("varId") int varId, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 변수에서 사용하는 가이드워드 신규등록
     *
     * @param variableUseGuideword
     *            변수에서 사용하는 가이드워드 변수
     * @return 등록 행 수
     * @throws Exception
     */
    public int createVariableUseGuideword(VariableUseGuideword variableUseGuideword) throws Exception;

    /**
     * 변수에서 사용하는 가이드워드 수정
     *
     * @param variableUseGuideword
     *            변수에서 사용하는 가이드워드 변수
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateVariableUseGuideword(VariableUseGuideword variableUseGuideword) throws Exception;

    /**
     * 가이드워드 별 가능한 원인 조회
     *
     * @return 가이드워드 별 가능한 원인 목록
     * @throws Exception
     */
    public List<VariableUseGuidewordPosibleCause> getVariableUseGuidewordPosibleCauses(@Param("varId") int varId, @Param("guidewordCd") String guidewordCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 가능한 원인 신규등록
     *
     * @param variableUseGuidewordPosibleCause
     *            가능한 원인
     * @return 등록 행 수
     * @throws Exception
     */
    public int createVariableUseGuidewordPosibleCause(VariableUseGuidewordPosibleCause variableUseGuidewordPosibleCause) throws Exception;

    /**
     * 가능한 원인 삭제
     *
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteVariableUseGuidewordPosibleCause(@Param("varId") int varId, @Param("guidewordCd") String guidewordCd) throws Exception;

    /**
     * 변수 조회
     *
     * @param useYn
     *            사용여부
     * @return 변수 목록
     * @throws Exception
     */
    public List<String> getGuidewordCds() throws Exception;

}
