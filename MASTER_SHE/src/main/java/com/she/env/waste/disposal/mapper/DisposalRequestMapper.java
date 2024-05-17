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
package com.she.env.waste.disposal.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.waste.model.DisposalRequest;

/**
 * 폐기물 처리요청 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.baseInfo.mapper.DisposalRequestMapper")
public interface DisposalRequestMapper {

    /**
     * 폐기물 처리요청 전체 조회
     * 
     * @param reqYmdFrom
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
    public List<DisposalRequest> getDisposalRequests(@Param("rqstDeptCd") String rqstDeptCd, @Param("reqYmdFrom") String reqYmdFrom, @Param("reqYmdTo") String reqYmdTo, @Param("ewstDispoStCd") String ewstDispoStCd, @Param("ewstClassCd") String ewstClassCd, @Param("plantCd") String plantCd, @Param("ewstWasteNo") String ewstWasteNo, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 선택된 폐기물 처리요청 상세 조회
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 처리물 처리요청 정보
     * @throws Exception
     */
    public DisposalRequest getDisposalRequest(@Param("ewstDispoReqNo") int ewstDispoReqNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 폐기물 처리요청 신규 생성
     * 
     * @param disposalRequest
     *            폐기물 처리요청 정보
     * @return 등록행수
     * @throws Exception
     */
    public int createDisposalRequest(DisposalRequest disposalRequest) throws Exception;

    /**
     * 선택된 폐기물 처리요청 수정
     * 
     * @param disposalRequest
     *            폐기물 처리요청 정보
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalRequest(DisposalRequest disposalRequest) throws Exception;

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
    public int updateDisposalRequestStatus(@Param("ewstDispoReqNo") int ewstDispoReqNo, @Param("ewstDispoStCd") String ewstDispoStCd) throws Exception;

    /**
     * 선택된 폐기물 처리요청 발생량 수정
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @param amtGen
     *            발생량
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalRequestAmtGen(@Param("ewstDispoReqNo") int ewstDispoReqNo, @Param("amtGen") Float amtGen) throws Exception;

    /**
     * 선택된 폐기물 처리요청 삭제
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteDisposalRequest(@Param("ewstDispoReqNo") int ewstDispoReqNo) throws Exception;
}
