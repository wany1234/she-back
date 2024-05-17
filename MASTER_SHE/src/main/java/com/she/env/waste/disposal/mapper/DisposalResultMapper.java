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

import com.she.env.waste.model.DisporateDeptItem;
import com.she.env.waste.model.DisposalMtd;
import com.she.env.waste.model.DisposalRequest;
import com.she.env.waste.model.DisposalResult;

/**
 * 폐기물 처리결과 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.waste.baseInfo.mapper.DisposalResultMapper")
public interface DisposalResultMapper {

    /**
     * 폐기물 처리결과 전체 조회
     * 
     * @param reqYmdFrom
     *            검색시작일(요청)
     * @param reqYmdTo
     *            검색종료일(요청)
     * @param ewstDispoStCd
     *            처리상태
     * @param ewstClassCd
     *            폐기물분류
     * @param ewstDispoReqNo
     *            폐기물 처리요청 번호
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    public List<DisposalResult> getDisposalResults(@Param("dispoDeptCd") String dispoDeptCd, @Param("reqYmdFrom") String reqYmdFrom, @Param("reqYmdTo") String reqYmdTo, @Param("ewstDispoStCd") String ewstDispoStCd, @Param("ewstClassCd") String ewstClassCd, @Param("ewstDispoReqNo") int ewstDispoReqNo, @Param("ewstDispoCoNo") String ewstDispoCoNo,
            @Param("ewstFreightCoNo") String ewstFreightCoNo, @Param("plantCd") String plantCd, @Param("ewstWasteNo") String ewstWasteNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    public List<DisposalRequest> getDisposalRequsts(@Param("ewstDispoResultNo") int ewstDispoResultNo, @Param("defaultParam") DefaultParam defaultParam);

    /**
     * 선택된 폐기물 처리결과 상세 조회
     * 
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 폐기물 처리결과
     * @throws Exception
     */

    public DisposalResult getDisposalResult(@Param("ewstDispoResultNo") int ewstDispoResultNo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 분배율 조회
     * 
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 폐기물 분배율
     * @throws Exception
     */
    public List<DisporateDeptItem> getDispoRateResult(@Param("ewstDispoResultNo") int ewstDispoResultNo) throws Exception;

    /**
     * 폐기물 처리결과 신규 생성
     * 
     * @param disposalResult
     *            폐기물 처리결과
     * @return 등록행수
     * @throws Exception
     */
    public int createDisposalResult(DisposalResult disposalResult) throws Exception;

    /**
     * 폐기물 처리내역 연결
     * 
     * @param ewstDispoResultNo,
     *            ewstDispoReqNo 폐기물 처리내역 연결
     * @return 등록행수
     * @throws Exception
     */
    public int createDispoRequests(@Param("ewstDispoResultNo") int ewstDispoResultNo, @Param("ewstDispoReqNo") int ewstDispoReqNo) throws Exception;

    /**
     * 폐기물 처리요청 내역 입력 시 처리요청 단계 완료 상태로 변경
     * 
     * @param ewstDispoReqNo
     * @return 등록행수
     * @throws Exception
     */
    public int updateDispoReqComplete(@Param("ewstDispoReqNo") int ewstDispoReqNo) throws Exception;

    /**
     * 폐기물 처리요청 내역 입력 시 제거된 처리요청의 진행단계를 처리요청으로 변경
     * 
     * @return 등록행수
     * @throws Exception
     */
    public int updateDispoReqRequest() throws Exception;

    /**
     * 폐기물 처리내역 연결 제거
     * 
     * @param ewstDispoResultNo
     *            폐기물 처리내역 연결 제거
     * @return 등록행수
     * @throws Exception
     */
    public int deleteDispoRequests(@Param("ewstDispoResultNo") int ewstDispoResultNo) throws Exception;

    /**
     * 선택된 폐기물 처리결과 수정
     * 
     * @param disposalResult
     *            폐기물 처리결과
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalResult(DisposalResult disposalResult) throws Exception;

    /**
     * 선택된 폐기물 처리결과 상태 수정
     * 
     * @param ewstDispoReqNo
     *            폐기물 처리결과 번호
     * @param ewstDispoStCd
     *            처리상태
     * @return 수정행수
     * @throws Exception
     */
    public int updateDisposalResultStatus(@Param("ewstDispoResultNo") int ewstDispoResultNo, @Param("ewstDispoStCd") String ewstDispoStCd) throws Exception;

    /**
     * 선택된 폐기물 처리결과 삭제
     * 
     * @param ewstDispoResultNo
     *            폐기물 처리결과 번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteDisposalResult(@Param("ewstDispoResultNo") int ewstDispoResultNo) throws Exception;

    /**
     * 폐기물 처리(위탁폐기물 처리완료)결과 엑셀 데이터 조회
     * 
     * @param reqYmdFrom
     *            검색시작일(요청)
     * @param reqYmdTo
     *            검색종료일(요청)
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    public List<DisposalResult> getDisposalResultsExcel(@Param("reqYmdFrom") String reqYmdFrom, @Param("reqYmdTo") String reqYmdTo, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 폐기물 처리방법 전체 조회
     *
     * @return 폐기물 처리결과 목록
     * @throws Exception
     */
    public List<DisposalMtd> getDisposalResultsMtd() throws Exception;
}
