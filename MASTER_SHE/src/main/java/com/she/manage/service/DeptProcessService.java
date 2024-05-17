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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.mapper.DeptProcessMapper;
import com.she.manage.model.DeptProcess;

/**
 * 부서별 공정 기능정의
 *
 */
@Service
public class DeptProcessService {

    @Autowired
    private DeptProcessMapper deptProcessMapper;

    /**
     * 부서별 공정 조회
     * 
     * @param processNo
     *            공정번호
     * @param processNm
     *            공정명
     * @param deptCd
     *            부서코드
     * @param deptNm
     *            부서명
     * @return 부서별 공정 목록
     * @throws Exception
     */
    public List<DeptProcess> getDeptProcesses(int processNo, String processNm, String deptCd, String deptNm)
            throws Exception {
        return this.deptProcessMapper.getDeptProcesses(processNo, processNm, deptCd, deptNm);
    }

}
