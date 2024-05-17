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
package com.she.psm.processFacility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.Chemprod;

@Mapper
@Repository("com.she.psm.processFacility.mapper.PipeChemprodMapper")
public interface PipeChemprodMapper {
    /**
     * 배관별 취급물질 조회
     * 
     * @param plantCd
     *            사업장
     * @param chemProdNmKr
     *            취급물질명
     * @param pipeNo
     *            배관번호
     * @return 배관별 취급물질 목록
     * @throws Exception
     */
    public List<Chemprod> getPipeChemprods(@Param("plantCd") String plantCd, @Param("chemProdNmKr") String chemProdNmKr,
            @Param("pipeNo") int equipmentNo) throws Exception;

    /**
     * 배관별 취급물질 등록
     * 
     * @param Chemprod
     * @return 변경 행 수
     * @throws Exception
     */
    public int createPipeChemprod(Chemprod chemprod) throws Exception;

    /**
     * 배관별 취급물질 삭제
     * 
     * @param chemProdNo
     * @param pipeNo
     * @return 변경 행 수
     * @throws Exception
     */
    public int deletePipeChemprod(@Param("chemProdNo") int chemProdNo, @Param("pipeNo") int pipeNo) throws Exception;

}
