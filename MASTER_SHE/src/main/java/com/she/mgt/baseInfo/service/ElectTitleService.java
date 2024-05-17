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

import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.she.common.model.DefaultParam;
import com.she.mgt.baseInfo.mapper.ElectTitleMapper;
import com.she.mgt.model.ElectTitlItem;
import com.she.mgt.model.ElectTitlLcn;
import com.she.mgt.model.ElectTitle;

@Service
public class ElectTitleService {

    @Autowired
    private ElectTitleMapper electTitleMapper;

    /**
     * 선해임명 조회
     *
     * @param refLawCd
     *            구분
     * @param electAttCd
     *            분야
     * @param electTitlNm
     *            선해임명명
     * @param useYn
     *            사용여부
     * @return 선해임명 목록
     * @throws Exception
     */
    public List<ElectTitle> getElectTitles(String refLawCd, String electAttCd, String electTitlNm, String useYn, String electClsCd, String evalTgtYn, String plantCd, DefaultParam defaultParam) throws Exception {
        return electTitleMapper.getElectTitles(refLawCd, electAttCd, electTitlNm, useYn, electClsCd, evalTgtYn, plantCd, defaultParam);
    }

    /**
     * 선해임명 상세 조회
     *
     * @param safElectTitlNo
     *            선해임명번호
     * @return 선해임명
     * @throws Exception
     */
    public ElectTitle getElectTitle(int safElectTitlNo, DefaultParam defaultParam) throws Exception {
        ElectTitle electTitle = this.electTitleMapper.getElectTitle(safElectTitlNo, defaultParam);
        electTitle.setElectTitleItems(electTitleMapper.getElectTitlItems(safElectTitlNo));
        return electTitle;
    }

    /**
     * 선해임명 신규등록
     *
     * @param electTitle
     *            선해임명
     * @return 선해임명번호
     * @throws Exception
     */
    @Transactional
    public int createElectTitle(ElectTitle electTitle) throws Exception {
        this.electTitleMapper.createElectTitle(electTitle);
        int safElectTitlNo = electTitle.getSafElectTitlNo();
        this.electTitleMapper.deleteElectTitlItems(safElectTitlNo);
        if (CollectionUtils.isNotEmpty(electTitle.getElectTitleItems())) {
            for (ElectTitlItem electTitlItem : electTitle.getElectTitleItems()) {
                electTitlItem.setSafElectTitlNo(safElectTitlNo);
                this.electTitleMapper.createElectTitlItems(electTitlItem);
            }

        }

        // 자격증 저장
        // this.saveElectTitlLcn(electTitle);

        return electTitle.getSafElectTitlNo();
    }

    /**
     * 선해임명 수정
     *
     * @param electTitle
     *            선해임명
     * @return 수정 행 수
     * @throws Exception
     */
    @Transactional
    public int updateElectTitle(ElectTitle electTitle) throws Exception {
        this.electTitleMapper.updateElectTitle(electTitle);
        int safElectTitlNo = electTitle.getSafElectTitlNo();
        this.electTitleMapper.deleteElectTitlItems(safElectTitlNo);
        if (CollectionUtils.isNotEmpty(electTitle.getElectTitleItems())) {
            for (ElectTitlItem electTitlItem : electTitle.getElectTitleItems()) {
                electTitlItem.setSafElectTitlNo(safElectTitlNo);
                this.electTitleMapper.createElectTitlItems(electTitlItem);
            }
        }
        return electTitle.getSafElectTitlNo();
    }

    public int saveElectTitlLcn(ElectTitle electTitle) throws Exception {
        int resultNo = 0;

        if (electTitle.getLcnTypeCds() != null) {
            // 선해임명번호에 따른 자격증 일괄삭제
            resultNo += this.electTitleMapper.deleteElectTitlLcn(electTitle.getSafElectTitlNo());

            if (electTitle.getLcnTypeCds().size() > 0) {
                for (String lcnTypeCd : electTitle.getLcnTypeCds()) {
                    ElectTitlLcn electTitlLcn = new ElectTitlLcn();
                    electTitlLcn.setSafElectTitlNo(electTitle.getSafElectTitlNo());
                    electTitlLcn.setLcnTypeCd(lcnTypeCd);
                    // 자격증 등록
                    resultNo += this.electTitleMapper.createElectTitlLcn(electTitlLcn);
                }
            }
        }

        return resultNo;
    }

    /**
     * 선해임명명 체크
     *
     * @param electTitlNm
     *            선해임명명
     * @param safElectTitlNo
     *            선해임명번호
     * @return 체크 값
     * @throws Exception
     */
    public List<HashMap<String, Object>> getCheckElectTitle(String electTitlNm, int safElectTitlNo) throws Exception {
        return electTitleMapper.getCheckElectTitle(electTitlNm, safElectTitlNo);
    }

}
