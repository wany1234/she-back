package com.she.psm.buildingFacilitiesLayout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.buildingFacilitiesLayout.mapper.GasDetectorMapper;
import com.she.psm.model.GasDetector;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 가스누출감지 경보기 설치계획 기능정의
 */
@Service
public class GasDetectorService {
    @Autowired
    private GasDetectorMapper gasDetectorMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 가스누출감지 경보기 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param mgrNum
     *            관리번호
     * @param sensingObj
     *            감지대상
     * @param location
     *            설치위치
     * @return 가스누출감지 경보기 설치계획 목록
     * @throws Exception
     */
    public List<GasDetector> getGasDetectorLists(String plantCd, String mgrNum, String sensingObj, String location) throws Exception {
        return gasDetectorMapper.getGasDetectorLists(plantCd, mgrNum, sensingObj, location);
    }

    /**
     * 가스누출감지 경보기 설치계획조회
     * 
     * @param gasDetectorNo
     *            운전상태 No
     * @return 가스누출감지 경보기 설치계획 상세정보
     * @throws Exception
     */
    public GasDetector getGasDetector(int gasDetectorNo) throws Exception {
        return gasDetectorMapper.getGasDetector(gasDetectorNo);
    }

    /**
     * 가스누출감지 경보기 설치계획 항목 생성
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 가스누출감지 경보기 설치계획 항목 Key값
     * @throws Exception
     */
    public int createGasDetector(GasDetector gasDetector) throws Exception {
        gasDetectorMapper.createGasDetector(gasDetector);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(gasDetector.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(gasDetector.getGasDetectorNo()));
        changeRefWork.setRefTableNm("saf_gas_detector");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(gasDetector.getCreaterId());
        changeService.taskChange(changeRefWork);
        return gasDetector.getGasDetectorNo();
    }

    /**
     * 가스누출감지 경보기 설치계획 항목 수정
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 가스누출감지 경보기 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateGasDetector(GasDetector gasDetector) throws Exception {
        gasDetectorMapper.updateGasDetector(gasDetector);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(gasDetector.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(gasDetector.getGasDetectorNo()));
        changeRefWork.setRefTableNm("saf_gas_detector");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(gasDetector.getUpdaterId());
        changeService.taskChange(changeRefWork);
        return gasDetector.getGasDetectorNo();
    }

    /**
     * 가스누출감지 경보기 설치계획 항목 삭제
     * 
     * @param gasDetector
     *            가스누출감지 경보기 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteGasDetector(GasDetector gasDetector) throws Exception {
        return gasDetectorMapper.deleteGasDetector(gasDetector);
    }
}
