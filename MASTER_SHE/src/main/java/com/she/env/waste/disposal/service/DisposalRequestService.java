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
package com.she.env.waste.disposal.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.waste.disposal.mapper.DisposalRequestMapper;
import com.she.env.waste.model.DisposalRequest;
import com.she.utils.ConstVal;

/**
 * 폐기물 처리요청 기능정의
 *
 */
@Service("EwstDisposalRequestService")
public class DisposalRequestService {
    @Autowired
    private DisposalRequestMapper disposalRequestMapper;

    /**
     * 폐기물 처리요청 전체 조회
     * 
     * @param reqYmdStart
     *            검색시작일
     * @param reqYmdTo
     *            검색종료일
     * @param ewstDispoStCd
     *            처리상태
     * @param ewstClassCd
     *            폐기물분류
     * @return 폐기물 처리요청 목록
     * @throws Exception
     */
    public List<DisposalRequest> getDisposalRequests(String rqstDeptCd, String reqYmdFrom, String reqYmdTo, String ewstDispoStCd, String ewstClassCd, String plantCd, String ewstWasteNo, DefaultParam defaultParam) throws Exception {
        return this.disposalRequestMapper.getDisposalRequests(rqstDeptCd, reqYmdFrom, reqYmdTo, ewstDispoStCd, ewstClassCd, plantCd, ewstWasteNo, defaultParam);
    }

    /**
     * 선택된 폐기물 처리요청 상세 조회
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 처리물 처리요청 정보
     * @throws Exception
     */
    public DisposalRequest getDisposalRequest(int ewstDispoReqNo, DefaultParam defaultParam) throws Exception {
        return this.disposalRequestMapper.getDisposalRequest(ewstDispoReqNo, defaultParam);
    }

    /**
     * 폐기물 처리요청 신규 생성
     * 
     * @param disposalRequest
     *            폐기물 처리요청 정보
     * @return 폐기물 처리요청 번호
     * @throws Exception
     */
    public int createDisposalRequest(DisposalRequest disposalRequest) throws Exception {
        disposalRequest.setEwstDispoStCd(ConstVal.ENV_WASTE_DISPOSAL_STATUS_REQUEST);
        this.disposalRequestMapper.createDisposalRequest(disposalRequest);
        return disposalRequest.getEwstDispoReqNo();
    }

    /**
     * 선택된 폐기물 처리요청 수정
     * 
     * @param disposalRequest
     *            폐기물 처리요청 정보
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalRequest(DisposalRequest disposalRequest) throws Exception {
        return this.disposalRequestMapper.updateDisposalRequest(disposalRequest);
    }

    /**
     * 선택된 폐기물 처리요청 상태 수정
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @param ewstDispoStCd
     *            처리상태
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalRequestStatus(int ewstDispoReqNo, String ewstDispoStCd) throws Exception {
        return this.disposalRequestMapper.updateDisposalRequestStatus(ewstDispoReqNo, ewstDispoStCd);
    }

    /**
     * 선택된 폐기물 처리요청 삭제
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteDisposalRequest(int ewstDispoReqNo) throws Exception {
        return this.disposalRequestMapper.deleteDisposalRequest(ewstDispoReqNo);
    }
}
