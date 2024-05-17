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
package com.she.env.air.facility.mapper;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.DischFac;
import com.she.env.air.model.Fuel;


/**
 * 대기배출시설 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.facility.mapper.DischFacMapper")
public interface DischFacMapper {

    /**
     * 배출시설 전체 조회
     *
     * @param useYn
     *            사용여부
     * @param eairDischFacNm
     *            배출시설명
     * @param plantCd
     *            사업장코드
     * @param mgDeptCd
     *            관리부서코드
     * @return 배출시설 목록
     * @throws Exception
     */
    public List<DischFac> getDischargeFacilities(@Param("useYn") String useYn,
                                                 @Param("eairDischFacNm") String eairDischFacNm,
                                                 @Param("plantCd") String plantCd,
                                                 @Param("mgDeptCd") String mgDeptCd,
                                                 @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 선택된 배출시설 상세 조회
     * 
     * @param eairDischFacNo
     *            배출시설번호
     * @return 배출시설
     * @throws Exception
     */
    public DischFac getDischargeFacility(@Param("eairDischFacNo") int eairDischFacNo) throws Exception;

    /**
     * 배출시설 신규등록
     * 
     * @param dischFac
     *            배출시설
     * @return 등록행수
     * @throws Exception
     */
    public int createDischargeFacility(DischFac dischFac) throws Exception;

    /**
     * 선택된 배출시설 수정
     * 
     * @param dischFac
     *            배출시설
     * @return 수정행수
     * @throws Exception
     */
    public int updateDischargeFacility(DischFac dischFac) throws Exception;
              
       

    /**
     * 배출시설 연료 조회
     *
     * @param eairDischFacNo
     *            배출시설 번호
     * @return 배출시설 연료
     * @throws Exception
     */
    public List<Fuel> getFuels(@Param("eairDischFacNo") int eairDischFacNo) throws Exception;

    /**
     * 배출시설 연료 등록
     *
     * @param eairDischFacNo
     *            배출시설번호
     * @param eairFuelCd
     *            연료코드
     * @return 등록행수
     * @throws Exception
     */
    public int createFuel(@Param("eairDischFacNo") int eairDischFacNo, @Param("eairFuelCd") String fuel)
            throws Exception;

    /**
     * 배출시설 연료 삭제
     *
     * @param eairDischFacNo
     *            배출시설번호
     * @return 수정행수
     * @throws Exception
     */
    public int deleteFuel(@Param("eairDischFacNo") int eairDischFacNo) throws Exception;

    /**
     * 배출시설명 중복체크
     * @param eairDischFacNm
     *            대기 배출시설 명
     * @param eairDischFacNo
     *            대기 배출시설 번호
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkDischargeFacility(@Param("eairDischFacNm") String eairDischFacNm,
                                      @Param("eairDischFacNo") int eairDischFacNo,
                                      @Param("plantCd") String plantCd) throws Exception;
}
