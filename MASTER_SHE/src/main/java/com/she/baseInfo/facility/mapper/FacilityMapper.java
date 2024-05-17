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
package com.she.baseInfo.facility.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.baseInfo.model.FacilityMst;
import com.she.baseInfo.model.FacilityTypeItem;
import com.she.baseInfo.model.FacilityTypeItemVal;
import com.she.common.model.DefaultParam;

@Mapper
@Repository("com.she.baseInfo.facility.mapper.FacilityMapper")
public interface FacilityMapper {
    /**
     * 안전설비마스터 조회
     * 
     * @param safFacilityTypeCd
     *            설비유형코드
     * @param processCd
     *            공정번호
     * @param facilityNm
     *            설비명
     * @param deptCd
     *            설비관리부서
     * @param safFacilityCd
     *            설비코드
     * @param facilityMgrNum
     *            관리번호
     * @param installLocate
     *            설치위치
     * @param plantCd
     *            사업장
     * @return 안전설비마스터 목록
     * @throws Exception
     */
    public List<FacilityMst> getFacilityMsts(@Param("safFacilityTypeCd") String safFacilityTypeCd,
            @Param("facilityNm") String facilityNm, @Param("deptCd") String deptCd,
            @Param("processCd") String processCd, @Param("safFacilityCd") String safFacilityCd,
            @Param("installLocate") String installLocate, @Param("plantCd") String plantCd,
            @Param("disuseYn") String disuseYn, @Param("safCheckTypeCd") String safCheckTypeCd,
            @Param("safLsnYn") String safLsnYn, @Param("safChkYn") String safChkYn, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전설비마스터 상세조회
     * 
     * @param safFacilityCd
     *            안전설비코드
     * @return 안전설비
     * @throws Exception
     */
    public FacilityMst getFacilityMst(@Param("safFacilityCd") String safFacilityCd, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 안전설비유형 관리항목 조회
     * 
     * @param safFacilityCd
     *            설비코드
     * @param safFacilityTypeCd
     *            설비유형 코드
     * @return 안전설비유형 관리항목 목록
     * @throws Exception
     */
    public List<FacilityTypeItem> getFacilityTypeItems(@Param("safFacilityCd") String safFacilityCd,
            @Param("safFacilityTypeCd") String safFacilityTypeCd) throws Exception;

    /**
     * 안전설비유형 관리항목 조회
     * 
     * @param safFacilityCd
     *            설비코드
     * @param safFacilityTypeCd
     *            설비유형 코드
     * @return 안전설비유형 관리항목 목록
     * @throws Exception
     */
    public List<FacilityTypeItem> getNewFacilityTypeItems(@Param("safFacilityTypeCd") String safFacilityTypeCd)
            throws Exception;

    /**
     * 안전설비마스터 중복검사
     * 
     * @param safFacilityCd
     * @return 중복 행 수
     * @throws Exception
     */
    public int countFacilityMst(@Param("safFacilityCd") String safFacilityCd) throws Exception;

    /**
     * 안전설비마스터 신규등록
     * 
     * @param facilityMst
     * @return 등록 행 수
     * @throws Exception
     */
    public int createFacilityMst(FacilityMst facilityMst) throws Exception;

    /**
     * 안전설비마스터 수정
     * 
     * @param facilityMst
     * @return 수정 행 수
     * @throws Exception
     */
    public int updateFacilityMst(FacilityMst facilityMst) throws Exception;

    /**
     * 안전설비 관리항목값 등록
     * 
     * @param facilityTypeItemVal
     *            안전설비 관리항목값
     * @return 생성 행 수
     * @throws Exception
     */
    public int createFacilityTypeItemVal(FacilityTypeItemVal facilityTypeItemVal) throws Exception;

    /**
     * 안전설비 관리항목값 삭제
     * 
     * @param plantCd
     *            사업장
     * @param safFacilityCd
     *            안전설비코드
     * @param safFacilityTypeItemNo
     *            안전설비유형 관리항목번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteFacilityTypeItemVal(@Param("safFacilityCd") String safFacilityCd,
            @Param("safFacilityTypeItemNo") int safFacilityTypeItemNo) throws Exception;

    /**
     * 유형별 설비현황 조회
     * 
     * @param processCd
     *            공정코드
     * @param deptCd
     *            관리부서코드
     * @return 유형별 설비현황
     * @throws Exception
     */
    public List<FacilityMst> getFacilityMstStatus(@Param("plantCd") String plantCd, @Param("deptCd") String deptCd,
            @Param("processCd") String processCd, @Param("specHealth") boolean specHealth, @Param("defaultParam") DefaultParam defaultParam) throws Exception;

}
