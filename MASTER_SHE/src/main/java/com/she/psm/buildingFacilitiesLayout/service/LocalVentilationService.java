package com.she.psm.buildingFacilitiesLayout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.buildingFacilitiesLayout.mapper.LocalVentilationMapper;
import com.she.psm.model.LocalVentilation;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 국소배기장치 설치계획 기능정의
 */
@Service
public class LocalVentilationService {
    @Autowired
    private LocalVentilationMapper localVentilationMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 국소배기장치 설치계획조회
     * 
     * @param plantCd
     *            사업장
     * @param source
     *            발생원
     * @param processNm
     *            공정또는작업장명
     * @param inoutFlag
     *            실내외구분
     * @param harmfulType
     *            유해물질종류
     * @return 국소배기장치 설치계획 목록
     * @throws Exception
     */
    public List<LocalVentilation> getLocalVentilationLists(String plantCd, String source, String processNm, String inoutFlag, String harmfulType) throws Exception {
        return localVentilationMapper.getLocalVentilationLists(plantCd, source, processNm, inoutFlag, harmfulType);
    }

    /**
     * 국소배기장치 설치계획조회
     * 
     * @param localVentilationNo
     *            운전상태 No
     * @return 국소배기장치 설치계획 상세정보
     * @throws Exception
     */
    public LocalVentilation getLocalVentilation(int localVentilationNo) throws Exception {
        return localVentilationMapper.getLocalVentilation(localVentilationNo);
    }

    /**
     * 국소배기장치 설치계획 항목 생성
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 국소배기장치 설치계획 항목 Key값
     * @throws Exception
     */
    public int createLocalVentilation(LocalVentilation localVentilation) throws Exception {
        localVentilationMapper.createLocalVentilation(localVentilation);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(localVentilation.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(localVentilation.getLocalVentilationNo()));
        changeRefWork.setRefTableNm("saf_local_ventilation");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(localVentilation.getCreaterId());
        changeService.taskChange(changeRefWork);
        return localVentilation.getLocalVentilationNo();
    }

    /**
     * 국소배기장치 설치계획 항목 수정
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 국소배기장치 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateLocalVentilation(LocalVentilation localVentilation) throws Exception {
        localVentilationMapper.updateLocalVentilation(localVentilation);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(localVentilation.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(localVentilation.getLocalVentilationNo()));
        changeRefWork.setRefTableNm("saf_local_ventilation");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(localVentilation.getUpdaterId());
        changeService.taskChange(changeRefWork);
        return localVentilation.getLocalVentilationNo();
    }

    /**
     * 국소배기장치 설치계획 항목 삭제
     * 
     * @param localVentilation
     *            국소배기장치 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteLocalVentilation(LocalVentilation localVentilation) throws Exception {
        return localVentilationMapper.deleteLocalVentilation(localVentilation);
    }
}
