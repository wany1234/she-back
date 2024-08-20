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
package com.she.mgt.elect.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.manage.model.CodeMaster;
import com.she.manage.service.CodeMasterService;
import com.she.mgt.elect.mapper.ElectHisMapper;
import com.she.mgt.model.ElectHis;
import com.she.mgt.model.ElectTitlLcn;

@Service
public class ElectHisService {
    @Autowired
    private ElectHisMapper electHisMapper;

    @Autowired
    private CodeMasterService codeMasterService;

    /**
     * 선해임 조회
     * 
     * @param lcnGetStartDt
     *            선해임일자시작일
     * @param lcnGetEndDt
     *            선해임일자종료일
     * @param electTypeCd
     *            구분
     * @param userNm
     *            선임자
     * @param safElectTitlNo
     *            선해임명번호
     * @return 선해임 목록
     * @throws Exception
     */
    public List<ElectHis> getElectHises(String lcnGetStartDt, String lcnGetEndDt, String plantCd, String userNm, int safElectTitlNo, String testEndDt, String employmentYn, DefaultParam defaultParam) throws Exception {
        return electHisMapper.getElectHises(lcnGetStartDt, lcnGetEndDt, plantCd, userNm, safElectTitlNo, testEndDt, employmentYn, defaultParam);
    }

    // 법정선임자 현황 통계 조회

    public List<HashMap<String, Object>> getElectStatus(String plantCd, int safElectTitlNo, DefaultParam defaultParam) throws Exception {

        List<CodeMaster> plantCdList = codeMasterService.getSelect("COM_PLANT_CD", "Y", defaultParam);
        List<String> plantCds = new ArrayList<String>();
        for (CodeMaster cm : plantCdList) {
            plantCds.add(cm.getCode());
        }
        return electHisMapper.getElectStatus(plantCd, safElectTitlNo, plantCds, defaultParam);
    }

    /**
     * 선해임 상세 조회
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 선해임
     * @throws Exception
     */
    public ElectHis getElectHis(int safElectHisNo, DefaultParam defaultParam) throws Exception {
        return this.electHisMapper.getElectHis(safElectHisNo, defaultParam);
    }

    /**
     * 선해임 이력 조회
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 선해임
     * @throws Exception
     */
    public List<ElectHis> getElectHisesRevs(int safElectHisNo, DefaultParam defaultParam) throws Exception {
        return this.electHisMapper.getElectHisesRevs(safElectHisNo, defaultParam);
    }

    /**
     * 선해임 신규등록
     * 
     * @param electHis
     *            선해임
     * @return 선해임번호
     * @throws Exception
     */
    @Transactional
    public int createElectHis(ElectHis electHis) throws Exception {
        if (electHis.getSafElectHisGrpNo() > 0) {
            this.electHisMapper.updateOldElectHisUseYn(electHis.getSafElectHisGrpNo());
        }
        this.electHisMapper.createElectHis(electHis);
        return electHis.getSafElectHisNo();
    }

    /**
     * 선해임 수정
     * 
     * @param electHis
     *            선해임
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateElectHis(ElectHis electHis) throws Exception {
        int count = this.electHisMapper.updateElectHis(electHis);
        return electHis.getSafElectHisNo();
    }

    /**
     * 선해임 삭제
     * 
     * @param safElectHisNo
     *            선해임번호
     * @return 삭제 행 수
     * @throws Exception
     */
    public int deleteElectHis(int safElectHisNo) throws Exception {
        return this.electHisMapper.deleteElectHis(safElectHisNo);
    }

    /**
     * 선해임 개정이력 삭제
     * 
     * @param ElectHis
     * @return 생성 행 수
     * @throws Exception
     */
    @Transactional
    public int revDeleteElectHis(List<ElectHis> electHis) throws Exception {
        int count = 0;
        for (ElectHis electHi : electHis) {
            count += this.electHisMapper.revDeleteElectHis(electHi);
        }

        return count;
    }

    /**
     * 선해임 체크
     * 
     * @param userId
     *            선해임자 ID
     * @param safElectTitlNo
     *            선해임명번호
     * @param safElectLcnNo
     *            자격증번호
     * @param safElectHisNo
     *            선해임번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckElectHis(String userId, int safElectTitlNo, int safElectLcnNo, int safElectHisNo, DefaultParam defaultParam) throws Exception {
        return electHisMapper.getCheckElectHis(userId, safElectTitlNo, safElectLcnNo, safElectHisNo, defaultParam);
    }

    /**
     * 선해임 체크
     * 
     * @param userId
     *            선해임자 ID
     * @param safElectTitlNo
     *            선해임명번호
     * @param plantCd
     *            사업장 번호
     * @return 체크 값
     * @throws Exception
     */
    public int countElectHisNm(String plantCd, String userId, String safElectTitlNo) throws Exception {
        return electHisMapper.countElectHisNm(plantCd, userId, safElectTitlNo);
    }

    /**
     * HR 자격 조회
     * 
     * @param userId
     * @param userNm
     * @param licenseNm
     * @param plantCd
     * @param pageNumber
     * @param pageSize
     * @param orderByExpression
     * @return
     * @throws Exception
     */
    public List<ElectTitlLcn> getHrElectTitlLcns(String userId, String userNm, String licenseNm, String plantCd, int pageNumber, int pageSize, String orderByExpression, DefaultParam defaultParam) throws Exception {
        return electHisMapper.getHrElectTitlLcns(userId, userNm, licenseNm, plantCd, pageNumber, pageSize, orderByExpression, defaultParam);
    }

    public int getHrElectTitlLcnsTotSize(String userId, String userNm, String licenseNm, String plantCd) throws Exception {
        return electHisMapper.getHrElectTitlLcnsTotSize(userId, userNm, licenseNm, plantCd);
    }

}
