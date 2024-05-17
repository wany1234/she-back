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
package com.she.chm.mat.mapper;

import java.util.HashMap;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.chm.model.MatInoutStatus;
import com.she.chm.model.StorageFacilityManagement;
import com.she.chm.model.StorageFacilityManagementDgrPerm;

@Mapper
@Repository("com.she.chm.mat.mapper.StorageFacilityManagementMapper")
public interface StorageFacilityManagementMapper {

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
    public List<StorageFacilityManagement> getStorageFacilityManagements(@Param("plantCd") String plantCd,
            @Param("location") String location, @Param("matStrgNm") String matStrgNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 저장위치 상세조회
     * 
     * @param matStrgCd
     *            저장위치코드
     * @return 저장위치
     * @throws Exception
     */
    public StorageFacilityManagement getStorageFacilityManagement(@Param("plantCd") String plantCd,
            @Param("matStrgCd") String matStrgCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public HashMap<String, Object> getStorageFacilityManagementCheck(@Param("plantCd") String plantCd,
            @Param("matStrgCd") String matStrgCd) throws Exception;

    /**
     * 저장위치 등록
     * 
     * @param storageFacilityManagement
     *            저장위치
     * @return 변경 행 수
     * @throws Exception
     */
    public int createStorageFacilityManagement(StorageFacilityManagement storageFacilityManagement) throws Exception;

    /**
     * 저장위치 저장
     * 
     * @param storageFacilityManagement
     *            저장위치
     * @return 변경 행 수
     * @throws Exception
     */
    public int updateStorageFacilityManagement(StorageFacilityManagement storageFacilityManagement) throws Exception;

    /**
     * 저장위치에 따른 위험물허가량 조회
     * 
     * @param matStrgCd
     *            저장위치코드
     * @return 저장위치
     * @throws Exception
     */
    public List<StorageFacilityManagementDgrPerm> getChemMatFacDgrPerms(@Param("plantCd") String plantCd,
            @Param("matStrgCd") String matStrgCd) throws Exception;

    /**
     * 위험물허가량 조회
     * 
     * @return 위험물허가량
     * @throws Exception
     */
    public List<StorageFacilityManagementDgrPerm> getChemDgrAttAmts(@Param("plantCd") String plantCd,
            @Param("matStrgCd") String matStrgCd) throws Exception;

    /**
     * 저장위치에 따른 위험물허가량 등록
     * 
     * @param storageFacilityManagementDgrPerm
     *            저장위치에 따른 위험물허가량
     * @return 변경 행 수
     * @throws Exception
     */
    public int createChemMatFacDgrPerms(StorageFacilityManagementDgrPerm storageFacilityManagementDgrPerm)
            throws Exception;

    /**
     * 저장위치에 따른 위험물허가량 삭제
     * 
     * @param matStrgCd
     *            저장위치코드
     * @return 변경 행 수
     * @throws Exception
     */
    public int deleteChemMatFacDgrPerms(@Param("matStrgCd") String matStrgCd, @Param("plantCd") String plantCd)
            throws Exception;

    /**
     * 저장위치 sap에서 받은 데이터 조회
     * 
     * @param plantCd
     *            대표사업장
     * @return 저장위치 sap에서 받은 데이터
     * @throws Exception
     */
    public List<StorageFacilityManagement> getSapMatStrg(@Param("plantCd") String plantCd,
            @Param("matStrgNm") String matStrgNm, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

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
    public List<MatInoutStatus> getChemMatStorageStatus(@Param("plantCd") String plantCd,
            @Param("matStrgCd") String matStrgCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
