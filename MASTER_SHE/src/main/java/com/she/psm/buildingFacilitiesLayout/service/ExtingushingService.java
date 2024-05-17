package com.she.psm.buildingFacilitiesLayout.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.buildingFacilitiesLayout.mapper.ExtingushingMapper;
import com.she.psm.model.Extingushing;
import com.she.safety.change.service.ChangeService;
import com.she.safety.model.ChangeRefWork;
import com.she.utils.ConstVal;

/**
 * 소화설비 설치계획 기능정의
 */
@Service
public class ExtingushingService {

    @Autowired
    private ExtingushingMapper extingushingMapper;
    @Autowired
    private ChangeService changeService;

    /**
     * 소화설비 설치계획 조회
     * 
     * @param plantCd
     *            사업장
     * @param category
     *            구분
     * @param extingushingNum
     *            지역번호
     * @param extingushingNm
     *            지역명
     * @return 소화설비 설치계획 목록
     * @throws Exception
     */
    public List<Extingushing> getExtingushingLists(String plantCd, String location) throws Exception {
        return extingushingMapper.getExtingushingLists(plantCd, location);
    }

    /**
     * 소화설비 설치계획조회
     * 
     * @param extingushingNo
     *            운전상태 No
     * @return 소화설비 설치계획 상세정보
     * @throws Exception
     */
    public Extingushing getExtingushing(int extingushingNo) throws Exception {
        return extingushingMapper.getExtingushing(extingushingNo);
    }

    /**
     * 소화설비 설치계획 항목 생성
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 소화설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int createExtingushing(Extingushing extingushing) throws Exception {
        extingushingMapper.createExtingushing(extingushing);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(extingushing.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(extingushing.getExtingushingNo()));
        changeRefWork.setRefTableNm("saf_extingushing");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(extingushing.getCreaterId());
        changeService.taskChange(changeRefWork);
        return extingushing.getExtingushingNo();
    }

    /**
     * 소화설비 설치계획 항목 수정
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 소화설비 설치계획 항목 Key값
     * @throws Exception
     */
    public int updateExtingushing(Extingushing extingushing) throws Exception {
        extingushingMapper.updateExtingushing(extingushing);

        ChangeRefWork changeRefWork = new ChangeRefWork();
        changeRefWork.setSafChngNo(extingushing.getSafChngNo());
        changeRefWork.setRefTableId(String.valueOf(extingushing.getExtingushingNo()));
        changeRefWork.setRefTableNm("saf_extingushing");
        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
        changeRefWork.setCreateUserId(extingushing.getUpdaterId());
        changeService.taskChange(changeRefWork);
        return extingushing.getExtingushingNo();
    }

    /**
     * 소화설비 설치계획 항목 삭제
     * 
     * @param extingushing
     *            소화설비 설치계획 정보
     * @return 삭제성공여부
     * @throws Exception
     */
    public int deleteExtingushing(Extingushing extingushing) throws Exception {
        return extingushingMapper.deleteExtingushing(extingushing);
    }

}
