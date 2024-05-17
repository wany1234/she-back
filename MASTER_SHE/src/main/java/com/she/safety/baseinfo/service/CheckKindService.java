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
package com.she.safety.baseinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.common.model.DefaultParam;
import com.she.safety.baseinfo.mapper.CheckKindMapper;
import com.she.safety.model.CheckKind;

/**
 * 안전점검종류 기능정의
 *
 */
@Service
public class CheckKindService {

    @Autowired
    private CheckKindMapper checkKindMapper;

    /**
     * 안전 점검 종류 리스트 조회
     * 
     * @param plantCd
     *            사업장코드
     * @param safCheckKindNm
     *            점검항목명
     * @param planUseYn
     *            점검계획사용여부
     * @param facilityUseYn
     *            설비점검해당여부
     * @param useYn
     *            사용여부
     * @param patrolYn
     *            순회여부
     * @return 점검 종류 목록
     * @throws Exception
     */
    public List<CheckKind> getCheckKindList(String plantCd, String safCheckKindNm, String planUseYn,
            String facilityUseYn, String useYn, String patrolYn, String subconUseYn, String congYn, String chngKind,
            DefaultParam defaultParam) throws Exception {
        return checkKindMapper.getCheckKindList(plantCd, safCheckKindNm, planUseYn, facilityUseYn, useYn, patrolYn,
                subconUseYn, congYn, chngKind, defaultParam);
    }

    /*
     * 안전 점검 종류 상세 조회
     * 
     * @param safCheckKindNo
     * 
     * @return 점검 종류 상세 조회
     * 
     * @throws Exception
     */
    public CheckKind getCheckKindItem(String safCheckKindNo) throws Exception {
        return checkKindMapper.getCheckKindItem(safCheckKindNo);
    }

    /*
     * 안전 점검 종류 생성
     * 
     * @param wkodChkItem 점검종류
     * 
     * @return 안전 점검 종류 번호
     * 
     * @throws Exception
     */
    public int createCheckKind(CheckKind checkKind) throws Exception {

        // 신규 등록시 id값은 null
        checkKind.setSafCheckKindNo(0);
        // 안전점검 종류명 중복 체크
        List<CheckKind> safCheckKind = checkKindMapper.checkCheckKind(checkKind);

        if (safCheckKind.size() == 0) {
            checkKindMapper.createCheckKind(checkKind);
            return checkKind.getSafCheckKindNo();
        } else {
            return 0;
        }

    }

    /*
     * 안전 점검 종류 수정
     * 
     * @param CheckKind 점검종류
     * 
     * @return 안전 점검 종류 번호
     * 
     * @throws Exception
     */
    public int updateCheckKind(CheckKind checkKind) throws Exception {
        // 안전점검 종류명 중복 체크
        List<CheckKind> safCheckKind = checkKindMapper.checkCheckKind(checkKind);

        if (safCheckKind.size() == 0) {
            checkKindMapper.updateCheckKind(checkKind);
            return checkKind.getSafCheckKindNo();
        } else {
            return 0;
        }
    }

    public List<CheckKind> checkCheckKind(CheckKind checkKind) throws Exception {
        return checkKindMapper.checkCheckKind(checkKind);
    }
}
