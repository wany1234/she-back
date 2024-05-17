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

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.ProcessMapper;
import com.she.manage.model.Process;

/**
 * 작업공정 기능정의
 *
 */
@Service
public class ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    /**
     * 작업공정 조회
     * 
     * @param useYn
     *            사용여부
     * @param nodeYn
     *            노드여부
     * @param heaHazardCd
     *            유해인자 코드
     * @param deptCd
     *            부서 코드
     * @param processNm
     *            공정명
     * @param plantCd
     *            사업장
     * @return 작업공정목록
     * @throws Exception
     */
    public List<Process> getProcesses(String useYn, String heaHazardCd, String deptCd, String processNm, String plantCd, DefaultParam defaultParam) throws Exception {
        return this.processMapper.getProcesses(useYn, heaHazardCd, deptCd, processNm, plantCd, defaultParam);
    }

    /**
     * 작업공정 상세 조회
     * 
     * @param processCd
     *            공정코드
     * @return 작업공정
     * @throws Exception
     */
    public Process getProcess(String processCd, DefaultParam defaultParam) throws Exception {
        Process process = this.processMapper.getProcess(processCd);
        if (process != null) {
            process.setPlants(this.processMapper.getProcessPlants(processCd, defaultParam));
            process.setDepts(this.processMapper.getProcessDepts(processCd));
        }
        return process;
    }

    /**
     * 작업공정 생성
     * 
     * @param process
     *            작업공정
     * @return 공정번호
     * @throws Exception
     */
    public String createProcess(Process process) throws Exception {
        return this.processMapper.createProcess(process) > 0 ? process.getProcessCd() : "";
    }

    /**
     * 작업공정 수정
     * 
     * @param process
     *            작업공정
     * @return 변경적용 행 수
     * @throws Exception
     */
    public int updateProcess(Process process) throws Exception {
        return this.processMapper.updateProcess(process);
    }

    /**
     * 작업공정 중복검사
     * 
     * @param processCd
     *            공정코드
     * @param processNm
     *            공정명
     * @return 중복검사
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckProcesses(String processCd, String processNm) throws Exception {
        return this.processMapper.getCheckProcesses(processCd, processNm);
    }
}
