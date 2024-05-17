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

import com.she.psm.model.PowerMachine;
import com.she.psm.processFacility.mapper.FacilityChemprodMapper;
import com.she.psm.processFacility.mapper.PowerMachineMapper;

/**
 * 공정시설정보 - 동력기계 기능정의
 */
@Service
public class PowerMachineService {

    @Autowired
    private PowerMachineMapper powerMachineMapper;

    @Autowired
    private FacilityChemprodMapper facilityChemprodMapper;

    @Autowired
    private ChangeService changeService;

    /**
     * 동력기계 조회
     *
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param facilityNm
     *            설비명
     * @return 동력기계 목록
     * @throws Exception
     */
    public List<PowerMachine> getPowerMachines(String plantCd, String processCd, String safFacilityCd, String facilityNm) throws Exception {
        return powerMachineMapper.getPowerMachines(plantCd, processCd, safFacilityCd, facilityNm);
    }

    /**
     * 동력기계 상세 조회
     *
     * @param safFacilityCd
     *            설비코드
     * @return 동력기계
     * @throws Exception
     */
    public PowerMachine getPowerMachine(String safFacilityCd) throws Exception {
        return powerMachineMapper.getPowerMachine(safFacilityCd);
    }

    /**
     * 동력기계 등록/수정
     *
     * @param PowerMachine
     *            동력기계
     * @return 변경 행 수
     * @throws Exception
     */
    public int savePowerMachine(PowerMachine powerMachine) throws Exception {
        int resultNo = 0;
        PowerMachine checkPowerMachine = powerMachineMapper.getPowerMachine(powerMachine.getSafFacilityCd());
        if (checkPowerMachine != null && !checkPowerMachine.getSafFacilityCd().equals("")) {
            resultNo += powerMachineMapper.updatePowerMachine(powerMachine);
        } else {
            resultNo += powerMachineMapper.createPowerMachine(powerMachine);
        }

        // 2020-01-28 kdh 설비마스터 화면에서만 취급자재 저장
        // // 설비코드에 해당하는 취급물질 일괄 삭제
        // this.facilityChemprodMapper.deleteFacilityChemprod(powerMachine.getSafFacilityCd(),
        // 0, 0);
        //
        // // 취급물질 등록
        // for (Chemprod chemprod : powerMachine.getChemprods()) {
        // chemprod.setSafFacilityCd(powerMachine.getSafFacilityCd());
        // this.facilityChemprodMapper.createFacilityChemprod(chemprod);
        // }

        // MOC 번호가 들어온 경우
//        ChangeRefWork changeRefWork = new ChangeRefWork();
//        changeRefWork.setSafChngNo(powerMachine.getSafChngNo());
//        changeRefWork.setRefTableId(String.valueOf(powerMachine.getSafFacilityCd()));
//        changeRefWork.setRefTableNm("saf_facility_pwr_info");
//        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
//        changeRefWork.setCreateUserId(powerMachine.getCreateUserId());
//        changeService.taskChange(changeRefWork);

        return resultNo;
    }

}
