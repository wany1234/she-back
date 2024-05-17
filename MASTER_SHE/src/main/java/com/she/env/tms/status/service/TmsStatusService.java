package com.she.env.tms.status.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.tms.model.TmsStatus;
import com.she.env.tms.status.mapper.TmsStatusMapper;

@Service
public class TmsStatusService {
    @Autowired
    private TmsStatusMapper tmsStatusMapper;

    /**
     * TMS 현황 조회
     * 
     * @param type
     *            구분
     * @param plantCd
     *            사업장
     * @param startDate
     *            시작일
     * @param endDate
     *            종료일
     * @param stationCode
     *            TMS 측정소코드
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 현황 목록
     * @throws Exception
     */
    public List<TmsStatus> getTms5Status(String tmsType, String plantCd, String startDate, String endDate, String stationCode, String itemCode, int pageNumber, int pageSize, String orderByExpression) throws Exception {
        orderByExpression = orderByExpression.toLowerCase();
        return this.tmsStatusMapper.getTms5Status(tmsType, plantCd, startDate, endDate, stationCode, itemCode, pageNumber, pageSize, orderByExpression);
    }

}
