package com.she.psm.buildingFacilitiesLayout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.buildingFacilitiesLayout.mapper.FireDetectionMapper;
import com.she.psm.model.FireDetection;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 화재탐지 및 경보설비 설치계획 기능정의
 */
@Service
public class FireDetectionService {
    @Autowired
    private FireDetectionMapper fireDetectionMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 화재탐지 및 경보설비 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param location
     *            작업장소
     * @return 화재탐지 및 경보설비 설치계획 목록
     * @throws Exception
     */
    public List<FireDetection> getFireDetectionLists(String plantCd, String location) throws Exception {
        return fireDetectionMapper.getFireDetectionLists(plantCd, location);
    }

    /**
     * 화재탐지 및 경보설비 설치계획조회
     * 
     * @param fireDetectionNo
     *            운전상태 No
     * @return 화재탐지 및 경보설비 설치계획 상세정보
     * @throws Exception
     */
    public FireDetection getFireDetection(int fireDetectionNo) throws Exception {
        return fireDetectionMapper.getFireDetection(fireDetectionNo);
    }

    /**
     * 화재탐지 및 경보설비 설치계획 항목 생성
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 화재탐지 및 경보설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int createFireDetection(FireDetection fireDetection) throws Exception {
        fireDetectionMapper.createFireDetection(fireDetection);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(fireDetection.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(fireDetection.getFireDetectionNo()));
        changeRefWork.setRefTableNm("saf_fire_detection");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(fireDetection.getCreaterId());
        changeService.taskChange(changeRefWork);
        return fireDetection.getFireDetectionNo();
    }

    /**
     * 화재탐지 및 경보설비 설치계획 항목 수정
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 화재탐지 및 경보설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateFireDetection(FireDetection fireDetection) throws Exception {
        fireDetectionMapper.updateFireDetection(fireDetection);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(fireDetection.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(fireDetection.getFireDetectionNo()));
        changeRefWork.setRefTableNm("saf_fire_detection");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(fireDetection.getUpdaterId());
        changeService.taskChange(changeRefWork);
        return fireDetection.getFireDetectionNo();
    }

    /**
     * 화재탐지 및 경보설비 설치계획 항목 수정
     * 
     * @param fireDetection
     *            화재탐지 및 경보설비 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteFireDetection(FireDetection fireDetection) throws Exception {
        return fireDetectionMapper.deleteFireDetection(fireDetection);
    }
}
