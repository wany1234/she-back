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
package com.she.mgt.mgtLaw.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.she.manage.model.CodeMaster;
import com.she.mgt.mgtLaw.mapper.SheLawRevisionMapper;
import com.she.mgt.model.MgtLawRevision;

@Service
public class SheLawRevisionService {
    @Autowired
    private SheLawRevisionMapper sheLawRevisionMapper;

    /***
     * 개정법규 목록 조회
     *
     * @param lkey
     *            법령ID
     * @param stYd
     *            시작일
     * @param edYd
     *            종료일
     * @return 개정법규 목록
     * @throws Exception
     */
    public List<MgtLawRevision> getLawRevisionList(String lkey, String stYd, String edYd) throws Exception {
        return sheLawRevisionMapper.getLawRevisionList(lkey, stYd, edYd);
    }

    /***
     * 법령 콤보 조회
     *
     * @return 법령
     * @throws Exception
     */
    public List<CodeMaster> getLawRevisionCombo() throws Exception {
        return sheLawRevisionMapper.getLawRevisionCombo();
    }


    /***
     * 법조 조회
     *
     * @return 법조
     * @throws Exception
     */
    public List<HashMap<String, Object>> getLawJomun(String legiKey) {
        return sheLawRevisionMapper.getLawJomun(legiKey);
    }
}
