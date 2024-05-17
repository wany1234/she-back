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
package com.she.env.air.facility.service;

import java.util.List;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.air.facility.mapper.DischFacMapper;
import com.she.env.air.model.DischFac;
import com.she.env.air.model.Fuel;

/**
 * 대기 배출시설 기능정의
 *
 */
@Service("DischFacNService")
public class DischFacService {
    @Autowired
    private DischFacMapper dischFacNMapper;

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
    public List<DischFac> getDischargeFacilities(String useYn, String eairDischFacNm, String plantCd, String mgDeptCd, DefaultParam defaultParam) throws Exception {
        return this.dischFacNMapper.getDischargeFacilities(useYn, eairDischFacNm, plantCd, mgDeptCd, defaultParam);
    }

    /**
     * 배출시설 상세 조회
     *
     * @param eairDischFacNo
     *            배출시설번호
     * @return 배출시설
     * @throws Exception
     */
    public DischFac getDischargeFacility(int eairDischFacNo) throws Exception {
        DischFac dischFac = this.dischFacNMapper.getDischargeFacility(eairDischFacNo);


        dischFac.setFuelItems(this.dischFacNMapper.getFuels(eairDischFacNo));
        return dischFac;
    }

    /**
     * 배출시설 신규등록
     *
     * @param dischFac
     *            배출시설
     * @return 배출시설번호
     * @throws Exception
     */
    @Transactional
    public int createDischargeFacility(DischFac dischFac) throws Exception {

        int count = this.dischFacNMapper.createDischargeFacility(dischFac);

        if (count > 0) {
            this.dischFacNMapper.deleteFuel(dischFac.getEairDischFacNo());
            for (String data : dischFac.getFuelItem()) {
                this.dischFacNMapper.createFuel(dischFac.getEairDischFacNo(), data);
            }
        }

        return count > 0 ? dischFac.getEairDischFacNo() : 0;
    }

    /**
     * 배출시설 수정
     *
     * @param dischFac
     *            배출시설
     * @return 수정행수
     * @throws Exception
     */
    @Transactional
    public int updateDischargeFacility(DischFac dischFac) throws Exception {

        int count = this.dischFacNMapper.updateDischargeFacility(dischFac);

        if (count > 0) {
            this.dischFacNMapper.deleteFuel(dischFac.getEairDischFacNo());
            for (String data : dischFac.getFuelItem()) {
                this.dischFacNMapper.createFuel(dischFac.getEairDischFacNo(), data);
            }
        }
        return count > 0 ? dischFac.getEairDischFacNo() : 0;
    }

    /**
     * 배출시설 연료 조회
     *
     * @param eairDischFacNo
     *            배출시설 번호
     * @return 배출시설 연료
     * @throws Exception
     */
    public List<Fuel> getFuels(int eairDischFacNo) throws Exception {
        return dischFacNMapper.getFuels(eairDischFacNo);
    }

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
    public int createFuel(@Param("eairDischFacNo") int eairDischFacNo, @Param("eairFuelCd") String eairFuelCd)
            throws Exception {
        return dischFacNMapper.createFuel(eairDischFacNo, eairFuelCd);
    }

    /**
     * 배출시설 연료 삭제
     *
     * @param eairDischFacNo
     *            배출시설번호
     * @return 수정행수
     * @throws Exception
     */
    public int deleteFuel(@Param("eairDischFacNo") int eairDischFacNo) throws Exception {
        return dischFacNMapper.deleteFuel(eairDischFacNo);
    }

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
    public int checkDischargeFacility(String eairDischFacNm, int eairDischFacNo, String plantCd) throws Exception {
        return this.dischFacNMapper.checkDischargeFacility(eairDischFacNm, eairDischFacNo, plantCd);
    }
}
