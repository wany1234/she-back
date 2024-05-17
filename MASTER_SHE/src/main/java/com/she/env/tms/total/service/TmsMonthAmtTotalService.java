package com.she.env.tms.total.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.tms.total.mapper.TmsMonthAmtTotalMapper;

@Service
public class TmsMonthAmtTotalService {
    @Autowired
    private TmsMonthAmtTotalMapper tmsMonthAmtTotalMapper;

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
    public List<HashMap<String, Object>> getTmsAmtTotal(String tmsType, String plantCd, List<HashMap<String, Object>> searchYears, String stationCode, String itemCode) throws Exception {
        String searchYearStr = String.join("| ", searchYears.stream().map(item -> {
            List<String> yearMonthStr = new ArrayList<>((Collection<String>) item.get("yearMonth"));

            return item.get("year").toString() + "?" + String.join(",", yearMonthStr) + "?" + item.get("alias1").toString() + "?" + item.get("alias2").toString();
        }).collect(Collectors.toList()));

        return this.tmsMonthAmtTotalMapper.getTmsAmtTotal(tmsType, plantCd, searchYears, stationCode, itemCode, searchYearStr);
    }

}
