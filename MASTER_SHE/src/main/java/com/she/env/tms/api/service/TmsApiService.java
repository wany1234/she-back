package com.she.env.tms.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.tms.api.mapper.TmsApiMapper;
import com.she.env.tms.baseInfo.mapper.TmsItemMapper;
import com.she.env.tms.baseInfo.mapper.TmsStationMapper;
import com.she.env.tms.model.TmsItem;
import com.she.env.tms.model.TmsStation;

@Service
public class TmsApiService {
    @Autowired
    private TmsApiMapper tmsApiMapper;
    @Autowired
    private TmsStationMapper tmsStationMapper;
    @Autowired
    private TmsItemMapper tmsItemMapper;

    public int saveTms5(List<HashMap<String, Object>> tms5) throws Exception {
        int result = 0;
        if (tms5 != null && tms5.size() > 0) {
            for (HashMap<String, Object> map : tms5) {
                // 측정소 코드와 항목코드가 없다면 저장 하지 않음
                if (map.get("station_code") != null && !"".equals(String.valueOf(map.get("station_code"))) && map.get("item_code") != null && !"".equals(String.valueOf(map.get("item_code")))) {
                    TmsStation tmsStation = tmsStationMapper.getTmsStation(String.valueOf(map.get("station_code")));
                    if (tmsStation == null) {
                        // 받은 데이터의 측정소 데이터가 없는 경우 저장 하지 않고 다음 데이터를 체크한다.
                        continue;
                    }
                    TmsItem tmsItem = tmsItemMapper.getTmsItem(String.valueOf(map.get("item_code")));
                    if (tmsItem == null) {
                        // 받은 데이터의 측정항목 데이터가 없는 경우 저장 하지 않고 다음 데이터를 체크한다.
                        continue;
                    }
                    if (!validationDate(String.valueOf(map.get("data_date")))) {
                        // 받은 데이터의 측정일시가 날짜 형태가 아닌 경우 저장하지 않고 다음 데이터를 체크한다.
                        continue;
                    }

                    /**
                     * [TMS 5분 데이터 저장]
                     * 
                     * 측정소, 측정항목, 측정일시마다 값이 들어가게 되며 혹 동일한 데이터가 들어오는 경우 update
                     */
                    result += tmsApiMapper.saveTms5(map);

                    /**
                     * [TMS 5분 최근 데이터 저장]
                     * 
                     * 측정소, 측정항목별로 데이터가 들어가며 해당 값이 존재할 경우 update
                     */
                    tmsApiMapper.saveTms5Recent(map);
                }
            }
        }
        return result;
    }

    public int saveTms30(List<HashMap<String, Object>> tms30) throws Exception {
        int result = 0;
        if (tms30 != null && tms30.size() > 0) {
            for (HashMap<String, Object> map : tms30) {
                // 측정소 코드와 항목코드가 없다면 저장 하지 않음
                if (map.get("station_code") != null && !"".equals(String.valueOf(map.get("station_code"))) && map.get("item_code") != null && !"".equals(String.valueOf(map.get("item_code")))) {
                    TmsStation tmsStation = tmsStationMapper.getTmsStation(String.valueOf(map.get("station_code")));
                    if (tmsStation == null) {
                        // 받은 데이터의 측정소 데이터가 없는 경우 저장 하지 않고 다음 데이터를 체크한다.
                        continue;
                    }
                    TmsItem tmsItem = tmsItemMapper.getTmsItem(String.valueOf(map.get("item_code")));
                    if (tmsItem == null) {
                        // 받은 데이터의 측정항목 데이터가 없는 경우 저장 하지 않고 다음 데이터를 체크한다.
                        continue;
                    }
                    if (!validationDate(String.valueOf(map.get("data_date")))) {
                        // 받은 데이터의 측정일시가 날짜 형태가 아닌 경우 저장하지 않고 다음 데이터를 체크한다.
                        continue;
                    }

                    /**
                     * [TMS 30분 데이터 저장]
                     * 
                     * 측정소, 측정항목, 측정일시마다 값이 들어가게 되며 혹 동일한 데이터가 들어오는 경우 update
                     */
                    result += tmsApiMapper.saveTms30(map);

                    /**
                     * [TMS 30분 최근 데이터 저장]
                     * 
                     * 측정소, 측정항목별로 데이터가 들어가며 해당 값이 존재할 경우 update
                     */
                    tmsApiMapper.saveTms30Recent(map);
                }
            }
        }
        return result;
    }

    /** 입력 date가 yyyy-MM-dd HH:mi:ss 형태로 들어옴 */
    public boolean validationDate(String checkDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            dateFormat.setLenient(false);
            dateFormat.parse(checkDate);
            return true;

        } catch (ParseException pe) {
            return false;
        } catch (Exception e) {
            return false;
        }

    }

}
