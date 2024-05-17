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
package com.she.chm.mat.service;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.chm.mat.mapper.StorageFacilityManagementMapper;
import com.she.chm.model.MatInoutStatus;
import com.she.chm.model.StorageFacilityManagement;
import com.she.chm.model.StorageFacilityManagementDgrPerm;

@Service
public class StorageFacilityManagementService {
    @Autowired
    private StorageFacilityManagementMapper storageFacilityManagementMapper;

    /**
     * 저장위치 조회
     * 
     * @param plantCd
     *            대표사업장
     * @param location
     *            설치위치
     * @param matStrgNm
     *            저장위치명
     * @return 저장위치 목록
     * @throws Exception
     */
    public List<StorageFacilityManagement> getStorageFacilityManagements(String plantCd, String location,
            String matStrgNm, DefaultParam defaultParam) throws Exception {
        return storageFacilityManagementMapper.getStorageFacilityManagements(plantCd, location, matStrgNm, defaultParam);
    }

    /**
     * 저장위치 상세조회
     * 
     * @param matStrgCd
     *            저장위치코드
     * @return 저장위치
     * @throws Exception
     */
    public StorageFacilityManagement getStorageFacilityManagement(String plantCd, String matStrgCd, DefaultParam defaultParam) throws Exception {
        // 저장위치 상세조회
        StorageFacilityManagement storageFacilityManagement = storageFacilityManagementMapper
                .getStorageFacilityManagement(plantCd, matStrgCd, defaultParam);
        if (storageFacilityManagement != null) {
            // 저장위치에 따른 위험물허가량 조회
            storageFacilityManagement.setStorageFacilityManagementDgrPerms(
                    storageFacilityManagementMapper.getChemMatFacDgrPerms(plantCd, matStrgCd));
        }
        return storageFacilityManagement;
    }

    /**
     * 저장위치 키값 체크
     * 
     * @param plantCd
     *            대표사업장
     * @param matStrgCd
     *            저장위치
     * @return 저장위치 키값 체크
     * @throws Exception
     */
    public HashMap<String, Object> getStorageFacilityManagementCheck(String plantCd, String matStrgCd)
            throws Exception {
        return storageFacilityManagementMapper.getStorageFacilityManagementCheck(plantCd, matStrgCd);
    }

    /**
     * 저장위치 등록
     * 
     * @param storageFacilityManagement
     *            저장위치
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int createStorageFacilityManagement(StorageFacilityManagement storageFacilityManagement) throws Exception {
        int resultVal = 0;
        resultVal += storageFacilityManagementMapper.createStorageFacilityManagement(storageFacilityManagement);
        if (storageFacilityManagement.getStorageFacilityManagementDgrPerms() != null
                && storageFacilityManagement.getStorageFacilityManagementDgrPerms().size() > 0) {
            for (StorageFacilityManagementDgrPerm storageFacilityManagementDgrPerm : storageFacilityManagement
                    .getStorageFacilityManagementDgrPerms()) {
                storageFacilityManagementDgrPerm.setMatStrgCd(storageFacilityManagement.getMatStrgCd());
                storageFacilityManagementDgrPerm.setPlantCd(storageFacilityManagement.getPlantCd());
                resultVal += storageFacilityManagementMapper.createChemMatFacDgrPerms(storageFacilityManagementDgrPerm);
            }
        }
        return resultVal;
    }

    /**
     * 저장위치 저장
     * 
     * @param storageFacilityManagement
     *            저장위치
     * @return 변경 행 수
     * @throws Exception
     */
    @Transactional
    public int updateStorageFacilityManagement(StorageFacilityManagement storageFacilityManagement) throws Exception {
        int resultVal = 0;
        resultVal += storageFacilityManagementMapper.updateStorageFacilityManagement(storageFacilityManagement);
        if (storageFacilityManagement.getStorageFacilityManagementDgrPerms() != null) {
            resultVal += storageFacilityManagementMapper.deleteChemMatFacDgrPerms(
                    storageFacilityManagement.getMatStrgCd(), storageFacilityManagement.getPlantCd());
            if (storageFacilityManagement.getStorageFacilityManagementDgrPerms().size() > 0) {
                for (StorageFacilityManagementDgrPerm storageFacilityManagementDgrPerm : storageFacilityManagement
                        .getStorageFacilityManagementDgrPerms()) {
                    storageFacilityManagementDgrPerm.setMatStrgCd(storageFacilityManagement.getMatStrgCd());
                    storageFacilityManagementDgrPerm.setPlantCd(storageFacilityManagement.getPlantCd());
                    resultVal += storageFacilityManagementMapper
                            .createChemMatFacDgrPerms(storageFacilityManagementDgrPerm);
                }
            }
        }
        return resultVal;
    }

    /**
     * 위험물허가량 조회
     * 
     * @return 위험물허가량
     * @throws Exception
     */
    public List<StorageFacilityManagementDgrPerm> getChemDgrAttAmts(String plantCd, String matStrgCd) throws Exception {
        return storageFacilityManagementMapper.getChemDgrAttAmts(plantCd, matStrgCd);
    }

    /**
     * 저장위치 sap에서 받은 데이터 조회
     * 
     * @param plantCd
     *            대표사업장
     * @return 저장위치 sap에서 받은 데이터
     * @throws Exception
     */
    public List<StorageFacilityManagement> getSapMatStrg(String plantCd, String matStrgNm, DefaultParam defaultParam) throws Exception {
        return storageFacilityManagementMapper.getSapMatStrg(plantCd, matStrgNm, defaultParam);
    }

    /**
     * 저장위치에 따른 재고 현황 조회
     * 
     * @param plantCd
     *            대표사업장
     * @param matStrgCd
     *            저장위치코드
     * @return 저장위치에 따른 재고 현황
     * @throws Exception
     */
    public List<MatInoutStatus> getChemMatStorageStatus(String plantCd, String matStrgCd, DefaultParam defaultParam) throws Exception {
        return storageFacilityManagementMapper.getChemMatStorageStatus(plantCd, matStrgCd, defaultParam);
    }

}
