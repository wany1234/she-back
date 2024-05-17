package com.she.rsa.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.mapper.AttachFileMapper;
import com.she.file.service.FileStorageService;
import com.she.manage.mapper.CodeMasterMapper;
import com.she.rsa.baseInfo.mapper.RiskHazardMapper;
import com.she.rsa.model.RiskHazard;

@Service
public class RiskHazardService {

    @Autowired
    private RiskHazardMapper riskHazardMapper;

    @Autowired
    private AttachFileMapper attachFileMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CodeMasterMapper codeMasterMapper;

    /**
     * 유해위험요인 조회
     *
     * @param priskHazardNo
     *            유해위험요인 Lv1
     * @param riskHazardNm
     *            유해위험요인 Lv2
     * @param riskHazardLevel
     *            유해위험요소 레벨
     * @return 유해위험요인 목록
     * @throws Exception
     */
    public List<RiskHazard> getRiskHazards(int priskHazardNo, String riskHazardNm, int riskHazardLevel, String useYn) throws Exception {
        return riskHazardMapper.getRiskHazards(priskHazardNo, riskHazardNm, riskHazardLevel, useYn);
    }

    /**
     * 유해위험요인 상세 조회
     *
     * @param riskHazardNo
     *            유해위험요인 번호
     * @return 유해위험요인
     * @throws Exception
     */
    public RiskHazard getRiskHazard(int riskHazardNo) throws Exception {
        return this.riskHazardMapper.getRiskHazard(riskHazardNo);
    }

    /**
     * 유해위험요인 신규등록
     *
     * @param riskHazard
     *            유해위험요인
     * @return 유해위험요인 번호
     * @throws Exception
     */
    @Transactional
    public int createRiskHazard(RiskHazard riskHazard) throws Exception {
        riskHazard.setRiskHazardLevel(2);
        this.riskHazardMapper.createRiskHazard(riskHazard);

        return riskHazard.getRiskHazardNo();
    }

    /**
     * 유해위험요인 수정
     *
     * @param riskHazard
     *            유해위험요인
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateRiskHazard(RiskHazard riskHazard) throws Exception {
        return this.riskHazardMapper.updateRiskHazard(riskHazard);
    }

    /**
     * 유해위험요인 명칭 체크
     *
     * @param priskHazardNo
     *            상위 번호
     * @param riskHazardNo
     *            유해위험요인 번호
     * @param riskHazardNm
     *            유해위험요인명
     * @return 수정 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> getRiskHazardCheck(int priskHazardNo, int riskHazardNo, String riskHazardNm) throws Exception {
        return this.riskHazardMapper.getRiskHazardCheck(priskHazardNo, riskHazardNo, riskHazardNm);
    }

}
