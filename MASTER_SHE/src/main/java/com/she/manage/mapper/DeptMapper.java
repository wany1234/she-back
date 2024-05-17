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

import com.she.common.model.DefaultParam;
import com.she.manage.model.Dept;

/**
 * 부서 맵퍼
 *
 */
@Mapper
@Repository("com.she.manage.mapper.DeptMapper")
public interface DeptMapper {

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
    public List<Dept> getDepts(@Param("plantCd") String plantCd, @Param("processCd") String processCd, @Param("deptCd") String deptCd, @Param("deptNm") String deptNm, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 부서 상세 조회
     * 
     * @param deptCd
     *            부서코드
     * @return 부서
     * @throws Exception
     */
    public Dept getDept(@Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public List<Dept> getTreeDepts(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptNm") String deptNm, @Param("pdeptCd") String pdeptCd, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public List<Dept> getTreeDeptsForMobile(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("deptNm") String deptNm, @Param("pdeptCd") String pdeptCd, @Param("useYn") String useYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 부서 생성
     * 
     * @param dept
     *            부서정보
     * @return 부서코드
     * @throws Exception
     */
    public int createDept(Dept dept) throws Exception;

    /**
     * dept 중복검사
     * 
     * @param deptCd
     * @return 중복 행 수
     * @throws Exception
     */
    public int countDept(@Param("deptCd") String deptCd) throws Exception;

    /**
     * 부서 수정
     * 
     * @param dept
     *            부서정보
     * @return 부서코드
     * @throws Exception
     */
    public int updateDept(Dept dept) throws Exception;

    /**
     * 부서별 공정 생성
     * 
     * @param deptCd
     *            부서코드
     * @param processCd
     *            공정번호
     * @return 부서코드
     * @throws Exception
     */
    public int createProcessDept(@Param("deptCd") String deptCd, @Param("processCd") String processCd, @Param("createUserId") String createUserId) throws Exception;

    /**
     * 부서별 공정 삭제
     * 
     * @param deptCd
     *            부서코드
     * @param processCd
     *            공정코드
     * @return 부서코드
     * @throws Exception
     */
    public int deleteProcessDept(@Param("deptCd") String deptCd, @Param("processCd") String processCd) throws Exception;

}
