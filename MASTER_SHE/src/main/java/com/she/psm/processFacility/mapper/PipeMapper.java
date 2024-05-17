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

import com.she.psm.model.Pipe;

@Mapper
@Repository("com.she.psm.processFacility.mapper.PipeMapper")
public interface PipeMapper {
    /**
     * 배관 및 개스킷 조회
     * 
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param pipeCd
     *            분류코드
     * @return 배관 및 개스킷 목록
     * @throws Exception
     */
    public List<Pipe> getPipes(@Param("plantCd") String plantCd, @Param("processCd") String processCd,
            @Param("pipeCd") String safFacilityCd) throws Exception;

    /**
     * 배관 및 개스킷 상세 조회
     * 
     * @param pipeNo
     *            배관번호
     * @param pipeCd
     *            분류코드
     * @return 배관 및 개스킷
     * @throws Exception
     */
    public Pipe getPipe(@Param("pipeNo") int pipeNo, @Param("pipeCd") String pipeCd) throws Exception;

    /**
     * 배관 및 개스킷 등록
     * 
     * @param Pipe
     * @return 변경 행 수
     * @throws Exception
     */
    public int createPipe(Pipe pipe) throws Exception;

    /**
     * 배관 및 개스킷 수정
     * 
     * @param Pipe
     * @return 변경 행 수
     * @throws Exception
     */
    public int updatePipe(Pipe pipe) throws Exception;

    /**
     * 배관 및 개스킷 삭제
     * 
     * @param pipeNo
     *            배관번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deletePipe(@Param("pipeNo") int pipeNo) throws Exception;

}
