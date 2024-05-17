package com.she.env.envEffectEval.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.env.envEffectEval.model.EnvEffectEval;

@Mapper
@Repository("com.she.env.envEffectEval.mapper.envEffectEvalMapper")
public interface EnvEffectEvalMapper {

    /**
     * 환경 > 환경영향평가 > 환경영향평가 목록 조회
     *
     * @param parameter
     *            검색필드
     * @return 환경영향평가 목록
     * @throws Exception
     */
    List<EnvEffectEval> getEnvEffectEvals(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("evalGubun") String evalGubun, @Param("evalProgState") String evalProgState, @Param("evalStepCd") String evalStepCd, @Param("proxyVendorCd") String proxyVendorCd, @Param("businessName") String businessName,
            @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 등록
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 등록행수
     * @throws Exception
     */
    int createEnvEffectEval(EnvEffectEval envEffectEval);

    /**
     * 환경 > 환경영향평가 > 환경영향평가 조회
     *
     * @param envEffectEvalNo
     * @return 환경영향평가
     * @throws Exception
     */
    EnvEffectEval getEnvEffectEval(int envEffectEvalNo);

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 수정
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 수정행수
     * @throws Exception
     */
    int updateEnvEffectEval(EnvEffectEval envEffectEval);

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가탭 수정
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 수정행수
     * @throws Exception
     */
    int updateEnvEffectEvalTab(EnvEffectEval envEffectEval);

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 삭제
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 삭제행수
     * @throws Exception
     */
    int deleteEnvEffectEval(int envEffectEvalNo);
}
