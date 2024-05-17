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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.manage.mapper.DeptMapper;
import com.she.manage.mapper.ProcessMapper;
import com.she.manage.model.Dept;
import com.she.manage.model.Process;

/**
 * 부서 기능정의
 *
 */
@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private ProcessMapper processMapper;

    /**
     * 부서 조회
     * 
     * @param plantCd
     *            사업장
     * @param processCd
     *            공정번호
     * @param deptCd
     *            부서코드
     * @param deptNm
     *            부서명
     * @param useYn
     *            사용여부
     * @return 부서목록
     * @throws Exception
     */
    public List<Dept> getDepts(String plantCd, String processCd, String deptCd, String deptNm, String useYn, DefaultParam defaultParam) throws Exception {
        return this.deptMapper.getDepts(plantCd, processCd, deptCd, deptNm, useYn, defaultParam);
    }

    /**
     * 부서 상세 조회
     * 
     * @param deptCd
     *            부서코드
     * @return 부서
     * @throws Exception
     */
    public Dept getDept(String deptCd, DefaultParam defaultParam) throws Exception {
        Dept dept = this.deptMapper.getDept(deptCd, defaultParam);
        dept.setSelectProcess(this.processMapper.getProcesses("", "", deptCd, "", "", defaultParam));
        return dept;
    }

    /**
     * 부서 트리 조회
     * 
     * @param plantCd
     *            사업장
     * @param deptCd
     *            부서코드
     * @param deptNm
     *            부서명
     * @param pdeptCd
     *            상위부서
     * @param useYn
     *            사용여부
     * @return 부서목록
     * @throws Exception
     */
    @Cacheable(cacheNames = "DeptTreeCashe", key = "'DEPTTREE' + #plantCd + #deptCd + #deptNm + #pdeptCd + #useYn")
    public List<Dept> getTreeDepts(String plantCd, String deptCd, String deptNm, String pdeptCd, String useYn, DefaultParam defaultParam) throws Exception {
        return this.deptMapper.getTreeDepts(plantCd, deptCd, deptNm, pdeptCd, useYn, defaultParam);
    }

    /**
     * 부서 트리 조회
     * 
     * @param plantCd
     *            사업장
     * @param deptCd
     *            부서코드
     * @param deptNm
     *            부서명
     * @param pdeptCd
     *            상위부서
     * @param useYn
     *            사용여부
     * @return 부서목록
     * @throws Exception
     */
    @Cacheable(cacheNames = "DeptTreeMobileCashe", key = "'DEPTTREE' + #plantCd + #deptCd + #deptNm + #pdeptCd + #useYn")
    public List<Dept> getTreeDeptsForMobile(String plantCd, String deptCd, String deptNm, String pdeptCd, String useYn, DefaultParam defaultParam) throws Exception {
        return this.deptMapper.getTreeDeptsForMobile(plantCd, deptCd, deptNm, pdeptCd, useYn, defaultParam);
    }

    /**
     * 부서 생성
     * 
     * @param dept
     *            부서정보
     * @return 부서코드
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "DeptTreeCashe", "DeptTreeMobileCashe" }, allEntries = true)
    public String createDept(Dept dept) throws Exception {
        this.deptMapper.createDept(dept);

        if (dept.getSelectProcess() != null) {
            for (Process process : dept.getSelectProcess()) {
                this.deptMapper.createProcessDept(dept.getDeptCd(), process.getProcessCd(), dept.getCreateUserId());
            }
        }

        return dept.getDeptCd();
    }

    /**
     * 부서 수정
     * 
     * @param dept
     *            부서정보
     * @return 부서코드
     * @throws Exception
     */
    @CacheEvict(cacheNames = { "DeptTreeCashe", "DeptTreeMobileCashe" }, allEntries = true)
    public String updateDept(Dept dept) throws Exception {
        this.deptMapper.updateDept(dept);

        if (dept.getSelectProcess() != null) {
            this.deptMapper.deleteProcessDept(dept.getDeptCd(), "");
        }
        if (dept.getSelectProcess() != null) {
            for (Process process : dept.getSelectProcess()) {
                this.deptMapper.createProcessDept(dept.getDeptCd(), process.getProcessCd(), dept.getCreateUserId());
            }
        }
        return dept.getDeptCd();
    }

    /**
     * 부서별 공정 신규등록
     * 
     * @param process
     * @return 등록 행 수
     * @throws Exception
     */
	@CacheEvict(cacheNames = { "DeptTreeCashe", "DeptTreeMobileCashe" }, allEntries = true)
    public int createDeptProcess(List<Process> processes) throws Exception {
        int resultNo = 0;
        for (Process process : processes) {
            resultNo += this.deptMapper.createProcessDept(process.getDeptCd(), process.getProcessCd(), process.getCreateUserId());
        }
        return resultNo;
    }

    /**
     * 부서별 공정 신규등록
     * 
     * @param process
     * @return 등록 행 수
     * @throws Exception
     */
	@CacheEvict(cacheNames = { "DeptTreeCashe", "DeptTreeMobileCashe" }, allEntries = true)
    public int deleteDeptProcess(List<Process> processes) throws Exception {
        int resultNo = 0;
        for (Process process : processes) {
            resultNo += this.deptMapper.deleteProcessDept("", process.getProcessCd());
        }
        return resultNo;
    }

    /**
     * Dept 중복검사
     * 
     * @param deptCd
     * @return 중복 행 수
     * @throws Exception
     */
    public int countDept(String deptCd) throws Exception {
        return this.deptMapper.countDept(deptCd);
    }

}
