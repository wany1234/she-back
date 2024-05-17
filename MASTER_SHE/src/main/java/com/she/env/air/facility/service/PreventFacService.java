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

import com.she.common.model.DefaultParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.env.air.facility.mapper.PreventFacMapper;
import com.she.env.air.model.PreventDischFacItem;
import com.she.env.air.model.PreventFac;
import com.she.env.air.model.PreventFacActCarbHist;
import com.she.env.air.model.PreventFacChgHist;
import com.she.env.air.model.PreventFacElecMeter;
import com.she.env.air.model.PreventFacItem;
import com.she.env.air.model.PreventFacMaintHist;

/**
 * 대기 방지시설 기능정의
 *
 */
@Service("EairPreventFacNService")
public class PreventFacService {

    @Autowired
    private PreventFacMapper preventFacMapper;

    /**
     * 방지시설 전체 조회
     *
     * @param useYn
     *            사용여부
     * @param eairPreventFacClassCd
     *            방지시설분류코드
     * @param eairPreventFacNm
     *            방지시설명
     * @param plantCd
     *            사업장코드
     * @param mgDeptCd
     *            관리부서코드
     * @return 방지시설목록
     * @throws Exception
     */
    public List<PreventFac> getPreventionFacilities(String useYn, String eairPreventFacClassCd, String eairPreventFacNm, String plantCd, String mgDeptCd, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacilities(useYn, eairPreventFacClassCd, eairPreventFacNm, plantCd, mgDeptCd, defaultParam);
    }

    /**
     * 선택된 방지시설 상세 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    public PreventFac getPreventionFacility(int eairPreventFacNo) throws Exception {
        PreventFac preventFac = this.preventFacMapper.getPreventionFacility(eairPreventFacNo);
        preventFac.setOutletDischItem(this.preventFacMapper.selectPreventDisch(eairPreventFacNo));
        preventFac.setPrevFacElecMeter(this.preventFacMapper.getPreventionElecMeter(eairPreventFacNo));

        return preventFac;
    }

    /**
     * 선택된 방지시설 전략량계 상세 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설
     * @throws Exception
     */
    public List<PreventFacElecMeter> getPreventionElecMeter(int eairPreventFacNo) throws Exception {
        return this.preventFacMapper.getPreventionElecMeter(eairPreventFacNo);
    }

    /**
     * 방지시설 신규등록
     *
     * @param preventFac
     *            방지시설
     * @return 방지시설번호
     * @throws Exception
     */

    @Transactional
    public int createPreventionFacility(PreventFac preventFac) throws Exception {
        int count = this.preventFacMapper.createPreventionFacility(preventFac);
        if (count > 0) {
            // 연결 배출시설 삭제 및 등록
            this.preventFacMapper.deletePreventionDischItem(preventFac.getEairPreventFacNo());
            if (preventFac.getOutletDischItem() != null) {
                for (PreventDischFacItem preventDischFacItem : preventFac.getOutletDischItem()) {
                    preventDischFacItem.setEairPreventFacNo(preventFac.getEairPreventFacNo());
                    this.preventFacMapper.createPreventionDischItem(preventDischFacItem);
                }
            }

            // 전력량계 등록
            if (preventFac.getPrevFacElecMeter() != null && preventFac.getPrevFacElecMeter().size() > 0) {
                for (PreventFacElecMeter preventFacElecMeter : preventFac.getPrevFacElecMeter()) {
                    preventFacElecMeter.setEairPreventFacNo(preventFac.getEairPreventFacNo());
                    this.preventFacMapper.createPreventionElecMeter(preventFacElecMeter);
                }
            }
        }
        return count > 0 ? preventFac.getEairPreventFacNo() : 0;
    }

    /**
     * 방지지설 수정
     *
     * @param preventFac
     *            방지시설
     * @return 수정행수
     * @throws Exception
     */
    @Transactional
    public int updatePreventionFacility(PreventFac preventFac) throws Exception {
        int count = this.preventFacMapper.updatePreventionFacility(preventFac);
        if (count > 0) {
            // 연결 배출시설 삭제 및 등록
            this.preventFacMapper.deletePreventionDischItem(preventFac.getEairPreventFacNo());
            if (preventFac.getOutletDischItem() != null) {
                for (PreventDischFacItem preventDischFacItem : preventFac.getOutletDischItem()) {
                    preventDischFacItem.setEairPreventFacNo(preventFac.getEairPreventFacNo());
                    this.preventFacMapper.createPreventionDischItem(preventDischFacItem);
                }
            }

            // 전력량계 삭제 및 등록
            this.preventFacMapper.deletePreventionElecMeter(preventFac.getEairPreventFacNo());
            if (preventFac.getPrevFacElecMeter() != null && preventFac.getPrevFacElecMeter().size() > 0) {
                for (PreventFacElecMeter preventFacElecMeter : preventFac.getPrevFacElecMeter()) {
                    preventFacElecMeter.setEairPreventFacNo(preventFac.getEairPreventFacNo());
                    this.preventFacMapper.createPreventionElecMeter(preventFacElecMeter);
                }
            }
        }
        return count > 0 ? preventFac.getEairPreventFacNo() : 0;
    }

    /**
     * 방지시설 변경이력 전체 조회
     *
     * @param eairPreventFacNo
     * @return 방지시설 변경이력 목록
     * @throws Exception
     */
    public List<PreventFacChgHist> getPreventionFacilityChangeHistories(int eairPreventFacNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacilityChangeHistories(eairPreventFacNo, defaultParam);
    }

    /**
     * 선택된 방지시설 변경이력 상세 조회
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경 이력 번호
     * @return 방지시설 변경이력
     * @throws Exception
     */
    public PreventFacChgHist getPreventionFacilityChangeHistory(int eairPreventFacChgHistNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacilityChangeHistory(eairPreventFacChgHistNo, defaultParam);
    }

    /**
     * 선택된 방지시설 마지막 변경이력 상세 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설 변경이력
     * @throws Exception
     */
    public PreventFacChgHist getPreventionFacilityLastChangeHistory(int eairPreventFacNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacilityLastChangeHistory(eairPreventFacNo, defaultParam);
    }

    /**
     * 방지시설 변경이력 신규등록
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 방지시설 변경이력 번호
     * @throws Exception
     */

    @Transactional
    public int createPreventionFacilityChangeHistory(PreventFacChgHist preventFacChgHist) throws Exception {
        int count = this.preventFacMapper.createPreventionFacilityChangeHistory(preventFacChgHist);
        if (count > 0) {
            if (preventFacChgHist.getEairPolluCds() != null && preventFacChgHist.getEairPolluCds().size() > 0) {
                for (PreventFacItem eairTestItemCd : preventFacChgHist.getEairPolluCds()) {
                    eairTestItemCd.setEairPreventFacChgHistNo(preventFacChgHist.getEairPreventFacChgHistNo());
                    count += this.preventFacMapper.createPreventionFacilityChangeHistoryPollu(eairTestItemCd);
                }
            }
        }

        if (count > 0) {
            if (preventFacChgHist.getEairChemCds() != null && preventFacChgHist.getEairChemCds().size() > 0) {
                for (PreventFacItem eairTestItemCd : preventFacChgHist.getEairChemCds()) {
                    eairTestItemCd.setEairPreventFacChgHistNo(preventFacChgHist.getEairPreventFacChgHistNo());
                    count += this.preventFacMapper.createPreventionFacilityChangeHistoryChem(eairTestItemCd);
                }
            }
        }
        return preventFacChgHist.getEairPreventFacChgHistNo();
    }

    /**
     * 방지시설 변경이력 처리오염물질
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 방지시설 변경이력 번호
     * @throws Exception
     */
    public List<PreventFacItem> selectPreventionFacilityChangeHistoryPollu(int eairPreventFacChgHistNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.selectPreventionFacilityChangeHistoryPollu(eairPreventFacChgHistNo, defaultParam);
    }

    /**
     * 방지시설 변경이력 처리오염물질
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 방지시설 변경이력 번호
     * @throws Exception
     */
    public int createPreventionFacilityChangeHistoryPollu(PreventFacItem preventFacItem) throws Exception {
        this.preventFacMapper.createPreventionFacilityChangeHistoryPollu(preventFacItem);
        return preventFacItem.getEairPreventFacChgHistNo();
    }

    /**
     * 방지시설 변경이력 사용약품 조회
     *
     * @param eairPreventFacChgHistNo
     *            방지시설 변경이력
     * @return 방지시설 변경이력 번호
     * @throws Exception
     */

    public List<PreventFacItem> selectPreventionFacilityChangeHistoryChem(int eairPreventFacChgHistNo) throws Exception {
        return this.preventFacMapper.selectPreventionFacilityChangeHistoryChem(eairPreventFacChgHistNo);
    }

    /**
     * 방지시설 변경이력 사용약품
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 방지시설 변경이력 번호
     * @throws Exception
     */

    public int createPreventionFacilityChangeHistoryChem(PreventFacItem preventFacItem) throws Exception {
        this.preventFacMapper.createPreventionFacilityChangeHistoryChem(preventFacItem);
        return preventFacItem.getEairPreventFacChgHistNo();
    }

    /**
     * 선택된 방지시설 변경이력 수정
     *
     * @param preventFacChgHist
     *            방지시설 변경이력
     * @return 수정행수
     * @throws Exception
     */

    @Transactional
    public int updatePreventionFacilityChangeHistory(PreventFacChgHist preventFacChgHist) throws Exception {
        int count = this.preventFacMapper.updatePreventionFacilityChangeHistory(preventFacChgHist);
        if (count > 0) {
            this.preventFacMapper.deletePreventionFacilityChangeHistoryPollu(preventFacChgHist.getEairPreventFacChgHistNo());
            for (PreventFacItem eairTestItemCd : preventFacChgHist.getEairPolluCds()) {
                eairTestItemCd.setEairPreventFacChgHistNo(preventFacChgHist.getEairPreventFacChgHistNo());
                count += this.preventFacMapper.createPreventionFacilityChangeHistoryPollu(eairTestItemCd);
            }
        }

        if (count > 0) {
            this.preventFacMapper.deletePreventionFacilityChangeHistoryChem(preventFacChgHist.getEairPreventFacChgHistNo());
            for (PreventFacItem eairTestItemCd : preventFacChgHist.getEairChemCds()) {
                eairTestItemCd.setEairPreventFacChgHistNo(preventFacChgHist.getEairPreventFacChgHistNo());
                count += this.preventFacMapper.createPreventionFacilityChangeHistoryChem(eairTestItemCd);
            }
        }

        return count;
    }

    /**
     * 활성탄 교체 내역 조회
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체 내역
     * @throws Exception
     */
    public List<PreventFacActCarbHist> getPreventionFacActCarbHists(int eairPreventFacNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacActCarbHists(eairPreventFacNo, defaultParam);
    }

    /**
     * 활성탄 교체 내역단건 조회
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */
    public PreventFacActCarbHist getPreventionFacActCarbHist(int eairPreventFacActCarbHistNo) throws Exception {
        return this.preventFacMapper.getPreventionFacActCarbHist(eairPreventFacActCarbHistNo);
    }

    /**
     * 활성탄 교체내역 등록
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체 내역
     * @return 활성탄 교체
     * @throws Exception
     */

    public int createPreventionFacActCarbHist(PreventFacActCarbHist preventFacActCarbHist) throws Exception {
        return this.preventFacMapper.createPreventionFacActCarbHist(preventFacActCarbHist);
    }

    /**
     * 활성탄 교체내역 수정
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체내역
     * @return 활성탄 교체
     * @throws Exception
     */
    public int updatePreventionFacActCarbHist(PreventFacActCarbHist preventFacActCarbHist) throws Exception {

        return this.preventFacMapper.updatePreventionFacActCarbHist(preventFacActCarbHist);
    }

    /**
     * 활성탄 교체내역 삭제
     *
     * @param PreventFacActCarbHist
     *            활성탄 교체내역 삭제
     * @return 활성탄 교체
     * @throws Exception
     */

    public int deletePreventionFacActCarbHist(List<PreventFacActCarbHist> preventFacActCarbHist) throws Exception {

        int count = 0;
        for (PreventFacActCarbHist preventFacActCarbHistObject : preventFacActCarbHist) {
            count += this.preventFacMapper.deletePreventionFacActCarbHist(preventFacActCarbHistObject);
        }

        return count;
    }

    /**
     * 선택된 방지시설 변경이력 삭제
     *
     * @param preventFacChgHists
     *            방지시설 변경 이력 목록
     * @return 삭제행수
     * @throws Exception
     */

    @Transactional
    public int deletePreventionFacilityChangeHistory(List<PreventFacChgHist> preventFacChgHists) throws Exception {
        int count = 0;
        for (PreventFacChgHist preventFacChgHist : preventFacChgHists) {
            count += this.preventFacMapper.deletePreventionFacilityChangeHistory(preventFacChgHist.getEairPreventFacChgHistNo(), preventFacChgHist.getEairPreventFacNo());
        }

        return count;
    }

    // 방지시설 보수이력
    /**
     * 방지시설 보수이력 전체 조회
     *
     * @param eairPreventFacNo
     *            방지시설번호
     * @return 방지시설 보수이력 목록
     * @throws Exception
     */
    public List<PreventFacMaintHist> getPreventionFacilityMaintenanceHistories(int eairPreventFacNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacilityMaintenanceHistories(eairPreventFacNo, defaultParam);
    }

    /**
     * 선택된 방지시설 보수이력 상세 조회
     *
     * @param eairPreventFacMaintHistNo
     *            방지시설 보수이력번호
     * @return 방지시설 보수이력
     * @throws Exception
     */
    public PreventFacMaintHist getPreventionFacilityMaintenanceHistory(int eairPreventFacMaintHistNo, DefaultParam defaultParam) throws Exception {
        return this.preventFacMapper.getPreventionFacilityMaintenanceHistory(eairPreventFacMaintHistNo, defaultParam);
    }

    /**
     * 방지시설 보수이력 신규등록
     *
     * @param preventFacMaintHist
     *            방지시설 보수이력
     * @return 방지시설 보수이력 번호
     * @throws Exception
     */
    public int createPreventionFacilityMaintenanceHistory(PreventFacMaintHist preventFacMaintHist) throws Exception {
        this.preventFacMapper.createPreventionFacilityMaintenanceHistory(preventFacMaintHist);
        return preventFacMaintHist.getEairPreventFacMaintHistNo();
    }

    /**
     * 선택된 방지시설 보수이력 수정
     *
     * @param preventFacMaintHist
     *            방지시설 보수이력
     * @return 수정행수
     * @throws Exception
     */
    public int updatePreventionFacilityMaintenanceHistory(PreventFacMaintHist preventFacMaintHist) throws Exception {
        return this.preventFacMapper.updatePreventionFacilityMaintenanceHistory(preventFacMaintHist);
    }

    /**
     * 선택된 방지시설 보수이력 삭제
     *
     * @param eairPreventFacMaintHistNo
     *            방지시설 보수이력번호
     * @return 삭제행수
     * @throws Exception
     */
    @Transactional
    public int deletePreventionFacilityMaintenanceHistory(List<PreventFacMaintHist> preventFacMaintHists) throws Exception {
        int count = 0;
        for (PreventFacMaintHist preventFacMaintHist : preventFacMaintHists) {
            count += this.preventFacMapper.deletePreventionFacilityMaintenanceHistory(preventFacMaintHist.getEairPreventFacMaintHistNo());
        }

        return count;
    }

    /**
     * 방지시설명 중복체크
     *
     * @param eairPreventFacNo
     *            대기 오염 방지시설 번호
     * @param eairPreventFacNm
     *            대기 오염 방지시설 명칭
     * @param plantCd
     *            사업장코드
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkPreventionFacility(int eairPreventFacNo, String eairPreventFacNm, String plantCd) throws Exception {
        return this.preventFacMapper.checkPreventionFacility(eairPreventFacNo, eairPreventFacNm, plantCd);
    }

    /**
     * 고유방지시설번호 중복체크
     *
     * @param eairPreventFacInhNum
     *            고유방지시설번호
     * @return 중복 행 수
     * @throws Exception
     */
    public int checkPreventionInhNum(String eairPreventFacInhNum, int eairPreventFacNo) throws Exception {
        return this.preventFacMapper.checkPreventionInhNum(eairPreventFacInhNum, eairPreventFacNo);
    }

}
