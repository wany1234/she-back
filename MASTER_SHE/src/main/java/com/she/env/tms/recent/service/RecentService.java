package com.she.env.tms.recent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.tms.baseInfo.mapper.TmsItemMapper;
import com.she.env.tms.model.TmsItem;
import com.she.env.tms.recent.mapper.RecentMapper;

@Service
public class RecentService {
    @Autowired
    private RecentMapper recentMapper;
    @Autowired
    private TmsItemMapper tmsItemMapper;

    /**
     * TMS 현재값 조회
     *
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 현재값 목록
     * @throws Exception
     */
    public List<HashMap<String, Object>> getTms5Recents() throws Exception {
        List<TmsItem> tmsItems = tmsItemMapper.getTmsItems("", "false");
        if (tmsItems == null || tmsItems.size() == 0) {
            return null;
        } else {
            String[] laws = new String[tmsItems.size()];
            String[] vals = new String[tmsItems.size()];
            String[] dates = new String[tmsItems.size()];

            int index = 0;
            for (TmsItem tmsItem : tmsItems) {
                laws[index] = "[" + tmsItem.getItemCode() + "_law]";
                vals[index] = "[" + tmsItem.getItemCode() + "_val]";
                dates[index] = "[" + tmsItem.getItemCode() + "_date]";
                index++;
            }

            String lawStr = String.join(", ", laws);
            String valStr = String.join(", ", vals);
            String dateStr = String.join(", ", dates);

            return recentMapper.getTms5Recents(laws, vals, dates, lawStr, valStr, dateStr);
        }
    }

    /**
     * TMS 배출구별 측정치, 법적기준 차트 데이터 조회
     *
     * @param itemCode
     *            TMS 측정항목코드
     * @return TMS 배출구별 측정치, 법적기준 차트 데이터
     * @throws Exception
     */
    public Map<String, Object> getTms5RecentChart(String itemCode, String startYmd, String endYmd) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", this.recentMapper.getTms5RecentChart(itemCode, startYmd, endYmd));
        map.put("yearLaw", this.recentMapper.getTms5RecentLawChart(itemCode, startYmd, endYmd));
        return map;
    }

}
