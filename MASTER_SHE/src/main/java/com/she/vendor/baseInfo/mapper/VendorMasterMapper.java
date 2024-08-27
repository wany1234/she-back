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
package com.she.vendor.baseInfo.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.common.model.DefaultParam;
import com.she.vendor.model.ChemicalVendorMaster;
import com.she.vendor.model.VendorWorker;

@Mapper
@Repository("com.she.vendor.baseInfo.mapper.VendorMasterMapper")
public interface VendorMasterMapper {

    /**
     * 업체 조회
     * 
     * @param originCd
     *            원산지
     * @param vendorNm
     *            업체명
     * @param vendorTypeCd
     *            업체분류
     * @param useYn
     *            사용여부
     * @return 업체 목록
     * @throws Exception
     */
    public List<ChemicalVendorMaster> getChemicalVendorMasters(@Param("plantCd") String plantCd, @Param("vendorNm") String vendorNm, @Param("vendorTypeCd") String vendorTypeCd, @Param("vendorAttCd") String vendorAttCd, @Param("useYn") String useYn, @Param("pageNumber") Integer pageNumber, @Param("pageSize") Integer pageSize,
            @Param("orderByExpression") String orderByExpression, @Param("authYn") String authYn, @Param("addYn") String addYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 업체 상세 조회
     * 
     * @param vendorCd
     *            업체코드
     * @return 업체
     * @throws Exception
     */
    public ChemicalVendorMaster getChemicalVendorMaster(@Param("vendorCd") String vendorCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 업체 상세 조회 - 사업장
     * 
     * @param vendorCd
     *            업체코드
     * @return 업체
     * @throws Exception
     */
    public List<Map<String, String>> getChemicalVendorMasterPlants(@Param("vendorCd") String vendorCd) throws Exception;

    /**
     * 업체 상세 조회 - 근무자
     * 
     * @param vendorCd
     *            업체코드
     * @param workerNm
     *            작업자성명
     * @return 업체
     * @throws Exception
     */
    public List<VendorWorker> getChemicalVendorMasterWorkers(@Param("vendorCd") String vendorCd, @Param("workerNm") String workerNm, @Param("plantCd") String plantCd, @Param("vendorTypeCd") String vendorTypeCd, @Param("vendorAttCd") String vendorAttCd, @Param("vendorNm") String vendorNm, @Param("defaultParam") DefaultParam defaultParam)
            throws Exception;

    /**
     * 업체 신규등록
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalVendorMaster(ChemicalVendorMaster chemicalVendorMaster) throws Exception;

    /**
     * 업체 신규등록 - 선택 사업장 등록
     * 
     * @param plants
     *            업체 사업장
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalVendorMasterPlants(Map<String, String> plants) throws Exception;

    /**
     * 업체 신규등록 - 근무인원 등록
     * 
     * @param vendorWorker
     *            근무자
     * @return 등록 행 수
     * @throws Exception
     */
    public int createChemicalVendorMasterWorkers(VendorWorker vendorWorker) throws Exception;

    /**
     * 업체 수정
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalVendorMaster(ChemicalVendorMaster chemicalVendorMaster) throws Exception;

    /**
     * 포털 비밀번호 초기화
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateChemicalVendorMasterPwdReset(ChemicalVendorMaster chemicalVendorMaster) throws Exception;

    /**
     * 업체명 체크
     * 
     * @param vendorNm
     *            업체명
     * @param vendorCdOrigin
     *            업체코드 (저장된)
     * @param vendorCd
     *            업체코드 (수정할)
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckChemicalVendorMaster(@Param("vendorNm") String vendorNm, @Param("vendorCdOrigin") String vendorCdOrigin, @Param("vendorCd") String vendorCd) throws Exception;

    /**
     * 업체 사업자 삭제
     * 
     * @param vendorCd
     *            업체코드
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChemicalVendorMasterPlants(String vendorCd) throws Exception;

    /**
     * 업체 근무자 삭제
     * 
     * @param venderCd
     *            업체코드
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteChemicalVendorMasterWorkers(String vendorCd) throws Exception;

    /**
     * 포탈아이디 중복 체크
     *
     * @param portalId
     *            포탈아이디
     * @return 체크 값
     * @throws Exception
     */
    public Integer getCheckPortalId(@Param("portalId") String portalId) throws Exception;

    /**
     * 사업자번호 중복 체크
     *
     * @param bizNum
     *            사업자번호
     * @return 체크 값
     * @throws Exception
     */
    public Integer getCheckBizNum(@Param("bizNum") String bizNum) throws Exception;

    /**
     * 인증번호 중복 체크
     *
     * @param authNumber
     *            인증번호
     * @return 체크 값
     * @throws Exception
     */
    public String getAuthNumber(@Param("authNumber") String authNumber) throws Exception;

    /**
     * 인증번호 업데이트
     * 
     * @param chemicalVendorMaster
     *            업체
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateAuthNumber(ChemicalVendorMaster chemicalVendorMaster) throws Exception;
    
    /**
     * 인증번호 체크
     * @param authNumber
     * @return
     * @throws Exception
     */
    public ChemicalVendorMaster getVendorAuth(@Param("authNumber") String authNumber) throws Exception;
}
