package com.she.env.gas.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.gas.mapper.ActDataMapper;
import com.she.env.gas.model.ActData;
import com.she.env.gas.model.ActDataResult;

@Service
public class ActDataService {

    @Autowired
    private ActDataMapper actDataMapper;

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
    public List<ActData> getActDatas(String plantCd, String startYear, String endYear, String stepCd) throws Exception {
        return actDataMapper.getActDatas(plantCd, startYear, endYear, stepCd);
    }

    /**
     * 활동데이터 체크
     * 
     * @param ActDataCd
     *            활동데이터명
     * @param actMonth
     * @return 활동데이터 목록
     * @throws Exception
     */
    public int countActData(String plantCd, String actMonth) {
        return actDataMapper.countActData(plantCd, actMonth);
    }

    /**
     * 활동데이터 신규등록
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터
     * @throws Exception
     */
    @Transactional
    public int createActData(ActData actData) throws Exception {
        actData.setActDaRptNo(this.getActDaRptSeq());
        this.actDataMapper.createActData(actData);

        if (actData.getActDataList() != null && actData.getActDataList().size() > 0) {
            for (ActDataResult adr : actData.getActDataList()) {
                adr.setActDaRptNo(actData.getActDaRptNo());
                this.createActDataResult(adr);
            }
        }
        return actData.getActDaRptNo();
    }

    public Integer getActDaRptSeq() throws Exception {
        return this.actDataMapper.getActDaRptSeq();
    }

    @Transactional
    public int createActDataResult(ActDataResult ActDataResult) throws Exception {
        this.actDataMapper.createActDataResult(ActDataResult);
        return ActDataResult.getActDaRsltNo();
    }

    /**
     * 활동데이터 등록 시 목록조회
     * 
     * @param plantCd
     *            활동데이터코드 year 년
     * @return 활동데이터코드 상세내역
     * @throws Exception
     */
    public List<ActData> getItemsActData(String plantCd, String year) {
        return this.actDataMapper.getItemsActData(plantCd, year);
    }

    /**
     * 활동데이터 상세조회
     * 
     * @param disActCd
     *            활동데이터
     * @return 활동데이터 상세내역
     * @throws Exception
     */
    public ActData getActData(String ActData) {
        return this.actDataMapper.getActData(ActData);
    }

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
    public List<ActData> getUpdatedItemsActData(String actDaRptNo) {
        return this.actDataMapper.getUpdatedItemsActData(actDaRptNo);
    }

    /**
     * 활동데이터 수정
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터
     * @throws Exception
     */
    public int updateActData(ActData actData) throws Exception {
        this.actDataMapper.updateActData(actData);

        if (actData.getActDataList() != null && actData.getActDataList().size() > 0) {
            for (ActDataResult adr : actData.getActDataList()) {
                this.updateActDataResult(adr);
            }
        }

        return actData.getActDaRptNo();
    }

    @Transactional
    public int updateActDataResult(ActDataResult ActDataResult) throws Exception {
        this.actDataMapper.updateActDataResult(ActDataResult);
        return ActDataResult.getActDaRsltNo();
    }

    /**
     * 활동데이터 완료
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    public int completeActData(ActData actData) {
        String completeCd = "0002";
        actData.setParameter(completeCd);
        this.actDataMapper.completeActData(actData);
        return actData.getActDaRptNo();
    }

    /**
     * 활동데이터 확정취소
     *
     * @param actData
     *            검색조건
     * @return 활동데이터코드
     * @throws Exception
     */
    public int cancelCompleteActData(ActData actData) {
        String completeCd = "0001";
        actData.setParameter(completeCd);
        this.actDataMapper.cancelCompleteActData(actData);
        return actData.getActDaRptNo();
    }

    /**
     * 활동데이터 삭제
     * 
     * @param parameter
     *            검색조건
     * @return 활동데이터
     * @throws Exception
     */
    public int deleteActData(String actDaRptNo) {
        this.actDataMapper.deleteActData(actDaRptNo);
        return 0;
    }

    /**
     * 배출량 산정 계산식
     * 
     * @param actDaRptNo
     * @return
     * @throws Exception
     */
    public List<ActData> calActDate(ActData actData) throws Exception {
        if (actData.getActDataList() != null && actData.getActDataList().size() > 0) {
            for (ActDataResult adr : actData.getActDataList()) {
                this.updateActDataResult(adr);
            }
        }

        int result = this.actDataMapper.calActDate(Integer.toString(actData.getActDaRptNo()));

        return this.actDataMapper.getUpdatedItemsActData(Integer.toString(actData.getActDaRptNo()));
    }

}
