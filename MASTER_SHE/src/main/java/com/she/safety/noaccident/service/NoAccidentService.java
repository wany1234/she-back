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
package com.she.safety.noaccident.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.model.NoAccident;
import com.she.safety.noaccident.mapper.NoAccidentMapper;

/**
 * 사사업장고 기능정의
 *
 */
@Service
public class NoAccidentService {
    @Autowired
    private NoAccidentMapper noAccidentMapper;

    /**
     * 사업장 무사고 조회
     * 
     * @param startYmd
     *            무재해시작일
     * @param endSchYmd
     *            무재해달성예정일
     * @param plantCd
     *            사업장코드
     * @param searchFlag
     *            조회구분 'LIST'목록 'HIS' 이력
     * @return
     * @throws Exception
     */
    public List<NoAccident> getNoAccidents(String startYmd, String endSchYmd, String plantCd, String searchFlag, DefaultParam defaultParam) throws Exception {
        return noAccidentMapper.getNoAccidents(startYmd, endSchYmd, plantCd, searchFlag, defaultParam);
    }

    /**
     * 부서 무재해 조회
     * 
     * @param startYmd
     *            무재해시작일
     * @param endSchYmd
     *            무재해달성예정일
     * @param plantCd
     *            사업장코드
     * @param deptCd
     *            부서코드
     * @param searchFlag
     *            조회구분 'LIST'목록 'HIS' 이력
     * @return
     * @throws Exception
     */
    public List<NoAccident> getDeptNoAccidents(String startYmd, String endSchYmd, String plantCd, String deptCd, String searchFlag, String deptSubYn, DefaultParam defaultParam) throws Exception {
        return noAccidentMapper.getDeptNoAccidents(startYmd, endSchYmd, plantCd, deptCd, searchFlag, deptSubYn, defaultParam);
    }

    /**
     * 사업장 무사고 상세
     * 
     * @param safNoAccidentNo
     * @return
     * @throws Exception
     */
    public NoAccident getNoAccident(int safNoAccidentNo, DefaultParam defaultParam) throws Exception {
        return noAccidentMapper.getNoAccident(safNoAccidentNo, defaultParam);
    }

    /**
     * 부서 무사고 상세
     * 
     * @param safNoAccidentNo
     * @return
     * @throws Exception
     */
    public NoAccident getDeptNoAccident(int safNoAccidentDeptNo, DefaultParam defaultParam) throws Exception {
        return noAccidentMapper.getDeptNoAccident(safNoAccidentDeptNo, defaultParam);
    }

    /**
     * 활성화된 사업장 무사고 상세 조회
     * 
     * @return
     * @throws Exception
     */
    public NoAccident getNoAccidentLastDetail() throws Exception {
        return noAccidentMapper.getNoAccidentLastDetail();
    }

    /**
     * 사업장 무사고 등록
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int createNoAccident(NoAccident noaccident) throws Exception {
        /*
         * NoAccident lastnoaccident = getNoAccidentLastDetail(); try { Date
         * lastEndDate = new
         * SimpleDateFormat("yyyy-MM-dd").parse(lastnoaccident.getEndSchYmd());
         * Date newStartDate = new
         * SimpleDateFormat("yyyy-MM-dd").parse(noaccident.getStartYmd()); int
         * compare = newStartDate.compareTo(lastEndDate); // 무사고 건수 조회 int
         * countNoAcc = countNoAccident(); if (compare < 0 && (countNoAcc > 0))
         * { // 이전 등록된 무사고 종료날짜 업데이트 updateLastNoAccident(noaccident); } } catch
         * (ParseException e) { e.printStackTrace(); }
         */
        noAccidentMapper.createNoAccident(noaccident);
        int noaccidentNo = noaccident.getSafNoAccidentNo();
        return noaccidentNo;
    }

    /**
     * 사업장 무사고 수정
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int updateNoAccident(NoAccident noaccident) throws Exception {
        noAccidentMapper.updateNoAccident(noaccident);
        int noaccidentNo = noaccident.getSafNoAccidentNo();
        return noaccidentNo;
    }

    /**
     * 사업장 무재해 삭제
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int deleteNoAccident(NoAccident noaccident) throws Exception {
        return noAccidentMapper.deleteNoAccident(noaccident.getSafNoAccidentNo(), noaccident.getUpdateUserId());
    }

    /**
     * 부서 무사고 등록
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int createDeptNoAccident(NoAccident noaccident) throws Exception {
        noAccidentMapper.createDeptNoAccident(noaccident);
        int noaccidentNo = noaccident.getSafNoAccidentDeptNo();
        return noaccidentNo;
    }

    /**
     * 부서 무사고 수정
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int updateDeptNoAccident(NoAccident noaccident) throws Exception {
        noAccidentMapper.updateDeptNoAccident(noaccident);
        int noaccidentNo = noaccident.getSafNoAccidentDeptNo();
        return noaccidentNo;
    }

    /**
     * 부서 무재해 삭제 처리
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int deleteDeptNoAccident(NoAccident noaccident) throws Exception {
        return noAccidentMapper.deleteDeptNoAccident(noaccident.getSafNoAccidentDeptNo(), noaccident.getUpdateUserId());
    }

    /**
     * 활성화된 사업장 무사고 수정
     * 
     * @param noaccident
     * @return
     * @throws Exception
     */
    public int updateLastNoAccident(NoAccident noaccident) throws Exception {
        int countNoAcc = countNoAccident(noaccident.getPlantCd(), null);
        noaccident.setCountNoAccount(countNoAcc);
        int res = noAccidentMapper.updateLastNoAccident(noaccident);
        return res;
    }

    /**
     * 사업장 무재해 건수 조회
     * 
     * @return
     * @throws Exception
     */
    public int countNoAccident(String plantCd, DefaultParam defaultParam) throws Exception {
        int res = noAccidentMapper.countNoAccident(plantCd, defaultParam);
        return res;
    }

    /**
     * 부서 무재해 건수 조회
     * 
     * @return
     * @throws Exception
     */
    public int countDeptNoAccident(String plantCd, String deptCd, DefaultParam defaultParam) throws Exception {
        int res = noAccidentMapper.countDeptNoAccident(plantCd, deptCd, defaultParam);
        return res;
    }
}
