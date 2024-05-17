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
package com.she.safety.noaccident.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.safety.model.NoAccident;

@Mapper
@Repository("com.she.safety.accident.mapper.NoAccidentMapper")
public interface NoAccidentMapper {

    /**
     * 사업장 무재해 조회
     * 
     * @param startYmd
     *            무재해시작일
     * @param endSchYmd
     *            무재해달성예정일
     * @param plantCd
     *            사업장코드
     * @param searchFlag
     *            조회구분 'LIST'목록 'HIS' 이력
     * @return
     * @throws Exception
     */
    public List<NoAccident> getNoAccidents(@Param("startYmd") String startYmd, @Param("endSchYmd") String endSchYmd, @Param("plantCd") String plantCd, @Param("searchFlag") String searchFlag, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 부서 무재해 조회
     * 
     * @param startYmd
     *            무재해시작일
     * @param endSchYmd
     *            무재해달성예정일
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param searchFlag
     *            조회구분 'LIST'목록 'HIS' 이력
     * @param deptSubYn
     *            하위부서여부(Y:하위부서까지 포함해서 조회/N:해당부서만 조회)
     * 
     * @return
     * @throws Exception
     */
    public List<NoAccident> getDeptNoAccidents(@Param("startYmd") String startYmd, @Param("endSchYmd") String endSchYmd, @Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("searchFlag") String searchFlag, @Param("deptSubYn") String deptSubYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 사업장 무재해 상세
     * 
     * @param safNoAccidentNo
     * @return
     * @throws Exception
     */
    public NoAccident getNoAccident(@Param("safNoAccidentNo") int safNoAccidentNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 부서 무재해 상세
     * 
     * @param safNoAccidentDeptNo
     * @return
     * @throws Exception
     */
    public NoAccident getDeptNoAccident(@Param("safNoAccidentDeptNo") int safNoAccidentDeptNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 활성화된 무재해 상세 조회
     * 
     * @return NoAccident
     * @throws Exception
     */
    public NoAccident getNoAccidentLastDetail() throws Exception;

    /**
     * 사업장 등록
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer createNoAccident(NoAccident noAccident) throws Exception;

    /**
     * 사업장 수정
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer updateNoAccident(NoAccident noAccident) throws Exception;

    /**
     * 사업장 무재해 삭제
     * 
     * @param safNoAccidentNo
     * @param updateUserId
     * @return
     * @throws Exception
     */
    public int deleteNoAccident(@Param("safNoAccidentNo") int safNoAccidentNo, @Param("updateUserId") String updateUserId) throws Exception;

    /**
     * 부서 등록
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer createDeptNoAccident(NoAccident noAccident) throws Exception;

    /**
     * 부서 수정
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer updateDeptNoAccident(NoAccident noAccident) throws Exception;

    /**
     * 부서 무재해 삭제
     * 
     * @param safNoAccidentDeptNo
     * @param updateUserId
     * @return
     * @throws Exception
     */
    public int deleteDeptNoAccident(@Param("safNoAccidentDeptNo") int safNoAccidentDeptNo, @Param("updateUserId") String updateUserId) throws Exception;

    /**
     * 활성화된 무재고 수정
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer updateLastNoAccident(NoAccident noAccident) throws Exception;

    /**
     * 사업장 무재해 건수 조회
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer countNoAccident(@Param("plantCd") String plantCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 부서 무재해 건수 조회
     * 
     * @param noAccident
     * @return
     * @throws Exception
     */
    public Integer countDeptNoAccident(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;
}
