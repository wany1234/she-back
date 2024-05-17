package com.she.psm.buildingFacilitiesLayout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.buildingFacilitiesLayout.mapper.WashupMapper;
import com.she.psm.model.Washup;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 기능정의
 */
@Service
public class WashupService {
    @Autowired
    private WashupMapper washupMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param usage
     *            용도
     * @param location
     *            설치위치
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 목록
     * @throws Exception
     */
    public List<Washup> getWashupLists(String plantCd, String usage, String location) throws Exception {
        return washupMapper.getWashupLists(plantCd, usage, location);
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획조회
     * 
     * @param washupNo
     *            운전상태 No
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 상세정보
     * @throws Exception
     */
    public Washup getWashup(int washupNo) throws Exception {
        return washupMapper.getWashup(washupNo);
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 생성
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 Key값
     * @throws Exception
     */
    public int createWashup(Washup washup) throws Exception {
        washupMapper.createWashup(washup);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(washup.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(washup.getWashupNo()));
        changeRefWork.setRefTableNm("saf_washup");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(washup.getCreaterId());
        changeService.taskChange(changeRefWork);
        return washup.getWashupNo();
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 수정
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateWashup(Washup washup) throws Exception {
        washupMapper.updateWashup(washup);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(washup.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(washup.getWashupNo()));
        changeRefWork.setRefTableNm("saf_washup");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(washup.getUpdaterId());
        changeService.taskChange(changeRefWork);
        return washup.getWashupNo();
    }

    /**
     * 세척ㆍ세안 시설 및 안전 보호장구 설치계획 항목 삭제
     * 
     * @param washup
     *            세척ㆍ세안 시설 및 안전 보호장구 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteWashup(Washup washup) throws Exception {
        return washupMapper.deleteWashup(washup);
    }
}
