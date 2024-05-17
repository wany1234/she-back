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
package com.she.psm.processFacility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.psm.model.Equipment;

@Mapper
@Repository("com.she.psm.processFacility.mapper.EquipmentMapper")
public interface EquipmentMapper {
    /**
     * 장치 및 설비 조회
     * 
     * @param plantCd
     *            사업장
     * @param processNo
     *            공정
     * @param safFacilityCd
     *            설비코드
     * @param facilityNm
     *            설비명
     * @param facilityPsmCd
     *            PSM 설비유형
     * @return 장치 및 설비 목록
     * @throws Exception
     */
    public List<Equipment> getEquipments(@Param("plantCd") String plantCd, @Param("processCd") String processCd,
            @Param("safFacilityCd") String safFacilityCd, @Param("facilityNm") String facilityNm,
            @Param("facilityPsmCd") String facilityPsmCd) throws Exception;

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
    public Equipment getEquipment(@Param("safFacilityCd") String safFacilityCd, @Param("equipmentNo") int equipmentNo)
            throws Exception;

    /**
     * 장치 및 설비 등록
     * 
     * @param PowerMachine
     * @return 변경 행 수
     * @throws Exception
     */
    public int createEquipment(Equipment equipment) throws Exception;

    /**
     * 장치 및 설비 수정
     * 
     * @param PowerMachine
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateEquipment(Equipment equipment) throws Exception;

}
