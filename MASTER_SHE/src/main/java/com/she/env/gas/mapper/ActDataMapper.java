package com.she.env.gas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.gas.model.ActData;
import com.she.env.gas.model.ActDataResult;

@Mapper
@Repository("com.she.env.gas.mapper.ActDataMapper")
public interface ActDataMapper {

    /**
     * 활동데이터 조회
     * 
     * @param plantCd
     *            사업장
     * @param actMonth
     *            작성년월
     * @return 활동데이터 목록
     * @throws Exception
     */
    public List<ActData> getActDatas(@Param("plantCd") String plantCd, @Param("startYear") String startYear, @Param("endYear") String endYear, @Param("stepCd") String stepCd) throws Exception;

    /**
     * 활동데이터 체크
     * 
     * @param actDataCd
     *            활동데이터명
     * @param actMonth
     * @return 활동데이터 목록
     * @throws Exception
     */
    public int countActData(@Param("plantCd") String plantCd, @Param("actMonth") String actMonth);

    /**
     * 활동데이터 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터
     * @throws Exception
     */
    public Integer getActDaRptSeq();

    public void createActData(ActData actDataAct);

    public void createActDataResult(ActDataResult actDataResult);

    /**
     * 활동데이터 등록 시 목록조회
     * 
     * @param plantCd
     *            활동데이터코드 year 년
     * @return 활동데이터 상세내역
     * @throws Exception
     */
    public List<ActData> getItemsActData(@Param("plantCd") String plantCd, @Param("year") String year);

    /**
     * 활동데이터 상세조회
     * 
     * @param actDataCd
     *            활동데이터
     * @return 활동데이터 상세내역
     * @throws Exception
     */
    public ActData getActData(@Param("actDaRptNo") String actDaRptNo);

    /**
     * 활동데이터 상세목록 조회
     * 
     * @param year
     * @param plantCd
     * 
     * @param actDataCd
     *            활동데이터코드
     * @return 활동데이터코드 상세내역
     * @throws Exception
     */
    public List<ActData> getUpdatedItemsActData(@Param("actDaRptNo") String actDaRptNo);

    /**
     * 활동데이터 수정
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터
     * @throws Exception
     */
    public void updateActData(ActData actData);

    public void updateActDataResult(ActDataResult actDataResult);

    /**
     * 활동데이터 완료
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    public void completeActData(ActData actData);

    /**
     * 활동데이터 삭제
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터
     * @throws Exception
     */
    public void deleteActData(String actDaRptNo);

    /**
     * 배출량 산정 계산식
     * 
     * @param actDaRptNo
     * @return
     * @throws Exception
     */
    public int calActDate(@Param("actDaRptNo") String actDaRptNo) throws Exception;

    /**
     * 활동데이터 확정취소
     *
     * @param actData
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    public void cancelCompleteActData(ActData actData);
}
