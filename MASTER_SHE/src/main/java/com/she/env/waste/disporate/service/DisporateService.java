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
package com.she.env.waste.disporate.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.env.waste.disporate.mapper.DisporateMapper;
import com.she.env.waste.model.Disporate;
import com.she.env.waste.model.DisporateDeptItem;

/**
 * 폐기물 관리대장 기능정의
 *
 */
@Service("DisporateService")
public class DisporateService {

    @Autowired
    private DisporateMapper disporateMapper;

    /**
     * 부서별 분배율 목록
     * 
     * @param fromDate,
     *            시작기간
     * @param toDate,
     *            마감기간
     * @param plantCd,
     *            사업장코드
     * @param ewstClassCd,
     *            폐기물 분류 코드
     * @param ewstWasteNo,
     *            폐기물 고유번호
     * @return 부서별 분배율 목록
     * @throws Exception
     */
    public List<Disporate> getDisporates(String dispoDeptCd, String fromYear, String toYear, String plantCd, String ewstClassCd, String ewstWasteNo, DefaultParam defaultParam) throws Exception {
        return this.disporateMapper.getDisporates(dispoDeptCd, fromYear, toYear, plantCd, ewstClassCd, ewstWasteNo, defaultParam);

    }

    /**
     * 분배율 (사업장, 연도, 폐기물, 폐기물분류) 중복 체크
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * @param ewstWasteNo
     *            폐기물 번호
     * @param plantCd
     *            사업장
     * @param year
     *            연도
     * @throws Exception
     */
    public int getDisporateCheck(String ewstWasteRateNo, String ewstWasteNo, String plantCd, String year)
            throws Exception {
        return this.disporateMapper.getDisporateCheck(ewstWasteRateNo, ewstWasteNo, plantCd, year);

    }

    /**
     * 분배율 조회
     * 
     * @param ewstWasteRateNo
     *            분배율 번호
     * 
     * @throws Exception
     */

    public Disporate getDisporate(String plantCd, String ewstWasteRateNo) throws Exception {
        Disporate disporate = Optional.ofNullable(this.disporateMapper.getDisporate(ewstWasteRateNo))
                .orElse(new Disporate());
        disporate.setDisporateDeptItem(
                Optional.ofNullable(this.disporateMapper.getDisporateDept(plantCd, ewstWasteRateNo))
                        .orElse(new ArrayList<DisporateDeptItem>()));
        disporate.setCheckedDeptList(Optional.ofNullable(this.disporateMapper.getDisporateCheckedDepts(ewstWasteRateNo))
                .orElse(new ArrayList<String>()));
        return disporate;
    }

    /**
     * 분배율 업데이트
     * 
     * @param Disporate
     *            분배율
     * 
     * @throws Exception
     */
    @Transactional
    public int updateDisporate(Disporate disporate) throws Exception {
        String regExp = "[m]([0-9]{2})";
        int count = 0;
        count += this.disporateMapper.updateDisporate(disporate);
        List<DisporateDeptItem> disporateDeptItem = disporate.getDisporateDeptItem();

        this.disporateMapper.deleteDisporateDept(disporate.getEwstWasteRateNo());
        for (DisporateDeptItem item : disporateDeptItem) {

            Class<?> object = item.getClass();
            for (Field instance : object.getDeclaredFields()) {
                instance.setAccessible(true);
                if (Pattern.compile(regExp).matcher(instance.getName()).matches()) {
                    String month = instance.getName().replaceAll(regExp, "$1");
                    if (instance.getType().getName().equals("java.lang.Float")) {
                        Float rate = (Float) instance.get(item);
                        if (rate != 0.0) {
                            count = +this.disporateMapper.createDisporateDept(disporate.getEwstWasteRateNo(),
                                    item.getDeptCd(), month, rate);
                        }
                    }
                }
            }
        }
        ;

        return count;
    }

    /**
     * 분배율 생성
     * 
     * @param Disporate
     *            분배율
     * 
     * @throws Exception
     */
    @Transactional
    public int createDisporate(Disporate disporate) throws Exception {
        String regExp = "[m]([0-9]{2})";
        int count = 0;

        count += this.disporateMapper.createDisporate(disporate);

        List<DisporateDeptItem> disporateDeptItem = disporate.getDisporateDeptItem();
        for (DisporateDeptItem item : disporateDeptItem) {

            Class<?> object = item.getClass();
            for (Field instance : object.getDeclaredFields()) {
                instance.setAccessible(true);
                if (Pattern.compile(regExp).matcher(instance.getName()).matches()) {
                    String month = instance.getName().replaceAll(regExp, "$1");
                    if (instance.getType().getName().equals("java.lang.Float")) {
                        Float rate = (Float) instance.get(item);
                        if (rate != 0.0) {
                            count = +this.disporateMapper.createDisporateDept(disporate.getEwstWasteRateNo(),
                                    item.getDeptCd(), month, rate);
                        }
                    }
                }
            }
        }
        ;

        return Integer.parseInt(disporate.getEwstWasteRateNo());
    }

}
