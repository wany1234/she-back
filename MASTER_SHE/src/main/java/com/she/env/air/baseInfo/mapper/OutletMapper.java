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
package com.she.env.air.baseInfo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.she.env.air.model.Outlet;
import com.she.env.air.model.OutletDischFacItem;
import com.she.env.air.model.OutletPreventSerial;
import com.she.env.air.model.TestItem;

/**
 * 대기 배출구 맵퍼
 *
 */
@Mapper
@Repository("com.she.env.air.baseInfo.mapper.OutletMapper")
public interface OutletMapper {

    /**
     * 배출구 전체 조회
     *
     * @param useYn
     *            사용여부
     * @param eairOutletNm
     *            배출구일련번호
     * @param mainDischFacNm
     *            배출구명
     * @param plantCd
     *            사업장코드
     * @param mgDeptCd
     *            관리부서코드
     * @return 배출구목록
     * @throws Exception
     */
    public List<Outlet> getOutlets(@Param("useYn") String useYn,
                                   @Param("eairOutletNm") String eairOutletNm,
                                   @Param("mainDischFacNm") String mainDischFacNm,
                                   @Param("plantCd") String plantCd,
                                   @Param("mgDeptCd") String mgDeptCd,
                                   @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 배출구 상세 조회
     * 
     * @param eairOutletNo
     *            배출구번호
     * @return 배출구
     * @throws Exception
     */
    public Outlet getOutlet(@Param("eairOutletNo") int eairOutletNo) throws Exception;

    /**
     * 배출구 신규등록
     * 
     * @param outlet
     *            배출구
     * @return 생성행수
     * @throws Exception
     */
    public int createOutlet(Outlet outlet) throws Exception;

    /**
     * 배출구 수정
     * 
     * @param outlet
     *            배출구
     * @return 수정행수
     * @throws Exception
     */
    public int updateOutlet(Outlet outlet) throws Exception;

    /**
     * 배출구별 검사항목 조회
     * 
     * @param eairOutletNo
     *            배출구번호
     * @return 검사항목 목록
     * @throws Exception
     */
    public ArrayList<TestItem> getOutletTestItems(@Param("eairOutletNo") int eairOutletNo,
                                                  @Param("defaultParam") DefaultParam defaultParam) throws Exception;

    /**
     * 배출구별 검사항목 등록
     * 
     * @param outletitem
     *            검사항목
     * @return 생성행수
     * @throws Exception
     */
    public int createOutletTestItem(TestItem outletitem) throws Exception;

    /**
     * 배출구별 검사항목 삭제
     * 
     * @param eairOutletNo
     *            배출구번호
     * @return 삭제행수
     * @throws Exception
     */
    public int deleteOutletTestItem(@Param("eairOutletNo") int eairOutletNo) throws Exception;

    /**
     * 배출구명 중복체크
     *
     * @param eairOutletNo
     *            배출구번호
     * @param mainDischFacNm
     *            배출구명
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkOutlet(@Param("eairOutletNo") int eairOutletNo,
                           @Param("mainDischFacNm") String mainDischFacNm,
                           @Param("plantCd") String plantCd) throws Exception;
    
    /**
     * 배출구-배출시설 등록
     * @param outletDischFacItem
     * @return
     * @throws Exception
     */
    public int createOutletDischItem(OutletDischFacItem outletDischFacItem) throws Exception;
    
    
    
    /**
     * 배출구-배출시설 제거
     * @param eairOutletNo
     * @return
     * @throws Exception
     */
    public int delOutletDischItem(@Param("eairOutletNo") int eairOutletNo) throws Exception;
    
    
    
    /**
     * 배출구-배출시설 조회
     * @param eairOutletNO
     * @return
     * @throws Exception
     */
    public List<OutletDischFacItem> getOutletDischItem(@Param("eairOutletNo") int eairOutletNO) throws Exception;
    
    
    
    /**
     * 배출구-연결방지시설 생성
     * @param outletPreventSerial
     * @return
     * @throws Exception
     */
    public int createSerialPreventFac(OutletPreventSerial outletPreventSerial) throws Exception;
    
    /**
     * 배출구-연결방지시설 삭제
     */
    public int delSerialPreventFac(@Param("eairOutletNo") int eairOutletNo) throws Exception;
    
    /**
     * 배출구-연결방지시설 조회
     * @param eairOutletNo
     * @return
     * @throws Exception
     */
    public List<OutletPreventSerial> getSerialPreventFac(@Param("eairOutletNo") int eairOutletNo) throws Exception; 
    
    
}
