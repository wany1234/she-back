package com.she.env.envEffectEval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.env.envEffectEval.mapper.EnvEffectEvalMapper;
import com.she.env.envEffectEval.model.EnvEffectEval;

@Service
public class EnvEffectEvalService {

    @Autowired
    private EnvEffectEvalMapper envEffectEvalMapper;

    /**
     * 환경 > 환경영향평가 > 환경영향평가 목록 조회
     *
     * @param parameter
     *            검색필드
     * @return 환경영향평가 목록
     * @throws Exception
     */
    public List<EnvEffectEval> getEnvEffectEvals(String plantCd, String deptCd, String evalGubun, String evalProgState, String evalStepCd, String proxyVendorCd, String businessName, DefaultParam defaultParam) throws Exception {
        return envEffectEvalMapper.getEnvEffectEvals(plantCd, deptCd, evalGubun, evalProgState, evalStepCd, proxyVendorCd, businessName, defaultParam);
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 조회
     *
     * @param envEffectEvalNo
     * @return 환경영향평가
     * @throws Exception
     */
    public EnvEffectEval getEnvEffectEval(int envEffectEvalNo) {
        return envEffectEvalMapper.getEnvEffectEval(envEffectEvalNo);
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 등록
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 환경영향평가 번호
     * @throws Exception
     */
    public int createEnvEffectEval(EnvEffectEval envEffectEval) throws Exception {
        envEffectEvalMapper.createEnvEffectEval(envEffectEval);
        return envEffectEval.getEnvEffectEvalNo();
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 수정
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 환경영향평가 번호
     * @throws Exception
     */
    public int updateEnvEffectEval(EnvEffectEval envEffectEval) throws Exception {
        envEffectEvalMapper.updateEnvEffectEval(envEffectEval);
        return envEffectEval.getEnvEffectEvalNo();
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가탭 수정
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 환경영향평가 번호
     * @throws Exception
     */
    public int updateEnvEffectEvalTab(EnvEffectEval envEffectEval) throws Exception {
        envEffectEvalMapper.updateEnvEffectEvalTab(envEffectEval);
        return envEffectEval.getEnvEffectEvalNo();
    }

    /**
     * 환경 > 환경영향평가 > 환경영향평가 환경영향평가 삭제
     *
     * @param EnvEffectEval
     *            envEffectEval
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteEnvEffectEval(int envEffectEvalNo) throws Exception {
        return envEffectEvalMapper.deleteEnvEffectEval(envEffectEvalNo);
    }
}
