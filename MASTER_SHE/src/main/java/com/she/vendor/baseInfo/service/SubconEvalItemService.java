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
package com.she.vendor.baseInfo.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.vendor.baseInfo.mapper.SubconEvalItemMapper;
import com.she.vendor.model.SubconEvalItem;

@Service
public class SubconEvalItemService {
    @Autowired
    private SubconEvalItemMapper subconEvalItemMapper;

    /**
     * 협력업체평가항목 조회
     * 
     * @param subconEvalClsCd
     *            평가구분코드
     * @param subconEvalItemNm
     *            협력업체평가항목명
     * @param useYn
     *            사용여부
     * @return 협력업체평가항목 목록
     * @throws Exception
     */
    public List<SubconEvalItem> getSubconEvalItems(String subconEvalClsCd, String subconEvalItemNm, String useYn, DefaultParam defaultParam)
            throws Exception {
        return subconEvalItemMapper.getSubconEvalItems(subconEvalClsCd, subconEvalItemNm, useYn, defaultParam);
    }

    /**
     * 협력업체평가항목 상세 조회
     * 
     * @param safSubconEvalItemNo
     *            협력업체평가항목번호
     * @return 협력업체평가항목
     * @throws Exception
     */
    public SubconEvalItem getSubconEvalItem(int safSubconEvalItemNo, DefaultParam defaultParam) throws Exception {
        return this.subconEvalItemMapper.getSubconEvalItem(safSubconEvalItemNo, defaultParam);
    }

    /**
     * 협력업체평가항목 신규등록
     * 
     * @param subconEvalItem
     *            협력업체평가항목
     * @return 협력업체평가항목번호
     * @throws Exception
     */
    @Transactional
    public int createSubconEvalItem(SubconEvalItem subconEvalItem) throws Exception {
        this.subconEvalItemMapper.createSubconEvalItem(subconEvalItem);

        return subconEvalItem.getSafSubconEvalItemNo();
    }

    /**
     * 협력업체평가항목 수정
     * 
     * @param subconEvalItem
     *            협력업체평가항목
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateSubconEvalItem(SubconEvalItem subconEvalItem) throws Exception {
        return this.subconEvalItemMapper.updateSubconEvalItem(subconEvalItem);
    }

    /**
     * 협력업체평가항목명 체크
     * 
     * @param subconEvalItemNm
     *            협력업체평가항목명
     * @param safSubconEvalItemNo
     *            협력업체평가항목번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckSubconEvalItem(String subconEvalItemNm, int safSubconEvalItemNo)
            throws Exception {
        return subconEvalItemMapper.getCheckSubconEvalItem(subconEvalItemNm, safSubconEvalItemNo);
    }

}
