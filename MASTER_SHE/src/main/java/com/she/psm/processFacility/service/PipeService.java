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

import com.she.safety.change.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.model.Pipe;
import com.she.psm.processFacility.mapper.FacilityChemprodMapper;
import com.she.psm.processFacility.mapper.PipeMapper;

/**
 * 공정시설정보 - 배관 및 개스킷 기능정의
 */
@Service
public class PipeService {
    @Autowired
    private PipeMapper pipeMapper;

    @Autowired
    private FacilityChemprodMapper facilityChemprodMapper;

    @Autowired
    private ChangeService changeService;

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
    public List<Pipe> getPipes(String plantCd, String processCd, String pipeCd) throws Exception {
        return pipeMapper.getPipes(plantCd, processCd, pipeCd);
    }

    /**
     * 배관 및 개스킷 상세 조회
     *
     * @param pipeCd
     *            분류코드
     * @return 배관 및 개스킷
     * @throws Exception
     */
    public Pipe getPipe(int pipeNo, String pipeCd) throws Exception {
        return pipeMapper.getPipe(pipeNo, pipeCd);
    }

    /**
     * 배관 및 개스킷 등록/수정
     *
     * @param Pipe
     *            배관 및 개스킷
     * @return 변경 행 수
     * @throws Exception
     */
    public int savePipe(Pipe pipe) throws Exception {
        int resultNo = 0;
        Pipe checkPipe = pipeMapper.getPipe(pipe.getPipeNo(), pipe.getPipeCd());
        if (checkPipe != null && checkPipe.getPipeNo() != 0) {
            resultNo += pipeMapper.updatePipe(pipe);
        } else {
            resultNo += pipeMapper.createPipe(pipe);
        }

        // // 설비코드에 해당하는 취급물질 일괄 삭제
        // this.facilityChemprodMapper.deleteFacilityChemprod(String.valueOf(pipe.getPipeNo()),
        // 0, 0);

        // // 취급물질 등록
        // for (Chemprod chemprod : pipe.getChemprods()) {
        // chemprod.setSafFacilityCd(powerMachine.getSafFacilityCd());
        // this.facilityChemprodMapper.createFacilityChemprod(chemprod);
        // }

        // MOC 번호가 들어온 경우
//        ChangeRefWork changeRefWork = new ChangeRefWork();
//        changeRefWork.setSafChngNo(pipe.getSafChngNo());
//        changeRefWork.setRefTableId(String.valueOf(pipe.getPipeNo()));
//        changeRefWork.setRefTableNm("saf_facility_pipe_info");
//        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
//        changeRefWork.setCreateUserId(pipe.getCreateUserId());
//        changeService.taskChange(changeRefWork);

        return resultNo;
    }

    /**
     * 배관 및 개스킷 삭제
     *
     * @param pipeNo
     *            배관번호
     * @return 변경 행 수
     * @throws Exception
     */
    public int deletePipe(List<Pipe> pipes) throws Exception {
        int resultNo = 0;
        for (Pipe pipe : pipes) {
            resultNo += pipeMapper.deletePipe(pipe.getPipeNo());
        }
        return resultNo;
    }

}
