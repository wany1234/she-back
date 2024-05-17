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
package com.she.env.air.baseInfo.service;

import java.util.List;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.she.env.air.baseInfo.mapper.OutletMapper;
import com.she.env.air.model.Outlet;
import com.she.env.air.model.OutletDischFacItem;
import com.she.env.air.model.OutletPreventSerial;
import com.she.env.air.model.TestItem;

/**
 * 대기 배출구 기능정의
 *
 */
@Service("EairOutletNService")
public class OutletService {
    @Autowired
    private OutletMapper outletMapper;

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
    public List<Outlet> getOutlets(String useYn, String eairOutletNm, String mainDischFacNm, String plantCd, String mgDeptCd, DefaultParam defaultParam) throws Exception {
        return this.outletMapper.getOutlets(useYn, eairOutletNm, mainDischFacNm, plantCd, mgDeptCd, defaultParam);
    }

    /**
     * 배출구 상세 조회
     *
     * @param eairOutletNo
     *            배출구번호
     * @return 배출구
     * @throws Exception
     */
    public Outlet getOutlet(int eairOutletNo, DefaultParam defaultParam) throws Exception {
        Outlet outlet = this.outletMapper.getOutlet(eairOutletNo);
        if (outlet.getPreventFacExemYn() != null && outlet.getPreventFacExemYn().equals("N")) {

            outlet.setOutletPreventSerial(this.outletMapper.getSerialPreventFac(outlet.getEairOutletNo()));

        } else if (outlet.getPreventFacExemYn() != null && outlet.getPreventFacExemYn().equals("Y")) {
            outlet.setOutletDischItem(this.outletMapper.getOutletDischItem(outlet.getEairOutletNo()));
        }
        outlet.setEairTestItemCds(this.outletMapper.getOutletTestItems(eairOutletNo, defaultParam));

        return outlet;
    }

    /**
     * 배출구 신규등록
     *
     * @param outlet
     *            배출구
     * @return 배출구번호
     * @throws Exception
     */
    @Transactional
    public int createOutlet(Outlet outlet) throws Exception {
        int count = this.outletMapper.createOutlet(outlet);
        if (count > 0) {
            for (TestItem eairTestItemCd : outlet.getEairTestItemCds()) {
                count += this.outletMapper.createOutletTestItem(eairTestItemCd);
            }
            if (outlet.getPreventFacExemYn().equals("N")) {
                if (outlet.getOutletPreventSerial() != null && outlet.getOutletPreventSerial().size() > 0) {
                    for (OutletPreventSerial item : outlet.getOutletPreventSerial()) {
                        item.setEairOutletNo(outlet.getEairOutletNo());
                        if (item.getEairSerialPreventFacNo() == 0) {
                            item.setEairSerialPreventFacNo(item.getEairPreventFacNo());
                        }
                        this.outletMapper.createSerialPreventFac(item);
                    }
                }

            } else if (outlet.getPreventFacExemYn().equals("Y")) {
                if (outlet.getOutletDischItem() != null && outlet.getOutletDischItem().size() > 0) {
                    for (OutletDischFacItem item : outlet.getOutletDischItem()) {
                        item.setEairOutletNo(outlet.getEairOutletNo());
                        item.setEairDischFacNo(item.getCode());
                        this.outletMapper.createOutletDischItem(item);
                    }
                }
            }
        }
        return count > 0 ? outlet.getEairOutletNo() : 0;
    }

    /**
     * 선택된 배출구 수정
     *
     * @param outlet
     *            배출구
     * @return 수정행수
     * @throws Exception
     */
    @Transactional
    public int updateOutlet(Outlet outlet) throws Exception {
        int count = this.outletMapper.updateOutlet(outlet);
        if (count > 0) {

            this.outletMapper.deleteOutletTestItem(outlet.getEairOutletNo());

            for (TestItem eairTestItemCd : outlet.getEairTestItemCds()) {
                eairTestItemCd.setEairOutletNo(outlet.getEairOutletNo());

                count += this.outletMapper.createOutletTestItem(eairTestItemCd);
            }
            this.outletMapper.delOutletDischItem(outlet.getEairOutletNo());

            this.outletMapper.delSerialPreventFac(outlet.getEairOutletNo());

            if (outlet.getPreventFacExemYn().equals("N")) {
                if (outlet.getOutletPreventSerial() != null && outlet.getOutletPreventSerial().size() > 0) {
                    for (OutletPreventSerial item : outlet.getOutletPreventSerial()) {
                        item.setEairOutletNo(outlet.getEairOutletNo());
                        if (item.getEairSerialPreventFacNo() == 0) {
                            item.setEairSerialPreventFacNo(item.getEairPreventFacNo());
                        }
                        this.outletMapper.createSerialPreventFac(item);
                    }
                }

            } else if (outlet.getPreventFacExemYn().equals("Y")) {
                if (outlet.getOutletDischItem() != null && outlet.getOutletDischItem().size() > 0) {
                    for (OutletDischFacItem item : outlet.getOutletDischItem()) {
                        item.setEairOutletNo(outlet.getEairOutletNo());
                        item.setEairDischFacNo(item.getCode());
                        this.outletMapper.createOutletDischItem(item);
                    }
                }
            }
        }
        return count > 0 ? outlet.getEairOutletNo() : 0;
    }

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
    public int checkOutlet(int eairOutletNo, String mainDischFacNm, String plantCd) throws Exception {
        return this.outletMapper.checkOutlet(eairOutletNo, mainDischFacNm, plantCd);
    }

}
