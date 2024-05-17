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

import com.she.psm.model.Equipment;
import com.she.psm.processFacility.mapper.EquipmentMapper;
import com.she.psm.processFacility.mapper.FacilityChemprodMapper;

/**
 * 공정시설정보 - 장치 및 설비 기능정의
 */
@Service
public class EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private FacilityChemprodMapper facilityChemprodMapper;

    @Autowired
    private ChangeService changeService;

    /**
     * 장치 및 설비 조회
     *
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param facilityNm
     *            설비명
     * @param facilityPsmCd
     *            PSM 설비유형
     * @return 장치 및 설비 목록
     * @throws Exception
     */
    public List<Equipment> getEquipments(String plantCd, String processCd, String safFacilityCd, String facilityNm, String facilityPsmCd) throws Exception {
        return equipmentMapper.getEquipments(plantCd, processCd, safFacilityCd, facilityNm, facilityPsmCd);
    }

    /**
     * 장치 및 설비 상세 조회
     *
     * @param safFacilityCd
     *            설비코드
     * @param equipmentNo
     *            설비별 장치번호
     * @return 장치 및 설비
     * @throws Exception
     */
    public Equipment getEquipment(String safFacilityCd, int equipmentNo) throws Exception {
        return equipmentMapper.getEquipment(safFacilityCd, equipmentNo);
    }

    /**
     * 장치 및 설비 등록/수정
     *
     * @param PowerMachine
     *            장치 및 설비
     * @return 변경 행 수
     * @throws Exception
     */
    public int saveEquipment(Equipment equipment) throws Exception {
        int resultNo = 0;
        Equipment checkEquipment = equipmentMapper.getEquipment(equipment.getSafFacilityCd(), equipment.getEquipmentNo());
        if (checkEquipment != null && !checkEquipment.getSafFacilityCd().equals("")) {
            resultNo += equipmentMapper.updateEquipment(equipment);
        } else {
            resultNo += equipmentMapper.createEquipment(equipment);
        }

        // 2020-01-28 kdh 설비마스터 화면에서만 취급자재 저장
        // // 설비코드에 해당하는 취급물질 일괄 삭제
        // this.facilityChemprodMapper.deleteFacilityChemprod(equipment.getSafFacilityCd(),
        // 0, equipment.getEquipmentNo());
        //
        // // 취급물질 등록
        // for (Chemprod chemprod : equipment.getChemprods()) {
        // chemprod.setSafFacilityCd(equipment.getSafFacilityCd());
        // this.facilityChemprodMapper.createFacilityChemprod(chemprod);
        // }

        // MOC 번호가 들어온 경우
//        ChangeRefWork changeRefWork = new ChangeRefWork();
//        changeRefWork.setSafChngNo(equipment.getSafChngNo());
//        changeRefWork.setRefTableId(equipment.getSafFacilityCd() + "_" + String.valueOf(equipment.getEquipmentNo()));
//        changeRefWork.setRefTableNm("saf_facility_equip_spec");
//        changeRefWork.setChngRefWorkCd(ConstVal.SAF_CHNG_REF_WORK_1);
//        changeRefWork.setCreateUserId(equipment.getCreateUserId());
//        changeService.taskChange(changeRefWork);

        return resultNo;
    }

}
