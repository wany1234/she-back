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
package com.she.mgt.baseInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.mgt.baseInfo.mapper.LicensingStatusMapper;
import com.she.mgt.model.LicensingStatus;

@Service
public class LicensingStatusService {
    @Autowired
    private LicensingStatusMapper licensingStatusMapper;

    /**
     * 경영 인허가현황 조회
     * 
     * @param fromDate,
     *            toDate, title, kindCd
     * @return 인허가현황
     * @throws Exception
     */
    public List<LicensingStatus> getLicensingStatusList(String fromDate, String toDate, String title, String kindCd, String plantCd, DefaultParam defaultParam) throws Exception {
        return licensingStatusMapper.getLicensingStatusList(fromDate, toDate, title, kindCd, plantCd, defaultParam);
    }

    /**
     * 경영 인허가현황 개정 조회
     * 
     * @param fromDate,
     *            toDate, title, kindCd
     * @return 인허가현황
     * @throws Exception
     */

    public List<LicensingStatus> getLicensingStatusRevisionList(String lcnBoardNo, DefaultParam defaultParam) throws Exception {
        return licensingStatusMapper.getLicensingStatusRevisionList(lcnBoardNo, defaultParam);
    }

    /**
     * 경영 인허가현황 단건 조회
     * 
     * @param lcnBoardNo
     * @return 건수
     * @throws Exception
     */
    public LicensingStatus getLicensingStatus(int lcnBoardNo, DefaultParam defaultParam) throws Exception {
        return licensingStatusMapper.getLicensingStatus(lcnBoardNo, defaultParam);
    }

    /**
     * 경영 인허가현황 삭제
     * 
     * @param lcnBoardNo
     * @return 건수
     * @throws Exception
     */
    public int deleteLicensingStatus(int lcnBoardNo) throws Exception {
        return licensingStatusMapper.deleteLicensingStatus(lcnBoardNo);
    }

    /**
     * 경영 인허가현황 개정이력 삭제
     * 
     * @param LicensingStatus
     * @return 생성 행 수
     * @throws Exception
     */
    @Transactional
    public int revDeleteLicensingStatus(List<LicensingStatus> licensingStatus) throws Exception {
        int count = 0;
        for (LicensingStatus licensingStatu : licensingStatus) {
            count += this.licensingStatusMapper.revDeleteLicensingStatus(licensingStatu);
        }

        return count;
    }

    /**
     * 경영 인허가현황 등록
     * 
     * @param LicensingStatus
     * @return 인허가현황 등록 수
     * @throws Exception
     */
    public int insertLicensingStatus(LicensingStatus dto) throws Exception {
        int insertCnt = this.licensingStatusMapper.insertLicensingStatus(dto);
        return insertCnt;
    }

    /**
     * 경영 인허가현황 수정
     * 
     * @param LicensingStatus
     * @return 인허가현황 수정 수
     * @throws Exception
     */
    public int updateLicensingStatus(LicensingStatus dto) throws Exception {
        int updateCnt = this.licensingStatusMapper.updateLicensingStatus(dto);
        return updateCnt;

    }

}
