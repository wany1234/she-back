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
package com.she.psm.processFacility.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.chm.model.Chemprod;
import com.she.psm.model.Pipe;
import com.she.psm.processFacility.mapper.PipeChemprodMapper;

/**
 * 배관별 취급물질
 */
@Service
public class PipeChemprodService {
    @Autowired
    private PipeChemprodMapper pipeChemprodMapper;

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
    public List<Chemprod> getPipeChemprods(String plantCd, String chemProdNmKr, int pipeNo) throws Exception {
        return pipeChemprodMapper.getPipeChemprods(plantCd, chemProdNmKr, pipeNo);
    }

    /**
     * 배관별 취급물질 등록
     * 
     * @param facilityMst
     *            안전설비마스터
     * @return 등록 행 수
     * @throws Exception
     */
    @Transactional
    public int createPipeChemprods(Pipe pipe) throws Exception {
        int count = 0;
        this.pipeChemprodMapper.deletePipeChemprod(0, pipe.getPipeNo());

        for (Chemprod chemprod : pipe.getChemprods()) {
            chemprod.setPipeNo(pipe.getPipeNo());
            this.pipeChemprodMapper.createPipeChemprod(chemprod);
        }

        return count;
    }

    /**
     * 배관별 취급물질 삭제
     * 
     * @param chemprods
     *            배관별 취급물질s
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int deletePipeChemprods(List<Chemprod> chemprods) throws Exception {
        int resultNo = 0;

        for (Chemprod chemprod : chemprods) {
            resultNo += this.pipeChemprodMapper.deletePipeChemprod(chemprod.getChemProdNo(), chemprod.getPipeNo());
        }

        return resultNo;
    }

}
