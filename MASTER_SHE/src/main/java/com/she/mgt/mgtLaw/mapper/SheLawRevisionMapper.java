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
package com.she.mgt.mgtLaw.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.manage.model.CodeMaster;
import com.she.mgt.model.MgtLawRevision;

@Mapper
@Repository("com.she.mgt.mgtLaw.mapper.SheLawRevisionMapper")
public interface SheLawRevisionMapper {

    /***
     * 개정법규 목록 조회
     *
     * @param lkey
     *            법령ID
     * @param stYd
     *            시작일
     * @param edYd
     *            종료일
     * @return 개정법규 목록
     * @throws Exception
     */
    public List<MgtLawRevision> getLawRevisionList(@Param("lkey") String lkey, @Param("stYd") String stYd, @Param("edYd") String edYd) throws Exception;

    /***
     * 법령 콤보 조회
     *
     * @return 법령
     * @throws Exception
     */
    public List<CodeMaster> getLawRevisionCombo() throws Exception;

    /***
     * 법조 조회
     *
     * @return 법조리스트
     * @throws Exception
     */
    List<HashMap<String, Object>> getLawJomun(@Param("legiKey") String legiKey);
}
