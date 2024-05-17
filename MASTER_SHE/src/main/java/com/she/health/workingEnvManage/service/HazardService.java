package com.she.health.workingEnvManage.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.health.model.Hazard;
import com.she.health.model.HazardProcess;
import com.she.health.workingEnvManage.mapper.HazardMapper;

/**
 * 유해인자 기능정의
 *
 */
@Service
public class HazardService {

    @Autowired
    private HazardMapper hazardMapper;

    /**
     * 유해인자 조회
     *
     * @param 검색조건
     * @return 유해인자 목록
     * @throws Exception
     */
    public List<Hazard> getHazards(String hazardClsCd, String hazardGradCd, String hazardNmKo, String hazardNmEn,
            String specialYn, String workEnvYn) throws Exception {
        return this.hazardMapper.getHazards(hazardClsCd, hazardGradCd, hazardNmKo, hazardNmEn, specialYn, workEnvYn);
    }

    /**
     * 유해인자 상세 조회
     *
     * @param heaHazardCd
     *            유해인자 코드
     * @return 유해인자
     * @throws Exception
     */
    public Hazard getHazard(String hazardCd) throws Exception {
        Hazard hazard = this.hazardMapper.getHazard(hazardCd);
        if (hazard != null) {
            // 유해인자 취급공정 목록 조회
            hazard.setHazardProcessList(this.hazardMapper.getHazardProcessList(hazardCd));
        }
        return hazard;
    }

    /**
     * 유해인자 중복체크
     * 
     * @param hazardNmKo
     *            유해인자한글명
     * @param hazardNmEn
     *            유해인자영어명
     * @param hazardCd
     *            유해인자코드
     * @return 중복 행 수
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCountHazard(String hazardNmKo, String hazardNmEn, String hazardCd)
            throws Exception {
        return this.hazardMapper.getCountHazard(hazardNmKo, hazardNmEn, hazardCd);
    }

    /**
     * 유해인자 신규등록
     *
     * @param hazard
     *            유해인자
     * @return 생성 행 수
     * @throws Exception
     */
    @Transactional
    public String createHazard(Hazard hazard) throws Exception {
        int result = this.hazardMapper.createHazard(hazard);

        if (result > 0) {
            // 유해인자 대분류가 [물리적 인자]인 경우
            if ("P".equals(hazard.getHazardClsCd())) {
                // 등록된 유해인자 취급공정 삭제
                hazardMapper.deleteHazardProcess(hazard.getHazardCd());

                if (CollectionUtils.isNotEmpty(hazard.getHazardProcessList())) {
                    for (HazardProcess hazardProcess : hazard.getHazardProcessList()) {
                        hazardProcess.setHazardCd(hazard.getHazardCd());
                        hazardMapper.createHazardProcess(hazardProcess);
                    }
                }
            }
        }
        return hazard.getHazardCd();
    }

    /**
     * 유해인자 수정
     *
     * @param hazard
     *            유해인자
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public String updateHazard(Hazard hazard) throws Exception {
        int result = this.hazardMapper.updateHazard(hazard);

        if (result > 0) {
            // 유해인자 대분류가 [물리적 인자]인 경우
            if ("P".equals(hazard.getHazardClsCd())) {
                // 등록된 유해인자 취급공정 삭제
                hazardMapper.deleteHazardProcess(hazard.getHazardCd());

                if (CollectionUtils.isNotEmpty(hazard.getHazardProcessList())) {
                    for (HazardProcess hazardProcess : hazard.getHazardProcessList()) {
                        hazardProcess.setHazardCd(hazard.getHazardCd());
                        hazardMapper.createHazardProcess(hazardProcess);
                    }
                }
            }
        }
        return hazard.getHazardCd();
    }

}
