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
import com.she.safety.baseinfo.mapper.CheckItemMapper;
import com.she.safety.model.CheckItem;

/**
 * 안전점검항목 기능정의
 *
 */
@Service
public class CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    /*
     * 안전 점검 항목 List 조회
     *
     * @param parameter (사업장코드, 점검종류 번호, 안전점검항목명, 사용여부)
     *
     * @return 안전점검항목 List
     *
     * @throws Exception
     */
    public List<CheckItem> getCheckItemList(String plantCd, int safCheckKindNo, String safCheckTypeNm, String useYn, String chngKind, DefaultParam defaultParam) throws Exception {
        return checkItemMapper.getCheckItemList(plantCd, safCheckKindNo, safCheckTypeNm, useYn, chngKind, defaultParam);
    }

    /*
     * 안전 점검 항목 상세 조회
     *
     * @param parameter (점검항목번호)
     *
     * @return 안전점검항목
     *
     * @throws Exception
     */
    public CheckItem getCheckItem(int safCheckItemNo) throws Exception {
        return checkItemMapper.getCheckItem(safCheckItemNo);
    }

    /*
     * 안전 점검 항목 신규
     *
     * @param CheckItem 안전 점검 항목
     *
     * @return 점검항목번호
     *
     * @throws Exception
     */
    public int createCheckItem(CheckItem checkItem) throws Exception {
        // 신규 등록시 id값은 null
        checkItem.setSafCheckItemNo(0);
        // 안전 점검명 중복 검사
        List<CheckItem> safCheckItem = checkItemMapper.checkCheckItem(checkItem);

        if (safCheckItem.size() == 0) {
            checkItemMapper.createCheckItem(checkItem);
            return checkItem.getSafCheckItemNo();
        } else {
            return 0;
        }
    }

    /*
     * 안전 점검 항목 수정
     *
     * @param CheckItem 안전 점검 항목
     *
     * @return 점검항목번호
     *
     * @throws Exception
     */
    public int updateCheckItem(CheckItem checkItem) throws Exception {
        // 안전 점검명 중복 검사
        List<CheckItem> safCheckItem = checkItemMapper.checkCheckItem(checkItem);

        if (safCheckItem.size() == 0) {
            checkItemMapper.updateCheckItem(checkItem);
            return checkItem.getSafCheckItemNo();

        } else {
            return 0;
        }

    }

    public List<CheckItem> checkCheckItem(CheckItem checkItem) throws Exception {
        return checkItemMapper.checkCheckItem(checkItem);
    }
}
