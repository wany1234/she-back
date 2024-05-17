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

package com.she.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.DeptProcess;

/**
 * 부서별 공정
 */
@Mapper
@Repository("com.she.manage.mapper.DeptProcessMapper")
public interface DeptProcessMapper {

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
    public List<DeptProcess> getDeptProcesses(@Param("processNo") int processNo, @Param("processNm") String processNm,
            @Param("deptCd") String deptCd, @Param("deptNm") String deptNm) throws Exception;

    public List<DeptProcess> getDeptProcessesMatching(@Param("deptCd") String deptCd, @Param("processCd") String processCd) throws Exception;

}
