/**
 * Copyright (C) 2019, 2019 All Right Reserved, http://www.yullin.com/
 *
 * SHE Business can not be copied and/or distributed without the express
 * permission of Yullin Technologies
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.she.psm.processFacility.service;

import java.util.List;

import com.she.safety.change.service.ChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.psm.model.SafetyValve;
import com.she.psm.processFacility.mapper.FacilityChemprodMapper;
import com.she.psm.processFacility.mapper.SafetyValveMapper;

/**
 * 공정시설정보 - 안전밸브 및 파열판 기능정의
 */
@Service
public class SafetyValveService {
    @Autowired
    private SafetyValveMapper safetyValveMapper;

    @Autowired
    private FacilityChemprodMapper facilityChemprodMapper;

    @Autowired
    private ChangeService changeService;

    /**
     * 안전밸브 및 파열판 조회
     *
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param facilityNm
     *            설비명
     * @return 안전밸브 및 파열판 목록
     * @throws Exception
     */
    public List<SafetyValve> getSafetyValves(String plantCd, String processCd, String safFacilityCd, String facilityNm)
            throws Exception {
        return safetyValveMapper.getSafetyValves(plantCd, processCd, safFacilityCd, facilityNm);
    }

    /**
     * 안전밸브 및 파열판 상세 조회
     *
     * @param safFacilityCd
     *            설비코드
     * @return 안전밸브 및 파열판
     * @throws Exception
     */
    public SafetyValve getSafetyValve(String safFacilityCd) throws Exception {
        return safetyValveMapper.getSafetyValve(safFacilityCd);
    }

    /**
     * 안전밸브 및 파열판 등록/수정
     *
     * @param safetyValve
     *            안전밸브 및 파열판
     * @return 변경 행 수
     * @throws Exception
     */
    public int saveSafetyValve(SafetyValve safetyValve) throws Exception {
        int resultNo = 0;
        SafetyValve checkSafetyValve = safetyValveMapper.getSafetyValve(safetyValve.getSafFacilityCd());
        if (checkSafetyValve != null && !checkSafetyValve.getSafFacilityCd().equals("")) {
            resultNo += safetyValveMapper.updateSafetyValve(safetyValve);
        } else {
            resultNo += safetyValveMapper.createSafetyValve(safetyValve);
        }

        // if (safetyValve.getChemprods() != null &&
        // safetyValve.getChemprods().size() > 0) {
        // // 설비코드에 해당하는 취급물질 일괄 삭제
        // this.facilityChemprodMapper.deleteFacilityChemprod(safetyValve.getSafFacilityCd(),
        // 0, 0);
        //
        // // 취급물질 등록
        // for (Chemprod chemprod : safetyValve.getChemprods()) {
        // chemprod.setSafFacilityCd(safetyValve.getSafFacilityCd());
        // this.facilityChemprodMapper.createFacilityChemprod(chemprod);
        // }
        // }

        // MOC 번호가 들어온 경우
//        ChangeRefWork changeRefWork = new ChangeRefWork();
//        changeRefWork.setSafChngNo(safetyValve.getSafChngNo());
//        changeRefWork.setRefTableId(String.valueOf(safetyValve.getSafFacilityCd()));
//        changeRefWork.setRefTableNm("saf_facility_valve_info");
//        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
//        changeRefWork.setCreateUserId(safetyValve.getCreateUserId());
//        changeService.taskChange(changeRefWork);
        return resultNo;
    }

}
